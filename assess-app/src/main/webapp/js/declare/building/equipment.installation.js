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

equipmentInstallation.deleteByType = function (data,callback) {
    $.ajax({
        type: "post",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/deleteByType",
        data: data,
        success: function (result) {
            if (result.ret) {
                callback(result);
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
 * 设备安装 初始化并赋值
 * @param item
 */
equipmentInstallation.init = function (item) {
    $("#" + equipmentInstallation.config.frm).clearAll();
    $("#" + equipmentInstallation.config.frm).initForm(item);
    //使校验生效
    $("#" + equipmentInstallation.config.frm).validate();
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + equipmentInstallation.config.frm).find("select[name='province']"),
        cityTarget: $("#" + equipmentInstallation.config.frm).find("select[name='city']"),
        districtTarget: $("#" + equipmentInstallation.config.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    equipmentInstallation.showFile(equipmentInstallation.config.fileId, AssessDBKey.DeclareBuildEquipmentInstall, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
    equipmentInstallation.fileUpload(equipmentInstallation.config.fileId, AssessDBKey.DeclareBuildEquipmentInstall, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
};

/**
 * @author:  zch
 * 描述:在建工程（设备安装）显示
 * @date:2018-09-27
 **/
equipmentInstallation.showAddModel = function () {
    $('#' + equipmentInstallation.config.box).find("#" + commonDeclareApplyModel.config.equipmentInstallation.handleId).remove();
    $('#' + equipmentInstallation.config.box).find(".panel-body").append(commonDeclareApplyModel.equipmentInstallation.getHtml());
    //由于是填充的hmtl所以需要手动初始化select2
    DatepickerUtils.parse();
    $('#' + equipmentInstallation.config.box).find(".select2").each(function () {
        $(this).select2();
    });
    //使校验生效
    $("#" + equipmentInstallation.config.frm).validate();
    $('#' + equipmentInstallation.config.box).modal("show");
    equipmentInstallation.init({});
};

/**
 * @author:  zch
 * 描述:在建工程（设备安装）更新
 * @date:2018-09-27
 **/
equipmentInstallation.saveAndUpdateData = function () {
    if (!$("#" + equipmentInstallation.config.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.declareType = declareFunObj.getDeclareType("设备安装");
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEquipmentInstall/saveAndUpdateDeclareBuildEquipmentInstall",
        data: data,
        success: function (result) {
            if (result.ret) {
                equipmentInstallation.loadList();
                toastr.success('成功!');
                $('#' + equipmentInstallation.config.box).modal("hide");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
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
 * 描述:在建工程（设备安装）编辑
 * @date:2018-09-27
 **/
equipmentInstallation.editData = function () {
    var rows = $("#" + equipmentInstallation.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        equipmentInstallation.showAddModel();
        equipmentInstallation.getData(rows[0].id, function (data) {
            equipmentInstallation.init(data);
        });
        $('#' + equipmentInstallation.config.box).modal("show");
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * @author:  zch
 * 描述:在建工程（设备安装）删除
 * @date:2018-09-27
 **/
equipmentInstallation.deleteData = function () {
    var rows = $("#" + equipmentInstallation.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要删除的数据");
    } else {
        Alert("确认要删除么？", 2, null, function () {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            $.ajax({
                type: "post",
                url: getContextPath() + "/declareBuildEquipmentInstall/deleteDeclareBuildEquipmentInstallById",
                data: {ids: idArray.join()},
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
        })
    }
};

equipmentInstallation.handleFather = function (item) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter",
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
    $('#' + equipmentInstallation.config.declareBuildingPermit.box).find("#" + commonDeclareApplyModel.config.buildingPermit.handleId).remove();
    $('#' + equipmentInstallation.config.declareBuildingPermit.box).find(".panel-body").append(commonDeclareApplyModel.buildingPermit.getHtml());
    DatepickerUtils.parse();
    $('#' + equipmentInstallation.config.declareBuildingPermit.box).modal("show");
    $("#" + equipmentInstallation.config.declareBuildingPermit.frm).clearAll();
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
                                    equipmentInstallation.fileUpload(equipmentInstallation.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + equipmentInstallation.config.declareBuildingPermit.frm).initForm({pid: id});
                    equipmentInstallation.showFile(equipmentInstallation.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                    equipmentInstallation.fileUpload(equipmentInstallation.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
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
 * 描述:建设工程规划许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareBuildingPermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallation.config.declareBuildingPermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.declareBuildingPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildingPermit/saveAndUpdateDeclareBuildingPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallation.config.declareBuildingPermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.buildingPermitId = item;
                    fData.id = data.pid;
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
equipmentInstallation.declareBuildingPermitRemove = function () {
    var data = formParams(equipmentInstallation.config.declareBuildingPermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclareBuildingPermit"};
        equipmentInstallation.deleteByType(item, function () {
            $('#' + equipmentInstallation.config.declareBuildingPermit.box).modal("hide");
            equipmentInstallation.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};

/**
 * @author:  zch
 * 描述:建设用地规划许可证 view
 * @date:2018-09-28
 **/
equipmentInstallation.declareLandUsePermitView = function (id) {
    $('#' + equipmentInstallation.config.declareLandUsePermit.box).find("#" + commonDeclareApplyModel.config.landUsePermit.handleId).remove();
    $('#' + equipmentInstallation.config.declareLandUsePermit.box).find(".panel-body").append(commonDeclareApplyModel.landUsePermit.getHtml());
    DatepickerUtils.parse();
    $('#' + equipmentInstallation.config.declareLandUsePermit.box).modal("show");
    $("#" + equipmentInstallation.config.declareLandUsePermit.frm).clearAll();
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
                                    equipmentInstallation.fileUpload(equipmentInstallation.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + equipmentInstallation.config.declareLandUsePermit.frm).initForm({pid: id});
                    equipmentInstallation.showFile(equipmentInstallation.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                    equipmentInstallation.fileUpload(equipmentInstallation.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
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
 * 描述:建设用地规划许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareLandUsePermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallation.config.declareLandUsePermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.declareLandUsePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareLandUsePermit/saveAndUpdateDeclareLandUsePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallation.config.declareLandUsePermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.landUsePermitId = item;
                    fData.id = data.pid;
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
equipmentInstallation.declareLandUsePermitRemove = function () {
    var data = formParams(equipmentInstallation.config.declareLandUsePermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclareLandUsePermit"};
        equipmentInstallation.deleteByType(item, function () {
            $('#' + equipmentInstallation.config.declareLandUsePermit.box).modal("hide");
            equipmentInstallation.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};

/**
 * @author:  zch
 * 描述:商品房预售许可证 view
 * @date:2018-09-28
 **/
equipmentInstallation.declarePreSalePermitView = function (id) {
    $('#' + equipmentInstallation.config.declarePreSalePermit.box).find("#" + commonDeclareApplyModel.config.preSalePermit.handleId).remove();
    $('#' + equipmentInstallation.config.declarePreSalePermit.box).find(".panel-body").append(commonDeclareApplyModel.preSalePermit.getHtml());
    DatepickerUtils.parse();
    $("#" + equipmentInstallation.config.declarePreSalePermit.frm).clearAll();
    $('#' + equipmentInstallation.config.declarePreSalePermit.box).modal("show");
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
                                    equipmentInstallation.fileUpload(equipmentInstallation.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + equipmentInstallation.config.declarePreSalePermit.frm).initForm({pid: id});
                    equipmentInstallation.showFile(equipmentInstallation.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                    equipmentInstallation.fileUpload(equipmentInstallation.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
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
 * 描述:商品房预售许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declarePreSalePermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallation.config.declarePreSalePermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.declarePreSalePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declarePreSalePermit/saveAndUpdateDeclarePreSalePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallation.config.declarePreSalePermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.preSalePermitId = item;
                    fData.id = data.pid;
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
equipmentInstallation.declarePreSalePermitRemove = function () {
    var data = formParams(equipmentInstallation.config.declarePreSalePermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclarePreSalePermit"};
        equipmentInstallation.deleteByType(item, function () {
            $('#' + equipmentInstallation.config.declarePreSalePermit.box).modal("hide");
            equipmentInstallation.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};

/**
 * @author:  zch
 * 描述:建筑工程施工许可证 view
 * @date:2018-09-28
 **/
equipmentInstallation.declareBuildingConstructionPermitView = function (id) {
    $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).find("#" + commonDeclareApplyModel.config.buildingConstructionPermit.handleId).remove();
    $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).find(".panel-body").append(commonDeclareApplyModel.buildingConstructionPermit.getHtml());
    DatepickerUtils.parse();
    $("#" + equipmentInstallation.config.declareBuildingConstructionPermit.frm).clearAll();
    $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).modal("show");
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
                                    equipmentInstallation.fileUpload(equipmentInstallation.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
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
                    equipmentInstallation.fileUpload(equipmentInstallation.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
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
 * 描述:建筑工程施工许可证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareBuildingConstructionPermitSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallation.config.declareBuildingConstructionPermit.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.declareBuildingConstructionPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildingConstructionPermit/saveAndUpdateDeclareBuildingConstructionPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.buildingConstructionPermitId = item;
                    fData.id = data.pid;
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
equipmentInstallation.declareBuildingConstructionPermitRemove = function () {
    var data = formParams(equipmentInstallation.config.declareBuildingConstructionPermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclareBuildingConstructionPermit"};
        equipmentInstallation.deleteByType(item, function () {
            $('#' + equipmentInstallation.config.declareBuildingConstructionPermit.box).modal("hide");
            equipmentInstallation.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};

/**
 * @author:  zch
 * 描述:土地证 初始化并且赋值
 * @date:2018-09-28
 **/
equipmentInstallation.declareRealtyLandCertInit = function (item) {
    $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).initForm(item);
    $("#" + equipmentInstallation.config.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    $("#" + equipmentInstallation.config.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    equipmentInstallation.showFile(equipmentInstallation.config.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
    equipmentInstallation.fileUpload(equipmentInstallation.config.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find("select[name='province']"),
        cityTarget: $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find("select[name='city']"),
        districtTarget: $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
};
/**
 * @author:  zch
 * 描述:土地证 视图
 * @date:2018-09-28
 **/
equipmentInstallation.declareRealtyLandCertView = function (id) {
    $('#' + equipmentInstallation.config.declareRealtyLandCert.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + equipmentInstallation.config.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApplyModel.land.getHtml());
    DatepickerUtils.parse();
    $('#' + equipmentInstallation.config.declareRealtyLandCert.box).find(".select2").each(function () {
        $(this).select2();
    });
    $('#' + equipmentInstallation.config.declareRealtyLandCert.box).modal("show");
    $("#" + equipmentInstallation.config.declareRealtyLandCert.frm).clearAll();
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
                                    data.pidC = id ;
                                    equipmentInstallation.declareRealtyLandCertInit(data);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    equipmentInstallation.getData(result.data.buildEquipmentId, function (item) {
                        equipmentInstallation.declareRealtyLandCertInit({
                            pidC: id, province: item.province,
                            city: item.city,
                            district: item.district
                        });
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
 * 描述:土地证 更新
 * @date:2018-09-28
 **/
equipmentInstallation.declareRealtyLandCertSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallation.config.declareRealtyLandCert.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.declareRealtyLandCert.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallation.config.declareRealtyLandCert.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
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
equipmentInstallation.declareRealtyLandCertRemove = function () {
    var data = formParams(equipmentInstallation.config.declareRealtyLandCert.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pidC, type: "DeclareRealtyLandCert"};
        equipmentInstallation.deleteByType(item, function () {
            $('#' + equipmentInstallation.config.declareRealtyLandCert.box).modal("hide");
            equipmentInstallation.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};


/**
 * @author:  zch
 * 描述:不动产
 * @date:2018-09-28
 **/
equipmentInstallation.declareRealtyRealEstateCertInit = function (item) {
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).initForm(item);
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(item.useEndDate));
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(item.useStartDate));
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(item.registrationTime));
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    equipmentInstallation.showFile(equipmentInstallation.config.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
    equipmentInstallation.fileUpload(equipmentInstallation.config.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, equipmentInstallation.isNotBlank(item.id) ? item.id : "0");
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("select[name='province']"),
        cityTarget: $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("select[name='city']"),
        districtTarget: $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
};
equipmentInstallation.declareRealtyRealEstateCertView = function (id) {
    $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).find(".panel-body").append(commonDeclareApplyModel.realEstateCert.getHtml());
    DatepickerUtils.parse();
    $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).find(".select2").each(function () {
        $(this).select2();
    });
    $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).modal("show");
    $("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).clearAll();
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
                                    var item = result.data ;
                                    item.pidC = id;
                                    equipmentInstallation.declareRealtyRealEstateCertInit(item);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    equipmentInstallation.getData(result.data.buildEquipmentId, function (item) {
                        equipmentInstallation.declareRealtyRealEstateCertInit({
                            pidC: id, province: item.province,
                            city: item.city,
                            district: item.district
                        });
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
equipmentInstallation.declareRealtyRealEstateCertSaveAndUpdate = function () {
    if (!$("#" + equipmentInstallation.config.declareRealtyRealEstateCert.frm).valid()) {
        return false;
    }
    var data = formParams(equipmentInstallation.config.declareRealtyRealEstateCert.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).modal("hide");
                var item = result.data;
                if (equipmentInstallation.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.realEstateId = item;
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
equipmentInstallation.declareRealtyRealEstateCertRemove = function () {
    var data = formParams(equipmentInstallation.config.declareRealtyRealEstateCert.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pidC, type: "DeclareRealtyRealEstateCert"};
        equipmentInstallation.deleteByType(item, function () {
            $('#' + equipmentInstallation.config.declareRealtyRealEstateCert.box).modal("hide");
            equipmentInstallation.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};

/**
 * 经济指标
 */
equipmentInstallation.declareEconomicIndicatorsView = function (pid) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
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
                } else {
                    $.ajax({
                        type: "POST",
                        url: getContextPath() + "/declareBuildEconomicIndicatorsCenter/saveAndUpdateDeclareBuildEconomicIndicatorsCenter",
                        data: {planDetailsId: declareCommon.getPlanDetailsId()},
                        success: function (result) {
                            if (result.ret) {
                                var id = result.data;
                                var fData = {};
                                fData.indicatorId = id;
                                fData.id = pid;
                                equipmentInstallation.handleFather(fData);
                                $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).clearAll();
                                $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).find('[name=pid]').val(id);
                                $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).find('[name=planDetailsId]').val(declareCommon.getPlanDetailsId());
                                $("#" + equipmentInstallation.config.declareEconomicIndicators.frm).find('.dynamic').remove();
                                economicIndicators.initForm(id, function () {
                                    $('#' + equipmentInstallation.config.declareEconomicIndicators.box).modal("show");
                                });
                            } else {
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


//复制子数据
equipmentInstallation.copyData = function () {
    var rows = $("#" + equipmentInstallation.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要copy的数据");
    } else if (rows.length == 1) {
        $.ajax({
            url: getContextPath() + "/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById",
            type: "get",
            dataType: "json",
            data: {id: rows[0].id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#" + equipmentInstallation.config.copy).find("input[name='name']").val(result.data.name);
                        $("#" + equipmentInstallation.config.copy).find("input[name='id']").val(result.data.centerId);
                        $("#" + equipmentInstallation.config.table).bootstrapTable('uncheckAll');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    } else {
        toastr.info("只能选择一行数据进行copy");
    }
};


//把复制的从表粘贴到所有的列中
equipmentInstallation.pasteAll = function () {
    var item = $("#" + equipmentInstallation.config.table).bootstrapTable('getSelections');
    if (item.length < 1) {
        toastr.info("至少选择一行");
        return false;
    }
    var copyId = $("#" + equipmentInstallation.config.copy).find("input[name='id']").val();
    if (copyId) {
        var ids = "";
        var select = true;
        $.each(item, function (i, n) {
            if (i == item.length - 1) {
                ids += n.id
            } else {
                ids += n.id + ",";
            }
            //检测是否自己复制自己,这样情况是不被允许的
            if (copyId == n.id) {
                select = false;
            }
        });
        if (!select) {
            toastr.info("自己复制自己,这样情况是不被允许的");
            return false;
        }
        if (select) {
            $.ajax({
                type: "post",
                url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter",
                data: {ids: ids, copyId: copyId, type: "DeclareBuildEquipmentInstall"},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('成功');
                        equipmentInstallation.loadList();
                        $("#" + equipmentInstallation.config.copy).find("input").each(function () {
                            $(this).val('');
                        });
                    } else {
                        Alert("失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
    } else {
        toastr.info("没有copy从数据");
        return false;
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
        field: 'id', title: '关联信息', formatter: function (value, row, index) {
            var str = '<div class="dropdown">';
            str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown' id='dropdownMenu2'>" + "<i class='fa fa-users'>" + "</i>" + "关联信息" + "<span class='caret'>" + "</span>" + "</button>";
            str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
            if (row.declareBuildEngineeringAndEquipmentCenter.landId) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
            } else {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
            }
            if (row.declareBuildEngineeringAndEquipmentCenter.realEstateId) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
            } else {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
            }
            if (row.declareBuildEngineeringAndEquipmentCenter.buildingPermitId) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
            } else {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
            }
            if (row.declareBuildEngineeringAndEquipmentCenter.landUsePermitId) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
            } else {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
            }
            if (row.declareBuildEngineeringAndEquipmentCenter.buildingConstructionPermitId) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
            } else {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
            }
            if (row.declareBuildEngineeringAndEquipmentCenter.preSalePermitId) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
            } else {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareEconomicIndicatorsView(" + row.centerId + ")'" + ">" + "经济规划指标" + "<i class='fa fa-adjust'></i>" + "</a>" + "</li>";
            }
            str += "</ul>";
            str += "</div>";
            return str;
        }
    });
    $("#" + equipmentInstallation.config.table).bootstrapTable('destroy');
    TableInit(equipmentInstallation.config.table, getContextPath() + "/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallList", cols, {
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
        url: getContextPath() + "/declareBuildEquipmentInstall/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: equipmentInstallation.config.excelUpload,//文件选择框的id属性
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
