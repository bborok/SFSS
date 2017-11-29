<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-11-13
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src= 'resources/js/sorttable.js'></script>
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

<div id="wrapper" style="padding-top: 50px" >

    <jsp:include page="partfiles/sidebar.jsp"/>



    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid xyz">
            <div class="col-sm-12 text">
                <div class="description">
                    <center>
                        <img src="resources/img/logo_made/logo_2.png" class="img-responsive"
                             style="height:100px;width:500px">
                    </center>
                    <hr>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">List of Timecards</div>
                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${shifts.size() == 0}">
                                No TimeCards on Record!
                            </c:when>
                            <c:otherwise>
                                <table class="table table-striped sortable" style="text-align:left; ">
                                    <thead>
                                    <tr>
                                        <th width="15%">Name</th>
                                        <th width="4%">User</th>
                                        <th width="14%">Date</th>
                                        <th width="22%">Time Card</th>
                                        <th width="19%">Start Time</th>
                                        <th width="16%">End Time</th>
                                        <th width="4%">Campus</th>
                                        <th width="6%">Location</th>
                                    </tr>
                                    </thead>

                                    <tbody style="color:black">
                                    <c:forEach items="${shifts}" var="shift">
                                        <tr>
                                            <td >
                                                <c:choose>
                                                    <c:when test="${!isAdmin}">
                                                        <c:choose>
                                                            <c:when test="${shift.isTimeCardSubmitted }">
                                                                <c:out value="${shift.title}"/>
                                                            </c:when>
                                                            <c:when test="${!shift.isTimeCardSubmitted }">
                                                                <a href="${pageContext.request.contextPath}/timecard?username=${shift.username}&shift_id=${shift.id}"><c:out value="${shift.title}"/></a>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="${pageContext.request.contextPath}/timecard?username=${shift.username}&shift_id=${shift.id}"><c:out value="${shift.title}"/></a>
                                                    </c:otherwise>
                                                </c:choose>

                                            <td >
                                                    <c:out value="${shift.username}"/>
                                            <td >
                                                    <c:out value="${shift.date}"/>
                                            <td >
                                                <c:choose>
                                                <c:when test="${shift.isTimeCardSubmitted }"> <c:out value="Submitted"/> </c:when>
                                                <c:when test="${!shift.isTimeCardSubmitted  }"> <c:out value="Not Submitted"/> </c:when>
                                                </c:choose>
                                                    <%--<c:if test="${refreshSent}">--%>
                                                    <%--your code...--%>
                                                    <%--</c:if>--%>
                                            <td >
                                                    <c:out value="${shift.start}"/>
                                            <td >
                                                    <c:out value="${shift.end}"/>
                                            <td >
                                                    <c:out value="${shift.campus}"/>
                                            <td >
                                                    <c:out value="${shift.location}"/>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>


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
