

$(document).ready(function() {


            var events_array = [
            {
                title: "Test 1",
                allday: false,
                description: "lol",
                start: new Date(),
                end: new Date(),
                allDay: "false"
                // id: 1,
                // title: 'New event',
                // start: new Date(2017, 5, 25),
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
                    h = 725;
                } else {
                    h = 725;
                }
                $('#calendar').fullCalendar('option', 'contentHeight', h);
            },

            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay,listWeek'
            },

            selectable: true,
            events: events_array,

            eventRender: function(event, element) {
                element.attr('title', event.tip);
            },

            select: function (start, end, jsEvent, view) {

                startTime = moment(start).format('MMM Do h:mm A');
                endTime = moment(end).format('h:mm A');
                var mywhen = startTime + ' - ' + endTime;

                $('#createEventModal #apptStartTime').val(start);
                $('#createEventModal #apptEndTime').val(end);

                $('#createEventModal #when').text(mywhen);
                $('#createEventModal').modal('show');

            },

            eventClick: function(event){
                $('#modalTitle').html(event.title);
                $('#modalStart').html(moment(event.start).format('MMM Do h:mm A'));
                $('#modalEnd').html(moment(event.end).format('MMM Do h:mm A'));
                $('#modalBody').html(event.description);
                $('#fullCalModal').modal();
                // title: event.title;
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
                    title: $('#eventTitle').val(),
                    start: new Date($('#apptStartTime').val()),
                    end: new Date($('#apptEndTime').val()),
                    allDay: ($('#apptAllDay').val() == "true"),
                },
                true);
        }
    });
