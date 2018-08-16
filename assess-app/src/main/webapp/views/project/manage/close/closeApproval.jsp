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
                <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-external-link-square"></i> 申请信息
                    </div>
                    <div class="panel-body">
                        <form id="frm_apply" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        项目名称
                                    </label>
                                    <div class="col-sm-11">
                                        <label class="form-control">${projectClose.projectName}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        终止原因
                                    </label>
                                    <div class="col-sm-11">
                                        <label class="form-control">${projectClose.reason}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">
                                    附件
                                </label>
                                <div class="col-sm-11">
                                    <div id="_apply_file">
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <%@include file="/views/share/form_approval.jsp" %>
                <%@include file="/views/share/form_log.jsp" %>
            </div>
        </div>
    </div>
    <%@include file="/views/share/main_footer.jsp" %>
    <%@include file="/views/share/model_project.jsp" %>
    <script type="application/javascript">
        $(function () {

            GetFileShows("apply_file",
                {
                    tableName: "tb_project_close",
                    processInsId: "${processInsId}",
                    tableId: ${projectClose.id},
                    projectId: "${projectClose.projectId}"
                }, 0);
            $("#frm_approval").validate();
        })
        function saveform() {
            if (!$("#frm_approval").valid()) {
                return false;
            }

            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/projectClose/submitClose",
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
