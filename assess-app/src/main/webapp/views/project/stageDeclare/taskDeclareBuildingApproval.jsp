<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>

<%@include file="/views/project/stageDeclare/BuildingDeclareModel/viewEconomicIndicatorsDetail.jsp" %>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div class="x_title">
                        <h3>申报证书类型
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frmCertificate">
                        <div id="frmCertificateHTML">

                        </div>
                    </form>
                </div>
            </div>

            <!-- 土建 -->
            <div class="x_panel" id="viewCivilEngineering">
                <div class="x_panel">
                    <div class="x_content">
                        <div class="x_title">
                            <h3>
                                在建工程（土建）申报
                            </h3>
                            <div class="clearfix"></div>
                        </div>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12">

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
            </div>

            <!-- 设备安装 -->
            <div class="x_panel" id="viewEquipmentInstallation">
                <div class="x_panel">
                    <div class="x_content">
                        <div class="x_title">
                            <h3>
                                在建工程（设备安装）
                            </h3>
                            <div class="clearfix"></div>
                        </div>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12">

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
            </div>


            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<script>
    var equipmentInstallationConfig = {
        name: "设备安装",
        frm: "equipmentInstallationFrm",
        table: "equipmentInstallationTableList",
        box: "equipmentInstallationBox",
        fileId:"equipmentInstallationFileId",
        excelUpload:"equipmentInstallationUpload",
        declareBuildingConstructionPermit: {
            box: "declareBuildingConstructionPermitBoxE",
            frm: "declareBuildingConstructionPermitFrmE",
            fileId: "declareBuildingConstructionPermitFileIdE",
            name: "建筑工程施工许可证"
        },
        declarePreSalePermit:{
            box:"declarePreSalePermitBoxE",
            frm:"declarePreSalePermitFrmE",
            fileId:"declarePreSalePermitFileIdE",
            name: "商品房预售许可证"
        },
        declareLandUsePermit:{
            box:"declareLandUsePermitBoxE",
            frm:"declareLandUsePermitFrmE",
            fileId:"declareLandUsePermitFileIdE",
            name:"建设用地规划许可证"
        },
        declareBuildingPermit:{
            box:"declareBuildingPermitBoxE",
            frm:"declareBuildingPermitFrmE",
            fileId:"declareBuildingPermitFileIdE",
            name:"建设工程规划许可证"
        },
        declareRealtyLandCert:{
            box:"declareRealtyLandCertBoxE",
            frm:"declareRealtyLandCertFrmE",
            fileId:"declareRealtyLandCertFileIdE",
            name:"土地证"
        },
        declareRealtyRealEstateCert:{
            box:"declareRealtyRealEstateCertBoxE",
            frm:"declareRealtyRealEstateCertFrmE",
            fileId:"declareRealtyRealEstateCertFileIdE",
            name:"不动产"
        }
    };

    var equipmentInstallation = new Object();
    //处理标识符的地方-------start
    equipmentInstallation.equipmentInstallationFlag = true;
    equipmentInstallation.declareRealtyLandCertFlag = true;
    equipmentInstallation.declareRealtyRealEstateCertFlag = true;
    //----------------------end

    equipmentInstallation.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    equipmentInstallation.objectWriteSelectData = function (frm, data, name) {
        if (equipmentInstallation.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    equipmentInstallation.showFile = function (target,tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id,
                projectId: 0
            },
            deleteFlag: true
        });
    };

    equipmentInstallation.fileUpload = function (target,tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            onUpload: function (file) {
                var formData = {
                    fieldsName: target,
                    tableName: tableName,
                    tableId: id
                };
                return formData;
            }, onUploadComplete: function (result,file) {
                equipmentInstallation.showFile(target,tableName,id);
            },
            deleteFlag: true
        });
    };

    equipmentInstallation.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
    };

    var eqArr = new Array();
    eqArr.push(equipmentInstallationConfig.frm,equipmentInstallationConfig.declareBuildingConstructionPermit.frm);
    eqArr.push(equipmentInstallationConfig.declareBuildingPermit.frm,equipmentInstallationConfig.declareLandUsePermit.frm);
    eqArr.push(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,equipmentInstallationConfig.declarePreSalePermit.frm);
    eqArr.push(equipmentInstallationConfig.declareRealtyLandCert.frm);


    /**
     * @author:  zch
     * 描述:在建工程（设备安装）编辑
     * @date:2018-09-27
     **/
    equipmentInstallation.editData = function (id) {
        $("#" + equipmentInstallationConfig.frm).clearAll();
        if (equipmentInstallation.equipmentInstallationFlag){
            equipmentInstallation.init();
            equipmentInstallation.equipmentInstallationFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data ;
                    if (equipmentInstallation.isEmpty(data)){
                        $("#" + equipmentInstallationConfig.frm).initForm(result.data);
                        equipmentInstallation.showFile(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,data.id);
                        $("#" + equipmentInstallationConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + equipmentInstallationConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + equipmentInstallationConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                        $.each(eqArr,function (i,n) {
                            $("#"+n+" :input").attr("readonly","readonly");
                        });
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        //使校验生效
        $("#" + equipmentInstallationConfig.frm).validate();
        $('#' + equipmentInstallationConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:建设工程规划许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingPermitView = function (id) {
        $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var buildingPermitId = data.buildingPermitId ;
                    if (equipmentInstallation.isEmpty(buildingPermitId)){
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareBuildingPermit/getDeclareBuildingPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).initForm(data);
                                        $("#" + equipmentInstallationConfig.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                        $.each(eqArr,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }else {
                        $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).initForm({pid: id});
                        equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                    }
                    $('#' + equipmentInstallationConfig.declareBuildingPermit.box).modal("show");
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
        $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var landUsePermitId = data.landUsePermitId ;
                    if (equipmentInstallation.isEmpty(landUsePermitId)){
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareLandUsePermit/getDeclareLandUsePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: landUsePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).initForm(data);
                                        $("#" + equipmentInstallationConfig.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                        $.each(eqArr,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }else {
                        $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).initForm({pid: id});
                        equipmentInstallation.showFile(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                    }
                    $('#' + equipmentInstallationConfig.declareLandUsePermit.box).modal("show");
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
        $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var preSalePermitId = data.preSalePermitId;
                    if (equipmentInstallation.isEmpty(preSalePermitId)){
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declarePreSalePermit/getDeclarePreSalePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: preSalePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).initForm(data);
                                        $("#" + equipmentInstallationConfig.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                        $.each(eqArr,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }else {
                        $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).initForm({pid: id});
                        equipmentInstallation.showFile(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                    }
                    $('#' + equipmentInstallationConfig.declarePreSalePermit.box).modal("show");
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
        $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var buildingConstructionPermitId = data.buildingConstructionPermitId;
                    if (equipmentInstallation.isEmpty(buildingConstructionPermitId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingConstructionPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).initForm(data);
                                        $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                                        $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                        $.each(eqArr,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).initForm({pid: id});
                        equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                    }
                    $('#' + equipmentInstallationConfig.declareBuildingConstructionPermit.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    equipmentInstallation.declareRealtyLandCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
    };
    equipmentInstallation.declareRealtyLandCertView = function (id) {
        if (equipmentInstallation.declareRealtyLandCertFlag){
            equipmentInstallation.declareRealtyLandCertInit();
            equipmentInstallation.declareRealtyLandCertFlag = false;
        }
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var landId = data.landId ;
                    if (equipmentInstallation.isEmpty(landId)){
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: landId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isEmpty(data)) {
                                        data.pidC = id;
                                        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).initForm(data);
                                        equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm,data.purpose,"purpose");
                                        equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm,data.type,"type");
                                        equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm,data.useRightType,"useRightType");
                                        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                        equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                        $.each(eqArr,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }else {
                        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).initForm({pidC: id});
                        equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
                    }
                    $('#' + equipmentInstallationConfig.declareRealtyLandCert.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    equipmentInstallation.declareRealtyRealEstateCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType,'', function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
    };
    equipmentInstallation.declareRealtyRealEstateCertView = function (id) {
        if (equipmentInstallation.declareRealtyRealEstateCertFlag){
            equipmentInstallation.declareRealtyRealEstateCertInit();
            equipmentInstallation.declareRealtyRealEstateCertFlag = false;
        }
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var realEstateId = data.realEstateId ;
                    if (equipmentInstallation.isEmpty(realEstateId)){
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: realEstateId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (equipmentInstallation.isEmpty(data)) {
                                        data.pidC = id;
                                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).initForm(data);
                                        equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,data.purpose,"purpose");
                                        equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,data.type,"type");
                                        equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,data.useRightType,"useRightType");
                                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                        equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                                        $.each(eqArr,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    }else {
                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).initForm({pidC: id});
                        equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
                    }
                    $('#' + equipmentInstallationConfig.declareRealtyRealEstateCert.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });

    };

    equipmentInstallation.loadList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'bookEquipmentFee', title: '账面设备费'});
        cols.push({field: 'bookCapitalCost', title: '账面资金成本'});
        cols.push({field: 'bookInstallationFee', title: '账面安装费'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn dropdown-toggle' data-toggle='dropdown' id='dropdownMenu2'>"+"操作" +"<span class='caret'>"+"</span>"+"</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>" ;
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.editData(" +row.id+")'"+">"+"在建工程（设备安装）"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyLandCertView(" +row.centerId+")'"+">"+"土地证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyRealEstateCertView(" +row.centerId+")'"+">"+"不动产"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingPermitView(" +row.centerId+")'"+">"+"建设工程规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareLandUsePermitView(" +row.centerId+")'"+">"+"建设用地规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingConstructionPermitView(" +row.centerId+")'"+">"+"建筑工程施工许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declarePreSalePermitView(" +row.centerId+")'"+">"+"商品房预售许可证"+ "</a>" + "</li>";
                str += "</ul>" ;
                str += "</div>";
                return str;
            }
        });
        $("#" + equipmentInstallationConfig.table).bootstrapTable('destroy');
        TableInit(equipmentInstallationConfig.table, "${pageContext.request.contextPath}/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
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
        equipmentInstallation.loadList();
    });
</script>
<script>
    /**
     * @author:  zch
     * 描述:配置一些必须的参数
     * @date:2018-09-27
     **/
    var civilEngineeringConfig = {
        name: "土建",
        frm: "civilEngineeringFrm",
        table: "civilEngineeringTableList",
        box: "civilEngineeringBox",
        fileId: "civilEngineeringFileId",
        excelUpload: "civilEngineeringUpload",
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

    var civilEngineering = new Object();

    //处理标识符的地方-------start
    civilEngineering.civilEngineeringFlag = true;
    civilEngineering.declareRealtyLandCertFlag = true;
    civilEngineering.declareRealtyRealEstateCertFlag = true;
    //----------------------end

    civilEngineering.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    civilEngineering.objectWriteSelectData = function (frm, data, name) {
        if (civilEngineering.isEmpty(data)) {
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
                tableId: id,
                projectId: 0
            },
            deleteFlag: true
        });
    };

    civilEngineering.fileUpload = function (target, tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            onUpload: function (file) {
                var formData = {
                    fieldsName: target,
                    tableName: tableName,
                    tableId: id
                };
                return formData;
            }, onUploadComplete: function (result, file) {
                civilEngineering.showFile(target, tableName, id);
            },
            deleteFlag: true
        });
    };

    civilEngineering.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
    };

    var eqArr2 = new Array();
    eqArr2.push(civilEngineeringConfig.frm,civilEngineeringConfig.declareBuildingConstructionPermit.frm);
    eqArr2.push(civilEngineeringConfig.declareBuildingPermit.frm,civilEngineeringConfig.declareLandUsePermit.frm);
    eqArr2.push(civilEngineeringConfig.declareRealtyRealEstateCert.frm,civilEngineeringConfig.declarePreSalePermit.frm);
    eqArr2.push(civilEngineeringConfig.declareRealtyLandCert.frm);

    /**
     * @author:  zch
     * 描述:在建工程（土建）编辑
     * @date:2018-09-27
     **/
    civilEngineering.editData = function (id) {
        $("#" + civilEngineeringConfig.frm).clearAll();
        if (civilEngineering.civilEngineeringFlag) {
            civilEngineering.init();
            civilEngineering.civilEngineeringFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareBuildEngineering/getDeclareBuildEngineeringById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (civilEngineering.isEmpty(data)) {
                        $("#" + civilEngineeringConfig.frm).initForm(result.data);
                        civilEngineering.showFile(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, data.id);
                        $("#" + civilEngineeringConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                        $.each(eqArr2,function (i,n) {
                            $("#"+n+" :input").attr("readonly","readonly");
                        });
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        //使校验生效
        $("#" + civilEngineeringConfig.frm).validate();
        $('#' + civilEngineeringConfig.box).modal("show");
    };

    <!-- 不动产 -->
    civilEngineering.declareRealtyRealEstateCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType,'', function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
    };
    civilEngineering.declareRealtyRealEstateCertView = function (id) {
        if (civilEngineering.declareRealtyRealEstateCertFlag) {
            civilEngineering.declareRealtyRealEstateCertInit();
            civilEngineering.declareRealtyRealEstateCertFlag = false;
        }
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var realEstateId = data.realEstateId;
                    if (civilEngineering.isEmpty(realEstateId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: realEstateId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isEmpty(data)) {
                                        data.pidC = id;
                                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).initForm(data);
                                        civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.purpose, "purpose");
                                        civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.type, "type");
                                        civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.useRightType, "useRightType");
                                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                        civilEngineering.showFile(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                                        $.each(eqArr2,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).initForm({pidC: id});
                        civilEngineering.showFile(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
                        civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
                    }
                    $('#' + civilEngineeringConfig.declareRealtyRealEstateCert.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    <!-- 土地证 -->
    civilEngineering.declareRealtyLandCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType,'', function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
    };
    civilEngineering.declareRealtyLandCertView = function (id) {
        if (civilEngineering.declareRealtyLandCertFlag) {
            civilEngineering.declareRealtyLandCertInit();
            civilEngineering.declareRealtyLandCertFlag = false;
        }
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var landId = data.landId;
                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).clearAll();
                    if (civilEngineering.isEmpty(landId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                            type: "get",
                            dataType: "json",
                            data: {id: landId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isEmpty(data)) {
                                        data.pidC = id;
                                        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).initForm(data);
                                        civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.purpose, "purpose");
                                        civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.type, "type");
                                        civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.useRightType, "useRightType");
                                        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                        civilEngineering.showFile(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                        $.each(eqArr2,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).initForm({pidC: id});
                        civilEngineering.showFile(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
                        civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
                    }
                    $('#' + civilEngineeringConfig.declareRealtyLandCert.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    <!-- 建设工程规划许可证 -->
    civilEngineering.declareBuildingPermitView = function (id) {
        $("#" + civilEngineeringConfig.declareBuildingPermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var buildingPermitId = data.buildingPermitId;
                    if (civilEngineering.isEmpty(buildingPermitId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareBuildingPermit/getDeclareBuildingPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineeringConfig.declareBuildingPermit.frm).initForm(data);
                                        $("#" + civilEngineeringConfig.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                        $.each(eqArr2,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + civilEngineeringConfig.declareBuildingPermit.frm).initForm({pid: id});
                        civilEngineering.showFile(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                        civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                    }
                    $('#' + civilEngineeringConfig.declareBuildingPermit.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    <!-- 建设用地规划许可证 -->
    civilEngineering.declareLandUsePermitView = function (id) {
        $("#" + civilEngineeringConfig.declareLandUsePermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var landUsePermitId = data.landUsePermitId;
                    if (civilEngineering.isEmpty(landUsePermitId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareLandUsePermit/getDeclareLandUsePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: landUsePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineeringConfig.declareLandUsePermit.frm).initForm(data);
                                        $("#" + civilEngineeringConfig.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                        $.each(eqArr2,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + civilEngineeringConfig.declareLandUsePermit.frm).initForm({pid: id});
                        civilEngineering.showFile(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                        civilEngineering.fileUpload(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                    }
                    $('#' + civilEngineeringConfig.declareLandUsePermit.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    <!-- 商品房预售许可证 -->
    civilEngineering.declarePreSalePermitView = function (id) {
        $("#" + civilEngineeringConfig.declarePreSalePermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var preSalePermitId = data.preSalePermitId;
                    if (civilEngineering.isEmpty(preSalePermitId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declarePreSalePermit/getDeclarePreSalePermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: preSalePermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineeringConfig.declarePreSalePermit.frm).initForm(data);
                                        $("#" + civilEngineeringConfig.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                        $.each(eqArr2,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + civilEngineeringConfig.declarePreSalePermit.frm).initForm({pid: id});
                        civilEngineering.showFile(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                        civilEngineering.fileUpload(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                    }
                    $('#' + civilEngineeringConfig.declarePreSalePermit.box).modal("show");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    <!-- 建筑工程施工许可证 -->
    civilEngineering.declareBuildingConstructionPermitView = function (id) {
        $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).clearAll();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var buildingConstructionPermitId = data.buildingConstructionPermitId;
                    if (civilEngineering.isEmpty(buildingConstructionPermitId)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                            type: "get",
                            dataType: "json",
                            data: {id: buildingConstructionPermitId},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    if (civilEngineering.isEmpty(data)) {
                                        data.pid = id;
                                        $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).initForm(data);
                                        $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                                        $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                                        civilEngineering.showFile(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                        $.each(eqArr2,function (i,n) {
                                            $("#"+n+" :input").attr("readonly","readonly");
                                        });
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    } else {
                        $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).initForm({pid: id});
                        civilEngineering.showFile(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                        civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                    }
                    $('#' + civilEngineeringConfig.declareBuildingConstructionPermit.box).modal("show");
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
                    if (civilEngineering.isEmpty(indicatorId)){
                        $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).clearAll();
                        $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('.dynamic').remove();
                        economicIndicators.initForm(indicatorId, function () {
                            $('#' + civilEngineeringConfig.declareEconomicIndicators.box).modal("show");
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
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'bookNetValue', title: '帐面净值'});
        cols.push({field: 'bookValue', title: '帐面价值'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn dropdown-toggle' data-toggle='dropdown' id='dropdownMenu1'>"+"操作" +"<span class='caret'>"+"</span>"+"</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" ;
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.editData(" +row.id+")'"+">"+"在建工程（土建）"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyLandCertView(" +row.centerId+")'"+">"+"土地证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyRealEstateCertView(" +row.centerId+")'"+">"+"不动产"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingPermitView(" +row.centerId+")'"+">"+"建设工程规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareLandUsePermitView(" +row.centerId+")'"+">"+"建设用地规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingConstructionPermitView(" +row.centerId+")'"+">"+"建筑工程施工许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declarePreSalePermitView(" +row.centerId+")'"+">"+"商品房预售许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareEconomicIndicatorsView(" +row.centerId+")'"+">"+"经济指标"+ "</a>" + "</li>";
                str += "</ul>" ;
                str += "</div>";
                return str;
            }
        });
        $("#" + civilEngineeringConfig.table).bootstrapTable('destroy');
        TableInit(civilEngineeringConfig.table, "${pageContext.request.contextPath}/declareBuildEngineering/getDeclareBuildEngineeringList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
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
        civilEngineering.loadList();
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="application/javascript">
    var config = {
        declare: {
            frm: "frmCertificate"
        },
        civilEngineering: {
            name: "土建",
            view:"viewCivilEngineering"
        },
        equipmentInstallation: {
            name: "设备安装",
            view:"viewEquipmentInstallation"
        }
    };

    var declareFunObj = new Object();

    declareFunObj.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    declareFunObj.updateInit = function () {
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleDeclareBuildingCertificateType, function (html, data){
            $.each(data, function (i, n) {
                if (config.civilEngineering.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareBuildEngineering/listDeclareBuildEngineering",
                        data: {planDetailsId:'${empty projectPlanDetails.id?0:projectPlanDetails.id}',declareType:n.id},
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)){
                                    if (result.data.length >= 1){
                                        declareFunObj.civilEngineering.toggle();//view 显示
                                        $("#" + config.declare.frm + " :checkbox").each(function (j,oo) {
                                            if ($(oo).val() == n.id){
                                                $(this).prop("checked",true);//单选框 选中
                                            }
                                        });
                                    }
                                }
                            } else {
                                Alert("失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            });
            $.each(data, function (i, n) {
                if (config.equipmentInstallation.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/listDeclareBuildEquipmentInstall",
                        data: {planDetailsId:'${empty projectPlanDetails.id?0:projectPlanDetails.id}',declareType:n.id},
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)){
                                    if (result.data.length >= 1){
                                        declareFunObj.equipmentInstallation.toggle();//view 显示
                                        $("#" + config.declare.frm + " :checkbox").each(function (j,oo) {
                                            if ($(oo).val() == n.id){
                                                $(this).prop("checked",true);//单选框 选中
                                            }
                                        });
                                    }
                                }
                            } else {
                                Alert("失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            });
        });
    };

    declareFunObj.declare = {
        init: function () {
            var num = 6;
            AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleDeclareBuildingCertificateType, function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += data[j].name;
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + config.declare.frm + "HTML").append(resetHtml);
                declareFunObj.updateInit();
            });
        },
    };

    declareFunObj.civilEngineering = {
        toggle:function () {
            $("#" + config.civilEngineering.view).show();
        }
    };

    declareFunObj.equipmentInstallation = {
        toggle:function () {
            $("#" + config.equipmentInstallation.view).show();
        }
    }

    $(function () {
        declareFunObj.declare.init();
    });

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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" readonly="readonly" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设单位（个人）<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设单位（个人）" name="unit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设位置<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设位置" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareBuildingPermitFileId"--%>
                                                   <%--name="declareBuildingPermitFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地单位" name="unit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地位置<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地位置" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地性质<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地性质" name="nature" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true'
                                                   name="area" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareLandUsePermitFileId"--%>
                                                   <%--name="declareLandUsePermitFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">售房单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="售房单位" name="salesUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">法定代表人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="法定代表人" name="legalRepresentative" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售范围<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售范围" name="preSaleScope" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售面积(数字)" data-rule-maxlength="100"
                                                   data-rule-number='true' name="preSaleArea" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋用途<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋用途" name="housingUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑结构<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑结构" name="buildingStructure" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售款监管信息<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售款监管信息" name="preSaleSupervisionInformation"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">在建工程抵押情况<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declarePreSalePermitFileId"--%>
                                                   <%--name="declareBuildingConstructionPermitFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设单位（个人）<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设单位（个人）" name="buildUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设地址<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设地址" name="buildAddress" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">勘察单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="勘察单位" name="reconnaissanceUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">设计单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="设计单位" name="designUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">施工单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="施工单位" name="constructionUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">监理单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="监理单位" name="constructionControlUnit"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">勘察单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">设计单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">施工单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="施工单位项目负责人" name="constructionUnitPerson"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">总监理工程师<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">合同工期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="合同工期"
                                                   name="contractPeriod" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareBuildingConstructionPermitFileId"--%>
                                                   <%--name="declareBuildingConstructionPermitFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province" id="civilEngineeringFrmprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="civilEngineeringFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="civilEngineeringFrmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">占有单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="占有单位" name="occupancyUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">结构<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="结构" name="structure" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="buildArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">开工日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="开工日期"
                                                   name="startDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预期完成日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="预期完成日期"
                                                   name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">形象进度<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="形象进度" name="speedProgress" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">付款比例<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="付款比例" name="paymentRatio" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面价值<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面价值" name="bookValue" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">帐面净值<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="帐面净值" name="bookNetValue" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="申报人" name="declarer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="申报日期"
                                                   name="declarationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="remark" required="required"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="civilEngineeringFileId" name="civilEngineeringFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="declareRealtyLandCertFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pidC">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyLandCertFrmprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCertFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            县(区)
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCertFrmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            所在地
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="location" required="required" class="form-control"
                                                   placeholder="所在地">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用权人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="ownership" required="required" class="form-control"
                                                   placeholder="土地使用权人">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地权证号
                                        </label>
                                        <div class="col-sm-11">
                                            <input type="text" name="landCertName" readonly="readonly"
                                                   class="form-control" placeholder="土地权证号">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            年份
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" data-rule-maxlength="100"
                                                   data-rule-number='true' name="year" class="form-control"
                                                   placeholder="年份(数字如:2018)">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            编号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" name="number" class="form-control"
                                                   placeholder="编号">
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房屋坐落" name="beLocated" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">街道号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="街道号" name="streetNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附号</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附号(数字)" name="attachedNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">栋号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">地号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="地号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">图号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="图号" name="graphNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="purpose"
                                                    class="form-control search-select select2 purpose">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">取得价格<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="取得价格" name="acquisitionPrice" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            使用权类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="useRightType"
                                                    class="form-control search-select select2 useRightType">
                                                <option value="请选择">请选择</option>
                                                <option value="划拨" name="useRightType">划拨</option>
                                                <option value="出证" name="useRightType">出证</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            终止日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="终止日期"
                                                   name="terminationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">使用权面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">独用面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">登记机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="登记机关" name="registrationAuthority" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记日期"
                                                   name="registrationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">记事<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareRealtyLandCertFileId" name="declareRealtyLandCertFileId"--%>
                                                   <%--required="required" placeholder="上传附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产</h3>
            </div>
            <form id="declareRealtyRealEstateCertFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pidC">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyRealEstateCertFrmprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyRealEstateCertFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            县(区)
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyRealEstateCertFrmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房产权证号<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房产权证号" name="certName" class="form-control">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">所在地<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="所在地" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                   placeholder="编号(数字)" name="number" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房产证类型<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control search-select select2 type"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋所有权人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋所有权人" name="ownership" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="共有情况" name="publicSituation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="floorArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房屋坐落" name="beLocated" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">街道号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="街道号" name="streetNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附号(数字)" name="attachedNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">栋号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记时间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记时间"
                                                   name="registrationTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋性质<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋性质" name="nature" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">规划用途<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="规划用途" name="planningUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">总层数<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="总层数(数字)" name="floorCount" class="form-control"
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证载面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">套内面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="套内面积(数字)" name="innerArea" class="form-control"
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地证号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">土地取得方式<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地取得方式" name="landAcquisition" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用年限起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="土地使用年限起"
                                                   name="useStartDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用年限止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="土地使用年限止"
                                                   name="useEndDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">公摊面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="公摊面积(数字)" name="publicArea" class="form-control"
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附记其它<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附记其它" name="otherNote" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="登记机关" name="registrationAuthority" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记日期"
                                                   name="registrationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">地号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="地号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">图号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="图号" name="graphNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="purpose"
                                                    class="form-control search-select select2 purpose">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">取得价格<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="取得价格" name="acquisitionPrice" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            使用权类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="useRightType"
                                                    class="form-control search-select select2 useRightType">
                                                <option value="请选择">请选择</option>
                                                <option value="划拨" name="useRightType">划拨</option>
                                                <option value="出证" name="useRightType">出证</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            终止日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="终止日期"
                                                   name="terminationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">使用权面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">独用面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">记事<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareRealtyRealEstateCertFileId"--%>
                                                   <%--name="declareRealtyRealEstateCertFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设单位（个人）<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设单位（个人）" name="unit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设位置<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设位置"  name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareBuildingPermitFileIdE"--%>
                                                   <%--name="declareBuildingPermitFileIdE"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地单位" name="unit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地位置<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地位置" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地性质<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地性质" name="nature" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true' name="area" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareLandUsePermitFileIdE"--%>
                                                   <%--name="declareLandUsePermitFileIdE"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">售房单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="售房单位" name="salesUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">法定代表人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="法定代表人" name="legalRepresentative" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售范围<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售范围" name="preSaleScope" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售面积(数字)" data-rule-maxlength="100" data-rule-number='true' name="preSaleArea" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋用途<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋用途" name="housingUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑结构<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑结构" name="buildingStructure" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售款监管信息<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售款监管信息" name="preSaleSupervisionInformation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">在建工程抵押情况<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declarePreSalePermitFileIdE"--%>
                                                   <%--name="declareBuildingConstructionPermitFileIdE"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设单位（个人）<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设单位（个人）" name="buildUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设地址<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设地址" name="buildAddress" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">勘察单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="勘察单位" name="reconnaissanceUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">设计单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="设计单位" name="designUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">施工单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="施工单位" name="constructionUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">监理单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="监理单位" name="constructionControlUnit"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">勘察单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">设计单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">施工单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="施工单位项目负责人" name="constructionUnitPerson"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">总监理工程师<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">合同工期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="合同工期"
                                                   name="contractPeriod" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareBuildingConstructionPermitFileIdE"--%>
                                                   <%--name="declareBuildingConstructionPermitFileIdE"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province" id="equipmentInstallationFrmprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="equipmentInstallationFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="equipmentInstallationFrmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">占有单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="占有单位" name="occupancyUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">规格型号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="规格型号" name="specificationModel" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">生产厂家<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="生产厂家" name="manufacturer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">计量单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="计量单位" name="measurementUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">数量<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                   placeholder="数量(数字)" name="number" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">开工日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="开工日期"
                                                   name="startDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预期完成日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="预期完成日期"
                                                   name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面设备费<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面设备费" name="bookEquipmentFee" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面资金成本<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面资金成本" name="bookCapitalCost" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面安装费<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面安装费" name="bookInstallationFee" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="申报人" name="declarer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="申报日期"
                                                   name="declarationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="remark" required="required"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="equipmentInstallationFileId" name="equipmentInstallationFileId"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="declareRealtyLandCertFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pidC">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyLandCertFrmEprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCertFrmEcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            县(区)
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCertFrmEdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            所在地
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="location" required="required" class="form-control" placeholder="所在地">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用权人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="ownership" required="required"  class="form-control" placeholder="土地使用权人">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地权证号
                                        </label>
                                        <div class="col-sm-11">
                                            <input type="text" name="landCertName" readonly="readonly" class="form-control" placeholder="土地权证号">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            年份
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" data-rule-maxlength="100" data-rule-number='true' name="year" class="form-control" placeholder="年份(数字如:2018)">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            编号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" name="number" class="form-control" placeholder="编号">
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房屋坐落" name="beLocated" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">街道号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="街道号" name="streetNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附号</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附号(数字)" name="attachedNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">栋号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">地号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="地号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">图号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="图号" name="graphNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="purpose"
                                                    class="form-control search-select select2 purpose">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">取得价格<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="取得价格" name="acquisitionPrice" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            使用权类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="useRightType" class="form-control search-select select2 useRightType">
                                                <option value="请选择">请选择</option>
                                                <option value="划拨" name="useRightType">划拨</option>
                                                <option value="出证" name="useRightType">出证</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            终止日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="终止日期"
                                                   name="terminationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">使用权面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">独用面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">登记机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="登记机关" name="registrationAuthority" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记日期"
                                                   name="registrationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">记事<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareRealtyLandCertFileIdE" name="declareRealtyLandCertFileIdE"--%>
                                                   <%--required="required" placeholder="上传附件" class="form-control"--%>
                                                   <%--type="file">--%>
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
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产</h3>
            </div>
            <form id="declareRealtyRealEstateCertFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pidC">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyRealEstateCertFrmEprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyRealEstateCertFrmEcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            县(区)
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyRealEstateCertFrmEdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房产权证号<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房产权证号" name="certName" class="form-control">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">所在地<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="所在地" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                   placeholder="编号(数字)" name="number" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房产证类型<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control search-select select2 type"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋所有权人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋所有权人" name="ownership" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="共有情况" name="publicSituation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="floorArea" class="form-control" data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房屋坐落" name="beLocated" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">街道号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="街道号" name="streetNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附号(数字)" name="attachedNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">栋号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记时间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记时间"
                                                   name="registrationTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋性质<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋性质" name="nature" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">规划用途<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="规划用途" name="planningUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">总层数<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="总层数(数字)" name="floorCount" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证载面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">套内面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="套内面积(数字)" name="innerArea" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地证号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">土地取得方式<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地取得方式" name="landAcquisition" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用年限起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="土地使用年限起"
                                                   name="useStartDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用年限止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="土地使用年限止"
                                                   name="useEndDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">公摊面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="公摊面积(数字)" name="publicArea" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附记其它<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附记其它" name="otherNote" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="登记机关" name="registrationAuthority" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记日期"
                                                   name="registrationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">地号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="地号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">图号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="图号" name="graphNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="purpose"
                                                    class="form-control search-select select2 purpose">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">取得价格<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="取得价格" name="acquisitionPrice" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            使用权类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="useRightType" class="form-control search-select select2 useRightType">
                                                <option value="请选择">请选择</option>
                                                <option value="划拨" name="useRightType">划拨</option>
                                                <option value="出证" name="useRightType">出证</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            终止日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="终止日期"
                                                   name="terminationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">使用权面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">独用面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">记事<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <%--<input id="declareRealtyRealEstateCertFileIdE" name="declareRealtyRealEstateCertFileIdE"--%>
                                                   <%--required="required" placeholder="附件" class="form-control"--%>
                                                   <%--type="file">--%>
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

