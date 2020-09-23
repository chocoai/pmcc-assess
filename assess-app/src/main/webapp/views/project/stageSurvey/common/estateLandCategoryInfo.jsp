<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-15
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/method/module/projectLandAchievementGroup.jsp" %>



<script type="text/html" id="estateLandCategoryInfoToolbar">
    <tr>
        <td>
            <input type="hidden" data-name="id" value="{id}">
            <div class="input-group">
                <input type="text" required="required" name="landUseType{id}" data-name="landUseType" style="width: 50%"
                       onblur="estateLandCategoryInfo.onblur(this);" id="landUseType{id}"
                       class="form-control form-control-sm" value="{landUseType}">

                <div class="input-group-append">
                    <button class="btn btn-warning btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">选择</button>
                    <div class="dropdown-menu" id="landUseTypeList{id}">
                    </div>
                </div>
            </div>
        </td>
        <td>
            <div class="input-group">
                <input type="text" required="required" name="landUseCategory{id}" data-name="landUseCategory" style="width: 50%"
                       onblur="estateLandCategoryInfo.onblur(this);"
                       class="form-control form-control-sm" list="landUseCategoryList{id}" value="{landUseCategory}">
                
                <div class="input-group-append">
                    <button class="btn btn-warning btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onfocus="estateLandCategoryInfo.landUseTypeChange(this);">选择</button>
                    <div class="dropdown-menu" id="landUseCategoryList{id}">
                    </div>
                </div>
            </div>
        </td>
        <td>
            <input placeholder="土地取得时间"
                   name="acquisitionTime{id}" required data-name="acquisitionTime" style="width: 50%"
                   data-date-format="yyyy-mm-dd"
                   class="form-control form-control-sm input-full date-picker dbdate " value="{acquisitionTime}"
                   onblur="estateLandCategoryInfo.onblur(this);">
        </td>
        <td>
            <input placeholder="土地使用年限"  style="width: 50%" type="number"
                   name="landUseYear{id}" required data-name="landUseYear"
                   class="form-control form-control-sm input-full x-valid" value="{landUseYear}" onblur="estateLandCategoryInfo.onblur(this);">
        </td>
        <td>
            <div class="input-group">
                <input type="hidden" name="landLevel{id}" data-name="landLevel" value="{landLevel}"
                       onblur="estateLandCategoryInfo.onblur(this);">

                <input type="text" readonly="readonly" style="width: 30%"
                       onclick="examineCommon.landLevelSelect(this);"
                       placeholder="土地级别" class="form-control form-control-sm"
                       name="landLevelName{id}" data-name="landLevelName" required value="{landLevelName}">

                <div class="input-group-prepend">
                    <button class="btn btn-warning btn-sm "
                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                            type="button"
                            onclick="$(this).closest('.input-group').find('input').val('');">
                        清空
                    </button>
                </div>
                <div class="input-group-prepend">
                    <button class="btn btn-primary btn-sm "
                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                            type="button"
                            onclick="examineCommon.landLevelSelect(this);">
                        选择
                    </button>
                </div>
                <div class="input-group-prepend">
                    <button class="btn btn-info btn-sm "
                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                            type="button"
                            onclick="estateLandCategoryInfo.openLevelDetailModal(this);">
                        因素
                    </button>
                </div>

            </div>
        </td>
        <td>
            <span class="input-group-btn">
                <input class="btn btn-warning btn-sm" type="button" value="X"
                       onclick="estateLandCategoryInfo.cleanHTMLData(this)"></span>
        </td>
    </tr>
</script>

<script>


    var estateLandCategoryInfo = {};

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    estateLandCategoryInfo.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    estateLandCategoryInfo.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    //js数组去重复 ,直接重载在原生js上
    Array.prototype.deleteEle = function () {
        var newArr = this;
        for (var i = newArr.length - 1; i >= 0; i--) {
            var targetNode = newArr[i];
            for (var j = 0; j < i; j++) {
                if (targetNode == newArr[j]) {
                    newArr.splice(i, 1);
                    break;
                }
            }
        }
        return newArr;
    };


    estateLandCategoryInfo.fileUpload = function (target, tableName, id) {
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

    estateLandCategoryInfo.showFile = function (target, tableName, id) {
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

    estateLandCategoryInfo.htmlModel = "#estateLandCategoryInfoToolbar";
    estateLandCategoryInfo.fileId = "estateLandCategoryInfo";

    estateLandCategoryInfo.run = function (data, url, type, callback, funParams, errorCallback) {
        AssessCommon.run(data,url,type,callback,funParams,errorCallback) ;
    };
    estateLandCategoryInfo.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        AssessCommon.ajaxServerFun(data,url,type,callback ,funParams,errorCallback) ;
    };

    estateLandCategoryInfo.saveData = function (data, callback) {
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data)}, "/basicEstateLandCategoryInfo/saveAndUpdateBasicEstateLandCategoryInfo", "post", callback, null);
    };

    estateLandCategoryInfo.deleteBasicEstateStreetInfo = function (id, callback) {
        AssessCommon.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/deleteBasicEstateLandCategoryInfo", "post", callback, "delete");
    };
    estateLandCategoryInfo.getBasicEstateLandCategoryInfoById = function (id, callback) {
        AssessCommon.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/getBasicEstateLandCategoryInfoById", "get", callback);
    };
    estateLandCategoryInfo.getBasicEstateStreetInfoList = function (query, callback) {
        AssessCommon.ajaxServerFun(query, "/basicEstateLandCategoryInfo/basicEstateLandCategoryInfoList", "get", callback);
    };

    estateLandCategoryInfo.appendHTML = function (_this) {
        var table = $(_this).closest("table");
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        estateLandCategoryInfo.saveData({landId: data.id}, function (id) {
            var html = estateLandCategoryInfo.replaceHtml({id: id});
            table.find("tbody").append(html);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.estate_total_land_use, null, function (html, data) {
                var landUseTypeList = $("#landUseTypeList" + id);
                landUseTypeList.empty().html(html).trigger('change');
            }, true);

            estateLandCategoryInfo.showFile(estateLandCategoryInfo.fileId + id, AssessDBKey.BasicEstateLandCategoryInfo, id);
            estateLandCategoryInfo.fileUpload(estateLandCategoryInfo.fileId + id, AssessDBKey.BasicEstateLandCategoryInfo, id);
            DatepickerUtils.parse();
        });
    };

    estateLandCategoryInfo.replaceHtml = function (data) {
        var html = $(estateLandCategoryInfo.htmlModel).html();
        html = html.replace(/{id}/g, data.id);
        if (data.landUseType) {
            html = html.replace(/{landUseType}/g, data.landUseType);
        } else {
            html = html.replace(/{landUseType}/g, '');
        }
        if (data.landUseCategory) {
            html = html.replace(/{landUseCategory}/g, data.landUseCategory);
        } else {
            html = html.replace(/{landUseCategory}/g, '');
        }
        if (data.landUseYear) {
            html = html.replace(/{landUseYear}/g, data.landUseYear);
        } else {
            html = html.replace(/{landUseYear}/g, '');
        }
        if (data.acquisitionTime) {
            html = html.replace(/{acquisitionTime}/g, formatDate(data.acquisitionTime));
        } else {
            html = html.replace(/{acquisitionTime}/g, '');
        }
        if (data.landLevel) {
            html = html.replace(/{landLevel}/g, data.landLevel);
        } else {
            html = html.replace(/{landLevel}/g, '');
        }
        if (data.landLevelName) {
            html = html.replace(/{landLevelName}/g, data.landLevelName);
        } else {
            html = html.replace(/{landLevelName}/g, '');
        }
        return html;
    };

    estateLandCategoryInfo.cleanHTMLData = function (_this) {
        var tr = $(_this).closest("tr");
        var id = tr.find("input[data-name=id]").val();
        estateLandCategoryInfo.deleteBasicEstateStreetInfo(id, function () {
            tr.remove();
        });
    };

    estateLandCategoryInfo.onblur = function (_this) {
        var value = $(_this).val();
        if (!value) {
            return false;
        }
        var tr = $(_this).closest("tr");

        var id = tr.find("input[data-name=id]").val();
        var landUseType = tr.find("input[data-name=landUseType]").val();
        var landUseYear = tr.find("input[data-name=landUseYear]").val();
        var landUseCategory = tr.find("input[data-name=landUseCategory]").val();
        var acquisitionTime = tr.find("input[data-name=acquisitionTime]").val();
        var landLevel = tr.find("input[data-name=landLevel]").val();
        var landLevelName = tr.find("input[data-name=landLevelName]").val();
        var data = {
            id: id,
            landLevel: landLevel,
            landLevelName: landLevelName,
            landUseType: landUseType,
            landUseYear: landUseYear,
            landUseCategory: landUseCategory,
            acquisitionTime: acquisitionTime
        };
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        estateLandCategoryInfo.saveData(data);
    };

    estateLandCategoryInfo.landUseTypeChange = function (this_) {
        var tr = $(this_).closest("tr");
        var id = tr.find("input[data-name=id]").val();
        var value = $("#landUseType"+id).val() ;
        AssessCommon.getSonTextAppendDicList(AssessDicKey.estate_total_land_use, value, null, function (html, data) {
            $("#landUseCategoryList" + id).empty().html(html).trigger('change');
        });
    };

    estateLandCategoryInfo.init = function (landId) {
        (function (table) {
            var frm = table.closest("form");
            var data = formSerializeArray(frm);
            if (!landId) {
                landId = data.id;
            }
            table.find("tbody").empty() ;
            estateLandCategoryInfo.getBasicEstateStreetInfoList({landId: landId}, function (data) {
                if (data.length >= 1) {
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateLandCategoryInfo.replaceHtml(item));
                        estateLandCategoryInfo.showFile(estateLandCategoryInfo.fileId + item.id, AssessDBKey.BasicEstateLandCategoryInfo, item.id);
                        estateLandCategoryInfo.fileUpload(estateLandCategoryInfo.fileId + item.id, AssessDBKey.BasicEstateLandCategoryInfo, item.id);
                        AssessCommon.loadTextAppendDicHtml(AssessDicKey.estate_total_land_use, null, function (html, data) {
                            var landUseTypeList = $("#landUseTypeList" + item.id);
                            landUseTypeList.empty().html(html).trigger('change');
                        }, true);
                    });
                    setTimeout(function () {
                        var html = "";
                        html += "<button class='btn btn-sm btn-success' type='button' onclick='estateLandCategoryInfo.appendHTML(this);'>";
                        html += "<i class='fa fa-plus'></i>";
                        html += "</button>";
                        table.find("tbody").find("tr").first().find("td").last().empty().append(html);
                        DatepickerUtils.parse();
                    }, 100);
                } else {
                    estateLandCategoryInfo.saveData({landId: landId}, function (id) {
                        estateLandCategoryInfo.init(landId);
                    });
                }
            });
        }($(".basicEstateLandCategoryInfo")));
    };


    estateLandCategoryInfo.openLevelDetailModal = function (this_) {
        var group = $(this_).closest('.input-group');
        var tr = $(this_).closest("tr");
        var id = tr.find('input[data-name="id"]').val();
        var landLevelId = group.find('input[data-name="landLevel"]').val();
        if (!landLevelId) {
            notifyWarning("提示", "请选择土地级别!");
            return false;
        }
        var projectId = '${applyBatchDetail.projectId}' ;
        var option = {projectId:projectId,levelDetailId:landLevelId ,dataTableId:id,dataTableName:AssessDBKey.BasicEstateLandCategoryInfo} ;
        landAchievementGroup.openLevelDetailModal(option,true ) ;
        // console.log(option) ;

    } ;



</script>