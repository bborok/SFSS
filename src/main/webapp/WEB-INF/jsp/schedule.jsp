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


    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">




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

<%--mla189
TODO:
Adjustable Time
Resolve error assigning shift but getting error on db
cancel button functionalities
"required training"?
--%>

<div id="wrapper" class="toggled">

    <jsp:include page="partfiles/sidebar.jsp"/>


    <div id="page-content-wrapper">
            <div class="container-fluid">
                <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
                    <div class="col-sm-12 text">

                        <div class="description">
                            <center>
                            <img src="resources/img/logo_made/logo_2.png" class="img-responsive"
                                 style="height:100px;width:500px">
                            </center>
                            <hr>
                        </div>

                        <div class="col-sm-12 row">
                            <div class="radio">
                                <label>
                                    <input class='allOrNone' type="checkbox" value="ALLCAMPUSES" id="ALLCAMPUSES" checked>ALL CAMPUSES
                                </label>
                                <br>
                                <label>
                                    <input class='campusFilter' type="checkbox" value="BURNABY" id="BURNABY" class = "others">BURNABY
                                </label>
                                <br>
                                <label>
                                    <input class='campusFilter' type="checkbox" value="SURREY" id="SURREY" class = "others">SURREY
                                </label>
                                <br>
                                <label>
                                    <input class='campusFilter' type="checkbox" value="VANCOUVER" id="VANCOUVER" class = "others">VANCOUVER
                                </label>
                                <br>
                                <%--<label>--%>
                                    <%--<input class='campusFilter' type="checkbox" value="NOCAMPUSES" id="NOCAMPUS" class = "others">NONE--%>
                                <%--</label>--%>
                            </div>
                            <%--<select id="shiftSelect"></select>--%>
                            <select class="form-control" id="shiftSelect"></select>
                            <br>
                        </div>
                        <hr><br>

                        <div id='calendar'></div>

                            <%--Create Event Modal--%>
                        <div id="createEventModal" class="modal fade">
                            <div class="modal-dialog">
                                <%--Modal Content--%>

                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X
                                        </button>
                                        <h4 id="myModalLabel1"><b>Assign a shift</b></h4>
                                    </div>

                                    <%--Modal Body--%>
                                    <div class="modal-body">
                                        <form id="createAppointmentForm" class="form-horizontal">
                                            <div class="control-group">
                                                <label class="control-label"><u>Shift:</u> </label>
                                                <div class="controls">
                                                    <select class="form-control" name="eventCampus" id="eventCampus">
                                                        <option value='all' id='allCampuses' disabled="true" selected>Select Campus
                                                        </option>
                                                        <option value="BURNABY" class="BURNABY">BURNABY</option>
                                                        <option value="SURREY" class="SURREY">SURREY</option>
                                                        <option value="VANCOUVER" class="VANCOUVER">VANCOUVER</option>
                                                    </select>

                                                    <select class="form-control" name="eventTitle" id="eventTitle">

                                                        <option value="SURREY" disabled="true" selected="selected">Select Surrey Shift
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

                                                        <option value="VANCOUVER" disabled="true" selected="selected">Select Vancouver Shift
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

                                                        <option value="BURNABY" disabled="true" selected="selected">Select Burnaby Shift
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
                                                <label class="control-label" id = "hideDate"><u>Date:</u></label>
                                                <label class="control-label" id = "addShiftTime"><u>Date:</u><br>
                                                    Start: <input type="datetime-local" id="startTime" name = "evtStart"/><br>
                                                    End: <input type="datetime-local" id="endTime" name = "evtEnd"/>

                                                </label>
                                                <label id="when"></label>

                                            </div>
                                            <div style="padding-left: 15px;padding-right: 15px">
                                                <div class="form-group">
                                                    <label class="control-label"><u>Member:</u></label>
                                                    <div class="controls">
                                                        <select class = "form-control" name="eventMember" id = "eventMember" data-tab = "${user.getUsername()}">
                                                            <c:forEach items="${users}" var = "user">
                                                                <option value = "${user.getUsername()}" >
                                                                        ${user.getUsername()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label"><u>Location:</u></label>
                                                    <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="eventMember" id="eventLocation" placeholder="Enter the Location">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label"><u>Required Training:</u></label>
                                                    <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="eventMember" id="eventRequiredTraining" placeholder="Enter the Requirements">
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label">Notes: </label>
                                                    <textarea style="border-width:1px;border-color: #a9b7d1;height: 100px" class="form-control" rows="8" id="eventNotes"></textarea>
                                                </div>
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

    $('#createEventModal').on('hidden.bs.modal', function () {
        $(this).find("input,textarea,select").val('').end();

    });

//    $(document).ready(function() {
//        $('#eventRequiredTraining').multiselect();
//    });




</script>

</body>

</html>
