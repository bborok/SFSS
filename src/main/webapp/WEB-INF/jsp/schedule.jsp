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

    <link rel='stylesheet' href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.css" />

    <!-- FullCalendar Resources -->
    <link rel='stylesheet' href='resources/fullcalendar/fullcalendar.css' />
    <script src='resources/lib/jquery.min.js'></script>
    <script src='resources/lib/moment.min.js'></script>
    <script src='resources/fullcalendar/fullcalendar.js'></script>

    <script src= 'resources/js/schedule.js'></script>

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
    #side-container{
    }

    #side-contact{
        position: absolute;
        bottom: 0;
    }

</style>



<body>

<div id="wrapper" class="toggled">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <p>SFU SFEP</p>
            </li>
            <li >
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li class="active">
                <a href="${pageContext.request.contextPath}/schedule">Schedule</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/statistics">Statistics</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log">Log</a>
            </li>
            <li>
                <a type="button" data-toggle="modal" data-target="#myModal">IF NO AUTH</a>
            </li>
        </ul>
        <div id="side-container">
            <div id="side-contact" style="text-align:center">

                <p style="text-align:center; font-size:20px">
                    <a class="fa fa-sign-out fa-x" href="${pageContext.request.contextPath}/">Sign Out</a>
                </p>
                <br>
                <p style="font-weight: bold; text-decoration: underline;">Contact: </p>
                <p class="fa fa-phone "> (604)-666-6666</p>

                <p class="fa fa-envelope-o"> admin_sfep@sfu.ca</p>
            </div>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:100px">
        <div class="modal-dialog" role="document" style="margin-top:100px">
            <div class="modal-content" style="background-color:red">

                <div class="modal-body" style="color:white">
                    <center>
                        <strong>ACCESS DENIED </strong>
                        <p>No Authorization </p>
                    </center>
                </div>
            </div>
        </div>
    </div>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
            <div class="col-sm-12 text">
                <div class="description">
                    <center>
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px">
                    </center>
                    <!-- <img src="resources/img/stole_from_sfu/sample_SSEP.png" class="img-responsive col-sm-12"> -->
                    <%--<select name = "campusSelect" id="campusSelect">--%>
                        <%--<option value="all" id = "allCampus">All Campuses</option>--%>
                        <%--<option value="Burnaby" class = "Burnaby">Burnaby</option>--%>
                        <%--<option value="Surrey" class = "Surrey">Surrey</option>--%>
                        <%--<option value="Vancouver" class = "Vancouver">Vancouver</option>--%>
                    <%--</select>--%>
                    <div class="checkbox">
                        <label>
                            <input class='campusFilter' type="checkbox" value="Burnaby" id = "Burnaby" checked>Burnaby</label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input class='campusFilter' type="checkbox" value="Surrey" id = "Surrey">Surrey</label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input class='campusFilter' type="checkbox" value="Vancouver" id = "Vancouver">Vancouver</label>
                    </div>
                    <select id = "shiftSelect"></select>
                    <br>
                    <div id='calendar'></div>
                    <div id = "createEventModal" class = "modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                    <h3 id="myModalLabel1">Assign a shift</h3>
                                </div>
                                <div class="modal-body">
                                    <form id="createAppointmentForm" class="form-horizontal">
                                        <div class="control-group">
                                            <label class="control-label" for="inputTitle">Shift: </label>
                                            <div class="controls">
                                                <select name = "eventCampus" id="eventCampus">
                                                    <option value="Burnaby" class = "Burnaby" selected>Burnaby</option>
                                                    <option value="Surrey" class = "Surrey">Surrey</option>
                                                    <option value="Vancouver" class = "Vancouver">Vancouver</option>
                                                </select>
                                                <select name = "eventTitle" id = "eventTitle">
                                                    <option value="all">All Shifts</option>

                                                    <option value="Burnaby">--Burnaby--</option>
                                                    <option value="Burnaby" class = "Information and Lost & Found Kiosk">Information and Lost & Found Kiosk</option>
                                                    <option value="Burnaby">Speed Watch / Moving Traffic</option>
                                                    <option value="Burnaby">Community Presence</option>
                                                    <option value="Burnaby">Safety Screen</option>
                                                    <option value="Burnaby">Theft Prevention</option>
                                                    <option value="Burnaby">Auto Theft Prevention</option>
                                                    <option value="Burnaby">Bike Presence</option>
                                                    <option value="Burnaby">Special Events</option>
                                                    <option value="Burnaby">Smoking Checks</option>
                                                    <option value="Burnaby">Pedestrian Safety</option>

                                                    <option value="Surrey">--Surrey--</option>
                                                    <option value="Surrey">Community Presence</option>
                                                    <option value="Surrey">Theft Prevention</option>
                                                    <option value="Surrey">Special Events</option>
                                                    <option value="Surrey">Pedestrian Safety</option>

                                                    <option value="Vancouver">--Vancouver--</option>
                                                    <option value="Vancouver">Community Presence</option>
                                                    <option value="Vancouver">Theft Prevention</option>
                                                    <option value="Vancouver">Special Events</option>
                                                    <option value="Vancouver">Pedestrian Safety</option>

                                                </select>
                                                <%--<input type="text" name="eventTitle" id="eventTitle" placeholder="Enter a short description."/><input type="hidden" id = apptID"/>--%>
                                                <input type="hidden" id="apptStartTime"/>
                                                <input type="hidden" id="apptEndTime"/>
                                                <input type="hidden" id="apptAllDay" />
                                            </div>
                                            <label class="control-label" for="when">When:</label>
                                            <%--<div class="controls">--%>
                                                <%--<div class="controls" id="when">--%>
                                                <%--</div>--%>
                                            <%--</div>--%>

                                        </div>
                                        <div class="control-group">
                                        </div>
                                        <label class="control-label" for="inputDescription">Volunteer:</label>
                                        <h3><input type="text" name="eventMember" id="eventMember" placeholder="Enter a volunteer.">
                                        </h3>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                                    <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="fullCalModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span> <span class="sr-only">close</span></button>
                                    Title: <span id = "modalTitle" class = "modal-title"></span>
                                </div><br>
                                Start: <span id = "modalStart"></span><br>
                                End: <span id = "modalEnd"></span><br><br>
                                Volunteer: <span id = "modalMember"></span><br>
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
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<!--     <script src="resources/jquery/jquery.min.js"></script>
    <script src="resources/popper/popper.min.js"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
 -->
<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
