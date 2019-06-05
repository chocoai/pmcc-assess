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


<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p id="toolbar_template">
                                <a class="btn btn-success" onclick="addTemplate();"> 新增 </a>
                            </p>
                            <table id="tb_template_list" class="table table-bordered"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
<%@include file="/views/document/bookmark.jsp" %>
<%@include file="/views/document/fieldsSetting.jsp" %>
<div id="modalTemplate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>模板</h3></div>
            <form id='frmTemplate' class='form-horizontal'>
                <div class='modal-body'>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>名称<span class="symbol required"></span></label>
                            <div class='col-sm-10'>
                                <input type="hidden" id="templateId" name="id" required class='form-control'>
                                <input type="text" name="templateName" required class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>文号规则</label>
                            <div class='col-sm-10'>
                                <input type="text" name="prefix" class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>模板</label>
                            <div class='col-sm-10'>
                                <input id="file_upload" name="file_upload" type="file" multiple="false">
                                <div id="_file_upload">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary' onclick="saveTemplate();">保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
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
        cols.push({field: 'templateName', title: '模板名称'});
        cols.push({field: 'prefix', title: '文号规则'});
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {

                var str = "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='editTemplate(" + index + ")'   ><i class='fa fa-edit fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='字段'  class='btn btn-xs btn-info tooltips'  onclick='loadFiledWindow(" + row.id + ")'   ><i class='fa fa-external-link fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='书签'  class='btn btn-xs btn-info tooltips'  onclick='loadBookmarkWindow(" + row.id + ")'   ><i class='fa fa-bookmark-o fa-white'></i></a>";
                return str;
            }
        });
        TableInit(templateTable, "${pageContext.request.contextPath}/DocumentTemplate/getDocumentTemplate", cols, {}, {
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
        loadTemplateAttachment();
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }
    //编辑模板
    function editTemplate(index) {
        var row = $(templateTable).bootstrapTable('getData')[index];
        $("#frmTemplate").clearAll();
        $("#frmTemplate").initForm(row);
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
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    TableReload(templateTable);
                    toastr.success('保存成功');
                    $('#modalTemplate').modal('hide');
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


</script>


