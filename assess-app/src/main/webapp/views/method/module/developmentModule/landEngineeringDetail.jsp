<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form class="form-horizontal" id="developmentFrm">
    <div class="x_content">
        <input type="hidden" name="id" value="${mdDevelopment.id}">
        <div class="col-sm-12 form-group">
                <span class="col-sm-1">
                    <label>经营方式</label><span class="symbol required"></span>
                </span>
            <c:if test="${mdDevelopment.type == 1}">
                    <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                         <input type="radio" id="developmentLand" name="type" value="1" checked="checked">
                        <label for="developmentLand">土地</label>
                    </span>
            </c:if>
            <c:if test="${mdDevelopment.type == 2}">
                   <span class="col-sm-2  checkbox-inline">
                    <input type="radio" id="developmentEngineering" name="type" value="2" checked="checked">
                    <label for="developmentEngineering">在建工程</label>
                    </span>
            </c:if>
        </div>
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
                        项目建设期(年)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.projectConstructionPeriod}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        已开发时间(年)
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.developedYear}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        剩余开发时间(年)
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.remainingDevelopmentYear}</label>
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
                        <input type="button" class="btn btn-primary" value="经济规划指标"
                               onclick="economicIndicators.init({economicId:'${mdDevelopment.economicId}',attribute:{readonly:'readonly','class':'form-control'} });">
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
                                <td class="info">规划建筑面积 (㎡)<label name="plannedBuildingArea" class="label label-default">${mdDevelopment.plannedBuildingArea}</label></td>
                                <td class="info">总可售面积售价 (万元)<label name="totalSaleableAreaPrice" class="label label-default">${mdDevelopment.totalSaleableAreaPrice}</label></td>
                                <td class="info">可售面积 (㎡)<label name="saleableArea" class="label label-default">${mdDevelopment.saleableArea}</label></td>
                                <td class="active">不可售建筑面积 (㎡)<label name="unsaleableBuildingArea" class="label label-default">${mdDevelopment.unsaleableBuildingArea}</label></td>
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
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.reconnaissanceDesign}" type="percent" maxFractionDigits="4"/></label>                </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.reconnaissanceDesignExplain}</label>
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
                                        建筑安装工程费 (元/㎡)
                                    </button>
                                </span>
                                <span class="input-group-btn">
                                   <label class="form-control">${mdDevelopment.constructionInstallationEngineeringFee}</label>
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
                        基础设施配套费 (元/㎡)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.infrastructureCost}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.infrastructureCostExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        公共配套设施建设费 (元/㎡)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.infrastructureMatchingCost}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.infrastructureMatchingCostExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        开发期间税费 (元/㎡)<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.devDuring}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.devDuringExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        其它工程费率<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.otherEngineeringCost}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.otherEngineeringCostExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        不可预见费率<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.unforeseenExpenses}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.unforeseenExpensesExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <div class="col-sm-12">
                        <table class="table table-bordered" id="landMdDevelopmentInfrastructureChildrenTable">

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
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.deedTaxRate}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.deedTaxRateExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        交易费率<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.transactionTaxRate}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.transactionTaxRateExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        <c:if test="${mdDevelopment.type == 1}">
                            管理费率
                        </c:if>

                        <c:if test="${mdDevelopment.type == 2}">
                            续建管理费率
                        </c:if>
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.managementExpense}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.managementExpenseExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        <c:if test="${mdDevelopment.type == 1}">
                            土地取得附加成本 (万元)
                        </c:if>

                        <c:if test="${mdDevelopment.type == 2}">
                            在建工程修复费用 (万元)
                        </c:if>
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.landGetRelevant}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.landGetRelevantExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        销售费用率<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.salesFee}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.salesFeeExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        <c:if test="${mdDevelopment.type == 1}">
                            投资利息率
                        </c:if>

                        <c:if test="${mdDevelopment.type == 2}">
                            续建投资利息率
                        </c:if>
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.interestInvestmentTax}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.interestInvestmentTaxExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">

                        <c:if test="${mdDevelopment.type == 1}">
                            投资利润率
                        </c:if>

                        <c:if test="${mdDevelopment.type == 2}">
                            续建投资利润率
                        </c:if>
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.investmentProfitTax}" type="percent" maxFractionDigits="4"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.investmentProfitTaxExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        销售环节增值税及附加<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.salesTaxAndAdditional}" type="percent" maxFractionDigits="4"/></label>

                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.salesTaxAndAdditionalExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        土地增值税<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.landValueAddedTax}" type="percent"/></label>

                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.landValueAddedTaxExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        项目开发所得税<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatNumber value="${mdDevelopment.projectDevelopmentIncomeTax}" type="percent"/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        费率说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.projectDevelopmentIncomeTaxExplain}</label>
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
                            <label class="form-control"><fmt:formatNumber value="${mdDevelopment.remunerationRate}" type="percent" maxFractionDigits="4"/></label>
                            <span class="input-group-btn">
                                        <input type="hidden" name="rewardRateId" value="${mdDevelopment.rewardRateId}">
                                  <input type="button" class="btn btn-primary" value="报酬率测算"
                                         onclick="rewardRateDetail.calculationDetail('${mdDevelopment.rewardRateId}');"/>
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
                        <label class="form-control">${mdDevelopment.statutoryLife}</label>
                    </div>
                </div>

                <label class="col-sm-1 control-label">
                    剩余年限<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.remainingYears}</label>
                    </div>
                </div>
            </div>



            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        权利状况修正<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.amendmentStatusRights}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.amendmentStatusRightsExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        其他修正<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.otherAmendments}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.otherAmendmentsExplain}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        开发程度修正<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.developmentDegreeRevision}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        说明
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.developmentDegreeRevisionExplain}</label>
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
            <div class="form-group">
                <div class="col-md-12 col-sm-12">
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

</form>


<script>

    var landEngineering = {};
    landEngineering.target = $("#developmentFrm");
    landEngineering.type = '${mdDevelopment.type}' ;

    landEngineering.constructionInstallationEngineeringFeeEvent = function (id) {
        var target = $("#boxLandEngineering");
        target.modal("show");
        target.find(".panel-body").empty() ;
        developmentCommon.getMdArchitecturalObjById(id,function (item) {
            var data = [] ;
            try {
                data = JSON.parse(item.jsonContent) ;
            } catch (e) {
                console.log("解析异常!");
            }
            var attribute = {readonly:"readonly",'class':'form-control'} ;
            if ('${mdDevelopment.type}' == 1){
                developmentCommon.architecturalA.appendHtml(target.find(".panel-body"),data,attribute,item.price,function (tr) {
                    $(tr).find("input").attr(attribute);
                }) ;
            }else {
                developmentCommon.architecturalB.appendHtml(target.find(".panel-body"),data,attribute,item.price,function (tr) {
                    $(tr).find("input").attr(attribute);
                }) ;
            }
        });
    };

    landEngineering.loadMdCalculatingMethodEngineeringCostTable = function () {
        var obj = {planDetailsId:'${projectPlanDetails.id}',type:landEngineering.type} ;
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='建筑安装工程费明细' onclick='landEngineering.constructionInstallationEngineeringFeeEvent(" + row.architecturalObjId + ")'" + ">" + "<i class='fa fa-search fa-white'>" + "建筑安装工程费明细" + "</a>";
                str += '</div>';
                return str;
            }
        });
        developmentCommon.loadMdCalculatingMethodEngineeringCostTable($("#landConstructionInstallationEngineeringFeeInfoTarget"),obj,$("#toolbarMdCalculatingMethodEngineeringCostLand"),function () {
        },cols) ;
    };

    landEngineering.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var data = {pid:'${mdDevelopment.id}'} ;
        developmentCommon.infrastructureChildren.loadTable2(data,$("#landMdDevelopmentInfrastructureChildrenTable"),null) ;
    };



    $(function () {
        landEngineering.loadMdDevelopmentInfrastructureChildrenTable() ;
        landEngineering.loadMdCalculatingMethodEngineeringCostTable() ;
    });

</script>


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
            <form id="boxLandEngineeringFrm" class="form-horizontal">
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
                        关闭
                    </button>

                </div>
            </form>
        </div>
    </div>
</div>

