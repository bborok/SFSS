var dateFormat = "YYYY-MM-DD HH:mm:ss";
// var dateTimeInputFormat = "YYYY-MM-DD'T'HH:mm:ss";
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var calendar;
var startTimeInput;
var endTimeInput;

$(document).ready(function () {
    csrfAndAjaxSetup();

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
        customButtons: {
            add_event: {
                text: 'Add a Shift',
                click: function () {
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
        select: selectEventHandler,

        //Selecting a scheduled event
        eventClick: eventClickHandler,

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

var selectEventHandler = function (start, end) {
    var myStart = moment(start).format("YYYY-MM-DD[T]HH:mm:ss");
    var myEnd = moment(end).format("YYYY-MM-DD[T]HH:mm:ss");
    startTimeInput.val(myStart);
    endTimeInput.val(myEnd);
    $('#createEventModal').modal('show'); //popup modal
};

var eventClickHandler = function (event) {
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
    $('#availabilitySelect').val(event.confirmationStatus);
    $('#modalAvailability').text(_.replace(event.confirmationStatus, '_', ' '));

    $('#fullCalModal').modal();

    $('#btnDelete').off().on('click', function (e) {
        e.preventDefault();
        deleteShift(event);
        $('#fullCalModal').modal('hide');
    });

    $("#btnConfirmAvailability").off().on('click', function () {
        updateShiftConfirmation(event.id, $("#availabilitySelect").val());
        $('#fullCalModal').modal('hide');
    });

    var eventStart = moment(event.start).format(dateFormat);
    var currentDate = moment().format(dateFormat);

    //Enable the availability dropdown if the shift hasn't started yet, otherwise disable it
    if (moment(eventStart).isAfter(currentDate)) {
        $("#availabilitySelect").show();
        $("#modalAvailability").hide();
    } else {
        $("#availabilitySelect").hide();
        $("#modalAvailability").show();
    }
};

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

function updateShiftConfirmation(shiftId, status) {
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
        url: api + '/shift/updateConfirmation',
        data: $.param(payload),
        success: function () {
            $.notify("Updated availability.", "success");
            calendar.fullCalendar('refetchEvents');
        },
        error: function () {
            $.notify("Error updating availability", "warn");
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
