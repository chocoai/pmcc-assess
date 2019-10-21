<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="../projectNavigation.jsp" %>
        <div class="right_col" role="main">
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="../projectStagePlan.jsp" %>
            <%@include file="/views/share/form_details.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/html" id="surveyCustomeBtnHtml">
    <input type="button" class="btn btn-xs btn-success" onclick="projectDetailsEnterNextStage();"
           value="添加案例">
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#workStageCustomBtn').append($('#surveyCustomeBtnHtml').html());
    });
</script>