<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="buildModel">

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>建设成本</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        勘察设计和前期工程费率
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text"
                                   placeholder="勘察设计和前期工程费率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="reconnaissanceDesignTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        建筑安装工程费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <div class="input-group">
                                <input type="text" readonly="readonly"
                                       placeholder="建筑安装工程费" value="0" class="form-control"
                                       name="constructionInstallationEngineeringFeeTax">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        基础设施建设费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <select name="infrastructureCostTax"
                                    class="form-control search-select select2 infrastructureCostTax">
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        公共配套设施建设费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <select name="infrastructureMatchingCostTax"
                                    class="form-control search-select select2 infrastructureMatchingCostTax">
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        开发期间税费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text"
                                   placeholder="开发期间税费" class="form-control" name="devDuringTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        其它工程费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text"
                                   placeholder="其它工程费" class="form-control" name="otherEngineeringCostTax">
                        </div>
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
            <h2>合计税率</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal">

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            营业税
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="营业税" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="businessTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            城建税
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="城建税" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="urbanMaintenanceTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            教育费附加
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="教育费附加" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="educationTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            地方教育费附加
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="地方教育费附加" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="localEducationTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            印花税
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="印花税" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="stampDuty">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            合计税率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="合计税率" class="form-control" readonly="readonly" name="totalTaxRate">
                        </div>
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
            <h2>设计费参数比率</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            不可预见费率
                        </label>
                        <div class="x-valid">
                            <div class="col-sm-3">
                                <input type="text"
                                       placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpensesTax">
                            </div>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="unforeseenExpensesTaxExplain">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            管理费率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="管理费率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="managementExpenseTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="managementExpenseTaxExplain">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            销售费用率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="销售费用率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="salesFeeTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="salesFeeTaxExplain">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            投资利息率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="投资利息率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="interestInvestmentTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="interestInvestmentTaxExplain">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            计息周期
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="计息周期" class="form-control" data-rule-number='true' readonly="readonly"
                                   name="interestInvestmentCorrecting">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="interestInvestmentCorrectingExplain">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            投资利润率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="投资利润率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="investmentProfitTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="investmentProfitTaxExplain">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        成新率
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-7">
                            <div class="input-group">
                                <input type="text" readonly="readonly"
                                       placeholder="成新率" value="0" class="form-control" name="newRate">
                            </div>
                        </div>
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
            <h2>测算结果</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="col-md-12 col-sm-12">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <td>建设成本</td>
                                <td class="constructionCost">0.00</td>
                            </tr>
                            <tr>
                                <td> 投资利息</td>
                                <td class="interestInvestment">0.00</td>
                            </tr>
                            <tr>
                                <td> 投资利润</td>
                                <td class="investmentProfit">0.00</td>
                            </tr>
                            <tr>
                                <td> 重置价格</td>
                                <td class="replacementValue">0.00</td>
                            </tr>
                            <tr>
                                <td> 评估单价</td>
                                <td class="assessPrice">0.00</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    var build = new Object();

    build.config = {
        id: "buildModel",
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        totalTaxRate: {
            key: "totalTaxRate",
            name: "合计税率",
            business: "businessTax",//营业税
            urbanMaintenance: "urbanMaintenanceTax",//城建税
            education: "educationTax",//教育费附加
            localEducation: "localEducationTax",//地方教育费附加
            stampDuty: "stampDuty"//印花税
        },
        inputConfig: {
            reconnaissanceDesign: {
                key: "reconnaissanceDesign",
                tax: "reconnaissanceDesignTax",
                correcting: "",
                name: "勘察设计和前期工程费"
            },
            constructionInstallationEngineeringFee: {
                key: "constructionInstallationEngineeringFee",
                tax: "constructionInstallationEngineeringFeeTax",
                correcting: "",
                name: "建筑安装工程费",
                class: "constructionInstallationEngineeringFeeClass"
            },
            infrastructureCost: {
                key: "infrastructureCost",
                tax: "infrastructureCostTax",
                correcting: "",
                name: "基础设施费用"
            },
            infrastructureMatchingCost: {
                key: "infrastructureMatchingCost",
                tax: "infrastructureMatchingCostTax",
                correcting: "",
                name: "公共配套设施费用"
            },
            devDuring: {
                key: "devDuring",
                tax: "devDuringTax",
                correcting: "",
                name: "开发期间"
            },
            otherEngineeringCost: {
                key: "otherEngineeringCost",
                tax: "otherEngineeringCostTax",
                correcting: "",
                name: "其它工程费"
            },
            unforeseenExpenses: {
                key: "unforeseenExpenses",
                tax: "unforeseenExpensesTax",
                correcting: "",
                name: "不可预见费"
            },
            managementExpense: {
                key: "managementExpense",
                tax: "managementExpenseTax",
                correcting: "managementExpenseCorrecting",
                name: "管理费"
            },
            salesFee: {
                key: "salesFee",
                tax: "salesFeeTax",
                correcting: "salesFeeCorrecting",
                name: "销售费用"
            },
            interestInvestment: {
                key: "interestInvestment",
                tax: "interestInvestmentTax",//利率
                correcting: "interestInvestmentCorrecting",//计息周期
                name: "投资利息"
            },
            investmentProfit: {
                key: "investmentProfit",
                tax: "investmentProfitTax",
                correcting: "investmentProfitCorrecting",
                name: "投资利润"
            },
            constructionCost: {
                key: "constructionCost",
                tax: "",
                name: "建设成本"
            },
            replacementValue: {key: "replacementValue", name: "重置价格", tax: ""},
            newRate: {
                key: "newRate",
                name: "成新率",
                tax: "",
                div: "newRateModelDiv",
                frm: "newRateModelFrm",
                correcting: "newRateCorrecting"
            },
            assessPrice: {key: "assessPrice", name: "评估单价", tax: ""}
        }
    };

    build.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * @author:  zch
     * 描述:收集数据
     * @date:2018-10-12
     **/
    build.formParams = function () {
        var item = {};
        var forms = $("#" + build.config.id).find("form");
        $.each(forms, function (i, n) {
            // if (!$(n).valid()) {
            //     return false;
            // }
        });
        $.each(forms, function (i, n) {
            try {
                /*Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。 ECMAScript6 (可能不兼容) */
                item = Object.assign(item, formSerializeArray($(n)));
            } catch (e) {
                item = $.extend(item, formSerializeArray($(n)));
            }
        });
        item.constructionCost = $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html();
        item.interestInvestment = $("#" + build.config.id).find("." + build.config.inputConfig.interestInvestment.key).html();
        item.investmentProfit = $("#" + build.config.id).find("." + build.config.inputConfig.investmentProfit.key).html();
        item.replacementValue = $("#" + build.config.id).find("." + build.config.inputConfig.replacementValue.key).html();
        item.assessPrice = $("#" + build.config.id).find("." + build.config.inputConfig.assessPrice.key).html();

        item.constructionCost = Number(item.constructionCost);
        item.interestInvestment = Number(item.interestInvestment);
        item.investmentProfit = Number(item.investmentProfit);
        item.replacementValue = Number(item.replacementValue);
        item.assessPrice = Number(item.assessPrice);
        return item;
    };


    /**
     * @author:  zch
     * 描述:赋值
     * @date:2018-10-12
     **/
    build.initForm = function (item) {
        var forms = $("#" + build.config.id).find("form");
        item = JSON.parse(item);
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(item);
        });
        $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html(item.constructionCost);
        $("#" + build.config.id).find("." + build.config.inputConfig.interestInvestment.key).html(item.interestInvestment);
        $("#" + build.config.id).find("." + build.config.inputConfig.investmentProfit.key).html(item.investmentProfit);
        $("#" + build.config.id).find("." + build.config.inputConfig.replacementValue.key).html(item.replacementValue);
        $("#" + build.config.id).find("." + build.config.inputConfig.assessPrice.key).html(item.assessPrice);
    };
</script>
