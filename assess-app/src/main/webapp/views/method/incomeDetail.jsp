<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>收益法</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_income" class="form-horizontal" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${empty mdIncome?0: mdIncome.id}">
            <input type="hidden" name="operationMode" value="${empty mdIncome?0: mdIncome.operationMode}">
            <input type="hidden" name="formType" value="${empty mdIncome?0: mdIncome.formType}">
            <input type="hidden" name="name" value="${judgeObject.name}">
            <%--<div class="form-group">--%>
                <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">--%>
                        <%--房产剩余使用年限--%>
                    <%--</label>--%>
                    <%--<div class="col-sm-3">--%>
                        <%--<label class="form-control">${houseSurplusYear}</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">--%>
                        <%--土地剩余使用年限--%>
                    <%--</label>--%>
                    <%--<div class="col-sm-3">--%>
                        <%--<label class="form-control">${landSurplusYear}</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    经营方式
                </label>
                <div class="col-sm-2 col-sm-offset-1">
                    <span class="radio-inline">
                             <input type="radio" required name="operationMode" readonly="readonly"
                                    id="operationMode0" onclick="incomeIndex.operationModeChange(this);" value="0">
                        <label for="operationMode0">自营</label></span>
                </div>
                <div class="col-sm-2 col-sm-offset-1">
                         <span class="radio-inline"><input type="radio" name="operationMode" id="operationMode1"
                                                           readonly="readonly"
                                                           onclick="incomeIndex.operationModeChange(this);"
                                                           value="1"><label
                                 for="operationMode1">租赁</label></span>
                </div>
            </div>
            <div class="form-group" id="group_FormType" style="display: none;">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        表单类型
                    </label>
                    <div class="col-sm-2 col-sm-offset-1">
                    <span class="radio-inline">
                        <input type="radio" checked="checked" name="formType" id="formType0"
                               onchange="incomeIndex.formTypeChange(this);" value="0">
                        <label for="formType0">默认</label></span>
                    </div>
                    <div class="col-sm-2">
                        <span class="radio-inline">
                            <input type="radio" name="formType" id="formType1" value="1"
                                   onchange="incomeIndex.formTypeChange(this);">
                            <label for="formType1">餐饮、酒店、宾馆</label></span>
                    </div>
                </div>
            </div>
            <div class="form-group" id="group_leaseMode" style="display: none;">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        租赁限制
                    </label>
                    <div class="col-sm-2 col-sm-offset-1">
                        <span class="radio-inline"><input type="radio" required name="leaseMode" id="leaseMode0"
                                                          readonly="readonly"
                                                          onchange="incomeIndex.leaseModeChange(this);" value="0"><label
                                for="leaseMode0">限制</label></span>
                    </div>
                    <div class="col-sm-2 col-sm-offset-1">
                            <span class="radio-inline"><input type="radio" name="leaseMode" id="leaseMode1" value="1"
                                  readonly="readonly"
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
        <h3>时间分段</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <table class="table table-bordered" id="tb_data_section_list">
        </table>
    </div>
</div>
<jsp:include page="/views/method/module/income/selfSupport/detail/info.jsp"></jsp:include>
<jsp:include page="/views/method/module/income/leaseView.jsp"></jsp:include>

<script type="text/javascript">
    $(function () {
        if ("${mdIncome.id}" > 0) {
            $("#frm_income").find("[name=operationMode][value=${mdIncome.operationMode}]").trigger('click');
            $("#frm_income").find("[name=leaseMode][value=${mdIncome.leaseMode}]").trigger('click');
            $("#frm_income").find("[name=formType][value=${mdIncome.formType}]").trigger('click');
            $("#frm_income").find(':radio').attr('disabled', 'disabled');
        }
    })
    var incomeIndex = {};

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
            $("#self_support_info,#group_FormType").show();
            $("#group_leaseMode,#group_restriction_explain,#lease_info").hide();
            switch (incomeIndex.getFormType()) {
                case "0":
                    selfSupportForecast.loadHistoryList(0);
                    selfSupportForecast.loadHistoryList(1);
                    selfSupportForecast.loadForecastAnalyseList(0);
                    selfSupportForecast.loadForecastAnalyseList(1);
                    break;
                case "1":
                    forecastRestaurant.loadHistoryList(0);
                    forecastRestaurant.loadHistoryList(1);
                    forecastRestaurant.loadForecastAnalyseList(0);
                    forecastRestaurant.loadForecastAnalyseList(1);
                    break;
            }
            selfSupport.loadForecastList(0);
            selfSupport.loadForecastList(1);
            selfSupport.loadCalculationResult();
        } else if ($(_this).val() == 1) {
            $("#self_support_info,#group_FormType").hide();
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

    //表单类型切换
    incomeIndex.formTypeChange = function (_this) {
        var value = $(_this).val();
        if (value == 0) {
            $("#ref_forecastIncome,#ref_forecastCost").show();
            $("#ref_forecastRestaurantIncome,#ref_forecastRestaurantCost").hide();
            selfSupportForecast.loadHistoryList(0);
            selfSupportForecast.loadHistoryList(1);
            selfSupportForecast.loadForecastAnalyseList(0);
            selfSupportForecast.loadForecastAnalyseList(1);
        } else if (value == 1) {
            $("#ref_forecastIncome,#ref_forecastCost").hide();
            $("#ref_forecastRestaurantIncome,#ref_forecastRestaurantCost").show();
            forecastRestaurant.loadHistoryList(0);
            forecastRestaurant.loadHistoryList(1);
            forecastRestaurant.loadForecastAnalyseList(0);
            forecastRestaurant.loadForecastAnalyseList(1);
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





