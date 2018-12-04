<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
                            <button  class="btn btn-default" onclick="findEstate()">
                                楼盘详情
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="/views/case/caseBuild/buildDetail.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
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
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">

    function findEstate() {
        var href = "${pageContext.request.contextPath}/case/findEstate";
        href += "?id=${caseBuildingMain.estateId}";
        window.open(href, "");
    }

</script>

</html>
