/**
 * Created by kings on 2018-12-20.
 */
var declareCommon = {};

declareCommon.fileArray = ["project_proxy"];


declareCommon.declareApplyForm = $('#declareApplyForm');

declareCommon.getPlanDetailsId = function () {
    return declareCommon.declareApplyForm.find('[name=planDetailsId]').val();
};
declareCommon.getProjectId = function () {
    return declareCommon.declareApplyForm.find('[name=projectId]').val();
};

declareCommon.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};


declareCommon.fileUpload = function (target, tableName, id, deleteFlag, fieldsName) {
    declareCommon.fileUpload2(target, tableName, id, deleteFlag, true, fieldsName);
};

declareCommon.fileUpload2 = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
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

declareCommon.showFile = function (target, tableName, id, deleteFlag, fieldsName) {
    declareCommon.showFile2(target, tableName, id, deleteFlag, true, fieldsName);
};

declareCommon.showFile2 = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        deleteFlag: deleteFlag,
        editFlag: editFlag
    })
};


/**
 * 删除FTP附件
 * @param array
 * @param callback
 */
declareCommon.deleteAttachmentById = function (array, callback) {
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
declareCommon.getSysAttachmentDtoList = function (data, callback) {
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
declareCommon.batchUpdateSysAttachmentDto = function (data, callback) {
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

/**
 * 获取配置项
 * @param value
 * @param callback
 */
declareCommon.getServerDeclaration = function (value, callback) {
    var data = {setting: true, pid: declareCommon.declareApplyForm.find('[name=assetsSettingId]').val()};
    dataAssetsAppraisalDic.getDataAssetsAppraisalDicList(data, function (item) {
        if (callback) {
            var arr = [];
            $.each(item, function (i, obj) {
                if (value == 2) {
                    if (obj.fileId) {
                        arr.push({
                            name: obj.name,
                            projectId: declareCommon.getProjectId(),
                            planDetailsId: declareCommon.getPlanDetailsId(),
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
                            projectId: declareCommon.getProjectId(),
                            planDetailsId: declareCommon.getPlanDetailsId(),
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
declareCommon.writeDeclarationHtml = function (value) {
    var target = undefined;
    if (value == '2') {
        declareCommon.getServerDeclaration(value, function (data) {
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
                html = html.replace(/{method}/g, 'declareCommon');
                target.append(html);
                var select = {};
                select.tableName = AssessDBKey.AssetsCustomizeDataField;
                select.tableId = item.id;
                declareCommon.getSysAttachmentDtoList(select, function (obj) {
                    $.each(obj, function (i, n) {
                        n.fieldsName = fileId;
                    });
                    declareCommon.batchUpdateSysAttachmentDto(obj, function () {
                        declareCommon.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, false, true, fileId);
                        declareCommon.fileUpload2(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, false, true, fileId);
                    })
                });
            });
        });
    }
    if (value == '1') {
        declareCommon.getServerDeclaration(value, function (data) {
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
                html = html.replace(/{method}/g, 'declareCommon');
                target.append(html);
                declareCommon.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
                declareCommon.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
            });
        });
    }
    if (value == '0') {
        var item = {
            projectId: declareCommon.getProjectId(),
            planDetailsId: declareCommon.getPlanDetailsId(),
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
                html = html.replace(/{method}/g, 'declareCommon');
                target.append(html);
                declareCommon.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
                declareCommon.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, item.id, true, fileId);
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
declareCommon.cleanItemHTML = function (_this, fileId, value, id) {
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
        declareCommon.getSysAttachmentDtoList(select, function (data) {
            var array = [];
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    array.push(data[i].id);
                }
            }
            if (array.length != 0) {
                declareCommon.deleteAttachmentById(array, function () {
                    toastr.success('清除附件成功!');
                });
            }
        });
    }
};


