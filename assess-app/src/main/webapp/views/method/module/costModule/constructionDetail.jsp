<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="x_panel">
    <input type="hidden" name="id" value="${mdCostVo.mdCostConstruction.id}">
    <input type="hidden" name="pid" value="${mdCostVo.id}">
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
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.developLandAreaTax} </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发建筑面积(㎡)<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.developBuildAreaTax} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发期（年）<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.developYearNumberTax} </label>

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
                        <label  class="form-control"> ${mdCostVo.mdCostConstruction.landPurchasePrice} </label>
                        <span class="input-group-btn">
                                        <input type="button" class="btn btn-primary" value="市场比较法"
                                               onclick="callCompareMethod('${mdCostVo.mdCostConstruction.mcId}');">
                                    </span>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    价格说明
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.landPurchasePriceExplain} </label>

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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.landGetRelevant}" type="percent"/>
                    </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.landGetRelevantExplain} </label>

                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">

                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.additionalCostLandAcquisition} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostVo.mdCostConstruction.additionalCostLandAcquisitionExplain}</label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.reconnaissanceDesign}" type="percent"/>
                        </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostVo.mdCostConstruction.reconnaissanceDesignExplain}</label>
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
                        <label  class="form-control"> ${mdCostVo.mdCostConstruction.constructionInstallationEngineeringFee} </label>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    工程费列表
                </label>
                <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                    <c:forEach items="${mdCostVo.mdCostConstruction.constructionInstallationEngineeringFeeDtos}" var="item">
                        <div class="panel panel-info">
                            <i class="fa fa-search" onclick="constructionInstallationEngineeringFeeEvent('${item.key}')" title="查看"
                               style="margin-right: 10px;font-size: 15px;cursor: pointer;"></i>
                            <a  style="cursor: pointer;" onclick="constructionInstallationEngineeringFeeEvent('${item.key}')">${item.value}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                基础设施建设费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.infrastructureCost} </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostVo.mdCostConstruction.infrastructureCostExplain}</label>
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
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.infrastructureMatchingCost} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostVo.mdCostConstruction.infrastructureMatchingCostExplain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                开发期间税费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.devDuring} </label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostVo.mdCostConstruction.devDuringExplain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                其它工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.otherEngineeringCost} </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label   class="form-control">${mdCostVo.mdCostConstruction.otherEngineeringCostExplain}</label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.unforeseenExpenses}" type="percent"/>
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.unforeseenExpensesExplain} </label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.managementExpense}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.managementExpenseExplain} </label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.salesFee}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.salesFeeExplain} </label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.interestInvestmentTax}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.interestInvestmentTaxExplain} </label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.salesTaxAndAdditional}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.salesTaxAndAdditionalExplain} </label>
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
                        <fmt:formatNumber value="${mdCostVo.mdCostConstruction.investmentProfitTax}" type="percent"/>
                         </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label  class="form-control"> ${mdCostVo.mdCostConstruction.investmentProfitTaxExplain} </label>
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
    <input type="hidden" readonly="readonly" name="constructionAssessmentValue2" onblur="construction.constructionAssessmentValueCalculationE25();" value="${mdCostVo.mdCostConstruction.constructionAssessmentValue2}" class="form-control" placeholder="在建工程评估价值2">
    <div class="x_content">
        <div class="form-group">
            <div class="col-md-12 col-sm-12">
                <table class="table table-bordered">
                    <tfoot>
                    <tr>
                        <td> 土地取得成本小计</td>
                        <td><label class="landGetCostTotal">${mdCostVo.mdCostConstruction.landGetCostTotal}</label> <input type="hidden"  value="${mdCostVo.mdCostConstruction.landGetCostTotal}" name="landGetCostTotal" placeholder="土地取得成本小计"> </td>
                    </tr>
                    <tr>
                        <td> 建设成本小计</td>
                        <td><label class="constructionSubtotal">${mdCostVo.mdCostConstruction.constructionSubtotal}</label></td>
                    </tr>
                    <tr>
                        <td> 投资利息</td>
                        <td><label class="interestInvestment">${mdCostVo.mdCostConstruction.interestInvestment}</label><input  type="hidden" value="${mdCostVo.mdCostConstruction.interestInvestment}" name="interestInvestment" placeholder="投资利息"> </td>
                    </tr>
                    <tr>
                        <td> 开发利润</td>
                        <td><label class="investmentProfit">${mdCostVo.mdCostConstruction.investmentProfit}</label><input type="hidden"  value="${mdCostVo.mdCostConstruction.investmentProfit}" name="investmentProfit" placeholder="开发利润"> </td>
                    </tr>
                    <tr>
                        <td> 在建工程评估价值</td>
                        <td><label class="constructionAssessmentValue">${mdCostVo.mdCostConstruction.constructionAssessmentValue}</label><input type="hidden"  value="${mdCostVo.mdCostConstruction.constructionAssessmentValue}" name="constructionAssessmentValue" placeholder="在建工程评估价值"> </td>
                    </tr>
                    <tr>
                        <td> 在建工程单位价</td>
                        <td><label class="constructionAssessmentPriceCorrecting">${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}</label><input type="hidden" value="${mdCostVo.mdCostConstruction.constructionAssessmentPriceCorrecting}" name="constructionAssessmentPriceCorrecting" placeholder="在建工程单位价"> </td>
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
    function constructionInstallationEngineeringFeeEvent(id) {
        var target = $("#boxMdCostConstruction");
        if (target.find(".panel-body").find("table").size() == 0) {
            target.find(".panel-body").append(developmentCommon.architecturalB.getHtmlDetail());
            developmentCommon.architecturalB.treeGirdParse(target);
        }
        developmentCommon.getMdArchitecturalObjById(id,function (item) {
            var data = {} ;
            try {
                data = JSON.parse(item.jsonContent) ;
            } catch (e) {
                console.log("解析异常!");
            }
            developmentCommon.architecturalB.initData(target.find("table"),data) ;
        });
        target.modal("show");
    }

    function loadMdDevelopmentInfrastructureChildrenTable() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '金额'});
        cols.push({field: 'tax', title: '税费'});
        $("#landMdCostConstructionChildrenTable").bootstrapTable('destroy');
        TableInit('landMdCostConstructionChildrenTable', "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/getBootstrapTableVo?pid=${mdCostVo.mdCostConstruction.id}&planDetailsId=${projectPlanDetails.id}&type=engineering", cols, {}, {
            showColumns: true,
            showRefresh: true,
            search: false
        });
    }

    function callCompareMethod(mcId) {
        if ($.isNumeric(mcId)){
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
