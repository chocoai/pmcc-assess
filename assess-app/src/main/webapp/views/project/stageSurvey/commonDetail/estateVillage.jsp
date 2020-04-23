<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<script type="text/html" id="estateVillageToolbar">
    <tr>
        <td>

            <label class="form-control input-full">{districtName}</label>
        </td>
        <td>

            <label class="form-control input-full">{villageStreet}</label>
        </td>
        <td>
            <label class="form-control input-full">{burgStreet}</label>
        </td>
        <td>
            <label class="form-control input-full">{numberGroup}</label>
        </td>
    </tr>
</script>


<script type="text/javascript">


    var estateVillageObj = {};

    estateVillageObj.htmlModel = "#estateVillageToolbar";

    estateVillageObj.run = function (data, url, type, callback, funParams, errorCallback) {
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
    estateVillageObj.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                estateVillageObj.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            estateVillageObj.run(data, url, type, callback, funParams, errorCallback);
        }
    };


    estateVillageObj.getBasicEstateVillageList = function (query, callback) {
        estateVillageObj.ajaxServerFun(query, "/basicEstateVillage/basicEstateVillageList", "get", callback);
    };



    estateVillageObj.replaceHtml = function (data) {
        var html = $(estateVillageObj.htmlModel).html();
        html = html.replace(/{id}/g, data.id);
        if (data.districtName) {
            html = html.replace(/{districtName}/g, data.districtName);
        } else {
            html = html.replace(/{districtName}/g, '');
        }
        if (data.villageStreet) {
            html = html.replace(/{villageStreet}/g, data.villageStreet);
        } else {
            html = html.replace(/{villageStreet}/g, '');
        }
        if (data.burgStreet) {
            html = html.replace(/{burgStreet}/g, data.burgStreet);
        } else {
            html = html.replace(/{burgStreet}/g, '');
        }
        if (data.numberGroup) {
            html = html.replace(/{numberGroup}/g, data.numberGroup);
        } else {
            html = html.replace(/{numberGroup}/g, '');
        }
        return html;
    };



    estateVillageObj.init = function (estateId) {
        (function (table) {
            var frm = table.closest("form");
            var data = formSerializeArray(frm);
            if (! estateId){
                estateId = data.id ;
            }
            estateVillageObj.getBasicEstateVillageList({estateId: estateId}, function (data) {
                setTimeout(function () {
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateVillageObj.replaceHtml(item));
                    });
                }, 100);
            });
        }($(".estateVillages")));
    } ;



</script>


</html>
