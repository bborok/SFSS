<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SFU</title>
    <script>
        $(document).ready(function(){
            alert('<c:out value= "${teststring}">');
        });
    </script>
</head>
<body>
    <%--<c:out value= "${teststring}"  />--%>
</body>
</html>
