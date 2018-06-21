<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>
                        <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name}
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <%@include file="/views/project/projectCenterHeader.jsp" %>
                    <div class="row">
                        <div class="animated flipInY col-lg-3">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-check-square"></i>
                                </div>
                                <div class="count">${todayTaskCount}</div>

                                <h3>今日完成</h3>
                                <p>今日完成</p>
                            </div>
                        </div>
                        <div class="animated flipInY col-lg-3">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-calendar-o"></i>
                                </div>
                                <div class="count">${weekTaskCount}</div>

                                <h3>本周完成</h3>
                                <p>本周完成</p>
                            </div>
                        </div>
                        <div class="animated flipInY col-lg-3">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-tachometer"></i>
                                </div>
                                <div class="count">${userJoinProjectCount}</div>

                                <h3>进行中的项目</h3>
                                <p>进行中的项目</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>


<%@include file="/views/share/main_footer.jsp" %>

</html>
