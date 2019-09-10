<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal" id="mdDevelopmentLandFrm">
    <div class="x_content">
        <div class="col-sm-12 form-group">
                <span class="col-sm-1">
                    <label>经营方式</label><span class="symbol required"></span>
                </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                     <input type="radio" id="developmentLand" name="type" value="1" checked="checked">
                    <label for="developmentLand">土地</label>
                </span>
            <span class="col-sm-2  checkbox-inline">
                    <input type="radio" id="developmentEngineering" name="type" value="2">
                    <label for="developmentEngineering">在建工程</label>
                </span>
        </div>
        <input type="hidden" name="id" value="${mdDevelopment.id}">
        <input type="hidden" name="economicId" value="${mdDevelopment.economicId}" onblur="checkParams(this);">
    </div>
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
                        项目建设期年( 已开发时间+剩余开发时间)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" value="${mdDevelopment.projectConstructionPeriod}" required="required"
                               placeholder="项目建设期(年)"
                               class="form-control"  name="projectConstructionPeriod" onblur="checkParams(this);">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        已开发时间(年)
                    </label>
                    <div class="col-sm-3">
                        <input type="text" value="${mdDevelopment.developedYear}"
                               placeholder="已开发时间(年)"
                               class="form-control"  name="developedYear" onblur="checkParams(this);">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        剩余开发时间(年)
                    </label>
                    <div class="col-sm-3">
                        <input type="text" value="${mdDevelopment.remainingDevelopmentYear}"
                               placeholder="剩余开发时间(年)"
                               class="form-control"  name="remainingDevelopmentYear" onblur="checkParams(this);">
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
            <h3>经济指标(参数)</h3>
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

                                <td class="info">总可售面积售价<label name="totalSaleableAreaPrice" class="label label-default">${mdDevelopment.totalSaleableAreaPrice}</label>
                                    <input type="hidden"  value="${mdDevelopment.totalSaleableAreaPrice}" name="totalSaleableAreaPrice">
                                </td>

                                <td class="info">可售面积<label name="saleableArea" class="label label-default">${mdDevelopment.saleableArea}</label>
                                    <input type="hidden"  value="${mdDevelopment.saleableArea}" name="saleableArea" onblur="checkParams(this);">
                                </td>
                                <td class="active">
                                    <!-- 不可售建筑面积 -->
                                    <a data-key="unsaleableBuildingArea">${mdDevelopment.unsaleableBuildingArea}</a>
                                    <input type="hidden" value="${mdDevelopment.unsaleableBuildingArea}" name="unsaleableBuildingArea" class="form-control" onblur="checkParams(this);">
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
                               name="reconnaissanceDesign" onblur="checkParams(this);" data-value="${mdDevelopment.reconnaissanceDesign}">
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
                    <label class="col-sm-1 control-label">
                        建筑安装工程费
                    </label>
                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                        <input type="text" readonly="readonly"  class="form-control" onblur="checkParams(this);"
                               name="constructionInstallationEngineeringFee"  value="${mdDevelopment.constructionInstallationEngineeringFee}" >
                    </div>
                </div>
                <div class="x-valid">
                    <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
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
                                   name="infrastructureCost" onblur="checkParams(this);" value="${mdDevelopment.infrastructureCost}">
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
                                   name="infrastructureMatchingCost" onblur="checkParams(this);" value="${mdDevelopment.infrastructureMatchingCost}">
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
                               name="devDuring" onblur="checkParams(this);" value="${mdDevelopment.devDuring}">
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
                               placeholder="其它工程费率" class="form-control x-percent" name="otherEngineeringCost" onblur="checkParams(this);"  data-value="${mdDevelopment.otherEngineeringCost}">
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
                               placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpenses" onblur="checkParams(this);"  value="${mdDevelopment.unforeseenExpenses}" data-value="${mdDevelopment.unforeseenExpenses}">
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
                               name="deedTaxRate" onblur="checkParams(this);" value="${mdDevelopment.deedTaxRate}"  data-value="${mdDevelopment.deedTaxRate}">
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
                               name="transactionTaxRate" onblur="checkParams(this);" value="${mdDevelopment.transactionTaxRate}"  data-value="${mdDevelopment.transactionTaxRate}">
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

                        管理费率
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="管理费率" class="form-control x-percent" required="required"
                               name="managementExpense" onblur="checkParams(this);" value="${mdDevelopment.managementExpense}"  data-value="${mdDevelopment.managementExpense}">
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
                               name="landGetRelevant" onblur="checkParams(this);" value="${mdDevelopment.landGetRelevant}" >
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="说明" class="form-control" name="landGetRelevantExplain" value="${mdDevelopment.landGetRelevantExplain}">
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
                               name="salesFee" onblur="checkParams(this);" value="${mdDevelopment.salesFee}"   data-value="${mdDevelopment.salesFee}">
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
                               name="interestInvestmentTax" onblur="checkParams(this);" value="${mdDevelopment.interestInvestmentTax}"  data-value="${mdDevelopment.interestInvestmentTax}">
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
                               name="investmentProfitTax" onblur="checkParams(this);" value="${mdDevelopment.investmentProfitTax}"  data-value="${mdDevelopment.investmentProfitTax}">
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
                               name="salesTaxAndAdditional" onblur="checkParams(this);" value="${mdDevelopment.salesTaxAndAdditional}"  data-value="${mdDevelopment.salesTaxAndAdditional}">
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
                               name="landValueAddedTax" onblur="checkParams(this);" value="${mdDevelopment.landValueAddedTax}"   data-value="${mdDevelopment.landValueAddedTax}">
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
                               name="projectDevelopmentIncomeTax" onblur="checkParams(this);" value="${mdDevelopment.projectDevelopmentIncomeTax}"   data-value="${mdDevelopment.projectDevelopmentIncomeTax}">
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
                            <input type="text" required="required" class="form-control x-percent" name="remunerationRate" value="${mdDevelopment.remunerationRate}"
                                   placeholder="报酬率"  data-value="${mdDevelopment.remunerationRate}" onblur="checkParams(this);">
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
                               name="statutoryLife" onblur="checkParams(this);" value="${mdDevelopment.statutoryLife}">
                    </div>
                </div>

                <label class="col-sm-1 control-label">
                    剩余年限<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" placeholder="剩余年限" class="form-control" required="required"
                               name="remainingYears" onblur="checkParams(this);" value="${mdDevelopment.remainingYears}">
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
                               name="amendmentStatusRights" onblur="checkParams(this);" value="${mdDevelopment.amendmentStatusRights}">
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
                               name="otherAmendments" onblur="checkParams(this);" value="${mdDevelopment.otherAmendments}">
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
                               name="developmentDegreeRevision" onblur="checkParams(this);" value="${mdDevelopment.developmentDegreeRevision}">
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



    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>测算结果</h3>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <input type="hidden" placeholder="单元格d26" name="constructionCostSubtotal" value="${mdDevelopment.constructionCostSubtotal}">
            <input type="hidden" placeholder="单元格f34" name="interestInvestment" value="${mdDevelopment.interestInvestment}">
            <input type="hidden" placeholder="单元格f35" name="investmentProfit" value="${mdDevelopment.investmentProfit}">
            <input type="hidden" placeholder="单元格d41" name="assessPrice"  value="${mdDevelopment.assessPrice}">
            <input type="hidden" placeholder="单元格d47" name="price" value="${mdDevelopment.price}">
            <div class="form-group">
                <div class="col-md-12 col-sm-12">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <td> 工程建设成本小计</td>
                            <td name="constructionCostSubtotal">
                                ${mdDevelopment.constructionCostSubtotal}
                            </td>

                        </tr>
                        <tr>
                            <td> 投资利息(万元)</td>
                            <td name="interestInvestment">
                                ${mdDevelopment.interestInvestment}
                            </td>
                        </tr>
                        <tr>
                            <td> 投资利润(万元)</td>
                            <td name="investmentProfit">
                                ${mdDevelopment.investmentProfit}
                            </td>
                        </tr>
                        <tr>
                            <td> 委估土地单价（元/㎡）</td>
                            <td name="assessPrice">
                                ${mdDevelopment.assessPrice}
                            </td>
                        </tr>
                        <tr>
                            <td> 测算单价(万元)</td>
                            <td name="price">
                                ${mdDevelopment.price}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

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


<div id="boxMdCalculatingMethodEngineeringCost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">工程费</h3>
            </div>
            <input type="hidden" name="id">
            <input type="hidden" name="masterId">
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <input type="hidden" name="id">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" class="form-control" name="area"
                                                   placeholder="面积" required="required">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="landEngineering.saveMdCalculatingMethodEngineeringCost();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="toolbarMdCalculatingMethodEngineeringCostLand" style="display: none">
    <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" onclick="landEngineering.delMdCalculatingMethodEngineeringCost()">删除</button>
                                    <button type="button" class="btn btn-primary" onclick="landEngineering.showMdCalculatingMethodEngineeringCost();">添加</button>
                                </span>
        <span class="input-group-btn">

                                </span>
    </div>
</div>



