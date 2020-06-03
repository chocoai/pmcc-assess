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
                    <jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>

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
                                    <input type="hidden" name="id" value="${master.id}">

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
                                                                <input type="text" placeholder="农用地总面积"
                                                                       class="form-control input-full"
                                                                       name="farmlandArea" required
                                                                       onblur="getPloughArearatio()"
                                                                       value="${master.farmlandArea}"
                                                                       data-rule-number="true"
                                                                       id="farmlandArea">
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                耕地面积(亩)
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <input type="text" value="${master.ploughArea}"
                                                                       data-rule-number="true"
                                                                       required onblur="getPloughArearatio()"
                                                                       placeholder="耕地面积"
                                                                       class="form-control input-full" name="ploughArea"
                                                                       id="ploughArea">
                                                            </div>
                                                            <label class="col-sm-1 control-label">
                                                                人口数(人)
                                                            </label>
                                                            <div class="col-sm-3">
                                                                <input type="text" value="${master.populationNumber}"
                                                                       data-rule-number="true" required
                                                                       placeholder="人口数" class="form-control input-full"
                                                                       name="populationNumber"
                                                                       id="populationNumber">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地外设定</label>
                                                            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5"
                                                                 id="industrySupplyInfoContainer_BBBBB">

                                                            </div>
                                                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地内设定</label>
                                                            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5  "
                                                                 id="developmentDegreeContentContainer_BBBBB">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <p>
                                                    <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                                            type="button"
                                                            data-toggle="modal"
                                                            onclick="research.prototype.showModel()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                        新增
                                                    </button>
                                                </p>
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
                                                    <p>
                                                        <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                                                type="button"
                                                                data-toggle="modal"
                                                                onclick="taxes.prototype.showModel()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                            新增
                                                        </button>
                                                    </p>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <table class="table" id="tb_taxesList">
                                                                <thead>
                                                                <tr>
                                                                    <th>类型</th>
                                                                    <th>耕地标准</th>
                                                                    <th>非耕地标准</th>
                                                                    <th>价格(元/亩)</th>
                                                                    <th>备注</th>
                                                                    <th>操作</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody id="tbodyContent">
                                                                <c:forEach items="${taxesVos}" var="approachTaxe"
                                                                           varStatus="s">
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
                                                                <input type="hidden" name="landLevelContent"
                                                                       id="landLevelContent"
                                                                       value='${master.landLevelContent}'>
                                                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">区域及个别修正系数</label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="text"
                                                                               class="form-control x-percent"
                                                                               name="plotRatioElementAmend"
                                                                               id="plotRatioElementAmend">
                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-info btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="getLandLevelTabContent();">
                                                                                土地因素
                                                                            </button>
                                                                        </div>
                                                                    </div>
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
                                                                    <input type="text" placeholder="农用地总面积"
                                                                           class="form-control input-full"
                                                                           id="circulationExpense"
                                                                           name="circulationExpense" required
                                                                           onblur="getLandProductionUnit()"
                                                                           value="${master.circulationExpense}"
                                                                           data-rule-number="true">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.circulationExpenseRemark}"
                                                                           class="form-control input-full"
                                                                           name="circulationExpenseRemark">
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
                                                                    <input type="text" placeholder="场平费用"
                                                                           class="form-control input-full"
                                                                           id="flatExpense"
                                                                           name="flatExpense" required
                                                                           onblur="getLandProductionUnit()"
                                                                           value="${master.flatExpense}"
                                                                           data-rule-number="true">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.flatExpenseRemark}"
                                                                           class="form-control input-full"
                                                                           name="flatExpenseRemark">
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
                                                                    <input type="text" placeholder="计息周期"
                                                                           class="form-control input-full"
                                                                           id="machineCycle"
                                                                           name="machineCycle" required
                                                                           onblur="getLandProductionInterest()"
                                                                           value="${master.machineCycle}"
                                                                           data-rule-number="true">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.machineCycleRemark}"
                                                                           class="form-control input-full"
                                                                           name="machineCycleRemark">
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
                                                                    <input type="text" placeholder="计息利率"
                                                                           class="form-control input-full x-percent"
                                                                           id="calculatedInterest"
                                                                           name="calculatedInterest" required
                                                                           onblur="getLandProductionInterest()"
                                                                           value="${master.calculatedInterest}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.calculatedInterestRemark}"
                                                                           class="form-control input-full"
                                                                           name="calculatedInterestRemark">
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
                                                                    <input type="text" placeholder="开发利润率"
                                                                           class="form-control input-full x-percent"
                                                                           id="profitMargin"
                                                                           name="profitMargin" required
                                                                           onblur="getLandProductionProfit()"
                                                                           value="${master.profitMargin}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.profitMarginRemark}"
                                                                           class="form-control input-full"
                                                                           name="profitMarginRemark">
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
                                                                    <input type="text" placeholder="土地增值率"
                                                                           class="form-control input-full x-percent"
                                                                           id="incrementalBenefit"
                                                                           name="incrementalBenefit" required
                                                                           onblur="getLandAppreciation()"
                                                                           value="${master.incrementalBenefit}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.incrementalBenefitRemark}"
                                                                           class="form-control input-full"
                                                                           name="incrementalBenefitRemark">
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
                                                                    <input type="text" placeholder="容积率调整"
                                                                           class="form-control input-full x-percent"
                                                                           id="plotRatioAdjust"
                                                                           name="plotRatioAdjust" required
                                                                           onblur="getYearFixed()"
                                                                           value="${master.plotRatioAdjust}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.plotRatioAdjustRemark}"
                                                                           class="form-control input-full"
                                                                           name="plotRatioAdjustRemark">
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
                                                                    <input type="text" placeholder="剩余年限"
                                                                           class="form-control input-full"
                                                                           id="landRemainingYear"
                                                                           data-rule-number="true"
                                                                           name="landRemainingYear" required
                                                                           onblur="getYearFixed()"
                                                                           value="${master.landRemainingYear}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    说明
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text"
                                                                           value="${master.landRemainingYearRemark}"
                                                                           class="form-control input-full"
                                                                           name="landRemainingYearRemark">
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
                                                                    <div class="input-group">
                                                                        <input type="text" required
                                                                               class="form-control x-percent"
                                                                               id="rewardRate"
                                                                               name="rewardRate" placeholder="还原率"
                                                                               readonly="readonly"
                                                                               value="${master.rewardRate}">
                                                                        <div class="input-group-prepend">
                                                                            <input type="hidden" name="rewardRateId"
                                                                                   value="${master.rewardRateId}">
                                                                            <button class="btn btn-info btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="getRewardRate(this);">还原率测算
                                                                            </button>
                                                                        </div>
                                                                    </div>
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

                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>
<div id="modal_research" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">年平均产值调查</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_research" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2 control-label">
                                    年份描述<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" name="yearDescribe" class="form-control input-full"
                                           required="required">
                                </div>
                                <label class="col-sm-2 control-label">
                                    平均产值(元)<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" name="averageProduction" class="form-control input-full"
                                           data-rule-number="true"
                                           required="required">
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="research.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="modal_taxes" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">税率配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_taxes" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                类型
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" id="typeName" name="typeName" list="itemList"
                                                           class="form-control">
                                                    <datalist id="itemList">
                                                        <c:forEach var="item" items="${taxesTypes}">
                                                            <option value="${item.name}">${item.name}</option>
                                                        </c:forEach>
                                                    </datalist>
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="StandardContent">

                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="taxes.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="detailAchievementModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="landLevelContentFrm">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <div class="col-sm-12">
                                    <div id="_select_land_level_file"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" id="landLevelTableList">
                                <thead>
                                <tr>
                                    <th width="10%">土地级别类型</th>
                                    <th width="10%">土地级别类别</th>
                                    <th width="10%">土地级别等级</th>
                                    <th width="20%">说明</th>
                                    <th width="10%">分值</th>
                                    <th width="5%"></th>
                                </tr>
                                </thead>
                                <tbody id="landLevelTabContent">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="getPlotRatioElementAmend()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td>
            {typeName}
        </td>
        <td class="table-cell">
            {landLevelTypeName}
        </td>
        <td>
            <select class="form-control input-full" name="landLevelGrade"
                    onchange="caseCommon.landLevelHandle(this);">
                {landLevelGradeHTML}
            </select>
        </td>
        <td>
            <label name="reamark" class="form-control input-full">{reamark}</label>
        </td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">
            <input type="text" class="form-control input-full  x-percent" name="landFactorTotalScore"
                   value="{landFactorTotalScore}">
            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="caseCommon.landLevelEmpty(this)">
        </td>
    </tr>
</script>
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
        formData.landLevelContent = $("#landLevelContent").val();
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

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    $(function () {
        if ("${master}") {
            getLandAcquisitionBhou("${master.id}")
            getPloughArearatio("${master.farmlandArea}", "${master.ploughArea}");
            var data = {
                parcelSettingInner: '${master.parcelSettingInner}',
                parcelSettingOuter: '${master.parcelSettingOuter}'
            };
            initParcelSettingData(data);

            if(!'${master.landLevelContent}'){
                $("#landLevelContent").val('${landLevelContent}');
            }
            if(!'${master.plotRatioElementAmend}'){
                $("#plotRatioElementAmend").val(AssessCommon.pointToPercent('${landFactorTotalScore}'));
            }else {
                $("#plotRatioElementAmend").val(AssessCommon.pointToPercent('${master.plotRatioElementAmend}'));
            }



        }

    })

    //耕地比例
    function getPloughArearatio(farmlandAreaVal, ploughAreaVal) {
        var farmlandArea = parseFloat($("#farmlandArea").val()) ? parseFloat($("#farmlandArea").val()) : 0;
        var ploughArea = parseFloat($("#ploughArea").val()) ? parseFloat($("#ploughArea").val()) : 0;
        if (farmlandAreaVal && ploughAreaVal) {
            farmlandArea = parseFloat(farmlandAreaVal);
            ploughArea = parseFloat(ploughAreaVal);
        }
        var ploughArearatio = getSomePlaces(ploughArea / farmlandArea, 4);
        var noPloughArearatio = 1 - ploughArearatio;
        $("#ploughArearatio").text(AssessCommon.pointToPercent(ploughArearatio));
        $("#noPloughArearatio").text(AssessCommon.pointToPercent(noPloughArearatio));

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
                    AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }


    //计算土地开发费
    function getLandProductionUnit(circulationExpenseVal, flatExpenseVal) {
        var circulationExpense = parseFloat($("#circulationExpense").val()) ? parseFloat($("#circulationExpense").val()) : 0;
        var flatExpense = parseFloat($("#flatExpense").val()) ? parseFloat($("#flatExpense").val()) : 0;
        if (circulationExpenseVal && flatExpenseVal) {
            circulationExpense = parseFloat(circulationExpenseVal);
            flatExpense = parseFloat(flatExpenseVal);
        }
        $("#landProductionBhou").text(getSomePlaces((circulationExpense + flatExpense) * AssessCommon.BHOU, 2));
        $("#landProductionUnit").text(getSomePlaces(parseFloat($("#landProductionBhou").text()) / AssessCommon.BHOU, 2));
        //计算土地开发利息
        getLandProductionInterest();
    }

    //计算土地开发利息
    function getLandProductionInterest(machineCycleVal, calculatedInterestVal) {
        //计算周期F22
        var machineCycle = parseFloat($("#machineCycle").val());
        //计算利息G22
        var calculatedInterest = parseFloat(AssessCommon.percentToPoint($("#calculatedInterest").val()));
        //土地取得费E4
        var landAcquisitionUnit = getSomePlaces(parseFloat($("#landAcquisitionBhou").text()) / AssessCommon.BHOU, 2);
        //土地开发费E17
        var landProductionUnit = parseFloat($("#landProductionBhou").text()) / AssessCommon.BHOU;
        if (machineCycleVal && calculatedInterestVal) {
            machineCycle = parseFloat(machineCycleVal);
            calculatedInterest = parseFloat(AssessCommon.percentToPoint(calculatedInterestVal));
        }
        //(E4*((1+G22)^F22-1)+E17*((1+G22)^(F22/2)-1),2)
        if (machineCycle && calculatedInterest && landAcquisitionUnit && landProductionUnit) {
            var temp = Math.pow((1 + calculatedInterest), machineCycle) - 1;
            var temp2 = Math.pow((1 + calculatedInterest), machineCycle / 2) - 1;
            var landProductionInterestUnit = getSomePlaces(landAcquisitionUnit * temp + landProductionUnit * temp2, 2);
            $("#landProductionInterestBhou").text(getSomePlaces(landProductionInterestUnit * AssessCommon.BHOU, 2));
            $("#landProductionInterestUnit").text(landProductionInterestUnit);
        }
        //土地开发利润
        getLandProductionProfit();
    }


    //土地开发利润
    function getLandProductionProfit(profitMarginVal) {
        //土地取得费E4
        var landAcquisitionUnit = getSomePlaces(parseFloat($("#landAcquisitionBhou").text()) / AssessCommon.BHOU, 2);
        //土地开发费H17
        var landProductionUnit = getSomePlaces(parseFloat($("#landProductionBhou").text()) / AssessCommon.BHOU, 2);
        //开发利润率F24
        var profitMargin = parseFloat(AssessCommon.percentToPoint($("#profitMargin").val()));
        if (profitMarginVal) {
            profitMargin = parseFloat(AssessCommon.percentToPoint(profitMarginVal));
        }

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
            getLandAppreciation();
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

    //年期修正、价格修正与确定、单价、估价对象楼面地价、委估宗地总价
    function getYearFixed(rewardRatePercent) {
        //G33
        var rewardRate = AssessCommon.percentToPoint($("#rewardRate").val());
        if (rewardRatePercent) {
            rewardRate = AssessCommon.percentToPoint(rewardRatePercent)
        }
        //E29
        var landRemainingYear = parseFloat($("#landRemainingYear").val());
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
                var plotRatioElementAmend = parseFloat(AssessCommon.percentToPoint($("#plotRatioElementAmend").val()));
                var plotRatioElementAmendBhou = getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend), 2);
                $("#plotRatioElementAmendBhou").text(getSomePlaces(plotRatioElementAmendBhou / 10000, 2));
                $("#plotRatioElementAmendUnit").text(getSomePlaces(plotRatioElementAmendBhou / AssessCommon.BHOU, 2));

                //容积率修正
                var plotRatioAdjust = parseFloat(AssessCommon.percentToPoint($("#plotRatioAdjust").val()));
                var plotRatioAdjustBhou = getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend) * (1 + plotRatioAdjust), 2);
                $("#plotRatioAdjustBhou").text(getSomePlaces(plotRatioAdjustBhou / 10000, 2));
                $("#plotRatioAdjustUnit").text(getSomePlaces(plotRatioAdjustBhou / AssessCommon.BHOU, 2));

                //委估宗地价格
                $("#parcelBhou").text(getSomePlaces(plotRatioAdjustBhou / 10000, 2));
                $("#parcelUnit").text(getSomePlaces(yearFixed * landUseBhou * (1 + plotRatioElementAmend) * (1 + plotRatioAdjust) / AssessCommon.BHOU, 2));
            }
        }
    }

    //宗地外，宗地内
    function initParcelSettingData(data) {
        var industrySupplyInfoContainer = $("#industrySupplyInfoContainer_BBBBB");
        var developmentDegreeContentContainer = $("#developmentDegreeContentContainer_BBBBB");
        industrySupplyInfoContainer.empty();
        developmentDegreeContentContainer.empty();
        //宗地外设定
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var resultHtml = '';
            var array = [];
            if (data) {
                if (data.parcelSettingOuter) {
                    array = data.parcelSettingOuter.split(',');
                }
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingOuter' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"checkedFun(this,'parcelSettingOuter',false)\">";
            if (industrySupplyInfoContainer.find("div").size() == 0) {
                industrySupplyInfoContainer.append(resultHtml);
            } else {
                $.each(resultData, function (i, item) {
                    var ele = "#parcelSettingOuterBBBBB" + item.id;
                    ele = $(ele);
                    if ($.inArray(item.id.toString(), array) > -1) {
                        ele.prop("checked", true);
                    } else {
                        ele.prop("checked", false);
                    }
                });
            }
        }, true);
        //宗地内设定
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
            var resultHtml = '';
            var array = [];
            if (data) {
                if (data.parcelSettingInner) {
                    array = data.parcelSettingInner.split(',');
                }
            }

            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingInner' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";

            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"checkedFun(this,'parcelSettingInner',false)\">";
            if (developmentDegreeContentContainer.find("div").size() == 0) {
                developmentDegreeContentContainer.append(resultHtml);
            } else {
                $.each(resultData, function (i, item) {
                    var ele = "#parcelSettingInnerBBBBB" + item.id;
                    ele = $(ele);
                    if ($.inArray(item.id.toString(), array) > -1) {
                        ele.prop("checked", true);
                    } else {
                        ele.prop("checked", false);
                    }
                });
            }
        });
    };

    function checkedFun(that, name, flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='" + name + "']:checkbox");
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };


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

        var landLevelContent = $("#landLevelContent").val();
        if (landLevelContent) {
            landLevelLoadHtml2(landLevelContent)
        } else {
            $.ajax({
                url: getContextPath() + "/dataLandLevelDetailAchievement/landLevelFilter",
                type: "get",
                data: {levelDetailId: '${levelDetailId}'},
                success: function (result) {
                    landLevelLoadHtml(result.data);
                }
            })
        }
    }


    function landLevelLoadHtml(data) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        $("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            var rows = [];
            $.each(dataA, function (i, obj) {
                var item = caseCommon.getLandLevelFilter(obj);
                rows.push(item);
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{typeName}/g, item.typeName);
                    var landLevelTypeName = "";
                    if (item.classification) {
                        landLevelTypeName += item.classification;
                    }
                    if (item.category) {
                        landLevelTypeName += "/" + item.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
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

    function landLevelLoadHtml2(jsonStr) {
        var jsonParse = JSON.parse(jsonStr);
        var data = caseCommon.landLevelFilter(jsonParse);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        $("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            var rows=[]
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                        rows.push(item);
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{typeName}/g, item.typeName);
                    var landLevelTypeName = "";
                    if (item.classification) {
                        landLevelTypeName += item.classification;
                    }
                    if (item.category) {
                        landLevelTypeName += "/" + item.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
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


    function getPlotRatioElementAmend(that) {
        var landLevelContent = [];
        var landFactorTotalScoreResult = 0;

        $("#landLevelContentFrm").find("input[name='landLevelContent']").each(function (i, n) {
            var group = $(n).closest(".group");
            var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
            var landFactorTotalScore = AssessCommon.percentToPoint(group.find("input[name='landFactorTotalScore']").val());
            landFactorTotalScoreResult += Number(landFactorTotalScore);
            var obj = JSON.parse($(n).val());
            var dataObject = [];
            obj.forEach(function (value, index) {
                if (value.id == dataLandLevelAchievement) {
                    value.modelStr = "update";
                    value.achievement = landFactorTotalScore;
                } else {
                    delete value.modelStr;
                }
                dataObject.push(value);
            });
            landLevelContent.push(dataObject);
        });
        if (landLevelContent.length >= 1) {
            $("#landLevelContent").val(JSON.stringify(landLevelContent));
            $("#plotRatioElementAmend").val(AssessCommon.pointToPercent(landFactorTotalScoreResult));
        } else {
            $("#landLevelContent").val('');
            $("#plotRatioElementAmend").val('');
        }
        $("#detailAchievementModal").modal('hide');

        //年期修正等
        getYearFixed();
    }

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
            data.box = "modal_research";
            data.frm = "frm_research";
            return data;
        },
        loadDataList: function (masterId) {
            var cols = [];
            cols.push({field: 'yearDescribe', title: '年份描述'});
            cols.push({field: 'averageProduction', title: '平均产值(元)'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="research.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="research.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
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
        },
        removeData: function (id) {
            AlertConfirm("确认删除么", "删除后数据不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/costApproach/deleteCostApproachItem",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess('成功', '删除成功');
                            research.prototype.loadDataList(${master.id});
                        }
                        else {
                            AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + research.prototype.config().frm).clearAll();
            $('#' + research.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + research.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(research.prototype.config().frm);
            data.masterId = ${master.id};
            $.ajax({
                url: "${pageContext.request.contextPath}/costApproach/addCostApproachItem",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功', '保存成功');
                        $('#' + research.prototype.config().box).modal('hide');
                        research.prototype.loadDataList(${master.id});
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/costApproach/getCostApproachItemById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + research.prototype.config().frm).clearAll();
                        $("#" + research.prototype.config().frm).initForm(result.data);
                        $('#' + research.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }

    }
</script>
<%--税率配置--%>
<script type="text/javascript">
    $(function () {


    });
    var taxes = function () {

    };
    taxes.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_taxes_list";
            data.box = "modal_taxes";
            data.frm = "frm_taxes";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataList: function (masterId) {
            var cols = [];
            cols.push({field: 'typeName', title: '类型'});
            cols.push({field: 'standard', title: '耕地标准/非耕地标准'});
            cols.push({field: 'price', title: '价格(元/亩)'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="taxes.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="taxes.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + taxes.prototype.config().table).bootstrapTable('destroy');
            TableInit(taxes.prototype.config().table, "${pageContext.request.contextPath}/costApproach/getMdCostApproachTaxesList", cols, {
                masterId: masterId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                    getLandAcquisitionBhou(masterId)
                }
            });
        },
        removeData: function (id, this_) {
            AlertConfirm("确认删除么", "删除后数据不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/costApproach/deleteCostApproachTaxes",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess('成功', '删除成功');
                            $(this_).parent().parent().remove();

                        }
                        else {
                            AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            });
        },
        showModel: function () {
            $("#StandardContent").empty();
            $("#" + taxes.prototype.config().frm).clearAll();
            $('#' + taxes.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + taxes.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(taxes.prototype.config().frm);
            data.masterId = $("#master").find("input[name='id']").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/costApproach/addCostApproachTaxes",
                type: "post",
                dataType: "json",
                data: {
                    formData: JSON.stringify(data),
                },
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功', '保存成功');
                        $('#' + taxes.prototype.config().box).modal('hide');
                        var html = "<tr>"
                        html += uploadTaxeHtml(result.data.id, result.data.typeKey, result.data.typeName, "", "", "", "");
                        html += "</tr>"
                        $("#tbodyContent").append(html);
                    } else {
                        notifyInfo("失败", "失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                    url: "${pageContext.request.contextPath}/costApproach/getCostApproachTaxesById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + taxes.prototype.config().frm).clearAll();
                            $("#" + taxes.prototype.config().frm).initForm(result.data);

                            var html = '';
                            switch (result.data.typeKey) {
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
                                    html += '<div class="form-inline x-valid">';
                                    html += '<label class="col-sm-1 control-label">耕地标准</label>';
                                    html += '<div class="col-sm-3">';
                                    if (result.data.standardFirst != null) {
                                        html += '<input type="text" name="standardFirst" value="' + result.data.standardFirst + '" class="form-control input-full" data-rule-number="true">';
                                    } else {
                                        html += '<input type="text" name="standardFirst" value="" class="form-control input-full" data-rule-number="true">';
                                    }
                                    html += '</div>';
                                    html += '</div>';
                                    html += '<div class="form-inline x-valid">';
                                    html += '<label class="col-sm-1 control-label">非耕地标准</label>';
                                    html += '<div class="col-sm-3">';
                                    if (result.data.standardSecond != null) {
                                        html += '<input type="text" name="standardSecond" value="' + result.data.standardSecond + '" class="form-control input-full" data-rule-number="true">';
                                    } else {
                                        html += '<input type="text" name="standardSecond" value="" class="form-control input-full" data-rule-number="true">';
                                    }
                                    html += '</div>';
                                    html += '</div>';
                                    break;
                                }
                                case"data.land.approximation.method.crops.compensate": {
                                    html += '<div class="form-inline x-valid">';
                                    html += '<label class="col-sm-1 control-label">耕地标准</label>';
                                    html += '<div class="col-sm-3">';
                                    if (result.data.standardFirst != null) {
                                        html += '<input type="text" name="standardFirst" class="form-control input-full x-percent" value="' + AssessCommon.pointToPercent(result.data.standardFirst) + '">';
                                    } else {
                                        html += '<input type="text" name="standardFirst" value="" class="form-control input-full" data-rule-number="true">';
                                    }
                                    html += '</div>';
                                    html += '</div>';
                                    html += '<div class="form-inline x-valid">';
                                    html += '<label class="col-sm-1 control-label">非耕地标准</label>';
                                    html += '<div class="col-sm-3">';
                                    if (result.data.standardSecond != null) {
                                        html += '<input type="text" name="standardSecond" value="' + result.data.standardSecond + '" class="form-control input-full" data-rule-number="true">';
                                    } else {
                                        html += '<input type="text" name="standardSecond" value="" class="form-control input-full" data-rule-number="true">';
                                    }
                                    html += '</div>';
                                    html += '</div>';
                                    break;
                                }
                                case "data.land.approximation.method.land.manager": {
                                }
                                case "data.land.approximation.method.cannot.foresee": {
                                }
                                case "data.land.approximation.method.land.acquisition": {
                                    html += '<div class="form-inline x-valid">';
                                    html += '<label class="col-sm-1 control-label">标准</label>';
                                    html += '<div class="col-sm-3">';
                                    if (result.data.standardFirst != null) {
                                        html += '<input type="text" name="standardFirst" class="form-control input-full x-percent" value="' + AssessCommon.pointToPercent(result.data.standardFirst) + '">';
                                    } else {
                                        html += '<input type="text" name="standardFirst" value="" class="form-control input-full" data-rule-number="true">';
                                    }
                                    html += '</div>';
                                    html += '</div>';
                                    break;
                                }
                            }
                            $("#StandardContent").empty().append(html);
                            $('#' + taxes.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            )
        },

    }

    function uploadTaxeHtml(id, typeKey, typeName, standardFirst, standardSecond, price, remark) {
        var html = '';
        html += '<td><input type="hidden" name="id" value="' + id + '">';
        html += '<input type="hidden" name="typeKey" value="' + typeKey + '">' + typeName + '</td>';
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
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" data-rule-number="true" name="standardFirst" class="form-control input-full " value="' + standardFirst + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text"  data-rule-number="true"  name="standardSecond" class="form-control input-full " value="' + standardSecond + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" data-rule-number="true" name="price" class="form-control input-full " value="' + price + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" name="remark" class="form-control input-full " value="' + remark + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<a class="btn btn-xs btn-danger" onclick="getThisPrice(this)">计算价格</a>';
                html += '<a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空</a>';
                html += ' <a  class="btn btn-xs btn-warning" onclick="cleanHTMLData(this)">移除</a>'
                html += '</td>';
                break;
            }
            case"data.land.approximation.method.crops.compensate": {
            }
            case "data.land.approximation.method.land.manager": {
            }
            case "data.land.approximation.method.cannot.foresee": {
            }
            case "data.land.approximation.method.land.acquisition": {
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                if (standardFirst) {
                    html += '<input type="text" data-rule-number="true" name="standardFirst"  class="form-control input-full x-percent" value="' + AssessCommon.pointToPercent(standardFirst) + '">';
                } else {
                    html += '<input type="text" data-rule-number="true" name="standardFirst"  class="form-control input-full x-percent">';
                }
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" data-rule-number="true" name="price" class="form-control input-full " value="' + price + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" name="remark" class="form-control input-full " value="' + remark + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<a class="btn btn-xs btn-danger" onclick="getThisPrice(this)">计算价格</a>';
                html += '<a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空</a>';
                html += ' <a  class="btn btn-xs btn-warning" onclick="cleanHTMLData(this)">移除</a>'
                html += '</td>';
                break;
            }
            default:
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this);" data-rule-number="true" name="price" class="form-control input-full " value="' + price + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" name="remark" class="form-control input-full " value="' + remark + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空</a>';
                html += ' <a  class="btn btn-xs btn-warning" onclick="cleanHTMLData(this)">移除</a>'
                html += '</td>';
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
        var remark = $(that).parent().closest("tr").find("input[name='remark']").val();
        var data = {};
        data.id = id;
        data.masterId = masterId;
        data.typeKey = typeKey;
        data.standardFirst = percentToPoint(standardFirst);
        data.standardSecond = percentToPoint(standardSecond);
        data.price = price;
        data.remark = remark;
        var farmlandArea = $("#farmlandArea").val();
        if (!farmlandArea) {
            notifyInfo("提示", "请填写农用地总面积");
            return false;
        }
        var ploughArea = $("#ploughArea").val();
        if (!ploughArea) {
            notifyInfo("提示", "请填耕地面积");
            return false;
        }
        var populationNumber = $("#populationNumber").val();
        if (!populationNumber) {
            notifyInfo("提示", "请填人口数");
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
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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

    function getApproachTaxesData(that) {
        var data = {};
        data.id = $(that).find("input[name='id']").val();
        data.masterId = $("#master").find("input[name='id']").val();
        data.typeKey = $(that).find("input[name='typeKey']").val();
        var standardFirst =$(that).find("input[name='standardFirst']").val();
        var standardSecond =$(that).find("input[name='standardSecond']").val();
        var price =$(that).find("input[name='price']").val();
        var remark =$(that).find("input[name='remark']").val();
        data.standardFirst = standardFirst?percentToPoint(standardFirst):"";
        data.standardSecond = standardSecond?percentToPoint(standardSecond):"";
        data.price = price?price:"";
        data.remark = remark?remark:"";
        return data;
    }
</script>
</html>

