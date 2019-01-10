<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="lease_info" style="display: none;">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>收入类</h3>
            <div class="clearfix"></div>
        </div>

        <div class="x_content">
            <table class="table table-bordered" id="tb_lease_income_list">
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
            <table class="table table-bordered" id="tb_lease_cost_list">
            </table>
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
            <form id="frm_lease" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="报酬率">
                            报酬率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="text" required class="form-control x-percent" name="rewardRate"
                                       placeholder="报酬率"
                                       data-value="${mdIncome.rewardRate}" onblur="lease.computeNetProfit();">
                                <span class="input-group-btn">
                              <input type="button" class="btn btn-primary" value="报酬率测算"
                                     onclick="lease.getRewardRate(this);"/>
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table table-bordered" id="tb_lease_parameter_list">
            </table>
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
        <div class="x_content form-horizontal" id="leaseResult">
            <div class="form-group">
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
                    <th>毛收入</th>
                    <th>运营费</th>
                    <th>房地产年净收益</th>
                    <th>年期修正系数(h)</th>
                    <th>收益现值系数(k)</th>
                    <th>房地产收益价格</th>
                </tr>
                </thead>
                <tbody id="leaseResultBody">

                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="modal_lease_income" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">收入</h3>
            </div>
            <div class="modal-body">
                <form id="frm_lease_income" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                月租金收入(元)<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="rentalIncome" placeholder="月租金收入" data-rule-digits="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                全年月份数<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="monthNumber" placeholder="全年月份数" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                出租率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="rentals" placeholder="出租率" class="form-control x-percent"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                出租率说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="rentalsRemark" placeholder="出租率说明" class="form-control"
                                       required="required">
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                押金<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="deposit" data-rule-digits="true" placeholder="押金"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                押金说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="depositRemark" placeholder="押金说明" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                押金利率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="depositRate" placeholder="押金利率" class="form-control x-percent"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                利率说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="depositRateRemark" placeholder="利率说明" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                其他收入<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="otherIncome" placeholder="其他收入" data-rule-digits="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                其它收入说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="otherIncomeRemark" placeholder="其它收入说明" class="form-control"
                                       required="required">
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
                        onclick="lease.saveLease();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal_lease_cost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">成本</h3>
            </div>
            <div class="modal-body">
                <form id="frm_lease_cost" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                管理费<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="managementCostRatio" placeholder="管理费"
                                       class="form-control x-percent" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                重置价格<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="replacementValue" readonly="readonly" placeholder="重置价格"
                                       data-rule-number="true"
                                       class="form-control " required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                维护保养费<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="maintenanceCostRatio" placeholder="维护保养费"
                                       class="form-control x-percent" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                租赁税费<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="additionalRatio" readonly="readonly" placeholder="租赁税费"
                                       class="form-control x-percent" required="required">
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                保险费<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="insurancePremiumRatio" placeholder="保险费"
                                       class="form-control x-percent" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                土地使用税<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="landUseTax" placeholder="土地使用税" class="form-control"
                                       data-rule-number="true"
                                       required="required">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="lease.saveLeaseCost();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal_lease_parameter" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">成本</h3>
            </div>
            <div class="modal-body">
                <form id="frm_lease_parameter" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                租金增长率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="rentalGrowthRate" placeholder="租金增长率"
                                       class="form-control x-percent" required="required">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="lease.saveLeaseParameter();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="leaseResultHtml">
    <tr data-id="{id}">
        <td>
            <input type="hidden" data-name="rentalGrowthRate" value="{rentalGrowthRate}">
            <label>{beginDate}</label>
        </td>
        <td><label>{endDate}</label></td>
        <td><label data-name="yearCount">{yearCount}</label></td>
        <td><label data-name="incomeTotal">{incomeTotal}</label></td>
        <td><label data-name="costTotal">{costTotal}</label></td>
        <td><label data-name="netProfit">{netProfit}</label></td>
        <td><label data-name="correctionFactor">{correctionFactor}</label></td>
        <td><label data-name="presentValueFactor">{presentValueFactor}</label></td>
        <td><label data-name="incomePrice">{incomePrice}</label></td>
    </tr>
</script>

<script type="text/javascript">
    var lease = {};

    //获取报酬率
    lease.getRewardRate = function (_this) {
        rewardRateFunc.calculation(function (result) {
            if (result) {
                var element = $(_this).closest('.input-group').find(':text');
                element.val(result);
                AssessCommon.elementParsePoint(element);
                lease.computeNetProfit();
            }
        })
    }

    //编辑收入信息
    lease.editLease = function (index) {
        var row = $("#tb_lease_income_list").bootstrapTable('getData')[index];
        $("#frm_lease_income").clearAll();
        $("#frm_lease_income").initForm(row);
        $("#frm_lease_income").find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        $('#modal_lease_income').modal();
    }

    //保存收入信息
    lease.saveLease = function () {
        if (!$("#frm_lease_income").valid()) {
            return false;
        }
        var data = formParams("frm_lease_income");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/updateLease",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    lease.loadLeaseList();
                    lease.loadCalculationResult();
                    $('#modal_lease_income').modal('hide');
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

    //加载收入列表信息
    lease.loadLeaseList = function () {
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
        cols.push({field: 'rentalIncome', title: '月租金收入'});
        cols.push({
            field: 'rentals', title: '出租率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'monthNumber', title: '全年月份数'});
        cols.push({field: 'deposit', title: '押金'});
        cols.push({
            field: 'depositRate', title: '押金利率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'otherIncome', title: '其他收入'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="lease.editLease(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_income_list").bootstrapTable('destroy');
        TableInit("tb_lease_income_list", "${pageContext.request.contextPath}/income/getLeaseList", cols, {
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                lease.computeNetProfit();
            }
        });
    }


    //编辑成本信息
    lease.editLeaseCost = function (index) {
        var row = $("#tb_lease_cost_list").bootstrapTable('getData')[index];
        $("#frm_lease_cost").clearAll();
        $("#frm_lease_cost").initForm(row);
        $("#frm_lease_cost").find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        //租赁税费特殊处理
        var additionalRatioElement = $("#frm_lease_cost").find('[name=additionalRatio]');
        additionalRatioElement.attr('data-value', '${additionalRatio}');
        AssessCommon.elementParsePercent(additionalRatioElement);
        //重置价格特殊处理
        $("#frm_lease_cost").find('[name=replacementValue]').val("${replacementValue}");
        $('#modal_lease_cost').modal();
    }

    //保存成本信息
    lease.saveLeaseCost = function () {
        if (!$("#frm_lease_cost").valid()) {
            return false;
        }
        var data = formParams("frm_lease_cost");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/updateLeaseCost",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    lease.loadLeaseCostList();
                    lease.loadCalculationResult();
                    $('#modal_lease_cost').modal('hide');
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

    //加载成本列表信息
    lease.loadLeaseCostList = function () {
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
        cols.push({
            field: 'managementCostRatio', title: '管理费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'maintenanceCostRatio', title: '维修保养费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'additionalRatio', title: '租赁税费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'insurancePremiumRatio', title: '保险费', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'landUseTax', title: '土地使用税'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="lease.editLeaseCost(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_cost_list").bootstrapTable('destroy');
        TableInit("tb_lease_cost_list", "${pageContext.request.contextPath}/income/getLeaseCostList", cols, {
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                lease.computeNetProfit();
            }
        });
    }

    //编辑参数信息
    lease.editLeaseParameter = function (index) {
        var row = $("#tb_lease_parameter_list").bootstrapTable('getData')[index];
        $("#frm_lease_parameter").clearAll();
        $("#frm_lease_parameter").initForm(row);
        $("#frm_lease_parameter").find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        $('#modal_lease_parameter').modal();
    }

    //保存参数信息
    lease.saveLeaseParameter = function () {
        if (!$("#frm_lease_parameter").valid()) {
            return false;
        }
        var data = formParams("frm_lease_parameter");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/updateDateSection",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    lease.loadLeaseParameterList();
                    lease.loadCalculationResult();
                    $('#modal_lease_parameter').modal('hide');
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

    //加载参数列表信息
    lease.loadLeaseParameterList = function () {
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
        cols.push({
            field: 'rentalGrowthRate', title: '租金增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="lease.editLeaseParameter(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_parameter_list").bootstrapTable('destroy');
        TableInit("tb_lease_parameter_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            operationMode: incomeIndex.getOperationMode(),
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                lease.computeNetProfit();
            }
        });
    }

    //加载测算结果数据
    lease.loadCalculationResult = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/income/getDateSectionList",
            type: "get",
            dataType: "json",
            data: {
                incomeId: $("#frm_income").find('[name=id]').val(),
                operationMode: $("#frm_income").find('[name=operationMode]:checked').val()
            },
            success: function (result) {
                if (result.rows) {
                    $("#leaseResultBody").empty();
                    $(result.rows).each(function (i, item) {
                        var html = $('#leaseResultHtml').html();
                        html = html.replace(/{id}/g, item.id).replace(/{beginDate}/g, formatDate(item.beginDate, false));
                        html = html.replace(/{endDate}/g, formatDate(item.endDate, false)).replace(/{yearCount}/g, item.yearCount);
                        html = html.replace(/{incomeTotal}/g, AssessCommon.toString(item.incomeTotal)).replace(/{costTotal}/g, AssessCommon.toString(item.costTotal));
                        html = html.replace(/{rentalGrowthRate}/g, AssessCommon.toString(item.rentalGrowthRate));
                        html = html.replace(/{netProfit}/g, AssessCommon.toString(item.netProfit)).replace(/{correctionFactor}/g, AssessCommon.toString(item.correctionFactor));
                        html = html.replace(/{presentValueFactor}/g, AssessCommon.toString(item.presentValueFactor)).replace(/{incomePrice}/g, AssessCommon.toString(item.incomePrice));
                        $("#leaseResultBody").append(html);
                    })
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //获取表单内容
    lease.getData = function () {
        var formData = {};
        formData.mdIncome = {};
        formData.mdIncome.id = $("#frm_income").find('[name=id]').val();
        formData.mdIncome.name = $("#frm_income").find('[name=name]').val();
        formData.mdIncome.price = $("#leaseResult").find('[data-name=price]').text();
        formData.mdIncome.operationMode = $("#frm_income").find('[name=operationMode]:checked').val();
        formData.mdIncome.leaseMode = $("#frm_income").find('[name=leaseMode]:checked').val();
        formData.mdIncome.rewardRate = $("#frm_lease").find('[name=rewardRate]').attr('data-value');
        formData.dateSectionList = [];
        $("#leaseResultBody").find('tr').each(function () {
            var section = {};
            section.id = $(this).attr('data-id');
            section.netProfit = $(this).find('[data-name=netProfit]').text();
            section.correctionFactor = $(this).find('[data-name=correctionFactor]').text();
            section.presentValueFactor = $(this).find('[data-name=presentValueFactor]').text();
            section.incomePrice = $(this).find('[data-name=incomePrice]').text();
            formData.dateSectionList.push(section);
        })
        return formData;
    }
</script>
<%--测算--%>
<script type="text/javascript">
    //计算净收益
    lease.computeNetProfit = function () {
        $("#leaseResultBody").find('tr').each(function () {
            var incomeTotal = $(this).find('[data-name=incomeTotal]').text();
            var costTotal = $(this).find('[data-name=costTotal]').text();
            if (!AssessCommon.isNumber(incomeTotal)) return false;
            if (!AssessCommon.isNumber(costTotal)) return false;
            incomeTotal = parseFloat(incomeTotal);
            costTotal = parseFloat(costTotal);
            //净收益
            var netProfit = incomeTotal - costTotal;
            $(this).find('[data-name=netProfit]').text(netProfit.toFixed(2));
        })

        lease.computePrice();
    }

    //计算年期修正系数[(h)=(1-((1+g)/(1+r))^n]、收益现值系数[(k)=h/(r-g)]、房地产收益价格[房地产收益价格*收益现值系数]
    lease.computePrice = function () {
        var r = $("#frm_lease").find('[name=rewardRate]').attr('data-value');//报酬率
        if (!AssessCommon.isNumber(r)) return false;
        r = parseFloat(r);
        var incomePriceTotal = 0;//收益价格合计
        $("#leaseResultBody").find('tr').each(function () {
            var n = $(this).find('[data-name=yearCount]').text();
            if (!AssessCommon.isNumber(n)) return false;
            n = parseInt(n);//期限

            var g = $(this).find('[data-name=rentalGrowthRate]').val();
            if (!AssessCommon.isNumber(g)) return false;
            g = parseFloat(g);//租金增长率

            var h = (1 - Math.pow((1 + g) / (1 + r), n)).toFixed(6);//年期修正系数
            if (h <= 0) return false;
            var k = (h / (r - g)).toFixed(6);//收益现值系数

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
        $("#leaseResult").find('[data-name=price]').text(incomePriceTotal.toFixed(2));
    }
</script>



