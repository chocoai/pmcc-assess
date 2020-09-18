<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
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

                    <%@include file="/views/method/module/baseLandPriceIndex.jsp" %>

                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/case/case.common.js?v=${assessVersion}"></script>

<script type="application/javascript">
    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = formParams("master");
        formData.dateAmend = $.trim($("#dateAmend").val()) ? $("#dateAmend").val() : '';
        formData.periodAmend = $.trim($("#periodAmend").text()) ? $("#periodAmend").text() : '';
        formData.volumeFractionAmend = $.trim($("#volumeFractionAmend").val()) ? $("#volumeFractionAmend").val() : '';
        if (!$.isNumeric(formData.volumeFractionAmend)) {
            formData.volumeFractionAmend = null;
        }
        formData.parcelPrice = $.trim($("#parcelPrice").text()) ? $("#parcelPrice").text() : '';
        formData.parcelBhouPrice = $.trim($("#parcelBhouPrice").text()) ? $("#parcelBhouPrice").text() : '';
        formData.parcelTotalPrice = $.trim($("#parcelTotalPrice").text()) ? $("#parcelTotalPrice").text() : '';
        formData.floorPremium = $.trim($("#floorPremium").text()) ? $("#floorPremium").text() : '';
        formData.correctionDifference = $.trim($("#correctionDifference").text()) ? $("#correctionDifference").text() : '';
        formData.areaAndSeveralAmend = AssessCommon.percentToPoint($("#areaAndSeveralAmend").val());
        formData.landLevelContent = getLandLevelContent();


        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        } else {
            submitToServer(JSON.stringify(formData));
        }
    }



</script>

</html>

