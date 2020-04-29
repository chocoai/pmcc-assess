<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="categoryDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                        土地类型类别（_number）
                        <small>
                        </small>
                    </div>
                    <div class="card-tools">
                        <button class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" id="categoryFrm_number">
                    <input type="hidden" name="id">
                    <input type="hidden" name="landUseTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地用途类型</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landUseType"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地用途类别</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landUseCategory"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地级别</label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                <input type="hidden" name="landLevel">
                                                <label type="text" name="landLevelName"
                                                       class="form-control"></label>
                                                <div class="input-group-prepend">
                                                    <button class="btn btn-info btn-sm "
                                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                            type="button"
                                                            onclick="landUseType.openLevelDetailModal(this);">
                                                        因素
                                                    </button>
                                                </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地取得时间</label>
                                            <div class="col-sm-3">
                                                <label name="acquisitionTime" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地使用年限</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landUseYear"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地形状</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landShape"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">开发时间</label>
                                            <div class="col-sm-3">
                                                <label name="developTime" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">容积率</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="plotRatio"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">建筑密度</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="buildingDensity"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">绿地率</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="greeningRate"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">兼容类型</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="compatibilityType"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">兼容比例</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="compatibilityRate"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">建筑限高</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="heightPermitted"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
        </div>

    </div>
</script>

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

<script type="text/javascript">
    $(function () {
        landUseType.loadSonDataListHtml();
    });

    var LandUseType = function () {

    };
    LandUseType.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_LandUseTypeList";
            },
            box: function () {
                return "divBoxUseTypeFather";
            }
        },
        son: {
            frm: function () {
                return "frmSon";
            },
            table: function () {
                return "tb_LandUseCategoryList";
            },
            box: function () {
                return "divBoxUseCategorySon";
            },
            tableBox: function () {
                return "LandUseCategoryListBox";
            },
            tableFrm: function () {
                return "LandUseCategoryListBoxFrm";
            },
        }
    }
    LandUseType.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    var landUseType = new LandUseType();

    var num = 1;

    landUseType.loadSonDataListHtml = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandCategoryInfo/basicEstateLandCategoryInfoList",
            type: "get",
            dataType: "json",
            data: {landId: estateCommon.estateLandStateForm.find('input[name="id"]').val()},
            success: function (result) {
                if (result.ret) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                var html = $("#categoryDiv").html();
                                var number = num;
                                html = html.replace(/_number/g, number);
                                $("#LandUseTypeContent").append(html);
                                $("#categoryFrm" + number).initForm(item);
                                //百分字段
                                $("#categoryFrm" + number).find('[name=greeningRate]').text(AssessCommon.pointToPercent(item.greeningRate));
                                $("#categoryFrm" + number).find('[name=compatibilityRate]').text(AssessCommon.pointToPercent(item.compatibilityRate));
                               
                                num++;
                            });
                        }
                    }
                }
                else {
                    ("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }


    //土地因素
    landUseType.openLevelDetailModal = function (this_) {
        var form = $(this_).closest("form");
        var id = form.find('input[name="id"]').val();
        var landLevelId = form.find('input[name="landLevel"]').val();
        if (!landLevelId) {
            notifyWarning("提示", "无土地级别!");
            return false;
        }
        landUseType.getBasicEstateLandUseById(id, function (result) {
            var data = [];
            if (result.landLevelContentResult) {
                try {
                    data = JSON.parse(result.landLevelContentResult);
                } catch (e) {
                    console.log(e);
                }
                //过滤 分组
                data = landUseType.landLevelFilter(data);
            }
            if (data.length >= 1) {
                //编辑
                landUseType.landLevelLoadHtml(data, true);
            }else {
                notifyWarning("提示", "无土地因素数据!");
            }
        });

    };

    landUseType.landLevelLoadHtml = function (data, beEcho) {
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

    landUseType.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                landUseType.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            landUseType.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    landUseType.run = function (data, url, type, callback, funParams, errorCallback) {
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

    landUseType.landLevelFilter = function (list) {
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

    landUseType.getBasicEstateLandUseById = function (id, callback) {
        landUseType.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/getBasicEstateLandCategoryInfoById", "get", callback);
    };
</script>


