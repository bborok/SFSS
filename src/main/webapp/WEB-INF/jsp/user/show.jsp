<%--
  Created by IntelliJ IDEA.
  User: Patrick
  Date: 2017-10-01
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${user.name}"/>'s Details</title>
</head>
<body>
User Student Number: <span><c:out value="${user.studentNumber}"/></span>
User Name: <span><c:out value="${user.name}"/></span>
User Email: <span><c:out value="${user.email}"/></span>
User PhoneNumber: <span><c:out value="${user.phoneNumber}"/></span>
User Role: <span><c:out value="${user.role}"/></span>
User Preferred Campus: <span><c:out value="${user.campus}"/></span>
User Account Code: <span><c:out value="${user.accountCode}"/></span>

</body>
</html>
