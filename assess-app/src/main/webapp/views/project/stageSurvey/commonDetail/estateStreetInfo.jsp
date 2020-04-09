<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<script type="text/html" id="estateStreetInfoToolbar">
    <tr>
        <td>

            <label class="form-control input-full">{streetNumber}</label>
        </td>
        <td>
            <label class="form-control input-full">{gateName}</label>
        </td>
        <td>
            <label class="form-control input-full">{attachedNumber}</label>
        </td>
        <td>
            <div id="_estateStreetInfoObj{id}"></div>
        </td>
        <td>

        </td>
    </tr>
</script>


<script type="text/javascript">


    var estateStreetInfoObj = {};



    estateStreetInfoObj.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: target
            },
            deleteFlag: false
        })
    };

    estateStreetInfoObj.htmlModel = "#estateStreetInfoToolbar";
    estateStreetInfoObj.fileId = "estateStreetInfoObj";

    estateStreetInfoObj.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
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
    estateStreetInfoObj.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                estateStreetInfoObj.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            estateStreetInfoObj.run(data, url, type, callback, funParams, errorCallback);
        }
    };


    estateStreetInfoObj.getBasicEstateStreetInfoList = function (query, callback) {
        estateStreetInfoObj.ajaxServerFun(query, "/basicEstateStreetInfo/basicEstateStreetInfoList", "get", callback);
    };



    estateStreetInfoObj.replaceHtml = function (data) {
        var html = $(estateStreetInfoObj.htmlModel).html();
        html = html.replace(/{id}/g, data.id);
        if (data.streetNumber) {
            html = html.replace(/{streetNumber}/g, data.streetNumber);
        } else {
            html = html.replace(/{streetNumber}/g, '');
        }
        if (data.gateName) {
            html = html.replace(/{gateName}/g, data.gateName);
        } else {
            html = html.replace(/{gateName}/g, '');
        }
        if (data.attachedNumber) {
            html = html.replace(/{attachedNumber}/g, data.attachedNumber);
        } else {
            html = html.replace(/{attachedNumber}/g, '');
        }
        return html;
    };



    estateStreetInfoObj.init = function (estateId) {
        (function (table) {
            var frm = table.closest("form");
            var data = formSerializeArray(frm);
            if (! estateId){
                estateId = data.id ;
            }
            estateStreetInfoObj.getBasicEstateStreetInfoList({estateId: estateId}, function (data) {
                setTimeout(function () {
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateStreetInfoObj.replaceHtml(item));
                        estateStreetInfoObj.showFile(estateStreetInfoObj.fileId + item.id, AssessDBKey.BasicEstateStreetInfo, item.id);
                    });
                }, 100);
            });
        }($(".estateStreetInfos")));
    } ;



</script>


</html>
