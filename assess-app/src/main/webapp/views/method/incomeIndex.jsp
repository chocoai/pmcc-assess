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
            <input type="hidden" name="id" value="${empty mdIncome?0: mdIncome.id}">
            <input type="hidden" name="name" value="${judgeObject.name}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        房产剩余使用年限
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${houseSurplusYear}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        土地剩余使用年限
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${landSurplusYear}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    经营方式<span class="symbol required"></span>
                </label>
                <div class="col-sm-2 col-sm-offset-1">
                    <div class="x-valid">
                         <span class="radio-inline"><input type="radio" required name="operationMode"
                                                           id="operationMode0"
                                                           onclick="incomeIndex.operationModeChange(this);"
                                                           value="0">
                        <label for="operationMode0">自营</label></span>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="x-valid">
                        <span class="radio-inline"><input type="radio" name="operationMode" id="operationMode1"
                                                          onclick="incomeIndex.operationModeChange(this);"
                                                          value="1"><label
                                for="operationMode1">租赁</label></span>
                    </div>
                </div>
            </div>
            <div class="form-group" id="group_leaseMode" style="display: none;">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        租赁限制<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2 col-sm-offset-1">
                    <span class="radio-inline"><input type="radio" required name="leaseMode" id="leaseMode0"
                                                      onchange="incomeIndex.leaseModeChange(this);" value="0"><label
                            for="leaseMode0">限制</label></span>
                    </div>
                    <div class="col-sm-2">
                        <span class="radio-inline"><input type="radio" name="leaseMode" id="leaseMode1" value="1"
                                                          onchange="incomeIndex.leaseModeChange(this);"><label
                                for="leaseMode1">无限制</label></span>
                    </div>
                </div>
            </div>
            <div class="form-group" id="group_restriction_explain" style="display: none;">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        租约限制说明<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-11">
                        <textarea name="restrictionExplain" class="form-control" required></textarea>
                    </div>
                </div>
            </div>
        </form>


    </div>
</div>
<div class="x_panel" id="panel_date_section" style="display: none;">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>时间分段</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <button class="btn btn-success" data-toggle="modal"
                onclick="incomeIndex.addDateSection();">
            新增
        </button>
        <table class="table table-bordered" id="tb_data_section_list">
        </table>
    </div>
</div>
<jsp:include page="/views/method/module/income/selfSupport.jsp"></jsp:include>
<jsp:include page="/views/method/module/income/lease.jsp"></jsp:include>
<jsp:include page="/views/method/module/income/rewardRate.jsp"></jsp:include>
<div id="modal_data_section" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">时间段</h3>
            </div>
            <form id="frm_data_section" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                开始时间<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" required name="beginDate" placeholder="开始时间"
                                       data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                结束时间<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" required name="endDate" placeholder="结束时间"
                                       data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                       readonly="readonly">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="incomeIndex.saveDateSection();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        DatepickerUtils.sectionChoose($("#frm_data_section").find('[name=beginDate]'), $("#frm_data_section").find('[name=endDate]'));
        if ("${mdIncome.id}" > 0) {
            $("#frm_income").find("[name=operationMode][value=${mdIncome.operationMode}]").trigger('click');
            $("#frm_income").find("[name=leaseMode][value=${mdIncome.leaseMode}]").trigger('click');
        }
    })
    var incomeIndex = {};

    //新增时间段
    incomeIndex.addDateSection = function () {
        $("#frm_data_section").clearAll();
        $("#modal_data_section").modal();
    }

    //编辑时间段
    incomeIndex.editDateSection = function (index) {
        var row = $("#tb_data_section_list").bootstrapTable('getData')[index];
        $("#frm_data_section").clearAll();
        $("#frm_data_section").initForm(row);
        $("#frm_data_section").find('[name=beginDate]').val(formatDate(row.beginDate, false));
        $("#frm_data_section").find('[name=endDate]').val(formatDate(row.endDate, false));
        $("#modal_data_section").modal();
    }

    //保存时间段
    incomeIndex.saveDateSection = function () {
        if (!$("#frm_data_section").valid()) {
            return false;
        }
        var data = formParams("frm_data_section");
        data.incomeId = $("#frm_income").find('[name=id]').val();
        data.operationMode = $("#frm_income").find('[name=operationMode]:checked').val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveDateSection",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    incomeIndex.loadDateSectionList(data.operationMode);
                    if (data.operationMode == 0) {
                        selfSupport.loadForecastList(0);
                        selfSupport.loadForecastList(1);
                        selfSupport.loadCalculationResult();
                    }
                    if (data.operationMode == 1) {
                        lease.loadLeaseList();
                        lease.loadLeaseCostList();
                        lease.loadLeaseParameterList();
                        lease.loadCalculationResult();
                    }
                    $('#modal_data_section').modal('hide');
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

    //加载时间段列表信息
    incomeIndex.loadDateSectionList = function (operationMode) {
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
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="incomeIndex.delDateSection(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_data_section_list").bootstrapTable('destroy');
        TableInit("tb_data_section_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            incomeId: $("#frm_income").find('[name=id]').val(),
            operationMode: operationMode
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //删除时间段
    incomeIndex.delDateSection = function (id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteDateSection",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        incomeIndex.loadDateSectionList($("#frm_income").find('[name=operationMode]:checked').val());

                        //刷新从表信息
                        var operationMode = $("#frm_income").find('[name=operationMode]:checked').val();
                        if (operationMode == 0) {
                            selfSupport.loadForecastList(0);
                            selfSupport.loadForecastList(1);
                        }

                        if (operationMode == 1) {
                            lease.loadLeaseList();
                            lease.loadLeaseCostList();
                            lease.loadLeaseParameterList();
                            lease.loadCalculationResult();
                        }

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


    //经营方式切换
    incomeIndex.operationModeChange = function (_this) {
        $("#panel_date_section").show();
        incomeIndex.loadDateSectionList($(_this).val());//加载时间分段

        if ($(_this).val() == 0) {
            $("#self_support_info").show();
            $("#group_leaseMode,#group_restriction_explain,#lease_info").hide();
            selfSupport.loadHistoryList(0);
            selfSupport.loadHistoryList(1);
            selfSupport.loadForecastList(0);
            selfSupport.loadForecastList(1);
            selfSupport.loadCalculationResult();
        } else if ($(_this).val() == 1) {
            $("#self_support_info").hide();
            $("#group_leaseMode,#lease_info").show();
            lease.loadLeaseList();
            lease.loadLeaseCostList();
            lease.loadLeaseParameterList();
            lease.loadCalculationResult();
        }
    }

    //租赁方式切换
    incomeIndex.leaseModeChange = function (_this) {
        var value = $(_this).val();
        if (value == 0) {
            $("#group_restriction_explain").show();
        } else if (value == 1) {
            $("#group_restriction_explain").hide();
        }
    }

    //表单验证
    incomeIndex.valid = function () {
        if (!$("#frm_income").valid()) {
            return false;
        }
        if (!$("#frm_self_support").valid()) {
            return false;
        }
        if (!$("#frm_lease").valid()) {
            return false;
        }
        return true;
    }

    //获取表单数据
    incomeIndex.getData = function () {
        var formData = {};
        var operationMode = $("#frm_income").find('[name=operationMode]:checked').val();
        if (operationMode == 0) {//自营
            formData = selfSupport.getData();
        }
        if (operationMode == 1) {//租赁
            formData = lease.getData();
        }
        return formData;
    }

</script>





