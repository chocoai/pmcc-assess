<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>

<div id="divRecordBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">编号变更</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal">

                    <input type="hidden"  name="id">

                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                名称
                                            </label>
                                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                                <label class="form-control input-full" name="name"> </label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                编号<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                                <input type="text" data-rule-maxlength="50" placeholder="编号"
                                                       name="number" data-rule-number='true'
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
                <button type="button" class="btn btn-primary btn-sm" onclick="objRecord.saveDeclareRecordData() ;">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>


                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        权证编号变更
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
                                    <input type="hidden" name="projectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    权证号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="权证号"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    坐落
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="坐落"
                                                           name="seat"
                                                           class="form-control input-full">
                                                </div>

                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="objRecord.loadDeclareRecordList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <button class="btn btn-success btn-sm"
                                                            type="button"
                                                            onclick="">
                                                        <span class="btn-label"><i
                                                                class="fa fa-cog"></i></span>自动重设编号
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tb_declare_record_list"></table>
                                </form>
                            </div>
                        </div>
                    </div>




                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <div class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                            <div class="form-inline x-valid">
                                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                </div>
                                                <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                                    <button class="btn btn-warning" type="button"
                                                            onclick="window.close()">
                                                        关闭
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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



<script>

    var objRecord = {} ;

    objRecord.run = function (data, url, type, callback, funParams, errorCallback) {
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
    objRecord.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                objRecord.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            objRecord.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    objRecord.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        objRecord.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };

    /**
     * 申报记录 修改
     * @param data
     * @param callback
     */
    objRecord.saveDeclareRecord = function (data, callback) {
        objRecord.ajaxServerFun({formData: JSON.stringify(data)}, "/declareRecord/saveDeclareRecord", "post", callback, null);
    };

    /**
     * 变更权证号
     * @param declareRecordId
     * @param number
     * @param callback
     */
    objRecord.changeDeclareRecordNumber = function ( declareRecordId,  number ,callback) {
        objRecord.ajaxServerFun({number:number ,declareRecordId:declareRecordId , projectId:'${projectInfo.id}'}, "/declareRecord/changeDeclareRecordNumber", "post", callback, null);
    } ;


    objRecord.declareRecordTable = $("#tb_declare_record_list");
    objRecord.declareRecordBox = $("#divRecordBox");

    objRecord.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    objRecord.loadDeclareRecordList = function (_this) {
        var data = {projectId: '${projectInfo.id}'};
        if (_this) {
            data = formSerializeArray($(_this).closest("form"));
        }
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });

        var target = objRecord.handleJquery(objRecord.declareRecordTable);
        var selector = target.selector;
        var cols = [];
        cols.push({
            field: 'name', title: '权证号', width: "13%", formatter: function (value, row, index) {
                return value;
            }
        });
        cols.push({field: 'number', title: '编号', width: "10%"});
        cols.push({field: 'seat', title: '坐落', width: "20%"});
        cols.push({field: 'certUse', title: '证载用途', width: "5%"});
        cols.push({field: 'practicalUse', title: '实际用途', width: "5%"});
        cols.push({field: 'floorArea', title: '面积', width: "5%"});
        cols.push({
            field: 'id', title: '操作', width: "30%", formatter: function (value, row, index) {
                var str = "";

                str += '<button type="button" onclick="objRecord.editDeclareRecord(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';


                return str;
            }
        });
        target.bootstrapTable('destroy');
        var method = {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        } ;
        TableInit(target, "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, data, method);
    };

    objRecord.editDeclareRecord= function (id) {
        var target = objRecord.handleJquery(objRecord.declareRecordTable);
        var data = target.bootstrapTable('getRowByUniqueId', id);
        var frm = objRecord.handleJquery(objRecord.declareRecordBox).find("form");
        frm.clearAll();
        frm.validate();
        frm.initForm(data);
        objRecord.handleJquery(objRecord.declareRecordBox).modal("show");
    } ;

    objRecord.saveDeclareRecordData = function () {
        var frm = objRecord.handleJquery(objRecord.declareRecordBox).find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        objRecord.saveDeclareRecord(data,function () {
            notifySuccess("成功", "修改数据成功!");
            objRecord.handleJquery(objRecord.declareRecordBox).modal("hide");
            objRecord.loadDeclareRecordList() ;
        }) ;
    } ;


    $(document).ready(function () {
        objRecord.loadDeclareRecordList() ;

    }) ;


</script>


</html>

