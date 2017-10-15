<%@ page import="com.zeta.Models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-09-29
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
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
    #side-container{
    }

    #side-contact{
        position: absolute;
        bottom: 0;
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
            <li>
                <a type="button" data-toggle="modal" data-target="#myModal">IF NO AUTH</a>
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
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px">
                    </div></center>
                <br><br>

                <div class="row">
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
                                <c:forEach items="${users}" var="user">
                                    <tr onclick="switchColors(this)" data-tab="${user.getUsername()}">
                                        <td> <c:out value="${user.getName()}" /> </td>
                                        <td> <c:out value="${user.getStudentNumber()}" /> </td>
                                    </tr>
                                </c:forEach>
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

                        <c:forEach items="${users}" var="user">
                            <div class="tab-content" id="${user.getUsername()}" style="display: none" >
                                <p>
                                    <b>Profile</b>
                                </p>
                                <center>
                                    <img src="resources/img/etc/dog.jpg" class="img-responsive" height="300" width="300">
                                </center>
                                <h3> <c:out value="${user.getName()}" /> </h3>
                                <h4> <c:out value="${user.getRole()}" /> </h4>
                                <p> <c:out value="${user.getEmail()}" /> </p>
                                <h5> <c:out value="${user.getPreferredCampus()}" /> </h5>
                            </div>
                        </c:forEach>


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
                    </div>
                </divrow>
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

