<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-10-31
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
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
                            <th>数量</th>
                            <th>户型</th>
                            <th>户型面积</th>
                            <th>户数</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tr class="treegrid-1" data-key="netLandArea">
                            <td>一、规划建设净用地面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2" data-key="planTotalArea">
                            <td>二、规划总建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-1 treegrid-parent-2" data-key="groundBuildingArea">
                            <td>（一）地上计入容积率的建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-1-1 treegrid-parent-2-1" data-key="houseBuildingArea">
                            <td>1、住宅建筑面积
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.addChild(this,'residentialAreaHtml');"><i
                                        class="fa fa-plus fa-white"></i></a></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-1-2 treegrid-parent-2-1" data-key="nonHouseBuildingArea">
                            <td>2、非住宅建筑面积
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.addChild(this,'nonResidentialAreaHtml');"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>

                        <tr class="treegrid-2-2 treegrid-parent-2" data-key="groundExcludBuildingArea">
                            <td>（二）地上不计入容积率的建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-2-1 treegrid-parent-2-2" data-key="overheadBuildingArea">
                            <td>1、首层架空建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-2-2 treegrid-parent-2-2" data-key="heatBuildingArea">
                            <td>2、外保温建筑面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-2-3 treegrid-parent-2" data-key="undergroundBuildingArea">
                            <td>（三）地下建筑面积及层数
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.addChild(this,'substructureAreaHtml');"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-3" data-key="volumetricRate">
                            <td>三、容积率</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"></td>
                        </tr>
                        <tr class="treegrid-4">
                            <td>四、建筑基底面积</td>
                            <td></td>
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
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-4-2 treegrid-parent-4" data-key="highMainBaseArea">
                            <td>高层主体基底（基座）面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-5">
                            <td>五、建筑密度</td>
                            <td></td>
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
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> %</td>
                        </tr>
                        <tr class="treegrid-5-1 treegrid-parent-5" data-key="highMainBuildingDensity">
                            <td>高层主体建筑密度</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> %</td>
                        </tr>
                        <tr class="treegrid-6" data-key="greenSpaceRate">
                            <td>六、绿地率</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> %</td>
                        </tr>
                        <tr class="treegrid-6-1 treegrid-parent-6" data-key="greenArea">
                            <td>绿地面积</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> ㎡</td>
                        </tr>
                        <tr class="treegrid-7" data-key="mobileParkingSpace">
                            <td>七、机动车位</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1 treegrid-parent-7" data-key="undergroundParkingSpace">
                            <td>（一）地下停车位
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.addChild(this,'parkingSpaceHtml');"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1-1 treegrid-parent-7-1" data-key="residentialParkingSpace">
                            <td>（1）住宅停车位</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1-2 treegrid-parent-7-1" data-key="commercialParkingSpace">
                            <td>（2）商业停车位</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1-3 treegrid-parent-7-1" data-key="parkingFacilitie">
                            <td>（3）配套设施停车位
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.addChild(this,'supportingFacilitieHtml');"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1-3-1 treegrid-parent-7-1-3" data-key="propertyRoomParking">
                            <td>物管用房停车位</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1-3-2 treegrid-parent-7-1-3" data-key="publicHousingParking">
                            <td>社区公共服务用房停车位</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>
                        <tr class="treegrid-7-1-3-3 treegrid-parent-7-1-3" data-key="intelligentCommunityParking">
                            <td>智慧小区用房停车位</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input type="text" name="content" style="width: 100px;"> 辆</td>
                        </tr>

                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="economicIndicators.success();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<%--住宅面积模板--%>
<script type="text/html" id="residentialAreaHtml">
    <tr class="treegrid-2-1-1-1 treegrid-parent-2-1-1">
        <td>
            <input type="text" name="area" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td><input type="text" name="area" style="width: 100px;"></td>
        <td><input type="text" name="area" style="width: 100px;"></td>
        <td><input type="text" name="area" style="width: 100px;"></td>
        <td><input type="text" name="area" style="width: 100px;"></td>
        <td><input type="text" name="area" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--非住宅面积模板--%>
<script type="text/html" id="nonResidentialAreaHtml">
    <tr class="treegrid-2-1-2-1 treegrid-parent-2-1-2">
        <td>
            <input type="text" name="area" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="area" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--地下建筑面积模板--%>
<script type="text/html" id="substructureAreaHtml">
    <tr class="treegrid-2-3-1 treegrid-parent-2-3">
        <td>
            <input type="text" name="area" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="area" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--地下停车位模板--%>
<script type="text/html" id="parkingSpaceHtml">
    <tr class="treegrid-7-1-1 treegrid-parent-7-1">
        <td>
            <input type="text" name="area" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="area" style="width: 100px;"> 辆</td>
    </tr>
</script>

<%--配套设施停车位模板--%>
<script type="text/html" id="supportingFacilitieHtml">
    <tr class="treegrid-7-1-3-1 treegrid-parent-7-1-3">
        <td>
            <input type="text" name="area" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="area" style="width: 100px;"> 辆</td>
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

    economicIndicators.onSuccess = undefined;

    //测算
    economicIndicators.calculation = function (callback) {
        if (callback) economicIndicators.onSuccess = callback;
        $("#declareEconomicIndicatorsBox").modal();
    }

    //测算结果
    economicIndicators.success = function () {
        var rootNodes = $('#frm_reward_rate').find('.tree').treegrid('getRootNodes');
        var result = 0;
        rootNodes.each(function () {
            var val = economicIndicators.getValue(this);
            if (val) {
                result += parseFloat(val.replace(/%$/, ''));
            }
        })
        $("#declareEconomicIndicatorsBox").modal('hide');
        if (economicIndicators.onSuccess) {
            economicIndicators.onSuccess(result + '%');
        }
    }

    economicIndicators.getValue = function (node) {
        var val;
        if ($(node).treegrid('isLeaf')) {
            val = $(node).find(':text').val();
        } else {
            val = $(node).find('td:eq(1)').text();
        }
        return val;
    }

    //递归从下到上依次计算
    function rewardRateRecursion(node) {
        if (node == undefined) return;
        var parentNode = $(node).treegrid('getParentNode');
        if (parentNode) {
            var childNodes = $(parentNode).treegrid('getChildNodes');
            var result = 0;
            childNodes.each(function () {
                var val = economicIndicators.getValue(this);
                if (val) {
                    result += parseFloat(val);
                }
            })
            $(parentNode).find('td:eq(1)').text(result.toFixed(2) + "%")
            rewardRateRecursion(parentNode);
        }
    }
</script>
