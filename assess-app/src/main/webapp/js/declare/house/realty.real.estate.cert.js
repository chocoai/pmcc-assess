/**
 * Created by kings on 2018-12-21.
 */
var declareRealtyRealEstateCertConfig = {
    frm: "frmDeclareRealtyRealEstateCert",
    name: "房产证",
    table: "tableDeclareRealtyRealEstateCert",
    box: "boxDeclareRealtyRealEstateCert",
    fileId: "declareRealtyRealEstateCertFileId",
    newFileId: "declareRealtyRealEstateCertNewFileId",
    fileView: "declareRealtyRealEstateCertFileView"
};

var declareRealtyRealEstateCert = new Object();

declareRealtyRealEstateCert.isEmpty = function (item) {
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



declareRealtyRealEstateCert.objectWriteSelectData = function (frm, data, name) {
    if (declareRealtyRealEstateCert.isEmpty(data)) {
        $("#" + frm + " ." + name).val(data).trigger("change");
    } else {
        $("#" + frm + " ." + name).val(null).trigger("change");
    }
};


declareRealtyRealEstateCert.init = function (item) {
    $("#" + declareRealtyRealEstateCertConfig.frm).initForm(item);
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType,item.type, function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation,item.publicSituation, function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType,item.useRightType, function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + declareRealtyRealEstateCertConfig.frm).find("select[name='province']"),
        cityTarget: $("#" + declareRealtyRealEstateCertConfig.frm).find("select[name='city']"),
        districtTarget: $("#" + declareRealtyRealEstateCertConfig.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='registrationTime']").val(formatDate(item.registrationTime));
    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='useEndDate']").val(formatDate(item.useEndDate));
    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='useStartDate']").val(formatDate(item.useStartDate));
    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    declareRealtyRealEstateCert.showFile(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, declareRealtyRealEstateCert.isEmpty(item.id)?item.id:'0');
    declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, declareRealtyRealEstateCert.isEmpty(item.id)?item.id:'0');
};

declareRealtyRealEstateCert.showAddModel = function () {
    $('#' + declareRealtyRealEstateCertConfig.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + declareRealtyRealEstateCertConfig.box).find(".panel-body").append(commonDeclareApplyModel.realEstateCert.getHtml());
    declareRealtyRealEstateCert.init({});
    try { //由于是填充的hmtl所以需要手动初始化select2
        DatepickerUtils.parse();
        $('#' + declareRealtyRealEstateCertConfig.box).find(".select2").each(function () {
            $(this).select2();
        });
    } catch (e) {
    }
    $("#" + declareRealtyRealEstateCertConfig.frm).clearAll();
    $("#" + declareRealtyRealEstateCertConfig.frm).validate();
    $('#' + declareRealtyRealEstateCertConfig.box).modal("show");
};


declareRealtyRealEstateCert.editData = function (id) {
    declareRealtyRealEstateCert.showAddModel();
    $.ajax({
        url: getContextPath()+"/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (result.data){
                    declareRealtyRealEstateCert.init(result.data);
                }else {
                    declareRealtyRealEstateCert.init({});
                }
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
    $('#' + declareRealtyRealEstateCertConfig.box).modal("show");
    $("#" + declareRealtyRealEstateCertConfig.frm).validate();
};

declareRealtyRealEstateCert.enclosureFun = function () {
    var id = $("#"+declareRealtyRealEstateCertConfig.fileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath()+"/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName:AssessDBKey.DeclareRealtyRealEstateCert,
            tableId:id,
            fieldsName:declareRealtyRealEstateCertConfig.newFileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: declareRealtyRealEstateCertConfig.fileId,//文件选择框的id属性
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
    $("#"+declareRealtyRealEstateCertConfig.fileId).attr("data-id",id);
    $("#"+declareRealtyRealEstateCertConfig.fileId).trigger('click');
};

/**
 * @author:  zch
 * 描述:批量导入
 * @date:2018-09-21
 **/
declareRealtyRealEstateCert.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath()+"/declareRealtyRealEstateCert/importData",
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

declareRealtyRealEstateCert.distinguish = function () {

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
            str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="declareRealtyRealEstateCert.editData(' + row.id + ',\'exampleList\')"><i class="fa fa-edit fa-white"></i></a>';
            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="declareRealtyRealEstateCert.deleteData(' + row.id + ',\'exampleList\')"><i class="fa fa-minus fa-white"></i></a>';
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='不动产附件' onclick='declareRealtyRealEstateCert.enclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "不动产附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + declareRealtyRealEstateCertConfig.table).bootstrapTable('destroy');
    TableInit(declareRealtyRealEstateCertConfig.table, getContextPath()+"/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
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

declareRealtyRealEstateCert.deleteData = function (id) {
    Alert("是否删除",2,null,
        function (){
            $.ajax({
                type: "POST",
                url: getContextPath()+"/declareRealtyRealEstateCert/deleteDeclareRealtyRealEstateCertById",
                data: {id: id},
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
};

declareRealtyRealEstateCert.saveAndUpdateData = function () {
    if (!$("#" + declareRealtyRealEstateCertConfig.frm).valid()) {
        return false;
    }
    var data = formParams(declareRealtyRealEstateCertConfig.frm);
    if (!declareRealtyRealEstateCert.isEmpty(data.id)){
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.declareType = declareFunObj.getDeclareType("不动产证");
    }
    $.ajax({
        type: "POST",
        url: getContextPath()+"/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                toastr.success('成功!');
                $('#' + declareRealtyRealEstateCertConfig.box).modal("hide");
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
