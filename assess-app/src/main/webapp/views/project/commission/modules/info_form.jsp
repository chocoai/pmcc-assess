<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    项目提成
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_master_form" class="form-horizontal">
                <input type="hidden" name="id" value="${projectXlxCommission.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                发票号码
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="发票号码" name="invoiceNumber"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.invoiceNumber}">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                发票总金额
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="发票总金额" data-rule-number='true' name="invoiceTotalMoney"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.invoiceTotalMoney}">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                本项目金额
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="本项目金额" data-rule-number='true' name="projectMoney"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.projectMoney}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                收款确认
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="收款确认" name="paymentConfirmation"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.paymentConfirmation}">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                报告文号
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="报告文号" name="reportNumber"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.reportNumber}">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                未提成确定
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="未提成确定" name="notCommissionConfirm"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.notCommissionConfirm}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                未返佣确定
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="未返佣确定" name="notRebateConfirm"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.notRebateConfirm}">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                确定时间
                            </label>
                            <div class="col-sm-3">
                                <input placeholder="确定时间"
                                       name="confirmTime" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate" readonly="readonly"
                                       value="<fmt:formatDate value='${projectXlxCommission.confirmTime}' pattern='yyyy-MM-dd'/>">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                报告装订日
                            </label>
                            <div class="col-sm-3">
                                <input placeholder="报告装订日"
                                       name="reportBindDate" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate" readonly="readonly"
                                       value="<fmt:formatDate value='${projectXlxCommission.reportBindDate}' pattern='yyyy-MM-dd'/>">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                归档日期
                            </label>
                            <div class="col-sm-3">
                                <input placeholder="归档日期"
                                       name="pigeonholeDate" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate" readonly="readonly"
                                       value="<fmt:formatDate value='${projectXlxCommission.pigeonholeDate}' pattern='yyyy-MM-dd'/>">

                            </div>
                            <label class="col-sm-1 col-form-label">
                                逾期归档
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="逾期归档" name="overduePigeonhole"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.overduePigeonhole}">
                            </div>
                            <label class="col-sm-1 col-form-label">
                                归档确定
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="归档确定" name="pigeonholeConfirm"
                                       class="form-control input-full"
                                       value="${projectXlxCommission.pigeonholeConfirm}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="x_title">返佣结算比例</div>
                <div class="x_content">
                    <button style="margin-left: 10px" class="btn btn-success btn-sm" type="button"
                                data-toggle="modal" onclick="rebateRatio.prototype.showModel()"
                                href="#divRebateRatioBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                            新增
                    </button>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" id="tb_rebateRatioList">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
                <div class="x_title">提成结算比例</div>
                <div class="x_content">
                    <button class="btn btn-success btn-sm" type="button" style="margin-left: 10px"
                            data-toggle="modal" onclick="commissionRatio.prototype.showModel()"
                            href="#divCommissionRatioBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" id="tb_commissionRatioList">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="application/javascript">
    var projectXlxCommission = {};

    //申请提交
    projectXlxCommission.commit = function () {
        if (!$("#project_master_form").valid()) {
            return false;
        }
        var data = formParams("project_master_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectXlxCommission/applyCommit",
            type: "post",
            data: {formData:JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //返回修改
    projectXlxCommission.editCommit = function () {
        var data = formParams("project_master_form");

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectXlxCommission/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
</script>
<script type="text/javascript">
    $(function () {
        if(${processInsId != 0}){
            commissionRatio.prototype.loadDataDicList();
        }else{
            commissionRatio.prototype.init();
        }
    });
    var commissionRatio = function () {

    };
    commissionRatio.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_commissionRatioList";
            data.box = "divCommissionRatioBox";
            data.frm = "commissionRatioFrm";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'name', width: '20%', title: '姓名'});
            cols.push({field: 'money', width: '20%', title: '金额'});
            cols.push({field: 'coefficient', width: '40%', title: '系数'});
            cols.push({
                field: 'id', width: '20%', title: '操作', formatter: function (value, row, index) {
                    var str = '<button type="button" onclick="commissionRatio.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button type="button" onclick="commissionRatio.prototype.removeData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;

                }
            });
            $("#" + commissionRatio.prototype.config().table).bootstrapTable('destroy');
            TableInit(commissionRatio.prototype.config().table, "${pageContext.request.contextPath}/projectXlxCommissionRatio/getList", cols, {
                masterId: '${projectXlxCommission.id}'
            }, {
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
                    url: "${pageContext.request.contextPath}/projectXlxCommissionRatio/deleteById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            commissionRatio.prototype.loadDataDicList();
                        }
                        else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            })
        },
        showModel: function () {
            $("#" + commissionRatio.prototype.config().frm).clearAll();
            $('#' + commissionRatio.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + commissionRatio.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(commissionRatio.prototype.config().frm);
            data.masterId = '${projectXlxCommission.id}'
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxCommissionRatio/saveAndUpdate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + commissionRatio.prototype.config().box).modal('hide');
                        commissionRatio.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxCommissionRatio/getById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + commissionRatio.prototype.config().frm).clearAll().initForm(result.data);
                        $('#' + commissionRatio.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        init: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxCommissionRatio/init",
                type: "post",
                dataType: "json",
                data: {
                    projectId:'${projectId}',
                    masterId:'${projectXlxCommission.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        commissionRatio.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
    }
</script>
<div id="divCommissionRatioBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">提成结算比例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="commissionRatioFrm" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                姓名<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" required data-rule-maxlength="50" placeholder="姓名"
                                                       id="name" name="name"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 col-form-label">
                                                金额<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" required data-rule-number="true" placeholder="金额"
                                                       id="money" name="money"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                系数
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="系数" id="coefficient" name="coefficient"
                                                       class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="commissionRatio.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        rebateRatio.prototype.loadDataDicList();
    });
    var rebateRatio = function () {

    };
    rebateRatio.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_rebateRatioList";
            data.box = "divRebateRatioBox";
            data.frm = "rebateRatioFrm";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'name', width: '40%', title: '姓名'});
            cols.push({field: 'money', width: '40%', title: '金额'});
            cols.push({
                field: 'id', width: '20%', title: '操作', formatter: function (value, row, index) {
                    var str = '<button type="button" onclick="rebateRatio.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button type="button" onclick="rebateRatio.prototype.removeData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;

                }
            });
            $("#" + rebateRatio.prototype.config().table).bootstrapTable('destroy');
            TableInit(rebateRatio.prototype.config().table, "${pageContext.request.contextPath}/projectXlxRebateRatio/getList", cols, {
                masterId: '${projectXlxCommission.id}'
            }, {
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
                    url: "${pageContext.request.contextPath}/projectXlxRebateRatio/deleteById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            rebateRatio.prototype.loadDataDicList();
                        }
                        else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            })
        },
        showModel: function () {
            $("#" + rebateRatio.prototype.config().frm).clearAll();
            $('#' + rebateRatio.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + rebateRatio.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(rebateRatio.prototype.config().frm);
            data.masterId = '${projectXlxCommission.id}'
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxRebateRatio/saveAndUpdate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + rebateRatio.prototype.config().box).modal('hide');
                        rebateRatio.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxRebateRatio/getById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + rebateRatio.prototype.config().frm).clearAll().initForm(result.data);
                        $('#' + rebateRatio.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }

    }
</script>
<div id="divRebateRatioBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">返佣结算比例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="rebateRatioFrm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                姓名<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" required data-rule-maxlength="50" placeholder="姓名"
                                                       name="name" class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 col-form-label">
                                                金额<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" required data-rule-number="true" placeholder="金额"
                                                       name="money" class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="rebateRatio.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>