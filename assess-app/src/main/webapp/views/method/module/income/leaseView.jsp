<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    收入类
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <table class="table table-bordered" id="tb_lease_income_list">
                </table>
            </form>
        </div>
    </div>
</div>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    成本类
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <table class="table table-bordered" id="tb_lease_cost_list">
                </table>
            </form>
        </div>
    </div>
</div>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    参数
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="frm_lease" class="form-horizontal" enctype="multipart/form-data">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label" title="报酬率">
                                报酬率
                            </label>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" required class="form-control x-percent" name="rewardRate"
                                           placeholder="报酬率" readonly="readonly"
                                           data-value="${mdIncome.rewardRate}">
                                    <span class="input-group-btn">
                                     <input type="button" class="btn btn-primary" value="报酬率"
                                            onclick="rewardRateDetail.calculationDetail('${mdIncome.rewardRateId}');"/>
                                </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tb_lease_parameter_list">
                        </table>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    测算结果
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="leaseResult" class="form-horizontal">

                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                估价对象的价格
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full" data-name="price">${mdIncome.price}</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-12">
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
            </form>
        </div>
    </div>
</div>

<div id="modal_lease_income" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">收入</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_lease_income" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <input type="hidden" name="mcId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                月租金收入(元/m²)
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <label class="form-control" data-name="rentalIncome"></label>
                                                    <div class="input-group-prepend">
                                                        <input type="button" class="btn btn-primary" value="市场比较法"
                                                               id="btnViewCompare"
                                                               onclick="lease.viewCompareMethod(this);"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                全年月份数
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" data-name="monthNumber"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                月租金收入说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <label class="form-control input-full"
                                                       data-name="rentalIncomeRemark"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                出租率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       data-name="rentals"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                出租率说明
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full"
                                                       data-name="rentalsRemark"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                年押金(元/m²)
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" data-name="deposit"></label>
                                            </div>

                                            <label class="col-sm-2 control-label">
                                                押金说明
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full"
                                                       data-name="depositRemark"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                押金利率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       data-name="depositRate"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                利率说明
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full"
                                                       data-name="depositRateRemark"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                年其他收入
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" data-name="otherIncome"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                其它收入说明
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full"
                                                       data-name="otherIncomeRemark"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                有效收缴率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       data-name="additionalCapture"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                有效收缴率说明
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full"
                                                       data-name="additionalCaptureRemark"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
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
                <h4 class="modal-title">成本</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_lease_cost" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                管理费率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="managementCostRatio"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                重置价格(元/m²)
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="replacementValue"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                维护保养费率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="maintenanceCostRatio"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                保险费率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="insurancePremiumRatio"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                土地使用税
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="landUseTax"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                其它相关税费率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="transactionTaxeFeeRatio"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                其它相关税费率说明
                                            </label>
                                            <div class="col-sm-10">
                                                <label class="form-control input-full"
                                                       name="transactionTaxeFeeExplain"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                房产税率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="propertyTaxRatio"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                印花税率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="stampDutyRatio"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                增值税率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="salesTaxRatio"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                城建税率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="constructionTaxRatio"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                地方教育费附加税率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="localEducationRatio"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                教育费附加税率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="educationRatio"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                租赁税费率
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full v-percent"
                                                       name="additionalRatio"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
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


    //查看收入信息
    lease.viewLeaseIncome = function (index) {
        var row = $("#tb_lease_income_list").bootstrapTable('getData')[index];
        $("#frm_lease_income").initForm(row).find('label').each(function () {
            if ($(this).hasClass('v-percent')) {
                $(this).text(AssessCommon.pointToPercent(row[$(this).attr('data-name')]));
            } else {
                $(this).text(row[$(this).attr('data-name')]);
            }
        })
        if ($("#frm_lease_income").find('[name=mcId]').val() > 0) {
            $("#btnViewCompare").removeAttr("disabled");
        } else {
            $("#btnViewCompare").attr("disabled", "disabled");
        }
        $('#modal_lease_income').modal();
    }

    lease.viewCompareMethod = function (_this) {
        var mcId = $("#frm_lease_income").find('[name=mcId]').val();
        var frame = layer.open({
            type: 2,
            title: '市场比较法',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/marketCompare/index?mcId=' + mcId + '&judgeObjectId=${projectPlanDetails.judgeObjectId}&readonly=true',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    $(_this).closest('form').find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            },
            btnAlign: 'c',
            btn: ['关闭'],
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    $(_this).closest('form').find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            }
        });
        layer.full(frame);
    }

    //查看成本信息
    lease.viewLeaseCost = function (index) {
        var row = $("#tb_lease_cost_list").bootstrapTable('getData')[index];
        $("#frm_lease_cost").find('label').each(function () {
            if ($(this).hasClass('v-percent')) {
                $(this).text(AssessCommon.pointToPercent(row[$(this).attr('name')]));
            } else {
                $(this).text(row[$(this).attr('name')]);
            }
        })
        $('#modal_lease_cost').modal();
    }

    //加载收入列表信息
    lease.loadLeaseList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
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
        cols.push({field: 'otherIncome', title: '年其他收入'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="lease.viewLeaseIncome(' + index + ');" ><i class="fa fa-search fa-white"></i></button>';
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
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载成本列表信息
    lease.loadLeaseCostList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
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
        cols.push({field: 'replacementValue', title: '重置价格'});
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
                str += '<button type="button" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="lease.viewLeaseCost(' + index + ');" ><i class="fa fa-search fa-white"></i></button>';
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
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
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


    //加载参数列表信息
    lease.loadLeaseParameterList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({
            field: 'rentalGrowthRate', title: '租金增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'rentalGrowthRateExplain', title: '增长率说明'});
        $("#tb_lease_parameter_list").bootstrapTable('destroy');
        TableInit("tb_lease_parameter_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            operationMode: incomeIndex.getOperationMode(),
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
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
                incomeId: incomeIndex.getInComeId(),
                limit: 100,
                operationMode: incomeIndex.getOperationMode()
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
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

</script>



