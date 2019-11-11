<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <%@include file="/views/project/stageInit/other/otherProjectIndexJs.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>

            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        <input type="hidden" name="projectInfoVoJson" id="projectInfoVoJson"
                               value='${projectInfoVoJson}'>
                        项目信息
                        <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <!-- 项目基本信息 start -->
                    <%@include file="/views/project/stageInit/other/projectInfo.jsp" %>
                    <!-- 项目基本信息 end -->
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <h2>委托人</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <!-- 委托人 start -->
                <%@include file="/views/project/stageInit/other/projectConsignor.jsp" %>
                <!-- 委托人 end -->
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <h2> 占有人</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <!-- 占有人 start -->
                <%@include file="/views/project/stageInit/other/projectPossessor.jsp" %>
                <!-- 占有人 end -->
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <!-- 报告使用单位 start -->
                <%@include file="/views/project/stageInit/other/projectUnit_information.jsp" %>
                <!-- 报告使用单位 end -->
            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4   col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="draft_btn" class="btn btn-warning" onclick="projectApplyDraft();">
                                保存草稿<i style="margin-left: 10px" class="fa fa-save"></i>
                            </button>
                            <button id="commit_btn" class="btn btn-success" onclick="projectApply(false);">
                                直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                            <c:if test="${processInsId == '0' || processInsId == null || processInsId == 0}">
                                <button id="approval_btn" class="btn btn-primary" onclick="projectApply(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    $(document).ready(function () {
        try {
            objProject.loadInit();
        } catch (e) {
            console.log(e);
        }

        FileUtils.getFileShows({
            target: "projectPhaseWorkTemp",
            formData: {
                tableName: AssessDBKey.ProjectPhase,
                fieldsName: AssessUploadKey.PROJECT_PHASE_WORK_TEMP,
                tableId: '${projectPhaseId}'
            },
            deleteFlag: false
        })
    });

    //保存草稿
    function projectApplyDraft() {
        var projectInfo = formParams(objProject.config.info.frm);//projectName
        var html = "<span class='help-block' for='for'>" + "该字段为必填项" + "</span>";
        if (!objProject.isNotBlank(projectInfo.projectName)) {
            $("#" + objProject.config.info.frm).find("input[name='projectName']").after(html.replace(/for/g, "projectName"));
            return false;
        }

        var data = {};
        data.formData = JSON.stringify(objProject.getFormData());
        var url = "${pageContext.request.contextPath}/projectInfo/projectApplyDraft";
        Loading.progressShow();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("保存成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }

    //项目提交立项
    function projectApply(mustUseBox) {
        if (!objProject.valid()) {
            return false;
        }
        var data = {};
        data.formData = JSON.stringify(objProject.getFormData());
        data.mustUseBox = mustUseBox;//注意这是是否发起流程标志,假如为false直接进入下一个阶段,如果为true那么会发起流程
        var url = "${pageContext.request.contextPath}/projectInfo/projectApplySubmit";
        if ("${empty processInsId?"0":processInsId}" != "0") {
            url = "${pageContext.request.contextPath}/projectInfo/projectEditSubmit";
            var approvalData = formParams("frm_approval");
            data.projectInfoId = '${projectInfo.id}';
            data = $.extend(data, approvalData);
        }
        Loading.progressShow();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


</script>
