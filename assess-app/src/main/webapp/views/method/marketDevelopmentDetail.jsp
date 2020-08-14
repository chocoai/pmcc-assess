<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                                经营方式
                            </label>
                            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                <c:if test="${mdDevelopment.type == 1}">
                                    <div class="form-check" style="justify-content:left">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" name="developmentLand"
                                                   checked="checked" disabled="disabled">
                                            <span class="form-check-sign">土地</span>
                                        </label>
                                    </div>
                                </c:if>
                                <c:if test="${mdDevelopment.type == 2}">
                                    <div class="form-check" style="justify-content:left">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox"
                                                   name="developmentEngineering" checked="checked" disabled="disabled">
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

<!-- 假设开发法 -->

<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    基本参数
                </div>
            </div>
        </div>
        <div class="card-body ">
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            项目建设期(年)
                        </label>
                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                            <label class="form-control input-full">${mdDevelopment.projectConstructionPeriod}</label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            已开发时间(年)
                        </label>
                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                            <label class="form-control input-full">${mdDevelopment.developedYear}</label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            剩余开发时间(年)
                        </label>
                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                            <label class="form-control input-full">${mdDevelopment.remainingDevelopmentYear}</label>
                        </div>
                        <c:if test="${mdDevelopment.type == 1}">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                土地面积
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <label class="form-control input-full">${mdDevelopment.landArea}</label>
                            </div>
                        </c:if>
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
                    经济指标
                    <button type="button" class="btn btn-info btn-sm"
                            onclick="economicIndicators.init({economicId:'${mdDevelopment.economicId}',attribute:{readonly:'readonly','class':'form-control'} });">
                        经济规划指标
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body ">
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <table class="table table-condensed">
                                <tfoot>
                                <tr>
                                    <td>收入类预期销售合计:</td>
                                    <td class="info">规划建筑面积 ${mdDevelopment.plannedBuildingArea} (㎡)</td>
                                    <td class="info">总可售面积售价 ${mdDevelopment.totalSaleableAreaPrice} (万元)</td>
                                    <td class="info">可售面积 ${mdDevelopment.saleableArea}(㎡)</td>
                                    <td class="active">不可售建筑面积 ${mdDevelopment.unsaleableBuildingArea} (㎡)</td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            移交面积(㎡)
                        </label>
                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                            <label class="form-control input-full">${mdDevelopment.transferArea}</label>
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
                            <label class="form-control input-full">${mdDevelopment.parcelSettingOuterName}</label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            宗地内设定
                        </label>
                        <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                            <label class="form-control input-full">${mdDevelopment.parcelSettingInnerName}</label>
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
                    单位成本或费率
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
                                <fmt:formatNumber value="${mdDevelopment.reconnaissanceDesign}" type="percent"
                                                  maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.reconnaissanceDesignExplain}</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            建筑安装工程费 (元/㎡)
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdDevelopment.constructionInstallationEngineeringFee}
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div id="toolbarMdCalculatingMethodEngineeringCostLand" style="display: none">
                            </div>
                            <table class="table table-striped"
                                   id="landConstructionInstallationEngineeringFeeInfoTarget">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            基础设施配套费 (元/㎡)
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdDevelopment.infrastructureCost}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.infrastructureCostExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
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
                            公共配套设施建设费 (元/㎡)
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdDevelopment.infrastructureMatchingCost}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.infrastructureMatchingCostExplain}</label>
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
                                ${mdDevelopment.devDuring}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.devDuringExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            其它工程费率 (元/㎡)
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.otherEngineeringCost}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.otherEngineeringCostExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            不可预见费率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.unforeseenExpenses}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.unforeseenExpensesExplain}</label>
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
                            契税率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.deedTaxRate}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.deedTaxRateExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            交易费率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.transactionTaxRate}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.transactionTaxRateExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            <c:if test="${mdDevelopment.type == 1}">
                                管理费率
                            </c:if>

                            <c:if test="${mdDevelopment.type == 2}">
                                续建管理费率
                            </c:if>


                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.managementExpense}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.managementExpenseExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            <c:if test="${mdDevelopment.type == 1}">
                                土地取得附加成本 (万元)
                            </c:if>

                            <c:if test="${mdDevelopment.type == 2}">
                                在建工程修复费用 (万元)
                            </c:if>


                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                ${mdDevelopment.landGetRelevant}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.landGetRelevantExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            销售费用率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.salesFee}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.salesFeeExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            <c:if test="${mdDevelopment.type == 1}">
                                投资利息率
                            </c:if>

                            <c:if test="${mdDevelopment.type == 2}">
                                续建投资利息率
                            </c:if>


                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.interestInvestmentTax}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.interestInvestmentTaxExplain}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            <c:if test="${mdDevelopment.type == 1}">
                                投资利润率
                            </c:if>

                            <c:if test="${mdDevelopment.type == 2}">
                                续建投资利润率
                            </c:if>


                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.investmentProfitTax}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.investmentProfitTaxExplain}</label>
                        </div>
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
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.salesTaxAndAdditional}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.salesTaxAndAdditionalExplain}</label>
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
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.landValueAddedTax}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.landValueAddedTaxExplain}</label>
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
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.projectDevelopmentIncomeTax}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            费率说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.projectDevelopmentIncomeTaxExplain}</label>
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
                    地价修正
                </div>
            </div>
        </div>
        <div class="card-body ">
            <div class="row form-group">
                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                    <div class="form-inline x-valid">
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            土地还原率
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <div class="input-group">
                                <label class="form-control">
                                    <fmt:formatNumber value="${mdDevelopment.remunerationRate}" type="percent"
                                                      maxFractionDigits="4"/>
                                </label>
                            </div>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            法定年限
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.statutoryLife}</label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            剩余年限
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.remainingYears}</label>
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
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.amendmentStatusRights}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.amendmentStatusRightsExplain}</label>
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
                            <label class="form-control input-full">
                                <fmt:formatNumber value="${mdDevelopment.otherAmendments}"
                                                  type="percent" maxFractionDigits="4"/>
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.otherAmendmentsExplain}</label>
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
                            <label class="form-control input-full">
                                ${mdDevelopment.developmentDegreeRevision}
                            </label>
                        </div>
                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                            说明
                        </label>
                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                            <label class="form-control input-full">${mdDevelopment.developmentDegreeRevisionExplain}</label>
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
                                <tbody>
                                <tr>
                                    <td> 工程建设成本小计</td>
                                    <td class="constructionCostSubtotal">${mdDevelopment.constructionCostSubtotal}</td>
                                </tr>
                                <tr>
                                    <td> 投资利息</td>
                                    <td class="interestInvestment">${mdDevelopment.interestInvestment}</td>
                                </tr>
                                <tr>
                                    <td> 投资利润</td>
                                    <td class="investmentProfit">${mdDevelopment.investmentProfit}</td>
                                </tr>
                                <tr>
                                    <td> 委估土地单价（元/㎡）</td>
                                    <td class="assessPrice">${mdDevelopment.assessPrice}</td>
                                </tr>
                                <tr>
                                    <td> 测算单价</td>
                                    <td>${mdDevelopment.price}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>

<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRateDetail.jsp" %>

<%@include file="/views/method/module/economicIndicators.jsp" %>

<script>

    var landEngineering = {};
    landEngineering.target = $("#developmentFrm");
    landEngineering.type = '${mdDevelopment.type}';

    landEngineering.constructionInstallationEngineeringFeeEvent = function (id) {
        var eleName = 'boxLandEngineering';
        var target = $("#" + eleName);
        target.modal("show");
        var table = target.find("div[data-title=" + eleName + "]");
        table.empty();
        developmentCommon.getMdArchitecturalObjById(id, function (item) {
            var data = [];
            try {
                data = JSON.parse(item.jsonContent);
            } catch (e) {
                console.log("解析异常!");
            }
            var reckon = "b";
            if (landEngineering.type == 1) {
                reckon = "a";
            }
            var options = {
                target: table,
                obj: data,
                attribute: {disabled: "disabled"},
                price: item.price,
                reckon: reckon
            };
            developmentCommon.architecturalB.init(options);
        });
    };

    landEngineering.loadMdCalculatingMethodEngineeringCostTable = function () {
        var obj = {
            projectId: '${projectPlanDetails.projectId}',
            judgeObjectId: '${projectPlanDetails.judgeObjectId}',
            planDetailsId: '${projectPlanDetails.id}',
            type: landEngineering.type
        };
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="landEngineering.constructionInstallationEngineeringFeeEvent(' + row.architecturalObjId + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="建筑安装工程费明细">';
                str += '<i class="fa fa-cog"></i>';
                str += '</button>';
                return str;
            }
        });
        developmentCommon.loadMdCalculatingMethodEngineeringCostTable($("#landConstructionInstallationEngineeringFeeInfoTarget"), obj, $("#toolbarMdCalculatingMethodEngineeringCostLand"), function () {
        }, cols);
    };

    landEngineering.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var data = {pid: '${mdDevelopment.id}', planDetailsId: '${projectPlanDetails.id}'};
        developmentCommon.infrastructureChildren.loadTable2(data, $("#landMdDevelopmentInfrastructureChildrenTable"), null);
    };


    $(function () {
        landEngineering.loadMdDevelopmentInfrastructureChildrenTable();
        landEngineering.loadMdCalculatingMethodEngineeringCostTable();
    });

</script>


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
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
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