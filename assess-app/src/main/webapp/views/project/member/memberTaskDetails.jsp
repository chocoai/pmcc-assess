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
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>设置项目经理</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                项目经理<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${projectMemberVo.userAccountManagerName}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                项目成员
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${projectMemberVo.userAccountMemberName}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时(h)<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control">${projectPlanDetails.actualHours}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${projectPlanDetails.taskRemarks}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <div id="_file_upload"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_details.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<script type="application/javascript">

    $(function () {
        GetFileShows("file_upload",
            {
                tableName: "tb_project_plan_details",
                processInsId: "${processInsId}",
                tableId: "${projectPlanTask.id}",
                fieldsName: "task",
                projectId: "${projectPlanDetails.projectId}"
            }, "0");
    });
</script>
</html>
