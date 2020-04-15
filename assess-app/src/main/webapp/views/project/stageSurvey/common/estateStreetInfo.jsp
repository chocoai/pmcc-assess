<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<script type="text/html" id="estateStreetInfoToolbar">
    <tr>
        <td>

            <input type="hidden" data-name="id" value="{id}">

            <input type="text" required="required"
                   placeholder="街道号" data-name="streetNumber" name="streetNumber{id}"
                   onblur="estateStreetInfoObj.onblur(this);"
                   class="form-control input-full " value="{streetNumber}">
        </td>
        <td>
            <div class="input-group">
                <input type="text" required="required" data-name="gateName" onblur="estateStreetInfoObj.onblur(this);"
                       name="gateName{id}" placeholder="大门名称"
                       class="form-control form-control-sm"
                       list="estateStreetInfoToolbar{id}" value="{gateName}">
                <datalist id="estateStreetInfoToolbar{id}">
                    <option value="" selected="">-请选择-</option>
                    <option value="南门">南门</option>
                    <option value="北门">北门</option>
                    <option value="东门">东门</option>
                    <option value="西门">西门</option>
                    <option value="正门">正门</option>
                    <option value="后门">后门</option>
                </datalist>
                <div class="input-group-prepend ">
                    <button class="btn btn-warning btn-sm "
                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                            type="button"
                            onclick="$(this).closest('.input-group').find('input').val('');">
                        清空
                        <i class="fa "></i>
                    </button>
                </div>
            </div>
        </td>
        <td><input type="text"
                   placeholder="附号" onblur="estateStreetInfoObj.onblur(this);"
                   data-name="attachedNumber" name="attachedNumber{id}"
                   class="form-control input-full " value="{attachedNumber}">
        </td>
        <td>
            <input id="estateStreetInfoObj{id}"
                   name="estateStreetInfoObj{id}" type="file"
                   multiple="false">
            <div id="_estateStreetInfoObj{id}"></div>
        </td>
        <td>
            <span class="input-group-btn">
                <input class="btn btn-warning btn-sm" type="button" value="X"
                       onclick="estateStreetInfoObj.cleanHTMLData(this)"></span>
        </td>
    </tr>
</script>


<script type="text/javascript">


    var estateStreetInfoObj = {};

    estateStreetInfoObj.fileUpload = function (target, tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: target
            },
            deleteFlag: true
        });
    };

    estateStreetInfoObj.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: target
            },
            deleteFlag: true
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

    estateStreetInfoObj.saveData = function (data, callback) {
        estateStreetInfoObj.ajaxServerFun(data, "/basicEstateStreetInfo/saveAndUpdateBasicEstateStreetInfo", "post", callback, null);
    };

    estateStreetInfoObj.deleteBasicEstateStreetInfo = function (id, callback) {
        estateStreetInfoObj.ajaxServerFun({id: id}, "/basicEstateStreetInfo/deleteBasicEstateStreetInfo", "post", callback, "delete");
    };
    estateStreetInfoObj.getBasicEstateStreetInfoList = function (query, callback) {
        estateStreetInfoObj.ajaxServerFun(query, "/basicEstateStreetInfo/basicEstateStreetInfoList", "get", callback);
    };

    estateStreetInfoObj.appendHTML = function (_this) {
        var table = $(_this).closest("table");
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        estateStreetInfoObj.saveData({estateId: data.id}, function (id) {
            var html = estateStreetInfoObj.replaceHtml({id: id});
            table.find("tbody").append(html);
            estateStreetInfoObj.showFile(estateStreetInfoObj.fileId + id, AssessDBKey.BasicEstateStreetInfo, id);
            estateStreetInfoObj.fileUpload(estateStreetInfoObj.fileId + id, AssessDBKey.BasicEstateStreetInfo, id);
        });
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

    estateStreetInfoObj.cleanHTMLData = function (_this) {
        var tr = $(_this).closest("tr");
        var id = tr.find("input[data-name=id]").val();
        estateStreetInfoObj.deleteBasicEstateStreetInfo(id, function () {
            tr.remove();
        });
    };

    estateStreetInfoObj.onblur = function (_this) {
        var value = $(_this).val();
        if (!value) {
            return false;
        }
        var tr = $(_this).closest("tr");
        var id = tr.find("input[data-name=id]").val();
        var streetNumber = tr.find("input[data-name=streetNumber]").val();
        var gateName = tr.find("input[data-name=gateName]").val();
        var attachedNumber = tr.find("input[data-name=attachedNumber]").val();
        var data = {id: id, streetNumber: streetNumber, gateName: gateName, attachedNumber: attachedNumber};
        estateStreetInfoObj.saveData(data);
    };

    estateStreetInfoObj.init = function (estateId) {
        (function (table) {
            var frm = table.closest("form");
            var data = formSerializeArray(frm);
            if (! estateId){
                estateId = data.id ;
            }
            estateStreetInfoObj.getBasicEstateStreetInfoList({estateId: estateId}, function (data) {
                if (data.length >= 1){
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateStreetInfoObj.replaceHtml(item));
                        estateStreetInfoObj.showFile(estateStreetInfoObj.fileId + item.id, AssessDBKey.BasicEstateStreetInfo, item.id);
                        estateStreetInfoObj.fileUpload(estateStreetInfoObj.fileId + item.id, AssessDBKey.BasicEstateStreetInfo, item.id);
                    });
                    setTimeout(function () {
                        var html = "" ;
                        html += "<button class='btn btn-sm btn-success' type='button' onclick='estateStreetInfoObj.appendHTML(this);'>" ;
                        html += "<i class='fa fa-plus'></i>" ;
                        html += "</button>" ;
                        table.find("tbody").find("tr").first().find("td").last().empty().append(html) ;
                    }, 100);
                }else {
                    estateStreetInfoObj.saveData({estateId: estateId},function (id) {
                        estateStreetInfoObj.init(estateId) ;
                    });
                }
            });
        }($(".estateStreetInfos")));
    } ;



</script>


</html>
