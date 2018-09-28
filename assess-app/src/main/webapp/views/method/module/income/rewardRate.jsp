<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="modal_reward_rate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title">报酬率</h3>
            </div>
            <div class="modal-body">
                <form id="frm_reward_rate" class="form-horizontal">
                    <table class="table tree">
                        <tr class="treegrid-1">
                            <td>机会成本</td>
                            <td><input type="text" class="x-percent"></td>
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
                <button type="button" class="btn btn-primary" onclick="rewardRateFunc.success();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#frm_reward_rate').find('.tree').treegrid();
        $('#frm_reward_rate').find(':text').bind('blur', function () {
            rewardRateRecursion($(this).closest('tr'));
        })
    })
    var rewardRateFunc = {};
    rewardRateFunc.onSuccess = undefined;

    //测算
    rewardRateFunc.calculation = function (callback) {
        if (callback) rewardRateFunc.onSuccess = callback;
        $("#modal_reward_rate").modal();
    }

    //测算结果
    rewardRateFunc.success = function () {
        var rootNodes = $('#frm_reward_rate').find('.tree').treegrid('getRootNodes');
        var result = 0;
        rootNodes.each(function () {
            var val=rewardRateFunc.getValue(this);
            if (val) {
                result += parseFloat(val.replace(/%$/,''));
            }
        })
        $("#modal_reward_rate").modal('hide');
        if (rewardRateFunc.onSuccess) {
            rewardRateFunc.onSuccess(result+'%');
        }
    }

    rewardRateFunc.getValue = function (node) {
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
                var val=rewardRateFunc.getValue(this);
                if (val) {
                    result += parseFloat(val);
                }
            })
            $(parentNode).find('td:eq(1)').text(result.toFixed(2)+"%")
            rewardRateRecursion(parentNode);
        }
    }
</script>




