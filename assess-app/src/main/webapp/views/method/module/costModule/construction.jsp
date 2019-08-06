<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <input type="hidden" name="id" value="${mdCostConstruction.id}">
    <input type="hidden" name="pid" value="${mdCost.id}">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>开发信息</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发土地面积(㎡)<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <c:if test="${!empty mdCostConstruction.developLandAreaTax}">
                    <input type="text" name="developLandAreaTax"  onblur="cost.checkParams(this);construction.calculationE6();construction.calculationD8()"
                           placeholder=" 开发土地面积(㎡)" class="form-control" data-rule-number='true' required="required" value="${mdCostConstruction.developLandAreaTax}">
                    </c:if>
                    <c:if test="${empty mdCostConstruction.developLandAreaTax}">
                    <input type="text" name="developLandAreaTax"  onblur="cost.checkParams(this);construction.calculationE6();construction.calculationD8()"
                           placeholder=" 开发土地面积(㎡)" class="form-control" data-rule-number='true' required="required" value="${declareEconomicIndicatorsHead.assessUseLandArea}">
                    </c:if>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发建筑面积(㎡)<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <c:if test="${!empty mdCostConstruction.developBuildAreaTax}">
                        <input type="text" name="developBuildAreaTax" onblur="cost.checkParams(this);construction.calculationE12();construction.calculationE13();construction.calculationE14();construction.calculationE15();construction.calculationE16();"
                               placeholder="开发建筑面积(㎡)" class="form-control" data-rule-number='true' required="required" value="${mdCostConstruction.developBuildAreaTax}">
                    </c:if>
                    <c:if test="${empty mdCostConstruction.developBuildAreaTax}">
                        <input type="text" name="developBuildAreaTax" onblur="cost.checkParams(this);construction.calculationE12();construction.calculationE13();construction.calculationE14();construction.calculationE15();construction.calculationE16();"
                               placeholder="开发建筑面积(㎡)" class="form-control" data-rule-number='true' required="required" value="${declareEconomicIndicatorsHead.assessTotalBuildArea}">
                    </c:if>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发期（年）<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <input type="text" name="developYearNumberTax" onblur="cost.checkParams(this);construction.calculationE21();construction.calculationG21();construction.constructionAssessmentPriceCorrectingCalculationE26();"
                           placeholder="开发期（年）" class="form-control" data-rule-number='true' required="required" value="${mdCostConstruction.developYearNumberTax}">
                </div>
            </div>
        </div>
    </div>

</div>


<div class="x_panel">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>土地取得成本</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地购买价格<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" name="landPurchasePrice" placeholder="土地购买价格" data-rule-number="true" onblur="cost.checkParams(this);construction.calculationE6();"
                               class="form-control" required="required" value="${mdCostConstruction.landPurchasePrice}" data-value="${mdCostConstruction.landPurchasePrice}">
                        <span class="input-group-btn">
                                        <input type="button" class="btn btn-primary" value="市场比较法"
                                               onclick="construction.callCompareMethod(this);">
                                    </span>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    价格说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="价格说明" class="form-control"
                           name="landPurchasePriceExplain" value="${mdCostConstruction.landPurchasePriceExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得相关税费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" name="landGetRelevant"
                           placeholder="土地取得相关税费率" class="form-control x-percent"  onblur="cost.checkParams(this);construction.calculationE7();"
                           required="required" value="${mdCostConstruction.landGetRelevant}" data-value="${mdCostConstruction.landGetRelevant}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control"
                           name="landGetRelevantExplain" value="${mdCostConstruction.landGetRelevantExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" name="additionalCostLandAcquisition"
                           placeholder="土地取得附加成本" class="form-control" data-rule-number='true' onblur="cost.checkParams(this);construction.calculationD8();"
                           required="required" value="${mdCostConstruction.additionalCostLandAcquisition}">
                </div>
            </div>


        </div>
    </div>

    <input type="hidden" readonly="readonly" class="form-control" name="e6" placeholder="e6" onblur="construction.calculationE7();">
    <input type="hidden" readonly="readonly" class="form-control" name="e7" placeholder="e7" onblur="construction.calculationLandGetCostTotalE9();">
    <input type="hidden" readonly="readonly" class="form-control" name="d8" placeholder="d8" onblur="construction.calculationLandGetCostTotalE9();">

</div>


<div class="x_panel">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>建设成本或费率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    勘察设计和前期工程费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" name="reconnaissanceDesign" onblur="cost.checkParams(this);construction.calculationE11()"
                           placeholder="勘察设计和前期工程费率" class="form-control x-percent"
                           required="required" value="${mdCostConstruction.reconnaissanceDesign}" data-value="${mdCostConstruction.reconnaissanceDesign}">
                </div>
            </div>


        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                建筑安装工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" readonly="readonly"
                               placeholder="建筑安装工程费" class="form-control" onblur="construction.calculationE12()"
                               name="constructionInstallationEngineeringFee"  value="${mdCostConstruction.constructionInstallationEngineeringFee}" required>
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="construction.constructionInstallationEngineeringFeeEvent.show();">
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
                <div class="col-sm-5">
                    <div class="input-group">
                        <input type="text" required="required"
                               placeholder="基础设施配套费"  class="form-control"
                               name="infrastructureCost" onblur="cost.checkParams(this);construction.calculationE13(this)" value="${mdCostConstruction.infrastructureCost}">
                        <span class="input-group-btn">
                        </span>
                        <select name="infrastructureCostValue"
                                class="form-control" onchange="construction.calculationE13Select(this)">
                            <c:forEach items="${dataInfrastructureList}" var="item">
                                <c:if test="${item.infrastructureSupportingFacilities != 0}">
                                    <c:if test="${mdCostConstruction.infrastructureCost != item.infrastructureSupportingFacilities}">
                                        <option value="${item.infrastructureSupportingFacilities}" data-key="${item.id}" data-type="${item.type}">${item.timeSlot} 金额:${item.infrastructureSupportingFacilities}</option>
                                    </c:if>
                                    <c:if test="${mdCostConstruction.infrastructureCost == item.infrastructureSupportingFacilities}">
                                        <option value="${item.infrastructureSupportingFacilities}" selected="selected"  data-key="${item.id}"  data-type="${item.type}">${item.timeSlot} 金额:${item.infrastructureSupportingFacilities}</option>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            <option value="100">2012-2017 金额100</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                    <div id="toolbarMdCostConstructionChildrenTable" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" onclick="construction.deleteMdDevelopmentInfrastructureChildrenTable('#landMdCostConstructionChildrenTable')">删除</button>
                            <button type="button" class="btn btn-primary" onclick="construction.editMdDevelopmentInfrastructureChildrenTable('#landMdCostConstructionChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',true)">编辑</button>
                            <button type="button" class="btn btn-primary" onclick="construction.editMdDevelopmentInfrastructureChildrenTable('#landMdCostConstructionChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',false)">添加</button>
                        </div>
                    </div>
                    <table class="table table-bordered" id="landMdCostConstructionChildrenTable">

                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    公共配套设施建设费<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required"
                           placeholder="公共配套设施建设费" class="form-control" onblur="cost.checkParams(this);construction.calculationE14()"
                           name="infrastructureMatchingCost" value="${mdCostConstruction.infrastructureMatchingCost}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <c:if test="${empty mdCostConstruction.infrastructureMatchingCostExplain}">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="infrastructureMatchingCostExplain" value="医疗卫生、文化体育、教育、社区服务">
                    </c:if>
                    <c:if test="${!empty mdCostConstruction.infrastructureMatchingCostExplain}">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="infrastructureMatchingCostExplain" value="${mdCostConstruction.infrastructureMatchingCostExplain}">
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                开发期间税费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-5">
                    <div class="input-group">
                        <input type="text" required="required"
                               placeholder="公共配套设施建设费" class="form-control" onblur="cost.checkParams(this);construction.calculationE15()"
                               name="devDuring" value="${mdCostConstruction.devDuring}">
                        <span class="input-group-btn">
                        </span>
                        <select name="devDuring2"
                                class="form-control " onchange="construction.calculationE15Select(this);" >
                            <option value="10">2009-2032 金额 10</option>
                            <c:forEach items="${dataInfrastructureList}" var="item">
                                <c:if test="${item.devTaxTotal != 0}">
                                    <c:if test="${mdCostConstruction.devDuring != item.devTaxTotal}">
                                        <option value="${item.devTaxTotal}">${item.timeSlot} 金额:${item.devTaxTotal}</option>
                                    </c:if>
                                    <c:if test="${mdCostConstruction.devDuring == item.devTaxTotal}">
                                        <option value="${item.devTaxTotal}" selected="selected">${item.timeSlot} 金额:${item.devTaxTotal}</option>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                其它工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" value="${mdCostConstruction.otherEngineeringCost}" onblur="cost.checkParams(this);construction.calculationE16()"
                           placeholder="其它工程费" required class="form-control" name="otherEngineeringCost">
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" readonly="readonly" class="form-control" name="e12" placeholder="e12" onblur="construction.calculationE11();">
    <input type="hidden" readonly="readonly" class="form-control" name="e11" placeholder="e11" onblur="construction.calculationE17();">
    <input type="hidden" readonly="readonly" class="form-control" name="e13" placeholder="e13" onblur="construction.calculationE17();">
    <input type="hidden" readonly="readonly" class="form-control" name="e14" placeholder="e14" onblur="construction.calculationE17();">
    <input type="hidden" readonly="readonly" class="form-control" name="e15" placeholder="e15" onblur="construction.calculationE17();">
    <input type="hidden" readonly="readonly" class="form-control" name="e16" placeholder="e16" onblur="construction.calculationE17();">


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
                    不可预见费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpenses" onblur="cost.checkParams(this);construction.calculationE18();"
                           value="${mdCostConstruction.unforeseenExpenses}" data-value="${mdCostConstruction.unforeseenExpenses}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control" name="unforeseenExpensesExplain" onblur=""
                           value="${mdCostConstruction.unforeseenExpensesExplain}" data-value="">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    管理费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="管理费率" class="form-control x-percent" name="managementExpense" onblur="cost.checkParams(this);construction.calculationE19();"
                           value="${mdCostConstruction.managementExpense}" data-value="${mdCostConstruction.managementExpense}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control " name="managementExpenseExplain" onblur=""
                           value="${mdCostConstruction.managementExpenseExplain}" data-value="">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="销售费率" class="form-control x-percent" name="salesFee" onblur="cost.checkParams(this);construction.calculationG23();construction.calculationG21();"
                           value="${mdCostConstruction.salesFee}" data-value="${mdCostConstruction.salesFee}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control " name="salesFeeExplain" onblur=""
                           value="${mdCostConstruction.salesFeeExplain}" data-value="">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    投资利息率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="投资利息率" class="form-control x-percent" name="interestInvestmentTax" onblur="cost.checkParams(this);construction.calculationE21();construction.calculationG21();"
                           value="${mdCostConstruction.interestInvestmentTax}" data-value="${mdCostConstruction.interestInvestmentTax}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control " name="interestInvestmentTaxExplain" onblur=""
                           value="${mdCostConstruction.interestInvestmentTaxExplain}" data-value="">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售税金及附加率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="销售税金及附加率" class="form-control x-percent" name="salesTaxAndAdditional" onblur="cost.checkParams(this);construction.calculationG24()"
                           value="${mdCostConstruction.salesTaxAndAdditional}" data-value="${mdCostConstruction.salesTaxAndAdditional}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control " name="salesTaxAndAdditionalExplain" onblur=""
                           value="${mdCostConstruction.salesTaxAndAdditionalExplain}" data-value="">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发利润率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="开发利润率" class="form-control x-percent" name="investmentProfitTax" onblur="cost.checkParams(this);construction.calculationG23();construction.calculationE23();"
                           data-value="${mdCostConstruction.investmentProfitTax}"  value="${mdCostConstruction.investmentProfitTax}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="说明" class="form-control " name="investmentProfitTaxExplain" onblur=""
                            data-value=""  value="${mdCostConstruction.investmentProfitTaxExplain}">
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" readonly="readonly" class="form-control" name="e18" placeholder="e18" onblur="construction.calculationE19();">
    <input type="hidden" readonly="readonly" class="form-control" name="e19" placeholder="e19" onblur="construction.calculationE23();construction.calculationE21()">
    <input type="hidden" readonly="readonly" class="form-control" name="g23" placeholder="g23" onblur="construction.calculationG24();">
    <input type="hidden" readonly="readonly" class="form-control" name="g24" placeholder="g24" onblur="construction.constructionAssessmentValueCalculationE25();">
    <input type="hidden" readonly="readonly" class="form-control" name="g21" placeholder="g21" onblur="construction.calculationG24();">
</div>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>测算结果</h3>
        <div class="clearfix"></div>
    </div>
    <input type="hidden" readonly="readonly" name="constructionAssessmentValue2" onblur="construction.constructionAssessmentValueCalculationE25();" value="${mdCostConstruction.constructionAssessmentValue2}" class="form-control" placeholder="在建工程评估价值2">
    <div class="x_content">
        <div class="form-group">
            <div class="col-md-12 col-sm-12">
                <table class="table table-bordered">
                    <tfoot>
                    <tr>
                        <td> 土地取得成本小计</td>
                        <td><label class="landGetCostTotal">${mdCostConstruction.landGetCostTotal}</label> <input type="hidden" onblur="construction.calculationE19();" value="${mdCostConstruction.landGetCostTotal}" name="landGetCostTotal" placeholder="土地取得成本小计"> </td>
                    </tr>
                    <tr>
                        <td> 建设成本小计</td>
                        <td><label class="constructionSubtotal">${mdCostConstruction.constructionSubtotal}</label>
                            <input type="hidden" onblur="construction.calculationE18();"
                                   value="${mdCostConstruction.constructionSubtotal}" name="constructionSubtotal" placeholder="建设成本小计">
                        </td>
                    </tr>
                    <tr>
                        <td> 投资利息</td>
                        <td><label class="interestInvestment">${mdCostConstruction.interestInvestment}</label><input onblur="construction.constructionAssessmentValue2calculationE24()" type="hidden" value="${mdCostConstruction.interestInvestment}" name="interestInvestment" placeholder="投资利息"> </td>
                    </tr>
                    <tr>
                        <td> 开发利润</td>
                        <td><label class="investmentProfit">${mdCostConstruction.investmentProfit}</label><input type="hidden" onblur="construction.constructionAssessmentValue2calculationE24()" value="${mdCostConstruction.investmentProfit}" name="investmentProfit" placeholder="开发利润"> </td>
                    </tr>
                    <tr>
                        <td> 在建工程评估价值</td>
                        <td><label class="constructionAssessmentValue">${mdCostConstruction.constructionAssessmentValue}</label><input type="hidden" onblur="construction.constructionAssessmentPriceCorrectingCalculationE26();" value="${mdCostConstruction.constructionAssessmentValue}" name="constructionAssessmentValue" placeholder="在建工程评估价值"> </td>
                    </tr>
                    <tr>
                        <td> 在建工程单位价</td>
                        <td><label class="constructionAssessmentPriceCorrecting">${mdCostConstruction.constructionAssessmentPriceCorrecting}</label><input type="hidden" value="${mdCostConstruction.constructionAssessmentPriceCorrecting}" name="constructionAssessmentPriceCorrecting" placeholder="在建工程单位价"> </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="boxMdCostConstruction" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装工程费</h3>
            </div>
            <form id="frmMdCostConstruction" class="form-horizontal">
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
                            onclick="construction.constructionInstallationEngineeringFeeEvent.save();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
