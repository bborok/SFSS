<%--
  Created by IntelliJ IDEA.
  model.User: PrivateAcc
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
        color: #ffffff;
    }

</style>

<body>

<div id="wrapper" class="toggled">


    <jsp:include page="partfiles/sidebar.jsp"/>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
            <div class="col-sm-12 text">
                <div class="description">
                    <center>
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px">
                        <img src="resources/img/stole_from_sfu/sample_SSEP.png" class="img-responsive">
                    </center>
                </div>
                <div class="col-sm-6">
                    <center>
                        <img src="resources/img/etc/dog.jpg" class="img-circle" height="200px" width="200px">
                        <h3>Supervisor</h3>
                        <p>604-666-666</p>
                        <p>advisor@sfu.ca</p>
                    </center>
                </div>
                <div class="col-sm-6">
                    <center>
                        <img src="resources/img/etc/dog.jpg" class="img-circle" height="200px" width="200px">
                        <h3>Manager</h3>
                        <p>778-777-777</p>
                        <p>manager@sfu.ca</p>
                    </center>
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
