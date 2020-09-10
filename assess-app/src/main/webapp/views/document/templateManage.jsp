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
    <title>模板管理</title>
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
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">模板名称</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="模板名称" name="templateName"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">项目类型</label>
                                        <div class="col-md-2 p-0">
                                            <select name="assessProjectType" class="form-control input-full">
                                                <option value="">-请选择-</option>
                                                <c:if test="${not empty assessProjectTypeList}">
                                                    <c:forEach var="items" items="${assessProjectTypeList}">
                                                        <option value="${items.key}">${items.explain}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">模板类型</label>
                                        <div class="col-md-2 p-0">
                                            <select name="templateType" class="form-control input-full">
                                                <option value="">-请选择-</option>
                                                <c:if test="${not empty templateTypes}">
                                                    <c:forEach items="${templateTypes}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                        <div class="col-md-3 p-0">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="loadTemplateList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>
                                            <button type="button" style="margin-left: 5px" class="btn btn-success btn-sm"
                                                    onclick="addTemplate()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <table id="tb_template_list" class="table table-bordered"></table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

<%@include file="/views/document/bookmark.jsp" %>
<%@include file="/views/document/fieldsSetting.jsp" %>
<div id="modalTemplate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">模板</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTemplate" class="form-horizontal">
                    <input type="hidden" id="templateId" name="id" required>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                模板名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-maxlength="50" placeholder="模板名称"
                                                       id="templateName" name="templateName"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                提供日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" placeholder="提供日期"
                                                       class="form-control date-picker dbdate input-full" required
                                                       data-rule-maxlength="50"
                                                       data-date-format="yyyy-mm-dd" name="provideDate"
                                                       readonly="readonly">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full" required
                                                        data-rule-maxlength="50" name="assessProjectType"
                                                        onchange="getNumberRule(this);">
                                                    <option value="">--请选择--</option>
                                                    <c:if test="${not empty assessProjectTypeList}">
                                                        <c:forEach var="items" items="${assessProjectTypeList}">
                                                            <option value="${items.key}">${items.explain}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                文号规则<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full" name="numbetRuleId"
                                                        data-rule-maxlength="50">

                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                模板类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full" required name="templateType"
                                                        data-rule-maxlength="50">
                                                    <option value="">--请选择--</option>
                                                    <c:if test="${not empty templateTypes}">
                                                        <c:forEach items="${templateTypes}" var="item">
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                模板
                                            </label>
                                            <div class="col-sm-11">
                                                <input id="file_upload" name="file_upload" type="file" multiple="false">
                                                <div id="_file_upload">
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

</body>
</html>


<script type="application/javascript">
    var templateTable = $("#tb_template_list")
    $(function () {
        FileUtils.uploadFiles({
            target: "file_upload",
            fileExtArray: ["doc", "docx"],
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_document_template",
                    tableId: $("#templateId").val()
                };
                return formData;
            },
            editFlag: true,
            deleteFlag: true
        }, {
            onUploadComplete: function (file, result) {
                loadTemplateAttachment();
            }
        });
        loadTemplateList();
    })

    //加载附件
    function loadTemplateAttachment() {
        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: "tb_document_template",
                tableId: $("#templateId").val()
            },
            editFlag: true,
            deleteFlag: true
        });
    }

    function loadTemplateList() {
        var cols = [];
        cols.push({field: 'templateName', width: "25%", title: '模板名称'});
        cols.push({
            field: 'provideDate', width: "15%", title: '提供日期', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'assessProjectTypeName', width: "15%", title: '项目类型'});
        cols.push({field: 'reportTypeName', width: "15%", title: '文号规则'});
        cols.push({field: 'templateTypeName', width: "15%", title: '模板类型'});
        cols.push({
            field: 'opation', width: "15%", title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="editTemplate(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="loadFiledWindow(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="字段">';
                str += '<i class="fa fa-th-list"></i>';
                str += '</button>';
                str += '<button onclick="loadBookmarkWindow(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="书签">';
                str += '<i class="fa fa-tags"></i>';
                str += '</button>';

                return str;
            }
        });
        var data = formSerializeArray($('#frmQuery'));
        templateTable.bootstrapTable('destroy');
        TableInit(templateTable, "${pageContext.request.contextPath}/DocumentTemplate/getDocumentTemplate", cols, data, {
            showColumns: false,
            toolbar: "toolbar_template",
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    function loadBookmarkWindow(id) {
        $("#templateId").val(id);
        reloadBookmarkList(id);
        $('#modalTemplate_bookmark_base').modal({backdrop: 'static', keyboard: false});
    }

    function loadFiledWindow(id) {
        $("#templateId").val(id);
        reloadFieldList(id);
        $('#modalTemplate_fields_base').modal({backdrop: 'static', keyboard: false});
    }

    //新增类型
    function addTemplate() {
        $("#frmTemplate").clearAll();
        $("#templateId").val(0);
        getNumberRule();
        loadTemplateAttachment();
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }

    //编辑模板
    function editTemplate(index) {
        var row = $(templateTable).bootstrapTable('getData')[index];
        $("#frmTemplate").clearAll();
        $("#frmTemplate").initForm(row);
        getNumberRule(row.numbetRuleId);
        loadTemplateAttachment();
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }


    //保存模板
    function saveTemplate() {
        if (!$("#frmTemplate").valid()) {
            return false;
        }
        var data = formParams("frmTemplate");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/DocumentTemplate/saveTemplate",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    TableReload(templateTable);
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    $('#modalTemplate').modal('hide');
                } else {
                    AlertError("错误", "保存数据失败");
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("错误", "保存数据失败");
            }
        })
    }


    //类别
    function getNumberRule(numbetRuleId) {
        $("#frmTemplate").find('[name=numbetRuleId]').html('');
        var assessProjectType = $("#frmTemplate").find('[name=assessProjectType]').val();
        if (!assessProjectType) {
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/numberRule/getDataByProjectType",
            type: "post",
            dataType: "json",
            data: {assessProjectType: assessProjectType},
            success: function (result) {
                if (result.ret) {
                    var data = result.data
                    if (data.length >= 1) {
                        var option = "<option value=''>请选择</option>";
                        for (var i = 0; i < data.length; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].reportTypeName + "</option>";
                        }
                        $("#frmTemplate").find('[name=numbetRuleId]').html(option);
                        if (numbetRuleId) {
                            $("#frmTemplate").find('[name=numbetRuleId]').val(numbetRuleId);
                        }
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })

    }
</script>


