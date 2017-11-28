var dateTimeFormat = "YYYY-MM-DD HH:mm:ss";
var dateFormat = "YYYY-MM-DD";
var timeFormat = "HH:mm:ss";
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var calendar;

$(document).ready(function () {

    var timePickerOptions = {
        format: 'LT',
        stepping: 15
    };

    $('#date').datetimepicker({
        format: 'LL'
    });
    $('#startTime').datetimepicker(timePickerOptions);
    $('#endTime').datetimepicker(timePickerOptions);
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
                    resetFormFields();
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
    });
}

var eventRenderHandler = function (event, element) {
    if (event.campus === 'BURNABY') {
        element.css('background-color', '#9fa8da');
    } else if (event.campus === "SURREY") {
        element.css('background-color', '#9ccc65');
    } else if (event.campus === "VANCOUVER") {
        element.css('background-color', '#bbdefb');
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
    conditionallyRender(start, end, false);
};

var selectScheduledEventHandler = function (event) {
    console.log(event);
    $('#modalTitle').text('Edit Shift');

    $('#shiftID').val(event.id);
    $('#campusSelect').val(event.campus).trigger('change');
    $('#eventShiftSelect').val(event.title);

    $('#date').data("DateTimePicker").date(moment(event.date));
    $('#startTime').data("DateTimePicker").date(event.start);
    $('#endTime').data("DateTimePicker").date(event.end);

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

    conditionallyRender(event.start, event.end, event.isTimeCardSubmitted);
};


function conditionallyRender(start, end, isTimeCardSubmitted) {
    console.log(isTimeCardSubmitted);
    //Condtionally Render
    if (loggedInUser.role === 'ADMIN' || loggedInUser.role === 'SUPERVISOR') {
        showAllConditionalButtons();
        enableAllInputElementsInForm();
        $('#createEventModal').modal(); //popup modal
        return;
    }

    if (loggedInUser.role === 'VOLUNTEER' || loggedInUser.role === 'MEMBER') {
        $('#btnDelete').hide();
        disableAllInputElementsInForm();
        hideAllCondtionalButtons();

        var eventStart = moment(start).format(dateTimeFormat);
        var eventEnd = moment(end).format(dateTimeFormat);
        var currentDate = moment().format(dateTimeFormat);

        console.log(currentDate);
        console.log(eventStart);
        //Enable the availability dropdown if the shift hasn't started yet, otherwise disable it
        if (moment(currentDate).isBefore(eventStart)) {
            console.log('showing save button');
            $("#availabilitySelect").prop('disabled', false);
            $("#submitButton").show();
        }
        //Condtionally render timecard
        if (moment(currentDate).isAfter(eventStart) && isTimeCardSubmitted === false) {
            console.log('showing timecard');
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
    console.log(shift);
    saveShift(shift);
    $("#createEventModal").modal('hide');
}

function resetFormFields() {
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

function hideAllCondtionalButtons() {
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