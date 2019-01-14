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
                                        <select id='projectType' class='form-control search-select select2' onchange="getProjectClassify()">
                                            <c:forEach var="item" items="${projectTypeList}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-xs-4">
                                        <select id='projectCategory' class='form-control  search-select select2'
                                                onchange="loadTemplateTableList()">
                                        </select>
                                    </div>
                                    <div class="col-xs-4">
                                        <select id='reportType'
                                                class='form-control  search-select select2'
                                                onchange="loadTemplateTableList()">
                                            <c:forEach var="item" items="${reportType}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="tab-content">
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
</body>

<div id="modalTemplate" class="modal fade bs-example-modal-lg"
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
                                            <input name='name' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            委托目的
                                        </label>
                                        <div class="col-sm-10">
                                            <select  name='entrustPurpose' class='form-control  search-select select2'>
                                                <option value="0">-请选择-</option>
                                                <c:forEach var="item" items="${entrustPurposeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>报告模板</label>
                                        <div class='col-sm-10'>
                                            <input id="uploadFile" name="uploadFile"
                                                   type="file" multiple="false">
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
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveTemplate();">保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</html>

<script type="text/javascript">
    $(function () {
        loadTree();
        loadTemplateTableList();
        FileUtils.uploadFiles({
            target: "uploadFile",
            showFileList: false,
            fileExtArray: ["doc", "docx"],
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_base_report_template",
                    creater: "${currUserAccount}",
                    tableId: $("#files_id").val()
                };
                return formData;
            },
            onUploadComplete: function () {
                loadTemplateAttachment($("#files_id").val());
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

    function loadTree() {
        initBaseTreeView("tree", "${pageContext.request.contextPath}/templateSet/queryCustomerTree", {pid: -1}, false, function (objs) {
            treeView_setValue("tree", 0);
            objs.on('nodeSelected', function (event, node) {
                if (node.id >= 0) {
                    $("#tree_value").val(node.id);
                    loadTemplateTableList();
                }
            });
        });
    }

    function loadTemplateTableList() {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '20%'});
        cols.push({field: 'entrustPurposeName', title: '委托目的', width: '20%'});
        cols.push({
            field: 'report', title: '报告模板', width: '30%', formatter: function (value, row, index) {
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
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editTemplate(" + row.id + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";
                if (row.bisEnable) {
                    s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='停用' onclick='stop(" + row.id + ")' style='margin-left: 5px'><i class='fa fa-stop fa-white'></i></a>";
                }
                else {
                    s += "<a href='javascript:;' class='btn btn-xs btn-info tooltips'  data-toggle='tooltip' data-original-title='启用' onclick='satrt(" + row.id + ")' style='margin-left: 5px'><i class='fa fa-play fa-white'></i></a>";
                }
                return s;
            }
        });
        $("#tb_files_list").bootstrapTable('destroy');
        TableInit("tb_files_list", "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", cols,
            {
                useUnit: $("#tree_value").val(),
                type: $("#projectType").val(),
                category: $("#projectCategory").val(),
                reportType: $("#reportType").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
   
    function addReportTemplate() {
        $("#frm_files").clearAll();
        $("#files_id").val(0);
        loadTemplateAttachment(0);
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }
    function editTemplate(id) {
        $("#frm_files").clearAll();
        var row = $("#tb_files_list").bootstrapTable("getRowByUniqueId", id);
        $("#frm_files").initForm(row);
        loadTemplateAttachment(id);
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }

    function saveTemplate() {
        if (!$("#frm_files").valid()) {
            return false;
        }
        var data = formParams("frm_files");
        data["useUnit"] = $("#tree_value").val();
        data["type"] = $("#projectType").val();
        data["category"] = $("#projectCategory").val();
        data["reportType"] = $("#reportType").val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/saveTemplateData",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("保存成功");
                    loadTemplateTableList();
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
    function stop(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/stopBaseReportTemplate",
            data: {
                id: id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    loadTemplateTableList();
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
    function satrt(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/startBaseReportTemplate",
            data: {
                id: id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    loadTemplateTableList();
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
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProjectClassify/getCacheProjectClassifyListByPid",
            data: {
                pid: $("#projectType").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var ativityHtml = "<option value=''>-请选择-</option>";
                    if (result.ret) {
                        $.each(result.data, function (i, j) {
                            ativityHtml += "<option value='" + j.id + "'>" + j.name + "</option>";
                        });
                    }
                    $("#projectCategory").html(ativityHtml).trigger('change');
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




