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
                                                    估价委托书
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <div id="_project_proxy"></div>
                                                </div>
                                            </div>
                                        </div>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript">
    var declareApprovalFun = {};


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


    //不动产
    declareApprovalFun.realtyRealEstatefindData = function (id) {
        var item = $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        var box = $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box);
        AssessCommon.getDataDicInfo(item.landCertGetQuestion, function (dicData) {
            box.find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId3).remove();
            box.find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId2).remove();
            if (dicData.fieldName == AssessDicKey.projectDeclareCertificate_YES) {
                box.find(".card-body").prepend(commonDeclareApprovalModel.realEstateCert.getHtml3());
            }
            if (dicData.fieldName == AssessDicKey.projectDeclareCertificate_NO) {
                box.find(".card-body").prepend(commonDeclareApprovalModel.realEstateCert.getHtml2());
            }
            box.find("label[name='unit']").closest(".form-group").hide();
            box.find("label[name='attachedNumber']").closest(".form-group").hide();
            box.find("label[name='buildingNumber']").closest(".form-group").hide();
            box.find("label[name='streetNumber']").closest(".form-group").hide();
            box.find("label[name='floor']").closest(".form-group").hide();
            box.find("label[name='roomNumber']").closest(".form-group").hide();
            declareCommon.showHtmlMastInit($("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm), function (area) {
                declareCommon.initDeclareRealty(item, $("#" + declareApprovalFun.declareRealtyRealEstateCertConfig.frm), [declareApprovalFun.declareRealtyRealEstateCertConfig.newFileId], null, false);
                $('#' + declareApprovalFun.declareRealtyRealEstateCertConfig.box).modal("show");
            });
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
        var box = $('#' + declareApprovalFun.landConfig.box);
        AssessCommon.getDataDicInfo(item.landCertGetQuestion, function (dicData) {
            box.find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
            box.find("#" + commonDeclareApprovalModel.config.land.handleId1).remove();
            if (dicData.fieldName == AssessDicKey.projectDeclareCertificate_YES) {
                box.find(".card-body").prepend(commonDeclareApprovalModel.land.getHtml());
            }
            if (dicData.fieldName == AssessDicKey.projectDeclareCertificate_NO) {
                box.find(".card-body").prepend(commonDeclareApprovalModel.land.getHtml2());
            }
            box.find("label[name='unit']").closest(".form-group").hide();
            box.find("label[name='attachedNumber']").closest(".form-group").hide();
            box.find("label[name='buildingNumber']").closest(".form-group").hide();
            box.find("label[name='streetNumber']").closest(".form-group").hide();
            box.find("label[name='floor']").closest(".form-group").hide();
            box.find("label[name='roomNumber']").closest(".form-group").hide();
            declareCommon.showHtmlMastInit($("#" + declareApprovalFun.landConfig.frm), function (area) {
                declareCommon.initLand(item, $("#" + declareApprovalFun.landConfig.frm), [declareApprovalFun.landConfig.fileId], null, false);
                $('#' + declareApprovalFun.landConfig.box).modal("show");
            });
        });

    };

    //土地 table list
    declareApprovalFun.landLoadList = function () {
        var cols = declareCommon.getLandColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
                var str = "";

                str += '<button type="button" onclick="declareApprovalFun.LandModelHouseView(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="关联的房产证">';
                str += '房产证';
                str += '</button>';
                str += '<button type="button" onclick="declareApprovalFun.landFindData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="土地证">';
                str += '土地证';
                str += '</button>';

                str += '<button type="button" onclick="declareApprovalFun.LanddeclareEconomicIndicators(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">';
                str += '经济指标';
                str += '</button>';


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
                } else {
                    notifyInfo("没有目标数据", "没有关联房产证!");
                }
            });
        });
    };

    $(function () {
        declareApprovalFun.realEstateloadList();
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

