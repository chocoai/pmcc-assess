<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>收益法</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_income" class="form-horizontal" enctype="multipart/form-data">
            <input type="hidden" name="id" value="0">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    经营方式<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <span class="radio-inline"><input type="radio" required name="operationMode" id="operationMode0"
                                                      onclick="income.operationModeChange(0)"
                                                      value="0"><label
                            for="operationMode0">自营</label></span>
                    <span class="radio-inline"><input type="radio" name="operationMode" id="operationMode1"
                                                      onclick="income.operationModeChange(1)"
                                                      value="1"><label
                            for="operationMode1">租赁</label></span>
                </div>
            </div>
            <div class="form-group" id="group_leaseMode" style="display: none;">
                <label class="col-sm-1 control-label">
                    租赁限制<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <span class="radio-inline"><input type="radio" required name="leaseMode" id="leaseMode0"
                                                      value="0"><label
                            for="leaseMode0">限制</label></span>
                    <span class="radio-inline"><input type="radio" name="leaseMode" id="leaseMode1" value="1"><label
                            for="leaseMode1">无限制</label></span>
                </div>
            </div>
        </form>
        <div id="self_support_info" style="display: none">
            <div class="x_title">
                <h3>收入类</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <button class="btn btn-success" data-toggle="modal"
                        onclick="income.addSupportCost(0,'tb_income_list');">
                    新增
                </button>
                <table class="table table-bordered" id="tb_income_list">
                </table>
            </div>
            <div class="x_title">
                <h3>成本类</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <button class="btn btn-success" data-toggle="modal" onclick="income.addSupportCost(1,'tb_cost_list');">
                    新增
                </button>
                <table class="table table-bordered" id="tb_cost_list">
                </table>
            </div>
            <div class="x_title">
                <h3>费用类</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <button class="btn btn-success" data-toggle="modal"
                        onclick="income.addSupportCost(2,'tb_expense_list');">
                    新增
                </button>
                <table class="table table-bordered" id="tb_expense_list">
                </table>
            </div>
            <form id="frm_self_support" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="id" value="0">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            行业经经营平均利润率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="averageProfitRate" placeholder="行业经经营平均利润率" class="form-control"
                                   data-rule-number="true" required="required">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            行业经经营平均利润率取数说明
                        </label>
                        <div class="col-sm-11">
                            <textarea name="averageProfitRateRemark" class="form-control"
                                      placeholder="行业经经营平均利润率取数说明"></textarea>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div id="lease_info" style="display: none">
            <div class="x_title">
                <h3>收益价格计算信息</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <button class="btn btn-success" data-toggle="modal" onclick="income.addLease();">
                    新增
                </button>
                <table class="table table-bordered" id="tb_lease_list">
                </table>
            </div>
        </div>

    </div>
</div>

<div id="modal_self_support_cost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">金额信息</h3>
            </div>
            <form id="frm_self_support_cost" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <input type="hidden" name="tbListId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            会计科目<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="accountingSubject" placeholder="会计科目"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            一级编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="costType" placeholder="一级编号"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            二级编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="costCategory" placeholder="二级编号"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            月度<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="monthly" placeholder="月度"
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
                                                   onblur="income.selfSupportCalculate();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            数量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="number" placeholder="数量"
                                                   onblur="income.selfSupportCalculate();"
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
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="income.saveSupportCost();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modal_lease" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 1200px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">租赁收益</h3>
            </div>
            <form id="frm_lease" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <fieldset>
                        <legend>1.年有效毛收入（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    月租金收入<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="rentalIncome" placeholder="月租金收入"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    全年月份数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="monthNumber" placeholder="全年月份数"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    出租率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="rentals" placeholder="出租率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    出租率说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="rentalsRemark" class="form-control" placeholder="出租率说明"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    押金<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="deposit" placeholder="押金"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    利率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="interestRate" placeholder="利率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    年押金利息收入<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="interestIncome" placeholder="年押金利息收入"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    利率说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="interestRateRemark" class="form-control"
                                              placeholder="利率说明"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    其它收入<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="otherIncome" placeholder="其它收入"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    其它收入说明
                                </label>
                                <div class="col-sm-11">
                                    <textarea name="otherIncomeRemark" class="form-control"
                                              placeholder="其它收入说明"></textarea>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>2.年运营费（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    管理费用<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="managementCost" placeholder="管理费用"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    费用比率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="managementCostRatio" placeholder="管理费用比率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    取费说明<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="paymentRemark" placeholder="取费说明"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    维护保养<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="maintenance" placeholder="维护保养"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    费用比率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="maintenanceCostRatio" placeholder="维护保养费用比率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    计算基数重置价格<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="replacementValue" placeholder="计算基数重置价格"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    相关税金及附加<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="additional" placeholder="相关税金及附加"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    税率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="taxRate" placeholder="税率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    保险费<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="insurancePremium" placeholder="保险费"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    保险费率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="insuranceRate" placeholder="保险费率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用税<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="landUseTax" placeholder="土地使用税"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    使用税参数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="usageTaxParameter" placeholder="使用税参数"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>3.房地产年净收益（元/m2）</legend>
                    </fieldset>
                    <fieldset>
                        <legend>4.房地产收益价格（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    资本化率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="capitalizationRate" placeholder="资本化率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    收益期限<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="returnPeriod" placeholder="收益期限"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开始时间<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="leaseBeginDate" placeholder="开始时间"
                                           required="required" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="leaseEndDate" placeholder="结束时间"
                                           data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                           readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    租金增长率<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="rentalGrowthRate" placeholder="租金增长率"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    年期修正系数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="correctionFactor" placeholder="年期修正系数"
                                           class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    收益现值系数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" name="presentValueFactor" placeholder="收益现值系数"
                                           class="form-control" required="required">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="income.saveLease();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var income = {};

    //初始化
    income.init = function (options) {
        var defaluts = {
            incomeInfo: undefined,
            incomeSelfSupport: undefined,
            readonly: false//
        };
        defaluts = $.extend({}, defaluts, options);
        if (income.incomeInfo) {

        }
        if (income.incomeSelfSupport) {

        }
    }

    //经营方式切换
    income.operationModeChange = function (value) {
        if (value == 0) {
            $("#self_support_info").show();
            $("#group_leaseMode,#lease_info").hide();

            income.loadSupportCostList('0', 'tb_income_list');
            income.loadSupportCostList('1', 'tb_cost_list');
            income.loadSupportCostList('2', 'tb_expense_list');
        } else if (value == 1) {
            $("#self_support_info").hide();
            $("#group_leaseMode,#lease_info").show();
            income.loadLeaseList();
        }
    }

    //租赁方式切换
    income.leaseModeChange = function (value) {
        if (value == 0) {

        } else if (value == 1) {

        }
    }

    //添加自营金额列表信息
    income.addSupportCost = function (type, tbListId) {
        $("#frm_self_support_cost").clearAll();
        $("#frm_self_support_cost").find('[name=type]').val(type);
        $("#frm_self_support_cost").find('[name=tbListId]').val(tbListId);
        $('#modal_self_support_cost').modal();
    }

    //编辑自营金额列表信息
    income.editSupportCost = function (index, tbListId) {
        var row = $("#" + tbListId).bootstrapTable('getData')[index];
        $("#frm_self_support_cost").clearAll();
        $("#frm_self_support_cost").initForm(row);
        $("#frm_self_support_cost").find('[name=tbListId]').val(tbListId);
        $('#modal_self_support_cost').modal();
    }

    //删除自营金额列表信息
    income.delSupportCost = function (id, tbListId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteSelfSupportCost",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        income.loadSupportCostList(tbListId);
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

    //保存自营金额列表信息
    income.saveSupportCost = function () {
        if (!$("#frm_self_support_cost").valid()) {
            return false;
        }
        var data = formParams("frm_self_support_cost");
        data.supportId = $("#frm_self_support").find('[name=id]').val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveSelfSupportCost",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    var type = $("#frm_self_support_cost").find('[name=type]').val();
                    var tbListId = $("#frm_self_support_cost").find('[name=tbListId]').val();
                    income.loadSupportCostList(type, tbListId);
                    $('#modal_self_support_cost').modal('hide');
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

    //显示自营金额列表信息
    income.loadSupportCostList = function (type, tbListId) {
        var cols = [];
        cols.push({field: 'accountingSubjectName', title: '会计科目'});
        cols.push({field: 'costTypeName', title: '一级编号'});
        cols.push({field: 'costCategoryName', title: '二级编号'});
        cols.push({field: 'monthly', title: '月度'});
        cols.push({field: 'unitPrice', title: '单价'});
        cols.push({field: 'number', title: '数量'});
        cols.push({field: 'amountMoney', title: '金额'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="income.editSupportCost(' + index + ',\'' + tbListId + '\');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="income.delSupportCost(' + row.id + ',\'' + tbListId + '\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + tbListId).bootstrapTable('destroy');
        TableInit(tbListId, "${pageContext.request.contextPath}/income/getSelfSupportCostList", cols, {
            type: type,
            supportId: $("#frm_self_support").find('[name=id]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //计算自营金额
    income.selfSupportCalculate = function () {
        var unitPrice = $("#frm_self_support_cost").find('[name=unitPrice]').val();
        var number = $("#frm_self_support_cost").find('[name=number]').val();
        if (unitPrice && number) {
            var amountMoney = parseFloat(unitPrice) * parseFloat(number);
            $("#frm_self_support_cost").find('[name=amountMoney]').val(amountMoney.toFixed(2));
        }
    }

    //租赁-----------------------------------------------------------------------------------------------------

    //添加租赁收益列表信息
    income.addLease = function () {
        $("#frm_lease").clearAll();
        $('#modal_lease').modal();
    }

    //编辑租赁收益
    income.editLease = function (index) {
        var row = $("#tb_lease_list").bootstrapTable('getData')[index];
        $("#frm_lease").clearAll();
        $("#frm_lease").initForm(row);
        $('#modal_lease').modal();
    }

    //删除租赁收益
    income.delLease = function (id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteLease",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        income.loadLeaseList();
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

    //保存租赁收益
    income.saveLease = function () {
        if (!$("#frm_lease").valid()) {
            return false;
        }
        var formData = formParams("frm_lease");
        formData.incomeId = $("#frm_income").find('[name=id]').val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveLease",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(formData)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    income.loadLeaseList();
                    $('#modal_lease').modal('hide');
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

    //显示租赁收益列表信息
    income.loadLeaseList = function () {
        var cols = [];
        cols.push({
            field: 'leaseBeginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'leaseEndDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({field: 'rentalGrowthRate', title: '租金增长率'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="income.editLease(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="income.delLease(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_list").bootstrapTable('destroy');
        TableInit("tb_lease_list", "${pageContext.request.contextPath}/income/getLeaseList", cols, {
            supportId: $("#frm_income").find('[name=id]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //获取需要保存的数据
    income.getData = function () {
        var mdIncome = formParams("frm_income");
        var mdIncomeSelfSupport = formParams("frm_self_support");
        var mdIncomeResultDto = {};
        mdIncomeResultDto.mdIncome = mdIncome;
        mdIncomeResultDto.mdIncomeSelfSupport = mdIncomeSelfSupport;
        return mdIncomeResultDto;
    }
</script>




