<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-2-27
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                ${judgeObject.name}
                <small>(${judgeObject.evaluationArea}㎡)</small>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                建筑形态
                            </label>
                            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                <c:if test="${mdCostVo.type == 1}">
                                    <div class="form-check" style="justify-content:left">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" name="type"
                                                   checked="checked" disabled="disabled">
                                            <span class="form-check-sign">建筑物</span>
                                        </label>
                                    </div>
                                </c:if>
                                <c:if test="${mdCostVo.type == 2}">
                                    <div class="form-check" style="justify-content:left">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox"
                                                   name="type" checked="checked" disabled="disabled">
                                            <span class="form-check-sign">在建工程</span>
                                        </label>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    开发信息<button type="button" class="btn btn-info btn-sm"
                                onclick="economicIndicators.init({economicId:'${mdCostVo.mdCostConstruction.economicId}',attribute:{readonly:'readonly','class':'form-control'} });">
                    经济规划指标</button>
                </div>
            </div>
        </div>
        <div class="card-body ">
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            开发土地面积(㎡)
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.developLandAreaTax}</label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            开发建筑面积(㎡)
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.developBuildAreaTax}</label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            开发期（年）
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.developYearNumberTax}</label>
                        </div>
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
                        ${mdCostVo.mdCostConstruction.parcelSettingOuterName}
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        宗地内设定
                    </label>
                    <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                        ${mdCostVo.mdCostConstruction.parcelSettingInnerName}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${mdCostVo.type == 2}">
    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                        土地取得成本
                    </div>
                </div>
            </div>
            <div class="card-body ">

                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地购买价格 (元/㎡)
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <label class="form-control input-full">${mdCostVo.mdCostConstruction.landPurchasePrice}</label>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                价格说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <label class="form-control input-full">${mdCostVo.mdCostConstruction.landPurchasePriceExplain}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地取得相关税费率
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <label class="form-control input-full"><fmt:formatNumber
                                        value="${mdCostVo.mdCostConstruction.landGetRelevant}" type="percent"
                                        maxFractionDigits="4"/></label>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                价格说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <label class="form-control input-full">${mdCostVo.mdCostConstruction.landGetRelevantExplain}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地取得单价(元/㎡)
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <label class="form-control input-full">${mdCostVo.mdCostConstruction.additionalCostLandAcquisition}</label>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                价格说明
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <label class="form-control input-full">${mdCostVo.mdCostConstruction.additionalCostLandAcquisitionExplain}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    建设成本
                </div>
            </div>
        </div>
        <div class="card-body ">
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            勘察设计和前期工程费率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.reconnaissanceDesign}"
                                                  type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.reconnaissanceDesignExplain}</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div id="toolbarMdCalculatingMethodEngineeringCost" style="display: none">
                                <button type="button" class="btn btn-info btn-sm disabled">
                                    建筑安装工程费 ${mdCostVo.mdCostConstruction.constructionInstallationEngineeringFee}(元/㎡)
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
                            基础设施建设费 (元/㎡)
                            
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.infrastructureCost}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.infrastructureCostExplain}</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
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
                            公共配套设施建设费 (元/㎡)
                            
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.infrastructureMatchingCost}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            开发期间税费 (元/㎡)
                            
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.devDuring}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.devDuringExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            其它工程费 (元/㎡)
                            
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.otherEngineeringCost}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.otherEngineeringCostExplain}</label>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    参数比率
                </div>
            </div>
        </div>
        <div class="card-body ">

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            不可预见费率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.unforeseenExpenses}"
                                                  type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdCostVo.mdCostConstruction.unforeseenExpensesExplain}</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            管理费率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.managementExpense}"
                                                  type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.managementExpenseExplain}
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            销售费率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.salesFee}" type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.salesFeeExplain}
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            投资利息率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.interestInvestmentTax}"
                                                  type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.interestInvestmentTaxExplain}
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            销售税金及附加率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.salesTaxAndAdditional}"
                                                  type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.salesTaxAndAdditionalExplain}
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            开发利润率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdCostVo.mdCostConstruction.investmentProfitTax}" type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.investmentProfitTaxExplain}
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <c:if test="${mdCostVo.type eq 1}">

            </c:if>
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

                                <input type="hidden" name="residueRatio"
                                       value="${mdCostVo.mdCostConstruction.residueRatio}">


                                <label class="form-control" aria-label="" aria-describedby="basic-addon1">
                                    <fmt:formatNumber value="${mdCostVo.mdCostConstruction.residueRatio}" type="percent"
                                                      maxFractionDigits="4"/>
                                </label>
                            </div>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdCostVo.mdCostConstruction.investmentProfitTaxExplain}
                            </label>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    测算结果
                </div>
            </div>
        </div>
        <div class="card-body ">
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <table class="table table-bordered">
                                <tfoot>
                                <c:if test="${mdCostVo.type == 2}">
                                    <tr>
                                        <td> 土地取得成本小计 (万元)</td>
                                        <td><label class="landGetCostTotal">${mdCostVo.mdCostConstruction.landGetCostTotal}</label>
                                            <input type="hidden" value="${mdCostVo.mdCostConstruction.landGetCostTotal}"
                                                   name="landGetCostTotal" placeholder="土地取得成本小计"></td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td> 建设成本小计 (万元)</td>
                                    <td><label
                                            class="constructionSubtotal">${mdCostVo.mdCostConstruction.constructionSubtotal}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td> 投资利息 (万元)</td>
                                    <td><label
                                            class="interestInvestment">${mdCostVo.mdCostConstruction.interestInvestment}</label><input
                                            type="hidden" value="${mdCostVo.mdCostConstruction.interestInvestment}"
                                            name="interestInvestment" placeholder="投资利息"></td>
                                </tr>
                                <tr>
                                    <td> 开发利润 (万元)</td>
                                    <td><label
                                            class="investmentProfit">${mdCostVo.mdCostConstruction.investmentProfit}</label><input
                                            type="hidden" value="${mdCostVo.mdCostConstruction.investmentProfit}"
                                            name="investmentProfit" placeholder="开发利润"></td>
                                </tr>
                                <tr>
                                    <td> 在建工程评估价值 (万元)</td>
                                    <td><label
                                            class="constructionAssessmentValue">${mdCostVo.mdCostConstruction.constructionAssessmentValue}</label><input
                                            type="hidden" value="${mdCostVo.mdCostConstruction.constructionAssessmentValue}"
                                            name="constructionAssessmentValue" placeholder="在建工程评估价值"></td>
                                </tr>
                                <tr>
                                    <td> 在建工程单位价(元)</td>
                                    <td><label
                                            class="constructionAssessmentPriceCorrecting">${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}</label><input
                                            type="hidden"
                                            value="${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}"
                                            name="constructionAssessmentPriceCorrecting" placeholder="在建工程单位价"></td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
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
                <form class="form-horizontal" id="frmMdCostConstruction">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12 "
                                                 data-title="boxMdCostConstruction">

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
            </div>
        </div>
    </div>
</div>


<script>

    var construction = {};

    construction.target = $("#constructionFrm");
    construction.type = "engineering";


    construction.callResidueRatio = function (_this, readonly) {
        try {
            var target = $(_this).closest("form");
            residueRatio.init({
                readonly: readonly,
                residueRatioId: target.find("input[name='residueRatioId']").val(),
                houseId: '${basicHouseVo.id}',
                success: function (id, resultValue) {
                }
            });
        } catch (e) {
        }
    };

    construction.constructionInstallationEngineeringFeeEvent = function (id) {
        var eleName = 'boxMdCostConstruction';
        var target = $("#" + eleName);
        target.modal("show");
        var table = target.find(".card-body").find("div[data-title=" + eleName + "]");
        table.empty() ;
        developmentCommon.getMdArchitecturalObjById(id, function (item) {
            var data = [];
            try {
                data = JSON.parse(item.jsonContent);
            } catch (e) {
                console.log("解析异常!");
            }
            var options = {
                target: table,
                obj: data,
                attribute: {disabled: "disabled"},
                price: item.price,
                reckon: 'c'
            };
            developmentCommon.architecturalB.init(options);
        });
    };

    construction.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var data = {pid: '${mdCostVo.mdCostConstruction.id}', planDetailsId: '${projectPlanDetails.id}'};
        developmentCommon.infrastructureChildren.loadTable2(data, $("#landMdCostConstructionChildrenTable"), null);
    };


    construction.loadMdCalculatingMethodEngineeringCostTable = function () {
        var obj = {
            projectId: '${projectPlanDetails.projectId}',
            judgeObjectId: '${projectPlanDetails.judgeObjectId}',
            planDetailsId: '${projectPlanDetails.id}',
        };
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="construction.constructionInstallationEngineeringFeeEvent(' + row.architecturalObjId + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="建筑安装工程费明细">';
                str += '<i class="fa  fa-cog"></i>';
                str += '</button>';
                return str;
            }
        });
        developmentCommon.loadMdCalculatingMethodEngineeringCostTable($("#engineeringConstructionInstallationEngineeringFeeInfoTarget"), obj, $("#toolbarMdCalculatingMethodEngineeringCost"), function () {

        }, cols);
    };


    construction.callCompareMethod = function (mcId) {
        if ($.isNumeric(mcId)) {
            var frame = layer.open({
                type: 2,
                title: '市场比较法',
                shadeClose: true,
                shade: true,
                maxmin: true, //开启最大化最小化按钮
                area: ['893px', '600px'],
                content: '${pageContext.request.contextPath}/marketCompare/index?mcId=' + mcId + '&judgeObjectId=${projectPlanDetails.judgeObjectId}&readonly=true',
                cancel: function (index, layero) {
                    var iframe = window[layero.find('iframe')[0]['name']];
                    if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                        $(_this).closest('form').find('[name=mcId]').val(iframe.marketCompare.mcId);
                    }
                },
                btnAlign: 'c',
                btn: ['关闭'],
                btn2: function (index, layero) {
                    var iframe = window[layero.find('iframe')[0]['name']];
                    if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                        $(_this).closest('form').find('[name=mcId]').val(iframe.marketCompare.mcId);
                    }
                }
            });
            layer.full(frame);
        } else {
            notifyInfo('提示','未使用过比较法!');
        }
    };

    $(function () {
        construction.loadMdDevelopmentInfrastructureChildrenTable();
        construction.loadMdCalculatingMethodEngineeringCostTable();
    });
</script>
