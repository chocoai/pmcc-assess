<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <script type="text/javascript"
                    src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
            <!-- 房产证 -->
            <div class="x_panel" id="viewDeclareRealtyHouseCert">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            房产证
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <table class="table table-bordered" id="tableDeclareRealtyHouseCert">
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 土地证 -->
            <%--<div class="x_panel" id="viewDeclareRealtyLandCert">--%>
                <%--<div class="x_content">--%>
                    <%--<div class="x_title">--%>
                        <%--<h3>--%>
                            <%--土地证--%>
                        <%--</h3>--%>
                        <%--<div class="clearfix"></div>--%>
                    <%--</div>--%>
                    <%--<form class="form-horizontal">--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="x-valid">--%>
                                <%--<table class="table table-bordered" id="tableDeclareRealtyLandCert">--%>
                                    <%--<!-- cerare document add ajax data-->--%>
                                <%--</table>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>

            <!-- 不动产证 -->
            <div class="x_panel" id="viewDeclareRealtyRealEstateCert">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            不动产证
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <table class="table table-bordered" id="tableDeclareRealtyRealEstateCert">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content form-horizontal">
                    <form class="form-horizontal" id="declareApplyForm">

                        <input type="hidden" name="id" value="${declare.id}">
                        <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托单位</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${declare.client}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">完成时限</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"><fmt:formatDate value='${declare.dateLimit}'
                                                                                pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1   control-label">
                                    估价委托书
                                </label>
                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5  ">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script>

    var declareApprovalFun = {};

    declareApprovalFun.houseConfig = {
        frm: declareCommon.config.house.frm,
        name: declareCommon.config.house.name,
        table: declareCommon.config.house.table,
        box: declareCommon.config.house.box,
        fileId: declareCommon.config.house.fileId,
        fileIdNew: declareCommon.config.house.fileIdNew,
        fileViewNew: declareCommon.config.house.fileViewNew,
        landFileId: declareCommon.config.house.landFileId,
        son: {
            declareRealtyLandCert: {
                frm: declareCommon.config.house.son.declareRealtyLandCert.frm,
                box: declareCommon.config.house.son.declareRealtyLandCert.box,
                view: declareCommon.config.house.son.declareRealtyLandCert.view,
                fileId: declareCommon.config.house.son.declareRealtyLandCert.fileId,
                name: declareCommon.config.house.son.declareRealtyLandCert.name,
                table: declareCommon.config.house.son.declareRealtyLandCert.table
            }
        },
        declareEconomicIndicatorsHead:{
            frm:  declareCommon.config.declareEconomicIndicatorsHead.frm,
            name: declareCommon.config.declareEconomicIndicatorsHead.name,
            box: declareCommon.config.declareEconomicIndicatorsHead.box
        } ,
        declareEconomicIndicatorsContent:{
            frm:  declareCommon.config.declareEconomicIndicatorsContent.frm,
            name: declareCommon.config.declareEconomicIndicatorsContent.name
        }
    };

    declareApprovalFun.declareRealtyRealEstateCertConfig = {
        frm: declareCommon.config.declareRealty.frm,
        name: declareCommon.config.declareRealty.name,
        table: declareCommon.config.declareRealty.table,
        box: declareCommon.config.declareRealty.box,
        fileId: declareCommon.config.declareRealty.fileId,
        newFileId: declareCommon.config.declareRealty.newFileId,
        fileView: declareCommon.config.declareRealty.fileView,
        declareEconomicIndicatorsHead:{
            frm:  declareCommon.config.declareEconomicIndicatorsHead2.frm,
            name: declareCommon.config.declareEconomicIndicatorsHead2.name,
            box: declareCommon.config.declareEconomicIndicatorsHead2.box
        } ,
        declareEconomicIndicatorsContent:{
            frm:  declareCommon.config.declareEconomicIndicatorsContent2.frm,
            name: declareCommon.config.declareEconomicIndicatorsContent2.name
        }
    };

    declareApprovalFun.landConfig = {
        frm: declareCommon.config.land.frm,
        name: declareCommon.config.land.name,
        table: declareCommon.config.land.table,
        box: declareCommon.config.land.box,
        fileId: declareCommon.config.land.fileId,
        newFileId: declareCommon.config.land.newFileId,
        houseFileId: declareCommon.config.land.houseFileId,
        newHouseFileId: declareCommon.config.land.newHouseFileId,
        HouseCert:{
            frm:declareCommon.config.land.HouseCert.frm,
            box:declareCommon.config.land.HouseCert.box
        }
    };

    //房产证显示
    declareApprovalFun.houseFindData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.houseConfig.box).find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        $('#' + declareApprovalFun.houseConfig.box).find(".panel-body").append(commonDeclareApprovalModel.house.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.houseConfig.frm), function (area) {
            declareCommon.initHouse(item, $("#" + declareApprovalFun.houseConfig.frm), [declareApprovalFun.houseConfig.fileId], null);
            $('#' + declareApprovalFun.houseConfig.box).modal("show");
        });
    };

    //房产证关联的土地证
    declareApprovalFun.houseRelationLandData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landId)) {//关联情况
                declareCommon.getLandData(centerData.landId, function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        $('#' + declareApprovalFun.houseConfig.son.declareRealtyLandCert.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
                        $('#' + declareApprovalFun.houseConfig.son.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
                        declareCommon.initLand(data, $("#" + declareApprovalFun.houseConfig.son.declareRealtyLandCert.frm), [declareApprovalFun.houseConfig.son.declareRealtyLandCert.fileId], null);
                        $('#' + declareApprovalFun.houseConfig.son.declareRealtyLandCert.box).modal("show");
                    } else {
                        toastr.success('关联的土地证数据已经被删除!');
                    }
                });
            }else {
                toastr.success('无土地证数据!');
            }
        });
    };

    //房产证关联的经济指标
    declareApprovalFun.houseRelationDeclareEconomicIndicatorsData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var element1 = $("#"+declareApprovalFun.houseConfig.declareEconomicIndicatorsHead.frm).find(".panel-body") ;
        var element2 = $("#"+declareApprovalFun.houseConfig.declareEconomicIndicatorsContent.frm).find(".panel-body") ;
        declareCommon.appendDeclareEconomicIndicatorsApproval(element1,element2) ;
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                declareCommon.getByDeclareEconomicIndicatorsHeadId(centerData.indicatorId , function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        declareCommon.initDeclareEconomicIndicators($("#" + declareApprovalFun.houseConfig.declareEconomicIndicatorsHead.frm) ,$("#"+declareApprovalFun.houseConfig.declareEconomicIndicatorsContent.frm), data , function () {
                            $('#' + declareApprovalFun.houseConfig.declareEconomicIndicatorsHead.box).modal("show");
                        }) ;
                    } else {
                        toastr.success('关联的经济指标数据已经被删除!');
                    }
                }) ;
            }else {
                toastr.success('经济指标无!');
            }
        });
    };

    //房产 table list
    declareApprovalFun.houseLoadList = function () {
        var cols = declareCommon.getHouseColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:declareApprovalFun.houseRelationLandData(' + row.id + ');" ><i class="fa fa-eye">土地证</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:declareApprovalFun.houseRelationDeclareEconomicIndicatorsData(' + row.id + ');" ><i class="fa fa-eye">经济指标</i></a>';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="declareApprovalFun.houseFindData(' + row.id + ',\'tb_List\')">房产证<i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('destroy');
        TableInit(declareApprovalFun.houseConfig.table, "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertList", cols, {
            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}', enable: declareCommon.masterData
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    //土地证
    declareApprovalFun.landFindData = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.landConfig.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
        $('#' + declareApprovalFun.landConfig.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.landConfig.frm), function (area) {
            declareCommon.initLand(item, $("#" + declareApprovalFun.landConfig.frm), [declareApprovalFun.landConfig.fileId], null);
            $('#' + declareApprovalFun.landConfig.box).modal("show");
        });
    };

    //土地证关联的房产证
    declareApprovalFun.landRelationHouseData = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.landConfig.HouseCert.box).find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        $('#' + declareApprovalFun.landConfig.HouseCert.box).find(".panel-body").append(commonDeclareApprovalModel.house.getHtml());
        if (declareCommon.isNotBlank(item.pid)) {
            declareCommon.getHouseData(item.pid,function (data) {
                if (declareCommon.isNotBlank(data)) {
                    declareCommon.initHouse(data,$("#" + declareApprovalFun.landConfig.HouseCert.frm),[declareApprovalFun.landConfig.houseFileId],null);
                    $('#' + declareApprovalFun.landConfig.HouseCert.box).modal("show");
                } else {
                    toastr.success('关联数据已经被删除了!');
                }
            });
        }
    };

    //土地 table list
    declareApprovalFun.landLoadList = function () {
        var cols = declareCommon.getLandColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.pid) {
                    str += '<a class="btn btn-xs btn-success" href="javascript:declareApprovalFun.landRelationHouseData(' + row.id + ');" ><i class="fa fa-check">房产证关联数据查看详情</i></a>';
                }
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="土地证查看详情" onclick="declareApprovalFun.landFindData(' + row.id + ',\'tb_List\')">土地证<i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + declareApprovalFun.landConfig.table).bootstrapTable('destroy');
        TableInit(declareApprovalFun.landConfig.table, "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}', enable: declareCommon.masterData
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    //不动产
    declareApprovalFun.realtyRealEstatefindData = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find(".panel-body").append(commonDeclareApprovalModel.realEstateCert.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm),function (area) {
            declareCommon.initDeclareRealty(item,$("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm),[declareApprovalFun.declareRealtyRealEstateCertConfig.fileId],null);
            $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).modal("show");
        });
    };

    //不动产 经济指标
    declareApprovalFun.realtyRealDeclareEconomicIndicators = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var element1 = $("#"+declareApprovalFun.declareRealtyRealEstateCertConfig.declareEconomicIndicatorsHead.frm).find(".panel-body") ;
        var element2 = $("#"+declareApprovalFun.declareRealtyRealEstateCertConfig.declareEconomicIndicatorsContent.frm).find(".panel-body") ;
        declareCommon.appendDeclareEconomicIndicatorsApproval(element1,element2) ;
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                declareCommon.getByDeclareEconomicIndicatorsHeadId(centerData.indicatorId , function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        declareCommon.initDeclareEconomicIndicators($("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.declareEconomicIndicatorsHead.frm) ,$("#"+declareApprovalFun.declareRealtyRealEstateCertConfig.declareEconomicIndicatorsContent.frm), data , function () {
                            $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.declareEconomicIndicatorsHead.box).modal("show");
                        }) ;
                    } else {
                        toastr.success('关联的经济指标数据已经被删除!');
                    }
                }) ;
            }else {
                toastr.success('经济指标无!');
            }
        });
    };

    //不动产
    declareApprovalFun.realEstateloadList  = function () {
        var cols = declareCommon.getRealEstateColumn();
        cols.push({field: 'fileViewName', title: '不动产附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:declareApprovalFun.realtyRealDeclareEconomicIndicators(' + row.id + ');" ><i class="fa fa-themeisle">经济指标</i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看详情" onclick="declareApprovalFun.realtyRealEstatefindData(' + row.id + ',\'exampleList\')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('destroy');
        TableInit(declareApprovalFun.declareRealtyRealEstateCertConfig.table, "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}', enable: declareCommon.masterData
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    $(function () {
        declareApprovalFun.realEstateloadList();
        declareApprovalFun.houseLoadList();
        declareApprovalFun.landLoadList();
        declareCommon.showFile(AssessUploadKey.PROJECT_PROXY, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", false);
    });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>

<!--  房产证模块 经济指标 -->
<div id="boxDeclareEconomicIndicatorsHead" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">经济指标</h3>
            </div>
            <form id="frmDeclareEconomicIndicatorsHead" class="form-horizontal" style="display: block;margin-bottom: 0px;padding-bottom: 0px;">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <form id="frmDeclareEconomicIndicatorsContent" class="form-horizontal" style="display: block;margin-top: -4px;padding-top: -4px;">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <form>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--  房产证模块 房产证信息 -->
<div id="boxDeclareRealtyHouseCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房产证信息</h3>
            </div>
            <form id="frmDeclareRealtyHouseCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<!--  房产证模块  土地证 -->
<div id="boxSonDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">关联土地证信息</h3>
            </div>
            <form id="frmSonDeclareRealtyLandCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--  土地证模块  房产证信息-->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">关联房产证信息</h3>
            </div>
            <form id="declareRealtyLandCert_HouseCert_frm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <div id="_declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<!--  土地证模块  土地证信息-->
<div id="boxDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证信息</h3>
            </div>
            <form id="frmDeclareRealtyLandCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 不动产  -->
<div id="boxDeclareRealtyRealEstateCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产证信息</h3>
            </div>
            <form id="frmDeclareRealtyRealEstateCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- 不动产 经济指标  -->
<div id="boxDeclareEconomicIndicatorsHeadRealtyRealEstate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">经济指标</h3>
            </div>
            <form id="frmDeclareEconomicIndicatorsHeadRealtyRealEstate" class="form-horizontal" style="display: block;margin-bottom: 0px;padding-bottom: 0px;">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <form id="frmDeclareEconomicIndicatorsContentRealtyRealEstate" class="form-horizontal" style="display: block;margin-top: -4px;padding-top: -4px;">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <form>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

