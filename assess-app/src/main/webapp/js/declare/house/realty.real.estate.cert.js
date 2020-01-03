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
    handleCopy: "#realtyRealEstateHandleInputGroup"
};

declareRealtyRealEstateCert.init = function (item) {
    declareCommon.initDeclareRealty(item, $("#" + declareRealtyRealEstateCert.config.frm), [declareRealtyRealEstateCert.config.newFileId], null);
};

declareRealtyRealEstateCert.showAddModel = function () {
    $('#' + declareRealtyRealEstateCert.config.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + declareRealtyRealEstateCert.config.box).find(".panel-body").prepend(commonDeclareApplyModel.realEstateCert.getHtml());
    declareCommon.showHtmlMastInit($("#" + declareRealtyRealEstateCert.config.frm), function (area) {
        declareRealtyRealEstateCert.init(area);
        $('#' + declareRealtyRealEstateCert.config.box).modal("show");
    });
};


declareRealtyRealEstateCert.editData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        declareRealtyRealEstateCert.showAddModel();
        declareRealtyRealEstateCert.init(rows[0]);
        $('#' + declareRealtyRealEstateCert.config.box).modal("show");
        $("#" + declareRealtyRealEstateCert.config.frm).validate();
    } else {
        toastr.info("只能选择一行数据进行编辑");
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
                    toastr.success('成功 !');
                    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('updateByUniqueId', {
                        id: id,
                        row: row
                    });
                });
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
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};


declareRealtyRealEstateCert.loadList = function () {
    var cols = declareCommon.getRealEstateColumn();
    cols.push({field: 'fileViewName', title: '不动产附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success" href="javascript:declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators(' + row.id + ');" ><i class="fa fa-themeisle">经济指标</i></a>';
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='不动产附件' onclick='declareRealtyRealEstateCert.enclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "不动产附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    cols.push({
        field: 'creator', title: '许可证信息', formatter: function (value, row, index) {
            var str = '<div class="dropdown">';
            str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>" + "<i class='fa fa-users'>" + "</i>" + "许可证信息" + "<span class='caret'>" + "</span>" + "</button>";
            str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareRealtyRealEstateCert.declareBuildingPermitView(" + row.id + ")'" + ">" + "建设工程规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareRealtyRealEstateCert.declareLandUsePermitView(" + row.id + ")'" + ">" + "建设用地规划许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareRealtyRealEstateCert.declareBuildingConstructionPermitView(" + row.id + ")'" + ">" + "建筑工程施工许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";

            str += "<li role='presentation'>" + "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='declareRealtyRealEstateCert.declarePreSalePermitView(" + row.id + ")'" + ">" + "商品房预售许可证" + "<i class='fa fa-navicon'></i>" + "</a>" + "</li>";


            str += "</ul>";
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
    }, true);
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
            toastr.info("其中包括了已经参与查勘任务的权证,请重新选择");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
            return false;
        }
        Alert("是否删除", 2, null,
            function () {
                declareCommon.deleteDeclareRealtyData(arr.join(","), function () {
                    declareRealtyRealEstateCert.loadList();
                    toastr.success('成功!');
                });
            }
        );
    } else {
        Alert("至少选择一个");
    }
};

declareRealtyRealEstateCert.copyData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要复制的数据");
    } else if (rows.length == 1) {
        Alert("确认要复制？", 2, null, function () {
            $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='name']").val(rows[0].certName);
            $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='id']").val(rows[0].centerId);
            toastr.info("复制从数据成功!");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
        });
    } else {
        toastr.info("只能选择一行数据进行复制");
    }
};

declareRealtyRealEstateCert.pasteAll = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要复制的数据");
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
            toastr.info("需要粘贴的从数据包含了自身,这样情况是不被允许的");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
            return false;
        }
        Alert("确认要粘贴？", 2, null, function () {
            declareCommon.copyDeclareBuildCenter(copyId, idArray.join(","), function () {
                toastr.info("粘贴从数据成功!");
                $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('uncheckAll');
                $(declareRealtyRealEstateCert.config.handleCopy).find("input").val('');
                declareRealtyRealEstateCert.loadList();
            });
        });
    } else {
        toastr.info("只能选择一行数据进行复制");
    }
};

//建设工程规划许可证
declareRealtyRealEstateCert.declareBuildingPermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    var box = $("#declareBuildingPermitRealtyRealBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingPermit.handleId).remove();
    box.find(".panel-body").append(commonDeclareApplyModel.buildingPermit.getHtml());
    var arr = ["declareBuildingPermitFileId2"] ;
    var inputArr = ["date"] ;
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.buildingPermitId) {
                declareCommon.getDeclareBuildingPermitById(centerData.buildingPermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingPermit,inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclareBuildingPermit,inputArr);
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
                    toastr.success('已经删除!');
                });
            } else {
                toastr.success('未添加数据!');
            }
        });
    }
};

//建设用地规划许可证
declareRealtyRealEstateCert.declareLandUsePermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    var box = $("#declareLandUsePermitRealtyRealBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.landUsePermit.handleId).remove();
    box.find(".panel-body").append(commonDeclareApplyModel.landUsePermit.getHtml());
    var arr = ["declareLandUsePermitFileId2"] ;
    var inputArr = ["date"] ;
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.landUsePermitId) {
                declareCommon.getDeclareLandUsePermitById(centerData.landUsePermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareLandUsePermit,inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclareLandUsePermit,inputArr);
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
                    toastr.success('已经删除!');
                });
            } else {
                toastr.success('未添加数据!');
            }
        });
    }
};

//建筑工程施工许可证
declareRealtyRealEstateCert.declareBuildingConstructionPermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    var arr = ["declareBuildingConstructionPermitFileId2"];
    var inputArr = ["date","contractPeriod"] ;
    var box = $("#declareBuildingConstructionPermitRealtyRealBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.buildingConstructionPermit.handleId).remove();
    box.find(".panel-body").append(commonDeclareApplyModel.buildingConstructionPermit.getHtml());
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.buildingConstructionPermitId) {
                declareCommon.getDeclareBuildingConstructionPermitById(centerData.buildingConstructionPermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclareBuildingConstructionPermit,inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclareBuildingConstructionPermit,inputArr);
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
                    toastr.success('已经删除!');
                });
            } else {
                toastr.success('未添加数据!');
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
        declareCommon.declareBuildCenterSaveAndUpdate({buildingConstructionPermitId: item.id, id: data.centerId}, function () {
            box.modal("hide");
        });
    });
};
//商品房预售许可证
declareRealtyRealEstateCert.declarePreSalePermitView = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    var arr = ["declarePreSalePermitFileId2"];
    var inputArr = ["date"] ;
    var box = $("#declarePreSalePermitRealtyRealBox");
    var frm = box.find("form");
    box.find("#" + commonDeclareApplyModel.config.preSalePermit.handleId).remove();
    box.find(".panel-body").append(commonDeclareApplyModel.preSalePermit.getHtml());
    declareCommon.showHtmlMastInit(frm, function (area) {
        box.modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.preSalePermitId) {
                declareCommon.getDeclarePreSalePermitById(centerData.preSalePermitId, function (data) {
                    data.centerId = centerData.id;
                    declareCommon.initFormData(frm, data, arr, false, AssessDBKey.DeclarePreSalePermit,inputArr);
                });
            } else {
                declareCommon.initFormData(frm, {centerId: centerData.id}, arr, false, AssessDBKey.DeclarePreSalePermit,inputArr);
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
                    toastr.success('已经删除!');
                });
            } else {
                toastr.success('未添加数据!');
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
        });
    });
};

//经济指标
declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators = function (id) {
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
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
                    toastr.success('已经删除!');
                    economicIndicators.autoSummary(true);
                    declareRealtyRealEstateCert.loadList();
                });
            } else {
                toastr.success('未添加数据!');
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
        toastr.success('成功!');
        $('#' + declareRealtyRealEstateCert.config.box).modal("hide");
        declareRealtyRealEstateCert.loadList();
    });
};

$(function () {
    declareRealtyRealEstateCert.loadList();
});
