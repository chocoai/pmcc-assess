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
                    <form class="form-horizontal" id="master">
                        <div class="x_panel">
                            <div class="x_title collapse-link">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                </ul>
                                <h3>年平均产值调查</h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            农用地总面积(亩)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.farmlandArea}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            耕地面积(亩)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.ploughArea}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            人口数(人)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.populationNumber}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地外设定</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3" >
                                            <label class="form-control">${master.parcelSettingOuterName}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地内设定</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control">${master.parcelSettingInnerName}</label>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-bordered" id="tb_research_list">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>

                        <div class="x_panel">
                            <div class="x_title collapse-link">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                </ul>
                                <h3>土地取得费及相关税费</h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <table class="table table-bordered" id="tb_taxes_list">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>

                        <div class="x_panel">
                            <div class="x_title collapse-link">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                </ul>
                                <h3>因素条件说明及修正系数</h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <form class="form-horizontal" id="areaAndSeveralAmendForm">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                宗地个别因素修正
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.plotRatioElementAmend}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <table class="table table-striped table-bordered" style="display: none">
                                                <thead>
                                                <tr>
                                                    <th width="10%">土地级别类别</th>
                                                    <th width="10%">土地级别类型</th>
                                                    <th width="10%">土地级别等级</th>
                                                    <th width="20%">说明</th>
                                                    <th width="10%">分值</th>
                                                </tr>
                                                </thead>
                                                <tbody id="landLevelTabContent">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="x_panel">
                            <div class="x_title collapse-link">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                </ul>
                                <h3>土地开发因素</h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <form class="form-horizontal" id="master_other">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                宗地外六通费用(元/㎡)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.circulationExpense}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.circulationExpenseRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                场平费用(元/㎡)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.flatExpense}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.flatExpenseRemark}</label>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                计息周期
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.machineCycle}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.machineCycleRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                计息利率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.calculatedInterest}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.calculatedInterestRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                开发利润率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.profitMargin}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.profitMarginRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                土地增值率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.incrementalBenefit}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.incrementalBenefitRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <%--<div class="form-group">--%>
                                        <%--<div class="x-valid">--%>
                                            <%--<label class="col-sm-1 control-label">--%>
                                                <%--宗地个别因素修正--%>
                                            <%--</label>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label class="form-control">${master.plotRatioElementAmend}</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="x-valid">--%>
                                            <%--<label class="col-sm-1 control-label">--%>
                                                <%--说明--%>
                                            <%--</label>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label class="form-control">${master.plotRatioElementAmendRemark}</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                容积率调整
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.plotRatioAdjust}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.plotRatioAdjustRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                剩余年限
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.landRemainingYear}</label>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control">${master.landRemainingYearRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label">
                                            还原利率
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.rewardRate}</label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="x_panel">
                            <div class="x_title collapse-link">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                </ul>
                                <h3>测算结果</h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="form-group">
                                    <div class="col-md-12 col-sm-12">
                                        <table class="table table-bordered">
                                            <tbody>
                                            <tr>
                                                <td> 耕地比例</td>
                                                <td id="ploughArearatio"></td>
                                                <td> 非耕地比例</td>
                                                <td id="noPloughArearatio">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> 土地取得费及相关税费(元/亩)</td>
                                                <td id="landAcquisitionBhou"></td>
                                                <td> 土地开发费(元/亩)</td>
                                                <td id="landProductionBhou">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> 土地开发利息(元/亩)</td>
                                                <td id="landProductionInterestBhou">
                                                </td>
                                                <td> 土地开发利润(元/亩)</td>
                                                <td id="landProductionProfitBhou">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> 土地取得成本（元/亩）</td>
                                                <td id="landCostPriceBhou">
                                                </td>
                                                <td> 无限年期土地使用权价格(元/亩)</td>
                                                <td id="landUseBhou">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> 年期修正系数</td>
                                                <td id="yearFixed">
                                                </td>
                                                <td> 年期修正（元/亩）</td>
                                                <td id="priceCorrectionBhou">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> 宗地个别因素修正(元/亩)</td>
                                                <td id="plotRatioElementAmendBhou">
                                                </td>
                                                <td> 容积率修正(元/亩)</td>
                                                <td id="plotRatioAdjustBhou">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> 委估宗地价格(元/亩)</td>
                                                <td id="parcelBhou">
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
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
<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td class="table-cell">
            {landLevelTypeName}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            {landLevelCategoryName}
        </td>
        <td>
            {gradeName}
        </td>
        <td>
            <label name="reamark" class="form-control">{reamark}</label>
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control">{landFactorTotalScore}</label>
        </td>
    </tr>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        getLandAcquisitionBhou("${master.id}");
        getPloughArearatio();
        //因素条件说明及修正系数
        landLevelLoadHtml()
    });

    //耕地比例
    function getPloughArearatio() {
        var farmlandArea = parseFloat("${master.farmlandArea}");
        var ploughArea = parseFloat("${master.ploughArea}");
        if (farmlandArea && ploughArea) {
            var ploughArearatio = getSomePlaces(ploughArea / farmlandArea, 4);
            var noPloughArearatio = 1 - ploughArearatio;
            $("#ploughArearatio").text(AssessCommon.pointToPercent(ploughArearatio));
            $("#noPloughArearatio").text(AssessCommon.pointToPercent(noPloughArearatio));
        }
    }

    //土地取得费及相关税费(元/亩)
    function getLandAcquisitionBhou(masterId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/costApproach/getLandAcquisitionBhou",
            type: "get",
            dataType: "json",
            data: {masterId: masterId},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#landAcquisitionBhou").text(result.data);
                        //计算土地开发费
                        getLandProductionUnit("${master.circulationExpense}", "${master.flatExpense}");
                    }
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }


    //计算土地开发费
    function getLandProductionUnit(circulationExpenseVal, flatExpenseVal) {
        var circulationExpense = parseFloat(circulationExpenseVal);
        var flatExpense = parseFloat(flatExpenseVal);
        if (circulationExpense && flatExpense) {
            circulationExpense = parseFloat(circulationExpenseVal);
            flatExpense = parseFloat(flatExpenseVal);
        }
        $("#landProductionBhou").text(getSomePlaces((circulationExpense + flatExpense) * AssessCommon.BHOU, 2));
        //计算土地开发利息
        getLandProductionInterest("${master.machineCycle}", "${master.calculatedInterest}");
    }

    //计算土地开发利息
    function getLandProductionInterest(machineCycleVal, calculatedInterestVal) {
        //计算周期F22
        var machineCycle = parseFloat(machineCycleVal);
        //计算利息G22
        var calculatedInterest = parseFloat(AssessCommon.percentToPoint(calculatedInterestVal));
        //土地取得费E4
        var landAcquisitionUnit = getSomePlaces(parseFloat($("#landAcquisitionBhou").text()) / AssessCommon.BHOU, 2);
        //土地开发费E17
        var landProductionUnit = parseFloat($("#landProductionBhou").text()) / AssessCommon.BHOU;
        //(E4*((1+G22)^F22-1)+E17*((1+G22)^(F22/2)-1),2)
        if (machineCycle && calculatedInterest && landAcquisitionUnit && landProductionUnit) {
            var temp = Math.pow((1 + calculatedInterest), machineCycle) - 1;
            var temp2 = Math.pow((1 + calculatedInterest), machineCycle / 2) - 1;
            var landProductionInterestUnit = getSomePlaces(landAcquisitionUnit * temp + landProductionUnit * temp2, 2);
            $("#landProductionInterestBhou").text(getSomePlaces(landProductionInterestUnit * AssessCommon.BHOU, 2));
        }
        //土地开发利润
        getLandProductionProfit("${master.profitMargin}");
    }

    //土地开发利润
    function getLandProductionProfit(profitMarginVal) {
        //土地取得费E4
        var landAcquisitionUnit = getSomePlaces(parseFloat($("#landAcquisitionBhou").text()) / AssessCommon.BHOU, 2);
        //土地开发费H17
        var landProductionUnit = getSomePlaces(parseFloat($("#landProductionBhou").text()) / AssessCommon.BHOU, 2);
        //开发利润率F24
        var profitMargin = parseFloat(AssessCommon.percentToPoint(profitMarginVal));

        if (landAcquisitionUnit && landProductionUnit && profitMargin) {
            //(E4+E17)*F24
            var landProductionProfitUnit = getSomePlaces((landAcquisitionUnit + landProductionUnit) * profitMargin, 2);
            $("#landProductionProfitBhou").text(getSomePlaces(landProductionProfitUnit * AssessCommon.BHOU, 2));

            var landProductionProfitBhou = parseFloat($("#landProductionProfitBhou").text());
            var landAcquisitionBhou = parseFloat($("#landAcquisitionBhou").text());
            var landProductionBhou = parseFloat($("#landProductionBhou").text());
            //土地开发利息E21
            var landProductionInterestBhou = parseFloat($("#landProductionInterestBhou").text());
            //土地成本价格
            var landCostPriceBhou = getSomePlaces(landAcquisitionBhou + landProductionBhou + landProductionProfitBhou + landProductionInterestBhou, 2);
            $("#landCostPriceBhou").text(landCostPriceBhou);
            //无限年期土地使用权价格
            getLandAppreciation("${master.incrementalBenefit}");
        }
    }

    //无限年期土地使用权价格
    function getLandAppreciation(incrementalBenefitVal) {
        //土地成本价格D25
        var landCostPriceBhou = parseFloat($("#landCostPriceBhou").text());
        //土地增值率G27
        var incrementalBenefit = getSomePlaces(AssessCommon.percentToPoint($("#incrementalBenefit").val()), 2);
        if (incrementalBenefitVal) {
            incrementalBenefit = getSomePlaces(AssessCommon.percentToPoint(incrementalBenefitVal), 2);
        }
        $("#landUseBhou").text(getSomePlaces(landCostPriceBhou / (1 - incrementalBenefit), 2));

        //年期修正、价格修正与确定、单价、估价对象楼面地价、委估宗地总价
        getYearFixed("${master.rewardRate}");
    }

    //年期修正
    function getYearFixed(rewardRatePercent) {
        //G33
        var rewardRate = AssessCommon.percentToPoint(rewardRatePercent)
        //E29
        var landRemainingYear = parseFloat("${master.landRemainingYear}");
        if (rewardRate && landRemainingYear) {
            var temp = Math.pow(1 + parseFloat(rewardRate), landRemainingYear);
            //年期修正H33
            var yearFixed = getSomePlaces(1 - 1 / temp, 4);
            $("#yearFixed").text(yearFixed);
            //无限年期土地使用权价格H29
            var landUseBhou = parseFloat($("#landUseBhou").text());
            if (landUseBhou) {
                //价格修正与确定
                var priceCorrectionBhou = yearFixed * landUseBhou;
                $("#priceCorrectionBhou").text(getSomePlaces(priceCorrectionBhou / 10000, 2));

                //宗地个别因素修正
                var plotRatioElementAmend = parseFloat("${master.plotRatioElementAmend}");
                var plotRatioElementAmendBhou = getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend), 2);
                $("#plotRatioElementAmendBhou").text(getSomePlaces(plotRatioElementAmendBhou / 10000, 2));

                //容积率修正
                var plotRatioAdjust = parseFloat(AssessCommon.percentToPoint("${master.plotRatioAdjust}"));
                var plotRatioAdjustBhou = getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend) * (1 + plotRatioAdjust), 2);
                $("#plotRatioAdjustBhou").text(getSomePlaces(plotRatioAdjustBhou / 10000, 2));

                //委估宗地价格
                $("#parcelBhou").text(getSomePlaces(plotRatioAdjustBhou / 10000, 2));
            }
        }
    }


    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //因素条件说明及修正系数
    function landLevelLoadHtml() {
        var jsonContent = JSON.parse('${master.landLevelContent}');
        var data = estateCommon.landLevelFilter(jsonContent);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();
        target.parent().show();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, item.achievement);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.category);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{gradeName}/g, item.gradeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    target.append(landLevelBodyHtml);
                }
            });

            if (indexM == 0) {
                target.find("tr").first().find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i != 0) {
                        $(n).find("td").first().remove();
                    }
                });
            }
            if (indexM == 1) {
                var length = data[0].length;
                target.find("tr").eq(length).find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i > length) {
                        $(n).find("td").first().remove();
                    }
                });
            }
        });
    };
</script>
<%--年平均产值--%>
<script type="text/javascript">
    $(function () {
        research.prototype.loadDataList(${master.id});
    });
    var research = function () {

    };
    research.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_research_list";
            return data;
        },
        loadDataList: function (masterId) {
            var cols = [];
            cols.push({field: 'yearDescribe', title: '年份描述'});
            cols.push({field: 'averageProduction', title: '平均产值(元)'});
            $("#" + research.prototype.config().table).bootstrapTable('destroy');
            TableInit(research.prototype.config().table, "${pageContext.request.contextPath}/costApproach/getMdCostApproachItemList", cols, {
                masterId: masterId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    }
</script>
<%--税率配置--%>
<script type="text/javascript">
    $(function () {
        taxes.prototype.loadDataList(${master.id});

    });
    var taxes = function () {

    };
    taxes.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_taxes_list";
            return data;
        },
        loadDataList: function (masterId) {
            var cols = [];
            cols.push({field: 'typeName', title: '类型'});
            cols.push({field: 'standard', title: '耕地标准/非耕地标准'});
            cols.push({field: 'price', title: '价格(元/亩)'});
            $("#" + taxes.prototype.config().table).bootstrapTable('destroy');
            TableInit(taxes.prototype.config().table, "${pageContext.request.contextPath}/costApproach/getMdCostApproachTaxesList", cols, {
                masterId: masterId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },


    }
</script>
</body>
</html>

