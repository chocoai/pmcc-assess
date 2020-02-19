<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty projectPlanResponsibility and projectPlanResponsibility.pid<=0}">
    <div class="x_panel">
        <div class="x_content">
            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                    取消
                </button>
                <c:choose>
                    <c:when test="${projectPhase.bisUseBox eq false}">
                        <button  class="btn btn-success" onclick="submit(false);">
                            直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                        <button  class="btn btn-primary" onclick="submit(true);">
                            提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button  class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${processInsId ne '0'}">
    <div class="x_panel">
        <div class="x_content">
            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                <button  class="btn btn-default" onclick="window.close()">
                    取消
                </button>
                <button  class="btn btn-success" onclick="submit();">
                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                </button>
            </div>
        </div>
    </div>
</c:if>--%>
<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
    <button class="btn btn-default " style="margin-left: 10px;" onclick="window.close()">
        取消
    </button>

    <button id="btn_submit" class="btn btn-primary" style="margin-left: 10px;" onclick="saveform();">
        提交
    </button>
    <c:if test="${bisEdit==1}">
        <a id="commit_btn" class="btn btn-warning  " style="margin-left: 10px;" onclick="closeProcess()">
            撤销
        </a>
    </c:if>
</div>


<div id="model_closeProcess" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">撤销原因</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group ">
                        <textarea id="closeProcess" required name="closeProcess" class="form-control"></textarea>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveCloseProcess()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function closeProcess() {
        $('#model_closeProcess').modal({
            backdrop: 'static'
        });
    }

    function saveCloseProcess() {
        var closeProcess = $("#closeProcess").val();
        if (closeProcess == "") {
            notifyInfo("提示","撤销原因不能为空");
            return false;
        }

        AlertConfirm("是否确认撤销","流程撤销后将不可恢复",function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/public/closeSubmit",
                data: {
                    boxId: "${boxId}",
                    processInsId: "${processInsId}",
                    taskId: "${taskId}",
                    activityName: "${activityName}",
                    activityKey: "${activityReName}",
                    activityCnName: "${activityCnName}",
                    CurrentStep: "${CurrentStep}",
                    StepCount: "${StepCount}",
                    viewUrl: "${viewUrl}",
                    workPhaseId: "${phaseId}",
                    activityId: "${activityId}",
                    opinions: closeProcess,
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "提交数据成功", function () {
                            window.close();
                        });
                    } else {
                        AlertError("提交数据失败", result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败", result);
                }
            });
        });


    }


    //提交标识，业务数据JSON串，提交成果描述，实际工时,工作成果选择复核人
    function submitToServer(data, taskRemarks, actualHours) {
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
                taskRemarks: taskRemarks,
                actualHours: actualHours,
                projectDetailsId: "${projectPlanDetails.id}",
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
                } else {
                    AlertError("提交数据失败", result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败", result);
            }
        });
    }

    //提交标识，业务数据JSON串，提交成果描述，实际工时
    function submitEditToServer(data, taskRemarks, actualHours) {
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
                activityName: "${activityName}",
                activityCnName: "${activityCnName}",
                activityKey: "${activityReName}",
                activityId: "${activityId}",
                reId: "${reId}",
                CurrentStep: "${CurrentStep}",
                StepCount: "${StepCount}",
                workStageId: "${projectPhase.workStageId}",
                nextApproval: nextApproval,
                bisNext: 0,
                viewUrl: "${viewUrl}",
                formData: data,
                taskRemarks: taskRemarks,
                actualHours: actualHours,
                projectId: "${projectId}"


            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("提交数据失败", result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败", result);
            }
        });
    }

</script>