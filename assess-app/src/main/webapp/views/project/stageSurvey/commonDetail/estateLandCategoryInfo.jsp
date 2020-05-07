<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-15
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td class="table-cell">
            {landLevelTypeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            <select class="form-control input-full" name="landLevelGrade" disabled>
                {landLevelGradeHTML}
            </select>
        </td>
        <td>
            {reamark}
        </td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">

            <input type="text" class="form-control input-full x-percent" name="landFactorTotalScore"
                   value="{landFactorTotalScore}" readonly>

            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>

        </td>
    </tr>
</script>


<div id="detailAchievementModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="landLevelContentFrm">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="10%">土地级别类型类别</th>
                                        <th width="10%">土地级别等级</th>
                                        <th width="30%">说明</th>
                                        <th width="10%">分值</th>
                                        <th width="5%"></th>
                                    </tr>
                                    </thead>
                                    <tbody id="landLevelTabContent"></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>


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

                <input type="hidden" name="landFactorTotalScore{id}" data-name="landFactorTotalScore"
                       value="{landFactorTotalScore}">

                <input type="hidden" name="landLevelContentResult{id}" data-name="landLevelContentResult"
                       value="{landLevelContentResult}" >

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
    estateLandCategoryInfo.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                estateLandCategoryInfo.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            estateLandCategoryInfo.run(data, url, type, callback, funParams, errorCallback);
        }
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
        if (data.landFactorTotalScore) {
            html = html.replace(/{landFactorTotalScore}/g, data.landFactorTotalScore);
        } else {
            html = html.replace(/{landFactorTotalScore}/g, '');
        }
        if (data.landLevelContentResult) {
            html = html.replace(/{landLevelContentResult}/g, data.landLevelContentResult);
        } else {
            html = html.replace(/{landLevelContentResult}/g, '');
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





    /**
     * 土地级别详情分类
     * @param list
     * @returns {Array}
     */
    estateLandCategoryInfo.landLevelFilter = function (list) {
        var flag = 0, data = [];
        for (var i = 0; i < list.length; i++) {
            var az = '';
            for (var j = 0; j < data.length; j++) {
                if (data[j][0].type == list[i].type) {
                    flag = 1;
                    az = j;
                    break;
                }
            }
            if (flag == 1) {
                data[az].push(list[i]);
                flag = 0;
            } else if (flag == 0) {
                var wdy = [];
                wdy.push(list[i]);
                data.push(wdy);
            }
        }
        return data;
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
        estateLandCategoryInfo.getBasicEstateLandCategoryInfoById(id, function (result) {
            var data = [];
            if (result.landLevelContentResult) {
                try {
                    data = JSON.parse(result.landLevelContentResult);
                } catch (e) {
                    console.log(e);
                }
                //过滤 分组
                data = estateLandCategoryInfo.landLevelFilter(data);
            }
            if (data.length >= 1) {
                //编辑
                estateLandCategoryInfo.landLevelLoadHtml(data, true);
            }else {
                notifyWarning("提示", "无土地因素数据!");
            }
        });

    };

    estateLandCategoryInfo.landLevelLoadHtml = function (data, beEcho) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        $("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                //默认选中最优
                var item;
                if (beEcho) {
                    obj.forEach(function (value, index) {
                        if (value.modelStr == "update") {
                            item = value;
                        }
                    })
                }

                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    //landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.categoryName);
                    var landLevelTypeName = item.typeName;
                    if(item.classification){
                        landLevelTypeName+="/"+item.classification;
                    }
                    if(item.categoryName){
                        landLevelTypeName+="/"+item.categoryName;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
                }
            });

        });
    };



</script>