<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建设周期</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" required="required"
                       placeholder="建设周期" class="form-control" name="">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">已开发时间</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" required="required"
                       placeholder="已开发时间" class="form-control" name="">
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

    <div class="hypothesisDevelopmentModel" style="display: none;">
        <jsp:include page="../architecturalEngineering/hypothesisDevelopment.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="hypothesisDevelopment.constructionInstallationEngineeringFee.close();">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="hypothesisDevelopment.constructionInstallationEngineeringFee.getDataAndWriteHtml();">
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
            开发建筑面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="开发建筑面积" class="form-control" name="developmentBuildArea">
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
            重置价格
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="重置价格" class="form-control" name="replacementValue">
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


    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            销售费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="销售费" class="form-control" name="salesFee">
            </div>
        </div>

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
                       placeholder="增值及附加税金" class="form-control" name="addedValueAdditionalTaxRate">
            </div>
        </div>

    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            计息周期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="计息周期" class="form-control" name="interestPeriod">
            </div>
        </div>

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

    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资利息
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利息" class="form-control" name="interestInInvestment">
            </div>
        </div>

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

    <div class="form-group">
        <label class="col-sm-1 control-label">
            地价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="地价" class="form-control" name="landPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            委估土地单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="委估土地单价" class="form-control" name="valuationLandUnitPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            年期修正
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="年期修正" class="form-control" name="landPrice">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            权利状况修正
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="权利状况修正" class="form-control" name="statusRightsRevision">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            其它修正
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="其它修正" class="form-control" name="otherRevision">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发程度修正
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="开发程度修正" class="form-control" name="developmentDegreeRevision">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地还原率
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="土地还原率" class="form-control" name="landReductionRate">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            法定年限
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="法定年限" class="form-control" name="legalPeriod">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            剩余年限
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="剩余年限" class="form-control" name="remainingYear">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            评估单价
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="评估单价" class="form-control" name="evaluationPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            楼面地价
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="楼面地价" class="form-control" name="floorPrice">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            市场价格预测
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <textarea class="form-control" placeholder="市场价格预测"></textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            用地类别对房地产价格影响
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <textarea class="form-control" placeholder="用地类别对房地产价格影响"></textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            开发程度修正说明
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <textarea class="form-control" placeholder="开发程度修正说明"></textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            权力状况修正
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <textarea class="form-control" placeholder="权力状况修正"></textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            其他修正
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <textarea class="form-control" placeholder="其他修正"></textarea>
            </div>
        </div>
    </div>

</form>



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
        var str = obj.replace("%", "");
        str = str / 100;
        return str;
    }
    //小数转百分数
    hypothesisDevelopment.toPercent = function (point) {
        var str = Number(point * 100).toFixed(1);
        str += "%";
        return str;
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
        config.architecturalEngineeringModel = "hypothesisDevelopmentModel";
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
                replacementValue: {key: "replacementValue", describe: "重置价格"},
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
                valuationLandUnitPrice: {key: "valuationLandUnitPrice", describe: "委估土地单价"},
                landPrice: {key: "landPrice", describe: "地价"},
                yearRevision: {key: "yearRevision", describe: "年起修正"},
                statusRightsRevision: {key: "statusRightsRevision", describe: "权利状况修正"},
                otherRevision: {key: "otherRevision", describe: "其它修正"},
                developmentDegree: {key: "developmentDegreeRevision", describe: "开发程度修正"},
                landReductionRate: {key: "landReductionRate", describe: "土地还原率"},
                legalPeriod: {key: "legalPeriod", describe: "法定年限"},
                remainingYear: {key: "remainingYear", describe: "剩余年限"},
                evaluationPrice: {key: "evaluationPrice", describe: "评估单价"},
                floorPrice: {key: "floorPrice", describe: "楼面单价"},
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
        constructionInstallationEngineeringFeeInput: function (data) {
            hypothesisDevelopment.inputAlgorithmObject.reconnaissanceDesignFun();
            hypothesisDevelopment.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignRoteInput: function () {
            console.log("test");
            hypothesisDevelopment.inputAlgorithmObject.reconnaissanceDesignFun();
            console.log("test1");
        },
        //基础设施建设费 单价选择
        infrastructureCostSelectInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.infrastructureCostFun();
        },
        //公共配套设施建设费 单价选择
        infrastructureMatchingCostSelectInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.infrastructureMatchingCostFun();
        },
        //开发建筑面积
        developmentBuildAreaInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.infrastructureCostFun();
            hypothesisDevelopment.inputAlgorithmObject.infrastructureMatchingCostFun();
            hypothesisDevelopment.inputAlgorithmObject.devDuringPriceTaxFun();
            hypothesisDevelopment.inputAlgorithmObject.otherEngineeringCostFun();
        },
        //开发期间单价
        devDuringPriceInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.devDuringPriceTaxFun();
        },
        //其它工程费单价
        otherEngineeringCostPriceInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.otherEngineeringCostFun();
        },
        //管理费率
        managementExpenseRoteInput:function () {
            hypothesisDevelopment.inputAlgorithmObject.managementExpenseFun();//管理费
        },
        //  增值及附加税率
        addedValueAdditionalTaxRateSelectInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.addedValueAdditionalTaxRateFun();//增值及附加税金
        },
        //不可预见费率
        unforeseenExpensesRoteInput:function () {
            hypothesisDevelopment.inputAlgorithmObject.unforeseenExpensesFun();//不可预见费
        },
        //重置价格
        replacementValueInput:function () {
            hypothesisDevelopment.inputAlgorithmObject.salesFeeRoteFun();//销售费
            hypothesisDevelopment.inputAlgorithmObject.addedValueAdditionalTaxRateFun();//增值及附加税金
        },
        //销售费率
        salesFeeRoteInput:function () {
            hypothesisDevelopment.inputAlgorithmObject.salesFeeRoteFun();//销售费
        },
    }
    hypothesisDevelopment.inputAlgorithmObject = {
        //销售费 = 重置价格*销售费率
        salesFeeRoteFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().salesFeeRote.key, null);//销售费率
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().replacementValue.key, null);//重置价格
            c = hypothesisDevelopment.mul(a,b);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().salesFee.key, c);//销售费
        },
        //不可预见费 = （建设成本+管理费金额）*不可预见费率
        unforeseenExpensesFun:function () {
            var a = 0, b = 0, c = 0 ,d = 0; ;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().constructionCost.key, null);//建设成本
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().managementExpense.key, null);//管理费
            c = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().unforeseenExpensesRote.key, null);//不可预见费率
            d = hypothesisDevelopment.add(a,b);
            d = hypothesisDevelopment.mul(d,c);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().unforeseenExpenses.key,d);//不可预见费
        },
        //管理费 = 建设成本*管理费率
        managementExpenseFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().constructionCost.key, null);//建设成本
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().managementExpenseRote.key, null);//管理费率
            c = hypothesisDevelopment.mul(a,b);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().managementExpense.key, c);//管理费
            hypothesisDevelopment.inputAlgorithmObject.unforeseenExpensesFun();//不可预见费
        },
        //建设费成本 = 前期工程费+安装工程费+基础设施费+公共设施费+开发期间税费+其它工程费
        constructionCostFun: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().reconnaissanceDesign.key, null);//勘察设计和前期工程费
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            c = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().infrastructureCost.key, null);//基础设施费
            d = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().infrastructureMatchingCost.key, null);//公共设施费
            e = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().devDuringPriceTax.key, null);//开发期间税费
            f = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().otherEngineeringCost.key, null);//其它工程费
            var value = 0;
            value = hypothesisDevelopment.add(hypothesisDevelopment.add(a, b), hypothesisDevelopment.add(c, d));
            value = hypothesisDevelopment.add(hypothesisDevelopment.add(e, f), value);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().constructionCost.key, c);//建设成本
            hypothesisDevelopment.inputAlgorithmObject.managementExpenseFun();//管理费
        },
        //其它工程费 = 其它工程费单价  * 开发建筑面积
        otherEngineeringCostFun: function () {
            var a = 0, b = 0, c = 0;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().otherEngineeringCostPrice.key, null);//其它工程费单价
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().otherEngineeringCost.key, c);//其它工程费
            hypothesisDevelopment.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //增值及附加税金
        addedValueAdditionalTaxRateFun: function () {
            var a = 0, b = 0, c = 0;
            a = $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().addedValueAdditionalTaxRate.select).eq(1).val();//增值及附加税率
            AssessCommon.getDataDicInfo(a, function (data) {
                if (hypothesisDevelopment.isNotNull(data)) {
                    try {
                        a = hypothesisDevelopment.toPoint(data.name);
                    } catch (e) {
                        a = 0;
                    }
                    b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().replacementValue.key, null);//重置价格
                    c = hypothesisDevelopment.mul(a,b);
                    console.log("a:" + a + "; b:" + b + "; c:" + c);
                    // run method
                    hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().addedValueAdditionalTaxRate.key, c);//增值及附加税金
                }
            });
        },
        //开发期间税费
        devDuringPriceTaxFun: function () {
            var a = 0, b = 0, c = 0;
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().devDuringPrice.key, null);//开发期间单价
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().devDuringPriceTax.key, c);//开发期间税费
            hypothesisDevelopment.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //公共配套设施建设费
        infrastructureMatchingCostFun: function () {
            var a = 0, b = 0, c = 0;
            a = $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().infrastructureMatchingCost.select).eq(1).val();//公共设施单价
            a = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(a);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().infrastructureMatchingCost.key, c);//公共设施费
            hypothesisDevelopment.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //基础设施建设费
        infrastructureCostFun: function () {
            var a = 0, b = 0, c = 0;
            a = $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().infrastructureCost.select).eq(1).val();//基础设施单价
            a = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(a);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().infrastructureCost.key, c);//基础设施费
            hypothesisDevelopment.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //f勘察设计和前期工程费 = 建筑安装工程费*勘察设计和前期工程费率
        reconnaissanceDesignFun: function () {
            console.log("test2");
            var a = 0, b = 0, c = 0;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().reconnaissanceDesignRote.key, null);//勘察设计和前期工程费率
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            c = hypothesisDevelopment.mul(b, a);
            console.log("a:" + a + "; b:" + b + "; c:" + c);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().reconnaissanceDesign.key, c);//勘察设计和前期工程费
            hypothesisDevelopment.inputAlgorithmObject.constructionCostFun();//建设成本
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
            console.log("jqueryInputGetAndSet");
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
                    console.log("key:" + key);
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

    hypothesisDevelopment.constructionInstallationEngineeringFee = {
        event: function () {
            $("."+hypothesisDevelopment.config().frm+" ."+hypothesisDevelopment.config().architecturalEngineeringModel).show();
            $(function () {
                hypothesisDevelopmentBuild.viewInit();
            });
        },
        close: function () {
            $("."+hypothesisDevelopment.config().frm+" ."+hypothesisDevelopment.config().architecturalEngineeringModel).hide();
        },
        getDataAndWriteHtml:function () {
            var data = hypothesisDevelopmentBuild.getCalculatedResults();
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().constructionInstallationEngineeringFee.key,data);//建筑安装工程费
            hypothesisDevelopment.inputFun.constructionInstallationEngineeringFeeInput(data);
            hypothesisDevelopment.constructionInstallationEngineeringFee.close();
            hypothesisDevelopment.constructionInstallationEngineeringFee.saveAndUpdate();
        },
        saveAndUpdate:function () {

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
            loadAddedValueAdditionalTaxRate: function () {
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
            loadCostAndMatchingCost: function () {
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
            //监听change 并且处理显示问题
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
            // 基础设施建设费 单价选择
            infrastructureCost: function () {
                var key = hypothesisDevelopment.config().inputConfig().infrastructureCost;
                $("." + hypothesisDevelopment.config().frm + " ." + key.select).change(function () {
                    var value = $("." + hypothesisDevelopment.config().frm + " ." + key.select).eq(1).val();
                    var funName = "hypothesisDevelopment.inputFun." + key.select + "Input(" + value + ")";
                    try {
                        eval(funName);
                    } catch (e) {
                        console.log("没有这个函数");
                    }
                });
            },
            //公共配套设施建设费 单价选择
            infrastructureMatchingCost: function () {
                var key = hypothesisDevelopment.config().inputConfig().infrastructureMatchingCost;
                $("." + hypothesisDevelopment.config().frm + " ." + key.select).change(function () {
                    var value = $("." + hypothesisDevelopment.config().frm + " ." + key.select).eq(1).val();
                    var funName = "hypothesisDevelopment.inputFun." + key.select + "Input(" + value + ")";
                    try {
                        eval(funName);
                    } catch (e) {
                        console.log("没有这个函数");
                    }
                });
            },
            //增值及附加税率
            addedValueAdditionalTaxRate: function () {
                var key = hypothesisDevelopment.config().inputConfig().addedValueAdditionalTaxRate;
                $("." + hypothesisDevelopment.config().frm + " ." + key.select).change(function () {
                    var value = $("." + hypothesisDevelopment.config().frm + " ." + key.select).eq(1).val();
                    var funName = "hypothesisDevelopment.inputFun." + key.select + "Input(" + value + ")";
                    try {
                        console.log(funName);
                        eval(funName);
                    } catch (e) {
                        console.log("没有这个函数" + e);
                    }
                });
            },
            monitor: function () {
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2();
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2Type();
                hypothesisDevelopment.selectEvent.monitor.infrastructureCost();
                hypothesisDevelopment.selectEvent.monitor.infrastructureMatchingCost();
                hypothesisDevelopment.selectEvent.monitor.addedValueAdditionalTaxRate();
            }
        }
    };
    hypothesisDevelopment.eventInit = function () {
        $("#" + hypothesisDevelopment.config().frm).validate();
        hypothesisDevelopment.selectEvent.load.load();
        hypothesisDevelopment.inputEvent();
    };
    //初始化
    $(function () {
        hypothesisDevelopment.eventInit();
    });
</script>