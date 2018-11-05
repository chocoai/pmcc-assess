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
        <form class="form-horizontal">
            <%--<div class="form-group">--%>
                <%--<label class="col-sm-1 control-label">--%>
                    <%--不可预见费率--%>
                <%--</label>--%>
                <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                        <%--<input type="text"--%>
                               <%--placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpensesTax">--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">--%>
                        <%--费率说明--%>
                    <%--</label>--%>
                    <%--<div class="col-sm-3">--%>
                        <%--<input type="text"--%>
                               <%--placeholder="费率说明" class="form-control"--%>
                               <%--name="unforeseenExpensesTaxExplain">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        管理费率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="管理费率" class="form-control x-percent"  required="required"
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
                        投资利息率
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
                        投资利润率
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

        </form>
    </div>
</div>