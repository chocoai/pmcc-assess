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

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${judgeObject.name}
                                        <small>(${judgeObject.evaluationArea}㎡)</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="master">
                                    <div class="col-md-12">
                                        <div class="card full-height">
                                            <div class="card-header collapse-link">
                                                <div class="card-head-row">
                                                    <div class="card-title">
                                                        年平均产值调查
                                                    </div>
                                                    <div class="card-tools">
                                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                                class="fa fa-angle-down"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">
                                                            农用地总面积(亩)
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${master.farmlandArea}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">
                                                            耕地面积(亩)
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${master.ploughArea}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">
                                                            人口数(人)
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${master.populationNumber}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地外设定</label>
                                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                            <label class="form-control input-full">${master.parcelSettingOuterName}</label>
                                                        </div>
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地内设定</label>
                                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                                            <label class="form-control input-full">${master.parcelSettingInnerName}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <table class="table table-bordered" id="tb_research_list">
                                                            <!-- cerare document add ajax data-->
                                                        </table>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="card full-height">
                                            <div class="card-header collapse-link">
                                                <div class="card-head-row">
                                                    <div class="card-title">
                                                        土地取得费及相关税费
                                                    </div>
                                                    <div class="card-tools">
                                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                                class="fa fa-angle-down"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <form class="form-horizontal" id="approachTaxesForm">
                                                    <table class="table" id="tb_taxesList">
                                                        <thead>
                                                        <tr>
                                                            <th>类型</th>
                                                            <th>耕地标准</th>
                                                            <th>非耕地标准</th>
                                                            <th>价格(元/亩)</th>
                                                            <th>备注</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="tbodyContent">
                                                        <c:forEach items="${taxesVos}" var="approachTaxe" varStatus="s">
                                                            <tr id="content${approachTaxe.id}">


                                                            </tr>
                                                            <script type="text/javascript">
                                                                $(function () {
                                                                    var html = uploadTaxeHtml("${approachTaxe.id}", "${approachTaxe.typeKey}", "${approachTaxe.typeName}", "${approachTaxe.standardFirst}", "${approachTaxe.standardSecond}", "${approachTaxe.price}", "${approachTaxe.remark}");
                                                                    $("#content${approachTaxe.id}").append(html);
                                                                })
                                                            </script>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="card full-height">
                                            <div class="card-header collapse-link">
                                                <div class="card-head-row">
                                                    <div class="card-title">
                                                        因素条件说明及修正系数
                                                    </div>
                                                    <div class="card-tools">
                                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                                class="fa fa-angle-down"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <form class="form-horizontal" id="areaAndSeveralAmendForm">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">区域及个别修正系数</label>
                                                            <div class="col-sm-3">
                                                                <div class="input-group">
                                                                    <input type="text" readonly="readonly" class="form-control"
                                                                           id="plotRatioElementAmend">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <table class="table table-bordered" id="landLevelTableList">
                                                                <thead>
                                                                <tr>
                                                                    <th  width="10%">土地级别类型</th>
                                                                    <th  width="10%">土地级别类别</th>
                                                                    <th  width="10%">土地级别等级</th>
                                                                    <th  width="20%">说明</th>
                                                                    <th  width="10%">分值</th>
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
                                    </div>

                                    <div class="col-md-12">
                                        <div class="card full-height">
                                            <div class="card-header collapse-link">
                                                <div class="card-head-row">
                                                    <div class="card-title">
                                                        土地开发因素
                                                    </div>
                                                    <div class="card-tools">
                                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                                class="fa fa-angle-down"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <form class="form-horizontal" id="master_other">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                宗地外六通费用(元/㎡)
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.circulationExpense}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.circulationExpenseRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                场平费用(元/㎡)
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.flatExpense}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.flatExpenseRemark}</label>
                                                            </div>
                                                        </div>
                                                        </div>

                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                计息周期
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.machineCycle}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.machineCycleRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                计息利率
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.calculatedInterest}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.calculatedInterestRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                开发利润率
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.profitMargin}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.profitMarginRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                土地增值率
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.incrementalBenefit}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.incrementalBenefitRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                容积率调整
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.plotRatioAdjust}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.plotRatioAdjustRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">
                                                                剩余年限
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.landRemainingYear}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                说明
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full">${master.landRemainingYearRemark}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">
                                                            还原利率
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${master.rewardRate}</label>
                                                        </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="card full-height">
                                            <div class="card-header collapse-link">
                                                <div class="card-head-row">
                                                    <div class="card-title">
                                                        测算结果
                                                    </div>
                                                    <div class="card-tools">
                                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                                class="fa fa-angle-down"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <div class="row form-group">
                                                    <div class="col-md-12">
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
                                                                <td> 土地取得费及相关税费(元/㎡)</td>
                                                                <td id="landAcquisitionUnit"></td>
                                                            </tr>
                                                            <tr>
                                                                <td> 土地开发费(元/亩)</td>
                                                                <td id="landProductionBhou">
                                                                </td>
                                                                <td> 土地开发费(元/㎡)</td>
                                                                <td id="landProductionUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 土地开发利息(元/亩)</td>
                                                                <td id="landProductionInterestBhou">
                                                                </td>
                                                                <td> 土地开发利息(元/㎡)</td>
                                                                <td id="landProductionInterestUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 土地开发利润(元/亩)</td>
                                                                <td id="landProductionProfitBhou">
                                                                </td>
                                                                <td> 土地开发利润(元/㎡)</td>
                                                                <td id="landProductionProfitUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 土地取得成本（元/亩）</td>
                                                                <td id="landCostPriceBhou">
                                                                </td>
                                                                <td> 土地取得成本（元/㎡）</td>
                                                                <td id="landCostPriceUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 无限年期土地使用权价格(元/亩)</td>
                                                                <td id="landUseBhou">
                                                                </td>
                                                                <td> 无限年期土地使用权价格(元/㎡)</td>
                                                                <td id="landUseUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 年期修正（万元/亩）</td>
                                                                <td id="priceCorrectionBhou">
                                                                </td>
                                                                <td> 年期修正（元/㎡）</td>
                                                                <td id="priceCorrectionUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 宗地个别因素修正(万元/亩)</td>
                                                                <td id="plotRatioElementAmendBhou">
                                                                </td>
                                                                <td> 宗地个别因素修正(元/㎡)</td>
                                                                <td id="plotRatioElementAmendUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 容积率修正(万元/亩)</td>
                                                                <td id="plotRatioAdjustBhou">
                                                                </td>
                                                                <td> 容积率修正(元/㎡)</td>
                                                                <td id="plotRatioAdjustUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 委估宗地价格(万元/亩)</td>
                                                                <td id="parcelBhou">
                                                                </td>
                                                                <td> 委估宗地价格(元/㎡)</td>
                                                                <td id="parcelUnit">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td> 年期修正系数</td>
                                                                <td id="yearFixed">
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td>
            {typeName}
        </td>
        <td class="table-cell">
            {landLevelTypeName}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            {gradeName}
        </td>
        <td>
            {reamark}
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control input-full">{landFactorTotalScore}</label>
        </td>
    </tr>
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/case/case.common.js?v=${assessVersion}"></script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        getLandAcquisitionBhou("${master.id}");
        getPloughArearatio();
        getLandLevelTabContent();
        $("#plotRatioElementAmend").val(AssessCommon.pointToPercent('${master.plotRatioElementAmend}'));
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
                        $("#landAcquisitionUnit").text(parseFloat(getSomePlaces($("#landAcquisitionBhou").text() / AssessCommon.BHOU, 2)));
                        //计算土地开发费
                        getLandProductionUnit("${master.circulationExpense}", "${master.flatExpense}");
                    }
                }
                else {
                    AlertError("失败","保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
        $("#landProductionUnit").text(getSomePlaces(parseFloat($("#landProductionBhou").text()) / AssessCommon.BHOU, 2));
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
            $("#landProductionInterestUnit").text(landProductionInterestUnit);
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
            $("#landProductionProfitUnit").text(getSomePlaces(landProductionProfitUnit, 2));

            var landProductionProfitBhou = parseFloat($("#landProductionProfitBhou").text());
            var landAcquisitionBhou = parseFloat($("#landAcquisitionBhou").text());
            var landProductionBhou = parseFloat($("#landProductionBhou").text());
            //土地开发利息E21
            var landProductionInterestBhou = parseFloat($("#landProductionInterestBhou").text());
            //土地成本价格
            var landCostPriceBhou = getSomePlaces(landAcquisitionBhou + landProductionBhou + landProductionProfitBhou + landProductionInterestBhou, 2);
            $("#landCostPriceBhou").text(landCostPriceBhou);
            $("#landCostPriceUnit").text(getSomePlaces(landCostPriceBhou / AssessCommon.BHOU, 2));
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
        $("#landUseUnit").text(getSomePlaces(parseFloat($("#landUseBhou").text()) / AssessCommon.BHOU, 2));

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
                $("#priceCorrectionUnit").text(getSomePlaces(priceCorrectionBhou / AssessCommon.BHOU, 2));

                //宗地个别因素修正
                var plotRatioElementAmend = parseFloat("${master.plotRatioElementAmend}");
                var plotRatioElementAmendBhou = getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend), 2);
                $("#plotRatioElementAmendBhou").text(getSomePlaces(plotRatioElementAmendBhou / 10000, 2));
                $("#plotRatioElementAmendUnit").text(getSomePlaces(plotRatioElementAmendBhou / AssessCommon.BHOU, 2));

                //容积率修正
                var plotRatioAdjust = parseFloat(AssessCommon.percentToPoint("${master.plotRatioAdjust}"));
                var plotRatioAdjustBhou = getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend) * (1 + plotRatioAdjust), 2);
                $("#plotRatioAdjustBhou").text(getSomePlaces(plotRatioAdjustBhou / 10000, 2));
                $("#plotRatioAdjustUnit").text(getSomePlaces(plotRatioAdjustBhou / AssessCommon.BHOU, 2));

                //委估宗地价格
                $("#parcelBhou").text(getSomePlaces(plotRatioAdjustBhou / 10000, 2));
                $("#parcelUnit").text(getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend) * (1 + plotRatioAdjust) / AssessCommon.BHOU, 2));
            }
        }
    }


    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //因素条件说明及修正系数
    function getLandLevelTabContent() {
        if(!'${landLevelId}'&&!'${levelDetailId}'){
            notifyInfo("提示","未关联土地级别");
            return false;
        }
        FileUtils.getFileShows({
            target: "select_land_level_file",
            formData: {
                tableName: AssessDBKey.DataLandLevel,
                tableId: '${landLevelId}'
            },
            deleteFlag: false
        })

        var jsonContent = JSON.parse('${master.landLevelContent}');
        var data = caseCommon.landLevelFilter(jsonContent);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        //$("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            var rows = [];
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                        rows.push(item)
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{typeName}/g, item.typeName);
                    var landLevelTypeName = "";
                    if(item.classification){
                        landLevelTypeName+=item.classification;
                    }
                    if(item.category){
                        landLevelTypeName+="/"+item.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{gradeName}/g, item.gradeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    target.append(landLevelBodyHtml);
                }
            });
            var length = rows.length;// 获取当前表格中tr的个数

            var mark = 0; //要合并的单元格数

            var index = 0; //起始行数

            if(length <= 2){
            }else{
                for(var i=0;i < length ;i++){
                    var ford = $("#landLevelTableList tr:gt(0):eq("+i+") td:eq(0)").text();
                    var behind = $("#landLevelTableList tr:gt(0):eq("+(parseInt(i)+1)+") td:eq(0)").text();
                    console.log(ford+"==="+behind);
                    if(ford == behind){
                        $("#landLevelTableList tr:gt(0):eq("+(parseInt(i)+1)+") td:eq(0)").hide();
                        mark = mark +1;
                    }else if(ford != behind){
                        index = i-mark;
                        $("#landLevelTableList tr:gt(0):eq("+index+") td:eq(0)").attr("rowspan",mark+1);//+1 操作标识，将当前的行加入到隐藏
                        mark = 0;
                        $("#landLevelTableList tr:gt(0):eq("+(parseInt(i))+") td:eq(0)").hide();
                    }
                }
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

    function uploadTaxeHtml(id, typeKey, typeName, standardFirst, standardSecond, price,remark) {
        var html = '';
        html += '<td>' + typeName + '</td>';
        switch (typeKey) {
            case "data.land.approximation.method.land.compensate": {
            }
            case "data.land.approximation.method.placement.compensate": {
            }
            case "data.land.approximation.method.house.compensate": {
            }
            case "data.land.approximation.method.removal.award": {
            }
            case"data.land.approximation.method.vegetable.build": {
            }
            case"data.land.approximation.method.plough.reclaim": {
            }
            case"data.land.approximation.method.occupation.land": {
                html += '<td>' + standardFirst + '</td>';
                html += '<td>' + standardSecond + '</td>';
                html += '<td>' + price + '</td>';
                html += '<td>' + remark + '</td>';
                break;
            }
            case"data.land.approximation.method.crops.compensate": {
            }
            case "data.land.approximation.method.land.manager": {
            }
            case "data.land.approximation.method.cannot.foresee": {
            }
            case "data.land.approximation.method.land.acquisition": {
                html += '<td>' + AssessCommon.pointToPercent(standardFirst) + '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>' + price + '</td>';
                html += '<td>' + remark + '</td>';
                break;
            }
            default:
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>' + price + '</td>';
                html += '<td>' + remark + '</td>';

        }

        return html;

    }

    //清空重填
    function emptyRefill(_this) {
        $(_this).closest("tr").find("input").val("");
    }

    function cleanHTMLData(this_) {
        var id = $(this_).closest("tr").find("input[name='id']").val();
        taxes.prototype.removeData(id, this_);

    }

    function getThisPrice(that) {
        var id = $(that).closest("tr").find("input[name='id']").val();
        var masterId = $("#master").find("input[name='id']").val();
        var typeKey = $(that).closest("tr").find("input[name='typeKey']").val();
        var standardFirst = $(that).closest("tr").find("input[name='standardFirst']").val();
        var standardSecond = $(that).closest("tr").find("input[name='standardSecond']").val();
        var price = $(that).parent().closest("tr").find("input[name='price']").val();
        var data = {};
        data.id = id;
        data.masterId = masterId;
        data.typeKey = typeKey;
        data.standardFirst = percentToPoint(standardFirst);
        data.standardSecond = percentToPoint(standardSecond);
        data.price = price;
        var farmlandArea = $("#farmlandArea").val();
        if (!farmlandArea) {
            alert("请填写农用地总面积");
            return false;
        }
        var ploughArea = $("#ploughArea").val();
        if (!ploughArea) {
            alert("请填耕地面积");
            return false;
        }
        var populationNumber = $("#populationNumber").val();
        if (!populationNumber) {
            alert("请填人口数");
            return false;
        }
        if (!$("#" + taxes.prototype.config().frm).valid()) {
            return false;
        }

        $.ajax({
            url: "${pageContext.request.contextPath}/costApproach/getThisPrice",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(data),
                farmlandArea, farmlandArea,
                ploughArea: ploughArea,
                populationNumber: populationNumber
            },
            success: function (result) {
                if (result.ret) {
                    $(that).closest("tr").find("input[name='price']").val(result.data.price);
                    getLandAcquisitionBhou(masterId);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //百分数转小数
    function percentToPoint(value) {
        if (value) {
            if (value.indexOf("%") >= 0) {
                var value = value.replace("%", "");
                if (AssessCommon.isNumber(value)) {
                    return (parseFloat(value) / 100).toFixed(4);
                }
            } else {
                return value;
            }
        }
    }
</script>

</html>

