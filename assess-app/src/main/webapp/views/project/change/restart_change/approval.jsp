<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目重启变更</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <!-- 公共模块引用 -->
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <!-- 公共模块end -->

                    <%@include file="/views/project/change/restart_change/modules/restart_approval.jsp" %>
                    <!-- 公共尾部模块引用 -->
                    <!-- 审批用 -->
                    <%@include file="/views/share/form_approval.jsp" %>
                    <!-- 尾部end -->
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>




</body>
</html>
<script type="text/javascript">
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectRestart/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }
</script>