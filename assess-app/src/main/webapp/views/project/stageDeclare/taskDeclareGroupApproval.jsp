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



            <div class="x_panel">
                <div class="x_content form-horizontal">
                    <form class="form-horizontal" id="declareApplyForm">

                        <input type="hidden" name="id" value="${declare.id}">
                        <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">

                    </form>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script>


</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>




</html>

