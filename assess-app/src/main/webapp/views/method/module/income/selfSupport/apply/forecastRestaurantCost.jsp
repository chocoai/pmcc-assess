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
        <div class="row form-group ">
            <div class="col-md-12">
                <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">
                范围
            </label>
            <div class="col-sm-1">
                <select class="form-control input-full" name="bisForecast">
                    <option value="">全部</option>
                    <option value="1">预测</option>
                    <option value="0">非预测</option>
                </select>
            </div>
            <label class="col-sm-1 control-label">
                二级编号
            </label>
            <div class="col-sm-1">
                <input type="text" name="secondLevelNumber" class="form-control input-full">
            </div>
            <div class="col-sm-6">
                <div class="btn-group">
                    <button type="button" class="btn btn-info btn-sm" onclick="forecastRestaurant.loadHistoryList(1,this);">
                        查询
                    </button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal" onclick="forecastRestaurant.addHistory(1);">
                        新增
                    </button>
                </div>
                <div class="dropdown" style="display: inline;margin-left: 5px;">
                    <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown"
                            aria-expanded="false">
                        导入数据
                    </button>
                    <div class="dropdown-menu" role="menu">
                        <a href="javascript://" class="dropdown-item"
                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftMethodIncomeHistory);">下载模板</a>
                        <a href="javascript://;" class="dropdown-item"
                           onclick="$('#ajaxFileUpload').val('').attr('data-type',1).trigger('click');">导入数据</a>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown"
                            aria-expanded="false">
                        添加到预测
                    </button>
                    <ul class="dropdown-menu" role="menu" id="ulForecastRestaurantAnalyseCost">

                    </ul>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary btn-sm" data-toggle="modal"
                            onclick="forecastRestaurant.forecastToHistory(1);">
                        取消预测
                    </button>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary btn-sm" data-toggle="modal"
                            onclick="forecastRestaurant.startAnalyse(1);">
                        开始分析
                    </button>
                </div>
            </div>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered" id="tb_history_restaurant_cost_list">
    </table>
</div>

<div class="x_title">
    历史数据分析
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_restaurant_cost_analyse_list">
    </table>
</div>