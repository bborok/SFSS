<%@ page import="com.zeta.Models.User" %>
<%@ page import="com.zeta.Models.Announcement" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  model.User: PrivateAcc
  Date: 2017-09-29
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>SFU</title>

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">


</head>



<script>
    var api = '${pageContext.request.contextPath}';
</script>

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

    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("user", user);
        String burnabyColor = "#9fa8da";
        pageContext.setAttribute("burnabyColor", burnabyColor);
        String surreyColor = "#9ccc65";
        pageContext.setAttribute("surreyColor", surreyColor);
        String vancouverColor = "#bbdefb";
        pageContext.setAttribute("vancouverColor", vancouverColor);
    %>

    <script>
        var user = "${user.username}";
//          var user = "bobaec"; // for local use

        var announce = {
            <c:forEach items="${announcements}" var = "announcement">
            "${announcement.id}" : {
                user : '${announcement.username}',
                title : '${announcement.title}',
                message : '${announcement.message}',
                date : '<fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium"
                                                        value = "${announcement.date}" />',
                campus : '${announcement.campus}',
                color: <c:choose><c:when test="${announcement.campus == 'BURNABY'}">'${burnabyColor}'</c:when><c:when test="${announcement.campus == 'SURREY'}">'${surreyColor}'</c:when><c:when test="${announcement.campus == 'VANCOUVER'}">'${vancouverColor}'</c:when></c:choose>,
                role : '${user.role}',
                id : ${announcement.id}

        },
            </c:forEach>
        };

    </script>

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
                               <button type="button" class = "btn btn-primary" id = "createAnnouncement">Add an Announcement</button></li>
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
                                                <input class="form-control" name="announceTitleModal" id="announceTitleModal" style="width:100%;float:left;" placeholder="Enter a title." required>
                                                </input>
                                            </div>

                                            <br><br>

                                            <label class="control-label" style="float:left;"><u>Specific Team:</u></label>
                                            <select class="form-control" name="announceCampusModal" id="announceCampusModal" required>
                                                <option value='all' id='allCampuses' disabled="true" selected>Select Teams
                                                </option>
                                                <option value ="BURNABY" class = "BURNABY">BURNABY</option>
                                                <option value ="SURREY" class = "SURREY">SURREY</option>
                                                <option value ="VANCOUVER" class = "VANCOUVER">VANCOUVER</option>

                                            </select>

                                            <label class="control-label" style="float:left;" ><u>Message: </u> </label><br>
                                            <div class="controls">
                                            <textarea style="border-width:1px;border-color: #a9b7d1;height: 100px" class="form-control" rows="8" id = "announceMessageModal" placeholder="Enter a message." required></textarea>
                                            </div>
                                        </div>

                                        <div class = "modal-footer">
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                                            <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id = "editAnnouncementModal" class = "modal fade">
                                <div class = "modal-dialog">
                                    <div class = "modal-content">
                                        <div class = "modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X
                                            </button>
                                            <h4 id="myModalLabel2" style="text-align:left"><b>Edit an Announcement</b></h4>
                                        </div>
                                        <div class = "modal-body">
                                            <label class="control-label" id ="title" style="float:left;"><u>Title: </u> </label><br>
                                            <div class="controls">
                                                <input class="form-control" name="announceTitleModal" id="announceSaveTitle" style="width:100%;float:left;" placeholder="Enter a title." required>
                                                </input>
                                            </div>

                                            <br><br>

                                            <label class="control-label" id="campus" style="float:left;"><u>Specific Team:</u></label>
                                            <select class="form-control" name="announceCampusModal" id="announceSaveCampus" required>
                                                <option value='all' id='allSaveCampus' disabled="true" selected>Select Teams
                                                </option>
                                                <option value ="BURNABY" class = "BURNABY">BURNABY</option>
                                                <option value ="SURREY" class = "SURREY">SURREY</option>
                                                <option value ="VANCOUVER" class = "VANCOUVER">VANCOUVER</option>

                                            </select>

                                            <label class="control-label" id="message" style="float:left;" ><u>Message: </u> </label><br>
                                            <div class="controls">
                                                <textarea style="border-width:1px;border-color: #a9b7d1;height: 100px" class="form-control" rows="8" id = "announceSaveMessage" placeholder="Enter a message." required></textarea>
                                            </div>
                                        </div>

                                        <div class = "modal-footer">
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                                            <button type="submit" class="btn btn-primary" id="saveButton">Save</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </center>
                        <div class="panel panel-primary" id = "fixed" data-spy="affix" style ="width:25%;text-align:left;float:right">
                            <div class="panel-heading" id = "announceFilter" style="font-weight:bold;">Filter</div>
                            <div class="panel-body" id = campusLead>
                                <div class="col-sm-12 row">
                                    <div class="radio" id = "filter">
                                        <label>
                                            <input class='allOrNone' type="checkbox" value="ALLCAMPUSES" id="ALLCAMPUSES" onclick="allOrNone(this)" checked>ALL CAMPUSES
                                        </label>
                                        <br>
                                        <label>
                                            <input class='campusFilter' type="checkbox" value="BURNABY" id="BURNABY">BURNABY
                                        </label>
                                        <br>
                                        <label>
                                            <input class='campusFilter' type="checkbox" value="SURREY" id="SURREY">SURREY
                                        </label>
                                        <br>
                                        <label>
                                            <input class='campusFilter' type="checkbox" value="VANCOUVER" id="VANCOUVER">VANCOUVER
                                        </label>
                                        <br>
                                        <label>
                                            <input class='campusFilter' type="checkbox" value="SUPERVISOR" id="SUPERVISOR">SUPERVISOR
                                        </label>
                                        <br>
                                        <label>
                                            <input class='campusFilter' type="checkbox" value="ADMINISTRATOR" id="ADMINISTRATOR">ADMINISTRATOR
                                        </label>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="controls" id = "sortAnnounce" style="width:65%;">
                            <div id="alertsDiv"></div>
                            <c:forEach items="${announcements}" var = "announcement">
                                <div class = "check" id = "${announcement.id}">
                                <div class="panel panel-primary" id = "sortAnnounce2" style ="text-align:left">

                                    <div class="panel-heading" id = "announceTitle" style="font-weight:bold;background-color:<c:choose><c:when test="${announcement.campus == 'BURNABY'}">${burnabyColor}</c:when><c:when test="${announcement.campus == 'SURREY'}">${surreyColor}</c:when><c:when test="${announcement.campus == 'VANCOUVER'}">${vancouverColor}</c:when></c:choose>">${announcement.title} <a id = "sortCampus" style="color:white;">| ${announcement.getCampus()}</a>
                                    </div>
                                    <hr>
                                    <div class="panel-body" id = announceBody>
                                        ${announcement.message}
                                    </div><hr>
                                    <div class = "panel-body" id = "announceDate" style="font-weight:bold;">Date:
                                        <a style="color:black;font-weight:normal;"><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium"
                                                        value = "${announcement.date}" /></a>
                                    </div>
                                    <div class = "panel-body" id = "announceAuthor" style="font-weight:bold;">Author: <a style="color:black;font-weight:normal;">${announcement.getUsername()}</a>
                                        <button type="button" class="btn btn-primary editButton" style="float:right;margin-left:2%;width:10%;" id = "${announcement.getId()}" onclick="doEdit(${announcement.id}, '${announcement.title}', '${announcement.message}', '${announcement.campus}')">Edit</button>
                                        <button type="button" class="btn btn-primary removeButton" style="float:right;width:10%;vertical-align:middle;" id = "${announcement.getId()}" onclick="doRemove(${announcement.id})">Delete</button>
                                    </div>
                                </div>
                                </div>
                            </c:forEach>

                        </div>

                    </div>
                <hr><br><br>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/cidric.png" alt="" class="img-circle" height="140px" width="140px">
                        <h4><b>Cidric Butac</b></h4>
                        <h4>Supervisor</h4>
                        <p>sfucsp@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/miriam.png" class="img-circle" height="140px" width="140px">
                        <h4><b>Miriam Sise Odaa</b></h4>
                        <h4>Burnaby Team Lead</h4>
                        <p>ssepbur@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/kitty.png" class="img-circle" height="140px" width="140px">
                        <h4><b>Kitty Lo</b></h4>
                        <h4>Surrey Team Lead</h4>
                        <p>ssepsur@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-3">
                    <center>
                        <img src="resources/img/stole_from_sfu/satpal.png" class="img-circle" height="140px" width="140px">
                        <h4><b>Satpal Samra</b></h4>
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

    $("#filter input").click(function() {
        var burnabyCheck = $('#BURNABY').is(":checked");
        var surreyCheck = $('#SURREY').is(":checked");
        var vancouverCheck = $('#VANCOUVER').is(":checked");
        var supervisorCheck = $('#SUPERVISOR').is(":checked");
        var administratorCheck = $('#ADMINISTRATOR').is(":checked");

        var filterArray = [];
        for (var campus in announce) {
            if (announce[campus].campus.toUpperCase() == "BURNABY" && burnabyCheck) {
                filterArray.push(announce[campus]);
            }
            if (announce[campus].campus.toUpperCase() == "SURREY" && surreyCheck) {
                filterArray.push(announce[campus]);
            }
            if (announce[campus].campus.toUpperCase() == "VANCOUVER" && vancouverCheck) {
                filterArray.push(announce[campus]);
            }
            if (announce[campus].role.toUpperCase() == "SUPERVISOR" && supervisorCheck) {
                filterArray.push(announce[campus]);
            }
            if (announce[campus].role.toUpperCase() == "ADMINISTRATOR" && administratorCheck) {
                filterArray.push(announce[campus]);
            }
        }
        filterArray.sort(function(firstCampus, comparingCampus) {
            if (firstCampus.date > comparingCampus.date) {
                return -1;
            }
            if (firstCampus.date < comparingCampus.date) {
                return 1;
            }
            return 0;
        });
        <%--<c:choose><c:when test="${announcement.campus == 'BURNABY'}">'${burnabyColor}'</c:when>--%>
        <%--<c:when test="${announcement.campus == 'SURREY'}">'${surreyColor}'</c:when>--%>
        <%--<c:when test="${announcement.campus == 'VANCOUVER'}">'${vancouverColor}'</c:when></c:choose>,--%>

        var htmlAdd = "";
        for (var index in filterArray) {
            htmlAdd += "<div class = 'check'>" +
                    "<div class = 'panel panel-primary' id = 'sortAnnounce2' style = 'text-align:left'>" +
                    "<div class = 'panel-heading' id = 'announceTitle' style='font-weight:bold;background-color:" + filterArray[index].color + "'>" + filterArray[index].title +
                    "<a id = 'sortCampus' style='color:white;'>" + " | " + filterArray[index].campus + "</a>" +
                    "</div>" + "<hr>" +
                    "<div class = 'panel-body' id = 'announceBody'>" + filterArray[index].message + "</div>" + "<hr>" +
                    "<div class = 'panel-body' id = 'announceDate' style='font-weight:bold;'>" + "Date: " +

                    "<a style='color:black;font-weight:normal;'>" + filterArray[index].date + "</a>" +
                     "</div>" +
                    "<div class = 'panel-body' id = 'announceAuthor' style='font-weight:bold;'>Author: " +"<a style='color:black;font-weight:normal;'>" + filterArray[index].user + "</a>" +
                    "<button type = 'button' class = 'btn btn-primary editButton' style='float:right;margin-left:2%;width:10%;' id = '" + filterArray[index].id + "'" + " onclick='doEdit(" + filterArray[index].id +
                    ',"' + filterArray[index].title + '"' + ',"' + filterArray[index].message + '"' + ',"' + filterArray[index].campus + '"' +
                ")'>Edit</button>" +
                    "<button type = 'button' class = 'btn btn-primary removeButton' style = 'float:right;width:10%;' id = '" + filterArray[index].id + "'" + " onclick='doRemove(" + filterArray[index].id +
                ")'>Delete</button>" +
                    "</div></div></div>"

        }

        console.log(filterArray);
        if (htmlAdd.length == 0) {
            var emptyAdd = "";
            emptyAdd += "<div class ='empty'>" +
                    "<div class = 'panel panel-primary' style='text-align:left'>" +
                    "<div class = 'panel-heading'>" + "No announcements to show" +
                "</div>" + "</div>" + "</div>" + "<br>"+ "<br>"+ "<br>"+ "<br>"+ "<br>"+ "<br>" + "<hr>" + "<br>";

            $('#sortAnnounce').empty();
            $('#sortAnnounce').append(emptyAdd);
        } else {
            $('#sortAnnounce').empty();
            $('#sortAnnounce').append(htmlAdd);
        }

    });

    function doEdit(id, title, message, campus) {

        $('#announceSaveTitle').val(title);
        $('#announceSaveMessage').val(message);
        $('#announceSaveCampus').val(campus);

        $('#editAnnouncementModal').modal('show');

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $('#saveButton').on('click', function (e) {
            e.preventDefault();
            save();
        });
        function save() {
            var announcement = {
                "id" : parseInt(id),
                "username": user.trim(),
                "title": $('#announceSaveTitle').val().trim(),
                "message": $('#announceSaveMessage').val(),
                "campus": $('#announceSaveCampus').val().toUpperCase().toString()
            };
            $('#editAnnouncementModal').modal('hide');

            $.ajax({
                type: 'POST',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                url: api + '/announcements/add',
                data: JSON.stringify(announcement),
                success: function() {
                    displaySuccessAlert('Saving edited ' + announcement.title + ' to database...');
                    setTimeout(function() {
                        location.reload();
                    }, 3000);
                },
                error: function() {
                    displayErrorAlert(('Failed to save ' + announcement.title + ' to database.'));
                },
                contentType: "application/json; charset=utf-8"
            });
        }

    };

    function doRemove(ID) {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

         $.ajax({
             type: 'POST',
             beforeSend: function(xhr) {
                 xhr.setRequestHeader(header, token);
             },
             url: api + '/announcements/remove',
             data: JSON.stringify(ID),

             success: function() {
                 displaySuccessAlert('Removing ' + ID + ' from database...');
                 setTimeout(function() {
                     location.reload();
                 }, 3000);
             },
             error: function() {
                 displayErrorAlert(('Failed to remove ' + ID + ' from database.'));
             },
             contentType: "application/json; charset=utf-8"
         });
    }

</script>
</body>
</html>