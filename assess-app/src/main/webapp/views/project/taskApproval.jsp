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
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${projectPlanDetails.projectPhaseName}工作成果</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                实际工时
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${projectPlanDetails.actualHours}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                成果描述
                            </label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                <label class="form-control">${projectPlanDetails.taskRemarks}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                成果文件
                            </label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                <div id="_file_upload_task"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        FileUtils.getFileShows({
            target: "file_upload_task",
            formData: {
                tableName: AssessDBKey.ProjectPlanDetails,
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: false
        })
    })
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

