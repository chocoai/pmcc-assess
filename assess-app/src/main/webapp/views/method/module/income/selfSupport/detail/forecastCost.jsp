<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-11-7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h3>历史数据</h3>
    <div class="clearfix"></div>
</div>
<div class="x_content">
    <div class="form-horizontal">
        <div class="form-group ">
            <label class="col-sm-1 control-label">
                范围
            </label>
            <div class="col-sm-2">
                <select class="form-control" onchange="selfSupportForecast.loadHistoryList(1,$(this).val())">
                    <option value="">全部</option>
                    <option value="1">预测</option>
                    <option value="0">非预测</option>
                </select>
            </div>
        </div>
    </div>
    <table class="table table-bordered" id="tb_history_cost_list">
    </table>
</div>

<div class="x_title">
    <h3>预测分析数据</h3>
    <div class="clearfix"></div>
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_cost_analyse_list">
    </table>
</div>




