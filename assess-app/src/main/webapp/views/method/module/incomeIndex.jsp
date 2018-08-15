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
            <div class="form-group">
                <div class="col-sm-3 col-sm-offset-1">
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
                <div class="col-sm-3 col-sm-offset-1">
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
                <button class="btn btn-success" data-toggle="modal" onclick="income.addSupportCost(0);">
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
                <button class="btn btn-success" data-toggle="modal" onclick="income.addSupportCost(1);">
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
                <button class="btn btn-success" data-toggle="modal" onclick="income.addSupportCost(2);">
                    新增
                </button>
                <table class="table table-bordered" id="tb_expense_list">
                </table>
            </div>
            <form id="frm_self_support" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="id">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            行业经经营平均利润率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="averageProfitRate" placeholder="行业经经营平均利润率" class="form-control"
                                   required="required">
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
                <button class="btn btn-success" data-toggle="modal" onclick="">
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
                <input type="hidden" name="typeListId">
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
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            数量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="number" placeholder="数量"
                                                   class="form-control" required="required">
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
                                                   class="form-control" required="required">
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

<script type="text/javascript">
    var income = {};

    //初始化
    income.init = function (options) {
        var defaluts = {

            readonly: false//
        };
        defaluts = $.extend({}, defaluts, options);
    }

    //经营方式切换
    income.operationModeChange = function (value) {
        if (value == 0) {
            $("#self_support_info").show();
            $("#group_leaseMode,#lease_info").hide();

            income.loadSupportCostList('tb_income_list');
            income.loadSupportCostList('tb_cost_list');
            income.loadSupportCostList('tb_expense_list');
        } else if (value == 1) {
            $("#self_support_info").hide();
            $("#group_leaseMode,#lease_info").hide();
        }
    }

    //租赁方式切换
    income.leaseModeChange = function (value) {
        if (value == 0) {

        } else if (value == 1) {

        }
    }

    //添加自营金额列表信息
    income.addSupportCost = function (type, typeListId) {
        $("#frm_self_support_cost").clearAll();
        $("#frm_self_support_cost").find('[name=type]').val(type);
        $("#frm_self_support_cost").find('[name=typeListId]').val(typeListId);
        $('#modal_self_support_cost').modal();
    }

    //编辑自营金额列表信息
    income.editSupportCost = function (index, tbListId) {
        var row = $("#" + tbListId).bootstrapTable('getData')[index];
        $("#frm_self_support_cost").clearAll();
        $("#frm_self_support_cost").initForm(row);
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
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveSelfSupportCost",
            type: "post",
            dataType: "json",
            data: formParams("frm_self_support_cost"),
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    income.loadSupportCostList($("#frm_self_support_cost").find('[name=typeListId]').val());
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
</script>




