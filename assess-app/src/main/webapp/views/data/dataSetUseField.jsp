<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="reloadSetUseFieldList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success" onclick="addSetUseField()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">

                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">字典管理</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <input type="hidden" id="pid" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                   id="name" name="name" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            是否启用
                                        </label>
                                        <div class="col-sm-9">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="bisEnable" name="bisEnable" value="true"
                                                       checked="checked">
                                            </label>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" data-rule-digits="true" placeholder="排序"
                                                   id="sorting" name="sorting" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            备注
                                        </label>
                                        <div class="col-sm-9">
                                    <textarea placeholder="备注" id="remark" name="remark"
                                              class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveSetUseField()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--子项管理-->
<div id="divSubSetUseField" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">子项数据</h3>
            </div>
            <div class="panel-body">
        <span id="toolbarSub">
            <button type="button" class="btn btn-success" onclick="addSubSetUseField()"
                    data-toggle="modal" href="#divSubSetUseFieldManage"> 新增
            </button>
        </span>
                <table class="table table-bordered" id="tbSetUseFieldList">
                </table>
            </div>
        </div>
    </div>
</div>
<div id="divSubSetUseFieldManage" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">子项管理</h3>
            </div>
            <input type="hidden" id="mainId" name="mainId" value="0">
            <form id="frmSub" class="form-horizontal">
                <input type="hidden" id="subId" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div>
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                   id="subName" name="name" class="form-control">
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-maxlength="100" placeholder="字段名称"
                                                   id="subFieldName" name="fieldName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        是否启用
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="checkbox" id="subBisEnable" name="bisEnable" value="true"
                                                   checked="checked">
                                        </label>
                                    </div>
                                    <label class="col-sm-2 control-label">
                                        是否主键
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="checkbox" id="subBisPrimaryKey" name="bisPrimaryKey" value="true"
                                                   checked="checked">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        是否成交价
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="checkbox" id="subBisPrice" name="bisPrice" value="true"
                                                   checked="checked">
                                        </label>
                                    </div>
                                    <label class="col-sm-2 control-label">
                                        是否只读
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="checkbox" id="subBisOnlyView" name="bisOnlyView" value="true"
                                                   checked="checked">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-digits="true" placeholder="排序"
                                                   id="subSorting" name="sorting" class="form-control">
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            备注
                                        </label>
                                        <div class="col-sm-10">
                                    <textarea placeholder="备注" id="subRemark" name="remark"
                                              class="form-control"></textarea>
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveSubSetUseField()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadSetUseFieldList();
    })
    //加载代理数据列表
    function loadSetUseFieldList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubSetUseField(' + row.id + ');" >查看子项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editSetUseField(' + row.id + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delSetUseField(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
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
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/setUseField/saveSetUseField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        TableReload("tb_List");
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    $("#id").val(id);
                    $("#name").val(result.data.name);
                    $("#fieldName").val(result.data.fieldName);
                    $("#bisEnable").prop("checked", result.data.bisEnable);
                    $("#sorting").val(result.data.sorting);
                    $("#remark").val(result.data.remark);
                    $('#divBox').modal();
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //删除字典数据
    function delSetUseField(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/setUseField/delSetUseField",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        $('#' + tbId).bootstrapTable("refresh");
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
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubSetUseField(' + row.id + ');" ><i class="fa fa-edit">查看子项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editSubSetUseField(' + row.id + ');" ><i class="fa fa-edit">编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delSetUseField(' + row.id + ',\'tbSetUseFieldList\')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
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
                        toastr.success('保存成功');
                        TableReload("tbSetUseFieldList");
                        $('#divSubSetUseFieldManage').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
