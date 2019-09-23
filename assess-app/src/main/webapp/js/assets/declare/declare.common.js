/**
 * Created by kings on 2018-12-20.
 */
var declareCommon = {};

declareCommon.fileArray  = ["project_proxy"] ;


declareCommon.declareApplyForm = $('#declareApplyForm');

declareCommon.getPlanDetailsId = function () {
    return declareCommon.declareApplyForm.find('[name=planDetailsId]').val();
};
declareCommon.getProjectId = function () {
    return declareCommon.declareApplyForm.find('[name=projectId]').val();
};


declareCommon.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        deleteFlag: deleteFlag
    });
    // FileUtils.uploadFiles({
    //     target: target,
    //     disabledTarget: "btn_submit",
    //     onUpload: function (file) {
    //         var formData = {
    //             fieldsName: target,
    //             tableName: tableName,
    //             tableId: id
    //         };
    //         return formData;
    //     }, onUploadComplete: function (result, file) {
    //
    //     },
    //     deleteFlag: true
    // });
};

declareCommon.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        deleteFlag: deleteFlag
    })
};

declareCommon.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};

/**
 * 获取附件列表
 * @param data
 * @param callback
 */
declareCommon.getSysAttachmentDtoList = function (data,callback) {
    $.ajax({
        url: getContextPath() +"/public/getSysAttachmentDtoList",
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

declareCommon.getServerDeclaration = function (value , callback) {
    var data = {setting:true,pid:declareCommon.declareApplyForm.find('[name=assetsSettingId]').val()} ;
    dataAssetsAppraisalDic.getDataAssetsAppraisalDicList(data,function (item) {
        if (callback) {
            var arr = [] ;
            $.each(item,function (i,obj) {
                if (value == 2){
                    if (obj.fileViewName){
                        arr.push(obj) ;
                    }
                }
                if (value == 1){
                    if (obj.fileViewName){
                    }else {
                        arr.push(obj) ;
                    }
                }
            });
            callback(arr);
        }
    }) ;
};

declareCommon.writeDeclarationHtml = function (value,data) {
    console.log(data) ;
    if (value == 1){

    }
};

/**
 * 删除FTP附件
 * @param array
 * @param callback
 */
declareCommon.deleteAttachmentById = function (array,callback) {
    $.ajax({
        url: getContextPath() +"/public/deleteAttachmentById",
        type: "post",
        dataType: "json",
        data: {id:array.join(",")},
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
