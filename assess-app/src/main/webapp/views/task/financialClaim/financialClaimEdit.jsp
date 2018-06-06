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


            <c:if test="${projectPlanDetailsVo.declareFormName=='loanCustomInfoAssist'}">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>${projectPlanDetailsVo.projectPhaseName}</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" id="${projectPlanDetailsVo.declareFormName}_details_id" value="${projectPlanDetailsVo.id}">
                        <jsp:include page="/views/task/financialClaim/share/${projectPlanDetailsVo.declareFormName}.jsp"></jsp:include>
                    </div>
                </div>
            </c:if>
            <c:if test="${projectPlanDetailsVo.declareFormName!='loanCustomInfoAssist'}">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>客户信息</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <jsp:include page="/views/task/financialClaim/share/loanCustomInfoAssistDisplay.jsp"></jsp:include>
                    </div>
                </div>
                <div class="x_panel">
                    <div class="x_title">
                        <h2>${projectPlanDetailsVo.projectPhaseName}</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" id="${projectPlanDetailsVo.declareFormName}_details_id" value="${projectPlanDetailsVo.id}">
                        <jsp:include page="/views/task/financialClaim/share/${projectPlanDetailsVo.declareFormName}.jsp"></jsp:include>
                    </div>
                </div>
            </c:if>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submitEditToServer();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTaskApproval",
            type: "post",
            dataType: "json",
            data: formParams("frm_approval"),
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
    //提交标识，业务数据JSON串，提交成果描述，实际工时
    function submitEditToServer() {
        var nextApproval = "";
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTaskEdit",
            data: {

                boxId: "${boxId}",
                processInsId: "${processInsId}",
                taskId: "${taskId}",
                activityName: "${activityName}",
                activityCnName: "${activityCnName}",
                CurrentStep: "${CurrentStep}",
                StepCount: "${StepCount}",
                viewUrl: "${viewUrl}",
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
</script>

</html>

