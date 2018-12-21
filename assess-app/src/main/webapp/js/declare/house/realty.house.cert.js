/**
 * Created by kings on 2018-12-21.
 */

/**
 * @author:  zch
 * 描述:房产证 所以配置信息
 * @date:2018-09-19
 **/
var declareRealtyHouseCertConfig = {
    frm: "frmDeclareRealtyHouseCert",
    name: "房产证",
    table: "tableDeclareRealtyHouseCert",
    box: "boxDeclareRealtyHouseCert",
    fileId: "declareRealtyHouseCertFileId",
    fileIdNew: "declareRealtyHouseCertFileIdNew",
    fileViewNew: "declareRealtyHouseCertFileViewNew",
    landFileId: "declareRealtyHouseCert_land_FileId",
    son: {
        declareRealtyLandCert: {
            frm: "frmSonDeclareRealtyLandCert",
            box: "boxSonDeclareRealtyLandCert",
            view: "viewSonDeclareRealtyLandCertCert",
            fileId: "sonDeclareRealtyLandCertFileId",
            name: "土地",
            table: "tableSonDeclareRealtyLandCert"
        }
    }
};

var declareRealtyHouseCert = new Object();

declareRealtyHouseCert.isEmpty = function (item) {
    if (item) {
        return true;
    }
    return false;
};

declareRealtyHouseCert.objectWriteSelectData = function (frm, data, name) {
    if (declareRealtyHouseCert.isEmpty(data)) {
        $("#" + frm + " ." + name).val(data).trigger("change");
    } else {
        $("#" + frm + " ." + name).val(null).trigger("change");
    }
};

//处理标识符的地方-------start
declareRealtyHouseCert.declareRealtyHouseCertFlag = true;//父标识符
declareRealtyHouseCert.sonDeclareRealtyLandCertFlag = true;//子标识符 (土地)
declareRealtyHouseCert.sonDeclareRealtyRealEstateCert = true;//子标识符 (不动产)
declareRealtyHouseCert.startPath = null;
declareRealtyHouseCert.sysOcrRecordId = null;
//----------------------end

/**
 * @author:  zch
 * 描述:房产证 文件上传
 * @date:2018-09-19
 **/
declareRealtyHouseCert.fileUpload = function (target, tableName, id) {
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
            declareRealtyHouseCert.showFile(target, tableName, id);
            //房产证识别
            if (target == declareRealtyHouseCertConfig.fileId) {
                if (declareRealtyHouseCert.isEmpty(result)) {
                    AssessCommon.parseRealtyHouseCert(result, AssessDBKey.HouseOcrkey, function (data) {
                        declareRealtyHouseCert.sysOcrRecordId = data.sysOcrRecordId;
                    });
                }
            }
            declareRealtyHouseCert.loadList();
        },
        deleteFlag: true
    });
};
declareRealtyHouseCert.fileUploadNew = function (target, tableName, id) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id
        },
        deleteFlag: true
    });
};

/**
 * @author:  zch
 * 描述:房产证 文件显示
 * @date:2018-09-19
 **/
declareRealtyHouseCert.showFile = function (target, tableName, id) {
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
 * 描述:房产证 更新或者新增
 * @date:2018-09-19
 **/
declareRealtyHouseCert.saveAndUpdate = function () {
    if (!$("#" + declareRealtyHouseCertConfig.frm).valid()) {
        return false;
    }
    var data = formParams(declareRealtyHouseCertConfig.frm);
    if (!declareRealtyHouseCert.isEmpty(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.pid = "0";
        data.declareType = declareFunObj.getDeclareType("房产证");
    }
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                declareRealtyHouseCert.loadList();
                toastr.success('成功!');
                $('#' + declareRealtyHouseCertConfig.box).modal("hide");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
}

/**
 * @author:  zch
 * 描述:房产证 删除
 * @date:2018-09-19
 **/
declareRealtyHouseCert.deleteData = function () {
    var rows = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要删除的数据");
    } else {
        Alert("确认要删除么？", 2, null, function () {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })
            $.ajax({
                url: getContextPath() + "/declareRealtyHouseCert/deleteDeclareRealtyHouseCertById",
                type: "post",
                dataType: "json",
                data: {ids: idArray.join()},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        declareRealtyHouseCert.loadList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }
};

/**
 * @author:  zch
 * 描述:房产证 编辑
 * @date:2018-09-19
 **/
declareRealtyHouseCert.editData = function () {
    var rows = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        $.ajax({
            url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            data: {id: rows[0].id},
            success: function (result) {
                if (result.ret) {
                    $("#" + declareRealtyHouseCertConfig.frm).clearAll();
                    if (declareRealtyHouseCert.declareRealtyHouseCertFlag) {
                        declareRealtyHouseCert.init();
                        declareRealtyHouseCert.declareRealtyHouseCertFlag = false;
                    }
                    var data = result.data;
                    if (declareRealtyHouseCert.isEmpty(data)) {
                        $("#" + declareRealtyHouseCertConfig.frm).initForm(result.data);
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='landRegistrationDate']").val(formatDate(data.landRegistrationDate));
                        declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.frm, data.type, "type");
                        declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                        declareRealtyHouseCert.showFile(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + declareRealtyHouseCertConfig.frm + "province"),
                            cityTarget: $("#" + declareRealtyHouseCertConfig.frm + "city"),
                            districtTarget: $("#" + declareRealtyHouseCertConfig.frm + "district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        });
                    }
                    //使校验生效
                    $("#" + declareRealtyHouseCertConfig.frm).validate();
                    $('#' + declareRealtyHouseCertConfig.box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * @author:  zch
 * 描述:房产证 显示
 * @date:2018-09-19
 **/
declareRealtyHouseCert.showAddModel = function () {
    $("#" + declareRealtyHouseCertConfig.frm).clearAll();
    if (declareRealtyHouseCert.declareRealtyHouseCertFlag) {
        declareRealtyHouseCert.init();
        declareRealtyHouseCert.declareRealtyHouseCertFlag = false;
    }
    declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, 0);
    declareRealtyHouseCert.showFile(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, 0);
    //使校验生效
    $("#" + declareRealtyHouseCertConfig.frm).validate();
    $('#' + declareRealtyHouseCertConfig.box).modal("show");
};

/**
 * @author:  zch
 * 描述:房产证 初始化
 * @date:2018-09-19
 **/
declareRealtyHouseCert.init = function () {
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + declareRealtyHouseCertConfig.frm + "province"),
        cityTarget: $("#" + declareRealtyHouseCertConfig.frm + "city"),
        districtTarget: $("#" + declareRealtyHouseCertConfig.frm + "district"),
        provinceValue: '',
        cityValue: '',
        districtValue: ''
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, '', function (html, data) {
        $("#" + declareRealtyHouseCertConfig.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, '', function (html, data) {
        $("#" + declareRealtyHouseCertConfig.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    declareRealtyHouseCert.role.writeCertName.init();
    declareRealtyHouseCert.role.beLocated.init();
};

/**
 * @author:  zch
 * 描述:房产证 列表加载
 * @date:2018-09-19
 **/
declareRealtyHouseCert.loadList = function () {
    var cols = [];
    cols.push({
        field: 'provinceName', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'floorArea', title: '建筑面积'});
    cols.push({field: 'certName', title: '房屋权证号'});
    cols.push({field: 'beLocated', title: '房屋坐落'});
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success" href="javascript:declareRealtyHouseCert.sonDeclareRealtyLandCert.showViewModel(' + row.id + ');" ><i class="fa fa-check">土地证</i></a>';
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='declareRealtyHouseCert.houseEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='declareRealtyHouseCert.landEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('destroy');
    TableInit(declareRealtyHouseCertConfig.table, getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId()
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    },true);
};


/**
 * @author:  zch
 * 描述:房产证 识别
 * @date:2018-09-19
 **/
declareRealtyHouseCert.distinguish = function () {
    var sysOcrRecordId = declareRealtyHouseCert.sysOcrRecordId;
    if (!declareRealtyHouseCert.isEmpty(sysOcrRecordId)) {
        toastr.success('稍后再试!');
        return false;
    }
    $.ajax({
        url: getContextPath() + "/declareRealtyHouseCert/parseRealtyHouseCert",
        type: "POST",
        dataType: "json",
        data: {sysOcrRecordId: sysOcrRecordId},
        success: function (result) {
            if (result.ret) {
                var data = result.data;
                $("#" + declareRealtyHouseCertConfig.frm).initForm(data);
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                declareRealtyHouseCert.sysOcrRecordId = null;
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    })
};

declareRealtyHouseCert.inputFileLand = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/importDataLand",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: 'ajaxFileUploadHouseLand',//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyHouseCert.loadList();
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

declareRealtyHouseCert.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: 'ajaxFileUploadHouse',//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyHouseCert.loadList();
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
 * 描述:房产证附件 页面
 * @date:2018-10-23
 **/
declareRealtyHouseCert.houseEnclosure = function (id) {
    $("#" + declareRealtyHouseCertConfig.fileIdNew).attr("data-id", id);
    $("#" + declareRealtyHouseCertConfig.fileIdNew).trigger('click');
};
/**
 * @author:  zch
 * 描述:附件处理服务端
 * @date:2018-10-23
 **/
declareRealtyHouseCert.enclosureServer = function () {
    var id = $("#" + declareRealtyHouseCertConfig.fileIdNew).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyHouseCert,
            tableId: id,
            fieldsName: declareRealtyHouseCertConfig.fileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: declareRealtyHouseCertConfig.fileIdNew,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyHouseCert.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};


declareRealtyHouseCert.enclosureServer2 = function () {
    var id = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyLandCert,
            tableId: id,
            fieldsName: declareRealtyHouseCertConfig.landFileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                declareRealtyHouseCert.loadList();
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
 * 描述:土地证附件 页面
 * @date:2018-10-23
 **/
declareRealtyHouseCert.landEnclosure = function (id) {
    var item = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getRowByUniqueId', id);
    if (declareRealtyHouseCert.isEmpty(item.pid)) {
        $.ajax({
            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            async: false,
            data: {id: item.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyHouseCert.isEmpty(data)) {
                        $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId).attr("data-id", data.id);
                        $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId).trigger('click');
                    } else {
                        toastr.success('关联的土地证数据已经被删除!');
                        toastr.success('请重新填写!');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    } else {
        toastr.success('没有关联土地证数据!');
    }

};

/**
 * @author:  zch
 * 描述:房产证 格式化某些input
 * @date:2018-09-19
 **/
declareRealtyHouseCert.role = {
    //房产权证号
    writeCertName: {
        init: function () {
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='location']").blur(function () {
                declareRealtyHouseCert.role.writeCertName.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='number']").blur(function () {
                declareRealtyHouseCert.role.writeCertName.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " .type").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + declareRealtyHouseCertConfig.frm).find('select.type').val();
                if (declareRealtyHouseCert.isEmpty(id)) {
                    declareRealtyHouseCert.role.writeCertName.write();
                }
            });
        },
        //拼接 房产权证号
        write: function () {
            var location = $("#" + declareRealtyHouseCertConfig.frm + " input[name='location']").val();
            var number = $("#" + declareRealtyHouseCertConfig.frm + " input[name='number']").val();
            var id = $("#" + declareRealtyHouseCertConfig.frm).find('select.type').val();
            if (!declareRealtyHouseCert.isEmpty(location)) {
                location = "";
            }
            if (!declareRealtyHouseCert.isEmpty(number)) {
                number = "";
            }
            if (!declareRealtyHouseCert.isEmpty(id)) {
                id = "";
            }
            if (declareRealtyHouseCert.isEmpty(id)) {
                AssessCommon.getDataDicInfo(id, function (data) {
                    if (declareRealtyHouseCert.isEmpty(data)) {
                        var temp = location + "房权证" + data.name + "字地" + number + "号";
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='certName']").val(temp);
                    }
                    console.log(data);
                });
            } else {
                var temp = location + "房权证" + id + "字地" + number + "号";
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='certName']").val(temp);
            }
        }
    },
    //房屋坐落
    beLocated: {
        init: function () {
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='unit']").blur(function () {
                declareRealtyHouseCert.role.beLocated.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='floor']").blur(function () {
                declareRealtyHouseCert.role.beLocated.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='roomNumber']").blur(function () {
                declareRealtyHouseCert.role.beLocated.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='streetNumber']").blur(function () {
                declareRealtyHouseCert.role.beLocated.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='attachedNumber']").blur(function () {
                declareRealtyHouseCert.role.beLocated.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " input[name='buildingNumber']").blur(function () {
                declareRealtyHouseCert.role.beLocated.write();
            });
            $("#" + declareRealtyHouseCertConfig.frm + " .district").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + declareRealtyHouseCertConfig.frm + " .district").eq(1).val();
                if (declareRealtyHouseCert.isEmpty(id)) {
                    declareRealtyHouseCert.role.beLocated.write();
                }
            });
        },
        //房屋坐落 拼接
        write: function () {
            var temp = null;
            var district = $("#" + declareRealtyHouseCertConfig.frm + " .district").eq(1).val();
            var unit = $("#" + declareRealtyHouseCertConfig.frm + " input[name='unit']").val();
            var floor = $("#" + declareRealtyHouseCertConfig.frm + " input[name='floor']").val();
            var roomNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='roomNumber']").val();
            var streetNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='streetNumber']").val();
            var attachedNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='attachedNumber']").val();
            var buildingNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='buildingNumber']").val();
            if (!declareRealtyHouseCert.isEmpty(unit)) {
                unit = "";
            } else {
                unit = unit + "单元";
            }
            if (!declareRealtyHouseCert.isEmpty(floor)) {
                floor = "";
            } else {
                floor = floor + "楼";
            }
            if (!declareRealtyHouseCert.isEmpty(roomNumber)) {
                roomNumber = "";
            } else {
                roomNumber = roomNumber + "号";
            }
            if (!declareRealtyHouseCert.isEmpty(streetNumber)) {
                streetNumber = "";
            }
            if (!declareRealtyHouseCert.isEmpty(attachedNumber)) {
                attachedNumber = "";
            } else {
                attachedNumber = "附" + attachedNumber;
            }
            if (!declareRealtyHouseCert.isEmpty(buildingNumber)) {
                buildingNumber = "";
            } else {
                buildingNumber = buildingNumber + "栋";
            }
            if (declareRealtyHouseCert.isEmpty(district)) {
                AssessCommon.getAreaById(district, function (data) {
                    if (!declareRealtyHouseCert.isEmpty(data)) {
                        district = "";
                    } else {
                        district = data.name;
                    }
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + declareRealtyHouseCertConfig.frm + " input[name='beLocated']").val(temp);
                });
            } else {
                district = "";
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='beLocated']").val(temp);
            }
        }
    }
};


/**
 * @author:  zch
 * 描述:房产证 挂的土地证证(属子类)
 * @date:2018-09-19
 **/
declareRealtyHouseCert.sonDeclareRealtyLandCert = {
    showViewModel: function (id) {
        if (declareRealtyHouseCert.sonDeclareRealtyLandCertFlag) {
            declareRealtyHouseCert.sonDeclareRealtyLandCert.init();
            declareRealtyHouseCert.sonDeclareRealtyLandCertFlag = false;
        }
        $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).clearAll();
        $('#' + declareRealtyHouseCertConfig.son.declareRealtyLandCert.box).modal("show");
        var item = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (declareRealtyHouseCert.isEmpty(item.pid)) {
            //从服务端获取关联数据
            $.ajax({
                url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
                type: "get",
                dataType: "json",
                data: {id: item.pid, planDetailsId: declareCommon.getPlanDetailsId()},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (declareRealtyHouseCert.isEmpty(data)) {
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "province"),
                                cityTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "city"),
                                districtTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "district"),
                                provinceValue: data.province,
                                cityValue: data.city,
                                districtValue: data.district
                            });
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).initForm(data);
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                            declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm, data.type, "type");
                            declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm, data.purpose, "purpose");
                            declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm, data.useRightType, "useRightType");
                            declareRealtyHouseCert.showFile(declareRealtyHouseCertConfig.landFileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                            declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.landFileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                        } else {
                            toastr.success('关联的土地证数据已经被删除!');
                            toastr.success('请重新填写!');
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        } else {
            //把需要关联的数据带过来一些
            $.ajax({
                url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (resultA) {
                    if (resultA.ret) {
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "province"),
                            cityTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "city"),
                            districtTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "district"),
                            provinceValue: resultA.data.province,
                            cityValue: resultA.data.city,
                            districtValue: resultA.data.district
                        });
                        $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).initForm(
                            {
                                pid: id,
                                beLocated: resultA.data.beLocated,
                                streetNumber: resultA.data.streetNumber,
                                attachedNumber: resultA.data.attachedNumber,
                                buildingNumber: resultA.data.buildingNumber,
                                unit: resultA.data.unit,
                                roomNumber: resultA.data.roomNumber,
                                floor: resultA.data.floor
                            }
                        );
                        declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.landFileId, AssessDBKey.DeclareRealtyLandCert, 0);
                    }
                },
                error: function (resultA) {
                    Alert("调用服务端方法失败，失败原因:" + resultA);
                }
            })
        }
    },
    saveAndUpdateData: function () {
        if (!$("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).valid()) {
            return false;
        }
        var data = formParams(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm);
        $.ajax({
            type: "POST",
            url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功!');
                    $('#' + declareRealtyHouseCertConfig.son.declareRealtyLandCert.box).modal("hide");
                    declareRealtyHouseCert.loadList();
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    },
    init: function () {
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, '', function (html, data) {
            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, '', function (html, data) {
            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.init();
    },
    role: {
        //土地权证号
        landCertName: {
            write: function () {
                var id = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).find('select.type').val();
                var year = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='year']").val();
                var number = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='number']").val();
                var location = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='location']").val();
                if (declareRealtyHouseCert.isEmpty(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (declareRealtyHouseCert.isEmpty(data)) {
                            var temp = location + data.name + year + "第" + number + "号";
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                        }
                    });
                } else {
                    var temp = location + year + "第" + number + "号";
                    $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                }
            },
            init: function () {
                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='year']").blur(function () {
                    declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                });
                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='number']").blur(function () {
                    declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                });
                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='location']").blur(function () {
                    declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                });
                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " .type").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " .type").eq(1).val();
                    if (declareRealtyHouseCert.isEmpty(id)) {
                        declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                    }
                });
            }
        }
    }
};

$(function () {
    declareRealtyHouseCert.loadList();
});

