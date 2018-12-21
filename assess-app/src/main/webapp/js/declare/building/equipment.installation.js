/**
 * Created by kings on 2018-12-20.
 */

var equipmentInstallationConfig = {
    name: "设备安装",
    frm: "equipmentInstallationFrm",
    table: "equipmentInstallationTableList",
    box: "equipmentInstallationBox",
    fileId: "equipmentInstallationFileId",
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

equipmentInstallation.showFile = function (target, tableName, id) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            fieldsName: target,
            tableName: tableName,
            tableId: id
        },
        deleteFlag: true
    });
};

equipmentInstallation.fileUpload = function (target, tableName, id) {
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
            equipmentInstallation.showFile(target, tableName, id);
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

/**
 * @author:  zch
 * 描述:在建工程（设备安装）显示
 * @date:2018-09-27
 **/
equipmentInstallation.showAddModel = function () {
    $("#" + equipmentInstallationConfig.frm).clearAll();
    if (equipmentInstallation.equipmentInstallationFlag) {
        equipmentInstallation.init();
        equipmentInstallation.equipmentInstallationFlag = false;
    }
    equipmentInstallation.showFile(equipmentInstallationConfig.fileId, AssessDBKey.DeclareBuildEquipmentInstall, 0);
    equipmentInstallation.fileUpload(equipmentInstallationConfig.fileId, AssessDBKey.DeclareBuildEquipmentInstall, 0);
    //使校验生效
    $("#" + equipmentInstallationConfig.frm).validate();
    $('#' + equipmentInstallationConfig.box).modal("show");
};

/**
 * @author:  zch
 * 描述:在建工程（设备安装）更新
 * @date:2018-09-27
 **/
equipmentInstallation.saveAndUpdateData = function () {
    if (!$("#" + equipmentInstallationConfig.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.declareType = declareFunObj.getDeclareType("设备安装");
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareBuildEquipmentInstall/saveAndUpdateDeclareBuildEquipmentInstall",
        data: data,
        success: function (result) {
            if (result.ret) {
                equipmentInstallation.loadList();
                toastr.success('成功!');
                $('#' + equipmentInstallationConfig.box).modal("hide");
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
 * 描述:在建工程（设备安装）编辑
 * @date:2018-09-27
 **/
equipmentInstallation.editData = function (id) {
    $("#" + equipmentInstallationConfig.frm).clearAll();
    if (equipmentInstallation.equipmentInstallationFlag) {
        equipmentInstallation.init();
        equipmentInstallation.equipmentInstallationFlag = false;
    }
    $.ajax({
        url: getContextPath()+"/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var data = result.data;
                if (equipmentInstallation.isEmpty(data)) {
                    $("#" + equipmentInstallationConfig.frm).initForm(result.data);
                    equipmentInstallation.showFile(equipmentInstallationConfig.fileId, AssessDBKey.DeclareBuildEquipmentInstall, data.id);
                    equipmentInstallation.fileUpload(equipmentInstallationConfig.fileId, AssessDBKey.DeclareBuildEquipmentInstall, data.id);
                    $("#" + equipmentInstallationConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                    $("#" + equipmentInstallationConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                    $("#" + equipmentInstallationConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                    AssessCommon.initAreaInfo({
                        provinceTarget: $("#" + equipmentInstallationConfig.frm + "province"),
                        cityTarget: $("#" + equipmentInstallationConfig.frm + "city"),
                        districtTarget: $("#" + equipmentInstallationConfig.frm + "district"),
                        provinceValue: result.data.province,
                        cityValue: result.data.city,
                        districtValue: result.data.district
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
 * 描述:在建工程（设备安装）删除
 * @date:2018-09-27
 **/
equipmentInstallation.deleteData = function (id) {
    Alert("是否删除", 2, null, function () {
        $.ajax({
            type: "POST",
            url: getContextPath()+"/declareBuildEquipmentInstall/deleteDeclareBuildEquipmentInstallById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    equipmentInstallation.loadList();
                    toastr.success('成功!');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    });
};

equipmentInstallation.handleFather = function (item) {
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter",
        data: item,
        success: function (result) {
            if (result.ret) {
                equipmentInstallation.loadList();
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
equipmentInstallation.declareBuildingPermitView = function (id) {
    $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var buildingPermitId = result.data.buildingPermitId;
                if (equipmentInstallation.isEmpty(buildingPermitId)) {
                    $.ajax({
                        url: getContextPath()+"/declareBuildingPermit/getDeclareBuildingPermitById",
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
                                    equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
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
 * 描述:建设工程规划许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareBuildingPermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallationConfig.declareBuildingPermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.declareBuildingPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareBuildingPermit/saveAndUpdateDeclareBuildingPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallationConfig.declareBuildingPermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.buildingPermitId = item;
                    fData.id = data.pid ;
                    equipmentInstallation.handleFather(fData);
                    toastr.success('成功!');
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
    $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var landUsePermitId = result.data.landUsePermitId;
                if (equipmentInstallation.isEmpty(landUsePermitId)) {
                    $.ajax({
                        url: getContextPath()+"/declareLandUsePermit/getDeclareLandUsePermitById",
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
                                    equipmentInstallation.fileUpload(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
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
 * 描述:建设用地规划许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareLandUsePermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallationConfig.declareLandUsePermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.declareLandUsePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareLandUsePermit/saveAndUpdateDeclareLandUsePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallationConfig.declareLandUsePermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.landUsePermitId = item;
                    fData.id = data.pid ;
                    equipmentInstallation.handleFather(fData);
                    toastr.success('成功!');
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
    $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var preSalePermitId = result.data.preSalePermitId;
                if (equipmentInstallation.isEmpty(preSalePermitId)) {
                    $.ajax({
                        url: getContextPath()+"/declarePreSalePermit/getDeclarePreSalePermitById",
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
                                    equipmentInstallation.fileUpload(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
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
 * 描述:商品房预售许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declarePreSalePermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallationConfig.declarePreSalePermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.declarePreSalePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declarePreSalePermit/saveAndUpdateDeclarePreSalePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallationConfig.declarePreSalePermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.preSalePermitId = item;
                    fData.id = data.pid ;
                    equipmentInstallation.handleFather(fData);
                    toastr.success('成功!');
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
    $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var buildingConstructionPermitId = result.data.buildingConstructionPermitId;
                if (equipmentInstallation.isEmpty(buildingConstructionPermitId)) {
                    $.ajax({
                        url: getContextPath()+"/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
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
                                    equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
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

/**
 * @author:  zch
 * 描述:建筑工程施工许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareBuildingConstructionPermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.declareBuildingConstructionPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareBuildingConstructionPermit/saveAndUpdateDeclareBuildingConstructionPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallationConfig.declareBuildingConstructionPermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.buildingConstructionPermitId = item;
                    fData.id = data.pid ;
                    equipmentInstallation.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                console.log(result.errmsg);
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
equipmentInstallation.declareRealtyLandCertCertRoleBeLocated = {
    init: function () {
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='unit']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='unit']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='floor']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='floor']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .district").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
            if (equipmentInstallation.isEmpty(id)) {
                equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
    },
    write: function () {
        var temp = null;
        var district = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
        var unit = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='unit']").val();
        var floor = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='floor']").val();
        var roomNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val();
        var streetNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val();
        var attachedNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val();
        var buildingNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val();
        if (!equipmentInstallation.isEmpty(unit)) {
            unit = "";
        } else {
            unit = unit + "单元";
        }
        if (!equipmentInstallation.isEmpty(floor)) {
            floor = "";
        } else {
            floor = floor + "楼";
        }
        if (!equipmentInstallation.isEmpty(roomNumber)) {
            roomNumber = "";
        } else {
            roomNumber = roomNumber + "号";
        }
        if (!equipmentInstallation.isEmpty(streetNumber)) {
            streetNumber = "";
        }
        if (!equipmentInstallation.isEmpty(attachedNumber)) {
            attachedNumber = "";
        } else {
            attachedNumber = "附" + attachedNumber;
        }
        if (!equipmentInstallation.isEmpty(buildingNumber)) {
            buildingNumber = "";
        } else {
            buildingNumber = buildingNumber + "栋";
        }
        if (equipmentInstallation.isEmpty(district)) {
            AssessCommon.getAreaById(district, function (data) {
                if (!equipmentInstallation.isEmpty(data)) {
                    district = "";
                } else {
                    district = data.name;
                }
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
            });
        } else {
            district = "";
            temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
        }
    }
};
equipmentInstallation.declareRealtyLandCertCertRoleCertName = {
    init: function () {
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='location']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='location']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='year']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='year']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='number']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='number']").val())) {
                equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .type").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
            if (equipmentInstallation.isEmpty(id)) {
                equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
            }
        });
    },
    write: function () {
        var temp = "";
        var id = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
        var location = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='location']").val();
        var year = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='year']").val();
        var number = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='number']").val();
        if (equipmentInstallation.isEmpty(id)) {
            AssessCommon.getDataDicInfo(id, function (data) {
                if (equipmentInstallation.isEmpty(data)) {
                    var temp = location + data.name + year + "第" + number + "号";
                    $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                }
            });
        } else {
            var temp = location + year + "第" + number + "号";
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
        }
    }
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
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, '', function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, "", function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    equipmentInstallation.declareRealtyLandCertCertRoleCertName.init();
    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.init();
};
equipmentInstallation.declareRealtyLandCertView = function (id) {
    if (equipmentInstallation.declareRealtyLandCertFlag) {
        equipmentInstallation.declareRealtyLandCertInit();
        equipmentInstallation.declareRealtyLandCertFlag = false;
    }
    $.ajax({
        type: "get",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var landId = result.data.landId;
                $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).clearAll();
                if (equipmentInstallation.isEmpty(landId)) {
                    $.ajax({
                        url: getContextPath()+"/declareRealtyLandCert/getDeclareRealtyLandCertById",
                        type: "get",
                        dataType: "json",
                        data: {id: landId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (equipmentInstallation.isEmpty(data)) {
                                    data.pidC = id;
                                    $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).initForm(data);
                                    equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm, data.purpose, "purpose");
                                    equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm, data.type, "type");
                                    equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm, data.useRightType, "useRightType");
                                    $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                    $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                    equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                    equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                    AssessCommon.initAreaInfo({
                                        provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "province"),
                                        cityTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "city"),
                                        districtTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "district"),
                                        provinceValue: data.province,
                                        cityValue: data.city,
                                        districtValue: data.district
                                    });
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
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
equipmentInstallation.declareRealtyLandCertSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.declareRealtyLandCert.frm);
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallationConfig.declareRealtyLandCert.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.landId = item;
                    fData.id = data.pidC;
                    equipmentInstallation.handleFather(fData);
                    toastr.success('成功!');
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
 * 描述:不动产
 * @date:2018-09-28
 **/
equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated = {
    init: function () {
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .district").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
            if (equipmentInstallation.isEmpty(id)) {
                equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
    },
    write: function () {
        var temp = "";
        var district = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
        var unit = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val();
        var floor = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val();
        var roomNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val();
        var streetNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val();
        var attachedNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val();
        var buildingNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val();
        if (!equipmentInstallation.isEmpty(unit)) {
            unit = "";
        } else {
            unit = unit + "单元";
        }
        if (!equipmentInstallation.isEmpty(floor)) {
            floor = "";
        } else {
            floor = floor + "楼";
        }
        if (!equipmentInstallation.isEmpty(roomNumber)) {
            roomNumber = "";
        } else {
            roomNumber = roomNumber + "号";
        }
        if (!equipmentInstallation.isEmpty(streetNumber)) {
            streetNumber = "";
        }
        if (!equipmentInstallation.isEmpty(attachedNumber)) {
            attachedNumber = "";
        } else {
            attachedNumber = "附" + attachedNumber;
        }
        if (!equipmentInstallation.isEmpty(buildingNumber)) {
            buildingNumber = "";
        } else {
            buildingNumber = buildingNumber + "栋";
        }
        if (equipmentInstallation.isEmpty(district)) {
            AssessCommon.getAreaById(district, function (data) {
                if (!equipmentInstallation.isEmpty(data)) {
                    district = "";
                } else {
                    district = data.name;
                }
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
            });
        } else {
            district = "";
            temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
        }
    }
};
equipmentInstallation.declareRealtyRealEstateCertRoleCertName = {
    init: function () {
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='location']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleCertName.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='number']").blur(function () {
            if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val())) {
                equipmentInstallation.declareRealtyRealEstateCertRoleCertName.write();
            }
        });
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .type").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
            if (equipmentInstallation.isEmpty(id)) {
                equipmentInstallation.declareRealtyRealEstateCertRoleCertName.write();
            }
        });
    },
    write: function () {
        var location = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val();
        var number = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val();
        var id = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
        if (!equipmentInstallation.isEmpty(location)) {
            location = "";
        }
        if (!equipmentInstallation.isEmpty(number)) {
            number = "";
        }
        if (!equipmentInstallation.isEmpty(id)) {
            id = "";
        }
        if (equipmentInstallation.isEmpty(id)) {
            AssessCommon.getDataDicInfo(id, function (data) {
                if (equipmentInstallation.isEmpty(data)) {
                    var temp = location + "房权证" + data.name + "字地" + number + "号";
                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
                }
            });
        } else {
            var temp = location + "房权证" + id + "字地" + number + "号";
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
        }
    }
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
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, '', function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, "", function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, "", function (html, data) {
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    equipmentInstallation.declareRealtyRealEstateCertRoleCertName.init();
    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.init();
};
equipmentInstallation.declareRealtyRealEstateCertView = function (id) {
    if (equipmentInstallation.declareRealtyRealEstateCertFlag) {
        equipmentInstallation.declareRealtyRealEstateCertInit();
        equipmentInstallation.declareRealtyRealEstateCertFlag = false;
    }
    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var realEstateId = result.data.realEstateId;
                if (equipmentInstallation.isEmpty(realEstateId)) {
                    $.ajax({
                        url: getContextPath()+"/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                        type: "get",
                        dataType: "json",
                        data: {id: realEstateId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (equipmentInstallation.isEmpty(data)) {
                                    data.pidC = id;
                                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).initForm(data);
                                    equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm, data.purpose, "purpose");
                                    equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm, data.type, "type");
                                    equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm, data.useRightType, "useRightType");
                                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                    equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                                    equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                                    AssessCommon.initAreaInfo({
                                        provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "province"),
                                        cityTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "city"),
                                        districtTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "district"),
                                        provinceValue: data.province,
                                        cityValue: data.city,
                                        districtValue: data.district
                                    });
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
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
equipmentInstallation.declareRealtyRealEstateCertSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallationConfig.declareRealtyRealEstateCert.frm);
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallationConfig.declareRealtyRealEstateCert.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.realEstateId = item;
                    fData.id = data.pidC ;
                    equipmentInstallation.handleFather(fData);
                    toastr.success('成功!');
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
        url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: pid},
        success: function (result) {
            if (result.ret) {
                var indicatorId = result.data.indicatorId;
                if (equipmentInstallation.isEmpty(indicatorId)){
                    $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).clearAll();
                    $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('[name=pid]').val(indicatorId);
                    $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('[name=planDetailsId]').val('${projectPlanDetails.id}');
                    $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('.dynamic').remove();
                    economicIndicators.initForm(indicatorId, function () {
                        $('#' + equipmentInstallationConfig.declareEconomicIndicators.box).modal("show");
                    });
                }else {
                    $.ajax({
                        type: "POST",
                        url: getContextPath()+"/declareBuildEconomicIndicatorsCenter/saveAndUpdateDeclareBuildEconomicIndicatorsCenter",
                        data: {planDetailsId:"${projectPlanDetails.id}"},
                        success: function (result) {
                            if (result.ret) {
                                var id = result.data;
                                var fData = {};
                                fData.indicatorId = id;
                                fData.id = pid ;
                                equipmentInstallation.handleFather(fData);
                                $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).clearAll();
                                $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('[name=pid]').val(id);
                                $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('[name=planDetailsId]').val('${projectPlanDetails.id}');
                                $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('.dynamic').remove();
                                economicIndicators.initForm(id, function () {
                                    $('#' + equipmentInstallationConfig.declareEconomicIndicators.box).modal("show");
                                });
                            } else {
                                console.log(result.errmsg);
                                Alert("保存失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};


equipmentInstallation.copyId = undefined;
//复制子数据
equipmentInstallation.copyData = function (id, centerId) {
    var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getData');
    $.each(item, function (i, data) {
        $("#equipmentInstallationCancel" + data.id).parent().hide();
        $("#equipmentInstallationCopy" + data.id).parent().hide();
    });
    $.each(item, function (i, data) {
        if (data.id != id) {
            $("#equipmentInstallationPaste" + data.id).parent().show();
        }
    });
    $("#equipmentInstallationCancel" + id).parent().show();
    equipmentInstallation.copyId = centerId;
};

//粘贴子数据
equipmentInstallation.pasteData = function (id, centerId) {
    Alert("确认粘贴", 2,
        function () {//放弃操作后的函数
            $.each($("#" + equipmentInstallationConfig.table).bootstrapTable('getData'), function (i, data) {
                $("#equipmentInstallationCancel" + data.id).parent().hide();
                $("#equipmentInstallationPaste" + data.id).parent().hide();
                $("#equipmentInstallationCopy" + data.id).parent().show();
            });
            equipmentInstallation.copyId = undefined;
        },//具体操作函数
        function () {
            var copyId = equipmentInstallation.copyId;
            $.ajax({
                type: "post",
                url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter",
                data: {ids: id, copyId: copyId, type: "DeclareBuildEquipmentInstall"},
                success: function (result) {
                    if (result.ret) {
                        equipmentInstallation.loadList();
                        toastr.success('成功');
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
            equipmentInstallation.copyId = undefined;
        }
    );
};


//放弃复制
equipmentInstallation.cancelData = function (id, centerId) {
    $("#equipmentInstallationCancel" + id).parent().hide();
    $.each($("#" + equipmentInstallationConfig.table).bootstrapTable('getData'), function (i, data) {
        $("#equipmentInstallationPaste" + data.id).parent().hide();
        $("#equipmentInstallationCopy" + data.id).parent().show();
    });
    equipmentInstallation.copyId = undefined;
};

//把复制的从表粘贴到所有的列中
equipmentInstallation.pasteAll = function () {
    var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getSelections');
    if (this.isEmpty(equipmentInstallation.copyId)) {
        if (this.isEmpty(item)) {
            if (item.length >= 1) {
                var copyId = equipmentInstallation.copyId;
                var ids = "" ;
                $.each(item,function (i,n) {
                    if (i == item.length-1){
                        ids += n.id
                    }else {
                        ids += n.id+",";
                    }
                });
                $.ajax({
                    type: "post",
                    url: getContextPath()+"/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter",
                    data: {ids: ids, copyId: copyId, type: "DeclareBuildEquipmentInstall"},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('成功');
                            equipmentInstallation.loadList();
                        } else {
                            Alert("失败:" + result.errmsg);
                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
                equipmentInstallation.copyId = undefined;
            } else {
                Alert("请先勾选需要粘贴的对象!");
            }
        }
    } else {
        Alert("请先复制对象!");
    }
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
        field: 'id', title: '子类以及自身操作', formatter: function (value, row, index) {
            var str = '<div class="dropdown">';
            str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown' id='dropdownMenu2'>" + "操作" + "<span class='caret'>" + "</span>" + "</button>";
            str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.deleteData(" + row.id + ")'" + ">" + "删除" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.editData(" + row.id + ")'" + ">" + "编辑" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "</a>" + "</li>";
            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareEconomicIndicatorsView(" + row.centerId + ")'" + ">" + "经济规划指标" + "</a>" + "</li>";
            str += "</ul>";
            str += "</div>";
            return str;
        }
    });
    cols.push({
        field: 'id', title: '复制从表数据', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success tooltips" id="equipmentInstallationCopy' + row.id + '" data-placement="top" data-original-title="复制" onclick="equipmentInstallation.copyData(' + row.id + ',' + row.centerId + ')"><i class="fa fa-copy fa-white"></i></a>';
            str += '</div>';
            return str;
        }
    });
    cols.push({
        field: 'id', title: '放弃粘贴数据', formatter: function (value, row, index) {
            var str = '<div class="btn-margin" style="display: none">';
            str += '<a class="btn btn-xs btn-success tooltips" id="equipmentInstallationCancel' + row.id + '" data-placement="top" data-original-title="放弃" onclick="equipmentInstallation.cancelData(' + row.id + ',' + row.centerId + ')">放弃</a>';
            str += '</div>';
            return str;
        }
    });
    cols.push({
        field: 'id', title: '粘贴从表数据', formatter: function (value, row, index) {
            var str = '<div class="btn-margin" style="display: none">';
            str += '<a class="btn btn-xs btn-success tooltips" id="equipmentInstallationPaste' + row.id + '" data-placement="top" data-original-title="粘贴" onclick="equipmentInstallation.pasteData(' + row.id + ',' + row.centerId + ')"><i class="fa fa-paste fa-white"></i></a>';
            str += '</div>';
            return str;
        }
    });
    $("#" + equipmentInstallationConfig.table).bootstrapTable('destroy');
    TableInit(equipmentInstallationConfig.table, getContextPath()+"/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),
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

/**
 * @author:  zch
 * 描述:导入
 * @date:2018-09-28
 **/
equipmentInstallation.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath()+"/declareBuildEquipmentInstall/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: equipmentInstallationConfig.excelUpload,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                equipmentInstallation.loadList();
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

$(function () {
    equipmentInstallation.loadList();
});
