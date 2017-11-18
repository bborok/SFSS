<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-09-29
  Time: 5:02 PM
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

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="resources/css/style.css">--%>

</head>

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
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav nav-pills nav-stacked" id="menu">

            <li>
                <a href="${pageContext.request.contextPath}/"><span class="fa-stack fa-lg pull-left"><i class="fa fa-home fa-stack-1x "></i></span> Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/schedule"><span class="fa-stack fa-lg pull-left"><i class="fa fa-calendar fa-stack-1x "></i></span> Schedule</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/profile"><span class="fa-stack fa-lg pull-left"><i class="fa fa-user fa-stack-1x "></i></span> Profile</a>
            </li>
            <li>
                <a href="#"> <span class="fa-stack fa-lg pull-left"><i class="fa fa-area-chart fa-stack-1x "></i></span> Statistics</a>
                <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                    <li><a href="${pageContext.request.contextPath}/statistics_info_lf"><span class="fa-stack fa-lg pull-left"><i class="fa fa-bar-chart fa-stack-1x "></i></span>Lost & Found</a></li>
                    <li><a href="${pageContext.request.contextPath}/statistics_public_contact"><span class="fa-stack fa-lg pull-left"><i class="fa fa-bar-chart fa-stack-1x "></i></span>Public Contact</a></li>

                </ul>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll"><span class="fa-stack fa-lg pull-left"><i class="fa fa-credit-card fa-stack-1x "></i></span>Payroll</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/timecard"><span class="fa-stack fa-lg pull-left"><i class="fa fa-clock-o fa-stack-1x "></i></span>Time Cards</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/users"><span class="fa-stack fa-lg pull-left"><i class="fa fa-users fa-stack-1x "></i></span>Users</a>
            </li>
        </ul>
    </div><!-- /#sidebar-wrapper -->
    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="col-sm-12 text">
                <div class="description">
                    <img src="resources/img/logo_made/logo_2.png" class="img-responsive">
                    <img src="resources/img/stole_from_sfu/sample_SSEP.png" class="img-responsive col-sm-12">
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/popper/popper.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-1.11.2.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/sidebar_menu.js"></script>

</body>

</html>

