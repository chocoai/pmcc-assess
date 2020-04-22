<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-11-7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_title">
    历史或调查数据
</div>
<div class="x_content">
    <div class="form-horizontal">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">
                        范围
                    </label>
                    <div class="col-sm-2">
                        <select class="form-control input-full"
                                onchange="forecastRestaurant.loadHistoryList(0,$(this).val())">
                            <option value="">全部</option>
                            <option value="1">预测</option>
                            <option value="0">非预测</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered" id="tb_history_restaurant_income_list">
    </table>
</div>
<div class="x_title">
    历史数据分析
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_restaurant_income_analyse_list">
    </table>
</div>

<div id="divBoxAnalyseItemData_b" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <table class="table table-bordered" id="analyseItemList_b">
                        <!-- cerare document add ajax data-->
                    </table>
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

<script type="text/javascript">
    var forecastRestaurant = {};

    //获取list id
    forecastRestaurant.getHistoryListId = function (type) {
        if (type == 0) return "tb_history_restaurant_income_list";
        if (type == 1) return "tb_history_restaurant_cost_list";
    }

    //获取预测分析list id
    forecastRestaurant.getForecastAnalyseListId = function (type) {
        if (type == 0) return "tb_forecast_restaurant_income_analyse_list";
        if (type == 1) return "tb_forecast_restaurant_cost_analyse_list";
    }

    //获取list id
    forecastRestaurant.getForecastListId = function (type) {
        if (type == 0) return "tb_forecast_income_list";
        if (type == 1) return "tb_forecast_cost_list";
    }


    //获取类型key
    forecastRestaurant.getHistoryTypeKey = function (type) {
        if (type == 0) return AssessDicKey.mdIncomeHistoryTypeIncome;
        if (type == 1) return AssessDicKey.mdIncomeHistoryTypeCost;
    }

    //加载历史列表信息
    forecastRestaurant.loadHistoryList = function (type, bisForecast) {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'accountingSubjectName', title: '会计科目', formatter: function (value, row, index) {
                if (row.bisForecast) {
                    value += '<i class="green fa fa-tag"></i>';
                }
                return value;
            }
        });
        cols.push({field: 'firstLevelNumber', title: '一级编号'});
        cols.push({field: 'secondLevelNumber', title: '二级编号'});
        cols.push({field: 'unit', title: '单位'});
        cols.push({field: 'unitPrice', title: '单价'});
        cols.push({field: 'number', title: '数量'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({field: 'deprecitionRoyalty', title: '房屋折旧与使用费'});
        $("#" + forecastRestaurant.getHistoryListId(type)).bootstrapTable('destroy');
        var queryParam = {
            type: type,
            formType: incomeIndex.getFormType(),
            incomeId: incomeIndex.getInComeId()
        };
        if (bisForecast) {
            queryParam.bisForecast = bisForecast;
        }
        TableInit(forecastRestaurant.getHistoryListId(type), "${pageContext.request.contextPath}/income/getHistoryList", cols, queryParam, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载历史数据分析
    forecastRestaurant.loadForecastAnalyseList = function (type) {
        var cols = [];
        cols.push({field: 'year', title: '年份'});
        cols.push({field: 'sourceType', title: '类型'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                if (row.amountMoney != null) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看明细" onclick="forecastRestaurant.showItemData(' + row.id + ')"><i class="fa fa-search"></i></a>';
                    str += '</div>';
                    return str;
                }
            }
        });
        $("#" + forecastRestaurant.getForecastAnalyseListId(type)).bootstrapTable('destroy');
        TableInit(forecastRestaurant.getForecastAnalyseListId(type), "${pageContext.request.contextPath}/income/getForecastAnalyseList", cols, {
            type: type,
            formType: incomeIndex.getFormType(),
            bisParticipateIn: true,
            incomeId: incomeIndex.getInComeId()
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

    forecastRestaurant.showItemData = function (id) {
        forecastRestaurant.loadForecastAnalyseItemList(id);
        $('#divBoxAnalyseItemData_b').modal("show");
    }
    //加载预测分析明细
    forecastRestaurant.loadForecastAnalyseItemList = function (id) {
        var cols = [];
        cols.push({field: 'name', title: '会计科目/一级编号/二级编号'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({field: 'number', title: '数量'});
        cols.push({field: 'moneyTrend', title: '金额趋势'});
        cols.push({field: 'quantitativeTrend', title: '数量趋势'});
        $("#analyseItemList_b").bootstrapTable('destroy');
        TableInit("analyseItemList_b", "${pageContext.request.contextPath}/income/getForecastAnalyseItemList", cols, {
            forecastAnalyseId: id,
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


    //加载历史数据分析
    forecastRestaurant.loadCostForecastAnalyseList = function (type) {
        var cols = [];
        cols.push({field: 'year', title: '年份'});
        cols.push({field: 'sourceType', title: '类型'});
        cols.push({field: 'amountMoney', title: '成本金额'});
        cols.push({
            field: 'costRatio', title: '成本比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'earnedProfit', title: '经营利润'});
        cols.push({
            field: 'earnedProfitRatio', title: '经营利润比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'operatingExpensesRatio', title: '经营费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'operatingTaxRatio', title: '税金及附加比率', formatter: function (value, row, index) {
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
        $("#tb_forecast_restaurant_cost_analyse_list").bootstrapTable('destroy');
        TableInit("tb_forecast_restaurant_cost_analyse_list", "${pageContext.request.contextPath}/income/getForecastAnalyseList", cols, {
            type: type,
            formType: incomeIndex.getFormType(),
            bisParticipateIn: true,
            incomeId: incomeIndex.getInComeId()
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
</script>