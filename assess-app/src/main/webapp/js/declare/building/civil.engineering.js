/**
 * Created by kings on 2018-12-20.
 */
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

//从表数据删除
civilEngineering.deleteByType = function (data, callback) {
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
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
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
    civilEngineering.fileUpload(civilEngineering.config.fileId, AssessDBKey.DeclareBuildEngineering, civilEngineering.isNotBlank(item.id) ? item.id : "0");
    $("#" + civilEngineering.config.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    $("#" + civilEngineering.config.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + civilEngineering.config.frm).find("select[name='province']"),
        cityTarget: $("#" + civilEngineering.config.frm).find("select[name='city']"),
        districtTarget: $("#" + civilEngineering.config.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + civilEngineering.config.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + civilEngineering.config.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + civilEngineering.config.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    //使校验生效
    $("#" + civilEngineering.config.frm).validate();
};

/**
 * @author:  zch
 * 描述:在建工程（土建）显示
 * @date:2018-09-27
 **/
civilEngineering.showAddModel = function () {
    $('#' + civilEngineering.config.box).modal("show");
    $('#' + civilEngineering.config.box).find("#" + commonDeclareApplyModel.config.civilEngineering.handleId).remove();
    $('#' + civilEngineering.config.box).find(".panel-body").prepend(commonDeclareApplyModel.civilEngineering.getHtml());
    mapPosition.getCurrentCityByArea(function (area) {
        civilEngineering.init(area);
    });
    try {
        //由于是填充的hmtl所以需要手动初始化select2
        DatepickerUtils.parse();
        $('#' + civilEngineering.config.box).find(".select2").each(function () {
            $(this).select2();
        });
    } catch (e) {
    }
};

/**
 * @author:  zch
 * 描述:在建工程（土建）删除
 * @date:2018-09-27
 **/
civilEngineering.deleteData = function () {

    var rows = $("#" + civilEngineering.config.table).bootstrapTable('getSelections');
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
                url: getContextPath() + "/declareBuildEngineering/deleteDeclareBuildEngineeringById",
                data: {ids: idArray.join()},
                success: function (result) {
                    if (result.ret) {
                        civilEngineering.loadList();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + e);
                }
            });
        })
    }
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
            AlertError("失败","调用服务端方法失败，失败原因:" + result);
        }
    });
};

/**
 * @author:  zch
 * 描述:在建工程（土建）编辑
 * @date:2018-09-27
 **/
civilEngineering.editData = function () {
    var rows = $("#" + civilEngineering.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        civilEngineering.showAddModel();
        civilEngineering.getData(rows[0].id, function (data) {
            civilEngineering.init(data);
        });
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * @author:  zch
 * 描述:在建工程（土建）更新
 * @date:2018-09-27
 **/
civilEngineering.saveAndUpdateData = function () {
    if (!$("#" + civilEngineering.config.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.declareType = declareFunObj.getDeclareType("土建");
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineering/saveAndUpdateDeclareBuildEngineering",
        data: data,
        success: function (result) {
            if (result.ret) {
                civilEngineering.loadList();
                toastr.success('成功!');
                $('#' + civilEngineering.config.box).modal("hide");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:更新绑定到父类上的子类
 * @date:2018-09-28
 **/
civilEngineering.handleFather = function (item) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter",
        data: item,
        success: function (result) {
            if (result.ret) {
                civilEngineering.loadList();
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
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
    civilEngineering.fileUpload(civilEngineering.config.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, civilEngineering.isNotBlank(item.id) ? item.id : "0");
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find("select[name='province']"),
        cityTarget: $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find("select[name='city']"),
        districtTarget: $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
};
/**
 * @author:  zch
 * 描述:不动产 视图
 * @date:2018-09-28
 **/
civilEngineering.declareRealtyRealEstateCertView = function (id) {
    $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).find(".panel-body").append(commonDeclareApplyModel.realEstateCert.getHtml());
    DatepickerUtils.parse();
    $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).find(".select2").each(function () {
        $(this).select2();
    });
    $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).modal("show");
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
                                    var item = result.data;
                                    item.pidC = id;
                                    civilEngineering.declareRealtyRealEstateCertInit(item);
                                }
                            }
                        },
                        error: function (result) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    civilEngineering.getData(data.buildEngineeringId, function (data) {
                        civilEngineering.declareRealtyRealEstateCertInit({
                            pidC: id,
                            province: data.province,
                            city: data.city,
                            district: data.district
                        });
                    });
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};
civilEngineering.declareRealtyRealEstateCertSaveAndUpdate = function () {
    if (!$("#" + civilEngineering.config.declareRealtyRealEstateCert.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.declareRealtyRealEstateCert.frm);
    if (data.id){

    }else {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.enable = declareCommon.branchData;
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: {formData:JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isNotBlank(item)) {
                    //把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)  久
                    //把新增的子类关联道中间表
                    var pidC = data.pidC;
                    var fData = {};
                    fData.realEstateId = item;
                    fData.id = pidC;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};
civilEngineering.declareRealtyRealEstateCertRemove = function () {
    var data = formParams(civilEngineering.config.declareRealtyRealEstateCert.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pidC, type: "DeclareRealtyRealEstateCert"};
        civilEngineering.deleteByType(item, function () {
            $('#' + civilEngineering.config.declareRealtyRealEstateCert.box).modal("hide");
            civilEngineering.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
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
    civilEngineering.fileUpload(civilEngineering.config.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, civilEngineering.isNotBlank(item.id) ? item.id : "0");
    $("#" + civilEngineering.config.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    $("#" + civilEngineering.config.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + civilEngineering.config.declareRealtyLandCert.frm).find("select[name='province']"),
        cityTarget: $("#" + civilEngineering.config.declareRealtyLandCert.frm).find("select[name='city']"),
        districtTarget: $("#" + civilEngineering.config.declareRealtyLandCert.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + civilEngineering.config.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });

};
civilEngineering.declareRealtyLandCertView = function (id) {
    $('#' + civilEngineering.config.declareRealtyLandCert.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + civilEngineering.config.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApplyModel.land.getHtml());
    DatepickerUtils.parse();
    $('#' + civilEngineering.config.declareRealtyLandCert.box).find(".select2").each(function () {
        $(this).select2();
    });
    $('#' + civilEngineering.config.declareRealtyLandCert.box).modal("show");
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
                                    var item = result.data;
                                    item.pidC = id;
                                    civilEngineering.declareRealtyLandCertInit(item);
                                }
                            }
                        },
                        error: function (result) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    civilEngineering.getData(data.buildEngineeringId, function (item) {
                        civilEngineering.declareRealtyLandCertInit({
                            pidC: id,
                            province: item.province,
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
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};
civilEngineering.declareRealtyLandCertSaveAndUpdate = function () {
    if (!$("#" + civilEngineering.config.declareRealtyLandCert.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.declareRealtyLandCert.frm);
    if (data.id){

    }else {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.enable = declareCommon.branchData;
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: {formData:JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineering.config.declareRealtyLandCert.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.landId = item;
                    fData.id = data.pidC;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};
civilEngineering.declareRealtyLandCertRemove = function () {
    var data = formParams(civilEngineering.config.declareRealtyLandCert.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pidC, type: "DeclareRealtyLandCert"};
        civilEngineering.deleteByType(item, function () {
            $('#' + civilEngineering.config.declareRealtyLandCert.box).modal("hide");
            civilEngineering.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无删除数据!');
    }
};

/**
 * @author:  zch
 * 描述:建设工程规划许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingPermitView = function (id) {
    $('#' + civilEngineering.config.declareBuildingPermit.box).find("#" + commonDeclareApplyModel.config.buildingPermit.handleId).remove();
    $('#' + civilEngineering.config.declareBuildingPermit.box).find(".panel-body").append(commonDeclareApplyModel.buildingPermit.getHtml());
    DatepickerUtils.parse();
    $("#" + civilEngineering.config.declareBuildingPermit.frm).clearAll();
    $('#' + civilEngineering.config.declareBuildingPermit.box).modal("show");
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
                                    civilEngineering.fileUpload(civilEngineering.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineering.config.declareBuildingPermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineering.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                    civilEngineering.fileUpload(civilEngineering.config.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:建设工程规划许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingPermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineering.config.declareBuildingPermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.declareBuildingPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildingPermit/saveAndUpdateDeclareBuildingPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineering.config.declareBuildingPermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.buildingPermitId = item;
                    fData.id = data.pid
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:建设工程规划许可证 删除
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingPermitRemove = function () {
    var data = formParams(civilEngineering.config.declareBuildingPermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclareBuildingPermit"};
        civilEngineering.deleteByType(item, function () {
            $('#' + civilEngineering.config.declareBuildingPermit.box).modal("hide");
            civilEngineering.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无数据!');
    }
};

/**
 * @author:  zch
 * 描述:建设用地规划许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declareLandUsePermitView = function (id) {
    $('#' + civilEngineering.config.declareLandUsePermit.box).find("#" + commonDeclareApplyModel.config.landUsePermit.handleId).remove();
    $('#' + civilEngineering.config.declareLandUsePermit.box).find(".panel-body").append(commonDeclareApplyModel.landUsePermit.getHtml());
    DatepickerUtils.parse();
    $('#' + civilEngineering.config.declareLandUsePermit.box).modal("show");
    $("#" + civilEngineering.config.declareLandUsePermit.frm).clearAll();
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
                                    civilEngineering.fileUpload(civilEngineering.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineering.config.declareLandUsePermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineering.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                    civilEngineering.fileUpload(civilEngineering.config.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });

};

/**
 * @author:  zch
 * 描述:建设用地规划许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declareLandUsePermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineering.config.declareLandUsePermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.declareLandUsePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareLandUsePermit/saveAndUpdateDeclareLandUsePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineering.config.declareLandUsePermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var pid = data.pid;
                    var fData = {};
                    fData.landUsePermitId = item;
                    fData.id = data.pid;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * 建设用地规划许可证 删除
 */
civilEngineering.declareLandUsePermitRemove = function () {
    var data = formParams(civilEngineering.config.declareLandUsePermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclareLandUsePermit"};
        civilEngineering.deleteByType(item, function () {
            $('#' + civilEngineering.config.declareLandUsePermit.box).modal("hide");
            civilEngineering.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无数据!');
    }
};

/**
 * @author:  zch
 * 描述:商品房预售许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declarePreSalePermitView = function (id) {
    $('#' + civilEngineering.config.declarePreSalePermit.box).find("#" + commonDeclareApplyModel.config.preSalePermit.handleId).remove();
    $('#' + civilEngineering.config.declarePreSalePermit.box).find(".panel-body").append(commonDeclareApplyModel.preSalePermit.getHtml());
    DatepickerUtils.parse();
    $('#' + civilEngineering.config.declarePreSalePermit.box).modal("show");
    $("#" + civilEngineering.config.declarePreSalePermit.frm).clearAll();
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
                                    civilEngineering.fileUpload(civilEngineering.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineering.config.declarePreSalePermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineering.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                    civilEngineering.fileUpload(civilEngineering.config.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });

};

/**
 * @author:  zch
 * 描述:商品房预售许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declarePreSalePermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineering.config.declarePreSalePermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.declarePreSalePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declarePreSalePermit/saveAndUpdateDeclarePreSalePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineering.config.declarePreSalePermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.preSalePermitId = item;
                    fData.id = data.pid;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};

//商品房预售许可证删除
civilEngineering.declarePreSalePermitRemove = function () {
    var data = formParams(civilEngineering.config.declarePreSalePermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclarePreSalePermit"};
        civilEngineering.deleteByType(item, function () {
            $('#' + civilEngineering.config.declarePreSalePermit.box).modal("hide");
            civilEngineering.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无数据!');
    }
};

/**
 * @author:  zch
 * 描述:建筑工程施工许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingConstructionPermitView = function (id) {
    $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).find("#" + commonDeclareApplyModel.config.buildingConstructionPermit.handleId).remove();
    $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).find(".panel-body").append(commonDeclareApplyModel.buildingConstructionPermit.getHtml());
    DatepickerUtils.parse();
    $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).modal("show");
    $("#" + civilEngineering.config.declareBuildingConstructionPermit.frm).clearAll();
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
                                    civilEngineering.fileUpload(civilEngineering.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineering.config.declareBuildingConstructionPermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineering.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                    civilEngineering.fileUpload(civilEngineering.config.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });

};


/**
 * @author:  zch
 * 描述:建筑工程施工许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingConstructionPermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineering.config.declareBuildingConstructionPermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineering.config.declareBuildingConstructionPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildingConstructionPermit/saveAndUpdateDeclareBuildingConstructionPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isNotBlank(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var pid = data.pid;
                    var fData = {};
                    fData.buildingConstructionPermitId = item;
                    fData.id = data.pid;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                console.log(result.errmsg);
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};

//建筑工程施工许可证 删除
civilEngineering.declareBuildingConstructionPermitRemove = function () {
    var data = formParams(civilEngineering.config.declareBuildingConstructionPermit.frm);
    if (data.id) {
        var item = {dataId: data.id, centerId: data.pid, type: "DeclareBuildingConstructionPermit"};
        civilEngineering.deleteByType(item, function () {
            $('#' + civilEngineering.config.declareBuildingConstructionPermit.box).modal("hide");
            civilEngineering.loadList();
            toastr.success('成功!');
        });
    } else {
        toastr.success('无数据!');
    }
};

/**
 * 经济指标 show
 */
civilEngineering.declareEconomicIndicatorsView = function (pid) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: pid},
        success: function (result) {
            if (result.ret) {
                var indicatorId = result.data.indicatorId;
                if (civilEngineering.isNotBlank(indicatorId)) {
                    $('#' + civilEngineering.config.declareEconomicIndicators.frm).find("#" + commonDeclareApplyModel.config.economicIndicators.handleId).remove();
                    $('#' + civilEngineering.config.declareEconomicIndicators.frm).find(".panel-body").append(commonDeclareApplyModel.economicIndicators.getHtml());
                    DatepickerUtils.parse();
                    $("#" + civilEngineering.config.declareEconomicIndicators.frm).clearAll();
                    $("#" + civilEngineering.config.declareEconomicIndicators.frm).find('[name=pid]').val(indicatorId);
                    $("#" + civilEngineering.config.declareEconomicIndicators.frm).find('.dynamic').remove();
                    commonDeclareApplyModel.economicIndicators.initForm(indicatorId,function () {
                        $('#' + civilEngineering.config.declareEconomicIndicators.box).modal("show");
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
                                civilEngineering.handleFather(fData);
                                $('#' + civilEngineering.config.declareEconomicIndicators.frm).find("#" + commonDeclareApplyModel.config.economicIndicators.handleId).remove();
                                $('#' + civilEngineering.config.declareEconomicIndicators.frm).find(".panel-body").append(commonDeclareApplyModel.economicIndicators.getHtml());
                                DatepickerUtils.parse();
                                $("#" + civilEngineering.config.declareEconomicIndicators.frm).clearAll();
                                $("#" + civilEngineering.config.declareEconomicIndicators.frm).find('[name=pid]').val(id);
                                $("#" + civilEngineering.config.declareEconomicIndicators.frm).find('.dynamic').remove();
                                commonDeclareApplyModel.economicIndicators.initForm(id,function () {
                                    $('#' + civilEngineering.config.declareEconomicIndicators.box).modal("show");
                                });
                            } else {
                                Alert("保存失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            AlertError("失败","调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};
/**
 * 经济指标 保存
 * @returns {boolean}
 */
civilEngineering.declareEconomicIndicatorsSaveAndUpdate = function () {
    if (!$('#'+civilEngineering.config.declareEconomicIndicators.frm).valid()) {
        return false;
    }
    try {
        var item = formParams(civilEngineering.config.declareEconomicIndicators.frm) ;
        var formData = commonDeclareApplyModel.economicIndicators.getFormData();
        var data = {
            formData: JSON.stringify(formData),
            pid:item.pid
        };
        $.ajax({
            beforeSend: function () {
                Loading.progressShow();
            },
            url: getContextPath()+"/economicIndicators/saveEconomicIndicatorsList",
            type: 'post',
            data: data,
            dataType: 'json',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    civilEngineering.loadList();
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    } catch (e) {
        toastr.success('保存失败');
        console.error(e);
    }
    $('#' + civilEngineering.config.declareEconomicIndicators.box).modal("hide");
};
/**
 * 经济指标删除
 */
civilEngineering.declareEconomicIndicatorsRemove = function () {
    var pid = formParams(civilEngineering.config.declareEconomicIndicators.frm).pid;
    function get(id, callback) {
        $.ajax({
            type: "get",
            url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/listDeclareBuildEngineeringAndEquipmentCenter",
            data: {planDetailsId: declareCommon.getPlanDetailsId(), indicatorId: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        callback(result.data[0]);
                    }
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                AlertError("失败","调用服务端方法失败，失败原因:" + e);
            }
        });
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/economicIndicators/getEntityListByPid",
        data: {pid: pid},
        success: function (result) {
            if (result.ret) {
                if (result.data.length >= 1) {
                    get(pid, function (data) {
                        var item = {dataId: pid, centerId: data.id, type: "DeclareBuildEconomicIndicatorsCenter"};
                        civilEngineering.deleteByType(item, function () {
                            $('#' + civilEngineering.config.declareEconomicIndicators.box).modal("hide");
                            civilEngineering.loadList();
                            toastr.success('成功!');
                        });
                    });
                } else {
                    toastr.success('无数据!');
                }
            } else {
                Alert("失败:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("失败","调用服务端方法失败，失败原因:" + e);
        }
    });
};


//复制子数据
civilEngineering.copyData = function () {
    var rows = $("#" + civilEngineering.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要copy的数据");
    } else if (rows.length == 1) {
        $.ajax({
            url: getContextPath() + "/declareBuildEngineering/getDeclareBuildEngineeringById",
            type: "get",
            dataType: "json",
            data: {id: rows[0].id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#" + civilEngineering.config.copy).find("input[name='name']").val(result.data.name);
                        $("#" + civilEngineering.config.copy).find("input[name='id']").val(result.data.centerId);
                        $("#" + civilEngineering.config.table).bootstrapTable('uncheckAll');
                        toastr.info("copy成功!");
                    }
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        });
    } else {
        toastr.info("只能选择一行数据进行copy");
    }
};


//把复制的从表粘贴到所有的列中
civilEngineering.pasteAll = function () {
    var item = $("#" + civilEngineering.config.table).bootstrapTable('getSelections');
    if (item.length < 1) {
        toastr.info("至少选择一行");
        return false;
    }
    var copyId = $("#" + civilEngineering.config.copy).find("input[name='id']").val();
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
                data: {ids: ids, copyId: copyId, type: "DeclareBuildEngineering"},
                success: function (result) {
                    if (result.ret) {
                        $("#" + civilEngineering.config.copy).find("input").each(function () {
                            $(this).val('');
                        });
                        civilEngineering.loadList();
                        toastr.success('成功');
                    } else {
                        console.log(result);
                        Alert("失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    console.log(e);
                    AlertError("失败","调用服务端方法失败，失败原因:" + e);
                }
            });
        }
    } else {
        toastr.info("没有copy从数据");
        return false;
    }
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
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                if (row.declareBuildEngineeringAndEquipmentCenter.landId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                } else {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyLandCertView(" + row.centerId + ")'" + ">" + "土地证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
                }
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                if (row.declareBuildEngineeringAndEquipmentCenter.realEstateId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                } else {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyRealEstateCertView(" + row.centerId + ")'" + ">" + "不动产" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
                }
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                if (row.declareBuildEngineeringAndEquipmentCenter.buildingPermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                } else {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingPermitView(" + row.centerId + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
                }
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                if (row.declareBuildEngineeringAndEquipmentCenter.landUsePermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                } else {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareLandUsePermitView(" + row.centerId + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
                }
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                if (row.declareBuildEngineeringAndEquipmentCenter.buildingConstructionPermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                } else {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingConstructionPermitView(" + row.centerId + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
                }
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                if (row.declareBuildEngineeringAndEquipmentCenter.preSalePermitId) {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-check'></i>" + "</a>" + "</li>";
                } else {
                    str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declarePreSalePermitView(" + row.centerId + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-remove'></i>" + "</a>" + "</li>";
                }
            }
            if (row.declareBuildEngineeringAndEquipmentCenter) {
                str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareEconomicIndicatorsView(" + row.centerId + ")'" + ">" + "经济规划指标" + "<i class='fa fa-adjust'></i>" + "</a>" + "</li>";
            }
            str += "</ul>";
            str += "</div>";
            return str;
        }
    });
    $("#" + civilEngineering.config.table).bootstrapTable('destroy');
    TableInit(civilEngineering.config.table, getContextPath() + "/declareBuildEngineering/getDeclareBuildEngineeringList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId()
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


/**
 * @author:  zch
 * 描述:导入
 * @date:2018-09-28
 **/
civilEngineering.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineering/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            declareType:declareFunObj.getDeclareType("土建")
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: civilEngineering.config.excelUpload,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                civilEngineering.loadList();
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("失败","调用服务端方法失败，失败原因:" + result);
        }
    });
};

$(function () {
    civilEngineering.loadList();
});

