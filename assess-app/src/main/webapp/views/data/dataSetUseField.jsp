<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>设定用途</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                                <div class="row">
                                    <div class="col-md-12">
                                        <form id="frmQuery" class="form-horizontal">
                                            <div class="form-group form-inline">
                                                <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                                <div class="col-md-3 p-0">
                                                    <input type="text" data-rule-maxlength="50"
                                                           placeholder="名称" id="queryName" name="queryName"
                                                           class="form-control input-full">
                                                </div>
                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                        onclick="reloadSetUseFieldList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                        data-toggle="modal" onclick="addSetUseField()"
                                                        href="#divBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                    新增
                                                </button>
                                            </div>


                                        </form>
                                        <table class="table table-bordered" id="tb_List">
                                            <!-- cerare document add ajax data-->
                                        </table>
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
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">字典管理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <input type="hidden" id="pid" name="pid">
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
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="name" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="type" class="form-control input-full" required>
                                                    <option value="">-请选择-</option>
                                                    <option value="house">房产</option>
                                                    <option value="land">土地</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-digits="true" placeholder="排序"
                                                       id="sorting" name="sorting" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                            <textarea placeholder="备注" id="remark" name="remark"
                                                      class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveSetUseField()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<!--子项管理-->
<div id="divSubSetUseField" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="titleContent">数据列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <p id="toolbarSub">
                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                        data-toggle="modal" onclick="addSubSetUseField()"
                                        href="#divSubSetUseFieldManage">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </p>
                            <table id="tbSetUseFieldList" class="table table-bordered"></table>


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

<div id="divSubSetUseFieldManage" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">子项管理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <input type="hidden" id="mainId" name="mainId" value="0">
                <form id="frmSub" class="form-horizontal">
                    <input type="hidden" id="subId" name="id" value="0">
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
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="subName" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                字段名称
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="100" placeholder="字段名称"
                                                       id="subFieldName" name="fieldName" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subBisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subBisPrimaryKey"
                                                               name="bisPrimaryKey" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否主键</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subBisPrice"
                                                               name="bisPrice" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否成交价</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subBisOnlyView"
                                                               name="bisOnlyView" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否只读</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subCanShrink"
                                                               name="canShrink" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否可收缩</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subShow"
                                                               name="bisShow" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否显示</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-digits="true" placeholder="排序"
                                                       id="subSorting" name="sorting" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                             <textarea placeholder="备注" id="subRemark" name="remark"
                                                       class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveSubSetUseField()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<script type="application/javascript">
    $(function () {
        loadSetUseFieldList();
    })
    //加载代理数据列表
    function loadSetUseFieldList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="setSubSetUseField(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="editSetUseField(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="delSetUseField(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        TableInit("tb_List", "${pageContext.request.contextPath}/setUseField/getSetUseFieldList", cols, {
            name: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }
    //刷新数据列表
    function reloadSetUseFieldList() {
        var opt = {
            url: "${pageContext.request.contextPath}/setUseField/getSetUseFieldList",
            query: {
                fieldName: $("#queryFieldName").val(),
                name: $("#queryName").val()
            }
        };
        $("#tb_List").bootstrapTable("refresh", opt);
    }

    //保存数据
    function saveSetUseField() {
        if ($("#frm").valid()) {
            var data = formParams("frm");
            data.bisEnable = $("#bisEnable").prop("checked");
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/setUseField/saveSetUseField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        TableReload("tb_List");
                        $('#divBox').modal('hide');
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }
    }

    //新增字典数据
    function addSetUseField() {
        $("#frm").clearAll();
        $("#id").val("0");
        $("#bisEnable").prop("checked", true);
    }
    //编辑字典数据
    function editSetUseField(id) {
        $("#frm").clearAll();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/setUseField/getSetUseFieldInfo",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm").clearAll().initForm(result.data);
                    $('#divBox').modal();
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    //删除字典数据
    function delSetUseField(id, tbId) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/setUseField/delSetUseField",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        })
    }

    //重置查询条件
    function queryReset() {
        $("#queryId").val("");
        $("#groupKey").val("");
        $("#queryName").val("");
    }
    //设置子项数据
    function setSubSetUseField(pid) {
        $("#mainId").val(pid);
        getSetUseFieldLevel(pid);
        loadSubSetUseFieldList(pid, function () {
            $('#divSubSetUseField').modal("show");
        });
    }

    //加载节点角色数据
    function loadSubSetUseFieldList(pid, fn) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'fieldName', title: '字段名称'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<button onclick="setSubSetUseField(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="editSubSetUseField(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="delSetUseField(' + row.id + ',\'tbSetUseFieldList\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tbSetUseFieldList").bootstrapTable("destroy");
        TableInit("tbSetUseFieldList", "${pageContext.request.contextPath}/setUseField/getSetUseFieldListByPid",
            cols, {pid: pid}, {
                showRefresh: false,                  //是否显示刷新按钮
                toolbar: '#toolbarSub',
                uniqueId: "id",
                onLoadSuccess: function () {
                    if (fn) {
                        fn();
                    }
                }
            });
    }
    //新增子项
    function addSubSetUseField() {
        $("#frmSub").clearAll();
        $("#subId").val("0");
        $("#subBisEnable").prop("checked", true);
        $("#subBisPrimaryKey").prop("checked", false);
        $("#subBisPrice").prop("checked", false);
        $("#subBisOnlyView").prop("checked", false);
        $("#subCanShrink").prop("checked", false);
        $("#subShow").prop("checked", true);
    }

    //编辑子项
    function editSubSetUseField(id) {
        $("#frmSub").clearAll();
        var row = $("#tbSetUseFieldList").bootstrapTable("getRowByUniqueId", id);
        if (row) {
            $("#frmSub").initForm(row);
            $("#subBisEnable").prop("checked", row.bisEnable);
            $("#subBisPrimaryKey").prop("checked", row.bisPrimaryKey);
            $("#subBisPrice").prop("checked", row.bisPrice);
            $("#subBisOnlyView").prop("checked", row.bisOnlyView);
            $("#subCanShrink").prop("checked", row.canShrink);
            $("#subShow").prop("checked", row.bisShow);
        }
        $('#divSubSetUseFieldManage').modal();
    }


    //保存子项
    function saveSubSetUseField() {
        if ($("#frmSub").valid()) {
            var data = {};
            data.id = $("#subId").val();
            data.pid = $("#mainId").val();
            data.name = $("#subName").val();
            data.itemKey = $("#subItemKey").val();
            data.sorting = $("#subSorting").val();
            data.bisEnable = $("#subBisEnable").prop("checked");
            data.bisPrimaryKey = $("#subBisPrimaryKey").prop("checked");
            data.bisPrice = $("#subBisPrice").prop("checked");
            data.bisOnlyView = $("#subBisOnlyView").prop("checked");
            data.canShrink = $("#subCanShrink").prop("checked");
            data.bisShow = $("#subShow").prop("checked");
            data.remark = $("#subRemark").val();
            if ($("#subFieldName").val()) {
                data.fieldName = $("#subFieldName").val();
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/setUseField/saveSetUseField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        TableReload("tbSetUseFieldList");
                        $('#divSubSetUseFieldManage').modal('hide');
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }
    }
    var strLevelHtml = "";
    //获取字典层级
    function getSetUseFieldLevel(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/setUseField/getSetUseFieldLevel",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    strLevelHtml = "";
                    if (result.data) {
                        if (result.data.keyValueDto) {
                            getSetUseFieldLevelRecursion(result.data.keyValueDto);
                        }
                        strLevelHtml += '<a href="javascript:setSubSetUseField(' + result.data.key + ')">' + result.data.value + '</a>' + ">";
                        $("#titleContent").html(strLevelHtml.replace(/>$/, ""));
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
    }
    function getSetUseFieldLevelRecursion(keyValueDto) {
        if (keyValueDto) {
            getSetUseFieldLevelRecursion(keyValueDto.keyValueDto);
            strLevelHtml += '<a href="javascript:setSubSetUseField(' + keyValueDto.key + ')">' + keyValueDto.value + '</a>' + ">";
        }
    }
</script>


</html>
