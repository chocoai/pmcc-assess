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
    },
    //不动产清单
    declareRealtyCheckListModel: "#declareRealtyCheckListDataModelBox",
    declareRealtyCheckListListBox: "#divDataDeclareRealtyCheckList",
    declareRealtyCheckListToolBar: "#toolbarDeclareRealtyCheckList",
    declareRealtyCheckListTable: "#tbDataDeclareRealtyCheckListList",
    //建设工程规划许可证
    declareBuildingPermitListBox: "#divDeclareBuildingPermitBox",
    declareBuildingPermitDataModelBox: "#declareBuildingPermitDataModelBox",
    tbDeclareBuildingPermitList: "#tbDeclareBuildingPermitList",
    declareBuildingPermitFileId: "declareBuildingPermitAnnex",
    //建设用地规划许可证
    declareLandUsePermitListBox: "#divDeclareLandUsePermitBox",
    declareLandUsePermitDataModelBox: "#declareLandUsePermitDataModelBox",
    tbDeclareLandUsePermitList: "#tbDeclareLandUsePermitList",
    declareLandUsePermitFileId: "declareLandUsePermitAnnex",
    //建筑工程施工许可证
    declareBuildingConstructionPermitListBox: "#divDeclareBuildingConstructionPermitBox",
    declareBuildingConstructionPermitDataModelBox: "#declareBuildingConstructionPermitDataModelBox",
    tbDeclareBuildingConstructionPermitList: "#tbDeclareBuildingConstructionPermitList",
    declareBuildingConstructionPermitFileId: "declareBuildingConstructionPermitAnnex",
    //商品房预售许可证
    declarePreSalePermitListBox: "#divDeclarePreSalePermitBox",
    declarePreSalePermitDataModelBox: "#declarePreSalePermitDataModelBox",
    tbDeclarePreSalePermitList: "#tbDeclarePreSalePermitList",
    declarePreSalePermitFileId: "declarePreSalePermitAnnex"
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
    indicatorIdDelHtml: "<input type=\"button\" class=\"btn btn-warning btn-sm\" value=\"删除\" onclick=\"{method}('{frm}','{box}');\">"
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
            fieldsName: target,
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
            fieldsName: target,
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

declareCommon.removeStyleFun = function (target) {
    var card = target.find(".card-body");
    if (card.size() == 0) {
        return false;
    }
    var nodes = card.children(".form-group");
    if (nodes.size() == 0) {
        return false;
    }
    $.each(nodes, function (i, node) {
        var element = $(node).find("input[type=file]");
        var size = element.size();
        if (size == 0) {
            $(node).remove();
        }
    });
};

declareCommon.handleLandCertGetQuestion = function (flag, data, callback) {
    if (flag) {
        console.log(flag);
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCertificate_YES, null, function (retHtml, array) {
            console.log(AssessDicKey.projectDeclareCertificate_YES);
            console.log(array);
            console.log(retHtml);
            // data.landCertGetQuestion = array[0].id;
            // if (callback) {
            //     callback();
            // }
        });
    } else {
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCertificate_NO, null, function (retHtml, array) {
            data.landCertGetQuestion = array[0].id;
            if (callback) {
                callback();
            }
        });
    }
};


//公共  赋值 方法
declareCommon.initFormData = function (form, item, fileArr, bisDetail, tableName, inputArr) {
    var frm = $(form.selector);
    frm.clearAll();
    frm.initForm(item);
    frm.validate();
    if (fileArr) {
        $.each(fileArr, function (i, n) {
            if (bisDetail == false) {
                declareCommon.showFile(n, tableName, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
                declareCommon.fileUpload(n, tableName, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
            } else {
                declareCommon.showFile(n, tableName, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
                declareCommon.fileUpload(n, tableName, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            }
        });
    }
    if (inputArr) {
        $.each(inputArr, function (i, n) {
            frm.find("input[name='" + n + "']").val(formatDate(item[n]));
            frm.find("label[name='" + n + "']").html(formatDate(item[n]));
        });
    }
};

declareCommon.run = function (data, url, type, callback, funParams, errorCallback) {
    Loading.progressShow();
    $.ajax({
        type: type,
        url: getContextPath() + url,
        data: data,
        success: function (result) {
            Loading.progressHide();
            if (result.ret) {
                if (funParams) {
                    if (funParams == 'save') {
                        notifySuccess("成功", "保存数据成功!");
                    }
                    if (funParams == 'add') {
                        notifySuccess("成功", "添加数据成功!");
                    }
                    if (funParams == 'update') {
                        notifySuccess("成功", "修改数据成功!");
                    }
                    if (funParams == 'query') {
                        notifySuccess("成功", "查询数据成功!");
                    }
                    if (funParams == 'delete') {
                        notifySuccess("成功", "删除数据成功!");
                    }
                }
                if (callback) {
                    callback(result.data);
                }
            } else {
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
                if (errorCallback) {
                    errorCallback();
                }
            }
        },
        error: function (result) {
            Loading.progressHide();
            if (result.errmsg) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
            }
        }
    });
};
declareCommon.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
    var deleteParams = false;
    if (funParams) {
        if (funParams == 'delete') {
            deleteParams = true;
        }
    }
    if (deleteParams) {
        AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
            declareCommon.run(data, url, type, callback, funParams, errorCallback);
        });
    } else {
        declareCommon.run(data, url, type, callback, funParams, errorCallback);
    }
};

declareCommon.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
    declareCommon.ajaxServerFun(data, url, type, callback, null, errorCallback);
};

declareCommon.ajaxFileUploadCommon = function (data, fileElementId, url, callback, flag) {
    Loading.progressShow();
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + url,
        data: data,//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: fileElementId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            Loading.progressHide();
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
                if (flag) {

                } else {
                    AlertSuccess("导入情况", result.data);
                }
            } else {
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            if (result.errmsg) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
            }
        }
    });
};

declareCommon.ajaxFileUploadCommonFun = function (data, fileElementId, url, callback) {
    declareCommon.ajaxFileUploadCommon(data, fileElementId, url, callback, true);
};

//土地
declareCommon.getLandColumn = function () {
    var cols = [];
    cols.push({field: 'autoInitNumber', title: '编号'});
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'landCertName', title: '土地权证号'});
    cols.push({field: 'beLocated', title: '坐落'});
    cols.push({field: 'landNumber', title: '地号'});
    cols.push({field: 'graphNumber', title: '图号'});
    cols.push({field: 'useRightArea', title: '使用权面积'});
    return cols;
};

//房产
declareCommon.getHouseColumn = function () {
    var cols = [];
    cols.push({field: 'autoInitNumber', title: '编号'});
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'certName', title: '房屋权证号'});
    cols.push({field: 'beLocated', title: '坐落'});
    cols.push({field: 'ownership', title: '房屋所有权人'});
    cols.push({field: 'floorArea', title: '建筑面积'});
    cols.push({field: 'planningUseName', title: '规划用途'});
    return cols;
};

//不动产
declareCommon.getRealEstateColumn = function () {
    var cols = [];
    cols.push({field: 'autoInitNumber', title: '编号'});
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'evidenceArea', title: '证载面积'});
    cols.push({field: 'certName', title: '不动产权证号'});
    cols.push({field: 'beLocated', title: '坐落'});
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
declareCommon.declareBuildCenterSaveAndUpdateBase = function (item, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(item),
        updateNull: updateNull
    }, "/declareBuildEngineeringAndEquipmentCenter/saveDeclareBuildEngineeringAndEquipmentCenter", "post", callback);
};

declareCommon.declareBuildCenterSaveAndUpdate = function (item, callback) {
    declareCommon.declareBuildCenterSaveAndUpdateBase(item, false, callback);
};

/**
 * 申报中间表 获取
 * @param id
 * @param callback
 */
declareCommon.getDeclareBuildCenter = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById", "get", callback);
};

/**
 * 需要传入 type  , centerId
 * 申报中间表 根据type删除子项id
 * @param centerId
 * @param type
 * @param callback
 */
declareCommon.deleteByDeclareBuildCenterType = function (centerId, type, callback) {
    AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
        declareCommon.ajaxServerFun({
            centerId: centerId,
            type: type
        }, "/declareBuildEngineeringAndEquipmentCenter/deleteByType", "post", callback);
    });
};

/**
 * 申报中间表 删除
 * @param id
 * @param callback
 */
declareCommon.deleteDeclareBuildCenter = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildEngineeringAndEquipmentCenter/deleteDeclareBuildEngineeringAndEquipmentCenterById", "post", callback);
};

declareCommon.copyDeclareBuildCenter = function (copyId, ids, callback) {
    declareCommon.ajaxServerMethod({
        copyId: copyId,
        ids: ids
    }, "/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter", "post", callback);
};


declareCommon.saveLandDataBase = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert", "post", callback);
};

declareCommon.saveLandData = function (data, callback) {
    declareCommon.saveLandDataBase(data, false, callback);
};

declareCommon.getLandData = function (id, callback, errCallback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareRealtyLandCert/getDeclareRealtyLandCertById", "get", callback, errCallback);
};

declareCommon.deleteHouseData = function (ids, callback) {
    declareCommon.ajaxServerMethod({ids: ids}, "/declareRealtyHouseCert/deleteDeclareRealtyHouseCertById", "post", callback);
};

declareCommon.deleteLandData = function (ids, callback) {
    declareCommon.ajaxServerMethod({ids: ids}, "/declareRealtyLandCert/deleteDeclareRealtyLandCertById", "post", callback);
};

declareCommon.saveHouseDataBase = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert", "post", callback);
};

declareCommon.saveHouseData = function (data, callback) {
    declareCommon.saveHouseDataBase(data, false, callback);
};

declareCommon.getHouseData = function (id, callback) {
    declareCommon.ajaxServerMethod({
        id: id,
        planDetailsId: declareCommon.getPlanDetailsId()
    }, "/declareRealtyHouseCert/getDeclareRealtyHouseCertById", "get", callback);
};

//不动产获取
declareCommon.getDeclareRealtyData = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById", "get", callback);
};

//不动产 delete
declareCommon.deleteDeclareRealtyData = function (ids, callback) {
    declareCommon.ajaxServerMethod({ids: ids}, "/declareRealtyRealEstateCert/deleteDeclareRealtyRealEstateCertById", "post", callback);
};

//不动产save
declareCommon.saveDeclareRealtyDataBase = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert", "post", callback);
};

declareCommon.saveDeclareRealtyData = function (data, callback) {
    declareCommon.saveDeclareRealtyDataBase(data, false, callback);
};

//房产初始化并且赋值
declareCommon.initHouse = function (item, form, fileArr, callback, bisDetail) {
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
    AssessCommon.loadDataListHtml(AssessDicKey.examineHouseLoadUtility, item.certUse, function (html, data) {
        frm.find("#houseUseList").empty().html(html).trigger('change');
    }, true);
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareRoomType, item.nature, function (html, data) {
        frm.find("select[name='nature']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.landAcquisition, function (html, data) {
        frm.find("select[name='landAcquisition']").empty().html(html).trigger('change');
    });
    frm.find("input[name='certUse']").off('change').on('change', function () {
        AssessCommon.getSonDataList(AssessDicKey.examineHouseLoadUtility, $(this).val(), item.certUseCategory, function (html, data) {
            frm.find("#housecertUseCategoryList").empty().html(html).trigger('change');
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
            if (bisDetail == false) {
                declareCommon.showFile(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
                declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
            } else {
                declareCommon.showFile(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
                declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            }
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
declareCommon.initLand = function (item, form, fileArr, callback, bisDetail) {
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
    AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, item.certUse, function (html, data) {
        frm.find("#certUseList").empty().html(html).trigger('change');
        frm.find("#certUseList2").empty().html(html).trigger('change');
    }, true);
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        frm.find("select[name='publicSituation']").empty().html(html).trigger('change');
    });
    frm.find("input[name='certUse']").off('change').on('change', function () {
        AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), item.certUseCategory, function (html, data) {
            frm.find("#certUseCategoryList").empty().html(html).trigger('change');
            frm.find("#certUseCategoryList2").empty().html(html).trigger('change');
        });
    });
    //绑定变更事件
    frm.find("select[name=landRightNature]").off('change').on('change', function () {
        var landRightNatureId = $(this).val();
        console.log("landRightNatureId:" + landRightNatureId);
        if (landRightNatureId) {
            AssessCommon.getDataDicInfo(landRightNatureId, function (landRightNatureData) {
                if (landRightNatureData.name == "划拨") {
                    frm.find("input[name='terminationDate']").closest(".form-group").hide();
                } else {
                    frm.find("input[name='terminationDate']").closest(".form-group").show();
                }
            });
        }
    });
    try {
        //在这加了时间的input 请在下面的label[name='xxx'] 加上 谢谢
        frm.find("input[name='terminationDate']").val(formatDate(item.terminationDate));
        frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
        frm.find("input[name='approvalTime']").val(formatDate(item.approvalTime));
    } catch (e) {
    }

    try {
        frm.find("label[name='terminationDate']").html(formatDate(item.terminationDate));
        frm.find("label[name='registrationDate']").html(formatDate(item.registrationDate));
        frm.find("label[name='approvalTime']").html(formatDate(item.approvalTime));
    } catch (e) {
    }
    if (fileArr) {
        $.each(fileArr, function (i, n) {
            if (bisDetail == false) {
                declareCommon.showFile(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
                declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
            } else {
                declareCommon.showFile(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
                declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            }
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
declareCommon.initDeclareRealty = function (item, form, fileArr, callback, bisDetail) {
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
    AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, item.landCertUse, function (html, data) {
        frm.find("#landCertUseList").empty().html(html).trigger('change');
        frm.find("#landCertUseList2").empty().html(html).trigger('change');
    }, true);
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.landRightType, function (html, data) {
        frm.find("select[name='landRightType']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        frm.find("select[name='publicSituation']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.landRightNature, function (html, data) {
        frm.find("select[name='landRightNature']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataListHtml(AssessDicKey.examineHouseLoadUtility, item.houseCertUse, function (html, data) {
        frm.find("#realHouseUseList").empty().html(html).trigger('change');
        frm.find("#realHouseUseList2").empty().html(html).trigger('change');
    }, true);
    frm.find("input[name='houseCertUse']").off('change').on('change', function () {
        AssessCommon.getSonDataList(AssessDicKey.examineHouseLoadUtility, $(this).val(), item.houseCertUseCategory, function (html, data) {
            frm.find("#houseCertUseCategoryList1").empty().html(html).trigger('change');
            frm.find("#houseCertUseCategoryList2").empty().html(html).trigger('change');
        });
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareRoomType, item.nature, function (html, data) {
        frm.find("select[name='nature']").empty().html(html).trigger('change');
    });

    frm.find("input[name='landCertUse']").off('change').on('change', function () {
        AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), item.landCertUseCategory, function (html, data) {
            frm.find("#landCertUseCategoryList").empty().html(html).trigger('change');
            frm.find("#landCertUseCategoryList2").empty().html(html).trigger('change');
        });
    });
    //绑定变更事件
    frm.find("select.landRightNature").off('change').on('change', function () {
        var landRightNatureId = frm.find("select.landRightNature").val();
        if (landRightNatureId) {
            AssessCommon.getDataDicInfo(landRightNatureId, function (landRightNatureData) {
                console.log(landRightNatureData.name)
                if (landRightNatureData.name == "划拨") {
                    frm.find("input[name='useEndDate']").parent().hide();
                    frm.find("input[name='useEndDate']").parent().prev().hide();
                    frm.find("input[name='useStartDate']").attr("required", true);
                } else {
                    frm.find("input[name='useEndDate']").parent().show();
                    frm.find("input[name='useEndDate']").parent().prev().show();
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
        frm.find("label[name='approvalTime']").html(formatDate(item.approvalTime));
    } catch (e) {
    }
    if (fileArr) {
        $.each(fileArr, function (i, n) {
            if (bisDetail == false) {
                declareCommon.showFile(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
                declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0', false);
            } else {
                declareCommon.showFile(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
                declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0', true);
            }
        });
    }
    if (callback) {
        callback();
    }

    frm.find("select").select2({
        minimumResultsForSearch: -1
    });
};

//save 设备安装
declareCommon.saveDeclareBuildEquipmentInstall = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareBuildEquipmentInstall/saveDeclareBuildEquipmentInstall", "post", callback);
};
//initForm 设备安装
declareCommon.initFormDeclareBuildEquipmentInstall = function (form, item) {
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
};

//delete 设备安装
declareCommon.deleteDeclareBuildEquipmentInstallById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildEquipmentInstall/deleteDeclareBuildEquipmentInstallById", "post", callback);
};

//get 设备安装
declareCommon.getDeclareBuildEquipmentInstallById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById", "get", callback);
};
//save 土建
declareCommon.saveDeclareBuildEngineering = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareBuildEngineering/saveDeclareBuildEngineering", "post", callback);
};
//initForm 土建
declareCommon.initFormDeclareBuildEngineering = function (form, item) {
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

    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        frm.find("select[name='type']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        frm.find("select[name='useRightType']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        frm.find("select[name='purpose']").empty().html(html).trigger('change');
    });

    frm.validate();
};

//delete 土建
declareCommon.deleteDeclareBuildEngineeringById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildEngineering/deleteDeclareBuildEngineeringById", "POST", callback);
};

//get 土建
declareCommon.getDeclareBuildEngineeringById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildEngineering/getDeclareBuildEngineeringById", "get", callback);
};
//save 建筑工程施工许可证
declareCommon.saveDeclareBuildingConstructionPermit = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareBuildingConstructionPermit/saveDeclareBuildingConstructionPermit", "post", callback);
};

//delete 建筑工程施工许可证
declareCommon.deleteDeclareBuildingConstructionPermitById = function (id, callback) {
    declareCommon.ajaxServerFun({id: id}, "/declareBuildingConstructionPermit/deleteDeclareBuildingConstructionPermitById", "POST", callback, "delete");
};

//get 建筑工程施工许可证
declareCommon.getDeclareBuildingConstructionPermitById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById", "get", callback);
};


//save 建设工程规划许可证
declareCommon.saveDeclareBuildingPermit = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareBuildingPermit/saveDeclareBuildingPermit", "post", callback);
};

//delete 建设工程规划许可证
declareCommon.deleteDeclareBuildingPermitById = function (id, callback) {
    declareCommon.ajaxServerFun({id: id}, "/declareBuildingPermit/deleteDeclareBuildingPermitById", "post", callback, "delete");
};

//get 建设工程规划许可证
declareCommon.getDeclareBuildingPermitById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareBuildingPermit/getDeclareBuildingPermitById", "get", callback);
};


//save 建设用地规划许可证
declareCommon.saveDeclareLandUsePermit = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declareLandUsePermit/saveDeclareLandUsePermit", "POST", callback, function (message) {
        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
    });
};

//delete 建设用地规划许可证
declareCommon.deleteDeclareLandUsePermitById = function (id, callback) {
    declareCommon.ajaxServerFun({id: id}, "/declareLandUsePermit/deleteDeclareLandUsePermitById", "POST", callback, "delete");
};

//get 建设用地规划许可证
declareCommon.getDeclareLandUsePermitById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declareLandUsePermit/getDeclareLandUsePermitById", "get", callback, function (message) {
        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
    });
};

//

//save 商品房预售许可证
declareCommon.saveDeclarePreSalePermit = function (data, updateNull, callback) {
    if (updateNull == null) {
        updateNull = false;
    }
    if (updateNull == undefined) {
        updateNull = false;
    }
    if (updateNull == '') {
        updateNull = false;
    }
    declareCommon.ajaxServerMethod({
        formData: JSON.stringify(data),
        updateNull: updateNull
    }, "/declarePreSalePermit/saveDeclarePreSalePermit", "POST", callback, function (message) {
        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
    });
};

//delete 商品房预售许可证
declareCommon.deleteDeclarePreSalePermitById = function (id, callback) {
    declareCommon.ajaxServerFun({id: id}, "/declarePreSalePermit/deleteDeclarePreSalePermitById", "POST", callback, "delete");
};

//get 商品房预售许可证
declareCommon.getDeclarePreSalePermitById = function (id, callback) {
    declareCommon.ajaxServerMethod({id: id}, "/declarePreSalePermit/getDeclarePreSalePermitById", "get", callback, function (message) {
        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
    });
};

//save 不动产清单
declareCommon.saveAndUpdateDeclareRealtyCheckList = function (data, callback) {
    declareCommon.ajaxServerFun({
        formData: JSON.stringify(data),
        updateNull: true
    }, "/declareRealtyCheckList/saveAndUpdateDeclareRealtyCheckList", "post", callback, "save");
};
declareCommon.getDeclareRealtyCheckListListByExample = function (data, callback) {
    declareCommon.ajaxServerFun(data, "/declareRealtyCheckList/getDeclareRealtyCheckListListByExample", "get", callback, null);
};
declareCommon.deleteDeclareRealtyCheckListById = function (id, callback) {
    declareCommon.ajaxServerFun({id: id}, "/declareRealtyCheckList/deleteDeclareRealtyCheckListById", "post", callback, "delete");
};
declareCommon.getDeclareRealtyCheckListById = function (id, callback) {
    declareCommon.ajaxServerFun({id: id}, "/declareRealtyCheckList/getDeclareRealtyCheckListById", "get", callback, null);
};
declareCommon.loadDeclareRealtyCheckListTable = function (marsterId, tableId) {
    var box = $(declareCommon.config.declareRealtyCheckListListBox);
    var frm = box.find("form");
    var query = {marsterId: marsterId, tableId: tableId};
    frm.clearAll();
    frm.initForm(query);
    var cols = [];

    cols.push({field: 'district', title: '所在区'});
    cols.push({field: 'streetNumber', title: '街道'});
    cols.push({field: 'houseNumber', title: '门牌号'});
    // cols.push({field: 'attachedNumber', title: '附号'});
    // cols.push({field: 'buildingNumber', title: '栋号'});
    // cols.push({field: 'unit', title: '单元'});
    // cols.push({field: 'floor', title: '楼层'});
    // cols.push({field: 'roomNumber', title: '房号'});
    cols.push({field: 'certUse', title: '用途'});
    cols.push({field: 'floorArea', title: '房屋建筑面积'});
    cols.push({field: 'apportionmentArea', title: '分摊面积'});
    cols.push({field: 'realEstateUnitNumber', title: '不动产单元号'});
    if (tableId) {
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.editDeclareRealtyCheckList(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    } else {
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.findDeclareRealtyCheckList(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    }
    var table = $(declareCommon.config.declareRealtyCheckListTable);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declareRealtyCheckList/getBootstrapTableVo", cols, query, {
        showColumns: false,
        showRefresh: false,
        search: false,
        toolbar: declareCommon.config.declareRealtyCheckListToolBar,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
    box.modal("show");
};
declareCommon.editDeclareRealtyCheckList = function (id) {
    var table = $(declareCommon.config.declareRealtyCheckListTable);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    declareCommon.addDeclareRealtyCheckList(function (frm) {
        declareCommon.initFormDeclareRealtyCheckList(item, frm);
    });
};
declareCommon.findDeclareRealtyCheckList = function (id) {
    var table = $(declareCommon.config.declareRealtyCheckListTable);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    var box = $(declareCommon.config.declareRealtyCheckListModel);
    var frm = box.find("form");
    box.find("#" + commonDeclareApprovalModel.config.declareRealtyCheckList.handleId).remove();
    box.find(".card-body").append(commonDeclareApprovalModel.declareRealtyCheckList.getHtml());
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.initFormDeclareRealtyCheckList(item, frm);
    });

};
declareCommon.delDeclareRealtyCheckList = function () {
    var table = $(declareCommon.config.declareRealtyCheckListTable);
    var box = $(declareCommon.config.declareRealtyCheckListListBox);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要删除的数据!");

    } else {
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });
        declareCommon.deleteDeclareRealtyCheckListById(idArray.join(","), function () {
            table.bootstrapTable('refresh');
        });
    }
};
declareCommon.declareRealtyCheckListSaveAndUpdate = function () {
    var box = $(declareCommon.config.declareRealtyCheckListModel);
    var table = $(declareCommon.config.declareRealtyCheckListTable);
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    if (!frm.valid()) {
        return false;
    }
    declareCommon.saveAndUpdateDeclareRealtyCheckList(data, function () {
        box.modal("hide");
        table.bootstrapTable('refresh');
        $(declareCommon.config.declareRealtyCheckListListBox).modal("show");// list box
    });
};
declareCommon.addDeclareRealtyCheckList = function (callback) {
    var box = $(declareCommon.config.declareRealtyCheckListModel);
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.declareRealtyCheckList.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.declareRealtyCheckList.getHtml());
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.initFormDeclareRealtyCheckList(area, frm);
        var form = $(declareCommon.config.declareRealtyCheckListListBox).find("form");
        var data = formSerializeArray(form);
        var str = "" + data.tableId + "";
        if (str == '#tableDeclareRealtyHouseCert') {
            //说明是房产证
            frm.find(".realEstateUnitNumber").empty();
        }
        if (callback) {
            callback(frm);
        }
    });
};
declareCommon.initFormDeclareRealtyCheckList = function (data, frm) {
    var arr = [];
    var inputArr = [];
    var box = $(declareCommon.config.declareRealtyCheckListListBox);
    var item = formSerializeArray(box.find("form"));
    jQuery.extend(data, item);
    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareRealtyCheckList, inputArr);
};
declareCommon.inputRealDeclareRealtyCheckList = function () {
    var box = $(declareCommon.config.declareRealtyCheckListListBox);
    var table = $(declareCommon.config.declareRealtyCheckListTable);
    var data = {planDetailsId: declareCommon.getPlanDetailsId()};
    jQuery.extend(data, formSerializeArray(box.find("form")));
    declareCommon.ajaxFileUploadCommon(data, 'ajaxFileUploadRealDeclareRealtyCheckList', "/declareRealtyCheckList/importData", function () {
        table.bootstrapTable('refresh');
    });
};

//建设工程规划许可证
declareCommon.loadTableDeclareBuildingPermit = function (masterId, tableId) {
    var box = $(declareCommon.config.declareBuildingPermitListBox);
    var frm = box.find("form");
    var query = {masterId: masterId};
    frm.clearAll();
    frm.initForm(query);
    var cols = [];
    cols.push({field: 'certificateNumber', title: '证书编号'});
    cols.push({field: 'issuingOrgan', title: '发证机关'});
    cols.push({
        field: 'date', title: '日期', formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    cols.push({field: 'unit', title: '建设单位（个人）'});
    cols.push({field: 'name', title: '建设项目名称'});
    if (tableId) {
        cols.push({
            field: 'id', title: '编辑', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.editDeclareBuildingPermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    } else {
        cols.push({
            field: 'id', title: '详情', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.findDeclareBuildingPermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    }
    var table = $(declareCommon.config.tbDeclareBuildingPermitList);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declareBuildingPermit/getDeclareBuildingPermitList", cols, query, {
        showColumns: false,
        showRefresh: false,
        search: false,
        toolbar: "." + box.attr("id"),
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
    box.modal("show");
};
declareCommon.addDeclareBuildingPermit = function (data) {
    var box = $(declareCommon.config.declareBuildingPermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.buildingPermit.getHtml());
    var arr = [declareCommon.config.declareBuildingPermitFileId];
    var inputArr = ["date"];
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        if (data) {
            declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingPermit, inputArr);
        } else {
            declareCommon.initFormData(frm, area, arr, true, AssessDBKey.DeclareBuildingPermit, inputArr);
        }
    });
};
declareCommon.saveDeclareBuildingPermitData = function () {
    var box = $(declareCommon.config.declareBuildingPermitDataModelBox);
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    var item = formSerializeArray($(declareCommon.config.declareBuildingPermitListBox).find("form"));
    $.extend(data, item);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    if (!frm.valid()) {
        return false;
    }
    declareCommon.saveDeclareBuildingPermit(data, true, function (item) {
        box.modal("hide");
        notifySuccess("成功", "保存成功!");
        var table = $(declareCommon.config.tbDeclareBuildingPermitList);
        table.bootstrapTable('refresh');
    });
};
declareCommon.editDeclareBuildingPermit = function (id) {
    var table = $(declareCommon.config.tbDeclareBuildingPermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    declareCommon.addDeclareBuildingPermit(item);
};

declareCommon.delDeclareBuildingPermit = function () {
    var table = $(declareCommon.config.tbDeclareBuildingPermitList);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要删除的数据!");

    } else {
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });
        declareCommon.deleteDeclareBuildingPermitById(idArray.join(","), function () {
            table.bootstrapTable('refresh');
        });
    }
};
declareCommon.findDeclareBuildingPermit = function (id) {
    var table = $(declareCommon.config.tbDeclareBuildingPermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    var arr = [declareCommon.config.declareBuildingPermitFileId];
    var inputArr = ["date"];
    var box = $(declareCommon.config.declareBuildingPermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApprovalModel.config.buildingPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApprovalModel.buildingPermit.getHtml());
    declareCommon.initFormData(frm, item, arr, false, AssessDBKey.DeclareBuildingPermit, inputArr);
    box.modal("show");
};


//建设用地
declareCommon.loadTableDeclareLandUsePermit = function (masterId, tableId) {
    var box = $(declareCommon.config.declareLandUsePermitListBox);
    var frm = box.find("form");
    var query = {masterId: masterId};
    frm.clearAll();
    frm.initForm(query);
    var cols = [];
    cols.push({field: 'certificateNumber', title: '证书编号'});
    cols.push({field: 'issuingOrgan', title: '发证机关'});
    cols.push({
        field: 'date', title: '日期', formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    cols.push({field: 'unit', title: '用地单位'});
    cols.push({field: 'name', title: '用地项目名称'});
    cols.push({field: 'location', title: '用地位置'});
    if (tableId) {
        cols.push({
            field: 'id', title: '编辑', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.editDeclareLandUsePermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    } else {
        cols.push({
            field: 'id', title: '详情', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.findDeclareLandUsePermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    }
    var table = $(declareCommon.config.tbDeclareLandUsePermitList);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declareLandUsePermit/getDeclareLandUsePermitList", cols, query, {
        showColumns: false,
        showRefresh: false,
        search: false,
        toolbar: "." + box.attr("id"),
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
    box.modal("show");
};
declareCommon.addDeclareLandUsePermit = function (data) {
    var box = $(declareCommon.config.declareLandUsePermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.landUsePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.landUsePermit.getHtml());
    var arr = [declareCommon.config.declareLandUsePermitFileId];
    var inputArr = ["date"];
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        if (data) {
            declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareLandUsePermit, inputArr);
        } else {
            declareCommon.initFormData(frm, area, arr, true, AssessDBKey.DeclareLandUsePermit, inputArr);
        }
    });
};
declareCommon.saveDeclareLandUsePermitData = function () {
    var box = $(declareCommon.config.declareLandUsePermitDataModelBox);
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    var item = formSerializeArray($(declareCommon.config.declareLandUsePermitListBox).find("form"));
    $.extend(data, item);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    if (!frm.valid()) {
        return false;
    }
    declareCommon.saveDeclareLandUsePermit(data, true, function (item) {
        box.modal("hide");
        notifySuccess("成功", "保存成功!");
        var table = $(declareCommon.config.tbDeclareLandUsePermitList);
        table.bootstrapTable('refresh');
    });
};
declareCommon.editDeclareLandUsePermit = function (id) {
    var table = $(declareCommon.config.tbDeclareLandUsePermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    declareCommon.addDeclareLandUsePermit(item);
};

declareCommon.delDeclareLandUsePermit = function () {
    var table = $(declareCommon.config.tbDeclareLandUsePermitList);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要删除的数据!");

    } else {
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });
        declareCommon.deleteDeclareLandUsePermitById(idArray.join(","), function () {
            table.bootstrapTable('refresh');
        });
    }
};
declareCommon.findDeclareLandUsePermit = function (id) {
    var table = $(declareCommon.config.tbDeclareLandUsePermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    var arr = [declareCommon.config.declareLandUsePermitFileId];
    var inputArr = ["date"];
    var box = $(declareCommon.config.declareLandUsePermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApprovalModel.config.landUsePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApprovalModel.landUsePermit.getHtml());
    declareCommon.initFormData(frm, item, arr, false, AssessDBKey.DeclareLandUsePermit, inputArr);
    box.modal("show");
};


//建筑工程施工许可证
declareCommon.loadTableDeclareBuildingConstructionPermit = function (masterId, tableId) {
    var box = $(declareCommon.config.declareBuildingConstructionPermitListBox);
    var frm = box.find("form");
    var query = {masterId: masterId};
    frm.clearAll();
    frm.initForm(query);
    var cols = [];
    cols.push({field: 'certificateNumber', title: '证书编号'});
    cols.push({field: 'issuingOrgan', title: '发证机关'});
    cols.push({
        field: 'date', title: '日期', formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    cols.push({field: 'buildUnit', title: '建设单位（个人）'});
    cols.push({field: 'name', title: '工程名称'});
    cols.push({field: 'buildAddress', title: '建设地址'});
    cols.push({field: 'chiefEngineerConstructionInspection', title: '总监理工程师'});
    cols.push({
        field: 'contractPeriod', title: '合同日期', formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    if (tableId) {
        cols.push({
            field: 'id', title: '编辑', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.editDeclareBuildingConstructionPermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    } else {
        cols.push({
            field: 'id', title: '详情', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.findDeclareBuildingConstructionPermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    }
    var table = $(declareCommon.config.tbDeclareBuildingConstructionPermitList);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitList", cols, query, {
        showColumns: false,
        showRefresh: false,
        search: false,
        toolbar: "." + box.attr("id"),
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
    box.modal("show");
};
declareCommon.addDeclareBuildingConstructionPermit = function (data) {
    var box = $(declareCommon.config.declareBuildingConstructionPermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingConstructionPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.buildingConstructionPermit.getHtml());
    var arr = [declareCommon.config.declareBuildingConstructionPermitFileId];
    var inputArr = ["date","contractPeriod"];
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        if (data) {
            declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
        } else {
            declareCommon.initFormData(frm, area, arr, true, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
        }
    });
};
declareCommon.saveDeclareBuildingConstructionPermitData = function () {
    var box = $(declareCommon.config.declareBuildingConstructionPermitDataModelBox);
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    var item = formSerializeArray($(declareCommon.config.declareBuildingConstructionPermitListBox).find("form"));
    $.extend(data, item);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    if (!frm.valid()) {
        return false;
    }
    declareCommon.saveDeclareBuildingConstructionPermit(data, true, function (item) {
        box.modal("hide");
        notifySuccess("成功", "保存成功!");
        var table = $(declareCommon.config.tbDeclareBuildingConstructionPermitList);
        table.bootstrapTable('refresh');
    });
};
declareCommon.editDeclareBuildingConstructionPermit = function (id) {
    var table = $(declareCommon.config.tbDeclareBuildingConstructionPermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    declareCommon.addDeclareBuildingConstructionPermit(item);
};

declareCommon.delDeclareBuildingConstructionPermit = function () {
    var table = $(declareCommon.config.tbDeclareBuildingConstructionPermitList);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要删除的数据!");

    } else {
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });
        declareCommon.deleteDeclareBuildingConstructionPermitById(idArray.join(","), function () {
            table.bootstrapTable('refresh');
        });
    }
};
declareCommon.findDeclareBuildingConstructionPermit = function (id) {
    var table = $(declareCommon.config.tbDeclareBuildingConstructionPermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    var arr = [declareCommon.config.declareBuildingConstructionPermitFileId];
    var inputArr = ["date","contractPeriod"];
    var box = $(declareCommon.config.declareBuildingConstructionPermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApprovalModel.config.buildingConstructionPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApprovalModel.buildingConstructionPermit.getHtml());
    declareCommon.initFormData(frm, item, arr, false, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
    box.modal("show");
};



//商品房预售许可证
declareCommon.loadTableDeclarePreSalePermit = function (masterId, tableId) {
    var box = $(declareCommon.config.declarePreSalePermitListBox);
    var frm = box.find("form");
    var query = {masterId: masterId};
    frm.clearAll();
    frm.initForm(query);
    var cols = [];
    cols.push({field: 'certificateNumber', title: '证书编号'});
    cols.push({field: 'issuingOrgan', title: '发证机关'});
    cols.push({
        field: 'date', title: '日期', formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    cols.push({field: 'salesUnit', title: '售房单位'});
    cols.push({field: 'legalRepresentative', title: '法定代表人'});
    if (tableId) {
        cols.push({
            field: 'id', title: '编辑', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.editDeclarePreSalePermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    } else {
        cols.push({
            field: 'id', title: '详情', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button onclick="declareCommon.findDeclarePreSalePermit(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
    }
    var table = $(declareCommon.config.tbDeclarePreSalePermitList);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declarePreSalePermit/getDeclarePreSalePermitList", cols, query, {
        showColumns: false,
        showRefresh: false,
        search: false,
        toolbar: "." + box.attr("id"),
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
    box.modal("show");
};
declareCommon.addDeclarePreSalePermit = function (data) {
    var box = $(declareCommon.config.declarePreSalePermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.preSalePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.preSalePermit.getHtml());
    var arr = [declareCommon.config.declarePreSalePermitFileId];
    var inputArr = ["date"];
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        if (data) {
            declareCommon.initFormData(frm, data, arr, true, AssessDBKey.DeclarePreSalePermit, inputArr);
        } else {
            declareCommon.initFormData(frm, area, arr, true, AssessDBKey.DeclarePreSalePermit, inputArr);
        }
    });
};
declareCommon.saveDeclarePreSalePermitData = function () {
    var box = $(declareCommon.config.declarePreSalePermitDataModelBox);
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    var item = formSerializeArray($(declareCommon.config.declarePreSalePermitListBox).find("form"));
    $.extend(data, item);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    if (!frm.valid()) {
        return false;
    }
    declareCommon.saveDeclarePreSalePermit(data, true, function (item) {
        box.modal("hide");
        notifySuccess("成功", "保存成功!");
        var table = $(declareCommon.config.tbDeclarePreSalePermitList);
        table.bootstrapTable('refresh');
    });
};
declareCommon.editDeclarePreSalePermit = function (id) {
    var table = $(declareCommon.config.tbDeclarePreSalePermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    declareCommon.addDeclarePreSalePermit(item);
};

declareCommon.delDeclarePreSalePermit = function () {
    var table = $(declareCommon.config.tbDeclarePreSalePermitList);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要删除的数据!");

    } else {
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });
        declareCommon.deleteDeclarePreSalePermitById(idArray.join(","), function () {
            table.bootstrapTable('refresh');
        });
    }
};
declareCommon.findDeclarePreSalePermit = function (id) {
    var table = $(declareCommon.config.tbDeclarePreSalePermitList);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    var arr = [declareCommon.config.declarePreSalePermitFileId];
    var inputArr = ["date"];
    var box = $(declareCommon.config.declarePreSalePermitDataModelBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApprovalModel.config.preSalePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApprovalModel.preSalePermit.getHtml());
    declareCommon.initFormData(frm, item, arr, false, AssessDBKey.DeclarePreSalePermit, inputArr);
    box.modal("show");
};