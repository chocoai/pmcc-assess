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
        <div class="form-group ">
            <label class="col-sm-1 control-label">
                范围
            </label>
            <div class="col-sm-2">
                <select class="form-control" onchange="selfSupportForecast.loadHistoryList(0,$(this).val())">
                    <option value="">全部</option>
                    <option value="1">预测</option>
                    <option value="0">非预测</option>
                </select>
            </div>
        </div>
    </div>
    <table class="table table-bordered" id="tb_history_income_list">
    </table>
</div>
<div class="x_title">
    历史数据分析
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_income_analyse_list">
    </table>
</div>

<script type="text/javascript">

    var selfSupportForecast = {};

    //获取list id
    selfSupportForecast.getHistoryListId = function (type) {
        if (type == 0)
            return "tb_history_income_list";
        if (type == 1)
            return "tb_history_cost_list";
    }

    //获取预测分析list id
    selfSupportForecast.getForecastAnalyseListId = function (type) {
        if (type == 0)
            return "tb_forecast_income_analyse_list";
        if (type == 1)
            return "tb_forecast_cost_analyse_list";
    }

    //获取list id
    selfSupportForecast.getForecastListId = function (type) {
        if (type == 0)
            return "tb_forecast_income_list";
        if (type == 1)
            return "tb_forecast_cost_list";
    }

    //加载历史列表信息
    selfSupportForecast.loadHistoryList = function (type, bisForecast) {
        var cols = [];
        cols.push({field: 'year', title: '年度'});
        cols.push({field: 'month', title: '月度'});
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
        $("#" + selfSupportForecast.getHistoryListId(type)).bootstrapTable('destroy');
        var queryParam = {
            type: type,
            formType: incomeIndex.getFormType(),
            incomeId: incomeIndex.getInComeId()
        };
        if (bisForecast) {
            queryParam.bisForecast = bisForecast;
        }
        TableInit(selfSupportForecast.getHistoryListId(type), "${pageContext.request.contextPath}/income/getHistoryList", cols, queryParam, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载历史数据分析
    selfSupportForecast.loadForecastAnalyseList = function (type) {
        var cols = [];
        cols.push({field: 'year', title: '年份'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({field: 'quantitativeTrend', title: '数量趋势'});
        cols.push({field: 'univalentTrend', title: '单价趋势'});
        $("#" + selfSupportForecast.getForecastAnalyseListId(type)).bootstrapTable('destroy');
        TableInit(selfSupportForecast.getForecastAnalyseListId(type), "${pageContext.request.contextPath}/income/getForecastAnalyseList", cols, {
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
