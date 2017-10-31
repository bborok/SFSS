var iBURNABY = ["Information and Lost & Found Kiosk", "Speed Watch/Moving Traffic", "Community Presence", "Safety Screen", "Theft Prevention", "Auto Theft Prevention", "Bike Presence", "Special Events", "Smoking Checks", "Pedestrian Safety"];
var iSURREY = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];
var iVANCOUVER = ["Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];

var iALLCAMPUSES = ["Information and Lost & Found Kiosk", "Speed Watch/Moving Traffic", "Community Presence", "Safety Screen", "Theft Prevention", "Auto Theft Prevention", "Bike Presence", "Special Events", "Smoking Checks", "Pedestrian Safety",
    "Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety",
    "Community Presence", "Theft Prevention", "Special Events", "Pedestrian Safety"];

var iNOCAMPUSES = [];

var addButtonBool = false;

//TODO: change this to 'https://cmpt373-1177z.cmpt.sfu.ca/events/api' during when deployed to server
var api = 'http://localhost:8080/ROOT/api';
$(document).ready(function () {
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
            api + '/shiftraws'
        ],
        // put your options and callbacks here
        customButtons: {
            add_event: {
                text: 'Add a Shift',
                click: function(start, end) {
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
            if (view.name == "month") {
                h = 700;
            } else {
                h = 700;
            }
            $('#calendar').fullCalendar('option', 'contentHeight', h);
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
            // $('#createEventModal #eventCampus').val(event.campus);
            // $('#createEventModal #eventMember').val(event.member);
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
            $('#modalDate').html(event.date);
            $('#modalLocation').html(event.location);
            $('#modalNotes').html(event.notes);
            $('#modalTraining').html(event.requiredTraining);

            $('#fullCalModal').modal();

            $('#btnDelete').on('click', function (e) {
                e.preventDefault();
                //AJAX DELETE REQUEST
                $.ajax({
                    type: 'DELETE',
                    url: api + '/shifts/delete/' + event.id,
                    success: function (data) {
                        location.reload(); //reload the page to refresh data (shouldn't really be need, but is used just in case)
                    },
                    fail: function () {
                        alert('Error delete shift in DB');
                    }
                });
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

        //Start & End must be formatted: "yyyy-MM-dd'T'hh:mm:ss"
        //This date format is what the AbstractShift class is currently programmed to accept.
        var start = moment(new Date($('#apptStartTime').val())).format('YYYY-MM-DDTHH:mm:ss');
        var end = moment(new Date($('#apptEndTime').val())).format('YYYY-MM-DDTHH:mm:ss');

        if (addButtonBool == true) {
            var start = moment(new Date($('#startTime').val())).format('YYYY-MM-DDTHH:mm:ss');
            var end = moment(new Date($('#endTime').val())).format('YYYY-MM-DDTHH:mm:ss');

            var shiftRaw2 = {
                title: eventTitleElement.find(":selected").attr('class'),
                start: start,
                end: end,
                campus: $('#eventCampus').val(),
                username: $('#eventMember').val(),
                location: $('#eventLocation').val(),
                notes: $('#eventNotes').val(),
                requiredTraining: $('#eventRequiredTraining').val()
            };

            console.log(shiftRaw2);

            $.ajax({
                type: 'POST',
                url: api + '/shifts/save',
                data: JSON.stringify(shiftRaw2),
                success: function (data) {
                    $("#calendar").fullCalendar('renderEvent',
                        {
                            title: eventTitleElement.find(":selected").attr('class'),
                            start: new Date($('#startTime').val()),
                            end: new Date($('#endTime').val()),
                            username: $('#eventMember').val(),
                            campus: $('#eventCampus').val()
                        }, true);
                },
                error: function () {
                    alert('Error saving shift to DB');
                },
                contentType: "application/json",
                // dataType: 'json'
            });
        } else {
            //AJAX POST Request Here to Save to Database

            //TODO: Read Comments Underneath
            //PROBLEM: If the specified username and requiredTraining do not exist in the database,
            //this shift will not be saved to the database
            //POTENTIAL SOLUTION: Dropdown/Selection/Autocorrect Menus would ensure that the user
            // cannot accidentally input an invalid value

            //The fields in the 'shiftRaw' variable matches up with the field name
            //in the AbstractShift and ShiftRaw classes
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

            console.log(shiftRaw);

            $.ajax({
                type: 'POST',
                url: api + '/shifts/save',
                data: JSON.stringify(shiftRaw),
                success: function (data) {
                    $("#calendar").fullCalendar('renderEvent',
                        {
                            title: eventTitleElement.find(":selected").attr('class'),
                            start: new Date($('#apptStartTime').val()),
                            end: new Date($('#apptEndTime').val()),
                            username: $('#eventMember').val(),
                            campus: $('#eventCampus').val()
                        }, true);
                },
                error: function () {
                    alert('Error saving shift to DB');
                },
                contentType: "application/json",
                // dataType: 'json'
            });
        }

    }

    function filter(calEvent) {

        var vals = [];

        $('input:checkbox.campusFilter:checked').each(function () {
            vals.push($(this).val());
        });

        var vals2 = [];

        $('#shiftSelect option:selected').each(function () {
            vals2.push($(this).val());
        })

        $('.allOrNone').on('click',function() { //
            if ($('.allOrNone').is(':checked')) {
                $('.campusFilter').prop("checked", true)

            } else {
                $('.campusFilter').prop("checked", false);
            }
        })


        if ($('#shiftSelect').val() == null) {
            return vals.indexOf(calEvent.campus) !== -1;
        }
        if ($('#shiftSelect option:selected').val() == "all") {
            return vals.indexOf(calEvent.campus) !== -1;
        }

        return vals.indexOf(calEvent.campus) !== -1 && vals2.indexOf(calEvent.title) !== -1;
    }

    $('.campusFilter').prop("checked", true) // everything is checked

    $('input:checkbox.allOrNone').on('change', function() {
        $('#calendar').fullCalendar('rerenderEvents');
    })


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

    $("#eventCampus").on('change',function () {
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
