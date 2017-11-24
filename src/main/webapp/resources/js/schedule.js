var dateFormat = "YYYY-MM-DD HH:mm:ss";
var dateTimeInputFormat = "YYYY-MM-DD'T'HH:mm:ss";
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var calendar;
var alertsDiv;
var startTimeInput;
var endTimeInput;

$(document).ready(function () {
    csrfAndAjaxSetup();

    alertsDiv = $('#alertsDiv');
    startTimeInput = $('#startTime');
    endTimeInput = $('#endTime');
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
        // put your options and callbacks here
        customButtons: {
            add_event: {
                text: 'Add a Shift',
                click: function (start, end) {
                    // startTimeInput.val(moment(start).format(dateTimeInputFormat));
                    // endTimeInput.val(moment(end).format(dateTimeInputFormat));
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

        eventRender: function eventRender(event, element, view) {
            if (event.campus === 'BURNABY') {
                element.css('background-color', '#E8502F');
            }
            if (event.campus === "SURREY") {
                element.css('background-color', '#C5E744');
            }
            if (event.campus === "VANCOUVER") {
                element.css('background-color', '#75C6E7');
            }
            return filter(event);
        },

        eventAfterRender: function (event, element, view) {
            $(element).css('width', '80%');
        },

        //Selecting an empty area
        select: function (start, end) {
            var myStart = moment(start).format("YYYY-MM-DD[T]HH:mm:ss");
            var myEnd = moment(end).format("YYYY-MM-DD[T]HH:mm:ss");
            // console.log(myStart);
            // console.log(myEnd);
            startTimeInput.val(myStart);
            endTimeInput.val(myEnd);
            $('#createEventModal').modal('show'); //popup modal
        },

        //Selecting a scheduled event
        eventClick: function (event) {
            console.log(event);
            //The field after 'event' matches up with the field name in the AbstractShift and Shift classes
            $('#modalTitle').html(event.title);
            $('#modalStart').html(moment(event.start).format('MMM Do h:mm A'));
            $('#modalEnd').html(moment(event.end).format('MMM Do h:mm A'));
            $('#modalMember').html(event.username);
            $('#modalCampus').html(event.campus);
            $('#modalID').html(event.id);
            $('#modalDate').html(moment(event.date).format('MMM DD YYYY'));
            $('#modalLocation').html(event.location);
            $('#modalNotes').html(event.notes);
            $('#modalTraining').html(event.requiredTraining);
            $('#modalTimeCard').html(new Boolean(event.isTimeCardSubmitted).toString());

            if (event.confirmed === null)
                $('#availabilitySelect').val('');
            else if (event.confirmed === true)
                $('#availabilitySelect').val('true');
            else
                $('#availabilitySelect').val('false');

            $('#fullCalModal').modal();

            $('#btnDelete').off().on('click', function (e) {
                e.preventDefault();
                //AJAX DELETE REQUEST
                // console.log('Deleting shift ' + event.id);
                deleteShift(event);
                $('#fullCalModal').modal('hide');
            });

            $("#btnConfirmAvailability").off().on('click', function () {
                console.log("Updating availability.");
                updateShiftConfirmation(event.id, $("#btnConfirmAvailability").val());
            });
        },

        navLinks: true, // can click day/week names to navigate views
        weekNumbers: true,
        weekNumbersWithinDays: true,
        weekNumberCalculation: 'ISO',
        editable: false
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

    $("#eventCampus").on('change', function () {
        if ($(this).data('options') === undefined) {
            /*Taking an array of all options-2 and kind of embedding it on the select1*/
            $(this).data('options', $('#eventTitle option').clone());
        }
        var id = $(this).val();
        var options = $(this).data('options').filter('[value=' + id + ']');
        $('#eventTitle').html(options);
    });


}

//Creates a Shift object based on the form input fields and passes it to the saveShift()
function doSubmit() {
    var eventTitleElement = $('#eventTitle');
    $("#createEventModal").modal('hide');

    var start = moment(new Date($('#startTime').val())).format(dateFormat);
    var end = moment(new Date($('#endTime').val())).format(dateFormat);
    var date = moment(new Date($('#startTime').val())).format("YYYY-MM-DD");

    var shift = {
        title: eventTitleElement.find(":selected").attr("class"),
        date: date,
        start: start,
        end: end,
        campus: $('#eventCampus').val(),
        username: $('#eventMember').val(),
        location: $('#eventLocation').val(),
        notes: $('#eventNotes').val(),
        requiredTraining: $('#eventRequiredTraining').val()
    };
    saveShift(shift);
}

//Sends a requests via AJAX to save a shift
var saveShift = function (shift) {
    // console.log(shift);
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
            displaySuccessAlert('Saved ' + shift.title + '.');
            calendar.fullCalendar('refetchEvents');
        },
        error: function () {
            displayErrorAlert('Error saving ' + shift.title + ' to database.');
        }
    });
};

//Sends a request via AJAX to delete a shift
var deleteShift = function (event) {
    $.ajax({
        type: 'DELETE',
        headers: {
            Accept: "text/plain"
        },
        url: api + '/shift/delete/' + event.id,
        success: function () {
            // console.log('Deleted shift' + event.id);
            displaySuccessAlert('Deleted ' + event.title + '.');
            calendar.fullCalendar('refetchEvents');
        },
        fail: function () {
            displayErrorAlert('Error deleting ' + event.title + 'to database.');
        }
    });
};

function updateShiftConfirmation(shiftId, status) {
    console.log("Updating " + shiftId + "with status of: " + status);
    var payload = {
        shift_id: shiftId,
        confirmation_status: status
    };
    $.ajax({
        type: 'POST',
        headers: {
            Accept: "text/plain",
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: api + 'shift/updateConfirmation',
        data: $.param(payload),
        success: function () {
            console.log('Updated shift' + event.id);
            displaySuccessAlert('Updated ' + event.title + '.');
            calendar.fullCalendar('refetchEvents');
        },
        fail: function () {
            displayErrorAlert('Error deleting ' + event.title + 'to database.');
        }
    });
}

//Displays a Bootstrap error alert at the top of the page
var displayErrorAlert = function (msg) {
    alertsDiv.append(
        "<div id=\"errorAlert\" class=\"alert alert-danger alert-dismissable fade in\">" +
        "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" +
        "    <strong>Danger! </strong> " + msg +
        "</div>"
    );
};

//Displays a Bootstrap success alert at the top of the page
var displaySuccessAlert = function (msg) {
    alertsDiv.append(
        "<div id=\"successAlert\" class=\"alert alert-success alert-dismissable fade in\">" +
        "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" +
        "    <strong>Success! </strong> " + msg +
        "</div>"
    );
};

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
