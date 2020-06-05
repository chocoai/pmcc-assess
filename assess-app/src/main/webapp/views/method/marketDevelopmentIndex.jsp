<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">

        <div class="card-body ">
            <form class="form-horizontal" id="mdDevelopmentLandFrm">

                <input type="hidden" name="type" value="${mdDevelopment.type==null?1:mdDevelopment.type}">

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
                                        <c:choose>
                                            <c:when test="${empty mdDevelopment.type}">
                                                <input class="form-check-input" type="radio" name="typeShow" value="1"
                                                       checked="checked"
                                                       data-value="1">
                                            </c:when>
                                            <c:otherwise>
                                                <input class="form-check-input" type="radio" name="typeShow" value="1"
                                                       data-value="1">
                                            </c:otherwise>
                                        </c:choose>
                                        <span class="form-check-sign">土地</span>
                                    </label>
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="typeShow" value="2"
                                               data-value="2">
                                        <span class="form-check-sign">在建工程</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                ${judgeObject.name}
                                <small>(${judgeObject.evaluationArea}㎡)</small>
                                <small>
                                    <button class="btn btn-sm btn-primary" data-toggle="modal" type="button"
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
                            基本参数 <button type="button" class="btn btn-info btn-sm" onclick="landEngineering.showMdDevelopmentIncomeCategory('${mdDevelopment.economicId}');">经济规划指标</button>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                项目建设期年<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" disabled="disabled"
                                       value="${mdDevelopment.projectConstructionPeriod}" required="required"
                                       placeholder="项目建设期(年) = 已开发时间+剩余开发时间"
                                       class="form-control input-full" name="projectConstructionPeriod"
                                       onblur="checkParams(this);">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                已开发时间(年)
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" value="${mdDevelopment.developedYear}"
                                       placeholder="已开发时间(年)"
                                       class="form-control input-full" name="developedYear" onblur="checkParams(this);">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                剩余开发时间(年)
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <input type="text" value="${mdDevelopment.remainingDevelopmentYear}"
                                       placeholder="剩余开发时间(年)"
                                       class="form-control input-full" name="remainingDevelopmentYear"
                                       onblur="checkParams(this);">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label showLandArea">
                                土地面积
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2 showLandArea">
                                <input type="text" value="${mdDevelopment.landArea}" class="form-control input-full"
                                       name="landArea" onblur="checkParams(this);" placeholder="土地面积">
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

                                    <div class="input-group-append">

                                    </div>
                                </div>

                                <div class="input-group ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-addon">
                                            <input type="text" required="required"
                                                   placeholder="基础设施配套费(元/㎡)" class="form-control"
                                                   name="infrastructureCost" onblur="checkParams(this);"
                                                   value="${mdDevelopment.infrastructureCost}">
                                        </span>
                                    </div>
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
                                    <button type="button" class="btn btn-warning btn-sm"
                                            onclick="landEngineering.deleteMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable')">
                                        <i class="fa fa-minus"></i>
                                        删除
                                    </button>
                                    <button type="button" class="btn btn-primary btn-sm "
                                            onclick="landEngineering.editMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',true)">
                                        <i class="fa fa-pen"></i>
                                        编辑
                                    </button>
                                    <button type="button" class="btn btn-success btn-sm"
                                            onclick="landEngineering.editMdDevelopmentInfrastructureChildrenTable('#landMdDevelopmentInfrastructureChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',false)">
                                            <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                        添加
                                    </button>
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
                                           placeholder="土地还原率" data-value="${mdDevelopment.remunerationRate}"
                                           onblur="loadParamsValue(this);">

                                    <div class="input-group-append">
                                        <input type="hidden" name="rewardRateId" value="${mdDevelopment.rewardRateId}">
                                        <input type="button" class="btn btn-info btn-sm" value="土地还原率"
                                               onclick="landEngineering.getRewardRate(this);"/>
                                    </div>
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

<div id="boxLandEngineering" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%;">
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
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="landEngineering.constructionInstallationEngineeringFeeEvent.save()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="boxMdCalculatingMethodEngineeringCost" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%;">
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="landEngineering.saveMdCalculatingMethodEngineeringCost();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="toolbarMdCalculatingMethodEngineeringCostLand" style="display: none">
    <button type="button" class="btn btn-warning btn-sm" onclick="landEngineering.delMdCalculatingMethodEngineeringCost()"><i class="fa fa-minus"></i>删除</button>
    <button type="button" class="btn btn-success btn-sm" onclick="landEngineering.showMdCalculatingMethodEngineeringCost();"><span class="btn-label"><i class="fa fa-plus"></i></span>添加</button>
    <button type="button" class="btn btn-info btn-sm" onclick="landEngineering.setMdCalculatingMethodEngineeringCost(false)">同步成本法建筑安装工程费</button>
    <button type="button" class="btn btn-info btn-sm" onclick="landEngineering.setMdCalculatingMethodEngineeringCost(true)">同步查勘建筑安装工程费</button>
</div>

