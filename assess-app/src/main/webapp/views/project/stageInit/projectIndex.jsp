<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <%@include file="/views/project/stageInit/stageInitModel/otherProjectIndexJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%--项目基本信息--%>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        <input type="hidden" name="projectInfoVoJson" id="projectInfoVoJson"
                                               value='${projectInfoVoJson}'>
                                        项目信息
                                        <small>${projectInfo.projectCategoryName}</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <%@include file="/views/project/stageInit/stageInitModel/projectInfo.jsp" %>
                        </div>
                    </div>
                    <!-- 委托人 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        委托人
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <%@include file="/views/project/stageInit/stageInitModel/projectConsignor.jsp" %>
                        </div>
                    </div>
                    <!-- 占有人 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        占有人
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <%@include file="/views/project/stageInit/stageInitModel/projectPossessor.jsp" %>
                        </div>
                    </div>
                    <!-- 报告使用单位 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        报告使用单位
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <%@include file="/views/project/stageInit/stageInitModel/projectUnit_information.jsp" %>

                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button type="button" id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <c:choose>
                                <c:when test="${processInsId == '0' || processInsId == null || processInsId == 0}">
                                    <button type="button" id="draft_btn" class="btn btn-warning" style="margin-left: 10px;" onclick="projectApplyDraft();">
                                        保存草稿
                                    </button>
                                    <button type="button" id="commit_btn" class="btn btn-success" style="margin-left: 10px;" onclick="projectApply(false);">
                                        直接提交
                                    </button>
                                    <button type="button" id="approval_btn" class="btn btn-primary" style="margin-left: 10px;" onclick="projectApply(true);">
                                        提交审批
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-primary" style="margin-left: 10px;" onclick="projectApply(true);">
                                        提交审批
                                    </button>
                                </c:otherwise>
                            </c:choose>
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
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(document).ready(function () {
        try {
            objProject.loadInit();
            objProject.getProjectNumber('${projectInfo.projectMemberVo.userAccountManager}');
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
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + e);
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
        data.bisAssign = $("#bisAssign").prop("checked");//注意这是是否分派标志

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
                    AlertSuccess("提交成功", "数据已成功保存到数据库", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


</script>
