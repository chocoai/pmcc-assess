/**
 * Created by kings on 2018-5-21.
 */
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
        toPoint: function () {
            var str = percent.replace("%", "");
            str = str / 100;
            return str;
        },
        /*
        *描述:小数转百分数,这里需要先用Number进行数据类型转换，然后去指定截取转换后的小数点后几位(按照四舍五入)，这里是截取一位，0.1266转换后会变成12.7%*/
        toPercent: function () {
            var str = Number(point * 100).toFixed(1);
            str += "%";
            return str;
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
                                if (item.id === value) {
                                    retHtml += ' <option value="' + item.id + '" selected="selected">' + item.name + '</option>';
                                } else {
                                    retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                                }
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
            if (key) {
                $.ajax({
                    url: getContextPath() + "/baseDataDic/getDataDicListByFieldName",
                    type: "get",
                    dataType: "json",
                    data: {
                        fieldName: key
                    },
                    success: function (result) {
                        if (result.ret) {
                            var retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(result.data, function (i, item) {
                                if (item.id == value) {
                                    retHtml += ' <option value="' + item.id + '" selected="selected">' + item.name + '</option>';
                                } else {
                                    retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                                }
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

        //根据pid获取区域信息
        loadAreaInfoByPid: function (pid, callback) {
            if (pid) {
                $.ajax({
                    url: getContextPath() + "/area/getAreaList",
                    type: "get",
                    dataType: "json",
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

            defaults.provinceTarget.select2();
            defaults.cityTarget.select2();
            defaults.districtTarget.select2();

            //省切换
            defaults.provinceTarget.bind('change', function () {
                isProvinceFirstChange = false;
                defaults.cityTarget.select2('val', '').empty();
                if (defaults.districtTarget) {
                    defaults.districtTarget.select2('val', '').empty();
                }

                //加载市
                AssessCommon.loadAreaInfoByPid($(this).val(), function (html) {
                    defaults.cityTarget.append(html);
                    //初始化设置默认选中项
                    if (!defaults.cityValue && isCityFirstChange && defaults.useDefaultText && defaults.cityDefaultText) {
                        defaults.cityTarget.select2('val', defaults.cityTarget.find("option:contains(" + defaults.cityDefaultText + ")").val()).trigger('change');
                    } else {
                        if (defaults.cityValue) {
                            defaults.cityTarget.select2('val', defaults.cityValue).trigger('change');
                        }
                    }
                });
            })


            //有区域元素才做处理
            if (defaults.districtTarget) {
                if ($.type(defaults.districtTarget) === "string") {
                    defaults.districtTarget = $("#" + defaults.districtTarget);
                }
                defaults.districtTarget.select2();

                //市切换
                defaults.cityTarget.bind('change', function () {
                    isCityFirstChange = false;
                    defaults.districtTarget.select2('val', '').empty();
                    //加载区县
                    AssessCommon.loadAreaInfoByPid($(this).val(), function (html) {
                        defaults.districtTarget.append(html);
                        if (defaults.districtValue) {
                            defaults.districtTarget.select2('val', defaults.districtValue);
                        }
                    });
                })
            }


            //获取省数据
            $.ajax({
                url: getContextPath() + "/area/getProvinceList",
                type: "post",
                dataType: "json",
                data: {},
                success: function (result) {
                    if (result.ret && result.data) {
                        defaults.provinceTarget.append("<option value=''>-请选择-</option>");
                        $.each(result.data, function (i, item) {
                            defaults.provinceTarget.append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        });
                        if (defaults.provinceValue) {
                            defaults.provinceTarget.val(defaults.provinceValue);
                            //触发一次change事件
                            defaults.provinceTarget.trigger('change');
                        }

                        //初始化设置默认选中项
                        if (!defaults.provinceValue && isProvinceFirstChange && defaults.useDefaultText && defaults.provinceDefaultText) {
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
        }
    };

    window.AssessCommon = assessCommon;
})(jQuery)



