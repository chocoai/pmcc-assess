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
    $('#' + declareRealtyRealEstateCert.config.box).find(".card-body").append(commonDeclareApplyModel.realEstateCert.getHtml());
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
    declareCommon.ajaxFileUploadCommonFun({
        planDetailsId: declareCommon.getPlanDetailsId(),
        tableName: AssessDBKey.DeclareRealtyRealEstateCert,
        tableId: id,
        fieldsName: declareRealtyRealEstateCert.config.newFileId
    }, target.attr("id"), "/public/importAjaxFile", function () {
        declareCommon.getDeclareRealtyData(id, function (row) {
            notifyInfo('提示', "操作成功!");
            $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('updateByUniqueId', {
                id: id,
                row: row
            });
        });
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
    declareCommon.ajaxFileUploadCommon({planDetailsId: declareCommon.getPlanDetailsId()}, 'ajaxFileUploadRealEstate', "/declareRealtyRealEstateCert/importData", function () {
        declareRealtyRealEstateCert.loadList();
    });
};


declareRealtyRealEstateCert.loadList = function () {
    var table = $("#" + declareRealtyRealEstateCert.config.table);
    var cols = declareCommon.getRealEstateColumn();
    cols.push({field: 'fileViewName', title: '不动产附件'});
    cols.push({
        field: 'id', title: '操作', width: "20%", formatter: function (value, row, index) {
            var str = "";
            str += '<button type="button" onclick="declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="经济指标">经济指标</button>';
            str += '<button type="button" class="btn btn-xs btn-info tooltips" style="margin-left: 5px;" data-placement="bottom" data-original-title="不动产清单" onclick="declareCommon.loadDeclareRealtyCheckListTable(' + "'" + row.centerId + "'" + "," + "'" + table.selector + "'" + ')" > <i class="fa "></i>不动产清单</button>';

            str += '<button type="button" onclick="declareRealtyRealEstateCert.enclosure(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="不动产附件">不动产附件</button>';

            str += '<div class="dropdown">';
            str += "<button class='btn btn-info btn-xs dropdown-toggle'  style=\"margin-left: 5px;\" data-toggle='dropdown'>许可证信息</button>";
            str += "<div class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>";
            str += "<a class='dropdown-item' onclick='declareCommon.loadTableDeclareBuildingPermit(" + row.centerId + "," + row.centerId + ")'>建设工程规划许可证</a>";
            str += "<a class='dropdown-item' onclick='declareCommon.loadTableDeclareLandUsePermit(" + row.centerId + "," + row.centerId + ")'>建设用地规划许可证</a>";
            str += "<a class='dropdown-item' onclick='declareCommon.loadTableDeclareBuildingConstructionPermit(" + row.centerId + "," + row.centerId + ")'>建筑工程施工许可证</a>";
            str += "<a class='dropdown-item' onclick='declareCommon.loadTableDeclarePreSalePermit(" + row.centerId + "," + row.centerId + ")'>商品房预售许可证</a>";

            str += "</div>";
            str += "</div>";

            return str;
        }
    });
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

declareRealtyRealEstateCert.distinguish = function (_this) {
    var frm = $(_this).closest("form");
    var id = formSerializeArray(frm).id;
    id = declareCommon.isNotBlank(id) ? id : '0';
    $.ajax({
        url: getContextPath() + "/public/getSysAttachmentDtoList",
        type: "get",
        dataType: "json",
        async: false,
        data: {
            tableId: id,
            tableName: AssessDBKey.DeclareRealtyRealEstateCert
        },
        success: function (result) {
            console.log(result) ;
            if (result.ret && result.data) {
                if (result.data.length >= 1) {
                    if (AssessCommon.checkImgFile(result.data[0].fileName)) {//是否是图片检测
                        AssessCommon.parseRealtyHouseCert(result.data[0].id, AssessDBKey.HouseOcrkey, function (data) {//获取图片解析数据
                            console.log(data) ;
                        });
                    } else {
                        notifyWarning("警告", "不是图片!");
                    }
                } else {
                    notifyWarning("警告", "无证件图片!");

                }
            }
        }
    });
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

$(function () {


    declareRealtyRealEstateCert.loadList();


    //pdf 附件
    (function (id, FileId, tableName) {
        declareCommon.fileUpload(FileId, tableName, id, true);
        declareCommon.showFile(FileId, tableName, id, true);
    }(declareCommon.getPlanDetailsId(), declareRealtyRealEstateCert.config.autoPDFFileId, AssessDBKey.DeclareRealtyRealEstateCert));

});
