<%@ page import="com.zeta.Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <%--CSRF Setup, needed for AJAX requests--%>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <!-- ... -->

    <title>SFU</title>

    <!-- jQuery Resources -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%--Lodash--%>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.4/lodash.min.js"></script>
    <%--Moment.js--%>
    <script src="https://momentjs.com/downloads/moment.min.js"></script>
    <%--Notify.js--%>
    <script src='resources/js/notify.js'></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <link rel='stylesheet' href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.css"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("user", user);
    %>
    <script>
        var api = '${pageContext.request.contextPath}/api';
        var iBURNABY = [];
        var iSURREY = [];
        var iVANCOUVER = [];
        var iALLCAMPUSES = [];
        var iNOCAMPUSES = [];
        <c:forEach items="${BURNABYTASKS}" var="task">
        iBURNABY.push("${task.taskName}");
        </c:forEach>
        <c:forEach items="${SURREYTASKS}" var="task">
        iSURREY.push("${task.taskName}");
        </c:forEach>
        <c:forEach items="${VANCOUVERTASKS}" var="task">
        iVANCOUVER.push("${task.taskName}");
        </c:forEach>
        <c:forEach items="${ALLTASKS}" var="task">
        iALLCAMPUSES.push("${task.taskName}");
        </c:forEach>

        <%--Fetch the currently logged in user from session--%>
        var loggedInUser = {
            username: "${user.username}",
            name: "${user.name}",
            email: "${user.email}",
            phoneNumber: "${user.phoneNumber}",
            preferredCampus: "${user.preferredCampus.toString()}",
            studentNumber: "${user.studentNumber}",
            role: "${user.role.toString()}",
            callSign: "${user.callSign}"
        };
    </script>
    <script src='resources/js/schedule.js'></script>

    <!-- Bootstrap core CSS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">

    <%--FullCalendar--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.js"></script>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.css"></script>--%>
    <!-- Custom styles for this template -->

    <%--fullCalendarCSS.css was throwing errors in Chrome dev console--%>
    <%--<link rel="stylesheet" href="resources/css/fullCalendarCSS.css">--%>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">


    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
</head>


<body>

<nav class="navbar navbar-default no-margin navbar-fixed-top">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header fixed-brand">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"  id="menu-toggle">
            <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
        </button>
        <a class="navbar-brand" href="#"><img src="resources/img/stole_from_sfu/sfu_official_logo.png" width="220px"></a>
    </div><!-- navbar-header-->

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active" ><button class="navbar-toggle collapse in" data-toggle="collapse" id="menu-toggle-2"> <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span></button></li>
        </ul>
    </div>
</nav>
<div id="wrapper" style="padding-top: 56px">

    <jsp:include page="partfiles/sidebar.jsp"/>

    <div id="page-content-wrapper">
        <div class="container-fluid xyz">
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
                            <input class='allOrNone' type="checkbox" value="ALLCAMPUSES" id="ALLCAMPUSES" checked>ALL
                            CAMPUSES
                        </label>
                        <br>
                        <label>
                            <input class='campusFilter' type="checkbox" value="BURNABY" id="BURNABY" class="others">BURNABY
                        </label>
                        <br>
                        <label>
                            <input class='campusFilter' type="checkbox" value="SURREY" id="SURREY" class="others">SURREY
                        </label>
                        <br>
                        <label>
                            <input class='campusFilter' type="checkbox" value="VANCOUVER" id="VANCOUVER" class="others">VANCOUVER
                        </label>
                        <br>
                    </div>
                    <select class="form-control" id="shiftSelect"></select>
                    <br>
                </div>
                <hr>
                <br>

                <%--Calendar--%>
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
                                                <option value="" disabled selected>Select Campus</option>
                                                <option value="BURNABY" class="BURNABY">Burnaby</option>
                                                <option value="SURREY" class="SURREY">Surrey</option>
                                                <option value="VANCOUVER" class="VANCOUVER">Vancouver</option>
                                            </select>

                                            <select class="form-control" name="eventTitle" id="eventTitle">

                                                <option value="SURREY" disabled selected="selected">Select Surrey
                                                    Shift
                                                </option>

                                                <c:forEach items="${SURREYTASKS}" var="task">
                                                    <option value="SURREY" class="${task.taskName}">
                                                            ${task.taskName}
                                                    </option>
                                                </c:forEach>

                                                <option value="VANCOUVER" disabled selected="selected">Select
                                                    Vancouver Shift
                                                </option>

                                                <c:forEach items="${VANCOUVERTASKS}" var="task">
                                                    <option value="VANCOUVER" class="${task.taskName}">
                                                            ${task.taskName}
                                                    </option>
                                                </c:forEach>

                                                <option value="BURNABY" disabled="true" selected="selected">Select
                                                    Burnaby Shift
                                                </option>

                                                <c:forEach items="${BURNABYTASKS}" var="task">
                                                    <option value="BURNABY" class="${task.taskName}">
                                                            ${task.taskName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                    </div>
                                    <div style="padding-left: 15px;padding-right: 15px">
                                        <div class="form-group">
                                            <label><u>Date:</u></label>
                                            <div class='input-group date' id='date'>
                                                <input type='text' class="form-control input-sm"
                                                       style="border-width:1px;border-color: #a9b7d1"/>
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label><u>Start:</u></label>
                                            <div class='input-group date' id='startTime'>
                                                <input type='text' class="form-control input-sm"
                                                       style="border-width:1px;border-color: #a9b7d1"/>
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label id="addShiftTime"><u>End:</u></label>
                                            <div class='input-group date' id='endTime'>
                                                <input type='text' class="form-control input-sm"
                                                       style="border-width:1px;border-color: #a9b7d1"/>
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label"><u>Member:</u></label>
                                            <div class="controls">
                                                <select class="form-control" name="eventMember" id="eventMember"
                                                        data-tab="${user.getUsername()}">
                                                    <option value="" disabled selected>Select User</option>
                                                    <c:forEach items="${users}" var="user">
                                                        <option value="${user.getUsername()}">
                                                                ${user.getUsername()}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Location:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1"
                                                   class="form-control" name="eventMember" id="eventLocation"
                                                   placeholder="Enter the Location">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Required Training:</u></label>
                                            <div class="controls">
                                                <select class="form-control" id="eventRequiredTraining">
                                                    <option value="" selected>None</option>
                                                    <c:forEach items="${TRAININGTYPES}" var="training">
                                                        <option value="${training}">${training}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Notes: </label>
                                            <textarea style="border-width:1px;border-color: #a9b7d1;height: 100px"
                                                      class="form-control" rows="8" id="eventNotes"></textarea>
                                        </div>
                                    </div>

                                </form>
                            </div>
                            <div class="modal-footer">

                                <c:choose>
                                    <c:when test="${!(user.role eq 'MEMBER' or user.role eq 'VOLUNTEER')}">
                                        <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Select Event Modal -->
                <div id="fullCalModal" class="modal fade">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span>
                                    <span class="sr-only">close</span></button>
                                <h4><b>Title: <span id="modalTitle" class="modal-title"></span></b></h4>
                            </div>
                            <br>
                            <div style="padding-left: 15px">
                                <b><u>Date:</u> </b><span id="modalDate"></span><br>
                                <b><u>Start:</u> </b><span id="modalStart"></span><br>
                                <b><u>End:</u> </b><span id="modalEnd"></span><br>
                                <hr>

                                <b><u>Availability: </u></b>
                                <select id="availabilitySelect" class="form-control">
                                    <option value="NO_RESPONSE">No Response</option>
                                    <option value="CONFIRMED">Confirmed</option>
                                    <option value="DECLINED">Declined</option>
                                </select>
                                <span id="modalAvailability"></span>

                                <hr>

                                <b><u>Campus:</u> </b><span id="modalCampus"></span><br>
                                <b><u>Location:</u> </b><span id="modalLocation"></span><br>
                                <b><u>ID:</u> </b><span id="modalID"></span><br>
                                <hr>

                                <b><u>Member:</u> </b><span id="modalMember"></span><br>
                                <b><u>Notes:</u> </b><span id="modalNotes"></span><br>
                                <b><u>Required Training:</u> </b><span id="modalTraining"></span><br>
                                <b><u>TimeCard Submitted: </u></b> <span id="modalTimeCard"></span><br><br>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button class="btn btn-primary" id="btnConfirmAvailability">Update
                                    Availability
                                </button>
                                <c:choose>
                                    <c:when test="${user.role eq 'MEMBER' or user.role eq 'VOLUNTEER'}">

                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-primary" id="btnDelete">Remove</button>
                                    </c:otherwise>
                                </c:choose>
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

    $('#createEventModal').on('hidden.bs.modal', function () {
        $(this).find("input,textarea,select").val('').end();

    });

    //    $(document).ready(function() {
    //        $('#eventRequiredTraining').multiselect();
    //    });


</script>

<!-- Bootstrap core JavaScript -->

</body>

</html>
