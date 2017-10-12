<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
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
        bottom: 0;
        color: #ffffff;
    }

    tr{
        width: 100%;
        display: inline-table;
    }

    table{
        height:300px;
    }
    tbody{
        overflow-y: scroll;
        height: 500px;
        position: absolute;
    }
    .click a{
        color: chocolate;
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
            <li class="active">
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/statistics">Statistics</a>
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



    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
            <div class="col-sm-12 text">
                <center>
                    <div class="description">
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px"><hr>
                    </div></center>
                <br><br>

                <row>
                    <div class="col-sm-6" style="height:600px; text-align:center; border-style:solid" >
                        <p>
                            <b>Users</b>
                        </p>

                        <table class="table table-striped" style="text-align:left; ">
                            <thead>
                            <tr>
                                <th width="40%">Name</th>
                                <th width="30%">#</th>
                            </tr>
                            </thead>
                            <tbody style="color:black">
                            <tr onclick="switchColors(this);" data-tab="one">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="two">
                                <td>Steven Kim</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="three">
                                <td>Steven Pak</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            <tr onclick="switchColors(this);" data-tab="none">
                                <td>Steven Lee</td>
                                <td>301250558</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="sample col-sm-6" style="text-align:center; border-style:solid; height:600px" >

                        <div class="tab-content">
                            <p>
                                <b>Profile</b>
                            </p>
                            <center>
                                <img src="resources/img/etc/annonymous.jpg" class="img-responsive" height="300" width="300">
                            </center>
                            <h3>User Profile</h3>
                            <h5>Select User from the list</h5>
                        </div>

                        <div class="tab-content" id="none" style="display: none;">
                            <p>
                                <b>Profile</b>
                            </p>
                            <center>
                                <img src="resources/img/etc/annonymous.jpg" class="img-responsive" height="300" width="300">
                            </center>
                            <h3>--</h3>
                            <h5>This user has no profile yet</h5>
                        </div>



                        <div class="tab-content" id="one"  style="display: none;">
                            <p>
                                <b>Profile</b>
                            </p>
                            <center>
                                <img src="resources/img/etc/dog.jpg" class="img-responsive" height="300" width="300">
                            </center>
                            <h3>Steven Lee</h3>
                            <h4>Volunteer</h4>
                            <p>mla189@sfu.ca</p>
                            <h5>Campus: Burnaby | Surrey</h5>
                        </div>

                        <div class="tab-content" id="two"  style="display: none;">
                            <p>
                                <b>Profile</b>
                            </p>
                            <center>
                                <img src="resources/img/etc/dog2.png" class="img-responsive" height="300" width="300">
                            </center>
                            <h3>Steven Kim</h3>
                            <h4>Volunteer</h4>
                            <p>mla189@sfu.ca</p>
                            <h5>Campus: Burnaby | Surrey</h5>
                        </div>

                        <div class="tab-content" id="three"  style="display: none;">
                            <p>
                                <b>Profile</b>
                            </p>
                            <center>
                                <img src="resources/img/etc/dog.jpg" class="img-responsive" height="300" width="300">
                            </center>
                            <h3>Steven Park</h3>
                            <h4>Volunteer</h4>
                            <p>mla189@sfu.ca</p>
                            <h5>Campus: Burnaby | Surrey</h5>
                        </div>
                    </div>
                </row>
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
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    function switchColors(element){
        links=document.getElementsByTagName("tr") ;
        for (var i = 0 ; i < links.length ; i ++)
            links.item(i).style.color = 'black' ;
        element.style.color='orange' ;
    };

    $(function(){
        $("table td").click(function(){
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

