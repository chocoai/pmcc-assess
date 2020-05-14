<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>

    var dataPropertyModelQuote = {};

    dataPropertyModelQuote.getDataProperty = function (id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataProperty/getDataPropertyById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    dataPropertyModelQuote.saveDataProperty = function (data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataProperty/saveAndUpdateDataProperty",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    dataPropertyModelQuote.deleteDataProperty = function (id, callback) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataProperty/deleteDataPropertyById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        });
    };

    dataPropertyModelQuote.getDataPropertyServiceItemVoList = function (masterId, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataPropertyServiceItem/getDataPropertyServiceItemVoList",
            type: "get",
            dataType: "json",
            data: {masterId: masterId},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };


    dataPropertyModelQuote.dataPropertyServiceItemList = function (selectId, masterId, colArr) {
        var cols = [];
        cols.push({field: 'serviceTypeName', title: '服务类型'});
        cols.push({field: 'serviceContentName', title: '服务内容'});
        cols.push({field: 'serviceTime', title: '服务时间'});
        cols.push({field: 'gradeEvaluationName', title: '等级评价'});
        if (colArr) {
            $.each(colArr, function (i, n) {
                cols.push(n);
            });
        }
        selectId.bootstrapTable('destroy');
        TableInit(selectId.attr("id"), "${pageContext.request.contextPath}/dataPropertyServiceItem/getDataPropertyServiceItemList?masterId=" + masterId, cols, {}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    dataPropertyModelQuote.saveDataPropertyServiceItem = function (data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataPropertyServiceItem/saveAndUpdateDataPropertyServiceItem",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    dataPropertyModelQuote.dataPropertyServiceItemModalToolSave = function (_this) {
        var form = $(_this).parent().parent().parent().find("form");
        if (!form.valid()) {
            return false;
        }
        var data = formSerializeArray(form);
        var target = $("#dataPropertyServiceItemTableModalTool");
        dataPropertyModelQuote.saveDataPropertyServiceItem(data, function () {
            AlertSuccess("成功", "数据已成功保存到数据库");
            dataPropertyModelQuote.dataPropertyServiceItemList($("#dataPropertyServiceItemTableModalToolTable"), data.masterId);
            target.find("input[name='masterId']").val(data.masterId);
            $(_this).parent().parent().parent().parent().modal('hide');
        });
    };

    dataPropertyModelQuote.deleteDataPropertyServiceItem = function (id, callback) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataPropertyServiceItem/deleteDataPropertyServiceItemById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback();
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        })
    };

    dataPropertyModelQuote.dataPropertyServiceItemTableModalToolShow = function (id) {
        var target = $("#dataPropertyServiceItemTableModalTool");
        target.modal('show');
        target.find("input[name='masterId']").val(id);
        dataPropertyModelQuote.dataPropertyServiceItemList($("#dataPropertyServiceItemTableModalToolTable"), id);
    };

    dataPropertyModelQuote.addDataPropertyServiceItemModalTool = function (_this) {
        var masterId = $(_this).parent().parent().find("input[name='masterId']").val();
        dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool($("#dataPropertyServiceItemModalTool").find("form"), {masterId: masterId});
    };

    dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool = function (frm, data) {
        frm.clearAll();
        frm.initForm(data);
        AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, data.gradeEvaluation, function (html, data) {
            frm.find('[name=gradeEvaluation]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.DATA_SERVICE_CONTENT, data.serviceType, function (html, data) {
            frm.find('[name=serviceType]').empty().html(html).trigger('change');
        });
        frm.find('[name=serviceType]').change(function () {
            var serviceType = frm.find('[name=serviceType]').val();
            AssessCommon.loadDataDicByPid(serviceType, data.serviceContent, function (html, data) {
                frm.find('[name=serviceContent]').empty().html(html).trigger('change');
            });
        });
        if (data.serviceType) {
            if (data.serviceContent) {
                AssessCommon.loadDataDicByPid(data.serviceType, data.serviceContent, function (html, data) {
                    frm.find('[name=serviceContent]').empty().html(html).trigger('change');
                });
            }
        }
    };


    dataPropertyModelQuote.getFatherHtml = function () {
        return $("#dataPropertyModelQuoteFatherHtml").html();
    };

    dataPropertyModelQuote.dataPropertyModelQuoteFatherSave = function (_this) {
        var target = $(_this).parent().parent().parent().parent().parent();
        if (!target.find("form").valid()) {
            return false;
        }
        var data = formSerializeArray(target.find("form"));
        dataPropertyModelQuote.saveDataProperty(data, function () {
            notifySuccess('成功','保存成功');
            target.modal('hide');
        });
    };


</script>

<script type="text/html" id="dataPropertyModelQuoteFatherHtml">
    <input type="hidden" name="id">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-2 col-form-label">
                    名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control input-full" name="name"
                           placeholder="名称" required="required">
                </div>
                <label class="col-sm-2 col-form-label">
                    公司性质
                </label>
                <div class="col-sm-4">
                    <select name="companyNature" class="form-control input-full">
                        <option value="">-请选择-</option>
                        <c:forEach items="${unitPropertiesList}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-2 col-form-label">
                    社会信誉
                </label>
                <div class="col-sm-4">
                    <select name="socialPrestige" class="form-control input-full">
                        <option value="">-请选择-</option>
                        <c:forEach items="${reputations}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="row form-group" style="display: none">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <div class="col-sm-4">
                    <button type="button" class="btn btn-success btn-sm"
                            onclick="showItemable(this)" data-toggle="modal"> 设置服务内容
                    </button>
                </div>
            </div>
        </div>
    </div>
</script>

<div id="dataPropertyServiceItemTableModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">服务内容</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <input type="hidden" name="masterId">
                            <p>
                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                        data-toggle="modal" onclick="dataPropertyModelQuote.addDataPropertyServiceItemModalTool(this)"
                                        href="#dataPropertyServiceItemModalTool">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </p>
                            <table class="table table-bordered" id="dataPropertyServiceItemTableModalToolTable">
                                <!-- cerare document add ajax data-->
                            </table>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<%--
<div id="dataPropertyServiceItemTableModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">服务内容</h3>
            </div>
            <div class="modal-body">
                <button type="button" class="btn btn-success"
                        onclick="dataPropertyModelQuote.addDataPropertyServiceItemModalTool(this)"
                        data-toggle="modal" href="#dataPropertyServiceItemModalTool"> 新增
                </button>
                <input type="hidden" name="masterId">
                <table class="table table-bordered" id="dataPropertyServiceItemTableModalToolTable">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
--%>

<div id="dataPropertyServiceItemModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">编辑服务内容</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                服务类型
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="serviceType" class="form-control input-full serviceType"
                                                        onchange="">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${serviceContent}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                服务内容
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="serviceContent" class="form-control input-full">
                                                    <option value="">请先选择类型</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                服务时间
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="serviceTime"
                                                       placeholder="服务时间" required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                等级评价
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="gradeEvaluation" class="form-control input-full  ">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${reputations}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                收费标准<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="chargesNotes"
                                                       placeholder="收费标准" required="required">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataPropertyModelQuote.dataPropertyServiceItemModalToolSave(this)">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<%--
<div id="dataPropertyServiceItemModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">编辑服务内容</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            服务类型
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <select name="serviceType" class="form-control input-full serviceType"
                                                    onchange="">
                                                <option value="">-请选择-</option>
                                                <c:forEach items="${serviceContent}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            服务内容
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <select name="serviceContent" class="form-control input-full">
                                                <option value="">请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            服务时间
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <input type="text" class="form-control input-full" name="serviceTime"
                                                   placeholder="服务时间" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            等级评价
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <select name="gradeEvaluation" class="form-control input-full  ">
                                                <option value="">-请选择-</option>
                                                <c:forEach items="${reputations}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            收费标准<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10">
                                            <input type="text" class="form-control input-full" name="chargesNotes"
                                                   placeholder="收费标准" required="required">
                                        </div>
                                    </div>
                                </div>
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
                        onclick="dataPropertyModelQuote.dataPropertyServiceItemModalToolSave(this);">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
--%>

<div id="dataPropertyModelQuoteFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">物业公司</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="panel-body">

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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataPropertyModelQuote.dataPropertyModelQuoteFatherSave(this)">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<%--
<div id="dataPropertyModelQuoteFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">物业公司</h3>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="dataPropertyModelQuote.dataPropertyModelQuoteFatherSave(this)">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
--%>
