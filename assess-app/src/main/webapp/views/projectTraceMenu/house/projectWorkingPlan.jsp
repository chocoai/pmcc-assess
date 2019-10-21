<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="../trace_project_menu.jsp" %>
        <div class="right_col" role="main">
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="../stagePlan.jsp" %>
            <%@include file="/views/share/form_details.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/html" id="schemeCustomeBtnHtml">
    <input type="button" class="btn btn-xs btn-success" onclick="setProgramme();"
           value="方案设置">
</script>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $('#workStageCustomBtn').append($('#schemeCustomeBtnHtml').html());
    });
    //设置方案
    function setProgramme() {
        openWin('${pageContext.request.contextPath}/schemeProgramme/index?projectId=${projectId}&planId=${projectPlan.id}', function () {
            projectStagePlan.stageTable.bootstrapTable('refresh');
        });
    };
</script>