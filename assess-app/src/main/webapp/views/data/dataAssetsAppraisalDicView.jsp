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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <div id="toolbar">
                        <div class="container-fluid">
                            <div class="pull-left">
                                <button type="button" data-toggle="modal" href="#modelDataAssetsAppraisalDic"
                                        class="btn btn-default"
                                        onclick="dataAssetsAppraisalDic.initDataAssetsAppraisalDicForm({id:0});">
                                                   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增 
                                           
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="dataAssetsAppraisalDic.editDataAssetsAppraisalDic();">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="dataAssetsAppraisalDic.delDataAssetsAppraisalDic();">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <div class="pull-right" id="frmQuery" style="padding-bottom:10px;">
                                <input name="name" type="text" placeholder="名称" class="form-control"
                                       style="float:left;width:150px;margin-right:5px;">
                                <input name="fieldName" type="text" placeholder="字段key" class="form-control"
                                       style="float:left;width:150px;margin-right:5px;">
                                <button onclick="dataAssetsAppraisalDic.loadBootstrapTable();" type="button"
                                        class="btn btn-primary btn-space">
                                    搜索<span class="fa fa-search" aria-hidden="true" class="btn-icon-space"></span>
                                </button>
                                <button onclick="dataAssetsAppraisalDic.resetSearch();" type="button"
                                        class="btn btn-default btn-space">
                                    重置<span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered" id="tableDataAssetsAppraisalDic">
                        <!-- cerare document add ajax data-->
                    </table>

                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">

    var dataAssetsAppraisalDic = {};

    dataAssetsAppraisalDic.frmQuery = $("#frmQuery");

    dataAssetsAppraisalDic.table = $("#tableDataAssetsAppraisalDic");

    dataAssetsAppraisalDic.modelId = "#modelDataAssetsAppraisalDic";

    dataAssetsAppraisalDic.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName
                // projectId: id
            },
            deleteFlag: deleteFlag
        });
        // FileUtils.uploadFiles({
        //     target: target,
        //     disabledTarget: "btn_submit",
        //     onUpload: function (file) {
        //         var formData = {
        //             fieldsName: target,
        //             tableName: tableName,
        //             tableId: id
        //         };
        //         return formData;
        //     }, onUploadComplete: function (result, file) {
        //
        //     },
        //     deleteFlag: true
        // });
    };

    dataAssetsAppraisalDic.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName
                // projectId: id
            },
            deleteFlag: deleteFlag
        })
    };

    /*列表*/
    dataAssetsAppraisalDic.loadBootstrapTable = function () {
        var method = {};
        method.toolbar = "#toolbar";
        method.search = false;
        var select = dataAssetsAppraisalDic.getQueryData();
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '名称', width: "15%"});
        cols.push({field: 'fieldName', title: '字段', width: "15%"});
        cols.push({
            field: 'bisEnable', title: '启用与否',width: "15%", formatter: function (value, row, index) {
                if(value){
                    return "启用" ;
                }
                return "不启用" ;
            }
        });
        cols.push({
            field: 'setting', title: '固定字段与否',width: "15%", formatter: function (value, row, index) {
                if(value){
                    return "是" ;
                }
                return "否" ;
            }
        });
        cols.push({field: 'fileViewName', title: '可编辑固定附件', width: "20%"});
        dataAssetsAppraisalDic.table.bootstrapTable('destroy');
        TableInit(dataAssetsAppraisalDic.table.attr("id"), "${pageContext.request.contextPath}/dataAssetsAppraisalDic/getBootstrapTableVo", cols, select, method);
    };

    /*重置*/
    dataAssetsAppraisalDic.resetSearch = function () {
        dataAssetsAppraisalDic.frmQuery.find("input[name]").val('');
        dataAssetsAppraisalDic.frmQuery.find("input[fieldName]").val('');
    };

    dataAssetsAppraisalDic.getQueryData = function () {
        var data = {};
        var name = dataAssetsAppraisalDic.frmQuery.find("input[name]").val();
        var fieldName = dataAssetsAppraisalDic.frmQuery.find("input[fieldName]").val();
        if (name) {
            data.name = name;
        }
        if (fieldName) {
            data.fieldName = fieldName;
        }
        return data;
    };

    /*赋值*/
    dataAssetsAppraisalDic.initDataAssetsAppraisalDicForm = function (data) {
        var frm = $(dataAssetsAppraisalDic.modelId).find("form");
        frm.clearAll();
        frm.initForm(data);
        var retHtml = "<option value=''>-请选择-</option>";
        $.each(${dataAssetsAppraisalTypeEnum}, function (i, item) {
            retHtml += '<option key="' + item.key + '" title="' + item.key + '" value="' + item.key + '"';
            if (item.key == data.type) {
                retHtml += 'selected="selected"'
            }
            retHtml += '>' + item.value + '</option>';
        });
        frm.find("select[name='type']").empty().append(retHtml);
        $.each(['fileIdDataAssetsAppraisalDic'] , function (i,n) {
            dataAssetsAppraisalDic.showFile(n,'tb_data_assets_appraisal_dic',data.id,false,n);
            dataAssetsAppraisalDic.fileUpload(n,'tb_data_assets_appraisal_dic',data.id,false,n);

        })
    };

    dataAssetsAppraisalDic.editDataAssetsAppraisalDic = function () {
        var rows = dataAssetsAppraisalDic.table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要编辑的数据");
        } else {
            $(dataAssetsAppraisalDic.modelId).modal("show");
            dataAssetsAppraisalDic.initDataAssetsAppraisalDicForm(rows[0]) ;
            dataAssetsAppraisalDic.table.bootstrapTable('uncheckAll');
        }
    };

    dataAssetsAppraisalDic.delDataAssetsAppraisalDic = function () {
        var rows = dataAssetsAppraisalDic.table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            Alert("确认要删除么？", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataAssetsAppraisalDic/deleteDataAssetsAppraisalDicById/"+idArray.join(","),
                    type: "post",
                    data: {_method: "DELETE"},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            dataAssetsAppraisalDic.table.bootstrapTable('uncheckAll');
                            dataAssetsAppraisalDic.table.bootstrapTable('refresh');
                            toastr.success('删除成功');
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        }
    };

    dataAssetsAppraisalDic.saveDataAssetsAppraisalDic = function (_this) {
        var frm = $(dataAssetsAppraisalDic.modelId).find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        $.ajax({
            url: "${pageContext.request.contextPath}/dataAssetsAppraisalDic/saveDataAssetsAppraisalDic",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    $(dataAssetsAppraisalDic.modelId).modal("hide");
                    dataAssetsAppraisalDic.loadBootstrapTable();
                }else {
                    toastr.success('失败'+result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    $(document).ready(function () {
        dataAssetsAppraisalDic.loadBootstrapTable();
    });


</script>
<div id="modelDataAssetsAppraisalDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">资产评估 文件和字段配置</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            字段
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="fieldName"
                                                   placeholder="字段">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            固定字段
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="checkbox" name="setting"
                                                   value="true">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            是否启用
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="checkbox" name="bisEnable"
                                                   value="true">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 ">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            排序<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" required="required" class="form-control" name="sorting"
                                                   placeholder="排序" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="fileIdDataAssetsAppraisalDic" name="fileIdDataAssetsAppraisalDic" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_fileIdDataAssetsAppraisalDic"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="dataAssetsAppraisalDic.saveDataAssetsAppraisalDic();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

</html>

