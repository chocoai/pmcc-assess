<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目信息修改</title>

    <%@include file="/views/share/main_css.jsp" %>
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
                        <%@include file="apply/project_info_apply.jsp" %>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <c:if test="${not empty processInsId and processInsId ne '0'}">
                                <button type="button" class="btn btn-warning" style="margin-left: 10px;" onclick="masterObj.closeProcess()">
                                    撤销
                                </button>
                            </c:if>
                            <button id="commit_btn" class="btn btn-primary" style="margin-left: 10px;" onclick="masterObj.commit();">
                                提交
                            </button>
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
<%@include file="/views/project/stageInit/stageInitModel/otherProjectIndexJs.jsp" %>
</body>
</html>

<script type="text/javascript">
    $(function () {
        objProject.loadInit();

        $("#" + objProject.config.info.frm).find("input[name='userAccountMember']").closest('.form-group').hide();
        $("#" + objProject.config.info.frm).find("input[name='userAccountManager']").closest('.form-group').hide();
    })

    var masterObj = {};
    /**
     * 提交数据
     * @returns {*}
     */
    masterObj.commit = function () {
        if (!objProject.valid()) {
            return false;
        }
        if (!$("#project_info_form").valid()) {
            return false;
        }
        var data = {};

        if("${CurrentStep}"!="0"){
            var projectInfoVoJson = $("#projectInfoVoJson").val() ;
            if (projectInfoVoJson){
                try {
                    data.oldRecord = JSON.stringify(JSON.parse(projectInfoVoJson));
                } catch (e) {
                }
                <%--data.oldRecord = JSON.stringify(JSON.parse('${el:toJsonString(projectInfo)}')) ;--%>
            }
        }
        data.newRecord = JSON.stringify(objProject.getFormData());
        data.changeReason = $("#changeReason").val();
        data.projectId = "${projectInfo.id}";
        data.id = "${costsProjectChangeLog.id}"


        if ("${processInsId}" == "0") {
            //申请
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/project.information.change/applyCommit",
                type: "post",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "提交数据成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            var approvalData = formParams("frm_approval");
            data = $.extend({}, approvalData, data);
            //修改提交
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/project.information.change/editCommit",
                type: "post",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "提交数据成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    };

    //撤销
    masterObj.closeProcess = function () {
        AlertConfirm("是否确认撤销", "流程撤销后将不可恢复", function () {
            var approvalModelDto = formSerializeArray($("#process_variable_form"));
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/public/closeProcess",
                type: "post",
                data: approvalModelDto,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "流程撤销成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        })
    };

</script>

