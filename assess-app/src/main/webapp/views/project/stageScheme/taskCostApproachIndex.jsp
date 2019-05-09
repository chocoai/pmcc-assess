<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>成本逼近法测算表</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="master">
                        <input type="hidden" name="id" value="${master.id}">
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
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="imprevisionCost" class="form-control x-percent"
                                                       id="imprevisionCost" required onblur="getTempPrice()"
                                                       placeholder="请输入数字" value="${master.imprevisionCost}">
                                            </div>
                                        </td>
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
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="circulationExpense" class="form-control"
                                                       id="circulationExpense" required
                                                       onblur="getLandProductionUnit()" placeholder="请输入数字"
                                                       data-rule-number="true" value="${master.circulationExpense}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>宗地内</td>
                                        <td></td>
                                        <td>场平</td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="flatExpense" class="form-control"
                                                       id="flatExpense" required
                                                       onblur="getLandProductionUnit()" placeholder="请输入数字"
                                                       data-rule-number="true" value="${master.flatExpense}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">（三）土地开发利息</td>
                                        <td id="landProductionInterestBhou"></td>
                                        <td id="landProductionInterestUnit"></td>
                                    </tr>
                                    <tr>
                                        <td>计息周期</td>
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="machineCycle" class="form-control"
                                                       id="machineCycle" required
                                                       onblur="getLandProductionInterest()" placeholder="请输入数字"
                                                       data-rule-number="true" value="${master.machineCycle}">
                                            </div>
                                        </td>
                                        <td>计算利息</td>
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="calculatedInterest"
                                                       class="form-control x-percent"
                                                       id="calculatedInterest" required
                                                       onblur="getLandProductionInterest()" placeholder="请输入数字"
                                                       value="${master.calculatedInterest}">
                                            </div>
                                        </td>
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
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="profitMargin"
                                                       class="form-control x-percent"
                                                       id="profitMargin" required
                                                       onblur="getLandProductionProfit()" placeholder="请输入数字"
                                                       value="${master.profitMargin}">
                                            </div>
                                        </td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">二、土地增值</td>
                                        <td>增值收益率</td>
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="incrementalBenefit"
                                                       class="form-control x-percent"
                                                       id="incrementalBenefit" required
                                                       onblur="getLandAppreciation()" placeholder="请输入数字"
                                                       value="${master.incrementalBenefit}">
                                            </div>
                                        </td>
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
                                        <td>
                                            <div class="input-group">
                                                <input type="text" required class="form-control x-percent"
                                                       id="rewardRate"
                                                       name="rewardRate" placeholder="报酬率" readonly="readonly"
                                                       value="${master.rewardRate}">
                                                <span class="input-group-btn">
                                                <input type="hidden" name="rewardRateId" value="${master.rewardRateId}">
                                                <input type="button" class="btn btn-primary" value="报酬率测算"
                                                       onclick="getRewardRate(this);"/>
                                                </span>
                                            </div>
                                        </td>
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

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="application/javascript">
    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        ;
        var formData = {};
        formData.id = $('#master').find('[name=id]').val();
        formData.rewardRate = $('#master').find('[name=rewardRate]').val();
        formData.rewardRateId = $('#master').find('[name=rewardRateId]').val();
        formData.imprevisionCost = $('#master').find('[name=imprevisionCost]').val();
        formData.circulationExpense = $('#master').find('[name=circulationExpense]').val();
        formData.flatExpense = $('#master').find('[name=flatExpense]').val();
        formData.machineCycle = $('#master').find('[name=machineCycle]').val();
        formData.calculatedInterest = $('#master').find('[name=calculatedInterest]').val();
        formData.profitMargin = $('#master').find('[name=profitMargin]').val();
        formData.incrementalBenefit = $('#master').find('[name=incrementalBenefit]').val();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
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

        if ("${master.rewardRate}") {
            getYearFixed("${master.rewardRate}");
        }
    })

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    function getTempPrice(imprevisionCostVal) {
        //不可预见费
        var imprevisionCost = parseFloat(AssessCommon.percentToPoint($("#imprevisionCost").val()));
        if (imprevisionCostVal) {
            imprevisionCost = parseFloat(AssessCommon.percentToPoint(imprevisionCostVal));
        }
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

        //代征地比例
        var confiscateLandRatio = parseFloat(AssessCommon.percentToPoint($("#confiscateLandRatio").text()));
        $("#confiscateLandUnit").text(getSomePlaces((computationalBase + tempBhouPrice) / (1 - confiscateLandRatio), 2));

        //利润基数
        getProfitBase();
        //土地开发利息
        getLandProductionInterest();
        //无限年期土地使用权价格
        getLandAppreciation();

    }

    //计算土地开发费
    function getLandProductionUnit(circulationExpenseVal, flatExpenseVal) {
        var circulationExpense = parseFloat($("#circulationExpense").val());
        var flatExpense = parseFloat($("#flatExpense").val());
        if (circulationExpenseVal && flatExpenseVal) {
            circulationExpense = parseFloat(circulationExpenseVal);
            flatExpense = parseFloat(flatExpenseVal);
        }
        if (circulationExpense && flatExpense) {
            $("#landProductionUnit").text(getSomePlaces(circulationExpense + flatExpense, 2));
            $("#landProductionBhou").text(getSomePlaces((circulationExpense + flatExpense) * 666.67, 2));
        }

        //利润基数
        getProfitBase();
    }

    //计算土地开发利息
    function getLandProductionInterest(machineCycleVal, calculatedInterestVal) {
        //计算周期D23
        var machineCycle = parseFloat($("#machineCycle").val());
        //计算利息F23
        var calculatedInterest = parseFloat(AssessCommon.percentToPoint($("#calculatedInterest").val()));
        //土地取得费H6
        var landAcquisitionUnit = parseFloat($("#landAcquisitionUnit").text());
        //土地开发费H19
        var landProductionUnit = parseFloat($("#landProductionUnit").text());
        if (machineCycleVal && calculatedInterestVal) {
            machineCycle = parseFloat(machineCycleVal);
            calculatedInterest = parseFloat(AssessCommon.percentToPoint(calculatedInterestVal));
        }
        //(H6*((1+F23)^D23-1)+H19*((1+F23)^(D23/2)-1)),2)
        if (machineCycle && calculatedInterest && landAcquisitionUnit && landProductionUnit) {
            var temp = Math.pow((1 + calculatedInterest), machineCycle) - 1;
            var temp2 = Math.pow((1 + calculatedInterest), machineCycle / 2) - 1;
            var landProductionInterestUnit = landAcquisitionUnit * temp + landProductionUnit * temp2;
            $("#landProductionInterestUnit").text(getSomePlaces(landProductionInterestUnit, 2));
            $("#landProductionInterestBhou").text(getSomePlaces(landProductionInterestUnit * 666.67, 2));
        }
        getLandProductionProfit();
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
        var profitMargin = parseFloat(AssessCommon.percentToPoint($("#profitMargin").val()));
        if (profitMarginVal) {
            profitMargin = parseFloat(AssessCommon.percentToPoint(profitMarginVal));
        }
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

    //无限年期土地使用权价格
    function getLandAppreciation(incrementalBenefitVal) {
        //增值收益率F26
        var incrementalBenefit = getSomePlaces(AssessCommon.percentToPoint($("#incrementalBenefit").val()), 2);
        var landAppreciationText = $("#landAppreciationText").text();
        if (incrementalBenefitVal) {
            incrementalBenefit = getSomePlaces(AssessCommon.percentToPoint(incrementalBenefitVal), 2);
        }
        $("#landAppreciationResult").text(incrementalBenefit + landAppreciationText);
        //土地成本单格H5
        var landCostPriceUnit = parseFloat($("#landCostPriceUnit").text());
        //土地成本亩格G5
        var landCostPriceBhou = parseFloat($("#landCostPriceBhou").text());
        if (landCostPriceUnit) {
            $("#landUseUnit").text(getSomePlaces(landCostPriceUnit / (1 - incrementalBenefit), 2));
            $("#landUseBhou").text(getSomePlaces(landCostPriceBhou / (1 - incrementalBenefit), 2));
        }
        //年期修正
        getYearFixed();
    }

    //获取报酬率
    function getRewardRate(_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                var element = group.find(':text');
                element.val(data.resultValue);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue));

                getYearFixed();
            }
        })
    }

    //年期修正
    function getYearFixed(rewardRatePercent) {
        //G29
        var rewardRate = AssessCommon.percentToPoint($("#rewardRate").val());
        if (rewardRatePercent) {
            rewardRate = AssessCommon.percentToPoint(rewardRatePercent)
        }
        //E29
        var landRemainingYear = "${landRemainingYear}";
        if (rewardRate) {
            var temp = Math.pow(1 + parseFloat(rewardRate), landRemainingYear);
            //H29
            var yearFixed = 1 - 1 / temp;
            $("#yearFixed").text(getSomePlaces(yearFixed, 2));
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
</script>

</html>

