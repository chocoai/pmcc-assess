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
//标识符
declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag = true;
declareRealtyRealEstateCert.startPath = null;
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
            //不动产识别
            if (target == declareRealtyRealEstateCertConfig.newFileId) {
                if (declareRealtyRealEstateCert.isEmpty(result)){

                }
            }
            declareRealtyRealEstateCert.loadList();
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

/**
 * @author:  zch
 * 描述:数据拼接
 * @date:2018-09-21
 **/
declareRealtyRealEstateCert.role = {
    CertName: {
        init: function () {
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='location']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='location']").val())) {
                    declareRealtyRealEstateCert.role.CertName.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='number']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='number']").val())) {
                    declareRealtyRealEstateCert.role.CertName.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " .type").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + declareRealtyRealEstateCertConfig.frm + " .type").eq(1).val();
                if (declareRealtyRealEstateCert.isEmpty(id)) {
                    declareRealtyRealEstateCert.role.CertName.write();
                }
            });
        },
        write: function () {
            var location = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='location']").val();
            var number = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='number']").val();
            var id = $("#" + declareRealtyRealEstateCertConfig.frm + " .type").eq(1).val();
            if (!declareRealtyRealEstateCert.isEmpty(location)) {
                location = "";
            }
            if (!declareRealtyRealEstateCert.isEmpty(number)) {
                number = "";
            }
            if (!declareRealtyRealEstateCert.isEmpty(id)) {
                id = "";
            }
            if (declareRealtyRealEstateCert.isEmpty(id)) {
                AssessCommon.getDataDicInfo(id, function (data) {
                    if (declareRealtyRealEstateCert.isEmpty(data)) {
                        var temp = location + "房权证" + data.name + "字地" + number + "号";
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='certName']").val(temp);
                    }
                });
            } else {
                var temp = location + "房权证" + id + "字地" + number + "号";
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='certName']").val(temp);
            }
        }
    },
    beLocated: {
        init: function () {
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='unit']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='unit']").val())) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='floor']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='floor']").val())) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='roomNumber']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='roomNumber']").val())) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='streetNumber']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='streetNumber']").val())) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='attachedNumber']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='attachedNumber']").val())) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='buildingNumber']").blur(function () {
                if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='buildingNumber']").val())) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
            $("#" + declareRealtyRealEstateCertConfig.frm + " .district").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + declareRealtyRealEstateCertConfig.frm + " .district").eq(1).val();
                if (declareRealtyRealEstateCert.isEmpty(id)) {
                    declareRealtyRealEstateCert.role.beLocated.write();
                }
            });
        },
        write: function () {
            var temp = "";
            var district = $("#" + declareRealtyRealEstateCertConfig.frm + " .district").eq(1).val();
            var unit = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='unit']").val();
            var floor = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='floor']").val();
            var roomNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='roomNumber']").val();
            var streetNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='streetNumber']").val();
            var attachedNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='attachedNumber']").val();
            var buildingNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='buildingNumber']").val();
            if (!declareRealtyRealEstateCert.isEmpty(unit)) {
                unit = "";
            } else {
                unit = unit + "单元";
            }
            if (!declareRealtyRealEstateCert.isEmpty(floor)) {
                floor = "";
            } else {
                floor = floor + "楼";
            }
            if (!declareRealtyRealEstateCert.isEmpty(roomNumber)) {
                roomNumber = "";
            } else {
                roomNumber = roomNumber + "号";
            }
            if (!declareRealtyRealEstateCert.isEmpty(streetNumber)) {
                streetNumber = "";
            }
            if (!declareRealtyRealEstateCert.isEmpty(attachedNumber)) {
                attachedNumber = "";
            } else {
                attachedNumber =   "附" + attachedNumber;
            }
            if (!declareRealtyRealEstateCert.isEmpty(buildingNumber)) {
                buildingNumber = "";
            } else {
                buildingNumber = buildingNumber + "栋";
            }
            if (declareRealtyRealEstateCert.isEmpty(district)) {
                AssessCommon.getAreaById(district, function (data) {
                    if (!declareRealtyRealEstateCert.isEmpty(data)) {
                        district = "";
                    } else {
                        district = data.name;
                    }
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='beLocated']").val(temp);
                });
            } else {
                district = "";
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='beLocated']").val(temp);
            }
        }
    }
};

declareRealtyRealEstateCert.objectWriteSelectData = function (frm, data, name) {
    if (declareRealtyRealEstateCert.isEmpty(data)) {
        $("#" + frm + " ." + name).val(data).trigger("change");
    } else {
        $("#" + frm + " ." + name).val(null).trigger("change");
    }
};


declareRealtyRealEstateCert.init = function () {
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType,'', function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation,'', function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType,'', function (html, data) {
        $("#" + declareRealtyRealEstateCertConfig.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "province"),
        cityTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "city"),
        districtTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "district"),
        provinceValue: '',
        cityValue: '',
        districtValue: ''
    });
    declareRealtyRealEstateCert.role.CertName.init();
    declareRealtyRealEstateCert.role.beLocated.init();
};

declareRealtyRealEstateCert.showAddModel = function () {
    $("#" + declareRealtyRealEstateCertConfig.frm).clearAll();
    if (declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag) {
        declareRealtyRealEstateCert.init();
        declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag = false;
    }
    $("#" + declareRealtyRealEstateCertConfig.frm).validate();
    $('#' + declareRealtyRealEstateCertConfig.box).modal("show");
    declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
};


declareRealtyRealEstateCert.editData = function (id) {
    $("#" + declareRealtyRealEstateCertConfig.frm).clearAll();
    if (declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag) {
        declareRealtyRealEstateCert.init();
        declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag = false;
    }
    $.ajax({
        url: getContextPath()+"/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var data = result.data;
                if (declareRealtyRealEstateCert.isEmpty(data)) {
                    $("#" + declareRealtyRealEstateCertConfig.frm).initForm(result.data);
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                    declareRealtyRealEstateCert.objectWriteSelectData(declareRealtyRealEstateCertConfig.frm, data.type, "type");
                    declareRealtyRealEstateCert.objectWriteSelectData(declareRealtyRealEstateCertConfig.frm, data.useRightType, "useRightType");
                    declareRealtyRealEstateCert.objectWriteSelectData(declareRealtyRealEstateCertConfig.frm, data.purpose, "purpose");
                    AssessCommon.initAreaInfo({
                        provinceTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "province"),
                        cityTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "city"),
                        districtTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "district"),
                        provinceValue: result.data.province,
                        cityValue: result.data.city,
                        districtValue: result.data.district
                    });
                }
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    })
    declareRealtyRealEstateCert.showFile(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, id);
    declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, id);
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
