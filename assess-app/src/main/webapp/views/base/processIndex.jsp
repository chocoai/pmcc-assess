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
                                <input type="hidden" id="id" name="id" value="0">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="hidden" id="boxName" name="boxName">
                                            <input type="text" required placeholder="模型" readonly="readonly" id="cnName"
                                                   name="cnName" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            key
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="key" name="name" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            监听器
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="监听器" name="executor" class="form-control"
                                                   maxlength="200">
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
                                <a id="btn_add_process_form" class="btn btn-success"
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
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">表单编辑</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <form id="frm_processForm" class="form-horizontal" onsubmit="return false;">
                                <input type="hidden" id="form_id" name="id" value="0">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            填写节点<span class="symbol required"></span>
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
                                            表单名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="hidden" name="formId" id="formId" value="0">
                                            <input type="text" id="formIdName" name="formIdName" class="form-control"
                                                   required readonly="readonly" onclick="loadSelectFormList();">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            表单模块名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <select required name="formModuleId" id="formModuleId"
                                                    class="form-control ">

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



<script type="text/javascript" src="/pmcc-bpm/js/bpm-box-utils.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var currProcessId = 0;
    $(function () {
        loadProcessTable();
        $("#btn_process").click(function () {
            saveProcess();
        });
        $("#btn_process_form").click(function () {
            saveProcessForm();
        });
        $("#cnName").click(function () {
            bpmBoxRe.select(function (row) {
                $("#boxName").val(row.name);
                $("#cnName").val(row.cnName);
            });
        });
    })

    function loadProcessTable() {
        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'cnName', title: '流程名称'});
        cols.push({field: 'boxName', title: '模型'});
        cols.push({field: 'name', title: '值'});
        cols.push({field: 'executor', title: '监听器'});
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
        $('#model_process').modal({backdrop: 'static', keyboard: false});
    }

    function editProcess(index) {
        var row = getTableDataByIndex("tb_processList", index);
        $("#frm_process").clearAll();
        $("#frm_process").initForm(row);
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
        currProcessId = row.id;
        loadBoxReActivityList(row.id);
        loadProcessFormTable(row.id);
        $('#model_processFormList').modal({backdrop: 'static', keyboard: false});

    }
    function loadProcessFormTable(processId) {
        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'formModuleName', title: '表单名称'});
        cols.push({field: 'sorting', title: '序号'});
        cols.push({field: 'boxReActivityName', title: '流程节点'});

        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var s = "";
                s += "<a class='btn btn-xs btn-warning' style='font-size: 12px;margin-right: 8px;' href='javascript:void(0)' title='删除' onclick='delProcessForm(" + row.id + ")'><i class='fa fa-trash-o'></i>删除</a>";
                return s;
            }
        });
        $("#tb_processFormList").bootstrapTable('destroy');
        TableInit("tb_processFormList", "${pageContext.request.contextPath}/baseProcess/getBaseProcessFormList", cols, {
            processId: processId
        }, {
            toolbar: "#procesFormBar"
        });
    }

    function loadBoxReActivityList(processId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProcess/getBoxReActivityList",
            data: {
                processId: processId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                var ativityHtml = "<option value=''>-选择-</option>";
                if (result.ret) {
                    $.each(result.data, function (i, j) {
                        ativityHtml += "<option value='" + j.name + "_" + j.sortMultilevel + "'>" + j.cnName + "_" + j.sortMultilevel + "</option>";
                    });
                }
                $("#boxReActivityName").html(ativityHtml);
                $("#boxReActivityName").select2();
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
            data.processId = currProcessId;
            data.formModuleName=$("#formModuleId").find("option:selected").text();
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

    //加载列表数据
    function loadSelectFormList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'professionalDisplayName', title: '显示名称'});
        cols.push({field: 'professionalDisplayName', title: '分组'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="selectFormClick(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#select_form_tb_list").bootstrapTable('destroy');
        TableInit("select_form_tb_list", "${pageContext.request.contextPath}/formConfigure/getFormList", cols, {
            name: $("#queryName").val(),
            groupId: $("#queryGroupId").val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onLoadSuccess: function () {
                $('#select_form_modal').modal('show');
            }
        });
    }

    function selectFormClick(index) {
        var row = getTableDataByIndex("select_form_tb_list", index);
        $("#formId").val(row.id);
        $("#formIdName").val(row.name);
        //加载表单模块
        loadFormModuleList(row.id);
        $('#select_form_modal').modal('hide');
    }

    function loadFormModuleList(formId, value) {
        $.ajax({
            url: "${pageContext.request.contextPath}/formConfigure/getFormModules",
            data: {formId: formId},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var html = "";
                        $.each(result.data, function (i, item) {
                            html += '<option value="' + item.id + '">' + item.name + '</option>';
                        })
                        $("#formModuleId").append(html).val(value);
                    }
                }
            }
        });
    }
</script>

</body>
</html>
