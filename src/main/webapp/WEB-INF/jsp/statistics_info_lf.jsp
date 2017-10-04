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
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/form-elements.css">
    <link rel="stylesheet" href="/resources/css/style.css">
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
            <div class="col-xs-6"><img src="/resources/img/logo_made/logo_2.png" class="img-responsive"></div>
            <div class="col-xs-6"><h3 class="text-center">STATISTICS INFO&LF</h3></div>
            <div class="col-xs-6 col-md-offset-6">
                <div class="col-xs-4"><button type="button" class="btn btn-info btn-block">EXPORT</button></div>
                <div class="col-xs-4"><button type="button" class="btn btn-warning btn-block">SAVE</button></div>
                <div class="col-xs-4"><button type="button" class="btn btn-danger btn-block">EDIT</button></div>
            </div>
            <div class="col-xs-8">
                <table id="table1" class="table table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>2017</th>
                        <th>DEC'16</th>
                        <th>JAN</th>
                        <th>FEB</th>
                        <th>MAR</th>
                        <th>APR</th>
                        <th>MAY</th>
                        <th>JUN</th>
                        <th>JUL</th>
                        <th>AUG</th>
                        <th>SEP</th>
                        <th>OCT</th>
                        <th>NOV</th>
                        <th>DEV</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Directions</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>Lost&Found</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>Payments</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>PhoneService</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>KeyService</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>Others</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-xs-3 col-xs-offset-1">
                <table id="table2" class="table table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Total</th>
                        <th>%</th>
                        <th>Num</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>333</th>
                        <th>3%</th>
                        <th>11</th>
                    </tr>
                    <tr>
                        <th>333</th>
                        <th>3%</th>
                        <th>11</th>
                    </tr>
                    <tr>
                        <th>333</th>
                        <th>3%</th>
                        <th>11</th>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="col-xs-12">

            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="/resources/js/jquery-1.12.4.js"></script>
<script src="/resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function() {});
</script>
</body>
</html>
