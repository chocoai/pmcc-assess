/**
 * Created by kings on 2018-1-24.
 */
(function () {
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

    var canEdit = function (fileExtension) {
        if (!fileExtension) return false;
        switch (fileExtension) {
            case "doc":
            case "docx":
            case "xls":
            case "xlsx":
            case "ppt":
                return true;
        }
        return false;
    }

    var fileUtils = {
        //附件上传
        uploadFiles: function (options, uploadifiveOptions) {
            var defalutsExtend = {
                target: undefined,
                disabledTarget: undefined,
                fileExtArray: undefined,
                showFileList: true,
                onlyDeleteData: false,
                editFlag: false,
                formData: {},
                onUpload:function () {

                },
                success: function (attachmentId) {

                }
            };
            defalutsExtend = $.extend({}, defalutsExtend, options);

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
                    if (defalutsExtend.fileExtArray) {
                        fileCheckResultArray.push(checkFileExt(file.name, defalutsExtend.fileExtArray));
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
                        $("#" + defalutsExtend.target).uploadifive('upload');
                    } else {
                        alert("上传文件的格式仅支持：" + defalutsExtend.fileExtArray.toString());
                        $("#" + defalutsExtend.target).uploadifive('clearQueue');
                    }
                },
                onUpload: function (file) {//上传之前触发
                    if(defalutsExtend.onUpload){
                        defalutsExtend.onUpload(defalutsExtend.formData,file);
                    }

                    $("#" + defalutsExtend.target).data('uploadifive').settings.formData = defalutsExtend.formData;   //动态更改formData的值
                    if (defalutsExtend.disabledTarget) {
                        $("#" + defalutsExtend.disabledTarget).disabled = true;//当开始上传文件，要防止上传未完成而表单被提交
                    }
                },
                onUploadComplete: function (file, result) {
                    if (defalutsExtend.disabledTarget) {
                        $("#" + defalutsExtend.disabledTarget).disabled = true;//当开始上传文件，要防止上传未完成而表单被提交
                    }
                    var resultData = eval("(" + result + ")");
                    if (resultData.ret) {
                        if (defalutsExtend.showFileList == undefined || defalutsExtend.showFileList == true) {
                            fileUtils.getFileShows({
                                target: defalutsExtend.target,
                                onlyDeleteData: defalutsExtend.onlyDeleteData,
                                formData:defalutsExtend.formData
                            });
                        }
                        defalutsExtend.success(resultData.data.id);
                    }
                    else {
                        Alert(result.errmsg, 1, null, null);
                    }
                },
                onFallback: function () {
                    Alert("浏览器不支持该上传附件控件，请更新浏览器!", 1, null, null);
                }
            };
            defaluts = $.extend({}, defaluts, uploadifiveOptions);
            $("#" + defalutsExtend.target).uploadifive(defaluts);
        },
        //获取附件数据信息
        getFileShows: function (options) {
            var defaluts = {
                target: undefined,
                deleteFlag: true,
                editFlag: false,
                onlyDeleteData: false,
                formData: {}
            };
            defaluts = $.extend({}, defaluts, options);
            $.ajax({
                type: "get",
                url: getContextPath() + "/attachment/getFilesFromServer?_=" + Math.random(),
                data: defaluts.formData,
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        fileUtils.showFileList({
                            target: defaluts.target,
                            data: result.data,
                            deleteFlag: defaluts.deleteFlag,
                            onlyDeleteData: defaluts.onlyDeleteData,
                            editFlag: defaluts.editFlag
                        });
                    }
                    else {
                        Alert("读取文件列表失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                }
            });
        },
        //删除附件
        deleteFile: function (options) {
            var defaluts = {
                attachmentId: undefined,
                onlyDeleteData: false,
                success: function (attachmentId) {
                }
            };
            defaluts = $.extend({}, defaluts, options);
            var url = getContextPath() + '/attachment/deleteFileToServer';
            if (defaluts.onlyDeleteData == true) {
                url = getContextPath() + '/attachment/deleteDataToServer';
            }
            $.ajax({
                type: "Post",
                url: url,
                data: {id: defaluts.attachmentId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        $("#div_files_" + defaluts.attachmentId).remove();
                        defaluts.success(defaluts.attachmentId);
                    }
                    else {
                        Alert("删除文件失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                }
            });
        },
        //显示附件列表
        showFileList: function (options) {
            var defaluts = {
                target: undefined,
                deleteFlag: true,
                editFlag: false,
                data: {},
                onlyDeleteData: false,
                deleteSuccess: function (attachmentId) {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            $("#_" + defaluts.target).empty();
            if (defaluts.data) {
                $.each(defaluts.data, function (index, item) {
                    var strHtmlApproer = "<div id='div_files_" + item.id + "' class='alert alert-info'>";
                    if (item.hasKeep) {//在线编辑历史记录
                        strHtmlApproer += "<i class='fa fa-folder' onclick='FileUtils.showAttachmentKeepList(" + item.id + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
                    }
                    strHtmlApproer += "<i class='fa fa-download' onclick='FileUtils.downAttachments(" + item.id + ")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
                    if ((defaluts.editFlag == "1" || defaluts.editFlag == true) && canEdit(item.fileExtension)) {
                        strHtmlApproer += "<i class='fa fa-edit' onclick='FileUtils.editAttachment(" + item.id + ",\"" + item.fileExtension + "\")'  style='margin-right: 10px;font-size: 15px;cursor: pointer;'></i>";
                    }
                    if (defaluts.deleteFlag == "1" || defaluts.deleteFlag == true) {
                        strHtmlApproer += "<button onclick='FileUtils.deleteFile({attachmentId:" + item.id + ",onlyDeleteData:" + defaluts.onlyDeleteData + ",success:" + defaluts.deleteSuccess + "});return false' class='close'>";
                        strHtmlApproer += "&times;";
                        strHtmlApproer += "</button>";
                    }
                    strHtmlApproer += "<a onclick='FileUtils.showAttachment(" + item.id + ",\"" + item.fileExtension + "\")' style='cursor: pointer;'> " + item.fileName + "</a>";
                    strHtmlApproer += "</div>";
                    $("#_" + defaluts.target).append(strHtmlApproer);
                });
            }
        },
        //附件下载
        downAttachments: function (attachmentId) {
            window.open(getContextPath() + "/attachment/downloadFileFromServer?id=" + attachmentId);
        },
        //存档附件下载
        downKeepAttachments: function (attachmentId) {
            window.open(getContextPath() + "/attachment/downloadKeepFileFromServer?id=" + attachmentId);
        },
        //附件在线编辑
        editAttachment: function (attachmentId, fileExtension) {
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
        },
        //附件在线查看
        showAttachment: function (attachmentId, fileExtension) {
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
        },
        //显示存档附件数据
        showAttachmentKeepList: function (attachmentId) {
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
                    str += '<a class="btn btn-xs btn-teal" onclick="FileUtils.downKeepAttachments(\'' + row.id + '\')" href="javascript://"><i class="fa fa-download"></i>下载</a>';
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
    };
    window.FileUtils = fileUtils;
})(jQuery)
