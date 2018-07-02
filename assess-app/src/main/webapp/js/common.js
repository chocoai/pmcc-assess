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

        //根据pid获取区域信息
        loadAreaInfoByPid: function (pid, value, callback) {
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
                                if (item.areaId === value) {
                                    retHtml += ' <option value="' + item.areaId + '" selected="selected">' + item.name + '</option>';
                                } else {
                                    retHtml += ' <option value="' + item.areaId + '">' + item.name + '</option>';
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

        //初始化区域信息
        initAreaInfo: function (options) {
            var isProvinceFirstChange = true;
            var isCityFirstChange = true;
            var defaluts = {
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
            defaluts = $.extend({}, defaluts, options);
            if ($.type(defaluts.provinceTarget) === "string") {
                defaluts.provinceTarget = $("#" + defaluts.provinceTarget);
            }
            if ($.type(defaluts.cityTarget) === "string") {
                defaluts.cityTarget = $("#" + defaluts.cityTarget);
            }
            defaluts.provinceTarget.select2();
            defaluts.cityTarget.select2();

            //省切换
            defaluts.provinceTarget.bind('change', function () {
                isProvinceFirstChange = false;
                defaluts.cityTarget.select2('val', '').empty();
                if (defaluts.districtTarget) {
                    defaluts.districtTarget.select2('val', '').empty();
                }

                //加载市
                AssessCommon.loadAreaInfoByPid($(this).val(), defaluts.cityValue, function (html) {
                    defaluts.cityTarget.append(html);
                    //初始化设置默认选中项
                    if (!defaluts.cityValue && isCityFirstChange && defaluts.useDefaultText && defaluts.cityDefaultText) {
                        defaluts.cityTarget.select2('val', defaluts.cityTarget.find("option:contains(" + defaluts.cityDefaultText + ")").val()).trigger('change');
                    }
                });
            })


            //有区域元素才做处理
            if (defaluts.districtTarget) {
                if ($.type(defaluts.districtTarget) === "string") {
                    defaluts.districtTarget = $("#" + defaluts.districtTarget);
                }
                defaluts.districtTarget.select2();

                //市切换
                defaluts.cityTarget.bind('change', function () {
                    isCityFirstChange = false;
                    defaluts.districtTarget.select2('val', '').empty();
                    //加载区县
                    AssessCommon.loadAreaInfoByPid($(this).val(), defaluts.districtValue, function (html) {
                        defaluts.districtTarget.append(html);
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
                    if (result.ret&&result.data) {
                        defaluts.provinceTarget.append("<option value=''>-请选择-</option>");
                        $.each(result.data, function (i, item) {
                            defaluts.provinceTarget.append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        });
                        if (defaluts.provinceValue) {
                            defaluts.provinceTarget.val(defaluts.provinceValue);
                            //触发一次change事件
                            defaluts.provinceTarget.trigger('change');
                        }

                        //初始化设置默认选中项
                        if (!defaluts.provinceValue && isProvinceFirstChange && defaluts.useDefaultText && defaluts.provinceDefaultText) {
                            defaluts.provinceTarget.select2('val', defaluts.provinceTarget.find("option:contains(" + defaluts.provinceDefaultText + ")").val()).trigger('change');
                        }

                        if (defaluts.success) {
                            defaluts.success();
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })


        }
    };

    window.AssessCommon = assessCommon;
})(jQuery)



