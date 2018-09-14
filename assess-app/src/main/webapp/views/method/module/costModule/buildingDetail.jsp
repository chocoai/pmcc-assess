<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:03
  To change this template use File | Settings | File Templates.
  建筑物
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal frmBuild" id="frmBuild">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            委估对象面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" placeholder="委估对象面积"
                       class="form-control mdCost area" name="area" readonly="readonly" value="${mdCost.area}">
                <!-- 委估对象面积 -->
            </div>
        </div>

        <label class="col-sm-1 control-label">
            委估对象价格
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" placeholder="委估对象价格"
                       class="form-control mdCost price" name="price" readonly="readonly" value="${mdCost.area}">
                <!-- 委估对象价格 -->
            </div>
        </div>

    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="勘察设计和前期工程费率" data-toggle="popover reconnaissanceDesignRote" class="form-control"
                           name="reconnaissanceDesignRote">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                建筑安装工程费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑安装工程费" readonly="readonly" class="form-control"
                           name="constructionInstallationEngineeringFee">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="勘察设计和前期工程费" data-toggle="popover reconnaissanceDesign"
                           class="form-control" name="reconnaissanceDesign" readonly="readonly">
                </div>
            </div>
        </div>
    </div>




    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                基础设施建设费 单价选择
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="公共配套设施建设费 单价选择" class="form-control" name="infrastructureCostSelect2">

                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                基础设施建设费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="基础设施建设费" class="form-control" name="infrastructureCost">
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                公共配套设施建设费 单价选择
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="公共配套设施建设费 单价选择" class="form-control" name="infrastructureMatchingCostSelect2">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                公共配套设施建设费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="公共配套设施建设费" class="form-control" name="infrastructureMatchingCost">
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
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
        <div class="x-valid">
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
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                其它工程费单价
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="其它工程费单价" class="form-control" name="otherEngineeringCostPrice">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                其它工程费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="其它工程费" class="form-control" name="otherEngineeringCost">
                </div>
            </div>
        </div>
        <div class="x-valid">
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
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                管理费率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="管理费率" class="form-control" name="managementExpenseRote">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                管理费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="管理费" class="form-control" name="managementExpense">
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                不可预见费率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="不可预见费率" class="form-control" name="unforeseenExpensesRote">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                不可预见费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="不可预见费" class="form-control" name="unforeseenExpenses">
                </div>
            </div>
        </div>
        <div class="x-valid">
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
    </div>

    <div class="form-group">
        <div class="x-valid">
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
        <div class="x-valid">
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
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                增值及附加税率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="销售费" class="form-control" name="addedValueAdditionalTaxRateSelect2">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                增值及附加税金
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="增值及附加税金" class="form-control" name="valueAddedAdditionalTaxes">
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            计息周期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
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
            投资利润率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="投资利润率" class="form-control" name="investmentProfitRate">
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
            成新率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="成新率" class="form-control" name="newRate">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            评估单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="评估单价" class="form-control" name="assessPrice">
            </div>
        </div>

    </div>


</form>




