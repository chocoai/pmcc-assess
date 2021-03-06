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
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途</label>
                                <div class="col-sm-2">
                                    <select name="purpose"
                                            class="form-control search-select select2" id="queryType">
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataObjFun.listMaster()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="dataObjFun.initFormMaster({})"
                                        data-toggle="modal" href="#divBoxFather"> 新增
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
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var dataObjFun = {};

    dataObjFun.config = {
        frm: "#frmFather",
        box: "#divBoxFather",
        table: "#tb_FatherList",
        dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox: "#dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox",
        dataAllocationCorrectionCoefficientVolumeRatioDetailTable: "#dataAllocationCorrectionCoefficientVolumeRatioDetailTable",
        dataAllocationCorrectionCoefficientVolumeRatioDetailBox: "#dataAllocationCorrectionCoefficientVolumeRatioDetailBox",
        dataAllocationCorrectionCoefficientVolumeRatioDetailFrm: "#dataAllocationCorrectionCoefficientVolumeRatioDetailFrm"
    };

    dataObjFun.initFormMaster = function (data) {
        var frm = $(dataObjFun.config.frm);
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
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, data.purpose, function (html, data) {
            frm.find("select[name='purpose']").empty().html(html).trigger('change');
        });
    };

    dataObjFun.editMasterById = function (index) {
        var row = $(dataObjFun.config.table).bootstrapTable('getData')[index];
        dataObjFun.initFormMaster(row);
        $(dataObjFun.config.box).modal("show");
    };

    dataObjFun.deleteMasterById = function (id) {
        Alert('确认要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatio/delete/' + id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess('成功','删除成功');
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
        cols.push({field: 'purposeName', title: '土地用途'});
        cols.push({
            field: 'releaseDate', title: '发布时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'evaluationDate', title: '估价期日', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editMasterById(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteMasterById(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="dataObjFun.showDataAllocationCorrectionCoefficientVolumeRatioDetail(' + row.id + ')"><i class="fa fa-th-list fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $(dataObjFun.config.table).bootstrapTable('destroy');
        TableInit($(dataObjFun.config.table).prop("id"), "${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatio/getBootstrapTableVo", cols, {
            purpose: $("#queryType").val()
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
        var frm = $(dataObjFun.config.frm);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        $.ajax({
            url:  '${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatio'+"/save",
            data: {formData:JSON.stringify(data)},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功','成功');
                    dataObjFun.listMaster();
                    $(dataObjFun.config.box).modal("hide");
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    dataObjFun.showDataAllocationCorrectionCoefficientVolumeRatioDetail = function (id) {
        $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox).find("input[name='allocationVolumeRatioId']").val(id);
        $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox).modal("show");
        dataObjFun.showDataHousePriceIndexDetailList(id);
    };

    dataObjFun.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox = function () {
        $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm).clearAll();
        var allocationVolumeRatioId = $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox).find("input[name='allocationVolumeRatioId']").val();
        $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm).find('input[name=allocationVolumeRatioId]').val(allocationVolumeRatioId);
    };

    dataObjFun.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
        var row = $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable).bootstrapTable('getData')[index];
        Alert('确认要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataLandLevelDetailVolume/delete/' + row.id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess('成功','删除成功');
                        dataObjFun.showDataHousePriceIndexDetailList(row.allocationVolumeRatioId);
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        })
    };

    dataObjFun.editDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
        var row = $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable).bootstrapTable('getData')[index];
        var frm = $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm);
        frm.clearAll();
        frm.initForm(row);
        $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox).modal("show");
    };

    dataObjFun.saveDataAllocationCorrectionCoefficientVolumeRatioDetail = function () {
        var frm = $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/dataLandLevelDetailVolume' +"/save",
            data: data,
            type: "post",
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功','成功');
                    dataObjFun.showDataHousePriceIndexDetailList(data.allocationVolumeRatioId);
                    $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox).modal("hide");
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    dataObjFun.showDataHousePriceIndexDetailList = function (allocationVolumeRatioId) {
        var cols = [];
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'correctionFactor', title: '修正系数'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable).bootstrapTable('destroy');
        TableInit($(dataObjFun.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable).prop("id"), "${pageContext.request.contextPath}/dataLandLevelDetailVolume/getBootstrapTableVo", cols, {
            province:$("#frmQuery").find("select[name='province']").val(),
            city:$("#frmQuery").find("select[name='city']").val(),
            district:$("#frmQuery").find("select[name='district']").val(),
            allocationVolumeRatioId: allocationVolumeRatioId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    $(document).ready(function () {
        dataObjFun.listMaster();
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, null, function (html, data) {
            $("#frmQuery").find("select[name='purpose']").empty().html(html).trigger('change');
        });
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#frmQuery").find("select[name='province']"),
            cityTarget: $("#frmQuery").find("select[name='city']"),
            districtTarget: $("#frmQuery").find("select[name='district']")
        });
    });

</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">容积率修正系数配置 </h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select name="province"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select name="city" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">县</label>
                                        <div class="col-sm-10">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">土地用途<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select name="purpose" required="required"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            发布时间
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="releaseDate" placeholder="发布日期">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            估价期
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="evaluationDate" placeholder="估价期">
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

<div id="dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">容积率修正系数配置 详情</h3>
                <input type="hidden" name="allocationVolumeRatioId">
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="dataObjFun.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox()"
                     data-toggle="modal" href="#dataAllocationCorrectionCoefficientVolumeRatioDetailBox"> 新增
                </div>
                <table class="table table-bordered" id="dataAllocationCorrectionCoefficientVolumeRatioDetailTable">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="dataAllocationCorrectionCoefficientVolumeRatioDetailBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">容积率修正系数配置 详情</h3>
            </div>
            <form id="dataAllocationCorrectionCoefficientVolumeRatioDetailFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="allocationVolumeRatioId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            容积率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-number='true'
                                                   class="form-control"
                                                   name="plotRatio" placeholder="容积率">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            修正系数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-number='true'
                                                   class="form-control"
                                                   name="correctionFactor" placeholder="修正系数">
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
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.saveDataAllocationCorrectionCoefficientVolumeRatioDetail()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
