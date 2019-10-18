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

            <%@include file="./baseView.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <button type="button" onclick="setProgramme();" class="btn btn-success btn-xs">
                            <i class='fa fa-cog fa-white'></i> 设置方案
                        </button>
                    </div>
                </div>
            </div>

            <%@include file="../stagePlan.jsp" %>

            <%@include file="/views/share/form_approval.jsp" %>

            <%@include file="/views/share/form_log.jsp" %>

            <%@include file="/views/share/form_details.jsp" %>

        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>


</html>

<script type="text/javascript">

    //设置方案
    function setProgramme() {
        openWin('${pageContext.request.contextPath}/schemeProgramme/index?projectId=${projectId}&planId=${projectPlan.id}', function () {
            projectStagePlan.stageTable.bootstrapTable('refresh');
        });
    };

    $(document).ready(function () {

    });


</script>