<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="constructionModel">


    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>开发信息</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        开发土地面积(㎡)
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text" name="developLandAreaTax"
                                   placeholder=" 开发土地面积(㎡)" class="form-control" data-rule-number='true'
                                   readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        开发建筑面积(㎡)
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text" name="developBuildAreaTax"
                                   placeholder="开发建筑面积(㎡)" class="form-control" data-rule-number='true'
                                   readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        开发期（年）
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text" name="developYearNumberTax"
                                   placeholder="开发期（年）" class="form-control" data-rule-number='true'
                                   readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        土地购买价格
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text" name="landPurchasePriceTax"
                                   placeholder="土地购买价格" class="form-control" data-rule-number='true'
                                   readonly="readonly">
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
                            契税率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="契税率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="deedTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            交易费率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="交易费率" class="form-control x-percent" data-rule-number='true'
                                   readonly="readonly"
                                   name="transactionCostTax">
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
                </div>
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

            </form>
        </div>
    </div>

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
                            <input type="text" readonly="readonly"
                                   placeholder="基础设施建设费" class="form-control" name="infrastructureCostTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        公共配套设施建设费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text" readonly="readonly"
                                   placeholder="公共配套设施建设费" class="form-control" name="infrastructureMatchingCostTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        开发期间税费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text" readonly="readonly"
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
                            <input type="text" readonly="readonly"
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
            <h2>设计费参数比率</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        不可预见费率
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-3">
                            <input type="text" readonly="readonly"
                                   placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpensesTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="unforeseenExpensesTaxExplain" readonly="readonly">
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
                                   name="managementExpenseTaxExplain" readonly="readonly">
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
                                   name="salesFeeTaxExplain" readonly="readonly">
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
                                   name="interestInvestmentTaxExplain" readonly="readonly">
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
                                   name="investmentProfitTaxExplain"  readonly="readonly">
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
                                <td>土地取得成本合计</td>
                                <td class="landGetCostTotal">0.00</td>
                            </tr>
                            <tr>
                                <td> 建设成本小计</td>
                                <td class="constructionSubtotal">0.00</td>
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
                                <td> 在建工程评估价值</td>
                                <td class="constructionAssessmentValue">0.00</td>
                            </tr>
                            <tr>
                                <td> 在建工程单位价</td>
                                <td class="constructionAssessmentPriceCorrecting">0.00</td>
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

    var construction = new Object();

    construction.config = {
        id: "constructionModel",
        totalTaxRate: {
            key: "totalTaxRate",
            name: "合计税率",
            business: "businessTax",//营业税
            urbanMaintenance: "urbanMaintenanceTax",//城建税
            education: "educationTax",//教育费附加
            localEducation: "localEducationTax",//地方教育费附加
            stampDuty: "stampDuty"//印花税
        },
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        inputConfig: {
            developLandArea: {
                key: "developLandArea",
                tax: "developLandAreaTax",
                name: "开发土地面积"
            },
            developBuildArea: {
                key: "developBuildArea",
                tax: "developBuildAreaTax",
                name: "开发建筑面积"
            },
            developYearNumber: {
                key: "developYearNumber",
                tax: "developYearNumberTax",
                name: "开发年"
            },
            landPurchasePrice: {
                key: "landPurchasePrice",
                name: "土地购买价格",
                tax: "landPurchasePriceTax"
            },
            landGetRelevant: {
                key: "landGetRelevant",
                tax: "landGetRelevantTax",
                name: "土地取得相关税费"
            },
            landGetCostTotal: {
                key: "landGetCostTotal",
                name: "土地取得成本合计",
                tax: ""
            },
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
            constructionSubtotal: {
                key: "constructionSubtotal",
                tax: "",
                name: "建设成本小计"
            },
            unforeseenExpenses: {
                key: "unforeseenExpenses",
                tax: "unforeseenExpensesTax",
                correcting: "",
                name: "不可预见费"
            },
            deed: {
                key: "deed",
                tax: "deedTax",
                correcting: "deedCorrecting",
                name: "契税"
            },
            transactionCost: {
                key: "transactionCost",
                tax: "transactionCostTax",
                correcting: "transactionCostCorrecting",
                name: "交易费"
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
                tax: "interestInvestmentTax",
                correcting: "interestInvestmentCorrecting",
                name: "投资利息"
            },
            investmentProfit: {
                key: "investmentProfit",
                tax: "investmentProfitTax",
                correcting: "investmentProfitCorrecting",
                name: "投资利润"
            },
            salesTaxAndAdditional: {
                key: "salesTaxAndAdditional",
                tax: "",
                correcting: "salesTaxAndAdditionalCorrecting",
                name: "销售税金及附加"
            },
            businessAdditional: {
                key: "businessAdditional",
                tax: "businessAdditionalTax",
                name: "营业税金及附加"
            },
            landIncrement: {
                key: "landIncrement",
                tax: "landIncrementTax",
                name: "土地增值"
            },
            constructionAssessmentValue: {
                key: "constructionAssessmentValue",
                tax: "",
                name: "在建工程评估价值"
            },
            constructionAssessmentPrice: {
                key: "constructionAssessmentPrice",
                tax: "",
                correcting: "constructionAssessmentPriceCorrecting",
                name: "在建工程单位价"
            }
        }
    };

    construction.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };


    construction.specialTreatment = function (obj) {
        if (construction.isEmpty(obj)) {
            var nnn = "" + obj + "";
            var str = nnn.substring(nnn.length - 1, nnn.length);
            if (str == '%') {//检测是否为百分比
                return nnn;
            } else {
                str = AssessCommon.pointToPercent(Number(nnn));
                return str;
            }
            return obj;
        }
        return 0;
    };

    construction.set = function (name, data) {
        if (construction.isEmpty(name)) {
            if (construction.isEmpty(data)) {
                $("#" + construction.config.id).find("input[name='" + name + "']").val(data);
            }
        }
    };

    /**
     * @author:  zch
     * 描述:收集数据
     * @date:2018-10-12
     **/
    construction.formParams = function () {
        var item = {};
        var forms = $("#" + construction.config.id).find("form");
        $.each(forms, function (i, n) {
        });
        $.each(forms, function (i, n) {
            try {
                /*Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。 ECMAScript6 (可能不兼容) */
                item = Object.assign(item, formSerializeArray($(n)));
            } catch (e) {
                item = $.extend(item, formSerializeArray($(n)));
            }
        });
        item.landGetCostTotal = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html());
        item.constructionSubtotal = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html());
        item.interestInvestment = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.interestInvestment.key).html());
        item.investmentProfit = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.investmentProfit.key).html());
        item.constructionAssessmentValue = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentValue.key).html());
        item.constructionAssessmentPriceCorrecting = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentPrice.correcting).html());
        return item;
    };


    /**
     * @author:  zch
     * 描述:赋值
     * @date:2018-10-12
     **/
    construction.initForm = function (item) {
        var forms = $("#" + construction.config.id).find("form");
        item = JSON.parse(item);
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(item);
        });
        $.each(forms, function (i, n) {
            var inputs = $(n).find(":input");
            $.each(inputs, function (i, k) {
                var kk = $(k);
                var className = kk.attr("class");
                var str = null;
                var name = kk.attr("name");
                if (className.indexOf("x-percent") != -1) {
                    try {
                        str = eval("item." + name);
                        if (construction.isEmpty(str)) {
                            str = construction.specialTreatment(str);
                            construction.set(name,str);
                        }
                    } catch (e) {
                    }
                }
            });
        });
        $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html(item.landGetCostTotal);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html(item.constructionSubtotal);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.interestInvestment.key).html(item.interestInvestment);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.investmentProfit.key).html(item.investmentProfit);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentValue.key).html(item.constructionAssessmentValue);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentPrice.correcting).html(item.constructionAssessmentPriceCorrecting);
    };


</script>