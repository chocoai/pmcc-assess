<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>



<div id="declarePreSalePermitDataModelBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">商品房预售许可证</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <div id="_declarePreSalePermitAnnex"></div>
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
            </div>
        </div>
    </div>
</div>



<div id="declareBuildingConstructionPermitDataModelBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建筑工程施工许可证</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <div id="_declareBuildingConstructionPermitAnnex"></div>
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
            </div>
        </div>
    </div>
</div>




<div id="declareBuildingPermitDataModelBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">建设工程规划许可证</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <div id="_declareBuildingPermitAnnex"></div>
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
            </div>
        </div>
    </div>
</div>


<div id="declareLandUsePermitDataModelBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建设用地规划许可证</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <div id="_declareLandUsePermitAnnex"></div>
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
            </div>
        </div>
    </div>
</div>





<!-- 不动产清单 -->
<div id="declareRealtyCheckListDataModelBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">不动产清单</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="marsterId">
                    <input type="hidden" name="autoInitNumber">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>


<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row mt--2">
                    <link rel="stylesheet"
                          href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
                    <script type="text/javascript"
                            src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
                    <script type="text/javascript"
                            src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
                    <!-- 申报各种类型的html视图 -->
                    <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
                    <%@include file="/views/method/module/economicIndicators.jsp" %>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        申报记录
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <div id="objDeclareRecordAssessmentPlanDetailSelector">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <form class="form-horizontal">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" data-title="许可证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        许可证以及清单信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <ul class="nav nav-pills nav-secondary" role="tablist">
                                    <li class="nav-item submenu">
                                        <!-- 设置为默认选中 -->
                                        <a class="nav-link active show" data-toggle="pill"
                                           href="#tab_content_RealtyReal_declareBuildingPermit" role="tab"
                                           aria-selected="true">建设工程规划许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_RealtyReal_declareLandUsePermitLand" role="tab"
                                           aria-selected="false">建设用地规划许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_RealtyReal_declareBuildingConstructionPermitLand"
                                           role="tab"
                                           aria-selected="false">建筑工程施工许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_RealtyReal_declarePreSalePermitLand" role="tab"
                                           aria-selected="false">商品房预售许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_RealtyReal_CheckList" role="tab"
                                           aria-selected="false">不动产清单</a>
                                    </li>
                                </ul>
                                <div class="tab-content mt-2 mb-3">
                                    <div class="tab-pane fade show active"
                                         id="tab_content_RealtyReal_declareBuildingPermit"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <!--建设工程规划许可证-->
                                            <table class="table table-bordered" id="tbDeclareBuildingPermitList">
                                            </table>
                                        </div>
                                    </div>

                                    <div class="tab-pane fade " id="tab_content_RealtyReal_declareLandUsePermitLand"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <!--建设用地规划许可证-->
                                            <table class="table table-bordered" id="tbDeclareLandUsePermitList">
                                            </table>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade "
                                         id="tab_content_RealtyReal_declareBuildingConstructionPermitLand"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <!--建筑工程施工许可证-->
                                            <table class="table table-bordered" id="tbDeclareBuildingConstructionPermitList">
                                            </table>
                                        </div>
                                    </div>

                                    <div class="tab-pane fade " id="tab_content_RealtyReal_declarePreSalePermitLand"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <!--商品房预售许可证-->
                                            <table class="table table-bordered" id="tbDeclarePreSalePermitList">
                                            </table>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade " id="tab_content_RealtyReal_CheckList"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <!--不动产清单-->
                                            <table class="table table-bordered" id="tbDataDeclareRealtyCheckListList">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;" data-id="houseModel"
                         data-title="房产证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        房产证关联数据
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <ul class="nav nav-pills nav-secondary" role="tablist">
                                    <li class="nav-item submenu">
                                        <!-- 设置为默认选中 -->
                                        <a class="nav-link active show" data-toggle="pill"
                                           href="#tab_content_house_land" role="tab" aria-selected="true">土地证</a>
                                    </li>

                                </ul>
                                <div class="tab-content mt-2 mb-3">
                                    <div class="tab-pane fade show active" id="tab_content_house_land" role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <form class="form-horizontal">
                                            </form>
                                        </div>
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <div class="row">
                                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                    附件
                                                </label>
                                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                    <div id="_declareRealtyHouseCert_land_FileId"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;" data-id="landModel"
                         data-title="土地证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        土地证关联数据
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <ul class="nav nav-pills nav-secondary" role="tablist">
                                    <li class="nav-item submenu">
                                        <!-- 设置为默认选中 -->
                                        <a class="nav-link active show" data-toggle="pill"
                                           href="#tab_content_land_house" role="tab" aria-selected="true">房产证</a>
                                    </li>
                                </ul>
                                <div class="tab-content mt-2 mb-3">
                                    <div class="tab-pane fade show active" id="tab_content_land_house" role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <form class="form-horizontal">
                                            </form>
                                        </div>
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <div class="row">
                                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                    附件
                                                </label>
                                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                    <div id="__declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;"
                         data-id="RealtyRealModel" data-title="不动产证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        不动产证关联数据
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <ul class="nav nav-pills nav-secondary" role="tablist">

                                </ul>
                                <div class="tab-content mt-2 mb-3">
                                </div>
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


<input type="file" id="ajaxFileUpload" name="file" style="display: none;">

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

<script>

    var objDeclareRecord = {};

    objDeclareRecord.config = {
        selector: "#objDeclareRecordAssessmentPlanDetailSelector"
    };

    objDeclareRecord.houseConfig = {
        fileId: declareCommon.config.house.fileId
    };

    objDeclareRecord.landConfig = {
        fileId: declareCommon.config.land.fileId
    };

    objDeclareRecord.declareRealtyRealEstateCertConfig = {
        newFileId: declareCommon.config.declareRealty.newFileId
    };

    objDeclareRecord.loadLicense = function (centerId) {
        declareCommon.loadDeclareRealtyCheckListTable(centerId) ;
        declareCommon.loadTableDeclareLandUsePermit(centerId) ;
        declareCommon.loadTableDeclareBuildingPermit(centerId) ;
        declareCommon.loadTableDeclareBuildingConstructionPermit(centerId) ;
        declareCommon.loadTableDeclarePreSalePermit(centerId) ;
    } ;


    objDeclareRecord.init = function () {
        var selector = $(objDeclareRecord.config.selector);
        var targetId = '${declareRecord.dataTableId}';
        var tableName = '${declareRecord.dataTableName}';
        if (tableName == AssessDBKey.DeclareRealtyHouseCert) {
            var landId = $("#tab_content_house_land").find("form");
            var economicIndicators = $("#tab_content_house_economicIndicators");
            selector.find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.house.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getHouseData(targetId, function (data) {

                    declareCommon.initHouse(data, selector.find("form"), [objDeclareRecord.houseConfig.fileId], null, false);
                    var attribute = {readonly: "readonly", 'class': 'form-control'};
                    objDeclareRecord.loadLicense(data.centerId) ;
                    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
                        if (!centerData) {
                            return;
                        }
                        if (declareCommon.isNotBlank(centerData.landId)) {//关联情况
                            declareCommon.getLandData(centerData.landId, function (data) {
                                if (declareCommon.isNotBlank(data)) {
                                    landId.append(commonDeclareApprovalModel.land.getHtml());
                                    declareCommon.initLand(data, landId, [declareCommon.config.house.landFileId], null, false);
                                }
                            });
                        }
                    });

                });
            });
            $(document).find("[data-id=houseModel]").show();
        }


        if (tableName == AssessDBKey.DeclareRealtyLandCert) {
            selector.find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.land.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getLandData(targetId, function (data) {

                    declareCommon.initLand(data, selector.find("form"), [objDeclareRecord.landConfig.fileId], null, false);
                    objDeclareRecord.loadLicense(data.centerId) ;
                    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
                        if (!centerData) {
                            return;
                        }
                        var houseId = $("#tab_content_land_house").find("form");
                        var fileArr = [declareCommon.config.land.houseFileId];
                        if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
                            declareCommon.getHouseData(centerData.houseId, function (data) {
                                houseId.append(commonDeclareApprovalModel.house.getHtml());
                                declareCommon.initHouse(data, houseId, fileArr, null, false);
                            });
                        }
                    });
                });
            });

            $(document).find("[data-id=landModel]").show();
        }

        if (tableName == AssessDBKey.DeclareRealtyRealEstateCert) {
            selector.find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.realEstateCert.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getDeclareRealtyData(targetId, function (data) {

                    declareCommon.initDeclareRealty(data, selector.find("form"), [objDeclareRecord.declareRealtyRealEstateCertConfig.newFileId], null, false);
                    objDeclareRecord.loadLicense(data.centerId) ;
                });
            });
            $(document).find("[data-id=RealtyRealModel]").show();
        }



    };

    $(document).ready(function () {

        objDeclareRecord.init();


    });

</script>


</html>

