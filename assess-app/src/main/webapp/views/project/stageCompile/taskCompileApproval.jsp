<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <jsp:include page="/views/project/stageCompile/module/compileInfoModule.jsp"></jsp:include>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
<input type="hidden" id="compileReportDetailsJSON" value='${compileReportDetailsJSON}'>

<script type="text/javascript">
    $(function () {
        //初始化
        compileInfoModule.init({
            readonly: true,
            compileInfo: JSON.parse($("#compileReportDetailsJSON").val())
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

