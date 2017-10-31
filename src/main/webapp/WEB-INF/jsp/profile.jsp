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

    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <p>SFU SFEP</p>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/schedule">Schedule</a>
            </li>
            <li class="active">
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/statistics_info_lf">Statistics</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log">Log</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/timecard">Time Card</a>
            </li>
            <c:if test="${sessionScope.user.role != 'MEMBER'}">
                <li>
                    <a href="${pageContext.request.contextPath}/users">Users</a>
                </li>
            </c:if>
        </ul>
        <div id="side-container">
            <div id="side-contact" style="text-align:center; margin-bottom:10px;">

                <p style="text-align:center; font-size:20px">
                    <a style=" color: yellow" class="fa fa-sign-out fa-x" href="${pageContext.request.contextPath}/">Sign Out</a>
                </p>
                <br>
                <p style="font-weight: bold; text-decoration: underline;">Contact: </p>
                <p class="fa fa-phone "> (778)-782-5425</p>
                <p class="fa fa-envelope-o col-sm-12"> sfucsp@sfu.ca</p>
                <%--https://www.sfu.ca/srs/security/contact-us.html--%>
            </div>
        </div>
    </div>
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
                                <b><c:out value="${sessionScope.user.name}"/>'s Profile</b>
                            </h1>
                            <center>
                                <img src="resources/img/etc/annonymous.jpg" class="img-responsive" height="300"
                                     width="300">
                            </center>

                        </div>
                        <div>
                            <h3>Role: ${sessionScope.user.role}</h3>
                            <h3>Student Number: ${sessionScope.user.studentNumber}</h3>
                            <h3>Username: ${sessionScope.user.username}</h3>
                            <%--Info to display if role is MEMBER --%>
                            <c:if test="${sessionScope.user.role eq 'MEMBER'}">
                                <h3>Campus: ${sessionScope.user.preferredCampus}</h3>
                                <h3>Training:</h3>
                                <c:choose>
                                    <c:when test="${empty sessionScope.user.training}">
                                        Current not trained for any task
                                    </c:when>
                                    <c:otherwise>
                                        <ul>
                                            <c:forEach var="training" items="${sessionScope.user.training}"
                                                       varStatus="status">
                                                <li>training</li>
                                            </c:forEach>
                                        </ul>
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

