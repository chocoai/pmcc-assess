<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/9/13
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link" onclick="loadReturnRecordList();">
            <div class="card-head-row">
                <div class="card-title">
                    ${projectPlanDetails.projectPhaseName}-工作内容
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-up"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body " style="display: none;">
            <div class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                开始时间
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control input-full"><fmt:formatDate value="${projectPlanDetails.planStartDate}"
                                                                            pattern="yyyy-MM-dd"/></label>
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                结束时间
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control input-full"><fmt:formatDate value="${projectPlanDetails.planEndDate}"
                                                                            pattern="yyyy-MM-dd"/> </label>
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                计划工时(小时)
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control input-full">${projectPlanDetails.planHours}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                工作程序模板
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <div id="_projectPhaseWorkTemp"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-bordered" id="tb_returnRecordList">
                </table>
            </div>
        </div>

    </div>
</div>
<script type="application/javascript">
    $(function () {
        loadReturnRecordList();
        FileUtils.getFileShows({
            target: "projectPhaseWorkTemp",
            formData: {
                tableName: AssessDBKey.ProjectPhase,
                fieldsName: AssessUploadKey.PROJECT_PHASE_WORK_TEMP,
                tableId: '${projectPlanDetails.projectPhaseId}'
            },
            deleteFlag: false
        })
    })

    //提交标识，业务数据JSON串，提交成果描述，实际工时,工作成果选择复核人
    function submitToServer(data, mustUseBox) {
        var nextApproval = "";
        if ($("#nextApproval").length > 0) {
            nextApproval = $("#nextApproval").val();
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTask",
            data: {
                formData: data,
                responsibilityId: "${responsibilityId}",
                actualHours: mainObj.getDiffHours(),
                projectDetailsId:${projectPlanDetails.id},
                nextApproval: nextApproval,
                mustUseBox: mustUseBox == undefined ? false : mustUseBox
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //提交标识，业务数据JSON串，提交成果描述，实际工时
    function submitEditToServer(data) {
        var nextApproval = "";
        if ($("#nextApproval").length > 0) {
            nextApproval = $("#nextApproval").val();
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTaskEdit",
            data: {
                boxId: "${boxId}",
                processInsId: "${processInsId}",
                taskId: "${taskId}",
                activityId: "${activityId}",
                reActivityName: "${reActivityName}",
                activityName: "${activityName}",
                activityCnName: "${activityCnName}",
                CurrentStep: "${CurrentStep}",
                StepCount: "${StepCount}",
                viewUrl: "${viewUrl}",
                agentUserAccount: "${agentUserAccount}",
                formData: data,
                actualHours: mainObj.getDiffHours(),
                projectId: "${projectId}",
                workStageId: "${projectPhase.workStageId}",
                nextApproval: nextApproval
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //审批页面提交
    function saveApprovalform(formData, approvalData) {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        data["formData"] = formData;
        data = $.extend({}, data, approvalData);

        if ("${bisCheck}" == "1") {
            if (!vaildChksData()) {
                return false;
            }
            var itemObj = getChksData();
            data = jQuery.extend({}, data, itemObj);
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTaskApproval",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    //任务重启原因
    function loadReturnRecordList() {
        var cols = [];
        cols.push({field: 'reason', title: '重启原因'});
        cols.push({field: 'returnPersonName', title: '重启人'});
        cols.push({
            field: 'returnTime', title: '重启时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'fileViewName', title: '附件'});
        $("#tb_returnRecordList").bootstrapTable('destroy');
        TableInit("tb_returnRecordList", "${pageContext.request.contextPath}/ProjectTask/loadReturnRecordList", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }


</script>
