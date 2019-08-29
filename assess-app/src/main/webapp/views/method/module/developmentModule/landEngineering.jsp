<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>基本参数</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    项目建设期(年)<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.projectConstructionPeriod}" required="required"
                           placeholder="项目建设期(年)"
                           class="form-control"  name="projectConstructionPeriod" onblur="checkParams(this);landEngineering.calculationF34(); landEngineering.calculationD34();">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    已开发时间(年)
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.developedYear}"
                           placeholder="已开发时间(年)"
                           class="form-control"  name="developedYear" onblur="checkParams(this);projectConstructionPeriodFun(this);">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    剩余开发时间(年)
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.remainingDevelopmentYear}"
                           placeholder="剩余开发时间(年)"
                           class="form-control"  name="remainingDevelopmentYear" onblur="checkParams(this);projectConstructionPeriodFun(this);">
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
        <h3>收入类(参数)</h3>
        <div class="clearfix"></div>
    </div>


    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                    <div id="toolbarLandIncomeCategoryTableId" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" onclick="landEngineering.deleteMdDevelopmentIncomeCategoryTable('#landIncomeCategoryTableId')">删除</button>
                            <button type="button" class="btn btn-primary" onclick="landEngineering.editMdDevelopmentIncomeCategoryTable('#landIncomeCategoryTableId','#basicMdDevelopmentIncomeCategoryModalTool',true)">编辑</button>
                            <button type="button" class="btn btn-primary" onclick="landEngineering.editMdDevelopmentIncomeCategoryTable('#landIncomeCategoryTableId','#basicMdDevelopmentIncomeCategoryModalTool',false)">添加</button>
                        </div>
                    </div>
                    <table class="table table-striped" id="landIncomeCategoryTableId" >

                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                    <table class="table table-condensed">
                        <tfoot>
                        <tr>
                        </tr>
                        <tr>
                            <td>收入类预期销售合计:</td>
                            <td class="info">规划建筑面积<label name="plannedBuildingArea" class="label label-default">${mdDevelopment.plannedBuildingArea}</label><input type="hidden"  value="${mdDevelopment.plannedBuildingArea}" name="plannedBuildingArea"></td>
                            <td class="info">总可售面积售价<label name="totalSaleableAreaPrice" class="label label-default">${mdDevelopment.totalSaleableAreaPrice}</label><input type="hidden"  value="${mdDevelopment.totalSaleableAreaPrice}" name="totalSaleableAreaPrice" onblur="landEngineering.calculationF33();landEngineering.calculationF40();landEngineering.calculationF36()"></td>
                            <td class="info">可售面积<label name="saleableArea" class="label label-default">${mdDevelopment.saleableArea}</label><input type="hidden"  value="${mdDevelopment.saleableArea}" name="saleableArea" onblur="landEngineering.calculationF18();"></td>
                            <td class="active">
                                <!-- 不可售建筑面积 -->
                                <a data-key="unsaleableBuildingArea">${mdDevelopment.unsaleableBuildingArea}</a>
                                <input type="hidden" value="${mdDevelopment.unsaleableBuildingArea}" name="unsaleableBuildingArea" class="form-control" onblur="landEngineering.calculationF18();">
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="x_panel">

    <input type="hidden"
           placeholder="f18" class="form-control" readonly="readonly"
           name="f18" onblur="landEngineering.calculationD41();">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>单位成本或费率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    勘察设计和前期工程费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.reconnaissanceDesign}"
                           placeholder="勘察设计和前期工程费率" class="form-control x-percent"
                           required="required"
                           name="reconnaissanceDesign" onblur="checkParams(this);landEngineering.calculationD20()" data-value="${mdDevelopment.reconnaissanceDesign}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.reconnaissanceDesignExplain}"
                           placeholder="说明" class="form-control" name="reconnaissanceDesignExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <div id="toolbarMdCalculatingMethodEngineeringCostLand" style="display: none">
                        <div class="input-group">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-info disabled">
                                    建筑安装工程费
                                </button>
                                <button type="button" class="btn btn-primary" onclick="landEngineering.delMdCalculatingMethodEngineeringCost()">删除</button>
                                <button type="button" class="btn btn-primary" onclick="landEngineering.constructionInstallationEngineeringFeeEvent.appendHTML();">添加</button>
                            </span>
                            <span class="input-group-btn">
                                <input type="text" readonly="readonly"  class="form-control" onblur="landEngineering.calculationD21()"
                                       name="constructionInstallationEngineeringFee"  value="${mdDevelopment.constructionInstallationEngineeringFee}" >
                                <input type="hidden" name="constructionInstallationEngineeringFeeIds" value="${mdDevelopment.constructionInstallationEngineeringFeeIds}">
                            </span>
                        </div>
                    </div>
                    <table class="table table-striped" id="landConstructionInstallationEngineeringFeeInfoTarget" >

                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    基础设施配套费 <span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" required="required"
                               placeholder="基础设施配套费"  class="form-control"
                               name="infrastructureCost" onblur="checkParams(this);landEngineering.calculationD22(this)" value="${mdDevelopment.infrastructureCost}">
                        <span class="input-group-btn">
                        </span>
                        <select name="f22Value" class="form-control" onchange="landEngineering.calculationF22(this)">
                            <option>请选择</option>
                            <c:forEach items="${dataInfrastructureList}" var="item">
                                <c:if test="${item.infrastructureSupportingFacilities != 0}">
                                    <c:if test="${mdDevelopment.f22 != item.infrastructureSupportingFacilities}">
                                        <option value="${item.infrastructureSupportingFacilities}" data-key="${item.id}" data-type="${item.type}">${item.timeSlot} 金额:${item.infrastructureSupportingFacilities}</option>
                                    </c:if>
                                    <c:if test="${mdDevelopment.f22 == item.infrastructureSupportingFacilities}">
                                        <option value="${item.infrastructureSupportingFacilities}" selected="selected"  data-key="${item.id}"  data-type="${item.type}">${item.timeSlot} 金额:${item.infrastructureSupportingFacilities}</option>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            <option value="100">2012-2017 金额100</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.infrastructureCostExplain}"
                           placeholder="说明" class="form-control" name="infrastructureCostExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                    <div id="toolbarMdDevelopmentInfrastructureChildrenTable" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" onclick="landEngineering.deleteMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable')">删除</button>
                            <button type="button" class="btn btn-primary" onclick="landEngineering.editMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',true)">编辑</button>
                            <button type="button" class="btn btn-primary" onclick="landEngineering.editMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',false)">添加</button>
                        </div>
                    </div>
                    <table class="table table-bordered" id="landMdDevelopmentInfrastructureChildrenTable">

                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    公共配套设施建设费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required="required"
                               placeholder="公共配套设施建设费"  class="form-control"
                               name="infrastructureMatchingCost" onblur="checkParams(this);landEngineering.calculationD23(this)" value="${mdDevelopment.infrastructureMatchingCost}">
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <c:if test="${empty mdDevelopment.infrastructureMatchingCostExplain}">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="infrastructureMatchingCostExplain" value="医疗卫生、文化体育、教育、社区服务">
                    </c:if>
                    <c:if test="${!empty mdDevelopment.infrastructureMatchingCostExplain}">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="infrastructureMatchingCostExplain" value="${mdDevelopment.infrastructureMatchingCostExplain}">
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发期间税费<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required"
                           placeholder="开发期间税费"  class="form-control"
                           name="devDuring" onblur="checkParams(this);landEngineering.calculationD24(this)" value="${mdDevelopment.devDuring}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.devDuringExplain}"
                           placeholder="说明" class="form-control" name="devDuringExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    其它工程费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.otherEngineeringCost}" required="required"
                           placeholder="其它工程费率" class="form-control x-percent" name="otherEngineeringCost" onblur="checkParams(this);landEngineering.calculationD25()"  data-value="${mdDevelopment.otherEngineeringCost}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.otherEngineeringCostExplain}"
                           placeholder="说明" class="form-control" name="otherEngineeringCostExplain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    不可预见费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required"
                           placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpenses" onblur="checkParams(this);landEngineering.calculationD27()"  value="${mdDevelopment.unforeseenExpenses}" data-value="${mdDevelopment.unforeseenExpenses}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" value="${mdDevelopment.unforeseenExpensesExplain}"
                           placeholder="说明" class="form-control" name="unforeseenExpensesExplain">
                </div>
            </div>
        </div>
    </div>

    <input type="hidden"
           placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"
           name="d20">

    <input type="hidden"
           placeholder="建筑安装工程费" class="form-control" readonly="readonly"
           name="d21">

    <input type="hidden"
           placeholder="基础设施费用" class="form-control" readonly="readonly"
           name="d22" >

    <input type="hidden"
           placeholder="公共配套设施建设费" class="form-control" readonly="readonly"
           name="d23">

    <input type="hidden"
           placeholder="开发期间税费" class="form-control" readonly="readonly"
           name="d24">

    <input type="hidden"
           placeholder="其它工程费" class="form-control" readonly="readonly"
           name="d25">

    <input type="hidden"
           placeholder="不可预见费" class="form-control" readonly="readonly"
           name="d27">


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
                           placeholder="契税率" class="form-control x-percent"  required="required"
                           name="deedTaxRate" onblur="checkParams(this);landEngineering.calculationD28();landEngineering.calculationH40();" value="${mdDevelopment.deedTaxRate}"  data-value="${mdDevelopment.deedTaxRate}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="费率说明" class="form-control"
                           name="deedTaxRateExplain" value="${mdDevelopment.deedTaxRateExplain}">
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
                           name="transactionTaxRate" onblur="checkParams(this);landEngineering.calculationD28();landEngineering.calculationH40();" value="${mdDevelopment.transactionTaxRate}"  data-value="${mdDevelopment.transactionTaxRate}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="费率说明" class="form-control"
                           name="transactionTaxRateExplain" value="${mdDevelopment.transactionTaxRateExplain}">
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
                           name="managementExpense" onblur="checkParams(this);landEngineering.calculationD32();landEngineering.calculationF32()" value="${mdDevelopment.managementExpense}"  data-value="${mdDevelopment.managementExpense}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="费率说明" class="form-control"
                           name="managementExpenseExplain" value="${mdDevelopment.managementExpenseExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地取得附加成本" class="form-control" data-rule-number='true'
                           name="landGetRelevant" onblur="checkParams(this);landEngineering.calculationF32();landEngineering.calculationF35();landEngineering.calculationF40()" value="${mdDevelopment.landGetRelevant}" >
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地取得成本说明" class="form-control" name="landGetRelevantExplain" value="${mdDevelopment.landGetRelevantExplain}">
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
                           name="salesFee" onblur="checkParams(this);landEngineering.calculationF33()" value="${mdDevelopment.salesFee}"   data-value="${mdDevelopment.salesFee}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="salesFeeExplain" value="${mdDevelopment.salesFeeExplain}">
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
                           name="interestInvestmentTax" onblur="checkParams(this);landEngineering.calculationD34();landEngineering.calculationF34()" value="${mdDevelopment.interestInvestmentTax}"  data-value="${mdDevelopment.interestInvestmentTax}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="interestInvestmentTaxExplain" value="${mdDevelopment.interestInvestmentTaxExplain}">
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
                           name="investmentProfitTax" onblur="checkParams(this);landEngineering.calculationD35();landEngineering.calculationF35()" value="${mdDevelopment.investmentProfitTax}"  data-value="${mdDevelopment.investmentProfitTax}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="investmentProfitTaxExplain" value="${mdDevelopment.investmentProfitTaxExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售环节增值税及附加<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="销售环节增值税及附加" class="form-control x-percent" required="required"
                           name="salesTaxAndAdditional" onblur="checkParams(this);landEngineering.calculationD36()" value="${mdDevelopment.salesTaxAndAdditional}"  data-value="${mdDevelopment.salesTaxAndAdditional}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="salesTaxAndAdditionalExplain" value="${mdDevelopment.salesTaxAndAdditionalExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地增值税<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地增值税" class="form-control x-percent" required="required"
                           name="landValueAddedTax" onblur="checkParams(this);landEngineering.calculationD36()" value="${mdDevelopment.landValueAddedTax}"   data-value="${mdDevelopment.landValueAddedTax}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="landValueAddedTaxExplain" value="${mdDevelopment.landValueAddedTaxExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    项目开发所得税
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="项目开发所得税" class="form-control x-percent"
                           name="projectDevelopmentIncomeTax" onblur="checkParams(this);landEngineering.calculationD36()" value="${mdDevelopment.projectDevelopmentIncomeTax}"   data-value="${mdDevelopment.projectDevelopmentIncomeTax}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="projectDevelopmentIncomeTaxExplain" value="${mdDevelopment.projectDevelopmentIncomeTaxExplain}">
                </div>
            </div>
        </div>
    </div>

    <input type="hidden"
           placeholder="单元格d28" class="form-control" readonly="readonly"
           name="d28">

    <input type="hidden"
           placeholder="管理费" class="form-control" readonly="readonly"
           name="d32" onblur="landEngineering.calculationD32();">

    <input type="hidden"
           placeholder="单元格f32" class="form-control" readonly="readonly"
           name="f32">

    <input type="hidden"
           placeholder="单元格f33" class="form-control" readonly="readonly"
           name="f33">

    <input type="hidden"
           placeholder="单元格d34" class="form-control" readonly="readonly"
           name="d34" onblur="landEngineering.calculationD34()">

    <input type="hidden"
           placeholder="单元格d35" class="form-control" readonly="readonly"
           name="d35" onblur="landEngineering.calculationD35()">

    <input type="hidden"
           placeholder="单元格d36" class="form-control" readonly="readonly"
           name="d36" onblur="">

    <input type="hidden"
           placeholder="单元格f36" class="form-control" readonly="readonly"
           name="f36" onblur="landEngineering.calculationF36();">

    <input type="hidden"
           placeholder="单元格h40" class="form-control" readonly="readonly"
           name="h40" onblur="landEngineering.calculationH40()">

    <input type="hidden"
           placeholder="单元格f40" class="form-control" readonly="readonly"
           name="f40" onblur="landEngineering.calculationF40()">

    <input type="hidden"
           placeholder="单元格e40" class="form-control" readonly="readonly"
           name="e40" onblur="landEngineering.calculationE40()">



</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>地价修正</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地还原率或者报酬率<span class="symbol required"></span>
                </label>

                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" required class="form-control x-percent" name="remunerationRate" value="${mdDevelopment.remunerationRate}"
                               placeholder="报酬率" readonly="readonly"
                               data-value="${mdDevelopment.remunerationRate}" onblur="landEngineering.calculationD43()">
                            <span class="input-group-btn">
                                    <input type="hidden" name="rewardRateId" value="${mdDevelopment.rewardRateId}">
                              <input type="button" class="btn btn-primary" value="报酬率测算"
                                     onclick="landEngineering.getRewardRate(this);"/>
                            </span>
                    </div>
                </div>
            </div>


        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                法定年限<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" placeholder="法定年限" class="form-control" required="required"
                           name="statutoryLife" onblur="checkParams(this);landEngineering.calculationD43()" value="${mdDevelopment.statutoryLife}">
                </div>
            </div>

            <label class="col-sm-1 control-label">
                剩余年限<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" placeholder="剩余年限" class="form-control" required="required"
                           name="remainingYears" onblur="landEngineering.calculationD43()" value="${mdDevelopment.remainingYears}">
                </div>
            </div>
        </div>



        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    权利状况修正<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="权利状况修正" class="form-control" required="required"
                           name="amendmentStatusRights" onblur="checkParams(this);landEngineering.calculationD47()" value="${mdDevelopment.amendmentStatusRights}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明" class="form-control" name="amendmentStatusRightsExplain" value="${mdDevelopment.amendmentStatusRightsExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    其他修正<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="其他修正" class="form-control" required="required"
                           name="otherAmendments" onblur="checkParams(this);landEngineering.calculationD47()" value="${mdDevelopment.otherAmendments}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明" class="form-control" name="otherAmendmentsExplain" value="${mdDevelopment.otherAmendmentsExplain}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发程度修正<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="开发程度修正" class="form-control" required="required"
                           name="developmentDegreeRevision" onblur="checkParams(this);landEngineering.calculationD47()" value="${mdDevelopment.developmentDegreeRevision}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明" class="form-control" name="developmentDegreeRevisionExplain" value="${mdDevelopment.developmentDegreeRevisionExplain}">
                </div>
            </div>
        </div>


    </div>
</div>

<input type="hidden"
       placeholder="单元格d43" class="form-control" readonly="readonly"
       name="d43" onblur="landEngineering.calculationD43()" value="">

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
                        <td> 工程建设成本小计</td>
                        <td class="constructionCostSubtotal">
                            <c:if test="${!empty mdDevelopment}">
                                <c:if test="${mdDevelopment.type == 1}">
                                    ${mdDevelopment.constructionCostSubtotal}</c:if>
                            </c:if>
                        </td>
                        <input type="hidden" placeholder="单元格d26" name="constructionCostSubtotal" value="${mdDevelopment.constructionCostSubtotal}" onblur="landEngineering.calculationD26();">
                    </tr>
                    <tr>
                        <td> 投资利息</td>
                        <td class="interestInvestment">
                            <c:if test="${!empty mdDevelopment}">
                                <c:if test="${mdDevelopment.type == 1}">
                                    ${mdDevelopment.interestInvestment}
                                </c:if>
                            </c:if>
                        </td>
                        <input type="hidden" placeholder="单元格f34" name="interestInvestment" value="${mdDevelopment.interestInvestment}" onblur="landEngineering.calculationF34();">
                    </tr>
                    <tr>
                        <td> 投资利润</td>
                        <td class="investmentProfit">
                            <c:if test="${!empty mdDevelopment}">
                                <c:if test="${mdDevelopment.type == 1}">
                                    ${mdDevelopment.investmentProfit}
                                </c:if>
                            </c:if>
                        </td>
                        <input type="hidden" placeholder="单元格f35" name="investmentProfit" value="${mdDevelopment.investmentProfit}" onblur="landEngineering.calculationF35();">
                    </tr>
                    <tr>
                        <td> 委估土地单价（元/㎡）</td>
                        <td class="assessPrice">
                            <c:if test="${!empty mdDevelopment}">
                                <c:if test="${mdDevelopment.type == 1}">
                                    ${mdDevelopment.assessPrice}
                                </c:if>
                            </c:if>
                        </td>
                        <input type="hidden"
                               placeholder="单元格d41" class="form-control" readonly="readonly"
                               name="assessPrice" onblur="landEngineering.calculationD41()" value="${mdDevelopment.assessPrice}">
                    </tr>
                    <tr>
                        <td> 测算单价</td>
                        <td  name="price">
                                <c:if test="${!empty mdDevelopment}">
                                    <c:if test="${mdDevelopment.type == 1}">
                                        ${mdDevelopment.price}
                                    </c:if>
                                </c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
                 <input type="hidden" placeholder="单元格d47" name="price" value="${mdDevelopment.price}" onblur="landEngineering.calculationD47();">
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
            <input type="hidden" name="id">
            <input type="hidden" name="masterId">
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">

                        </div>
                    </div>
                </div>
            </div>
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



