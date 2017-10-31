<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-10-21
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <!-- jQuery Resources -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://momentjs.com/downloads/moment.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script src= 'resources/js/timecard.js'></script>
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
                <a href="${pageContext.request.contextPath}/statistics_info_lf">Statistics</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log">Log</a>
            </li>
            <li class="active">
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
                <div class="description" style="margin-bottom: 2em;">
                    <center>
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px">

                    </center>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">Time Card</div>
                    <div class="panel-body">

                        <form:form modelAttribute="timeCard" method="post">
                            <div class="row form-group">
                                <div class="col-md-4">
                                    <label><form:radiobutton path="campus" name="campus" id="vancouver" value="vancouver"/> Vancouver </label>
                                </div>
                                <div class="col-md-4">
                                    <label><form:radiobutton path="campus" name="campus" id="burnaby" value="burnaby"/> Burnaby </label>
                                </div>
                                <div class="col-md-4">
                                    <label><form:radiobutton path="campus" name="campus" id="surrey" value="surrey"/> Surrey </label>
                                </div>
                                <div class="col-md-6 ">
                                    <label > Date : </label>
                                    <form:input path="date" class="form-control" type="input" id="date" value=""/>
                                </div>
                                <div class="col-md-6 ">
                                    <label > Location : </label>
                                    <form:input path="location" class="form-control" type="input" id="location" value=""/>
                                </div>
                                <div class="col-md-6 " style="margin-bottom: 2em;">
                                    <label > Start Time : </label>
                                    <form:input path="startTime" class="form-control" type="input" id="startTime" value=""/>
                                </div>
                                <div class="col-md-6 ">
                                    <label > End Time : </label>
                                    <form:input path="endTime" class="form-control" type="input" id="endTime" value=""/>
                                </div>
                                <div class="col-md-12">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">Action</div>
                                        <div class="panel-body">
                                            <div class="form-group">
                                                <table class="table table-bordered" id="action">
                                                    <tbody>
                                                    <tr>
                                                        <th>Item</th>
                                                        <th>#</th>
                                                        <th>Total</th>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-top: 1.5%;">Smoking Prevention</td>
                                                        <td>
                                                            <button type="button" name='increaseTotal' class="btn" style="background-color: blue;"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <button type="button" name='decreaseTotal' class="btn"><span class="glyphicon glyphicon-minus"></span></button>
                                                        </td>
                                                        <td><form:input path="SPTotal" type="input" class="form-control" id="SPTotal" value="0" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-top: 1.5%;">Theft Prevention</td>
                                                        <td>
                                                            <button type="button" name='increaseTotal' class="btn" style="background-color: blue;"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <button type="button" name='decreaseTotal' class="btn"><span class="glyphicon glyphicon-minus"></span></button>
                                                        </td>
                                                        <td><form:input path="TPTotal" type="input" class="form-control" id="TPTotal" value="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-top: 1.5%;">Public Contact</td>
                                                        <td>
                                                            <button type="button" name='increaseTotal' class="btn" style="background-color: blue;"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <button type="button" name='decreaseTotal' class="btn"><span class="glyphicon glyphicon-minus"></span></button>
                                                        </td>
                                                        <td><form:input path="PCTotal" type="input" class="form-control" id="PCTotal" value="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-top: 1.5%;">Safe Walks</td>
                                                        <td>
                                                            <button type="button" name='increaseTotal' class="btn" style="background-color: blue;"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <button type="button" name='decreaseTotal' class="btn"><span class="glyphicon glyphicon-minus"></span></button>
                                                        </td>
                                                        <td><form:input path="SWTotal" type="input" class="form-control" id="SWTotal" value="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-top: 1.5%;">Hazards/Service Request</td>
                                                        <td>
                                                            <button type="button" name='increaseTotal' class="btn" style="background-color: blue;"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <button type="button" name='decreaseTotal' class="btn"><span class="glyphicon glyphicon-minus"></span></button>
                                                        </td>
                                                        <td><form:input path="HSRTotal" type="input" class="form-control" id="HSRTotal" value="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-top: 1.5%;">Assist Security</td>
                                                        <td>
                                                            <button type="button" name='increaseTotal' class="btn" style="background-color: blue;"><span class="glyphicon glyphicon-plus"></span></button>
                                                            <button type="button" name='decreaseTotal' class="btn"><span class="glyphicon glyphicon-minus"></span></button>
                                                        </td>
                                                        <td><form:input path="ASTotal" type="input" class="form-control" id="ASTotal" value="0"/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 ">
                                    <label > Notes : </label>
                                    <form:input path="notes" class="form-control" type="input" id="notes" value=""/>
                                </div>

                                <div class="pull-right">
                                    <form:button type="submit" name="timecard" id="timecard" class="btn btn-success" style="margin-top:1em;margin-right:1em;">Submit Time Card</form:button>
                                </div>

                            </div> <%--end of row--%>
                        </form:form> <%--end of form--%>
                    </div> <%--end of panel body--%>
                </div><%--end of panel primary--%>
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
