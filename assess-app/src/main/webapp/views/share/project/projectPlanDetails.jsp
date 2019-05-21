<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/9/13
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3> ${projectPlanDetails.projectPhaseName}-工作内容</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    开始时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control"><fmt:formatDate value="${projectPlanDetails.planStartDate}"
                                                                pattern="yyyy-MM-dd"/></label>
                </div>
                <label class="col-sm-1 control-label">
                    结束时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control"><fmt:formatDate value="${projectPlanDetails.planEndDate}"
                                                                pattern="yyyy-MM-dd"/> </label>
                </div>
                <label class="col-sm-1 control-label">
                    计划工时(小时)
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${projectPlanDetails.planHours}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    工作程序模板
                </label>
                <div class="col-sm-5">
                    <c:forEach var="item" items="${projectPhaseProcessTemplate}">
                        <div class='alert alert-success'
                             style='width: 18%;float: left;padding: 5px;margin-bottom: 10px;margin-left: 10px;'>
                            <i class='fa fa-download' onclick='downAttachments(${item.id})'
                               style='margin-right: 5px;font-size: 15px;cursor: pointer;'></i>
                            <a onclick='showAttachment(${item.id},"${item.fileExtension}")'>${item.fileName}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:if test="${not empty projectPlanDetails.returnDetailsReason}">
                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        重启原因
                    </label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectPlanDetails.returnDetailsReason}</label>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script type="application/javascript">

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
</script>
