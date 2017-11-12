var iBURNABY = ["Information and Lost & Found Kiosk", "Speed Watch/Moving Traffic", "Community Presence", "Safety Screen", "Theft Prevention", "Auto Theft Prevention", "Bike Presence", "Special Events", "Smoking Checks", "Pedestrian Safety"];
var iSURREY = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];
var iVANCOUVER = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];

var iALLCAMPUSES = ["Information and Lost & Found Kiosk", "Speed Watch/Moving Traffic", "Community Presence", "Safety Screen", "Theft Prevention", "Auto Theft Prevention", "Bike Presence", "Special Events", "Smoking Checks", "Pedestrian Safety",
    "Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety",
    "Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];

var iNOCAMPUSES = [];

var addButtonBool = false;
var dateFormat = "YYYY-MM-DD HH:mm:ss";
var token;
var header;
var calendar;
var errorDiv;

$(document).ready(function () {
    //CSRF Setup, needed for AJAX requests
    token = $("meta[name='_csrf']").attr("content");
    header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        jqXHR.setRequestHeader(header, token);
    });

    errorDiv = $('#alertsDiv');


    // Initialize the calendar...
    calendar = $('#calendar');
    $('#external-events .fc-event').each(function () {
        // store data so the calendar knows to render an event upon drop
        $(this).data('event', {
            title: $.trim($(this).text()), // use the element's text as the event title
            stick: true // maintain when user navigates (see docs on the renderEvent method)
        });

        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });
    });

    calendar.fullCalendar({
        eventSources: [
            api + '/shiftraws'
        ],
        // put your options and callbacks here
        customButtons: {
            add_event: {
                text: 'Add a Shift',
                click: function (start, end) {
                    addButtonBool = true;
                    $('#addShiftTime').show();
                    $('#apptStartTime').hide();
                    $('#apptEndTime').hide();
                    $('#when').hide();
                    $('#hideDate').hide();

                    var startTime = moment(start).format('MMM Do h:mm A');
                    var endTime = moment(end).format('MMM Do h:mm A');
                    var mywhen = startTime + ' - ' + endTime;

                    $('#createEventModal #startTime').val(start);
                    $('#createEventModal #endTime').val(end);
                    // $('#createEventModal #eventCampus').val(event.campus);
                    // $('#createEventModal #eventMember').val(event.member);
                    $('#createEventModal #when').text(mywhen);

                    $('#createEventModal').modal('show'); //popup modal
                }
            }
        },
        timezone: 'local',
        viewRender: function (view) {
            var h;
            if (view.name === "month") {
                h = 700;
            } else {
                h = 700;
            }
            calendar.fullCalendar('option', 'contentHeight', h);
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
            addButtonBool = false;
            $('#addShiftTime').hide();
            $('#apptStartTime').show();
            $('#apptEndTime').show();
            $('#when').show();
            $('#hideDate').show();
            var startTime = moment(start).format('MMM Do h:mm A');
            var endTime = moment(end).format('MMM Do h:mm A');
            var mywhen = startTime + ' - ' + endTime;

            $('#createEventModal #apptStartTime').val(start);
            $('#createEventModal #apptEndTime').val(end);
            $('#createEventModal #when').text(mywhen);

            $('#createEventModal').modal('show'); //popup modal
        },

        //Selecting a scheduled event
        eventClick: function (event) {
            console.log(event);
            //The field after 'event' matches up with the field name in the AbstractShift and ShiftRaw classes
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

            $('#fullCalModal').modal();

            $('#btnDelete').on('click', function (e) {
                e.preventDefault();
                //AJAX DELETE REQUEST
                deleteShift(event);

                $('#fullCalModal').modal('hide');
            })
        },

        navLinks: true, // can click day/week names to navigate views
        weekNumbers: true,
        weekNumbersWithinDays: true,
        weekNumberCalculation: 'ISO',
        editable: true
    });

    $('#submitButton').on('click', function (e) {
        // We don't want this to act as a link so cancel the link action
        e.preventDefault();
        doSubmit();
    });


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

    $('#addShiftTime').hide();

});

function doSubmit() {
    var eventTitleElement = $('#eventTitle');
    $("#createEventModal").modal('hide');

    var start;
    var end;

    if (addButtonBool === true) {
        start = moment(new Date($('#startTime').val())).format(dateFormat);
        end = moment(new Date($('#endTime').val())).format(dateFormat);
    } else {
        start = moment(new Date($('#apptStartTime').val())).format(dateFormat);
        end = moment(new Date($('#apptEndTime').val())).format(dateFormat);
    }
    var shiftRaw = {
        title: eventTitleElement.find(":selected").attr('class'),
        start: start,
        end: end,
        campus: $('#eventCampus').val(),
        username: $('#eventMember').val(),
        location: $('#eventLocation').val(),
        notes: $('#eventNotes').val(),
        requiredTraining: $('#eventRequiredTraining').val()
    };
    saveShift(shiftRaw);
}

var saveShift = function (shiftRaw) {
    console.log(shiftRaw);
    var url = api + '/shifts/save';
    $.ajax({
        headers: {
            Accept: "text/plain"
        },
        type: 'POST',
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(shiftRaw),
        success: function () {
            displaySuccessAlert('Saved ' + shiftRaw.title + '.');
            calendar.fullCalendar('refetchEvents');
        },
        error: function () {
            displayErrorAlert('Error saving ' + shiftRaw.title + ' to database.');
        }
    });
};

var deleteShift = function (event) {
    $.ajax({
        type: 'DELETE',
        headers: {
            Accept: "text/plain"
        },
        url: api + '/shifts/delete/' + event.id,
        success: function () {
            displaySuccessAlert('Deleted ' + event.title + '.');
            calendar.fullCalendar('refetchEvents');
        },
        fail: function () {
            displayErrorAlert('Error deleting ' + event.title + 'to database.');
        }
    });
};

var displayErrorAlert = function (msg) {
    errorDiv.append(
        "<div id=\"errorAlert\" class=\"alert alert-danger alert-dismissable fade in\">" +
        "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" +
        "    <strong>Danger! </strong> " + msg +
        "</div>"
    );
};

var displaySuccessAlert = function (msg) {
    errorDiv.append(
        "<div id=\"successAlert\" class=\"alert alert-success alert-dismissable fade in\">" +
        "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" +
        "    <strong>Success! </strong> " + msg +
        "</div>"
    );
};


