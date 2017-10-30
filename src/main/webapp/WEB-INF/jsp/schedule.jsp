<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-09-29
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%--<script src='resources/lib/jquery.min.js'></script>--%>
    <%--<script src='resources/lib/moment.min.js'></script>--%>
    <%--<script src='resources/fullcalendar/fullcalendar.js'></script>--%>

    <script>
        var api = '${pageContext.request.contextPath}/api';
    </script>
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
                    <input class='campusFilter' type="checkbox" value="BURNABY" id="BURNABY"
                           checked>BURNABY</label>
            </div>
            <div class="checkbox">
                <label>
                    <input class='campusFilter' type="checkbox" value="SURREY" id="SURREY">SURREY</label>
            </div>
            <div class="checkbox">
                <label>
                    <input class='campusFilter' type="checkbox" value="VANCOUVER"
                           id="VANCOUVER">VANCOUVER</label>
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
                                            <option value="BURNABY" class="BURNABY">BURNABY</option>
                                            <option value="SURREY" class="SURREY">SURREY</option>
                                            <option value="VANCOUVER" class="VANCOUVER">VANCOUVER</option>
                                        </select>
                                        <select name="eventTitle" id="eventTitle">
                                            <option value="SURREY" disabled="true" selected="selected">Select a
                                                SURREY Shift
                                            </option>
                                            <option value="SURREY" class="Community Presence">Community
                                                Presence
                                            </option>
                                            <option value="SURREY" class="Theft Prevention">Theft Prevention
                                            </option>
                                            <option value="SURREY" class="Special Events">Special Events
                                            </option>
                                            <option value="SURREY" class="Pedestrian Safety">Pedestrian Safety
                                            </option>

                                            <option value="VANCOUVER" disabled="true" selected="selected">Select
                                                a VANCOUVER Shift
                                            </option>
                                            <option value="VANCOUVER" class="Community Presence">Community
                                                Presence
                                            </option>
                                            <option value="VANCOUVER" class="Theft Prevention">Theft
                                                Prevention
                                            </option>
                                            <option value="VANCOUVER" class="Special Events">Special Events
                                            </option>
                                            <option value="VANCOUVER" class="Pedestrian Safety">Pedestrian
                                                Safety
                                            </option>

                                            <option value="BURNABY" disabled="true" selected="selected">Select a
                                                BURNABY Shift
                                            </option>
                                            <option value="BURNABY" class="Information and Lost & Found Kiosk">
                                                Information and Lost & Found Kiosk
                                            </option>
                                            <option value="BURNABY" class="Speed Watch / Moving Traffic">Speed
                                                Watch / Moving Traffic
                                            </option>
                                            <option value="BURNABY" class="Community Presence">Community
                                                Presence
                                            </option>
                                            <option value="BURNABY" class="Safety Screen">Safety Screen</option>
                                            <option value="BURNABY" class="Theft Prevention">Theft Prevention
                                            </option>
                                            <option value="BURNABY" class="Auto Theft Prevention">Auto Theft
                                                Prevention
                                            </option>
                                            <option value="BURNABY" class="Bike Presence">Bike Presence</option>
                                            <option value="BURNABY" class="Special Events">Special Events
                                            </option>
                                            <option value="BURNABY" class="Smoking Checks">Smoking Checks
                                            </option>
                                            <option value="BURNABY" class="Pedestrian Safety">Pedestrian
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
                                <div class="form-group">
                                    <label class="control-label">Member:</label>
                                    <input type="text" name="eventMember" id="eventMember" placeholder="Enter a member's username.">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Location: </label>
                                    <input type="text" name="eventMember" id="eventLocation" placeholder="Enter the Location">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Required Training: </label>
                                    <input type="text" name="eventMember" id="eventRequiredTraining" placeholder="Enter the Location">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Notes: </label>
                                    <textarea rows="4" cols="50" id="eventNotes"></textarea>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                            <%--AJAX Request to POST to ShiftController--%>
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
                        ID: <span id="modalID"></span><br><br>
                        Member: <span id="modalMember"></span><br>
                        Date: <span id="modalDate"></span><br>
                        Location: <span id="modalLocation"></span><br>
                        Notes: <span id="modalNotes"></span><br>
                        Required Training: <span id="modalTraining"></span><br>

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
