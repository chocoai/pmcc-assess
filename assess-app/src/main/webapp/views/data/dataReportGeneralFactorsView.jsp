<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <style>
        .product-buyer-name {
            overflow: hidden;/*自动隐藏文字*/
            text-overflow: ellipsis;/*文字隐藏后添加省略号*/
            display: -webkit-box;
            -webkit-line-clamp:6; /*想要显示的行数*/
            -webkit-box-orient: vertical;
        }
    </style>
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
                                        <label  class="col-md-1 col-form-label">类型</label>
                                        <div class="col-md-2 p-0">
                                            <select name="type"
                                                    class="form-control input-full search-select select2"
                                                    required="required">
                                            </select>
                                        </div>

                                        <div class="col-md-3 p-0">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="objFactors.loadBootstrapTable()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>

                                            <button style="margin-left: 10px" class="btn btn-success btn-sm"
                                                    type="button" href="#modelDataReportGeneralFactors"
                                                    onclick="objFactors.initDataReportGeneralFactorsForm({id:0,pid:0,bisEnable:true,setting:true});">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增
                                            </button>
                                        </div>


                                        <div class="col-md-3 p-0">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="objFactors.resetSearch()">
                                                <span class="fa fa-eraser" aria-hidden="true" class="-space"></span>
                                                重置
                                            </button>

                                            <button style="margin-left: 10px" class="btn btn-primary  btn-sm"
                                                    type="button"
                                                    onclick="objFactors.editDataReportGeneralFactors();">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                编辑
                                            </button>
                                            <button style="margin-left: 10px" class="btn btn-warning  btn-sm"
                                                    type="button"
                                                    onclick="objFactors.delDataReportGeneralFactors();">
                                                <span class="btn-label" aria-hidden="true"><i
                                                        class="fa fa-minus"></i></span>
                                                删除
                                            </button>
                                        </div>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tableDataReportGeneralFactors">
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
    var ue = UE.getEditor('description');
    var template = UE.getEditor('template');
    var objFactors = {};

    objFactors.frmQuery = $("#frmQuery");

    objFactors.table = $("#tableDataReportGeneralFactors");
    objFactors.tableSub = '#tableDataReportGeneralFactorsSub';

    objFactors.modelId = "#modelDataReportGeneralFactors";
    objFactors.subModelId = "#modelDataReportGeneralFactorsSub";
    objFactors.subModelIdDiv = "#modelDataReportGeneralFactorsSubDiv";

    objFactors.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
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
    };

    objFactors.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
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
    objFactors.loadBootstrapTable = function (option) {
        var select = objFactors.getQueryData();
        if (option) {
            $.extend(select,option) ;
        }
        var cols = [];
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="objFactors.setSubDataDic(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                return str;
            }, width: "10%"
        });
        var url = "${pageContext.request.contextPath}/dataReportGeneralFactors/getBootstrapTableVo";
        objFactors.common.loadTable(objFactors.table.attr("id"), null, select, cols, url);
    };

    /*公共方法*/
    objFactors.common = {
        loadTable: function (id, toolbar, select, array, url) {
            var method = {};
            if (toolbar) {
                method.toolbar = toolbar;
            }
            method.search = false;
            method.onLoadSuccess = function () {
                $('.tooltips').tooltip();
            };
            var cols = [];
            cols.push({checkbox: true, width: "5%"});
            cols.push({field: 'name', title: '名称', width: "10%"});
            cols.push({field: 'typeName', title: '类型', width: "5%"});
            // cols.push({field: 'fieldName', title: '字段', width: "10%"});
            cols.push({
                field: 'bisEnable', title: '启用与否', width: "5%", formatter: function (value, row, index) {
                    if (value) {
                        return "启用";
                    }
                    return "不启用";
                }
            });
            cols.push({
                field: 'provinceName', title: '区域',width: "15%", formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName)
                }
            });
            //处理很多内容 省略
            cols.push({
                field: 'template', title: '模板(超过6行会省略显示)',  width: '40%',formatter: function (value, row, index) {
                   var html = "<div class='product-buyer-name'>" +value +"</div>" ;
                   return html;
                }
            });
            cols.push({field: 'sorting', title: '顺序', width: "5%"});
            if (array) {
                $.each(array, function (i, col) {
                    cols.push(col);
                });
            }
            $("#" + id).bootstrapTable('destroy');
            TableInit(id, url, cols, select, method);
        },
        save: function (data, callback) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataReportGeneralFactors/saveDataReportGeneralFactors",
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
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        del: function (idArray, callback) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataReportGeneralFactors/deleteDataReportGeneralFactorsById/" + idArray.join(","),
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
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            });
        },
        initForm: function (data, frm, arr) {
            template.setContent('', false);
            ue.setContent('', false);
            frm.clearAll();
            frm.validate();
            frm.initForm(data);
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name=province]"),
                cityTarget: frm.find("select[name=city]"),
                districtTarget: frm.find("select[name=district]"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_GENERAL_FACTORS, data.type, function (html, data) {
                frm.find("select[name=type]").empty().html(html).trigger('change');
            });
            AssessCommon.initArraySelect2(frm, data, ['type', 'province', 'city', 'district']);
            if (arr) {
                $.each(arr, function (i, n) {
                    objFactors.showFile(n, 'tb_data_assets_appraisal_dic', data.id, true, n);
                    objFactors.fileUpload(n, 'tb_data_assets_appraisal_dic', data.id, true, n);

                })
            }
        }
    };

    /*重置*/
    objFactors.resetSearch = function () {
        // objFactors.frmQuery.find("input[name]").val('');
        // objFactors.frmQuery.find("input[fieldName]").val('');
        var frm = $(objFactors.frmQuery.selector) ;
        frm.clearAll();
    };

    /*获取查询数据*/
    objFactors.getQueryData = function () {
        var frm = $(objFactors.frmQuery.selector) ;
        var data = formSerializeArray(frm);
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        return data;
    };

    /*赋值*/
    objFactors.initDataReportGeneralFactorsForm = function (data) {
        var frm = $(objFactors.modelId).find("form");
        objFactors.common.initForm(data, frm, ['fileIdDataReportGeneralFactors']);
        $(objFactors.modelId).modal("show")
    };

    /*编辑*/
    objFactors.editDataReportGeneralFactors = function () {
        var rows = objFactors.table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "请选择要编辑的数据");
        } else {
            $(objFactors.modelId).modal("show");
            var data = rows[0];
            objFactors.initDataReportGeneralFactorsForm(data);
            var value = data.template;
            setTimeout(function () {
                ue.setContent(value, false);
            }, 500);
            objFactors.table.bootstrapTable('uncheckAll');
        }
    };

    /*删除*/
    objFactors.delDataReportGeneralFactors = function () {
        var rows = objFactors.table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            objFactors.common.del(idArray, function () {
                objFactors.table.bootstrapTable('uncheckAll');
                objFactors.table.bootstrapTable('refresh');
            });
        }
    };

    /*保存*/
    objFactors.saveDataReportGeneralFactors = function (_this) {
        var frm = $(objFactors.modelId).find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.template = ue.getContent();
        data.pid = 0;
        if (!data.bisEnable) {
            data.bisEnable = false;
        }
        objFactors.common.save(data, function () {
            $(objFactors.modelId).modal("hide");
            objFactors.loadBootstrapTable();
        });
    };

    /*设置子项数据*/
    objFactors.setSubDataDic = function (id) {
        var target = $(objFactors.subModelId);
        target.find("input[name='mainId']").val(id);
        objFactors.loadSubDataReportGeneralFactorsTable(id, function () {
            target.modal("show");
        });
        objFactors.getLevelHtml(id, target.find("h4[name='titleContent']"));
    };

    /*显示子项添加*/
    objFactors.showSubAddModel = function () {
        var target = $(objFactors.subModelId);
        var pid = target.find("input[name='mainId']").val();
        var data = objFactors.table.bootstrapTable("getRowByUniqueId", pid);
        objFactors.initDataReportGeneralFactorsSubForm({
            pid: pid,
            bisEnable: true,
            id: 0
        });
    };

    /*子项赋值*/
    objFactors.initDataReportGeneralFactorsSubForm = function (data) {
        var targetDiv = $(objFactors.subModelIdDiv);
        var frm = targetDiv.find("form");
        objFactors.common.initForm(data, frm, ['fileIdDataReportGeneralFactorsSub']);
//        frm.find("select[name='type']").attr({disabled: 'disabled'});
    };

    /*子项保存 */
    objFactors.saveDataReportGeneralFactorsSub = function () {
        var targetDiv = $(objFactors.subModelIdDiv);
        var frm = targetDiv.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.template = template.getContent();
        if (!data.bisEnable) {
            data.bisEnable = false;
        }
        objFactors.common.save(data, function () {
            targetDiv.modal("hide");
            objFactors.loadSubDataReportGeneralFactorsTable(data.pid);
        });
    };

    /*子项删除 */
    objFactors.removeDataReportGeneralFactorsSub = function () {
        var rows = $(objFactors.tableSub).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            objFactors.common.del(idArray, function () {
                $(objFactors.tableSub).bootstrapTable('uncheckAll');
                $(objFactors.tableSub).bootstrapTable('refresh');
            });
        }
    };

    /*设置访问路径和show model */
    objFactors.editSubDataDic = function (id) {
        var targetDiv = $(objFactors.subModelIdDiv);
        var data = $(objFactors.tableSub).bootstrapTable("getRowByUniqueId", id);
        objFactors.initDataReportGeneralFactorsSubForm(data);
        targetDiv.modal("show");
        var value = data.template;
        setTimeout(function () {
            template.setContent(value, false);
        }, 400);
        // objFactors.getLevelHtml(id, targetDiv.find("h4[name='titleContent']"));
    };

    var strLevelHtml = "";
    objFactors.getLevelHtml = function (id, target) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataReportGeneralFactors/getDataReportGeneralFactorsLevel",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    strLevelHtml = "";
                    if (result.data) {
                        if (result.data.keyValueDto) {
                            objFactors.getDataDicLevelRecursion(result.data.keyValueDto);
                        }
                        strLevelHtml += '<a href="javascript:objFactors.setSubDataDic(' + result.data.key + ')">' + result.data.value + '</a>' + ">";
                        target.html(strLevelHtml.replace(/>$/, ""));
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    objFactors.getDataDicLevelRecursion = function (keyValueDto) {
        if (keyValueDto) {
            objFactors.getDataDicLevelRecursion(keyValueDto.keyValueDto);
            strLevelHtml += '<a href="javascript:objFactors.setSubDataDic(' + keyValueDto.key + ')">' + keyValueDto.value + '</a>' + ">";
        }
    };

    /*/加载节点角色数据*/
    objFactors.loadSubDataReportGeneralFactorsTable = function (pid, callback) {
        var select = {pid: pid};
        var cols = [];
        cols.push({
            field: 'id', title: '操作', width: '20%', formatter: function (value, row, index) {
                var str = '<button onclick="objFactors.setSubDataDic(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="javascript:objFactors.editSubDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                return str;
            }
        });
        var url = "${pageContext.request.contextPath}/dataReportGeneralFactors/getBootstrapTableVoByPid";
        objFactors.common.loadTable('tableDataReportGeneralFactorsSub', "#toolbarSub", select, cols, url);
        if (callback) {
            callback();
        }
    };

    $(document).ready(function () {
        objFactors.loadBootstrapTable({pid:0});

        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_GENERAL_FACTORS, null, function (html, data) {
            var frm = $(objFactors.frmQuery.selector) ;
            frm.find("select[name=type]").empty().html(html).trigger('change');
        });
    });


</script>
<div id="modelDataReportGeneralFactors" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">一般因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
                    <div class="row">
                        <div class="col-md-12">
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
                                <%--<div class="col-md-6">--%>
                                    <%--<div class="form-inline x-valid">--%>
                                        <%--<label class="col-sm-2 control-label">--%>
                                            <%--字段--%>
                                        <%--</label>--%>
                                        <%--<div class="col-sm-10">--%>
                                            <%--<input type="text" class="form-control input-full " name="fieldName"--%>
                                                   <%--placeholder="字段">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            省<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="province"
                                                    class="form-control input-full search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            市<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="city"
                                                    class="form-control input-full search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            县
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="district"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <div class="col-sm-12">
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
                                            类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="type"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            标题顺序
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="排序" name="sorting" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            模板
                                        </label>
                                        <div class="col-sm-10">
                                            <div style="width:99%;height:200px;" id="description"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="fileIdDataReportGeneralFactors"
                                                   name="fileIdDataReportGeneralFactors"
                                                   placeholder="上传附件" class="form-control input-full "
                                                   type="file">
                                            <div id="_fileIdDataReportGeneralFactors"></div>
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="objFactors.saveDataReportGeneralFactors()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<!-- 子项 table list -->
<div id="modelDataReportGeneralFactorsSub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" name="titleContent">子项数据</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <input type="hidden" name="mainId">
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <p id="toolbar_bookmark">
                            <button type="button" data-toggle="modal" href="#modelDataReportGeneralFactorsSubDiv"
                                    class="btn btn-success btn-sm"
                                    onclick="objFactors.showSubAddModel(this);">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增 
                            </button>

                            <button type="button" class="btn btn-warning btn-sm"
                                    onclick="objFactors.removeDataReportGeneralFactorsSub();">
                                <span class="btn-label" aria-hidden="true"><i class="fa fa-minus"></i></span>删除
                            </button>
                        </p>
                        <table class="table table-bordered" id="tableDataReportGeneralFactorsSub">
                        </table>
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


<div id="modelDataReportGeneralFactorsSubDiv" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" name="titleContent">一般因素 子项</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
                    <div class="row">
                        <div class="col-md-12">
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
                                <%--<div class="col-md-6">--%>
                                    <%--<div class="form-inline x-valid">--%>
                                        <%--<label class="col-sm-2 control-label">--%>
                                            <%--字段--%>
                                        <%--</label>--%>
                                        <%--<div class="col-sm-10">--%>
                                            <%--<input type="text" class="form-control input-full " name="fieldName"--%>
                                                   <%--placeholder="字段">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            省
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="province"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            市
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="city"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            县
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="district"
                                                    class="form-control input-full search-select select2">
                                            </select>
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
                                            类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="type"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            标题顺序
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="排序" name="sorting" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            模板
                                        </label>
                                        <div class="col-sm-10">
                                            <div style="width:99%;height:200px;" id="template"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="fileIdDataReportGeneralFactorsSub"
                                                   name="fileIdDataReportGeneralFactorsSub"
                                                   placeholder="上传附件" class="form-control input-full "
                                                   type="file">
                                            <div id="_fileIdDataReportGeneralFactorsSub"></div>
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="objFactors.saveDataReportGeneralFactorsSub()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>

