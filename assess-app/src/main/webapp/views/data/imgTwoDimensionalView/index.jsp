<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-1">
                            <ul class="nav nav-pills nav-stacked">
                                <li class="active"><a href="#" onclick="resizableRotate.startAll()">默认旋转方式</a></li>
                                <li><a href="#" onclick="resizableRotate.startAll()">开启鼠标旋转</a></li>
                                <li><a href="#">canvas 旋转</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-11" style="height:616px;">
                            <img id="imgA"/>
                        </div>
                    </div>
                </div>
            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="commit()">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/rotate/jquery.rotate.min.js"></script>
<script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
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
            url: "${pageContext.request.contextPath}/views/data/imgTwoDimensionalView/E2B8A525.png"
        },
        img: {
            id: "imgA",
            class: "ui-widget-content",
            src: "${pageContext.request.contextPath}/views/data/imgTwoDimensionalView/B69B3AFB.png"
        }
    };

    resizableRotate.style = function () {
        $("#" + resizableRotate.config.img.id).css({width: 530 / 5, height: 708 / 5});
        $("#" + resizableRotate.config.img.id).attr("src", resizableRotate.config.img.src);
    };

    /**
     * 背景设置
     */
    resizableRotate.setBackGround = function () {
        $("#" + resizableRotate.config.img.id).parent().css('background', 'url(' + resizableRotate.config.background.url + ')');
    };

    /**
     * 缩放
     */
    resizableRotate.resizable = function () {
        $("#" + resizableRotate.config.img.id).resizable();
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
        var item = resizableRotate.data;
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

    resizableRotate.startAll = function () {
        //缩放
        resizableRotate.resizable();
        //开启拖动
        resizableRotate.draggable();
        //开启旋转
        resizableRotate.rotateRun();
    };

    $(document).ready(function () {
        //设置被拖动的图片样式
        resizableRotate.style();
        //设置背景
        resizableRotate.setBackGround();
    });

    //提交
    function commit() {
        $.ajax({
            url:"${pageContext.request.contextPath}/dataImgTwoDimensional/saveAndUpdateDataImgTwoDimensional",
            type: "post",
            dataType: "json",
            data: resizableRotate.getData(),
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    window.close();
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>
