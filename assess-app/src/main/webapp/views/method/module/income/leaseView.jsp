<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="lease_info" style="display: none;">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>收入类</h2>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <table class="table table-bordered" id="tb_lease_income_list">
            </table>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>成本类</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <table class="table table-bordered" id="tb_lease_cost_list">
            </table>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>参数</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <form id="frm_lease" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="报酬率">
                            报酬率
                        </label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="text" required class="form-control x-percent" name="rewardRate"
                                       placeholder="报酬率" readonly="readonly"
                                       data-value="${mdIncome.rewardRate}">
                                <span class="input-group-btn">
                                     <input type="button" class="btn btn-primary" value="报酬率" onclick="rewardRateDetail.calculationDetail('${mdIncome.rewardRateId}');"/>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table table-bordered" id="tb_lease_parameter_list">
            </table>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>测算结果</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content form-horizontal" id="leaseResult">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        估价对象的价格
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control" data-name="price">${mdIncome.price}</label>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>收益期限(n)</th>
                    <th>毛收入</th>
                    <th>运营费</th>
                    <th>房地产年净收益</th>
                    <th>年期修正系数(h)</th>
                    <th>收益现值系数(k)</th>
                    <th>房地产收益价格</th>
                </tr>
                </thead>
                <tbody id="leaseResultBody">

                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="modal_lease_income" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">收入</h3>
            </div>
            <div class="modal-body">
                <form id="frm_lease_income" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                月租金收入(元/m2)
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="rentalIncome"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                全年月份数
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="monthNumber"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                出租率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" data-name="rentals"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                出租率说明
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="rentalsRemark"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                年押金(元/m2)
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="deposit"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                押金说明
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="depositRemark"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                押金利率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" data-name="depositRate"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                利率说明
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="depositRateRemark"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                其他收入
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="otherIncome"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                其它收入说明
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="otherIncomeRemark"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                有效收缴率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" data-name="additionalCapture"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                有效收缴率说明
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" data-name="additionalCaptureRemark"></label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal_lease_cost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">成本</h3>
            </div>
            <div class="modal-body">
                <form id="frm_lease_cost" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                管理费率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="managementCostRatio"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                重置价格(元/m2)
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" name="replacementValue"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                维护保养费率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="maintenanceCostRatio"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                保险费率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="insurancePremiumRatio"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                土地使用税
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control" name="landUseTax"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                其它相关税费率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="transactionTaxeFeeRatio"></label>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                其它相关税费率说明
                            </label>
                            <div class="col-sm-10">
                                <label class="form-control" name="transactionTaxeFeeExplain"></label>
                            </div>
                        </div>
                    </div>
                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                        color="#6f5499" size="10"/>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                房产税率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="propertyTaxRatio"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                印花税率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="stampDutyRatio"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                增值税率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="salesTaxRatio"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                城建税率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="constructionTaxRatio"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                地方教育费附加税率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="localEducationRatio"></label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                教育费附加税率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="educationRatio"></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                租赁税费率
                            </label>
                            <div class="col-sm-4">
                                <label class="form-control v-percent" name="additionalRatio"></label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>



<script type="text/html" id="leaseResultHtml">
    <tr data-id="{id}">
        <td>
            <input type="hidden" data-name="rentalGrowthRate" value="{rentalGrowthRate}">
            <label>{beginDate}</label>
        </td>
        <td><label>{endDate}</label></td>
        <td><label data-name="yearCount">{yearCount}</label></td>
        <td><label data-name="incomeTotal">{incomeTotal}</label></td>
        <td><label data-name="costTotal">{costTotal}</label></td>
        <td><label data-name="netProfit">{netProfit}</label></td>
        <td><label data-name="correctionFactor">{correctionFactor}</label></td>
        <td><label data-name="presentValueFactor">{presentValueFactor}</label></td>
        <td><label data-name="incomePrice">{incomePrice}</label></td>
    </tr>
</script>

<script type="text/javascript">
    var lease = {};


    //查看收入信息
    lease.viewLeaseIncome = function (index) {
        var row = $("#tb_lease_income_list").bootstrapTable('getData')[index];
        $("#frm_lease_income").find('label').each(function () {
            if ($(this).hasClass('v-percent')) {
                $(this).text(AssessCommon.pointToPercent(row[$(this).attr('data-name')]));
            } else {
                $(this).text(row[$(this).attr('data-name')]);
            }
        })
        $('#modal_lease_income').modal();
    }

    //查看成本信息
    lease.viewLeaseCost = function (index) {
        var row = $("#tb_lease_cost_list").bootstrapTable('getData')[index];
        $("#frm_lease_cost").find('label').each(function () {
            if ($(this).hasClass('v-percent')) {
                $(this).text(AssessCommon.pointToPercent(row[$(this).attr('name')]));
            } else {
                $(this).text(row[$(this).attr('name')]);
            }
        })
        $('#modal_lease_cost').modal();
    }

    //加载收入列表信息
    lease.loadLeaseList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'rentalIncome', title: '月租金收入'});
        cols.push({
            field: 'rentals', title: '出租率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'monthNumber', title: '全年月份数'});
        cols.push({field: 'deposit', title: '押金'});
        cols.push({
            field: 'depositRate', title: '押金利率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'otherIncome', title: '其他收入'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="lease.viewLeaseIncome(' + index + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_income_list").bootstrapTable('destroy');
        TableInit("tb_lease_income_list", "${pageContext.request.contextPath}/income/getLeaseList", cols, {
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载成本列表信息
    lease.loadLeaseCostList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({
            field: 'managementCostRatio', title: '管理费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'maintenanceCostRatio', title: '维修保养费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'replacementValue', title: '重置价格'});
        cols.push({
            field: 'additionalRatio', title: '租赁税费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'insurancePremiumRatio', title: '保险费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'landUseTax', title: '土地使用税'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="lease.viewLeaseCost(' + index + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_cost_list").bootstrapTable('destroy');
        TableInit("tb_lease_cost_list", "${pageContext.request.contextPath}/income/getLeaseCostList", cols, {
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //编辑参数信息
    lease.editLeaseParameter = function (index) {
        var row = $("#tb_lease_parameter_list").bootstrapTable('getData')[index];
        $("#frm_lease_parameter").clearAll();
        $("#frm_lease_parameter").initForm(row);
        $("#frm_lease_parameter").find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        $('#modal_lease_parameter').modal();
    }


    //加载参数列表信息
    lease.loadLeaseParameterList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({
            field: 'rentalGrowthRate', title: '租金增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'rentalGrowthRateExplain', title: '增长率说明'});
        $("#tb_lease_parameter_list").bootstrapTable('destroy');
        TableInit("tb_lease_parameter_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            operationMode: incomeIndex.getOperationMode(),
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载测算结果数据
    lease.loadCalculationResult = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/income/getDateSectionList",
            type: "get",
            dataType: "json",
            data: {
                incomeId: incomeIndex.getInComeId(),
                limit: 100,
                operationMode: incomeIndex.getOperationMode()
            },
            success: function (result) {
                if (result.rows) {
                    $("#leaseResultBody").empty();
                    $(result.rows).each(function (i, item) {
                        var html = $('#leaseResultHtml').html();
                        html = html.replace(/{id}/g, item.id).replace(/{beginDate}/g, formatDate(item.beginDate, false));
                        html = html.replace(/{endDate}/g, formatDate(item.endDate, false)).replace(/{yearCount}/g, item.yearCount);
                        html = html.replace(/{incomeTotal}/g, AssessCommon.toString(item.incomeTotal)).replace(/{costTotal}/g, AssessCommon.toString(item.costTotal));
                        html = html.replace(/{rentalGrowthRate}/g, AssessCommon.toString(item.rentalGrowthRate));
                        html = html.replace(/{netProfit}/g, AssessCommon.toString(item.netProfit)).replace(/{correctionFactor}/g, AssessCommon.toString(item.correctionFactor));
                        html = html.replace(/{presentValueFactor}/g, AssessCommon.toString(item.presentValueFactor)).replace(/{incomePrice}/g, AssessCommon.toString(item.incomePrice));
                        $("#leaseResultBody").append(html);
                    })
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

</script>



