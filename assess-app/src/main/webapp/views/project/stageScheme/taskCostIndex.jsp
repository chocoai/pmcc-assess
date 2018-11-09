<!-- 成本法 -->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link href="/pmcc-assess/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
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
            <!-- 引入成本法模块 -->
            <jsp:include page="/views/method/marketCostIndex.jsp"></jsp:include>
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
    </div>
    <input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="text/javascript">
    $(function () {
        //支撑信息初始化
        supportInfoModule.init({
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });
    })
</script>
<script type="application/javascript">

    $(function () {

        $("#frm_task").validate();

        loadUploadFiles();
        //上传附件
        FileUtils.uploadFiles({
            target: "apply_file",
            showFileList: false,
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.ProjectPlanDetails,
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true,
            onUploadComplete: function () {
                loadUploadFiles();
            }
        });
    });

    //显示附件
    function loadUploadFiles() {
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: AssessDBKey.ProjectPlanDetails,
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    }

    //提交
    function submit() {
        if (!supportInfoModule.valid()) {
            return false;
        }
        var data = {};
        data.supportInfoList = supportInfoModule.getData();
        data.mdCostBuilding = optionsBuildBox.getMdCostBuilding();
        data.mdCostConstruction = optionsBuildBox.getMdCostConstruction();
        data.mdCost = optionsBuildBox.getBuildKey();
        console.log(data.mdCostConstruction);

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

</script>

</html>

