<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="landEngineeringModel">

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>收入类(参数)</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <form class="form-horizontal" id="parameterFrm">
                <div class="form-group">
                    <div class="col-md-12 col-sm-12">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <td>项目</td>
                                <td>建筑面积（㎡）</td>
                                <td>可售面积（㎡）</td>
                                <td>单位售价（元/㎡）</td>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>住宅建筑</td>
                                <td><a href="#" class="villaResidenceBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                                </td>
                                <td><a href="#" class="villaResidenceMaySaleArea" data-type="text"
                                       data-title="可售面积">0.00</a>
                                </td>
                                <td><a href="#" class="villaResidenceUnitPrice" data-type="text" data-title="单位售价">0.00</a>
                                </td>
                            </tr>
                            <tr>
                                <td>商业建筑</td>
                                <td><a href="#" class="strategyBusinessBuildArea" data-type="text"
                                       data-title="建筑面积">0.00</a>
                                </td>
                                <td><a href="#" class="strategyBusinessMaySaleArea" data-type="text"
                                       data-title="可售面积">0.00</a>
                                </td>
                                <td><a href="#" class="strategyBusinessUnitPrice" data-type="text"
                                       data-title="单位售价">0.00</a>
                                </td>
                            </tr>
                            <tr>
                                <td>办公建筑</td>
                                <td><a href="#" class="workBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                                </td>
                                <td><a href="#" class="workBuildMaySaleArea" data-type="text" data-title="可售面积">0.00</a>
                                </td>
                                <td><a href="#" class="workBuildUnitPrice" data-type="text" data-title="单位售价">0.00</a>
                                </td>
                            </tr>
                            <tr>
                                <td>地下机动车停车库建筑</td>
                                <td><a href="#" class="undergroundGarageBuildArea" data-type="text"
                                       data-title="建筑面积">0.00</a>
                                </td>
                                <td><a href="#" class="undergroundGarageMaySaleArea" data-type="text"
                                       data-title="可售面积">0.00</a>
                                </td>
                                <td><a href="#" class="undergroundGarageUnitPrice" data-type="text"
                                       data-title="单位售价">0.00</a>
                                </td>
                            </tr>
                            <tr>
                                <td>地下商业建筑</td>
                                <td><a href="#" class="undergroundBusinessShopBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                                </td>
                                <td><a href="#" class="undergroundBusinessShopMaySaleArea" data-type="text"
                                       data-title="可售面积">0.00</a>
                                </td>
                                <td><a href="#" class="undergroundBusinessShopUnitPrice" data-type="text" data-title="单位售价">0.00</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 control-label">不可售建筑面积(㎡)</label>
                            <div class="col-md-2 col-sm-2">
                                <input type="text"
                                       placeholder="不可售建筑面积(㎡)" readonly="readonly" class="form-control" name="nonSaleFloorAreaTax">
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 control-label">预期销售合计</label>
                            <div class="col-md-2 col-sm-2">
                                <input type="text"
                                       placeholder="预期销售合计" class="form-control" name="estimateSaleTotal"
                                       readonly="readonly" value="0.00">
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 control-label">可售面积</label>
                            <div class="col-md-2 col-sm-2">
                                <input type="text" readonly="readonly"
                                       placeholder="可售面积" class="form-control" name="smallGarageMaySaleArea" value="0.00">
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 control-label">销售合价</label>
                            <div class="col-md-2 col-sm-2">
                                <input type="text" readonly="readonly"
                                       placeholder="销售合价" class="form-control" name="smallGarageTotalPrice" value="0.00">
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
            <h2>单位成本或费率</h2>
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
                                   placeholder="勘察设计和前期工程费率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
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
                                       placeholder="建筑安装工程费" value="0" class="form-control" name="constructionInstallationEngineeringFeeTax">
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
                            <input type="text"
                                   placeholder="基础设施建设费"  readonly="readonly" class="form-control" name="infrastructureCostTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        公共配套设施建设费
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text"
                                   placeholder="公共配套设施建设费"  readonly="readonly" class="form-control" name="infrastructureMatchingCostTax">
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
                                   placeholder="开发期间税费"  readonly="readonly" class="form-control" name="devDuringTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        其它工程费率
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text"
                                   placeholder="其它工程费率"  readonly="readonly" class="form-control x-percent" name="otherEngineeringCostTax">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        不可预见费率
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-11">
                            <input type="text"
                                   placeholder="不可预见费率"  readonly="readonly" class="form-control x-percent" name="unforeseenExpensesTax">
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
                            契税率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="契税率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
                                   name="deedTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="deedTaxExplain" readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            交易费率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="交易费率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
                                   name="transactionCostTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="transactionCostTaxExplain" readonly="readonly">
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
                                   placeholder="管理费率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
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
                                   placeholder="销售费用率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
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
                                   placeholder="投资利息率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
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
                                   placeholder="投资利润率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
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
                                   name="investmentProfitTaxExplain" readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            增值税金及附加率
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="增值税金及附加率" class="form-control x-percent" data-rule-number='true' readonly="readonly"
                                   name="businessAdditionalTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="businessAdditionalTaxExplain" readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            土地增值税
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="土地增值税" class="form-control x-percent" data-rule-number='true' readonly="readonly"
                                   name="landIncrementTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            费率说明
                        </label>
                        <div class="col-sm-3">
                            <input type="text"
                                   placeholder="费率说明" class="form-control"
                                   name="landIncrementTaxExplain" readonly="readonly">
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
                                <td>预期销售合计</td>
                                <td class="estimateSaleTotal">0.00</td>
                            </tr>
                            <tr>
                                <td> 总建筑面积小计（㎡）</td>
                                <td class="totalGrossFloorArea">0.00</td>
                            </tr>
                            <tr>
                                <td> 工程建设成本小计</td>
                                <td class="constructionCostSubtotal">0.00</td>
                            </tr>
                            <tr>
                                <td> 不可预见费金额</td>
                                <td class="unforeseenExpenses">0.00</td>
                            </tr>
                            <tr>
                                <td> 投资利润</td>
                                <td class="investmentProfit">0.00</td>
                            </tr>
                            <tr>
                                <td> 地价</td>
                                <td class="landPriceCorrecting">0.00</td>
                            </tr>
                            <tr>
                                <td> 委估土地单价</td>
                                <td class="estimateUnitPriceLandC33">0.00</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>