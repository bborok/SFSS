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

    <jsp:include page="partfiles/sidebar.jsp"/>
    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("user", user);
    %>

    <script>
        var loggedInUser = {
            username : "${user.username}",
            name : "${user.name}",
            email : "${user.email}",
            phoneNumber : "${user.phoneNumber}",
            altPhoneNumber : "${user.altPhoneNumber}",
            preferredCampus : "${user.preferredCampus}",
            studentNumber : "${user.studentNumber}",
            role : "${user.role}",
            callSign : "${user.callSign}"
        };
    </script>

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
                            <h3>Qualifications:</h3>
                        </div>

                        <script>
                            var phoneNum = '${user.phoneNumber}';
                            var cleanPhone = '(' + phoneNum.substring(0,3) + ') ' + phoneNum.substring(3,6)
                                + '-' + phoneNum.substring(6);

                            $('#phoneNum').html('<h3>Phone Number: ' + cleanPhone + '</h3>');

                            var altNum = '${user.altPhoneNumber}';
                            if (altNum !== '') {
                                var cleanAltPhone = '(' + altNum.substring(0, 3) + ') ' + altNum.substring(3, 6)
                                    + '-' + altNum.substring(6);

                                $('#altPhone').html('<h3>Alternate Phone Number: ' + cleanAltPhone + '</h3>');
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

