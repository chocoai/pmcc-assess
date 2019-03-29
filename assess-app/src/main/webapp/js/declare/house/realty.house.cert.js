var assessCommonHouse = {};
/**
 * 空字符串检测
 * @param item
 * @returns {boolean}
 */
assessCommonHouse.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};

assessCommonHouse.fileUpload = function (target, tableName, id) {
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


assessCommonHouse.showFile = function (target, tableName, id) {
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


assessCommonHouse.config = {
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

/**
 * @author:  zch
 * 描述:房产证 初始化并且赋值
 * @date:2018-09-19
 **/
assessCommonHouse.init = function (item) {
    $("#" + assessCommonHouse.config.frm).initForm(item);
    $("#" + assessCommonHouse.config.frm + " input[name='registrationTime']").val(formatDate(item.registrationTime));
    $("#" + assessCommonHouse.config.frm + " input[name='useEndDate']").val(formatDate(item.useEndDate));
    $("#" + assessCommonHouse.config.frm + " input[name='useStartDate']").val(formatDate(item.useStartDate));
    $("#" + assessCommonHouse.config.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    $("#" + assessCommonHouse.config.frm + " input[name='landRegistrationDate']").val(formatDate(item.landRegistrationDate));
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + assessCommonHouse.config.frm).find("select[name='province']"),
        cityTarget: $("#" + assessCommonHouse.config.frm).find("select[name='city']"),
        districtTarget: $("#" + assessCommonHouse.config.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHouseCertificateType, item.type, function (html, data) {
        $("#" + assessCommonHouse.config.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, item.publicSituation, function (html, data) {
        $("#" + assessCommonHouse.config.frm).find('select.publicSituation').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.certUse, function (html, data) {
        $("#" + assessCommonHouse.config.frm).find('select.certUse').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareRoomType, item.nature, function (html, data) {
        $("#" + assessCommonHouse.config.frm).find('select.nature').empty().html(html).trigger('change');
    });
    assessCommonHouse.fileUpload(assessCommonHouse.config.fileId, AssessDBKey.DeclareRealtyHouseCert, assessCommonHouse.isNotBlank(item.id) ? item.id : '0');
    assessCommonHouse.showFile(assessCommonHouse.config.fileId, AssessDBKey.DeclareRealtyHouseCert, assessCommonHouse.isNotBlank(item.id) ? item.id : '0');
};


/**
 * @author:  zch
 * 描述:房产证 更新或者新增
 * @date:2018-09-19
 **/
assessCommonHouse.saveAndUpdateHouse = function () {
    if (!$("#" + assessCommonHouse.config.frm).valid()) {
        return false;
    }
    var data = formParams(assessCommonHouse.config.frm);
    if (!assessCommonHouse.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.pid = "0";
        data.enable = "yes";
        data.declareType = declareFunObj.getDeclareType("房产证");
    }
    //当土地证填写后
    if (data.landNumber){
        var html = "<span class='help-block' for='for'>" +"该字段为必填项(土地证号需要的基本数据)"+"</span>" ;
        if (!data.landAcquisition){
            $("#" + assessCommonHouse.config.frm).find("input[name='landAcquisition']").after(html.replace(/for/g,"landAcquisition"));
            return false;
        }
        if (!data.registrationAuthority){
            $("#" + assessCommonHouse.config.frm).find("input[name='registrationAuthority']").after(html.replace(/for/g,"registrationAuthority"));
            return false;
        }
        if (!data.useStartDate){
            $("#" + assessCommonHouse.config.frm).find("input[name='useStartDate']").after(html.replace(/for/g,"useStartDate"));
            return false;
        }
        if (!data.useEndDate){
            $("#" + assessCommonHouse.config.frm).find("input[name='useEndDate']").after(html.replace(/for/g,"useEndDate"));
            return false;
        }
        if (!data.publicArea){
            $("#" + assessCommonHouse.config.frm).find("input[name='publicArea']").after(html.replace(/for/g,"publicArea"));
            return false;
        }
    }
    assessCommonHouse.saveHouse(data , function () {
        assessCommonHouse.loadList();
        toastr.success('成功!');
        $('#' + assessCommonHouse.config.box).modal("hide");
    });
};

/**
 * @author:  zch
 * 描述:房产证 删除
 * @date:2018-09-19
 **/
assessCommonHouse.deleteHouse = function () {
    var rows = $("#" + assessCommonHouse.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要删除的数据");
    } else {
        Alert("确认要删除么？", 2, null, function () {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            $.ajax({
                url: getContextPath() + "/declareRealtyHouseCert/deleteDeclareRealtyHouseCertById",
                type: "post",
                dataType: "json",
                data: {ids: idArray.join()},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        assessCommonHouse.loadList();
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

assessCommonHouse.getHouse = function (id,callback) {
    $.ajax({
        url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    })
};

assessCommonHouse.saveHouse = function (data,callback) {
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
        data: {formData:JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                callback();
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
 * 描述:房产证 编辑
 * @date:2018-09-19
 **/
assessCommonHouse.editHouse = function () {
    var rows = $("#" + assessCommonHouse.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要编辑的数据");
    } else if (rows.length == 1) {
        assessCommonHouse.getHouse(rows[0].id , function (data) {
            $("#" + assessCommonHouse.config.frm).clearAll();
            assessCommonHouse.init(data);
            //使校验生效
            $("#" + assessCommonHouse.config.frm).validate();
            $('#' + assessCommonHouse.config.box).modal("show");
        });
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * @author:  zch
 * 描述:房产证 显示
 * @date:2018-09-19
 **/
assessCommonHouse.showAddModelHouse = function () {
    //使校验生效
    $('#' + assessCommonHouse.config.box).find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    $('#' + assessCommonHouse.config.box).find(".panel-body").prepend(commonDeclareApplyModel.house.getHtml());
    $("#" + assessCommonHouse.config.frm).validate();
    mapPosition.getCurrentCityByArea(function (area) {
        assessCommonHouse.init(area);
    });
    //由于是填充的hmtl所以需要手动初始化select2
    DatepickerUtils.parse();
    $('#' + assessCommonHouse.config.box).find(".select2").each(function () {
        $(this).select2();
    });
    $("#" + assessCommonHouse.config.frm).clearAll();
    $('#' + assessCommonHouse.config.box).modal("show");
};

/**
 * 土地证 初始化并且赋值
 * @param item
 */
assessCommonHouse.initLand = function (item) {
    $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).initForm(item);
    AssessCommon.initAreaInfo({
        provinceTarget: $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).find("select[name='province']"),
        cityTarget: $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).find("select[name='city']"),
        districtTarget: $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).find("select[name='district']"),
        provinceValue: item.province,
        cityValue: item.city,
        districtValue: item.district
    });
    $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(item.terminationDate));
    $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
    assessCommonHouse.showFile(assessCommonHouse.config.landFileId, AssessDBKey.DeclareRealtyLandCert, assessCommonHouse.isNotBlank(item.id) ? item.id : '0');
    assessCommonHouse.fileUpload(assessCommonHouse.config.landFileId, AssessDBKey.DeclareRealtyLandCert, assessCommonHouse.isNotBlank(item.id) ? item.id : '0');
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, item.type, function (html, data) {
        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, item.useRightType, function (html, data) {
        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.purpose, function (html, data) {
        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
    });

};

/**
 * 土地证显示
 * @param id
 */
assessCommonHouse.showAddModelLand = function (id) {
    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApplyModel.land.getHtml());
    //由于是填充的hmtl所以需要手动初始化select2
    DatepickerUtils.parse();
    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).find(".select2").each(function () {
        $(this).select2();
    });
    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).modal("show");
    $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).clearAll();
    var item = $("#" + assessCommonHouse.config.table).bootstrapTable('getRowByUniqueId', id);
    if (assessCommonHouse.isNotBlank(item.pid)) {//关联情况
        //从服务端获取关联数据
        $.ajax({
            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            data: {id: item.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    if (assessCommonHouse.isNotBlank(result.data)) {
                        assessCommonHouse.initLand(result.data);
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
    } else {//未关联情况
        //把需要关联的数据带过来一些
        $.ajax({
            url: getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (resultA) {
                if (resultA.ret) {
                    var data = {
                        pid: id,
                        beLocated: resultA.data.beLocated,
                        streetNumber: resultA.data.streetNumber,
                        attachedNumber: resultA.data.attachedNumber,
                        buildingNumber: resultA.data.buildingNumber,
                        unit: resultA.data.unit,
                        roomNumber: resultA.data.roomNumber,
                        floor: resultA.data.floor,
                        province: resultA.data.province,
                        city: resultA.data.city,
                        district: resultA.data.district
                    };
                    assessCommonHouse.initLand(data);
                }
            },
            error: function (resultA) {
                Alert("调用服务端方法失败，失败原因:" + resultA);
            }
        })
    }
};

/**
 * 土地证保存
 * @returns {boolean}
 */
assessCommonHouse.saveAndUpdateLand = function () {
    if (!$("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm).valid()) {
        return false;
    }
    var data = formParams(assessCommonHouse.config.son.declareRealtyLandCert.frm);
    data.planDetailsId = declareCommon.getPlanDetailsId();
    data.enable = "no";
    $.ajax({
        type: "POST",
        url: getContextPath() + "/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
        data: {formData:JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                if (data.id){
                    assessCommonHouse.loadList();
                    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).modal("hide");
                }else {
                    assessCommonHouse.getHouse(data.pid , function (item) {
                        assessCommonHouse.saveHouse({id:item.id,pid:result.data} , function () {
                            assessCommonHouse.loadList();
                            $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).modal("hide");
                        });
                    });
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
 * 描述:房产证 识别
 * @date:2018-09-19
 **/
assessCommonHouse.distinguish = function () {
    var id = formParams(assessCommonHouse.config.son.declareRealtyLandCert.frm).id;
    id = assessCommonHouse.isNotBlank(id) ? id : '0';
    $.ajax({
        url: getContextPath() + "/public/getSysAttachmentDtoList",
        type: "get",
        dataType: "json",
        async: false,
        data: {
            tableId: id,
            tableName: AssessDBKey.DeclareRealtyHouseCert
        },
        success: function (result) {
            console.log(result.data);
            if (result.ret && result.data) {
                if (result.data.length >= 1) {
                    if (AssessCommon.checkImgFile(result.data[0].fileName)) {//是否是图片检测
                        AssessCommon.parseRealtyHouseCert(result.data[0].id, AssessDBKey.HouseOcrkey, function (data) {//获取图片解析数据
                            var dataJson = JSON.parse(data.dataJson);
                            var item = {
                                floorArea: dataJson.area,
                                publicSituation: dataJson.commonSituation,
                                planningUse: dataJson.used,
                                beLocated: dataJson.located,
                                ownership: dataJson.obligee,
                                number: dataJson.number,
                                registrationDate: dataJson.registration
                            };
                            $("#" + assessCommonHouse.config.frm).initForm(item);
                            $("#" + assessCommonHouse.config.frm + " input[name='registrationDate']").val(formatDate(item.registrationDate));
                        });
                    } else {
                        Alert("不是图片");
                    }
                } else {
                    Alert("无附件");
                }
            }
        }
    });
};


/**
 * 土地证excel导入处理
 */
assessCommonHouse.inputFileLand = function () {
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
                assessCommonHouse.loadList();
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
 * 房产证excel导入处理
 */
assessCommonHouse.inputFile = function () {
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
                assessCommonHouse.loadList();
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
 * 土地证附件触发事件 (会先检测是否关联)
 * @param id
 */
assessCommonHouse.landImportEvent = function (id) {
    var item = $("#" + assessCommonHouse.config.table).bootstrapTable('getRowByUniqueId', id);
    if (assessCommonHouse.isNotBlank(item.pid)) {
        $.ajax({
            url: getContextPath() + "/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            async: false,
            data: {id: item.pid, planDetailsId: declareCommon.getPlanDetailsId()},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (assessCommonHouse.isNotBlank(data)) {
                        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.fileId).attr("data-id", data.id);
                        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.fileId).trigger('click');
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
 * 土地证附件 导入处理
 */
assessCommonHouse.landImportHandle = function () {
    var id = $("#" + assessCommonHouse.config.son.declareRealtyLandCert.fileId).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyLandCert,
            tableId: id,
            fieldsName: assessCommonHouse.config.landFileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: assessCommonHouse.config.son.declareRealtyLandCert.fileId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                assessCommonHouse.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

/**
 * 房产证附件触发事件
 */
assessCommonHouse.houseImportEvent = function (id) {
    $("#" + assessCommonHouse.config.fileIdNew).attr("data-id", id);
    $("#" + assessCommonHouse.config.fileIdNew).trigger('click');
};
/**
 * 房产证附件 导入处理
 */
assessCommonHouse.houseImportHandle = function () {
    var id = $("#" + assessCommonHouse.config.fileIdNew).attr("data-id");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/public/importAjaxFile",
        data: {
            planDetailsId: declareCommon.getPlanDetailsId(),
            tableName: AssessDBKey.DeclareRealtyHouseCert,
            tableId: id,
            fieldsName: assessCommonHouse.config.fileId
        },//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: assessCommonHouse.config.fileIdNew,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                assessCommonHouse.loadList();
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};

/**
 * 房产证列表
 */
assessCommonHouse.loadList = function () {
    var cols = commonDeclareApplyModel.house.getHouseColumn();
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            if (row.pid) {
                str += '<a class="btn btn-xs btn-success" href="javascript:assessCommonHouse.showAddModelLand(' + row.id + ');" ><i class="fa fa-check">土地证</i></a>';
            } else {
                str += '<a class="btn btn-xs btn-success" href="javascript:assessCommonHouse.showAddModelLand(' + row.id + ');" ><i class="fa fa-remove">土地证</i></a>';
            }
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='assessCommonHouse.houseImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='assessCommonHouse.landImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + assessCommonHouse.config.table).bootstrapTable('destroy');
    TableInit(assessCommonHouse.config.table, getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),
        enable:'yes'
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    }, true);
};

$(function () {
    assessCommonHouse.loadList();
});

