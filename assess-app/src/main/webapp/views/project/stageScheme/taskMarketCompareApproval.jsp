<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <jsp:include page="/views/method/module/marketCompareIndex.jsp"></jsp:include>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<input type="hidden" id="marketCompareJSON" value='${marketCompareJSON}'>
<input type="hidden" id="fieldsJSON" value='${fieldsJSON}'>
<input type="hidden" id="evaluationJSON" value='${evaluationJSON}'>
<input type="hidden" id="casesJSON" value='${casesJSON}'>

<script type="text/javascript">
    $(function () {
        marketCompare.init({
            readonly: true,
            marketCompare: JSON.parse($("#marketCompareJSON").val()),
            fields: JSON.parse($("#fieldsJSON").val()),
            evaluation: JSON.parse($("#evaluationJSON").val()),
            projectId: ${projectPlanDetails.projectId},
            mcId: '${mcId}',
            isLand: '${isLand}',
            judgeObjectId: '${judgeObject.id}',
            cases: JSON.parse($("#casesJSON").val())
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

