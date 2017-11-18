<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- jQuery Resources -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>


    <!-- Sidebar -->
    <div id="sidebar-wrapper" style="padding-top: 0px">
        <ul class="sidebar-nav nav-pills nav-stacked" id="menu">

            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/"><span class="fa-stack fa-lg pull-left"><i class="fa fa-home fa-stack-1x "></i></span> Home</a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/schedule"><span class="fa-stack fa-lg pull-left"><i class="fa fa-calendar fa-stack-1x "></i></span> Schedule</a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/profile"><span class="fa-stack fa-lg pull-left"><i class="fa fa-user fa-stack-1x "></i></span> Profile</a>
            </li>
            <li class="sidebar-item">
                <a href="#"> <span class="fa-stack fa-lg pull-left"><i class="fa fa-area-chart fa-stack-1x "></i></span> Statistics</a>
                <ul class="nav-pills nav-stacked" style="list-style-type:none;">
                    <li><a href="${pageContext.request.contextPath}/statistics_info_lf"><span class="fa-stack fa-lg pull-left"><i class="fa fa-bar-chart fa-stack-1x "></i></span>Lost & Found</a></li>
                    <li><a href="${pageContext.request.contextPath}/statistics_public_contact"><span class="fa-stack fa-lg pull-left"><i class="fa fa-bar-chart fa-stack-1x "></i></span>Public Contact</a></li>

                </ul>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/payroll"><span class="fa-stack fa-lg pull-left"><i class="fa fa-credit-card fa-stack-1x "></i></span>Payroll</a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/timecard"><span class="fa-stack fa-lg pull-left"><i class="fa fa-clock-o fa-stack-1x "></i></span>Time Cards</a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/users"><span class="fa-stack fa-lg pull-left"><i class="fa fa-users fa-stack-1x "></i></span>Users</a>
            </li>
            <li class="sidebar-item" style="padding-top: 200px">
                <a href="${pageContext.request.contextPath}/logout"><span class="fa-stack fa-lg pull-left"><i class="fa fa-sign-out fa-stack-1x "></i></span><b style="color: yellow;font-size: 15px">Sign Out</b></a>
            </li>

        </ul>

    </div><!-- /#sidebar-wrapper -->

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
<script src="resources/js/sidebar_menu.js"></script>

