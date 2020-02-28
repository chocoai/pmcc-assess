/**
 * Created by Calvin on 2017/11/16.
 * 工具类JS
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

/**
 * 根据上级编号取得相应的下级基础数据列表,并返回组成的HTML以及返回的数据
 * @param pid
 * @param fn
 */
function loadDataDicByPid(pid, fn) {
    if (pid) {
        $.ajax({
            url: "/sysdatadic/getCacheDataDicListByPid",
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
            url: "/sysdatadic/getCacheDataDicListByPid",
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
            url: getContextPath()+"/BidBaseDataDic/getDataDicListByFieldName",
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }
}