<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../architecturalEngineering/constructionInstallationEngineeringFeeA.jsp"></jsp:include>

<form class="frmDevelopment form-horizontal" id="frmDevelopment">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建筑类别</label>
            <div class="col-md-2 col-sm-2">
                <select name="hypothesisDevelopmentSelect2"
                        class="form-control search-select select2 hypothesisDevelopmentSelect2">
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建筑类型</label>
            <div class="col-md-2 col-sm-2">
                <select name="hypothesisDevelopmentSelect2Type"
                        class="form-control search-select select2 hypothesisDevelopmentSelect2Type">
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预计销售建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预计销售建筑面积" class="form-control" name="estimateBuildSaleArea" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预计销售合计</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预计销售合计" class="form-control" name="estimateSaleTotal" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预计销售可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预计销售可售面积" class="form-control" name="estimateMaySaleArea" readonly="readonly">
            </div>
        </div>
        <input type="hidden"
               placeholder="不可销售面积" name="mayNotSaleArea" class="mayNotSaleArea" value="22.1">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">总建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="总建筑面积" class="form-control" name="totalBuildArea" readonly="readonly">
            </div>
        </div>
    </div>
    <!-- 引入类型form表单 -->
    <div class="baseFrmDevelopment">
        <jsp:include page="fromDevelopment.jsp"></jsp:include>
    </div>

    <div class="form-group">

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费"
                       class="form-control" name="reconnaissanceDesign" readonly="readonly">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费率" class="form-control" data-rule-number='true' required="required"
                       name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       onclick="hypothesisDevelopment.constructionInstallationEngineeringFee.event();"
                       placeholder="建筑安装工程费" class="form-control" name="constructionInstallationEngineeringFee">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureCostSelect"
                        class="form-control search-select select2 infrastructureCostSelect">
                </select>
            </div>
        </div>

        <label class="col-sm-1 control-label">
            基础设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="基础设施建设费" class="form-control" name="infrastructureCost">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            公共配套设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureMatchingCostSelect"
                        class="form-control search-select select2 infrastructureMatchingCostSelect">
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            公共配套设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="公共配套设施建设费" class="form-control" name="infrastructureMatchingCost">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期间单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="开发期间单价" class="form-control" name="devDuringPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期间税费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="开发期间税费" class="form-control" name="devDuringPriceTax">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            其它工程费单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="其它工程费单价" class="form-control" name="otherEngineeringCostPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            其它工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="其它工程费" class="form-control" name="otherEngineeringCost">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建设成本
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="建设成本" class="form-control" name="constructionCost">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            管理费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="管理费" class="form-control" name="managementExpense">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            管理费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="管理费率" class="form-control" name="managementExpenseRote">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            不可预见费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="不可预见费率" class="form-control" name="unforeseenExpensesRote">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            不可预见费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="不可预见费" class="form-control" name="unforeseenExpenses">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            销售费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="销售费率" class="form-control" name="salesFeeRote">
            </div>
        </div>


        <label class="col-sm-1 control-label">
            销售费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="销售费" class="form-control" name="salesFee">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            增值及附加税率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="addedValueAdditionalTaxRate"
                        class="form-control search-select select2 addedValueAdditionalTaxRateSelect">
                </select>
            </div>
        </div>

        <label class="col-sm-1 control-label">
            增值及附加税金
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="增值及附加税金" class="form-control" name="valueAddedAdditionalTaxes">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            计息周期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="计息周期" class="form-control" name="interestPeriod">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资计息利率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="投资计息利率" class="form-control" name="interestRateOnInvestment">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            投资计息税率修正
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资计息税率修正" class="form-control" name="interestRateOnInvestmentCorrect">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            投资利息
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利息" class="form-control" name="interestInInvestment">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资利润
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利润" class="form-control" name="investmentProfit">
            </div>
        </div>
    </div>
</form>

<div id="" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-dialog" role="document" style="width: 1000px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title">建设工程费用</h3>
                </div>
                <form class="form-horizontal">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="panel-body">
                                    <div>
                                        <%--<table id="constructionInstallationEngineeringFeeA">--%>

                                        <%--</table>--%>
                                        <%--<table cellpadding="5" class="table">--%>
                                        <%--<thead>--%>
                                        <%--<tr>--%>
                                        <%--<td>建安成本小计</td>--%>
                                        <%--<td>建筑面积（㎡）</td>--%>
                                        <%--<td>单方造价(元/㎡)</td>--%>
                                        <%--<td>总造价（万元）</td>--%>
                                        <%--<td>估价时点总价（万元)</td>--%>
                                        <%--<td>续建投入总价(万元)</td>--%>
                                        <%--</tr>--%>
                                        <%--</thead>--%>
                                        <%--<tbody>--%>
                                        <%--<tr class="info">--%>
                                        <%--<td><label class="control-label">计算值:</label></td>--%>
                                        <%--<td><label--%>
                                        <%--class="control-label constructionInstallationEngineeringFeeBAreaClassA">0</label>--%>
                                        <%--</td>--%>
                                        <%--<td><label--%>
                                        <%--class="control-label constructionInstallationEngineeringFeeBCurrencyClassA">0</label>--%>
                                        <%--</td>--%>
                                        <%--<td><label--%>
                                        <%--class="control-label constructionInstallationEngineeringFeeBTotalCostClassA">0</label>--%>
                                        <%--</td>--%>
                                        <%--<td><label class="control-label valuationDateTotalClassA">0</label></td>--%>
                                        <%--<td><label--%>
                                        <%--class="control-label continuedConstructionInvestmentTotalClassA">0</label>--%>
                                        <%--</td>--%>
                                        <%--</tr>--%>
                                        <%--</tbody>--%>
                                        <%--</table>--%>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn btn-default">
                            取消
                        </button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var hypothesisDevelopment = new Object();
    hypothesisDevelopment.setFlagK = function (obj) {
        this.flagK = obj;
    }
    hypothesisDevelopment.getFlagK = function () {
        this.flagK = true;
        return this.flagK;
    }
    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:除法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        return result;
    }
    //判断是否为数字
    hypothesisDevelopment.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    //百分数转小数
    hypothesisDevelopment.toPoint = function (obj) {
        return AssessCommon.toPoint(obj);
    }
    //小数转百分数
    hypothesisDevelopment.toPercent = function (obj) {
        return AssessCommon.toPercent(obj);
    }
    hypothesisDevelopment.isNotNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    hypothesisDevelopment.formType = {
        build: {
            cycle: "cycleBuild",
            build: "build",
            describe: "建设周期"
        },
        business: {
            operate: "operateBusiness",
            strategy: "strategyBusiness",
            business: "business",
            describe: "商业"
        },
        residence: {
            residence: "residence",
            ordinary: "ordinaryResidence",
            apartment: "apartmentResidence",
            villa: "villaResidence",
            describe: "住宅"
        },
        undergroundBusiness: {
            shop: "undergroundBusinessShop",
            undergroundBusiness: "undergroundBusiness",
            describe: "地下商业"
        },
        garage: {
            big: "bigGarage",
            small: "smallGarage",
            garage: "garage",
            describe: "车库类型"
        },
        work: {
            close: "closeWork",
            open: "openWork",
            scenery: "sceneryWork",
            unit: "unitWork",
            work: "work",
            describe: "办公建筑类型"
        },
        base: {
            unitPrice: "UnitPrice", /* 单位售价 */
            totalPrice: "TotalPrice", /* 合价 */
            maySaleArea: "MaySaleArea", /* 可售面积 */
            buildArea: "BuildArea", /* 建筑面积 */
            describe: "类型基础配置"
        }
    }
    hypothesisDevelopment.config = function () {
        var config = {};
        config.frm = "frmDevelopment";//表单id
        config.baseFrm = "baseFrmDevelopment";
        config.architecturalEngineeringModel = "architecturalEngineering";
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        config.inputConfig = function () {
            return {
                constructionInstallationEngineeringFee: {
                    key: "constructionInstallationEngineeringFee",
                    describe: "建筑安装工程费",
                    value: "",
                    select: ""
                },
                hypothesisDevelopmentSelect2: {
                    key: "hypothesisDevelopmentSelect2",
                    describe: "建筑类别",
                    value: "",
                    select: "hypothesisDevelopmentSelect2"
                },
                hypothesisDevelopmentSelect2Type: {
                    key: "hypothesisDevelopmentSelect2Type",
                    describe: "建筑类型",
                    value: "",
                    select: "hypothesisDevelopmentSelect2Type"
                },
                estimateSaleTotal: {key: "estimateSaleTotal", describe: "预计销售合计"},
                estimateMaySaleArea: {key: "estimateMaySaleArea", describe: "预计销售可售面积"},
                estimateBuildSaleArea: {key: "estimateBuildSaleArea", describe: "预计销售建筑面积"},
                totalBuildArea: {key: "totalBuildArea", describe: "总建筑面积"},
                mayNotSaleArea: {key: "mayNotSaleArea", describe: "不可销售面积"},
                constructionCycle: {key: "constructionCycle", describe: "建设周期", value: "", select: ""},
                developedTime: {key: "developedTime", describe: "开发周期", value: "", select: ""},

                smallGarageUnitPrice: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.unitPrice},
                smallGarageTotalPrice: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.totalPrice},
                smallGarageMaySaleArea: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.maySaleArea},
                smallGarageBuildArea: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.buildArea},
                bigGarageUnitPrice: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.unitPrice},
                bigGarageTotalPrice: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.totalPrice},
                bigGarageMaySaleArea: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.maySaleArea},
                bigGarageBuildArea: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.buildArea},

                strategyBusinessUnitPrice: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.unitPrice},
                strategyBusinessTotalPrice: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.totalPrice},
                strategyBusinessMaySaleArea: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.maySaleArea},
                strategyBusinessBuildArea: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.buildArea},
                operateBusinessUnitPrice: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.unitPrice},
                operateBusinessTotalPrice: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.totalPrice},
                operateBusinessMaySaleArea: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.maySaleArea},
                operateBusinessBuildArea: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.buildArea},

                undergroundBusinessShopUnitPrice: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.unitPrice},
                undergroundBusinessShopTotalPrice: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.totalPrice},
                undergroundBusinessShopMaySaleArea: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.maySaleArea},
                undergroundBusinessShopBuildArea: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.buildArea},


                villaResidenceUnitPrice: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.unitPrice},
                villaResidenceTotalPrice: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.totalPrice},
                villaResidenceMaySaleArea: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.maySaleArea},
                villaResidenceBuildArea: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.buildArea},
                apartmentResidenceUnitPrice: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.unitPrice},
                apartmentResidenceTotalPrice: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.totalPrice},
                apartmentResidenceMaySaleArea: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.maySaleArea},
                apartmentResidenceBuildArea: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.buildArea},
                ordinaryResidenceUnitPrice: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.unitPrice},
                ordinaryResidenceTotalPrice: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.totalPrice},
                ordinaryResidenceMaySaleArea: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.maySaleArea},
                ordinaryResidenceBuildArea: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.buildArea},

                closeWorkUnitPrice: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.unitPrice},
                closeWorkTotalPrice: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.totalPrice},
                closeWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.maySaleArea},
                closeWorkBuildArea: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.buildArea},
                openWorkUnitPrice: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.unitPrice},
                openWorkTotalPrice: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.totalPrice},
                openWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.maySaleArea},
                openWorkBuildArea: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.buildArea},
                unitWorkUnitPrice: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.unitPrice},
                unitWorkTotalPrice: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.totalPrice},
                unitWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.maySaleArea},
                unitWorkBuildArea: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.buildArea},
                sceneryWorkUnitPrice: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.unitPrice},
                sceneryWorkTotalPrice: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.totalPrice},
                sceneryWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.maySaleArea},
                sceneryWorkBuildArea: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.buildArea},

                reconnaissanceDesignRote: {key: "reconnaissanceDesignRote", describe: "勘察设计和前期工程费率"},
                reconnaissanceDesign: {key: "reconnaissanceDesign", describe: "勘察设计和前期工程费"},
                infrastructureCost: {
                    key: "infrastructureCost",//infrastructureCost 算是一个input
                    describe: "基础设施费用",
                    select: "infrastructureCostSelect"//infrastructureCostSelect 算是一个select (input)
                },
                infrastructureMatchingCost: {
                    key: "infrastructureMatchingCost",//infrastructureMatchingCost 算是一个input
                    describe: "公共配套设施费用",
                    select: "infrastructureMatchingCostSelect"//注意:infrastructureMatchingCostSelect算是一个select (input)
                },
                developmentBuildArea: {key: "developmentBuildArea", describe: "开发建筑面积", value: "", select: ""},
                devDuringPrice: {
                    key: "devDuringPrice",
                    describe: "开发期间单价", value: "", select: ""
                },
                devDuringPriceTax: {
                    key: "devDuringPriceTax",
                    describe: "开发期间单价税收", value: "", select: ""
                },
                otherEngineeringCost: {key: "otherEngineeringCost", describe: "其它工程费"},
                otherEngineeringCostPrice: {key: "otherEngineeringCostPrice", describe: "其它工程费单价"},
                constructionCost: {key: "constructionCost", describe: "建设成本"},
                managementExpense: {key: "managementExpense", describe: "管理费"},
                managementExpenseRote: {key: "managementExpenseRote", describe: "管理费率"},
                unforeseenExpenses: {key: "unforeseenExpenses", describe: "不可预见费"},
                unforeseenExpensesRote: {key: "unforeseenExpensesRote", describe: "不可预见费率"},
                salesFeeRote: {key: "salesFeeRote", describe: "销售费率"},
                salesFee: {key: "salesFee", describe: "销售费"},
                addedValueAdditionalTaxRate: {
                    key: "addedValueAdditionalTaxRate",
                    describe: "增值及附加税率",
                    select: "addedValueAdditionalTaxRateSelect"
                },
                valueAddedAdditionalTaxes: {key: "valueAddedAdditionalTaxes", describe: "增值及附加税金"},
                interestPeriod: {key: "interestPeriod", describe: "计息周期"},
                interestRateOnInvestment: {key: "interestRateOnInvestment", describe: "投资计息利率"},
                interestRateOnInvestmentCorrect: {key: "interestRateOnInvestmentCorrect", describe: "投资计息税率修正"},
                interestInInvestment: {key: "interestInInvestment", describe: "投资利息"},
                investmentProfit: {key: "investmentProfit", describe: "投资利润"},
            };
        };
        config.inputName = function () {
            var arr = new Array();
            //遍历对象
            $.each(hypothesisDevelopment.config().inputConfig(), function (i, n) {
                arr.push(n);
            })
            return arr;
        };
        return config;
    };
    hypothesisDevelopment.inputFun = {
        //建筑安装工程费
        constructionInstallationEngineeringFeeInput:function (data) {
            hypothesisDevelopment.inputAlgorithmObject.reconnaissanceDesignFun();
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignRoteInput:function () {
            console.log("sdsdg");
            hypothesisDevelopment.inputAlgorithmObject.reconnaissanceDesignFun();
        },
    }
    hypothesisDevelopment.inputAlgorithmObject = {
        //f勘察设计和前期工程费 = 建筑安装工程费*勘察设计和前期工程费率
        reconnaissanceDesignFun:function () {
            var a, c, b;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().reconnaissanceDesignRote.key, null);//勘察设计和前期工程费率
            c = hypothesisDevelopment.mul(b, a);
            console.log("a:"+a+"; b:"+b+"; c:"+c);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().reconnaissanceDesign.key, c);
        },
        //地下商业
        undergroundBusinessShopFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().undergroundBusinessShopMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().undergroundBusinessShopUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().undergroundBusinessShopTotalPrice.key, c);
            console.log("undergroundBusinessShopFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //闭式员工办公 算法
        closeWorkFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().closeWorkMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().closeWorkUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().closeWorkTotalPrice.key, c);
            console.log("closeWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        unitWorkFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().unitWorkMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().unitWorkUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().unitWorkTotalPrice.key, c);
            console.log("unitWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        openWorkFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().openWorkMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().openWorkUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().openWorkTotalPrice.key, c);
            console.log("openWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        sceneryWorkFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().sceneryWorkMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().sceneryWorkUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().sceneryWorkTotalPrice.key, c);
            console.log("sceneryWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        strategyBusinessFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().strategyBusinessMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().strategyBusinessUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().strategyBusinessTotalPrice.key, c);
            console.log("strategyBusinessFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        operateBusinessFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().operateBusinessMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().operateBusinessUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().operateBusinessTotalPrice.key, c);
            console.log("operateBusinessFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        ordinaryResidenceFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().ordinaryResidenceMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().ordinaryResidenceUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().ordinaryResidenceTotalPrice.key, c);
            console.log("ordinaryResidenceFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        apartmentResidenceFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().apartmentResidenceMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().apartmentResidenceUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().apartmentResidenceTotalPrice.key, c);
            console.log("apartmentResidenceFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        villaResidenceFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().villaResidenceMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().villaResidenceUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().villaResidenceTotalPrice.key, c);
            console.log("villaResidenceFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //大车库 算法
        bigGarageFun: function () {
            var a, b, c, d;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageBuildArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageMaySaleArea.key, null);
            c = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageUnitPrice.key, null);
            d = hypothesisDevelopment.mul(b, c);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().bigGarageTotalPrice.key, d);
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //小车库 算法
        smallGarageFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().smallGarageMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().smallGarageUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().smallGarageTotalPrice.key, c);
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        baseFormFun: function () {
            var key = null, data = null;
            var estimateBuildSaleArea = 0, estimateMaySaleArea = 0, estimateSaleTotal = 0, mayNotSaleArea = 0,
                totalBuildArea = 0;
            key = "." + hypothesisDevelopment.config().frm;
            key += " ." + hypothesisDevelopment.config().baseFrm;
            data = $(key + " :input");
            $.each(data, function (i, n) {
                var name = $(n).attr("name");
                var temp = 0;
                if (hypothesisDevelopment.indexOfUtils(name, hypothesisDevelopment.formType.base.maySaleArea)) {//可售面积
                    temp = $(n).val();
                    temp = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(temp);
                    estimateMaySaleArea = hypothesisDevelopment.add(estimateMaySaleArea, temp);
                }
                if (hypothesisDevelopment.indexOfUtils(name, hypothesisDevelopment.formType.base.buildArea)) {//建筑面积
                    temp = $(n).val();
                    temp = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(temp);
                    estimateBuildSaleArea = hypothesisDevelopment.add(estimateBuildSaleArea, temp);
                }
                if (hypothesisDevelopment.indexOfUtils(name, hypothesisDevelopment.formType.base.totalPrice)) {//合价
                    temp = $(n).val();
                    temp = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(temp);
                    estimateSaleTotal = hypothesisDevelopment.add(estimateSaleTotal, temp);
                }
            });
            /*预计销售建筑面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().estimateBuildSaleArea.key, estimateBuildSaleArea);
            /*预计销售可售面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().estimateMaySaleArea.key, estimateMaySaleArea);
            /*预计销售合计*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().estimateSaleTotal.key, estimateSaleTotal);
            //总建筑面积 = 预计销售建筑面积+不可销售面积
            mayNotSaleArea = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().mayNotSaleArea.key, null);
            totalBuildArea = hypothesisDevelopment.add(mayNotSaleArea, estimateBuildSaleArea);
            /*总建筑面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().totalBuildArea.key, totalBuildArea);
        },
        jqueryInputGetAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + name + "']").val();
                text = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + name + "']").val(data);
            }
        },
        isNotNull: function (obj) {
            if (obj == 0) {
                return true;
            }
            if (obj) {
                return true;
            }
            return false;
        },
        specialTreatment: function (obj) {
            if (hypothesisDevelopment.inputAlgorithmObject.isNotNull(obj)) {
                return obj;
            }
            return 0;
        }
    };

    /**
     * @author:  zch
     * 描述:判断某个字符串中是否包含某个字符串 (workdddg,WORK) return true
     * @date:
     **/
    hypothesisDevelopment.indexOfUtils = function (s, str) {
        if (s.indexOf(str) >= 0) {
            return true;
        }
        s = s.toUpperCase();
        str = str.toUpperCase();
        if (s.indexOf(str) >= 0) {
            return true;
        }
        return false;
    };
    hypothesisDevelopment.inputForm = function (key, value) {
        console.log("formType 进入@" + key);
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.garage)) {//属于车库
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.big)) {//大车库
                hypothesisDevelopment.inputAlgorithmObject.bigGarageFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.small)) {//小车库
                hypothesisDevelopment.inputAlgorithmObject.smallGarageFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.work)) {//属于办公
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.close)) {//闭式办公
                hypothesisDevelopment.inputAlgorithmObject.closeWorkFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.scenery)) {//
                hypothesisDevelopment.inputAlgorithmObject.sceneryWorkFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.unit)) {//
                hypothesisDevelopment.inputAlgorithmObject.unitWorkFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.open)) {//
                hypothesisDevelopment.inputAlgorithmObject.openWorkFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.business.business)) {//属于商业
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.business.strategy)) {//
                hypothesisDevelopment.inputAlgorithmObject.strategyBusinessFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.business.operate)) {//
                hypothesisDevelopment.inputAlgorithmObject.operateBusinessFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.undergroundBusiness.undergroundBusiness)) {//属于地下商业
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.undergroundBusiness.shop)) {//地下商业购物广场
                hypothesisDevelopment.inputAlgorithmObject.undergroundBusinessShopFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.residence)) {//属于住宅
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.ordinary)) {//
                hypothesisDevelopment.inputAlgorithmObject.ordinaryResidenceFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.apartment)) {//
                hypothesisDevelopment.inputAlgorithmObject.apartmentResidenceFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.villa)) {//
                hypothesisDevelopment.inputAlgorithmObject.villaResidenceFun();
            }
        }
    };
    hypothesisDevelopment.inputEvent = function () {
        var arr = hypothesisDevelopment.config().inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                try {
                    console.log("key:"+key);
                    var funName = "hypothesisDevelopment.inputFun." + key + "Input(" + input.val() + ")";
                    console.log(funName);
                    eval(funName);
                } catch (e) {
                    console.log("没有相关定义的函数或者是属于子表单");
                    hypothesisDevelopment.inputForm(key, value);
                }
            });
        })
        hypothesisDevelopment.selectEvent.monitor.monitor();
    }
    hypothesisDevelopment.selectEvent = {

    }

    hypothesisDevelopment.constructionInstallationEngineeringFee = {
        event: function () {
            hypothesisDevelopment.constructionInstallationEngineeringFee.open();
            $(function () {
                constructEngineeringObjectA.viewInit();
            });
        }
        ,
        open: function () {
            var key = hypothesisDevelopment.config().inputConfig().constructionInstallationEngineeringFee.key;
            var btn = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + key + "']").offset();
            var top = 3080 - btn.top;
            var left = 1104 - btn.left;
            console.log("top:" + top + "; left:" + left);
            $('#' + hypothesisDevelopment.config().architecturalEngineeringModel).window({
                modal: true, closable: true
            });
            $('#' + hypothesisDevelopment.config().architecturalEngineeringModel).window('open').window('resize', {
                top: -560,
                left: left,
                width: '1000px',
                height: "1000px"
            });
        },
        show: function () {
            $('#' + hypothesisDevelopment.config().architecturalEngineeringModel).modal("show");
        },
        close: function () {
            $("#" + hypothesisDevelopment.config().architecturalEngineeringModel).window("close");
        },
        submit:function () {
            var data = constructEngineeringObjectA.getCalculatedResults();
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set",hypothesisDevelopment.config().inputConfig().constructionInstallationEngineeringFee.key,data);
            hypothesisDevelopment.inputFun.constructionInstallationEngineeringFeeInput(data);
            hypothesisDevelopment.constructionInstallationEngineeringFee.close();
        }

    }

    hypothesisDevelopment.selectEvent = {
        load: {
            /**
            * @author:  zch
            * 描述:动态类型
            * @date:
            **/
            hypothesisDevelopmentSelect2: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.mdHypothesisDevelopment, "", function (html, data) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(data, function (i, item) {
                        retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                    });
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select).html(retHtml);
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select).select2();
                });
            },
            /**
            * @author:  zch
            * 描述:增值及附加税率
            * @date:
            **/
            loadAddedValueAdditionalTaxRate:function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.build_addedvalueadditionaltaxrate, "", function (html, data) {
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().addedValueAdditionalTaxRate.select).html(html);
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().addedValueAdditionalTaxRate.select).select2();//加载样式
                })
            },
            /**
            * @author:  zch
            * 描述:获取基础设施费用列表和公共配套设施费用
            * @date:
            **/
            loadCostAndMatchingCost:function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/marketCost/listCostAndMatchingCost",
                    type: "get",
                    data: {projectId: "${projectInfo.id}"},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var infrastructureVo = result.data.InfrastructureVo;
                            var optionA = "<option value=''>请选择</option>";
                            var optionB = "<option value=''>请选择</option>";
                            if (infrastructureVo.length > 0) {
                                var temp = null;
                                for (var i = 0; i < infrastructureVo.length; i++) {
                                    temp = infrastructureVo[i].temp + " (" + infrastructureVo[i].priceCost + ")";
                                    optionA += "<option value='" + infrastructureVo[i].priceCost + "'>" + temp + "</option>";
                                    temp = infrastructureVo[i].temp + " (" + infrastructureVo[i].priceMarch + ")";
                                    optionB += "<option value='" + infrastructureVo[i].priceMarch + "'>" + temp + "</option>";
                                }
                                $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().infrastructureCost.select).html(optionA);
                                $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().infrastructureCost.select).select2();
                                $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().infrastructureMatchingCost.select).html(optionB);
                                $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().infrastructureMatchingCost.select).select2();
                            }

                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            load: function () {
                hypothesisDevelopment.selectEvent.load.hypothesisDevelopmentSelect2();
                hypothesisDevelopment.selectEvent.load.loadAddedValueAdditionalTaxRate();
                hypothesisDevelopment.selectEvent.load.loadCostAndMatchingCost();
            }
        },
        //监听 change 事件
        monitor: {
            hypothesisDevelopmentSelect2: function () {
                var key = hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select;
                $("." + key).change(function () {
                    var pid = $("." + key).eq(1).val();
                    AssessCommon.loadDataDicByPid(pid, "", function (html, data) {
                        $("." + key + "Type").prev().remove();
                        $("." + key + "Type").empty();
                        if (hypothesisDevelopment.isNotNull(data)) {
                            $("." + key + "Type").html(html);
                            $("." + key + "Type").select2();
                        } else {
                            AssessCommon.getDataDicInfo(pid, function (data) {
                                if (hypothesisDevelopment.isNotNull(data)) {
                                    if (data.fieldName == 'build') {
                                        $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.formType.build.cycle).toggle();
                                    }
                                }
                            });
                        }
                    });
                });
            },
            hypothesisDevelopmentSelect2Type: function () {
                var key = hypothesisDevelopment.config().frm + " .";
                key += hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select;
                key += "Type";
                $("." + key).change(function () {
                    var value = $("." + key).eq(1).val();
                    AssessCommon.getDataDicInfo(value, function (data) {
                        if (hypothesisDevelopment.isNotNull(data)) {
                            $("." + hypothesisDevelopment.config().frm + " ." + data.fieldName).toggle();
                        }
                    });
                });

            },
            monitor: function () {
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2();
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2Type();
            }
        }
    };
    hypothesisDevelopment.eventInit = function () {
        $("#"+hypothesisDevelopment.config().frm).validate();
        hypothesisDevelopment.selectEvent.load.load();
        hypothesisDevelopment.inputEvent();
    };

    $(function () {
        hypothesisDevelopment.eventInit();
    });
</script>


<div id="architecturalEngineering" class="easyui-window" title="建筑工程费"
     data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
    <table id="constructionInstallationEngineeringFeeA">

    </table>
    <table class="table">
        <thead>
        <tr>
            <td>建安成本小计</td>
            <td>建筑面积（㎡）</td>
            <td>单方造价(元/㎡)</td>
            <td>总造价（万元）</td>
            <td>估价时点总价（万元)</td>
            <td>续建投入总价(万元)</td>
        </tr>
        <tr class="info">
            <td><label class="control-label">计算值:</label></td>
            <td><label
                    class="control-label constructionInstallationEngineeringFeeBAreaClassA">0</label>
            </td>
            <td><label
                    class="control-label constructionInstallationEngineeringFeeBCurrencyClassA">0</label>
            </td>
            <td><label
                    class="control-label constructionInstallationEngineeringFeeBTotalCostClassA">0</label>
            </td>
            <td><label class="control-label valuationDateTotalClassA">0</label></td>
            <td><label
                    class="control-label continuedConstructionInvestmentTotalClassA">0</label>
            </td>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td colspan="3" style="text-align: center;"><label class="btn btn-success" onclick="hypothesisDevelopment.constructionInstallationEngineeringFee.submit()">submit</label></td>
            <td colspan="3" style="text-align: center;"><label class="btn btn-default" onclick="hypothesisDevelopment.constructionInstallationEngineeringFee.close()">cancel</label></td>
        </tr>
        </tfoot>
    </table>
</div>


