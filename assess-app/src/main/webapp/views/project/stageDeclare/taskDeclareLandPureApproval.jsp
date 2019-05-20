<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
            <!-- 土地证 -->
            <div class="x_panel" id="viewDeclareRealtyLandCert">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            土地证
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <table class="table table-bordered" id="tableDeclareRealtyLandCert">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 不动产证 -->
            <div class="x_panel" >
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

            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">
    var declareApprovalFun = {};
    declareApprovalFun.declareRealtyRealEstateCertConfig = {
        frm: declareCommon.config.declareRealty.frm,
        name: declareCommon.config.declareRealty.name,
        table: declareCommon.config.declareRealty.table,
        box: declareCommon.config.declareRealty.box,
        fileId: declareCommon.config.declareRealty.fileId,
        newFileId: declareCommon.config.declareRealty.newFileId,
        fileView: declareCommon.config.declareRealty.fileView
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



    //土地证
    declareApprovalFun.landFindData = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        AssessCommon.getDataDicInfo(item.landCertGetQuestion, function (data) {
            if (data.name == "有权证") {

            } else {

            }
            //暂时不处理
            $('#' + declareApprovalFun.landConfig.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
            $('#' + declareApprovalFun.landConfig.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
            declareCommon.showHtmlMastInit($("#" + declareApprovalFun.landConfig.frm), function (area) {
                declareCommon.initLand(item, $("#" + declareApprovalFun.landConfig.frm), [declareApprovalFun.landConfig.fileId], null);
                $('#' + declareApprovalFun.landConfig.box).modal("show");
            });
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
        AssessCommon.getDataDicInfo(item.landCertGetQuestion, function (data) {
            if (data.name == "有权证") {

            } else {

            }
            //暂时不处理
            $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
            $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find(".panel-body").append(commonDeclareApprovalModel.realEstateCert.getHtml());
            declareCommon.showHtmlMastInit($("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm),function (area) {
                declareCommon.initDeclareRealty(item,$("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm),[declareApprovalFun.declareRealtyRealEstateCertConfig.newFileId],null);
                $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).modal("show");
            });
        });
    };

    //不动产
    declareApprovalFun.realEstateloadList  = function () {
        var cols = declareCommon.getRealEstateColumn();
        cols.push({field: 'fileViewName', title: '不动产附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
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
        declareApprovalFun.landLoadList();
        declareCommon.showFile(AssessUploadKey.PROJECT_PROXY, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", false);
    });
</script>

<script type="application/javascript">

    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>


<!--  土地证模块  房产证信息-->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
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
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
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
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证信息</h3>
            </div>
            <form id="frmDeclareRealtyLandCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <div id="_declareRealtyLandCertFileId"></div>
                                    </div>
                                </div>
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
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产证信息</h3>
            </div>
            <form id="frmDeclareRealtyRealEstateCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <div id="_declareRealtyRealEstateCertFileId"></div>
                                    </div>
                                </div>
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
</html>

