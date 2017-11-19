<%@ page import="com.zeta.Models.User" %><%--
  Page that displays the currently logged in users information.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SFU</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

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
        bottom: 0;
        color: #ffffff;
    }

    tr {
        width: 100%;
        display: inline-table;
    }

    table {
        height: 300px;
    }

    tbody {
        overflow-y: scroll;
        height: 500px;
        position: absolute;
    }

    .click a {
        color: chocolate;
    }


</style>

<body>

<div id="wrapper" class="toggled">

    <jsp:include page="partfiles/sidebar.jsp"/>
    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("user", user);
    %>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>

            <div class="col-sm-12 text">
                <center>
                    <%--SFU LOGO--%>
                    <div class="description">
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive"
                             style="height:100px;width:500px">
                        <hr>
                    </div>
                </center>

                <div id="userModal" class="modal fade">
                    <div class="modal-dialog">
                        <%--Modal Content--%>

                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X
                                </button>
                                <h4 id="myModalLabel1"><b>Edit User</b></h4>
                            </div>

                            <%--Modal Body--%>
                            <div class="modal-body">
                                <form id="userForm" class="form-horizontal" data-toggle="validator">
                                    <div style="padding-left: 15px;padding-right: 15px">
                                        <div class="form-group">
                                            <label class="control-label"><u>Username:</u></label>
                                            <div class="input-group">
                                                <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="username" id="username" placeholder="Enter Username">
                                                <span class="input-group-addon">@sfu.ca</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Student Number:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="studentNumber" id="studentNumber" placeholder="Enter Student Number">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Full Name:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="name" id="userFullName" placeholder="Enter Full Name">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Email:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="email" id="userEmail" placeholder="Enter Alternate Email">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Phone Number:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="phoneNumber" id="userPhoneNumber" placeholder="555-555-1234">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Role:</u></label>
                                            <div class="controls">
                                                <select class="form-control" name="role" id="userRole" required>
                                                    <option value="" disabled="disabled" selected="selected">Select A Role
                                                        <c:forEach items="${roles}" var="role">
                                                    <option value="${role.name()}">
                                                            ${role.name()}
                                                    </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row-fluid">
                                            <label class="control-label"><u>Preferred Campus:</u></label>
                                            <br>
                                            <label class="radio-inline">
                                                <input type="radio" name="campus" id="BURNABY" value="BURNABY" required>Burnaby
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="campus" id="SURREY" value="SURREY" required>Surrey
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="campus" id="VANCOUVER" value="VANCOUVER" required>Vancouver
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Call Sign:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="callSign" id="userCallsign" placeholder="Enter Call Sign">
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                                        <%--AJAX Request to POST to ShiftController--%>
                                        <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <br><br>
                <center>
                    <div>
                        <div>
                            <h1>
                                <b><c:out value="${user.getName()}"/>'s Profile</b>
                                <br>
                                <button type="button" class="btn" data-toggle="modal" data-target="#userModal">Edit User</button>
                            </h1>
                            <center>
                                <img src="resources/img/etc/annonymous.jpg" class="img-responsive" height="300"
                                     width="300">
                            </center>

                        </div>
                        <div>
                            <h3>Username: ${user.getUsername()}</h3>
                            <h3>Student Number: ${user.getStudentNumber()}</h3>
                            <h3>Role: ${user.getRole()}</h3>
                            <%--Info to display if role is MEMBER --%>
                            <c:if test="${user.getRole() eq 'MEMBER'}">
                                <h3>Campus: ${user.getPreferredCampus()}</h3>
                                <h3>Training:</h3>
                                <c:choose>
                                    <c:when test="${empty user.getTraining()}">
                                        Currently not trained for any task
                                    </c:when>
                                    <c:otherwise>
                                        <dl>
                                            <c:set var="totalHours" value="${0}" />
                                            <c:forEach var="training" items="${user.getTraining()}"
                                                       varStatus="status">
                                                <dt>${training.getTask()}:</dt>
                                                <dd>${training.getHours()} hours</dd>
                                                <c:set var="totalHours" value="${totalHours + training.getHours()}" />
                                            </c:forEach>
                                            <dt>Total Hours:</dt>
                                            <dd>${totalHours} hours</dd>
                                        </dl>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                    </div>
                </center>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/popper/popper.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    function switchColors(element) {
        links = document.getElementsByTagName("tr");
        for (var i = 0; i < links.length; i++)
            links.item(i).style.color = 'black';
        element.style.color = 'orange';
    };

    $(function () {
        $("table td").click(function () {
            event.preventDefault();
            $('table td').removeClass('current');
            $(this).addClass("current");
            var tab = $(this).parent().attr("data-tab");
            $('.tab-content').hide();
            $('#' + tab).fadeIn();
        });
    });
</script>

</body>

</html>

