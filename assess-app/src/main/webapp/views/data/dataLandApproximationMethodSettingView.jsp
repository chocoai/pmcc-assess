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
                                        <div class="col-md-3 p-0">
                                            <select name="province"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">市</label>
                                        <div class="col-md-3 p-0">
                                            <select name="city" class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">区</label>
                                        <div class="col-md-3 p-0">
                                            <select name="district"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">类别</label>
                                        <div class="col-md-3 p-0">
                                            <select name="category"
                                                    class="form-control input-full search-select select2"
                                                    id="queryName">
                                            </select>
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataObjFun.listMaster()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataObjFun.initFormMaster({});"
                                                href="#MasterBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="MasterTable">
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
<%@include file="/views/project/tool/residueRatio.jsp" %>

<script type="text/javascript">
    var dataObjFun = {};

    dataObjFun.config = {
        frm: $("#MasterFrm"),
        box: $("#MasterBox"),
        table: $("#MasterTable")
    };

    dataObjFun.initFormMaster = function (data) {
        var frm = $(dataObjFun.config.frm.selector);
        frm.clearAll();
        frm.initForm(data);
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name='province']"),
            cityTarget: frm.find("select[name='city']"),
            districtTarget: frm.find("select[name='district']"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.dataLandApproximationMethodSetting, data.category, function (html, data) {
            frm.find("select[name='category']").empty().html(html).trigger('change');
        });
    };


    dataObjFun.editMasterById = function (index) {
        var row = dataObjFun.config.table.bootstrapTable('getData')[index];
        dataObjFun.initFormMaster(row);
        $(dataObjFun.config.box.selector).modal("show");
    };

    dataObjFun.deleteMasterById = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/landApproximationMethodSetting/delete/' + id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.listMaster();
                    } else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                }
            })
        })
    };

    dataObjFun.listMaster = function () {
        var cols = [];
        cols.push({field: 'areaName', width: '30%',title: '区域'});
        cols.push({field: 'categoryName',width: '15%', title: '类别'});
        cols.push({field: 'symbol',width: '15%', title: '文号'});
        cols.push({field: 'amountMoney',width: '15%', title: '金额'});
        cols.push({
            field: 'releaseTime', width: '15%',title: '发布时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'id',width: '10%', title: '操作', formatter: function (value, row, index) {
                var str = '<button type="button" onclick="dataObjFun.editMasterById(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="dataObjFun.deleteMasterById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        dataObjFun.config.table.bootstrapTable('destroy');
        TableInit(dataObjFun.config.table.prop("id"), "${pageContext.request.contextPath}/landApproximationMethodSetting/getBootstrapTableVo", cols, {
            province: $("#frmQuery").find("select[name='province']").val(),
            city: $("#frmQuery").find("select[name='city']").val(),
            district: $("#frmQuery").find("select[name='district']").val(),
            category: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    dataObjFun.saveMaster = function () {
        var frm = $(dataObjFun.config.frm.selector);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/landApproximationMethodSetting/save',
            data: {formData: JSON.stringify(data)},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    dataObjFun.listMaster();
                    $(dataObjFun.config.box.selector).modal("hide");
                } else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            }
        })
    };

    $(document).ready(function () {
        dataObjFun.listMaster();
        AssessCommon.loadDataDicByKey(AssessDicKey.dataLandApproximationMethodSetting, null, function (html, data) {
            $("#frmQuery").find("select[name='category']").empty().html(html).trigger('change');
        });
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#frmQuery").find("select[name='province']"),
            cityTarget: $("#frmQuery").find("select[name='city']"),
            districtTarget: $("#frmQuery").find("select[name='district']")
        });
    });

</script>

<div id="MasterBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地逼近法补偿配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="MasterFrm" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
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
                                                <select name="city" class="form-control input-full search-select select2"
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
                                            <label class="col-sm-2 col-form-label">
                                                类别
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="category"
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
                                                文号
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="symbol"
                                                       placeholder="文号">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                金额
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true' class="form-control input-full"
                                                       name="amountMoney"
                                                       placeholder="金额">
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                发布时间
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       name="releaseTime" placeholder="发布日期">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.saveMaster()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
