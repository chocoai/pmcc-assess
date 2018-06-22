(function ($) {
    //表单自动赋值
    $.fn.extend({
        initForm: function (options) {
            //默认参数
            var defaults = {
                formdata: "",
                isDebug: false   //是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name value打印出来
            }
            //如果传入的json字符串，将转为json对象
            var tempData = "";
            if ($.type(options) === "string") {
                defaults.formdata = JSON.parse(options);
            } else {
                defaults.formdata = options;
            }
            //设置参数
            // var setting = $.extend({}, defaults, tempData);
            var setting = defaults;
            var form = this;
            formdata = setting.formdata;

            //如果传入的json对象为空，则不做任何操作
            if (!$.isEmptyObject(formdata)) {
                var debugInfo = "";
                $.each(formdata, function (key, value) {
                    //是否开启调试，开启将会把name value打印出来
                    if (setting.isDebug) {
                        debugInfo += "name:" + key + "; value:" + value + "\r\n ";
                    }
                    //表单处理
                    var formField = form.find("[name='" + key + "']");
                    if ($.type(formField[0]) === "undefined") {
                        if (setting.isDebug) {
                            console.warn("can not find name:[" + key + "] in form!!!"); //没找到指定name的表单
                        }
                    } else {
                        var fieldTagName = formField[0].tagName.toLowerCase();
                        if (fieldTagName == "input") {
                            if (formField.attr("type") == "radio") {
                                $("input:radio[name='" + key + "'][value='" + value + "']").attr("checked", "checked");
                            } else if (formField.attr("type") == "checkbox") {
                                $("input:checkbox[name='" + key + "'][value='" + value + "']").prop("checked", true);
                            } else {
                                formField.val(value);
                            }
                        } else if (fieldTagName == "label" || fieldTagName == "a") {
                            formField.html(value);
                        } else {
                            formField.val(value);
                        }
                    }
                    //图片链接处理form.find("img[fieldata=img_url]")
                    var formImage = form.find("img[fieldata=" + key + "]");
                    if ($.type(formImage[0]) != "undefined") {
                        formImage.attr("src", value);
                    }
                    //a链接处理
                    var formLink = form.find("a[fieldata=" + key + "]");
                    if ($.type(formLink[0]) != "undefined") {
                        formLink.attr("href", value);
                    }
                })
                if (setting.isDebug) {
                    console.log(debugInfo);
                }
            }
            return form;    //返回对象，提供链式操作
        }
    });

})(jQuery)

function getContextPath() {

    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}


function closeWindow() {
    window.opener = null;
    window.open('', '_top', '');
    window.parent.close();
}


function GetFormatDate(value, dateFormat) {
    if (value) {
        return new Date(parseInt(value.substring(value.indexOf('(') + 1, value.indexOf(')')))).format(dateFormat);
    } else {
        return "";
    }
}
//日期格式化
function formatDate(v, isfull) {
    if (!v) {
        return "";
    }
    if (/^(-)?\d{1,10}$/.test(v)) {
        v = v * 1000;
    } else if (/^(-)?\d{1,13}$/.test(v)) {
        v = v * 1;
    }
    var dateObj = new Date(v);
    var month = dateObj.getMonth() + 1;
    var day = dateObj.getDate();
    var hours = dateObj.getHours();
    var minutes = dateObj.getMinutes();
    var seconds = dateObj.getSeconds();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hours < 10) {
        hours = "0" + hours;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    var UnixTimeToDate = dateObj.getFullYear() + '-' + month + '-' + day;
    if (isfull) {
        UnixTimeToDate += ' ' + hours + ':' + minutes;
        UnixTimeToDate += ':' + seconds;
    }
    return UnixTimeToDate;
}

//时间格式化
function formatTime(v) {
    if (!v) {
        return "";
    }
    if (/^(-)?\d{1,10}$/.test(v)) {
        v = v * 1000;
    } else if (/^(-)?\d{1,13}$/.test(v)) {
        v = v * 1;
    }
    var dateObj = new Date(v);
    var hours = dateObj.getHours();
    var minutes = dateObj.getMinutes();
    if (hours < 10) {
        hours = "0" + hours;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    return hours + ':' + minutes + " ";
}

//获取参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
//获取url后缀
function getSearch() {
    var index = window.location.href.indexOf("?");
    return window.location.href.substring(index + 1);
}
//获取指定表单的值
function getValue(elementId) {
    return document.getElementById(elementId).value;
}


function getRootPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var path = prePath + "/";
    return path;
}

//表单查询参数
function formParams(formId) {
    return formSerializeArray($("#" + formId));
}

function formSerializeArray(obj) {
    var ret = {};
    var queryParams = obj.serializeArray();
    $.each(queryParams, function (i, field) {
        if (field.value != undefined) {
            if (ret[field.name] != undefined && ret[field.name] != '') {
                ret[field.name] = ret[field.name] + "," + field.value;
            } else {
                ret[field.name] = field.value;
            }
        }
    })
    return ret;
}


//向form表单中添加参数
function formAddParam(formId, params) {
    var form = $("#" + formId);
    if (form && params) {
        form.empty();
        $.each(params, function (name, value) {
            var item = "<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\" />";
            form.append(item);
        })
    }
}

//loading
var Loading = {
    progressShow: function (text) {
        var progressModal = $('#loadingModal');
        if (text) {
            $('#loading-progress-info-text').text(text);
        }
        progressModal.modal();
    },
    progressHide: function () {
        var progressModal = $('#loadingModal');
        $('#loading-progress-info-text').text("");
        progressModal.modal('hide');
    }
};

/**
 * 关闭面板
 * @param item
 */
function closePanel(item) {
    $(item).parents(".panel").remove();
}

///////////////////
//loading end
/////////////////
//汉字转拼音
function getpinyin(cnWord) {
    var enWord = undefined;
    $.ajax({
        url: "/public/getPinyin",
        type: "get",
        dataType: "json",
        async: false,
        data: {
            cnWord: cnWord
        },
        success: function (result) {
            if (result.ret) {
                enWord = result.data;
            }
        }
    })
    return enWord;
}


/**
 * 指定日期添加相应的天数
 * @param date 日期
 * @param dayNumber 添加天数
 * @returns {Date} 添加相应天数后的日期
 */
function addDay(date, dayNumber) {
    date = date ? date : new Date();
    var ms = dayNumber * (1000 * 60 * 60 * 24)
    var newDate = new Date(date.getTime() + ms);
    return newDate;
}

/**
 * 监听打开的弹窗，关闭后刷新页面
 */
function openWin(url, callback) {
    var winObj = window.open(url);
    var loop = setInterval(function () {
        if (winObj.closed) {
            clearInterval(loop);
            callback();
        }
    }, 1);
}

/**
 * 去除字符
 * @param str
 * @param item
 */
function trimChar(str, item) {
    if (!item) item = " ";
    var reStart = new RegExp("^" + item + "+");
    var reEnd = new RegExp(item + "+$");
    return str.replace(reStart, "").replace(reEnd, "");
}

function checkFileExt(fileName, arr) {
    var flag = false; //状态
    //取出上传文件的扩展名
    var index = fileName.lastIndexOf(".");
    var ext = fileName.substr(index + 1);
    //循环比较
    for (var i = 0; i < arr.length; i++) {
        if (ext.toLocaleLowerCase() == arr[i]) {
            flag = true; //一旦找到合适的，立即退出循环
            break;
        }
    }
    return flag;
}

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}


function DateAdd(interval, number, date) {
    switch (interval) {
        case "year": {//年
            date.setFullYear(date.getFullYear() + number);
            return date;
            break;
        }
        case "quarter": {//季度
            date.setMonth(date.getMonth() + number * 3);
            return date;
            break;
        }
        case "month": {//月
            date.setMonth(date.getMonth() + number);
            return date;
            break;
        }
        case "week": {//周
            date.setDate(date.getDate() + number * 7);
            return date;
            break;
        }
        case "day": {//天
            date.setDate(date.getDate() + number);
            return date;
            break;
        }
        case "hour": {//小时
            date.setHours(date.getHours() + number);
            return date;
            break;
        }
        case "minute": {//分
            date.setMinutes(date.getMinutes() + number);
            return date;
            break;
        }
        case "second": {//秒钟
            date.setSeconds(date.getSeconds() + number);
            return date;
            break;
        }
        default: {//
            date.setDate(d.getDate() + number);
            return date;
            break;
        }
    }
}

//整数转两个浮点数
function nubmertoFixed(value) {
    return (parseInt(value) / 100).toFixed(2);
}

/**
 * 根据员工账号取得员工基本信息
 */

/**
 * 弹出框
 * @param message 消息内容
 * @param butNumber 按钮数量
 * @param cancelFn 取消按钮事件
 * @param okFn 确认按钮事件
 * @constructor
 */
function Alert(message, butNumber, cancelFn, okFn) {
    if (butNumber == undefined || butNumber <= 1) {
        bootbox.alert(message, function () {
            if (okFn) {
                okFn();
            }
        });
    } else {
        bootbox.confirm(message, function (result) {
            if (result) {
                if (okFn) {
                    okFn();
                }
            } else {
                if (cancelFn) {
                    cancelFn();
                }
            }
        });
    }
}

function getBoolChs(value) {
    var str = "";
    switch (value) {
        case "true":
        case true:
        case 1: {
            str = "是";
            break;
        }
        case "false":
        case false:
        case 0: {
            str = "否";
            break;
        }
    }
    return str;
}

/**
 * 公共取得下拉列表数据的信息
 * @param url 访问的地址
 * @param data 参数
 * @param fn 回调函数
 */
function loadSelectOpationHtml(url, data, fn) {
    $.ajax({
        url: url,
        type: "get",
        dataType: "json",
        data: data,
        success: function (result) {
            var retHtml = '<option value="" selected>-请选择-</option>';
            if (result.ret) {

                $.each(result.data, function (i, item) {
                    retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                });
                fn(retHtml, result.data);
            }
            else {
                fn(retHtml, result.data);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
}


/**
 * 根据上级编号取得相应的下级基础数据列表,并返回组成的HTML以及返回的数据
 * @param pid
 * @param fn
 */
function loadDataDicByPid(pid, fn) {
    if (pid) {
        $.ajax({
            url: getContextPath() + "/sysdatadic/getCacheDataDicListByPid",
            type: "get",
            dataType: "json",
            data: {
                pid: pid
            },
            success: function (result) {
                if (result.ret) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                    });
                    fn(retHtml, result.data);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
}


/**
 * 根据上级编号取得相应的下级基础数据列表,并返回组成的HTML以及返回的数据
 * @param pid
 * @param fn
 */
function loadDataDicByPidExtend(pid, subId, fn) {
    if (pid) {
        $.ajax({
            url: getContextPath() + "/sysdatadic/getCacheDataDicListByPid",
            type: "get",
            dataType: "json",
            data: {
                pid: pid
            },
            success: function (result) {
                if (result.ret) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        if (item.id === subId) {
                            retHtml += ' <option value="' + item.id + '" selected="selected">' + item.name + '</option>';
                        } else {
                            retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                        }
                    });
                    fn(retHtml, result.data);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
}

/**
 * 根据字段名称取得相应的下级基础数据列表,并返回组成的HTML以及返回的数据
 * @param pid
 * @param fn
 */
function loadDataDicByFieldNameExtend(fieldName, subId, fn) {
    if (fieldName) {
        $.ajax({
            url: getContextPath() + "/BidBaseDataDic/getDataDicListByFieldName",
            type: "get",
            dataType: "json",
            data: {
                fieldName: fieldName
            },
            success: function (result) {
                if (result.ret) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        if (item.id == subId) {
                            retHtml += ' <option value="' + item.id + '" selected="selected">' + item.name + '</option>';
                        } else {
                            retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                        }
                    });
                    console.log(retHtml);
                    fn(retHtml, result.data);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
}

/*
 * 功能：对JSON对象字符串数组进行多字段（多列）排序
 * 参数：
 *   objArr: 目标数组
 *   keyArr: 排序字段，以数组形式传递
 *   type：排序方式，undefined以及asc都是按照升序排序，desc按照降序排序
 * */
function sortObjectArray(objArr, keyArr, type) {
    if (type != undefined && type != 'asc' && type != 'desc') {
        return 'error';
    }
    var order = 1;
    if (type != undefined && type == 'desc') {
        order = -1;
    }
    var key = keyArr[0];
    objArr.sort(function (objA, objB) {
        if (objA[key] > objB[key]) {
            return order;
        } else if (objA[key] < objB[key]) {
            return 0 - order;
        } else {
            return 0;
        }
    })
    for (var i = 1; i < keyArr.length; i++) {
        var key = keyArr[i];
        objArr.sort(function (objA, objB) {
            for (var j = 0; j < i; j++) {
                if (objA[keyArr[j]] != objB[keyArr[j]]) {
                    return 0;
                }
            }
            if (objA[key] > objB[key]) {
                return order;
            } else if (objA[key] < objB[key]) {
                return 0 - order;
            } else {
                return 0;
            }
        })
    }
    return objArr;
}

/**
 * 格式化下划线
 * @param key
 * @param value
 */
function formatToUnderline(var1, var2, separator) {
    if (!var1) return "";
    if (!separator) {
        separator = ',';
    }
    var keyArray = var1.split(separator);
    if (var2) {
        var valueArray = var2.split(separator);
    }
    var resultStr = '';
    $.each(keyArray, function (i, item) {
        resultStr += item + '_' + valueArray[i]+',';
    })
    return resultStr.replace(/,$/,'');
}
