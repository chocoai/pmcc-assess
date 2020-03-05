/**
 * Created by kings on 2018-12-21.
 */

var declareRealtyRealEstateCert = {};

declareRealtyRealEstateCert.config = {
    frm: declareCommon.config.declareRealty.frm,
    name: declareCommon.config.declareRealty.name,
    table: declareCommon.config.declareRealty.table,
    box: declareCommon.config.declareRealty.box,
    fileId: declareCommon.config.declareRealty.fileId,
    newFileId: declareCommon.config.declareRealty.newFileId,
    fileView: declareCommon.config.declareRealty.fileView,
    declareEconomicIndicatorsHead: {
        frm: declareCommon.config.declareEconomicIndicatorsHead2.frm,
        name: declareCommon.config.declareEconomicIndicatorsHead2.name,
        box: declareCommon.config.declareEconomicIndicatorsHead2.box
    },
    declareEconomicIndicatorsContent: {
        frm: declareCommon.config.declareEconomicIndicatorsContent2.frm,
        name: declareCommon.config.declareEconomicIndicatorsContent2.name
    },
    handleCopy: "#realtyRealEstateHandleInputGroup",
    autoPDFFileId: "RealEstateAttachmentAutomatedWarrantsPDF"
};

declareRealtyRealEstateCert.init = function (item) {
    declareCommon.initDeclareRealty(item, $("#" + declareRealtyRealEstateCert.config.frm), [declareRealtyRealEstateCert.config.newFileId], null);
};

declareRealtyRealEstateCert.showAddModel = function () {
    $('#' + declareRealtyRealEstateCert.config.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + declareRealtyRealEstateCert.config.box).find(".card-body").prepend(commonDeclareApplyModel.realEstateCert.getHtml());
    declareCommon.showHtmlMastInit($("#" + declareRealtyRealEstateCert.config.frm), function (area) {
        declareRealtyRealEstateCert.init(area);
        $('#' + declareRealtyRealEstateCert.config.box).modal("show");
    });
};


declareRealtyRealEstateCert.editData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要编辑的数据!");
    } else if (rows.length == 1) {
        declareRealtyRealEstateCert.showAddModel();
        declareRealtyRealEstateCert.init(rows[0]);
        $('#' + declareRealtyRealEstateCert.config.box).modal("show");
        $("#" + declareRealtyRealEstateCert.config.frm).validate();
        $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
    } else {
        notifyWarning("警告", "只能选择一行数据进行编辑!");

    }
};
//上传附件到服务端
declareRealtyRealEstateCert.enclosureFun = function () {
    var target = $("#" + declareRealtyRealEstateCert.config.fileId);
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
            tableName: AssessDBKey.DeclareRealtyRealEstateCert,
            tableId: id,
            fieldsName: declareRealtyRealEstateCert.config.newFileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: target.attr("id"),//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareCommon.getDeclareRealtyData(id, function (row) {
                    notifyInfo('提示', "操作成功!");
                    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('updateByUniqueId', {
                        id: id,
                        row: row
                    });
                });
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
        }
    });
};
/**
 * @author:  zch
 * 描述:附件
 * @date:2018-09-21
 **/
declareRealtyRealEstateCert.enclosure = function (id) {
    $("#" + declareRealtyRealEstateCert.config.fileId).attr("data-id", id);
    $("#" + declareRealtyRealEstateCert.config.fileId).trigger('click');
};

/**
 * @author:  zch
 * 描述:excel 批量导入
 * @date:2018-09-21
 **/
declareRealtyRealEstateCert.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: 'ajaxFileUploadRealEstate',//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyRealEstateCert.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
        }
    });
};


declareRealtyRealEstateCert.loadList = function () {
    var cols = declareCommon.getRealEstateColumn();
    cols.push({field: 'fileViewName', title: '不动产附件'});


    cols.push({
        field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
            var str = "";
            str += '<button type="button" onclick="declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">经济指标</button>';

            str += '<button type="button" onclick="declareRealtyRealEstateCert.enclosure(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="不动产附件">不动产附件</button>';

            str += '<div class="dropdown">';
            str += "<button class='btn btn-info btn-xs dropdown-toggle'  style=\"margin-left: 5px;\" data-toggle='dropdown'>许可证信息</button>";
            str += "<div class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
            str += "<a class='dropdown-item' onclick='declareRealtyRealEstateCert.declareBuildingPermitView(" + row.id + ")'>建设工程规划许可证</a>";
            str += "<a class='dropdown-item' onclick='declareRealtyRealEstateCert.declareLandUsePermitView(" + row.id + ")'>建设用地规划许可证</a>";
            str += "<a class='dropdown-item' onclick='declareRealtyRealEstateCert.declareBuildingConstructionPermitView(" + row.id + ")'>建筑工程施工许可证</a>";
            str += "<a class='dropdown-item' onclick='declareRealtyRealEstateCert.declarePreSalePermitView(" + row.id + ")'>商品房预售许可证</a>";
            str += "</div>";
            str += "</div>";

            return str;
        }
    });

    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('destroy');
    TableInit(declareRealtyRealEstateCert.config.table, getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(), enable: declareCommon.masterData
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true, false);
};

declareRealtyRealEstateCert.deleteData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (rows.length >= 1) {
        var arr = [];
        var idCenters = [];
        var bisRecord = 0;
        $.each(rows, function (i, n) {
            arr.push(n.id);
            if (n.centerId) {
                idCenters.push(n.centerId);
            }
            if (declareCommon.isNotBlank(n.bisRecord)) {
                if (n.bisRecord) {
                    bisRecord++;
                }
            }
        });
        if (bisRecord != 0) {
            notifyWarning("警告", "其中包括了已经参与查勘任务的权证,请重新选择!");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
            return false;
        }

        AlertConfirm("是否删除", "", function () {
            declareCommon.deleteDeclareRealtyData(arr.join(","), function () {
                declareRealtyRealEstateCert.loadList();
                notifySuccess("成功", "删除数据成功!");
            });
        });
    } else {
        notifyWarning("警告", "至少选择一个!");
    }
};

declareRealtyRealEstateCert.copyData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要复制的数据!");
    } else if (rows.length == 1) {
        AlertConfirm("是否确认要复制", "", function () {
            $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='name']").val(rows[0].certName);
            $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='id']").val(rows[0].centerId);
            notifySuccess("成功", "复制从数据成功!");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
        });
    } else {
        notifyWarning("警告", "只能选择一行数据进行复制!");
    }
};

declareRealtyRealEstateCert.pasteAll = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyWarning("警告", "请选择要复制的数据!");
    } else if (rows.length >= 1) {
        var copyId = $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='id']").val();
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
            notifyWarning("警告", "需要粘贴的从数据包含了自身,这样情况是不被允许的!");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
            return false;
        }

        AlertConfirm("确认要粘贴", "", function () {
            declareCommon.copyDeclareBuildCenter(copyId, idArray.join(","), function () {
                notifySuccess("成功", "粘贴从数据成功!");
                $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
                $(declareRealtyRealEstateCert.config.handleCopy).find("input").val('');
                declareRealtyRealEstateCert.loadList();
            });
        });
    } else {
        notifyWarning("警告", "只能选择一行数据进行复制!");
    }
};

//建设工程规划许可证
declareRealtyRealEstateCert.declareBuildingPermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
        return false;
    }
    var box = $("#declareBuildingPermitRealtyRealBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingPermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.buildingPermit.getHtml());
    var arr = ["declareBuildingPermitFileId2"];
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
declareRealtyRealEstateCert.declareBuildingPermitSaveAndUpdate = function () {
    var box = $("#declareBuildingPermitRealtyRealBox");
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
declareRealtyRealEstateCert.declareBuildingPermitRemove = function () {
    var box = $("#declareBuildingPermitRealtyRealBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.buildingPermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.buildingPermitId.type, function () {
                    box.modal("hide");
                    notifyInfo('提示', "删除成功!");
                });
            } else {
                notifyWarning("警告", "未添加数据!");
            }
        });
    }
};

//建设用地规划许可证
declareRealtyRealEstateCert.declareLandUsePermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
        return false;
    }
    var box = $("#declareLandUsePermitRealtyRealBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.landUsePermit.handleId).remove();
    box.find(".card-body").append(commonDeclareApplyModel.landUsePermit.getHtml());
    var arr = ["declareLandUsePermitFileId2"];
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
declareRealtyRealEstateCert.declareLandUsePermitSaveAndUpdate = function () {
    var box = $("#declareLandUsePermitRealtyRealBox");
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
declareRealtyRealEstateCert.declareLandUsePermitRemove = function () {
    var box = $("#declareLandUsePermitRealtyRealBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landUsePermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.landUsePermitId.type, function () {
                    box.modal("hide");
                    notifyInfo('提示', "删除成功!");
                });
            } else {
                notifyWarning("警告", "未添加数据!");
            }
        });
    }
};

//建筑工程施工许可证
declareRealtyRealEstateCert.declareBuildingConstructionPermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
        return false;
    }
    var arr = ["declareBuildingConstructionPermitFileId2"];
    var inputArr = ["date", "contractPeriod"];
    var box = $("#declareBuildingConstructionPermitRealtyRealBox");
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
declareRealtyRealEstateCert.declareBuildingConstructionPermitRemove = function () {
    var box = $("#declareBuildingConstructionPermitRealtyRealBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.buildingConstructionPermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.buildingConstructionPermitId.type, function () {
                    box.modal("hide");
                    notifyInfo('提示', "删除成功!");
                });
            } else {
                notifyWarning("警告", "未添加数据!");
            }
        });
    }
};

//建筑工程施工许可证
declareRealtyRealEstateCert.declareBuildingConstructionPermitSaveAndUpdate = function () {
    var box = $("#declareBuildingConstructionPermitRealtyRealBox");
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
declareRealtyRealEstateCert.declarePreSalePermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");

        return false;
    }
    var arr = ["declarePreSalePermitFileId2"];
    var inputArr = ["date"];
    var box = $("#declarePreSalePermitRealtyRealBox");
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
declareRealtyRealEstateCert.declarePreSalePermitRemove = function () {
    var box = $("#declarePreSalePermitRealtyRealBox");
    var frm = box.find("form");
    var data = formSerializeArray(frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.preSalePermitId)) {
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.preSalePermitId.type, function () {
                    box.modal("hide");
                    notifyInfo('提示', "删除成功!");
                });
            } else {
                notifyWarning("警告", "未添加数据!");
            }
        });
    }
};
//商品房预售许可证
declareRealtyRealEstateCert.declarePreSalePermitSaveAndUpdate = function () {
    var box = $("#declarePreSalePermitRealtyRealBox");
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

//经济指标
declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifyWarning("警告", "不合符调整后的数据约定,请联系管理员!");
        return false;
    }
    var showDelHtml = "";
    showDelHtml += declareCommon.declareCenterData.indicatorIdDelHtml;
    showDelHtml = showDelHtml.replace(/{method}/g, 'declareRealtyRealEstateCert.deleteDeclareEconomicIndicatorsCenter');
    declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
        if (centerData.indicatorId) {
            economicIndicators.init({
                planDetailsId: declareCommon.getPlanDetailsId(),
                economicId: centerData.indicatorId,
                showDelHtml: showDelHtml,
                centerId: item.centerId
            });
        } else {
            economicIndicators.init({
                planDetailsId: declareCommon.getPlanDetailsId(),
                saveCallback: function (economicId) {//经济指标id更新到中间表
                    declareCommon.declareBuildCenterSaveAndUpdate({indicatorId: economicId, id: item.centerId});
                },
                showDelHtml: showDelHtml,
                centerId: item.centerId,
                targetCallback: function () {
                    economicIndicators.autoSummary(true);
                }
            });
        }
    });
};

//经济指标删除
declareRealtyRealEstateCert.deleteDeclareEconomicIndicatorsCenter = function (frmEle, box) {
    var data = formParams(frmEle);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.indicatorId.type, function () {
                    $('#' + box).modal("hide");
                    notifyInfo('提示', "删除成功!");


                    economicIndicators.autoSummary(true);
                    declareRealtyRealEstateCert.loadList();
                });
            } else {
                notifyWarning("警告", "未添加数据!");

            }
        });
    }
};


declareRealtyRealEstateCert.saveAndUpdateData = function () {
    if (!$("#" + declareRealtyRealEstateCert.config.frm).valid()) {
        return false;
    }
    var data = formParams(declareRealtyRealEstateCert.config.frm);
    if (!declareCommon.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.declareType = declareCommon.declareRealType;
        data.enable = declareCommon.masterData;
    }
    //当土地证填写后
    if (data.landNumber) {
        var html = "<span class='help-block' for='for'>" + "该字段为必填项(土地证号需要的基本数据)" + "</span>";
        if (!data.landAcquisition) {
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='landAcquisition']").after(html.replace(/for/g, "landAcquisition"));
            return false;
        }
        if (!data.registrationAuthority) {
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='registrationAuthority']").after(html.replace(/for/g, "registrationAuthority"));
            return false;
        }
        if (!data.useStartDate) {
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='useStartDate']").after(html.replace(/for/g, "useStartDate"));
            return false;
        }
        if (!data.useEndDate) {
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='useEndDate']").after(html.replace(/for/g, "useEndDate"));
            return false;
        }
    }
    declareCommon.saveDeclareRealtyDataBase(data, true, function (item) {
        if (!declareCommon.isNotBlank(data.id)) {
            declareCommon.declareBuildCenterSaveAndUpdate({
                realEstateId: item,
                planDetailsId: declareCommon.getPlanDetailsId(),
                type: declareCommon.declareCenterData.realEstateId.type
            }, function () {
                //重新加载一次
                declareRealtyRealEstateCert.loadList();
            });
        }
        notifySuccess("成功", "保存操作成功!");

        $('#' + declareRealtyRealEstateCert.config.box).modal("hide");
        declareRealtyRealEstateCert.loadList();
    });
};

/*自动关联编号的附件*/
declareRealtyRealEstateCert.attachmentAutomatedWarrants = function (_this) {
    var group = $(_this).closest(".form-group");
    var prefixNumber = group.find("[name='prefixNumber']").val();
    var startNumber = group.find("[name='startNumber']").val();
    var endNumber = group.find("[name='endNumber']").val();
    var step = group.find("[name='step']").val();
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
        fieldsName: declareRealtyRealEstateCert.config.newFileId,
        tableName: AssessDBKey.DeclareRealtyRealEstateCert,
        planDetailsId: declareCommon.getPlanDetailsId()
    };
    if (startNumber > endNumber) {
        notifyWarning("警告", "截至编号 必须大于 启始编号!");
        return false;
    }
    var query = {
        tableId: declareCommon.getPlanDetailsId(),
        tableName: AssessDBKey.DeclareRealtyRealEstateCert,
        fieldsName: declareRealtyRealEstateCert.config.autoPDFFileId
    };
    AssessCommon.getSysAttachmentDtoList(query, function (array) {
        if (!array) {
            notifyWarning("警告", "请上传pdf或者word 附件!");
            return false;
        }
        if (array.length == 1) {
            data.attachmentId = array[0].id;
            Loading.progressShow();
            declareCommon.ajaxServerMethod(data, "/declareRealtyRealEstateCert/attachmentAutomatedWarrants", "post", function () {
                Loading.progressHide();
                (function (id, FileId, tableName) {
                    declareCommon.fileUpload(FileId, tableName, id, true);
                    declareCommon.showFile(FileId, tableName, id, true);
                }(query.tableId, query.fieldsName, query.tableName));
                declareRealtyRealEstateCert.loadList();
                notifyInfo('提示', "操作成功!");
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


    declareRealtyRealEstateCert.loadList();


    //pdf 附件
    (function (id, FileId, tableName) {
        declareCommon.fileUpload(FileId, tableName, id, true);
        declareCommon.showFile(FileId, tableName, id, true);
    }(declareCommon.getPlanDetailsId(), declareRealtyRealEstateCert.config.autoPDFFileId, AssessDBKey.DeclareRealtyRealEstateCert));

});
