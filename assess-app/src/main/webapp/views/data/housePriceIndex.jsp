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
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">类别</label>
                                <div class="col-sm-7">
                                    <select name="type"
                                            class="form-control search-select select2" id="queryType">
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
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
        indexDetailTableBox: "#indexDetailTableBox",
        indexDetailTable: "#indexDetailTable",
        indexDetailBox: "#indexDetailBox",
        indexDetailFrm: "#indexDetailFrm"
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
        AssessCommon.loadDataDicByKey(AssessDicKey.dataTypeIndex, data.type, function (html, data) {
            frm.find("select[name='type']").empty().html(html).trigger('change');
        });
    };

    dataObjFun.editMasterById = function (index) {
        var row = $(dataObjFun.config.table).bootstrapTable('getData')[index];
        $.ajax({
            url: "${pageContext.request.contextPath}/housePriceIndex/get/" + row.id,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    dataObjFun.initFormMaster(result.data);
                    $(dataObjFun.config.box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    dataObjFun.deleteMasterById = function (id) {
        Alert('确认要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/housePriceIndex/delete/' + id,
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
        cols.push({field: 'typeName', title: '类别'});
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
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="dataObjFun.showDataHousePriceIndexDetail(' + row.id + ')"><i class="fa fa-th-list fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $(dataObjFun.config.table).bootstrapTable('destroy');
        TableInit($(dataObjFun.config.table).prop("id"), "${pageContext.request.contextPath}/housePriceIndex/getBootstrapTableVo", cols, {
            type: $("#queryType").val()
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
        var url = '${pageContext.request.contextPath}/housePriceIndex';
        var _method = null;
        if (data.id) {
            url += "/edit/" + JSON.stringify(data);
            _method = "PUT";
        } else {
            _method = "POST";
            url += "/save/" + JSON.stringify(data);
        }
        $.ajax({
            url: url,
            data: {_method: _method},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功');
                    dataObjFun.listMaster();
                    $(dataObjFun.config.box).modal("hide");
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    dataObjFun.showDataHousePriceIndexDetail = function (id) {
        $(dataObjFun.config.indexDetailTableBox).find("input[name='housePriceId']").val(id);
        $(dataObjFun.config.indexDetailTableBox).modal("show");
        dataObjFun.showDataHousePriceIndexDetailList(id);
    };

    dataObjFun.showDataHousePriceIndexDetailBox = function () {
        $(dataObjFun.config.indexDetailFrm).clearAll();
        var housePriceId = $(dataObjFun.config.indexDetailTableBox).find("input[name='housePriceId']").val();
        $(dataObjFun.config.indexDetailFrm).find('input[name=housePriceId]').val(housePriceId);
    };

    dataObjFun.deleteDataHousePriceIndexDetail = function (index) {
        var row = $(dataObjFun.config.indexDetailTable).bootstrapTable('getData')[index];
        Alert('确认要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataHousePriceIndexDetail/delete/' + row.id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.showDataHousePriceIndexDetailList(row.housePriceId);
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        })
    };

    dataObjFun.editDataHousePriceIndexDetail = function (index) {
        var row = $(dataObjFun.config.indexDetailTable).bootstrapTable('getData')[index];
        var frm = $(dataObjFun.config.indexDetailFrm);
        frm.clearAll();
        frm.initForm(row);
        $(dataObjFun.config.indexDetailBox).modal("show");
    };

    dataObjFun.saveDataHousePriceIndexDetail = function () {
        var frm = $(dataObjFun.config.indexDetailFrm);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/dataHousePriceIndexDetail/save',
            data: {formData: JSON.stringify(data)},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功');
                    dataObjFun.showDataHousePriceIndexDetailList(data.housePriceId);
                    $(dataObjFun.config.indexDetailBox).modal("hide");
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    dataObjFun.showDataHousePriceIndexDetailList = function (housePriceId) {
        var cols = [];
        cols.push({
            field: 'startDate', title: '开始月份', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'indexNumber', title: '指数'});
        cols.push({
            field: 'endDate', title: '结束月份', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editDataHousePriceIndexDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteDataHousePriceIndexDetail(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $(dataObjFun.config.indexDetailTable).bootstrapTable('destroy');
        TableInit($(dataObjFun.config.indexDetailTable).prop("id"), "${pageContext.request.contextPath}/dataHousePriceIndexDetail/getBootstrapTableVo", cols, {
            housePriceId: housePriceId
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
        AssessCommon.loadDataDicByKey(AssessDicKey.dataTypeIndex, null, function (html, data) {
            $("#frmQuery").find("select[name='type']").empty().html(html).trigger('change');
        });
        DatepickerUtils.initDate($('.date-month'), {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true,
            format: 'yyyy-mm',
            startView: 4,
            minView: 3
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
                <h3 class="modal-title">房价指数</h3>
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
                                        <label class="col-sm-2 control-label">类别<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select name="type" required="required"
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

<div id="indexDetailTableBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">指数详情</h3>
                <input type="hidden" name="housePriceId">
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="dataObjFun.showDataHousePriceIndexDetailBox()"
                     data-toggle="modal" href="#indexDetailBox"> 新增
                </div>
                <table class="table table-bordered" id="indexDetailTable">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="indexDetailBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房价指数</h3>
            </div>
            <form id="indexDetailFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="housePriceId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开始月份<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly" required
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="startDate" placeholder="开始月份">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            结束月份<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly" required
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="endDate" placeholder="结束月份">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            指数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required
                                                   class="form-control"
                                                   name="indexNumber" placeholder="指数">
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
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.saveDataHousePriceIndexDetail()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
