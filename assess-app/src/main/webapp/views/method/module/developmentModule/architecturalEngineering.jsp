<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="frmArchitecturalEngineering form-horizontal" id="frmArchitecturalEngineering">
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
                       placeholder="建设周期" class="form-control" name="constructionCycle">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">已开发时间</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" required="required"
                       placeholder="已开发时间" class="form-control date-picker dbdate" pattern='yyyy-MM-dd' name="developedTime">
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
    <div class="baseFrmArchitecturalEngineering">
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
                <div class="input-group">
                    <input type="text" readonly="readonly"
                           placeholder="建筑安装工程费" class="form-control" name="constructionInstallationEngineeringFee">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择" onclick="architecturalObj.constructionInstallationEngineeringFee.event()">
                                            <i class="fa fa-search"></i>
                                            </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                                            <i class="fa fa-trash-o"></i>
                                            </button>
                    </span>
                </div>
            </div>
        </div>
    </div>

    <div class="architecturalEngineeringModel" style="display: none;">
        <jsp:include page="../architecturalEngineering/architecturalEngineeringM.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="architecturalObj.constructionInstallationEngineeringFee.close();">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="architecturalObj.constructionInstallationEngineeringFee.getDataAndWriteHtml();">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureCostSelect" required="required"
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
                <select name="infrastructureMatchingCostSelect" required="required"
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
            续建成本 (建设成本)
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="续建成本" class="form-control" name="constructionCost">
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

    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            续建管理费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="管理费" class="form-control" name="managementExpense">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            续建管理费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="管理费率" class="form-control" name="managementExpenseRote">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            续建不可预见费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required="required" data-rule-number='true'
                       placeholder="不可预见费率" class="form-control" name="unforeseenExpensesRote">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            续建不可预见费
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
                <select name="addedValueAdditionalTaxRateSelect" required="required"
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
                <input type="text" required="required" data-rule-number='true'
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
            开发利润率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="投资利润率" class="form-control" name="investmentProfitRote">
            </div>
        </div>

    </div>

    <div class="form-group">


        <label class="col-sm-1 control-label">
            投资利润率修正
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利润率修正" class="form-control" name="investmentProfitRoteRevision">
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
            权利状况修正
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="权利状况修正" class="form-control" name="statusRightsRevision">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            其它修正
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="其它修正" class="form-control" name="otherRevision">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发程度修正
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" data-rule-number='true' required="required"
                       placeholder="法定年限" class="form-control" name="legalPeriod">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            剩余年限
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required="required" data-rule-number='true'
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
    var ArchitecturalEngineeringObj = function () {

    };
    /**
     * @author:  zch
     * 描述:加法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:除法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:判断是否是数字
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    //百分数转小数
    ArchitecturalEngineeringObj.prototype.toPoint = function (obj) {
        var str = obj.replace("%", "");
        str = str / 100;
        return str;
    }
    //小数转百分数
    ArchitecturalEngineeringObj.prototype.toPercent = function (point) {
        var str = Number(point * 100).toFixed(1);
        str += "%";
        return str;
    }
    ArchitecturalEngineeringObj.prototype.isNotNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }

    ArchitecturalEngineeringObj.prototype.formType = {
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
    };
    ArchitecturalEngineeringObj.prototype.config = {
        frm: function () {
            return "frmArchitecturalEngineering";//表单id
        },
        baseFrm: function () {
            return "baseFrmArchitecturalEngineering";
        },
        architecturalEngineeringModel: function () {
            return "architecturalEngineeringModel";
        },
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        inputConfig: function () {
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

                smallGarageUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                smallGarageTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                smallGarageMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                smallGarageBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                bigGarageUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                bigGarageTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                bigGarageMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                bigGarageBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                strategyBusinessUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                strategyBusinessTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                strategyBusinessMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                strategyBusinessBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                operateBusinessUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                operateBusinessTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                operateBusinessMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                operateBusinessBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                undergroundBusinessShopUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                undergroundBusinessShopTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                undergroundBusinessShopMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                undergroundBusinessShopBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},


                villaResidenceUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                villaResidenceTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                villaResidenceMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                villaResidenceBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                apartmentResidenceUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                apartmentResidenceTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                apartmentResidenceMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                apartmentResidenceBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                ordinaryResidenceUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                ordinaryResidenceTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                ordinaryResidenceMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                ordinaryResidenceBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                closeWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                closeWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                closeWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                closeWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                openWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                openWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                openWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                openWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                unitWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                unitWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                unitWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                unitWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                sceneryWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                sceneryWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                sceneryWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                sceneryWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

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
                investmentProfitRote: {key: "investmentProfitRote", describe: "投资利润率"},
                investmentProfitRoteRevision: {key: "investmentProfitRoteRevision", describe: "投资利润率修正"},
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
        },
        inputName: function () {
            var arr = new Array();
            //遍历对象
            $.each(ArchitecturalEngineeringObj.prototype.config.inputConfig(), function (i, n) {
                arr.push(n);
            })
            return arr;
        }
    };


    var architecturalObj = new ArchitecturalEngineeringObj();

    architecturalObj.inputFun = {
        //建筑安装工程费
        constructionInstallationEngineeringFeeInput:function () {
            architecturalObj.inputAlgorithmObject.reconnaissanceDesignFun();
            architecturalObj.inputAlgorithmObject.constructionCostFun();//续建成本
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignRoteInput:function () {
            architecturalObj.inputAlgorithmObject.reconnaissanceDesignFun();
        },
        //基础设施建设费 单价选择
        infrastructureCostSelectInput: function () {
            architecturalObj.inputAlgorithmObject.infrastructureCostFun();//基础设施建设费
        },
        //公共配套设施建设费 单价选择
        infrastructureMatchingCostSelectInput: function () {
            architecturalObj.inputAlgorithmObject.infrastructureMatchingCostFun();//公共配套设施建设费
        },
        //开发建筑面积
        developmentBuildAreaInput:function () {
            architecturalObj.inputAlgorithmObject.infrastructureCostFun();//基础设施建设费
            architecturalObj.inputAlgorithmObject.infrastructureMatchingCostFun();//公共配套设施建设费
            architecturalObj.inputAlgorithmObject.devDuringPriceTaxFun();//开发期间税费
            architecturalObj.inputAlgorithmObject.otherEngineeringCostFun();//其它工程费
        },
        //开发期间单价
        devDuringPriceInput:function () {
            architecturalObj.inputAlgorithmObject.devDuringPriceTaxFun();//开发期间税费
        },
        //其它工程费单价
        otherEngineeringCostPriceInput:function () {
            architecturalObj.inputAlgorithmObject.otherEngineeringCostFun();//其它工程费
        },
        //续建管理费率
        managementExpenseRoteInput:function () {
            architecturalObj.inputAlgorithmObject.managementExpenseFun();//续建管理费
        },
        //续建不可预见费率
        unforeseenExpensesRoteInput:function () {
            architecturalObj.inputAlgorithmObject.unforeseenExpensesFun();//续建不可预见费
        },
        //重置价格
        replacementValueInput:function () {
            architecturalObj.inputAlgorithmObject.salesFeeRoteFun();//销售费
            architecturalObj.inputAlgorithmObject.addedValueAdditionalTaxRateFun();//增值及附加税金
        },
        //销售费率
        salesFeeRoteInput:function () {
            architecturalObj.inputAlgorithmObject.salesFeeRoteFun();//销售费
            architecturalObj.inputAlgorithmObject.investmentProfitRoteRevisionFun();// 投资利润率修正
        },
        //  增值及附加税率
        addedValueAdditionalTaxRateSelectInput: function () {
            architecturalObj.inputAlgorithmObject.addedValueAdditionalTaxRateFun();//增值及附加税金
        },
        //计息周期
        interestPeriodInput: function () {
            architecturalObj.inputAlgorithmObject.interestRateOnInvestmentCorrectFun();// 投资计息税率修正
        },
        //投资计息利率
        interestRateOnInvestmentInput: function () {
            architecturalObj.inputAlgorithmObject.interestRateOnInvestmentCorrectFun();// 投资计息税率修正
        },
        //开发利润率
        investmentProfitRoteInput:function () {
            architecturalObj.inputAlgorithmObject.investmentProfitRoteRevisionFun();// 投资利润率修正
        },
    };

    architecturalObj.inputAlgorithmObject = {
        //投资利润率修正 = 开发利润率*销售费率
        investmentProfitRoteRevisionFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().salesFeeRote.key,null);//销售费率
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().investmentProfitRote.key,null);//开发利润率
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().investmentProfitRoteRevision.key,c);//投资利润率修正
        },
        //投资计息税率修正 = (1+投资利息利率)^(计息周期/2)-1
        interestRateOnInvestmentCorrectFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().interestRateOnInvestment.key,null);//投资利息利率
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().replacementValue.key,null);//计息周期
            a = architecturalObj.add(a,1);
            b = architecturalObj.div(b/2) - 1;
            c= Math.pow(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().interestRateOnInvestmentCorrect.key,c);//投资计息税率修正
        },
        //增值及附加税金 = 重置价格*增值及附加税率
        addedValueAdditionalTaxRateFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().replacementValue.key,null);//重置价格
            var key = architecturalObj.config.inputConfig().addedValueAdditionalTaxRate;
            b = $("." + architecturalObj.config.frm() + " ." + key.select).eq(1).val();
            AssessCommon.getDataDicInfo(b, function (data) {
                if (architecturalObj.isNotNull(data)) {
                    try {
                        b = architecturalObj.toPoint(data.name);
                    } catch (e) {
                        b = 0;
                    }
                    c = architecturalObj.mul(a,b);
                    console.log("c:" +c+"; a:"+a+"; b:"+b);
                    architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", key.key,c);//增值及附加税金
                }
            });
        },
        //销售费 = 重置价格*销售费率
        salesFeeRoteFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().salesFeeRote.key,null);//销售费率
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().replacementValue.key,null);//重置价格
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().salesFee.key,c);//销售费
        },
        //续建不可预见费 = （续建成本+续建管理费）*续建不可预见费率
        unforeseenExpensesFun:function () {
            var a = 0, b = 0, c = 0 ;
            var value = 0;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().managementExpense.key,null);//续建管理费
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().constructionCost.key,null);//续建成本
            c = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().unforeseenExpensesRote.key,null);//续建不可预见费率
            value = architecturalObj.add(a,b);
            value = architecturalObj.mul(value,c);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().unforeseenExpenses.key,value);//续建不可预见费
        },
        //续建管理费 = 续建成本*续建管理费率
        managementExpenseFun:function () {
            var a = 0, b = 0, c = 0 ;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().constructionCost.key,null);//续建成本
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().managementExpenseRote.key,null);//续建管理费率
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().managementExpense.key,c);//续建管理费
            architecturalObj.inputAlgorithmObject.unforeseenExpensesFun();//续建不可预见费
        },
        //续建成本 = 前期工程费+安装工程费+基础设施费+公共设施费+开发期间税费+其它工程费
        constructionCostFun:function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().reconnaissanceDesign.key,null);//勘察设计和前期工程费
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().constructionInstallationEngineeringFee.key,null);//建筑安装工程费
            c = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().infrastructureCost.key,null);//基础设施建设费
            d = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().infrastructureMatchingCost.key,null);//公共设施费
            e = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().devDuringPriceTax.key,null);//开发期间税费
            f = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().otherEngineeringCost.key,null);//其它工程费
            var value = 0;
            value = architecturalObj.add(architecturalObj.add(a, b), architecturalObj.add(c, d));
            value = architecturalObj.add(architecturalObj.add(e, f), value);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().constructionCost.key,value);//续建成本
            architecturalObj.inputAlgorithmObject.managementExpenseFun();// 续建管理费
        },
        //其它工程费 = 其它工程费单价*开发建筑面积
        otherEngineeringCostFun:function () {
            var a = 0, b = 0, c = 0;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().otherEngineeringCostPrice.key, null);//其它工程费单价
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().otherEngineeringCost.key,c);//其它工程费
            architecturalObj.inputAlgorithmObject.constructionCostFun();//续建成本
        },
        //开发期间税费
        devDuringPriceTaxFun:function () {
            var a = 0, b = 0, c = 0;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().devDuringPrice.key, null);//开发期间单价
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().devDuringPriceTax.key,c);//开发期间税费
            architecturalObj.inputAlgorithmObject.constructionCostFun();//续建成本
        },
        //基础设施建设费
        infrastructureCostFun:function () {
            var a = 0, b = 0, c = 0;
            a = $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().infrastructureCost.select).eq(1).val();//基础设施建设费单价
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().developmentBuildArea.key, null);//开发建筑面积
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().infrastructureCost.key,c);//基础设施建设费
            architecturalObj.inputAlgorithmObject.constructionCostFun();//续建成本
        },
        //公共配套设施建设费
        infrastructureMatchingCostFun:function () {
            var a = 0, b = 0, c = 0;
            a = $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().infrastructureMatchingCost.select).eq(1).val();//公共配套设施建设费单价
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().developmentBuildArea.key, null);//开发建筑面积
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().infrastructureMatchingCost.key,c);//公共配套设施建设费
            architecturalObj.inputAlgorithmObject.constructionCostFun();//续建成本
        },
        //勘察设计和前期工程费
        reconnaissanceDesignFun:function () {
            var a = 0, b = 0, c = 0;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().reconnaissanceDesignRote.key, null);//勘察设计和前期工程费率
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().reconnaissanceDesign.key,c);//勘察设计和前期工程费
            architecturalObj.inputAlgorithmObject.constructionCostFun();//续建成本
        },
        undergroundBusinessShopFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().undergroundBusinessShopMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().undergroundBusinessShopUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().undergroundBusinessShopTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        closeWorkFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().closeWorkMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().closeWorkUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().closeWorkTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        unitWorkFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().unitWorkMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().unitWorkUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().unitWorkTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        openWorkFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().openWorkMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().openWorkUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().openWorkTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        sceneryWorkFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().sceneryWorkMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().sceneryWorkUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().sceneryWorkTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        operateBusinessFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().operateBusinessMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().operateBusinessUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().operateBusinessTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        strategyBusinessFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().strategyBusinessMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().strategyBusinessUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().strategyBusinessTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        ordinaryResidenceFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().ordinaryResidenceMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().ordinaryResidenceUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().ordinaryResidenceTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        apartmentResidenceFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().apartmentResidenceMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().apartmentResidenceUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().apartmentResidenceTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        villaResidenceFun:function () {
            var a, b, c ;
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().villaResidenceMaySaleArea.key, null);
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().villaResidenceUnitPrice.key, null);
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().villaResidenceTotalPrice.key,c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        //大车库 算法
        bigGarageFun: function () {
            var a, b, c, d;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().bigGarageBuildArea.key, null);
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().bigGarageMaySaleArea.key, null);
            c = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().bigGarageUnitPrice.key, null);
            d = architecturalObj.mul(b, c);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().bigGarageTotalPrice.key, d);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        //小车库 算法
        smallGarageFun: function () {
            var a, b, c;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().smallGarageMaySaleArea.key, null);
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().smallGarageUnitPrice.key, null);
            c = architecturalObj.mul(b, a);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().smallGarageTotalPrice.key, c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        baseFormFun: function () {
            var key = null, data = null;
            var estimateBuildSaleArea = 0, estimateMaySaleArea = 0, estimateSaleTotal = 0, mayNotSaleArea = 0,
                totalBuildArea = 0;
            key = "." + architecturalObj.config.frm();
            key += " ." + architecturalObj.config.baseFrm();
            data = $(key + " :input");
            $.each(data, function (i, n) {
                var name = $(n).attr("name");
                var temp = 0;
                if (architecturalObj.indexOfUtils(name, architecturalObj.formType.base.maySaleArea)) {//可售面积
                    temp = $(n).val();
                    temp = architecturalObj.inputAlgorithmObject.specialTreatment(temp);
                    estimateMaySaleArea = architecturalObj.add(estimateMaySaleArea, temp);
                }
                if (architecturalObj.indexOfUtils(name, architecturalObj.formType.base.buildArea)) {//建筑面积
                    temp = $(n).val();
                    temp = architecturalObj.inputAlgorithmObject.specialTreatment(temp);
                    estimateBuildSaleArea = architecturalObj.add(estimateBuildSaleArea, temp);
                }
                if (architecturalObj.indexOfUtils(name, architecturalObj.formType.base.totalPrice)) {//合价
                    temp = $(n).val();
                    temp = architecturalObj.inputAlgorithmObject.specialTreatment(temp);
                    estimateSaleTotal = architecturalObj.add(estimateSaleTotal, temp);
                }
            });
            /*预计销售建筑面积*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().estimateBuildSaleArea.key, estimateBuildSaleArea);
            /*预计销售可售面积*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().estimateMaySaleArea.key, estimateMaySaleArea);
            /*预计销售合计*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().estimateSaleTotal.key, estimateSaleTotal);
            //总建筑面积 = 预计销售建筑面积+不可销售面积
            mayNotSaleArea = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().mayNotSaleArea.key, null);
            totalBuildArea = architecturalObj.add(mayNotSaleArea, estimateBuildSaleArea);
            /*总建筑面积*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().totalBuildArea.key, totalBuildArea);
        },
        jqueryInputGetAndSet: function (flag, name, data) {
            // console.log("jqueryInputGetAndSet");
            if (flag == 'get') {
                var text = null;
                text = $("." + architecturalObj.config.frm() + " " + "input[name='" + name + "']").val();
                text = architecturalObj.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + architecturalObj.config.frm() + " " + "input[name='" + name + "']").val(data);
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
            if (architecturalObj.inputAlgorithmObject.isNotNull(obj)) {
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
    architecturalObj.indexOfUtils = function (s, str) {
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

    architecturalObj.inputForm = function (key, value) {
        console.log("formType 进入@" + key);
        if (architecturalObj.indexOfUtils(key, architecturalObj.formType.garage.garage)) {//属于车库
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.garage.big)) {//大车库
                architecturalObj.inputAlgorithmObject.bigGarageFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.garage.small)) {//小车库
                architecturalObj.inputAlgorithmObject.smallGarageFun();
            }
        }
        if (architecturalObj.indexOfUtils(key,architecturalObj.formType.work.work)){//属于办公
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.work.close)) {
                architecturalObj.inputAlgorithmObject.closeWorkFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.work.open)) {
                architecturalObj.inputAlgorithmObject.openWorkFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.work.scenery)) {
                architecturalObj.inputAlgorithmObject.sceneryWorkFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.work.unit)) {
                architecturalObj.inputAlgorithmObject.unitWorkFun();
            }
        }
        if (architecturalObj.indexOfUtils(key,architecturalObj.formType.business.business)){//属于商业
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.business.operate)) {
                architecturalObj.inputAlgorithmObject.operateBusinessFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.business.strategy)) {
                architecturalObj.inputAlgorithmObject.strategyBusinessFun();
            }
        }
        if (architecturalObj.indexOfUtils(key,architecturalObj.formType.undergroundBusiness.undergroundBusiness)){//属于地下商业
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.undergroundBusiness.shop)) {
                architecturalObj.inputAlgorithmObject.undergroundBusinessShopFun();
            }
        }
        if (architecturalObj.indexOfUtils(key,architecturalObj.formType.residence.residence)){//属于住宅
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.residence.apartment)) {
                architecturalObj.inputAlgorithmObject.apartmentResidenceFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.residence.ordinary)) {
                architecturalObj.inputAlgorithmObject.ordinaryResidenceFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.residence.villa)) {
                architecturalObj.inputAlgorithmObject.villaResidenceFun();
            }
        }
    }

    architecturalObj.inputEvent = function () {
        var arr = architecturalObj.config.inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + architecturalObj.config.frm() + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                try {
                    console.log("key:" + key);
                    var funName = "architecturalObj.inputFun." + key + "Input(" + input.val() + ")";
                    console.log(funName);
                    eval(funName);
                } catch (e) {
                    console.log("没有相关定义的函数或者是属于子表单");
                    architecturalObj.inputForm(key, value);
                }
            });
        })
    }

    architecturalObj.selectEvent = {
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
                    $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select).html(retHtml);
                    $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select).select2();
                });
            },
            //增值及附加税率
            loadAddedValueAdditionalTaxRate: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.build_addedvalueadditionaltaxrate, "", function (html, data) {
                    $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().addedValueAdditionalTaxRate.select).html(html);
                    $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().addedValueAdditionalTaxRate.select).select2();//加载样式
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
                                $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().infrastructureCost.select).html(optionA);
                                $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().infrastructureCost.select).select2();
                                $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().infrastructureMatchingCost.select).html(optionB);
                                $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().infrastructureMatchingCost.select).select2();
                            }

                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            load: function () {
                architecturalObj.selectEvent.load.hypothesisDevelopmentSelect2();
                architecturalObj.selectEvent.load.loadAddedValueAdditionalTaxRate();
                architecturalObj.selectEvent.load.loadCostAndMatchingCost();
            }
        },
        //监听change 事件
        monitor: {
            // 基础设施建设费 单价选择
            infrastructureCost:function () {
                var key = architecturalObj.config.inputConfig().infrastructureCost;
                $("." + architecturalObj.config.frm() + " ." + key.select).change(function () {
                    var value = $("." + architecturalObj.config.frm() + " ." + key.select).eq(1).val();
                    var funName = "architecturalObj.inputFun." + key.select + "Input(" + value + ")";
                    try {
                        eval(funName);
                    } catch (e) {
                        console.log("没有这个函数");
                    }
                });
            },
            //公共配套设施建设费 单价选择
            infrastructureMatchingCost:function () {
                var key = architecturalObj.config.inputConfig().infrastructureMatchingCost;
                $("." + architecturalObj.config.frm() + " ." + key.select).change(function () {
                    var value = $("." + architecturalObj.config.frm() + " ." + key.select).eq(1).val();
                    var funName = "architecturalObj.inputFun." + key.select + "Input(" + value + ")";
                    try {
                        eval(funName);
                    } catch (e) {
                        console.log("没有这个函数");
                    }
                });
            },
            addedValueAdditionalTaxRate:function () {
                var key = architecturalObj.config.inputConfig().addedValueAdditionalTaxRate;
                $("." + architecturalObj.config.frm() + " ." + key.select).change(function () {
                    var value = $("." + architecturalObj.config.frm() + " ." + key.select).eq(1).val();
                    var funName = "architecturalObj.inputFun." + key.select + "Input(" + value + ")";
                    try {
                        eval(funName);
                    } catch (e) {
                        console.log("没有这个函数");
                    }
                });
            },
            hypothesisDevelopmentSelect2: function () {
                var key = architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select;
                $("." + key).change(function () {
                    var pid = $("." + key).eq(1).val();
                    AssessCommon.loadDataDicByPid(pid, "", function (html, data) {
                        $("." + key + "Type").prev().remove();
                        $("." + key + "Type").empty();
                        if (architecturalObj.isNotNull(data)) {
                            $("." + key + "Type").html(html);
                            $("." + key + "Type").select2();
                        }
                    });
                });
            },
            //监听之后 处理显示问题
            hypothesisDevelopmentSelect2Type: function () {
                var key = architecturalObj.config.frm() + " .";
                key += architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select;
                key += "Type";
                $("." + key).change(function () {
                    var value = $("." + key).eq(1).val();
                    AssessCommon.getDataDicInfo(value, function (data) {
                        if (architecturalObj.isNotNull(data)) {
                            $("." + architecturalObj.config.frm() + " ." + data.fieldName).toggle();
                        }
                    });
                });
            },
            monitor: function () {
                architecturalObj.selectEvent.monitor.hypothesisDevelopmentSelect2();
                architecturalObj.selectEvent.monitor.hypothesisDevelopmentSelect2Type();
                architecturalObj.selectEvent.monitor.infrastructureCost();
                architecturalObj.selectEvent.monitor.infrastructureMatchingCost();
                architecturalObj.selectEvent.monitor.addedValueAdditionalTaxRate();
            }
        }
    }

    architecturalObj.eventInit = function () {
        $("#" + ArchitecturalEngineeringObj.prototype.config.frm()).validate();
        architecturalObj.selectEvent.load.load();
        architecturalObj.inputEvent();
        architecturalObj.selectEvent.monitor.monitor();
    };

    architecturalObj.constructionInstallationEngineeringFee = {
        event: function () {
            // $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.architecturalEngineeringModel()).show();
            layer.open({
                type: 1,
                area: '1000px;',
                offset: 't',
                content: $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.architecturalEngineeringModel())
            });
            $(function () {
                architecturalEngineeringObj.viewInit();
            });
        },
        close: function () {
            // $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.architecturalEngineeringModel()).hide();
            layer.close(layer.index); //它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
        },
        getDataAndWriteHtml: function () {
            var data = architecturalEngineeringObj.getCalculatedResults();
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().constructionInstallationEngineeringFee.key,data);//建筑安装工程费
            architecturalObj.constructionInstallationEngineeringFee.close();
            architecturalObj.inputFun.constructionInstallationEngineeringFeeInput();
            architecturalObj.constructionInstallationEngineeringFee.saveAndUpdate(architecturalEngineeringObj.loadData());
        },
        saveAndUpdate:function (data) {
            var url = "${pageContext.request.contextPath}/marketCost/saveAndUpdateMdCostAndDevelopmentOther";
            $.ajax({
                url: url,
                type: "post",
                data: {
                    jsonContent: JSON.stringify(data),
                    type: "MdDevelopmentArchitectural",
                    id: "${mdCostAndDevelopmentOtherArchitectural.id}"
                },
                dataType: "json",
                success: function (result) {
                    toastr.success('成功');
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    //初始化
    $(function () {
        architecturalObj.eventInit();
    });
</script>

