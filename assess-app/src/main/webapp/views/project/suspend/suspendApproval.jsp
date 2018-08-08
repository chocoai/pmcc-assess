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
            <!--招标信息-->
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>申请信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div id="frm_apply" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    终止原因
                                </label>
                                <div class="col-sm-11">
                                    <label class="form-control">${projectSuspend.suspendReason}</label>
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
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/share/model_project.jsp" %>
<script type="application/javascript">
    $(function () {

        GetFileShows("apply_file",
            {
                tableName: "tb_project_suspend",
                processInsId: "${processInsId}",
                tableId: ${projectSuspend.id},
                projectId: "${projectSuspend.projectId}"
            }, 0);
        $("#frm_approval").validate();
    })
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectSuspend/submitsuspend",
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
</script>

</html>
