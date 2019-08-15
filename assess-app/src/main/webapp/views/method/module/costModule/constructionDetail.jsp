<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="x_panel">
    <input type="hidden" name="id" value="${mdCostConstruction.id}">
    <input type="hidden" name="pid" value="${mdCost.id}">
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
                    <label  class="form-control"> ${mdCostConstruction.developLandAreaTax} </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发建筑面积(㎡)<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostConstruction.developBuildAreaTax} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发期（年）<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostConstruction.developYearNumberTax} </label>

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
        <h3>土地取得成本</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地购买价格<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label  class="form-control"> ${mdCostConstruction.landPurchasePrice} </label>
                        <span class="input-group-btn">
                                        <input type="button" class="btn btn-primary" value="市场比较法"
                                               onclick="callCompareMethod('${mdCostConstruction.mcId}');">
                                    </span>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    价格说明
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostConstruction.landPurchasePriceExplain} </label>

                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得相关税费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.landGetRelevant}" type="percent"/>
                    </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostConstruction.landGetRelevantExplain} </label>

                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostConstruction.additionalCostLandAcquisition} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostConstruction.additionalCostLandAcquisitionExplain}</label>
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
        <h3>建设成本或费率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    勘察设计和前期工程费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.reconnaissanceDesign}" type="percent"/>
                        </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostConstruction.reconnaissanceDesignExplain}</label>
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

                        <label  class="form-control"> ${mdCostConstruction.constructionInstallationEngineeringFee} </label>

                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="constructionInstallationEngineeringFeeEvent();">
                                            <i class="fa fa-search"></i>
                                            </button>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                基础设施建设费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.infrastructureCost} </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostConstruction.infrastructureCostExplain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
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
                    <label  class="form-control"> ${mdCostConstruction.infrastructureMatchingCost} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostConstruction.infrastructureMatchingCostExplain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                开发期间税费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.devDuring} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostConstruction.devDuringExplain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                其它工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.otherEngineeringCost} </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostConstruction.otherEngineeringCostExplain}</label>
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
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.unforeseenExpenses}" type="percent"/>
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.unforeseenExpensesExplain} </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    管理费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.managementExpense}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.managementExpenseExplain} </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.salesFee}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.salesFeeExplain} </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    投资利息率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.interestInvestmentTax}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.interestInvestmentTaxExplain} </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售税金及附加率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.salesTaxAndAdditional}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.salesTaxAndAdditionalExplain} </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发利润率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label  class="form-control">
                        <fmt:formatNumber value="${mdCostConstruction.investmentProfitTax}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostConstruction.investmentProfitTaxExplain} </label>
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
    <input type="hidden" readonly="readonly" name="constructionAssessmentValue2" onblur="construction.constructionAssessmentValueCalculationE25();" value="${mdCostConstruction.constructionAssessmentValue2}" class="form-control" placeholder="在建工程评估价值2">
    <div class="x_content">
        <div class="form-group">
            <div class="col-md-12 col-sm-12">
                <table class="table table-bordered">
                    <tfoot>
                    <tr>
                        <td> 土地取得成本小计</td>
                        <td><label class="landGetCostTotal">${mdCostConstruction.landGetCostTotal}</label> <input type="hidden"  value="${mdCostConstruction.landGetCostTotal}" name="landGetCostTotal" placeholder="土地取得成本小计"> </td>
                    </tr>
                    <tr>
                        <td> 建设成本小计</td>
                        <td><label class="constructionSubtotal">${mdCostConstruction.constructionSubtotal}</label></td>
                    </tr>
                    <tr>
                        <td> 投资利息</td>
                        <td><label class="interestInvestment">${mdCostConstruction.interestInvestment}</label><input  type="hidden" value="${mdCostConstruction.interestInvestment}" name="interestInvestment" placeholder="投资利息"> </td>
                    </tr>
                    <tr>
                        <td> 开发利润</td>
                        <td><label class="investmentProfit">${mdCostConstruction.investmentProfit}</label><input type="hidden"  value="${mdCostConstruction.investmentProfit}" name="investmentProfit" placeholder="开发利润"> </td>
                    </tr>
                    <tr>
                        <td> 在建工程评估价值</td>
                        <td><label class="constructionAssessmentValue">${mdCostConstruction.constructionAssessmentValue}</label><input type="hidden"  value="${mdCostConstruction.constructionAssessmentValue}" name="constructionAssessmentValue" placeholder="在建工程评估价值"> </td>
                    </tr>
                    <tr>
                        <td> 在建工程单位价</td>
                        <td><label class="constructionAssessmentPriceCorrecting">${mdCostConstruction.constructionAssessmentPriceCorrecting}</label><input type="hidden" value="${mdCostConstruction.constructionAssessmentPriceCorrecting}" name="constructionAssessmentPriceCorrecting" placeholder="在建工程单位价"> </td>
                    </tr>
                    </tfoot>
                </table>
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
            <form id="frmMdCostConstruction" class="form-horizontal">
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
        var target = $("#boxMdCostConstruction");
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
        $("#landMdCostConstructionChildrenTable").bootstrapTable('destroy');
        TableInit('landMdCostConstructionChildrenTable', "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/getBootstrapTableVo?pid=${mdCostConstruction.id}&planDetailsId=${projectPlanDetails.id}&type=engineering", cols, {}, {
            showColumns: true,
            showRefresh: true,
            search: false
        });
    }

    function callCompareMethod(mcId) {
        if (mcId){
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
        }else {
            toastr.success('未使用过比较法!');
        }
    }


    $(function () {
        loadMdDevelopmentInfrastructureChildrenTable() ;
    });
</script>
