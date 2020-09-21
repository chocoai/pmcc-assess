/**
 * Created by kings on 2018-5-21.
 */
$(function () {
    try {
        $(".select2").each(function () {
            $(this).select2({minimumResultsForSearch: -1});
        })
    } catch (e) {
    }

    //百分比初始化
    $(".x-percent").each(function () {
        AssessCommon.elementParsePercent($(this));
    })

    //输入时自动添加百分比
    $(document).on('keyup', '.x-percent', function () {
        var val = $(this).val();
        if (val) {
            var numberVal;
            if (/%$/.test(val)) {
                numberVal = val.replace(/%$/g, '');
            } else if (/%/.test(val)) {
                $(this).val(val.replace(/%/g, '') + "%");
            } else {
                numberVal = val;
                $(this).val(val + "%");
            }
            $(this).attr('data-value', (parseFloat(numberVal) / 100).toFixed(4));
            if (AssessCommon.getCursorPosition($(this)) == $(this).val().length) {
                AssessCommon.moveCursor($(this), $(this).val().length - 1);
            }
        }
    })

});

(function ($) {

    //ztree获取父节点by key值
    function getParentNodeByKey(node, key) {
        if (node) {
            if (node.key == key) {
                return node.getParentNode();
            } else {
                getParentNodeByKey(node.getParentNode(), key);
            }
        }
    }

    var assessCommon = {
        //iframe的宽高自适应
        autoIframeHeight: function (iframe) {
            if (iframe) {
                var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                if (iframeWin.document.body) {
                    iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                }
            }
        },
        //获取光标位置
        getCursorPosition: function (element) {
            var el = $(element).get(0);
            var pos = 0;
            if ('selectionStart' in el) {
                pos = el.selectionStart;
            } else if ('selection' in document) {
                el.focus();
                var Sel = document.selection.createRange();
                var SelLength = document.selection.createRange().text.length;
                Sel.moveStart('character', -el.value.length);
                pos = Sel.text.length - SelLength;
            }
            return pos;
        },
        //移动光标
        moveCursor: function (element, position) {
            var txtFocus = $(element).get(0);
            if ($.browser && $.browser.msie) {
                var range = txtFocus.createTextRange();
                range.move("character", position);
                range.select();
            } else {
                txtFocus.setSelectionRange(position, position);
                txtFocus.focus();
            }
        },

        //对象不存在则返回空串
        toString: function (o) {
            if (o == null || o == undefined) return "";
            return o;
        },

        //判断数据是否为数字
        isNumber: function (val) {
            if (val === '' || val == undefined || val == null) return false;
            if (isNaN(Number(val))) return false;
            val = Number(val);
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if (regPos.test(val) || regNeg.test(val)) {
                return true;
            } else {
                return false;
            }
        },
        //判断是否为图片(图像)
        checkImgFile: function (filename) {
            var flag = false; //状态
            //常用图片后缀
            var arr = ["bmp", "dib", "rle", "emf", "gif", "jpg", "jpeg", "jpe", "jif", "pcx", "dcx", "pic", "png", "tga", "tif", "tiffxif", "wmf", "jfif"];
            if (filename) {
                //取出上传文件的扩展名
                var index = filename.lastIndexOf(".");
                var ext = filename.substr(index + 1);
                //转换为小写比较
                ext = ext.toLocaleLowerCase();
                //循环比较
                for (var i = 0; i < arr.length; i++) {
                    if (ext == arr[i]) {
                        flag = true; //一旦找到合适的，立即退出循环
                        break;
                    }
                }
            }
            return flag;
        },
        //百分数转小数
        percentToPoint: function (value) {
            if (value) {
                var value = value.replace("%", "");
                if (AssessCommon.isNumber(value)) {
                    return (parseFloat(value) / 100).toFixed(4);
                }
            }
        },
        //小数转百分比
        pointToPercent: function (value) {
            if (value != undefined && AssessCommon.isNumber(value)) {
                return (parseFloat(value) * 100).toFixed(2) + "%";
            }
        },
        //百分数转小数
        elementParsePoint: function (element) {
            var val = $(element).val();
            var pointVal = AssessCommon.percentToPoint(val);
            if (pointVal != undefined || pointVal != null) {
                $(element).attr('data-value', pointVal);
            }
        },
        //
        elementParsePercent: function (element) {
            var val = $(element).attr('data-value');
            var percentVal = AssessCommon.pointToPercent(val);
            if (percentVal != undefined || percentVal != null) {
                $(element).val(percentVal);
            }
        },

        //提取字段
        extractField: function (text) {
            if (!text) return text;
            var regex = /({.*?})/g;
            var group = text.match(regex);
            var resultArray = [];
            if (group && group.length > 0) {
                $.each(group, function (i, item) {
                    item = item.replace(/^{|}$/g, "");
                    if ($.inArray(item, resultArray) < 0) {
                        resultArray.push(item);
                    }
                })
            }
            return resultArray;
        },

        //替换模板数据
        replaceTemplate: function (text, temp, value) {
            if (!text) return text;
            var regex = '/({' + temp + '})/g';
            return text.replace(eval(regex), value);
        },
        //获取项目分类详细信息
        getProjectClassifyInfo: function (id, callback) {
            if (id) {
                $.ajax({
                    url: getContextPath() + "/baseProjectClassify/getProjectClassifyInfo",
                    type: "get",
                    dataType: "json",
                    data: {
                        id: id
                    },
                    success: function (result) {
                        if (result.ret) {
                            if (callback) {
                                callback(result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        //获取项目分类详细信息 非异步
        getProjectClassifyInfoAsync: function (id, callback) {
            if (id) {
                $.ajax({
                    url: getContextPath() + "/baseProjectClassify/getProjectClassifyInfo",
                    type: "get",
                    dataType: "json",
                    async: false,
                    data: {
                        id: id
                    },
                    success: function (result) {
                        if (result.ret) {
                            if (callback) {
                                callback(result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        loadProjectClassifyListByField: function (fieldName, value, callback) {
            if (fieldName) {
                $.ajax({
                    url: getContextPath() + "/baseProjectClassify/getProjectClassifyListByFieldName",
                    type: "get",
                    dataType: "json",
                    data: {
                        fieldName: fieldName
                    },
                    success: function (result) {
                        if (result.ret) {
                            var retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += '<option key="' + item.name + '" title="' + item.name + '" value="' + item.id + '"';
                                if (item.id == value) {
                                    retHtml += 'selected="selected"';
                                }
                                retHtml += '>' + item.name + '</option>';
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        //根据项目字段获取项目类别
        getProjectClassifyListByFieldName: function (fieldName, callback) {
            this.loadProjectClassifyListByField(fieldName, null, callback);
        },

        //根据项目类型获取项目类别
        getProjectClassifyList: function (typeId, callback) {
            if (typeId) {
                $.ajax({
                    url: getContextPath() + "/baseProjectClassify/getCacheProjectClassifyListByPid",
                    type: "get",
                    dataType: "json",
                    data: {
                        pid: typeId
                    },
                    success: function (result) {
                        if (result.ret) {
                            var retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },

        //找到key值对应的ztree父节点
        getParentNodeByKey: function (node, key) {
            return getParentNodeByKey(node, key);
        },

        //根据id获取单个数据字典信息
        getDataDicInfo: function (id, callback) {
            AssessCommon.getDataDicInfoAsync(id, callback, true);
        },
        getDataDicInfoAsync: function (id, callback, async) {
            if (id == undefined) return null;
            $.ajax({
                url: getContextPath() + '/baseDataDic/getDataDicInfo',
                type: 'get',
                async: async,
                data: {id: id},
                dataType: 'json',
                success: function (result) {
                    if (result.ret && callback) {
                        callback(result.data);
                    }
                }
            })
        },
        //根据pid获取字典信息
        loadDataDicByPid: function (pid, value, callback) {
            if (pid) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getCacheDataDicListByPid",
                    type: "get",
                    dataType: "json",
                    data: {
                        pid: pid
                    },
                    success: function (result) {
                        if (result.ret) {
                            var retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += '<option key="' + item.fieldName + '" title="' + item.remark + '" value="' + item.id + '"'
                                if (item.id == value) {
                                    retHtml += 'selected="selected"'
                                }
                                retHtml += '>' + item.name + '</option>'
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }

                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        //单个select2赋值
        initSelect2: function (frm, data, name) {
            if (data) {
                if (name) {
                    var value = data[name];
                    if (value) {
                        var select = frm.find("select[name=" + name + "]");
                        select.val(value).trigger('change');
                    }
                }
            }
        },
        //多个select2赋值
        initArraySelect2: function(frm,data,arrName){
            if (data){
                if (arrName){
                    var that = this;
                    $.each(arrName,function (k,name) {
                        that.initSelect2(frm,data,name) ;
                    }) ;
                }
            }
        } ,
        //数据字典  select2赋值
        loadDataDicAssignment: function (key, obj, name, frm) {
            var value = null;
            if (obj) {
                value = obj[name];
            }
            var target = frm.find("[name=" + name + "]");
            if (target.size() == 0) {
                target = frm.find("select." + name);
            }
            this.loadDataDicByKey(key, value, function (html, data) {
                target.empty().html(html).trigger('change');
                if (value) {
                    target.val(value).trigger('change');
                }
            });
        },
        //根据key获取字典信息
        loadDataDicByKey: function (key, value, callback, initHtml) {
            this.loadNewAsyncDataDicByKey(key, value, callback, true, initHtml);
        },
        //根据key获取字典信息
        loadAsyncDataDicByKey: function (key, value, callback, async) {
            this.loadNewAsyncDataDicByKey(key, value, callback, async, true);
        },
        //根据key获取字典信息
        loadNewAsyncDataDicByKey: function (key, value, callback, async, initHtml) {
            if (key) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getDataDicListByFieldName",
                    type: "get",
                    dataType: "json",
                    async: async,
                    data: {
                        fieldName: key
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
                            var retHtml = '';
                            if (initHtml == undefined || initHtml) {
                                retHtml += '<option value="" selected>-请选择-</option>';
                            }
                            $.each(result.data, function (i, item) {
                                retHtml += '<option key="' + item.fieldName + '" title="' + item.remark + '" value="' + item.id + '"'
                                if (item.id == value) {
                                    retHtml += 'selected="selected"'
                                }
                                retHtml += '>' + item.name + '</option>'
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }

                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },

        //获取区域单个信息
        getAreaById: function (target, callback) {
            $.ajax({
                url: getContextPath() + "/public/getAreaById",
                type: "get",
                data: {id: target},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        //根据pid获取区域信息
        loadAreaInfoByPid: function (pid, callback) {
            AssessCommon.loadAreaAsyncInfoByPid(pid, callback, true);
        },
        loadAreaAsyncInfoByPid: function (pid, callback, async) {
            if (pid) {
                $.ajax({
                    url: getContextPath() + "/area/getAreaList",
                    type: "get",
                    dataType: "json",
                    async: async,
                    data: {
                        pid: pid
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
                            var retHtml = '<option value="" >-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += ' <option value="' + item.areaId + '">' + item.name + '</option>';
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        loadAreaAsyncInfoByPidAndValue: function (pid, value, callback, async) {
            if (pid) {
                $.ajax({
                    url: getContextPath() + "/area/getAreaList",
                    type: "get",
                    dataType: "json",
                    async: async,
                    data: {
                        pid: pid
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
                            var retHtml = '<option value="" >-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += '<option value="' + item.areaId + '"'
                                if (item.areaId == value) {
                                    retHtml += 'selected="selected"'
                                }
                                retHtml += '>' + item.name + '</option>'
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        //初始化区域信息
        initAreaInfo: function (options) {
            assessCommon.initAsyncAreaInfo(options, true);
        },

        initAsyncAreaInfo: function (options, async) {
            var isProvinceFirstChange = true;
            var isCityFirstChange = true;
            var defaults = {
                useDefaultText: false,
                provinceTarget: undefined,
                cityTarget: undefined,
                districtTarget: undefined,
                provinceValue: undefined,
                cityValue: undefined,
                districtValue: undefined,
                provinceDefaultText: '四川',
                cityDefaultText: '成都',
                districtDefaultText: undefined,
                success: function () {

                }
            };
            defaults = $.extend({}, defaults, options);
            if ($.type(defaults.provinceTarget) === "string") {
                defaults.provinceTarget = $("#" + defaults.provinceTarget);
            } else {
                defaults.provinceTarget = $(defaults.provinceTarget);
            }

            if ($.type(defaults.cityTarget) === "string") {
                defaults.cityTarget = $("#" + defaults.cityTarget);
            } else {
                defaults.cityTarget = $(defaults.cityTarget);
            }
            if ($.type(defaults.districtTarget) === "string") {
                defaults.districtTarget = $("#" + defaults.districtTarget);
            } else {
                defaults.districtTarget = $(defaults.districtTarget);
            }
            //省切换
            defaults.provinceTarget.unbind('change').bind('change', function () {
                isProvinceFirstChange = false;
                defaults.cityTarget.select2('val', '').empty();
                if (defaults.districtTarget) {
                    defaults.districtTarget.select2('val', '').empty();
                }
                //加载市
                AssessCommon.loadAreaAsyncInfoByPid($(this).val(), function (html) {
                    defaults.cityTarget.append(html);
                    //初始化设置默认选中项
                    if (!defaults.cityValue && isCityFirstChange && defaults.useDefaultText && defaults.cityDefaultText) {
                        defaults.cityTarget.select2('val', defaults.cityTarget.find("option:contains(" + defaults.cityDefaultText + ")").val()).trigger('change');
                    } else {
                        if (defaults.cityValue) {
                            defaults.cityTarget.select2('val', defaults.cityValue).trigger('change');
                        }
                    }
                }, async);
            });
            //有区域元素才做处理
            if (defaults.districtTarget) {
                if ($.type(defaults.districtTarget) === "string") {
                    defaults.districtTarget = $("#" + defaults.districtTarget);
                }
                //市切换
                defaults.cityTarget.unbind('change').bind('change', function () {
                    isCityFirstChange = false;
                    defaults.districtTarget.select2('val', '').empty();
                    //加载区县
                    AssessCommon.loadAreaAsyncInfoByPid($(this).val(), function (html) {
                        defaults.districtTarget.append(html);
                        if (defaults.districtValue) {
                            defaults.districtTarget.select2('val', defaults.districtValue);
                        }
                    }, async);
                })
            }
            defaults.provinceTarget.select2('val', '').empty();
            //获取省数据
            $.ajax({
                url: getContextPath() + "/area/getProvinceList",
                type: "post",
                dataType: "json",
                async: async,
                data: {},
                success: function (result) {
                    if (result.ret && result.data) {
                        defaults.provinceTarget.append("<option value=''>-请选择-</option>");
                        $.each(result.data, function (i, item) {
                            defaults.provinceTarget.append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        });

                        if (defaults.provinceValue) {
                            defaults.provinceTarget.select2('val', defaults.provinceValue).trigger('change');
                        } else if (!defaults.provinceValue && isProvinceFirstChange && defaults.useDefaultText && defaults.provinceDefaultText) {
                            //初始化设置默认选中项
                            defaults.provinceTarget.select2('val', defaults.provinceTarget.find("option:contains(" + defaults.provinceDefaultText + ")").val()).trigger('change');
                        }

                        if (defaults.success) {
                            defaults.success();
                        }
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        //设置checkbox为选中状态
        checkboxToChecked: function (checkboxs, valueArray) {
            if (!valueArray || valueArray.length <= 0) return;
            $(checkboxs).each(function () {
                var that = $(this);
                $.each(valueArray, function (i, value) {
                    if (value && that.val() == value) {
                        that.prop('checked', true);
                    }
                })
            })
        },
        //获取用户部门信息
        getUserDepartmentInfo: function (userAccount, callback) {
            $.ajax({
                url: getContextPath() + "/RpcErpService/getDepartmentByUserAccount",
                type: "get",
                data: {userAccount: userAccount},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        //获取附件信息
        getSysAttachmentDto: function (target, callback) {
            $.ajax({
                url: getContextPath() + "/public/getSysAttachmentDto",
                type: "get",
                data: {attachmentId: target},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        getSysAttachmentDtoList: function (data, callback) {
            $.ajax({
                url: getContextPath() + "/public/getSysAttachmentDtoList",
                type: "get",
                data: data,
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        deleteAttachmentById: function (id, callback) {
            $.ajax({
                url: getContextPath() + "/public/deleteAttachmentById",
                type: "post",
                data: {id: id},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        //新增或者更新附件
        saveAndUpdateSysAttachmentDto: function (item, callback) {
            $.ajax({
                url: getContextPath() + "/public/saveAndUpdateSysAttachmentDto",
                type: "POST",
                data: {formData: JSON.stringify(item)},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        //下载附件模板
        downloadFileTemplate: function (key) {
            $.ajax({
                url: getContextPath() + "/baseFileTemplate/getAttachmentId",
                type: "get",
                data: {name: key},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        window.open(FileUtils.config.downloadUrl + result.data);
                    }
                }
            })
        },
        getSysAttachmentViewHtml: function (item, callback) {
            $.ajax({
                url: getContextPath() + "/public/getSysAttachmentViewHtml",
                type: "get",
                data: {attachmentId: item},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },
        //下载ftp附件到本地
        downloadFtpFileToLocal: function (item, callback) {
            $.ajax({
                url: getContextPath() + "/public/downloadFtpFileToLocal",
                type: "get",
                data: {attachmentId: item},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            })
        },

        //获取区域全名
        getAreaFullName: function (provinceName, cityName, districtName) {
            var fullName = provinceName;
            if (cityName) {
                fullName += cityName;
            }
            if (districtName) {
                fullName += districtName;
            }
            return fullName;
        },
        //阿里云图片识别
        parseRealtyHouseCert: function (id, key, callback) {
            $.ajax({
                url: "/aliocr/aliOcr",
                data: {
                    fileId: id,
                    method: key,
                    appKey: "pmcc-erp",
                    describe: "返回内容"
                },
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    }
                }
            });
        },
        //阿里云图片识别
        parseRealtyHouseCertNew: function (id, key, callback) {
            $.ajax({
                url: "/aliocr/aliOcr",
                data: {
                    fileId: id,
                    // method: key,
                    method: "houseCert",
                    appKey: "pmcc-assess",
                    describe: "识别房产证"
                },
                type: "get",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    } else {
                        console.log(result);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        },

        //关闭流程
        closeProcess: function (processInsId, callback) {
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/public/closeProcess",
                type: "post",
                dataType: "json",
                data: {processInsId: processInsId},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        if (callback)
                            callback();
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            });
        },

        //字符串截取
        substring: function (str, length) {
            if (!str) return "";
            if (length >= str.length) return str;
            return str.substring(0, length) + "...";
        },

        /**
         * 根据省份名称返回省份简称
         * @param province
         * @returns {String}
         */
        provinceForShort: function (province) {
            if (province == "北京")
                return "京";
            else if (province == "天津")
                return "津";
            else if (province == "重庆")
                return "渝";
            else if (province == "上海")
                return "沪";
            else if (province == "河北")
                return "冀";
            else if (province == "山西")
                return "晋";
            else if (province == "辽宁")
                return "辽";
            else if (province == "吉林")
                return "吉";
            else if (province == "黑龙江")
                return "黑";
            else if (province == "江苏")
                return "苏";
            else if (province == "浙江")
                return "浙";
            else if (province == "安徽")
                return "皖";
            else if (province == "福建")
                return "闽";
            else if (province == "江西")
                return "赣";
            else if (province == "山东")
                return "鲁";
            else if (province == "河南")
                return "豫";
            else if (province == "湖北")
                return "鄂";
            else if (province == "湖南")
                return "湘";
            else if (province == "广东")
                return "粤";
            else if (province == "海南")
                return "琼";
            else if (province == "四川")
                return "川";
            else if (province == "贵州")
                return "黔";
            else if (province == "云南")
                return "云";
            else if (province == "陕西")
                return "陕";
            else if (province == "甘肃")
                return "甘";
            else if (province == "青海")
                return "青";
            else if (province == "台湾")
                return "台";
            else if (province == "内蒙古")
                return "内";
            else if (province == "广西")
                return "桂";
            else if (province == "宁夏")
                return "宁";
            else if (province == "新疆")
                return "新";
            else if (province == "西藏")
                return "藏";
            else if (province == "香港")
                return "港";
            else if (province == "澳门")
                return "澳";
            else
                return "";
        },

        //獲取不包含文件名的名稱
        getFileName: function (fileName) {
            if (!fileName) return "";
            return fileName.substring(0, fileName.lastIndexOf("."));
        },
        //获取DataList字典信息
        loadDataListHtml: function (key, value, callback, async) {
            if (key) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getDataDicListByFieldName",
                    type: "get",
                    dataType: "json",
                    async: async,
                    data: {
                        fieldName: key
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
                            var retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += '<option value="' + item.name + '"'
                                if (item.name == value) {
                                    retHtml += 'selected="selected"'
                                }
                                retHtml += '>' + item.name + '</option>'
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }

                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        //通过fieldName与name获取子集
        getSonDataList: function (fieldName, name, value, callback) {
            $.ajax({
                url: getContextPath() + "/baseDataDic/getDataDicByName",
                type: "get",
                dataType: "json",
                data: {
                    fieldName: fieldName,
                    name: name
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            AssessCommon.loadSonDataListHtml(result.data.id, value, callback);
                        }
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });

        },
        loadSonDataListHtml: function (pid, value, callback) {
            if (pid) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getCacheDataDicListByPid",
                    type: "get",
                    dataType: "json",
                    data: {
                        pid: pid
                    },
                    success: function (result) {
                        if (result.ret) {
                            var retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                retHtml += '<option value="' + item.name + '"'
                                if (item.name == value) {
                                    retHtml += 'selected="selected"'
                                }
                                retHtml += '>' + item.name + '</option>'
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }

                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        prompt: function (val, callback) {
            layer.prompt({
                formType: 2,
                value: val,
                title: '请输入',
                area: ['300px', '100px'] //自定义文本域宽高
            }, function (value, index, elem) {
                if (callback) {
                    callback(value);
                }
                layer.close(index);
            });
        },
        //获取文本带下拉框字典信息
        loadTextAppendDicHtml: function (key, value, callback, async) {
            if (key) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getDataDicListByFieldName",
                    type: "get",
                    dataType: "json",
                    async: async,
                    data: {
                        fieldName: key
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
                            var retHtml = '';
                            $.each(result.data, function (i, item) {
                                retHtml += '<a class="dropdown-item" onclick="AssessCommon.setValueBySelect(this)">' + item.name + '</a>';
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        },
        setValueBySelect: function (_this) {
            $(_this).closest(".input-group").find("input").val($(_this).text()).trigger('change');
        },
        getSonTextAppendDicList: function (fieldName, name, value, callback) {
            $.ajax({
                url: getContextPath() + "/baseDataDic/getDataDicByName",
                type: "get",
                dataType: "json",
                data: {
                    fieldName: fieldName,
                    name: name
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            AssessCommon.loadSonTextAppendDicHtml(result.data.id, value, callback);
                        }
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });

        },
        loadSonTextAppendDicHtml: function (pid, value, callback) {
            if (pid) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getCacheDataDicListByPid",
                    type: "get",
                    dataType: "json",
                    data: {
                        pid: pid
                    },
                    success: function (result) {
                        if (result.ret) {
                            var retHtml = '';
                            $.each(result.data, function (i, item) {
                                retHtml += '<a class="dropdown-item" onclick="AssessCommon.setValueBySelect(this)">' + item.name + '</a>';
                            });
                            retHtml += '</div>';
                            if (callback) {
                                callback(retHtml, result.data);
                            }

                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        }
    };

    assessCommon.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: getContextPath() + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                    if (errorCallback) {
                        errorCallback();
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };
    assessCommon.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                assessCommon.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            assessCommon.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    assessCommon.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        assessCommon.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };

    window.AssessCommon = assessCommon;
    //一亩=666.67
    window.AssessCommon.BHOU = 666.67;
})(jQuery);

//数字运算-避免精度问题
//加
var accAdd = function (num1, num2) {
    num1 = Number(num1);
    num2 = Number(num2);
    var dec1, dec2, times;
    try {
        dec1 = countDecimals(num1) + 1;
    } catch (e) {
        dec1 = 0;
    }
    try {
        dec2 = countDecimals(num2) + 1;
    } catch (e) {
        dec2 = 0;
    }
    times = Math.pow(10, Math.max(dec1, dec2));
    // var result = (num1 * times + num2 * times) / times;
    var result = (accMul(num1, times) + accMul(num2, times)) / times;
    return getCorrectResult("add", num1, num2, result);
    // return result;
};
//减
var accSub = function (num1, num2) {
    num1 = Number(num1);
    num2 = Number(num2);
    var dec1, dec2, times;
    try {
        dec1 = countDecimals(num1) + 1;
    } catch (e) {
        dec1 = 0;
    }
    try {
        dec2 = countDecimals(num2) + 1;
    } catch (e) {
        dec2 = 0;
    }
    times = Math.pow(10, Math.max(dec1, dec2));
    // var result = Number(((num1 * times - num2 * times) / times);
    var result = Number((accMul(num1, times) - accMul(num2, times)) / times);
    return getCorrectResult("sub", num1, num2, result);
    // return result;
};
//乘
var accMul = function (num1, num2) {
    num1 = Number(num1);
    num2 = Number(num2);
    var times = 0, s1 = num1.toString(), s2 = num2.toString();
    try {
        times += countDecimals(s1);
    } catch (e) {
    }
    try {
        times += countDecimals(s2);
    } catch (e) {
    }
    var result = convertToInt(s1) * convertToInt(s2) / Math.pow(10, times);
    return getCorrectResult("mul", num1, num2, result);
    // return result;
};
//除
var accDiv = function (num1, num2) {
    num1 = Number(num1);
    num2 = Number(num2);
    var t1 = 0, t2 = 0, dec1, dec2;
    try {
        t1 = countDecimals(num1);
    } catch (e) {
    }
    try {
        t2 = countDecimals(num2);
    } catch (e) {
    }
    dec1 = convertToInt(num1);
    dec2 = convertToInt(num2);
    var result = accMul((dec1 / dec2), Math.pow(10, t2 - t1));
    return getCorrectResult("div", num1, num2, result);
    // return result;
};


var countDecimals = function (num) {
    var len = 0;
    try {
        num = Number(num);
        var str = num.toString().toUpperCase();
        if (str.split('E').length === 2) { // scientific notation
            var isDecimal = false;
            if (str.split('.').length === 2) {
                str = str.split('.')[1];
                if (parseInt(str.split('E')[0]) !== 0) {
                    isDecimal = true;
                }
            }
            let x = str.split('E');
            if (isDecimal) {
                len = x[0].length;
            }
            len -= parseInt(x[1]);
        } else if (str.split('.').length === 2) { // decimal
            if (parseInt(str.split('.')[1]) !== 0) {
                len = str.split('.')[1].length;
            }
        }
    } catch (e) {
        throw e;
    } finally {
        if (isNaN(len) || len < 0) {
            len = 0;
        }
        return len;
    }
};

var convertToInt = function (num) {
    num = Number(num);
    var newNum = num;
    var times = countDecimals(num);
    var temp_num = num.toString().toUpperCase();
    if (temp_num.split('E').length === 2) {
        newNum = Math.round(num * Math.pow(10, times));
    } else {
        newNum = Number(temp_num.replace(".", ""));
    }
    return newNum;
};

var getCorrectResult = function (type, num1, num2, result) {
    var temp_result = 0;
    switch (type) {
        case "add":
            temp_result = num1 + num2;
            break;
        case "sub":
            temp_result = num1 - num2;
            break;
        case "div":
            temp_result = num1 / num2;
            break;
        case "mul":
            temp_result = num1 * num2;
            break;
    }
    if (Math.abs(result - temp_result) > 1) {
        return temp_result;
    }
    return result;
};

var getDateDiff = function (sDate, eDate) { //sDate和eDate是yyyy-MM-dd格式
    var date1 = new Date(sDate);
    var date2 = new Date(eDate);
    var date3 = date2.getTime() - date1.getTime();
    var days = Math.floor(date3 / (24 * 3600 * 1000));
    return days;
}

