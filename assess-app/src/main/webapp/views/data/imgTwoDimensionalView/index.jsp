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
                                <%--<li class="active"><a href="#" onclick="resizableRotate.keyDown()">开启键盘左右移动</a></li>--%>
                                <%--<li><a href="#" onclick="resizableRotate.startAll()">开启鼠标旋转</a></li>--%>
                                <li><a href="#" onclick="resizableRotate.imgMaxAndMin(true)">放大</a></li>
                                <li><a href="#" onclick="resizableRotate.imgMaxAndMin(false)">缩小</a></li>
                                <li><a href="#" onclick="resizableRotate.rotateTransform(true)">右旋转</a></li>
                                <li><a href="#" onclick="resizableRotate.rotateTransform(false)">左旋转</a></li>
                                <%--<li><a href="#" onclick="resizableRotate.canvasHandle()">canvas 操作</a></li>--%>
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
        $("#" + resizableRotate.config.img.id).attr("style", "left:0px; top:0px; position:absolute;transform: rotate(0deg);");
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
        // $("#" + resizableRotate.config.img.id).parent().draggable();
        $("#" + resizableRotate.config.img.id).draggable();
    };

    /**
     * 收集数据
     */
    resizableRotate.formParams = function () {
        var ele = $("#" + resizableRotate.config.img.id);
        var item = {
            deg: resizableRotate.getAngle(document.getElementById(resizableRotate.config.img.id)),
            style: ele.attr("style"),
            imgUrl:resizableRotate.config.img.src,
            backgroundUrl:resizableRotate.config.background.url,
            height:$("#"+resizableRotate.config.img.id).height(),
            width:$("#"+resizableRotate.config.img.id).width(),
            id:"${dataImgTwoDimensional.id}"
        };
        return item;
    };

    resizableRotate.rotateTransform = function (flag) {
        var img = document.getElementById(resizableRotate.config.img.id);
        var current = 0;
        current = resizableRotate.getAngle(img);
        if (flag) {
            current = current + 5;
            img.style.transform = 'rotate(' + current + 'deg)';
        } else {
            current = current - 5;
            img.style.transform = 'rotate(' + current + 'deg)';
        }
    };

    resizableRotate.getAngle = function (el) {
        var st = window.getComputedStyle(el, null);
        var tr = st.getPropertyValue("-webkit-transform") ||
            st.getPropertyValue("-moz-transform") ||
            st.getPropertyValue("-ms-transform") ||
            st.getPropertyValue("-o-transform") ||
            st.getPropertyValue("transform") ||
            "FAIL";
        if (this.isNotBlank(tr)) {
            var values = tr.split('(')[1].split(')')[0].split(',');
            var a = values[0];
            var b = values[1];
            var c = values[2];
            var d = values[3];
            var scale = Math.sqrt(a * a + b * b);
            var sin = b / scale;
            var angle = Math.round(Math.atan2(b, a) * (180 / Math.PI));
            return angle;
        } else {
            return 0;
        }
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
       Alert("方法不建议使用",2,null,function () {
           //缩放
           resizableRotate.resizable();
           //开启拖动
           resizableRotate.draggable();
           //开启旋转
           resizableRotate.rotateRun();
       });
    };

    resizableRotate.keyDown = function () {
        var img = document.getElementById(resizableRotate.config.img.id);
        img.left = 0;
        img.top = 0;
        document.onkeydown = move;

        function move(e) {
            var k = e.keyCode || e.which;
            switch (k) {
                case 37:
                    img.left = img.left - 10;
                    break;
                case 38:
                    img.top = img.top - 10;
                    break;
                case 39:
                    img.left = img.left + 10;
                    break;
                case 40:
                    img.top = img.top + 10;
                    break;
            }
            return img.style.left = img.left + 'px', img.style.top = img.top + 'px';
        };
    };

    resizableRotate.canvasHandle = function () {
        Alert("暂时未提供!");
    };

    resizableRotate.imgMaxAndMin = function (flag) {
        var oImg = document.getElementById(resizableRotate.config.img.id);
        if (flag) {
            oImg.width = oImg.width * 1.2;
            oImg.height = oImg.height * 1.2;
        }
        else {
            oImg.width = oImg.width / 1.2;
            oImg.height = oImg.height / 1.2;
        }
    };

    $(document).ready(function () {
        //设置被拖动的图片样式
        resizableRotate.style();
        //设置背景
        resizableRotate.setBackGround();
        resizableRotate.draggable();
        var dataImgTwoDimensional = "${dataImgTwoDimensional}" ;
        if (resizableRotate.isNotBlank(dataImgTwoDimensional)){
            $("#" + resizableRotate.config.img.id).attr("style", "${dataImgTwoDimensional.style}");
            $("#" + resizableRotate.config.img.id).height("${dataImgTwoDimensional.height}");
            $("#" + resizableRotate.config.img.id).width("${dataImgTwoDimensional.width}");
        }
    });

    //提交
    function commit() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/dataImgTwoDimensional/saveAndUpdateDataImgTwoDimensional",
            type: "post",
            dataType: "json",
            data: resizableRotate.formParams(),
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("提交数据失败!");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>
