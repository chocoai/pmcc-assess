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

        //根据id获取单个数据字典信息
        getDataDicInfo: function (id) {
            if (id == undefined) return null;
            var data = {};
            $.ajax({
                url: getContextPath() + '/baseDataDic/getDataDicInfo',
                type: 'get',
                async: false,//通用方法只提供同步的方法
                data: {id: id},
                dataType: 'json',
                success: function (result) {
                    if (result.ret) {
                        data = result.data;
                    }
                }
            })
            return data;
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

        //找到key值对应的父节点
        getParentNodeByKey: function (node, key) {
            return getParentNodeByKey(node, key);
        }
    };

    window.AssessCommon = assessCommon;
})(jQuery)



