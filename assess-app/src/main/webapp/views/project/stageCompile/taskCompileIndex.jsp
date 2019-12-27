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
            <jsp:include page="/views/project/stageCompile/module/compileInfoModule.jsp"></jsp:include>
            <%@include file="/views/share/form_apply.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<input type="hidden" id="compileReportDetailsJSON" value='${compileReportDetailsJSON}'>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        //初始化
        compileInfoModule.init({
            compileInfo: JSON.parse($("#compileReportDetailsJSON").val())
        });
    })
</script>
<script type="application/javascript">
    //提交
    function submit() {
        if (!compileInfoModule.valid()) {
            return false;
        }
        var data = {};
        data.compileReportDetailList = compileInfoModule.getData();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }


</script>

</html>

