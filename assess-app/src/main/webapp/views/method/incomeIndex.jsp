<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    ${judgeObject.name}
                    <small>(${judgeObject.evaluationArea}㎡)</small>
                        <small>
                            <button class="btn btn-sm btn-primary" data-toggle="modal" type="button"
                                    href="#boxSchemeInfoModel"
                                    onclick="incomeIndex.loadSchemeInfoTableList({projectId:'${projectPlanDetails.projectId}',methodDataId:'${mdIncome.id}',methodType:'${methodTypeObj.id}'},'incomeIndex.selectFun');">
                                引用
                            </button>
                        </small>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="frm_income" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${empty mdIncome?0: mdIncome.id}">
                <input type="hidden" name="name" value="${judgeObject.name}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                房产剩余使用年限
                            </label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control input-full" name="houseRemainingYear"
                                       readonly="readonly" value="${houseSurplusYear}">
                            </div>
                            <label class="col-sm-1 control-label">
                                土地剩余使用年限
                            </label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control input-full" name="landRemainingYear"
                                       readonly="readonly" value="${landSurplusYear}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                经营方式<span class="symbol required"></span>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" name="operationMode" id="operationMode1" class="form-radio-input"
                                       onclick="incomeIndex.operationModeChange(this);"
                                       value="1"><label class="form-radio-sign" for="operationMode1">租赁</label>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" required name="operationMode" class="form-radio-input"
                                       id="operationMode0"
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
                                表单类型<span class="symbol required"></span>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" checked="checked" name="formType" id="formType0"
                                       class="form-radio-input"
                                       onchange="incomeIndex.formTypeChange(this);" value="0">
                                <label class="form-radio-sign" for="formType0">默认</label>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" name="formType" id="formType1" value="1" class="form-radio-input"
                                       onchange="incomeIndex.formTypeChange(this);">
                                <label class="form-radio-sign" for="formType1">餐饮、酒店、宾馆</label>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row form-group" id="group_leaseMode" style="display: none;">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                租赁限制<span class="symbol required"></span>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" required name="leaseMode" class="form-radio-input"
                                       id="leaseMode0"
                                       value="0"><label class="form-radio-sign" for="leaseMode0">限制</label>
                            </label>
                            <label class="form-radio-label col-sm-1">
                                <input type="radio" checked="checked" name="leaseMode" class="form-radio-input"
                                       id="leaseMode1" value="1"><label class="form-radio-sign"
                                                                        for="leaseMode1">无限制</label>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                租约限制说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-8">
                                <textarea name="restrictionExplain" class="form-control input-full"
                                          required>${empty mdIncome?'': mdIncome.restrictionExplain}</textarea>
                            </div>
                            <label class="col-sm-1 control-label">
                                租约限制附件
                            </label>
                            <div class="col-sm-2">
                                <input id="restrictionExplainEnclosure" placeholder="上传附件"
                                       name="restrictionExplainEnclosure" class="form-control input-full" type="file">
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
                <p>
                    <a class="btn btn-success btn-sm" data-toggle="modal" href="#modal_data_section"
                       onclick="incomeIndex.addDateSection();">
                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </a>
                </p>
                <table class="table table-bordered" id="tb_data_section_list">
                </table>
            </form>
        </div>
    </div>
</div>


<jsp:include page="/views/method/module/income/selfSupport/apply/info.jsp"></jsp:include>
<jsp:include page="/views/method/module/income/lease.jsp"></jsp:include>
<jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>

<div id="modal_data_section" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">时间段</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_data_section" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                开始时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" required name="beginDate" placeholder="开始时间"
                                                       data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"
                                                       readonly="readonly">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                结束时间
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="endDate" placeholder="结束时间"
                                                       data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"
                                                       readonly="readonly">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="incomeIndex.saveDateSection()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<script type="text/javascript">
    $(function () {
        DatepickerUtils.sectionChoose($("#frm_data_section").find('[name=beginDate]'), $("#frm_data_section").find('[name=endDate]'));
        if ("${mdIncome.id}" > 0) {
            $("#frm_income").find("[name=operationMode][value=${mdIncome.operationMode}]").trigger('click');
            $("#frm_income").find("[name=leaseMode][value=${mdIncome.leaseMode}]").trigger('click');
            $("#frm_income").find("[name=formType][value=${mdIncome.formType}]").trigger('click');
        }
        $.each(incomeIndex.fileArrayId, function (i, n) {
            incomeIndex.showFile(n, AssessDBKey.MdIncome, incomeIndex.isNotBlank('${mdIncome}') ? '${mdIncome.id}' : "0", true, n);
            incomeIndex.fileUpload(n, AssessDBKey.MdIncome, incomeIndex.isNotBlank('${mdIncome}') ? '${mdIncome.id}' : "0", true, n);
        });
    });
    var incomeIndex = {};
    incomeIndex.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    incomeIndex.fileArrayId = ["restrictionExplainEnclosure", "report_file"];

    incomeIndex.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName
            },
            deleteFlag: deleteFlag
        });
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
        data.incomeId = incomeIndex.getInComeId();
        data.operationMode = incomeIndex.getOperationMode();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveDateSection",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess('成功','保存成功');
                    incomeIndex.loadDateSectionList(data.operationMode);
                    if (data.operationMode == 0) {
                        selfSupport.loadForecastIncomeList();
                        selfSupport.loadForecastCostList();
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
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
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
                str += '<button type="button" onclick="incomeIndex.editDateSection(' + index + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="incomeIndex.delDateSection(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_data_section_list").bootstrapTable('destroy');
        TableInit("tb_data_section_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            incomeId: incomeIndex.getInComeId(),
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

    //删除时间段
    incomeIndex.delDateSection = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteDateSection",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        incomeIndex.loadDateSectionList(incomeIndex.getOperationMode());

                        //刷新从表信息
                        var operationMode = incomeIndex.getOperationMode();
                        if (operationMode == 0) {
                            selfSupport.loadForecastIncomeList();
                            selfSupport.loadForecastCostList();
                            selfSupport.loadCalculationResult();
                        }

                        if (operationMode == 1) {
                            lease.loadLeaseList();
                            lease.loadLeaseCostList();
                            lease.loadLeaseParameterList();
                            lease.loadCalculationResult();
                        }

                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
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
        return $("#frm_income").find('[name=operationMode]:checked').val();
    }

    //获取表单类型
    incomeIndex.getFormType = function () {
        return $("#frm_income").find('[name=formType]:checked').val();
    }

    //获取incomeId
    incomeIndex.getInComeId = function () {
        return $("#frm_income").find('[name=id]').val();
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
        var operationMode = incomeIndex.getOperationMode();
        if (operationMode == 0) {//自营
            formData = selfSupport.getData();
        }
        if (operationMode == 1) {//租赁
            formData = lease.getData();
        }
        return formData;
    }


    incomeIndex.selectFun = function (copyId, box) {
        var target = $("#" + box);
        AlertConfirm("是否确认引用", "引用后可继续根据实际情况来编辑", function () {
            incomeIndex.ajaxServerMethod({
                sourceId: copyId,
                targetId: '${mdIncome.id}'
            }, "/income/pasteMdIncome", "post", function () {
                notifySuccess("成功", "引用数据成功!");
                window.location.reload(true); //强制从服务器重新加载当前页面
                target.modal("hide");
            });
        });
    };

    incomeIndex.ajaxServerMethod = function (data, url, type, callback) {
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            },
            error: function (result) {
                if (result.errmsg) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        });
    };

    incomeIndex.loadSchemeInfoTableList = function (quarm, callbackName) {
        var table = $("#boxSchemeInfoList");
        var cols = [];
        cols.push({
            field: 'id', title: '测算方法', width: "20%", formatter: function (value, row, index) {
                var str = '';
                str += row.judgeObjectName + "-";
                str += row.methodName;
                return str;
            }
        });
        cols.push({
            field: 'gmtModified', title: '最后修改日期', width: "20%", formatter: function (value, row, index) {
                return formatDate(value);
            }
        });

        cols.push({
            field: 'gmtCreated', title: '测算方法创建日期', width: "20%", formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'id', title: '引用', width: "20%", formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" class="btn btn-xs btn-success" onclick="{method}(' + row.methodDataId + "," + "'boxSchemeInfoModel'" + ');" >引用 <i class="fa fa-check-circle"></i></button>';
                str += '';
                str = str.replace(/{method}/g, callbackName);
                return str;
            }
        });
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {//加载成功时执行
                // table.bootstrapTable('filterBy', {
                //     methodDataId: [itemId]
                // })
            },
            onLoadError: function () {

            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, getContextPath() + "/schemeInfo/getSchemeIncomeVo", cols, quarm, method);
    };
</script>





