<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/layer/theme/default/layer.css" rel="stylesheet">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <jsp:include page="/views/project/stageScheme/module/ftContentChangeModule.jsp"></jsp:include>
            <jsp:include page="/views/project/stageScheme/module/supportInfoModule.jsp"></jsp:include>
            <!-- 引入假设开发法模块 -->
            <jsp:include page="/views/method/marketDevelopmentIndex.jsp"></jsp:include>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
        <input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<%--<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>--%>

<script>
    $(function () {
        //支撑信息初始化
        supportInfoModule.init({
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });
    })
</script>
<script type="application/javascript">
    $(function () {
    });



    function submit() {
        if (!supportInfoModule.valid()) {
            return false;
        }
        var data = {};
        data.supportInfoList = supportInfoModule.getData();
        data.mdDevelopmentHypothesis = optionsBuildBox.getHypothesisDevelopment();
        data.mdDevelopmentArchitectural = optionsBuildBox.getArchitecturalEngineering();
        data.mdDevelopment = optionsBuildBox.getBuildKey();
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

</script>

</html>

