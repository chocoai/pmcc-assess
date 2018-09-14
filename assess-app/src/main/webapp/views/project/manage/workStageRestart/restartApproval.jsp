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
                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        所属项目
                                    </label>
                                    <div class="col-sm-7">
                                        <label class="form-control">${projectWorkStageRestart.projectName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        当前阶段
                                    </label>
                                    <div class="col-sm-3">
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
                                        <label class="form-control">${projectWorkStageRestart.projectRestartStageName}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        重启事由<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-11">
                                        <label class="form-control">${projectWorkStageRestart.restartReason}</label>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" id="id" name="id" value="${projectWorkStageRestart.id}">
                        </div>

                        <%@include file="/views/share/form_approval.jsp" %>
                        <%@include file="/views/share/form_log.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/views/share/main_footer.jsp" %>
    <script type="application/javascript">
        //保存数据到数据库
        function saveform() {
            if (!$("#frm_approval").valid()) {
                return false;
            }

            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/ProjectWorkStageRestart/approvalWorkStageRestart",
                type: "post",
                dataType: "json",
                data: formApproval.getFormData(),
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

</body>
</html>
