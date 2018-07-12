<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/17
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>报告模板管理</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div id="tree" class="col-md-3">

                            </div>
                            <div class="col-md-9">
                                <input type="hidden" id="tree_value" value="0">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select id='entrust' name='entrust' class='form-control search-select select2'
                                                onchange="getProjectClassify()">
                                            <c:forEach var="item" items="${entrust}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-xs-4">
                                        <select id='csType' name='csType' class='form-control  search-select select2'
                                                onchange="reloadTableList()">
                                            <option value="0">自然人</option>
                                            <option value="1">法人</option>
                                        </select>
                                    </div>
                                    <div class="col-xs-4">
                                        <select id='reportTypeId' name='reportTypeId'
                                                class='form-control  search-select select2'
                                                onchange="reloadTableList()">
                                            <c:forEach var="item" items="${reportType}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="tab-content">
                                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                            <li role="presentation" class="active"><a href="#tab_bookmark" id="home-tab"
                                                                                      role="tab" data-toggle="tab"
                                                                                      aria-expanded="true">书签</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_template" role="tab"
                                                                                id="profile-tab" data-toggle="tab"
                                                                                aria-expanded="false">模板</a>
                                            </li>
                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <div class="tab-pane fade active in" id="tab_bookmark"
                                                 aria-labelledby="home-tab">
                                                <p id="toolbar">
                                                    <a class="btn btn-success" onclick="addBookmark(0)">
                                                        新增书签
                                                    </a>
                                                </p>
                                                <table id="tb_bookmark_list" class="table table-bordered"></table>
                                            </div>
                                            <div class="tab-pane fade" id="tab_template" aria-labelledby="profile-tab">
                                                <p id="toolbar_files">
                                                    <a class="btn btn-success" onclick="addReportTemplate()">
                                                        新增模板
                                                    </a>
                                                </p>
                                                <table id="tb_files_list" class="table table-bordered"></table>
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
</div>
</body>


<div id="modalSubTemplate" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">

        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>书签设置</h3>
            </div>
            <p id="sublevel_toolbar">
                <a class="btn btn-success" onclick="addBookmark(1)">
                    新增
                </a>
            </p>
            <table id="tb_fileds_sublevel_list" class="table table-bordered"></table>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
            </div>
        </div>

    </div>
</div>
<div id="modalTemplate" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>书签设置</h3></div>
            <form id='frm' class='form-horizontal'>
                <input type='hidden' id='id' name='id' value="0">
                <input type='hidden' id='pid' name='pid' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            添加类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select id='templateType' name='templateType' required class='form-control'>
                                                <c:forEach var="item" items="${baseReportTemplateTypeEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            显示名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input name='bookmarkNameCn' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            书签/子模板名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input name='bookmarkName' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>数据来源</label>
                                        <div class='col-sm-10'>
                                            <select id='dataPoolType' name='dataPoolType' class='form-control'
                                                    onclick="changePool()">
                                                <c:forEach var="item" items="${baseReportDataPoolTypeEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label
                                                class='col-sm-2 control-label'></label>
                                        <div class='col-sm-5' id="div_dataPoolTableId" style="display: none;">
                                            <select id='dataPoolTableId' name='dataPoolTableId'
                                                    onclick="loadTableCloumns()"
                                                    class='form-control  search-select select2'>
                                                <option value="">--请选择--</option>
                                            </select>
                                        </div>
                                        <div class='col-sm-5' id="div_dataPoolColumnsId" style="display: none;">
                                            <select id='dataPoolColumnsId' name='dataPoolColumnsId'
                                                    class='form-control  search-select select2'>
                                                <option value="">--请选择--</option>
                                            </select>
                                        </div>
                                        <div class='col-sm-10' id="div_dataPoolTemplateId" style="display: none;">
                                            <select id='dataPoolTemplateId' name='dataPoolTemplateId'
                                                    class='form-control  search-select select2'>
                                                <option value="">--请选择--</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label
                                                class='col-sm-2 control-label'>模板文件</label>
                                        <div class='col-sm-10'>
                                            <input id="uploadFile" name="uploadFile" type="file" multiple="false">
                                            <div id="_uploadFile">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveTemplate();"
                        id='btn_save_bid_organization_agency'>保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="modalTemplateFiles" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>模板设置</h3></div>
            <form id='frm_files' class='form-horizontal'>
                <input type='hidden' id="files_id" name='id' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模板名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input name='filesRemarks' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            报告适用范围<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select id='classifyId' name='classifyId' required
                                                    class='form-control  search-select select2'>
                                                <option value="0">全部实用</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label
                                                class='col-sm-2 control-label'>报告模板</label>
                                        <div class='col-sm-10'>
                                            <input id="uploadTemplateFileReport" name="uploadTemplateFileReport"
                                                   type="file" multiple="false">
                                            <div id="_uploadTemplateFileReport">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label
                                                class='col-sm-2 control-label'>汇总模板</label>
                                        <div class='col-sm-10'>
                                            <input id="uploadTemplateFileExport" name="uploadTemplateFileExport"
                                                   type="file" multiple="false">
                                            <div id="_uploadTemplateFileExport">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveTemplateFiles();">保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</html>

<script type="text/javascript">
    var tableList = $("#tb_bookmark_list");
    var tableSubList = $("#tb_fileds_sublevel_list");
    var tableFilesList = $("#tb_files_list");
    $(function () {
        $("#entrust").select2();
        $("#csType").select2();
        $("#reportTypeId").select2();
        loadTree();

        loadDatagrid();
        loadSubDatagrid();
        loadTemplateFilesTableList();
        getProjectClassify();
        $("#dataPoolTableId").select2();
        $("#dataPoolColumnsId").select2();
        $("#dataPoolTemplateId").select2();
        FileUtils.uploadFiles({
            target: "uploadFile",
            showFileList: false,
            fileExtArray: ["doc", "docx"],
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_base_report_template",
                    creater: "${currUserAccount}",
                    tableId: $("#id").val()
                };
                return formData;
            },
            onUploadComplete: function () {
                loadTemplateAttachment($("#id").val());
            }
        });

        FileUtils.uploadFiles({
            target: "uploadTemplateFileReport",
            showFileList: false,
            fileExtArray: ["doc", "docx"],
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_base_report_template_files",
                    creater: "${currUserAccount}",
                    tableId: $("#files_id").val(),
                    fieldsName: "report"
                };
                return formData;
            },
            onUploadComplete: function () {
                loadTemplateFilesAttachment($("#files_id").val(), "report");
            }
        });
        FileUtils.uploadFiles({
            target: "uploadTemplateFileExport",
            showFileList: false,
            fileExtArray: ["xls", "xlsx"],
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_base_report_template_files",
                    creater: "${currUserAccount}",
                    tableId: $("#files_id").val(),
                    fieldsName: "export"
                };
                return formData;
            },
            onUploadComplete: function () {
                loadTemplateFilesAttachment($("#files_id").val(), "export");
            }
        });
    });

    //加载附件
    function loadTemplateAttachment(tableId) {
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: "tb_base_report_template",
                creater: "${currUserAccount}",
                tableId: tableId
            },
            deleteFlag: true
        });
    }

    //加载附件
    function loadTemplateFilesAttachment(tableId, fieldsName) {
        var target = "uploadTemplateFile";
        switch (fieldsName) {
            case "report": {
                target += "Report";
                break;
            }
            case "export": {
                target += "Export";
                break;
            }
        }
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: "tb_base_report_template_files",
                creater: "${currUserAccount}",
                tableId: tableId,
                fieldsName: fieldsName
            },
            deleteFlag: true
        });
    }
    function reloadTableList() {
        TableReload(tableList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", {
            customId: $("#tree_value").val(),
            entrustId: $("#entrust").val(),
            reportId: $("#reportTypeId").val(),
            csType: $("#csType").val()
        });
        reloadTemplateFilesTableList();
    }
    function reloadSubTableList() {
        TableReload(tableSubList, "${pageContext.request.contextPath}/templateSet/getBaseReportSubTemplateList", {
            customId: $("#tree_value").val(),
            entrustId: $("#entrust").val(),
            reportId: $("#reportTypeId").val(),
            pid: $("#pid").val()
        });
    }
    function loadTree() {

        initBaseTreeView("tree", "${pageContext.request.contextPath}/templateSet/queryCustomerTree", {pid: -1}, false, function (objs) {
            treeView_setValue("tree", 0);
            objs.on('nodeSelected', function (event, node) {
                if (node.id >= 0) {
                    $("#tree_value").val(node.id);
                    reloadTableList();
                }
            });
        });
    }
    function loadSubDatagrid() {

        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'bookmarkNameCn', width: '20%', title: '显示名称'});
        cols.push({
            field: 'bookmarkName', title: '书签名称', width: '20%', formatter: function (value, row, index) {
                var s = value;
                if (row.templateType == "${templateTypeId}") {
                    s = "<a href='javascript:;' class='btn btn-xs btn-danger tooltips'  data-toggle='tooltip' data-original-title='模板' style='margin-left: 5px'><i  class='fa fa-tag' title='模板'></i></a>" + value;
                }
                return s;
            }
        });
        cols.push({field: 'typeName', width: '10%', title: '字段类型'});
        cols.push({
            field: 'dataPoolTypename', width: '30%', title: '数据来源', formatter: function (value, row, index) {
                var s = value;
                if (value == "") {
                    if (row.templateType == "${templateId}") {
                        s = "<a href='javascript:;' onclick='showSubTemplate(" + row.dataPoolTemplateId + ")'>" + value + "</a>"
                    }
                }
                else {
                    if (row.keyValueDtos) {
                        $.each(row.keyValueDtos, function (index, item) {
                            s = "<div id='div_files_" + item.id + "' class='alert alert-info'>";
                            //在线编辑历史记录
                            s += "<i class='fa fa-folder' onclick='FileUtils.showAttachmentKeepList(" + item.key + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";

                            s += "<i class='fa fa-download' onclick='FileUtils.downAttachments(" + item.key + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";

                            s += "<a onclick='FileUtils.showAttachment(" + item.key + ",\"" + item.explain + "\")' style='cursor: pointer;'> " + item.value + "</a>";
                            s += "</div>";
                        });
                    }
                }
                return s;
            }
        });
        cols.push({
            field: 'opation', title: '操作', width: '15%', formatter: function (value, row, index) {
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editBookmark(" + row.id + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";
                s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='删除' onclick='deleteBookmark(" + row.id + ")' style='margin-left: 5px'><i class='fa fa-minus fa-white' title='删除'></i></a>";
                return s;
            }
        });

        TableInit(tableSubList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", cols,
            {
                customId: $("#tree_value").val(),
                entrustId: $("#entrust").val(),
                reportId: $("#reportTypeId").val(),
                pid: $("#pid").val()
            }, {
                toolbar: '#sublevel_toolbar',
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
    function loadDatagrid() {

        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'bookmarkNameCn', title: '显示名称', width: '20%'});
        cols.push({
            field: 'bookmarkName', title: '书签名称', width: '20%', formatter: function (value, row, index) {
                var s = value;
                if (row.templateType == "${templateTypeId}") {
                    s = "<a href='javascript:;' onclick='showSubTemplate(" + row.id + ")' class='btn btn-xs btn-danger tooltips'  data-toggle='tooltip' data-original-title='模板' style='margin-left: 5px'><i  class='fa fa-tag' title='模板'></i></a>" + value;
                }
                return s;
            }
        });
        cols.push({field: 'typeName', width: '10%', title: '字段类型'});
        cols.push({
            field: 'dataPoolTypename', width: '30%', title: '数据来源', formatter: function (value, row, index) {
                var s = value;
                if (value != "") {
                    if (row.templateType == "${templateId}") {
                        s = "<a href='javascript:;' onclick='showSubTemplate(" + row.dataPoolTemplateId + ")'>" + value + "</a>"
                    }
                }
                else {
                    if (row.keyValueDtos) {
                        $.each(row.keyValueDtos, function (index, item) {
                            // s = "<div  class='alert alert-info'>";
                            //在线编辑历史记录
                            s = "<i class='fa fa-download' onclick='FileUtils.downAttachments(" + item.key + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
                            s += "<a onclick='FileUtils.showAttachment(" + item.key + ",\"" + item.explain + "\")' style='cursor: pointer;'> " + item.value + "</a>";
                            // s += "</div>";
                        });
                    }
                }
                return s;
            }
        });
        cols.push({
            field: 'opation', title: '操作', width: '15%', formatter: function (value, row, index) {
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editBookmark(" + row.id + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";
                s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='删除' onclick='deleteBookmark(" + row.id + ")' style='margin-left: 5px'><i class='fa fa-minus fa-white' title='删除'></i></a>";
                return s;
            }
        });

        TableInit(tableList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", cols,
            {
                customId: $("#tree_value").val(),
                entrustId: $("#entrust").val(),
                reportId: $("#reportTypeId").val(),
                csType: $("#csType").val()
            }, {
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
    function addBookmark(id) {
        var pid = $("#pid").val();
        $("#frm").clearAll();
        $("#id").val(0);
        $("#dataPoolTableId").hide();
        $("#dataPoolColumnsId").hide();
        $("#dataPoolTemplateId").hide();
        if (id == 0) {
            $("#pid").val(0);
        }
        else {
            $("#pid").val(pid);
        }
        loadTemplateAttachment(0);
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }
    function editBookmark(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateById",
            data: {
                id: id
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var data = result.data;
                    $("#frm").clearAll();
                    $("#frm").initForm(data);
                    $("#dataPoolTableId").select2().val(data.dataPoolTableId).trigger("change");
                    $("#dataPoolColumnsId").select2().val(data.dataPoolColumnsId).trigger("change");
                    $("#dataPoolTemplateId").select2().val(data.dataPoolTemplateId).trigger("change");
                    $("#dataPoolType").val(data.dataPoolType);
                    changePool();
                    loadTableCloumns();
                    loadTemplateAttachment(id);
                    $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
                }
                else {
                    Alert("取数失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
    function changePool() {
        var dataPoolType = $("#dataPoolType").val();
        if (!dataPoolType) {
            return false;
        }
        if (dataPoolType != "${templateId}") {
            $("#div_dataPoolTableId").show();
            $("#div_dataPoolColumnsId").show();
            $("#div_dataPoolTemplateId").hide();
        }
        else {
            $("#div_dataPoolTableId").hide();
            $("#div_dataPoolColumnsId").hide();
            $("#div_dataPoolTemplateId").show();
        }


        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getBaseDataPool",
            type: "get",
            dataType: "json",
            data: {
                typeId: dataPoolType,
                customerId: $("#tree_value").val()
            },
            success: function (result) {
                var retHtml = '<option value="" selected>-请选择-</option>';
                if (result.ret) {

                    $.each(result.data, function (i, item) {
                        retHtml += ' <option value="' + item.key + '">' + item.value + '</option>';
                    });
                }
                if (dataPoolType != "${templateId}") {
                    $("#dataPoolTableId").html(retHtml);
                }
                else {
                    $("#dataPoolTemplateId").html(retHtml);
                }

            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
    function deleteBookmark(id) {
        var tips = "是否确认删除当前表单?";

        var url = "${pageContext.request.contextPath}/templateSet/deleteBaseReportTemplate";
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
                        if ($("#pid").val() == "0") {
                            reloadTableList();
                        }
                        else {
                            reloadSubTableList();
                        }
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
    function loadTableCloumns() {
        var dataPoolTableId = $("#dataPoolTableId").val();
        if (!dataPoolTableId) {
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getBaseReportColumnsList",
            type: "get",
            dataType: "json",
            data: {
                tableId: dataPoolTableId
            },
            success: function (result) {
                var retHtml = '<option value="" selected>-请选择-</option>';
                if (result.ret) {

                    $.each(result.data, function (i, item) {
                        retHtml += ' <option value="' + item.id + '">' + item.columnsCnName + '</option>';
                    });
                }
                $("#dataPoolColumnsId").html(retHtml);
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
    function saveTemplate() {
        if (!$("#frm").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm");
        data["customerId"] = $("#tree_value").val();
        data["entrustId"] = $("#entrust").val();
        data["reportTypeId"] = $("#reportTypeId").val();
        data["csType"] = $("#csType").val();

        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/saveTemplateData",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("保存成功");
                    if ($("#pid").val() == "0") {
                        reloadTableList();
                    }
                    else {
                        reloadSubTableList();
                    }
                    $('#modalTemplate').modal('hide');
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
    function showSubTemplate(id) {
        $("#pid").val(id);
        reloadSubTableList();
        $('#modalSubTemplate').modal({backdrop: 'static', keyboard: false});
    }

    function loadTemplateFilesTableList() {
        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'classifyName', title: '适用范围', width: '10%'});
        cols.push({field: 'filesRemarks', title: '显示名称', width: '20%'});
        cols.push({
            field: 'reportFiles', title: '报告模板', width: '30%', formatter: function (value, row, index) {
                var s = "";
                if (value) {
                    $.each(value, function (i, j) {
                        s += value;
                    })
                }
                return s;
            }
        });
        cols.push({
            field: 'exportFiles', title: '汇总表模板', width: '30%', formatter: function (value, row, index) {
                var s = "";
                if (value) {
                    $.each(value, function (i, j) {
                        s += value;
                    })
                }
                return s;
            }
        });
        cols.push({
            field: 'opation', title: '操作', width: '15%', formatter: function (value, row, index) {
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editTemplateFiles(" + row.id + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";

                if (row.bisEnable) {
                    s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='停用' onclick='stopFiles(" + row.id + ")' style='margin-left: 5px'><i class='fa fa-stop fa-white'></i></a>";
                }
                else {
                    s += "<a href='javascript:;' class='btn btn-xs btn-info tooltips'  data-toggle='tooltip' data-original-title='启用' onclick='satrtFiles(" + row.id + ")' style='margin-left: 5px'><i class='fa fa-play fa-white'></i></a>";
                }
                return s;
            }
        });

        TableInit(tableFilesList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateFilesList", cols,
            {
                customId: $("#tree_value").val(),
                entrustId: $("#entrust").val(),
                reportId: $("#reportTypeId").val(),
                csType: $("#csType").val()
            }, {
                toolbar: "#toolbar_files",
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
    function reloadTemplateFilesTableList() {
        TableReload(tableFilesList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateFilesList", {
            customId: $("#tree_value").val(),
            entrustId: $("#entrust").val(),
            reportId: $("#reportTypeId").val(),
            csType: $("#csType").val()
        });
    }
    function addReportTemplate() {
        $("#frm_files").clearAll();
        $("#files_id").val(0);
        loadTemplateFilesAttachment(0, "report");
        loadTemplateFilesAttachment(0, "export");
        $('#modalTemplateFiles').modal({backdrop: 'static', keyboard: false});
    }
    function editTemplateFiles(id) {
        $("#frm_files").clearAll();
        var row = $(tableFilesList).bootstrapTable("getRowByUniqueId", id);
        $("#frm_files").initForm(row);
        loadTemplateFilesAttachment(id, "report");
        loadTemplateFilesAttachment(id, "export");
        $('#modalTemplateFiles').modal({backdrop: 'static', keyboard: false});
    }

    function saveTemplateFiles() {
        if (!$("#frm_files").valid()) {
            return false;
        }
        var data = formParams("frm_files");
        data["customerId"] = $("#tree_value").val();
        data["entrustId"] = $("#entrust").val();
        data["reportTypeId"] = $("#reportTypeId").val();
        data["csType"] = $("#csType").val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/saveTemplateFilesData",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("保存成功");
                    reloadTemplateFilesTableList();
                    $('#modalTemplateFiles').modal('hide');
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
    function stopFiles(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/stopBaseReportTemplateFiles",
            data: {
                id: id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    reloadTemplateFilesTableList();
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
    function satrtFiles(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/startBaseReportTemplateFiles",
            data: {
                id: id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    reloadTemplateFilesTableList();
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

    function getProjectClassify() {
        reloadTableList();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getClassifyList",
            data: {
                id: $("#entrust").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var ativityHtml = "<option value='0'>所有适用</option>";
                    if (result.ret) {
                        $.each(result.data, function (i, j) {
                            ativityHtml += "<option value='" + j.id + "'>" + j.name + "</option>";
                        });
                    }
                    $("#classifyId").html(ativityHtml);
                    $("#classifyId").select2();
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

</script>




