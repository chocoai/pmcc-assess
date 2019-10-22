/**
 * Created by kings on 2018-12-20.
 */
var declareCommon = {};

declareCommon.config = {
    land: {
        frm: "frmDeclareRealtyLandCert",
        name: "土地证",
        table: "tableDeclareRealtyLandCert",
        box: "boxDeclareRealtyLandCert",
        fileId: "declareRealtyLandCertFileId",
        newFileId: "declareRealtyLandCertNewFileId",
        houseFileId: "declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId",
        newHouseFileId: "declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId",
        HouseCert: {
            frm: "declareRealtyLandCert_HouseCert_frm",
            box: "declareRealtyLandCert_HouseCert_box"
        }
    },
    house: {
        frm: "frmDeclareRealtyHouseCert",
        name: "房产证",
        table: "tableDeclareRealtyHouseCert",
        box: "boxDeclareRealtyHouseCert",
        fileId: "declareRealtyHouseCertFileId",
        fileIdNew: "declareRealtyHouseCertFileIdNew",
        fileViewNew: "declareRealtyHouseCertFileViewNew",
        landFileId: "declareRealtyHouseCert_land_FileId",
        son: {
            declareRealtyLandCert: {
                frm: "frmSonDeclareRealtyLandCert",
                box: "boxSonDeclareRealtyLandCert",
                view: "viewSonDeclareRealtyLandCertCert",
                fileId: "sonDeclareRealtyLandCertFileId",
                name: "土地",
                table: "tableSonDeclareRealtyLandCert"
            }
        }
    },
    declareRealty: {
        frm: "frmDeclareRealtyRealEstateCert",
        name: "不动产证",
        table: "tableDeclareRealtyRealEstateCert",
        box: "boxDeclareRealtyRealEstateCert",
        fileId: "declareRealtyRealEstateCertFileId",
        newFileId: "declareRealtyRealEstateCertNewFileId",
        fileView: "declareRealtyRealEstateCertFileView"
    },
    declareEconomicIndicatorsHead: {
        frm: "frmDeclareEconomicIndicatorsHead",
        name: "经济指标1",
        box: "boxDeclareEconomicIndicatorsHead"
    },
    declareEconomicIndicatorsContent: {
        frm: "frmDeclareEconomicIndicatorsContent",
        name: "经济指标2"
    },
    declareEconomicIndicatorsHead2: {
        frm: "frmDeclareEconomicIndicatorsHeadRealtyRealEstate",
        name: "经济指标1",
        box: "boxDeclareEconomicIndicatorsHeadRealtyRealEstate"
    },
    declareEconomicIndicatorsContent2: {
        frm: "frmDeclareEconomicIndicatorsContentRealtyRealEstate",
        name: "经济指标2"
    }
};

declareCommon.declareCenterData = {
    buildEngineeringId: {name: "在建工程（土建）", field: "buildEngineeringId", type: "DeclareBuildEngineering"},
    buildEquipmentId: {name: "在建工程（设备安装）", field: "buildEquipmentId", type: "DeclareBuildEquipmentInstall"},
    houseId: {name: "房产证", field: "houseId", type: "DeclareRealtyHouseCert"},
    buildingConstructionPermitId: {
        name: "建筑工程施工许可证",
        field: "buildingConstructionPermitId",
        type: "DeclareBuildingConstructionPermit"
    },
    buildingPermitId: {name: "建设工程规划许可证", field: "buildingPermitId", type: "DeclareBuildingPermit"},
    landUsePermitId: {name: "建设用地规划许可证", field: "landUsePermitId", type: "DeclareLandUsePermit"},
    preSalePermitId: {name: "商品房预售许可证", field: "preSalePermitId", type: "DeclarePreSalePermit"},
    landId: {name: "土地证", field: "landId", type: "DeclareRealtyLandCert"},
    realEstateId: {name: "不动产", field: "realEstateId", type: "DeclareRealtyRealEstateCert"},
    indicatorId: {name: "经济指标", field: "indicatorId", type: "MdEconomicIndicators"},
    indicatorIdOld: {name: "待删除经济指标", field: "indicatorId", type: "DeclareBuildEconomicIndicatorsCenter"},
    indicatorIdDelHtml:"<input type=\"button\" class=\"btn btn-warning\" value=\"删除\" onclick=\"{method}('{frm}','{box}');\">"
};

declareCommon.declareApplyForm = $('#declareApplyForm');

declareCommon.getPlanDetailsId = function () {
    return declareCommon.declareApplyForm.find('[name=planDetailsId]').val();
};
declareCommon.getProjectId = function () {
    return declareCommon.declareApplyForm.find('[name=projectId]').val();
};
//来源 com.copower.pmcc.assess.common.enums.DeclareTypeEnum
declareCommon.masterData = "master";//主数据
declareCommon.branchData = "branch";//从数据

declareCommon.declareHouseType = "";//房产申报类型
declareCommon.declareLandType = "";//土地申报类型
declareCommon.declareRealType = "";//不动产申报类型

declareCommon.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName,
            // projectId: id
        },
        deleteFlag: deleteFlag
    });
    // FileUtils.uploadFiles({
    //     target: target,
    //     disabledTarget: "btn_submit",
    //     onUpload: function (file) {
    //         var formData = {
    //             fieldsName: target,
    //             tableName: tableName,
    //             tableId: id
    //         };
    //         return formData;
    //     }, onUploadComplete: function (result, file) {
    //
    //     },
    //     deleteFlag: true
    // });
};

declareCommon.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName,
            // projectId: id
        },
        deleteFlag: deleteFlag
    })
};

declareCommon.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};
//必要的,由于新添加html dom 很多样式未启动,所有手动开启
declareCommon.showHtmlMastInit = function (target, callback) {
    try {
        mapPosition.getCurrentCityByArea(function (area) {
            //由于是填充的hmtl所以需要手动初始化select2
            DatepickerUtils.parse();
            target.find(".select2").each(function () {
                $(this).select2();
            });
            target.clearAll();
            target.validate();
            callback(area);
        });
    } catch (e) {
        //由于是填充的hmtl所以需要手动初始化select2
        DatepickerUtils.parse();
        target.find(".select2").each(function () {
            $(this).select2();
        });
        target.clearAll();
        target.validate();
        callback({});
    }
};

//土地
declareCommon.getLandColumn = function () {
    var cols = [];
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'landCertName', title: '土地权证号'});
    cols.push({field: 'beLocated', title: '房屋坐落'});
    cols.push({field: 'landNumber', title: '地号'});
    cols.push({field: 'graphNumber', title: '图号'});
    cols.push({field: 'useRightArea', title: '使用权面积'});
    return cols;
};

//房产
declareCommon.getHouseColumn = function () {
    var cols = [];
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    // cols.push({
    //     field: 'bisRecord', title: '是否参与查勘任务', formatter: function (value, row, index) {
    //         if (row.bisRecord){
    //             return "已经参与" ;
    //         }
    //         return "暂时没有参与下一阶段任务";
    //     }
    // });
    cols.push({field: 'certName', title: '房屋权证号'});
    cols.push({field: 'beLocated', title: '房屋坐落'});
    cols.push({field: 'ownership', title: '房屋所有权人'});
    cols.push({field: 'floorArea', title: '建筑面积'});
    cols.push({field: 'planningUseName', title: '规划用途'});
    return cols;
};

//不动产
declareCommon.getRealEstateColumn = function () {
    var cols = [];
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'evidenceArea', title: '证载面积'});
    cols.push({field: 'certName', title: '不动产权证号'});
    cols.push({field: 'beLocated', title: '房屋坐落'});
    // cols.push({
    //     field: 'bisRecord', title: '是否参与查勘任务', formatter: function (value, row, index) {
    //         if (row.bisRecord){
    //             return "已经参与" ;
    //         }
    //         return "暂时没有参与下一阶段任务";
    //     }
    // });
    return cols;
};

//土建
declareCommon.getCivilEngineeringColumn = function () {
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
    return cols;
};

//设备安装
declareCommon.getEquipmentInstallationColumn = function () {
    var cols = [];
    cols.push({
        field: 'area', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'bookEquipmentFee', title: '账面设备费', visible: false});
    cols.push({field: 'bookCapitalCost', title: '账面资金成本', visible: false});
    cols.push({field: 'bookInstallationFee', title: '账面安装费'});
    cols.push({field: 'declarer', title: '申报人'});
    cols.push({field: 'beLocated', title: '坐落'});
    cols.push({field: 'centerId', title: '中间表id', visible: true});
    cols.push({field: 'fileViewName', title: '附件'});
    return cols;
};

/**
 * 申报 中间表
 * @param item
 * @param callback
 */
declareCommon.declareBuildCenterSaveAndUpdate = function (item, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter",
        data: item,
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
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
 * 申报中间表 获取
 * @param id
 * @param callback
 */
declareCommon.getDeclareBuildCenter = function (id, callback) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
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
 * 需要传入 type  , centerId
 * 申报中间表 根据type删除子项id
 * @param centerId
 * @param type
 * @param callback
 */
declareCommon.deleteByDeclareBuildCenterType = function (centerId, type, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/deleteByType",
        data: {centerId: centerId, type: type},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
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
 * 申报中间表 删除
 * @param id
 * @param callback
 */
declareCommon.deleteDeclareBuildCenter = function (id, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/deleteDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback();
                }
            } else {
                Alert("失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

declareCommon.copyDeclareBuildCenter = function (copyId, ids, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter",
        data: {copyId: copyId, ids: ids},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback();
                }
            } else {
                Alert("失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};


declareCommon.saveLandData = function (data, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: {formData: JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

declareCommon.getLandData = function (id, callback, errCallback) {
    $.ajax({
        url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            var flag = false;
            if (result.ret) {
                if (result.data) {
                    flag = true;
                }
            }
            if (flag) {
                callback(result.data);
            } else {
                if (errCallback) {
                    errCallback("数据异常!");
                }
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

declareCommon.deleteHouseData = function (ids, callback) {
    $.ajax({
        url: getContextPath() + "/declareRealtyHouseCert/deleteDeclareRealtyHouseCertById",
        type: "post",
        dataType: "json",
        data: {ids: ids},
        success: function (result) {
            if (result.ret) {
                callback();
            }
            else {
                Alert("保存数据失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    })
};

declareCommon.deleteLandData = function (ids, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/deleteDeclareRealtyLandCertById",
        data: {ids: ids},
        success: function (result) {
            if (result.ret) {
                callback();
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

declareCommon.saveHouseData = function (data, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
        data: {formData: JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

declareCommon.getHouseData = function (id, callback) {
    $.ajax({
        url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
        type: "get",
        dataType: "json",
        data: {id: id, planDetailsId: declareCommon.getPlanDetailsId()},
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    })
};

//不动产获取
declareCommon.getDeclareRealtyData = function (id, callback) {
    $.ajax({
        url: getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

//不动产 delete
declareCommon.deleteDeclareRealtyData = function (ids, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/deleteDeclareRealtyRealEstateCertById",
        data: {ids: ids},
        success: function (result) {
            if (result.ret) {
                callback();
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

//不动产save
declareCommon.saveDeclareRealtyData = function (data, callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: {formData: JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

//房产初始化并且赋值
declareCommon.initHouse = function (item, form, fileArr, callback) {
    var frm = $(form.selector);
    frm.clearAll();
    frm.initForm(item);
    AssessCommon.initAreaInfo({
        provinceTarget: frm.find("select[name='province']"),
        cityTarget: frm.find("select[name='city']"),
        districtTarget: frm.find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    frm.validate();
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        frm.find("select[name='publicSituation']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, item.type, function (html, data) {
        frm.find("select[name='type']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.certUse, function (html, data) {
        frm.find("select[name='certUse']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareRoomType, item.nature, function (html, data) {
        frm.find("select[name='nature']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.landAcquisition, function (html, data) {
        frm.find("select[name='landAcquisition']").empty().html(html).trigger('change');
    });
    frm.find("select.certUse").off('change').on('change', function () {
        AssessCommon.loadDataDicByPid($(this).val(), item.certUseCategory, function (html, data) {
            frm.find("select.certUseCategory").empty().html(html).trigger('change');
        });
    });
    //绑定变更事件
    frm.find("select.landAcquisition").off('change').on('change', function () {
        var landAcquisitionId = frm.find("select.landAcquisition").val();
        if (landAcquisitionId) {
            AssessCommon.getDataDicInfo(landAcquisitionId, function (landAcquisitionData) {
                if (landAcquisitionData.name == "划拨") {
                    frm.find("input[name='useEndDate']").parent().parent().hide();
                    frm.find("input[name='useStartDate']").attr("required", true);
                } else {
                    frm.find("input[name='useEndDate']").parent().parent().show();
                    frm.find("input[name='useStartDate']").attr("required", false);
                }
            });
        }
    });
    if (item.landAcquisition) {
        var landAcquisitionId = item.landAcquisition;
        AssessCommon.getDataDicInfo(landAcquisitionId, function (landAcquisitionData) {
            if (landAcquisitionData.name == "划拨") {
                $("#useEndDate_d").parent().parent().hide();
                frm.find("input[name='useEndDate']").parent().parent().hide();
                frm.find("input[name='useStartDate']").attr("required", true);
            } else {
                $("#useEndDate_d").parent().parent().show();
                frm.find("input[name='useEndDate']").parent().parent().show();
                frm.find("input[name='useStartDate']").attr("required", false);
            }
        });
    }

    try {
        //在这加了时间的input 请在下面的label[name='xxx'] 加上 谢谢
        frm.find("input[name='registrationTime']").val(formatDate(item.registrationTime));
        frm.find("input[name='useEndDate']").val(formatDate(item.useEndDate));
        frm.find("input[name='useStartDate']").val(formatDate(item.useStartDate));
        frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
        frm.find("input[name='landRegistrationDate']").val(formatDate(item.landRegistrationDate));
    } catch (e) {
    }


    try {
        frm.find("label[name='registrationTime']").html(formatDate(item.registrationTime));
        frm.find("label[name='useEndDate']").html(formatDate(item.useEndDate));
        frm.find("label[name='useStartDate']").html(formatDate(item.useStartDate));
        frm.find("label[name='registrationDate']").html(formatDate(item.registrationDate));
        frm.find("label[name='landRegistrationDate']").html(formatDate(item.landRegistrationDate));
    } catch (e) {
    }


    if (fileArr) {
        $.each(fileArr, function (i, n) {
            declareCommon.showFile(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
        });
    }
    if (callback) {
        callback();
    }

    frm.find("select.select2").select2({
        minimumResultsForSearch: -1
    });

};

//土地初始化并且赋值
declareCommon.initLand = function (item, form, fileArr, callback) {
    var frm = $(form.selector);
    frm.clearAll();
    frm.initForm(item);
    AssessCommon.initAreaInfo({
        provinceTarget: frm.find("select[name='province']"),
        cityTarget: frm.find("select[name='city']"),
        districtTarget: frm.find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    frm.validate();
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.landRightType, function (html, data) {
        frm.find("select[name='landRightType']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.landRightNature, function (html, data) {
        frm.find("select[name='landRightNature']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.certUse, function (html, data) {
        frm.find("select[name='certUse']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        frm.find("select[name='publicSituation']").empty().html(html).trigger('change');
    });
    frm.find("select.certUse").off('change').on('change', function () {
        AssessCommon.loadDataDicByPid($(this).val(), item.certUseCategory, function (html, data) {
            frm.find("select.certUseCategory").empty().html(html).trigger('change');
        });
    });
    //绑定变更事件
    frm.find("select.landRightNature").off('change').on('change', function () {
        var landRightNatureId = frm.find("select.landRightNature").val();
        if (landRightNatureId) {
            AssessCommon.getDataDicInfo(landRightNatureId, function (landRightNatureData) {
                console.log(landRightNatureData.name)
                if (landRightNatureData.name == "划拨") {
                    frm.find("input[name='terminationDate']").parent().parent().hide();
                } else {
                    frm.find("input[name='terminationDate']").parent().parent().show();
                }
            });
        }
    });
    if (item.landRightNature) {
        var landRightNatureId = item.landRightNature;
        AssessCommon.getDataDicInfo(landRightNatureId, function (landRightNatureData) {
            if (landRightNatureData.name == "划拨") {
                $("#terminationDate_d").parent().parent().hide();
                frm.find("input[name='terminationDate']").parent().parent().hide();
            } else {
                $("#terminationDate_d").parent().parent().show();
                frm.find("input[name='terminationDate']").parent().parent().show();
            }
        });
    }
    try {
        //在这加了时间的input 请在下面的label[name='xxx'] 加上 谢谢
        frm.find("input[name='terminationDate']").val(formatDate(item.terminationDate));
        frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
    } catch (e) {
    }

    try {
        frm.find("label[name='terminationDate']").html(formatDate(item.terminationDate));
        frm.find("label[name='registrationDate']").html(formatDate(item.registrationDate));
    } catch (e) {
    }
    if (fileArr) {
        $.each(fileArr, function (i, n) {
            declareCommon.showFile(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
        });
    }
    if (callback) {
        callback();
    }

    frm.find("select").select2({
        minimumResultsForSearch: -1
    });
};

//不动产初始化并且赋值
declareCommon.initDeclareRealty = function (item, form, fileArr, callback) {
    var frm = $(form.selector);
    frm.clearAll();
    frm.initForm(item);
    AssessCommon.initAreaInfo({
        provinceTarget: frm.find("select[name='province']"),
        cityTarget: frm.find("select[name='city']"),
        districtTarget: frm.find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    frm.validate();
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.landCertUse, function (html, data) {
        frm.find("select[name='landCertUse']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.landRightType, function (html, data) {
        frm.find("select[name='landRightType']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        frm.find("select[name='publicSituation']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.landRightNature, function (html, data) {
        frm.find("select[name='landRightNature']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.houseCertUse, function (html, data) {
        frm.find("select[name='houseCertUse']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareRoomType, item.nature, function (html, data) {
        frm.find("select[name='nature']").empty().html(html).trigger('change');
    });
    frm.find("select.landCertUse").off('change').on('change', function () {
        AssessCommon.loadDataDicByPid($(this).val(), item.landCertUseCategory, function (html, data) {
            frm.find("select.landCertUseCategory").empty().html(html).trigger('change');
        });
    });
    frm.find("select.houseCertUse").off('change').on('change', function () {
        AssessCommon.loadDataDicByPid($(this).val(), item.houseCertUseCategory, function (html, data) {
            frm.find("select.houseCertUseCategory").empty().html(html).trigger('change');
        });
    });
    //绑定变更事件
    frm.find("select.landRightNature").off('change').on('change', function () {
        var landRightNatureId = frm.find("select.landRightNature").val();
        if (landRightNatureId) {
            AssessCommon.getDataDicInfo(landRightNatureId, function (landRightNatureData) {
                console.log(landRightNatureData.name)
                if (landRightNatureData.name == "划拨") {
                    frm.find("input[name='useEndDate']").parent().parent().hide();
                    frm.find("input[name='useStartDate']").attr("required", true);
                } else {
                    frm.find("input[name='useEndDate']").parent().parent().show();
                    frm.find("input[name='useStartDate']").attr("required", false);
                }
            });
        }
    });
    if (item.landRightNature) {
        var landRightNatureId = item.landRightNature;
        AssessCommon.getDataDicInfo(landRightNatureId, function (landRightNatureData) {
            if (landRightNatureData.name == "划拨") {
                $("#useEndDateFmt_d").parent().parent().hide();
                frm.find("input[name='useEndDate']").parent().parent().hide();
                frm.find("input[name='useStartDate']").attr("required", true);
            } else {
                $("#useEndDateFmt_d").parent().parent().show();
                frm.find("input[name='useEndDate']").parent().parent().show();
                frm.find("input[name='useStartDate']").attr("required", false);
            }
        });
    }

    try {
        //在这加了时间的input 请在下面的label[name='xxx'] 加上 谢谢
        frm.find("input[name='registrationTime']").val(formatDate(item.registrationTime));
        frm.find("input[name='useEndDate']").val(formatDate(item.useEndDate));
        frm.find("input[name='useStartDate']").val(formatDate(item.useStartDate));
        frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
        frm.find("input[name='terminationDate']").val(formatDate(item.terminationDate));
    } catch (e) {
    }


    try {
        frm.find("label[name='registrationTime']").html(formatDate(item.registrationTime));
        frm.find("label[name='useEndDate']").html(formatDate(item.useEndDate));
        frm.find("label[name='useStartDate']").html(formatDate(item.useStartDate));
        frm.find("label[name='registrationDate']").html(formatDate(item.registrationDate));
        frm.find("label[name='terminationDate']").html(formatDate(item.terminationDate));
    } catch (e) {
    }
    if (fileArr) {
        $.each(fileArr, function (i, n) {
            declareCommon.showFile(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
        });
    }
    if (callback) {
        callback();
    }

    frm.find("select").select2({
        minimumResultsForSearch: -1
    });
};