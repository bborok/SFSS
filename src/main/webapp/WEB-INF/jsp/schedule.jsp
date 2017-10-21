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
        color: #ffffff;
        bottom: 0;
    }

</style>



<body>

<div id="wrapper" class="toggled">

    <jsp:include page="partfiles/sidebar.jsp"/>


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
                                            <label class="control-label" for="inputTitle">Title:</label>
                                            <div class="controls">
                                                <input type="text" name="eventTitle" id="eventTitle" placeholder="Enter a short description."/>                                              <input type="hidden" id = apptID"/>
                                                <input type="hidden" id="apptStartTime"/>
                                                <input type="hidden" id="apptEndTime"/>
                                                <input type="hidden" id="apptAllDay" />
                                            </div>
                                            <label class="control-label" for="when">When:</label>
                                            <div class="controls">
                                                <div class="controls" id="when">
                                                </div>
                                            </div>

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
