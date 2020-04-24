<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<script type="text/html" id="estateVillageToolbar">
    <tr>
        <td>
            <input type="hidden" data-name="id" value="{id}">
            <select required="required"
                    placeholder="县" data-name="district" name="district{id}"
                    onblur="estateVillageObj.onblur(this);"
                    class="form-control input-full search-select select2"></select>
        </td>
        <td>
            <input type="text" required="required"
                   placeholder="街道(乡)名称" data-name="villageStreet" name="villageStreet{id}"
                   onblur="estateVillageObj.onblur(this);"
                   class="form-control input-full " value="{villageStreet}">
        </td>
        <td>
            <input type="text" required="required"
                   placeholder="街道(村)名称" data-name="burgStreet" name="burgStreet{id}"
                   onblur="estateVillageObj.onblur(this);"
                   class="form-control input-full " value="{burgStreet}">
        </td>

        <td><input type="text"
                   placeholder="附号(组名称)" onblur="estateVillageObj.onblur(this);"
                   data-name="numberGroup" name="numberGroup{id}"
                   class="form-control input-full " value="{numberGroup}">
        </td>
        <td>
            <span class="input-group-btn">
                <input class="btn btn-warning btn-sm" type="button" value="X"
                       onclick="estateVillageObj.cleanHTMLData(this)"></span>
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

    estateVillageObj.saveData = function (data, callback) {
        estateVillageObj.ajaxServerFun(data, "/basicEstateVillage/saveAndUpdateBasicEstateVillage", "post", callback, null);
    };

    estateVillageObj.deleteBasicEstateVillage = function (id, callback) {
        estateVillageObj.ajaxServerFun({id: id}, "/basicEstateVillage/deleteBasicEstateVillage", "post", callback, "delete");
    };
    estateVillageObj.getBasicEstateVillageList = function (query, callback) {
        estateVillageObj.ajaxServerFun(query, "/basicEstateVillage/basicEstateVillageList", "get", callback);
    };

    estateVillageObj.appendHTML = function (_this) {
        var table = $(_this).closest("table");
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        estateVillageObj.saveData({estateId: data.id}, function (id) {
            var html = estateVillageObj.replaceHtml({id: id});
            table.find("tbody").append(html);
            var districtName = "district" + id;
            AssessCommon.loadAreaAsyncInfoByPidAndValue(estateCommon.estateForm.find("select[name='city']").val(), '', function (html, data) {
                $(estateCommon.estateForm).find(".estateVillages").find("select[name=\"" + districtName + "\"]").empty().html(html).trigger('change');
            }, false);
        });
    };

    estateVillageObj.replaceHtml = function (data) {
        var html = $(estateVillageObj.htmlModel).html();
        html = html.replace(/{id}/g, data.id);
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

    estateVillageObj.cleanHTMLData = function (_this) {
        var tr = $(_this).closest("tr");
        var id = tr.find("input[data-name=id]").val();
        estateVillageObj.deleteBasicEstateVillage(id, function () {
            tr.remove();
        });
    };

    estateVillageObj.onblur = function (_this) {
        var value = $(_this).val();
        if (!value) {
            return false;
        }
        var tr = $(_this).closest("tr");
        var id = tr.find("input[data-name=id]").val();
        var villageStreet = tr.find("input[data-name=villageStreet]").val();
        var burgStreet = tr.find("input[data-name=burgStreet]").val();
        var district = tr.find("select[data-name=district]").val();
        var numberGroup = tr.find("input[data-name=numberGroup]").val();
        var data = {
            id: id,
            villageStreet: villageStreet,
            burgStreet: burgStreet,
            district: district,
            numberGroup: numberGroup
        };
        estateVillageObj.saveData(data);
    };

    estateVillageObj.init = function (estateId) {
        (function (table) {
            var frm = table.closest("form");
            var data = formSerializeArray(frm);
            if (!estateId) {
                estateId = data.id;
            }
            estateVillageObj.getBasicEstateVillageList({estateId: estateId}, function (data) {
                if (data.length >= 1) {
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateVillageObj.replaceHtml(item));
                        var districtName = "district" + item.id;
                        AssessCommon.loadAreaAsyncInfoByPidAndValue(estateCommon.estateForm.find("select[name='city']").val(), item.district, function (html, data) {
                            $(estateCommon.estateForm).find(".estateVillages").find("select[name='" + districtName + "']").empty().html(html).trigger('change');
                        }, false);
                    });
                    setTimeout(function () {
                        var html = "";
                        html += "<button class='btn btn-sm btn-success' type='button' onclick='estateVillageObj.appendHTML(this);'>";
                        html += "<i class='fa fa-plus'></i>";
                        html += "</button>";
                        table.find("tbody").find("tr").first().find("td").last().empty().append(html);
                    }, 100);
                } else {
                    estateVillageObj.saveData({estateId: estateId}, function (id) {
                        estateVillageObj.init(estateId);
                    });
                }
            });
            estateCommon.estateForm.find("select[name='city']").off('change').on('change', function () {
                estateVillageObj.getBasicEstateVillageList({estateId: estateId}, function (data) {
                    $.each(data, function (i, item) {
                        var districtName = "district" + item.id;
                        AssessCommon.loadAreaAsyncInfoByPidAndValue(estateCommon.estateForm.find("select[name='city']").val(), item.district, function (html, data) {
                            $(estateCommon.estateForm).find(".estateVillages").find("select[name='" + districtName + "']").empty().html(html).trigger('change');
                        }, false);
                    });

                });
            });
        }($(".estateVillages")));
    };


</script>


</html>
