<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- jQuery Resources -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<script src= 'resources/js/timecard.js'></script>

<!-- Sidebar -->
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <p>SFU SFEP</p>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/" class="fa fa-home">Home</a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/schedule" class="fa fa-calendar">Schedule</a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/profile" class="fa fa-user">Profile</a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/statistics_info_lf" class="fa fa-area-chart">Statistics</a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/payroll" class="fa fa-credit-card">Payroll</a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/log" class="fa fa-book">Log</a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/timecard" class="fa fa-clock-o">Time Card</a>
        </li>
        <c:if test="${sessionScope.user.role != 'MEMBER'}">
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/users" class="fa fa-users">Users</a>
            </li>
        </c:if>
    </ul>
    <div id="side-container">
        <div id="side-contact" style="text-align:center; margin-bottom:10px;">

            <p style="text-align:center; font-size:20px">
                <a style=" color: yellow" class="fa fa-sign-out fa-x" href="${pageContext.request.contextPath}/logout">Sign Out</a>
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

<script type="text/javascript">
    $sbJQ = jQuery.noConflict(false);
    $sbJQ(document).ready(function() {
        var links = $sbJQ('li.sidebar-item').children();
        $sbJQ.each(links, function(key, value) {
            if (value.href === document.URL) {
                $sbJQ(this).parent().addClass('active');
            }
        })
    });
</script>

