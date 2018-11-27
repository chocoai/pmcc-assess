<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
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
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <img id="imgA" src="https://www.baidu.com/img/bd_logo1.png?where=super"/>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/rotate/jquery.rotate.min.js"></script>
<script type="text/javascript">
    var obj = {};

    /**
     * 背景设置
     */
    obj.setBackGround = function () {
        $(".container").css('background', 'url(${pageContext.request.contextPath}/views/data/DF8A8A34.png)');
    };

    /**
     * 旋转开启
     */
    obj.rotateRun = function () {
        var oldY = null;
        var isdown = false;
        $("#imgA").mousedown(function (e) {
            isdown = true;
            oldY = e.clientY;
        });
        $("html").mousemove(function (e) {
            console.log(isdown);
            if (isdown) {
                console.log('deg' + (e.clientY - oldY));
                $("#imgA").rotate({angle: (e.clientY - oldY)});
            }
        });
        $("html").mouseup(function (e) {
            isdown = false;
            oldY = null;
        });
    };

    $(document).ready(function () {
        obj.setBackGround();
        //缩放
        // $("#imgA").resizable();
        //开启拖动
        $("#imgA").draggable();
        //开启旋转
        obj.rotateRun();
    });
</script>


</html>
