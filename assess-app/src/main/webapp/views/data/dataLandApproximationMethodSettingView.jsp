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
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    省
                                </label>
                                <div class="col-sm-1">
                                    <select name="province" class="form-control search-select select2" >
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    市
                                </label>
                                <div class="col-sm-1">
                                    <select name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    区
                                </label>
                                <div class="col-sm-1">
                                    <select name="district" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    类别
                                </label>
                                <div class="col-sm-2">
                                    <select name="category"
                                            class="form-control search-select select2" id="queryName">
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataObjFun.listMaster()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success"
                                        onclick="dataObjFun.initFormMaster({});" data-toggle="modal" href="#MasterBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="MasterTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/project/tool/residueRatio.jsp" %>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var dataObjFun = {} ;

    dataObjFun.config = {
        frm: $("#MasterFrm") ,
        box: $("#MasterBox") ,
        table:$("#MasterTable")
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
        Alert('确认要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/landApproximationMethodSetting/delete/' + id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.listMaster();
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        })
    };

    dataObjFun.listMaster = function () {
        var cols = [];
        cols.push({field: 'areaName', title: '区域'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({
            field: 'releaseTime', title: '发布时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editMasterById(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteMasterById(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        dataObjFun.config.table.bootstrapTable('destroy');
        TableInit(dataObjFun.config.table.prop("id"), "${pageContext.request.contextPath}/landApproximationMethodSetting/getBootstrapTableVo", cols, {
            province:$("#frmQuery").find("select[name='province']").val(),
            city:$("#frmQuery").find("select[name='city']").val(),
            district:$("#frmQuery").find("select[name='district']").val(),
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
                    toastr.success('成功');
                    dataObjFun.listMaster();
                    $(dataObjFun.config.box.selector).modal("hide");
                } else {
                    Alert(result.errmsg);
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地逼近法补偿配置</h3>
            </div>
            <form id="MasterFrm" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province"
                                                    class="form-control search-select select2" required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">市<span class="symbol required"></span><span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="city" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县</label>
                                        <div class="col-sm-3">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            类别
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="category"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            文号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="symbol" placeholder="文号">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            金额
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-number='true' class="form-control" name="amountMoney"
                                                   placeholder="金额">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            发布时间
                                        </label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="releaseTime" placeholder="发布日期">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.saveMaster()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
