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
    <title>基础报告模板</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
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
                                    <div class="col-md-3" style="max-height: 500px;overflow-y: auto;">
                                        <ul id="ztree" class="ztree"></ul>
                                    </div>
                                    <div class="col-md-9">
                                        <input type="hidden" id="tree_value" value="0">
                                        <div class="form-group form-inline">
                                            <div class="col-md-3 p-0">
                                                <select id='projectType' class='form-control input-full search-select select2'
                                                        onchange="getProjectClassify()">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach var="item" items="${projectTypeList}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-3 p-0">
                                                <select id='projectCategory' class='form-control input-full  search-select select2'
                                                        onchange="loadTemplateTableList()">
                                                </select>
                                            </div>
                                            <div class="col-md-3 p-0">
                                                <select id='reportType'
                                                        class='form-control input-full  search-select select2'
                                                        onchange="loadTemplateTableList()">
                                                    <c:forEach var="item" items="${reportType}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <button style="margin-left: 10px" class="btn btn-success btn-sm" type="button"
                                                    data-toggle="modal" onclick="addReportTemplate()"
                                                    href="#modalTemplate">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增
                                            </button>
                                        </div>

                                        <table id="tb_files_list" class="table table-bordered"></table>
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
<div id="modalTemplate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">模板设置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id='frm_files' class='form-horizontal'>
                    <input type='hidden' id="files_id" name='id' value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                模板名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input name='name' class='form-control input-full' required
                                                       maxlength="200">
                                            </div>
                                            <label class="col-sm-2 col-form-label">
                                                贷款类型
                                            </label>
                                            <div class="col-sm-4">
                                                <select name='loanType' class='form-control input-full  search-select select2'>
                                                    <option value="0">-请选择-</option>
                                                    <c:forEach var="item" items="${loanTypeList}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                委托目的<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="entrustPurpose" multiple="multiple"
                                                        class="form-control input-full search-select select2"
                                                        required="required">
                                                    <c:forEach items="${entrustPurposeList}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                报告模板
                                            </label>
                                            <div class="col-sm-10">
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
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveTemplate()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
</html>

<script type="text/javascript">
    $(function () {
        ztreeInit();
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
            editFlag: true,
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
        cols.push({field: 'loanTypeName', title: '贷款类型', width: '20%'});
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
                var s = '<button onclick="editTemplate(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                s += '<i class="fa fa-pen"></i>';
                s += '</button>';
                if (row.bisEnable) {
                    s += '<button onclick="stop(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-success  btn-xs tooltips"  data-placement="bottom" data-original-title="停用">';
                    s += '<i class="fa fa-lock"></i>';
                    s += '</button>';
                }
                else {
                    s += '<button onclick="satrt(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="启用">';
                    s += '<i class="fa fa-lock-open"></i>';
                    s += '</button>';
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
        $("#frm_files").find("[name='entrustPurpose']").val(row.entrustPurpose.split(',')).trigger('change');

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
        data.entrustPurpose = ',' + data.entrustPurpose + ',';//方便like查询
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/saveTemplateData",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    loadTemplateTableList();
                    $('#modalTemplate').modal('hide');
                }
                else {
                    AlertError("保存数据失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("暂停失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("启动失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    var zTreeObj;
    var setting = {
        data: {
            key: {
                name: "name"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },// 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                var node = zTreeObj.getSelectedNodes()[0];
                $("#tree_value").val(node.id);
                loadTemplateTableList();
            }
        }
    };


    function ztreeInit() {
        $.ajax({
            url: '${pageContext.request.contextPath}/templateSet/queryCustomerTree',
            data: {},
            type: 'get',
            dataType: "json",
            success: function (result) {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, result.data);
                //展开第一级，选中根节点
                var rootNode = zTreeObj.getNodes()[0];
                zTreeObj.selectNode(rootNode);
                zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, rootNode);//调用事件
            }
        })
    }
</script>



