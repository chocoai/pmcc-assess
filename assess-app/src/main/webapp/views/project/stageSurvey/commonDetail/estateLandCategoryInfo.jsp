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

            <label class="form-control input-full" >{landUseType}</label>

        </td>
        <td>
            <label class="form-control input-full" >{landUseCategory}</label>
        </td>
        <td>
            <label class="form-control input-full" >{acquisitionTime}</label>
        </td>
        <td>
            <label class="form-control input-full" >{landUseYear}</label>
        </td>
        <td>
            <div class="input-group">
                <input type="hidden" name="landLevel{id}" data-name="landLevel" value="{landLevel}">
                <label class="form-control" >{landLevelName}</label>
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



    estateLandCategoryInfo.getBasicEstateLandCategoryInfoById = function (id, callback) {
        estateLandCategoryInfo.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/getBasicEstateLandCategoryInfoById", "get", callback);
    };

    estateLandCategoryInfo.getBasicEstateStreetInfoList = function (query, callback) {
        estateLandCategoryInfo.ajaxServerFun(query, "/basicEstateLandCategoryInfo/basicEstateLandCategoryInfoList", "get", callback);
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




    estateLandCategoryInfo.init = function (landId) {
        (function (table) {
            var frm = table.closest("form");
            var data = formSerializeArray(frm);
            if (!landId) {
                landId = data.id;
            }
            estateLandCategoryInfo.getBasicEstateStreetInfoList({landId: landId}, function (data) {
                if (data.length >= 1) {
                    $.each(data, function (i, item) {
                        table.find("tbody").append(estateLandCategoryInfo.replaceHtml(item));
                        estateLandCategoryInfo.showFile(estateLandCategoryInfo.fileId + item.id, AssessDBKey.BasicEstateLandCategoryInfo, item.id);
                        estateLandCategoryInfo.fileUpload(estateLandCategoryInfo.fileId + item.id, AssessDBKey.BasicEstateLandCategoryInfo, item.id);
                    });
                    setTimeout(function () {
                        DatepickerUtils.parse();
                    }, 100);
                }
            });
        }($(".basicEstateLandCategoryInfo")));
    };





    //土地因素
    estateLandCategoryInfo.openLevelDetailModal = function (this_) {
        var group = $(this_).closest('.input-group');
        var tr = $(this_).closest("tr");
        var id = tr.find('input[data-name="id"]').val();
        var landLevelId = group.find('input[data-name="landLevel"]').val();
        if (!landLevelId) {
            notifyWarning("提示", "无土地级别!");
            return false;
        }
        var projectId = '${applyBatchDetail.projectId}' ;
        var option = {projectId:projectId,levelDetailId:landLevelId ,dataTableId:id,dataTableName:AssessDBKey.BasicEstateLandCategoryInfo} ;
        landAchievementGroup.openLevelDetailModal(option,false ) ;
        // console.log(option) ;
    };



</script>