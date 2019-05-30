<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2018-8-10
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="modalTemplate_fields_base" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>字段</h3></div>
            <p id="toolbar_field">
                <a class="btn btn-success" onclick="addTemplateFields();"> 新增 </a>
            </p>
            <table id="tb_field_list" class="table table-bordered"></table>

            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
            </div>
        </div>
    </div>
</div>


<div id="modalTemplate_fields" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>合同字段</h3></div>
            <form id='frmTemplate_fields' class='form-horizontal'>
                <div class='modal-body'>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>名称<span class="symbol required"></span></label>
                            <div class='col-sm-10'>
                                <input type="hidden" id="template_fields_id" name="id" required class='form-control'>
                                <input type="hidden" id="template_fields_templateId" name="templateId" required class='form-control'>
                                <input type="text" name="name" required class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>
                                显示名称<span class="symbol required"></span>
                            </label>
                            <div class='col-sm-10'>
                                <input type="text" name="displayName" required class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>
                                字段类型
                            </label>
                            <div class='col-sm-10'>
                                <select id='template_fields_fieldType' name="fieldType" class='form-control search-select select2' required>
                                    <option value="">请选择</option>
                                    <c:forEach var="item" items="${customSetType}">
                                        <option value="${item.id}">${item.cnName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>
                                数据源
                            </label>
                            <div class='col-sm-10'>
                                <input type="text" name="dataSource" class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>
                                字段最大长度
                            </label>
                            <div class='col-sm-10'>
                                <input type="text" data-rule-digits="true" name="fieldLength" class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                排序
                            </label>
                            <div class='col-sm-10'>
                                <input type="text" data-rule-digits="true" name="sorting" class='form-control'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">必填</label>
                            <div class='col-sm-4'>
                                <input type="checkbox" value="true" name="bisRequired"
                                       id="bisRequired"><label for="bisRequired">是</label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">显示</label>
                            <div class='col-sm-4'>
                                <input type="checkbox" value="true" name="bisShow"
                                       id="bisShow"><label for="bisShow">是</label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary' onclick="saveTemplateFields();">保存
                </button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var fieldsTable = $("#tb_field_list");
    $(function () {
        $("#template_fields_fieldType").select2();
        loadTemplateFieldList();
    })
    function reloadFieldList(templateId) {
        TableReload(fieldsTable, "${pageContext.request.contextPath}/DocumentTemplate/getCmsTemplateField", {
            templateId: templateId
        });
    }
    //加载列表数据
    function loadTemplateFieldList() {
        var cols = [];
        cols.push({field: 'name', title: '字段'});
        cols.push({field: 'displayName', title: '名称'});
        cols.push({field: 'fieldTypeName', title: '字段类型'});
        cols.push({field: 'fieldLength', title: '最大长度'});
        cols.push({field: 'sorting', title: '排序'});
        cols.push({
            field: 'bisRequired', title: '必填', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisShow', title: '显示', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {

                var str = "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='editTemplateFields(" + index + ")'   ><i class='fa fa-edit fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips'  onclick='deleteTemplateFields(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                return str;
            }
        });
        TableInit(fieldsTable, "${pageContext.request.contextPath}/DocumentTemplate/getCmsTemplateField", cols, {
            templateId: 0
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //新增模板字段
    function addTemplateFields() {
        $("#frmTemplate_fields").clearAll();
        $("#template_fields_templateId").val($("#templateId").val());
        $("#template_fields_id").val(0);
        $('#modalTemplate_fields').modal({backdrop: 'static', keyboard: false});
    }
    //编辑模板字段
    function editTemplateFields(index) {

        var row = $(fieldsTable).bootstrapTable('getData')[index];
        $("#frmTemplate_fields").clearAll();
        $("#frmTemplate_fields").initForm(row);
        $("#template_fields_fieldType").select2().val(row.fieldType).trigger("change");
        $('#modalTemplate_fields').modal({backdrop: 'static', keyboard: false});
    }

    //删除模板字段
    function deleteTemplateFields(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/DocumentTemplate/deleteCmsTemplateField",
            type: "post",
            dataType: "json",
            data: {
                id: id
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    TableReload(fieldsTable);
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
    }

    //保存模板字段
    function saveTemplateFields() {
        if (!$("#frmTemplate_fields").valid()) {
            return false;
        }
        var data = formParams("frmTemplate_fields");
        data.bisRequired = $("#bisRequired").prop("checked");
        data.bisShow = $("#bisShow").prop("checked");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/DocumentTemplate/saveCmsTemplateField",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    TableReload(fieldsTable);
                    $('#modalTemplate_fields').modal('hide');
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