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
            <form class="form-horizontal">
                <button type="button" class="btn btn-success"
                        onclick="priceInvestigation.prototype.showModel()"
                        data-toggle="modal" href="#divBox"> 新增
                </button>
            </form>
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
                            <div class="input-group">
                                <input type="text" required class="form-control x-percent" name="rewardRate"
                                       placeholder="报酬率"
                                       data-value="${mdIncome.rewardRate}" onblur="selfSupport.computePrice();">
                                <span class="input-group-btn">
                              <input type="button" class="btn btn-primary" value="报酬率测算"
                                     onclick="selfSupport.getRewardRate(this);"/>
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="行业经营平均利润率">
                            利润率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="averageProfitRate" placeholder="行业经营平均利润率"
                                   data-value="${mdIncome.averageProfitRate}" class="form-control x-percent"
                                   onblur="selfSupport.computeOperatingProfit();" required="required">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="行业经营平均利润率取数说明">
                            取数说明
                        </label>
                        <div class="col-sm-11">
                            <textarea name="averageProfitRateRemark" class="form-control"
                                      placeholder="行业经营平均利润率取数说明">${mdIncome.averageProfitRateRemark}</textarea>
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
                    <th>收入</th>
                    <th>成本</th>
                    <th>经营利润</th>
                    <th>房地产年净收益</th>
                    <th>年期修正系数(h)</th>
                    <th>收益现值系数(k)</th>
                    <th>房地产收益价格</th>
                </tr>
                </thead>
                <tbody id="selfSupportResultBody"></tbody>
            </table>
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
            <div class="modal-body">
                <form id="frm_forecast_income" class="form-horizontal">
                    <input type="hidden" name="incomeForecastId">
                    <div class="col-sm-3">
                        <button type="button" class="btn btn-success"
                                onclick="selfSupport.showForecastIncomeItemModel()"
                                data-toggle="modal" href="#divBox"> 新增
                        </button>
                        <button type="button" class="btn btn-primary"
                                onclick="selfSupport.forecastIncomeItemQuoteData()"> 引用数据
                        </button>
                    </div>
                    <table class="table table-bordered" id="tb_md_income_forecast_analyse_item_list">
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="selfSupport.createForecastIncomeYear();">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="modal_forecast_income_item" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">收入预测明细</h3>
            </div>
            <div class="modal-body">
                <form id="frm_forecast_income_item" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                会计科目<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <select name="accountingSubject" class="form-control" required="required"></select>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                一级编号
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="firstLevelNumber" placeholder="一级编号" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                二级编号
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="secondLevelNumber" placeholder="二级编号" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="amountMoney" placeholder="金额" data-rule-number="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                增长率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="rateIncrease" placeholder="增长率" class="form-control x-percent"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                增长率说明
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="rateIncreaseExplain" placeholder="增长率说明" class="form-control">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="selfSupport.saveForecastIncomeItem();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal_same_name_item" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">同类历史数据</h3>
            </div>
            <div class="modal-body">
                <form id="frm_same_name" class="form-horizontal">
                    <input type="hidden" name="historyId">
                    <table class="table table-bordered" id="tb_md_same_name_item_list">
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="selfSupport.affirmQuoteMoney();">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>
<%--成本预测数据--%>
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
            <form id="frm_forecast_cost" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <input type="hidden" name="sectionId">
                <input type="hidden" name="incomeTotal">
                <input type="hidden" name="initialAmount">
                <input type="hidden" name="operatingCost">
                <input type="hidden" name="operatingExpenses">
                <input type="hidden" name="operatingTax">
                <input type="hidden" name="managementCost">
                <input type="hidden" name="financialCost">
                <input type="hidden" name="operatingProfit">
                <input type="hidden" name="excessProfit">
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
                <button class="btn btn-success" data-toggle="modal"
                        onclick="selfSupport.addHistory($('#yearType').val(),'frm_forecast_month','modal_forecast_month');">
                    新增
                </button>
                <table class="table table-bordered" id="tb_forecast_month_list">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="modal_forecast_month" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">月度信息</h3>
            </div>
            <div class="modal-body">
                <form id="frm_forecast_month" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                会计科目<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <select name="accountingSubject" class="form-control" required="required"></select>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                一级编号<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="firstLevelNumber" placeholder="一级编号" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                二级编号<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="secondLevelNumber" placeholder="一级编号" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                月度<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="month" placeholder="月度"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                单价<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="unitPrice" placeholder="单价"
                                       onblur="selfSupport.computeMoney('frm_forecast_month');"
                                       data-rule-number="true" class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                数量<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="number" placeholder="数量"
                                       onblur="selfSupport.computeMoney('frm_forecast_month');"
                                       data-rule-digits="true" class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="amountMoney" placeholder="金额"
                                       data-rule-number="true" class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="selfSupport.saveForecastMonth();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal_price_investigation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">商品调查价格</h3>
            </div>
            <form id="frm_price_investigation" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            商品名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            价格<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="price" class="form-control" data-rule-number="true"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="priceInvestigation.prototype.saveData()">
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
                                <%--<div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            添加明细<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="btn btn-xs btn-success"
                                                 onclick="selfSupport.appendHTML('',this)"><i
                                                    class="fa fa-plus"></i></div>
                                        </div>
                                    </div>
                                </div>--%>
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
                    <button type="button" class="btn btn-primary" onclick="selfSupport.saveCostItem()">
                        保存
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
        <td>
            <input type="hidden" data-name="rentalGrowthRate" value="{rentalGrowthRate}">
            <label>{beginDate}</label>
        </td>
        <td><label>{endDate}</label></td>
        <td><label data-name="yearCount">{yearCount}</label></td>
        <td><label data-name="incomeTotal">{incomeTotal}</label></td>
        <td><label data-name="costTotal">{costTotal}</label></td>
        <td><label data-name="operatingProfit">{operatingProfit}</label></td>
        <td><label data-name="netProfit">{netProfit}</label></td>
        <td><label data-name="correctionFactor">{correctionFactor}</label></td>
        <td><label data-name="presentValueFactor">{presentValueFactor}</label></td>
        <td><label data-name="incomePrice">{incomePrice}</label></td>
    </tr>
</script>

<script type="text/javascript">
    var selfSupport = {};
    selfSupport.frmForecastIncome = $('#frm_forecast_income');
    selfSupport.frmForecastIncomeItem = $('#frm_forecast_income_item');
    selfSupport.frmForecastCost = $('#frm_forecast_cost');

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
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecastIncomeItem(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="selfSupport.deleteForecastIncomeItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
               // str += '<a class="btn btn-xs btn-primary tooltips" data-placement="top" data-original-title="引用历史金额" onclick="selfSupport.showSameNameItemModel(' + row.id + ')">引用历史金额</a>';
                str += '</div>';
                return str;
            }
        });
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
    //新增收入预测明细
    selfSupport.showForecastIncomeItemModel = function () {
        selfSupport.frmForecastIncomeItem.clearAll();
        AssessCommon.loadDataDicByKey(AssessDicKey.mdIncomeHistoryTypeIncome, "", function (html, data) {
            $("#frm_forecast_income_item").find('[name=accountingSubject]').empty().append(html);
        })
        $('#modal_forecast_income_item').modal();
    }
    //保存收入预测明细
    selfSupport.saveForecastIncomeItem = function () {
        if (!selfSupport.frmForecastIncomeItem.valid()) {
            return false;
        }
        var data = formSerializeArray(selfSupport.frmForecastIncomeItem);
        data.incomeForecastId = selfSupport.frmForecastIncome.find('[name=incomeForecastId]').val()
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveIncomeForecastItem",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    selfSupport.loadIncomeForecastItemList();
                    $('#modal_forecast_income_item').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //编辑收入预测明细
    selfSupport.editForecastIncomeItem = function (index) {
        var row = $("#tb_md_income_forecast_analyse_item_list").bootstrapTable('getData')[index];
        selfSupport.frmForecastIncomeItem.clearAll();
        selfSupport.frmForecastIncomeItem.initForm(row);
        AssessCommon.loadDataDicByKey(AssessDicKey.mdIncomeHistoryTypeIncome, row.accountingSubject, function (html, data) {
            $("#frm_forecast_income_item").find('[name=accountingSubject]').empty().append(html);
        })
        selfSupport.frmForecastIncomeItem.find('[name=rateIncrease]').attr('data-value', row.rateIncrease);
        AssessCommon.elementParsePercent(selfSupport.frmForecastIncomeItem.find('[name=rateIncrease]'));
        $('#modal_forecast_income_item').modal();
    }
    //删除收入预测明细
    selfSupport.deleteForecastIncomeItem = function (id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteForecastIncomeItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        selfSupport.loadIncomeForecastItemList();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }
    //预测明细引用数据
    selfSupport.forecastIncomeItemQuoteData = function (id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/forecastIncomeItemQuoteData",
            type: "post",
            dataType: "json",
            data: {
                incomeId: incomeIndex.getInComeId(),
                formType: incomeIndex.getFormType(),
                incomeForecastId: selfSupport.frmForecastIncome.find('[name=incomeForecastId]').val()
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('引用数据成功');
                    selfSupport.loadIncomeForecastItemList();
                }
                else {
                    Alert("删除数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //相同名称历史数据modal
    selfSupport.showSameNameItemModel = function (historyId) {
        $("#frm_same_name").clearAll();
        if (incomeIndex.getFormType() == 0) {
            selfSupport.loadSameNameListDefault(historyId);
        } else {
            selfSupport.loadSameNameListRepast(historyId);
        }
        $("#frm_same_name").find("[name=historyId]").val(historyId);
        $('#modal_same_name_item').modal();
    }
    //默认表单加载同类物品历史数据信息
    selfSupport.loadSameNameListDefault = function (historyId) {
        var cols = [];
        cols.push({field: 'year', title: '年度'});
        cols.push({field: 'month', title: '月度'});
        cols.push({field: 'amountMoney', title: '金额'});
        $("#tb_md_same_name_item_list").bootstrapTable('destroy');
        TableInit("tb_md_same_name_item_list", "${pageContext.request.contextPath}/income/getSameNameHistoryList", cols, {
            historyId: historyId,
            formType: incomeIndex.getFormType(),
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        }, true);
    }

    //餐饮加载同类物品历史数据信息
    selfSupport.loadSameNameListRepast = function (historyId) {
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
        cols.push({field: 'amountMoney', title: '金额'});
        $("#tb_md_same_name_item_list").bootstrapTable('destroy');
        TableInit("tb_md_same_name_item_list", "${pageContext.request.contextPath}/income/getSameNameHistoryList", cols, {
            historyId: historyId,
            type: 0,
            formType: incomeIndex.getFormType(),
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        }, true);
    }

    //确认引用历史数据金额
    selfSupport.affirmQuoteMoney = function (type) {
        var rows = $("#tb_md_same_name_item_list").bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var arrayId = [];
            $.each(rows, function (i, row) {
                arrayId.push(row.id);
            })
            console.log(arrayId)
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/affirmQuoteMoney",
                type: "post",
                dataType: "json",
                data: {
                    ids: arrayId.join(),
                    historyId: $("#frm_same_name").find("[name=historyId]").val()
                },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        $('#modal_same_name_item').modal('hide');
                        selfSupport.loadIncomeForecastItemList();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
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
        $('#modal_forecast_cost').modal();
    }

    //生成年度收入预测
    selfSupport.createForecastIncomeYear = function () {
        if (!selfSupport.frmForecastIncome.valid()) {
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/createForecastIncomeYear",
            type: "post",
            dataType: "json",
            data: {
                incomeForecastId: selfSupport.frmForecastIncome.find('[name=incomeForecastId]').val()
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    selfSupport.loadForecastIncomeList();
                    selfSupport.loadCalculationResult();
                    $('#modal_forecast_income').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //保存预测信息
    selfSupport.saveForecastCost = function () {
        if (!selfSupport.frmForecastCost.valid()) {
            return false;
        }
        var data = formSerializeArray(selfSupport.frmForecastCost);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveForecast",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    selfSupport.loadForecastCostList();
                    selfSupport.loadCalculationResult();
                    $('#modal_forecast_cost').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
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
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecastIncome(' + row.id + ');" ><i class="fa fa-edit fa-white"></i></a>';
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
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecastCost(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
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


    //删除月度预测信息
    selfSupport.delForecastMonth = function (id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteForecastMonth",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        selfSupport.loadForecastMonthList($("#yearType").val(), $("#yearId").val());
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    selfSupport.editForecastMonth = function (index, type) {
        var row = $("#tb_forecast_month_list").bootstrapTable('getData')[index];
        $("#frm_forecast_month").clearAll();
        $("#frm_forecast_month").initForm(row);
        AssessCommon.loadDataDicByKey(selfSupport.getHistoryTypeKey(row.type), row.accountingSubject, function (html, data) {
            $("#frm_forecast_month").find('[name=accountingSubject]').empty().append(html);
        })
        AssessCommon.loadDataDicByPid(row.accountingSubject, row.firstLevelNumber, function (html, data) {
            $("#frm_forecast_month").find('[name=firstLevelNumber]').empty().append(html);
        })
        AssessCommon.loadDataDicByPid(row.firstLevelNumber, row.secondLevelNumber, function (html, data) {
            $("#frm_forecast_month").find('[name=secondLevelNumber]').empty().append(html);
        })
        $('#modal_forecast_month').modal();
    }

    //保存月度预测信息
    selfSupport.saveForecastMonth = function () {
        if (!$("#frm_forecast_month").valid()) {
            return false;
        }
        var data = formParams("frm_forecast_month");
        data.yearId = $("#yearId").val();
        data.type = $("#yearType").val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveForecastMonth",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    selfSupport.loadForecastMonthList(data.type, data.yearId);
                    $('#modal_forecast_month').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
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
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecastMonth(' + index + ',' + type + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="selfSupport.delForecastMonth(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
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

    //历史数据导入
    selfSupport.importHistory = function (_this) {
        switch (incomeIndex.getFormType()) {
            case '0':
                selfSupportForecast.importHistory(_this);
                break;
            case '1':
                forecastRestaurant.importHistory(_this);
                break;
        }
    }

    //获取报酬率
    selfSupport.getRewardRate = function (_this) {
        rewardRateFunc.calculation(function (result) {
            if (result) {
                var element = $(_this).closest('.input-group').find(':text');
                element.val(result);
                AssessCommon.elementParsePoint(element);
                selfSupport.computeOperatingProfit();
            }
        })
    }

    //加载测算结果数据
    selfSupport.loadCalculationResult = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/income/getDateSectionList",
            type: "get",
            dataType: "json",
            data: {
                incomeId: incomeIndex.getInComeId(),
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
                        html = html.replace(/{rentalGrowthRate}/g, AssessCommon.toString(item.rentalGrowthRate));
                        html = html.replace(/{netProfit}/g, AssessCommon.toString(item.netProfit)).replace(/{correctionFactor}/g, AssessCommon.toString(item.correctionFactor));
                        html = html.replace(/{presentValueFactor}/g, AssessCommon.toString(item.presentValueFactor)).replace(/{incomePrice}/g, AssessCommon.toString(item.incomePrice));
                        $("#selfSupportResultBody").append(html);
                    })
                    selfSupport.computeOperatingProfit();
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //获取表单内容
    selfSupport.getData = function () {
        var formData = {};
        formData.mdIncome = {};
        formData.mdIncome.id = incomeIndex.getInComeId();
        formData.mdIncome.houseRemainingYear = $("#frm_income").find('[name=houseRemainingYear]').val();
        formData.mdIncome.landRemainingYear = $("#frm_income").find('[name=landRemainingYear]').val();
        formData.mdIncome.name = $("#frm_income").find('[name=name]').val();
        formData.mdIncome.area = $("#selfSupportResult").find('[data-name=area]').text();
        formData.mdIncome.price = $("#selfSupportResult").find('[data-name=price]').text();
        formData.mdIncome.operationMode = 0;
        formData.mdIncome.formType = incomeIndex.getFormType();
        formData.mdIncome.averageProfitRate = $("#frm_self_support").find('[name=averageProfitRate]').attr('data-value');
        formData.mdIncome.averageProfitRateRemark = $("#frm_self_support").find('[name=averageProfitRateRemark]').val();
        formData.mdIncome.rewardRate = $("#frm_self_support").find('[name=rewardRate]').attr('data-value');

        formData.dateSectionList = [];
        $("#selfSupportResultBody").find('tr').each(function () {
            var section = {};
            section.id = $(this).attr('data-id');
            section.operatingProfit = $(this).find('[data-name=operatingProfit]').text();
            section.netProfit = $(this).find('[data-name=netProfit]').text();
            section.correctionFactor = $(this).find('[data-name=correctionFactor]').text();
            section.presentValueFactor = $(this).find('[data-name=presentValueFactor]').text();
            section.incomePrice = $(this).find('[data-name=incomePrice]').text();
            formData.dateSectionList.push(section);
        })
        return formData;
    }

    //自动计算初始金额
    selfSupport.computeInitialAmount = function (_this) {
        var form = $(_this).closest('form');
        //总收入
        var incomeTotal = form.find('[name=incomeTotal]').val();
        var initialAmount = 0;
        var operatingCostRatio = AssessCommon.percentToPoint(form.find('[name=operatingCostRatio]').val());
        if (operatingCostRatio) {
            form.find('[name=operatingCost]').val(accMul(operatingCostRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(operatingCostRatio, incomeTotal).toFixed(2));
        }
        var operatingExpensesRatio = AssessCommon.percentToPoint(form.find('[name=operatingExpensesRatio]').val());
        if (operatingExpensesRatio) {
            form.find('[name=operatingExpenses]').val(accMul(operatingExpensesRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(operatingExpensesRatio, incomeTotal).toFixed(2));
        }
        var operatingTaxRatio = AssessCommon.percentToPoint(form.find('[name=operatingTaxRatio]').val());
        if (operatingTaxRatio) {
            form.find('[name=operatingTax]').val(accMul(operatingTaxRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(operatingTaxRatio, incomeTotal).toFixed(2));
        }
        var managementCostRatio = AssessCommon.percentToPoint(form.find('[name=managementCostRatio]').val());
        if (managementCostRatio) {
            form.find('[name=managementCost]').val(accMul(managementCostRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(managementCostRatio, incomeTotal).toFixed(2));
        }
        var financialCostRatio = AssessCommon.percentToPoint(form.find('[name=financialCostRatio]').val());
        if (financialCostRatio) {
            form.find('[name=financialCost]').val(accMul(financialCostRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(financialCostRatio, incomeTotal).toFixed(2));
        }
        var operatingProfitRatio = AssessCommon.percentToPoint(form.find('[name=operatingProfitRatio]').val());
        if (operatingProfitRatio) {
            form.find('[name=operatingProfit]').val(accMul(operatingProfitRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(operatingProfitRatio, incomeTotal).toFixed(2));
        }
        var excessProfitRatio = AssessCommon.percentToPoint(form.find('[name=excessProfitRatio]').val());
        if (excessProfitRatio) {
            form.find('[name=excessProfit]').val(accMul(excessProfitRatio, incomeTotal).toFixed(2));
            initialAmount += parseFloat(accMul(excessProfitRatio, incomeTotal).toFixed(2));
        }
        form.find('[name=initialAmount]').val(initialAmount.toFixed(2));
    }

    //成本明细比率
    selfSupport.operatingCostItem = function(){
        $("#frmCostItem").clearAll();
        var id = $("#frm_forecast_cost").find("input[name='id']").val();
        $("#frmCostItem").find("input[name='id']").val(id);
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

    selfSupport.appendHTML=function() {
        var html = "<div class='form-group' >";
        html += "<div class='x-valid'>";
        html += "<label class='col-sm-1 control-label'>" + "一级编号" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control' name='firstLevelNumber'>";
        html += "</div>";
        html += "</div>";

        html += "<div class='x-valid'>";
        html += "<label class='col-sm-1 control-label'>" + "二级编号" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control' name='secondLevelNumber'>";
        html += "</div>";
        html += "</div>";

        html += "<div class='x-valid'>";
        html += "<label class='col-sm-1 control-label'>" + "比率" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control x-percent' name='ratio'>";
        html += "</div>";
        html += "</div>";

        html += "<div class='x-valid'>";
        html += " <div class='col-sm-2'>";
        html += "<input class='btn btn-warning' type='button' value='X' onclick='selfSupport.cleanHTMLData(this)'>" + "</span>";
        html += "</div>";
        html += "</div>";

        html += "</div>";

        $(".system").append(html);
    }

    selfSupport.cleanHTMLData=function(item) {
        var value = "";
        $(item).parent().parent().parent().remove();
    }

    selfSupport.saveCostItem = function () {
        if (!$("#frmCostItem").valid()) {
            return false;
        }
        //var data = formParams("frmCostItem");
        var id=$("#frmCostItem").find("input[name='id']").val();
        var operatingCostItem = [];
        $("#frmCostItem").find('.system').find('.form-group').each(function () {
            var item = {};
            var amountMoney = $(this).find('[name^=amountMoney]').val();
            var total = $(this).find('[name^=total]').val();
            var firstLevelNumber = $(this).find('[name^=firstLevelNumber]').val();
            var secondLevelNumber = $(this).find('[name^=secondLevelNumber]').val();
            var ratio = AssessCommon.percentToPoint($(this).find('[name^=ratio]').val());
            if(firstLevelNumber&&secondLevelNumber&&ratio) {
                item.firstLevelNumber = firstLevelNumber;
                item.secondLevelNumber = secondLevelNumber;
                item.ratio = ratio;
                item.amountMoney = amountMoney;
                item.total = total;
                operatingCostItem.push(item);
            }
        });
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveOperatingCostItem",
            type: "post",
            dataType: "json",
            data: {
                id:id,
                operatingCostItem:JSON.stringify(operatingCostItem)
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var costTotal = 0;
                    var incomeTotal = 0;
                    $.each(operatingCostItem, function (i, n) {
                        costTotal += Number(n.amountMoney)*Number(n.ratio);
                        incomeTotal = Number(n.total);
                    })
                    if(costTotal!=0&&incomeTotal!=0) {
                        var result = AssessCommon.pointToPercent((costTotal/incomeTotal).toFixed(4));
                        console.log(result+"===1")
                        console.log(costTotal+"===2")
                        console.log(incomeTotal)
                        $("#frm_forecast_cost").find("[name=operatingCostRatio]").val(result);
                        var incomeTotal = $("#frm_forecast_cost").find('[name=incomeTotal]').val();
                        $("#frm_forecast_cost").find('[name=operatingCostRatio]').attr('data-value',result);
                        $("#frm_forecast_cost").find('[name=operatingCost]').val(accMul(result, incomeTotal).toFixed(2));
                        selfSupport.computeInitialAmount("#frm_forecast_cost");
                    }
                    $('#costItemBox').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    selfSupport.writeHTMLData=function(json) {
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";
            html += "<input type='hidden'  name='amountMoney'  value='" + n.amountMoney + "'>";
            html += "<input type='hidden'  name='total'  value='" + n.total + "'>";
            html += "<div class='x-valid'>";
            html += "<label class='col-sm-1 control-label'>" + "一级编号" + "</label>";
            html += "<div class='col-sm-2'>";
            html += "<input type='text' required class='form-control' name='firstLevelNumber'  value='" + n.firstLevelNumber + "'>";
            html += "</div>";
            html += "</div>";

            html += "<div class='x-valid'>";
            html += "<label class='col-sm-1 control-label'>" + "二级编号" + "</label>";
            html += "<div class='col-sm-2'>";
            html += "<input type='text' required class='form-control' name='secondLevelNumber' value='" +n.secondLevelNumber + "'>";
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
<%--测算--%>
<script type="text/javascript">
    //计算经营利润与净收益
    selfSupport.computeOperatingProfit = function () {
        var averageProfitRate = $("#frm_self_support").find('[name=averageProfitRate]').attr('data-value');//行业平均利润率
        if (!AssessCommon.isNumber(averageProfitRate)) return false;
        averageProfitRate = parseFloat(averageProfitRate);
        $("#selfSupportResultBody").find('tr').each(function () {
            var incomeTotal = $(this).find('[data-name=incomeTotal]').text();
            var costTotal = $(this).find('[data-name=costTotal]').text();
            if (AssessCommon.isNumber(incomeTotal) && AssessCommon.isNumber(costTotal)) {
                incomeTotal = parseFloat(incomeTotal);
                costTotal = parseFloat(costTotal);
                var operatingProfit = (incomeTotal * averageProfitRate).toFixed(2);
                $(this).find('[data-name=operatingProfit]').text(operatingProfit);
                //净收益
                var netProfit = incomeTotal - costTotal - operatingProfit;
                $(this).find('[data-name=netProfit]').text(netProfit.toFixed(2));
            }
        })

        selfSupport.computePrice();
    }

    //计算年期修正系数[(h)=(1-((1+g)/(1+r))^n]、收益现值系数[(k)=h/(r-g)]、房地产收益价格[房地产收益价格*收益现值系数]
    selfSupport.computePrice = function () {
        var r = $("#frm_self_support").find('[name=rewardRate]').attr('data-value');//报酬率
        if (!AssessCommon.isNumber(r)) return false;
        r = parseFloat(r);
        var incomePriceTotal = 0;//收益价格合计
        var currYearCount = 1;//当前年份数
        $("#selfSupportResultBody").find('tr').each(function () {
            var n = $(this).find('[data-name=yearCount]').text();
            if (!AssessCommon.isNumber(n)) return true;
            n = parseInt(n);
            var g = $(this).find('[data-name=rentalGrowthRate]').val();
            if (!AssessCommon.isNumber(g)) g = 0;
            g = parseFloat(g);//增长率
            var h = (1 - Math.pow((1 + g), currYearCount) / Math.pow((1 + r), n)).toFixed(6);//年期修正系数
            var k = (h / (r - g)).toFixed(6);//收益现值系数
            $(this).find('[data-name=correctionFactor]').text(h);
            $(this).find('[data-name=presentValueFactor]').text(k);
            var netProfit = $(this).find('[data-name=netProfit]').text();
            if (!AssessCommon.isNumber(netProfit)) return true;
            netProfit = parseFloat(netProfit);
            var incomePrice = (netProfit * k).toFixed(2);
            incomePriceTotal = incomePriceTotal + parseFloat(incomePrice);
            $(this).find('[data-name=incomePrice]').text(incomePrice);//收益价格
            currYearCount += n;
        })
        //计算委估对象单价 （单价=收益价格合计\委估对象面积）
        var area = $("#selfSupportResult").find('[data-name=area]').text();
        if (!AssessCommon.isNumber(area)) return true;
        area = parseFloat(area);
        $("#selfSupportResult").find('[data-name=price]').text((incomePriceTotal / area).toFixed(2));
    }
</script>
<%--商品价格调查--%>
<script type="text/javascript">
    $(function () {
        priceInvestigation.prototype.loadDataList(incomeIndex.getInComeId());
    });
    var priceInvestigation = function () {

    };
    priceInvestigation.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_price_investigation_list";
            data.box = "modal_price_investigation";
            data.frm = "frm_price_investigation";
            return data;
        },
        loadDataList: function (incomeId) {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'price', title: '价格'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="priceInvestigation.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="priceInvestigation.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + priceInvestigation.prototype.config().table).bootstrapTable('destroy');
            TableInit(priceInvestigation.prototype.config().table, "${pageContext.request.contextPath}/income/getMdIncomePriceInvestigationList", cols, {
                incomeId: incomeId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/income/removeMdIncomePriceInvestigation",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            priceInvestigation.prototype.loadDataList(incomeIndex.getInComeId());
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + priceInvestigation.prototype.config().frm).clearAll();
            $('#' + priceInvestigation.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + priceInvestigation.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(priceInvestigation.prototype.config().frm);
            data.incomeId = incomeIndex.getInComeId();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/saveMdIncomePriceInvestigation",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + priceInvestigation.prototype.config().box).modal('hide');
                        priceInvestigation.prototype.loadDataList(incomeIndex.getInComeId());
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/income/getMdIncomePriceInvestigationById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + priceInvestigation.prototype.config().frm).clearAll();
                        $("#" + priceInvestigation.prototype.config().frm).initForm(result.data);
                        $('#' + priceInvestigation.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }

    }
</script>



