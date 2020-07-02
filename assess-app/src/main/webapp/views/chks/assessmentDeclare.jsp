<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5" style="padding-top: 2rem !important; padding-bottom: 2rem !important;">
                    <div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
                        <div>
                            <h2 class="text-white pb-2 fw-bold">申报考核-${assessmentPerformanceDto.businessKey}</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%--权证主信息--%>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        权证信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <div id="chksCustomerAssessmentPlanDetailSelector">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <form class="form-horizontal">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--房产证信息--%>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;" data-id="houseModel"
                         data-title="房产证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        其他信息
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
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_house_economicIndicators" role="tab"
                                           aria-selected="false">经济指标</a>
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
                                    <div class="tab-pane fade" id="tab_content_house_economicIndicators"
                                         role="tabpanel">
                                        <p>经济指标(无数据)</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--土地证信息--%>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;" data-id="landModel"
                         data-title="土地证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        其他信息
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
                                           href="#tab_content_land_declareBuildingPermit" role="tab"
                                           aria-selected="true">建设工程规划许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_land_declareLandUsePermitLand" role="tab"
                                           aria-selected="false">建设用地规划许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_land_declareBuildingConstructionPermitLand" role="tab"
                                           aria-selected="false">建筑工程施工许可证</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#tab_content_land_declarePreSalePermitLand" role="tab"
                                           aria-selected="false">商品房预售许可证</a>
                                    </li>
                                </ul>
                                <div class="tab-content mt-2 mb-3">
                                    <div class="tab-pane fade show active" id="tab_content_land_declareBuildingPermit"
                                         role="tabpanel">
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
                                                    <div id="_declareBuildingPermitFileId3"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane fade " id="tab_content_land_declareLandUsePermitLand"
                                         role="tabpanel">
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
                                                    <div id="_declareLandUsePermitFileId3"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade "
                                         id="tab_content_land_declareBuildingConstructionPermitLand" role="tabpanel">
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
                                                    <div id="_declareBuildingConstructionPermitFileId3"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane fade " id="tab_content_land_declarePreSalePermitLand"
                                         role="tabpanel">
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
                                                    <div id="_declarePreSalePermitFileId3"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--不动产证信息--%>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;"
                         data-id="RealtyRealModel" data-title="不动产证">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        其他信息
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
                                </ul>
                                <div class="tab-content mt-2 mb-3">
                                    <div class="tab-pane fade show active"
                                         id="tab_content_RealtyReal_declareBuildingPermit"
                                         role="tabpanel">
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
                                                    <div id="_declareBuildingPermitFileId2"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane fade " id="tab_content_RealtyReal_declareLandUsePermitLand"
                                         role="tabpanel">
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
                                                    <div id="_declareLandUsePermitFileId2"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade "
                                         id="tab_content_RealtyReal_declareBuildingConstructionPermitLand"
                                         role="tabpanel">
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
                                                    <div id="_declareBuildingConstructionPermitFileId2"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane fade " id="tab_content_RealtyReal_declarePreSalePermitLand"
                                         role="tabpanel">
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
                                                    <div id="_declarePreSalePermitFileId2"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--考核数据--%>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        考核数据
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                            <div class="form-inline x-valid">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <table class="table table-bordered" id="chksTableList">
                                                        <thead>
                                                        <tr>
                                                            <th width="3%">序号</th>
                                                            <th width="50%">考核标准</th>
                                                            <th width="10%">打分</th>
                                                            <th width="10%">说明</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="text-align: center;">
                        <button class="btn btn-default" type="button" onclick="window.close();">
                            关闭
                        </button>
                        <c:if test="${assessmentReadonly ne true}">
                            <button class="btn btn-primary" style="margin-left: 10px;" type="button" onclick="chksCustomer.saveAssessmentItem();">
                                保存
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- 申报各种类型的html视图 -->
        <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
        <%@include file="/views/method/module/economicIndicators.jsp" %>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<%@include file="/views/chks/assessmentCommon.jsp" %>
<script type="text/javascript">
    var chksCustomer = {};

    chksCustomer.config = {
        selector: "#chksCustomerAssessmentPlanDetailSelector"
    };

    chksCustomer.houseConfig = {
        fileId: declareCommon.config.house.fileId
    };

    chksCustomer.landConfig = {
        fileId: declareCommon.config.land.fileId
    };

    chksCustomer.declareRealtyRealEstateCertConfig = {
        newFileId: declareCommon.config.declareRealty.newFileId
    };

    chksCustomer.saveAssessmentItem = function () {
        var form = $("#chksTableList").closest("form");
        if (!form.valid()) {
            return false;
        }
        var filterData = [];
        var data = [];
        var remarks = form.find("textarea[name=remarks]").val();
        assessmentCommonHandle.getChksSonData(form, data);
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        if (filterData.length == 0) {
            notifyWarning("警告", "详情页考核需要填写全部数据!");
            return false;
        }
        var parentData = {
            id: '${assessmentPerformanceDto.id}',
            remarks: remarks,
            examineStatus: 'finish'
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(filterData),
            fomData: JSON.stringify(parentData),
            processInsId: '${assessmentPerformanceDto.processInsId}'
        }, function (data) {
            AlertSuccess("成功", "考核成功", function () {
                window.close();
            });
        });
    };

    chksCustomer.loadChksServerNew = function () {
        var target = $("#chksTableList").find("tbody");
        assessmentCommonHandle.getPerformanceDetailsByPerformanceId('${assessmentPerformanceDto.id}', function (performanceDetails) {
            assessmentCommonHandle.writeAssessmentItemHtml(target, {
                activityName: '${assessmentPerformanceDto.activityName}',
                id: '${assessmentPerformanceDto.id}',
                remarks: '${assessmentPerformanceDto.remarks}'
            }, performanceDetails,'${assessmentReadonly}');
        });
    };

    //权证信息初始化
    chksCustomer.init = function () {
        var selector = $(chksCustomer.config.selector);
        var targetId = '${targetObjectInfo.id}';
        var tableName = '${assessmentPerformanceDto.tableName}';
        //房产证
        if (tableName == AssessDBKey.DeclareRealtyHouseCert) {
            var landId = $("#tab_content_house_land").find("form");
            var economicIndicators = $("#tab_content_house_economicIndicators");
            selector.find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.house.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getHouseData(targetId, function (data) {
                    declareCommon.initHouse(data, selector.find("form"), [chksCustomer.houseConfig.fileId], null, false);
                    var attribute = {readonly: "readonly", 'class': 'form-control'};
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

        //土地证
        if (tableName == AssessDBKey.DeclareRealtyLandCert) {
            selector.find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.land.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getLandData(targetId, function (data) {
                    declareCommon.initLand(data, selector.find("form"), [chksCustomer.landConfig.fileId], null, false);
                    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
                        if (!centerData) {
                            return;
                        }
                        //建设工程规划许可证
                        if (centerData.buildingPermitId) {
                            declareCommon.getDeclareBuildingPermitById(centerData.buildingPermitId, function (data) {
                                var arr = ["declareBuildingPermitFileId3"];
                                var inputArr = ["date"];
                                var frm = $("#tab_content_land_declareBuildingPermit").find("form");
                                frm.append(commonDeclareApprovalModel.buildingPermit.getHtml());
                                declareCommon.showHtmlMastInit(frm, function (area) {
                                    declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingPermit, inputArr);
                                });
                            });
                        }

                        //建设用地规划许可证
                        if (centerData.landUsePermitId) {
                            declareCommon.getDeclareLandUsePermitById(centerData.landUsePermitId, function (data) {
                                var arr = ["declareLandUsePermitFileId3"];
                                var inputArr = ["date"];
                                var frm = $("#tab_content_land_declareLandUsePermitLand").find("form");
                                frm.append(commonDeclareApprovalModel.landUsePermit.getHtml());
                                declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareLandUsePermit, inputArr);
                            });
                        }

                        //建筑工程施工许可证
                        if (centerData.buildingConstructionPermitId) {
                            declareCommon.getDeclareBuildingConstructionPermitById(centerData.buildingConstructionPermitId, function (data) {
                                var arr = ["declareBuildingConstructionPermitFileId3"];
                                var inputArr = ["date", "contractPeriod"];
                                var frm = $("#tab_content_land_declareBuildingConstructionPermitLand").find("form");
                                frm.append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
                                declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
                            });
                        }

                        //商品房预售许可证
                        if (centerData.preSalePermitId) {
                            declareCommon.getDeclarePreSalePermitById(centerData.preSalePermitId, function (data) {
                                var arr = ["declarePreSalePermitFileId3"];
                                var inputArr = ["date"];
                                var frm = $("#tab_content_land_declarePreSalePermitLand").find("form");
                                frm.append(commonDeclareApprovalModel.preSalePermit.getHtml());
                                declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclarePreSalePermit, inputArr);
                            });
                        }
                    });
                });
            });

            $(document).find("[data-id=landModel]").show();
        }

        //不动产证
        if (tableName == AssessDBKey.DeclareRealtyRealEstateCert) {
            selector.find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.realEstateCert.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getDeclareRealtyData(targetId, function (data) {
                    declareCommon.initDeclareRealty(data, selector.find("form"), [chksCustomer.declareRealtyRealEstateCertConfig.newFileId], null, false);
                    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
                        if (!centerData) {
                            return;
                        }
                        //建设工程规划许可证
                        if (centerData.buildingPermitId) {
                            declareCommon.getDeclareBuildingPermitById(centerData.buildingPermitId, function (data) {
                                var arr = ["declareBuildingPermitFileId2"];
                                var inputArr = ["date"];
                                var frm = $("#tab_content_RealtyReal_declareBuildingPermit").find("form");
                                frm.append(commonDeclareApprovalModel.buildingPermit.getHtml());
                                declareCommon.showHtmlMastInit(frm, function (area) {
                                    declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingPermit, inputArr);
                                });
                            });
                        }

                        //建设用地规划许可证
                        if (centerData.landUsePermitId) {
                            declareCommon.getDeclareLandUsePermitById(centerData.landUsePermitId, function (data) {
                                var arr = ["declareLandUsePermitFileId2"];
                                var inputArr = ["date"];
                                var frm = $("#tab_content_RealtyReal_declareLandUsePermitLand").find("form");
                                frm.append(commonDeclareApprovalModel.landUsePermit.getHtml());
                                declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareLandUsePermit, inputArr);
                            });
                        }

                        //建筑工程施工许可证
                        if (centerData.buildingConstructionPermitId) {
                            declareCommon.getDeclareBuildingConstructionPermitById(centerData.buildingConstructionPermitId, function (data) {
                                var arr = ["declareBuildingConstructionPermitFileId2"];
                                var inputArr = ["date", "contractPeriod"];
                                var frm = $("#tab_content_RealtyReal_declareBuildingConstructionPermitLand").find("form");
                                frm.append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
                                declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
                            });
                        }

                        //商品房预售许可证
                        if (centerData.preSalePermitId) {
                            declareCommon.getDeclarePreSalePermitById(centerData.preSalePermitId, function (data) {
                                var arr = ["declarePreSalePermitFileId2"];
                                var inputArr = ["date"];
                                var frm = $("#tab_content_RealtyReal_declarePreSalePermitLand").find("form");
                                frm.append(commonDeclareApprovalModel.preSalePermit.getHtml());
                                declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclarePreSalePermit, inputArr);
                            });
                        }
                    });
                });
            });
            $(document).find("[data-id=RealtyRealModel]").show();
        }
    };
    $(document).ready(function () {
        chksCustomer.init();
        chksCustomer.loadChksServerNew();
    });
</script>
</html>

