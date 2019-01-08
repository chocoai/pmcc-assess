/**
 * Created by kings on 2018-12-21.
 */

var assessCommonLand = {};

/**
 * 空字符串检测
 * @param item
 * @returns {boolean}
 */
assessCommonLand.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};

assessCommonLand.fileUpload = function (target, tableName, id) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id
        },
        deleteFlag: true
    });
};


assessCommonLand.showFile = function (target, tableName, id) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            fieldsName: target,
            tableName: tableName,
            tableId: id,
            projectId: 0
        },
        deleteFlag: true
    })
};

assessCommonLand.config = {
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

/**
 * 土地证附件触发事件
 */
assessCommonLand.landImportEvent = function (id) {
    $("#" + assessCommonLand.config.newFileId).attr("data-id", id);
    $("#" + assessCommonLand.config.newFileId).trigger('click');
};

/**
 * 土地证附件 导入处理
 */
assessCommonLand.landImportHandle = function () {
    var id = $("#" + assessCommonLand.config.newFileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyLandCert,
            tableId: id,
            fieldsName: assessCommonLand.config.fileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: assessCommonLand.config.newFileId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                assessCommonLand.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

/**
 * 房产证附件触发事件 (会先检测是否关联)
 * @param id
 */
assessCommonLand.houseImportEvent = function (id) {
    var data = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (assessCommonLand.isNotBlank(data.pid)) {
        $.ajax({
            url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            async: false,
            data: {id: data.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    var item = result.data;
                    if (assessCommonLand.isNotBlank(item)) {
                        $("#" + assessCommonLand.config.newHouseFileId).attr("data-id", item.id);
                        $("#" + assessCommonLand.config.newHouseFileId).trigger('click');
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
 * 房产证附件 导入处理
 */
assessCommonLand.houseImportHandle = function () {
    var id = $("#" + assessCommonLand.config.newHouseFileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyHouseCert,
            tableId: id,
            fieldsName: assessCommonLand.config.houseFileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: assessCommonLand.config.newHouseFileId,//文件选择框的id属性
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

assessCommonLand.init = function (item) {
    $("#" + assessCommonLand.config.frm).initForm(item);
    $("#" + assessCommonLand.config.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    $("#" + assessCommonLand.config.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + assessCommonLand.config.frm).find("select[name='province']"),
        cityTarget: $("#" + assessCommonLand.config.frm).find("select[name='city']"),
        districtTarget: $("#" + assessCommonLand.config.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + assessCommonLand.config.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + assessCommonLand.config.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + assessCommonLand.config.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    assessCommonLand.fileUpload(assessCommonLand.config.fileId, AssessDBKey.DeclareRealtyLandCert, assessCommonLand.isNotBlank(item.id) ? item.id : "0");
    assessCommonLand.showFile(assessCommonLand.config.fileId, AssessDBKey.DeclareRealtyLandCert, assessCommonLand.isNotBlank(item.id) ? item.id : "0");
};

/**
 * 土地证显示
 */
assessCommonLand.showAddModelLand = function () {
    $('#' + assessCommonLand.config.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + assessCommonLand.config.box).find(".panel-body").prepend(commonDeclareApplyModel.land.getHtml());
    $('#' + assessCommonLand.config.box).modal("show");
    $("#" + assessCommonLand.config.frm).clearAll();
    $("#" + assessCommonLand.config.frm).validate();
    mapPosition.getCurrentCityByArea(function (area) {
        assessCommonLand.init(area);
    });
    try {
        //由于是填充的hmtl所以需要手动初始化select2
        DatepickerUtils.parse();
        $('#' + assessCommonLand.config.box).find(".select2").each(function () {
            $(this).select2();
        });
    } catch (e) {
    }
};

assessCommonLand.deleteLand = function () {
    var rows = $("#" + assessCommonLand.config.table).bootstrapTable('getSelections');
    if (rows.length >= 1) {
        Alert("是否删除", 2, null,
            function () {
                var ids = "";
                $.each(rows, function (i, n) {
                    if (i == rows.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    type: "POST",
                    url: getContextPath() + "/declareRealtyLandCert/deleteDeclareRealtyLandCertById",
                    data: {ids: ids},
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
    } else {
        Alert("至少选择一个");
    }
};

/**
 * 土地证编辑
 */
assessCommonLand.editLand = function () {
    var rows = $("#" + assessCommonLand.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        assessCommonLand.showAddModelLand();
        $("#" + assessCommonLand.config.frm).clearAll();
        $.ajax({
            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            data: {id: rows[0].id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        assessCommonLand.init(result.data);
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $("#" + assessCommonLand.config.frm).validate();
        $('#' + assessCommonLand.config.box).modal("show");
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * 土地证列表
 */
assessCommonLand.loadList = function () {
    var cols = [];
    cols.push({field: 'useRightType', title: '使用权类型'});
    cols.push({field: 'apportionmentArea', title: '分摊面积'});
    cols.push({field: 'useRightArea', title: '使用权面积'});
    cols.push({field: 'landCertName', title: '土地权证号'});
    cols.push({field: 'beLocated', title: '土地坐落'});
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            if (row.pid) {
                str += '<a class="btn btn-xs btn-success" href="javascript:assessCommonLand.showAddModelHouse(' + row.id + ');" ><i class="fa fa-check">房产证</i></a>';
            } else {
                str += '<a class="btn btn-xs btn-success" href="javascript:assessCommonLand.showAddModelHouse(' + row.id + ');" ><i class="fa fa-remove">房产证</i></a>';
            }
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='assessCommonLand.landImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='assessCommonLand.houseImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + assessCommonLand.config.table).bootstrapTable('destroy');
    TableInit(assessCommonLand.config.table, getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),
        enable:'yes'
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true);
};

/**
 * 土地证添加
 * @returns {boolean}
 */
assessCommonLand.saveAndUpdateLand = function () {
    if (!$("#" + assessCommonLand.config.frm).valid()) {
        return false;
    }
    var data = formParams(assessCommonLand.config.frm);
    if (!assessCommonLand.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.enable = "yes";
        data.pid = "0";
        data.declareType = declareFunObj.getDeclareType("土地证");
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                assessCommonLand.loadList();
                toastr.success('成功!');
                $('#' + assessCommonLand.config.box).modal("hide");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};


assessCommonLand.initHouse = function (item) {
    $("#" + assessCommonLand.config.HouseCert.frm).initForm(item);
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + assessCommonLand.config.HouseCert.frm).find("select[name='province']"),
        cityTarget: $("#" + assessCommonLand.config.HouseCert.frm).find("select[name='city']"),
        districtTarget: $("#" + assessCommonLand.config.HouseCert.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, item.type, function (html, data) {
        $("#" + assessCommonLand.config.HouseCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        $("#" + assessCommonLand.config.HouseCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    $("#" + assessCommonLand.config.HouseCert.frm + " input[name='registrationTime']").val(formatDate(item.registrationTime));
    $("#" + assessCommonLand.config.HouseCert.frm + " input[name='useEndDate']").val(formatDate(item.useEndDate));
    $("#" + assessCommonLand.config.HouseCert.frm + " input[name='useStartDate']").val(formatDate(item.useStartDate));
    $("#" + assessCommonLand.config.HouseCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    assessCommonLand.showFile(assessCommonLand.config.houseFileId, AssessDBKey.DeclareRealtyHouseCert, assessCommonLand.isNotBlank(item.id) ? item.id : '0');
    assessCommonLand.fileUpload(assessCommonLand.config.houseFileId, AssessDBKey.DeclareRealtyHouseCert, assessCommonLand.isNotBlank(item.id) ? item.id : '0');
};

/**
 * @author:  zch
 * 描述:房产证数据
 * @date:2018-09-21
 **/
assessCommonLand.showAddModelHouse = function (id) {
    $('#' + assessCommonLand.config.HouseCert.box).find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    $('#' + assessCommonLand.config.HouseCert.box).find(".panel-body").append(commonDeclareApplyModel.house.getHtml());
    //由于是填充的hmtl所以需要手动初始化select2
    DatepickerUtils.parse();
    $('#' + assessCommonLand.config.HouseCert.box).find(".select2").each(function () {
        $(this).select2();
    });
    $("#" + assessCommonLand.config.HouseCert.frm).validate();
    $('#' + assessCommonLand.config.HouseCert.box).modal("show");
    $("#" + assessCommonLand.config.HouseCert.frm).clearAll();
    var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (assessCommonLand.isNotBlank(item.pid)) {
        $.ajax({
            url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            data: {id: item.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    if (assessCommonLand.isNotBlank(result.data)) {
                        assessCommonLand.initHouse(result.data);
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
                    var item = {
                        pid: id,
                        beLocated: result.data.beLocated,
                        streetNumber: result.data.streetNumber,
                        attachedNumber: result.data.attachedNumber,
                        buildingNumber: result.data.buildingNumber,
                        unit: result.data.unit,
                        roomNumber: result.data.roomNumber,
                        floor: result.data.floor,
                        province: result.data.province,
                        city: result.data.city,
                        district: result.data.district
                    };
                    assessCommonLand.initHouse(item);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
};

/**
 * 房产证保存
 * @returns {boolean}
 */
assessCommonLand.saveAndUpdateHouse = function () {
    if (!$("#" + assessCommonLand.config.HouseCert.frm).valid()) {
        return false;
    }
    var data = formParams(assessCommonLand.config.HouseCert.frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.enable = "no";
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                toastr.success('成功!');
                $('#' + assessCommonLand.config.HouseCert.box).modal("hide");
                assessCommonLand.loadList();
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
 * 房产证excel导入
 */
assessCommonLand.inputFileHouse = function () {
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
 * 描述:土地证excel 批量导入
 * @date:2018-09-21
 **/
assessCommonLand.inputFile = function () {
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
    assessCommonLand.loadList();
});

