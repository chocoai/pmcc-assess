<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-5-19
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12 col-sm-12 col-lg-12" style="text-align: center;padding-bottom: 1.25rem">

    <div class="card full-height" style="min-height: 30px;">
        <div class="card-body">
            <div class="row col-md-12">
                <div class="col-md-5 ">
                </div>
                <div class="col-md-6 ">
                    <div id="paginationHtml">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
    <div class="card-body">
        <button  class="btn btn-default" onclick="window.close()">
            关闭
        </button>
    </div>
</div>

<script>
    var canvasQRcode = {};
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

    canvasQRcode.loadPagination = function () {
        var query = {applyBatchId: '${basicApplyBatch.id}', tableId: '${tbId}'};
        canvasQRcode.ajaxServerFun(query, "/basicApplyBatch/getBasicApplyBatchDetailList", "get", function (data) {
            if (!data) {
                return false;
            }
            if (!$.isArray(data)) {
                return false;
            }
            if (data.length == 0) {
                return false;
            }
            var item = data[0];
            canvasQRcode.ajaxServerFun({
                applyBatchId: query.applyBatchId,
                pid: item.id
            }, "/basicApplyBatch/getBasicApplyBatchDetailList", "get", function (data) {
                var html = "";
                html += "<ul class='pagination pagination-sm'>";
                html += "<li class='page-item'>" + "<a class='page-link' onclick='canvasQRcode.loadRoot();'>树首页</a>" + "</li>";
                if (item.pid != 0 || item.pid != '0') {
                    html += "<li class='page-item'>" + "<a class='page-link' onclick='canvasQRcode.openPrevious(" +item.pid +")'"+ " >"+      "上个节点</a>"+      "</li>";
                }
                $.each(data, function (i, n) {
                    html += "<li class='page-item'>" + "<a class='page-link' onclick='canvasQRcode.openUrl(" +n.id +")'"+ " >"+   n.name+   "</a>"+      "</li>";
                });
                html += "</ul>";


                var target = $("#paginationHtml");

                target.append(html);
            });
        });
    };

    canvasQRcode.loadRoot = function () {
        var url = '${pageContext.request.contextPath}/basicApplyBatch/informationPhoneTree?applyBatchId=${basicApplyBatch.id}';
        openWin(url, function () {
        })
    };



    canvasQRcode.openUrl = function (id) {
        canvasQRcode.ajaxServerFun({id: id}, "/basicApplyBatch/getBasicApplyBatchDetailList", "get", function (data) {
            if (! data){
                return false ;
            }
            if (!$.isArray(data)) {
                return false;
            }
            if (data.length == 0) {
                return false;
            }
            var node = data[0] ;
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationPhoneEdit?';
            url += 'applyBatchId=' + node.applyBatchId;
            url += '&formClassify=' + '${basicApplyBatch.classify}';
            url += '&formType=' + '${basicApplyBatch.type}';
            url += '&tbId=' + node.tableId;
            url += '&tbType=' + node.type;
            url += '&planDetailsId=${basicApplyBatch.planDetailsId}';
            openWin(url, function () {
            })
        });
    };

    canvasQRcode.openPrevious = function (id) {
        canvasQRcode.openUrl(id) ;
    };

    $(document).ready(function () {
        canvasQRcode.loadPagination();
    });
</script>