<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
  在建工程
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal frmConstruction" id="frmConstruction">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            开发土地面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="开发土地面积" class="form-control" name="developmentLandArea">
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
            开发期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发期" class="form-control" name="developmentDate">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            土地购买单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="土地购买单价" class="form-control" name="unitPriceLandPurchase">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地购买合价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地购买合价" class="form-control" name="landPurchasePrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地取得税率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="landAcquisitionTaxRate"
                        class="landAcquisitionTaxRateClass form-control search-select select2"></select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            土地取得税费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地取得税费" class="form-control" name="landAcquisitionTax">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地取得单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地取得单价" class="form-control" name="landAcquisitionPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地取得小计
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地取得小计" class="form-control" name="landAcquisitionTotal">
            </div>
        </div>
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
                <input type="text" data-rule-number='true' required="required"
                       placeholder="勘察设计和前期工程费率" class="form-control"
                       name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="建筑安装工程费" class="form-control" name="constructionInstallationEngineeringFee">
            </div>
        </div>
    </div>



    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="基础设施建设费 单价选择" class="form-control" name="infrastructureCostSelect">
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
                <input type="text" readonly="readonly"
                       placeholder="公共配套设施建设费 单价选择" class="form-control" name="infrastructureMatchingCostSelect">
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
            开发期间单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="开发期间单价" class="form-control" name="devDuringPrice">
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
                <input type="text" data-rule-number='true' required="required"
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
            销售费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="销售费率" class="form-control" name="salesFeeRote">
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
                <input type="text" readonly="readonly"
                       placeholder="增值及附加税率" class="form-control" name="addedValueAdditionalTaxRateSelect">
            </div>
        </div>

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

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资计息利率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="投资计息利率" class="form-control" name="interestRateOnInvestment">
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
            开发利润率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发利润率" class="form-control" name="developmentProfitMarginRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发利润
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="开发利润" class="form-control" name="developmentProfitMargin">
            </div>
        </div>
    </div>

    <div class="form-group">

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                开发利润修正
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="开发利润修正" class="form-control" name="developmentProfitMarginRoteCorrect">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                在建工程评估价值
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="在建工程评估价值" class="form-control" name="evaluationValueConstructionProject">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                在建工程评估价值修正
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="在建工程评估价值修正" class="form-control" name="evaluationValueConstructionProjectCorrect">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                在建工程评估值
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="在建工程评估值" class="form-control" name="constructionProcesAssessValue">
            </div>
        </div>
    </div>
</form>
