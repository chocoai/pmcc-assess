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
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <i class="fa fa-external-link-square"></i> 设置项目经理1
                </div>
                <div class="x_content">
                    <form id="frm_set_project_manager" class="form-horizontal">
                        <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input required placeholder="项目经理" id="projectName" name="projectName"
                                           value="${projectInfo.projectName}" class="form-control">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <%@include file="/views/share/form_approval.jsp" %>
                <%@include file="/views/share/form_log.jsp" %>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">
    $(function () {

    })
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formParams("frm_approval");
        var dataManager = formParams("frm_set_project_manager");
        data = $.extend({}, dataManager, approvalData);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/projectApprovalSubmit",
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
</html>
