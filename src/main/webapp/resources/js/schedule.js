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
                // console.log(['all', event.id].indexOf($('#shiftSelect').val()));
                // console.log(['all', event.campus].indexOf($('#campusSelect').val()));
                // console.log($('#eventTitle').val());
                // console.log($('#shiftSelect').find(":selected").attr('id'));

                if (($('#shiftSelect').find(":selected").attr('id')) == "allShifts") {
                    return ['all', event.campus].indexOf($('#campusSelect').val()) >= 0
                }


                // // console.log($('#eventTitle').find(":selected").attr('class'));
                // console.log($('#eventCampus').find(":selected").attr('class'));

                return ['all', event.id].indexOf($('#shiftSelect').find(":selected").attr('class')) >= 0 &&
                    ['all', event.campus].indexOf($('#campusSelect').val()) >= 0
                    // &&
                    // (['all', event.id].indexOf($('#eventTitle').find(":selected").attr('class')) &&
                    // ['all', event.campus].indexOf($('#eventCampus').find(":selected").attr('class')) >= 0)
            },
            // eventRender: function eventRender( event, element, view ) {
            //     return ['all', event.campus].indexOf($('#campusSelect').val()) >= 0
            // },

            // eventRender: function(event, element) {
            //     element.attr('title', event.tip);
            //
            // }
            eventAfterRender: function(event, element, view) {
                $(element).css('width','80%');
            },

            select: function (start, end, id) {

                // end = end.subtract('days', 1); // uses for first iteration, no connection
                // end = end.add('hours', 1);

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
            console.log($('#eventCampus').val());
            //
            // console.log($('#apptEndTime').val());
            // console.log($('#apptStartTime').val());
            //
            // $("#eventTitle").attr("placeholder", "Enter a volunteer.").val("").focus().blur();
            // $("#eventMember").attr("placeholder", "Enter a brief description of this shift.").val("").focus().blur();

        }

    $('#shiftSelect').on('change',function(){
        // console.log($('#campusSelect').val());
        // console.log($('#shiftSelect').val());
        $('#calendar').fullCalendar('rerenderEvents');
        })

    $('#campusSelect').on('change',function(){

       var val = $(this).val();
       var sub = $('#shiftSelect');
       if (val == 'all') {
           $('#shiftSelect').find('option').show();
       } else {
           sub.find('option').not(':first').hide();
           $('option', sub).filter(function() {
               if ($(this).attr('value')== val) {
                   $(this).show();
               }
           });
       }
       sub.val('all');
        $('#calendar').fullCalendar('rerenderEvents');
    })

    });
