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
            <div class="x_panel" style="width:1000px; height: 500px;">
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
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <div>
                            <img id="imgA"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel" style="width:1000px; height: 200px;">
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-default" onclick="start()" value="开启旋转">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-default" onclick="resizableRotate.getData()" value="获取数据">
                                </div>
                            </div>

                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-default" onclick="recovery()" value="缩放固定位置">
                                </div>
                            </div>
                        </div>
                    </form>
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


    var resizableRotate = new Object();
    //用做存储数据
    resizableRotate.data = {};

    /**
     * 判断字符串以及null等
     */
    resizableRotate.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    resizableRotate.config = {
        background: {
            url: "${pageContext.request.contextPath}/views/data/E2B8A525.png"
        },
        img: {
            id: "imgA",
            class: "ui-widget-content",
            src: "https://www.baidu.com/img/bd_logo1.png?where=super"
        }
    };

    resizableRotate.style = function () {
        $("#" + resizableRotate.config.img.id).parent().attr("class", resizableRotate.config.img.class);
        $("#" + resizableRotate.config.img.id).css({width: 220, height: 40});
        $("#" + resizableRotate.config.img.id).attr("src", resizableRotate.config.img.src);
    };

    /**
     * 背景设置
     */
    resizableRotate.setBackGround = function () {
        $("#" + resizableRotate.config.img.id).parent().parent().parent().parent().css('background', 'url(' + resizableRotate.config.background.url + ')');
    };

    /**
     * 缩放
     */
    resizableRotate.resizable = function () {
        $("#" + resizableRotate.config.img.id).parent().resizable();
    };

    /**
     * 拖动
     */
    resizableRotate.draggable = function () {
        $("#" + resizableRotate.config.img.id).parent().draggable();
    };

    /**
     * 收集数据
     */
    resizableRotate.formParams = function (deg) {
        var ele = $("#" + resizableRotate.config.img.id).parent();
        var item = {
            deg: deg,
            style: ele.attr("style")
        };
        resizableRotate.data = item;
    };

    /*获取收集的数据*/
    resizableRotate.getData = function () {
        var item  = resizableRotate.data;
        console.log(item);
        console.log($("#" + resizableRotate.config.img.id).parent()[0]);
        return item;
    };

    /**
     * 旋转开启
     */
    resizableRotate.rotateRun = function () {
        var oldY = null;
        var isDown = false;
        $("#" + resizableRotate.config.img.id).parent().mousedown(function (e) {
            isDown = true;
            oldY = e.clientY;
        });
        $("html").mousemove(function (e) {
            if (isDown) {
                $("#" + resizableRotate.config.img.id).parent().rotate({angle: (e.clientY - oldY)});
                resizableRotate.formParams(e.clientY - oldY);
            }
        });
        $("html").mouseup(function (e) {
            isDown = false;
            oldY = null;
        });
    };

    function start() {
        $(document).ready(function () {
            //设置被拖动的图片样式
            resizableRotate.style();
            //设置背景
            resizableRotate.setBackGround();
            //缩放
            resizableRotate.resizable();
            //开启拖动
            resizableRotate.draggable();
            //开启旋转
            resizableRotate.rotateRun();
        });
    }

    function recovery() {
        var item = {
            style:"transform: rotate(0deg); transform-origin: 50% 50% 0px; position: absolute; top: 106.886px; left: 370.205px; height: 145px;",
            deg:0
        };
        //设置被拖动的图片样式
        resizableRotate.style();
        $("#" + resizableRotate.config.img.id).parent().rotate({angle: item.deg});
        $("#" + resizableRotate.config.img.id).parent().attr("style",item.style);
    }
</script>


</html>
