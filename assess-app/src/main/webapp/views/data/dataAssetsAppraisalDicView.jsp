<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
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

                                        <label for="name" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="name" name="name"
                                                   class="form-control input-full  input-full">
                                        </div>
                                        <label for="fieldName" class="col-md-1 col-form-label">字段key</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="字段key" id="fieldName" name="fieldName"
                                                   class="form-control input-full  input-full">
                                        </div>
                                        <p id="toolbar">
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataAssetsAppraisalDic.loadBootstrapTable()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>

                                        <button style="margin-left: 10px" class="btn btn-success btn-sm" type="button" href="#modelDataAssetsAppraisalDic"
                                                onclick="dataAssetsAppraisalDic.initDataAssetsAppraisalDicForm({id:0,pid:0,bisEnable:true,setting:true});">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataAssetsAppraisalDic.resetSearch()">
                                            <span class="fa fa-eraser" aria-hidden="true" class="-space"></span>
                                            重置
                                        </button>
                                        <button style="margin-left: 10px" class="btn btn-default  btn-sm" type="button"
                                                onclick="dataAssetsAppraisalDic.editDataAssetsAppraisalDic();">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                            编辑
                                        </button>
                                        <button style="margin-left: 10px" class="btn btn-warning  btn-sm" type="button"
                                                onclick="dataAssetsAppraisalDic.delDataAssetsAppraisalDic();">
                                            <span class="btn-label" aria-hidden="true"><i class="fa fa-minus"></i></span>
                                            删除
                                        </button>
                                        </p>

                                    </div>


                                </form>
                                <table class="table table-bordered" id="tableDataAssetsAppraisalDic">
                                    <!-- cerare document add ajax data-->
                                </table>
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


<script type="text/javascript">

    var dataAssetsAppraisalDic = {};

    dataAssetsAppraisalDic.frmQuery = $("#frmQuery");

    dataAssetsAppraisalDic.table = $("#tableDataAssetsAppraisalDic");
    dataAssetsAppraisalDic.tableSub = '#tableDataAssetsAppraisalDicSub';

    dataAssetsAppraisalDic.modelId = "#modelDataAssetsAppraisalDic";
    dataAssetsAppraisalDic.subModelId = "#modelDataAssetsAppraisalDicSub";
    dataAssetsAppraisalDic.subModelIdDiv = "#modelDataAssetsAppraisalDicSubDiv";

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
        var select = dataAssetsAppraisalDic.getQueryData();
        var arr = Object.keys(select);
        if (arr.length == 0) {
            select.pid = 0;
        }
        var cols = [];
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:dataAssetsAppraisalDic.setSubDataDic(' + row.id + ');" >查看子项</i></a>';
                str += '</div>';
                return str;
            }, width: "10%"
        });
        var url = "${pageContext.request.contextPath}/dataAssetsAppraisalDic/getBootstrapTableVo";
        dataAssetsAppraisalDic.common.loadTable(dataAssetsAppraisalDic.table.attr("id"), "#toolbar", select, cols, url);
    };

    /*公共方法*/
    dataAssetsAppraisalDic.common = {
        loadTable: function (id, toolbar, select, array, url) {
            var method = {};
            if (toolbar) {
                method.toolbar = toolbar;
            }
            method.search = false;
            var cols = [];
            cols.push({checkbox: true, width: "5%"});
            cols.push({field: 'name', title: '名称', width: "15%"});
            cols.push({field: 'fieldName', title: '字段', width: "15%"});
            cols.push({
                field: 'bisEnable', title: '启用与否', width: "15%", formatter: function (value, row, index) {
                    if (value) {
                        return "启用";
                    }
                    return "不启用";
                }
            });
            cols.push({
                field: 'setting', title: '固定字段与否', width: "15%", formatter: function (value, row, index) {
                    if (value) {
                        return "是";
                    }
                    return "否";
                }
            });
            cols.push({field: 'fileViewName', title: '可编辑固定附件', width: "20%"});
            if (array) {
                $.each(array, function (i, col) {
                    cols.push(col);
                });
            }
            $("#" + id).bootstrapTable('destroy');
            TableInit(id, url, cols, select, method);
        },
        save: function (data, callback) {
            data.planDetailsId = 0;
            data.projectId = 0;
            $.ajax({
                url: "${pageContext.request.contextPath}/dataAssetsAppraisalDic/saveDataAssetsAppraisalDic",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        if (callback) {
                            callback();
                        }
                    } else {
                        AlertError("保存失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        del: function (idArray, callback) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataAssetsAppraisalDic/deleteDataAssetsAppraisalDicById/" + idArray.join(","),
                    type: "post",
                    data: {_method: "DELETE"},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            if (callback) {
                                callback();
                            }
                            notifySuccess("成功", "删除数据成功");
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        initForm: function (data, frm, arr) {
            frm.clearAll();
            frm.initForm(data);
            var retHtml = "";
            retHtml = "<option value=''>-请选择-</option>";
            $.each(${dataAssetsAppraisalTypeEnum}, function (i, item) {
                retHtml += '<option key="' + item.key + '" title="' + item.key + '" value="' + item.key + '"';
                if (item.key == data.type) {
                    retHtml += 'selected="selected"'
                }
                retHtml += '>' + item.value + '</option>';
            });
            frm.find("select[name='type']").empty().append(retHtml);
            if (data.type) {
                frm.find("select[name='type']").val(data.type).trigger('change');
            }
            if (arr) {
                $.each(arr, function (i, n) {
                    dataAssetsAppraisalDic.showFile(n, 'tb_data_assets_appraisal_dic', data.id, true, n);
                    dataAssetsAppraisalDic.fileUpload(n, 'tb_data_assets_appraisal_dic', data.id, true, n);

                })
            }
        }
    };

    /*重置*/
    dataAssetsAppraisalDic.resetSearch = function () {
        dataAssetsAppraisalDic.frmQuery.find("input[name]").val('');
        dataAssetsAppraisalDic.frmQuery.find("input[fieldName]").val('');
    };

    /*获取查询数据*/
    dataAssetsAppraisalDic.getQueryData = function () {
        var data = {};
        var name = dataAssetsAppraisalDic.frmQuery.find("input[name='name']").val();
        var fieldName = dataAssetsAppraisalDic.frmQuery.find("input[name='fieldName']").val();
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
        dataAssetsAppraisalDic.common.initForm(data, frm, ['fileIdDataAssetsAppraisalDic']);
        $(dataAssetsAppraisalDic.modelId).modal("show")
    };

    /*编辑*/
    dataAssetsAppraisalDic.editDataAssetsAppraisalDic = function () {
        var rows = dataAssetsAppraisalDic.table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要编辑的数据");
        } else {
            $(dataAssetsAppraisalDic.modelId).modal("show");
            dataAssetsAppraisalDic.initDataAssetsAppraisalDicForm(rows[0]);
            dataAssetsAppraisalDic.table.bootstrapTable('uncheckAll');
        }
    };

    /*删除*/
    dataAssetsAppraisalDic.delDataAssetsAppraisalDic = function () {
        var rows = dataAssetsAppraisalDic.table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            dataAssetsAppraisalDic.common.del(idArray, function () {
                dataAssetsAppraisalDic.table.bootstrapTable('uncheckAll');
                dataAssetsAppraisalDic.table.bootstrapTable('refresh');
            });
        }
    };

    /*保存*/
    dataAssetsAppraisalDic.saveDataAssetsAppraisalDic = function (_this) {
        var frm = $(dataAssetsAppraisalDic.modelId).find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        dataAssetsAppraisalDic.common.save(data, function () {
            $(dataAssetsAppraisalDic.modelId).modal("hide");
            dataAssetsAppraisalDic.loadBootstrapTable();
        });
    };

    /*设置子项数据*/
    dataAssetsAppraisalDic.setSubDataDic = function (id) {
        var target = $(dataAssetsAppraisalDic.subModelId);
        target.find("input[name='mainId']").val(id);
        dataAssetsAppraisalDic.loadSubDataAssetsAppraisalDicTable(id, function () {
            target.modal("show");
        });
        dataAssetsAppraisalDic.getLevelHtml(id, target.find("h3[name='titleContent']"));
    };

    /*显示子项添加*/
    dataAssetsAppraisalDic.showSubAddModel = function () {
        var target = $(dataAssetsAppraisalDic.subModelId);
        var pid = target.find("input[name='mainId']").val();
        var data = dataAssetsAppraisalDic.table.bootstrapTable("getRowByUniqueId", pid);
        dataAssetsAppraisalDic.initDataAssetsAppraisalDicSubForm({
            pid: pid,
            type: data.type,
            bisEnable: true,
            setting: true,
            id:0
        });
    };

    /*子项赋值*/
    dataAssetsAppraisalDic.initDataAssetsAppraisalDicSubForm = function (data) {
        var targetDiv = $(dataAssetsAppraisalDic.subModelIdDiv);
        var frm = targetDiv.find("form");
        dataAssetsAppraisalDic.common.initForm(data, frm, ['fileIdDataAssetsAppraisalDicSub']);
//        frm.find("select[name='type']").attr({disabled: 'disabled'});
    };

    /*子项保存 */
    dataAssetsAppraisalDic.saveDataAssetsAppraisalDicSub = function () {
        var targetDiv = $(dataAssetsAppraisalDic.subModelIdDiv);
        var frm = targetDiv.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        dataAssetsAppraisalDic.common.save(data, function () {
            targetDiv.modal("hide");
            dataAssetsAppraisalDic.loadSubDataAssetsAppraisalDicTable(data.pid);
        });
    };

    /*子项删除 */
    dataAssetsAppraisalDic.removeDataAssetsAppraisalDicSub = function () {
        var rows = $(dataAssetsAppraisalDic.tableSub).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            dataAssetsAppraisalDic.common.del(idArray, function () {
                $(dataAssetsAppraisalDic.tableSub).bootstrapTable('uncheckAll');
                $(dataAssetsAppraisalDic.tableSub).bootstrapTable('refresh');
            });
        }
    };

    /*设置访问路径和show model */
    dataAssetsAppraisalDic.editSubDataDic = function (id) {
        var targetDiv = $(dataAssetsAppraisalDic.subModelIdDiv);
        var data = $(dataAssetsAppraisalDic.tableSub).bootstrapTable("getRowByUniqueId", id);
        dataAssetsAppraisalDic.initDataAssetsAppraisalDicSubForm(data);
        targetDiv.modal("show");
    };

    var strLevelHtml = "";
    dataAssetsAppraisalDic.getLevelHtml = function (id, target) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataAssetsAppraisalDic/getDataAssetsAppraisalDicLevel",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    strLevelHtml = "";
                    if (result.data) {
                        if (result.data.keyValueDto) {
                            dataAssetsAppraisalDic.getDataDicLevelRecursion(result.data.keyValueDto);
                        }
                        strLevelHtml += '<a href="javascript:dataAssetsAppraisalDic.setSubDataDic(' + result.data.key + ')">' + result.data.value + '</a>' + ">";
                        target.html(strLevelHtml.replace(/>$/, ""));
                    }
                } else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    dataAssetsAppraisalDic.getDataDicLevelRecursion = function (keyValueDto) {
        if (keyValueDto) {
            dataAssetsAppraisalDic.getDataDicLevelRecursion(keyValueDto.keyValueDto);
            strLevelHtml += '<a href="javascript:dataAssetsAppraisalDic.setSubDataDic(' + keyValueDto.key + ')">' + keyValueDto.value + '</a>' + ">";
        }
    };

    /*/加载节点角色数据*/
    dataAssetsAppraisalDic.loadSubDataAssetsAppraisalDicTable = function (pid, callback) {
        var select = {pid: pid};
        var cols = [];
        cols.push({
            field: 'id', title: '操作', width: '20%', formatter: function (value, row, index) {
                var str = '<button onclick="dataAssetsAppraisalDic.setSubDataDic(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="javascript:dataAssetsAppraisalDic.editSubDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                return str;
            }
        });
        var url = "${pageContext.request.contextPath}/dataAssetsAppraisalDic/getBootstrapTableVoByPid";
        dataAssetsAppraisalDic.common.loadTable('tableDataAssetsAppraisalDicSub', "#toolbarSub", select, cols, url);
        if (callback) {
            callback();
        }
    };

    $(document).ready(function () {
        dataAssetsAppraisalDic.loadBootstrapTable();
    });


</script>
<div id="modelDataAssetsAppraisalDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">资产评估 文件和字段配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
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
                                                <input type="text" class="form-control input-full " name="name"
                                                       placeholder="名称" required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                字段<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full " name="fieldName"
                                                       placeholder="字段">
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
                                                        <input class="form-check-input" type="checkbox" id="setting"
                                                               name="setting" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">固定字段</span>
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
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="type"
                                                        class="form-control input-full  search-select select2 ">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                排序<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required="required" class="form-control input-full " name="sorting"
                                                       placeholder="排序" data-rule-number='true'>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="fileIdDataAssetsAppraisalDic" name="fileIdDataAssetsAppraisalDic"
                                                   placeholder="上传附件" class="form-control input-full "
                                                   type="file">
                                            <div id="_fileIdDataAssetsAppraisalDic"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataAssetsAppraisalDic.saveDataAssetsAppraisalDic()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<!-- 子项 table list -->
<div id="modelDataAssetsAppraisalDicSub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">子项数据</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <input type="hidden" name="mainId">
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <p id="toolbar_bookmark">
                                <button type="button" data-toggle="modal" href="#modelDataAssetsAppraisalDicSubDiv"
                                        class="btn btn-success btn-sm"
                                        onclick="dataAssetsAppraisalDic.showSubAddModel(this);">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增 
                                </button>

                                <button type="button" class="btn btn-warning btn-sm"
                                        onclick="dataAssetsAppraisalDic.removeDataAssetsAppraisalDicSub();">
                                    <span class="btn-label" aria-hidden="true"><i class="fa fa-minus"></i></span>删除
                                </button>
                            </p>
                            <table class="table table-bordered" id="tableDataAssetsAppraisalDicSub">
                            </table>

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


<div id="modelDataAssetsAppraisalDicSubDiv" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">资产评估 文件和字段配置 子项</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
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
                                                <input type="text" class="form-control input-full " name="name"
                                                       placeholder="名称" required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                字段<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full " name="fieldName"
                                                       placeholder="字段">
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
                                                        <input class="form-check-input" type="checkbox"
                                                               name="setting" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">固定字段</span>
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
                                                        <input class="form-check-input" type="checkbox"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="type"
                                                        class="form-control input-full  search-select select2 ">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                排序<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required="required" class="form-control input-full " name="sorting"
                                                       placeholder="排序" data-rule-number='true'>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="fileIdDataAssetsAppraisalDicSub" name="fileIdDataAssetsAppraisalDicSub"
                                                   placeholder="上传附件" class="form-control input-full "
                                                   type="file">
                                            <div id="_fileIdDataAssetsAppraisalDicSub"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataAssetsAppraisalDic.saveDataAssetsAppraisalDicSub()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>

