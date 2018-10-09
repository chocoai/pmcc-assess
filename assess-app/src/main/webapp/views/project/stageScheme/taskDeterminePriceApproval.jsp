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
            <jsp:include page="/views/project/stageScheme/module/supportInfoModule.jsp"></jsp:include>
            <jsp:include page="/views/method/incomeDetail.jsp"></jsp:include>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
<input type="hidden" id="mdIncomeJSON" value='${mdIncomeJSON}'>
<input type="hidden" id="incomeSelfSupportJSON" value='${incomeSelfSupportJSON}'>
<script type="text/javascript">
    $(function () {
        //支撑信息初始化
        supportInfoModule.init({
            readonly: true,
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });

        //收益法数据显示
        income.view({
            incomeInfo: JSON.parse($("#mdIncomeJSON").val()),
            incomeSelfSupport: JSON.parse($("#incomeSelfSupportJSON").val())
        })
    })
</script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

