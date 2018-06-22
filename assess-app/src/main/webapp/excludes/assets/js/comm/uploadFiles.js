/**
 * Created by Calvin on 2017/8/10.
 * 上传附件相关公共JS
 */


var uploadify_onSelectError = function (errorType, file) {
    var errorMsg = "";
    switch (errorType) {
        case '404_FILE_NOT_FOUND':
            errorMsg += '404 Error';
            break;
        case '403_FORBIDDEN':
            errorMsg += '403 Forbidden';
            break;
        case 'FORBIDDEN_FILE_TYPE':
            errorMsg += 'Forbidden File Type';
            break;
        case 'FILE_SIZE_LIMIT_EXCEEDED':
            errorMsg += "文件大小超过限制( 50MB )";
            break;
        case "QUEUE_LIMIT_EXCEEDED":
            errorMsg += "同时上传的文件不能超过5个";
            break;
        default:
            errorMsg += "错误代码：" + errorType + "\n" + errorMsg;
    }
    Alert("上传文件失败，失败原因：" + errorMsg, 1, null, null);
    $("#uploadifive-" + this[0].id + "-queue").hide();
};

/**
 * 上传附件
 * @param obj 显示附件列表的容器id
 * @param btnObj 上传中不可点击的按钮id
 * @param params 上传控件的相关参数设置
 * @param formData 上传提交的其他数据
 * @param fn 上传成功后的回调函数
 * @param editFlag 显示的附件数据列表是否可在线编辑
 */
function uploadFiles(obj, btnObj, params, formData, fn, editFlag) {
    var fileCheckResultArray = [];
    var defaluts = {
        multi: true,
        queueSizeLimit: 5,
        fileSizeLimit: "200MB",
        buttonText: "上传附件",
        auto: false,
        fileObjName: 'filesData',
        removeCompleted: true,
        uploadScript: getContextPath() + "/attachment/uploadFileToServer?_=" + Math.random(),
        overrideEvents: ['onError'],
        onError: uploadify_onSelectError,
        onAddQueueItem: function (file) {
            if (params.fileExtArray) {
                fileCheckResultArray.push(checkFileExt(file.name, params.fileExtArray));
            }
        },
        onSelect: function (queue) {
            var result = true;
            $.each(fileCheckResultArray, function (i, item) {
                if (item == false) {
                    result = false;
                    return false;
                }
            })
            fileCheckResultArray = [];
            if (result) {
                $('#' + obj).uploadifive('upload');
            } else {
                alert("上传文件的格式仅支持：" + params.fileExtArray.toString());
                $('#' + obj).uploadifive('clearQueue');
            }
        },
        onUpload: function (file) {//上传之前触发
            $("#" + obj).data('uploadifive').settings.formData = formData;   //动态更改formData的值
            if (btnObj) {
                $("#" + btnObj).disabled = true;//当开始上传文件，要防止上传未完成而表单被提交
            }
        },
        onUploadComplete: function (file, result) {
            if (btnObj) {
                $("#" + btnObj).disabled = false;//当开始上传文件，要防止上传未完成而表单被提交
            }
            var resultData = eval("(" + result + ")");
            if (resultData.ret) {
                if (params.showFileList == undefined || params.showFileList == true)
                    showFileList(obj, resultData.data.attachmentVos, "1", editFlag);
                fn(resultData.data.id);
            }
            else {
                Alert("上传文件失败，失败原因111111：" + result.errmsg, 1, null, null);
            }
        },
        onFallback: function () {
            Alert("浏览器不支持该上传附件控件，请更新浏览器!", 1, null, null);
        }
    };
    defaluts = $.extend({}, defaluts, params);
    $("#" + obj).uploadifive(defaluts);
}

/**
 * 显示附件列表
 * @param obj 显示附件列表的容器id
 * @param formData 查询附件的相关参数
 * @param deleteFlag 是否可删除状态
 * @constructor
 */
function GetFileShows(obj, formData, deleteFlag, editFlag) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/attachment/getFilesFromServer?_=" + Math.random(),
        data: formData,
        dataType: "json",
        success: function (result) {
            if (result.ret) {
                showFileList(obj, result.data, deleteFlag, editFlag);
            }
            else {
                Alert("读取文件列表失败，失败原因：" + result.errmsg, 1, null, null);
            }
        }
    });
}

/**
 * 删除附件
 * @param fileId 附件id
 * @param fn 删除成功的回调
 * @param onlyDeleteData 仅删除数据不删除原文件
 * @constructor
 */
function DeleteFile(fileId, fn, onlyDeleteData) {
    var url = getContextPath() + '/attachment/deleteFileToServer';
    if (onlyDeleteData == true)
        url = getContextPath() + '/attachment/deleteDataToServer';
    $.ajax({
        type: "Post",
        url: url,
        data: {id: fileId},
        dataType: "json",
        success: function (result) {
            if (result.ret) {
                $("#div_files_" + fileId).remove();
                if (fn && typeof fn == 'function') {
                    fn(fileId);
                }
            }
            else {
                Alert("删除文件失败，失败原因：" + result.errmsg, 1, null, null);
            }
        }
    });
}
/**
 * 显示附件列表
 * @param obj 显示文件列表的容器id
 * @param data 附件列表的数据
 * @param deleteFlag 是否可删除
 * @param editFlag 是否可在线编辑
 * @param fn 删除后的回调
 * @param onlyDeleteData 仅删除数据不删除原文件
 */
function showFileList(obj, data, deleteFlag, editFlag, fn, onlyDeleteData) {
    $("#_" + obj).empty();
    function extracted(strHtmlApproer, item) {
        if (item.hasKeep) {
            strHtmlApproer += "<i class='fa fa-folder' onclick='showAttachmentKeepList(" + item.id + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
        }
        if (editFlag) {
            if (typeof editFlag == 'function') {
                strHtmlApproer += "<i class='fa fa-edit' onclick='" + editFlag() + "(" + item.id + ",\"" + item.fileExtension + "\")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
            } else {
                strHtmlApproer += "<i class='fa fa-edit' onclick='editAttachment(" + item.id + ",\"" + item.fileExtension + "\")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
            }
        }
        return strHtmlApproer;
    }

    if (data) {
        $.each(data, function (index, item) {
            var strHtmlApproer = "";
            if (deleteFlag == "1" || deleteFlag == true) {
                strHtmlApproer = "<div id='div_files_" + item.id + "' class='alert alert-info'>";
                strHtmlApproer = extracted(strHtmlApproer, item);
                strHtmlApproer += "<i class='fa fa-download' onclick='downAttachments(" + item.id + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
                strHtmlApproer += "<button onclick='DeleteFile(" + item.id + "," + fn + "," + onlyDeleteData + ");return false' class='close'>";
                strHtmlApproer += "&times;";
                strHtmlApproer += "</button>";
                strHtmlApproer += "<a onclick='showAttachment(" + item.id + ",\"" + item.fileExtension + "\")' style='cursor: pointer;'>" + item.fileName + "</a>";
                strHtmlApproer += "</div>";
            }
            else {
                strHtmlApproer = "<div class='alert alert-info'>";
                strHtmlApproer = extracted(strHtmlApproer, item);
                strHtmlApproer += "<i class='fa fa-download' onclick='downAttachments(" + item.id + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
                strHtmlApproer += "<a onclick='showAttachment(" + item.id + ",\"" + item.fileExtension + "\")' style='cursor: pointer;'> " + item.fileName + "</a>";
                strHtmlApproer += "</div>";
            }
            $("#_" + obj).append(strHtmlApproer);
        });
    }
}


/**
 * 附件下载
 * @param fileId
 */
function downAttachments(attachmentId) {
    window.open(getContextPath() + "/attachment/downloadFileFromServer?id=" + attachmentId);
}
function downKeepAttachments(attachmentId) {
    window.open(getContextPath() + "/attachment/downloadKeepFileFromServer?id=" + attachmentId);
}

/**
 * 附件在线编辑
 * @param attachmentId
 */
function editAttachment(attachmentId, fileExtension) {
    switch (fileExtension) {
        case "doc":
        case "docx":
        case "xls":
        case "xlsx":
        case "ppt":
            $.ajax({
                type: "get",
                url: getContextPath() + "/zhuozheng/getEditFileUrl",
                data: {attachmentId: attachmentId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        window.location.href = result.data;
                    }
                }
            });
            break;
    }

}

/**
 * 附件预览
 * @param attachmentId
 */
function showAttachment(attachmentId, fileExtension) {
    if (fileExtension.indexOf(".") >= 0) {
        var fileExtensionArray = fileExtension.split(".");
        fileExtension = fileExtensionArray[fileExtensionArray.length - 1];
    }


    //如果是图片则直接预览
    switch (fileExtension) {
        case "jpg":
        case "jpeg":
        case "png":
        case "gif":
            $.ajax({
                type: "post",
                url: getContextPath() + "/attachment/getViewImageUrl",
                data: {id: attachmentId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        $('<img id="imgViewer" class="viewer" src="' + getContextPath() + result.data + '" alt="Picture">').appendTo("body");
                        var pictures = document.querySelector('.viewer');
                        var options = {
                            hidden: function () {
                                viewer.destroy();
                                $("#imgViewer").remove();
                            }
                        };
                        var viewer = new Viewer(pictures, options);
                        viewer.show();
                    }
                }
            });
            break;
        case "doc":
        case "docx":
        case "xls":
        case "xlsx":
        case "ppt":
        case "pdf":
            $.ajax({
                type: "get",
                url: getContextPath() + "/zhuozheng/getViewFileUrl",
                data: {attachmentId: attachmentId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        window.location.href = result.data;
                    }
                }
            });
            break;
    }
}

//显示附件存档列表
function showAttachmentKeepList(attachmentId) {
    var cols = [];
    cols.push({
        field: 'fileName', title: '附件名称', formatter: function (value, row, index) {
            return value;
        }
    });
    cols.push({
        field: 'created', title: '存档时间', formatter: function (value, row, index) {
            return formatDate(value, true);
        }
    });
    cols.push({field: 'owner', title: '版本拥有者'});
    cols.push({
        field: 'opt', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-teal" href="javascript:downKeepAttachments(\'' + row.id + '\')"><i class="fa fa-download"></i>下载</a>';
            str += '</div>';
            return str;
        }
    });
    $("#attachmentKeepList").bootstrapTable('destroy');
    TableInit("attachmentKeepList", getContextPath() + "/attachment/getAttachmentKeepList", cols, {
            attachmentId: attachmentId
        },
        {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('#attachmentKeepModal').modal({backdrop: 'static', keyboard: false});
            }
        });
}
