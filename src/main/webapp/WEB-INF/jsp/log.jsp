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
            <li>
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/schedule">Schedule</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/statistics">Statistics</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
            </li>
            <li class="active">
                <a href="${pageContext.request.contextPath}/log">Log</a>
            </li>
            <li>
                <a type="button" data-toggle="modal" data-target="#myModal">IF NO AUTH</a>
            </li>
        </ul>
        <div id="side-container">
            <div id="side-contact" style="text-align:center">

                <p style="text-align:center; font-size:20px">
                    <a class="fa fa-sign-out fa-x" href="${pageContext.request.contextPath}/">Sign Out</a>
                </p>
                <br>
                <p style="font-weight: bold; text-decoration: underline;">Contact: </p>
                <p class="fa fa-phone "> (604)-666-6666</p>

                <p class="fa fa-envelope-o"> admin_sfep@sfu.ca</p>
            </div>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:100px">
        <div class="modal-dialog" role="document" style="margin-top:100px">
            <div class="modal-content" style="background-color:red">

                <div class="modal-body" style="color:white">
                    <center>
                        <strong>ACCESS DENIED </strong>
                        <p>No Authorization </p>
                    </center>
                </div>
            </div>
        </div>
    </div>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
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

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
