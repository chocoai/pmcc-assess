/**
 * Created by kings on 2018-12-21.
 */

var declareRealtyRealEstateCert = {};

declareRealtyRealEstateCert.config = {
    frm: "frmDeclareRealtyRealEstateCert",
    name: "房产证",
    table: "tableDeclareRealtyRealEstateCert",
    box: "boxDeclareRealtyRealEstateCert",
    fileId: "declareRealtyRealEstateCertFileId",
    newFileId: "declareRealtyRealEstateCertNewFileId",
    fileView: "declareRealtyRealEstateCertFileView"
};

declareRealtyRealEstateCert.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};

declareRealtyRealEstateCert.fileUpload2 = function (target, tableName, id) {
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
            declareRealtyRealEstateCert.showFile(target, tableName, id);
        },
        deleteFlag: true
    });
};

/**
 * @author:  zch
 * 描述:房产证 文件显示
 * @date:2018-09-19
 **/
declareRealtyRealEstateCert.showFile = function (target, tableName, id) {
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


declareRealtyRealEstateCert.init = function (item) {
    $("#" + declareRealtyRealEstateCert.config.frm).initForm(item);
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + declareRealtyRealEstateCert.config.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, item.type, function (html, data) {
        $("#" + declareRealtyRealEstateCert.config.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        $("#" + declareRealtyRealEstateCert.config.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + declareRealtyRealEstateCert.config.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + declareRealtyRealEstateCert.config.frm).find("select[name='province']"),
        cityTarget: $("#" + declareRealtyRealEstateCert.config.frm).find("select[name='city']"),
        districtTarget: $("#" + declareRealtyRealEstateCert.config.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    $("#" + declareRealtyRealEstateCert.config.frm + " input[name='registrationTime']").val(formatDate(item.registrationTime));
    $("#" + declareRealtyRealEstateCert.config.frm + " input[name='useEndDate']").val(formatDate(item.useEndDate));
    $("#" + declareRealtyRealEstateCert.config.frm + " input[name='useStartDate']").val(formatDate(item.useStartDate));
    $("#" + declareRealtyRealEstateCert.config.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    $("#" + declareRealtyRealEstateCert.config.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    declareRealtyRealEstateCert.showFile(declareRealtyRealEstateCert.config.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, declareRealtyRealEstateCert.isNotBlank(item.id) ? item.id : '0');
    declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCert.config.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, declareRealtyRealEstateCert.isNotBlank(item.id) ? item.id : '0');
};

declareRealtyRealEstateCert.showAddModel = function () {
    $('#' + declareRealtyRealEstateCert.config.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + declareRealtyRealEstateCert.config.box).find(".panel-body").prepend(commonDeclareApplyModel.realEstateCert.getHtml());
    mapPosition.getCurrentCityByArea(function (area) {
        declareRealtyRealEstateCert.init(area);
    });
    try { //由于是填充的hmtl所以需要手动初始化select2
        DatepickerUtils.parse();
        $('#' + declareRealtyRealEstateCert.config.box).find(".select2").each(function () {
            $(this).select2();
        });
    } catch (e) {
    }
    $("#" + declareRealtyRealEstateCert.config.frm).clearAll();
    $("#" + declareRealtyRealEstateCert.config.frm).validate();
    $('#' + declareRealtyRealEstateCert.config.box).modal("show");
};


declareRealtyRealEstateCert.editData = function () {
    var rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        declareRealtyRealEstateCert.showAddModel();
        $.ajax({
            url: getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
            type: "get",
            dataType: "json",
            data: {id: rows[0].id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        declareRealtyRealEstateCert.init(result.data);
                    } else {
                        declareRealtyRealEstateCert.init({});
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $('#' + declareRealtyRealEstateCert.config.box).modal("show");
        $("#" + declareRealtyRealEstateCert.config.frm).validate();
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

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
                declareRealtyRealEstateCert.loadList();
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
 * 描述:批量导入
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
    var cols = [];
    cols.push({field: 'useRightType', title: '使用权类型'});
    cols.push({field: 'publicArea', title: '公摊面积'});
    cols.push({field: 'evidenceArea', title: '证载面积'});
    cols.push({field: 'certName', title: '权证号'});
    cols.push({field: 'beLocated', title: '坐落'});
    cols.push({field: 'fileViewName', title: '不动产附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='不动产附件' onclick='declareRealtyRealEstateCert.enclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "不动产附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('destroy');
    TableInit(declareRealtyRealEstateCert.config.table, getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),enable:'yes'
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
                Alert("是否删除", 2, null,
                    function () {
                        $.ajax({
                            type: "POST",
                            url: getContextPath() + "/declareRealtyRealEstateCert/deleteDeclareRealtyRealEstateCertById",
                            data: {ids: ids},
                            success: function (result) {
                                if (result.ret) {
                                    declareRealtyRealEstateCert.loadList();
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
            }
        );
    } else {
        Alert("至少选择一个");
    }
};

declareRealtyRealEstateCert.saveAndUpdateData = function () {
    if (!$("#" + declareRealtyRealEstateCert.config.frm).valid()) {
        return false;
    }
    var data = formParams(declareRealtyRealEstateCert.config.frm);
    if (!declareRealtyRealEstateCert.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.declareType = declareFunObj.getDeclareType("不动产证");
        data.enable = "yes";
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                toastr.success('成功!');
                $('#' + declareRealtyRealEstateCert.config.box).modal("hide");
                declareRealtyRealEstateCert.loadList();
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};


$(function () {
    declareRealtyRealEstateCert.loadList();
});
