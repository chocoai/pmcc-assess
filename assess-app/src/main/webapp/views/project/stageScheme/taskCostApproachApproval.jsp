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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>成本逼近法测算表</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-12 col-sm-12">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th colspan="2" rowspan="2" style="width:30%">收费项目</th>
                                        <th colspan="2" rowspan="2" style="width:40%">收费依据</th>
                                        <th colspan="2" style="width:30%">收费标准</th>
                                    </tr>
                                    <tr>
                                        <th>元/亩</th>
                                        <th>元/㎡</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td colspan="4">一、土地成本价格</td>
                                        <td id="landCostPriceBhou"></td>
                                        <td id="landCostPriceUnit"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">（一）土地取得费及相关税费</td>
                                        <td id="landAcquisitionBhou"></td>
                                        <td id="landAcquisitionUnit"></td>
                                    </tr>
                                    <c:forEach items="${vosList}" var="item" varStatus="i">
                                        <tr>
                                            <td colspan="2">${item.categoryName}</td>
                                            <td colspan="2">${item.symbol}</td>
                                            <td>${item.bhouPrice}</td>
                                            <td>${item.amountMoney}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td>不可预见费</td>
                                        <td>${master.imprevisionCost}</td>
                                        <td>计算基数</td>
                                        <td id="computationalBase"></td>
                                        <td id="tempBhouPrice"></td>
                                        <td id="tempUnitPrice"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">小计</td>
                                        <td id="subtotalBhou"></td>
                                        <td id="subtotalUnit"></td>
                                    </tr>
                                    <tr>
                                        <td>代征地比例</td>
                                        <td id="confiscateLandRatio">${confiscateLandRatio}</td>
                                        <td colspan="2"></td>
                                        <td></td>
                                        <td id="confiscateLandUnit"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">（二）土地开发费</td>
                                        <td id="landProductionBhou"></td>
                                        <td id="landProductionUnit"></td>
                                    </tr>
                                    <tr>
                                        <td>宗地外</td>
                                        <td></td>
                                        <td>宗地外流通费用</td>
                                        <td></td>
                                        <td></td>
                                        <td>${master.circulationExpense}</td>
                                    </tr>
                                    <tr>
                                        <td>宗地内</td>
                                        <td></td>
                                        <td>场平</td>
                                        <td></td>
                                        <td></td>
                                        <td>${master.flatExpense}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">（三）土地开发利息</td>
                                        <td id="landProductionInterestBhou"></td>
                                        <td id="landProductionInterestUnit"></td>
                                    </tr>
                                    <tr>
                                        <td>计息周期</td>
                                        <td>${master.machineCycle}</td>
                                        <td>计算利息</td>
                                        <td>${master.calculatedInterest}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">（四）土地开发利润</td>
                                        <td id="landProductionProfitBhou"></td>
                                        <td id="landProductionProfitUnit"></td>
                                    </tr>
                                    <tr>
                                        <td>计息基数</td>
                                        <td id="profitBase"></td>
                                        <td>利润率</td>
                                        <td>${master.profitMargin}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">二、土地增值</td>
                                        <td>增值收益率</td>
                                        <td>${master.incrementalBenefit}</td>
                                        <td id="landAppreciationText">v</td>
                                        <td id="landAppreciationResult"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">三、无限年期土地使用权价格</td>
                                        <td id="landUseBhou"></td>
                                        <td id="landUseUnit"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">四、价格修正与确定</td>
                                        <td id="priceCorrectionBhou"></td>
                                        <td id="priceCorrectionUnit"></td>
                                    </tr>
                                    <tr>
                                        <td>（一）年期修正</td>
                                        <td>土地剩余年期</td>
                                        <td>${landRemainingYear}</td>
                                        <td>还原利率</td>
                                        <td>${master.rewardRate}</td>
                                        <td id="yearFixed"></td>
                                    </tr>
                                    <tr>
                                        <td>（二）宗地个别因素修正</td>
                                        <td id="parcelIndividualFactor">5%</td>
                                        <td colspan="4"></td>
                                    </tr>
                                    <tr>
                                        <td>（三）容积率修正</td>
                                        <td>${volumeFractionAmend}</td>
                                        <td colspan="4"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">五、委估宗地价格</td>
                                        <td>单价（元/亩）</td>
                                        <td></td>
                                        <td>单价（元/㎡）</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>六、委估宗地面积（㎡）</td>
                                        <td>${floorArea}</td>
                                        <td>容积率</td>
                                        <td>${volumetricRate}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>七、委估宗地总价（万元）</td>
                                        <td></td>
                                        <td>估价对象楼面地价（元/㎡）</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
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
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        //计算基数
        var totalPrice = 0;
        <c:forEach items="${vosList}" var="item" varStatus="i">
        totalPrice += ${item.bhouPrice};
        </c:forEach>
        $("#computationalBase").text(getSomePlaces(totalPrice, 2));

        getTempPrice("${master.imprevisionCost}");

        getLandProductionUnit("${master.circulationExpense}", "${master.flatExpense}");

        getLandProductionInterest("${master.machineCycle}", "${master.calculatedInterest}");

        getProfitBase();

        getLandAppreciation("${master.incrementalBenefit}");

        getYearFixed("${master.rewardRate}");
    });

    function getTempPrice(imprevisionCostVal) {
        //不可预见费
        var imprevisionCost = parseFloat(AssessCommon.percentToPoint(imprevisionCostVal));
        //计算基数
        var computationalBase = parseFloat($("#computationalBase").text());
        var tempBhouPrice = imprevisionCost * computationalBase;
        $("#tempBhouPrice").text(getSomePlaces(tempBhouPrice, 2));
        $("#tempUnitPrice").text(getSomePlaces(tempBhouPrice / 666.67, 2));
        //土地取得费亩价
        var landAcquisitionBhou = (computationalBase + tempBhouPrice) / (1 - imprevisionCost);
        $("#landAcquisitionBhou").text(getSomePlaces(landAcquisitionBhou, 2));
        $("#landAcquisitionUnit").text(getSomePlaces(landAcquisitionBhou / 666.67, 2));
        //小计
        $("#subtotalBhou").text(getSomePlaces(computationalBase + tempBhouPrice, 2));
        $("#subtotalUnit").text(getSomePlaces((computationalBase + tempBhouPrice) / 666.67, 2));

        //代征地比例(待完成)
        var confiscateLandRatio = parseFloat(AssessCommon.percentToPoint($("#confiscateLandRatio").text()));
        $("#confiscateLandUnit").text(getSomePlaces((computationalBase + tempBhouPrice) / (1 - confiscateLandRatio), 2));

    }


    //计算土地开发费
    function getLandProductionUnit(circulationExpenseVal, flatExpenseVal) {
        var circulationExpense = parseFloat(circulationExpenseVal);
        var flatExpense = parseFloat(flatExpenseVal);
        if (circulationExpense && flatExpense) {
            $("#landProductionUnit").text(getSomePlaces(circulationExpense + flatExpense, 2));
            $("#landProductionBhou").text(getSomePlaces((circulationExpense + flatExpense) * 666.67, 2));
        }
    }

    //计算土地开发利息
    function getLandProductionInterest(machineCycleVal, calculatedInterestVal) {
        //计算周期D23
        var machineCycle = parseFloat(machineCycleVal);
        //计算利息F23
        var calculatedInterest = parseFloat(AssessCommon.percentToPoint(calculatedInterestVal));
        //土地取得费H6
        var landAcquisitionUnit = parseFloat($("#landAcquisitionUnit").text());
        //土地开发费H19
        var landProductionUnit = parseFloat($("#landProductionUnit").text());

        if (machineCycle && calculatedInterest && landAcquisitionUnit && landProductionUnit) {
            var temp = Math.pow((1 + calculatedInterest), machineCycle) - 1;
            var temp2 = Math.pow((1 + calculatedInterest), machineCycle / 2) - 1;
            var landProductionInterestUnit = landAcquisitionUnit * temp + landProductionUnit * temp2;
            $("#landProductionInterestUnit").text(getSomePlaces(landProductionInterestUnit, 2));
            $("#landProductionInterestBhou").text(getSomePlaces(landProductionInterestUnit * 666.67, 2));
        }
    }


    //利润基数
    function getProfitBase() {
        //土地取得费H6
        var landAcquisitionUnit = parseFloat($("#landAcquisitionUnit").text());
        //土地开发费H19
        var landProductionUnit = parseFloat($("#landProductionUnit").text());
        if (landAcquisitionUnit && landProductionUnit) {
            $("#profitBase").text(getSomePlaces(landAcquisitionUnit + landProductionUnit, 2));
        }
        getLandProductionProfit("${master.profitMargin}");
    }

    //土地开发利润
    function getLandProductionProfit(profitMarginVal) {
        //计算基数D25
        var profitBase = parseFloat($("#profitBase").text());
        //利润率G25
        var profitMargin = parseFloat(AssessCommon.percentToPoint(profitMarginVal));
        if (profitBase && profitMargin) {
            var landProductionProfitUnit = profitBase * profitMargin;
            $("#landProductionProfitUnit").text(getSomePlaces(landProductionProfitUnit, 2));
            $("#landProductionProfitBhou").text(getSomePlaces(landProductionProfitUnit * 666.67, 2));
            //土地取得费H6
            var landAcquisitionUnit = parseFloat($("#landAcquisitionUnit").text());
            //土地开发费H19
            var landProductionUnit = parseFloat($("#landProductionUnit").text());
            //土地开发利息H22
            var landProductionInterestUnit = parseFloat($("#landProductionInterestUnit").text());
            //土地成本价格
            var landCostPriceUnit = landAcquisitionUnit + landProductionUnit + landProductionInterestUnit + landProductionProfitUnit;
            $("#landCostPriceUnit").text(getSomePlaces(landCostPriceUnit, 2));
            $("#landCostPriceBhou").text(getSomePlaces(landCostPriceUnit * 666.67, 2));
        }


    }

    function getLandAppreciation(incrementalBenefitVal) {
        //增值收益率F26
        var incrementalBenefit = getSomePlaces(AssessCommon.percentToPoint(incrementalBenefitVal), 2);
        var landAppreciationText = $("#landAppreciationText").text();
        $("#landAppreciationResult").text(incrementalBenefit + landAppreciationText);
        //土地成本单格H5
        var landCostPriceUnit = parseFloat($("#landCostPriceUnit").text());
        //土地成本亩格G5
        var landCostPriceBhou = parseFloat($("#landCostPriceBhou").text());
        if (landCostPriceUnit) {
            $("#landUseUnit").text(getSomePlaces(landCostPriceUnit / (1 - incrementalBenefit), 2));
            $("#landUseBhou").text(getSomePlaces(landCostPriceBhou / (1 - incrementalBenefit), 2));
        }
    }

    //年期修正
    function getYearFixed(rewardRateVal) {
        //G29
        var rewardRate = AssessCommon.percentToPoint("${master.rewardRate}");
        if (rewardRateVal) {
            rewardRate = AssessCommon.percentToPoint(rewardRateVal);
        }
        //E29
        var landRemainingYear = "${landRemainingYear}";
        if (rewardRate) {
            var temp = Math.pow(1 + parseFloat(rewardRate), landRemainingYear);
            //H29
            var yearFixed = 1 - 1 / temp;
            $("#yearFixed").text(getSomePlaces(yearFixed, 2))
            //无限年期土地使用权价格H27
            var landUseUnit = parseFloat($("#landUseUnit").text());
            //宗地个别因素修正H30
            var parcelIndividualFactor = AssessCommon.percentToPoint($("#parcelIndividualFactor").text());
            if (landUseUnit && parcelIndividualFactor) {
                var priceCorrectionUnit = yearFixed * landUseUnit * (1 + parcelIndividualFactor);
                $("#priceCorrectionUnit").text(getSomePlaces(priceCorrectionUnit, 2));
                $("#priceCorrectionBhou").text(getSomePlaces(priceCorrectionUnit * 666.67, 2));
            }
        }
    }

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }
</script>
</body>
</html>

