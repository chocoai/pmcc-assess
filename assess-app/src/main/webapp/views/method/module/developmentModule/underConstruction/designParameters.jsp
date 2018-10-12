<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/10/8
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>设计费参数比率</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal" name="designParameters">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        续建契税率
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
                        续建交易费率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="交易费率" class="form-control x-percent" data-rule-number='true' required="required"
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
                        续建管理费率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="续建管理费率" class="form-control x-percent" data-rule-number='true' required="required"
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
                        续建销售费用率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="销售费用率" class="form-control x-percent" data-rule-number='true' required="required"
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
                        续建投资利息率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="投资利息率" class="form-control x-percent" data-rule-number='true' required="required"
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
                        续建投资利润率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="投资利润率" class="form-control x-percent" data-rule-number='true' required="required"
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
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        营业税金及附加率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="营业税金及附加率" class="form-control x-percent" data-rule-number='true' required="required"
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
                               name="businessAdditionalTaxExplain">
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
                               placeholder="土地增值税" class="form-control x-percent" data-rule-number='true' required="required"
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
                               name="landIncrementTaxExplain">
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>