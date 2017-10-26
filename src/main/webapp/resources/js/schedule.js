var iBURNABY = ["Information and Lost & Found Kiosk", "Speed Watch/Moving Traffic", "Community Presence", "Safety Screen", "Theft Prevention", "Auto Theft Prevention", "Bike Presence", "Special Events", "Smoking Checks", "Pedestrian Safety"];
var iSURREY = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];
var iVANCOUVER = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];
var shiftsAPI = 'http://localhost:8080/ROOT/eventsAPI/shifts';
$(document).ready(function () {

    //TODO: DELETE all these ajax requests when fully tested
    $.getJSON('https://jsonplaceholder.typicode.com/posts/1', function (data) {
        console.log(data);
    }).fail(function () {
        alert("fail jsonplaceholder.typicode.com/posts/1");
    });

    $.getJSON(shiftsAPI, function (data) {
        console.log(data);
    }).fail(function () {
        alert("fail shifts API");
    });

    // page is now ready, initialize the calendar...
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


    $('#calendar').fullCalendar({
        eventSources: [
            shiftsAPI
        ],
        // put your options and callbacks here
        timezone: 'local',
        viewRender: function (view) {
            var h;
            if (view.name == "month") {
                h = 700;
            } else {
                h = 700;
            }
            $('#calendar').fullCalendar('option', 'contentHeight', h);
        },

        header: {
            left: 'prev,next today',
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

            var startTime = moment(start).format('MMM Do h:mm A');
            var endTime = moment(end).format('MMM Do h:mm A');

            console.log(startTime + ',' + endTime);
            var mywhen = startTime + ' - ' + endTime;

            $('#createEventModal #apptStartTime').val(start);
            $('#createEventModal #apptEndTime').val(end);
            // $('#createEventModal #eventCampus').val(event.campus);
            // $('#createEventModal #eventMember').val(event.member);
            $('#createEventModal #when').text(mywhen);
            $('#createEventModal').modal('show'); //popup modal
        },

        //Selecting a scheduled event
        eventClick: function (event) {

            console.log(event); //TODO: delete this line when deploying

            $('#modalTitle').html(event.title);
            $('#modalStart').html(moment(event.start).format('MMM Do h:mm A'));
            $('#modalEnd').html(moment(event.end).format('MMM Do h:mm A'));
            $('#modalMember').html(event.user.name);
            $('#modalCampus').html(event.campus);
            $('#modalID').html(event.id);
            $('#fullCalModal').modal();

            $('#btnDelete').on('click', function (e) {
                e.preventDefault();

                $('#fullCalModal').modal('hide');
                $('#calendar').fullCalendar('removeEvents', event._id);
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

    function doSubmit() {

        var eventTitleElement = $('#eventTitle');
        $("#createEventModal").modal('hide');
        $("#calendar").fullCalendar('renderEvent',
            {
                title: eventTitleElement.find(":selected").attr('class'),
                start: new Date($('#apptStartTime').val()),
                end: new Date($('#apptEndTime').val()),
                username: $('#eventMember').val(),
                campus: $('#eventCampus').val()
            }, true);
        console.log(eventTitleElement.val());

        var start = moment(new Date($('#apptStartTime').val())).format('YYYY-MM-DDTHH:mm:ss');
        var end = moment(new Date($('#apptEndTime').val())).format('YYYY-MM-DDTHH:mm:ss');

        //AJAX POST Request Here
        var shiftRaw = {
            title: eventTitleElement.find(":selected").attr('class'),
            //Start & End must be formatted: "yyyy-MM-dd'T'hh:mm:ss"
            start: start,
            end: end,
            campus: $('#eventCampus').val(),
            username: $('#eventMember').val()
        };

        console.log("ShiftRaw: ");
        console.log(shiftRaw);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/ROOT/eventsAPI/shifts/save',
            data: JSON.stringify(shiftRaw),
            success: function(data) { location.reload(); },
            fail: function () {
              alert('Error saving shift to DB');
            },
            contentType: "application/json",
            dataType: 'json'
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

        if ($('#shiftSelect').val() == null) {
            return vals.indexOf(calEvent.campus) !== -1;
        }
        if ($('#shiftSelect option:selected').val() == "all") {
            return vals.indexOf(calEvent.campus) !== -1;
        }

        return vals.indexOf(calEvent.campus) !== -1 && vals2.indexOf(calEvent.title) !== -1;
    }


    $('input:checkbox.campusFilter').on('change', function () {
        $('#calendar').fullCalendar('rerenderEvents');
    });

    $('#shiftSelect').on('change', function () {
        $('#calendar').fullCalendar('rerenderEvents');
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

    $("#eventCampus").change(function () {
        if ($(this).data('options') === undefined) {
            /*Taking an array of all options-2 and kind of embedding it on the select1*/
            $(this).data('options', $('#eventTitle option').clone());
        }
        var id = $(this).val();
        var options = $(this).data('options').filter('[value=' + id + ']');
        $('#eventTitle').html(options);
    });


});
