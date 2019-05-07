var assessLand = {};

assessLand.config = {
    frm: "#" + declareCommon.config.land.frm,
    name: declareCommon.config.land.name,
    table: "#" + declareCommon.config.land.table,
    box: "#" + declareCommon.config.land.box,
    fileId: "#" + declareCommon.config.land.fileId,
    newFileId: "#" + declareCommon.config.land.newFileId,
    houseFileId: "#" + declareCommon.config.land.houseFileId,
    newHouseFileId: "#" + declareCommon.config.land.newHouseFileId,
    HouseCert: {
        frm: "#" + declareCommon.config.land.HouseCert.frm,
        box: "#" + declareCommon.config.land.HouseCert.box
    }
};

//分为有权证和无权证的情况
assessLand.showAddModelLand = function (flag) {
    if (flag) {
        $(assessLand.config.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
        $(assessLand.config.box).find(".panel-body").prepend(commonDeclareApplyModel.land.getHtml());
    }
    $(assessLand.config.box).modal("show");
    declareCommon.showHtmlMastInit($(assessLand.config.frm), function (area) {
        declareCommon.initLand(area,$(assessLand.config.frm),[$(assessLand.config.fileId).prop("id")],null);
    });
};

assessLand.saveAndUpdateLand = function () {
    if (!$(assessLand.config.frm).valid()) {
        return false;
    }
    var data = formSerializeArray($(assessLand.config.frm));
    if (!declareCommon.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.enable = declareCommon.masterData;
        data.pid = "0";
        data.declareType = declareCommon.declareLandType;
    }
    declareCommon.saveLandData(data, function () {
        assessLand.loadLandList();
        toastr.success('成功!');
        $(assessLand.config.box).modal("hide");
    });
};

assessLand.editLand = function () {
    var rows = $(assessLand.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        assessLand.showAddModelLand(true);
        declareCommon.initLand(rows[0],$(assessLand.config.frm),[$(assessLand.config.fileId).prop("id")],null);
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

assessLand.deleteLand = function () {
    var data = $(assessLand.config.table).bootstrapTable('getSelections');
    if (data.length >= 1) {
        Alert("是否删除", 2, null,
            function () {
                var arr = [];
                data.forEach(function (item, index, source) {
                    arr.push(item.id);
                });
                declareCommon.deleteLandData(arr.join(","), function () {
                    assessLand.loadLandList();
                    toastr.success('成功!');
                });
            }
        );
    } else {
        Alert("至少选择一个");
    }
};


assessLand.showAddModelHouse = function (id) {
    $(assessLand.config.HouseCert.box).find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    $(assessLand.config.HouseCert.box).find(".panel-body").append(commonDeclareApplyModel.house.getHtml());
    declareCommon.showHtmlMastInit($(assessLand.config.HouseCert.frm), function (area) {
    });
    $(assessLand.config.HouseCert.box).modal("show");
    var item = $(assessLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (declareCommon.isNotBlank(item.pid)) {
        declareCommon.getHouseData(item.pid, function (data) {
            if (declareCommon.isNotBlank(data)) {
                declareCommon.initHouse(data, $(assessLand.config.HouseCert.frm), [$(assessLand.config.houseFileId).prop("id")], null);
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
        declareCommon.initHouse(data, $(assessLand.config.HouseCert.frm), [$(assessLand.config.houseFileId).prop("id")], null);
    }
};

assessLand.saveAndUpdateHouse = function () {
    if (!$(assessLand.config.HouseCert.frm).valid()) {
        return false;
    }
    var data = formSerializeArray($(assessLand.config.HouseCert.frm));
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.enable = declareCommon.branchData;
    declareCommon.saveHouseData(data,function (houseId) {
        if (houseId){
            var landId = data.pid ;
            var item = $(assessLand.config.table).bootstrapTable('getRowByUniqueId', landId);
            declareCommon.saveLandData({id:landId,pid:houseId} , function () {
                toastr.success('成功!');
                $(assessLand.config.HouseCert.box).modal("hide");
                assessLand.loadLandList();
            });
        }
    });
};

assessLand.loadLandList = function () {
    var cols = commonDeclareApplyModel.land.getLandColumn();
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            if (row.pid) {
                str += '<a class="btn btn-xs btn-success" href="javascript:assessLand.showAddModelHouse(' + row.id + ');" ><i class="fa fa-check">房产证</i></a>';
            } else {
                str += '<a class="btn btn-xs btn-success" href="javascript:assessLand.showAddModelHouse(' + row.id + ');" ><i class="fa fa-remove">房产证</i></a>';
            }
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='assessLand.landImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='assessLand.houseImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $(assessLand.config.table).bootstrapTable('destroy');
    TableInit($(assessLand.config.table).prop("id"), getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),
        enable: declareCommon.masterData
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true);
};

$(document).ready(function () {
    assessLand.loadLandList();
});