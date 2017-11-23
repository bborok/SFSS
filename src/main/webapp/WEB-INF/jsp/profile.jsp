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

                <br><br>
                <center>
                    <div>
                        <div>
                            <h1>
                                <b><c:out value="${user.getName()}"/>'s Profile</b>
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
            showPreview: false,
            showCancel: false,
            showRemove: false,
            showCaption: false,
            allowedFileExtensions: ["jpg", "png", "gif"],
            maxImageWidth: 300,
            maxImageHeight: 300,
            resizeImage: true,
            uploadUrl: "/user/addImage",
            maxFileCount: 1
        });
    });

    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>

