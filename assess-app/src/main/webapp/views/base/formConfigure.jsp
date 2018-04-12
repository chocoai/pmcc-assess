<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/19
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-4 ">
                    <div class="x_panel">
                        <div class="x_title">
                            <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            表单数据
                        </div>
                        <div class="x_content">
                            <table id="tb_form_list" class="table table-bordered">

                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 ">
                    <div class="x_panel" id="panel_form_list" style="display: none;">
                        <div class="x_title">
                            表单内容panel
                        </div>
                        <div class="x_content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="tb_form_modular_list" class="table table-bordered"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="x_panel" id="panel_form_list_field" style="display: none;">
                <div class="x_title">
                    panel包含字段
                </div>
                <div class="x_content">
                    <button type="button" class="btn btn-success" data-toggle="modal"
                            href="#modal_form_modular_field"
                            onclick="addFormModularField();"> 新增
                    </button>
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="tb_form_modular_field_list" class="table table-bordered"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="formListId" name="formListId">
<div id='modal_form_modular_field' class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span
                        aria-hidden='true'>&times;</span></button>
                <h4 class='modal-title'>字段配置</h4></div>
            <form id='frm_field' class='form-horizontal'>
                <input type='hidden' id='fieldId' name='id' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='x_content'>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>
                                            字段名称<span class="required">*</span>
                                        </label>
                                        <div class='col-sm-4 '>
                                            <select id="name" name="name" required class="form-control">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否Json字段</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisJson"
                                                       id="bisJson">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>
                                            字段显示名称<span class="required">*</span>
                                        </label>
                                        <div class='col-sm-4'>
                                            <input type="text" id='displayName' name='displayName' required
                                                   class='form-control'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段类型<span class="required">*</span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select id="fieldType" name="fieldType" required class="form-control">
                                                <option value="">-请选择-</option>
                                                <c:if test="${not empty fieldTypeList}">
                                                    <c:forEach var="item" items="${fieldTypeList}">
                                                        <option value="${item.id}">${item.cnName}</option>
                                                    </c:forEach>
                                                </c:if>
                                                <option value="100">其它</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            Json字段名称
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" id='jsonName' name='jsonName'
                                                   class='form-control'>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            自定义view地址
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" id='customUrl' name='customUrl'
                                                   class='form-control'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            自定义view显示地址
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" id='customDisplayUrl' name='customDisplayUrl'
                                                   class='form-control'>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>
                                            字段长度
                                        </label>
                                        <div class='col-sm-4'>
                                            <input type="text" id='fieldLength' name='fieldLength' class='form-control'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            默认值
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" id='defaultValue' name='defaultValue'
                                                   class='form-control'>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            数据源sql
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea id="dataSourceSql" name="dataSourceSql" placeholder="select id,text from table"
                                                      class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            显示数据sql
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea id="dataViewSql" name="dataViewSql" placeholder="只返回单个值"
                                                      class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否缓存数据源</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisCacheDataSource"
                                                       id="bisCacheDataSource">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否缓存显示数据</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisCacheDataView"
                                                       id="bisCacheDataView">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-digits="true" id='sorting' name='sorting'
                                                   class='form-control'>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否必填</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisRequired"
                                                       id="bisRequired">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否显示</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisShow"
                                                       id="bisShow">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" id="multipleConfig" style="display: none;">
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否列表显示</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisListShow"
                                                       id="bisListShow">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否查询</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisQuery"
                                                       id="bisQuery">
                                                是
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid"><label class="col-sm-2 control-label">是否启用</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" class="grey" value="true" name="bisEnable"
                                                       id="bisEnable">
                                                是
                                            </label>
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
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveFormModularField();"
                        id='btn_save_field'>保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var currFormList = undefined;
    $(function () {
        loadFormList();
    })


    //加载列表数据
    function loadFormList() {
        var cols = [];
        cols.push({field: 'cnName', title: '显示名称'});
        cols.push({field: 'name', title: 'Assist名称'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        $("#tb_form_list").bootstrapTable('destroy');
        TableInit("tb_form_list", "${pageContext.request.contextPath}/formConfigure/getFormList", cols, {}, {
            showColumns: false,
            onClickRow: function (row) {
                $("#panel_form_list").show();
                loadFormModularList(row.name);
            }
        });
    }

    //加载列表数据
    function loadFormModularList(formName) {
        var cols = [];
        cols.push({field: 'cnName', title: '名称'});
        cols.push({field: 'name', title: '表单key'});
        cols.push({field: 'tableName', title: '表名'});
        cols.push({field: 'foreignKeyName', title: '外键字段'});
        cols.push({field: 'customUrl', title: '自定义路径'});
        cols.push({field: 'customeDisplayUrl', title: '自定义显示路径'});
        cols.push({
            field: 'bisConfigure', title: '是否为配置', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisMultiple', title: '是否为重复表', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisCard', title: '是否Card显示', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        $("#tb_form_modular_list").bootstrapTable('destroy');
        TableInit("tb_form_modular_list", "${pageContext.request.contextPath}/formConfigure/getFormModularList", cols, {
            formName: formName
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onClickRow: function (row) {
                $("#panel_form_list_field").show();
                $("#formListId").val(row.id);
                getFieldList(row.tableName);
                loadFormModularFieldList(row.id);
                if(row.bisMultiple){
                    $("#multipleConfig").show();
                }else{
                    $("#multipleConfig").hide();
                }
            }
        });
    }


    //加载列表数据
    function loadFormModularFieldList(formListId) {
        var cols = [];
        cols.push({
            field: 'name', title: '名称', formatter: function (value, row, index) {
                if (row.bisJson) {
                    return row.jsonName + "【" + value + "】";
                } else {
                    return value;
                }
            }
        });
        cols.push({field: 'displayName', title: '显示名称'});
        cols.push({field: 'fieldLength', title: '最大长度'});
        cols.push({field: 'defaultValue', title: '默认值'});
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
            field: 'bisListShow', title: '列表显示', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisQuery', title: '查询', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisEnable', title: '启用', formatter: function (value, row, index) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:editFormModularField(' + index + ');" ><i class="fa fa-edit">编辑</i></a>';
                if (row.bisJson) {
                    str += '<a class="btn btn-xs btn-warning" href="javascript:delFormModularField(' + row.id + ')"><i class="fa fa-trash-o"></i>删除</a>';
                }
                str += '</div>';
                return str;
            }
        });
        $("#tb_form_modular_field_list").bootstrapTable('destroy');
        TableInit("tb_form_modular_field_list", "${pageContext.request.contextPath}/formConfigure/getFormModularFieldList", cols, {
            formListId: formListId
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }


    //新增字段
    function addFormModularField() {
        //$("#frm_field").clearAll();
        $("#fieldId").val("0");
    }

    //编辑字段
    function editFormModularField(index) {
        var row = $("#tb_form_modular_field_list").bootstrapTable('getData')[index];
        $("#frm_field").clearAll();
        $("#frm_field").initForm(row);
        getFieldList(row.tableName, row.name);
        $('#modal_form_modular_field').modal();
    }


    //删除字段
    function delFormModularField(id) {
        bootbox.confirm("确认要删除么？", function (result) {
            if (result) {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/formConfigure/deleteFormListField",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            $('#tb_form_modular_field_list').bootstrapTable("refresh");
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
        });
    }

    //保存字段信息
    function saveFormModularField() {
        if ($("#frm_field").valid()) {
            Loading.progressShow();
            var data = formParams("frm_field");
            data.tableName = $("#tableName").val();
            data.formListId = $("#formListId").val();
            data.bisJson = $("#bisJson").prop("checked");
            data.bisCacheDataSource = $("#bisCacheDataSource").prop("checked");
            data.bisRequired = $("#bisRequired").prop("checked");
            data.bisShow = $("#bisShow").prop("checked");
            data.bisListShow = $("#bisListShow").prop("checked");
            data.bisQuery = $("#bisQuery").prop("checked");
            data.bisEnable = $("#bisEnable").prop("checked");
            $.ajax({
                url: "${pageContext.request.contextPath}/formConfigure/saveFormListField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#tb_form_modular_field_list').bootstrapTable("refresh");
                        $('#modal_form_modular_field').modal('hide');
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

    //获取字段信息列表
    function getFieldList(tableName, value) {
        if (tableName) {
            $("#name").empty();
            $.ajax({
                url: "${pageContext.request.contextPath}/formConfigure/getFieldList",
                type: "post",
                dataType: "json",
                data: {tableName: tableName},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        if (result.data) {
                            var html = "";
                            $.each(result.data, function (i, item) {
                                html += '<option value="' + item.key + '">' + item.key + '</option>';
                            })
                            $("#name").append(html);
                            if (value) {
                                $("#name").val(value);
                            }
                        }
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

</script>

</body>
</html>


