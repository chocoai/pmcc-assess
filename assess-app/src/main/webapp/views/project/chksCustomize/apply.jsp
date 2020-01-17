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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
            <script type="text/javascript"
                    src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
            <script type="text/javascript"
                    src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
            <%@include file="/views/method/module/economicIndicators.jsp" %>
            <div class="x_panel area_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>${chksCustomerAssessmentPlanDetail.typeName}</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <div id="chksCustomerAssessmentPlanDetailSelector">

                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <form class="form-horizontal">
                            </form>
                        </div>
                    </div>

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2><i class="fa fa-bars"></i> ${chksCustomerAssessmentPlanDetail.typeName}
                                    <small>子数据</small>
                                </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content" style="display: none;" data-id="houseModel" data-title="房产证">
                                <div role="tabpanel" data-example-id="togglable-tabs">

                                    <ul class="nav nav-tabs bar_tabs" role="tablist">
                                        <li role="presentation" class="active"><a href="#tab_content_house_land"
                                                                                  role="tab" data-toggle="tab"
                                                                                  aria-expanded="true">土地证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_house_economicIndicators" role="tab"
                                                data-toggle="tab" aria-expanded="false">经济指标(暂不提供)</a>
                                        </li>
                                    </ul>

                                    <div class="tab-content">

                                        <div role="tabpanel" class="tab-pane fade active in"
                                             id="tab_content_house_land">
                                            <div class="row">

                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareRealtyHouseCert_land_FileId"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_house_economicIndicators">
                                            <p>经济指标(无数据)</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="x_content" style="display: none;" data-id="landModel" data-title="土地证">
                                <div role="tabpanel" data-example-id="togglable-tabs">

                                    <ul class="nav nav-tabs bar_tabs" role="tablist">
                                        <li role="presentation" class="active"><a
                                                href="#tab_content_land_declareBuildingPermit"
                                                role="tab" data-toggle="tab"
                                                aria-expanded="true">建设工程规划许可证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_land_declareLandUsePermitLand" role="tab"
                                                data-toggle="tab" aria-expanded="false">建设用地规划许可证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_land_declareBuildingConstructionPermitLand"
                                                role="tab"
                                                data-toggle="tab" aria-expanded="false">建筑工程施工许可证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_land_declarePreSalePermitLand" role="tab"
                                                data-toggle="tab" aria-expanded="false">商品房预售许可证</a>
                                        </li>
                                    </ul>

                                    <div class="tab-content">

                                        <div role="tabpanel" class="tab-pane fade active in"
                                             id="tab_content_land_declareBuildingPermit">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareBuildingPermitFileId3"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_land_declareLandUsePermitLand">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareLandUsePermitFileId3"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_land_declareBuildingConstructionPermitLand">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareBuildingConstructionPermitFileId3"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_land_declarePreSalePermitLand">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
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

                            <div class="x_content" style="display: none;" data-id="RealtyRealModel" data-title="不动产证">
                                <div role="tabpanel" data-example-id="togglable-tabs">

                                    <ul class="nav nav-tabs bar_tabs" role="tablist">
                                        <li role="presentation" class="active"><a
                                                href="#tab_content_RealtyReal_declareBuildingPermit"
                                                role="tab" data-toggle="tab"
                                                aria-expanded="true">建设工程规划许可证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_RealtyReal_declareLandUsePermitLand" role="tab"
                                                data-toggle="tab" aria-expanded="false">建设用地规划许可证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_RealtyReal_declareBuildingConstructionPermitLand"
                                                role="tab"
                                                data-toggle="tab" aria-expanded="false">建筑工程施工许可证</a>
                                        </li>
                                        <li role="presentation" class=""><a
                                                href="#tab_content_RealtyReal_declarePreSalePermitLand" role="tab"
                                                data-toggle="tab" aria-expanded="false">商品房预售许可证</a>
                                        </li>
                                    </ul>

                                    <div class="tab-content">

                                        <div role="tabpanel" class="tab-pane fade active in"
                                             id="tab_content_RealtyReal_declareBuildingPermit">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareBuildingPermitFileId2"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_RealtyReal_declareLandUsePermitLand">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareLandUsePermitFileId2"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_RealtyReal_declareBuildingConstructionPermitLand">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
                                                        </label>
                                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                                            <div id="_declareBuildingConstructionPermitFileId2"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div role="tabpanel" class="tab-pane fade"
                                             id="tab_content_RealtyReal_declarePreSalePermitLand">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <form class="form-horizontal">
                                                    </form>
                                                </div>
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <div class="row">
                                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                            附件<span class="symbol required"></span>
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

                        </div>
                    </div>
                </div>

            </div>

            <div class="x_panel">
                <div class="x_content">

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form>
                            <table class="table" id="chksTableList">
                                <thead>
                                <tr>
                                    <th width="3%">序号</th>
                                    <th width="7%">节点名称</th>
                                    <th width="50%">考核标准</th>
                                    <th width="10%">打分</th>
                                    <th width="10%">说明</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </form>
                    </div>



                    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                        <button class="btn btn-success" onclick="chksCustomer.saveAssessmentItem();">
                            保存考核记录
                        </button>
                    </div>

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <table class="table" id="boxReActivityDtoTableView">
                        </table>
                    </div>
                </div>
            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                        <button class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/share/chksCommon.jsp" %>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script>

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
        var target = $("#chksTableList").find("tbody");
        if (!vaildChksData(target)) {
            return false;
        }
        var filterData = [];
        var data = [];
        var remarks = target.find("textarea[name=remarks]").val();
        assessmentCommonHandle.getChksSonData(target, data);
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        if (filterData.length == 0) {
            toastr.warning("详情页考核需要填写全部数据!");
            return false;
        }
        var parentData = {
            spotActivityId: '0',
            projectId: '${chksCustomerAssessmentPlanDetail.projectId}',
            processInsId: '${chksCustomerAssessmentPlanDetail.processInsId}',
            boxId: '${boxReDto.id}',
            activityId: '${chksCustomerAssessmentPlanDetail.activityId}',
            activityName: '${chksCustomerAssessmentPlanDetail.activityName}',
            remarks: remarks,
            tableId: '${chksCustomerAssessmentPlanDetail.id}'
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(filterData),
            fomData: JSON.stringify(parentData)
        }, function (data) {
            toastr.warning("考核成功!");
            chksCustomer.loadChksServerViewTable();
            chksCustomer.loadChksServerNew();
        });
    };

    chksCustomer.loadChksServerNew = function () {
        var target = $("#chksTableList").find("tbody");
        var option = {
            boxId: '${boxReDto.id}',
            activityId: '${chksCustomerAssessmentPlanDetail.activityId}',
            projectId: '${chksCustomerAssessmentPlanDetail.projectId}',
            processInsId: '${chksCustomerAssessmentPlanDetail.processInsId}',
            tableId: '${chksCustomerAssessmentPlanDetail.id}',//必须要
            isEffective: true //数据必须有效
        };
        assessmentCommonHandle.loadChksServerBase(option, {
            boxId: option.boxId,
            boxReActivitiId: option.activityId
        }, target);
    };

    chksCustomer.loadChksServerViewTable = function () {
        var target = $("#boxReActivityDtoTableView");
        var options = {
            boxId: '${boxReDto.id}',
            projectId: '${chksCustomerAssessmentPlanDetail.projectId}',
            processInsId: '${chksCustomerAssessmentPlanDetail.processInsId}',
            spotActivityId: 0,
            isEffective: true
        };
        if ('${activityDtoList}') {
            var activityIds = [];
            var data = null;
            try {
                data = JSON.parse('${el:toJsonString(activityDtoList)}');
            } catch (e) {
                console.log(e);
            }
            if (data) {
                $.each(data, function (i, item) {
                    activityIds.push(item.id);
                });
            }
            if (activityIds.length != 0) {
                options.activityIdList = activityIds.join(",");
            }
        }
        assessmentCommonHandle.loadChksServerViewBaseTable(target, options);
    };

    chksCustomer.init = function () {
        var selector = $(chksCustomer.config.selector);
        var targetId = '${targetObjectInfo.id}';
        var tableName = '${chksCustomerAssessmentPlanDetail.tableName}';
        var parent = selector.closest(".x_content");
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
            parent.find("[data-id=houseModel]").show();
        }


        if (tableName == AssessDBKey.DeclareRealtyLandCert) {
            selector.find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.land.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getLandData(targetId, function (data) {

                    declareCommon.initLand(data, selector.find("form"), [chksCustomer.landConfig.fileId], null, false);

                    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {

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

            parent.find("[data-id=landModel]").show();
        }

        if (tableName == AssessDBKey.DeclareRealtyRealEstateCert) {
            selector.find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
            selector.find("form").append(commonDeclareApprovalModel.realEstateCert.getHtml());
            declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
                declareCommon.getDeclareRealtyData(targetId, function (data) {

                    declareCommon.initDeclareRealty(data, selector.find("form"), [chksCustomer.declareRealtyRealEstateCertConfig.newFileId], null, false);

                    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {

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



            parent.find("[data-id=RealtyRealModel]").show();
        }

    };

    $(document).ready(function () {

        chksCustomer.init();

        chksCustomer.loadChksServerViewTable();

        chksCustomer.loadChksServerNew();
    });

</script>

<script type="application/javascript">


</script>

</html>

