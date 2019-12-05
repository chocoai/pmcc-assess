<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>

<%@include file="/views/project/stageDeclare/buildingDeclareModel/viewEconomicIndicatorsDetail.jsp" %>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>

            <!-- 土建 -->
            <div class="x_panel" id="viewCivilEngineering">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            在建工程（土建）
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <table class="table table-bordered" id="civilEngineeringTableList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 设备安装 -->
            <div class="x_panel" id="viewEquipmentInstallation">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            在建工程（设备安装）
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <table class="table table-bordered" id="equipmentInstallationTableList">
                                    <!-- cerare document add ajax data-->
                                </table>
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
<script>
    /**
     * Created by kings on 2018-12-20.
     */



    var equipmentInstallation = {};

    equipmentInstallation.config = {
        name: "设备安装",
        frm: "equipmentInstallationFrm",
        table: "equipmentInstallationTableList",
        box: "equipmentInstallationBox",
        fileId: "equipmentInstallationFileId",
        copy: "equipmentInstallationInputGroup",
        excelUpload: "equipmentInstallationUpload",
        declareBuildingConstructionPermit: {
            box: "declareBuildingConstructionPermitBoxE",
            frm: "declareBuildingConstructionPermitFrmE",
            fileId: "declareBuildingConstructionPermitFileIdE",
            name: "建筑工程施工许可证"
        },
        declarePreSalePermit: {
            box: "declarePreSalePermitBoxE",
            frm: "declarePreSalePermitFrmE",
            fileId: "declarePreSalePermitFileIdE",
            name: "商品房预售许可证"
        },
        declareLandUsePermit: {
            box: "declareLandUsePermitBoxE",
            frm: "declareLandUsePermitFrmE",
            fileId: "declareLandUsePermitFileIdE",
            name: "建设用地规划许可证"
        },
        declareBuildingPermit: {
            box: "declareBuildingPermitBoxE",
            frm: "declareBuildingPermitFrmE",
            fileId: "declareBuildingPermitFileIdE",
            name: "建设工程规划许可证"
        },
        declareRealtyLandCert: {
            box: "declareRealtyLandCertBoxE",
            frm: "declareRealtyLandCertFrmE",
            fileId: "declareRealtyLandCertFileIdE",
            name: "土地证"
        },
        declareRealtyRealEstateCert: {
            box: "declareRealtyRealEstateCertBoxE",
            frm: "declareRealtyRealEstateCertFrmE",
            fileId: "declareRealtyRealEstateCertFileIdE",
            name: "不动产"
        },
        declareEconomicIndicators: {
            box: "declareEconomicIndicatorsBox",
            frm: "declareEconomicIndicatorsFrm",
            fileId: "declareEconomicIndicatorsFileId",
            name: "经济指标"
        }
    };


    equipmentInstallation.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    equipmentInstallation.objectWriteSelectData = function (frm, data, name) {
        if (equipmentInstallation.isNotBlank(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    equipmentInstallation.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id
            },
            deleteFlag: false
        });
    };

    /**
     * 设备安装 初始化并赋值
     * @param item
     */
    equipmentInstallation.init = function (item) {
        $("#" + equipmentInstallation.config.frm).clearAll();
        $("#" + equipmentInstallation.config.frm).initForm(item);
        $("#" + equipmentInstallation.config.frm).find("input[name='startDate']").val(formatDate(item.startDate));
        $("#" + equipmentInstallation.config.frm).find("input[name='expectedCompletionDate']").val(formatDate(item.expectedCompletionDate));
        $("#" + equipmentInstallation.config.frm).find("input[name='declarationDate']").val(formatDate(item.declarationDate));
        equipmentInstallation.showFile(equipmentInstallation.config.fileId, AssessDBKey.DeclareBuildEquipmentInstall, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）显示
     * @date:2018-09-27
     **/
    equipmentInstallation.showAddModel = function () {
        $('#' + equipmentInstallation.config.box).find("#" + commonDeclareApprovalModel.config.equipmentInstallation.handleId).remove();
        $('#' + equipmentInstallation.config.box).find(".panel-body").append(commonDeclareApprovalModel.equipmentInstallation.getHtml());

        $('#' + equipmentInstallation.config.box).modal("show");
        equipmentInstallation.init({});
    };


    equipmentInstallation.getData = function (id, callback) {
        $.ajax({
            url: getContextPath() + "/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）查看
     * @date:2018-09-27
     **/
    equipmentInstallation.findData = function (id) {
        equipmentInstallation.showAddModel();
        equipmentInstallation.getData(id, function (data) {
            equipmentInstallation.init(data);
        });
        $('#' + equipmentInstallation.config.box).modal("show");
    };


    /**
     * @author:  zch
     * 描述:建设工程规划许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingPermitView = function (id) {
        $('#' + equipmentInstallation.config.declareBuildingPermit.box).find("#" + commonDeclareApprovalModel.config.buildingPermit.handleId).remove();
        $('#' + equipmentInstallation.config.declareBuildingPermit.box).find(".panel-body").append(commonDeclareApprovalModel.buildingPermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var buildingPermitId = result.data.buildingPermitId;
                    if (equipmentInstallation.isNotBlank(buildingPermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declareBuildingPermit/getDeclareBuildingPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallation.config.declareBuildingPermit.frm).initForm(data);
                                        $("#" + equipmentInstallation.config.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallation.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                        $('#' + equipmentInstallation.config.declareBuildingPermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    /**
     * @author:  zch
     * 描述:建设用地规划许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareLandUsePermitView = function (id) {
        $('#' + equipmentInstallation.config.declareLandUsePermit.box).find("#" + commonDeclareApprovalModel.config.landUsePermit.handleId).remove();
        $('#' + equipmentInstallation.config.declareLandUsePermit.box).find(".panel-body").append(commonDeclareApprovalModel.landUsePermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var landUsePermitId = result.data.landUsePermitId;
                    if (equipmentInstallation.isNotBlank(landUsePermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declareLandUsePermit/getDeclareLandUsePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: landUsePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallation.config.declareLandUsePermit.frm).initForm(data);
                                        $("#" + equipmentInstallation.config.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallation.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                        $('#' + equipmentInstallation.config.declareLandUsePermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };


    /**
     * @author:  zch
     * 描述:商品房预售许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declarePreSalePermitView = function (id) {
        $('#' + equipmentInstallation.config.declarePreSalePermit.box).find("#" + commonDeclareApprovalModel.config.preSalePermit.handleId).remove();
        $('#' + equipmentInstallation.config.declarePreSalePermit.box).find(".panel-body").append(commonDeclareApprovalModel.preSalePermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var preSalePermitId = result.data.preSalePermitId;
                    if (equipmentInstallation.isNotBlank(preSalePermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declarePreSalePermit/getDeclarePreSalePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: preSalePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallation.config.declarePreSalePermit.frm).initForm(data);
                                        $("#" + equipmentInstallation.config.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallation.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                        $('#' + equipmentInstallation.config.declarePreSalePermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };


    /**
     * @author:  zch
     * 描述:建筑工程施工许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingConstructionPermitView = function (id) {
        $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).find("#" + commonDeclareApprovalModel.config.buildingConstructionPermit.handleId).remove();
        $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).find(".panel-body").append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var buildingConstructionPermitId = result.data.buildingConstructionPermitId;
                    if (equipmentInstallation.isNotBlank(buildingConstructionPermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingConstructionPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallation.config.declareBuildingConstructionPermit.frm).initForm(data);
                                        $("#" + equipmentInstallation.config.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                                        $("#" + equipmentInstallation.config.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallation.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                        $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + equipmentInstallation.config.declareBuildingConstructionPermit.frm).initForm({pid: id});
                        equipmentInstallation.showFile(equipmentInstallation.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };


    /**
     * @author:  zch
     * 描述:土地证 视图
     * @date:2018-09-28
     **/
    equipmentInstallation.declareRealtyLandCertView = function (id) {
        $('#' + equipmentInstallation.config.declareRealtyLandCert.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
        $('#' + equipmentInstallation.config.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var landId = result.data.landId;
                    if (equipmentInstallation.isNotBlank(landId)) {
                        $.ajax({
                            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: landId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isNotBlank(data)) {
                                        $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).initForm(data);
                                        $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find("input[name='terminationDate']").val(formatDate(data.terminationDate));
                                        $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find("input[name='registrationDate']").val(formatDate(data.registrationDate));
                                        equipmentInstallation.showFile(equipmentInstallation.config.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                        $('#' + equipmentInstallation.config.declareRealtyLandCert.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };

    equipmentInstallation.declareRealtyRealEstateCertView = function (id) {
        $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).find(".panel-body").append(commonDeclareApprovalModel.realEstateCert.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var realEstateId = result.data.realEstateId;
                    if (equipmentInstallation.isNotBlank(realEstateId)) {
                        $.ajax({
                            url: getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: realEstateId},
                            success: function (result) {
                                if (result.ret) {
                                    if (equipmentInstallation.isNotBlank(result.data)) {
                                        equipmentInstallation.showFile(equipmentInstallation.config.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, result.data.id);
                                        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).initForm(result.data);
                                        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("input[name='registrationTime']").val(formatDate(result.data.registrationTime));
                                        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("input[name='useStartDate']").val(formatDate(result.data.useStartDate));
                                        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("input[name='useEndDate']").val(formatDate(result.data.useEndDate));
                                        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("input[name='registrationDate']").val(formatDate(result.data.registrationDate));
                                        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("input[name='terminationDate']").val(formatDate(result.data.terminationDate));
                                        $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    /**
     * 经济指标
     */
    equipmentInstallation.declareEconomicIndicatorsView = function (pid) {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: pid},
            success: function (result) {
                if (result.ret) {
                    var indicatorId = result.data.indicatorId;
                    if (equipmentInstallation.isNotBlank(indicatorId)) {
                        $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).clearAll();
                        $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).find('[name=pid]').val(indicatorId);
                        $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).find('.dynamic').remove();
                        economicIndicators.initForm(indicatorId, function () {
                            $('#' + equipmentInstallation.config.declareEconomicIndicators.box).modal("show");
                        });
                    }
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    equipmentInstallation.loadList = function () {
        var cols = [];
        cols.push({
            checkbox: true,
            formatter: {
                disabled: false,//设置是否可用
                checked: false//设置选中
            }
        });
        cols.push({
            field: 'area', title: '区域', formatter: function (value, row, index) {
                var result = '';
                if (row.provinceName) {
                    result = row.provinceName;
                }
                if (row.cityName) {
                    result += row.cityName;
                }
                if (row.districtName) {
                    result += row.districtName;
                }
                return result;
            }
        });
        cols.push({field: 'provinceName', title: '省', visible: false});
        cols.push({field: 'cityName', title: '市', visible: false});
        cols.push({field: 'districtName', title: '县', visible: false});
        cols.push({field: 'bookEquipmentFee', title: '账面设备费', visible: false});
        cols.push({field: 'bookCapitalCost', title: '账面资金成本', visible: false});
        cols.push({field: 'bookInstallationFee', title: '账面安装费'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'centerId', title: '中间表id', visible: true});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '关联信息', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown' id='dropdownMenu2'>" + "<i class='fa fa-users'>" + "</i>" + "关联信息" + "<span class='caret'>" + "</span>" + "</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
                if (row.declareBuildEngineeringAndEquipmentCenter.landId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.realEstateId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.buildingPermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.landUsePermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.buildingConstructionPermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.preSalePermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.indicatorId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareEconomicIndicatorsView(" + row.centerId + ")'" + ">" + "经济规划指标" + "<i class='fa fa-adjust'></i>" + "</a>" + "</li>";
                }
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.findData(" + row.id + ")'" + ">" + "设备安装(主数据)" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                str += "</ul>";
                str += "</div>";
                return str;
            }
        });
        $("#" + equipmentInstallation.config.table).bootstrapTable('destroy');
        TableInit(equipmentInstallation.config.table, getContextPath() + "/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function (data) {
                $('.tooltips').tooltip();
            }
        });
    };


    $(function () {
        equipmentInstallation.loadList();
    });

</script>
<script>
    var civilEngineering = {};

    civilEngineering.config = {
        name: "土建",
        frm: "civilEngineeringFrm",
        table: "civilEngineeringTableList",
        box: "civilEngineeringBox",
        fileId: "civilEngineeringFileId",
        excelUpload: "civilEngineeringUpload",
        copy: "civilEngineeringInputGroup",
        declareBuildingConstructionPermit: {
            box: "declareBuildingConstructionPermitBox",
            frm: "declareBuildingConstructionPermitFrm",
            fileId: "declareBuildingConstructionPermitFileId",
            name: "建筑工程施工许可证"
        },
        declarePreSalePermit: {
            box: "declarePreSalePermitBox",
            frm: "declarePreSalePermitFrm",
            fileId: "declarePreSalePermitFileId",
            name: "商品房预售许可证"
        },
        declareLandUsePermit: {
            box: "declareLandUsePermitBox",
            frm: "declareLandUsePermitFrm",
            fileId: "declareLandUsePermitFileId",
            name: "建设用地规划许可证"
        },
        declareBuildingPermit: {
            box: "declareBuildingPermitBox",
            frm: "declareBuildingPermitFrm",
            fileId: "declareBuildingPermitFileId",
            name: "建设工程规划许可证"
        },
        declareRealtyLandCert: {
            box: "declareRealtyLandCertBox",
            frm: "declareRealtyLandCertFrm",
            fileId: "declareRealtyLandCertFileId",
            name: "土地证"
        },
        declareRealtyRealEstateCert: {
            box: "declareRealtyRealEstateCertBox",
            frm: "declareRealtyRealEstateCertFrm",
            fileId: "declareRealtyRealEstateCertFileId",
            name: "不动产"
        },
        declareEconomicIndicators: {
            box: "declareEconomicIndicatorsBox",
            frm: "declareEconomicIndicatorsFrm",
            fileId: "declareEconomicIndicatorsFileId",
            name: "经济指标"
        }
    };

    civilEngineering.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    civilEngineering.objectWriteSelectData = function (frm, data, name) {
        if (civilEngineering.isNotBlank(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    civilEngineering.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id
            },
            deleteFlag: false
        });
    };

    /**
     * 土地建设 初始化并且赋值
     * @param item
     */
    civilEngineering.init = function (item) {
        $("#" + civilEngineering.config.frm).clearAll();
        $("#" + civilEngineering.config.frm).initForm(item);
        civilEngineering.showFile(civilEngineering.config.fileId, AssessDBKey.DeclareBuildEngineering, civilEngineering.isNotBlank(item.id) ? item.id : "0");
        $("#" + civilEngineering.config.frm).find("input[name='expectedCompletionDate']").val(formatDate(item.expectedCompletionDate));
        $("#" + civilEngineering.config.frm).find("input[name='startDate']").val(formatDate(item.startDate));
        $("#" + civilEngineering.config.frm).find("input[name='declarationDate']").val(formatDate(item.declarationDate));
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）显示
     * @date:2018-09-27
     **/
    civilEngineering.showAddModel = function () {
        $('#' + civilEngineering.config.box).modal("show");
        $('#' + civilEngineering.config.box).find("#" + commonDeclareApprovalModel.config.civilEngineering.handleId).remove();
        $('#' + civilEngineering.config.box).find(".panel-body").prepend(commonDeclareApprovalModel.civilEngineering.getHtml());
    };



    civilEngineering.getData = function (id, callback) {
        $.ajax({
            url: getContextPath() + "/declareBuildEngineering/getDeclareBuildEngineeringById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）查看
     * @date:2018-09-27
     **/
    civilEngineering.findData = function (id) {
        civilEngineering.showAddModel();
        civilEngineering.getData(id, function (data) {
            civilEngineering.init(data);
        });
    };


    /**
     * @author:  zch
     * 描述:不动产 初始化并且赋值
     * @date:2018-09-28
     **/
    civilEngineering.declareRealtyRealEstateCertInit = function (item) {
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).clearAll();
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).initForm(item);
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(item.useEndDate));
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(item.useStartDate));
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(item.registrationTime));
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
        civilEngineering.showFile(civilEngineering.config.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, civilEngineering.isNotBlank(item.id) ? item.id : "0");
    };
    /**
     * @author:  zch
     * 描述:不动产 视图
     * @date:2018-09-28
     **/
    civilEngineering.declareRealtyRealEstateCertView = function (id) {
        $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).find(".panel-body").append(commonDeclareApprovalModel.realEstateCert.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var realEstateId = data.realEstateId;
                    if (civilEngineering.isNotBlank(realEstateId)) {
                        $.ajax({
                            url: getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: realEstateId},
                            success: function (result) {
                                if (result.ret) {
                                    if (civilEngineering.isNotBlank(result.data)) {
                                        civilEngineering.declareRealtyRealEstateCertInit(result.data);
                                        $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    /**
     * @author:  zch
     * 描述:土地证
     * @date:2018-09-28
     **/
    civilEngineering.declareRealtyLandCertInit = function (item) {
        $("#" + civilEngineering.config.declareRealtyLandCert.frm).clearAll();
        $("#" + civilEngineering.config.declareRealtyLandCert.frm).initForm(item);
        civilEngineering.showFile(civilEngineering.config.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, civilEngineering.isNotBlank(item.id) ? item.id : "0");
        $("#" + civilEngineering.config.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
        $("#" + civilEngineering.config.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    };
    civilEngineering.declareRealtyLandCertView = function (id) {
        $('#' + civilEngineering.config.declareRealtyLandCert.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
        $('#' + civilEngineering.config.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var landId = data.landId;
                    if (civilEngineering.isNotBlank(landId)) {
                        $.ajax({
                            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: landId},
                            success: function (result) {
                                if (result.ret) {
                                    if (civilEngineering.isNotBlank(result.data)) {
                                        civilEngineering.declareRealtyLandCertInit(result.data);
                                        $('#' + civilEngineering.config.declareRealtyLandCert.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    /**
     * @author:  zch
     * 描述:建设工程规划许可证 view
     * @date:2018-09-28
     **/
    civilEngineering.declareBuildingPermitView = function (id) {
        $('#' + civilEngineering.config.declareBuildingPermit.box).find("#" + commonDeclareApprovalModel.config.buildingPermit.handleId).remove();
        $('#' + civilEngineering.config.declareBuildingPermit.box).find(".panel-body").append(commonDeclareApprovalModel.buildingPermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var buildingPermitId = result.data.buildingPermitId;
                    if (civilEngineering.isNotBlank(buildingPermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declareBuildingPermit/getDeclareBuildingPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineering.config.declareBuildingPermit.frm).initForm(data);
                                        $("#" + civilEngineering.config.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineering.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                        $('#' + civilEngineering.config.declareBuildingPermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    /**
     * @author:  zch
     * 描述:建设用地规划许可证 view
     * @date:2018-09-28
     **/
    civilEngineering.declareLandUsePermitView = function (id) {
        $('#' + civilEngineering.config.declareLandUsePermit.box).find("#" + commonDeclareApprovalModel.config.landUsePermit.handleId).remove();
        $('#' + civilEngineering.config.declareLandUsePermit.box).find(".panel-body").append(commonDeclareApprovalModel.landUsePermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var landUsePermitId = result.data.landUsePermitId;
                    if (civilEngineering.isNotBlank(landUsePermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declareLandUsePermit/getDeclareLandUsePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: landUsePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineering.config.declareLandUsePermit.frm).initForm(data);
                                        $("#" + civilEngineering.config.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineering.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                        $('#' + civilEngineering.config.declareLandUsePermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };


    /**
     * @author:  zch
     * 描述:商品房预售许可证 view
     * @date:2018-09-28
     **/
    civilEngineering.declarePreSalePermitView = function (id) {
        $('#' + civilEngineering.config.declarePreSalePermit.box).find("#" + commonDeclareApprovalModel.config.preSalePermit.handleId).remove();
        $('#' + civilEngineering.config.declarePreSalePermit.box).find(".panel-body").append(commonDeclareApprovalModel.preSalePermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var preSalePermitId = result.data.preSalePermitId;
                    if (civilEngineering.isNotBlank(preSalePermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declarePreSalePermit/getDeclarePreSalePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: preSalePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineering.config.declarePreSalePermit.frm).initForm(data);
                                        $("#" + civilEngineering.config.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineering.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                        $('#' + civilEngineering.config.declarePreSalePermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };


    /**
     * @author:  zch
     * 描述:建筑工程施工许可证 view
     * @date:2018-09-28
     **/
    civilEngineering.declareBuildingConstructionPermitView = function (id) {
        $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).find("#" + commonDeclareApprovalModel.config.buildingConstructionPermit.handleId).remove();
        $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).find(".panel-body").append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var buildingConstructionPermitId = result.data.buildingConstructionPermitId;
                    if (civilEngineering.isNotBlank(buildingConstructionPermitId)) {
                        $.ajax({
                            url: getContextPath() + "/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingConstructionPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isNotBlank(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineering.config.declareBuildingConstructionPermit.frm).initForm(data);
                                        $("#" + civilEngineering.config.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                                        $("#" + civilEngineering.config.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineering.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                        $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).modal("show");
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };




    /**
     * 经济指标
     */
    civilEngineering.declareEconomicIndicatorsView = function (pid) {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: pid},
            success: function (result) {
                if (result.ret) {
                    var indicatorId = result.data.indicatorId;
                    if (civilEngineering.isNotBlank(indicatorId)) {
                        $("#" + civilEngineering.config.declareEconomicIndicators.frm).clearAll();
                        $("#" + civilEngineering.config.declareEconomicIndicators.frm).find('.dynamic').remove();
                        economicIndicators.initForm(indicatorId, function () {
                            $('#' + civilEngineering.config.declareEconomicIndicators.box).modal("show");
                        });
                    }
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    civilEngineering.loadList = function () {
        var cols = [];
        cols.push({
            field: 'area', title: '区域', formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
            }
        });
        cols.push({field: 'name', title: '项目名称', visible: true});
        cols.push({field: 'bookNetValue', title: '帐面净值', visible: true});
        cols.push({field: 'bookValue', title: '帐面价值', visible: true});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'centerId', title: '中间表id', visible: false});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '关联信息', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>" + "<i class='fa fa-users'>" + "</i>" + "关联信息" + "<span class='caret'>" + "</span>" + "</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
                if (row.declareBuildEngineeringAndEquipmentCenter.landId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.realEstateId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.buildingPermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.landUsePermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.buildingConstructionPermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.preSalePermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                }
                if (row.declareBuildEngineeringAndEquipmentCenter.indicatorId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareEconomicIndicatorsView(" + row.centerId + ")'" + ">" + "经济规划指标" + "<i class='fa fa-adjust'></i>" + "</a>" + "</li>";
                }
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.findData(" + row.id + ")'" + ">" + "土建(主数据)" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                str += "</ul>";
                str += "</div>";
                return str;
            }
        });
        $("#" + civilEngineering.config.table).bootstrapTable('destroy');
        TableInit(civilEngineering.config.table, getContextPath() + "/declareBuildEngineering/getDeclareBuildEngineeringList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function (data) {
                $('.tooltips').tooltip();
            }
        }, true);
    };

    $(function () {
        civilEngineering.loadList();
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
<script type="application/javascript">

    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>

<!-- 土建 模块 -->
<!-- 建设工程规划许可证 -->
<div id="declareBuildingPermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareBuildingPermitFrm" class="form-horizontal">
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
                                            <div id="_declareBuildingPermitFileId"></div>
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

<!-- 建设用地规划许可证 -->
<div id="declareLandUsePermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareLandUsePermitFrm" class="form-horizontal">
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
                                            <div id="_declareLandUsePermitFileId"></div>
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

<!-- 商品房预售许可证 -->
<div id="declarePreSalePermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declarePreSalePermitFrm" class="form-horizontal">
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
                                            <div id="_declarePreSalePermitFileId"></div>
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

<!-- 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareBuildingConstructionPermitFrm" class="form-horizontal">
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
                                            <div id="_declareBuildingConstructionPermitFileId"></div>
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

<!-- 在建工程（土建） -->
<div id="civilEngineeringBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土建</h3>
            </div>
            <form id="civilEngineeringFrm" class="form-horizontal">
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
                                            <div id="_civilEngineeringFileId"></div>
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

<!-- 土地证 -->
<div id="declareRealtyLandCertBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="declareRealtyLandCertFrm" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <div id="_declareRealtyLandCertFileId"></div>
                                        </div>
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

<!-- 不动产 -->
<div id="declareRealtyRealEstateCertBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产</h3>
            </div>
            <form id="declareRealtyRealEstateCertFrm" class="form-horizontal">
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
                                            <div id="_declareRealtyRealEstateCertFileId"></div>
                                        </div>
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

<!-- 设备安装 模块 -->
<!-- 建设工程规划许可证 -->
<div id="declareBuildingPermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareBuildingPermitFrmE" class="form-horizontal">
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
                                            <div id="_declareBuildingPermitFileIdE"></div>
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

<!-- 建设用地规划许可证 -->
<div id="declareLandUsePermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareLandUsePermitFrmE" class="form-horizontal">
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
                                            <div id="_declareLandUsePermitFileIdE"></div>
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

<!-- 商品房预售许可证 -->
<div id="declarePreSalePermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declarePreSalePermitFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
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
                                            <div id="_declarePreSalePermitFileIdE"></div>
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

<!-- 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareBuildingConstructionPermitFrmE" class="form-horizontal">
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
                                            <div id="_declareBuildingConstructionPermitFileIdE"></div>
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

<!-- 在建工程（设备安装） -->
<div id="equipmentInstallationBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">设备安装</h3>
            </div>
            <form id="equipmentInstallationFrm" class="form-horizontal">
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
                                            <div id="_equipmentInstallationFileId"></div>
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

<!-- 土地证 -->
<div id="declareRealtyLandCertBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="declareRealtyLandCertFrmE" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <div id="_declareRealtyLandCertFileIdE"></div>
                                        </div>
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

<!-- 不动产 -->
<div id="declareRealtyRealEstateCertBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产</h3>
            </div>
            <form id="declareRealtyRealEstateCertFrmE" class="form-horizontal">
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
                                            <div id="_declareRealtyRealEstateCertFileIdE"></div>
                                        </div>
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
</body>
</html>

