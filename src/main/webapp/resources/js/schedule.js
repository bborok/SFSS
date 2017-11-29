var dateTimeFormat = "YYYY-MM-DD HH:mm:ss";
var dateFormat = "YYYY-MM-DD";
var timeFormat = "HH:mm:ss";
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var calendar;

var surreyShiftColor = "#9ccc65";
var burnabyShiftColor = "#9fa8da";
var vancouverShiftColor = "#bbdefb";

$(document).ready(function () {

    var timePickerOptions = {
        format: 'LT',
        stepping: 15
    };

    $('#date').datetimepicker({
        format: 'LL'
    });
    $('#date').data("DateTimePicker").minDate(moment());
    $('#startTime').datetimepicker(timePickerOptions);
    $('#endTime').datetimepicker(timePickerOptions);

    $('#burnabyCheckboxText').css('background-color', burnabyShiftColor);
    $('#surreyCheckboxText').css('background-color', surreyShiftColor);
    $('#vancouverCheckboxText').css('background-color', vancouverShiftColor);

    csrfAndAjaxSetup();
    initCalendar();
    initEventHandlers();
});

function csrfAndAjaxSetup() {
    //CSRF Setup, needed for AJAX requests
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        jqXHR.setRequestHeader(header, token);
    });
}

function initCalendar() {
    // Initialize the calendar for an admin, supervisor or team_leader...
    var calendarInitObject = {
        eventSources: [
            api + '/shifts'
        ],
        customButtons: {
            add_event: {
                text: 'Add a Shift',
                click: function () {
                    emptyDateAndTimeInputFields();
                    $('#createEventModal').modal('show'); //popup modal
                }
            }
        },
        timezone: 'local',
        viewRender: function (view) {
            var height;
            if (view.name === "month") {
                height = 700;
            } else {
                height = 700;
            }
            calendar.fullCalendar('option', 'contentHeight', height);
        },

        header: {
            left: 'prev,next today, add_event',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },

        selectable: true,

        eventRender: eventRenderHandler,

        eventAfterRender: function (event, element) {
            $(element).css('width', '80%');
        },

        //Selecting an empty area
        select: selectEmptyAreaEventHandler,

        //Selecting a scheduled event
        eventClick: selectScheduledEventHandler,

        navLinks: true, // can click day/week names to navigate views
        weekNumbers: true,
        weekNumbersWithinDays: true,
        weekNumberCalculation: 'ISO',
        editable: false,
        eventTextColor: "#000000"
    };

    //Overwrite some settings of the calendarInitObject if a member of volunteer
    if (loggedInUser.role === 'MEMBER' || loggedInUser.role === 'VOLUNTEER') {
        calendarInitObject.customButtons = {};
        calendarInitObject.header = {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        };
        calendarInitObject.selectable = false;
    }

    //Finally init the calendar
    calendar = $('#calendar');
    calendar.fullCalendar(calendarInitObject);
}

function initEventHandlers() {
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
        doSubmit();
    });

    $('.campusFilter').prop("checked", true);// everything is checked

    $('input:checkbox.allOrNone').on('change', function () {
        calendar.fullCalendar('rerenderEvents');
    });


    $('input:checkbox.campusFilter').on('change', function () {
        calendar.fullCalendar('rerenderEvents');
    });

    $('#shiftSelect').on('change', function () {
        calendar.fullCalendar('rerenderEvents');
    });

    $(function () {
        $("input:checked").each(function () {
            addItemsFromArray(eval("i" + this.id));
        });
        $("input:checkbox").change(function () {
            $("#shiftSelect").html("");
            $("input:checked").each(function () {
                addItemsFromArray(eval("i" + this.id));
            });
        });

        function addItemsFromArray(arr) {
            $('#shiftSelect').append('<option value ="' + 'all' + '">' + 'All Shifts' + '</option>');
            $.each(arr, function (i, v) {
                $("#shiftSelect").append('<option value="' + v + '">' + v + '</option>');
            });
        }
    });

    //Change the dropdown options when selecting a campus in the Assign/Edit a Shift form.
    $("#campusSelect").on('change', function () {
        $('#eventShiftSelect').empty();
        var selectedCampus = $("#campusSelect").val();
        if (selectedCampus === null) {
            $('#eventShiftSelect').append($("<option></option>").val('').attr('disabled', 'disabled').attr('selected', 'selected').text('Please Select a Campus'));
            return;
        }

        selectedCampus = selectedCampus.toUpperCase();

        var shiftsToDisplay;
        if (selectedCampus === 'BURNABY') shiftsToDisplay = iBURNABY;
        else if (selectedCampus === 'SURREY') shiftsToDisplay = iSURREY;
        else if (selectedCampus === 'VANCOUVER') shiftsToDisplay = iVANCOUVER;
        shiftsToDisplay.forEach(function (shift) {
            $('#eventShiftSelect').append($("<option></option>").val(shift).text(shift));
        })
    });

    $("#btnTimecard").off().on('click', function () {
        $(location).attr('href', contextPath + '/timecard?shift_id=' + $('#shiftID').val() + '&username=' + $('#memberSelect').val());
        $('#createEventModal').modal('hide');
    });
}

var eventRenderHandler = function (event, element) {
    if (event.campus === 'BURNABY') {
        element.css('background-color', burnabyShiftColor);
    } else if (event.campus === "SURREY") {
        element.css('background-color', surreyShiftColor);
    } else if (event.campus === "VANCOUVER") {
        element.css('background-color', vancouverShiftColor);
    }

    if (event.confirmationStatus === "NO_RESPONSE") {
        element.css({
            "border-style": "solid",
            "border-width": "3px",
            "border-color": "#ff8f00"
        })
    } else if (event.confirmationStatus === "CONFIRMED") {
        element.css({
            "border-style": "solid",
            "border-width": "3px",
            "border-color": "#00600f"
        })
    } else {
        element.css({
            "border-style": "solid",
            "border-width": "3px",
            "border-color": "#a30000"
        })
    }
    return filter(event);
};

var selectEmptyAreaEventHandler = function (start, end) {
    $('#modalTitle').text('Assign a Shift');
    $('#campusSelect').trigger('change');
    $('#date').data("DateTimePicker").date(start);
    $('#startTime').data("DateTimePicker").date(start);
    $('#endTime').data("DateTimePicker").date(end);
    $('#availabilitySelect').val("NO_RESPONSE");
    conditionallyRenderButtonsAndInputs(start, end, false);
};

var selectScheduledEventHandler = function (event) {
    $('#modalTitle').text('Edit Shift');
    console.log(event.end);
    console.log(moment(event.end).format(dateTimeFormat));
    $('#shiftID').val(event.id);
    $('#campusSelect').val(event.campus).trigger('change');
    $('#eventShiftSelect').val(event.title);

    $('#date').data("DateTimePicker").date(moment(event.date));
    $('#startTime').data("DateTimePicker").date(moment(event.start));
    $('#endTime').data("DateTimePicker").date(moment(event.end));

    $('#eventLocation').val(event.location);
    $('#eventRequiredTraining').val(event.requiredTraining);
    $('#memberSelect').val(event.username);
    $('#eventNotes').val(event.notes);
    $('#availabilitySelect').val(event.confirmationStatus);

    $('#btnDelete').off().on('click', function (e) {
        e.preventDefault();
        deleteShift(event);
        $('#createEventModal').modal('hide');
    });

    conditionallyRenderButtonsAndInputs(event.start, event.end, event.isTimeCardSubmitted);
};


function conditionallyRenderButtonsAndInputs(startDateTime, endDateTime, isTimeCardSubmitted) {

    var shiftStartDateTime = moment(startDateTime).format(dateTimeFormat);
    var shiftEndDateTime = moment(endDateTime).format(dateTimeFormat);
    var currentDateTime = moment().format(dateTimeFormat);
    //Conditionally Render
    if (loggedInUser.role === 'ADMIN' || loggedInUser.role === 'SUPERVISOR' || loggedInUser.role === 'TEAM_LEADER') {
        showAllConditionalButtons();
        enableAllInputElementsInForm();


        //Stop ADMINS, SUPERVISORS AND TEAM_LEADERS from changing the start and end times if the shift has already passed
        if (moment(currentDateTime).isAfter(shiftEndDateTime)) {
            $('#date input').prop('disabled', true);
            $('#startTime input').prop('disabled', true);
            $('#endTime input').prop('disabled', true);
        }

        $('#createEventModal').modal(); //popup modal
        return;
    }

    if (loggedInUser.role === 'VOLUNTEER' || loggedInUser.role === 'MEMBER') {
        //Start with disabling all forms of input and buttons
        disableAllInputElementsInForm();
        hideAllConditionalButtons();
        //Enable the availability dropdown if the shift hasn't started yet
        if (moment(currentDateTime).isBefore(shiftStartDateTime)) {
            $("#availabilitySelect").prop('disabled', false);
            $("#submitButton").show();
        }
        //Render timecard button if the start of the shift has already passed and the timecard has not yet been submitted
        if (moment(currentDateTime).isAfter(shiftStartDateTime) && isTimeCardSubmitted === false) {
            $("#btnTimecard").show();
        }
    }
    $('#createEventModal').modal(); //popup modal
}


//Creates a Shift object based on the form input fields and passes it to the saveShift()
function doSubmit() {
    var date = $('#date').data("DateTimePicker").date().format(dateFormat);
    var startTime = $('#startTime').data("DateTimePicker").date().format(timeFormat);
    var endTime = $('#endTime').data("DateTimePicker").date().format(timeFormat);

    var shift = {
        id: $('#shiftID').val(),
        title: $('#eventShiftSelect').val(),
        date: date,
        start: convertToDateFormat(date, startTime),
        end: convertToDateFormat(date, endTime),
        campus: $('#campusSelect').val(),
        username: $('#memberSelect').val(),
        location: $('#eventLocation').val(),
        notes: $('#eventNotes').val(),
        requiredTraining: $('#eventRequiredTraining').val(),
        confirmationStatus: $('#availabilitySelect').val()
    };
    saveShift(shift);
    $("#createEventModal").modal('hide');
}

function emptyDateAndTimeInputFields() {
    $('#eventCampus').val("");
    $('#memberSelect').val("");
    $('#eventRequiredTraining').val("");
}

//Sends a requests via AJAX to save a shift
function saveShift(shift) {
    var url = api + '/shift/save';
    $.ajax({
        headers: {
            Accept: "text/plain"
        },
        type: 'POST',
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(shift),
        success: function () {
            $.notify("Saved shift.", "success");
            calendar.fullCalendar('refetchEvents');
        },
        error: function () {
            $.notify("Error saving shift", "warn");
        }
    });
}

//Sends a request via AJAX to delete a shift
function deleteShift(event) {
    $.ajax({
        type: 'DELETE',
        headers: {
            Accept: "text/plain"
        },
        url: api + '/shift/delete/' + event.id,
        success: function () {
            $.notify("Deleted shift.", "success");
            calendar.fullCalendar('refetchEvents');
        },
        error: function () {
            $.notify("Error deleting shift", "warn");
        }
    });
}

function filter(calEvent) {
    var vals = [];
    $('input:checkbox.campusFilter:checked').each(function () {
        vals.push($(this).val());
    });

    var vals2 = [];
    $('#shiftSelect option:selected').each(function () {
        vals2.push($(this).val());
    });

    $('.allOrNone').on('click', function () { //
        if ($('.allOrNone').is(':checked')) {
            $('.campusFilter').prop("checked", true)

        } else {
            $('.campusFilter').prop("checked", false);
        }
    });

    if ($('#shiftSelect').val() === null) {
        return vals.indexOf(calEvent.campus) !== -1;
    }
    if ($('#shiftSelect option:selected').val() === "all") {
        return vals.indexOf(calEvent.campus) !== -1;
    }

    return vals.indexOf(calEvent.campus) !== -1 && vals2.indexOf(calEvent.title) !== -1;
}

function convertToDateFormat(start, end) {
    return start + ' ' + end;
}

function showAllConditionalButtons() {
    $('#btnTimecard').show();
    $('#btnDelete').show();
    $('#submitButton').show();
}

function hideAllConditionalButtons() {
    $('#btnTimecard').hide();
    $('#btnDelete').hide();
    $('#submitButton').hide();
}

function enableAllInputElementsInForm() {
    $('#createAppointmentForm input').prop('disabled', false);
    $('#createAppointmentForm select').prop('disabled', false);
    $('#createAppointmentForm textarea').prop('disabled', false);
}

function disableAllInputElementsInForm() {
    $('#createAppointmentForm input').prop('disabled', true);
    $('#createAppointmentForm select').prop('disabled', true);
    $('#createAppointmentForm textarea').prop('disabled', true);
}