<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/method/module/baseLandPriceIndex.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/case/case.common.js?v=${assessVersion}"></script>
<script type="text/javascript">

    //保存结果
    function saveResult(callback) {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = formParams("master");
        formData.rewardRate = AssessCommon.pointToPercent(formData.rewardRate);
        formData.dateAmend = $.trim($("#dateAmend").text()) ? $("#dateAmend").text() : '';
        formData.periodAmend = $.trim($("#periodAmend").text()) ? $("#periodAmend").text() : '';
        formData.volumeFractionAmend = $.trim($("#volumeFractionAmend").text()) ? $("#volumeFractionAmend").text() : '';
        formData.parcelPrice = $.trim($("#parcelPrice").text()) ? $("#parcelPrice").text() : '';
        formData.parcelBhouPrice = $.trim($("#parcelBhouPrice").text()) ? $("#parcelBhouPrice").text() : '';
        formData.parcelTotalPrice = $.trim($("#parcelTotalPrice").text()) ? $("#parcelTotalPrice").text() : '';
        formData.floorPremium = $.trim($("#floorPremium").text()) ? $("#floorPremium").text() : '';
        formData.correctionDifference = $.trim($("#correctionDifference").text()) ? $("#correctionDifference").text() : '';
        formData.areaAndSeveralAmend = AssessCommon.percentToPoint($("#areaAndSeveralAmend").val());
        formData.landLevelContent = getLandLevelContent();
        if (!$.isNumeric(formData.volumeFractionAmend)) {
            formData.volumeFractionAmend = null;
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/baseLandPrice/saveResult',
            data: {formData: JSON.stringify(formData)},
            method:"post" ,
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功','保存成功');
                    if (callback) {
                        callback(result.data.id, result.data.floorPremium)
                    }
                } else {
                    AlertError(result.errmsg);
                }
            }
        })
    }


</script>
</html>

