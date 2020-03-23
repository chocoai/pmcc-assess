<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    ${judgeObject.name}
                    <small>(${judgeObject.evaluationArea}㎡)</small>
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="frm_income" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${empty mdIncome?0: mdIncome.id}">
                <input type="hidden" name="operationMode" value="${empty mdIncome?0: mdIncome.operationMode}">
                <input type="hidden" name="formType" value="${empty mdIncome?0: mdIncome.formType}">
                <input type="hidden" name="name" value="${judgeObject.name}">

                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                房产剩余使用年限
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${houseSurplusYear}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                土地剩余使用年限
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${landSurplusYear}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                经营方式
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" name="operationMode" id="operationMode1" class="form-radio-input"
                                       onclick="incomeIndex.operationModeChange(this);" readonly="readonly"
                                       value="1"><label class="form-radio-sign" for="operationMode1">租赁</label>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" required name="operationMode" class="form-radio-input"
                                       id="operationMode0"  readonly="readonly"
                                       onclick="incomeIndex.operationModeChange(this);"
                                       value="0">
                                <label class="form-radio-sign" for="operationMode0">自营</label>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row form-group" id="group_FormType" style="display: none;">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                表单类型
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" checked="checked" name="formType" id="formType0"
                                       class="form-radio-input" readonly="readonly"
                                       onchange="incomeIndex.formTypeChange(this);" value="0">
                                <label class="form-radio-sign" for="formType0">默认</label>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" name="formType" id="formType1" value="1" class="form-radio-input"
                                       readonly="readonly"    onchange="incomeIndex.formTypeChange(this);">
                                <label class="form-radio-sign" for="formType1">餐饮、酒店、宾馆</label>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row form-group" id="group_leaseMode" style="display: none;">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                租赁限制
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" required name="leaseMode" class="form-radio-input"
                                       id="leaseMode0" readonly="readonly"
                                       value="0"><label class="form-radio-sign" for="leaseMode0">限制</label>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" checked="checked" name="leaseMode" class="form-radio-input"
                                      readonly="readonly"  id="leaseMode1" value="1">
                                <label class="form-radio-sign" for="leaseMode1">无限制</label>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                租约限制说明
                            </label>
                            <div class="col-sm-8">
                                <label class="form-control input-full">${mdIncome.restrictionExplain}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                租约限制附件
                            </label>
                            <div class="col-sm-2">
                                <div id="_restrictionExplainEnclosure"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="col-md-12" id="panel_date_section" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    时间分段
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
                <table class="table table-bordered" id="tb_data_section_list">
                </table>
            </form>
        </div>
    </div>
</div>


<jsp:include page="/views/method/module/income/selfSupport/detail/info.jsp"></jsp:include>
<jsp:include page="/views/method/module/income/leaseView.jsp"></jsp:include>
<jsp:include page="/views/project/tool/rewardRateDetail.jsp"></jsp:include>
<script type="text/javascript">
    $(function () {
        if ("${mdIncome.id}" > 0) {
            $("#frm_income").find("[name=operationMode][value=${mdIncome.operationMode}]").trigger('click');
            $("#frm_income").find("[name=leaseMode][value=${mdIncome.leaseMode}]").trigger('click');
            $("#frm_income").find("[name=formType][value=${mdIncome.formType}]").trigger('click');
            $("#frm_income").find(':radio').attr('disabled', 'disabled');
        }
        $.each(incomeIndex.fileArrayId, function (i, n) {
            incomeIndex.showFile(n, AssessDBKey.MdIncome, incomeIndex.isNotBlank('${mdIncome}') ? '${mdIncome.id}' : "0", false, n);
        });
    })
    var incomeIndex = {};

    incomeIndex.fileArrayId = ["restrictionExplainEnclosure", "report_file"];

    incomeIndex.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    incomeIndex.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName
            },
            deleteFlag: deleteFlag
        })
    };

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
        $("#tb_data_section_list").bootstrapTable('destroy');
        TableInit("tb_data_section_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            incomeId: $("#frm_income").find('[name=id]').val(),
            operationMode: operationMode
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

    //经营方式切换
    incomeIndex.operationModeChange = function (_this) {
        $("#panel_date_section").show();
        incomeIndex.loadDateSectionList($(_this).val());//加载时间分段

        if ($(_this).val() == 0) {
            $(".self_support_info,#group_FormType").show();
            $("#group_leaseMode,#group_restriction_explain,.lease_info").hide();
            switch (incomeIndex.getFormType()) {
                case "0":
                    selfSupportForecast.loadHistoryList(0);
                    selfSupportForecast.loadHistoryList(1);
                    selfSupportForecast.loadForecastAnalyseList(0);
                    selfSupportForecast.loadCostForecastAnalyseList(1);
                    break;
                case "1":
                    forecastRestaurant.loadHistoryList(0);
                    forecastRestaurant.loadHistoryList(1);
                    forecastRestaurant.loadForecastAnalyseList(0);
                    forecastRestaurant.loadCostForecastAnalyseList(1);
                    break;
            }
            selfSupport.loadForecastIncomeList();
            selfSupport.loadForecastCostList();
            selfSupport.loadCalculationResult();
        } else if ($(_this).val() == 1) {
            $(".self_support_info,#group_FormType").hide();
            $("#group_leaseMode,.lease_info").show();
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

    //表单类型切换
    incomeIndex.formTypeChange = function (_this) {
        var value = $(_this).val();
        if (value == 0) {
            $("#ref_forecastIncome,#ref_forecastCost").show();
            $("#ref_forecastRestaurantIncome,#ref_forecastRestaurantCost").hide();
            selfSupportForecast.loadHistoryList(0);
            selfSupportForecast.loadHistoryList(1);
            selfSupportForecast.loadForecastAnalyseList(0);
            selfSupportForecast.loadCostForecastAnalyseList(1);
        } else if (value == 1) {
            $("#ref_forecastIncome,#ref_forecastCost").hide();
            $("#ref_forecastRestaurantIncome,#ref_forecastRestaurantCost").show();
            forecastRestaurant.loadHistoryList(0);
            forecastRestaurant.loadHistoryList(1);
            forecastRestaurant.loadForecastAnalyseList(0);
            forecastRestaurant.loadCostForecastAnalyseList(1);
        }
    }
    //获取经营方式
    incomeIndex.getOperationMode = function () {
        return $("#frm_income").find('[name=operationMode]').val();
    }

    //获取表单类型
    incomeIndex.getFormType = function () {
        return $("#frm_income").find('[name=formType]').val();
    }

    //获取incomeId
    incomeIndex.getInComeId = function () {
        return $("#frm_income").find('[name=id]').val();
    }

</script>





