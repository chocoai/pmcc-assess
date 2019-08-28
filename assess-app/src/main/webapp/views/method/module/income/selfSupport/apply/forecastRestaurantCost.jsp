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
            <div class="col-sm-1">
                <select class="form-control" name="bisForecast">
                    <option value="">全部</option>
                    <option value="1">预测</option>
                    <option value="0">非预测</option>
                </select>
            </div>
            <label class="col-sm-1 control-label">
                二级编号
            </label>
            <div class="col-sm-1">
                <input type="text" name="secondLevelNumber" class="form-control">
            </div>
            <div class="col-sm-6">
                <div class="btn-group">
                    <button class="btn btn-primary" onclick="forecastRestaurant.loadHistoryList(1,this);">
                        查询
                    </button>
                    <button class="btn btn-success" data-toggle="modal" onclick="forecastRestaurant.addHistory(1);">
                        新增
                    </button>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-expanded="false">
                        导入数据
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript://"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftMethodIncomeHistory);">下载模板</a>
                        </li>
                        <li><a href="javascript://;"
                               onclick="$('#ajaxFileUpload').val('').attr('data-type',1).trigger('click');">导入数据</a>
                        </li>
                    </ul>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-expanded="false">
                        添加到预测
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu" id="ulForecastRestaurantAnalyseCost">

                    </ul>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary" data-toggle="modal"
                            onclick="forecastRestaurant.forecastToHistory(1);">
                        取消预测
                    </button>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary" data-toggle="modal"
                            onclick="forecastRestaurant.startAnalyse(1);">
                        开始分析
                    </button>
                </div>
            </div>

        </div>
    </div>
    <table class="table table-bordered" id="tb_history_restaurant_cost_list">
    </table>
</div>

<div class="x_title">
    预测分析数据
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_restaurant_cost_analyse_list">
    </table>
</div>