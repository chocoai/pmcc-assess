/**
 * Created by zch on 2019-9-19.
 */


var dataAssetsAppraisalDic = {};

//资产申报
dataAssetsAppraisalDic.declaration = "declaration";
//资产清查
dataAssetsAppraisalDic.inventory = "inventory";
//出具报告
dataAssetsAppraisalDic.report = "report";
//评定估算阶段
dataAssetsAppraisalDic.estimation = "estimation";


dataAssetsAppraisalDic.getDataAssetsAppraisalDicList = function (data, callback) {
    $.ajax({
        url: getContextPath() + "/dataAssetsAppraisalDic/getDataAssetsAppraisalDicListVo",
        type: "get",
        dataType: "json",
        data: data,
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
};

dataAssetsAppraisalDic.saveAssetsCustomizeDataField = function (arr, callback) {
    $.ajax({
        url: getContextPath() + "/assetsCustomizeDataField/saveAssetsCustomizeDataFieldAll",
        type: "post",
        dataType: "json",
        data: {formData: JSON.stringify(arr)},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
};

dataAssetsAppraisalDic.deleteAssetsCustomizeDataFieldById = function (id, callback) {
    $.ajax({
        url: getContextPath() + "/assetsCustomizeDataField/deleteAssetsCustomizeDataFieldById/" + id,
        type: "post",
        dataType: "json",
        data: {_method: "DELETE"},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
};

/**
 * 触发事件来保存数据
 * @param _this
 * @param id
 */
dataAssetsAppraisalDic.inputBlur = function (_this, id) {
    var text = $(_this).val();
    var target = $(_this).closest(".form-group");
    if (text) {
        var item = dataAssetsAppraisalDic.getFormData(target);
        item.id = id;
        dataAssetsAppraisalDic.saveAssetsCustomizeDataField([item], function () {

        });
    }
};

dataAssetsAppraisalDic.selectChange = function (_this, id) {
    var target = $(_this).closest(".form-group");
    var item = dataAssetsAppraisalDic.getFormData(target);
    item.id = id;
    dataAssetsAppraisalDic.saveAssetsCustomizeDataField([item], function () {

    });
};

dataAssetsAppraisalDic.getFormData = function (target) {
    var data = {};
    if (target.find("input[name='name']").size() != 0) {
        data.name = target.find("input[name='name']").val();
    }
    if (target.find("textarea[name='remark']").size() != 0) {
        data.remark = target.find("textarea[name='remark']").val();
    }
    if (target.find("select[name='typeCustomize']").size() != 0) {
        data.typeCustomize = target.find("select[name='typeCustomize'] option:selected").val();
    }
    if (target.find("select[name='category']").size() != 0) {
        data.category = target.find("select[name='category'] option:selected").val();
    }
    return data;
};

dataAssetsAppraisalDic.valid = function (frm) {
    var i = 0;
    var filters = ["input","textarea" , "select"] ;
    $.each(filters,function (j,ele) {
        frm.find(ele).each(function (i,item) {
            var name = $(item).attr("name") ;
            var html = "<span class='help-block' for='for'>" + "该字段为必填项("  +"必要的基本数据)" + "</span>";
            var required = $(item).attr("required") ;
            var value = $(item).val() ;
            if (ele == 'select'){
                value = $(item).find("option:selected").val() ;
            }
            if(required){
                if (value){
                }else {
                    $(item).after(html.replace(/for/g, name));
                    i++;
                }
            }
        });
    });
    return (i==0);
};

dataAssetsAppraisalDic.initForm = function (data, target) {
    try {
        target.find(".select2").each(function () {
            $(this).select2({minimumResultsForSearch: -1});
        })
    } catch (e) {
    }
    AssessCommon.loadDataDicByKey(AssessDicKey.DECLARE_ASSETS_CUSTOMIZE_TYPE, data.typeCustomize, function (html, item) {
        target.find("select[name='typeCustomize']").empty().html(html).trigger('change');
    });
    target.find("select[name='typeCustomize']").off('change').on('change', function () {
        AssessCommon.loadDataDicByPid($(this).val(), data.category, function (html, item) {
            target.find("select[name='category']").empty().html(html).trigger('change');
        });
    });
};
