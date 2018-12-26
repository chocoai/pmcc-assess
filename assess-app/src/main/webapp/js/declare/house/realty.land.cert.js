/**
 * Created by kings on 2018-12-21.
 */

var declareRealtyLandCertConfig = {
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
};

var declareRealtyLandCert = new Object();

declareRealtyLandCert.declareRealtyLandCertFlag = true;
declareRealtyLandCert.startPath = null;

declareRealtyLandCert.fileUpload = function (target, tableName, id) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id,
            projectId: "${projectPlanDetails.projectId}"
        },
        deleteFlag: true
    });
};

declareRealtyLandCert.fileUpload2 = function (target, tableName, id) {
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
            declareRealtyLandCert.showFile(target, tableName, id);
            if (target == declareRealtyLandCertConfig.fileId) {
                if (declareRealtyLandCert.isEmpty(result)) {

                }
            }
            declareRealtyLandCert.loadList();
        },
        deleteFlag: true
    });
};

declareRealtyLandCert.showFile = function (target, tableName, id) {
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

declareRealtyLandCert.isEmpty = function (item) {
    if (item) {
        return true;
    }
    return false;
};


declareRealtyLandCert.objectWriteSelectData = function (frm, data, name) {
    if (declareRealtyLandCert.isEmpty(data)) {
        $("#" + frm + " ." + name).val(data).trigger("change");
    } else {
        $("#" + frm + " ." + name).val(null).trigger("change");
    }
};

declareRealtyLandCert.init = function (item) {
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + declareRealtyLandCertConfig.frm).find("select[name='province']"),
        cityTarget: $("#" + declareRealtyLandCertConfig.frm).find("select[name='city']"),
        districtTarget: $("#" + declareRealtyLandCertConfig.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + declareRealtyLandCertConfig.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + declareRealtyLandCertConfig.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + declareRealtyLandCertConfig.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.fileId, AssessDBKey.DeclareRealtyLandCert, declareRealtyLandCert.isEmpty(item.id) ? item.id : "0");
};

declareRealtyLandCert.showAddModel = function () {
    $('#' + declareRealtyLandCertConfig.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + declareRealtyLandCertConfig.box).find(".panel-body").prepend(commonDeclareApplyModel.land.getHtml());
    $('#' + declareRealtyLandCertConfig.box).modal("show");
    $("#" + declareRealtyLandCertConfig.frm).clearAll();
    $("#" + declareRealtyLandCertConfig.frm).validate();
    declareRealtyLandCert.init({});
    try {
        //由于是填充的hmtl所以需要手动初始化select2
        DatepickerUtils.parse();
        $('#' + declareRealtyLandCertConfig.box).find(".select2").each(function () {
            $(this).select2();
        });
    } catch (e) {
    }
};

declareRealtyLandCert.deleteData = function (id) {
    Alert("是否删除", 2, null,
        function () {
            $.ajax({
                type: "POST",
                url: getContextPath() + "/declareRealtyLandCert/deleteDeclareRealtyLandCertById",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        declareRealtyLandCert.loadList();
                        toastr.success('成功!');
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
    );
};

declareRealtyLandCert.editData = function (id) {
    $("#" + declareRealtyLandCertConfig.frm).clearAll();
    if (declareRealtyLandCert.declareRealtyLandCertFlag) {
        declareRealtyLandCert.init();
        declareRealtyLandCert.declareRealtyLandCertFlag = false;
    }
    declareRealtyLandCert.showFile(declareRealtyLandCertConfig.fileId, AssessDBKey.DeclareRealtyLandCert, id);
    declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.fileId, AssessDBKey.DeclareRealtyLandCert, id);
    $.ajax({
        url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var data = result.data;
                if (declareRealtyLandCert.isEmpty(data)) {
                    $("#" + declareRealtyLandCertConfig.frm).initForm(result.data);
                    $("#" + declareRealtyLandCertConfig.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                    $("#" + declareRealtyLandCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                    declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.frm, data.purpose, "purpose");
                    declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.frm, data.type, "type");
                    declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.frm, data.useRightType, "useRightType");
                    AssessCommon.initAreaInfo({
                        provinceTarget: $("#" + declareRealtyLandCertConfig.frm + "province"),
                        cityTarget: $("#" + declareRealtyLandCertConfig.frm + "city"),
                        districtTarget: $("#" + declareRealtyLandCertConfig.frm + "district"),
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
    })
    $("#" + declareRealtyLandCertConfig.frm).validate();
    $('#' + declareRealtyLandCertConfig.box).modal("show");
}

declareRealtyLandCert.loadList = function () {
    var cols = [];
    cols.push({field: 'useRightType', title: '使用权类型'});
    cols.push({field: 'apportionmentArea', title: '分摊面积'});
    cols.push({field: 'useRightArea', title: '使用权面积'});
    cols.push({field: 'landCertName', title: '土地权证号'});
    cols.push({field: 'beLocated', title: '土地坐落'});
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '是否关联房产证', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            if (declareRealtyLandCert.isEmpty(row.pid)) {
                str += "已经关联";
            } else {
                str += "未关联";
            }
            str += '</div>';
            return str;
        }
    });
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="declareRealtyLandCert.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="declareRealtyLandCert.deleteData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证关联' onclick='declareRealtyLandCert.houseCard(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证关联" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='declareRealtyLandCert.landEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='declareRealtyLandCert.houseEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + declareRealtyLandCertConfig.table).bootstrapTable('destroy');
    TableInit(declareRealtyLandCertConfig.table, getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId()
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    });
};

declareRealtyLandCert.saveAndUpdateData = function () {
    if (!$("#" + declareRealtyLandCertConfig.frm).valid()) {
        return false;
    }
    var data = formParams(declareRealtyLandCertConfig.frm);
    if (!declareRealtyLandCert.isEmpty(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.pid = "0";
        data.declareType = declareFunObj.getDeclareType("土地证");
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                declareRealtyLandCert.loadList();
                toastr.success('成功!');
                $('#' + declareRealtyLandCertConfig.box).modal("hide");
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
 * 描述:土地证附件 页面
 * @date:2018-09-21
 **/
declareRealtyLandCert.landEnclosure = function (id) {
    $("#" + declareRealtyLandCertConfig.newFileId).attr("data-id", id);
    $("#" + declareRealtyLandCertConfig.newFileId).trigger('click');
};
declareRealtyLandCert.landEnclosureFun = function () {
    var id = $("#" + declareRealtyLandCertConfig.newFileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyLandCert,
            tableId: id,
            fieldsName: declareRealtyLandCertConfig.fileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: declareRealtyLandCertConfig.newFileId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyLandCert.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

//土地证识别
declareRealtyLandCert.distinguish = function () {

};

/**
 * @author:  zch
 * 描述:房产证附件 服务端
 * @date:2018-09-21
 **/
declareRealtyLandCert.houseEnclosureFun = function () {
    var id = $("#" + declareRealtyLandCertConfig.newHouseFileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyHouseCert,
            tableId: id,
            fieldsName: declareRealtyLandCertConfig.houseFileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: declareRealtyLandCertConfig.newHouseFileId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyLandCert.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};
/**
 * @author:  zch
 * 描述:房产证附件 页面
 * @date:2018-09-21
 **/
declareRealtyLandCert.houseEnclosure = function (id) {
    var data = $("#" + declareRealtyLandCertConfig.table).bootstrapTable('getRowByUniqueId', id);
    if (declareRealtyLandCert.isEmpty(data.pid)) {
        $.ajax({
            url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            async: false,
            data: {id: data.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    var item = result.data;
                    if (declareRealtyLandCert.isEmpty(item)) {
                        $("#" + declareRealtyLandCertConfig.newHouseFileId).attr("data-id", item.id);
                        $("#" + declareRealtyLandCertConfig.newHouseFileId).trigger('click');
                    } else {
                        toastr.success('关联数据已经被删除了!');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    } else {
        toastr.success('请关联房产证数据!');
    }
};

/**
 * @author:  zch
 * 描述:房产证数据
 * @date:2018-09-21
 **/
declareRealtyLandCert.houseCard = function (id) {
    $("#" + declareRealtyLandCertConfig.HouseCert.frm).clearAll();
    $('#' + declareRealtyLandCertConfig.HouseCert.box).modal("show");
    var item = $("#" + declareRealtyLandCertConfig.table).bootstrapTable('getRowByUniqueId', id);
    if (declareRealtyLandCert.isEmpty(item.pid)) {
        $.ajax({
            url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            data: {id: item.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyLandCert.isEmpty(data)) {
                        $("#" + declareRealtyLandCertConfig.HouseCert.frm).initForm(data);
                        $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                        $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                        $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                        $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                        declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.HouseCert.frm, data.type, "type");
                        declareRealtyLandCert.showFile(declareRealtyLandCertConfig.houseFileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                        declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.houseFileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                    } else {
                        toastr.success('关联数据已经被删除了!');
                        toastr.success('请重新填写');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    } else {
        $.ajax({
            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyLandCert.isEmpty(data)) {
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + declareRealtyLandCertConfig.HouseCert.frm + "province"),
                            cityTarget: $("#" + declareRealtyLandCertConfig.HouseCert.frm + "city"),
                            districtTarget: $("#" + declareRealtyLandCertConfig.HouseCert.frm + "district"),
                            provinceValue: data.province,
                            cityValue: data.city,
                            districtValue: data.district
                        });
                        $("#" + declareRealtyLandCertConfig.HouseCert.frm).initForm(
                            {
                                pid: id,
                                beLocated: result.data.beLocated,
                                streetNumber: result.data.streetNumber,
                                attachedNumber: result.data.attachedNumber,
                                buildingNumber: result.data.buildingNumber,
                                unit: result.data.unit,
                                roomNumber: result.data.roomNumber,
                                floor: result.data.floor
                            }
                        );
                        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, result.data.type, function (html, data) {
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm).find('select.type').empty().html(html).trigger('change');
                        });
                        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, '', function (html, data) {
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
                        });
                        declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.houseFileId, AssessDBKey.DeclareRealtyHouseCert, 0);
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
};

declareRealtyLandCert.houseCardSaveAndUpdate = function () {
    if (!$("#" + declareRealtyLandCertConfig.HouseCert.frm).valid()) {
        return false;
    }
    var data = formParams(declareRealtyLandCertConfig.HouseCert.frm);

    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                toastr.success('成功!');
                $('#' + declareRealtyLandCertConfig.HouseCert.box).modal("hide");
                declareRealtyLandCert.loadList();
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};


declareRealtyLandCert.inputFileHouse = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/importDataHouse",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: 'ajaxFileUploadLandHouse',//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyLandCert.loadList();
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

/**
 * @author:  zch
 * 描述:批量导入
 * @date:2018-09-21
 **/
declareRealtyLandCert.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: 'ajaxFileUploadLand',//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyLandCert.loadList();
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
    declareRealtyLandCert.loadList();
});

