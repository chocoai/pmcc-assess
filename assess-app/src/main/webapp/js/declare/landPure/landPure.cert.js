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
    },
    handleCopy: "#landHandleInputGroup",
    autoPDFFileId: "landAttachmentAutomatedWarrantsPDF"
};
//分为有权证和无权证的情况
assessLand.showAddModelLandFun = function (flag, item) {
    var box = $(assessLand.config.box) ;
    box.find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    box.find("#" + commonDeclareApplyModel.config.land.handleId2).remove();
    var landCertGetQuestion = null;
    if (flag) {
        landCertGetQuestion = "有权证";
        box.find(".card-body").prepend(commonDeclareApplyModel.land.getHtml());
    } else {
        landCertGetQuestion = "无权证";
        box.find(".card-body").prepend(commonDeclareApplyModel.land.getHtml2());
    }
    box.modal("show");
    declareCommon.showHtmlMastInit($(assessLand.config.frm), function (area) {
        var fileArr = [$(assessLand.config.fileId).prop("id")];
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCertificateIsnull, null, function (html, data) {
            var id = null;
            $.each(data, function (i, n) {
                if (n.name == landCertGetQuestion) {
                    id = n.id;
                }
            });
            area.landCertGetQuestion = id;
            if (jQuery.isEmptyObject(item)) {
                declareCommon.initLand(area, box.find("form"), fileArr, null);
            } else {
                declareCommon.initLand(item, box.find("form"), fileArr, null);
            }
        });
        if (landCertGetQuestion == '无权证') {
            $.each(fileArr, function (i, n) {
                var parent = $("#" + n).parent().parent().parent();
                parent.find(".col-sm-1").text("批文附件");
            });
        } else {
            $.each(fileArr, function (i, n) {
                var parent = $("#" + n).parent().parent().parent();
                parent.find(".col-sm-1").text("土地证附件");
            });
        }
        box.find("input[name='unit']").closest(".form-group").hide();
        box.find("input[name='attachedNumber']").closest(".form-group").hide();
        box.find("input[name='buildingNumber']").closest(".form-group").hide();
        box.find("input[name='streetNumber']").closest(".form-group").hide();
        box.find("input[name='floor']").closest(".form-group").hide();
        box.find("input[name='roomNumber']").closest(".form-group").hide();
        box.find("input[name='beLocated']").removeAttr('readonly');

        box.find("input[name='location']").removeAttr('required');
        box.find("input[name='location']").closest(".form-group").find("span").remove();
        box.find("input[name='number']").removeAttr('required');
        box.find("input[name='number']").closest(".form-group").find("span").remove();
        box.find("select[name='landRightType']").removeAttr('required');
        box.find("select[name='landRightType']").parent().prev().find("span").remove();
        box.find("input[name='year']").removeAttr('required');
        box.find("input[name='year']").closest(".form-group").find("span").remove();
    });
};
//分为有权证和无权证的情况
assessLand.showAddModelLand = function (flag) {
    this.showAddModelLandFun(flag, {});
};

assessLand.saveAndUpdateLand = function () {
    if (!$(assessLand.config.frm).valid()) {
        return false;
    }
    var data = formSerializeArray($(assessLand.config.frm));
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
                $(assessLand.config.table).bootstrapTable('refresh');
            });
        }
        $(assessLand.config.table).bootstrapTable('refresh');
        notifySuccess('成功', '成功!');
        $(assessLand.config.box).modal("hide");
    });
};

assessLand.editLand = function () {
    var rows = $(assessLand.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要编辑的数据");
    } else if (rows.length == 1) {
        var landCertGetQuestion = rows[0].landCertGetQuestion;
        if (landCertGetQuestion) {
            AssessCommon.getDataDicInfo(landCertGetQuestion, function (data) {
                if (data.name == "有权证") {
                    assessLand.showAddModelLandFun(true, rows[0]);
                } else {
                    assessLand.showAddModelLandFun(false, rows[0]);
                }
            });
        } else {
            assessLand.showAddModelLandFun(true, rows[0]);
        }
    } else {
        notifyInfo('提示', "只能选择一行数据进行编辑");
    }
};

//删除土地
assessLand.deleteLand = function () {
    var data = $(assessLand.config.table).bootstrapTable('getSelections');
    if (data.length >= 1) {
        AlertConfirm("确定删除么", "删除后数据将不可恢复", function () {
                var arr = [];
                data.forEach(function (item, index, source) {
                    arr.push(item.id);
                });
                declareCommon.deleteLandData(arr.join(","), function () {
                    $(assessLand.config.table).bootstrapTable('refresh');
                    notifySuccess('成功', '成功!');
                });
            }
        );
    } else {
        notifyInfo("提示", "至少选择一个");
    }
};

//显示关联的房产证
assessLand.showAddModelHouse = function (id) {
    var item = $(assessLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifySuccess('成功', '不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    $(assessLand.config.HouseCert.box).find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    $(assessLand.config.HouseCert.box).find(".card-body").prepend(commonDeclareApplyModel.house.getHtml());
    var data = {
        centerId: item.centerId,
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
    declareCommon.showHtmlMastInit($(assessLand.config.HouseCert.frm), function (area) {
        $(assessLand.config.HouseCert.box).modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
                declareCommon.getHouseData(centerData.houseId, function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        data.centerId = item.centerId;
                        declareCommon.initHouse(data, $(assessLand.config.HouseCert.frm), [$(assessLand.config.houseFileId).prop("id")], null);
                    }
                });
            } else {//未关联情况
                declareCommon.initHouse(data, $(assessLand.config.HouseCert.frm), [$(assessLand.config.houseFileId).prop("id")], null);
            }
        });
    });
};

//关联的房产证 save
assessLand.saveAndUpdateHouse = function () {
    if (!$(assessLand.config.HouseCert.frm).valid()) {
        return false;
    }
    var data = formSerializeArray($(assessLand.config.HouseCert.frm));
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.enable = declareCommon.branchData;
    declareCommon.saveHouseData(data, function (houseId) {
        if (houseId) {
            declareCommon.declareBuildCenterSaveAndUpdate({houseId: houseId, id: data.centerId}, function () {
                $(assessLand.config.table).bootstrapTable('refresh');
                $(assessLand.config.HouseCert.box).modal("hide");
                notifySuccess("成功","保存成功!");
            });
        }
    });
};

/**
 * 关联的房产证删除
 */
assessLand.deleteHouseCenter = function () {
    var data = formSerializeArray($(assessLand.config.HouseCert.frm));
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.houseId.type, function () {
                    notifySuccess('成功', '已经删除!');
                    $(assessLand.config.table).bootstrapTable('refresh');
                    $(assessLand.config.HouseCert.box).modal("hide");
                });
            } else {
                notifySuccess('成功', '未添加数据!');
            }
        });
    }
};

/**
 * 土地证附件触发事件
 */
assessLand.landImportEvent = function (id) {
    $(assessLand.config.newFileId).attr("data-id", id);
    $(assessLand.config.newFileId).trigger('click');
};

//房产证附件触发事件
assessLand.houseImportEvent = function (landId) {
    var data = $(assessLand.config.table).bootstrapTable('getRowByUniqueId', landId);
    declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
        if (declareCommon.isNotBlank(centerData.houseId)) {//关联情况
            $(assessLand.config.newHouseFileId).attr("data-id", centerData.houseId);
            $(assessLand.config.newHouseFileId).trigger('click');
        }else {
            notifySuccess('警告', '请关联房产证数据!');
        }
    });
};
/**
 * 房产证附件 导入处理
 */
assessLand.houseImportHandle = function () {
    var id = $(assessLand.config.newHouseFileId).attr("data-id");
    declareCommon.ajaxFileUploadCommonFun({
        planDetailsId: declareCommon.getPlanDetailsId(),
        tableName: AssessDBKey.DeclareRealtyHouseCert,
        tableId: id,
        fieldsName: $(assessLand.config.houseFileId).prop("id")
    }, $(assessLand.config.newHouseFileId).prop("id"), "/public/importAjaxFile", function () {
        assessLand.loadLandList();
    });
};
/**
 * 土地证附件 导入处理
 */
assessLand.landImportHandle = function () {
    var id = $(assessLand.config.newFileId).attr("data-id");
    declareCommon.ajaxFileUploadCommonFun({
        planDetailsId: declareCommon.getPlanDetailsId(),
        tableName: AssessDBKey.DeclareRealtyLandCert,
        tableId: id,
        fieldsName: $(assessLand.config.fileId).prop("id")
    }, $(assessLand.config.newFileId).prop("id"), "/public/importAjaxFile", function () {
        assessLand.loadLandList();
    });
};
/**
 * 房产证excel导入
 */
assessLand.inputFileHouse = function () {
    declareCommon.ajaxFileUploadCommon({planDetailsId: declareCommon.getPlanDetailsId()}, 'ajaxFileUploadLandHouse', "/declareRealtyLandCert/importDataHouse", function () {
        assessLand.loadLandList();
    });
};

/**
 * @author:  zch
 * 描述:土地证excel 批量导入
 * @date:2018-09-21
 **/
assessLand.inputFile = function (flag) {
    var landCertGetQuestion = null;
    if (flag) {
        landCertGetQuestion = "有权证";
    } else {
        landCertGetQuestion = "无权证";
    }
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCertificateIsnull, null, function (html, data) {
        var id = null;
        $.each(data, function (i, n) {
            if (n.name == landCertGetQuestion) {
                id = n.id;
            }
        });
        declareCommon.ajaxFileUploadCommon({
            planDetailsId: declareCommon.getPlanDetailsId(),
            landCertGetQuestion: id
        }, flag ? 'ajaxFileUploadLand' : 'ajaxFileUploadLandA', "/declareRealtyLandCert/importData", function () {
            assessLand.loadLandList();
        });
    });
};

/*自动关联编号的附件*/
assessLand.attachmentAutomatedWarrants = function (_this) {
    var frm = $(_this).closest("form");
    var options = formSerializeArray(frm);
    var data = {
        fieldsName: $(assessLand.config.fileId).attr("id"),
        tableName: AssessDBKey.DeclareRealtyLandCert,
        planDetailsId: declareCommon.getPlanDetailsId()
    };
    jQuery.extend(data, options);
    if (data.isSource) {
        data.fieldsName = $(assessLand.config.houseFileId).prop("id");
    }
    if (!$.isNumeric(data.startNumber)) {
        notifyWarning("警告", "启始编号非数字请重新填写!");
        return false;
    }
    if (!$.isNumeric(data.endNumber)) {
        notifyWarning("警告", "截至编号非数字请重新填写!");
        return false;
    }
    if (!$.isNumeric(data.step)) {
        notifyWarning("警告", "步长非数字请重新填写!");
        return false;
    }
    if (Number(data.startNumber) > Number(data.endNumber)) {
        notifyWarning("警告", "截至编号 必须大于 启始编号!");
        return false;
    }
    var query = {
        tableId: declareCommon.getPlanDetailsId(),
        tableName: AssessDBKey.DeclareRealtyLandCert,
        fieldsName: assessLand.config.autoPDFFileId
    };
    AssessCommon.getSysAttachmentDtoList(query, function (array) {
        if (!array) {
            notifyWarning("警告", "请上传pdf或者word 附件!");
            return false;
        }
        if (array.length == 1) {
            data.attachmentId = array[0].id;
            declareCommon.ajaxServerMethod(data, "/declareRealtyLandCert/attachmentAutomatedWarrants", "post", function () {
                (function (id, FileId, tableName) {
                    declareCommon.fileUpload(FileId, tableName, id, true);
                    declareCommon.showFile(FileId, tableName, id, true);
                }(query.tableId, query.fieldsName, query.tableName));
                assessLand.loadLandList();
                notifyInfo('提示', "操作成功!");
            });
        } else {
            notifyWarning("警告", "请上传pdf或者word一个!");
            return false;
        }
    });
};

/**
 * 经济指标
 * @param id
 */
assessLand.showAddModelDeclareEconomicIndicators = function (id) {
    var item = $(assessLand.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifySuccess('成功', '不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    var showDelHtml = "";
    showDelHtml += declareCommon.declareCenterData.indicatorIdDelHtml;
    showDelHtml = showDelHtml.replace(/{method}/g, 'assessLand.deleteDeclareEconomicIndicatorsCenter');
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

/**
 * 经济指标删除
 * @param frmEle
 * @param box
 */
assessLand.deleteDeclareEconomicIndicatorsCenter = function (frmEle, box) {
    var data = formParams(frmEle);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.indicatorId.type, function () {
                    $('#' + box).modal("hide");
                    notifySuccess('成功', '已经删除!');
                    economicIndicators.autoSummary(true);
                    $(assessLand.config.table).bootstrapTable('refresh');
                });
            } else {
                notifySuccess('成功', '未添加数据!');
            }
        });
    }
};

assessLand.copyData = function () {
    var table = $(assessLand.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要复制的数据");
    } else if (rows.length == 1) {
        AlertConfirm("确认要复制", "", function () {
            $(assessLand.config.handleCopy).find("input[name='name']").val(rows[0].landCertName);
            $(assessLand.config.handleCopy).find("input[name='id']").val(rows[0].centerId);
            notifyInfo('提示', "复制从数据成功!");
            table.bootstrapTable('uncheckAll');
        });
    } else {
        notifyInfo('提示', "只能选择一行数据进行复制");
    }
};

assessLand.pasteAll = function () {
    var table = $(assessLand.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要复制的数据");
    } else if (rows.length >= 1) {
        var copyId = $(assessLand.config.handleCopy).find("input[name='id']").val();
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
            notifyInfo('提示', "需要粘贴的从数据包含了自身,这样情况是不被允许的");
            table.bootstrapTable('uncheckAll');
            return false;
        }
        AlertConfirm("确认要粘贴", "", function () {
            declareCommon.copyDeclareBuildCenter(copyId, idArray.join(","), function () {
                notifyInfo('提示', "粘贴从数据成功!");
                table.bootstrapTable('uncheckAll');
                $(assessLand.config.handleCopy).find("input").val('');
                $(assessLand.config.table).bootstrapTable('refresh');
            });
        });
    } else {
        notifyInfo('提示', "只能选择一行数据进行复制");
    }
};

assessLand.loadLandList = function () {
    var cols = declareCommon.getLandColumn();
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
            var str = '<button type="button" onclick="assessLand.showAddModelHouse(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="关联的房产证">房产证</button>';

            str += '<button type="button" onclick="assessLand.houseImportEvent(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="房产证附件">房产证附件</button>';
            str += '<button type="button" onclick="assessLand.landImportEvent(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="土地证附件">土地证附件</button>';
            str += '<button type="button" onclick="assessLand.showAddModelDeclareEconomicIndicators(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">经济指标</button>';


            return str;
        }
    });
    var table = $(assessLand.config.table);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
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


//-----------------------分割一下--------------------------------

var declareRealtyRealEstateCert = {};

declareRealtyRealEstateCert.config = {
    frm: declareCommon.config.declareRealty.frm,
    name: declareCommon.config.declareRealty.name,
    table: declareCommon.config.declareRealty.table,
    box: declareCommon.config.declareRealty.box,
    fileId: declareCommon.config.declareRealty.fileId,
    newFileId: declareCommon.config.declareRealty.newFileId,
    fileView: declareCommon.config.declareRealty.fileView,
    handleCopy: "#realtyRealEstateHandleInputGroup",
    autoPDFFileId: "RealEstateAttachmentAutomatedWarrantsPDF"
};

//显示不动产模型（分有证和无证情况）
declareRealtyRealEstateCert.showAddModelFun = function (flag, item) {
    var box = $('#' + declareRealtyRealEstateCert.config.box) ;
    box.find("#" + commonDeclareApplyModel.config.realEstateCert.handleId2).remove();
    box.find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    var landCertGetQuestion = null;
    if (flag) {
        landCertGetQuestion = "有权证";
        box.find(".card-body").prepend(commonDeclareApplyModel.realEstateCert.getHtml());
    } else {
        landCertGetQuestion = "无权证";
        box.find(".card-body").prepend(commonDeclareApplyModel.realEstateCert.getHtml2());
    }
    box.modal("show");
    declareCommon.showHtmlMastInit($("#" + declareRealtyRealEstateCert.config.frm), function (area) {
        var fileArr = [declareRealtyRealEstateCert.config.newFileId];
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCertificateIsnull, null, function (html, data) {
            var id = null;
            $.each(data, function (i, n) {
                if (n.name == landCertGetQuestion) {
                    id = n.id;
                }
            });
            area.landCertGetQuestion = id;
            if (jQuery.isEmptyObject(item)) {
                declareCommon.initDeclareRealty(area, box.find("form"), fileArr, null);
            } else {
                declareCommon.initDeclareRealty(item, box.find("form"), fileArr, null);
            }
        });
        $.each(fileArr, function (i, n) {
            if (landCertGetQuestion == '有权证') {
                var parent = $("#" + n).parent().parent().parent();
                parent.find(".col-sm-1").text("权证附件");
            } else {
                var parent = $("#" + n).parent().parent().parent();
                parent.find(".col-sm-1").text("批文附件");
            }
        });
        box.find("input[name='unit']").closest(".form-group").hide();
        box.find("input[name='attachedNumber']").closest(".form-group").hide();
        box.find("input[name='buildingNumber']").closest(".form-group").hide();
        box.find("input[name='streetNumber']").closest(".form-group").hide();
        box.find("input[name='floor']").closest(".form-group").hide();
        box.find("input[name='roomNumber']").closest(".form-group").hide();
        box.find("input[name='beLocated']").removeAttr('readonly');

        box.find("input[name='location']").removeAttr('required');
        box.find("input[name='location']").closest(".form-group").find("span").remove();
        box.find("input[name='number']").removeAttr('required');
        box.find("input[name='number']").closest(".form-group").find("span").remove();
    });
};

//显示不动产添加模型
declareRealtyRealEstateCert.showAddModel = function (flag) {
    this.showAddModelFun(flag, {});
};

//编辑不动产
declareRealtyRealEstateCert.editData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要编辑的数据");
    } else if (rows.length == 1) {
        var landCertGetQuestion = rows[0].landCertGetQuestion;
        if (landCertGetQuestion) {
            AssessCommon.getDataDicInfo(landCertGetQuestion, function (data) {
                if (data.name == "有权证") {
                    declareRealtyRealEstateCert.showAddModelFun(true, rows[0]);
                } else {
                    declareRealtyRealEstateCert.showAddModelFun(false, rows[0]);
                }
            });
        } else {
            declareRealtyRealEstateCert.showAddModelFun(true, rows[0]);
        }
    } else {
        notifyInfo('提示', "只能选择一行数据进行编辑");
    }
};
//上传附件到服务端
declareRealtyRealEstateCert.enclosureFun = function () {
    var id = $("#" + declareRealtyRealEstateCert.config.fileId).attr("data-id");
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
        fileElementId: declareRealtyRealEstateCert.config.fileId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('refresh');
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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

declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators = function (id) {
    var table = $("#" + declareRealtyRealEstateCert.config.table);
    var item = table.bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        notifySuccess('成功', '不合符调整后的数据约定,请联系管理员!');
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

declareRealtyRealEstateCert.deleteDeclareEconomicIndicatorsCenter = function (frmEle, box) {
    var data = formParams(frmEle);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.indicatorId.type, function () {
                    $('#' + box).modal("hide");
                    notifySuccess('成功', '已经删除!');
                    economicIndicators.autoSummary(true);
                    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('refresh');
                });
            } else {
                notifySuccess('成功', '未添加数据!');
            }
        });
    }
};

/**
 * @author:  zch
 * 描述:excel 批量导入
 * @date:2018-09-21
 **/
declareRealtyRealEstateCert.inputFile = function (flag) {
    var landCertGetQuestion = null;
    if (flag) {
        landCertGetQuestion = "有权证";
    } else {
        landCertGetQuestion = "无权证";
    }
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCertificateIsnull, null, function (html, data) {
        var id = null;
        $.each(data, function (i, n) {
            if (n.name == landCertGetQuestion) {
                id = n.id;
            }
        });
        declareCommon.ajaxFileUploadCommon({
            planDetailsId: declareCommon.getPlanDetailsId(),
            landCertGetQuestion: id
        }, flag ? 'ajaxFileUploadRealEstate' : 'ajaxFileUploadRealEstateA', "/declareRealtyRealEstateCert/importData", function () {
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('refresh');
        });
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

            return str;
        }
    });
    var table = $("#" + declareRealtyRealEstateCert.config.table);
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
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
        $.each(rows, function (i, n) {
            arr.push(n.id);
        });
        AlertConfirm("确认删除", "", function () {
                declareCommon.deleteDeclareRealtyData(arr.join(","), function () {
                    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('refresh');
                    notifySuccess('成功', '成功!');
                });
            }
        );
    } else {
        notifyInfo("提示", "至少选择一个");
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
    declareCommon.saveDeclareRealtyData(data, function (item) {
        notifySuccess('成功', '成功!');
        var table = $("#" + declareRealtyRealEstateCert.config.table);
        $('#' + declareRealtyRealEstateCert.config.box).modal("hide");
        table.bootstrapTable('refresh');
        if (!declareCommon.isNotBlank(data.id)) {
            declareCommon.declareBuildCenterSaveAndUpdate({
                realEstateId: item,
                planDetailsId: declareCommon.getPlanDetailsId(),
                type: declareCommon.declareCenterData.realEstateId.type
            }, function () {
                //重新加载一次
                table.bootstrapTable('refresh');
            });
        }
    });
};

declareRealtyRealEstateCert.copyData = function () {
    var table = $("#" + declareRealtyRealEstateCert.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要复制的数据");
    } else if (rows.length == 1) {
        AlertConfirm("确认要复制", "", function () {
            $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='name']").val(rows[0].certName);
            $(declareRealtyRealEstateCert.config.handleCopy).find("input[name='id']").val(rows[0].centerId);
            notifyInfo('提示', "复制从数据成功!");
            table.bootstrapTable('uncheckAll');
        });
    } else {
        notifyInfo('提示', "只能选择一行数据进行复制");
    }
};


declareRealtyRealEstateCert.pasteAll = function () {
    var table = $("#" + declareRealtyRealEstateCert.config.table);
    var rows = table.bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        notifyInfo('提示', "请选择要复制的数据");
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
            notifyInfo('提示', "需要粘贴的从数据包含了自身,这样情况是不被允许的");
            table.bootstrapTable('uncheckAll');
            return false;
        }
        AlertConfirm("确认要粘贴", "", function () {
            declareCommon.copyDeclareBuildCenter(copyId, idArray.join(","), function () {
                notifyInfo('提示', "粘贴从数据成功!");
                table.bootstrapTable('uncheckAll');
                $(declareRealtyRealEstateCert.config.handleCopy).find("input").val('');
                table.bootstrapTable('refresh');
            });
        });
    } else {
        notifyInfo('提示', "只能选择一行数据进行复制");
    }
};

/*自动关联编号的附件*/
declareRealtyRealEstateCert.attachmentAutomatedWarrants = function (_this) {
    var frm = $(_this).closest("form");
    var options = formSerializeArray(frm);
    var data = {
        fieldsName: declareRealtyRealEstateCert.config.newFileId,
        tableName: AssessDBKey.DeclareRealtyRealEstateCert,
        planDetailsId: declareCommon.getPlanDetailsId()
    };
    jQuery.extend(data, options);
    if (!$.isNumeric(data.startNumber)) {
        notifyWarning("警告", "启始编号非数字请重新填写!");
        return false;
    }
    if (!$.isNumeric(data.endNumber)) {
        notifyWarning("警告", "截至编号非数字请重新填写!");
        return false;
    }
    if (!$.isNumeric(data.step)) {
        notifyWarning("警告", "步长非数字请重新填写!");
        return false;
    }

    if (Number(data.startNumber) > Number(data.endNumber)) {
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
            declareCommon.ajaxServerMethod(data, "/declareRealtyRealEstateCert/attachmentAutomatedWarrants", "post", function () {
                (function (id, FileId, tableName) {
                    declareCommon.fileUpload(FileId, tableName, id, true);
                    declareCommon.showFile(FileId, tableName, id, true);
                }(query.tableId, query.fieldsName, query.tableName));
                declareRealtyRealEstateCert.loadList();
                notifyInfo('提示', "操作成功!");
            });
        } else {
            notifyWarning("警告", "请上传pdf或者word一个!");
            return false;
        }
    });
};




$(document).ready(function () {
    assessLand.loadLandList();
    declareRealtyRealEstateCert.loadList();

    //pdf 附件
    (function (id, FileId, tableName) {
        declareCommon.fileUpload(FileId, tableName, id, true);
        declareCommon.showFile(FileId, tableName, id, true);
    }(declareCommon.getPlanDetailsId(), assessLand.config.autoPDFFileId, AssessDBKey.DeclareRealtyLandCert));

    //pdf 附件
    (function (id, FileId, tableName) {
        declareCommon.fileUpload(FileId, tableName, id, true);
        declareCommon.showFile(FileId, tableName, id, true);
    }(declareCommon.getPlanDetailsId(), declareRealtyRealEstateCert.config.autoPDFFileId, AssessDBKey.DeclareRealtyRealEstateCert));
});