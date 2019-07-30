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
    declareEconomicIndicatorsHead:{
        frm:  declareCommon.config.declareEconomicIndicatorsHead2.frm,
        name: declareCommon.config.declareEconomicIndicatorsHead2.name,
        box: declareCommon.config.declareEconomicIndicatorsHead2.box
    } ,
    declareEconomicIndicatorsContent:{
        frm:  declareCommon.config.declareEconomicIndicatorsContent2.frm,
        name: declareCommon.config.declareEconomicIndicatorsContent2.name
    },
    handleCopy: "#realtyRealEstateHandleInputGroup"
};

declareRealtyRealEstateCert.init = function (item) {
    declareCommon.initDeclareRealty(item,$("#" + declareRealtyRealEstateCert.config.frm),[declareRealtyRealEstateCert.config.newFileId],null);
};

declareRealtyRealEstateCert.showAddModel = function () {
    $('#' + declareRealtyRealEstateCert.config.box).find("#" + commonDeclareApplyModel.config.realEstateCert.handleId).remove();
    $('#' + declareRealtyRealEstateCert.config.box).find(".panel-body").prepend(commonDeclareApplyModel.realEstateCert.getHtml());
    declareCommon.showHtmlMastInit($("#" + declareRealtyRealEstateCert.config.frm),function (area) {
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
    var target = $("#" + declareRealtyRealEstateCert.config.fileId) ;
    var id = target.attr("data-id");
    var value = target.val();
    if (!declareCommon.isNotBlank(value)){
        return false ;
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
                declareCommon.getDeclareRealtyData(id,function (row) {
                    toastr.success('成功 !');
                    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('updateByUniqueId', {id: id, row: row});
                }) ;
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
    $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('destroy');
    TableInit(declareRealtyRealEstateCert.config.table, getContextPath() + "/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
        planDetailsId: declareCommon.getPlanDetailsId(),enable:declareCommon.masterData
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
        var arr = [] ;
        $.each(rows, function (i, n) {
            arr.push(n.id);
        });
        Alert("是否删除", 2, null,
            function () {
                declareCommon.deleteDeclareRealtyData(arr.join(","),function () {
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

//经济指标
declareRealtyRealEstateCert.showAddModelDeclareEconomicIndicators = function (id) {
    var element1 = $("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm).find(".panel-body") ;
    var element2 = $("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsContent.frm).find(".panel-body") ;
    declareCommon.appendDeclareEconomicIndicators(element1,element2) ;
    var item = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getRowByUniqueId', id);
    if (!declareCommon.isNotBlank(item.centerId)) {
        toastr.success('不合符调整后的数据约定,请联系管理员!');
        return false;
    }
    declareCommon.showHtmlMastInit($("#" + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm), function (area) {
        declareCommon.getDeclareBuildCenter(item.centerId, function (centerData) {
            if (centerData.indicatorId){
                declareCommon.getByDeclareEconomicIndicatorsHeadId(centerData.indicatorId , function (data) {
                    data.centerId = item.centerId;
                    declareCommon.initDeclareEconomicIndicators($("#" + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm) ,
                        $("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsContent.frm),
                        data ,
                        function () {
                        $('#' + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.box).modal("show");
                    }) ;
                }) ;
            }else {
                declareCommon.firstLoadChildDeclareEconomicIndicators(element2) ;
                declareCommon.initDeclareEconomicIndicators($("#" + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm),$("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsContent.frm),{centerId: centerData.id} ,function () {
                    $('#' + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.box).modal("show");
                }) ;
            }
        });
    });
};

//经济指标删除
declareRealtyRealEstateCert.deleteDeclareEconomicIndicatorsCenter = function () {
    var data = formParams(declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm);
    if (declareCommon.isNotBlank(data.centerId)) {
        declareCommon.getDeclareBuildCenter(data.centerId, function (centerData) {
            if (declareCommon.isNotBlank(centerData.indicatorId)) {//关联情况
                declareCommon.deleteByDeclareBuildCenterType(data.centerId, declareCommon.declareCenterData.indicatorId.type, function () {
                    $('#' + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.box).modal("hide");
                    toastr.success('已经删除!');
                    declareRealtyRealEstateCert.loadList();
                });
            } else {
                toastr.success('未添加数据!');
            }
        });
    }
};

//经济指标 save
declareRealtyRealEstateCert.saveDeclareEconomicIndicatorsData = function (this_) {
    declareCommon.saveDeclareEconomicIndicators(function () {
        $('#' + declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.box).modal("hide");
        $("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm).find(".panel-body").empty() ;
        $("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsContent.frm).find(".panel-body").empty() ;
        toastr.info("成功!");
    },$("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsHead.frm),
        $("#"+declareRealtyRealEstateCert.config.declareEconomicIndicatorsContent.frm)) ;
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
    if (data.landNumber){
        var html = "<span class='help-block' for='for'>" +"该字段为必填项(土地证号需要的基本数据)"+"</span>" ;
        if (!data.landAcquisition){
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='landAcquisition']").after(html.replace(/for/g,"landAcquisition"));
            return false;
        }
        if (!data.registrationAuthority){
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='registrationAuthority']").after(html.replace(/for/g,"registrationAuthority"));
            return false;
        }
        if (!data.useStartDate){
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='useStartDate']").after(html.replace(/for/g,"useStartDate"));
            return false;
        }
        if (!data.useEndDate){
            $("#" + declareRealtyRealEstateCert.config.frm).find("input[name='useEndDate']").after(html.replace(/for/g,"useEndDate"));
            return false;
        }
    }
    declareCommon.saveDeclareRealtyData(data,function (item) {
        if (!declareCommon.isNotBlank(data.id)){
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
