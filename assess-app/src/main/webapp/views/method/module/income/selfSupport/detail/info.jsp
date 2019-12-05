<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="self_support_info" style="display: none;">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>收入类</h3>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div id="ref_forecastIncome">
                <jsp:include page="forecastIncome.jsp"></jsp:include>
            </div>
            <div id="ref_forecastRestaurantIncome" style="display:none;">
                <jsp:include page="forecastRestaurantIncome.jsp"></jsp:include>
            </div>
            <div class="x_title">
                收入预测
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_forecast_income_list">
                </table>
            </div>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>商品价格调查</h3>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <table class="table table-bordered" id="tb_price_investigation_list">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>成本类</h3>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div id="ref_forecastCost">
                <jsp:include page="forecastCost.jsp"></jsp:include>
            </div>
            <div id="ref_forecastRestaurantCost" style="display:none;">
                <jsp:include page="forecastRestaurantCost.jsp"></jsp:include>
            </div>

            <div class="x_title">
                年运营费
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_forecast_cost_list">
                </table>
            </div>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>参数</h3>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <form id="frm_self_support" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="报酬率">
                            报酬率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control"><fmt:formatNumber value="${mdIncome.rewardRate*100}" type="currency" pattern=".00"/>%</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="行业经营平均利润率">
                            利润率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control">${mdIncome.averageProfitRate*100}%</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="行业经营平均利润率取数说明">
                            取数说明
                        </label>
                        <div class="col-sm-11">
                            <label class="form-control">${mdIncome.averageProfitRateRemark}</label>
                        </div>
                    </div>
                </div>
            </form>
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
        <div class="x_content form-horizontal" id="selfSupportResult">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        估价对象面积
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control" data-name="area">${judgeObject.evaluationArea}</label>
                    </div>
                </div>
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
                    <th>总收入</th>
                    <th>总成本</th>
                    <th>经营利润</th>
                    <th>房地产年净收益</th>
                    <th>房地产收益价格</th>
                </tr>
                </thead>
                <tbody id="selfSupportResultBody">

                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="modal_forecast_year_list" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">预测年度信息</h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tb_forecast_year_list">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="modal_forecast_month_list" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">预测月度信息</h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="yearId">
                <input type="hidden" id="yearType">
                <table class="table table-bordered" id="tb_forecast_month_list">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="modal_forecast_cost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">运营费</h3>
            </div>
            <form id="frm_forecast_cost_detail" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营成本比率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" name="operatingCostRatio" placeholder="经营成本比率"
                                           onblur="selfSupport.computeInitialAmount(this);"
                                           class="form-control x-percent">
                                    <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    onclick="selfSupport.operatingCostItem()"
                                                    data-toggle="tooltip" data-original-title="明细">
                                            <i class="fa fa-edit"></i>
                                            </button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营成本说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingCostRemark" placeholder="经营成本说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营费用比率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingExpensesRatio" placeholder="经营费用比率"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control x-percent">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营费用说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingExpensesRemark" placeholder="经营费用"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营税金及附加比率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingTaxRatio" placeholder="经营税金及附加比率"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control x-percent">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营税金及附加说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingTaxRemark" placeholder="经营税金及附加说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                管理费用比率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="managementCostRatio" placeholder="管理费用比率"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control x-percent">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                管理费用说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="managementCostRemark" placeholder="管理费用说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                财务费用比率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="financialCostRatio" placeholder="财务费用比率"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control x-percent">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                财务费用说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="financialCostRemark" placeholder="财务费用说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                特许权超额利润比率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="excessProfitRatio" placeholder="特许权超额利润比率"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control x-percent">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                特许权超额利润说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="excessProfitRemark" placeholder="特许权超额利润说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="selfSupport.saveForecastCost();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--收入预测--%>
<div id="modal_forecast_income" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">收入预测</h3>
            </div>
            <form id="frm_forecast_income" class="form-horizontal">
                <input type="hidden" name="incomeForecastId">
                <table class="table table-bordered" id="tb_md_income_forecast_analyse_item_list">
                </table>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="selfSupport.createForecastIncomeYear();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="costItemBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">经营成本比率明细</h3>
            </div>
            <form id="frmCostItem" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div style="margin-bottom: 8px;" class="system">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;" data-type="0"
       onchange="selfSupport.importHistory(this);">

<script type="text/html" id="selfSupportResultHtml">
    <tr data-id="{id}">
        <td><label>{beginDate}</label></td>
        <td><label>{endDate}</label></td>
        <td><label data-name="yearCount">{yearCount}</label></td>
        <td><label data-name="incomeTotal">{incomeTotal}</label></td>
        <td><label data-name="costTotal">{costTotal}</label></td>
        <td><label data-name="operatingProfit">{operatingProfit}</label></td>
        <td><label data-name="netProfit">{netProfit}</label></td>
        <td><label data-name="incomePrice">{incomePrice}</label></td>
    </tr>
</script>

<script type="text/javascript">
    var selfSupport = {};
    selfSupport.frmForecastCost = $('#frm_forecast_cost_detail');
    selfSupport.frmForecastIncome = $('#frm_forecast_income');

    //获取list id
    selfSupport.getForecastListId = function (type) {
        if (type == 0)
            return "tb_forecast_income_list";
        if (type == 1)
            return "tb_forecast_cost_list";
    }

    //加载预测列表信息
    selfSupport.loadForecastList = function (type) {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'yearCount', title: '年份数'});
        cols.push({field: 'initialAmount', title: '初始金额'});
        cols.push({
            field: 'growthRate', title: '增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="selfSupport.viewForecastYear(' + row.id + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + selfSupport.getForecastListId(type)).bootstrapTable('destroy');
        TableInit(selfSupport.getForecastListId(type), "${pageContext.request.contextPath}/income/getForecastList", cols, {
            type: type,
            incomeId: $("#frm_income").find('[name=id]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //查看年度预测
    selfSupport.viewForecastYear = function (forecastId) {
        selfSupport.loadForecastYearList(forecastId);
        $('#modal_forecast_year_list').modal('show');
    }

    //加载年度预测列表
    selfSupport.loadForecastYearList = function (forecastId) {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'amount', title: '金额'});
        $("#tb_forecast_year_list").bootstrapTable('destroy');
        TableInit("tb_forecast_year_list", "${pageContext.request.contextPath}/income/getForecastYearList", cols, {
            forecastId: forecastId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //查看年度预测
    selfSupport.viewForecastMonth = function (type, yearId) {
        selfSupport.loadForecastMonthList(type, yearId);
        $("#yearId").val(yearId);
        $("#yearType").val(type);
        $('#modal_forecast_month_list').modal('show');
    }


    //加载月度预测列表信息
    selfSupport.loadForecastMonthList = function (type, yearId) {
        var cols = [];
        cols.push({field: 'accountingSubjectName', title: '会计科目'});
        cols.push({field: 'firstLevelNumber', title: '一级编号'});
        cols.push({field: 'secondLevelNumber', title: '二级编号'});
        cols.push({field: 'month', title: '月度'});
        cols.push({field: 'unitPrice', title: '单价'});
        cols.push({field: 'number', title: '数量'});
        cols.push({field: 'amountMoney', title: '金额'});
        $("#tb_forecast_month_list").bootstrapTable('destroy');
        TableInit("tb_forecast_month_list", "${pageContext.request.contextPath}/income/getForecastMonthList", cols, {
            type: type,
            yearId: yearId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载测算结果数据
    selfSupport.loadCalculationResult = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/income/getDateSectionList",
            type: "get",
            dataType: "json",
            data: {
                incomeId: $("#frm_income").find('[name=id]').val(),
                operationMode: incomeIndex.getOperationMode()
            },
            success: function (result) {
                if (result.rows) {
                    $("#selfSupportResultBody").empty();
                    $(result.rows).each(function (i, item) {
                        var html = $('#selfSupportResultHtml').html();
                        html = html.replace(/{id}/g, item.id).replace(/{beginDate}/g, formatDate(item.beginDate, false));
                        html = html.replace(/{endDate}/g, formatDate(item.endDate, false)).replace(/{yearCount}/g, item.yearCount);
                        html = html.replace(/{incomeTotal}/g, AssessCommon.toString(item.incomeTotal)).replace(/{costTotal}/g, AssessCommon.toString(item.costTotal));
                        html = html.replace(/{operatingProfit}/g, AssessCommon.toString(item.operatingProfit));
                        html = html.replace(/{netProfit}/g, AssessCommon.toString(item.netProfit));
                        html = html.replace(/{incomePrice}/g, AssessCommon.toString(item.incomePrice));
                        $("#selfSupportResultBody").append(html);
                    })
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //加载成本预测列表信息
    selfSupport.loadForecastCostList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'yearCount', title: '年份数'});
        cols.push({
            field: 'operatingCostRatio', title: '经营成本比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'operatingExpensesRatio', title: '经营费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'operatingTaxRatio', title: '经营税金及附加比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'managementCostRatio', title: '管理费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'financialCostRatio', title: '财务费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'excessProfitRatio', title: '特许权超额利润比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="selfSupport.editForecastCost(' + index + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_forecast_cost_list").bootstrapTable('destroy');
        TableInit("tb_forecast_cost_list", "${pageContext.request.contextPath}/income/getForecastList", cols, {
            type: 1,
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }
    selfSupport.editForecastCost = function (index) {
        var row = $("#tb_forecast_cost_list").bootstrapTable('getData')[index];
        selfSupport.frmForecastCost.clearAll();
        selfSupport.frmForecastCost.initForm(row);
        selfSupport.frmForecastCost.find('[name=operatingCostRatio]').attr('data-value', row.operatingCostRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=operatingCostRatio]'));
        selfSupport.frmForecastCost.find('[name=operatingExpensesRatio]').attr('data-value', row.operatingExpensesRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=operatingExpensesRatio]'));
        selfSupport.frmForecastCost.find('[name=operatingTaxRatio]').attr('data-value', row.operatingTaxRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=operatingTaxRatio]'));
        selfSupport.frmForecastCost.find('[name=managementCostRatio]').attr('data-value', row.managementCostRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=managementCostRatio]'));
        selfSupport.frmForecastCost.find('[name=financialCostRatio]').attr('data-value', row.financialCostRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=financialCostRatio]'));
        selfSupport.frmForecastCost.find('[name=operatingProfitRatio]').attr('data-value', row.operatingProfitRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=operatingProfitRatio]'));
        selfSupport.frmForecastCost.find('[name=excessProfitRatio]').attr('data-value', row.excessProfitRatio);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=excessProfitRatio]'));
        selfSupport.frmForecastCost.find("input").attr("readonly","readonly");
        $('#modal_forecast_cost').modal();
    }

    //加载收入预测列表信息
    selfSupport.loadForecastIncomeList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'yearCount', title: '年份数'});
        cols.push({
            field: 'rateIncrease', title: '增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="明细" onclick="selfSupport.editForecastIncome(' + row.id + ');" >明细</a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="selfSupport.viewForecastYear(' + row.id + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_forecast_income_list").bootstrapTable('destroy');
        TableInit("tb_forecast_income_list", "${pageContext.request.contextPath}/income/getForecastList", cols, {
            type: 0,
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //编辑预测信息
    selfSupport.editForecastIncome = function (id) {
        selfSupport.frmForecastIncome.find('[name=incomeForecastId]').val(id);
        selfSupport.loadIncomeForecastItemList();
        $('#modal_forecast_income').modal();
    }
    //加载收入预测明细
    selfSupport.loadIncomeForecastItemList = function () {
        var cols = [];
        cols.push({field: 'name', title: '会计科目/一级编号/二级编号'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({
            field: 'rateIncrease', title: '增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'rateIncreaseExplain', title: '增长率说明'});
        $("#tb_md_income_forecast_analyse_item_list").bootstrapTable('destroy');
        TableInit("tb_md_income_forecast_analyse_item_list", "${pageContext.request.contextPath}/income/loadIncomeForecastItemList", cols, {
            incomeForecastId: selfSupport.frmForecastIncome.find('[name=incomeForecastId]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            pagination: false,
            search: false,
            onLoadSuccess: function (data) {
                $(".tooltips").tooltip();
            }
        });
    }

    //成本明细比率
    selfSupport.operatingCostItem = function(){
        $("#frmCostItem").clearAll();
        var id = $("#frm_forecast_cost_detail").find("input[name='id']").val();
        $(".system").empty();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/getForecastById",
            type: "post",
            dataType: "json",
            data: {id:id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if(result.data.operatingCostItem) {
                        selfSupport.writeHTMLData(result.data.operatingCostItem);
                    }
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
        $('#costItemBox').modal();
    }
    selfSupport.writeHTMLData=function(json) {
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";
            html += "<div class='x-valid'>";
            html += "<div class='col-sm-5'>";
            html +=  n.name ;
            html += "</div>";
            html += "</div>";


            html += "<div class='x-valid'>";
            html += "<label class='col-sm-1 control-label'>" + "比率" + "</label>";
            html += "<div class='col-sm-2'>";
            if(n.ratio){
                html += "<input type='text' required class='form-control x-percent' name='ratio' value='" + AssessCommon.pointToPercent(n.ratio) + "'>";
            }else {
                html += "<input type='text' required class='form-control x-percent' name='ratio' value=''>";
            }     
            html += "</div>";
            html += "</div>";

            html += "</div>";
            $(".system").append(html);
        })
    }
</script>
<%--商品价格调查--%>
<script type="text/javascript">
    $(function () {
        priceInvestigation.prototype.loadDataList();
    });
    var priceInvestigation = function () {

    };
    priceInvestigation.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_price_investigation_list";
            return data;
        },
        loadDataList: function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'price', title: '价格'});
            $("#" + priceInvestigation.prototype.config().table).bootstrapTable('destroy');
            TableInit(priceInvestigation.prototype.config().table, "${pageContext.request.contextPath}/income/getMdIncomePriceInvestigationList", cols, {
                incomeId: incomeIndex.getInComeId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }

    }
</script>


