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
    <title>项目拿号</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <!-- 公共模块end -->

                    <%@include file="/views/project/takeNumber/modules/info_approval.jsp" %>

                    <!-- 公共尾部模块引用 -->
                    <%@include file="/views/share/form_approval.jsp" %>
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
            url: "${pageContext.request.contextPath}/projectTakeNumber/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
</script>