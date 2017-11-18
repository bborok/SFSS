<%--
  Page that displays the users. Users displayed will be based on the
  List<User> found in the users function of the IndexController
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SFU</title>
    <!-- Bootstrap core CSS -->

    <!-- Custom styles for this template -->

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">

</head>

<style>

    tr {
        width: 100%;
        display: inline-table;
    }

    thead {
        height: 1%;
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
    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid xyz">
            <div class="col-sm-12 text">
                <center>
                    <div class="description">
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive"
                             style="height:100px;width:500px">
                        <hr>
                    </div>
                </center>
                <br><br>

                <div class="row">
                    <div class="col-sm-6" style="height:600px; text-align:center; border-style:solid">
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
                                    <td class="col-sm-6 col-xs-6">
                                            <c:out value="${user.getName()}"/>
                                    <td class="col-sm-6">
                                            <c:out value="${user.getStudentNumber()}"/>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="sample col-sm-6" style="text-align:center; border-style:solid; height:600px">

                        <div class="tab-content">
                            <p>
                                <b>Profile</b>
                            </p>
                            <center>
                                <img src="resources/img/etc/annonymous.jpg" class="img-responsive" height="300"
                                     width="300">
                            </center>
                            <h3>User Profile</h3>
                            <h5>Select User from the list</h5>
                        </div>

                        <c:forEach items="${users}" var="user">
                            <div class="tab-content" id="${user.getUsername()}" style="display: none">
                                <p>
                                    <b>Profile</b>
                                </p>
                                <center>
                                    <img src="resources/img/etc/dog.jpg" class="img-responsive" height="300"
                                         width="300">
                                </center>
                                <h3><c:out value="${user.getName()}"/></h3>
                                <h4><c:out value="${user.getRole()}"/></h4>
                                <p><c:out value="${user.getEmail()}"/></p>
                                <h5><c:out value="${user.getPreferredCampus()}"/></h5>
                            </div>
                        </c:forEach>
                    </div>
                </div>
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



<!-- Menu Toggle Script -->
<script>

    function switchColors(element) {
        links = document.getElementsByTagName("tr");
        for (var i = 0; i < links.length; i++)
            links.item(i).style.color = 'black';
        element.style.color = 'orange';
    };

    $(function () {
        $("table td").click(function (event) {
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

