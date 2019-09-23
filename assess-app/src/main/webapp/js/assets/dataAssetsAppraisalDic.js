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

dataAssetsAppraisalDic.saveAssetsCustomizeDataField = function (data,callback) {
    $.ajax({
        url: getContextPath() +"/assetsCustomizeDataField/saveAssetsCustomizeDataField",
        type: "post",
        dataType: "json",
        data: {formData:JSON.stringify(data)},
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
