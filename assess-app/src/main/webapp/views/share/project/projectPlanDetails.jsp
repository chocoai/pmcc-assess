<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/9/13
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="loadReturnRecordList();">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3> ${projectPlanDetails.projectPhaseName}-工作内容</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <div class="form-horizontal">
            <div class="form-group">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开始时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control"><fmt:formatDate value="${projectPlanDetails.planStartDate}"
                                                                pattern="yyyy-MM-dd"/></label>
                </div>
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    结束时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control"><fmt:formatDate value="${projectPlanDetails.planEndDate}"
                                                                pattern="yyyy-MM-dd"/> </label>
                </div>
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    计划工时(小时)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${projectPlanDetails.planHours}</label>
                </div>
            </div>
            <div class="form-group">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    工作程序模板
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_projectPhaseWorkTemp"></div>
                </div>
            </div>
        </div>
        <div class="x_title">
            <h4>
                重启记录
            </h4>
        </div>
        <table class="table table-bordered" id="tb_returnRecordList">
        </table>
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
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
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
                activityName: "${activityName}",
                activityCnName: "${activityCnName}",
                CurrentStep: "${CurrentStep}",
                StepCount: "${StepCount}",
                viewUrl: "${viewUrl}",
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
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
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
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTaskApproval",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
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
