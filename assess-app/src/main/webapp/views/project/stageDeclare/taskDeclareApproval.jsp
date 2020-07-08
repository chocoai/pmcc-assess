<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <!-- 申报各种类型的html视图 -->
                    <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">房产证</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered" id="tableDeclareRealtyHouseCert">
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">土地证</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered" id="tableDeclareRealtyLandCert">
                                </table>
                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">不动产证</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered"
                                       id="tableDeclareRealtyRealEstateCert">
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <form class="form-horizontal" id="declareApplyForm">
                                    <input type="hidden" name="id" value="${declare.id}">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估报告
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <div id="_assess_report_enclosure"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估面积
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <label class="form-control input-full">${declare.assessArea}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估金额
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <label class="form-control input-full">${declare.assessMoney}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估基准日
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <label class="form-control input-full"><fmt:formatDate
                                                            value='${declare.dateLimit}' pattern='yyyy-MM-dd'/></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次委托单位
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <label class="form-control input-full">${declare.client}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估机构
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <label class="form-control input-full">${declare.assessOrganization}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    备注
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <label class="form-control input-full">${declare.remark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <form class="form-horizontal">
                                    <c:forEach items="${declareApplyExtensionList}" var="itemData">
                                        <div class="row form-group">
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                        附件名称<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <label class="form-control input-full">${itemData.name}</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                        附件
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <div id="_other_Enclosure${itemData.id}"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <script>
                                                $(function () {
                                                    var fileId = 'other_Enclosure${itemData.id}';
                                                    declareCommon.showFile(fileId, AssessDBKey.DeclareApplyExtension, "${itemData.id}", true, fileId);
                                                });
                                            </script>
                                        </div>
                                    </c:forEach>
                                </form>

                            </div>
                        </div>
                    </div>


                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<%@include file="/views/method/module/economicIndicators.jsp" %>
<%@include file="/views/project/tool/declareApplyExtensionCumstomModelView.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript">
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
        declareEconomicIndicatorsHead: {
            frm: declareCommon.config.declareEconomicIndicatorsHead.frm,
            name: declareCommon.config.declareEconomicIndicatorsHead.name,
            box: declareCommon.config.declareEconomicIndicatorsHead.box
        },
        declareEconomicIndicatorsContent: {
            frm: declareCommon.config.declareEconomicIndicatorsContent.frm,
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
        declareEconomicIndicatorsHead: {
            frm: declareCommon.config.declareEconomicIndicatorsHead2.frm,
            name: declareCommon.config.declareEconomicIndicatorsHead2.name,
            box: declareCommon.config.declareEconomicIndicatorsHead2.box
        },
        declareEconomicIndicatorsContent: {
            frm: declareCommon.config.declareEconomicIndicatorsContent2.frm,
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
        houseBox: declareCommon.config.land.HouseCert.box,
        houseFile: declareCommon.config.land.houseFileId
    };

    //房产证显示
    declareApprovalFun.houseFindData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.houseConfig.box).find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        $('#' + declareApprovalFun.houseConfig.box).find(".card-body").prepend(commonDeclareApprovalModel.house.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.houseConfig.frm), function (area) {
            declareCommon.initHouse(item, $("#" + declareApprovalFun.houseConfig.frm), [declareApprovalFun.houseConfig.fileId], null, false);
            $('#' + declareApprovalFun.houseConfig.box).modal("show");
        });
    };

    //房产证关联的土地证
    declareApprovalFun.houseRelationLandData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
            return false;
        }
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landId)) {//关联情况
                declareCommon.getLandData(centerData.landId, function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        $('#' + declareApprovalFun.houseConfig.son.declareRealtyLandCert.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
                        $('#' + declareApprovalFun.houseConfig.son.declareRealtyLandCert.box).find(".card-body").prepend(commonDeclareApprovalModel.land.getHtml());
                        declareCommon.initLand(data, $("#" + declareApprovalFun.houseConfig.son.declareRealtyLandCert.frm), [declareCommon.config.house.landFileId], null, false);
                        $('#' + declareApprovalFun.houseConfig.son.declareRealtyLandCert.box).modal("show");
                    } else {
                        notifyWarning("警告", "关联的土地证数据已经被删除!");

                    }
                });
            } else {
                notifyWarning("警告", "无土地证数据!");

            }
        });
    };

    //房产证关联的经济指标
    declareApprovalFun.houseRelationDeclareEconomicIndicatorsData = function (id) {
        var item = $("#" + declareApprovalFun.houseConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
            return false;
        }
        var attribute = {readonly: "readonly", 'class': 'form-control'};
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                economicIndicators.init({
                    planDetailsId: declareCommon.getPlanDetailsId(),
                    economicId: centerData.indicatorId,
                    centerId: item.centerId,
                    attribute: attribute
                });
            } else {
                notifyWarning("警告", "经济指标无!");
            }
        });
    };



    //房产 table list
    declareApprovalFun.houseLoadList = function () {
        var cols = declareCommon.getHouseColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="declareApprovalFun.houseRelationLandData(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="土地证">';
                str += '土地证';
                str += '</button>';

                str += '<button type="button" onclick="declareApprovalFun.houseFindData(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="房产证">';
                str += '房产证';
                str += '</button>';
                str += '<button type="button" class="btn btn-xs btn-info tooltips" style="margin-left: 5px;" data-placement="bottom" data-original-title="不动产清单" onclick="declareCommon.loadDeclareRealtyCheckListTable('  +row.centerId +  ')" > <i class="fa "></i>不动产清单</button>';

                str += '<button type="button" onclick="declareApprovalFun.houseRelationDeclareEconomicIndicatorsData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">';
                str += '经济指标';
                str += '</button>';



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
        }, false, false);
    };


    //不动产
    declareApprovalFun.realtyRealEstatefindData = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).find(".card-body").prepend(commonDeclareApprovalModel.realEstateCert.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm), function (area) {
            declareCommon.initDeclareRealty(item, $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm), [declareApprovalFun.declareRealtyRealEstateCertConfig.newFileId], null, false);
            $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).modal("show");
        });
    };



    //不动产 经济指标
    declareApprovalFun.realtyRealDeclareEconomicIndicators = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
            return false;
        }
        var attribute = {readonly: "readonly", 'class': 'form-control'};
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                economicIndicators.init({
                    planDetailsId: declareCommon.getPlanDetailsId(),
                    economicId: centerData.indicatorId,
                    centerId: item.centerId,
                    attribute: attribute
                });
            } else {
                notifyWarning("警告", "经济指标无!");

            }
        });
    };


    //不动产
    declareApprovalFun.realEstateloadList = function () {
        var cols = declareCommon.getRealEstateColumn();
        cols.push({field: 'fileViewName', title: '不动产附件'});
        cols.push({
            field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="declareApprovalFun.realtyRealEstatefindData(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="不动产">';
                str += '不动产';
                str += '</button>';

                str += '<button type="button" onclick="declareApprovalFun.realtyRealDeclareEconomicIndicators(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">';
                str += '经济指标';
                str += '</button>';

                str += '<button type="button" class="btn btn-xs btn-info tooltips" style="margin-left: 5px;" data-placement="bottom" data-original-title="不动产清单" onclick="declareCommon.loadDeclareRealtyCheckListTable('  +row.centerId +  ')" > <i class="fa "></i>不动产清单</button>';


                str += '<div class="dropdown" style="display: inline;margin-left: 5px;">';
                str += "<button type='button' class='btn btn-info btn-xs dropdown-toggle'  style=\"margin-left: 5px;\" data-toggle='dropdown'>许可证信息</button>";
                str += "<div class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclareLandUsePermit(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclareBuildingPermit(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclareBuildingConstructionPermit(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclarePreSalePermit(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "</div>";
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

    //土地证
    declareApprovalFun.landFindData = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        $('#' + declareApprovalFun.landConfig.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
        $('#' + declareApprovalFun.landConfig.box).find(".card-body").prepend(commonDeclareApprovalModel.land.getHtml());
        declareCommon.showHtmlMastInit($("#" + declareApprovalFun.landConfig.frm), function (area) {
            declareCommon.initLand(item, $("#" + declareApprovalFun.landConfig.frm), [declareApprovalFun.landConfig.fileId], null,false);
            $('#' + declareApprovalFun.landConfig.box).modal("show");
        });
    };

    //土地 table list
    declareApprovalFun.landLoadList = function () {
        var cols = declareCommon.getLandColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="declareApprovalFun.LandModelHouseView(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="关联的房产证">';
                str += '房产证';
                str += '</button>';

                str += '<button type="button" onclick="declareApprovalFun.landFindData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="土地证">';
                str += '土地证';
                str += '</button>';

                str += '<button type="button" onclick="declareApprovalFun.LanddeclareEconomicIndicators(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">';
                str += '经济指标';
                str += '</button>';

                str += '<div class="dropdown" style="display: inline;margin-left: 5px;">';
                str += "<button type='button' class='btn btn-info btn-xs dropdown-toggle'  style=\"margin-left: 5px;\" data-toggle='dropdown'>许可证信息</button>";
                str += "<div class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclareLandUsePermit(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclareBuildingPermit(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclareBuildingConstructionPermit(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "<a  class='dropdown-item' onclick='declareCommon.loadTableDeclarePreSalePermit(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-navicon'></i></a>";
                str += "</div>";
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

    //土地证 经济指标
    declareApprovalFun.LanddeclareEconomicIndicators = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
            return false;
        }
        var attribute = {readonly: "readonly", 'class': 'form-control'};
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                economicIndicators.init({
                    planDetailsId: declareCommon.getPlanDetailsId(),
                    economicId: centerData.indicatorId,
                    centerId: item.centerId,
                    attribute: attribute
                });
            } else {
                notifyWarning("警告", "经济指标无!");

            }
        });
    };


    //土地证 房产证
    declareApprovalFun.LandModelHouseView = function (id) {
        var item = $("#" + declareApprovalFun.landConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
            return false;
        }
        var box = $("#" + declareApprovalFun.landConfig.houseBox);
        var frm = box.find("form");
        box.find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        box.find(".card-body").prepend(commonDeclareApprovalModel.house.getHtml());
        var fileArr = [declareApprovalFun.landConfig.houseFile];
        declareCommon.showHtmlMastInit(frm, function (area) {
            declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
                if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
                    declareCommon.getHouseData(centerData.houseId, function (data) {
                        declareCommon.initHouse(data, frm, fileArr, null, false);
                        box.modal("show");
                    });
                }
            });
        });
    };

    $(function () {
        declareApprovalFun.realEstateloadList();
        declareApprovalFun.houseLoadList();
        declareApprovalFun.landLoadList();
        var fileArr = [AssessUploadKey.PROJECT_PROXY, AssessUploadKey.ASSESS_REPORT_Enclosure];
        $.each(fileArr, function (i, n) {
            declareCommon.showFile(n, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", false, n);
        });
    });


</script>
<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }
</script>

<!--商品房预售许可证-->
<div id="divDeclarePreSalePermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    商品房预售许可证 列表
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" name="masterId">
                </form>
                <table class="table table-bordered" id="tbDeclarePreSalePermitList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
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

<!--建筑工程施工许可证-->
<div id="divDeclareBuildingConstructionPermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    建筑工程施工许可证 列表
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" name="masterId">
                </form>
                <table class="table table-bordered" id="tbDeclareBuildingConstructionPermitList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
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

<!--建设工程规划许可证-->
<div id="divDeclareBuildingPermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    建设工程规划许可证 列表
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" name="masterId">
                </form>
                <table class="table table-bordered" id="tbDeclareBuildingPermitList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
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
<!--建设用地规划许可证-->
<div id="divDeclareLandUsePermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    建设用地规划许可证 列表
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" name="masterId">
                </form>
                <table class="table table-bordered" id="tbDeclareLandUsePermitList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
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



<!--不动产清单-->
<div id="divDataDeclareRealtyCheckList" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    不动产清单 list
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" name="marsterId">
                    <input type="hidden" name="tableId">
                </form>
                <table class="table table-bordered" id="tbDataDeclareRealtyCheckListList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
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

<!--  房产证模块 房产证信息 -->
<div id="boxDeclareRealtyHouseCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房产证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="frmDeclareRealtyHouseCert" class="form-horizontal">
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
                                                <div id="_declareRealtyHouseCertFileId"></div>
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

<!--  房产证模块  土地证 -->
<div id="boxSonDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">关联土地证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frmSonDeclareRealtyLandCert">
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
                                                <div id="_declareRealtyHouseCert_land_FileId"></div>
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
<!-- 不动产  -->
<div id="boxDeclareRealtyRealEstateCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">不动产证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frmDeclareRealtyRealEstateCert">
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
                                                <div id="_declareRealtyRealEstateCertNewFileId"></div>
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



<!--  土地证模块  土地证信息-->
<div id="boxDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="frmDeclareRealtyLandCert" class="form-horizontal">
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
                                                <div id="_declareRealtyLandCertFileId"></div>
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

<!-- 土地证模块 关联房产证信息 -->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">关联房产证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                房产证附件
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <div id="_declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"></div>
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
</html>

