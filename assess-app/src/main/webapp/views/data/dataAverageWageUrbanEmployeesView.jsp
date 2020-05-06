<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
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
                                        <label class="col-md-1 col-form-label">省</label>
                                        <div class="col-md-2 p-0">
                                            <select name="province"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">市</label>
                                        <div class="col-md-2 p-0">
                                            <select name="city" class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">区县</label>
                                        <div class="col-md-2 p-0">
                                            <select name="district"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">年份</label>
                                        <div class="col-md-3 p-0">
                                            <input name="date"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="年份"/>
                                        </div>
                                        <div class="col-md-6">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="dataObj.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>
                                            <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                                    type="button"
                                                    onclick="dataObj.showModel()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增
                                            </button>
                                            <button type="button" class="btn btn-info btn-sm"
                                                    style="margin-left: 5px"
                                                    onclick="$('#importDataObjFileId').trigger('click')">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-upload-alt"></i>
                                                    </span>
                                            </button>
                                            <input type="file" style="display: none;" name="importDataObjFileId"
                                                   id="importDataObjFileId"
                                                   onchange="dataObj.importDataLand('importDataObjFileId');">
                                            <button type="button" class="btn btn-success btn-sm"
                                                    style="margin-left: 5px"
                                                    onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpDataAverageWageUrbanEmployeesTemplate);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-download-alt"></i>
                                                    </span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
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
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
</body>

<script type="text/javascript">
    $(function () {
        dataObj.loadDataDicList();
        (function (frm) {
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name=province]"),
                cityTarget: frm.find("select[name=city]"),
                districtTarget: frm.find("select[name=district]"),
                provinceValue: null,
                cityValue: null,
                districtValue: null
            })
        }($("#frmQuery")));
    });


    var dataObj = {};

    dataObj.masterId = 0;

    dataObj = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            data.itemBox = "divAverageWageUrbanEmployeesItemBox";
            data.itemBoxList = "divSubAverageWageUrbanEmployeesItem";
            data.itemList = "tbAverageWageUrbanEmployeesItemList";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', width: '15%', formatter: function (value, row, index) {
                    var areaFullName = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.townShipName) {
                        areaFullName = areaFullName + row.townShipName;
                    }
                    return areaFullName;
                }
            });
            cols.push({
                field: 'date',
                width: '5%',
                title: '年份',
                formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({field: 'stateOwnedEconomy', title: '国有经济', width: '5%'});
            cols.push({field: 'collectiveEconomy', title: '集体经济', width: '5%'});
            cols.push({field: 'privateEconomy', title: '私营经济', width: '5%'});
            cols.push({field: 'otherEconomy', title: '其他经济', width: '5%'});
            cols.push({field: 'total', title: '总计', width: '5%'});
            cols.push({
                field: 'id', title: '操作', width: '9%', formatter: function (value, row, index) {
                    var str = '<button type="button" onclick="dataObj.setSubDataDic(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';

                    str += '<button type="button" onclick="dataObj.editData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button type="button" onclick="dataObj.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            var data = formSerializeArray($("#frmQuery"));
            var keys = Object.keys(data);
            for (var i = 0; i < keys.length; i++) {
                var key = keys[i];
                if (!data[key]) {
                    data[key] = undefined;
                }
            }
            var table = $("#" + dataObj.config().table);
            table.bootstrapTable('destroy');
            TableInit(table, "${pageContext.request.contextPath}/dataAverageWageUrbanEmployees/getBootstrapTableVo", cols, {formData: JSON.stringify(data)}, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataAverageWageUrbanEmployees/deleteDataAverageWageUrbanEmployees",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            dataObj.loadDataDicList();
                        } else {
                            AlertError("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            var box = $('#' + dataObj.config().box);
            dataObj.initDataForm({}, ['date'], box.find("form"));
            box.modal("show");
        },
        saveData: function () {
            if (!$("#" + dataObj.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataObj.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataAverageWageUrbanEmployees/saveAndUpdateDataAverageWageUrbanEmployees",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data), updateNull: true},
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataObj.config().box).modal('hide');
                        $("#" + dataObj.config().table).bootstrapTable('refresh');
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        editData: function (id) {
            var frm = $("#" + dataObj.config().frm);
            var data = $("#" + dataObj.config().table).bootstrapTable('getRowByUniqueId', id);
            dataObj.initDataForm(data, ['date'], frm);
            $('#' + dataObj.config().box).modal("show");
        },
        initDataForm: function (data, inputArr, frm) {
            frm.clearAll();
            frm.initForm(data);
            frm.validate();
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name=province]"),
                cityTarget: frm.find("select[name=city]"),
                districtTarget: frm.find("select[name=district]"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
            if (inputArr) {
                $.each(inputArr, function (i, n) {
                    frm.find("input[name='" + n + "']").val(formatDate(data[n]));
                    frm.find("label[name='" + n + "']").html(formatDate(data[n]));
                });
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.dataAverageWageUrbanEmployeesItemCategory, null, function (html, data) {
                html = '';
                html += '<option value="" selected>-请选择-</option>';
                $.each(data, function (i, item) {
                    html += "<option value='" + item.name + "'>" + item.name + "</option>";
                });
                frm.find('#categoryDataList').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.dataAverageWageUrbanEmployeesItemType, null, function (html, data) {
                html = '';
                html += '<option value="" selected>-请选择-</option>';
                $.each(data, function (i, item) {
                    html += "<option value='" + item.name + "'>" + item.name + "</option>";
                });
                frm.find('#typeDataList').empty().html(html).trigger('change');
            });
        },
        handleTotal: function (_this) {
            var frm = $(_this).closest("form");
            var total = 0;
            var arr = [frm.find("[name=stateOwnedEconomy]").val(), frm.find("[name=collectiveEconomy]").val(), frm.find("[name=privateEconomy]").val(), frm.find("[name=otherEconomy]").val()];
            $.each(arr, function (i, item) {
                if ($.isNumeric(item)) {
                    total += Number(item);
                }
            });
            if (total != 0) {
                frm.find("[name=total]").val(total);
            }
        },
        importDataLand: function (fileElementId) {
            Loading.progressShow();
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/dataAverageWageUrbanEmployees/importDataAverageWageUrbanEmployees",
                data: {},//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: fileElementId,//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("导入情况", result.data);
                        $("#" + dataObj.config().table).bootstrapTable('refresh');
                    } else {
                        if (result.errmsg) {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                        } else {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                        }
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            });
        }
    };


    dataObj.setSubDataDic = function (masterId) {
        dataObj.masterId = masterId;
        var box = $('#' + dataObj.config().itemBoxList);
        box.modal("show");
        dataObj.initDataForm({}, [], box.find("form"));
        dataObj.subDataList();
    };

    dataObj.addSubDataDic = function () {
        var box = $('#' + dataObj.config().itemBox);
        dataObj.initDataForm({}, ['date'], box.find("form"));
        box.modal("show");
    };

    dataObj.delSubData = function(id){
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataAverageWageUrbanEmployeesItem/deleteDataAverageWageUrbanEmployeesItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        $("#" + dataObj.config().itemList).bootstrapTable('refresh');
                    } else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    } ;

    dataObj.editSubData = function(id){
        var box = $('#' + dataObj.config().itemBox);
        var frm = box.find("form") ;
        var data = $("#" + dataObj.config().itemList).bootstrapTable('getRowByUniqueId', id);
        dataObj.initDataForm(data, ['date'], frm);
        box.modal("show");
    } ;

    dataObj.subSaveData = function(){
        var box = $('#' + dataObj.config().itemBox);
        var frm = box.find("form") ;
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.masterId = dataObj.masterId;
        $.ajax({
            url: "${pageContext.request.contextPath}/dataAverageWageUrbanEmployeesItem/saveAndUpdateDataAverageWageUrbanEmployeesItem",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data), updateNull: true},
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    box.modal('hide');
                    $("#" + dataObj.config().itemList).bootstrapTable('refresh');
                } else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    } ;

    dataObj.subDataList = function () {
        var cols = [];
        cols.push({field: 'type', title: '类型', width: '5%'});
        cols.push({field: 'category', title: '类别', width: '5%'});
        cols.push({field: 'stateOwnedEconomy', title: '国有经济', width: '5%'});
        cols.push({field: 'collectiveEconomy', title: '集体经济', width: '5%'});
        cols.push({field: 'privateEconomy', title: '私营经济', width: '5%'});
        cols.push({field: 'otherEconomy', title: '其他经济', width: '5%'});
        cols.push({field: 'total', title: '总计', width: '5%'});
        cols.push({
            field: 'id', title: '操作', width: '9%', formatter: function (value, row, index) {
                var str = "";

                str += '<button type="button" onclick="dataObj.editSubData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="dataObj.delSubData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        var frm = $('#' + dataObj.config().itemBoxList).find("form");
        var data = formSerializeArray(frm);
        data.masterId = dataObj.masterId;
        var keys = Object.keys(data);
        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            if (!data[key]) {
                data[key] = undefined;
            }
        }
        var table = $("#" + dataObj.config().itemList);
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/dataAverageWageUrbanEmployeesItem/getBootstrapTableVo", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    dataObj.importSubDataItem = function (fileElementId) {
        Loading.progressShow();
        $.ajaxFileUpload({
            type: "POST",
            url: getContextPath() + "/dataAverageWageUrbanEmployeesItem/importDataAverageWageUrbanEmployeesItem",
            data: {masterId:dataObj.masterId},//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("导入情况", result.data);
                    $("#" + dataObj.config().itemList).bootstrapTable('refresh');
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    }

</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">${baseViewDto.currentMenu.name}</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                省<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-8">
                                                <select name="province"
                                                        class="form-control input-full search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                市<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-8">
                                                <select name="city"
                                                        class="form-control input-full search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                县
                                            </label>
                                            <div class="col-sm-8">
                                                <select name="district"
                                                        class="form-control input-full search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                年份
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="date"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd" placeholder="年份"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                国有经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="stateOwnedEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="国有经济"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                集体经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="collectiveEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="集体经济"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                私营经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="privateEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="私营经济"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                其他经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="otherEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="其他经济"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                总计
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="total" data-rule-number='true'
                                                       class="form-control input-full"
                                                       placeholder="总计"/>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObj.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<!--子项管理-->
<div id="divSubAverageWageUrbanEmployeesItem" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">${baseViewDto.currentMenu.name} 从表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <form class="form-horizontal">
                                <div class="row form-group">
                                    <div class="col-md-3">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                类型
                                            </label>
                                            <div class="col-sm-8">
                                                <input type="text"  name="type" placeholder="类型"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                类别
                                            </label>
                                            <div class="col-sm-8">
                                                <input type="text"  name="category" placeholder="类别"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-12">

                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                                        type="button"
                                                        onclick="dataObj.subDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                                <button style="margin-left: 10px" class="btn btn-success btn-sm"
                                                        type="button"
                                                        onclick="dataObj.addSubDataDic()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                    新增
                                                </button>

                                                <button type="button" class="btn btn-info btn-sm"
                                                        style="margin-left: 5px"
                                                        onclick="$('#importsubDataObjFileId').trigger('click')">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-upload-alt"></i>
                                                    </span>
                                                </button>
                                                <input type="file" style="display: none;" name="importsubDataObjFileId"
                                                       id="importsubDataObjFileId"
                                                       onchange="dataObj.importSubDataItem('importsubDataObjFileId');">
                                                <button type="button" class="btn btn-success btn-sm"
                                                        style="margin-left: 5px"
                                                        onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpDataAverageWageUrbanEmployeesItemTemplate);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-download-alt"></i>
                                                    </span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </form>
                            <table id="tbAverageWageUrbanEmployeesItemList" class="table table-bordered"></table>
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

<div id="divAverageWageUrbanEmployeesItemBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">${baseViewDto.currentMenu.name} 从表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-8">
                                                <div class="input-group">
                                                    <input type="text"  name="type" list="typeDataList"
                                                           class="form-control form-control-sm" required>
                                                    <datalist id="typeDataList">
                                                        <option value="企业">企业</option>
                                                    </datalist>
                                                    <div class="input-group-prepend ">
                                                        <button class="btn btn-warning btn-sm"
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                            <i class="fa "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-8">
                                                <div class="input-group">
                                                    <input type="text"  name="category" list="categoryDataList"
                                                           class="form-control form-control-sm" required>
                                                    <datalist id="categoryDataList">
                                                        <option value="农">农</option>
                                                    </datalist>
                                                    <div class="input-group-prepend ">
                                                        <button class="btn btn-warning btn-sm"
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                            <i class="fa "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                国有经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="stateOwnedEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="国有经济"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                集体经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="collectiveEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="集体经济"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                私营经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="privateEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="私营经济"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                其他经济
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="otherEconomy" data-rule-number='true'
                                                       onblur="dataObj.handleTotal(this)"
                                                       class="form-control input-full"
                                                       placeholder="其他经济"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-4 col-form-label">
                                                总计
                                            </label>
                                            <div class="col-sm-8">
                                                <input name="total" data-rule-number='true'
                                                       class="form-control input-full"
                                                       placeholder="总计"/>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObj.subSaveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
