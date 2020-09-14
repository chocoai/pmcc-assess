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
                       value="{gateName}">
                <div class="input-group-append">
                    <button class="btn btn-warning btn-sm dropdown-toggle" type="button" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">选择
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item"
                           onclick="estateStreetInfoObj.gateSelected(this);">南门</a>
                        <a class="dropdown-item"
                           onclick="estateStreetInfoObj.gateSelected(this);">北门</a>
                        <a class="dropdown-item"
                           onclick="estateStreetInfoObj.gateSelected(this);">东门</a>
                        <a class="dropdown-item"
                           onclick="estateStreetInfoObj.gateSelected(this);">西门</a>
                        <a class="dropdown-item"
                           onclick="estateStreetInfoObj.gateSelected(this);">正门</a>
                        <a class="dropdown-item"
                           onclick="estateStreetInfoObj.gateSelected(this);">后门</a>
                    </div>
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
            table.find("[name='streetNumber" + id + "']").apStreetName({
                getProvince: function () {
                    return estateCommon.estateForm.find("select[name='province']").val();
                },
                getCity: function () {
                    return estateCommon.estateForm.find("select[name='city']").val();
                },
                onSelect: function (id, name) {
                    caseFun.caseEstate.showModel(name);
                }
            });
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

    //大门选择
    estateStreetInfoObj.gateSelected = function (_this) {
        var input = $(_this).closest('.input-group').find('input');
        input.val($(_this).text());
        estateStreetInfoObj.onblur(input);
    }

    estateStreetInfoObj.init = function (estateId) {
        (function (table) {
            var frm = table.closest("form");
            table.find("tbody").empty();
            var data = formSerializeArray(frm);
            if (!estateId) {
                estateId = data.id;
            }
            estateStreetInfoObj.getBasicEstateStreetInfoList({estateId: estateId}, function (data) {
                if (data.length >= 1) {
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateStreetInfoObj.replaceHtml(item));
                        estateStreetInfoObj.showFile(estateStreetInfoObj.fileId + item.id, AssessDBKey.BasicEstateStreetInfo, item.id);
                        estateStreetInfoObj.fileUpload(estateStreetInfoObj.fileId + item.id, AssessDBKey.BasicEstateStreetInfo, item.id);
                        table.find("[name='streetNumber" + item.id + "']").apStreetName({
                            getProvince: function () {
                                return estateCommon.estateForm.find("select[name='province']").val();
                            },
                            getCity: function () {
                                return estateCommon.estateForm.find("select[name='city']").val();
                            },
                            onSelect: function (id, name) {
                                caseFun.caseEstate.showModel(name);
                            }
                        });
                    });
                    setTimeout(function () {
                        var html = "";
                        html += "<button class='btn btn-sm btn-success' type='button' onclick='estateStreetInfoObj.appendHTML(this);'>";
                        html += "<i class='fa fa-plus'></i>";
                        html += "</button>";
                        table.find("tbody").find("tr").first().find("td").last().empty().append(html);
                    }, 100);
                } else {
                    estateStreetInfoObj.saveData({estateId: estateId}, function (id) {
                        estateStreetInfoObj.init(estateId);
                    });
                }
            });
        }($(".estateStreetInfos")));
    };


</script>


</html>
