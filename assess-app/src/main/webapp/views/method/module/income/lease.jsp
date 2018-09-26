<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="lease_info" style="display: none">
    <div class="x_title">
        <h3>收益价格计算信息</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <button class="btn btn-success" data-toggle="modal" onclick="income.addLease();">
            新增
        </button>
        <table class="table table-bordered" id="tb_lease_list">
        </table>
    </div>
</div>
<div id="modal_lease" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 1200px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">租赁收益</h3>
            </div>
            <form id="frm_lease" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <fieldset>
                        <legend>1.年有效毛收入 <i class="red" data-name="grossIncome"></i>（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    月租金收入<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="rentalIncome" placeholder="月租金收入"
                                           onblur="income.cpGrossIncome();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    全年月份数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="monthNumber" placeholder="全年月份数"
                                           onblur="income.cpGrossIncome();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    出租率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="rentals" placeholder="出租率" onblur="income.cpGrossIncome();"
                                           data-rule-range="[0,1]" data-rule-number="true" class="form-control"
                                           required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    出租率说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="rentalsRemark" class="form-control" placeholder="出租率说明"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    押金<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="deposit" placeholder="押金"
                                           onblur="income.cpInterestIncome();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    利率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="interestRate" placeholder="利率"
                                           onblur="income.cpInterestIncome();"
                                           data-rule-range="[0,1]" data-rule-number="true" class="form-control"
                                           required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    年押金利息收入<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="interestIncome" placeholder="年押金利息收入" readonly="readonly"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    利率说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="interestRateRemark" class="form-control"
                                              placeholder="利率说明"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    其它收入<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="otherIncome" placeholder="其它收入"
                                           onblur="income.cpGrossIncome();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    其它收入说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="otherIncomeRemark" class="form-control"
                                              placeholder="其它收入说明"></textarea>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>2.年运营费 <i class="red" data-name="operatingExpense"></i>（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    费用比率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="managementCostRatio" placeholder="管理费用比率"
                                           onblur="income.cpManagementCost();"
                                           data-rule-range="[0,1]" data-rule-number="true" class="form-control"
                                           required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    管理费用<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="managementCost" placeholder="管理费用" readonly="readonly"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    取费说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="paymentRemark" placeholder="取费说明" class="form-control"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    维护保养费比率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="maintenanceCostRatio" placeholder="维护保养费用比率"
                                           onblur="income.cpMaintenance();"
                                           data-rule-range="[0,1]" data-rule-number="true" class="form-control"
                                           required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    计算基数重置价格<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="replacementValue" placeholder="计算基数重置价格"
                                           onblur="income.cpMaintenance();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    维护保养费用
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="maintenance" placeholder="维护保养" readonly="readonly"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    税率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="taxRate" placeholder="税率" onblur="income.cpAdditional();"
                                           data-rule-range="[0,1]" data-rule-number="true" class="form-control"
                                           required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    相关税金及附加<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="additional" placeholder="相关税金及附加" readonly="readonly"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    保险费率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="insuranceRate" placeholder="保险费率"
                                           onblur="income.cpInsurancePremium();"
                                           data-rule-range="[0,1]" data-rule-number="true" class="form-control"
                                           required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    保险费<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="insurancePremium" placeholder="保险费" readonly="readonly"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    使用税参数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="usageTaxParameter" placeholder="使用税参数"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用税<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="landUseTax" placeholder="土地使用税"
                                           onblur="income.cpOperatingExpense();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>3.房地产年净收益 <i class="red" data-name="netProfit"></i>（元/m2）</legend>
                    </fieldset>
                    <fieldset>
                        <legend>4.房地产收益价格 <i class="red" data-name="incomePrice"></i>（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开始时间<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="leaseBeginDate" placeholder="开始时间"
                                           required="required" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="leaseEndDate" placeholder="结束时间"
                                           data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                           readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    资本化率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="capitalizationRate" placeholder="资本化率"
                                           onblur="income.cpCorrectionFactor();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    收益期限<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="returnPeriod" placeholder="收益期限"
                                           onblur="income.cpCorrectionFactor();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    租金增长率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="rentalGrowthRate" placeholder="租金增长率"
                                           onblur="income.cpCorrectionFactor();"
                                           data-rule-number="true" class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    年期修正系数
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="correctionFactor" placeholder="年期修正系数" readonly="readonly"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    收益现值系数
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="presentValueFactor" placeholder="收益现值系数"
                                           readonly="readonly"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="income.saveLease();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>




