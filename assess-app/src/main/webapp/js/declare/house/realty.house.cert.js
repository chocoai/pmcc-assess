var assessCommonHouse = {};

assessCommonHouse.config = {
    frm: declareCommon.config.house.frm,
    name: declareCommon.config.house.name,
    table: declareCommon.config.house.table,
    box: declareCommon.config.house.box,
    fileId: declareCommon.config.house.fileId,
    fileIdNew: declareCommon.config.house.fileIdNew,
    fileViewNew: declareCommon.config.house.fileViewNew,
    landFileId: declareCommon.config.house.landFileId,
    son: {
        declareRealtyLandCert: {
            frm: declareCommon.config.house.son.declareRealtyLandCert.frm,
            box: declareCommon.config.house.son.declareRealtyLandCert.box,
            view: declareCommon.config.house.son.declareRealtyLandCert.view,
            fileId: declareCommon.config.house.son.declareRealtyLandCert.fileId,
            name: declareCommon.config.house.son.declareRealtyLandCert.name,
            table: declareCommon.config.house.son.declareRealtyLandCert.table
        }
    },
    handleCopy: "#houseHandleInputGroup"
};

/**
 * @author:  zch
 * 描述:房产证 初始化并且赋值
 * @date:2018-09-19
 **/
assessCommonHouse.init = function (item) {
    declareCommon.initHouse(item, $("#" + assessCommonHouse.config.frm), [assessCommonHouse.config.fileId], null);
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
    if (!declareCommon.isNotBlank(data.id)) {
        data.planDetailsId = declareCommon.getPlanDetailsId();
        data.pid = "0";
        data.enable = declareCommon.masterData;
        data.declareType = declareCommon.declareHouseType;
    }
    //当土地证填写后
    if (data.landNumber) {
        var html = "<span class='help-block' for='for'>" + "该字段为必填项(土地证号需要的基本数据)" + "</span>";
        if (!data.landAcquisition) {
            $("#" + assessCommonHouse.config.frm).find("input[name='landAcquisition']").after(html.replace(/for/g, "landAcquisition"));
            return false;
        }
        if (!data.registrationAuthority) {
            $("#" + assessCommonHouse.config.frm).find("input[name='registrationAuthority']").after(html.replace(/for/g, "registrationAuthority"));
            return false;
        }
        if (!data.useStartDate) {
            $("#" + assessCommonHouse.config.frm).find("input[name='useStartDate']").after(html.replace(/for/g, "useStartDate"));
            return false;
        }
        if (!data.useEndDate) {
            $("#" + assessCommonHouse.config.frm).find("input[name='useEndDate']").after(html.replace(/for/g, "useEndDate"));
            return false;
        }
        if (!data.publicArea) {
            $("#" + assessCommonHouse.config.frm).find("input[name='publicArea']").after(html.replace(/for/g, "publicArea"));
            return false;
        }
    }
    declareCommon.saveHouseData(data, function (item) {
        if (!declareCommon.isNotBlank(data.id)) {
            declareCommon.declareBuildCenterSaveAndUpdate({
                houseId: item,
                planDetailsId: declareCommon.getPlanDetailsId(),
                type: declareCommon.declareCenterData.houseId.type
            }, function () {
                //重新加载一次
                assessCommonHouse.loadList();
            });
        }
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
            var idCenters = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
                idCenters.push(item.centerId);
            });
            declareCommon.deleteHouseData(idArray.join(","), function () {
                declareCommon.deleteDeclareBuildCenter(idCenters.join(","), function () {
                    toastr.success('删除成功');
                    assessCommonHouse.loadList();
                });
            });
        })
    }
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
        assessCommonHouse.showAddModelHouse(rows[0]);
    } else {
        toastr.info("只能选择一行数据进行编辑");
    }
};

/**
 * @author:  zch
 * 描述:房产证 显示
 * @date:2018-09-19
 **/
assessCommonHouse.showAddModelHouse = function (data) {
    //使校验生效
    $('#' + assessCommonHouse.config.box).find("#" + commonDeclareApplyModel.config.house.handleId).remove();
    $('#' + assessCommonHouse.config.box).find(".panel-body").prepend(commonDeclareApplyModel.house.getHtml());
    declareCommon.showHtmlMastInit($("#" + assessCommonHouse.config.frm), function (area) {
        if (jQuery.isEmptyObject(data)) {
            assessCommonHouse.init(area);
        } else {
            assessCommonHouse.init(data);
        }
    });
    $('#' + assessCommonHouse.config.box).modal("show");
};

/**
 * 拷贝数据
 */
assessCommonHouse.copyData = function () {
    var rows = $("#" + assessCommonHouse.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要复制的数据");
    } else if (rows.length == 1) {
        Alert("确认要复制？", 2, null, function () {
            $(assessCommonHouse.config.handleCopy).find("input[name='name']").val(rows[0].certName);
            $(assessCommonHouse.config.handleCopy).find("input[name='id']").val(rows[0].centerId);
            toastr.info("复制从数据成功!");
            $("#" + assessCommonHouse.config.table).bootstrapTable('uncheckAll');
        });
    } else {
        toastr.info("只能选择一行数据进行复制");
    }
};

/**
 * 粘贴数据 (把复制的从表粘贴到所有的列中)
 */
assessCommonHouse.pasteAll = function () {
    var rows = $("#" + assessCommonHouse.config.table).bootstrapTable('getSelections');
    if (!rows || rows.length <= 0) {
        toastr.info("请选择要复制的数据");
    } else if (rows.length >= 1) {
        var copyId = $(assessCommonHouse.config.handleCopy).find("input[name='id']").val();
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
            $("#" + assessCommonHouse.config.table).bootstrapTable('uncheckAll');
            return false;
        }
        Alert("确认要粘贴？", 2, null, function () {
            declareCommon.copyDeclareBuildCenter(copyId, idArray.join(","), function () {
                toastr.info("粘贴从数据成功!");
                $("#" + assessCommonHouse.config.table).bootstrapTable('uncheckAll');
                $(assessCommonHouse.config.handleCopy).find("input").val('');
                assessCommonHouse.loadList();
            });
        });
    } else {
        toastr.info("只能选择一行数据进行复制");
    }
};

/**
 * 土地证 初始化并且赋值
 * @param item
 */
assessCommonHouse.initLand = function (item) {
    declareCommon.initLand(item, $("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm), [assessCommonHouse.config.landFileId], null);
};

/**
 * 土地证显示
 * @param id
 */
assessCommonHouse.showAddModelLand = function (id) {
    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).find("#" + commonDeclareApplyModel.config.land.handleId).remove();
    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).find(".panel-body").prepend(commonDeclareApplyModel.land.getHtml());
    var item = $("#" + assessCommonHouse.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    declareCommon.showHtmlMastInit($("#" + assessCommonHouse.config.son.declareRealtyLandCert.frm), function (area) {
        $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).modal("show");
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landId)) {//关联情况
                declareCommon.getLandData(centerData.landId, function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        data.centerId = item.centerId;
                        assessCommonHouse.initLand(data);
                    } else {
                        toastr.success('关联的土地证数据已经被删除!');
                        toastr.success('请重新填写!');
                    }
                });
            } else {//未关联情况
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
                    district: item.district,
                    landRightNature: item.landAcquisition,
                    registrationAuthority: item.registrationAuthority,
                    apportionmentArea: item.apportionmentArea,
                    terminationDate: item.useEndDate
                };
                assessCommonHouse.initLand(data);
            }
        });
    });
};

/**
 * 删除中间表得土地证 注意这得删除不是通过土地证方法删除得土地证而是中间表删除得土地证
 */
assessCommonHouse.deleteLandCenter = function () {
    var data = formParams(assessCommonHouse.config.son.declareRealtyLandCert.frm);
    console.log(data);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landId)) {//关联情况
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.landId.type, function () {
                    $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).modal("hide");
                    toastr.success('已经删除!');
                    assessCommonHouse.loadList();
                });
            } else {
                toastr.success('未添加数据!');
            }
        });
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
    data.enable = declareCommon.branchData;
    declareCommon.saveLandData(data, function (landId) {
        if (landId) {
            declareCommon.declareBuildCenterSaveAndUpdate({landId: landId, id: data.centerId}, function () {
                assessCommonHouse.loadList();
                $('#' + assessCommonHouse.config.son.declareRealtyLandCert.box).modal("hide");
            });
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
    id = declareCommon.isNotBlank(id) ? id : '0';
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
                            declareCommon.initHouse(item, $("#" + assessCommonHouse.config.frm), [], null);
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
    if (declareCommon.isNotBlank(item.centerId)) {
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.landId)) {
                declareCommon.getLandData(centerData.landId, function (data) {
                    if (declareCommon.isNotBlank(data)) {
                        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.fileId).attr("data-id", data.id);
                        $("#" + assessCommonHouse.config.son.declareRealtyLandCert.fileId).trigger('click');
                    } else {
                        toastr.success('关联的土地证数据已经被删除!');
                        toastr.success('请重新填写!');
                    }
                });
            } else {
                toastr.success('没有关联土地证数据!');
            }
        });
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
    var cols = declareCommon.getHouseColumn();
    cols.push({field: 'fileViewName', title: '附件'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success" href="javascript:assessCommonHouse.showAddModelLand(' + row.id + ');" ><i class="fa fa-eye">土地证</i></a>';
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='assessCommonHouse.houseImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
            str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='assessCommonHouse.landImportEvent(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
            str += '</div>';
            return str;
        }
    });
    $("#" + assessCommonHouse.config.table).bootstrapTable('destroy');
    TableInit(assessCommonHouse.config.table, getContextPath() + "/declareRealtyHouseCert/getDeclareRealtyHouseCertList", cols, {
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

$(function () {
    assessCommonHouse.loadList();
});

