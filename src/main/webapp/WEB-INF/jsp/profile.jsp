<%@ page import="com.zeta.Models.User" %>
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

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>SFU</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.4.5/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

    <!-- Custom styles for this template -->
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <script>
        var api = '${pageContext.request.contextPath}/user';
    </script>

</head>

<style>

    tr {
        width: 100%;
        display: inline-table;
    }

    th {
        text-align: center;
    }

    .click a {
        color: chocolate;
    }

</style>

<body>
<nav class="navbar navbar-default no-margin navbar-fixed-top">
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

<div id="wrapper" style="padding-top: 56px">

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
                            <h1 class="row" style="padding-left: 20px">
                                <b><c:out value="${user.name}"/>'s Profile</b>
                                <br>
                            </h1>
                            <center>
                                <img src="/user/image/${user.username}" class="img-responsive img-circle" height="300"
                                     width="300">
                            </center>
                            <label class="control-label"><u>Upload Profile Picture:</u></label>
                            <div class="file-loading">
                                <input id="userImage" name="userImage" type="file" accept="image/*">
                            </div>
                            <div id="file-error"></div>

                        </div>

                        <div>
                            <h3>Username: ${user.username}</h3>
                            <h3>Student Number: ${user.studentNumber}</h3>
                            <h3>Role: ${user.role}</h3>
                            <h3>Email: ${user.email}</h3>
                            <p id="phoneNum"></p>
                            <p id="altPhone"></p>
                            <p id="volunteerHours"></p>
                            <div id="serviceRecognition"></div>
                            <h3>Qualifications: </h3>
                            <dl>
                                <dt><u>Driver's License:</u></dt>
                                <div class="row">
                                    <dd>
                                        Class ${user.driversLicenseLevel} / Expires: ${user.driversLicenseExpirationDate}
                                    </dd>
                                </div>
                            </dl>
                            <dl>
                            <c:if test="${not empty user.languages}">
                                <dt><u>Languages Spoken:</u></dt>
                                <div class="row" style="overflow-y:scroll; width: 200px; height:115px">
                                    <table class="table table-striped" style="text-align:center">
                                        <tbody style="color:black">
                                            <c:forEach items="${user.languages}" var="language">
                                                <tr>
                                                    <td class="col-sm-6"><c:out value="${language}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </dl>
                            </c:if>

                            <c:if test="${not empty user.certificates}">
                                <dl>
                                    <dt><u>Certificates:</u></dt>
                                    <div class="row" style="overflow-y: scroll; width:700px; height: 200px">
                                        <table class="table table-striped" style="text-align: left">
                                            <thead>
                                                <tr style="text-align: center">
                                                    <th width="35%">Name</th>
                                                    <th width="25%">ID</th>
                                                    <th width="10%">Level</th>
                                                    <th width="30%">Expiration Date</th>
                                                </tr>
                                            </thead>

                                            <tbody style="color: black">
                                                <c:forEach items="${user.certificates}" var="certificate">
                                                    <tr style="text-align: center">
                                                        <td width="35%"><c:out value="${certificate.name}"/>
                                                        <td width="25%"><c:out value="${certificate.number}"/>
                                                        <td width="10%"><c:out value="${certificate.level}"/>
                                                        <td width="30%"><c:out value="${certificate.expirationDate}"/>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </dl>
                            </c:if>
                        </div>

                        <script>
                            var phoneNum = '${user.phoneNumber}';
                            var cleanPhone = '(' + phoneNum.substring(0,3) + ') ' + phoneNum.substring(3,6)
                                + '-' + phoneNum.substring(6);

                            $('#phoneNum').html('<h3>Phone Number: ' + cleanPhone + '</h3>');

                            var altNum = '${user.altPhoneNumber}';
                            if (altNum !== '0') {
                                var cleanAltPhone = '(' + altNum.substring(0, 3) + ') ' + altNum.substring(3, 6)
                                    + '-' + altNum.substring(6);

                                $('#altPhone').html('<h3>Alternate Phone Number: ' + cleanAltPhone + '</h3>');
                            }

                            var hours = ${user.volunteerMinutes} / 60;

                            $('#volunteerHours').html('<h3>Volunteer Hours: ' + hours + '</h3>');

                            var ranks = [80, 160, 240, 320, 400, 480];
                            var imgSrc = 'resources/img/service_recognition';
                            var options = 'style="background:transparent"';

                            if (hours >= ranks[0]) {
                                var serviceRank = '/one_star.png';
                                if (hours >= ranks[1]) {
                                    serviceRank = '/two_star.png';
                                }
                                if (hours >= ranks[2]) {
                                    serviceRank = '/three_star.png';
                                }
                                if (hours >= ranks[3]) {
                                    serviceRank = '/bronze_star.png';
                                }
                                if (hours >= ranks[4]) {
                                    serviceRank = '/silver_star.png';
                                }
                                if (hours >= ranks[5]) {
                                    serviceRank = '/gold_star.png';
                                }

                                $('#serviceRecognition').html('<img src="' + imgSrc + serviceRank + '"' + options + '>')
                            }
                        </script>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.4.5/js/fileinput.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $(document).ready(function() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader(header, token);
        });

        $('#userImage').fileinput({
            maxFileSize: 1500,
            browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
            showUpload: false,
            showPreview: false,
            showCancel: false,
            showRemove: false,
            showCaption: false,
            allowedFileTypes: ["image"],
            maxImageWidth: 300,
            maxImageHeight: 300,
            resizeImage: true,
            uploadAsync: true,
            uploadUrl: "/user/addImage",
            minFileCount: 1,
            maxFileCount: 1
        }).on('filebatchselected', function () {
            $('#userImage').fileinput('upload');
        });
    });

    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>

