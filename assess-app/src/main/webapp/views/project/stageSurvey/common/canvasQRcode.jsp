<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-24
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%--二维码问题处理--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <div class="col-sm-11">
                <div>
                    <script src="${pageContext.request.contextPath}/assets/qrcodejs/qrcode.js?v=${assessVersion}"></script>
                    <div id="canvasQRcodeModel"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>

    var canvasQRcode = {};
    canvasQRcode.targetId = "canvasQRcodeModel";
    canvasQRcode.run = function (data, url, type, callback, funParams, errorCallback) {
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
    canvasQRcode.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                canvasQRcode.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            canvasQRcode.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    canvasQRcode.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        canvasQRcode.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };


    $(document).ready(function () {
        var html = "";
        try {
            canvasQRcode.ajaxServerFun({tableId:'${tbId}' ,type:'${tbType}'}, '/baseQrCode/toImgQRCodePath', "get", function (data) {
                if (!data) {
                    return false;
                }
                if (!data.code) {
                    return false;
                }
                var qrcode = new QRCode(document.getElementById(canvasQRcode.targetId), {
                    width: 150,
                    height: 150
                });
                try {
                    //二维码 canvas 生成
                    qrcode.makeCode(data.code);
                    console.log(data.code) ;
                } catch (ex) {
                    console.log(ex) ;
                }
            })
        } catch (e) {
            console.log(e) ;
        }
    });
</script>