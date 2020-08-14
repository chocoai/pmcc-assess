<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-2-27
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-body">

            <form class="form-horizontal" id="constructionFrm">

                <input type="hidden" name="id" value="${mdCostVo.mdCostConstruction.id}">
                <input type="hidden" name="pid" value="${mdCostVo.mdCostConstruction.pid}">

                <input type="hidden" name="type" value="${mdCostVo.type==null?1:mdCostVo.type}">

                <input type="hidden" name="mcId" value="${mdCostVo.mdCostConstruction.mcId}">
                <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                <input type="hidden" name="economicId" value="${mdCostVo.mdCostConstruction.economicId}"
                       onblur="cost.checkParams(this);">


                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                建筑形态 <span class="symbol required"></span>
                            </label>
                            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                <div class="form-check" id="costCheckboxTool">
                                    <label class="form-check-label">
                                        <c:choose>
                                            <c:when test="${empty mdCostVo.type}">
                                                <input class="form-check-input" type="radio" name="typeShow" value="1"
                                                       checked="checked"
                                                       data-value="1">
                                                <script>
                                                    $(document).ready(function () {
                                                        cost.writeValueEvent(1, function () {
                                                        });
                                                    }) ;
                                                </script>
                                            </c:when>
                                            <c:otherwise>
                                                <input class="form-check-input" type="radio" name="typeShow" value="1"
                                                       data-value="1">
                                            </c:otherwise>
                                        </c:choose>
                                        <span class="form-check-sign">建筑物</span>
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
                                            onclick="developmentCommon.loadSchemeInfoTableList({projectId:'${projectPlanDetails.projectId}',methodDataId:'${mdCostVo.id}',methodType:'${methodTypeObj.id}'},'construction.selectFun');">
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
                            开发信息
                            <button type="button" class="btn btn-info btn-sm"
                                    onclick="construction.showMdDevelopmentIncomeCategory('${mdCostVo.mdCostConstruction.economicId}');">
                                经济规划指标
                            </button>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                开发土地面积(㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" name="developLandAreaTax" onblur="cost.checkParams(this);"
                                       placeholder=" 开发土地面积(㎡)" class="form-control input-full" data-rule-number='true'
                                       required="required"
                                       value="${mdCostVo.mdCostConstruction.developLandAreaTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                开发建筑面积(㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" name="developBuildAreaTax" onblur="cost.checkParams(this);"
                                       placeholder="开发建筑面积(㎡)" class="form-control input-full" data-rule-number='true'
                                       required="required"
                                       value="${mdCostVo.mdCostConstruction.developBuildAreaTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                开发期（年）<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" name="developYearNumberTax" onblur="cost.checkParams(this);"
                                       placeholder="开发期（年）" class="form-control input-full" data-rule-number='true'
                                       required="required"
                                       value="${mdCostVo.mdCostConstruction.developYearNumberTax}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">

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

                <div id="LAND_ACQUISITION_COST">
                    <div class="card-header">
                        <div class="card-head-row">
                            <div class="card-title">
                                土地取得成本
                            </div>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                    土地购买价格(元/㎡)<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" name="landPurchasePrice" placeholder="土地购买价格"
                                           data-rule-number="true"
                                           onblur="cost.checkParams(this);"
                                           class="form-control input-full" required="required"
                                           value="${mdCostVo.mdCostConstruction.landPurchasePrice}">

                                    <%--<div class="input-group">--%>
                                    <%--<span class="input-group-btn">--%>
                                    <%--<input type="button" class="btn btn-primary" value="市场比较法"--%>
                                    <%--onclick="construction.callCompareMethod(this);">--%>
                                    <%--</span>--%>
                                    <%--</div>--%>
                                </div>
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                    价格说明
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text"
                                           placeholder="价格说明" class="form-control input-full"
                                           name="landPurchasePriceExplain" required="required"
                                           value="${mdCostVo.mdCostConstruction.landPurchasePriceExplain}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                    土地取得相关税费率<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" name="landGetRelevant"
                                           placeholder="土地取得相关税费率" class="form-control x-percent input-full"
                                           onblur="cost.checkParams(this);"
                                           required="required"
                                           data-value="${mdCostVo.mdCostConstruction.landGetRelevant}">
                                </div>
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                    价格说明
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text"
                                           placeholder="说明" class="form-control input-full"
                                           name="landGetRelevantExplain" required="required"
                                           value="${mdCostVo.mdCostConstruction.landGetRelevantExplain}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                    土地取得附加成本(元/㎡)<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" name="additionalCostLandAcquisition" required
                                           placeholder="土地取得附加成本(元/㎡)" class="form-control input-full"
                                           data-rule-number='true'
                                           onblur="cost.checkParams(this);"
                                           value="${mdCostVo.mdCostConstruction.additionalCostLandAcquisition}">
                                </div>
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                    说明
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" required
                                           placeholder="说明" class="form-control input-full"
                                           name="additionalCostLandAcquisitionExplain"
                                           value="${mdCostVo.mdCostConstruction.additionalCostLandAcquisitionExplain}">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            建设成本
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
                                <input type="text" name="reconnaissanceDesign" onblur="cost.checkParams(this);"
                                       placeholder="勘察设计和前期工程费率" class="form-control x-percent input-full"
                                       required="required" value="${mdCostVo.mdCostConstruction.reconnaissanceDesign}"
                                       data-value="${mdCostVo.mdCostConstruction.reconnaissanceDesign}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="说明" class="form-control input-full"
                                       name="reconnaissanceDesignExplain" required="required"
                                       value="${mdCostVo.mdCostConstruction.reconnaissanceDesignExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                建筑安装工程费 (元/㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" readonly="readonly" class="form-control input-full"
                                       onblur="cost.checkParams(this);"
                                       name="constructionInstallationEngineeringFee"
                                       value="${mdCostVo.mdCostConstruction.constructionInstallationEngineeringFee}">
                            </div>
                            <div class="col-xs-8  col-sm-8  col-md-8  col-lg-8">
                                <div id="toolbarMdCalculatingMethodEngineeringCost" style="display: none">
                                    <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px;"
                                            onclick="construction.showMdCalculatingMethodEngineeringCost();"><span
                                            class="btn-label"><i class="fa fa-plus"></i></span>添加
                                    </button>
                                    <button type="button" class="btn btn-primary btn-sm "
                                            onclick="construction.editMdCalculatingMethodEngineeringCost() ;">
                                        <i class="fa fa-pen"></i>
                                        编辑
                                    </button>
                                    <button type="button" class="btn btn-warning btn-sm"
                                            onclick="construction.delMdCalculatingMethodEngineeringCost()"><span
                                            class="btn-label"><i class="fa fa-minus"></i></span>删除
                                    </button>
                                    <button type="button" class="btn btn-info btn-sm" style="margin-left: 5px;"
                                            onclick="construction.setMdCalculatingMethodEngineeringCost(false)">
                                        同步假设开发法建筑安装工程费
                                    </button>
                                    <button type="button" class="btn btn-info btn-sm" style="margin-left: 5px;"
                                            onclick="construction.setMdCalculatingMethodEngineeringCost(true)">
                                        同步查勘建筑安装工程费
                                    </button>
                                </div>
                                <table class="table table-striped"
                                       id="engineeringConstructionInstallationEngineeringFeeInfoTarget">
                                </table>
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
                                <div class="input-group ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-addon">
                                            <input type="text" placeholder="基础设施配套费" class="form-control"
                                                   required="required"
                                                   name="infrastructureCost" onblur="cost.checkParams(this);"
                                                   value="${mdCostVo.mdCostConstruction.infrastructureCost}">
                                        </span>
                                    </div>
                                    <select name="infrastructureCostValue"
                                            class="form-control"
                                            onchange="construction.calculationE13Select(this)">
                                        <option value="0">请选择</option>
                                        <c:forEach items="${dataInfrastructureList}" var="item">
                                            <c:if test="${item.infrastructureSupportingFacilities != 0}">
                                                <c:if test="${mdCostVo.mdCostConstruction.infrastructureCost != item.infrastructureSupportingFacilities}">
                                                    <option value="${item.infrastructureSupportingFacilities}"
                                                            data-key="${item.id}"
                                                            data-type="${item.type}">${item.timeSlot}
                                                        金额:${item.infrastructureSupportingFacilities}</option>
                                                </c:if>
                                                <c:if test="${mdCostVo.mdCostConstruction.infrastructureCost == item.infrastructureSupportingFacilities}">
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
                                <input type="text"
                                       placeholder="说明" class="form-control input-full"
                                       name="infrastructureCostExplain"
                                       value="${mdCostVo.mdCostConstruction.infrastructureCostExplain}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div id="toolbarMdCostConstructionChildrenTable" style="display: none">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-success btn-sm"
                                                onclick="construction.editMdDevelopmentInfrastructureChildrenTable('#landMdCostConstructionChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',false)">
                                            <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            添加
                                        </button>
                                        <button type="button" class="btn btn-primary btn-sm "
                                                onclick="construction.editMdDevelopmentInfrastructureChildrenTable('#landMdCostConstructionChildrenTable','#basicMdDevelopmentInfrastructureChildrenModalTool',true)">
                                            <i class="fa fa-pen"></i>
                                            编辑
                                        </button>
                                        <button type="button" class="btn btn-warning btn-sm"
                                                onclick="construction.deleteMdDevelopmentInfrastructureChildrenTable('#landMdCostConstructionChildrenTable')">
                                            <i class="fa fa-minus"></i>
                                            删除
                                        </button>
                                    </div>
                                </div>
                                <table class="table table-bordered" id="landMdCostConstructionChildrenTable">

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
                                <input type="text" placeholder="公共配套设施建设费" class="form-control input-full"
                                       onblur="cost.checkParams(this);"
                                       name="infrastructureMatchingCost" required
                                       value="${mdCostVo.mdCostConstruction.infrastructureMatchingCost}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <c:if test="${empty mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}">
                                    <input type="text"
                                           placeholder="说明" class="form-control input-full" required
                                           name="infrastructureMatchingCostExplain" value="医疗卫生、文化体育、教育、社区服务">
                                </c:if>
                                <c:if test="${!empty mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}">
                                    <input type="text"
                                           placeholder="说明" class="form-control input-full" required
                                           name="infrastructureMatchingCostExplain"
                                           value="${mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}">
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
                                <input type="text" required="required"
                                       placeholder="公共配套设施建设费" class="form-control input-full"
                                       onblur="cost.checkParams(this);"
                                       name="devDuring" value="${mdCostVo.mdCostConstruction.devDuring}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="说明" class="form-control input-full" required
                                       name="devDuringExplain" value="${mdCostVo.mdCostConstruction.devDuringExplain}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                其它工程费(元/㎡)<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" value="${mdCostVo.mdCostConstruction.otherEngineeringCost}"
                                       onblur="cost.checkParams(this);"
                                       placeholder="其它工程费" required class="form-control input-full"
                                       name="otherEngineeringCost">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="说明" class="form-control input-full"
                                       name="otherEngineeringCostExplain" required
                                       value="${mdCostVo.mdCostConstruction.otherEngineeringCostExplain}">
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
                                <input type="text" required
                                       placeholder="不可预见费率" class="form-control x-percent input-full"
                                       name="unforeseenExpenses"
                                       onblur="cost.checkParams(this);"
                                       value="${mdCostVo.mdCostConstruction.unforeseenExpenses}"
                                       data-value="${mdCostVo.mdCostConstruction.unforeseenExpenses}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="说明" class="form-control input-full" name="unforeseenExpensesExplain"
                                       onblur=""
                                       value="${mdCostVo.mdCostConstruction.unforeseenExpensesExplain}" data-value="">
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
                                <input type="text" required
                                       placeholder="管理费率" class="form-control x-percent input-full"
                                       name="managementExpense"
                                       onblur="cost.checkParams(this);"
                                       value="${mdCostVo.mdCostConstruction.managementExpense}"
                                       data-value="${mdCostVo.mdCostConstruction.managementExpense}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="说明" class="form-control input-full" name="managementExpenseExplain"
                                       value="${mdCostVo.mdCostConstruction.managementExpenseExplain}" data-value="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                销售费率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="销售费率" class="form-control x-percent input-full" name="salesFee"
                                       onblur="cost.checkParams(this);"
                                       value="${mdCostVo.mdCostConstruction.salesFee}"
                                       data-value="${mdCostVo.mdCostConstruction.salesFee}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="说明" class="form-control input-full" name="salesFeeExplain" onblur=""
                                       value="${mdCostVo.mdCostConstruction.salesFeeExplain}" data-value="">
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
                                <input type="text" required
                                       placeholder="投资利息率" class="form-control x-percent input-full"
                                       name="interestInvestmentTax"
                                       onblur="cost.checkParams(this);"
                                       value="${mdCostVo.mdCostConstruction.interestInvestmentTax}"
                                       data-value="${mdCostVo.mdCostConstruction.interestInvestmentTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="说明" class="form-control input-full"
                                       name="interestInvestmentTaxExplain"
                                       value="${mdCostVo.mdCostConstruction.interestInvestmentTaxExplain}"
                                       data-value="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                销售税金及附加率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="销售税金及附加率" class="form-control x-percent input-full"
                                       name="salesTaxAndAdditional"
                                       onblur="cost.checkParams(this);"
                                       value="${mdCostVo.mdCostConstruction.salesTaxAndAdditional}"
                                       data-value="${mdCostVo.mdCostConstruction.salesTaxAndAdditional}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="说明" class="form-control input-full"
                                       name="salesTaxAndAdditionalExplain"
                                       value="${mdCostVo.mdCostConstruction.salesTaxAndAdditionalExplain}"
                                       data-value="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                开发利润率<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="开发利润率" class="form-control x-percent input-full"
                                       name="investmentProfitTax"
                                       onblur="cost.checkParams(this);"
                                       data-value="${mdCostVo.mdCostConstruction.investmentProfitTax}"
                                       value="${mdCostVo.mdCostConstruction.investmentProfitTax}">
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" required
                                       placeholder="说明" class="form-control input-full"
                                       name="investmentProfitTaxExplain" onblur=""
                                       data-value="" value="${mdCostVo.mdCostConstruction.investmentProfitTaxExplain}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                成新率
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <div class="input-group">
                                    <input type="hidden" placeholder="成新率" name="residueRatioId"
                                           value="${mdCostVo.mdCostConstruction.residueRatioId}">
                                    <%--<input type="hidden" name="residueRatio"--%>
                                           <%--value="${mdCostVo.mdCostConstruction.residueRatio}">--%>

                                    <input type="text" aria-label="" aria-describedby="basic-addon1"
                                           placeholder="成新率(计算值)" class="form-control x-percent"
                                           name="residueRatio" onblur="cost.checkParams(this);"
                                           data-value="${mdCostVo.mdCostConstruction.residueRatio}">
                                    <div class="input-group-prepend">
                                        <button class="btn btn-info btn-sm" type="button"
                                                onclick="construction.callResidueRatio(this,false)">
                                            成新率
                                        </button>
                                    </div>
                                </div>

                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text"
                                       placeholder="说明" class="form-control input-full" name="residueRatioRemark"
                                       value="${mdCostVo.mdCostConstruction.residueRatioRemark}">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <input type="hidden" readonly="readonly" name="constructionAssessmentValue2"
                                       value="${mdCostVo.mdCostConstruction.constructionAssessmentValue2}"
                                       class="form-control"
                                       placeholder="在建工程评估价值2">
                                <input type="hidden" value="${mdCostVo.mdCostConstruction.landGetCostTotal+0}"
                                       name="landGetCostTotal"
                                       placeholder="土地取得成本小计">
                                <input type="hidden" value="${mdCostVo.mdCostConstruction.constructionSubtotal}"
                                       name="constructionSubtotal"
                                       placeholder="建设成本小计">
                                <input type="hidden" value="${mdCostVo.mdCostConstruction.interestInvestment}"
                                       name="interestInvestment"
                                       placeholder="投资利息">
                                <input type="hidden" value="${mdCostVo.mdCostConstruction.investmentProfit}"
                                       name="investmentProfit"
                                       placeholder="开发利润">
                                <input type="hidden" value="${mdCostVo.mdCostConstruction.constructionAssessmentValue}"
                                       name="constructionAssessmentValue" placeholder="在建工程评估价值">
                                <input type="hidden"
                                       value="${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}"
                                       name="constructionAssessmentPriceCorrecting" placeholder="在建工程单位价">
                            </div>
                        </div>
                    </div>
                </div>

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
                                    <tfoot>
                                    <tr>
                                        <td> 土地取得成本小计 (万元)</td>
                                        <td><label
                                                class="landGetCostTotal">${mdCostVo.mdCostConstruction.landGetCostTotal}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 建设成本小计 (万元)</td>
                                        <td><label
                                                class="constructionSubtotal">${mdCostVo.mdCostConstruction.constructionSubtotal}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 投资利息 (万元)</td>
                                        <td><label
                                                class="interestInvestment">${mdCostVo.mdCostConstruction.interestInvestment}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 开发利润 (万元)</td>
                                        <td><label
                                                class="investmentProfit">${mdCostVo.mdCostConstruction.investmentProfit}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 在建工程评估价值 (万元)</td>
                                        <td><label
                                                class="constructionAssessmentValue">${mdCostVo.mdCostConstruction.constructionAssessmentValue}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 在建工程单位价(元)</td>
                                        <td><label
                                                class="constructionAssessmentPriceCorrecting">${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}</label>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


<div id="boxMdCostConstruction" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12 "
                                     data-title="boxMdCostConstruction">
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
                        onclick="construction.constructionInstallationEngineeringFeeEvent.save();">
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
    <div class="modal-dialog modal-lg" >
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建筑安装工程费</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="architecturalObjId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="construction.saveMdCalculatingMethodEngineeringCost();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


