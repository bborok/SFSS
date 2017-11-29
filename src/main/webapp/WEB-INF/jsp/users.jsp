<%@ page import="com.zeta.Models.User" %><%--
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

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>SFU</title>
    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">

    <!-- Custom styles for this template -->

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script src='resources/js/users.js'></script>

    <script>
        var api = '${pageContext.request.contextPath}/user';
    </script>

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

    #userList tbody {
        overflow-y: scroll;
        height: 500px;
        position: absolute;
    }

    .click a {
        color: chocolate;
    }

    .bootstrap-datetimepicker-widget {
        height: 250px;
        width: 250px;
    }

    #userimage:hover {opacity: 0.7;}

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
<script>
    var users = {
        <c:forEach items="${users}" var="user">
        "${user.username}" : {
            username : "${user.username}",
            name : "${user.name}",
            email : "${user.email}",
            phoneNumber : "${user.phoneNumber}",
            altPhoneNumber : "${user.altPhoneNumber}",
            preferredCampus : "${user.preferredCampus.toString()}",
            studentNumber : "${user.studentNumber}",
            role : "${user.role.toString()}",
            callSign : "${user.callSign}",
            licenseClass : "${user.driversLicenseLevel}",
            licenseExpire : "${user.driversLicenseExpirationDate}",
        },
        </c:forEach>
    }

</script>

    <%
        User user = (User) session.getAttribute("user");
        pageContext.setAttribute("loggedInUser", user.getUsername());
    %>

<div id="wrapper" class="toggled">

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

                <div>
                    <button type="button" id="addUser" class="btn" data-toggle="modal" data-target="#userModal" style="height: 45px">Add User</button>
                </div>
                <br>

                <div id="userModal" class="modal fade">
                    <div class="modal-dialog modal-lg">
                        <%--Modal Content--%>

                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X
                                </button>
                                <h4 id="myModalLabel1"><b>Add A User</b></h4>
                            </div>

                            <%--Modal Body--%>
                            <div class="modal-body">
                                <form id="userForm" data-toggle="validator">
                                    <div style="padding-left: 15px;padding-right: 15px">
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label class="control-label"><u>Username:</u></label>
                                                <div class="input-group">
                                                    <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="username" id="username" placeholder="Enter Username">
                                                    <span class="input-group-addon">@sfu.ca</span>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <label class="control-label"><u>Student Number:</u></label>
                                                <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="studentNumber" id="studentNumber" placeholder="Enter Student Number">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label class="control-label"><u>Full Name:</u></label>
                                                <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="name" id="userFullName" placeholder="Enter Full Name">
                                            </div>
                                            <div class="col-md-6">
                                                <label class="control-label"><u>Email:</u></label>
                                                <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="email" id="userEmail" placeholder="Enter Alternate Email">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label class="control-label"><u>Phone Number:</u></label>
                                                <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="phoneNumber" id="userPhoneNumber" placeholder="555-555-1234">
                                            </div>
                                            <div class="col-md-6">
                                                <label class="control-label"><u>Alternate Phone Number:</u></label>
                                                <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="altPhoneNumber" id="userAltPhoneNumber" placeholder="Optional">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Role:</u></label>
                                            <div class="controls">
                                                <select class="form-control" name="role" id="userRole" required>
                                                    <option value="" disabled="disabled" selected="selected">Select A Role
                                                    <c:forEach items="${roles}" var="role">
                                                        <option value="${role.name()}">
                                                            ${role.name()}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Preferred Campus:</u></label>
                                            <br>
                                            <label class="radio-inline">
                                                <input type="radio" name="campus" id="BURNABY" value="BURNABY" required>Burnaby
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="campus" id="SURREY" value="SURREY" required>Surrey
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="campus" id="VANCOUVER" value="VANCOUVER" required>Vancouver
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><u>Call Sign:</u></label>
                                            <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="callSign" id="userCallsign" placeholder="Enter Call Sign">
                                        </div>
                                        <label class="control-label"><u>Driver's License</u></label>
                                        <div class="form-group row">
                                            <div class="col-xs-2">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Class</span>
                                                    <input type="text" style="border-width:1px;border-color: #a9b7d1" class="form-control" name="licenseClass" id="licenseClass">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Expires</span>
                                                    <div class='input-group date' data-provider="datepicker" data-date-format="YYYY/MM/DD" id='licenseExpire'>
                                                        <input type='text' id="expireDate" class="form-control input-sm" style="border-width:1px;border-color: #a9b7d1" placeholder="YYYY/MM/DD"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="control">
                                                    <select class="form-control" name="languages[]" id="languages" multiple required>
                                                        <c:forEach items="${languages}" var="language">
                                                            <option value="${language}">
                                                                    ${language}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>

                                        <%--AJAX Request to POST to ShiftController--%>
                                        <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-6" style="height:600px; text-align:center; border-style:solid">
                        <p>
                            <b>Users</b>
                        </p>

                        <table id="userList" class="table table-striped" style="text-align:left; ">
                            <thead>
                                <tr>
                                    <th width="40%">Name</th>
                                    <th width="30%">#</th>
                                </tr>
                            </thead>

                            <tbody style="color:black">
                            <c:forEach items="${users}" var="user">
                                <tr onclick="switchColors(this)" data-tab="${user.username}">
                                    <td class="col-sm-6 col-xs-6">
                                            <c:out value="${user.name}"/>
                                    <td class="col-sm-6">
                                            <c:out value="${user.studentNumber}"/>
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
                                <img src="resources/img/etc/annonymous.jpg" id="userimage" data-toggle="modal" data-target="#details" class="img-responsive" height="300"
                                     width="300">
                            </center>

                            <div id="details" class="modal fade">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span
                                                    aria-hidden="true">&times;</span>
                                                <span class="sr-only">close</span></button>
                                            <h4><b>User Details <span id="modalTitle" class="modal-title"></span></b></h4>
                                        </div>
                                        <br>
                                        <div align="left" style="padding-left: 15px">
                                            <b><u>Username: </u></b><c:out value="${user.username}"/><br>
                                            <b><u>Student Number: </u></b><c:out value="${user.studentNumber}"/><br>
                                            <b><u>Full Name: </u></b><c:out value="${user.name}"/><br>
                                            <b><u>Email: </u></b><c:out value="${user.email}"/><br>
                                            <b><u>Phone Number: </u></b><c:out value="${user.phoneNumber}"/><br>
                                            <hr>
                                            <b><u>Preferred Campus: </u></b><c:out value="${user.preferredCampus}"/><br>
                                            <b><u>Role:  </u></b><c:out value="${user.role}"/><br>
                                            <b><u>Call Sign: </u></b><c:out value="${user.callSign}"/><br>

                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <h3>User Profile</h3>
                            <h5>Select User from the list</h5>
                        </div>

                        <c:forEach items="${users}" var="user">
                            <div class="tab-content" id="${user.username}" style="display: none">
                                <p>
                                    <b>Profile</b>
                                </p>
                                <center>
                                    <img src="/user/image/${user.username}" class="img-responsive img-circle" height="300"
                                         width="300">
                                </center>
                                <h3><c:out value="${user.name}"/></h3>
                                <h4><c:out value="${user.role}"/></h4>
                                <p><c:out value="${user.email}"/></p>
                                <h5><c:out value="${user.preferredCampus}"/></h5>
                            </div>
                        </c:forEach>

                        <div class="row">
                            <button type="button" class="btn btn-primary" id="editButton">Edit User</button>
                            <button type="button" class="btn btn-primary" id="removeButton">Remove User</button>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12 row">
                    <div class="radio" id="filter">
                        <label class="col-sm-2">
                            <input type="checkbox" value="NAME" id="filter_name">NAME
                        </label>
                        <label class="col-sm-2">
                            <input type="checkbox" value="NAME" id="filter_stu">STU#
                        </label>
                        <label class="col-sm-2">
                            <input type="checkbox" value="BURNABY" id="filter_burnaby" checked>BURNABY
                        </label>
                        <label class="col-sm-2">
                            <input type="checkbox" value="SURREY" id="filter_surrey" checked>SURREY
                        </label>
                        <label class="col-sm-2">
                            <input type="checkbox" value="VANCOUVER" id="filter_vancouver" checked>VANCOUVER
                        </label>
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
<script src="https://momentjs.com/downloads/moment.min.js"></script>


<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>

<!-- Menu Toggle Script -->
<script>

    function switchColors(element) {
        links = document.getElementsByTagName("tr");
        for (var i = 0; i < links.length; i++)
            links.item(i).style.color = 'black';
        element.style.color = 'orange';
    };

    function sortByName(a, b) {
        if (a.name.toLowerCase() > b.name.toLowerCase()) {
            return 1;
        } else if(a.name.toLowerCase() < b.name.toLowerCase()) {
            return -1;
        } else {
            return 0;
        }
    }

    function sortByStu(a, b) {
        if (a.studentNumber.toLowerCase() > b.studentNumber.toLowerCase()) {
            return 1;
        } else if(a.studentNumber.toLowerCase() < b.studentNumber.toLowerCase()) {
            return -1;
        } else {
            return 0;
        }
    }

    $(function () {
        $("table td").click(function (event) {
            event.preventDefault();
            $('table td').removeClass('current');
            $(this).addClass("current");
            var tab = $(this).parent().attr("data-tab");
            $('.tab-content').hide();
            $('#' + tab).fadeIn();

            if ('${loggedInUser}' === tab) {
                $('#removeButton').hide();
            } else {
                $('#removeButton').show();
            }
        });

        $("#filter input").click(function () {
           var checked_name = $("#filter_name").is(":checked");
           var checked_stu = $("#filter_stu").is(":checked");
           var checked_burnaby = $("#filter_burnaby").is(":checked");
           var checked_surrey = $("#filter_surrey").is(":checked");
           var checked_vancouver = $("#filter_vancouver").is(":checked");
           var filter_array = [];
           for(var name in users) {
               if(users[name].preferredCampus.toLowerCase() == "burnaby" && checked_burnaby) {
                   filter_array.push(users[name]);
               }
               if(users[name].preferredCampus.toLowerCase() == "surrey" && checked_surrey) {
                   filter_array.push(users[name]);
               }
               if(users[name].preferredCampus.toLowerCase() == "vancouver" && checked_vancouver) {
                   filter_array.push(users[name]);
               }
           }
           if (checked_name) {
               filter_array.sort(sortByName);
           } else if (checked_stu){
               filter_array.sort(sortByStu);
           }
           var htmlAdd = "";
           for(var index in filter_array) {
               htmlAdd += "<tr onclick=\"switchColors(this)\" data-tab=\"" + filter_array[index].username + "\">"
                   + "<td class=\"col-sm-6 col-xs-6\">" + filter_array[index].name + "</td>"
                   + "<td class=\"col-sm-6\">" + filter_array[index].studentNumber + "</td>"
                   + "</tr>";
           }
           $("table tbody").empty();
           $("table tbody").append(htmlAdd);
           //clean event
            $("table td").unbind();
            //rebind event
            $("table td").click(function (event) {
                event.preventDefault();
                $('table td').removeClass('current');
                $(this).addClass("current");
                var tab = $(this).parent().attr("data-tab");
                $('.tab-content').hide();
                $('#' + tab).fadeIn();

                if ('${loggedInUser}' === tab) {
                    $('#removeButton').hide();
                } else {
                    $('#removeButton').show();
                }
            });
        });
    });

    $('#addUser').on('click', function () {
        $('#userModal').find('[id="myModalLabel1"]').html('<b>Add a User</b>').end();
    })

    $('#userModal').on('shown.bs.modal', function () {
        $('#languages').multiselect('rebuild');
        $('#languages').multiselect('select', 'English');
        $('#languages').multiselect('updateButtonText', false);
        $('#languages').multiselect('refresh');
    })

    $('#userModal').on('hidden.bs.modal', function () {
        $(this).find("input").val('').end()
            .find('[id="username"]').prop('disabled', false).end()
            .data('bootstrapValidator').resetForm();
        $('input[name="campus"]:checked').prop('checked', false);

        $('#languages').multiselect('deselectAll', false);
        $('#languages').multiselect('refresh');
    });
</script>
</body>
</html>

