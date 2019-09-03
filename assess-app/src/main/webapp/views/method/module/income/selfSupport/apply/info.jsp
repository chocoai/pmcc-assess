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
                有效毛收入
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
                    <th>总收入</th>
                    <th>总成本</th>
                    <th>经营利润</th>
                    <th>房地产年净收益</th>
                    <th>年期修正系数(h)</th>
                    <th>收益现值系数(k)</th>
                    <th>房地产收益价格</th>
                </tr>
                </thead>
                <tbody id="selfSupportResultBody">

                </tbody>
            </table>
        </div>
    </div>
</div>

<%--收入预测数据--%>
<div id="modal_forecast_income" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">预测数据</h3>
            </div>
            <form id="frm_forecast_income" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <input type="hidden" name="sectionId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                初始金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="initialAmount" placeholder="初始金额" data-rule-number="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                初始金额说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="initialAmountRemark" placeholder="初始金额说明"
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
                                <input type="text" name="growthRate" placeholder="增长率" class="form-control x-percent"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                增长率说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="growthRateRemaek" placeholder="增长率说明" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="selfSupport.saveForecastIncome();">
                        保存
                    </button>
                </div>
            </form>
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
                <h3 class="modal-title">预测数据</h3>
            </div>
            <form id="frm_forecast_cost" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <input type="hidden" name="sectionId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营成本<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingCost" placeholder="经营成本" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营成本说明<span class="symbol required"></span>
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
                                经营费用<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingExpenses" placeholder="经营费用" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营费用说明<span class="symbol required"></span>
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
                                经营税金及附加<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingTax" placeholder="经营税金及附加" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营税金及附加说明<span class="symbol required"></span>
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
                                管理费用<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="managementCost" placeholder="管理费用" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                管理费用说明<span class="symbol required"></span>
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
                                财务费用<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="financialCost" placeholder="财务费用" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                财务费用说明<span class="symbol required"></span>
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
                                经营利润<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingProfit" placeholder="经营利润" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                经营利润说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="operatingProfitRemark" placeholder="经营利润说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                特许权超额利润<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="excessProfit" placeholder="特许权超额利润" data-rule-number="true"
                                       onblur="selfSupport.computeInitialAmount(this);" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                特许权超额利润说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="excessProfitRemark" placeholder="特许权超额利润说明"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                初始金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="initialAmount" placeholder="初始金额" data-rule-number="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                初始金额说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="initialAmountRemark" placeholder="初始金额说明"
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
                                <input type="text" name="growthRate" placeholder="增长率" class="form-control x-percent"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                增长率说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="growthRateRemark" placeholder="增长率说明" class="form-control"
                                       required="required">
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
        <td><label data-name="correctionFactor">{correctionFactor}</label></td>
        <td><label data-name="presentValueFactor">{presentValueFactor}</label></td>
        <td><label data-name="incomePrice">{incomePrice}</label></td>
    </tr>
</script>

<script type="text/javascript">


    var selfSupport = {};
    selfSupport.frmForecastIncome = $('#frm_forecast_income');
    selfSupport.frmForecastCost = $('#frm_forecast_cost');

    //编辑预测信息
    selfSupport.editForecastIncome = function (index) {
        var row = $("#tb_forecast_income_list").bootstrapTable('getData')[index];
        selfSupport.frmForecastIncome.clearAll();
        selfSupport.frmForecastIncome.initForm(row);
        selfSupport.frmForecastIncome.find('[name=growthRate]').attr('data-value', row.growthRate);
        AssessCommon.elementParsePercent(selfSupport.frmForecastIncome.find('[name=growthRate]'));
        $('#modal_forecast_income').modal();
    }

    selfSupport.editForecastCost = function (index) {
        var row = $("#tb_forecast_cost_list").bootstrapTable('getData')[index];
        selfSupport.frmForecastCost.clearAll();
        selfSupport.frmForecastCost.initForm(row);
        selfSupport.frmForecastCost.find('[name=growthRate]').attr('data-value', row.growthRate);
        AssessCommon.elementParsePercent(selfSupport.frmForecastCost.find('[name=growthRate]'));
        $('#modal_forecast_cost').modal();
    }

    //保存收入预测信息
    selfSupport.saveForecastIncome = function () {
        if (!selfSupport.frmForecastIncome.valid()) {
            return false;
        }
        var data = formSerializeArray(selfSupport.frmForecastIncome);
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
        cols.push({field: 'initialAmount', title: '初始金额'});
        cols.push({
            field: 'growthRate', title: '增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecastIncome(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
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
        cols.push({field: 'operatingCost', title: '经营成本'});
        cols.push({field: 'operatingExpenses', title: '经营费用'});
        cols.push({field: 'operatingTax', title: '经营税金及附加'});
        cols.push({field: 'managementCost', title: '管理费用'});
        cols.push({field: 'financialCost', title: '财务费用'});
        cols.push({field: 'operatingProfit', title: '经营利润'});
        cols.push({field: 'excessProfit', title: '特许权超额利润'});
        cols.push({field: 'initialAmount', title: '初始金额'});
        cols.push({
            field: 'growthRate', title: '增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecastCost(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="selfSupport.viewForecastYear(' + row.id + ');" ><i class="fa fa-search fa-white"></i></a>';
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
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看月度" onclick="selfSupport.viewForecastMonth(' + row.type + ',' + row.id + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
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
        var initialAmount = 0;
        var operatingCost = form.find('[name=operatingCost]').val();
        if (operatingCost && AssessCommon.isNumber(operatingCost)) {
            initialAmount += parseFloat(operatingCost);
        }
        var operatingExpenses = form.find('[name=operatingExpenses]').val();
        if (operatingExpenses && AssessCommon.isNumber(operatingExpenses)) {
            initialAmount += parseFloat(operatingExpenses);
        }
        var operatingTax = form.find('[name=operatingTax]').val();
        if (operatingTax && AssessCommon.isNumber(operatingTax)) {
            initialAmount += parseFloat(operatingTax);
        }
        var managementCost = form.find('[name=managementCost]').val();
        if (managementCost && AssessCommon.isNumber(managementCost)) {
            initialAmount += parseFloat(managementCost);
        }
        var financialCost = form.find('[name=financialCost]').val();
        if (financialCost && AssessCommon.isNumber(financialCost)) {
            initialAmount += parseFloat(financialCost);
        }
        var operatingProfit = form.find('[name=operatingProfit]').val();
        if (operatingProfit && AssessCommon.isNumber(operatingProfit)) {
            initialAmount += parseFloat(operatingProfit);
        }
        var excessProfit = form.find('[name=excessProfit]').val();
        if (excessProfit && AssessCommon.isNumber(excessProfit)) {
            initialAmount += parseFloat(excessProfit);
        }
        form.find('[name=initialAmount]').val(initialAmount);
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
            if (!AssessCommon.isNumber(incomeTotal)) return false;
            if (!AssessCommon.isNumber(costTotal)) return false;
            incomeTotal = parseFloat(incomeTotal);
            costTotal = parseFloat(costTotal);
            var operatingProfit = (incomeTotal * averageProfitRate).toFixed(2);
            $(this).find('[data-name=operatingProfit]').text(operatingProfit);
            //净收益
            var netProfit = incomeTotal - costTotal - operatingProfit;
            $(this).find('[data-name=netProfit]').text(netProfit.toFixed(2));
        })

        selfSupport.computePrice();
    }

    //计算年期修正系数[(h)=(1-(1/(1+r))^n]、收益现值系数[(k)=h/r]、房地产收益价格[房地产收益价格*收益现值系数]
    selfSupport.computePrice = function () {
        var r = $("#frm_self_support").find('[name=rewardRate]').attr('data-value');//报酬率
        if (!AssessCommon.isNumber(r)) return false;
        r = parseFloat(r);
        var incomePriceTotal = 0;//收益价格合计
        $("#selfSupportResultBody").find('tr').each(function () {
            var n = $(this).find('[data-name=yearCount]').text();
            if (!AssessCommon.isNumber(n)) return false;
            n = parseInt(n);
            var h = (1 - Math.pow(1 / (1 + r), n)).toFixed(6);//年期修正系数
            var k = (h / r).toFixed(6);//收益现值系数
            $(this).find('[data-name=correctionFactor]').text(h);
            $(this).find('[data-name=presentValueFactor]').text(k);
            var netProfit = $(this).find('[data-name=netProfit]').text();
            if (!AssessCommon.isNumber(netProfit)) return false;
            netProfit = parseFloat(netProfit);
            var incomePrice = (netProfit * k).toFixed(2);
            incomePriceTotal = incomePriceTotal + parseFloat(incomePrice);
            $(this).find('[data-name=incomePrice]').text(incomePrice);//收益价格
        })
        //计算委估对象单价 （单价=收益价格合计\委估对象面积）
        var area = $("#selfSupportResult").find('[data-name=area]').text();
        if (!AssessCommon.isNumber(area)) return false;
        area = parseFloat(area);
        $("#selfSupportResult").find('[data-name=price]').text((incomePriceTotal / area).toFixed(2));
    }
</script>



