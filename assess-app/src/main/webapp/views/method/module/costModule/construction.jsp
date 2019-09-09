<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal" id="constructionFrm">

    <div class="x_panel">
        <input type="hidden" name="id" value="${mdCostVo.mdCostConstruction.id}">
        <input type="hidden" name="pid" value="${mdCostVo.mdCostConstruction.pid}">
        <input type="hidden" name="mcId" value="${mdCostVo.mdCostConstruction.mcId}">
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
                        <input type="text" name="developLandAreaTax"  onblur="cost.checkParams(this);"
                               placeholder=" 开发土地面积(㎡)" class="form-control" data-rule-number='true' required="required" value="${mdCostVo.mdCostConstruction.developLandAreaTax}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        开发建筑面积(㎡)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="developBuildAreaTax" onblur="cost.checkParams(this);"
                               placeholder="开发建筑面积(㎡)" class="form-control" data-rule-number='true' required="required" value="${mdCostVo.mdCostConstruction.developBuildAreaTax}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        开发期（年）<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">

                        <input type="text" name="developYearNumberTax" onblur="cost.checkParams(this);"
                               placeholder="开发期（年）" class="form-control" data-rule-number='true' required="required" value="${mdCostVo.mdCostConstruction.developYearNumberTax}">
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
            <h3>经济指标(参考值)</h3>
            <div class="clearfix"></div>
        </div>


        <div class="x_content">
            <div class="form-group">
                <div class="x-valid">
                    <div class="col-sm-12">
                        <div id="toolbarLandIncomeCategoryTableId" style="display: none">
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary" onclick="construction.deleteMdDevelopmentIncomeCategoryTable('#landIncomeCategoryTableId')">删除</button>
                                <button type="button" class="btn btn-primary" onclick="construction.editMdDevelopmentIncomeCategoryTable('#landIncomeCategoryTableId','#basicMdDevelopmentIncomeCategoryModalTool',true)">编辑</button>
                                <button type="button" class="btn btn-primary" onclick="construction.editMdDevelopmentIncomeCategoryTable('#landIncomeCategoryTableId','#basicMdDevelopmentIncomeCategoryModalTool',false)">添加</button>
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
                                <td>预期销售合计:</td>
                                <td class="info">规划建筑面积<label name="plannedBuildingArea" class="label label-default"></label></td>
                                <td class="info">总可售面积售价<label name="totalSaleableAreaPrice" class="label label-default"></label></td>
                                <td class="info">可售面积<label name="saleableArea" class="label label-default"></label></td>
                                <td class="active">
                                    <!-- 不可售建筑面积 -->
                                    <a data-key="unsaleableBuildingArea"></a>
                                    <input type="hidden" value="" name="unsaleableBuildingArea" class="form-control" >
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="x_panel" id="LAND_ACQUISITION_COST">

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
                        土地购买价格(万元)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="landPurchasePrice" placeholder="土地购买价格" data-rule-number="true" onblur="cost.checkParams(this);"
                                   class="form-control" required="required" value="${mdCostVo.mdCostConstruction.landPurchasePrice}">
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
                               name="landPurchasePriceExplain" value="${mdCostVo.mdCostConstruction.landPurchasePriceExplain}">
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
                               placeholder="土地取得相关税费率" class="form-control x-percent"  onblur="cost.checkParams(this);"
                               required="required" value="${mdCostVo.mdCostConstruction.landGetRelevant}" data-value="${mdCostVo.mdCostConstruction.landGetRelevant}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="landGetRelevantExplain" value="${mdCostVo.mdCostConstruction.landGetRelevantExplain}">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        土地取得附加成本<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="additionalCostLandAcquisition" required="required"
                               placeholder="土地取得附加成本" class="form-control" data-rule-number='true' onblur="cost.checkParams(this);" value="${mdCostVo.mdCostConstruction.additionalCostLandAcquisition}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="additionalCostLandAcquisitionExplain" value="${mdCostVo.mdCostConstruction.additionalCostLandAcquisitionExplain}">
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
            <h3>建设成本</h3>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        勘察设计和前期工程费率<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="reconnaissanceDesign" onblur="cost.checkParams(this);"
                               placeholder="勘察设计和前期工程费率" class="form-control x-percent"
                               required="required" value="${mdCostVo.mdCostConstruction.reconnaissanceDesign}" data-value="${mdCostVo.mdCostConstruction.reconnaissanceDesign}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="reconnaissanceDesignExplain" value="${mdCostVo.mdCostConstruction.reconnaissanceDesignExplain}">
                    </div>
                </div>
            </div>

            <div class="form-group">

            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    建筑安装工程费
                </label>
                <div class="x-valid">
                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                        <input type="text" readonly="readonly"  class="form-control" onblur="cost.checkParams(this);"
                               name="constructionInstallationEngineeringFee"  value="${mdCostVo.mdCostConstruction.constructionInstallationEngineeringFee}" >
                    </div>
                </div>
                <div class="x-valid">
                    <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                        <div id="toolbarMdCalculatingMethodEngineeringCost" style="display: none">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" onclick="construction.delMdCalculatingMethodEngineeringCost()">删除</button>
                                    <button type="button" class="btn btn-primary" onclick="construction.showMdCalculatingMethodEngineeringCost();">添加</button>
                                </span>

                                <span class="input-group-btn">
                                </span>
                            </div>
                        </div>

                        <table class="table table-striped" id="engineeringConstructionInstallationEngineeringFeeInfoTarget" >

                        </table>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    基础设施建设费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" required="required"
                                   placeholder="基础设施配套费"  class="form-control"
                                   name="infrastructureCost" onblur="cost.checkParams(this);" value="${mdCostVo.mdCostConstruction.infrastructureCost}">
                            <span class="input-group-btn">
                            </span>
                            <select name="infrastructureCostValue"
                                    class="form-control" onchange="construction.calculationE13Select(this)">
                                <option value="0">请选择</option>
                                <c:forEach items="${dataInfrastructureList}" var="item">
                                    <c:if test="${item.infrastructureSupportingFacilities != 0}">
                                        <c:if test="${mdCostVo.mdCostConstruction.infrastructureCost != item.infrastructureSupportingFacilities}">
                                            <option value="${item.infrastructureSupportingFacilities}" data-key="${item.id}" data-type="${item.type}">${item.timeSlot} 金额:${item.infrastructureSupportingFacilities}</option>
                                        </c:if>
                                        <c:if test="${mdCostVo.mdCostConstruction.infrastructureCost == item.infrastructureSupportingFacilities}">
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
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="infrastructureCostExplain" value="${mdCostVo.mdCostConstruction.infrastructureCostExplain}">
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
                               placeholder="公共配套设施建设费" class="form-control" onblur="cost.checkParams(this);"
                               name="infrastructureMatchingCost" value="${mdCostVo.mdCostConstruction.infrastructureMatchingCost}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <c:if test="${empty mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}">
                            <input type="text"
                                   placeholder="说明" class="form-control"
                                   name="infrastructureMatchingCostExplain" value="医疗卫生、文化体育、教育、社区服务">
                        </c:if>
                        <c:if test="${!empty mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}">
                            <input type="text"
                                   placeholder="说明" class="form-control"
                                   name="infrastructureMatchingCostExplain" value="${mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    开发期间税费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required="required"
                               placeholder="公共配套设施建设费" class="form-control" onblur="cost.checkParams(this);"
                               name="devDuring" value="${mdCostVo.mdCostConstruction.devDuring}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="devDuringExplain" value="${mdCostVo.mdCostConstruction.devDuringExplain}">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    其它工程费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" value="${mdCostVo.mdCostConstruction.otherEngineeringCost}" onblur="cost.checkParams(this);"
                               placeholder="其它工程费" required class="form-control" name="otherEngineeringCost">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control"
                               name="otherEngineeringCostExplain" value="${mdCostVo.mdCostConstruction.otherEngineeringCostExplain}">
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
                        不可预见费率<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpenses" onblur="cost.checkParams(this);"
                               value="${mdCostVo.mdCostConstruction.unforeseenExpenses}" data-value="${mdCostVo.mdCostConstruction.unforeseenExpenses}">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control" name="unforeseenExpensesExplain" onblur=""
                               value="${mdCostVo.mdCostConstruction.unforeseenExpensesExplain}" data-value="">
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
                               placeholder="管理费率" class="form-control x-percent" name="managementExpense" onblur="cost.checkParams(this);"
                               value="${mdCostVo.mdCostConstruction.managementExpense}" data-value="${mdCostVo.mdCostConstruction.managementExpense}">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control " name="managementExpenseExplain"
                               value="${mdCostVo.mdCostConstruction.managementExpenseExplain}" data-value="">
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
                               placeholder="销售费率" class="form-control x-percent" name="salesFee" onblur="cost.checkParams(this);"
                               value="${mdCostVo.mdCostConstruction.salesFee}" data-value="${mdCostVo.mdCostConstruction.salesFee}">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control " name="salesFeeExplain" onblur=""
                               value="${mdCostVo.mdCostConstruction.salesFeeExplain}" data-value="">
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
                               placeholder="投资利息率" class="form-control x-percent" name="interestInvestmentTax" onblur="cost.checkParams(this);"
                               value="${mdCostVo.mdCostConstruction.interestInvestmentTax}" data-value="${mdCostVo.mdCostConstruction.interestInvestmentTax}">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control " name="interestInvestmentTaxExplain"
                               value="${mdCostVo.mdCostConstruction.interestInvestmentTaxExplain}" data-value="">
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
                               placeholder="销售税金及附加率" class="form-control x-percent" name="salesTaxAndAdditional" onblur="cost.checkParams(this);"
                               value="${mdCostVo.mdCostConstruction.salesTaxAndAdditional}" data-value="${mdCostVo.mdCostConstruction.salesTaxAndAdditional}">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control " name="salesTaxAndAdditionalExplain"
                               value="${mdCostVo.mdCostConstruction.salesTaxAndAdditionalExplain}" data-value="">
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
                               placeholder="开发利润率" class="form-control x-percent" name="investmentProfitTax" onblur="cost.checkParams(this);"
                               data-value="${mdCostVo.mdCostConstruction.investmentProfitTax}"  value="${mdCostVo.mdCostConstruction.investmentProfitTax}">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="说明" class="form-control " name="investmentProfitTaxExplain" onblur=""
                               data-value=""  value="${mdCostVo.mdCostConstruction.investmentProfitTaxExplain}">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        成新率(非必需)
                    </label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="hidden" placeholder="成新率"  name="residueRatioId"   value="${mdCostVo.mdCostConstruction.residueRatioId}">
                            <span class="input-group-btn">
                                <input type="hidden"  name="residueRatio" onblur="cost.checkParams(this);"  value="${mdCostVo.mdCostConstruction.residueRatio}">
                                <input type="text" placeholder="成新率(计算值)" class="form-control" name="residueRatioShow" readonly="readonly" value="${mdCostVo.mdCostConstruction.residueRatio}">
                            </span>
                            <span class="input-group-btn">
                                <button type="button" class="btn-primary btn" onclick="construction.callResidueRatio(this,false)"> <i class="fa fa-dashcube"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="x_panel">
        <input type="hidden" readonly="readonly" name="constructionAssessmentValue2"  value="${mdCostVo.mdCostConstruction.constructionAssessmentValue2}" class="form-control" placeholder="在建工程评估价值2">
        <input type="hidden" value="${mdCostVo.mdCostConstruction.landGetCostTotal+0}" name="landGetCostTotal" placeholder="土地取得成本小计">
        <input type="hidden" value="${mdCostVo.mdCostConstruction.constructionSubtotal}" name="constructionSubtotal" placeholder="建设成本小计">
        <input type="hidden" value="${mdCostVo.mdCostConstruction.interestInvestment}" name="interestInvestment" placeholder="投资利息">
        <input type="hidden" value="${mdCostVo.mdCostConstruction.investmentProfit}" name="investmentProfit" placeholder="开发利润">
        <input type="hidden" value="${mdCostVo.mdCostConstruction.constructionAssessmentValue}" name="constructionAssessmentValue" placeholder="在建工程评估价值">
        <input type="hidden" value="${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}" name="constructionAssessmentPriceCorrecting" placeholder="在建工程单位价">
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
                        <tfoot>
                        <tr>
                            <td> 土地取得成本小计</td>
                            <td><label class="landGetCostTotal">${mdCostVo.mdCostConstruction.landGetCostTotal}</label> </td>
                        </tr>
                        <tr>
                            <td> 建设成本小计</td>
                            <td><label class="constructionSubtotal">${mdCostVo.mdCostConstruction.constructionSubtotal}</label>
                            </td>
                        </tr>
                        <tr>
                            <td> 投资利息</td>
                            <td><label class="interestInvestment">${mdCostVo.mdCostConstruction.interestInvestment}</label>
                            </td>
                        </tr>
                        <tr>
                            <td> 开发利润</td>
                            <td><label class="investmentProfit">${mdCostVo.mdCostConstruction.investmentProfit}</label> </td>
                        </tr>
                        <tr>
                            <td> 在建工程评估价值</td>
                            <td><label class="constructionAssessmentValue">${mdCostVo.mdCostConstruction.constructionAssessmentValue}</label> </td>
                        </tr>
                        <tr>
                            <td> 在建工程单位价</td>
                            <td><label class="constructionAssessmentPriceCorrecting">${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}</label> </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

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
                        onclick="construction.saveMdCalculatingMethodEngineeringCost();">
                    保存
                </button>
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
                            onclick="construction.constructionInstallationEngineeringFeeEvent.save();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
