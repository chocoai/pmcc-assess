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
                                        <label class="col-md-1 col-form-label">审批人</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" name="approver">
                                            <input type="text" readonly="readonly"
                                                   onclick="estateReport.approverSelect(this)"
                                                   placeholder="审批人" name="approverName"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">创建人</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" name="creator">
                                            <input type="text" readonly="readonly"
                                                   onclick="estateReport.createSelect(this)"
                                                   placeholder="创建人" name="creatorName"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">

                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input name="startDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input name="endDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                        <button class="btn btn-info  btn-sm" type="button" style="margin-left: 10px"
                                                onclick="">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
</body>

<script type="text/javascript">


    $(function () {

    });

    var estateReport = {};

    estateReport.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                    if (errorCallback) {
                        errorCallback();
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };
    estateReport.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                estateReport.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            estateReport.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    estateReport.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        estateReport.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };

    estateReport.approverSelect = function (_this) {
        estateReport.selectExecuteUserAccount(false, function (data) {
            $(_this).val(data.name);
            $(_this).closest(".form-group").find("[name=approver]").val(data.account);
        });
    };

    estateReport.createSelect = function (_this) {
        estateReport.selectExecuteUserAccount(false, function (data) {
            $(_this).val(data.name);
            $(_this).closest(".form-group").find("[name=creator]").val(data.account);
        });
    };

    /**
     * erp 人员选择
     */
    estateReport.selectExecuteUserAccount = function (multi, callback) {
        erpEmployee.select({
            multi: multi,
            currOrgId: '${companyId}',
            onSelected: function (data) {
                if (callback) {
                    callback(data);
                }
            }
        });
    };


</script>


</html>
