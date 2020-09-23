<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-9-22
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 因素条件说明及修正系数 公共页 -->

<!-- 因素详情 -->
<script type="text/html" id="landLevelTabContentDetailBody">
    <tr class="group">
        <td class="table-cell">
            {landLevelTypeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            {landSelectGrade}
        </td>
        <td>
            {reamark}
        </td>
        <td>
            {landFactorTotalScore}
        </td>
        <td>
        </td>
    </tr>
</script>

<!-- 因素申请 -->
<script type="text/html" id="landLevelTabContentApplyBody">
    <tr class="group">
        <td class="table-cell">
            {landLevelTypeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            <select class="form-control input-full" name="landLevelGrade"
                    onchange="landAchievementGroup.landLevelHandle(this);">
                {landLevelSelectHTML}
            </select>
        </td>
        <td>
            {reamark}
        </td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">

            <input type="text" class="form-control input-full x-percent" name="landFactorTotalScore"
                   onblur="landAchievementGroup.blurEvent(this);"
                   value="{landFactorTotalScore}">
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="landAchievementGroup.landLevelEmpty(this)">
        </td>
    </tr>
</script>

<!-- 因素申请 并且适用于测算方法-->
<script type="text/html" id="landLevelTabContentMethodBody">
    <tr class="group">
        <td class="table-cell">
            {landLevelTypeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            <select class="form-control input-full" name="landLevelGrade"
                    onchange="landAchievementGroup.landLevelHandle(this);">
                {landLevelSelectHTML}
            </select>
        </td>
        <td>
            <%--<span style="margin-left: 2px;cursor: pointer;" title="{reamark}"><span class="btn-label"><i--%>
                    <%--class="fa fa-question-circle"></i></span>--%>
            <%--</span>--%>
            {reamark}
        </td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">

            <input type="text" class="form-control input-full x-percent" name="landFactorTotalScore"
                   onblur="landAchievementGroup.blurEvent(this);"
                   value="{landFactorTotalScore}">
        </td>
        <td>
        </td>
    </tr>
</script>


<div id="detailAchievementModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="col-md-12">
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
                            <tbody></tbody>
                        </table>
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


    var landAchievementGroup = {};

    landAchievementGroup.callbackMethod = null;

    /**
     * 初始化
     * @param levelDetailId
     * @param projectId
     * @param dataTableId
     * @param dataTableName
     * @param callback
     */
    landAchievementGroup.initProjectLandAchievementGroup = function (levelDetailId, projectId, dataTableId, dataTableName, callback) {
        var param = {
            levelDetailId: levelDetailId,
            projectId: projectId,
            dataTableId: dataTableId,
            dataTableName: dataTableName
        };
        AssessCommon.ajaxServerMethod(param, "/projectLandAchievementGroup/initProjectLandAchievementGroup", "post", callback);
    };

    /**
     * 更新
     * @param param
     * @param callback
     */
    landAchievementGroup.updateProjectLandAchievementGroup = function (param, callback) {
        AssessCommon.ajaxServerMethod({formData: JSON.stringify(param)}, "/projectLandAchievementGroup/updateProjectLandAchievementGroup", "post", callback);
    };

    /**
     * 删除
     */
    landAchievementGroup.deleteProjectLandAchievementGroupByIds = function (id, callback) {
        AssessCommon.ajaxServerMethod({id: id}, "/projectLandAchievementGroup/deleteProjectLandAchievementGroupByIds", "post", callback);
    };

    /**
     * 获取初始化过的数据
     * @param projectId
     * @param dataTableId
     * @param dataTableName
     * @param callback
     */
    landAchievementGroup.getInitProjectLandAchievementGroupData = function (projectId, dataTableId, dataTableName, callback) {
        var param = {projectId: projectId, dataTableId: dataTableId, dataTableName: dataTableName};
        AssessCommon.ajaxServerMethod(param, "/projectLandAchievementGroup/getInitProjectLandAchievementGroupData", "get", callback);
    };

    /*
    获取统计数据
    */
    landAchievementGroup.countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId = function (projectId, dataTableId, dataTableName, callback) {
        var param = {projectId: projectId, dataTableId: dataTableId, dataTableName: dataTableName};
        AssessCommon.ajaxServerMethod(param, "/projectLandAchievementGroup/countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId", "get", callback);
    };

    //删除
    landAchievementGroup.landLevelEmpty = function (that) {
        var tr = $(that).closest('tr');
        AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function () {
            var id = tr.find("[name=dataLandLevelAchievement]").val();
            landAchievementGroup.deleteProjectLandAchievementGroupByIds(id, function () {
                tr.remove();
            });
        });
    };


    //change 事件 随着等级变化页面展示不同内容
    landAchievementGroup.landLevelHandle = function (that) {
        var tr = $(that).closest('tr');
        var ele = tr.find("select option:selected");
        var selectId = ele.val();
        var selectValue = ele.attr("key");
        var id = tr.find("[name=dataLandLevelAchievement]").val();
        if (!selectId) {
            return false;
        }
        var data = {id: id, selectId: selectId, selectValue: selectValue};
        tr.find("input[name='landFactorTotalScore']").val(AssessCommon.pointToPercent(selectValue));
        landAchievementGroup.updateProjectLandAchievementGroup(data, function () {
            if (landAchievementGroup.callbackMethod) {
                landAchievementGroup.callbackMethod();
            }
        });
    };

    landAchievementGroup.blurEvent = function (that) {
        var tr = $(that).closest('tr');
        var id = tr.find("[name=dataLandLevelAchievement]").val();
        var selectValue = $(that).val();
        selectValue = AssessCommon.percentToPoint(selectValue);
        var data = {id: id, selectValue: selectValue};
        landAchievementGroup.updateProjectLandAchievementGroup(data, function () {
            if (landAchievementGroup.callbackMethod) {
                landAchievementGroup.callbackMethod();
            }
        });
    };


    //土地因素
    landAchievementGroup.openLevelDetailModal = function (option, flag) {
        landAchievementGroup.initProjectLandAchievementGroup(option.levelDetailId, option.projectId, option.dataTableId, option.dataTableName, function (dataAll) {
            if (flag) {
                landAchievementGroup.applyLoadHtml(dataAll);
            } else {
                landAchievementGroup.detailLoadHtml(dataAll);
            }
        });
    };

    /**
     * 基本调用
     */
    landAchievementGroup.applyLoadHtml = function (dataAll) {
        var box = $("#detailAchievementModal");
        box.modal("show");
        var target = box.find("table").find("tbody");
        var landLevelBodyHtml = $("#landLevelTabContentApplyBody").html();
        landAchievementGroup.landLevelLoadHtml(dataAll, target, landLevelBodyHtml);
    };

    /*基本显示详情*/
    landAchievementGroup.detailLoadHtml = function (dataAll) {
        var box = $("#detailAchievementModal");
        box.modal("show");
        var target = box.find("table").find("tbody");
        var landLevelBodyHtml = $("#landLevelTabContentDetailBody").html();
        landAchievementGroup.landLevelLoadHtml(dataAll, target, landLevelBodyHtml);
    };

    /**
     * 测算方法 调用土地因素 测算
     * @param dataAll
     * @param table
     * @param callbackMethod 回调方法
     */
    landAchievementGroup.applyMethodLoadHtml = function (dataAll, table, callbackMethod) {
        var target = table.find("tbody");
        var landLevelBodyHtml = $("#landLevelTabContentMethodBody").html();
        landAchievementGroup.landLevelLoadHtml(dataAll, target, landLevelBodyHtml);
        landAchievementGroup.callbackMethod = callbackMethod;
        //加载完毕 调用第一次 ,其它调用机会由事件触发
        if (landAchievementGroup.callbackMethod) {
            landAchievementGroup.callbackMethod();
        }
    };

    /**
     * 测算方法 调用土地因素 显示
     * @param dataAll
     * @param table
     */
    landAchievementGroup.detailMethodLoadHtml = function (dataAll, table) {
        var target = table.find("tbody");
        var landLevelBodyHtml = $("#landLevelTabContentDetailBody").html();
        landAchievementGroup.landLevelLoadHtml(dataAll, target, landLevelBodyHtml);
    };

    /**
     * 构建html
     * @param data
     * @param target
     * @param html
     */
    landAchievementGroup.landLevelLoadHtml = function (data, target, html) {
        target.empty();
        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                //默认选中最优
                var landLevelBodyHtml = "" + html;
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, obj.id);
                    var landLevelTypeName = obj.type;
                    if (obj.classification) {
                        landLevelTypeName += "/" + obj.classification;
                    }
                    if (obj.category) {
                        landLevelTypeName += "/" + obj.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, obj.remark);
                    var array = [];
                    try {
                        array = JSON.parse(obj.keyValue);
                    } catch (e) {
                        console.error(e);
                    }
                    if (obj.selectValue) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(obj.selectValue));
                    } else {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, '');
                    }
                    var landSelectGrade = "";
                    if (obj.selectId) {
                        $.each(array, function (k, item) {
                            if (item.id == obj.selectId) {
                                landSelectGrade = item.key;
                            }
                        });
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landSelectGrade}/g, landSelectGrade);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelSelectHTML}/g, landAchievementGroup.getSelectHtmlByArray(array, obj.selectId));
                    target.append(landLevelBodyHtml);
                }
            });
        });
    };

    landAchievementGroup.getSelectHtmlByArray = function (array, value) {
        var retHtml = "";
        retHtml += '<option value="" selected>-请选择-</option>';
        if ($.isArray(array)) {
            if (array.length != 0) {
                $.each(array, function (i, item) {
                    retHtml += '<option key="' + item.achievement + '" title="' + item.value + '" value="' + item.id + '"';
                    if (value) {
                        if (item.id == value) {
                            retHtml += 'selected="selected"';
                        }
                    }
                    retHtml += '>' + item.key + '</option>';
                });
            }
        }
        return retHtml;
    };

</script>
