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


                    <%@include file="/views/method/module/costApproachIndex.jsp" %>

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
        src="${pageContext.request.contextPath}/js/case/case.common.js?v=${assessVersion}"></script>
<script type="application/javascript">
    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = formParams("master");
        var formDataOther = formParams("master_other");
        formData.id = $("#master").find("input[name='id']").val();
        formData.rewardRate = AssessCommon.pointToPercent(formDataOther.rewardRate);
        formData.rewardRateId = formDataOther.rewardRateId;
        formData.circulationExpense = formDataOther.circulationExpense;
        formData.circulationExpenseRemark = formDataOther.circulationExpenseRemark;
        formData.flatExpense = formDataOther.flatExpense;
        formData.flatExpenseRemark = formDataOther.flatExpenseRemark;
        formData.machineCycle = formDataOther.machineCycle;
        formData.machineCycleRemark = formDataOther.machineCycleRemark;
        formData.calculatedInterest = AssessCommon.pointToPercent(formDataOther.calculatedInterest);
        formData.calculatedInterestRemark = formDataOther.calculatedInterestRemark;
        formData.profitMargin = AssessCommon.pointToPercent(formDataOther.profitMargin);
        formData.profitMarginRemark = formDataOther.profitMarginRemark;
        formData.incrementalBenefit = AssessCommon.pointToPercent(formDataOther.incrementalBenefit);
        formData.incrementalBenefitRemark = formDataOther.incrementalBenefitRemark;
        formData.plotRatioElementAmend = AssessCommon.percentToPoint($("#plotRatioElementAmend").val());
        formData.plotRatioAdjust = AssessCommon.pointToPercent(formDataOther.plotRatioAdjust);
        formData.plotRatioAdjustRemark = formDataOther.plotRatioAdjustRemark;
        formData.landRemainingYear = formDataOther.landRemainingYear;
        formData.landRemainingYearRemark = formDataOther.landRemainingYearRemark;


        formData.parcelUnit = parseFloat($("#parcelUnit").text());
        formData.landUsePrice = parseFloat($("#landUseUnit").text());
        formData.yearFixed = parseFloat($("#yearFixed").text());
        formData.landCostPriceUnit = parseFloat($("#landCostPriceUnit").text());
        formData.landAcquisitionUnit = parseFloat($("#landAcquisitionUnit").text());
        formData.landProductionProfitUnit = parseFloat($("#landProductionProfitUnit").text());
        formData.landProductionInterestUnit = parseFloat($("#landProductionInterestUnit").text());
        formData.landLevelContent = getLandLevelContent();
        var costApproachTaxes = [];
        $.each($("#master").find("#tbodyContent").find("tr"), function (i, n) {
            var item = getApproachTaxesData($(n));
            costApproachTaxes.push(item);
        });
        var item = {};
        item.master = formData;
        item.costApproachTaxes = costApproachTaxes;
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(item));
        }
        else {
            submitToServer(JSON.stringify(item));
        }
    }

</script>

</html>

