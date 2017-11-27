<%@ page import="com.zeta.Models.User" %>
<%--
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

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>SFU</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.4.5/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

    <!-- Custom styles for this template -->
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <script>
        var api = '${pageContext.request.contextPath}/user';
    </script>

</head>

<style>

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
    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("user", user);
    %>

    <script>
        var loggedInUser = {
            username : "${user.username}",
            name : "${user.name}",
            email : "${user.email}",
            phoneNumber : "${user.phoneNumber}",
            preferredCampus : "${user.preferredCampus.toString()}",
            studentNumber : "${user.studentNumber}",
            role : "${user.role.toString()}",
            callSign : "${user.callSign}"
        };
    </script>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <div class="col-sm-12 text">
                <center>
                    <%--SFU LOGO--%>
                    <div class="description">
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive"
                             style="height:100px;width:500px">
                        <hr>
                    </div>
                </center>

                <br><br>
                <center>
                    <div>
                        <div>
                            <h1 class="row" style="padding-left: 20px">
                                <b><c:out value="${user.getName()}"/>'s Profile</b>
                                <button type="button" id="editButton" class="btn"><i class="fa fa-edit"></i></button>

                                <br>
                            </h1>
                            <center>
                                <img src="/user/image/${user.username}" class="img-responsive img-circle" height="300"
                                     width="300">
                            </center>
                            <label class="control-label"><u>Upload Profile Picture:</u></label>
                            <div class="file-loading">
                                <input id="userImage" name="userImage" type="file" accept="image/*">
                            </div>
                            <div id="file-error"></div>

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
<script src="resources/js/sidebar_menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.4.5/js/fileinput.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $(document).ready(function() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader(header, token);
        });

        $('#userImage').fileinput({
            maxFileSize: 1500,
            browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
            showUpload: false,
            showPreview: false,
            showCancel: false,
            showRemove: false,
            showCaption: false,
            allowedFileTypes: ["image"],
            maxImageWidth: 300,
            maxImageHeight: 300,
            resizeImage: true,
            uploadAsync: true,
            uploadUrl: "/user/addImage",
            minFileCount: 1,
            maxFileCount: 1
        }).on('filebatchselected', function () {
            $('#userImage').fileinput('upload');
        });
    });

    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>

