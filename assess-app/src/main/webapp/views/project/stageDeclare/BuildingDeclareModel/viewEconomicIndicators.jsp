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
                            <th>面积</th>
                        </tr>
                        </thead>
                        <tr class="treegrid-1">
                            <td>机会成本</td>
                            <td><input type="text" style="width: 100px;"></td>
                            <td><input type="text" style="width: 100px;"></td>
                            <td><input type="text" style="width: 100px;"></td>
                            <td><input type="text" style="width: 100px;"></td>
                            <td><input type="text" style="width: 100px;"></td>
                        </tr>
                        <tr class="treegrid-2">
                            <td>风险补偿率</td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-2-1 treegrid-parent-2">
                            <td>投资风险补偿</td>
                            <td><input type="text" class="x-percent"></td>
                        </tr>
                        <tr class="treegrid-2-2 treegrid-parent-2">
                            <td>管理负担补偿</td>
                            <td><input type="text" class="x-percent"></td>
                        </tr>
                        <tr class="treegrid-2-3 treegrid-parent-2">
                            <td>缺乏流动性补偿</td>
                            <td><input type="text" class="x-percent"></td>
                        </tr>
                        <tr class="treegrid-2-4 treegrid-parent-2">
                            <td>投资带来的优惠</td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-2-4-1 treegrid-parent-2-4">
                            <td>易与获得融资的好处</td>
                            <td><input type="text" class="x-percent"></td>
                        </tr>
                        <tr class="treegrid-2-4-2 treegrid-parent-2-4">
                            <td>所得税抵扣的好处</td>
                            <td><input type="text" class="x-percent"></td>
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

<script type="text/html" id="12">
    <tr class="treegrid-{id} treegrid-parent-2-4">
        <td>{name}</td>
        <td><input type="text" name="number" style="width: 100px;"></td>
        <td><input type="text" name="huxing" style="width: 100px;"></td>
        <td><input type="text" name="huxingArea" style="width: 100px;"></td>
        <td><input type="text" name="householdCount" style="width: 100px;"></td>
        <td><input type="text" name="area" style="width: 100px;"></td>
        </tr>
</script>

<script type="text/javascript">
    $(function () {
        $('#declareEconomicIndicatorsBox').find('.tree').treegrid();
    })


    var economicIndicators = {};
    //加载数据列表
    economicIndicators.loadDataList = function (pid) {
        $.ajax({
            url: '${pageContext.request.contextPath}/economicIndicators/getEconomicIndicatorsByPid',
            data: {pid: pid},
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.ret) {

                    $.each(result.data,function (i,item) {

                    })
                    console.log(result.data);
                }
            }
        })
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
