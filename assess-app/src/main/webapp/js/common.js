/**
 * Created by kings on 2018-5-21.
 */
$(function () {
    try {
        $(".select2").each(function () {
            $(this).select2();
            $(this).on("select2-highlight", function (e) {
                // layer.tips('只想提示地精准些',$(e.target));
                // console.log($(e.target).find('option').val());
            });
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
            }
            else {
                txtFocus.setSelectionRange(position, position);
                txtFocus.focus();
            }
        },

        //对象不存在则返回空串
        toString: function (o) {
            if (!o) return "";
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
        //百分数转小数
        percentToPoint: function (value) {
            if (value) {
                var value = value.replace("%", "");
                if (AssessCommon.isNumber(value)) {
                    return (parseFloat(value) / 100).toFixed(4);
                }
            }
        },
        /*
         *描述:小数转百分数,这里需要先用Number进行数据类型转换，然后去指定截取转换后的小数点后几位(按照四舍五入)，这里是截取一位，0.1266转换后会变成12.7%*/
        pointToPercent: function (value) {
            if (value && AssessCommon.isNumber(value)) {
                return (parseFloat(value) * 100).toFixed(2) + "%";
            }
        },
        //百分数转小数
        elementParsePoint: function (element) {
            var val = $(element).val();
            var pointVal = AssessCommon.percentToPoint(val);
            if (pointVal) {
                $(element).attr('data-value', pointVal);
            }
        },
        //
        elementParsePercent: function (element) {
            var val = $(element).attr('data-value');
            var percentVal = AssessCommon.pointToPercent(val);
            if (percentVal) {
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
                        Alert("调用服务端方法失败，失败原因:" + result);
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
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            }
        },
        //根据项目字段获取项目类别
        getProjectClassifyListByFieldName: function (fieldName, callback) {
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
                                retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                            });
                            if (callback) {
                                callback(retHtml, result.data);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            }
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
                        Alert("调用服务端方法失败，失败原因:" + result);
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
            if (id == undefined) return null;
            $.ajax({
                url: getContextPath() + '/baseDataDic/getDataDicInfo',
                type: 'get',
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
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            }
        },

        //根据key获取字典信息
        loadDataDicByKey: function (key, value, callback) {
            AssessCommon.loadAsyncDataDicByKey(key, value, callback, true);
        },
        //根据key获取字典信息
        loadAsyncDataDicByKey: function (key, value, callback, async) {
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
                        Alert("调用服务端方法失败，失败原因:" + result);
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
                        Alert("调用服务端方法失败，失败原因:" + result);
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
                useDefaultText: true,
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
                    Alert("调用服务端方法失败，失败原因:" + result);
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
        //新增或者更新附件
        saveAndUpdateSysAttachmentDto: function (item, callback) {
            $.ajax({
                url: getContextPath() + "/public/saveAndUpdateSysAttachmentDto",
                type: "POST",
                data: item,
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
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                }
            });
        }
    };

    window.AssessCommon = assessCommon;
})(jQuery)



