<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-09-29
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SFU</title>

    <!-- jQuery Resources -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://momentjs.com/downloads/moment.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.js'></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <link rel='stylesheet' href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.css"/>

    <!-- FullCalendar Resources -->
    <link rel='stylesheet' href='resources/fullcalendar/fullcalendar.css'/>
    <script src='resources/lib/jquery.min.js'></script>
    <script src='resources/lib/moment.min.js'></script>
    <script src='resources/fullcalendar/fullcalendar.js'></script>

    <script src='resources/js/schedule.js'></script>

    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<link href='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.3/jquery-ui.css' rel='stylesheet' />--%>


    <!-- Custom styles for this template -->
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/css/fullCalendarCSS.css">
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">



</head>

<style>
    #side-container {
    }

    #side-contact {
        position: absolute;
        color: #ffffff;
        bottom: 0;
    }

</style>



<body>

<div id="wrapper" class="toggled">

    <jsp:include page="partfiles/sidebar.jsp"/>


    <!-- Page Content -->

    <div class="col-sm-12 text">
        <div class="description">
            <div class="checkbox">
                <label>
                    <input class='campusFilter' type="checkbox" value="Burnaby" id="Burnaby"
                           checked>Burnaby</label>
            </div>
            <div class="checkbox">
                <label>
                    <input class='campusFilter' type="checkbox" value="Surrey" id="Surrey">Surrey</label>
            </div>
            <div class="checkbox">
                <label>
                    <input class='campusFilter' type="checkbox" value="Vancouver"
                           id="Vancouver">Vancouver</label>
            </div>
            <select id="shiftSelect"></select>
            <br>
            <div id='calendar'></div>

            <%--Create Event Modal--%>
            <div id="createEventModal" class="modal fade">
                <div class="modal-dialog">
                    <%--Modal Content--%>

                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x
                            </button>
                            <h3 id="myModalLabel1">Assign a shift</h3>
                        </div>

                        <%--Modal Body--%>
                        <div class="modal-body">
                            <form id="createAppointmentForm" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label">Shift: </label>
                                    <div class="controls">
                                        <select name="eventCampus" id="eventCampus">
                                            <option value='all' id='allCampuses' disabled="true" selected>Select
                                                a campus.
                                            </option>
                                            <option value="Burnaby" class="Burnaby">Burnaby</option>
                                            <option value="Surrey" class="Surrey">Surrey</option>
                                            <option value="Vancouver" class="Vancouver">Vancouver</option>
                                        </select>
                                        <select name="eventTitle" id="eventTitle">
                                            <option value="Surrey" disabled="true" selected="selected">Select a
                                                Surrey Shift
                                            </option>
                                            <option value="Surrey" class="Community Presence">Community
                                                Presence
                                            </option>
                                            <option value="Surrey" class="Theft Prevention">Theft Prevention
                                            </option>
                                            <option value="Surrey" class="Special Events">Special Events
                                            </option>
                                            <option value="Surrey" class="Pedestrian Safety">Pedestrian Safety
                                            </option>

                                            <option value="Vancouver" disabled="true" selected="selected">Select
                                                a Vancouver Shift
                                            </option>
                                            <option value="Vancouver" class="Community Presence">Community
                                                Presence
                                            </option>
                                            <option value="Vancouver" class="Theft Prevention">Theft
                                                Prevention
                                            </option>
                                            <option value="Vancouver" class="Special Events">Special Events
                                            </option>
                                            <option value="Vancouver" class="Pedestrian Safety">Pedestrian
                                                Safety
                                            </option>

                                            <option value="Burnaby" disabled="true" selected="selected">Select a
                                                Burnaby Shift
                                            </option>
                                            <option value="Burnaby" class="Information and Lost & Found Kiosk">
                                                Information and Lost & Found Kiosk
                                            </option>
                                            <option value="Burnaby" class="Speed Watch / Moving Traffic">Speed
                                                Watch / Moving Traffic
                                            </option>
                                            <option value="Burnaby" class="Community Presence">Community
                                                Presence
                                            </option>
                                            <option value="Burnaby" class="Safety Screen">Safety Screen</option>
                                            <option value="Burnaby" class="Theft Prevention">Theft Prevention
                                            </option>
                                            <option value="Burnaby" class="Auto Theft Prevention">Auto Theft
                                                Prevention
                                            </option>
                                            <option value="Burnaby" class="Bike Presence">Bike Presence</option>
                                            <option value="Burnaby" class="Special Events">Special Events
                                            </option>
                                            <option value="Burnaby" class="Smoking Checks">Smoking Checks
                                            </option>
                                            <option value="Burnaby" class="Pedestrian Safety">Pedestrian
                                                Safety
                                            </option>

                                        </select>
                                        <input type="hidden" id="apptStartTime"/>
                                        <input type="hidden" id="apptEndTime"/>
                                        <input type="hidden" id="apptAllDay"/>
                                    </div>
                                    <label class="control-label">When:</label>
                                    <label id="when"></label>
                                </div>
                                <div class="control-group">
                                </div>
                                <label class="control-label">Volunteer:</label>
                                <%--Ajax request--%>
                                <h3>
                                    <input type="text" name="eventMember" id="eventMember"
                                           placeholder="Enter a volunteer.">
                                </h3>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                            <%--AJAX Request--%>
                            <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div id="fullCalModal" class="modal fade">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span>
                                <span class="sr-only">close</span></button>
                            Title: <span id="modalTitle" class="modal-title"></span>
                        </div>
                        <br>
                        Start: <span id="modalStart"></span><br>
                        End: <span id="modalEnd"></span><br><br>
                        Campus: <span id="modalCampus"></span><br><br>
                        Volunteer: <span id="modalMember"></span><br>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button class="btn btn-primary" id="btnDelete">Remove</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->
<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
