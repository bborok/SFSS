<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contact Manager Home</title>
</head>
<body>
<div align="center">
    <h1>Contact List</h1>
    <h3><a href="/newContact">New Contact</a></h3>
    <table border="1">
        <th>No</th>
        <th>SFU ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Preferred Campus</th>
        <th>Student Number</th>
        <th>Role</th>

        <c:forEach var="contact" items="${userList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${contact.username}</td>
                <td>${contact.name}</td>
                <td>${contact.email}</td>
                <td>${contact.phoneNumber}</td>
                <td>${contact.preferredCampus}</td>
                <td>${contact.studentNumber}</td>
                <td>${contact.role}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>