/**
 * Created by kings on 2018-12-20.
 */
var declareCommon = {};

declareCommon.config = {
    land:{
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
    house:{
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
    declareRealty:{
        frm: "frmDeclareRealtyRealEstateCert",
        name: "不动产证",
        table: "tableDeclareRealtyRealEstateCert",
        box: "boxDeclareRealtyRealEstateCert",
        fileId: "declareRealtyRealEstateCertFileId",
        newFileId: "declareRealtyRealEstateCertNewFileId",
        fileView: "declareRealtyRealEstateCertFileView"
    }
};
declareCommon.declareApplyForm = $('#declareApplyForm');

declareCommon.getPlanDetailsId = function () {
    return declareCommon.declareApplyForm.find('[name=planDetailsId]').val();
};
declareCommon.getProjectId = function () {
    return declareCommon.declareApplyForm.find('[name=projectId]').val();
};
//来源 com.copower.pmcc.assess.common.enums.DeclareTypeEnum
declareCommon.masterData = "master" ;//主数据
declareCommon.branchData = "branch" ;//从数据

declareCommon.declareHouseType = "" ;//房产申报类型
declareCommon.declareLandType = "" ;//土地申报类型
declareCommon.declareRealType = "" ;//不动产申报类型

declareCommon.fileUpload = function (target, tableName, id,deleteFlag) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id
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

declareCommon.showFile = function (target, tableName, id , deleteFlag) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            fieldsName: target,
            tableName: tableName,
            tableId: id,
            projectId: 0
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
declareCommon.showHtmlMastInit = function (target,callback) {
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
    }
};

declareCommon.saveLandData = function (data,callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: {formData:JSON.stringify(data)},
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

declareCommon.getLandData = function (id,callback) {
    $.ajax({
        url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
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

declareCommon.deleteHouseData = function (ids,callback) {
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

declareCommon.deleteLandData = function (ids,callback) {
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

declareCommon.saveHouseData = function (data,callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
        data: {formData:JSON.stringify(data)},
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

declareCommon.getHouseData = function (id,callback) {
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
declareCommon.getDeclareRealtyData = function (id,callback) {
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
declareCommon.deleteDeclareRealtyData = function (ids,callback) {
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
declareCommon.saveDeclareRealtyData = function (data,callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: {formData:JSON.stringify(data)},
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
declareCommon.initHouse = function (item,form,fileArr,callback) {
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
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, item.type, function (html, data) {
        frm.find("select[name='type']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        frm.find("select[name='publicSituation']").empty().html(html).trigger('change');
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
    frm.find("input[name='registrationTime']").val(formatDate(item.registrationTime));
    frm.find("input[name='useEndDate']").val(formatDate(item.useEndDate));
    frm.find("input[name='useStartDate']").val(formatDate(item.useStartDate));
    frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
    if (fileArr){
        $.each(fileArr,function (i,n) {
            declareCommon.showFile(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0' , true);
            declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyHouseCert, declareCommon.isNotBlank(item.id) ? item.id : '0' , true);
        });
    }
    if (callback){
        callback();
    }
};
//土地初始化并且赋值
declareCommon.initLand = function (item,form,fileArr,callback) {
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
    frm.find("input[name='terminationDate']").val(formatDate(item.terminationDate));
    frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
    if (fileArr){
        $.each(fileArr,function (i,n) {
            declareCommon.showFile(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0' , true);
            declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyLandCert, declareCommon.isNotBlank(item.id) ? item.id : '0' , true);
        });
    }
    if (callback){
        callback();
    }
};

//不动产初始化并且赋值
declareCommon.initDeclareRealty = function (item,form,fileArr,callback) {
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
    frm.find("input[name='registrationTime']").val(formatDate(item.registrationTime));
    frm.find("input[name='useEndDate']").val(formatDate(item.useEndDate));
    frm.find("input[name='useStartDate']").val(formatDate(item.useStartDate));
    frm.find("input[name='registrationDate']").val(formatDate(item.registrationDate));
    frm.find("input[name='terminationDate']").val(formatDate(item.terminationDate));
    if (fileArr){
        $.each(fileArr,function (i,n) {
            declareCommon.showFile(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0' , true);
            declareCommon.fileUpload(n, AssessDBKey.DeclareRealtyRealEstateCert, declareCommon.isNotBlank(item.id) ? item.id : '0' , true);
        });
    }
    if (callback){
        callback();
    }
};