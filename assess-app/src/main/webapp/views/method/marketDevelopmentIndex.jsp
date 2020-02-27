<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">

        <div class="card-body ">
            <form class="form-horizontal" id="mdDevelopmentLandFrm">

                <input type="hidden" name="type" value="${mdDevelopment.type}">
                <input type="hidden" name="id" value="${mdDevelopment.id}">
                <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                <input type="hidden" name="economicId" value="${mdDevelopment.economicId}"
                       onblur="loadParamsValue(this);">

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                经营方式 <span class="symbol required"></span>
                            </label>
                            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6" id="developmentCheckboxTool">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio"
                                               id="developmentLand" name="type" value="1" data-value="1">
                                        <span class="form-check-sign">土地</span>
                                    </label>
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio"
                                               id="developmentEngineering" name="type" value="2" data-value="2">
                                        <span class="form-check-sign">在建工程</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                ${judgeObject.name}
                                <small>(${judgeObject.evaluationArea}㎡)</small>
                                <small>
                                    <button class="btn btn-xs btn-primary" data-toggle="modal" type="button"
                                            href="#boxSchemeInfoModel"
                                            onclick="developmentCommon.loadSchemeInfoTableList({projectId:'${projectPlanDetails.projectId}',methodDataId:'${mdDevelopment.id}',methodType:'${methodTypeObj.id}'},'landEngineering.selectFun');">
                                        引用
                                    </button>
                                </small>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            基本参数
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                项目建设期年<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" disabled="disabled"
                                       value="${mdDevelopment.projectConstructionPeriod}" required="required"
                                       placeholder="项目建设期(年) = 已开发时间+剩余开发时间"
                                       class="form-control input-full" name="projectConstructionPeriod"
                                       onblur="checkParams(this);">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                已开发时间(年)
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.developedYear}"
                                       placeholder="已开发时间(年)"
                                       class="form-control input-full" name="developedYear" onblur="checkParams(this);">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                剩余开发时间(年)
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.remainingDevelopmentYear}"
                                       placeholder="剩余开发时间(年)"
                                       class="form-control input-full" name="remainingDevelopmentYear"
                                       onblur="checkParams(this);">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            经济指标(参数)
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <button type="button" class="btn btn-info btn-sm"
                                        onclick="landEngineering.showMdDevelopmentIncomeCategory('${mdDevelopment.economicId}');">
                                    经济规划指标 <i class="fa fa-search"></i></button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                规划建筑面积 (㎡)
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" value="${mdDevelopment.plannedBuildingArea}"
                                       class="form-control input-full"
                                       name="plannedBuildingArea" onblur="checkParams(this);" placeholder="规划建筑面积">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                总可售面积售价 (万元)
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" value="${mdDevelopment.totalSaleableAreaPrice}"
                                       class="form-control input-full"
                                       name="totalSaleableAreaPrice" onblur="checkParams(this);" placeholder="总可售面积售价">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                可售面积 (㎡)
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" value="${mdDevelopment.saleableArea}" class="form-control input-full"
                                       name="saleableArea" onblur="checkParams(this);" placeholder="可售面积">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                不可售建筑面积 (㎡)
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" value="${mdDevelopment.unsaleableBuildingArea}"
                                       class="form-control input-full"
                                       name="unsaleableBuildingArea" onblur="checkParams(this);" placeholder="不可售建筑面积">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                宗地外设定
                            </label>
                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                <div id="industrySupplyInfoContainer_BBBBB"></div>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                宗地内设定
                            </label>
                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                <div id="developmentDegreeContentContainer_BBBBB"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            单位成本
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                勘察设计和前期工程费率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.reconnaissanceDesign}"
                                       placeholder="勘察设计和前期工程费率" class="form-control x-percent input-full"
                                       required="required"
                                       name="reconnaissanceDesign" onblur="checkParams(this);"
                                       data-value="${mdDevelopment.reconnaissanceDesign}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.reconnaissanceDesignExplain}"
                                       placeholder="说明" class="form-control input-full"
                                       name="reconnaissanceDesignExplain">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                建筑安装工程费(元/㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" readonly="readonly" class="form-control input-full"
                                       onblur="checkParams(this);"
                                       name="constructionInstallationEngineeringFee" placeholder="建筑安装工程费 (元/㎡)"
                                       value="${mdDevelopment.constructionInstallationEngineeringFee}">
                            </div>
                            <div class="col-xs-8  col-sm-8  col-md-8  col-lg-8">
                                <table class="table table-striped"
                                       id="landConstructionInstallationEngineeringFeeInfoTarget"></table>
                            </div>
                        </div>
                    </div>
                </div>



                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                基础设施配套费(元/㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <div class="input-group">
                                    <input type="text" required="required"
                                           placeholder="基础设施配套费(元/㎡)" class="form-control"
                                           name="infrastructureCost" onblur="checkParams(this);"
                                           value="${mdDevelopment.infrastructureCost}">
                                    <div class="input-group-append">
                                        <select name="f22Value" class="form-control"
                                                onchange="landEngineering.calculationF22(this)">
                                            <option>请选择</option>
                                            <c:forEach items="${dataInfrastructureList}" var="item">
                                                <c:if test="${item.infrastructureSupportingFacilities != 0}">
                                                    <c:if test="${mdDevelopment.infrastructureCost != item.infrastructureSupportingFacilities}">
                                                        <option value="${item.infrastructureSupportingFacilities}"
                                                                data-key="${item.id}"
                                                                data-type="${item.type}">${item.timeSlot}
                                                            金额:${item.infrastructureSupportingFacilities}</option>
                                                    </c:if>
                                                    <c:if test="${mdDevelopment.infrastructureCost == item.infrastructureSupportingFacilities}">
                                                        <option value="${item.infrastructureSupportingFacilities}"
                                                                selected="selected" data-key="${item.id}"
                                                                data-type="${item.type}">${item.timeSlot}
                                                            金额:${item.infrastructureSupportingFacilities}</option>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.infrastructureCostExplain}"
                                       placeholder="说明" class="form-control input-full"
                                       name="infrastructureCostExplain">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div id="toolbarMdDevelopmentInfrastructureChildrenTable" style="display: none">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="landEngineering.deleteMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable')">删除</button>
                                        <button type="button" class="btn btn-primary btn-sm" onclick="landEngineering.editMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',true)">编辑</button>
                                        <button type="button" class="btn btn-primary btn-sm" onclick="landEngineering.editMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',false)">添加</button>
                                    </div>
                                </div>
                                <table class="table table-bordered" id="landMdDevelopmentInfrastructureChildrenTable">

                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                公共配套设施建设费(元/㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="公共配套设施建设费 (元/㎡)" class="form-control input-full"
                                       name="infrastructureMatchingCost" onblur="checkParams(this);"
                                       value="${mdDevelopment.infrastructureMatchingCost}">

                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <c:if test="${empty mdDevelopment.infrastructureMatchingCostExplain}">
                                    <input type="text"
                                           placeholder="说明" class="form-control input-full"
                                           name="infrastructureMatchingCostExplain" value="医疗卫生、文化体育、教育、社区服务">
                                </c:if>
                                <c:if test="${!empty mdDevelopment.infrastructureMatchingCostExplain}">
                                    <input type="text"
                                           placeholder="说明" class="form-control input-full"
                                           name="infrastructureMatchingCostExplain"
                                           value="${mdDevelopment.infrastructureMatchingCostExplain}">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                开发期间税费(元/㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="开发期间税费 (元/㎡)" class="form-control input-full"
                                       name="devDuring" onblur="checkParams(this);" value="${mdDevelopment.devDuring}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.devDuringExplain}"
                                       placeholder="说明" class="form-control input-full" name="devDuringExplain">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                其它工程费率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.otherEngineeringCost}"
                                       placeholder="其它工程费率" class="form-control x-percent input-full"
                                       name="otherEngineeringCost" onblur="checkParams(this);"
                                       data-value="${mdDevelopment.otherEngineeringCost}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.otherEngineeringCostExplain}"
                                       placeholder="说明" class="form-control input-full"
                                       name="otherEngineeringCostExplain">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            参数比率
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                不可预见费率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="不可预见费率" class="form-control x-percent input-full"
                                       name="unforeseenExpenses" onblur="checkParams(this);"
                                       value="${mdDevelopment.unforeseenExpenses}"
                                       data-value="${mdDevelopment.unforeseenExpenses}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdDevelopment.unforeseenExpensesExplain}"
                                       placeholder="说明" class="form-control input-full"
                                       name="unforeseenExpensesExplain">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                契税率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="契税率" class="form-control x-percent input-full" required="required"
                                       name="deedTaxRate" onblur="checkParams(this);"
                                       value="${mdDevelopment.deedTaxRate}" data-value="${mdDevelopment.deedTaxRate}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="费率说明" class="form-control input-full"
                                       name="deedTaxRateExplain" value="${mdDevelopment.deedTaxRateExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                交易费率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="交易费率" class="form-control x-percent input-full" required="required"
                                       name="transactionTaxRate" onblur="checkParams(this);"
                                       value="${mdDevelopment.transactionTaxRate}"
                                       data-value="${mdDevelopment.transactionTaxRate}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="费率说明" class="form-control input-full"
                                       name="transactionTaxRateExplain"
                                       value="${mdDevelopment.transactionTaxRateExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地取得附加成本 (万元)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="土地取得附加成本" class="form-control input-full"
                                       data-rule-number='true'
                                       name="landGetRelevant" onblur="checkParams(this);"
                                       value="${mdDevelopment.landGetRelevant}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="说明" class="form-control input-full"
                                       name="landGetRelevantExplain" value="${mdDevelopment.landGetRelevantExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                管理费率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="管理费率" class="form-control x-percent input-full"
                                       name="managementExpense" onblur="checkParams(this);"
                                       value="${mdDevelopment.managementExpense}"
                                       data-value="${mdDevelopment.managementExpense}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="费率说明" class="form-control input-full"
                                       name="managementExpenseExplain"
                                       value="${mdDevelopment.managementExpenseExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                销售费用率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="销售费用率" class="form-control x-percent input-full" required="required"
                                       name="salesFee" onblur="checkParams(this);" value="${mdDevelopment.salesFee}"
                                       data-value="${mdDevelopment.salesFee}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="费率说明" class="form-control input-full"
                                       name="salesFeeExplain" value="${mdDevelopment.salesFeeExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                投资利息率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="投资利息率" class="form-control x-percent input-full"
                                       required="required"
                                       name="interestInvestmentTax" onblur="checkParams(this);"
                                       value="${mdDevelopment.interestInvestmentTax}"
                                       data-value="${mdDevelopment.interestInvestmentTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="费率说明" class="form-control input-full"
                                       name="interestInvestmentTaxExplain"
                                       value="${mdDevelopment.interestInvestmentTaxExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                投资利润率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="投资利润率" class="form-control x-percent input-full"
                                       required="required"
                                       name="investmentProfitTax" onblur="checkParams(this);"
                                       value="${mdDevelopment.investmentProfitTax}"
                                       data-value="${mdDevelopment.investmentProfitTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="费率说明" class="form-control input-full"
                                       name="investmentProfitTaxExplain"
                                       value="${mdDevelopment.investmentProfitTaxExplain}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            项目开发税及附加
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                销售环节增值税及附加
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="销售环节增值税及附加" class="form-control x-percent input-full"
                                       name="salesTaxAndAdditional" onblur="checkParams(this);"
                                       value="${mdDevelopment.salesTaxAndAdditional}"
                                       data-value="${mdDevelopment.salesTaxAndAdditional}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="费率说明" class="form-control input-full"
                                       name="salesTaxAndAdditionalExplain"
                                       value="${mdDevelopment.salesTaxAndAdditionalExplain}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地增值税
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="土地增值税" class="form-control x-percent input-full"
                                       name="landValueAddedTax" onblur="checkParams(this);"
                                       value="${mdDevelopment.landValueAddedTax}"
                                       data-value="${mdDevelopment.landValueAddedTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="费率说明" class="form-control input-full"
                                       name="landValueAddedTaxExplain"
                                       value="${mdDevelopment.landValueAddedTaxExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                项目开发所得税
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="项目开发所得税" class="form-control x-percent input-full"
                                       name="projectDevelopmentIncomeTax" onblur="checkParams(this);"
                                       value="${mdDevelopment.projectDevelopmentIncomeTax}"
                                       data-value="${mdDevelopment.projectDevelopmentIncomeTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                费率说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="费率说明" class="form-control input-full"
                                       name="projectDevelopmentIncomeTaxExplain"
                                       value="${mdDevelopment.projectDevelopmentIncomeTaxExplain}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            地价修正
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地还原率
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <div class="input-group">
                                    <input type="text" readonly="readonly" class="form-control x-percent"
                                           name="remunerationRate" value="${mdDevelopment.remunerationRate}"
                                           placeholder="报酬率" data-value="${mdDevelopment.remunerationRate}"
                                           onblur="loadParamsValue(this);">
                                    <span class="input-group-btn">
                                        <input type="hidden" name="rewardRateId" value="${mdDevelopment.rewardRateId}">
                                  <input type="button" class="btn btn-primary" value="土地还原率"
                                         onclick="landEngineering.getRewardRate(this);"/>
                                </span>
                                </div>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                法定年限
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="法定年限" class="form-control input-full"
                                       name="statutoryLife"
                                       onblur="loadParamsValue(this);" value="${mdDevelopment.statutoryLife}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                剩余年限
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="剩余年限" class="form-control input-full"
                                       name="remainingYears" onblur="loadParamsValue(this);"
                                       value="${mdDevelopment.remainingYears}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                权利状况修正
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="权利状况修正" class="form-control x-percent input-full"
                                       name="amendmentStatusRights" onblur="loadParamsValue(this);"
                                       value="${mdDevelopment.amendmentStatusRights}"
                                       data-value="${mdDevelopment.amendmentStatusRights}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="说明" class="form-control input-full"
                                       name="amendmentStatusRightsExplain"
                                       value="${mdDevelopment.amendmentStatusRightsExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                其他修正
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="其他修正" class="form-control x-percent input-full"
                                       name="otherAmendments" onblur="loadParamsValue(this);"
                                       value="${mdDevelopment.otherAmendments}"
                                       data-value="${mdDevelopment.otherAmendments}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="说明" class="form-control input-full"
                                       name="otherAmendmentsExplain"
                                       value="${mdDevelopment.otherAmendmentsExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                开发程度修正
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="开发程度修正" class="form-control input-full"
                                       name="developmentDegreeRevision" onblur="loadParamsValue(this);"
                                       value="${mdDevelopment.developmentDegreeRevision}">

                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="说明" class="form-control input-full"
                                       name="developmentDegreeRevisionExplain"
                                       value="${mdDevelopment.developmentDegreeRevisionExplain}">

                            </div>
                        </div>
                    </div>
                </div>


                <input type="hidden" placeholder="单元格d26" name="constructionCostSubtotal"
                       value="${mdDevelopment.constructionCostSubtotal}">
                <input type="hidden" placeholder="单元格f34" name="interestInvestment"
                       value="${mdDevelopment.interestInvestment}">
                <input type="hidden" placeholder="单元格f35" name="investmentProfit"
                       value="${mdDevelopment.investmentProfit}">
                <input type="hidden" placeholder="单元格d41" name="assessPrice" value="${mdDevelopment.assessPrice}">
                <input type="hidden" placeholder="单元格d47" name="price" value="${mdDevelopment.price}">

                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            测算结果
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
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
                                        <td> 测算单价</td>
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
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>

<%@include file="/views/method/module/developmentModule/landEngineeringJs.jsp" %>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRate.jsp" %>
<%@include file="/views/method/module/economicIndicators.jsp" %>

<div id="boxLandEngineering" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建筑安装工程费</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12 "
                                                 data-title="boxLandEngineering">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-success btn-sm"
                        onclick="landEngineering.constructionInstallationEngineeringFeeEvent.save()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>




<div id="boxMdCalculatingMethodEngineeringCost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建筑安装工程费</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="id">
                <input type="hidden" name="masterId">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <input type="text" class="form-control input-full" name="name"
                                                       placeholder="名称" required="required">
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                                建筑面积
                                            </label>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <input type="text" class="form-control input-full" name="area"
                                                       placeholder="面积" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-success btn-sm"
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
                                    <button type="button" class="btn btn-primary btn-sm"
                                            onclick="landEngineering.delMdCalculatingMethodEngineeringCost()">删除</button>
                                    <button type="button" class="btn btn-primary btn-sm"
                                            onclick="landEngineering.showMdCalculatingMethodEngineeringCost();">添加</button>
                                </span>
        <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-sm"
                                            onclick="landEngineering.setMdCalculatingMethodEngineeringCost(false)">同步成本法建筑安装工程费</button>
                                </span>
        <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-sm"
                                            onclick="landEngineering.setMdCalculatingMethodEngineeringCost(true)">同步查勘建筑安装工程费</button>
                                </span>
    </div>
</div>

<script>


    var development = {};

    development.config = {
        frm: "#mdDevelopmentLandFrm"
    };
    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    development.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    development.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    function loadParamsValue(that, t) {
        var value = $(that).val();
        var i = 0;
        if (!development.isNotBlank(value)) {
            if (t != 'not') {
                $(that).attr("data-value", '0');
                $(that).val(0);
            }
        }
        if (development.isNotBlank(value)) {
            if ($.isNumeric(value)) {
                i++;
            }
            var reg = new RegExp(/^[0-9]+\.?[0-9]*%$/);
            if (reg.test(value)) {
                i++;
            }
            if (i == 0) {
                alert("不符合，必须是数字!");
                $(that).attr("data-value", '');
                $(that).val('');
                return false;
            }
        }
        var target = $(development.config.frm);
        calculationNumeric(formSerializeArray(target), function (data) {
            target.find("input[name='projectConstructionPeriod']").val(data.projectConstructionPeriod);

            target.find("td[name='constructionCostSubtotal']").html(data.constructionCostSubtotal);
            target.find("input[name='constructionCostSubtotal']").val(data.constructionCostSubtotal);

            target.find("td[name='interestInvestment']").html(data.interestInvestment);
            target.find("input[name='interestInvestment']").val(data.interestInvestment);

            target.find("td[name='investmentProfit']").html(data.investmentProfit);
            target.find("input[name='investmentProfit']").val(data.investmentProfit);


            target.find("td[name='assessPrice']").html(data.assessPrice);
            target.find("input[name='assessPrice']").val(data.assessPrice);
            try {
                if (data.assessPrice) {
                    if (data.price == 0) {
                        data.price = data.assessPrice;
                    }
                }
            } catch (e) {
            }
            target.find("td[name='price']").html(data.price);
            target.find("input[name='price']").val(data.price);
            if ($("#md_development_form").size() != 0) {
                $("#md_development_form").find("input[name='price']").val(data.price);
            }
        });
    }

    function checkParams(that) {
        var value = $(that).val();
        if (!development.isNotBlank(that)) {
            return false;
        }
        if (!development.isNotBlank(value)) {
            $(that).attr("data-value", '');
            $(that).val('');
        }
        loadParamsValue(that, 'not');
    }

    function calculationNumeric(data, callback) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdDevelopment/calculationNumeric",
            data: {fomData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
//                   AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


    development.valid = function (callback) {
        var frm = $(development.config.frm);
        if (!frm.valid()) {
            return false;
        }
        if (callback) {
            callback();
        }
    };

    development.getFomData = function () {
        var frm = $(development.config.frm);
        var data = formSerializeArray(frm);
        return data;
    };

    development.initData = function () {
        var frm = $(development.config.frm);
        if (!development.isNotBlank('${mdDevelopment.id}')) {
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: {
                    province: '${schemeAreaGroup.province}',
                    city: '${schemeAreaGroup.city}',
                    district: '${schemeAreaGroup.district}',
                    bisNationalUnity: true
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            $.each(result.data, function (i, item) {
                                var taxRate = item.taxRate;
                                if (item.taxRate) {
                                    taxRate = Number(item.taxRate) * 100;
                                    taxRate = taxRate + "%";
                                }
                                if (item.typeName == '增值税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='landValueAddedTax']").val(taxRate);
                                        frm.find("input[name='landValueAddedTax']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '契税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='deedTaxRate']").val(taxRate);
                                        frm.find("input[name='deedTaxRate']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '所得税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='projectDevelopmentIncomeTax']").val(taxRate);
                                        frm.find("input[name='projectDevelopmentIncomeTax']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '其它税费') {
                                    if (item.taxRate) {
                                        frm.find("input[name='otherEngineeringCost']").val(taxRate);
                                        frm.find("input[name='otherEngineeringCost']").attr("data-value", item.taxRate);
                                    }
                                }
                            });
                        }
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);

                    }
                },
                error: function (e) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + e);
                }
            });
        }
        landEngineering.loadMdDevelopmentInfrastructureChildrenTable();
        landEngineering.loadMdCalculatingMethodEngineeringCostTable();
    };

    development.writeValueEvent = function (value, callback) {
        var frm = $(development.config.frm);
        frm.find("input[name='type']").val(value);
        if (value == 1) {
            var managementExpense = frm.find("input[placeholder='续建管理费率']");
            managementExpense.attr({placeholder: "管理费率"});
            managementExpense.closest(".form-group").find("label").first().text("管理费率");

            var landGetRelevant = frm.find("input[placeholder='在建工程修复费用']");
            landGetRelevant.attr({placeholder: "土地取得附加成本"});
            landGetRelevant.closest(".form-group").find("label").first().text("土地取得附加成本(万元)");

            var interestInvestmentTax = frm.find("input[placeholder='续建投资利息率']");
            interestInvestmentTax.attr({placeholder: "投资利息率"});
            interestInvestmentTax.closest(".form-group").find("label").first().text("投资利息率");

            var investmentProfitTax = frm.find("input[placeholder='续建投资利润率']");
            investmentProfitTax.attr({placeholder: "投资利润率"});
            investmentProfitTax.closest(".form-group").find("label").first().text("投资利润率");
        }
        if (value == 2) {
            var managementExpense = frm.find("input[placeholder='管理费率']");
            managementExpense.attr({placeholder: "续建管理费率"});
            managementExpense.closest(".form-group").find("label").first().text("续建管理费率");

            var landGetRelevant = frm.find("input[placeholder='土地取得附加成本']");
            landGetRelevant.attr({placeholder: "在建工程修复费用"});
            landGetRelevant.closest(".form-group").find("label").first().text("在建工程修复费用(万元)");

            var interestInvestmentTax = frm.find("input[placeholder='投资利息率']");
            interestInvestmentTax.attr({placeholder: "续建投资利息率"});
            interestInvestmentTax.closest(".form-group").find("label").first().text("续建投资利息率");

            var investmentProfitTax = frm.find("input[placeholder='投资利润率']");
            investmentProfitTax.attr({placeholder: "续建投资利润率"});
            investmentProfitTax.closest(".form-group").find("label").first().text("续建投资利润率");
        }
        if (callback) {
            callback(value);
        }
    };

    development.checkedFun = function (that, name, flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='" + name + "']:checkbox");
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };

    development.initParcelSettingData = function (data) {
        var industrySupplyInfoContainer = $("#industrySupplyInfoContainer_BBBBB");
        var developmentDegreeContentContainer = $("#developmentDegreeContentContainer_BBBBB");
        industrySupplyInfoContainer.empty();
        developmentDegreeContentContainer.empty();
        //宗地外设定
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingOuter) {
                    array = data.parcelSettingOuter.split(',');
                }
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingOuter' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingOuter',false)\">";
            industrySupplyInfoContainer.append(resultHtml);
        }, true);
        //宗地内设定
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingInner) {
                    array = data.parcelSettingInner.split(',');
                }
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingInner' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingInner',false)\">";
            developmentDegreeContentContainer.append(resultHtml);
        });
    };

    $(document).ready(function () {

        development.initData();

        var frm = $(development.config.frm);
        var type = '${mdDevelopment.type}';
        if (development.isNotBlank(type)) {
            if (type == '1') {
                $("#developmentLand").attr('checked', 'true');
                $("#developmentEngineering").removeAttr("checked");
            }
            if (type == '2') {
                $("#developmentEngineering").attr('checked', 'true');
                $("#developmentLand").removeAttr("checked");
            }
        } else {
            type = $("#developmentCheckboxTool").find("input[type='radio'][name='type']:checked").val();
        }
        development.writeValueEvent(type, function () {
            landEngineering.loadMdCalculatingMethodEngineeringCostTable();
            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
        });

        $("#developmentCheckboxTool").find("input[type='radio'][name='type']").change(function () {
            var value = $(this).attr('data-value');
            development.writeValueEvent(value, function () {
                landEngineering.loadMdCalculatingMethodEngineeringCostTable();

            });
        });


        var data = {
            parcelSettingInner: '${mdDevelopment.parcelSettingInner}',
            parcelSettingOuter: '${mdDevelopment.parcelSettingOuter}'
        };
        development.initParcelSettingData(data);
    });
</script>