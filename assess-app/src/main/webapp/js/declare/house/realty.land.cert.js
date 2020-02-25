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
    houseBox: declareCommon.config.land.HouseCert.box,
    houseFile: declareCommon.config.land.houseFileId,
    newHouseFileId: declareCommon.config.land.newHouseFileId,
    handleCopy: "#landHandleInputGroup",
    autoPDFFileId: "landAttachmentAutomatedWarrantsPDF"
};

assessCommonLand.copyData = function () {
    var table = $("#" + assessCommonLand.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要复制的数据!");
    } else if (rows.length == 1) {
        AlertConfirm("确认要复制？", "", function () {
            $(assessCommonLand.config.handleCopy).find("input[name='name']").val(rows[0].landCertName);
            $(assessCommonLand.config.handleCopy).find("input[name='id']").val(rows[0].centerId);
            notifySuccess("成功", "复制从数据成功!");
            table.bootstrapTable('uncheckAll');
        });

    } else {
        notifyInfo("只能选择一行数据进行复制", "请确认!");

    }
};

assessCommonLand.pasteAll = function () {
    var table = $("#" + assessCommonLand.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo("请选择要复制的数据", "请确认!");
    } else if (rows.length >= 1) {
        var copyId = $(assessCommonLand.config.handleCopy).find("input[name='id']").val();
        var idArray = [];
        rows.forEach(function (ele, index) {
            idArray.push(ele.centerId);
        });
        //过滤一次
        var filtered = idArray.filter(function (element, index, array) {
            return element == copyId;
        });
        //判断
        if (filtered.length == 1) {
            notifyInfo("需要粘贴的从数据包含了自身,这样情况是不被允许的", "请确认!");

            table.bootstrapTable('uncheckAll');
            return false;
        }

        AlertConfirm("确认要粘贴", "", function () {
            declareCommon.copyDeclareBuildCenter(copyId, idArray.join(","), function () {
                notifyInfo("粘贴从数据成功", "请确认!");

                table.bootstrapTable('uncheckAll');
                $(assessCommonLand.config.handleCopy).find("input").val('');
                $("#" + assessCommonLand.config.table).bootstrapTable('refresh');
            });
        });

    } else {
        notifyInfo("只能选择一行数据进行复制", "请确认!");

    }
};

/**
 * 房产证附件
 * @param flag
 * @param id
 * @returns {boolean}
 */
assessCommonLand.landImportHandleHouse = function (flag, id) {
    var target = $("#" + assessCommonLand.config.newHouseFileId);
    if (flag) {
        var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
        if (!declareCommon.isNotBlank(item.centerId)) {
            notifyInfo("不合符调整后的数据约定,请联系管理员", "请确认!");

            return false;
        }
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
                target.attr("data-id", centerData.houseId);
                target.trigger('click');
            }
        });
    }
    if (!flag) {
        id = target.attr("data-id");
        var value = target.val();
        if (!declareCommon.isNotBlank(value)) {
            return false;
        }
        $.ajaxFileUpload({
            type: "POST",
            url: getContextPath() + "/public/importAjaxFile",
            data: {
                planDetailsId: declareCommon.getPlanDetailsId(),
                tableName: AssessDBKey.DeclareRealtyHouseCert,
                tableId: id,
                fieldsName: assessCommonLand.config.houseFile
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: target.attr("id"),//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功", "操作成功!");
                    assessCommonLand.loadList();
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
            }
        });
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
    var target = $("#" + assessCommonLand.config.newFileId);
    var id = target.attr("data-id");
    var value = target.val();
    if (!declareCommon.isNotBlank(value)) {
        return false;
    }
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
        fileElementId: target.attr("id"),//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                notifySuccess("成功", "操作成功!");

                assessCommonLand.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("错误", "调用服务端方法失败，失败原因:" + result);

        }
    });
};


assessCommonLand.init = function (item) {
    declareCommon.initLand(item, $("#" + assessCommonLand.config.frm), [assessCommonLand.config.fileId], null);
};

/**
 * 土地证显示
 */
assessCommonLand.showAddModelLand = function () {
    var target = $('#' + assessCommonLand.config.box);
    var frm = target.find("form");
    target.find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    target.find(".card-body").prepend(commonDeclareApplyModel.land.getHtml());
    target.modal("show");
    declareCommon.showHtmlMastInit(frm, function (area) {
        assessCommonLand.init(area);
    });
};

assessCommonLand.deleteLand = function () {
    var rows = $("#" + assessCommonLand.config.table).bootstrapTable('getSelections');
    if (rows.length >= 1) {

        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            var arr = [];
            rows.forEach(function (item, index, source) {
                arr.push(item.id);
            });
            declareCommon.deleteLandData(arr.join(","), function () {
                assessCommonLand.loadList();
                notifySuccess("成功","操作成功!");
            });
        });

    } else {
        notifyWarning("警告", "至少选择一个!");

    }
};

assessCommonLand.getLand = function (id, callback) {
    declareCommon.getLandData(id, callback);
};

/**
 * 土地证编辑
 */
assessCommonLand.editLand = function () {
    var table = $("#" + assessCommonLand.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo("请选择要编辑的数据","请确认!");

    } else if (rows.length == 1) {
        assessCommonLand.showAddModelLand();
        assessCommonLand.init(rows[0]);
        $("#" + assessCommonLand.config.frm).validate();
        $('#' + assessCommonLand.config.box).modal("show");
        table.bootstrapTable('uncheckAll');
    } else {
        notifyInfo("只能选择一行数据进行编辑","请确认!");

    }
};

/**
 * 土地证列表
 */
assessCommonLand.loadList = function () {
    var cols = declareCommon.getLandColumn();
    cols.push({field: 'fileViewName', title: '附件'});



    cols.push({
        field: 'id', title: '操作', width:"20%",formatter: function (value, row, index) {
            var str = '<button type="button" onclick="assessCommonLand.showAddModelHouse(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-success  btn-xs tooltips"  data-placement="bottom" data-original-title="关联的房产证">';
            str += '关联的房产证 <i class="fa fa-eye"></i>';
            str += '</button>';

            str += '<button type="button" onclick="assessCommonLand.landImportHandleHouse(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-success  btn-xs tooltips"  data-placement="bottom" data-original-title="房产证附件">';
            str += '房产证附件 <i class="fa fa-file"></i>';
            str += '</button>';


            str += '<button type="button" onclick="assessCommonLand.landImportEvent(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-success  btn-xs tooltips"  data-placement="bottom" data-original-title="土地证附件">';
            str += '土地证附件 <i class="fa fa-file"></i>';
            str += '</button>';


            return str;
        }
    });


    cols.push({
        field: 'creator', title: '许可证信息', formatter: function (value, row, index) {
            var str = '<div class="dropdown">';
            str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>" + "<i class='fa fa-users'>" + "</i>" + "许可证信息" + "<span class='caret'>" + "</span>" + "</button>";
            str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='assessCommonLand.declareBuildingPermitView(" + row.id + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='assessCommonLand.declareLandUsePermitView(" + row.id + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='assessCommonLand.declareBuildingConstructionPermitView(" + row.id + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='assessCommonLand.declarePreSalePermitView(" + row.id + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";


            str += "</ul>";
            str += "</div>";
            return str;
        }
    });
    $("#" + assessCommonLand.config.table).bootstrapTable('destroy');
    TableInit(assessCommonLand.config.table, getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),
        enable: declareCommon.masterData
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
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
        data.declareType = declareCommon.declareLandType;
    }
    declareCommon.saveLandData(data, function (item) {
        if (!declareCommon.isNotBlank(data.id)) {
            declareCommon.declareBuildCenterSaveAndUpdate({
                landId: item,
                planDetailsId: declareCommon.getPlanDetailsId(),
                type: declareCommon.declareCenterData.landId.type
            }, function () {
                assessCommonLand.loadList();
            });
        } else {
            assessCommonLand.loadList();
        }
        notifySuccess("成功","操作成功!");
        $('#' + assessCommonLand.config.box).modal("hide");
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
                AlertSuccess("导入消息", result.data);
            }
        },
        error: function (result, status, e) {
            console.log(result);
            Loading.progressHide();
            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
        }
    });
};

assessCommonLand.importDataHouse = function () {
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
                AlertSuccess("导入消息", result.data);
            }
        },
        error: function (result, status, e) {
            console.log(result);
            Loading.progressHide();
            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
        }
    });
};

assessCommonLand.showAddModelHouse = function (id) {
    var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告","不合符调整后的数据约定,请联系管理员!");
        return false;
    }
    var box = $("#" + assessCommonLand.config.houseBox);
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    box.find(".card-body").prepend(commonDeclareApplyModel.house.getHtml());
    var fileArr = [assessCommonLand.config.houseFile];
    declareCommon.showHtmlMastInit(frm, function (area) {
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
                declareCommon.getHouseData(centerData.houseId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initHouse(data, frm, fileArr, null, false);
                });
            } else {//未关联情况
                area.centerId = centerData.id;
                declareCommon.initHouse(area, frm, fileArr, null, false);
            }
        });
    });
    box.modal("show");
};

assessCommonLand.saveHouseData = function () {
    var box = $("#" + assessCommonLand.config.houseBox);
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.enable = declareCommon.branchData;
    declareCommon.saveHouseDataBase(data, true, function (houseId) {
        if (houseId) {
            declareCommon.declareBuildCenterSaveAndUpdate({houseId: houseId, id: data.centerId}, function () {
                assessCommonLand.loadList();
                notifySuccess("成功", "操作成功!");

            });
        }
        box.modal("hide");
    });
};


//建设工程规划许可证
assessCommonLand.declareBuildingPermitView = function (id) {
    var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告","不合符调整后的数据约定,请联系管理员!");

        return false;
    }
    var box = $("#declareBuildingPermitLandBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.buildingPermit.getHtml());
    var arr = ["declareBuildingPermitFileId3"];
    var inputArr = ["date"];
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.buildingPermitId) {
                declareCommon.getDeclareBuildingPermitById(centerData.buildingPermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingPermit, inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclareBuildingPermit, inputArr);
            }
        });
    });
};
//建设工程规划许可证
assessCommonLand.declareBuildingPermitSaveAndUpdate = function () {
    var box = $("#declareBuildingPermitLandBox");
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    declareCommon.saveDeclareBuildingPermit(data, true, function (item) {
        declareCommon.declareBuildCenterSaveAndUpdate({buildingPermitId: item.id, id: data.centerId}, function () {
            box.modal("hide");
            notifySuccess("成功", "保存成功!");
        });
    });
};
//建设工程规划许可证
assessCommonLand.declareBuildingPermitRemove = function () {
    var box = $("#declareBuildingPermitLandBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.buildingPermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.buildingPermitId.type, function () {
                    box.modal("hide");
                    notifyInfo("已经删除","请确认!");
                });
            } else {
                notifyInfo("未添加数据","请确认!");

            }
        });
    }
};

//建设用地规划许可证
assessCommonLand.declareLandUsePermitView = function (id) {
    var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告","不合符调整后的数据约定,请联系管理员!");

        return false;
    }
    var box = $("#declareLandUsePermitLandBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.landUsePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.landUsePermit.getHtml());
    var arr = ["declareLandUsePermitFileId3"];
    var inputArr = ["date"];
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.landUsePermitId) {
                declareCommon.getDeclareLandUsePermitById(centerData.landUsePermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareLandUsePermit, inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclareLandUsePermit, inputArr);
            }
        });
    });
};
//建设用地规划许可证
assessCommonLand.declareLandUsePermitSaveAndUpdate = function () {
    var box = $("#declareLandUsePermitLandBox");
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    declareCommon.saveDeclareLandUsePermit(data, true, function (item) {
        declareCommon.declareBuildCenterSaveAndUpdate({landUsePermitId: item.id, id: data.centerId}, function () {
            box.modal("hide");
            notifySuccess("成功", "保存成功!");
        });
    });
};
//建设用地规划许可证
assessCommonLand.declareLandUsePermitRemove = function () {
    var box = $("#declareLandUsePermitLandBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landUsePermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.landUsePermitId.type, function () {
                    box.modal("hide");
                    notifyInfo("已经删除","请确认!");
                });
            } else {
                notifyInfo("未添加数据","请确认!");

            }
        });
    }
};

//建筑工程施工许可证
assessCommonLand.declareBuildingConstructionPermitView = function (id) {
    var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告","不合符调整后的数据约定,请联系管理员!");

        return false;
    }
    var arr = ["declareBuildingConstructionPermitFileId3"];
    var inputArr = ["date", "contractPeriod"];
    var box = $("#declareBuildingConstructionPermitLandBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingConstructionPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.buildingConstructionPermit.getHtml());
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.buildingConstructionPermitId) {
                declareCommon.getDeclareBuildingConstructionPermitById(centerData.buildingConstructionPermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclareBuildingConstructionPermit, inputArr);
            }
        });
    });
};
//建筑工程施工许可证
assessCommonLand.declareBuildingConstructionPermitRemove = function () {
    var box = $("#declareBuildingConstructionPermitLandBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.buildingConstructionPermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.buildingConstructionPermitId.type, function () {
                    box.modal("hide");
                    notifyInfo("已经删除","请确认!");
                });
            } else {
                notifyInfo("未添加数据","请确认!");

            }
        });
    }
};
//建筑工程施工许可证
assessCommonLand.declareBuildingConstructionPermitSaveAndUpdate = function () {
    var box = $("#declareBuildingConstructionPermitLandBox");
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    declareCommon.saveDeclareBuildingConstructionPermit(data, true, function (item) {
        declareCommon.declareBuildCenterSaveAndUpdate({
            buildingConstructionPermitId: item.id,
            id: data.centerId
        }, function () {
            box.modal("hide");
            notifySuccess("成功", "保存成功!");
        });
    });
};
//商品房预售许可证
assessCommonLand.declarePreSalePermitView = function (id) {
    var item = $("#" + assessCommonLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告","不合符调整后的数据约定,请联系管理员!");
        return false;
    }
    var arr = ["declarePreSalePermitFileId3"];
    var inputArr = ["date"];
    var box = $("#declarePreSalePermitLandBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.preSalePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.preSalePermit.getHtml());
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.preSalePermitId) {
                declareCommon.getDeclarePreSalePermitById(centerData.preSalePermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclarePreSalePermit, inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclarePreSalePermit, inputArr);
            }
        });
    });
};
//商品房预售许可证
assessCommonLand.declarePreSalePermitRemove = function () {
    var box = $("#declarePreSalePermitLandBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.preSalePermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.preSalePermitId.type, function () {
                    box.modal("hide");
                    notifyInfo("已经删除","请确认!");
                });
            } else {
                notifyInfo("未添加数据","请确认!");
            }
        });
    }
};
//商品房预售许可证
assessCommonLand.declarePreSalePermitSaveAndUpdate = function () {
    var box = $("#declarePreSalePermitLandBox");
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    declareCommon.saveDeclarePreSalePermit(data, true, function (item) {
        declareCommon.declareBuildCenterSaveAndUpdate({preSalePermitId: item.id, id: data.centerId}, function () {
            box.modal("hide");
            notifySuccess("成功", "保存成功!");
        });
    });
};

/*自动关联编号的附件*/
assessCommonLand.attachmentAutomatedWarrants = function (_this) {
    var group = $(_this).closest(".form-group");
    var prefixNumber = group.find("[name='prefixNumber']").val();
    var startNumber = group.find("[name='startNumber']").val();
    var endNumber = group.find("[name='endNumber']").val();
    var step = group.find("[name='step']").val();
    var isSource = group.find("[name='isSource']").val();
    if (!$.isNumeric(startNumber)) {
        notifyWarning("警告", "启始编号非数字请重新填写!");
        return false;
    }
    if (!$.isNumeric(endNumber)) {
        notifyWarning("警告", "截至编号非数字请重新填写!");
        return false;
    }
    if (!$.isNumeric(step)) {
        notifyWarning("警告", "步长非数字请重新填写!");
        return false;
    }
    var data = {
        prefixNumber: prefixNumber,
        startNumber: startNumber,
        endNumber: endNumber,
        step: step,
        isSource: 'true',
        fieldsName: assessCommonLand.config.fileId,
        tableName: AssessDBKey.DeclareRealtyLandCert,
        planDetailsId: declareCommon.getPlanDetailsId()
    };
    if (isSource != undefined && isSource != null && isSource != '') {
        data.isSource = isSource;
        data.fieldsName = assessCommonLand.config.houseFile;
    }
    if (startNumber > endNumber) {
        notifyWarning("警告", "截至编号 必须大于 启始编号!");
        return false;
    }
    var query = {
        tableId: declareCommon.getPlanDetailsId(),
        tableName: AssessDBKey.DeclareRealtyLandCert,
        fieldsName: assessCommonLand.config.autoPDFFileId
    };
    AssessCommon.getSysAttachmentDtoList(query, function (array) {
        if (!array) {
            notifyWarning("警告", "请上传pdf或者word 附件!");
            return false;
        }
        if (array.length == 1) {
            data.attachmentId = array[0].id;
            Loading.progressShow();
            declareCommon.ajaxServerMethod(data, "/declareRealtyLandCert/attachmentAutomatedWarrants", "post", function () {
                Loading.progressHide();
                (function (id, FileId, tableName) {
                    declareCommon.fileUpload(FileId, tableName, id, true);
                    declareCommon.showFile(FileId, tableName, id, true);
                }(query.tableId, query.fieldsName, query.tableName));
                assessCommonLand.loadList();
                notifyInfo("成功", "操作成功!");
            }, function (message) {
                Loading.progressHide();
                AlertError("错误", "调用服务端方法失败，失败原因:" + message);
            });
        } else {
            notifyWarning("警告", "请上传pdf或者word一个!");
            return false;
        }
    });
};


$(function () {
    assessCommonLand.loadList();

    //pdf 附件
    (function (id, FileId, tableName) {
        declareCommon.fileUpload(FileId, tableName, id, true);
        declareCommon.showFile(FileId, tableName, id, true);
    }(declareCommon.getPlanDetailsId(), assessCommonLand.config.autoPDFFileId, AssessDBKey.DeclareRealtyLandCert));

});

