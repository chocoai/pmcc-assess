<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="startModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">子模板</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <button id="toolbarTemplateItem" style="margin-left: 5px" class="btn btn-success btn-sm" type="button" data-toggle="modal" onclick="dataReportTemplateItem.prototype.showModel()" href="#dataReportTemplateItemModal"><span class="btn-label"><i class="fa fa-plus"></i></span>新增</button>
                        <table id="tb_dataTemplateItemList" class="table table-bordered"></table>
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

<div id="dataReportTemplateItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">编辑子模板</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="dataReportTemplateItemFrm" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="name" placeholder="名称"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                key值<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="fieldName" placeholder="key值"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="sorting" placeholder="排序"
                                                       required="required" data-rule-number='true'>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                模板<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-11">
                                                <div style="width:99%;height:200px;" id="item_template"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataReportTemplateItem.prototype.saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
    $(function () {
        UE.getEditor('item_template', {
            toolbars: [
                ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
            ],
            zIndex: 11009,
            initialFrameHeight: 220,
            elementPathEnabled: false,//是否启用元素路径，默认是true显示
            wordCount: false, //是否开启字数统计
            autoHeightEnabled: false,
            autoFloatEnabled: true
        });
    })
    var masterId = 0;
    var masterType = "";
    var dataReportTemplateItem = function () {

    };
    dataReportTemplateItem.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_dataTemplateItemList";
            data.box = "dataReportTemplateItemModal";
            data.startBox = "startModal";
            data.frm = "dataReportTemplateItemFrm";
            return data;
        },
        loadDataDicList: function (masterId,masterType) {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'fieldName', title: 'key值'});
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="dataReportTemplateItem.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataReportTemplateItem.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + dataReportTemplateItem.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataReportTemplateItem.prototype.config().table, "${pageContext.request.contextPath}/dataReportTemplateItem/getDataReportTemplateItemList?masterId=" + masterId+"&type="+masterType, cols, {}, {
                showColumns: false,
                showRefresh: false,
                toolbar: '#toolbarTemplateItem',
                search: false
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataReportTemplateItem/deleteDataReportTemplateItemById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            dataReportTemplateItem.prototype.loadDataDicList(masterId, masterType);
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
        },
        showModel: function () {
            $("#" + dataReportTemplateItem.prototype.config().frm).clearAll();
            $('#' + dataReportTemplateItem.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataReportTemplateItem.prototype.config().frm).valid()) {
                return false;
            }
            //var masterId = masterId;
            var data = formParams(dataReportTemplateItem.prototype.config().frm);
            data.masterId = masterId;
            data.type = masterType;
            data.template = UE.getEditor('item_template').getContent();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataReportTemplateItem/saveAndUpdateDataReportTemplateItem",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataReportTemplateItem.prototype.config().box).modal('hide');
                        dataReportTemplateItem.prototype.loadDataDicList(masterId, masterType);
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataReportTemplateItem/getDataReportTemplateItemById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataReportTemplateItem.prototype.config().frm).clearAll();
                        $("#" + dataReportTemplateItem.prototype.config().frm).initForm(result.data);
                        var content = result.data.template;
                        setTimeout(function () {
                            UE.getEditor('item_template').setContent(content, false);
                        }, 500);
                        $('#' + dataReportTemplateItem.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showStartModel: function (id,type) {
            masterId = id;
            masterType = type;
            dataReportTemplateItem.prototype.loadDataDicList(masterId,masterType);
            $('#' + dataReportTemplateItem.prototype.config().startBox).modal("show");
        }

    }
</script>
