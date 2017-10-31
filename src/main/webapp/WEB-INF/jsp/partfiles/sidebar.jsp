<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Sidebar -->
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <p>SFU SFEP</p>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/schedule">Schedule</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/profile">Profile</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/statistics_info_lf">Statistics</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/log">Log</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/timecard">Time Card</a>
        </li>
        <c:if test="${sessionScope.user.role != 'MEMBER'}">
            <li>
                <a href="${pageContext.request.contextPath}/users">Users</a>
            </li>
        </c:if>
    </ul>
    <div id="side-container">
        <div id="side-contact" style="text-align:center; margin-bottom:10px;">

            <p style="text-align:center; font-size:20px">
                <a style=" color: yellow" class="fa fa-sign-out fa-x" href="${pageContext.request.contextPath}/">Sign Out</a>
            </p>
            <br>
            <p style="font-weight: bold; text-decoration: underline;">Contact: </p>
            <p class="fa fa-phone "> (778)-782-5425</p>
            <p class="fa fa-envelope-o col-sm-12"> sfucsp@sfu.ca</p>
            <%--https://www.sfu.ca/srs/security/contact-us.html--%>
        </div>
    </div>
</div>
<!-- /#sidebar-wrapper -->