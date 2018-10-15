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
            <jsp:include page="/views/method/module/marketCompareIndex.jsp"></jsp:include>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<input type="hidden" id="marketCompareJSON" value='${marketCompareJSON}'>
<input type="hidden" id="fieldsJSON" value='${fieldsJSON}'>
<input type="hidden" id="evaluationJSON" value='${evaluationJSON}'>
<input type="hidden" id="casesJSON" value='${casesJSON}'>
<input type="hidden" id="casesAllJSON" value='${casesAllJSON}'>

<input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
<script type="text/javascript">
    $(function () {
        marketCompare.init({
            readonly: true,
            marketCompare: JSON.parse($("#marketCompareJSON").val()),
            fields: JSON.parse($("#fieldsJSON").val()),
            evaluation: JSON.parse($("#evaluationJSON").val()),
            casesAll: JSON.parse($("#casesAllJSON").val()),
            mcId: '${mcId}',
            setUse: '${setUse}',
            cases: JSON.parse($("#casesJSON").val())
        });

        //支撑信息初始化
        supportInfoModule.init({
            readonly: true,
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });
    })
</script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

