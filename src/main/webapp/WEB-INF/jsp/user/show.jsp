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
    <title>

        <c:choose>
            <c:when test="${empty user}">
                User Not Found
            </c:when>
            <c:otherwise>
                <c:out value="${user.name}"/>'s Details
            </c:otherwise>
        </c:choose>

    </title>
</head>
<body>
<c:choose>
    <c:when test="${empty user}">
        User Not Found
    </c:when>
    <c:otherwise>
        User Student Number: <c:out value="${user.studentNumber}"/>
        User Name: <c:out value="${user.name}"/>
        User Email: <c:out value="${user.email}"/>
        User PhoneNumber: <c:out value="${user.phoneNumber}"/>
        User Role: <c:out value="${user.role}"/>
        User Preferred Campus: <c:out value="${user.preferredCampus}"/>
        User Account Code: <c:out value="${user.accountCode}"/>
    </c:otherwise>
</c:choose>
</body>
</html>
