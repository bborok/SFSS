 $(document).ready(function() {

            var date = new Date();
            var day = date.getDate();
            var month = date.getMonth();
            var year = date.getFullYear();

            var events_array = [
            {
                id: 1,
                title: 'New event',
                start: new Date(2017, 5, 25)
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
                var abc = prompt('Enter Title');
                var allDay = !start.hasTime && !end.hasTime;
                var newEvent = new Object();
                newEvent.title = abc;
                newEvent.start = moment(start).format();
                newEvent.allDay = false;

                if (abc) { // if empty schedule, don't add
                    $('#calendar').fullCalendar('renderEvent', newEvent);
                }
                
            },
            
            navLinks: true, // can click day/week names to navigate views

            weekNumbers: true,
            weekNumbersWithinDays: true,
            weekNumberCalculation: 'ISO',

            editable: true
        })

    });