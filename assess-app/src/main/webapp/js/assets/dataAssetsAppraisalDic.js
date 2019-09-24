/**
 * Created by zch on 2019-9-19.
 */


var dataAssetsAppraisalDic = {} ;

//资产申报
dataAssetsAppraisalDic.declaration = "declaration" ;
//资产清查
dataAssetsAppraisalDic.inventory = "inventory" ;
//出具报告
dataAssetsAppraisalDic.report = "report" ;
//评定估算阶段
dataAssetsAppraisalDic.estimation = "estimation" ;



dataAssetsAppraisalDic.getDataAssetsAppraisalDicList = function (data,callback) {
    $.ajax({
        url: getContextPath() +"/dataAssetsAppraisalDic/getDataAssetsAppraisalDicListVo",
        type: "get",
        dataType: "json",
        data: data,
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                toastr.success('失败' + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
};

dataAssetsAppraisalDic.saveAssetsCustomizeDataField = function (arr,callback) {
    $.ajax({
        url: getContextPath() +"/assetsCustomizeDataField/saveAssetsCustomizeDataFieldAll",
        type: "post",
        dataType: "json",
        data: {formData:JSON.stringify(arr)},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                toastr.success('失败' + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
};

dataAssetsAppraisalDic.deleteAssetsCustomizeDataFieldById = function (id,callback) {
    $.ajax({
        url: getContextPath() +"/assetsCustomizeDataField/deleteAssetsCustomizeDataFieldById/"+id,
        type: "post",
        dataType: "json",
        data: {_method: "DELETE"},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                toastr.success('失败' + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
};

/**
 * 触发事件来保存数据
 * @param _this
 * @param id
 */
dataAssetsAppraisalDic.inputBlur = function (_this,id) {
    var text = $(_this).val() ;
    if (text){
        var item = {name:text,id:id} ;
        dataAssetsAppraisalDic.saveAssetsCustomizeDataField([item] ,function () {

        });
    }
};
