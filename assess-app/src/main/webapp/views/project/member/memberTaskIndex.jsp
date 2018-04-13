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
                    <h2>设置项目经理</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_task" class="form-horizontal">
                        <%@include file="/views/project/member/memeberModel.jsp" %>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button class="btn btn-success" onclick="submit()">
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


    $(function () {
        $("#frm_task").validate();
    });


    function submit() {
        if (!$("#frm_task").valid()) {
            return false;
        }
        var data = {
            id: $("#id").val(),
            planId: "${projectPlan.id}",
            projectId: "${projectPlan.projectId}",
            userAccountManager: $("#userAccountManager").val()
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectMember/setProjectManager",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

</script>

</html>
