var iBurnaby = ["Information and Lost & Found Kiosk", "Speed Watch/Moving Traffic", "Community Presence", "Safety Screen", "Theft Prevention", "Auto Theft Prevention", "Bike Presence", "Special Events", "Smoking Checks", "Pedestrian Safety"];
var iSurrey = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];
var iVancouver = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];

$(document).ready(function() {

            var events_array = [ //once we have database values, simple mysql is needed to connect values to these arrays
            {
                title: "Information and Lost & Found Kiosk",
                id: "Burnaby Information and Lost & Found Kiosk",
                allday: false,
                member: "Bobae",
                start: '2017-10-04T15:00:00',
                end: '2017-10-04T15:30:00',
                campus: 'Burnaby'
            },
            {
                title: "Community Presence",
                id: "Surrey Community Presence",
                allday: false,
                member: "Steven",
                start: '2017-10-06T13:00:00',
                end: '2017-10-06T15:00:00',
                campus: 'Surrey'
            },
            {
                title: "Pedestrian Safety",
                id: "Vancouver Pedestrian Safety",
                allday: false,
                member: "Alex",
                start: '2017-10-07T13:00:00',
                end: '2017-10-07T15:00:00',
                campus: 'Vancouver'
            }

        ];


        // page is now ready, initialize the calendar...

        $('#external-events .fc-event').each(function() {

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
            events: events_array,

            eventRender: function eventRender( event, element, view ) {

                if (event.campus == 'Burnaby') {
                    element.css('background-color', '#E8502F');
                }
                if (event.campus == "Surrey") {
                    element.css('background-color', '#C5E744');
                }
                if (event.campus == "Vancouver") {
                    element.css('background-color', '#75C6E7');
                }
                return filter(event);
            },

            eventAfterRender: function(event, element, view) {
                $(element).css('width','80%');
            },

            select: function (start, end, id) {

                startTime = moment(start).format('MMM Do h:mm A');
                endTime = moment(end).format('h:mm A');
                var mywhen = startTime + ' - ' + endTime;

                $('#createEventModal #apptStartTime').val(start);
                $('#createEventModal #apptEndTime').val(end);
                $('#createEventModal #apptID').val(event.id);
                $('#createEventModal #eventCampus').val(event.campus);
                $('#createEventModal #eventMember').val(event.member);
                $('#createEventModal #when').text(mywhen);
                $('#createEventModal').modal('show');
            },

            eventClick: function(event){

                $('#modalTitle').html(event.title);
                $('#modalStart').html(moment(event.start).format('MMM Do h:mm A'));
                $('#modalEnd').html(moment(event.end).format('MMM Do h:mm A'));
                $('#modalMember').html(event.member);
                $('#fullCalModal').modal();

                $('#btnDelete').on('click', function(e) {
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
        })

        $('#submitButton').on('click', function(e){
            // We don't want this to act as a link so cancel the link action
            e.preventDefault();

            doSubmit();
        })

        function doSubmit(){

            $("#createEventModal").modal('hide');
            $("#calendar").fullCalendar('renderEvent',
                {
                    title: $('#eventTitle').find(":selected").attr('class'),
                    start: new Date($('#apptStartTime').val()),
                    end: new Date($('#apptEndTime').val()),
                    allDay: ($('#apptAllDay').val() == "true"),
                    member: $('#eventMember').val(),
                    id: $('#eventTitle').val(),
                    campus: $('#eventCampus').val()
                },

                true);
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


    $('input:checkbox.campusFilter').on('change', function() {
        $('#calendar').fullCalendar('rerenderEvents');
    });

    $('#shiftSelect').on('change', function() {
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
        function addItemsFromArray (arr) {
            $('#shiftSelect').append('<option value ="' + 'all' + '">' + 'All Shifts' + '</option>');
            $.each(arr, function (i, v) {
                $("#shiftSelect").append('<option value="' + v + '">' + v + '</option>');
            });
        }
    });

});
