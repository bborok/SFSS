<%--
  Created by IntelliJ IDEA.
  model.User: PrivateAcc
  Date: 2017-09-29
  Time: 5:02 PM
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

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">


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
        <div class="nav pull-right" style="padding-top: 10px;padding-right: 10px">
            <%--<button type="button" class="btn btn-default" href="${pageContext.request.contextPath}/logout" style="height: 37px;font-size: 15px;padding-bottom: 30px"><b>Sign Out</b></button>--%>
        </div>
    </div>

</nav>
<div id="wrapper" style="padding-top: 56px">

<jsp:include page="partfiles/sidebar.jsp"/>
    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid xyz">
            <div class="col-sm-12 text">
                <div class="description">
                    <%--This contains all of the relevant info about announcement--%>
                    <center>
                        <%--<h4>Announcement</h4><br>--%>
                            <img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px">
                        <div id="nav">
                            <ul>
                                <li><button type="button" id = "createAnnouncement" style = "background:#901d33">Add an Announcement</button></li>
                            </ul>
                        </div>
                        <hr>
                            <div id = "createAnnouncementModal" class = "modal fade">
                                <div class = "modal-dialog">
                                    <div class = "modal-content">
                                        <div class = "modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X
                                            </button>
                                            <h4 id="myModalLabel1" style="text-align:left"><b>Create an Announcement</b></h4>
                                        </div>
                                        <div class = "modal-body">
                                            <label class="control-label" style="float:left;"><u>Title: </u> </label><br><br>
                                            <div class="controls">
                                                <input class="form-control" name="announceTitleModal" id="announceTitleModal" style="width:100%;float:left;" placeholder="Enter a title.">
                                                </input>
                                            </div>

                                            <br><br>

                                            <label class="control-label" style="float:left;padding-top: 10px"><u>Date:</u>&nbsp;&nbsp;</label><br><br>
                                                <input type="datetime-local" id="announceStartTime" name = "annStart" style="float:left;"/>

                                            <br><br>

                                            <hr>
                                            <label class="control-label" style="float:left;"><u>Specific Team:</u></label><br><br>
                                            <select class="form-control" name="eventCampus" id="eventCampus">
                                                <option value='all' id='allCampuses' disabled="true" selected>Select Teams
                                                </option>
                                            </select>

                                            <br>

                                            <label class="control-label" style="float:left;padding-top: 10px"><u>Message: </u> </label><br>
                                            <div class="controls">
                                            <textarea style="border-width:1px;border-color: #a9b7d1;height: 100px" class="form-control" rows="8" id="eventNotes" placeholder="Enter a message."></textarea>
                                            </div>
                                        </div>

                                        <div class = "modal-footer">
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                                            <button type="submit" class="btn btn-primary" id="submitAnnouncement">Save</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </center>
                        <div class="panel panel-primary" id = "fixed" data-spy="affix" style ="width:25%;text-align:left;float:right">
                            <div class="panel-heading" id = "announceFilter">Filter</div>
                            <div class="panel-body" id = campusLead>
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
                                    </div>
                                    <select class="form-control" id="shiftSelect"></select>
                                    <br>
                                </div>
                            </div>
                        </div>
                        <div class="controls" style="width:65%;">
                            <c:forEach items="${announcements}" var = "announcement">
                                <div class="panel panel-primary" style ="text-align:left"> <%--for demonstration purposes, should be deleted later--%>
                                    <div class="panel-heading" id = "announceTitle">${announcement.getTitle()}</div>
                                    <hr>
                                    <div class="panel-body" id = announceBody>
                                        ${announcement.getMessage()}
                                    </div><hr>
                                    <div class = "panel-body" id = "announceDate">Date: ${announcement.getDate()}</div>
                                    <div class = "panel-body" id = "announceAuthor">Author: ${announcement.getUsername()}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>




                <hr><br>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/cidric.png" alt="" class="img-circle" height="190px" width="200px">
                        <h3><b>Cidric Butac</b></h3>
                        <h4>Supervisor</h4>
                        <p>sfucsp@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/miriam.png" class="img-circle" height="190px" width="200px">
                        <h3><b>Miriam Sise Odaa</b></h3>
                        <h4>Burnaby Team Lead</h4>
                        <p>ssepbur@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/kitty.png" class="img-circle" height="190px" width="200px">
                        <h3><b>Kitty Lo</b></h3>
                        <h4>Surrey Team Lead</h4>
                        <p>ssepsur@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/satpal.png" class="img-circle" height="190px" width="200px">
                        <h3><b>Satpal Samra</b></h3>
                        <h4>Vancouver Team Lead</h4>
                        <p>ssepvan@sfu.ca</p>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->
<!-- Bootstrap core JavaScript -->
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/popper/popper.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src='resources/js/announcements.js'></script>
<script src="resources/js/sidebar_menu.js"></script>


<!-- Menu Toggle Script -->
<script>

    var top = $('.thisone').offset().top;
    $('.trigger').click(function () {
        $('.thisone').css('position','');
        $('.left2').toggle('slow',function(){
            top = $('.thisone').offset().top;
        });


    });

    $(document).scroll(function(){
        $('.thisone').css('position','');
        top = $('.thisone').offset().top;
        $('.thisone').css('position','absolute');
        $('.thisone').css('top',Math.max(top,$(document).scrollTop()));
    });

</script>
</body>
</html>