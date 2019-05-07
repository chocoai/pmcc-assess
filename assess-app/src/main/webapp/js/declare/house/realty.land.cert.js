/**
 * Created by kings on 2018-12-21.
 */

var assessCommonLand = {};

assessCommonLand.config = {
    frm: declareCommon.config.land.frm,
    name: declareCommon.config.land.name,
    table: declareCommon.config.land.table,
    box: declareCommon.config.land.box,
    fileId: declareCommon.config.land.fileId,
    newFileId: declareCommon.config.land.newFileId,
    houseFileId: declareCommon.config.land.houseFileId,
    newHouseFileId: declareCommon.config.land.newHouseFileId,
    HouseCert:{
        frm:declareCommon.config.land.HouseCert.frm,
        box:declareCommon.config.land.HouseCert.box
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
    if (declareCommon.isNotBlank(data.pid)) {
        declareCommon.getHouseData(data.pid , function (item) {
            if (declareCommon.isNotBlank(item)) {
                $("#" + assessCommonLand.config.newHouseFileId).attr("data-id", item.id);
                $("#" + assessCommonLand.config.newHouseFileId).trigger('click');
            } else {
                toastr.success('关联数据已经被删除了!');
            }
        });
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
    declareCommon.initLand(item,$("#" + assessCommonLand.config.frm),[assessCommonLand.config.fileId],null);
};

/**
 * 土地证显示
 */
assessCommonLand.showAddModelLand = function () {
    $('#' + assessCommonLand.config.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + assessCommonLand.config.box).find(".panel-body").prepend(commonDeclareApplyModel.land.getHtml());
    $('#' + assessCommonLand.config.box).modal("show");
    declareCommon.showHtmlMastInit($("#" + assessCommonLand.config.frm), function (area) {
        assessCommonLand.init(area);
    });
};

assessCommonLand.deleteLand = function () {
    var rows = $("#" + assessCommonLand.config.table).bootstrapTable('getSelections');
    if (rows.length >= 1) {
        Alert("是否删除", 2, null,
            function () {
                var arr = [];
                rows.forEach(function (item, index, source) {
                    arr.push(item.id);
                });
                declareCommon.deleteLandData(arr.join(","),function () {
                    assessCommonLand.loadList();
                    toastr.success('成功!');
                });
            }
        );
    } else {
        Alert("至少选择一个");
    }
};

assessCommonLand.getLand = function (id,callback) {
    declareCommon.getLandData(id,callback);
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
        assessCommonLand.getLand(rows[0].id , function (data) {
            assessCommonLand.init(data);
        }) ;
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
    var cols = commonDeclareApplyModel.land.getLandColumn() ;
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
        enable:declareCommon.masterData
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
    if (!declareCommon.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.enable = declareCommon.masterData;
        data.pid = "0";
        data.declareType = declareCommon.declareLandType;
    }
    declareCommon.saveLandData(data , function () {
        assessCommonLand.loadList();
        toastr.success('成功!');
        $('#' + assessCommonLand.config.box).modal("hide");
    });
};


assessCommonLand.initHouse = function (item) {
    declareCommon.initHouse(item,$("#" + assessCommonLand.config.HouseCert.frm),[assessCommonLand.config.houseFileId],null);
};

/**
 * @author:  zch
 * 描述:房产证数据
 * @date:2018-09-21
 **/
assessCommonLand.showAddModelHouse = function (id) {
    $('#' + assessCommonLand.config.HouseCert.box).find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    $('#' + assessCommonLand.config.HouseCert.box).find(".panel-body").append(commonDeclareApplyModel.house.getHtml());
    declareCommon.showHtmlMastInit($("#" + assessCommonLand.config.HouseCert.frm), function () {
        $('#' + assessCommonLand.config.HouseCert.box).modal("show");
        var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
        if (declareCommon.isNotBlank(item.pid)) {
            declareCommon.getHouseData(item.pid,function (data) {
                if (declareCommon.isNotBlank(data)) {
                    assessCommonLand.initHouse(data);
                } else {
                    toastr.success('关联数据已经被删除了!');
                    toastr.success('请重新填写');
                }
            });
        } else {
            var data = {
                pid: item.id,
                beLocated: item.beLocated,
                streetNumber: item.streetNumber,
                attachedNumber: item.attachedNumber,
                buildingNumber: item.buildingNumber,
                unit: item.unit,
                roomNumber: item.roomNumber,
                floor: item.floor,
                province: item.province,
                city: item.city,
                district: item.district
            };
            assessCommonLand.initHouse(data);
        }
    });
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
    data.enable = declareCommon.branchData;
    declareCommon.saveHouseData(data,function (houseId) {
        if (houseId){
            var landId = data.pid ;
            var item = $("#"+assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', landId);
            declareCommon.saveLandData({id:landId,pid:houseId} , function () {
                toastr.success('成功!');
                $('#' + assessCommonLand.config.HouseCert.box).modal("hide");
                assessCommonLand.loadList();
            });
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
                assessCommonLand.loadList();
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
                assessCommonLand.loadList();
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            console.log(result) ;
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

$(function () {
    assessCommonLand.loadList();
});

