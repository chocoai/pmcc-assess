/**
 * Created by zch on 2019-9-27.
 */


/**
 * Created by kings on 2018-12-20.
 */
var commonAssets = {};

commonAssets.declareApplyForm = $('#declareApplyForm');

commonAssets.getPlanDetailsId = function () {
    return commonAssets.declareApplyForm.find('[name=planDetailsId]').val();
};
commonAssets.getProjectId = function () {
    return commonAssets.declareApplyForm.find('[name=projectId]').val();
};

commonAssets.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};


commonAssets.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
    commonAssets.fileUpload2(target, tableName, id, deleteFlag, true, fieldsName);
};

commonAssets.fileUpload2 = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        deleteFlag: deleteFlag,
        editFlag: editFlag
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

commonAssets.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
    commonAssets.showFile2(target, tableName, id, deleteFlag, true, fieldsName);
};

commonAssets.showFile2 = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
    commonAssets.showFile3(target, tableName, id, deleteFlag, true, false, fieldsName);
};

commonAssets.showFile3 = function (target, tableName, id, deleteFlag, editFlag, signatureFlag, fieldsName) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        signatureFlag: signatureFlag,
        deleteFlag: deleteFlag,
        editFlag: editFlag
    })
};


/**
 * 删除FTP附件
 * @param array
 * @param callback
 */
commonAssets.deleteAttachmentById = function (array, callback) {
    $.ajax({
        url: getContextPath() + "/public/deleteAttachmentById",
        type: "post",
        dataType: "json",
        data: {id: array.join(",")},
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
 * 获取附件列表
 * @param data
 * @param callback
 */
commonAssets.getSysAttachmentDtoList = function (data, callback) {
    $.ajax({
        url: getContextPath() + "/public/getSysAttachmentDtoList",
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

/**
 * 批量更新附件
 * @param data
 * @param callback
 */
commonAssets.batchUpdateSysAttachmentDto = function (data, callback) {
    $.ajax({
        url: getContextPath() + "/public/batchUpdateSysAttachmentDto",
        type: "post",
        dataType: "json",
        data: {formData: JSON.stringify(data)},
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

commonAssets.getServerDeclaration2 = function (callback) {
    var data = {setting: true, pid: commonAssets.declareApplyForm.find('[name=assetsSettingId]').val()};
    dataAssetsAppraisalDic.getDataAssetsAppraisalDicList(data, function (item) {
        if (callback) {
            callback(item);
        }
    });
};
/**
 * 获取配置项
 * @param value
 * @param callback
 */
commonAssets.getServerDeclaration = function (value, callback) {
    commonAssets.getServerDeclaration2(function (item) {
        if (callback) {
            var arr = [];
            $.each(item, function (i, obj) {
                if (value == 2) {
                    if (obj.fileId) {
                        arr.push({
                            name: obj.name,
                            projectId: commonAssets.getProjectId(),
                            planDetailsId: commonAssets.getPlanDetailsId(),
                            type: value,
                            fileId: obj.fileId
                        })
                    }
                }
                if (value == 1) {
                    if (obj.fileId) {
                        //
                    } else {
                        arr.push({
                            name: obj.name,
                            projectId: commonAssets.getProjectId(),
                            planDetailsId: commonAssets.getPlanDetailsId(),
                            type: value
                        })
                    }
                }
            });
            if (arr.length != 0) {
                dataAssetsAppraisalDic.saveAssetsCustomizeDataField(arr, function (result) {
                    callback(result);
                });
            }
        }
    });
};

/**
 * 页面载入html
 * @param value
 */
commonAssets.writeDeclarationHtml = function (value) {
    var target = undefined;
    if (value == '2') {
        commonAssets.getServerDeclaration(value, function (data) {
            target = $("#assetsCustomizeDataField_Fixed_FileId");
            if (data.length == 0) {
                return null;
            }
            $.each(data, function (i, item) {
                var html = $("#assetsCustomizeDataField_Fixed_fileModel").html();
                var fileId = "assetsCustomizeDataField_Fixed_file" + item.id;
                html = html.replace(/{number}/g, item.id);
                html = html.replace(/{name}/g, item.name);
                html = html.replace(/{id}/g, item.id);
                html = html.replace(/{value}/g, value);
                html = html.replace(/{method}/g, 'commonAssets');
                target.append(html);
                var select = {};
                select.tableName = AssessDBKey.AssetsCustomizeDataField;
                select.tableId = item.id;
                commonAssets.getSysAttachmentDtoList(select, function (obj) {
                    $.each(obj, function (i, n) {
                        n.fieldsName = fileId;
                    });
                    commonAssets.batchUpdateSysAttachmentDto(obj, function () {
                        commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, false, true, fileId);
                        commonAssets.fileUpload2(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, false, true, fileId);
                    })
                });
            });
        });
    }
    if (value == '1') {
        commonAssets.getServerDeclaration(value, function (data) {
            target = $("#assetsCustomizeDataField_Fixed_fieldId");
            if (data.length == 0) {
                return null;
            }
            $.each(data, function (i, item) {
                var html = $("#assetsCustomizeDataField_Fixed_fieldModel").html();
                var fileId = "assetsCustomizeDataField_Fixed" + item.id;
                html = html.replace(/{number}/g, item.id);
                html = html.replace(/{name}/g, item.name);
                html = html.replace(/{id}/g, item.id);
                html = html.replace(/{value}/g, value);
                html = html.replace(/{method}/g, 'commonAssets');
                target.append(html);
                commonAssets.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
                commonAssets.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
            });
        });
    }
    if (value == '0') {
        var item = {
            projectId: commonAssets.getProjectId(),
            planDetailsId: commonAssets.getPlanDetailsId(),
            type: value
        };
        dataAssetsAppraisalDic.saveAssetsCustomizeDataField([item], function (data) {
            $.each(data, function (i, item) {
                target = $("#assetsCustomizeDataOther_fieldId");
                var html = $("#other_EnclosureModel").html();
                var fileId = "other_Enclosure" + item.id;
                html = html.replace(/{number}/g, item.id);
                //
                html = html.replace(/{id}/g, item.id);
                html = html.replace(/{value}/g, value);
                html = html.replace(/{method}/g, 'commonAssets');
                target.append(html);
                var group = $(".other_EnclosureModel" + item.id);
                dataAssetsAppraisalDic.initForm({}, group);
                commonAssets.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
                commonAssets.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
            });
        });

    }
};

/**
 * 清除html,并且删除对应得附件
 * @param _this
 * @param fileId
 * @param value
 * @param id
 */
commonAssets.cleanItemHTML = function (_this, fileId, value, id) {
    var target = $(_this).closest(".form-group");
    target.remove();
    var select = {};
    switch (value) {
        case '0': {
            select.tableName = AssessDBKey.AssetsCustomizeDataField;
            select.tableId = id;
            select.fieldsName = fileId;
        }
            break;
        case '1': {
            select.tableName = AssessDBKey.AssetsCustomizeDataField;
            select.tableId = id;
            select.fieldsName = fileId;
        }
            break;
        case '2': {
            select.tableName = AssessDBKey.AssetsCustomizeDataField;
            select.tableId = id;
            select.fieldsName = fileId;
        }
            break;
        default:
            break;
    }
    dataAssetsAppraisalDic.deleteAssetsCustomizeDataFieldById(id, null);
    var arr = Object.values(select);
    if (arr.length == 3) {
        commonAssets.getSysAttachmentDtoList(select, function (data) {
            var array = [];
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    array.push(data[i].id);
                }
            }
            if (array.length != 0) {
                commonAssets.deleteAttachmentById(array, function () {
                    toastr.success('清除附件成功!');
                });
            }
        });
    }
};


