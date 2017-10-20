<%--
  Created by IntelliJ IDEA.
  model.User: PrivateAcc
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

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <p>SFU SFEP</p>
            </li>
            <li >
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/schedule">Schedule</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li class="active">
                <a href="${pageContext.request.contextPath}/statistics_info_lf">Statistics</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log">Log</a>
            </li>
        </ul>
        <div id="side-container">
            <div id="side-contact" style="text-align:center; margin-bottom:10px">

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
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
            <div class="col-sm-12 text">
                <div class="description">
                    <center><img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px"></center><hr>
                </div>
            </div>
            <br>
            <div class="col-sm-12">
                <a href="${pageContext.request.contextPath}/statistics_info_lf" class="btn btn-primary btn-lg btn-block" style="height:80px;font-size:20px;padding-top:25px" role="button">Info & Lost and Found</a>
                <br><br>
                <a href="${pageContext.request.contextPath}/statistics_public_contact" class="btn btn-primary btn-lg btn-block" style="height:80px;font-size:20px;padding-top:25px" role="button">Public Contact</a>
            </div>
        </div>
    </div>

    <!-- /#page-content-wrapper -->

</div>
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/popper/popper.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
