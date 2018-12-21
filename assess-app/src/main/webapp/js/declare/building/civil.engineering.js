/**
 * Created by kings on 2018-12-20.
 */
/**
 * @author:  zch
 * 描述:配置一些必须的参数
 * @date:2018-09-27
 **/
var civilEngineeringConfig = {
    name: "土建",
    frm: "civilEngineeringFrm",
    table: "civilEngineeringTableList",
    box: "civilEngineeringBox",
    fileId: "civilEngineeringFileId",
    excelUpload: "civilEngineeringUpload",
    declareBuildingConstructionPermit: {
        box: "declareBuildingConstructionPermitBox",
        frm: "declareBuildingConstructionPermitFrm",
        fileId: "declareBuildingConstructionPermitFileId",
        name: "建筑工程施工许可证"
    },
    declarePreSalePermit: {
        box: "declarePreSalePermitBox",
        frm: "declarePreSalePermitFrm",
        fileId: "declarePreSalePermitFileId",
        name: "商品房预售许可证"
    },
    declareLandUsePermit: {
        box: "declareLandUsePermitBox",
        frm: "declareLandUsePermitFrm",
        fileId: "declareLandUsePermitFileId",
        name: "建设用地规划许可证"
    },
    declareBuildingPermit: {
        box: "declareBuildingPermitBox",
        frm: "declareBuildingPermitFrm",
        fileId: "declareBuildingPermitFileId",
        name: "建设工程规划许可证"
    },
    declareRealtyLandCert: {
        box: "declareRealtyLandCertBox",
        frm: "declareRealtyLandCertFrm",
        fileId: "declareRealtyLandCertFileId",
        name: "土地证"
    },
    declareRealtyRealEstateCert: {
        box: "declareRealtyRealEstateCertBox",
        frm: "declareRealtyRealEstateCertFrm",
        fileId: "declareRealtyRealEstateCertFileId",
        name: "不动产"
    },
    declareEconomicIndicators: {
        box: "declareEconomicIndicatorsBox",
        frm: "declareEconomicIndicatorsFrm",
        fileId: "declareEconomicIndicatorsFileId",
        name: "经济指标"
    }
};

var civilEngineering = new Object();

//处理标识符的地方-------start
civilEngineering.civilEngineeringFlag = true;
civilEngineering.declareRealtyLandCertFlag = true;
civilEngineering.declareRealtyRealEstateCertFlag = true;
//----------------------end

civilEngineering.isEmpty = function (item) {
    if (item) {
        return true;
    }
    return false;
};

civilEngineering.objectWriteSelectData = function (frm, data, name) {
    if (civilEngineering.isEmpty(data)) {
        $("#" + frm + " ." + name).val(data).trigger("change");
    } else {
        $("#" + frm + " ." + name).val(null).trigger("change");
    }
};

civilEngineering.showFile = function (target, tableName, id) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            fieldsName: target,
            tableName: tableName,
            tableId: id
        },
        deleteFlag: true
    });
};

civilEngineering.fileUpload = function (target, tableName, id) {
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
            civilEngineering.showFile(target, tableName, id);
        },
        deleteFlag: true
    });
};

civilEngineering.init = function () {
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + civilEngineeringConfig.frm + "province"),
        cityTarget: $("#" + civilEngineeringConfig.frm + "city"),
        districtTarget: $("#" + civilEngineeringConfig.frm + "district"),
        provinceValue: '',
        cityValue: '',
        districtValue: ''
    });
};

/**
 * @author:  zch
 * 描述:在建工程（土建）显示
 * @date:2018-09-27
 **/
civilEngineering.showAddModel = function () {
    $("#" + civilEngineeringConfig.frm).clearAll();
    if (civilEngineering.civilEngineeringFlag) {
        civilEngineering.init();
        civilEngineering.civilEngineeringFlag = false;
    }
    civilEngineering.showFile(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, 0);
    civilEngineering.fileUpload(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, 0);
    //使校验生效
    $("#" + civilEngineeringConfig.frm).validate();
    $('#' + civilEngineeringConfig.box).modal("show");
};

/**
 * @author:  zch
 * 描述:在建工程（土建）删除
 * @date:2018-09-27
 **/
civilEngineering.deleteData = function () {

    var rows = $("#" + civilEngineeringConfig.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要删除的数据");
    } else {
        Alert("确认要删除么？", 2, null, function () {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })
            $.ajax({
                type: "post",
                url: getContextPath() + "/declareBuildEngineering/deleteDeclareBuildEngineeringById",
                data: {ids: idArray.join()},
                success: function (result) {
                    if (result.ret) {
                        civilEngineering.loadList();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        })
    }
};

/**
 * @author:  zch
 * 描述:在建工程（土建）编辑
 * @date:2018-09-27
 **/
civilEngineering.editData = function () {
    var rows = $("#" + civilEngineeringConfig.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        $("#" + civilEngineeringConfig.frm).clearAll();
        if (civilEngineering.civilEngineeringFlag) {
            civilEngineering.init();
            civilEngineering.civilEngineeringFlag = false;
        }
        $.ajax({
            url: getContextPath() + "/declareBuildEngineering/getDeclareBuildEngineeringById",
            type: "get",
            dataType: "json",
            data: {id: rows[0].id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (civilEngineering.isEmpty(data)) {
                        $("#" + civilEngineeringConfig.frm).initForm(result.data);
                        civilEngineering.showFile(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, data.id);
                        civilEngineering.fileUpload(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, data.id);
                        $("#" + civilEngineeringConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + civilEngineeringConfig.frm + "province"),
                            cityTarget: $("#" + civilEngineeringConfig.frm + "city"),
                            districtTarget: $("#" + civilEngineeringConfig.frm + "district"),
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
        });
        //使校验生效
        $("#" + civilEngineeringConfig.frm).validate();
        $('#' + civilEngineeringConfig.box).modal("show");
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * @author:  zch
 * 描述:在建工程（土建）更新
 * @date:2018-09-27
 **/
civilEngineering.saveAndUpdateData = function () {
    if (!$("#" + civilEngineeringConfig.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.declareType = declareFunObj.getDeclareType("土建");
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineering/saveAndUpdateDeclareBuildEngineering",
        data: data,
        success: function (result) {
            if (result.ret) {
                civilEngineering.loadList();
                toastr.success('成功!');
                $('#' + civilEngineeringConfig.box).modal("hide");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:更新绑定到父类上的子类
 * @date:2018-09-28
 **/
civilEngineering.handleFather = function (item) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter",
        data: item,
        success: function (result) {
            if (result.ret) {
                civilEngineering.loadList();
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:不动产
 * @date:2018-09-28
 **/
civilEngineering.declareRealtyRealEstateCertRoleBeLocated = {
    init: function () {
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .district").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
            if (civilEngineering.isEmpty(id)) {
                civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
            }
        });
    },
    write: function () {
        var temp = "";
        var district = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
        var unit = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val();
        var floor = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val();
        var roomNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val();
        var streetNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val();
        var attachedNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val();
        var buildingNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val();
        if (!civilEngineering.isEmpty(unit)) {
            unit = "";
        } else {
            unit = unit + "单元";
        }
        if (!civilEngineering.isEmpty(floor)) {
            floor = "";
        } else {
            floor = floor + "楼";
        }
        if (!civilEngineering.isEmpty(roomNumber)) {
            roomNumber = "";
        } else {
            roomNumber = roomNumber + "号";
        }
        if (!civilEngineering.isEmpty(streetNumber)) {
            streetNumber = "";
        }
        if (!civilEngineering.isEmpty(attachedNumber)) {
            attachedNumber = "";
        } else {
            attachedNumber = "附" + attachedNumber;
        }
        if (!civilEngineering.isEmpty(buildingNumber)) {
            buildingNumber = "";
        } else {
            buildingNumber = buildingNumber + "栋";
        }
        if (civilEngineering.isEmpty(district)) {
            AssessCommon.getAreaById(district, function (data) {
                if (!civilEngineering.isEmpty(data)) {
                    district = "";
                } else {
                    district = data.name;
                }
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
            });
        } else {
            district = "";
            temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
        }
    }
};
civilEngineering.declareRealtyRealEstateCertRoleCertName = {
    init: function () {
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='location']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleCertName.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='number']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val())) {
                civilEngineering.declareRealtyRealEstateCertRoleCertName.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .type").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
            if (civilEngineering.isEmpty(id)) {
                civilEngineering.declareRealtyRealEstateCertRoleCertName.write();
            }
        });
    },
    write: function () {
        var location = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val();
        var number = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val();
        var id = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
        if (!civilEngineering.isEmpty(location)) {
            location = "";
        }
        if (!civilEngineering.isEmpty(number)) {
            number = "";
        }
        if (!civilEngineering.isEmpty(id)) {
            id = "";
        }
        if (civilEngineering.isEmpty(id)) {
            AssessCommon.getDataDicInfo(id, function (data) {
                if (civilEngineering.isEmpty(data)) {
                    var temp = location + "房权证" + data.name + "字地" + number + "号";
                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
                }
            });
        } else {
            var temp = location + "房权证" + id + "字地" + number + "号";
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
        }
    }
};
civilEngineering.declareRealtyRealEstateCertInit = function () {
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "province"),
        cityTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "city"),
        districtTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "district"),
        provinceValue: '',
        cityValue: '',
        districtValue: ''
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, '', function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, '', function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, "", function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });

    civilEngineering.declareRealtyRealEstateCertRoleCertName.init();
    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.init();
};
civilEngineering.declareRealtyRealEstateCertView = function (id) {
    if (civilEngineering.declareRealtyRealEstateCertFlag) {
        civilEngineering.declareRealtyRealEstateCertInit();
        civilEngineering.declareRealtyRealEstateCertFlag = false;
    }
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var data = result.data;
                var realEstateId = data.realEstateId;
                $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).clearAll();
                if (civilEngineering.isEmpty(realEstateId)) {
                    $.ajax({
                        url: getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                        type: "get",
                        dataType: "json",
                        data: {id: realEstateId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (civilEngineering.isEmpty(data)) {
                                    data.pidC = id;
                                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).initForm(data);
                                    civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.purpose, "purpose");
                                    civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.type, "type");
                                    civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.useRightType, "useRightType");
                                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                    civilEngineering.showFile(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                                    civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                                    AssessCommon.initAreaInfo({
                                        provinceTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "province"),
                                        cityTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "city"),
                                        districtTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "district"),
                                        provinceValue: data.province,
                                        cityValue: data.city,
                                        districtValue: data.district
                                    });
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).initForm({pidC: id});
                    civilEngineering.showFile(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
                    civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
                }
                $('#' + civilEngineeringConfig.declareRealtyRealEstateCert.box).modal("show");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};
civilEngineering.declareRealtyRealEstateCertSaveAndUpdate = function () {
    if (!$("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.declareRealtyRealEstateCert.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineeringConfig.declareRealtyRealEstateCert.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isEmpty(item)) {
                    //把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)  久
                    //把新增的子类关联道中间表
                    var pidC = data.pidC;
                    var fData = {};
                    fData.realEstateId = item;
                    fData.id = pidC;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:土地证
 * @date:2018-09-28
 **/
civilEngineering.declareRealtyLandCertCertRoleBeLocated = {
    init: function () {
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='unit']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='unit']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='floor']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='floor']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .district").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
            if (civilEngineering.isEmpty(id)) {
                civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
            }
        });
    },
    write: function () {
        var temp = null;
        var district = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
        var unit = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='unit']").val();
        var floor = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='floor']").val();
        var roomNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val();
        var streetNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val();
        var attachedNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val();
        var buildingNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val();
        if (!civilEngineering.isEmpty(unit)) {
            unit = "";
        } else {
            unit = unit + "单元";
        }
        if (!civilEngineering.isEmpty(floor)) {
            floor = "";
        } else {
            floor = floor + "楼";
        }
        if (!civilEngineering.isEmpty(roomNumber)) {
            roomNumber = "";
        } else {
            roomNumber = roomNumber + "号";
        }
        if (!civilEngineering.isEmpty(streetNumber)) {
            streetNumber = "";
        }
        if (!civilEngineering.isEmpty(attachedNumber)) {
            attachedNumber = "";
        } else {
            attachedNumber = "附" + attachedNumber;
        }
        if (!civilEngineering.isEmpty(buildingNumber)) {
            buildingNumber = "";
        } else {
            buildingNumber = buildingNumber + "栋";
        }
        if (civilEngineering.isEmpty(district)) {
            AssessCommon.getAreaById(district, function (data) {
                if (!civilEngineering.isEmpty(data)) {
                    district = "";
                } else {
                    district = data.name;
                }
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
            });
        } else {
            district = "";
            temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
        }
    }
};
civilEngineering.declareRealtyLandCertCertRoleCertName = {
    init: function () {
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='location']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='location']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleCertName.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='year']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='year']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleCertName.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='number']").blur(function () {
            if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='number']").val())) {
                civilEngineering.declareRealtyLandCertCertRoleCertName.write();
            }
        });
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .type").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
            if (civilEngineering.isEmpty(id)) {
                civilEngineering.declareRealtyLandCertCertRoleCertName.write();
            }
        });
    },
    write: function () {
        var temp = "";
        var id = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
        var location = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='location']").val();
        var year = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='year']").val();
        var number = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='number']").val();
        if (civilEngineering.isEmpty(id)) {
            AssessCommon.getDataDicInfo(id, function (data) {
                if (civilEngineering.isEmpty(data)) {
                    var temp = location + data.name + year + "第" + number + "号";
                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                }
            });
        } else {
            var temp = location + year + "第" + number + "号";
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
        }
    }
};
civilEngineering.declareRealtyLandCertInit = function () {
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "province"),
        cityTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "city"),
        districtTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "district"),
        provinceValue: '',
        cityValue: '',
        districtValue: ''
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, '', function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, "", function (html, data) {
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    civilEngineering.declareRealtyLandCertCertRoleCertName.init();
    civilEngineering.declareRealtyLandCertCertRoleBeLocated.init();
};
civilEngineering.declareRealtyLandCertView = function (id) {
    if (civilEngineering.declareRealtyLandCertFlag) {
        civilEngineering.declareRealtyLandCertInit();
        civilEngineering.declareRealtyLandCertFlag = false;
    }
    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var data = result.data;
                var landId = data.landId;
                if (civilEngineering.isEmpty(landId)) {
                    $.ajax({
                        url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
                        type: "get",
                        dataType: "json",
                        data: {id: landId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (civilEngineering.isEmpty(data)) {
                                    data.pidC = id;
                                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).initForm(data);
                                    civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.purpose, "purpose");
                                    civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.type, "type");
                                    civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.useRightType, "useRightType");
                                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                    civilEngineering.showFile(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                    civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                    AssessCommon.initAreaInfo({
                                        provinceTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "province"),
                                        cityTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "city"),
                                        districtTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "district"),
                                        provinceValue: data.province,
                                        cityValue: data.city,
                                        districtValue: data.district
                                    });
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).initForm({pidC: id});
                    civilEngineering.showFile(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
                    civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
                }
                $('#' + civilEngineeringConfig.declareRealtyLandCert.box).modal("show");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};
civilEngineering.declareRealtyLandCertSaveAndUpdate = function () {
    if (!$("#" + civilEngineeringConfig.declareRealtyLandCert.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.declareRealtyLandCert.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineeringConfig.declareRealtyLandCert.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.landId = item;
                    fData.id = data.pidC
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:建设工程规划许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingPermitView = function (id) {
    $("#" + civilEngineeringConfig.declareBuildingPermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var buildingPermitId = result.data.buildingPermitId;
                if (civilEngineering.isEmpty(buildingPermitId)) {
                    $.ajax({
                        url: getContextPath() + "/declareBuildingPermit/getDeclareBuildingPermitById",
                        type: "get",
                        dataType: "json",
                        data: {id: buildingPermitId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (civilEngineering.isEmpty(data)) {
                                    data.pid = id;
                                    $("#" + civilEngineeringConfig.declareBuildingPermit.frm).initForm(data);
                                    $("#" + civilEngineeringConfig.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                                    civilEngineering.showFile(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                    civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineeringConfig.declareBuildingPermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                    civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
                }
                $('#' + civilEngineeringConfig.declareBuildingPermit.box).modal("show");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:建设工程规划许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingPermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineeringConfig.declareBuildingPermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.declareBuildingPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildingPermit/saveAndUpdateDeclareBuildingPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineeringConfig.declareBuildingPermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.buildingPermitId = item;
                    fData.id = data.pid
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:建设用地规划许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declareLandUsePermitView = function (id) {
    $("#" + civilEngineeringConfig.declareLandUsePermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var landUsePermitId = result.data.landUsePermitId;
                if (civilEngineering.isEmpty(landUsePermitId)) {
                    $.ajax({
                        url: getContextPath() + "/declareLandUsePermit/getDeclareLandUsePermitById",
                        type: "get",
                        dataType: "json",
                        data: {id: landUsePermitId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (civilEngineering.isEmpty(data)) {
                                    data.pid = id;
                                    $("#" + civilEngineeringConfig.declareLandUsePermit.frm).initForm(data);
                                    $("#" + civilEngineeringConfig.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                                    civilEngineering.showFile(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                    civilEngineering.fileUpload(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineeringConfig.declareLandUsePermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                    civilEngineering.fileUpload(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
                }
                $('#' + civilEngineeringConfig.declareLandUsePermit.box).modal("show");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });

};

/**
 * @author:  zch
 * 描述:建设用地规划许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declareLandUsePermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineeringConfig.declareLandUsePermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.declareLandUsePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareLandUsePermit/saveAndUpdateDeclareLandUsePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineeringConfig.declareLandUsePermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var pid = data.pid;
                    var fData = {};
                    fData.landUsePermitId = item;
                    fData.id = data.pid;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:商品房预售许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declarePreSalePermitView = function (id) {
    $("#" + civilEngineeringConfig.declarePreSalePermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var preSalePermitId = result.data.preSalePermitId;
                if (civilEngineering.isEmpty(preSalePermitId)) {
                    $.ajax({
                        url: getContextPath() + "/declarePreSalePermit/getDeclarePreSalePermitById",
                        type: "get",
                        dataType: "json",
                        data: {id: preSalePermitId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (civilEngineering.isEmpty(data)) {
                                    data.pid = id;
                                    $("#" + civilEngineeringConfig.declarePreSalePermit.frm).initForm(data);
                                    $("#" + civilEngineeringConfig.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                                    civilEngineering.showFile(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                    civilEngineering.fileUpload(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineeringConfig.declarePreSalePermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                    civilEngineering.fileUpload(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
                }
                $('#' + civilEngineeringConfig.declarePreSalePermit.box).modal("show");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });

};

/**
 * @author:  zch
 * 描述:商品房预售许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declarePreSalePermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineeringConfig.declarePreSalePermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.declarePreSalePermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declarePreSalePermit/saveAndUpdateDeclarePreSalePermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineeringConfig.declarePreSalePermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var fData = {};
                    fData.preSalePermitId = item;
                    fData.id = data.pid;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * @author:  zch
 * 描述:建筑工程施工许可证 view
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingConstructionPermitView = function (id) {
    $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).clearAll();
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                var buildingConstructionPermitId = result.data.buildingConstructionPermitId;
                if (civilEngineering.isEmpty(buildingConstructionPermitId)) {
                    $.ajax({
                        url: getContextPath() + "/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                        type: "get",
                        dataType: "json",
                        data: {id: buildingConstructionPermitId},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (civilEngineering.isEmpty(data)) {
                                    data.pid = id;
                                    $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).initForm(data);
                                    $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                                    $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                                    civilEngineering.showFile(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                    civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                } else {
                    $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).initForm({pid: id});
                    civilEngineering.showFile(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                    civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
                }
                $('#' + civilEngineeringConfig.declareBuildingConstructionPermit.box).modal("show");
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });

};


/**
 * @author:  zch
 * 描述:建筑工程施工许可证 更新
 * @date:2018-09-28
 **/
civilEngineering.declareBuildingConstructionPermitSaveAndUpdate = function () {
    if (!$("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).valid()) {
        return false;
    }
    var data = formParams(civilEngineeringConfig.declareBuildingConstructionPermit.frm);
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareBuildingConstructionPermit/saveAndUpdateDeclareBuildingConstructionPermit",
        data: data,
        success: function (result) {
            if (result.ret) {
                $('#' + civilEngineeringConfig.declareBuildingConstructionPermit.box).modal("hide");
                var item = result.data;
                if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                    var pid = data.pid;
                    var fData = {};
                    fData.buildingConstructionPermitId = item;
                    fData.id = data.pid;
                    civilEngineering.handleFather(fData);
                    toastr.success('成功!');
                }
            } else {
                console.log(result.errmsg);
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};

/**
 * 经济指标
 */
civilEngineering.declareEconomicIndicatorsView = function (pid) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/getDeclareBuildEngineeringAndEquipmentCenterById",
        data: {id: pid},
        success: function (result) {
            if (result.ret) {
                var indicatorId = result.data.indicatorId;
                if (civilEngineering.isEmpty(indicatorId)) {
                    $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).clearAll();
                    $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('[name=pid]').val(indicatorId);
                    $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('[name=planDetailsId]').val('${projectPlanDetails.id}');
                    $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('.dynamic').remove();
                    economicIndicators.initForm(indicatorId, function () {
                        $('#' + civilEngineeringConfig.declareEconomicIndicators.box).modal("show");
                    });
                } else {
                    $.ajax({
                        type: "POST",
                        url: getContextPath() + "/declareBuildEconomicIndicatorsCenter/saveAndUpdateDeclareBuildEconomicIndicatorsCenter",
                        data: {planDetailsId: declareCommon.getPlanDetailsId()},
                        success: function (result) {
                            if (result.ret) {
                                var id = result.data;
                                var fData = {};
                                fData.indicatorId = id;
                                fData.id = pid;
                                civilEngineering.handleFather(fData);
                                $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).clearAll();
                                $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('[name=pid]').val(id);
                                $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('[name=planDetailsId]').val('${projectPlanDetails.id}');
                                $("#" + civilEngineeringConfig.declareEconomicIndicators.frm).find('.dynamic').remove();
                                economicIndicators.initForm(id, function () {
                                    $('#' + civilEngineeringConfig.declareEconomicIndicators.box).modal("show");
                                });
                            } else {
                                console.log(result.errmsg);
                                Alert("保存失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            }
        },
        error: function (e) {
            Alert("调用服务端方法失败，失败原因:" + e);
        }
    });
};


civilEngineering.copyId = undefined;
//复制子数据
civilEngineering.copyData = function (id, centerId) {
    var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getData');
    $.each(item, function (i, data) {
        $("#civilEngineeringCancel" + data.id).parent().hide();
        $("#civilEngineeringCopy" + data.id).parent().hide();
    });
    $.each(item, function (i, data) {
        if (data.id != id) {
            $("#civilEngineeringPaste" + data.id).parent().show();
        }
    });
    $("#civilEngineeringCancel" + id).parent().show();
    civilEngineering.copyId = centerId;
};

//粘贴子数据
civilEngineering.pasteData = function (id, centerId) {
    Alert("确认粘贴", 2,
        function () {//放弃操作后的函数
            $.each($("#" + civilEngineeringConfig.table).bootstrapTable('getData'), function (i, data) {
                $("#civilEngineeringCancel" + data.id).parent().hide();
                $("#civilEngineeringPaste" + data.id).parent().hide();
                $("#civilEngineeringCopy" + data.id).parent().show();
            });
            civilEngineering.copyId = undefined;
        },//具体操作函数
        function () {
            var copyId = civilEngineering.copyId;
            $.ajax({
                type: "post",
                url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter",
                data: {ids: id, copyId: copyId, type: "DeclareBuildEngineering"},
                success: function (result) {
                    if (result.ret) {
                        civilEngineering.loadList();
                        toastr.success('成功');
                        civilEngineering.copyId = undefined;
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

//把复制的从表粘贴到所有的列中
civilEngineering.pasteAll = function () {
    var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getSelections');
    if (this.isEmpty(civilEngineering.copyId)) {
        if (this.isEmpty(item)) {
            if (item.length >= 1) {
                var copyId = civilEngineering.copyId;
                var ids = "";
                $.each(item, function (i, n) {
                    if (i == item.length - 1) {
                        ids += n.id
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    type: "post",
                    url: getContextPath() + "/declareBuildEngineeringAndEquipmentCenter/copyDeclareBuildEngineeringAndEquipmentCenter",
                    data: {ids: ids, copyId: copyId, type: "DeclareBuildEngineering"},
                    success: function (result) {
                        if (result.ret) {
                            civilEngineering.loadList();
                            toastr.success('成功');
                            civilEngineering.copyId = undefined;
                        } else {
                            Alert("失败:" + result.errmsg);
                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
                civilEngineering.copyId = undefined;
                civilEngineering.loadList();
            } else {
                Alert("请先勾选需要粘贴的对象!");
            }
        }
    } else {
        Alert("请先复制对象!");
    }
};

//放弃复制
civilEngineering.cancelData = function (id, centerId) {
    $("#civilEngineeringCancel" + id).parent().hide();
    $.each($("#" + civilEngineeringConfig.table).bootstrapTable('getData'), function (i, data) {
        $("#civilEngineeringPaste" + data.id).parent().hide();
        $("#civilEngineeringCopy" + data.id).parent().show();
    });
    civilEngineering.copyId = undefined;
};

civilEngineering.loadList = function () {
    var cols = [];
    cols.push({
        field: 'area', title: '区域', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'name', title: '项目名称', visible: true});
    cols.push({field: 'bookNetValue', title: '帐面净值', visible: true});
    cols.push({field: 'bookValue', title: '帐面价值', visible: true});
    cols.push({field: 'declarer', title: '申报人'});
    cols.push({field: 'beLocated', title: '坐落'});
    cols.push({field: 'centerId', title: '中间表id', visible: false});
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var html = $("#civilEngineeringHtml").html();
            return html.replace(/paramCenterId/g, row.centerId);
        }
    });
    $("#" + civilEngineeringConfig.table).bootstrapTable('destroy');
    TableInit(civilEngineeringConfig.table, getContextPath() + "/declareBuildEngineering/getDeclareBuildEngineeringList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId()
    }, {
        method: "get",
        showColumns: false,
        showRefresh: false,
        search: false,
        striped: true,
        onLoadSuccess: function (data) {
            $('.tooltips').tooltip();
        }
    }, true);
};


/**
 * @author:  zch
 * 描述:导入
 * @date:2018-09-28
 **/
civilEngineering.inputFile = function () {
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/declareBuildEngineering/importData",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId()
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: civilEngineeringConfig.excelUpload,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                civilEngineering.loadList();
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

$(function () {
    civilEngineering.loadList();
});