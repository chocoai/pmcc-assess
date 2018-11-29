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
                        <div class="col-sm-12" style="height:616px;">
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
                                关闭
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
<script type="text/javascript">
    var dataImgTwoDimensional = "${dataImgTwoDimensional}" ;

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
        img: {
            id: "imgA",
        }
    };

    /**
     * 背景设置
     */
    resizableRotate.setBackGround = function () {
        $("#" + resizableRotate.config.img.id).parent().css('background', 'url(' + "${dataImgTwoDimensional.backgroundUrl}" + ')');
    };
$(function () {
    resizableRotate.setBackGround();
    $("#" + resizableRotate.config.img.id).attr("src", "${dataImgTwoDimensional.imgUrl}");
    $("#" + resizableRotate.config.img.id).attr("style", "${dataImgTwoDimensional.style}");
    $("#" + resizableRotate.config.img.id).height("${dataImgTwoDimensional.height}");
    $("#" + resizableRotate.config.img.id).width("${dataImgTwoDimensional.width}");
});
</script>
</html>