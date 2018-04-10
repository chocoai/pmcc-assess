<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="col-sm-4 col-sm-offset-5">
            <a id="cancel_btn" class="btn btn-default" onclick="window.close()">
                取消
            </a>
            <c:if test="${bisEdit==1}">
                <a id="commit_btn" class="btn btn-warning" onclick="closeProcess()">
                    撤销
                </a>
            </c:if>
            <%--<c:if test="${bisEdit!=1&&empty hideSaveDraft}">--%>
            <%--<a id="commit_btn" class="btn btn-warning" onclick="saveDraft()">--%>
            <%--保存草稿--%>
            <%--</a>--%>
            <%--</c:if>--%>
            <a id="commit_btn" class="btn btn-success" onclick="saveform()">
                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
            </a>
        </div>
    </div>
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
                <button type='button' onclick="saveCloseProcess()" class='btn btn-success save-event'>
                    <i class='fa fa-check'></i> 确认
                </button>
                <button type="button" data-dismiss="modal" class="btn btn-light-grey">
                    关闭
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
            Alert("撤销原因不能为空!");
            return false;
        }
        Alert("流程撤销后将不可恢复，是否继续？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/FinancialBase/CloseSubmit",
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
        });
    }

    function saveformToServer(processTitle, formData, appointUserAccount) {
        Loading.progressShow();
        formData["publicProjectId"] = "${publicProjectId}";
        $.ajax({
            url: "${pageContext.request.contextPath}/FinancialBase/ApplySubmit",
            data: {
                appointUserAccount: appointUserAccount,
                boxId: "${boxId}",
                phaseId: "${phaseId}",
                processTitle: processTitle,
                viewUrl: "${viewUrl}",
                phaseId: "${phaseId}",
                projectId: "${projectInfo.id}",
                formData: formData
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
    //保存草稿
    function saveDraftToServer(processTitle, formData, appointUserAccount) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/FinancialBase/saveDraft",
            data: {
                ruId: "${ruId}",
                boxId: "${boxId}",
                phaseId: "${phaseId}",
                processTitle: processTitle,
                viewUrl: "${viewUrl}",
                phaseId: "${phaseId}",
                projectId: "${projectInfo.id}",
                formData: formData
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存草稿成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存草稿失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function saveEditformToServer(formData, appointUserAccount) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/FinancialBase/EditSubmit",
            data: {
                nextApproval: appointUserAccount,
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
                formData: formData

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
</script> 