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
                    经营方式
                </label>
                <div class="col-sm-3">
                    <span class="radio-inline"><input type="radio" required name="operationMode" id="operationMode0"
                                                      onclick="income.operationModeChange(0)" readonly="readonly"
                                                      value="0"><label
                            for="operationMode0">自营</label></span>
                    <span class="radio-inline"><input type="radio" name="operationMode" id="operationMode1"
                                                      onclick="income.operationModeChange(1)" readonly="readonly"
                                                      value="1"><label
                            for="operationMode1">租赁</label></span>
                </div>
            </div>
            <div class="form-group" id="group_leaseMode" style="display: none;">
                <label class="col-sm-1 control-label">
                    租赁限制
                </label>
                <div class="col-sm-3">
                    <span class="radio-inline"><input type="radio" required name="leaseMode" id="leaseMode0"
                                                      onchange="income.leaseModeChange(this);" readonly="readonly"
                                                      value="0"><label
                            for="leaseMode0">限制</label></span>
                    <span class="radio-inline"><input type="radio" name="leaseMode" id="leaseMode1"
                                                      onchange="income.leaseModeChange(this);" readonly="readonly"
                                                      value="1"><label
                            for="leaseMode1">无限制</label></span>
                </div>
            </div>
            <div class="form-group" id="group_restriction_explain" style="display: none;">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        租约限制说明
                    </label>
                    <div class="col-sm-11">
                        <label name="restrictionExplain" class="form-control"></label>
                    </div>
                </div>
            </div>
        </form>
        <div id="self_support_info" style="display: none">
            <div class="x_title">
                <h3>收入类 <i class="red" id="amount_money_total_0"></i></h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_income_list">
                </table>
            </div>
            <div class="x_title">
                <h3>成本类 <i class="red" id="amount_money_total_1"></i></h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_cost_list">
                </table>
            </div>
            <div class="x_title">
                <h3>费用类 <i class="red" id="amount_money_total_2"></i></h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_expense_list">
                </table>
            </div>
            <form id="frm_self_support" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="id" value="0">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            行业经经营平均利润率
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control" name="averageProfitRate"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            行业经经营平均利润率取数说明
                        </label>
                        <div class="col-sm-11">
                            <label class="form-control" name="averageProfitRateRemark"></label>
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
                <table class="table table-bordered" id="tb_lease_list">
                </table>
            </div>
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
                        <legend>1.年有效毛收入 <i class="red" data-name="grossIncome"></i>（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    月租金收入
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="rentalIncome"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    全年月份数
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="monthNumber"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    出租率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="rentals"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    出租率说明
                                </label>
                                <div class="col-sm-11">
                                    <label class="form-control" data-name="rentalsRemark"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    押金
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="deposit"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    利率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="interestRate"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    年押金利息收入
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="interestIncome"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    利率说明
                                </label>
                                <div class="col-sm-11">
                                    <label class="form-control" data-name="interestRateRemark"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    其它收入
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="otherIncome"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    其它收入说明
                                </label>
                                <div class="col-sm-11">
                                    <label class="form-control" data-name="otherIncomeRemark"></label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>2.年运营费 <i class="red" data-name="operatingExpense"></i>（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    费用比率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="managementCostRatio"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    管理费用
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="managementCost"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    取费说明
                                </label>
                                <div class="col-sm-11">
                                    <label class="form-control" data-name="paymentRemark"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    维护保养费率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="maintenanceCostRatio"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    计算基数重置价格
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="replacementValue"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    维护保养费
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="maintenance"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    税率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="taxRate"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    相关税金及附加
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="additional"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    保险费率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="insuranceRate"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    保险费
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="insurancePremium"></label>
                                </div>
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    使用税参数
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="usageTaxParameter"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用税
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="landUseTax"></label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>3.房地产年净收益 <i class="red" data-name="netProfit"></i>（元/m2）</legend>
                    </fieldset>
                    <fieldset>
                        <legend>4.房地产收益价格 <i class="red" data-name="incomePrice"></i>（元/m2）</legend>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    资本化率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="capitalizationRate"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    收益期限
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="returnPeriod"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开始时间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="leaseBeginDate"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="leaseEndDate"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    租金增长率
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="rentalGrowthRate"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    年期修正系数
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="correctionFactor"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    收益现值系数
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="presentValueFactor"></label>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var income = {};
    //参数
    income.defaluts = {
        incomeInfo: undefined,
        incomeSelfSupport: undefined
    };

    //初始化
    income.view = function (options) {
        income.defaluts = $.extend({}, income.defaluts, options);
        var defaluts = income.defaluts;
        if (defaluts.incomeSelfSupport) {
            $("#frm_self_support").find("[name=id]").val(defaluts.incomeSelfSupport.id);
            $("#frm_self_support").find("[name=averageProfitRate]").text(defaluts.incomeSelfSupport.averageProfitRate);
            $("#frm_self_support").find("[name=averageProfitRateRemark]").text(defaluts.incomeSelfSupport.averageProfitRateRemark);
        }
        if (defaluts.incomeInfo) {
            $("#frm_income").find("[name=id]").val(defaluts.incomeInfo.id);
            $("#frm_income").find("[name=restrictionExplain]").text(defaluts.incomeInfo.restrictionExplain);
            $("#frm_income").find("[name=operationMode][value=" + defaluts.incomeInfo.operationMode + "]").trigger('click');
            $("#frm_income").find("[name=operationMode]").attr('disabled', 'disabled');
            $("#frm_income").find("[name=leaseMode]").attr('disabled', 'disabled');
        }

    }

    //经营方式切换
    income.operationModeChange = function (value) {
        if (value == 0) {
            $("#self_support_info").show();
            $("#group_leaseMode,#group_restriction_explain,#lease_info").hide();

            income.loadSupportCostList('0', 'tb_income_list');
            income.loadSupportCostList('1', 'tb_cost_list');
            income.loadSupportCostList('2', 'tb_expense_list');
        } else if (value == 1) {
            $("#self_support_info").hide();
            $("#group_leaseMode,#lease_info").show();
            $("#frm_income").find("[name=leaseMode][value=" + income.defaluts.incomeInfo.leaseMode + "]").trigger('click');
            income.loadLeaseList();
        }
    }

    //租赁方式切换
    income.leaseModeChange = function (_this) {
        var value = $(_this).val();
        if (value == 0) {
            $("#group_restriction_explain").show();
        } else if (value == 1) {
            $("#group_restriction_explain").hide();
        }
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


    //租赁-----------------------------------------------------------------------------------------------------


    //查看租赁收益
    income.viewLease = function (index) {
        var row = $("#tb_lease_list").bootstrapTable('getData')[index];
        $("#frm_lease").find('[data-name]').each(function () {
            $(this).text('').text(row[$(this).attr('data-name')]);
        })
        $("#frm_lease").find('[data-name=leaseBeginDate]').text(formatDate(row.leaseBeginDate, false));
        $("#frm_lease").find('[data-name=leaseEndDate]').text(formatDate(row.leaseEndDate, false));
        $('#modal_lease').modal();
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
        cols.push({field: 'grossIncome', title: '年有效毛收入'});
        cols.push({field: 'operatingExpense', title: '年运营费'});
        cols.push({field: 'netProfit', title: '房地产年净收益'});
        cols.push({field: 'incomePrice', title: '房地产收益价格'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="income.viewLease(' + index + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_list").bootstrapTable('destroy');
        TableInit("tb_lease_list", "${pageContext.request.contextPath}/income/getLeaseList", cols, {
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

</script>




