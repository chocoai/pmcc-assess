
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="declareEconomicIndicatorsBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 1100px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title">规划指标</h3>
            </div>
            <div class="modal-body">
                <form id="declareEconomicIndicatorsFrm" class="form-horizontal">
                    <table class="table tree">
                        <thead>
                        <tr>
                            <th>名称</th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="treegrid-1" data-key="netLandArea">
                            <td>一、规划建设净用地面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text"  readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2" data-key="planTotalArea">
                            <td>二、规划总建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-1 treegrid-parent-2" data-key="groundBuildingArea">
                            <td>（一）地上计入容积率的建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-1-1 treegrid-parent-2-1" data-key="houseBuildingArea">
                            <td>1、住宅建筑面积
                            </td>
                            <th>户型</th>
                            <th>户型面积</th>
                            <th>户数</th>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-1-2 treegrid-parent-2-1" data-key="nonHouseBuildingArea">
                            <td>2、非住宅建筑面积

                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>

                        <tr class="treegrid-2-2 treegrid-parent-2" data-key="groundExcludBuildingArea">
                            <td>（二）地上不计入容积率的建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-2-1 treegrid-parent-2-2" data-key="overheadBuildingArea">
                            <td>1、首层架空建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text"  readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-2-2 treegrid-parent-2-2" data-key="heatBuildingArea">
                            <td>2、外保温建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text"  readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-3 treegrid-parent-2" data-key="undergroundBuildingArea">
                            <td>（三）地下建筑面积及层数

                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-3" data-key="volumetricRate">
                            <td>三、容积率</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text"  readonly="readonly" disabled="disabled"name="content" data-rule-number="true" style="width: 100px;"></td>
                        </tr>
                        <tr class="treegrid-4">
                            <td>四、建筑基底面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-4-1 treegrid-parent-4" data-key="buildingBaseTotalArea">
                            <td>建筑基地总面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-4-2 treegrid-parent-4" data-key="highMainBaseArea">
                            <td>高层主体基底（基座）面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-5">
                            <td>五、建筑密度</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-5-1 treegrid-parent-5" data-key="totalBuildingDensity">
                            <td>总建筑密度</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> %</td>
                        </tr>
                        <tr class="treegrid-5-1 treegrid-parent-5" data-key="highMainBuildingDensity">
                            <td>高层主体建筑密度</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> %</td>
                        </tr>
                        <tr class="treegrid-6" data-key="greenSpaceRate">
                            <td>六、绿地率</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> %</td>
                        </tr>
                        <tr class="treegrid-6-1 treegrid-parent-6" data-key="greenArea">
                            <td>绿地面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7" data-key="mobileParkingSpace">
                            <td>七、机动车位</td>
                            <td></td>
                            <td></td>
                            <td>数量</td>
                            <td>面积</td>
                        </tr>
                        <tr class="treegrid-7-1 treegrid-parent-7" data-key="undergroundParkingSpace">
                            <td>（一）地下停车位

                            </td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7-1-1 treegrid-parent-7-1" data-key="residentialParkingSpace">
                            <td>（1）住宅停车位</td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7-1-2 treegrid-parent-7-1" data-key="commercialParkingSpace">
                            <td>（2）商业停车位</td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7-1-3 treegrid-parent-7-1" data-key="parkingFacilitie">
                            <td>（3）配套设施停车位

                            </td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7-1-3-1 treegrid-parent-7-1-3" data-key="propertyRoomParking">
                            <td>物管用房停车位</td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text"  readonly="readonly" disabled="disabled"name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7-1-3-2 treegrid-parent-7-1-3" data-key="publicHousingParking">
                            <td>社区公共服务用房停车位</td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7-1-3-3 treegrid-parent-7-1-3" data-key="intelligentCommunityParking">
                            <td>智慧小区用房停车位</td>
                            <td></td>
                            <td></td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                            <td><input type="text" readonly="readonly" disabled="disabled" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<%--住宅面积模板--%>
<script type="text/html" id="residentialAreaHtml">
    <tr class="dynamic treegrid-2-1-1-1 treegrid-parent-2-1-1">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td><input type="text" name="huxing" style="width: 100px;"></td>
        <td><input type="text" name="huxingArea" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" name="householdCount" data-rule-digits="true" style="width: 100px;"></td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--非住宅面积模板--%>
<script type="text/html" id="nonResidentialAreaHtml">
    <tr class="dynamic treegrid-2-1-2-1 treegrid-parent-2-1-2">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--地下建筑面积模板--%>
<script type="text/html" id="substructureAreaHtml">
    <tr class="dynamic treegrid-2-3-1 treegrid-parent-2-3">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--地下停车位模板--%>
<script type="text/html" id="parkingSpaceHtml">
    <tr class="dynamic treegrid-7-1-1 treegrid-parent-7-1">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--配套设施停车位模板--%>
<script type="text/html" id="supportingFacilitieHtml">
    <tr class="dynamic treegrid-7-1-3-1 treegrid-parent-7-1-3">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<script type="text/javascript">
    $(function () {
        economicIndicators.treegirdParse();
    })

    var economicIndicators = {};
    //树形表格解析
    economicIndicators.treegirdParse = function () {
        $('#declareEconomicIndicatorsBox').find('.tree').treegrid();
    }

    //添加子项
    economicIndicators.addChild = function (_this, template) {
        var tr = $(_this).closest('tr');
        var childs = tr.treegrid('getChildNodes');
        if (childs.length <= 0) {
            tr.after($('#' + template).html());
        } else {
            //如果最后一个子项下还有子项则在子项的子项后添加元素
            var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
            if (subChilds.length <= 0) {
                $(childs.get(childs.length - 1)).after($('#' + template).html());
            } else {
                $(subChilds.get(subChilds.length - 1)).after($('#' + template).html());
            }
        }
        economicIndicators.treegirdParse();
    }

    //获取表单数据
    economicIndicators.getFormData = function () {
        var dataArray = [];
        $("#declareEconomicIndicatorsFrm").find('tbody tr[data-key]').each(function () {
            var data = {};
            data.customKey = $(this).attr('data-key');
            data.content = $(this).find('[name=content]').val();
            if ($(this).find('[name=number]').length > 0) {
                data.number = $(this).find('[name=number]').val();
            }
            switch (data.customKey) {
                case "houseBuildingArea":
                case "nonHouseBuildingArea":
                case "undergroundBuildingArea":
                case "undergroundParkingSpace":
                case "parkingFacilitie":
                    var nodes = $(this).treegrid('getChildNodes');
                    if (nodes && nodes.length > 0) {
                        var childDataArray = [];
                        $.each(nodes, function (i, item) {
                            var child = {};
                            child.name = $(item).find('[name=name]').val();
                            if (child.name) {
                                if ($(item).find('[name=huxing]').length > 0) {
                                    child.huxing = $(item).find('[name=huxing]').val();
                                }
                                if ($(item).find('[name=huxingArea]').length > 0) {
                                    child.huxingArea = $(item).find('[name=huxingArea]').val();
                                }
                                if ($(item).find('[name=householdCount]').length > 0) {
                                    child.householdCount = $(item).find('[name=householdCount]').val();
                                }
                                if ($(item).find('[name=number]').length > 0) {
                                    child.number = $(item).find('[name=number]').val();
                                }
                                child.content = $(item).find('[name=content]').val();
                                childDataArray.push(child);
                            }
                        })
                        data.childData = JSON.stringify(childDataArray);
                    }
                    break;
                default:

                    break;
            }
            dataArray.push(data);
        })
        return dataArray;
    }

    //填充表单数据
    economicIndicators.initForm = function (pid, callback) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/economicIndicators/getEntityListByPid",
            type: 'post',
            data: {
                pid: pid
            },
            dataType: 'json',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $.each(result.data, function (i, item) {
                        var tr = $('#declareEconomicIndicatorsFrm').find('tr[data-key=' + item.customKey + ']');
                        tr.find('[name=content]').val(item.content);
                        if (tr.find('[name=number]').length > 0) {
                            tr.find('[name=number]').val(item.number);
                        }
                        if (item.childData) {
                            var template = undefined;
                            switch (item.customKey) {
                                case "houseBuildingArea":
                                    template = $("#residentialAreaHtml").html();
                                    break
                                case "nonHouseBuildingArea":
                                    template = $("#nonResidentialAreaHtml").html();
                                    break;
                                case "undergroundBuildingArea":
                                    template = $("#substructureAreaHtml").html();
                                    break;
                                case "undergroundParkingSpace":
                                    template = $("#parkingSpaceHtml").html();
                                    break;
                                case "parkingFacilitie":
                                    template = $("#supportingFacilitieHtml").html();
                                    break;
                            }
                            $.each(JSON.parse(item.childData), function (i, cdData) {
                                //确定写入位置
                                var element;
                                var childs = tr.treegrid('getChildNodes');
                                if (childs.length <= 0) {
                                    element = $(template).insertAfter(tr);
                                } else {
                                    //判断最后的子项是否还有子项数据
                                    var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
                                    if (subChilds.length <= 0) {
                                        element = $(template).insertAfter(childs.get(childs.length - 1));
                                    } else {
                                        element = $(template).insertAfter(subChilds.get(subChilds.length - 1));
                                    }
                                }
                                element.find('[name=name]').val(cdData.name);
                                element.find('[name=content]').val(cdData.content);
                                if (cdData.huxing) {
                                    element.find('[name=huxing]').val(cdData.huxing);
                                }
                                if (cdData.huxingArea) {
                                    element.find('[name=huxingArea]').val(cdData.huxingArea);
                                }
                                if (cdData.householdCount) {
                                    element.find('[name=householdCount]').val(cdData.householdCount);
                                }
                                if (cdData.number) {
                                    element.find('[name=number]').val(cdData.number);
                                }
                                economicIndicators.treegirdParse();
                            })
                        }
                    })

                }
                if (callback) {
                    callback();
                }
            }
        })
    }



</script>
