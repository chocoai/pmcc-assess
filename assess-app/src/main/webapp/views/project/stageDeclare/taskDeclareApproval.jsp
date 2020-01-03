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
                    src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
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
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1   control-label">
                                    估价委托书
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估报告
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                    <div id="_assess_report_enclosure"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估面积
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${declare.assessArea}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估金额
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${declare.assessMoney}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">上次评估基准日</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"><fmt:formatDate value='${declare.dateLimit}'
                                                                                pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">上次委托单位</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${declare.client}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">上次评估机构</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${declare.assessOrganization}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${declare.remark}</label>
                                </div>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal">
                        <c:forEach items="${declareApplyExtensionList}" var="itemData">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        自定义名称<span class="symbol required"></span>
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <label class="form-control">${itemData.name}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        附件
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                        <div id="_other_Enclosure${itemData.id}"></div>
                                    </div>
                                </div>
                                <script>
                                    $(function () {
                                        (function (fileId) {
                                            declareCommon.showFile(fileId, AssessDBKey.DeclareApplyExtension, "${itemData.id}", false, fileId);
                                        }('other_Enclosure${itemData.id}')) ;
                                    });
                                </script>
                            </div>
                        </c:forEach>
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
<%@include file="/views/method/module/economicIndicators.jsp" %>
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
        newFileId: declareCommon.config.land.newFileId
    };

    //房产证显示
    declareApprovalFun.houseFindData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.houseConfig.box).find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        $('#' + declareApprovalFun.houseConfig.box).find(".panel-body").append(commonDeclareApprovalModel.house.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.houseConfig.frm), function (area) {
            declareCommon.initHouse(item, $("#" + declareApprovalFun.houseConfig.frm), [declareApprovalFun.houseConfig.fileId], null,false);
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
                        declareCommon.initLand(data, $("#" + declareApprovalFun.houseConfig.son.declareRealtyLandCert.frm), [declareCommon.config.land.fileId], null,false);
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
        var attribute = {readonly:"readonly",'class':'form-control'} ;
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                economicIndicators.init({
                    planDetailsId: declareCommon.getPlanDetailsId(),
                    economicId: centerData.indicatorId,
                    centerId:item.centerId,
                    attribute:attribute
                });
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


    //不动产
    declareApprovalFun.realtyRealEstatefindData = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find(".panel-body").append(commonDeclareApprovalModel.realEstateCert.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm),function (area) {
            declareCommon.initDeclareRealty(item,$("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm),[declareApprovalFun.declareRealtyRealEstateCertConfig.fileId],null,false);
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
        var attribute = {readonly:"readonly",'class':'form-control'} ;
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                economicIndicators.init({
                    planDetailsId: declareCommon.getPlanDetailsId(),
                    economicId: centerData.indicatorId,
                    centerId:item.centerId,
                    attribute:attribute
                });
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
        cols.push({
            field: 'creator', title: '许可证信息', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>" + "<i class='fa fa-users'>" + "</i>" + "许可证信息" + "<span class='caret'>" + "</span>" + "</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.realtyRealDeclaredeclareBuildingPermitView(" + row.id + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.realtyRealDeclaredeclareLandUsePermitView(" + row.id + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.realtyRealDeclaredeclareBuildingConstructionPermitView(" + row.id + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.realtyRealDeclaredeclarePreSalePermitView(" + row.id + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";


                str += "</ul>";
                str += "</div>";
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
    //不动产 建设工程规划许可证
    declareApprovalFun.realtyRealDeclaredeclareBuildingPermitView = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var box = $("#declareBuildingPermitRealtyRealBox");
        var frm = box.find("form");
        var arr = ["declareBuildingPermitFileId2"] ;
        var inputArr = ["date"] ;
        box.find("#" + commonDeclareApprovalModel.config.buildingPermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.buildingPermit.getHtml());
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.buildingPermitId) {
                    declareCommon.getDeclareBuildingPermitById(centerData.buildingPermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingPermit,inputArr);
                    });
                }
            });
        });
    };
    //不动产 建设用地规划许可证
    declareApprovalFun.realtyRealDeclaredeclareLandUsePermitView = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var box = $("#declareLandUsePermitRealtyRealBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.landUsePermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.landUsePermit.getHtml());
        var arr = ["declareLandUsePermitFileId2"] ;
        var inputArr = ["date"] ;
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.landUsePermitId) {
                    declareCommon.getDeclareLandUsePermitById(centerData.landUsePermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareLandUsePermit,inputArr);
                    });
                }
            });
        });

    };
    //不动产 建筑工程施工许可证
    declareApprovalFun.realtyRealDeclaredeclareBuildingConstructionPermitView = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var arr = ["declareBuildingConstructionPermitFileId2"];
        var inputArr = ["date","contractPeriod"] ;
        var box = $("#declareBuildingConstructionPermitRealtyRealBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.buildingConstructionPermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.buildingConstructionPermitId) {
                    declareCommon.getDeclareBuildingConstructionPermitById(centerData.buildingConstructionPermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingConstructionPermit,inputArr);
                    });
                }
            });
        });
    };

    //不动产 商品房预售许可证
    declareApprovalFun.realtyRealDeclaredeclarePreSalePermitView = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var arr = ["declarePreSalePermitFileId2"];
        var inputArr = ["date"] ;
        var box = $("#declarePreSalePermitRealtyRealBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.preSalePermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.preSalePermit.getHtml());
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.preSalePermitId) {
                    declareCommon.getDeclarePreSalePermitById(centerData.preSalePermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclarePreSalePermit,inputArr);
                    });
                }
            });
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

    //土地 table list
    declareApprovalFun.landLoadList = function () {
        var cols = declareCommon.getLandColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="土地证查看详情" onclick="declareApprovalFun.landFindData(' + row.id + ',\'tb_List\')">土地证<i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        cols.push({
            field: 'creator', title: '许可证信息', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>" + "<i class='fa fa-users'>" + "</i>" + "许可证信息" + "<span class='caret'>" + "</span>" + "</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.LanddeclareBuildingPermitView(" + row.id + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.LanddeclareLandUsePermitView(" + row.id + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.LanddeclareBuildingConstructionPermitView(" + row.id + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareApprovalFun.LanddeclarePreSalePermitView(" + row.id + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";


                str += "</ul>";
                str += "</div>";
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
    //土地证 建设工程规划许可证
    declareApprovalFun.LanddeclareBuildingPermitView = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var box = $("#declareBuildingPermitLandBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.buildingPermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.buildingPermit.getHtml());
        var arr = ["declareBuildingPermitFileId3"] ;
        var inputArr = ["date"] ;
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.buildingPermitId) {
                    declareCommon.getDeclareBuildingPermitById(centerData.buildingPermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingPermit,inputArr);
                    });
                }
            });
        });
    };
    //土地证 建设用地规划许可证
    declareApprovalFun.LanddeclareLandUsePermitView = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var box = $("#declareLandUsePermitLandBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.landUsePermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.landUsePermit.getHtml());
        var arr = ["declareLandUsePermitFileId3"] ;
        var inputArr = ["date"] ;
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.landUsePermitId) {
                    declareCommon.getDeclareLandUsePermitById(centerData.landUsePermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareLandUsePermit,inputArr);
                    });
                }
            });
        });
    };
    //土地证 建筑工程施工许可证
    declareApprovalFun.LanddeclareBuildingConstructionPermitView = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var arr = ["declareBuildingConstructionPermitFileId3"];
        var inputArr = ["date","contractPeriod"] ;
        var box = $("#declareBuildingConstructionPermitLandBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.buildingConstructionPermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.buildingConstructionPermitId) {
                    declareCommon.getDeclareBuildingConstructionPermitById(centerData.buildingConstructionPermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingConstructionPermit,inputArr);
                    });
                }
            });
        });
    };
    //土地证 商品房预售许可证
    declareApprovalFun.LanddeclarePreSalePermitView = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            toastr.success('不合符调整后的数据约定,请联系管理员!');
            return false;
        }
        var arr = ["declarePreSalePermitFileId3"];
        var inputArr = ["date"] ;
        var box = $("#declarePreSalePermitLandBox");
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.preSalePermit.handleId).remove();
        box.find(".panel-body").append(commonDeclareApprovalModel.preSalePermit.getHtml());
        declareCommon.showHtmlMastInit(frm, function (area) {
            box.modal("show");
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (centerData.preSalePermitId) {
                    declareCommon.getDeclarePreSalePermitById(centerData.preSalePermitId, function (data) {
                        declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclarePreSalePermit,inputArr);
                    });
                }
            });
        });
    };


    $(function () {
        declareApprovalFun.realEstateloadList();
        declareApprovalFun.houseLoadList();
        declareApprovalFun.landLoadList() ;
        var fileArr = [AssessUploadKey.PROJECT_PROXY  ,AssessUploadKey.ASSESS_REPORT_Enclosure ] ;
        $.each(fileArr,function (i,n) {
            declareCommon.showFile(n, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", false, n);
        });
    });



</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>



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

<!-- 不动产 建设工程规划许可证 -->
<div id="declareBuildingPermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设工程规划许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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

<!-- 不动产 建设用地规划许可证 -->
<div id="declareLandUsePermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设用地规划许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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

<!-- 不动产 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑工程施工许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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

<!-- 不动产 商品房预售许可证 -->
<div id="declarePreSalePermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">商品房预售许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-5">
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


<!-- 土地证模块 建设工程规划许可证 -->
<div id="declareBuildingPermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设工程规划许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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

<!-- 土地证模块 建设用地规划许可证 -->
<div id="declareLandUsePermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设用地规划许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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

<!-- 土地证模块 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑工程施工许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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

<!-- 土地证模块 商品房预售许可证 -->
<div id="declarePreSalePermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">商品房预售许可证</h3>
            </div>
            <form  class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
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
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>


</html>

