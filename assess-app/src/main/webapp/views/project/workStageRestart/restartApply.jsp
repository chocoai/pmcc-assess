<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>
<body>
<div class="main-content" style="margin:0px;">
    <div class="container" style="min-height:0">
        <div class="panel-body">
            <div class="row">
                <%@include file="/views/share/form_head.jsp" %>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-external-link-square"></i> 申请信息
                    </div>
                    <div class="panel-body">
                        <form id="frm_apply" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        所属项目
                                    </label>
                                    <div class="col-sm-7">
                                        <input type="hidden" id="projectId" name="projectId"
                                               value="${projectWorkStageRestart.projectId}">
                                        <input type="hidden" id="projectName" name="projectName"
                                               value="${projectWorkStageRestart.projectName}">
                                        <label class="form-control">${projectWorkStageRestart.projectName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        当前阶段
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="hidden" id="projectThisWorkStage" name="projectThisWorkStage"
                                               value="${projectWorkStageRestart.projectThisWorkStage}">
                                        <label class="form-control">${projectWorkStageRestart.projectThisWorkStage}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        重启阶段<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-11">
                                        <select placeholder="重启阶段"
                                                class='form-control search-select select2' required id="projectPlanOldId"
                                                name="projectPlanOldId">
                                            <option value="">请选择</option>
                                            <c:forEach var="item" items="${keyValueDtos}">
                                                <option value="${item.key}">${item.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        重启事由<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-11">
                                        <textarea required placeholder="重启事由" id="restartReason" name="restartReason"
                                                  class="form-control">${projectWorkStageRestart.restartReason}</textarea>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" id="id" name="id" value="${projectWorkStageRestart.id}">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </form>

                        <%@include file="/views/share/form_apply.jsp" %>
                    </div>
                </div>
                <c:if test="${projectWorkStageRestart.id>0}">
                    <%@include file="/views/share/form_log.jsp" %>
                </c:if>
            </div>
        </div>
    </div>
    <%@include file="/views/share/main_footer.jsp" %>
    <script type="application/javascript">
        $(function () {
            $("#frm_apply").validate();
            if (parseInt(${projectWorkStageRestart.projectPlanOldId}) > 0) {
                $("#projectPlanOldId").select2().val("${projectWorkStageRestart.projectPlanOldId}").trigger("change");
            }
            else {
                $("#projectPlanOldId").select2();
            }
        })
        //保存数据到数据库
        function saveform() {
            if ($("#frm_apply").valid()) {
                var url = "${pageContext.request.contextPath}/ProjectWorkStageRestart/applyWorkStageRestart";
                if (parseInt("${projectWorkStageRestart.id}") > 0) {
                    url = "${pageContext.request.contextPath}/ProjectWorkStageRestart/editWorkStageRestart";
                }
                Loading.progressShow();
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: formParams("frm_apply"),
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
        }
    </script>

</body>
</html>
