<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>单位成本或费率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费率
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="勘察设计和前期工程费率" class="form-control x-percent" data-rule-number='true'
                           required="required"
                           name="reconnaissanceDesignTax">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                建筑安装工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <div class="input-group">
                        <input type="text" readonly="readonly"
                               placeholder="建筑安装工程费" value="0" class="form-control"
                               name="constructionInstallationEngineeringFeeTax">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="landEngineering.constructionInstallationEngineeringFeeEvent.show()">
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

        <div class="form-group">
            <label class="col-sm-1 control-label">
                基础设施建设费<span class="symbol required"></span>
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
                公共配套设施建设费<span class="symbol required"></span>
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
                开发期间税费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <select name="devDuringTax"
                            class="form-control search-select select2 devDuringTax">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                其它工程费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="其它工程费率" class="form-control x-percent" name="otherEngineeringCostTax">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                不可预见费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpensesTax">
                </div>
            </div>
        </div>
    </div>

    <input type="hidden"
           placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"
           name="reconnaissanceDesign">
    <input type="hidden"
           placeholder="建筑安装工程费" class="form-control" readonly="readonly"
           name="constructionInstallationEngineeringFee" value="0">
    <input type="hidden"
           placeholder="基础设施费用" class="form-control" readonly="readonly"
           name="infrastructureCost">
    <input type="hidden"
           placeholder="公共配套设施建设费" class="form-control" readonly="readonly"
           name="infrastructureMatchingCost">
    <input type="hidden"
           placeholder="开发期间税费" class="form-control" readonly="readonly"
           name="devDuring">
    <input type="hidden"
           placeholder="其它工程费" class="form-control" readonly="readonly"
           name="otherEngineeringCost">
    <input type="hidden"
           placeholder="不可预见费" class="form-control" readonly="readonly"
           name="unforeseenExpenses">
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>设计费参数比率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    契税率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="契税率" class="form-control x-percent" data-rule-number='true' required="required"
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
                           name="deedTaxExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交易费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="交易费率" class="form-control x-percent" required="required"
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
                           name="transactionCostTaxExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    管理费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="管理费率" class="form-control x-percent" required="required"
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
                    销售费用率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="销售费用率" class="form-control x-percent" required="required"
                           name="salesFeeTax">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="salesFeeTaxExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    投资利息率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="投资利息率" class="form-control x-percent" required="required"
                           name="interestInvestmentTax">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="interestInvestmentTaxExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    投资利润率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="投资利润率" class="form-control x-percent" required="required"
                           name="investmentProfitTax">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="investmentProfitTaxExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    营业税金及附加<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="营业税金及附加" class="form-control x-percent" required="required"
                           name="businessAdditionalTax">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="businessAdditionalTaxExplain">
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
                           placeholder="土地增值税" class="form-control x-percent" readonly="readonly"
                           data-rule-number='true' required="required"
                           name="landIncrementTax">
                </div>
            </div>
        </div>

    </div>
    <input type="hidden"
           placeholder="设计费参数比率" class="form-control" readonly="readonly"
           name="designFeeParameterRatio">
    <input type="hidden"
           placeholder="管理费" class="form-control" readonly="readonly"
           name="managementExpenseCorrecting">
    <input type="hidden"
           placeholder="管理费(金额)" class="form-control" readonly="readonly"
           name="managementExpense">
    <input type="hidden"
           placeholder="销售费用(金额)" class="form-control" readonly="readonly"
           name="salesFee">
    <input type="hidden"
           placeholder="投资利息(金额)" class="form-control" readonly="readonly"
           name="interestInvestment">
    <input type="hidden"
           placeholder="投资利润(计算率)" class="form-control" readonly="readonly"
           name="investmentProfitCorrecting">
    <input type="hidden"
           placeholder="销售税金及附加(计算率)" class="form-control" readonly="readonly"
           name="salesTaxAndAdditionalCorrecting">
    <input type="hidden"
           placeholder="销售税金及附加(金额)" class="form-control" readonly="readonly"
           name="salesTaxAndAdditional">
    <input type="hidden"
           placeholder="地价(金额)" class="form-control" readonly="readonly"
           name="landPrice">
    <input type="hidden"
           placeholder="单元格 E33" class="form-control" readonly="readonly"
           name="estimateUnitPriceLandE33">
    <input type="hidden"
           placeholder="单元格 F33" class="form-control" readonly="readonly"
           name="estimateUnitPriceLandF33">
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
    </div>
</div>

<!--  建筑安装工程费 -->
<div id="boxLandEngineering" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装工程费</h3>
            </div>
            <form id="boxLandEngineeringFrm" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="landEngineering.constructionInstallationEngineeringFeeEvent.save()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>



