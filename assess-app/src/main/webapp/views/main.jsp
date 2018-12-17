<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/06/13
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
    <title>首页</title>
    <%@include file="share/main_css.jsp" %>
</head>
<!-- end: HEAD -->
<!-- start: BODY -->
<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="share/main_navigation.jsp" %>
        <%@include file="share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-sm-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>今日工作</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <h1><i class="fa fa-calendar bricky"></i> <label id="lab_date"></label>
                                <small id="lab_week"></small>
                            </h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: PAGE -->
</div>
<!-- end: MAIN CONTAINER -->
<%@include file="share/main_footer.jsp" %>

</body>

<script type="application/javascript">
    $(function () {
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
        var week = today[date.getDay()];
        $("#lab_date").html((m + 1) + "." + d);
        $("#lab_week").html(week);
    });
</script>
</html>
