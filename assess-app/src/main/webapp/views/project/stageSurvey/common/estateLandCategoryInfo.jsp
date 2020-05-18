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
            <select class="form-control input-full" name="landLevelGrade"
                    onchange="estateLandCategoryInfo.landLevelHandle(this);">
                {landLevelGradeHTML}
            </select>
        </td>
        <td>
            {reamark}
            <%--<label name="reamark" class="form-control input-full">{reamark}</label>--%>
        </td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">
            <input type="text" class="form-control input-full x-percent" name="landFactorTotalScore"
                   value="{landFactorTotalScore}">
            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="estateLandCategoryInfo.landLevelEmpty(this)">
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="estateLandCategoryInfo.saveLandLevelTabContent()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


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

                <input type="hidden" name="landFactorTotalScore{id}" data-name="landFactorTotalScore"
                       value="{landFactorTotalScore}" onblur="estateLandCategoryInfo.onblur(this);">

                <input type="hidden" name="landLevelContentResult{id}" data-name="landLevelContentResult"
                       value="{landLevelContentResult}" onblur="estateLandCategoryInfo.onblur(this);">

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

    estateLandCategoryInfo.saveData = function (data, callback) {
        estateLandCategoryInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/basicEstateLandCategoryInfo/saveAndUpdateBasicEstateLandCategoryInfo", "post", callback, null);
    };

    estateLandCategoryInfo.deleteBasicEstateStreetInfo = function (id, callback) {
        estateLandCategoryInfo.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/deleteBasicEstateLandCategoryInfo", "post", callback, "delete");
    };
    estateLandCategoryInfo.getBasicEstateLandCategoryInfoById = function (id, callback) {
        estateLandCategoryInfo.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/getBasicEstateLandCategoryInfoById", "get", callback);
    };
    estateLandCategoryInfo.getBasicEstateStreetInfoList = function (query, callback) {
        estateLandCategoryInfo.ajaxServerFun(query, "/basicEstateLandCategoryInfo/basicEstateLandCategoryInfoList", "get", callback);
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
        var landFactorTotalScore = tr.find("input[data-name=landFactorTotalScore]").val();
        var landLevelContentResult = tr.find("input[data-name=landLevelContentResult]").val();
        var landLevel = tr.find("input[data-name=landLevel]").val();
        var landLevelName = tr.find("input[data-name=landLevelName]").val();
        var data = {
            id: id,
            landFactorTotalScore: landFactorTotalScore,
            landLevel: landLevel,
            landLevelName: landLevelName,
            landLevelContentResult: landLevelContentResult,
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
//        console.log(data);
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



    /**
     * 获取数组中随机的一个对象或者数组中曾经被选中的那个对象,原则是在收集数据的时候对选中元素进行添加元素属性
     * @param obj
     * @returns {*}
     */
    estateLandCategoryInfo.getLandLevelFilter = function (obj) {
        var random = Math.random() * (obj.length - 1);
        random = Math.round(random);
        random = 0;
        var objArray = [];
        obj.forEach(function (data, index) {
            //这里主要是获取对象长度
            var arr = Object.keys(data);
            objArray.push(arr.length);
        });
        if (objArray.deleteEle().length >= 2) {
            //说明修改过一次
            objArray.sort(function (a, b) {
                return a - b;
            });
            //获取有最大属性值的那个对象
            var max = objArray[objArray.length - 1];
            obj.forEach(function (data, index) {
                //这里主要是获取对象长度
                var arr = Object.keys(data);
                if (max == arr.length) {
                    return data;
                }
            });
        }
        var item = obj[random];
        return item;
    };

    //删除
    estateLandCategoryInfo.landLevelEmpty = function (that) {
        $(that).parent().parent().remove();
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


    //change 事件 随着等级变化页面展示不同内容
    estateLandCategoryInfo.landLevelHandle = function (that) {
        var group = $(that).closest('.group');
        var landLevelContent = group.find("input[name='landLevelContent']").val();
        var obj = {};
        try {
            obj = JSON.parse(landLevelContent);
        } catch (e) {
        }
        if (estateLandCategoryInfo.isNotBlankObject(obj)) {
            AssessCommon.getDataDicInfo($(that).val(), function (item) {
                obj.forEach(function (data, index) {
                    if (data.gradeName == item.name) {
                        group.find("input[name='landFactorTotalScore']").val(AssessCommon.pointToPercent(data.achievement));
                        group.find("input[name='dataLandLevelAchievement']").val(data.id);
                    }
                });
            });
        }
    };


    //土地因素
    estateLandCategoryInfo.openLevelDetailModal = function (this_) {
        var group = $(this_).closest('.input-group');
        var tr = $(this_).closest("tr");
        var id = tr.find('input[data-name="id"]').val();
        var landLevelId = group.find('input[data-name="landLevel"]').val();
        if (!landLevelId) {
            notifyWarning("提示", "请选择土地级别!");
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
                estateLandCategoryInfo.landLevelLoadHtml(data, true, this_);
            } else {
                //新增
                estateLandCategoryInfo.ajaxServerFun({levelDetailId: landLevelId}, "/dataLandLevelDetailAchievement/landLevelFilter", "get", function (dataAll) {
                    estateLandCategoryInfo.landLevelLoadHtml(dataAll, false, this_);
                });
            }
        });

    };

    estateLandCategoryInfo.landLevelLoadHtml = function (data, beEcho, this_) {
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
                } else {
                    item = estateLandCategoryInfo.getLandLevelFilter(obj);
                }

                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.categoryName);
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
        estateLandCategoryInfo.option = {this_: this_};
    };


    estateLandCategoryInfo.saveLandLevelTabContent = function () {
        var landLevelContent = [];
        var landFactorTotalScoreResult = 0;
        $("#landLevelContentFrm").find("input[name='landLevelContent']").each(function (i, n) {
            var group = $(n).closest(".group");
            var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
            var landFactorTotalScore = AssessCommon.percentToPoint(group.find("input[name='landFactorTotalScore']").val());
            landFactorTotalScoreResult += Number(landFactorTotalScore);
            var obj = JSON.parse($(n).val());
            var dataObject = [];
            obj.forEach(function (value, index) {
                if (value.id == dataLandLevelAchievement) {
                    value.modelStr = "update";
                    value.achievement = landFactorTotalScore;
                } else {
                    delete value.modelStr;
                }
                dataObject.push(value);
            });
            landLevelContent.push(dataObject);
        });
        if (estateLandCategoryInfo.option) {
            var group = $(estateLandCategoryInfo.option.this_).closest('.input-group');
            group.find("input[data-name=landLevelContentResult]").val(JSON.stringify(landLevelContent));
            group.find("input[data-name=landFactorTotalScore]").val(landFactorTotalScoreResult).trigger('onblur');
            estateLandCategoryInfo.option = null;
        }
        $("#detailAchievementModal").modal('hide');
    };



</script>