<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<div id="dataPropertyServiceItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <form id="dataPropertyServiceItemFrm" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务类型
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="serviceType" class="form-control search-select select2 ">
                                                <option value="">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务内容
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="serviceContent" class="form-control search-select select2 serviceContent">
                                                <option selected="selected" value="">请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务时间
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="serviceTime"
                                                   placeholder="服务时间" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            等级评价
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="gradeEvaluation" class="form-control search-select select2">
                                                <option value="">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            收费标准<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="chargesNotes"
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
                <button type="button" class="btn btn-primary" onclick="dataPropertyServiceItem.prototype.saveData(this)">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    var masterId = 0;
    var dataPropertyServiceItem = function () {

    };
    dataPropertyServiceItem.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_dataServiceItemList";
            data.box = "dataPropertyServiceItemModal";
            data.startBox = "startModal";
            data.frm = "dataPropertyServiceItemFrm";
            return data;
        },
        loadDataDicList: function (masterId) {
            var cols = [];
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success" href="javascript://;" onclick="dataPropertyServiceItem.prototype.getAndInit(' + row.id + ')" ><i class="fa fa-edit">编辑</i></a>';
                    str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="dataPropertyServiceItem.prototype.removeData(' + row.id + ')"><i class="fa fa-trash-o"></i>删除</a>';
                    str += '</div>';
                    return str;
                }
            });
            dataPropertyModelQuote.dataPropertyServiceItemList($("#" + dataPropertyServiceItem.prototype.config().table) ,masterId,cols) ;
        },
        removeData: function (id) {
            var data = $("#" + dataPropertyServiceItem.prototype.config().table).bootstrapTable('getRowByUniqueId',id);
            dataPropertyModelQuote.deleteDataPropertyServiceItem(id,function () {
                toastr.success('删除成功');
                dataPropertyServiceItem.prototype.loadDataDicList(data.masterId);
            });
        },
        showModel: function (_this) {
            var target = $('#' + dataPropertyServiceItem.prototype.config().box) ;
            var frm = $("#" + dataPropertyServiceItem.prototype.config().frm) ;
            var masterId = target.find("input[name='masterId']").val();
            dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool(frm,{masterId:masterId}) ;
            target.modal("show");
        },
        saveData: function (_this) {
            var target = $(_this).parent().parent().parent().parent() ;
            if (!target.find("form").valid()) {
                return false;
            }
            var data = formSerializeArray(target.find("form"));
            dataPropertyModelQuote.saveDataPropertyServiceItem(data,function () {
                toastr.success('保存成功');
                target.modal('hide');
                dataPropertyServiceItem.prototype.loadDataDicList(data.masterId);
            });
        },
        getAndInit: function (id) {
            var data = $("#" + dataPropertyServiceItem.prototype.config().table).bootstrapTable('getRowByUniqueId',id);
            var frm = $("#" + dataPropertyServiceItem.prototype.config().frm) ;
            dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool(frm , data) ;
            $('#' + dataPropertyServiceItem.prototype.config().box).modal("show");
        },
        showStartModel: function (id) {
            var target = $('#' + dataPropertyServiceItem.prototype.config().startBox) ;
            dataPropertyServiceItem.prototype.loadDataDicList(id);
            target.find("input[name='masterId']").val(id) ;
            target.modal("show");
        }

    }
</script>
<div id="startModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
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
                        onclick="dataPropertyServiceItem.prototype.showModel(this)"
                        data-toggle="modal"> 新增
                </button>
                <input type="hidden" name="masterId">
                <table class="table table-bordered" id="tb_dataServiceItemList">
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