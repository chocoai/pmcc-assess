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
            <%@include file="/views/method/module/costApproachIndex.jsp" %>
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
        $.ajax({
            url: '${pageContext.request.contextPath}/costApproach/saveResult',
            data: {formData: JSON.stringify(item)},
            method:"post" ,
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功','保存成功');
                    if (callback) {
                        callback(result.data.id, result.data.parcelUnit)
                    }
                } else {
                    AlertError(result.errmsg);
                }
            }
        })
    }


</script>
</html>

