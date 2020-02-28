<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2018-8-10
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="modalTemplate_fields_base" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">字段</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <p id="toolbar_field">
                                <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                        onclick="addTemplateFields()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </p>
                            <table id="tb_field_list" class="table table-bordered"></table>

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



<div id="modalTemplate_fields" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">合同字段</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTemplate_fields" class="form-horizontal">
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
                                                <input type="hidden" id="template_fields_id" name="id" required class='form-control'>
                                                <input type="hidden" id="template_fields_templateId" name="templateId" required class='form-control'>
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="name" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                显示名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-maxlength="50" placeholder="显示名称"
                                                       id="displayName" name="displayName" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                字段类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select id='template_fields_fieldType' name="fieldType" class='form-control search-select select2 input-full' required>
                                                    <option value="">请选择</option>
                                                    <c:forEach var="item" items="${customSetType}">
                                                        <option value="${item.id}">${item.cnName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                数据源
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="50" placeholder="数据源"
                                                       id="dataSource" name="dataSource" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                字段最大长度
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="50" placeholder="字段最大长度"
                                                       id="fieldLength" name="fieldLength" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="50" placeholder="排序"
                                                       id="sorting" name="sorting" class="form-control input-full">
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
                                                        <input class="form-check-input" type="checkbox" id="bisRequired"
                                                               name="bisRequired" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">必填</span>
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
                                                        <input class="form-check-input" type="checkbox" id="bisShow"
                                                               name="bisShow" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">显示</span>
                                                    </label>
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
                    取消
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveTemplateFields()">
                    保存
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
        cols.push({field: 'name',width:"10%", title: '字段'});
        cols.push({field: 'displayName',width:"30%", title: '名称'});
        cols.push({field: 'fieldTypeName',width:"10%", title: '字段类型'});
        cols.push({field: 'fieldLength',width:"10%", title: '最大长度'});
        cols.push({field: 'sorting',width:"10%", title: '排序'});
        cols.push({
            field: 'bisRequired',width:"10%", title: '必填', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisShow',width:"10%", title: '显示', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'opation',width:"10%", title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="editTemplateFields(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="deleteTemplateFields(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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
                    notifySuccess("成功","删除数据成功");
                    TableReload(fieldsTable);
                }
                else {
                    AlertError("删除数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
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
        //Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/DocumentTemplate/saveCmsTemplateField",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                //Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    TableReload(fieldsTable);
                    $('#modalTemplate_fields').modal('hide');
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                //Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>