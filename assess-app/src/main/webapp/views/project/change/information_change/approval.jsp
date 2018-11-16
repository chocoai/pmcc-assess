<%--
  Created by IntelliJ IDEA.
  User: zly
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目信息变更</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <!-- 公共模块end -->
            <%@include file="approval/project_info_approval.jsp"%>
            <!-- 公共尾部模块引用 -->
            <!-- 审批用 -->
            <%@include file="/views/share/form_approval.jsp" %>

            <%@include file="/views/share/form_log.jsp" %>
            <!-- 尾部end -->
        </div>
    </div>
</div>



<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script type="text/javascript">

    //审批提交
    function approvalCommit(approvalModelDto) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/formsProjectInformationChange/approvalCommit",
            type: "post",
            dataType: "json",
            data: approvalModelDto,
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
    };

    $(function () {

    });
</script>