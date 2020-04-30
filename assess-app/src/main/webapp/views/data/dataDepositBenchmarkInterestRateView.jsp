<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-3 p-0">
                                            <input name="startTime"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-3 p-0">
                                            <input name="endTime"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataDeveloper.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                onclick="dataDeveloper.prototype.showModel()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                        <button type="button" class="btn btn-info btn-sm"
                                                style="margin-left: 5px"
                                                onclick="$('#importDataLandFileId').trigger('click')">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-upload-alt"></i>
                                                    </span>
                                        </button>
                                        <input type="file" style="display: none;" name="importDataLandFileId"
                                               id="importDataLandFileId"
                                               onchange="dataDeveloper.prototype.importDataLand('importDataLandFileId');">
                                        <button type="button" class="btn btn-success btn-sm"
                                                style="margin-left: 5px"
                                                onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpDataDepositBenchmarkInterestRateTemplate);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-download-alt"></i>
                                                    </span>
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

</body>

<script type="text/javascript">
    $(function () {
        dataDeveloper.prototype.loadDataDicList();
    });


    var dataDeveloper = function () {

    };
    dataDeveloper.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({
                field: 'adjustTime',
                width: '5%',
                title: '利率调整时间',
                formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({
                field: 'demandDeposit',
                width: '5%',
                title: '活期存款 利率',
                formatter: function (value, row, index) {
                    return AssessCommon.pointToPercent(value);
                }
            });
            cols.push({
                field: 'id', width: '30%', title: '整存整取', formatter: function (value, row, index) {
                    var result = '';
                    if (row.depositWithdrawLumpSumThreeMonth) {
                        result += '三个月：' + AssessCommon.pointToPercent(row.depositWithdrawLumpSumThreeMonth) + '<br/>';
                    }
                    if (row.depositWithdrawLumpSumHalfYear) {
                        result += '半年：' + AssessCommon.pointToPercent(row.depositWithdrawLumpSumHalfYear) + '<br/>';
                    }
                    if (row.depositWithdrawLumpSumOneYear) {
                        result += '一年：' + AssessCommon.pointToPercent(row.depositWithdrawLumpSumOneYear) + '<br/>';
                    }
                    if (row.depositWithdrawLumpSumTwoYear) {
                        result += '二年：' + AssessCommon.pointToPercent(row.depositWithdrawLumpSumTwoYear) + '<br/>';
                    }
                    if (row.depositWithdrawLumpSumThreeYear) {
                        result += '三年：' + AssessCommon.pointToPercent(row.depositWithdrawLumpSumThreeYear) + '<br/>';
                    }
                    if (row.depositWithdrawLumpSumFiveYear) {
                        result += '五年：' + AssessCommon.pointToPercent(row.depositWithdrawLumpSumFiveYear) + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'id', width: '20%', title: '零存整取、整存零取、存本取息', formatter: function (value, row, index) {
                    var result = '';
                    if (row.depositInstallmentsWithdrawLumpSumOneYear) {
                        result += '一年：' + AssessCommon.pointToPercent(row.depositInstallmentsWithdrawLumpSumOneYear) + '<br/>';
                    }
                    if (row.depositInstallmentsWithdrawLumpSumTwoYear) {
                        result += '两年：' + AssessCommon.pointToPercent(row.depositInstallmentsWithdrawLumpSumTwoYear) + '<br/>';
                    }
                    if (row.depositInstallmentsWithdrawLumpSumThreeYear) {
                        result += '三年：' + AssessCommon.pointToPercent(row.depositInstallmentsWithdrawLumpSumThreeYear) + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'timeDemandOptionalDeposit',
                width: '5%',
                title: '定活两便 利率',
                formatter: function (value, row, index) {
                    return AssessCommon.pointToPercent(value);
                }
            });
            cols.push({
                field: 'agreementDeposit',
                width: '5%',
                title: '协定存款 利率',
                formatter: function (value, row, index) {
                    return AssessCommon.pointToPercent(value);
                }
            });
            cols.push({
                field: 'id', width: '20%', title: '通知存款', formatter: function (value, row, index) {
                    var result = '';
                    if (row.callDepositOneDay) {
                        result += '一天：' + AssessCommon.pointToPercent(row.callDepositOneDay) + '<br/>';
                    }
                    if (row.callDepositSevenDay) {
                        result += '七天：' + AssessCommon.pointToPercent(row.callDepositSevenDay) + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button type="button" onclick="dataDeveloper.prototype.editData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button type="button" onclick="dataDeveloper.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            var data = formSerializeArray($("#frmQuery"));
            $("#" + dataDeveloper.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataDeveloper.prototype.config().table, "${pageContext.request.contextPath}/dataDepositBenchmarkInterestRate/getBootstrapTableVo", cols, data, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataDepositBenchmarkInterestRate/deleteDataDepositBenchmarkInterestRate",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            dataDeveloper.prototype.loadDataDicList();
                        } else {
                            AlertError("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + dataDeveloper.prototype.config().frm).clearAll();
            $("#" + dataDeveloper.prototype.config().frm).validate();
            $('#' + dataDeveloper.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataDeveloper.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataDeveloper.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDepositBenchmarkInterestRate/saveAndUpdateDataDepositBenchmarkInterestRate",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data), updateNull: true},
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataDeveloper.prototype.config().box).modal('hide');
                        $("#" + dataDeveloper.prototype.config().table).bootstrapTable('refresh');
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        editData: function (id) {
            var frm = $("#" + dataDeveloper.prototype.config().frm);
            var data = $("#" + dataDeveloper.prototype.config().table).bootstrapTable('getRowByUniqueId', id);
            frm.clearAll();
            frm.initForm(data);
            frm.validate();
            $('#' + dataDeveloper.prototype.config().box).modal("show");
            frm.find('[name=demandDeposit]').attr('data-value', data.demandDeposit).val(AssessCommon.pointToPercent(data.demandDeposit));
            frm.find('[name=depositWithdrawLumpSumThreeMonth]').attr('data-value', data.depositWithdrawLumpSumThreeMonth).val(AssessCommon.pointToPercent(data.depositWithdrawLumpSumThreeMonth));
            frm.find('[name=depositWithdrawLumpSumHalfYear]').attr('data-value', data.depositWithdrawLumpSumHalfYear).val(AssessCommon.pointToPercent(data.depositWithdrawLumpSumHalfYear));
            frm.find('[name=depositWithdrawLumpSumOneYear]').attr('data-value', data.depositWithdrawLumpSumOneYear).val(AssessCommon.pointToPercent(data.depositWithdrawLumpSumOneYear));
            frm.find('[name=depositWithdrawLumpSumTwoYear]').attr('data-value', data.depositWithdrawLumpSumTwoYear).val(AssessCommon.pointToPercent(data.depositWithdrawLumpSumTwoYear));
            frm.find('[name=depositWithdrawLumpSumThreeYear]').attr('data-value', data.depositWithdrawLumpSumThreeYear).val(AssessCommon.pointToPercent(data.depositWithdrawLumpSumThreeYear));
            frm.find('[name=depositWithdrawLumpSumFiveYear]').attr('data-value', data.depositWithdrawLumpSumFiveYear).val(AssessCommon.pointToPercent(data.depositWithdrawLumpSumFiveYear));
            frm.find('[name=depositInstallmentsWithdrawLumpSumOneYear]').attr('data-value', data.depositInstallmentsWithdrawLumpSumOneYear).val(AssessCommon.pointToPercent(data.depositInstallmentsWithdrawLumpSumOneYear));
            frm.find('[name=depositInstallmentsWithdrawLumpSumTwoYear]').attr('data-value', data.depositInstallmentsWithdrawLumpSumTwoYear).val(AssessCommon.pointToPercent(data.depositInstallmentsWithdrawLumpSumTwoYear));
            frm.find('[name=depositInstallmentsWithdrawLumpSumThreeYear]').attr('data-value', data.depositInstallmentsWithdrawLumpSumThreeYear).val(AssessCommon.pointToPercent(data.depositInstallmentsWithdrawLumpSumThreeYear));
            frm.find('[name=timeDemandOptionalDeposit]').attr('data-value', data.timeDemandOptionalDeposit).val(AssessCommon.pointToPercent(data.timeDemandOptionalDeposit));
            frm.find('[name=agreementDeposit]').attr('data-value', data.agreementDeposit).val(AssessCommon.pointToPercent(data.agreementDeposit));
            frm.find('[name=callDepositOneDay]').attr('data-value', data.callDepositOneDay).val(AssessCommon.pointToPercent(data.callDepositOneDay));
            frm.find('[name=callDepositSevenDay]').attr('data-value', data.callDepositSevenDay).val(AssessCommon.pointToPercent(data.callDepositSevenDay));
        },
        importDataLand: function (fileElementId) {
            Loading.progressShow();
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/dataDepositBenchmarkInterestRate/importDataDepositBenchmarkInterestRate",
                data: {},//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: fileElementId,//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("导入情况", result.data);
                        $("#" + dataDeveloper.prototype.config().table).bootstrapTable('refresh');
                    } else {
                        if (result.errmsg) {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                        } else {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                        }
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            });
        }

    }
</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">存款基准利率</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                利率调整时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="adjustTime" required
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd" placeholder="利率调整时间"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                活期存款 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="demandDeposit"
                                                       class="form-control input-full x-percent"
                                                       placeholder="活期存款 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                整存整取 三个月 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositWithdrawLumpSumThreeMonth"
                                                       class="form-control input-full x-percent"
                                                       placeholder="整存整取 三个月 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                整存整取 半年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositWithdrawLumpSumHalfYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="整存整取 半年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                整存整取 一年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositWithdrawLumpSumOneYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="整存整取 一年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                整存整取 两年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositWithdrawLumpSumTwoYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="整存整取 两年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                整存整取 三年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositWithdrawLumpSumThreeYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="整存整取 三年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                整存整取 五年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositWithdrawLumpSumFiveYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="整存整取 五年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                零存整取 一年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositInstallmentsWithdrawLumpSumOneYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="零存整取 一年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                零存整取 两年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositInstallmentsWithdrawLumpSumTwoYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="零存整取 两年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                零存整取 三年 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="depositInstallmentsWithdrawLumpSumThreeYear"
                                                       class="form-control input-full x-percent"
                                                       placeholder="零存整取 三年 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                定活两便 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="timeDemandOptionalDeposit"
                                                       class="form-control input-full x-percent"
                                                       placeholder="定活两便 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                协定存款 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="agreementDeposit"
                                                       class="form-control input-full x-percent"
                                                       placeholder="协定存款 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                通知存款 一天 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="callDepositOneDay"
                                                       class="form-control input-full x-percent"
                                                       placeholder="通知存款 一天 利率"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                通知存款 七天 利率
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="callDepositSevenDay"
                                                       class="form-control input-full x-percent"
                                                       placeholder="通知存款 七天 利率"/>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataDeveloper.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
