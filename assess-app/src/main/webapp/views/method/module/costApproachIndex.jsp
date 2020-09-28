<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-9-16
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>
<%@include file="/views/method/module/projectLandAchievementGroup.jsp" %>

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
                                                   onblur="calculationNumeric(this);"
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
                                                   required onblur="calculationNumeric(this);"
                                                   placeholder="耕地面积"
                                                   class="form-control input-full" name="ploughArea"
                                                   id="ploughArea">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            人口数(人)
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.populationNumber}"
                                                   onblur="calculationNumeric(this);"
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
                                                           name="plotRatioElementAmend"  onblur="calculationNumeric(this);"
                                                           data-value="${master.plotRatioElementAmend}"
                                                           id="plotRatioElementAmend"
                                                           value="${master.plotRatioElementAmend}">

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
                                                <th width="50%">土地级别类型类别</th>
                                                <th width="30%">土地级别等级</th>
                                                <th width="20%">分值</th>
                                            </tr>
                                            </thead>
                                            <tbody>
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
                                                <input type="text" placeholder="农用地总面积"
                                                       class="form-control input-full"
                                                       id="circulationExpense"
                                                       name="circulationExpense" required
                                                       onblur="calculationNumeric(this);"
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
                                                       onblur="calculationNumeric(this);"
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
                                                       onblur="calculationNumeric(this);"
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
                                                       data-value="${master.calculatedInterest}"
                                                       onblur="calculationNumeric(this);"
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
                                                       name="profitMargin" required data-value="${master.profitMargin}"
                                                       onblur="calculationNumeric(this);"
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
                                                       data-value="${master.incrementalBenefit}"
                                                       onblur="calculationNumeric(this);"
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
                                                       data-value="${master.plotRatioAdjust != null?master.plotRatioAdjust:plotRatioAdjust}"
                                                       onblur="calculationNumeric(this);"
                                                       value="${master.plotRatioAdjust != null?master.plotRatioAdjust:plotRatioAdjust}">
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
                                                       onblur="calculationNumeric(this);"
                                                       value="${master.landRemainingYear == null?landRemainingYear:master.landRemainingYear}">
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
                                                           onblur="calculationNumeric(this)"
                                                           value="${master.rewardRate}" data-value="${master.rewardRate}">
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

                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text"
                                                       value="${master.rewardRateRemark}"
                                                       class="form-control input-full"
                                                       name="rewardRateRemark">
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


<script>

    $(function () {
        if ("${master}") {
            var data = {
                parcelSettingInner: '${master.parcelSettingInner}',
                parcelSettingOuter: '${master.parcelSettingOuter}'
            };
            initParcelSettingData(data);
        }
        loadProjectLandAchievementGroup() ;
        research.prototype.loadDataList(${master.id});
        calculationNumeric();
    });



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


    //获取报酬率
    function getRewardRate(_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                var element = group.find(':text');
                element.val(data.resultValue);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue)).trigger('blur');
            }
        })
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


</script>

<%--年平均产值--%>
<script type="text/javascript">

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
                        } else {
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
                        calculationNumeric();
                    } else {
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

    var taxes = function () {

    };
    taxes.prototype = {
        config: function () {
            var data = {};
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
        }
    }

    function uploadTaxeHtml(id, typeKey, typeName, standardFirst, standardSecond, price, remark) {
        var html = '';
        html += '<td><input type="hidden" name="id" value="' + id + '">';
        html += '<input type="hidden" name="typeKey" value="' + typeKey + '">' + typeName + '</td>';
        switch (typeKey) {
            case"data.land.approximation.method.occupation.land": {

            }
            case"data.land.approximation.method.plough.reclaim": {
            }
            case"data.land.approximation.method.vegetable.build": {
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this);" data-rule-number="true" name="standardFirst" class="form-control input-full " value="' + standardFirst + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this)" data-rule-number="true" name="price" class="form-control input-full " value="' + price + '">';
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
            case "data.land.approximation.method.removal.award": {
                html += '<td>';
                html += '<input type="hidden"  data-rule-number="true" name="standardFirst" value="0"  >';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this)" data-rule-number="true" name="price" class="form-control input-full " value="' + price + '">';
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
                break;
            }
            case "data.land.approximation.method.land.compensate": {
            }
            case "data.land.approximation.method.placement.compensate": {
            }
            case "data.land.approximation.method.house.compensate": {
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this);" data-rule-number="true" name="standardFirst" class="form-control input-full " value="' + standardFirst + '">';
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this);"  data-rule-number="true"  name="standardSecond" class="form-control input-full " value="' + standardSecond + '">';
                html += '</div>';
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
                html += '<td >';
                html += '<div class="form-inline x-valid">';
                if (standardFirst) {
                    html += '<input type="text" onblur="getThisPrice(this)" data-rule-number="true" name="standardFirst"  class="form-control input-full x-percent" value="' + AssessCommon.pointToPercent(standardFirst) + '">';
                } else {
                    html += '<input type="text" onblur="getThisPrice(this)" data-rule-number="true" name="standardFirst"  class="form-control input-full x-percent">';
                }
                html += '</div>';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td >';
                html += '<div class="form-inline x-valid">';
                html += '<input type="text" onblur="getThisPrice(this)" data-rule-number="true" name="price" class="form-control input-full " value="' + price + '">';
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

    /**
     * 土地因素  测算调用方法  触发事件后赋值
     */
    function initPlotRatioElementAmendValue(query ,dataAll ,table) {
        landAchievementGroup.applyMethodLoadHtml(dataAll ,table,function () {
            landAchievementGroup.countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(query.projectId ,query.dataTableId ,query.dataTableName ,function (item) {
                if (item){
                    console.log(item) ;
                    $("#plotRatioElementAmend").val(AssessCommon.pointToPercent(item)).attr("data-value",item).trigger('blur');
                }else {
                    $("#plotRatioElementAmend").val('').trigger('blur');
                }
            }) ;
        }) ;
    }

    /**
     * 土地因素  测算调用方法
     */
    function loadProjectLandAchievementGroup() {
        if ('${basicEstateLandCategoryInfo}') {
            var query = {
                projectId: '${judgeObject.projectId}',
                dataTableId: '${basicEstateLandCategoryInfo.id}',
                dataTableName: AssessDBKey.BasicEstateLandCategoryInfo,
                levelDetailId:'${basicEstateLandCategoryInfo.landLevel}',
                targetTableId:'${master.id}',
                targetTableName:AssessDBKey.MdCostApproach
            };
            var table = $("#landLevelTableList") ;
            landAchievementGroup.getInitProjectLandAchievementGroupData(query.projectId ,query.dataTableId ,query.dataTableName ,function (dataAll) {
                if (!dataAll || dataAll.length == 0){
                    landAchievementGroup.initProjectLandAchievementGroup(query.levelDetailId,query.projectId ,query.dataTableId ,query.dataTableName ,function (data) {
                        initPlotRatioElementAmendValue(query,data,table) ;
                    }) ;
                }else {
                    initPlotRatioElementAmendValue(query,dataAll,table) ;
                }
            });
        }
    }

    //清空重填
    function emptyRefill(_this) {
        $(_this).closest("tr").find("input").val("");
    }

    function cleanHTMLData(this_) {
        var id = $(this_).closest("tr").find("input[name='id']").val();
        taxes.prototype.removeData(id, this_);

    }

    /**
     * 计算价格 根据数据传入到服务端 匹配后的计算结果
     */
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
            // notifyInfo("提示", "请填写农用地总面积");
            return false;
        }
        var ploughArea = $("#ploughArea").val();
        if (!ploughArea) {
            // notifyInfo("提示", "请填耕地面积");
            return false;
        }
        var populationNumber = $("#populationNumber").val();
        if (!populationNumber) {
            // notifyInfo("提示", "请填人口数");
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
                farmlandArea: farmlandArea,
                ploughArea: ploughArea,
                populationNumber: populationNumber
            },
            success: function (result) {
                if (result.ret) {
                    $(that).closest("tr").find("input[name='price']").val(result.data.price);
                    calculationNumeric();
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    /**
     * 获取生成明细 土地取得费及相关税费 单行计算数据
     * @param that
     */
    function getApproachTaxesData(that) {
        var data = {};
        data.id = $(that).find("input[name='id']").val();
        data.masterId = $("#master").find("input[name='id']").val();
        data.typeKey = $(that).find("input[name='typeKey']").val();
        var standardFirst = $(that).find("input[name='standardFirst']").val();
        var standardSecond = $(that).find("input[name='standardSecond']").val();
        var price = $(that).find("input[name='price']").val();
        var remark = $(that).find("input[name='remark']").val();
        data.standardFirst = standardFirst ? percentToPoint(standardFirst) : "";
        data.standardSecond = standardSecond ? percentToPoint(standardSecond) : "";
        data.price = price ? price : "";
        data.remark = remark ? remark : "";
        return data;
    }

    /**
     * 收集提交参数 zch
     */
    function getCostApproachFormData() {
        var formData = formParams("master");
        var formDataOther = formParams("master_other");
        formData.id = $("#master").find("input[name='id']").val();
        formData.rewardRateId = formDataOther.rewardRateId;
        formData.circulationExpense = formDataOther.circulationExpense;
        formData.circulationExpenseRemark = formDataOther.circulationExpenseRemark;
        formData.flatExpense = formDataOther.flatExpense;
        formData.flatExpenseRemark = formDataOther.flatExpenseRemark;
        formData.machineCycle = formDataOther.machineCycle;
        formData.machineCycleRemark = formDataOther.machineCycleRemark;
        formData.calculatedInterestRemark = formDataOther.calculatedInterestRemark;
        formData.profitMarginRemark = formDataOther.profitMarginRemark;
        formData.incrementalBenefitRemark = formDataOther.incrementalBenefitRemark;
        formData.plotRatioAdjustRemark = formDataOther.plotRatioAdjustRemark;
        formData.landRemainingYear = formDataOther.landRemainingYear;
        formData.landRemainingYearRemark = formDataOther.landRemainingYearRemark;


        formData.parcelUnit = parseFloat($("#parcelUnit").text());
        formData.landUsePrice = parseFloat($("#landUseUnit").text());
        formData.yearFixed = parseFloat($("#yearFixed").text());
        formData.landCostPriceUnit = parseFloat($("#landCostPriceUnit").text());
        formData.landUseBhou = parseFloat($("#landUseBhou").text());
        formData.landAcquisitionUnit = parseFloat($("#landAcquisitionUnit").text());
        formData.landProductionProfitUnit = parseFloat($("#landProductionProfitUnit").text());
        formData.landProductionInterestUnit = parseFloat($("#landProductionInterestUnit").text());
        formData.landProductionProfitBhou = parseFloat($("#landProductionProfitBhou").text());
        formData.ploughArearatio = subPointToPercent($("#ploughArearatio").text());
        formData.noPloughArearatio = subPointToPercent($("#noPloughArearatio").text());
        formData.yearFixed = subPointToPercent($("#yearFixed").text());
        formData.landAcquisitionBhou = parseFloat($("#landAcquisitionBhou").text());
        formData.parcelBhou = parseFloat($("#parcelBhou").text());
        var costApproachTaxes = [];
        $.each($("#master").find("#tbodyContent").find("tr"), function (i, n) {
            var item = getApproachTaxesData($(n));
            costApproachTaxes.push(item);
        });
        var item = {};
        item.master = formData;
        item.costApproachTaxes = costApproachTaxes;
        return item;
    }

    function subPointToPercent(value) {
        if (value) {
            return parseFloat(value) / 100;
        } else {
            return "";
        }
    }

    function calculationNumeric(that) {
        var formData = formParams("master");
        var formDataOther = formParams("master_other");
        formDataOther.id = $("#master").find("input[name=id]").val();
        formDataOther.farmlandArea = formData.farmlandArea;
        formDataOther.ploughArea = formData.ploughArea;
        formDataOther.populationNumber = formData.populationNumber;
        formDataOther.plotRatioElementAmend = $("#plotRatioElementAmend").attr("data-value");
        formDataOther.calculatedInterest = AssessCommon.percentToPoint($("#calculatedInterest").val());
        formDataOther.circulationExpense = $("#circulationExpense").val();
        formDataOther.profitMargin = AssessCommon.percentToPoint($("#profitMargin").val());
        formDataOther.incrementalBenefit = AssessCommon.percentToPoint($("#incrementalBenefit").val());
        formDataOther.plotRatioAdjust = AssessCommon.percentToPoint($("#plotRatioAdjust").val());
        formDataOther.rewardRate = AssessCommon.percentToPoint($("#rewardRate").val());
        // console.log(formDataOther);
        $.ajax({
            url: getContextPath() + "/costApproach/calculationNumeric",
            type: "post",
            data: {
                fomData: JSON.stringify(formDataOther)
            },
            success: function (result) {
                var data = result.data;
                if (data) {
                    // console.log(data);
                    if (data.ploughArearatio) {
                        $("#ploughArearatio").text(AssessCommon.pointToPercent(data.ploughArearatio));
                    }
                    if (data.noPloughArearatio) {
                        $("#noPloughArearatio").text(AssessCommon.pointToPercent(data.noPloughArearatio));
                    }
                    if (data.landAcquisitionBhou) {
                        $("#landAcquisitionBhou").text(data.landAcquisitionBhou);
                    }
                    if (data.landAcquisitionUnit) {
                        $("#landAcquisitionUnit").text(data.landAcquisitionUnit);
                    }
                    if (data.landProductionBhou) {
                        $("#landProductionBhou").text(data.landProductionBhou);
                    }
                    if (data.landProductionUnit) {
                        $("#landProductionUnit").text(data.landProductionUnit);
                    }
                    if (data.landProductionInterestBhou) {
                        $("#landProductionInterestBhou").text(data.landProductionInterestBhou);
                    }
                    if (data.landProductionInterestUnit) {
                        $("#landProductionInterestUnit").text(data.landProductionInterestUnit);
                    }
                    if (data.landProductionProfitBhou) {
                        $("#landProductionProfitBhou").text(data.landProductionProfitBhou);
                    }
                    if (data.landProductionProfitUnit) {
                        $("#landProductionProfitUnit").text(data.landProductionProfitUnit);
                    }
                    if (data.landCostPriceBhou) {
                        $("#landCostPriceBhou").text(data.landCostPriceBhou);
                    }
                    if (data.landCostPriceUnit) {
                        $("#landCostPriceUnit").text(data.landCostPriceUnit);
                    }
                    if (data.landUseBhou) {
                        $("#landUseBhou").text(data.landUseBhou);
                    }
                    if (data.landUsePrice) {
                        $("#landUseUnit").text(data.landUsePrice);
                    }
                    if (data.priceCorrectionBhou) {
                        $("#priceCorrectionBhou").text(data.priceCorrectionBhou);
                    }
                    if (data.priceCorrectionUnit) {
                        $("#priceCorrectionUnit").text(data.priceCorrectionUnit);
                    }
                    if (data.plotRatioElementAmendBhou) {
                        $("#plotRatioElementAmendBhou").text(data.plotRatioElementAmendBhou);
                    }
                    if (data.plotRatioElementAmendUnit) {
                        $("#plotRatioElementAmendUnit").text(data.plotRatioElementAmendUnit);
                    }
                    if (data.plotRatioAdjustBhou) {
                        $("#plotRatioAdjustBhou").text(data.plotRatioAdjustBhou);
                    }
                    if (data.plotRatioAdjustUnit) {
                        $("#plotRatioAdjustUnit").text(data.plotRatioAdjustUnit);
                    }
                    if (data.parcelUnit) {
                        $("#parcelUnit").text(data.parcelUnit);
                    }
                    if (data.parcelBhou) {
                        $("#parcelBhou").text(data.parcelBhou);
                    }
                    if (data.yearFixed) {
                        $("#yearFixed").text(AssessCommon.pointToPercent(data.yearFixed));
                    }

                }
            }
        })
    }


</script>