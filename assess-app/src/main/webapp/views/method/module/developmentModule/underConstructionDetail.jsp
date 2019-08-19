<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <h3>收入类(参数)</h3>
        <div class="clearfix"></div>
    </div>


    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                    <table class="table table-striped" id="engineeringIncomeCategoryTableId" >

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
                            <td class="info">规划建筑面积<label name="plannedBuildingArea" class="label label-default">${mdDevelopment.plannedBuildingArea}</label></td>
                            <td class="info">总可售面积售价<label name="totalSaleableAreaPrice" class="label label-default">${mdDevelopment.totalSaleableAreaPrice}</label></td>
                            <td class="info">可售面积<label name="saleableArea" class="label label-default">${mdDevelopment.saleableArea}</label></td>
                            <td class="active">
                                ${mdCostConstruction.unsaleableBuildingArea}
                                <!-- 不可售建筑面积 -->
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
                    <label class="form-control">${mdDevelopment.reconnaissanceDesign}</label>
                </div>
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
            <label class="col-sm-1 control-label">
                建筑安装工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control">${mdDevelopment.constructionInstallationEngineeringFee}</label>
                        <span class="input-group-btn">
                              <input type="button" class="btn btn-primary" value="建筑安装工程费"
                                     onclick="constructionInstallationEngineeringFeeEvent();"/>
                            </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    基础设施配套费<span class="symbol required"></span>
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
                    公共配套设施建设费<span class="symbol required"></span>
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
                    开发期间税费<span class="symbol required"></span>
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
                    <label class="form-control">${mdDevelopment.otherEngineeringCost}</label>
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
                    <label class="form-control">${mdDevelopment.unforeseenExpenses}</label>
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
                    <table class="table table-bordered" id="underConstructionMdDevelopmentInfrastructureChildrenTable">

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
                    <label class="form-control">${mdDevelopment.deedTaxRate}</label>
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
                    <label class="form-control">${mdDevelopment.transactionTaxRate}</label>
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
                    续建管理费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.managementExpense}</label>
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
                    在建工程修复费用<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.landGetRelevant}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    在建工程修复费用说明
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
                    <label class="form-control">${mdDevelopment.salesFee}</label>
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
                    续建投资利息率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.interestInvestmentTax}</label>
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
                    续建投资利润（万元）<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.investmentProfitTax}</label>
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
                    <label class="form-control">${mdDevelopment.salesTaxAndAdditional}</label>

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
                    <label class="form-control">${mdDevelopment.landValueAddedTax}</label>

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
                    <label class="form-control">${mdDevelopment.projectDevelopmentIncomeTax}</label>
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
                    土地还原率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control">${mdDevelopment.remunerationRate}</label>
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
                        <td> 续建建设成本小计</td>
                        <td class="constructionCostSubtotal">${mdDevelopment.constructionCostSubtotal}</td>
                    </tr>
                    <tr>
                        <td> 续建投资利息</td>
                        <td class="interestInvestment">${mdDevelopment.interestInvestment}</td>
                    </tr>
                    <tr>
                        <td> 续建投资利润</td>
                        <td class="investmentProfit">${mdDevelopment.investmentProfit}</td>
                    </tr>
                    <tr>
                        <td> 在建工程单价（元/㎡）</td>
                        <td class="assessPrice">${mdDevelopment.assessPrice}</td>
                    </tr>
                    <tr>
                        <td> 测算单价</td>
                        <td >${mdDevelopment.price}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!--  建筑安装工程费 -->
<div id="boxMdDevelopmentEngineering" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装工程费</h3>
            </div>
            <form id="boxMdDevelopmentEngineeringFrm" class="form-horizontal">
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

<script>
    function constructionInstallationEngineeringFeeEvent() {
        var target = $("#boxMdDevelopmentEngineering");
        if (target.find(".panel-body").find("table").size() == 0) {
            target.find(".panel-body").append(developmentCommon.architecturalB.getHtmlDetail());
            developmentCommon.architecturalB.treeGirdParse(target);
        }
        developmentCommon.architecturalB.getData("engineering",AssessDBKey.ProjectPlanDetails,'${projectPlanDetails.pid}','${projectPlanDetails.pid}',function (data) {
            var item = undefined ;
            if (data.length >= 1){
                var n = data[0] ;
                if (n.jsonContent){
                    try {
                        item = JSON.parse(n.jsonContent) ;
                    } catch (e) {
                        console.log("解析异常!") ;
                    }
                }
            }
            if (item){
                developmentCommon.architecturalB.initData(target.find("table"),item) ;
            }
        }) ;
        target.modal("show");
    }


    function loadMdDevelopmentInfrastructureChildrenTable() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '金额'});
        cols.push({field: 'tax', title: '税费'});
        $("#underConstructionMdDevelopmentInfrastructureChildrenTable").bootstrapTable('destroy');
        TableInit('underConstructionMdDevelopmentInfrastructureChildrenTable', "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/getBootstrapTableVo?pid=${mdDevelopment.id}&planDetailsId=${mdDevelopment.planDetailsId}&type=engineering", cols, {}, {
            showColumns: true,
            showRefresh: true,
            search: false
        });
    }

    function loadIncomeCategoryTable() {
        var obj = {type:'engineering',planDetailsId:'${projectPlanDetails.id}'} ;
        developmentCommon.loadIncomeCategoryTable($("#engineeringIncomeCategoryTableId"),obj,null,function () {

        }) ;
    }


    $(function () {
        loadMdDevelopmentInfrastructureChildrenTable() ;
        loadIncomeCategoryTable() ;
    });
</script>




