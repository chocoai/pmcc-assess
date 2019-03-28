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
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务类型
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="serviceType" class="form-control search-select select2 serviceType"
                                                    onchange="getCategory();">
                                                <option value="">-请选择-</option>
                                                <c:forEach items="${serviceContent}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
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
                                            <input type="text" placeholder="服务时间" data-date-format='yyyy-mm-dd'
                                                   id="serviceTime" name="serviceTime"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            等级评价
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="gradeEvaluation" class="form-control search-select select2">
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
                <button type="button" class="btn btn-primary" onclick="dataPropertyServiceItem.prototype.saveData()">
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
            cols.push({field: 'serviceTypeName', title: '服务类型'});
            cols.push({field: 'serviceContentName', title: '服务内容'});
            cols.push({
                field: 'serviceTime', title: '服务时间', formatter: function (value, row, index) {
                    return formatDate(row.serviceTime, false);
                }
            });
            cols.push({field: 'gradeEvaluationName', title: '等级评价'});
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success" href="javascript://;" onclick="dataPropertyServiceItem.prototype.getAndInit(' + row.id + ')" ><i class="fa fa-edit">编辑</i></a>';
                    str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="dataPropertyServiceItem.prototype.removeData(' + row.id + ')"><i class="fa fa-trash-o"></i>删除</a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataPropertyServiceItem.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataPropertyServiceItem.prototype.config().table, "${pageContext.request.contextPath}/dataPropertyServiceItem/getDataPropertyServiceItemList?masterId=" + masterId, cols, {}, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        },
        removeData: function (id) {
            Alert("确认要删除么？", 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataPropertyServiceItem/deleteDataPropertyServiceItemById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            dataPropertyServiceItem.prototype.loadDataDicList(masterId);
                        }
                        else {
                            Alert("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            })
        },
        showModel: function () {
            $("#" + dataPropertyServiceItem.prototype.config().frm).clearAll();
            $('#' + dataPropertyServiceItem.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataPropertyServiceItem.prototype.config().frm).valid()) {
                return false;
            }
            //var masterId = masterId;
            var data = formParams(dataPropertyServiceItem.prototype.config().frm);
            data.masterId = masterId;
            $.ajax({
                url: "${pageContext.request.contextPath}/dataPropertyServiceItem/saveAndUpdateDataPropertyServiceItem",
                type: "post",
                dataType: "json",
                data: {formData:JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataPropertyServiceItem.prototype.config().box).modal('hide');
                        dataPropertyServiceItem.prototype.loadDataDicList(masterId);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataPropertyServiceItem/getDataPropertyServiceItemById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataPropertyServiceItem.prototype.config().frm).clearAll();
                        $("#" + dataPropertyServiceItem.prototype.config().frm).initForm(result.data);

                        getCategory(result.data.serviceContent);
                        $('#' + dataPropertyServiceItem.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showStartModel: function (id) {
            masterId = id;
            dataPropertyServiceItem.prototype.loadDataDicList(masterId);
            $('#' + dataPropertyServiceItem.prototype.config().startBox).modal("show");
        }

    }

    // function typeChange(this_) {
    //     var str = $(this_).attr("id");
    //     var number = str.substr(str.length - 1, 1);
    //     getCategory(number);
    // }

    //服务内容
    function getCategory(categoryValue) {
        if(!categoryValue){
            $("#dataPropertyServiceItemFrm").find('select.serviceContent').val(['']).trigger('change');
        }
        //监听change 事件 并做出......
        $("#dataPropertyServiceItemFrm" + " .serviceType").change(function () {
            var pid = $("#dataPropertyServiceItemFrm" + " .serviceType").eq(1).val();
            if (!pid) {
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/dataPropertyServiceItem/getServiceContentList",
                type: "get",
                dataType: "json",
                data: {pid: pid},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (data.length >= 1) {
                            var option = "<option value=''>请选择</option>";
                            for (var i = 0; i < data.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#dataPropertyServiceItemFrm").find('select.serviceContent').html(option);
                        }

                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
        if (categoryValue) {
            $("#dataPropertyServiceItemFrm").find('select.serviceContent').val([categoryValue]).trigger('change');
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
                        onclick="dataPropertyServiceItem.prototype.showModel()"
                        data-toggle="modal"> 新增
                </button>
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