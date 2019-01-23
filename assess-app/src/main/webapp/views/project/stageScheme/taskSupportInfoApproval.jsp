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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <jsp:include page="/views/project/stageScheme/module/supportInfoModule.jsp"></jsp:include>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        //支撑信息初始化
        supportInfoModule.init({
            readonly: true,
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });
    })
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

