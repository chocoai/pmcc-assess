<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/11/27
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed"><%--<%@include file="share/main_head.jsp" %>--%><!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12 ">
                    <!-- start: DEFAULT TREE PANEL -->
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>${baseViewDto.currentMenu.name}</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div id="procesBar">
                                <a class="btn btn-success" onclick="addProcess()">
                                    <i class="fa fa-plus"></i>
                                    新增流程
                                </a>
                            </div>
                            <table id="tb_processList" class="table table-bordered"></table>
                        </div>
                    </div>
                    <!-- end: DEFAULT TREE PANEL -->
                </div>
            </div>
        </div>
    </div>
</div>


<div id="model_process" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">流程编辑</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <form id="frm_process" class="form-horizontal" onsubmit="return false;">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="hidden" name="id" id="id" value="0">
                                            <input type="text" required placeholder="名称" name="cnName"
                                                   class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        值
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="值" name="name" class="form-control"
                                               maxlength="200">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模型
                                        </label>
                                        <div class="col-sm-9">
                                            <select required name="boxName" id="boxName"
                                                    class="form-control search-select select2">
                                                <option value="">-选择-</option>
                                                <c:forEach var="item" items="${boxRe}">
                                                    <option value="${item.name}">${item.cnName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            业务方法
                                        </label>
                                        <div class="col-sm-9">
                                            <select required name="baseForm" id="baseForm"
                                                    class="form-control search-select select2">
                                                <option value="">-选择-</option>
                                                <c:forEach var="item" items="${hrBaseForm}">
                                                    <option value="${item.name}">${item.cnName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        是否启用
                                    </label>
                                    <div class="col-sm-9">
                                        <label class="radio-inline">
                                            <input type="checkbox" class="grey" value="true" name="bisEnable"
                                                   id="bisEnable">是
                                        </label>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" id="btn_process" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<div id="model_processFormList" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">流程表单</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <div id="procesFormBar">
                                <a id="btn_add_process_form" style="display: none" class="btn btn-success"
                                   onclick="addProcessForm()">
                                    <i class="fa fa-plus"></i>
                                    新增节点表单
                                </a>
                            </div>
                            <table id="tb_processFormList" class="table table-bordered"></table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>

<div id="model_processForm" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">表单编辑</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <form id="frm_processForm" class="form-horizontal" onsubmit="return false;">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            填写节点
                                        </label>
                                        <div class="col-sm-9">
                                            <select required name="boxReActivityName" id="boxReActivityName"
                                                    class="form-control search-select select2">
                                                <option value="">-选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            表单名称
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="hidden" name="id" id="form_id" value="0">
                                            <select required name="name" id="name"
                                                    class="form-control search-select select2">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" id="btn_process_form" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var process = "";
    var currBoxName = "";
    $(function () {
        loadProcessTable();
        loadProcessFormTable();
        $("#btn_process").click(function () {
            saveProcess();
        });
        $("#btn_process_form").click(function () {
            saveProcessForm();
        });
        $("#boxName").select2();
        $("#baseForm").select2();
    })

    function loadProcessTable() {
        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'cnName', title: '流程名称'});
        cols.push({field: 'name', title: '值'});
        cols.push({field: 'boxName', title: '模型'});
        cols.push({field: 'baseForm', title: '业务方法'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var s = "";
                s += "<a class='btn btn-xs btn-success' style='font-size: 12px;margin-right: 8px;' href='javascript:void(0)' title='编辑' onclick='editProcess(" + index + ")'><i class='fa fa-edit'></i>编辑</a>";
                s += "<a class='btn btn-xs btn-warning' style='font-size: 12px;margin-right: 8px;' href='javascript:void(0)' title='删除' onclick='delProcess(" + row.id + ")'><i class='fa fa-trash-o'></i>删除</a>";

                s += "<a class='btn btn-xs btn-success' style='font-size: 12px;margin-right: 8px;' href='javascript:void(0)' title='表单设置' onclick='setProcessForm(" + index + ")'><i class='fa fa-cog'></i>表单设置</a>";
                return s;
            }
        });

        TableInit("tb_processList", "${pageContext.request.contextPath}/baseProcess/getBaseProcessList", cols, {}, {
            toolbar: "#procesBar"
        });
    }

    function addProcess() {
        $("#frm_process").clearAll();
        $("#id").val(0);
        $("#boxName").select2();
        $("#baseForm").select2();
        $('#model_process').modal({backdrop: 'static', keyboard: false});
    }

    function editProcess(index) {
        var row = getTableDataByIndex("tb_processList", index);
        $("#frm_process").clearAll();
        $("#frm_process").initForm(row);
        $("#boxName").select2().val(row.boxName).trigger("change");
        $("#baseForm").select2().val(row.baseForm).trigger("change");
        $('#model_process').modal({backdrop: 'static', keyboard: false});
        $("#id").val(row.id);
    }

    function delProcess(id) {
        var tips = "是否确认删除当前流程?";

        var url = "${pageContext.request.contextPath}/baseProcess/deleteBaseProcess";
        Alert(tips, 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: url,
                data: {
                    id: id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("操作成功");
                        TableReload("tb_processList");
                    }
                    else {
                        Alert("操作失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }

    function saveProcess() {
        if ($("#frm_process").valid()) {
            Loading.progressShow();
            var data = formParams("frm_process");
            data.bisEnable = $("#bisEnable").prop("checked");
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProcess/saveBaseProcess",
                data: data,
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("保存成功");
                        TableReload("tb_processList");
                        $('#model_process').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        }

        return false;
    }


    function setProcessForm(index) {
        var row = getTableDataByIndex("tb_processList", index);
        process = row.baseForm;
        currBoxName = row.boxName;
        loadFormList(row.id);
        TableReload("tb_processFormList", "${pageContext.request.contextPath}/baseProcess/getBaseProcessFormList", {
            process: row.baseForm,
            boxName: row.boxName
        });
        $('#model_processFormList').modal({backdrop: 'static', keyboard: false});

    }
    function loadProcessFormTable() {
        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'cnName', title: '名称'});
        cols.push({field: 'name', title: '值'});
        cols.push({field: 'sorting', title: '序号'});
        cols.push({field: 'boxReActivityName', title: '流程节点'});

        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var s = "";
                s += "<a class='btn btn-xs btn-success' style='font-size: 12px;margin-right: 8px;' href='javascript:void(0)' title='编辑' onclick='editProcessForm(" + index + ")'><i class='fa fa-edit'></i>编辑</a>";
                s += "<a class='btn btn-xs btn-warning' style='font-size: 12px;margin-right: 8px;' href='javascript:void(0)' title='删除' onclick='delProcessForm(" + row.id + ")'><i class='fa fa-trash-o'></i>删除</a>";
                return s;
            }
        });

        TableInit("tb_processFormList", "${pageContext.request.contextPath}/baseProcess/getBaseProcessFormList", cols, {
            process: process
        }, {
            toolbar: "#procesFormBar"
        });
    }

    function loadFormList(processId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProcess/getFormListByProcess",
            data: {
                processId: processId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                var formListHtml = "<option value=''>-选择-</option>";
                var ativityHtml = "<option value=''>-选择-</option>";
                if (result.ret) {

                    $.each(result.data.boxReActivityDtoList, function (i, j) {
                        ativityHtml += "<option value='" + j.name + "_" + j.sortMultilevel + "'>" + j.cnName + "_" + j.sortMultilevel + "</option>";
                    });
                    $.each(result.data.hrBaseFormLists, function (i, j) {
                        formListHtml += "<option value='" + j.name + "'>" + j.cnName + "</option>";
                    });
                }

                $("#name").html(formListHtml);
                $("#boxReActivityName").html(ativityHtml);
                $("#boxReActivityName").select2();
                $("#name").select2();
                $("#btn_add_process_form").show();
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function addProcessForm() {
        $("#frm_processForm").clearAll();
        $("#form_id").val(0);
        $("#boxReActivityName").select2();
        $("#name").select2();
        $('#model_processForm').modal({backdrop: 'static', keyboard: false});
    }

    function editProcessForm(index) {
        var row = getTableDataByIndex("tb_processFormList", index);
        $("#frm_processForm").initForm(row);
        $("#boxReActivityName").select2().val(row.boxReActivityName + "_" + row.sorting).trigger("change");
        $("#name").select2().val(row.name).trigger("change");
        $('#model_processForm').modal({backdrop: 'static', keyboard: false});
        $("#form_id").val(row.id);
    }

    function delProcessForm(id) {
        var tips = "是否确认删除当前表单?";

        var url = "${pageContext.request.contextPath}/baseProcess/deleteBaseProcessForm";
        Alert(tips, 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: url,
                data: {
                    id: id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("操作成功");
                        TableReload("tb_processFormList");
                    }
                    else {
                        Alert("操作失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }


    function saveProcessForm() {
        if ($("#frm_processForm").valid()) {
            Loading.progressShow();
            var data = formParams("frm_processForm");
            data["process"] = process;
            data["boxName"] = currBoxName;
            data["cnName"] = $("#name").find("option:selected").text();
            var activity = $("#boxReActivityName").val().split('_');
            data["boxReActivityName"] = activity[0];
            data["sorting"] = activity[1];
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProcess/saveBaseProcessForm",
                data: data,
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("保存成功");
                        TableReload("tb_processFormList");
                        $('#model_processForm').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        }
    }
</script>

</body>
</html>
