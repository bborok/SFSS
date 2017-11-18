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

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">

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
<nav class="navbar navbar-default no-margin">
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

<div id="wrapper">

    <jsp:include page="partfiles/sidebar.jsp"/>
    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("user", user);
    %>

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
                            <h1>
                                <b><c:out value="${user.getName()}"/>'s Profile</b>
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
<script src="resources/js/sidebar_menu.js"></script>

<!-- Menu Toggle Script -->
<script>

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

