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
                            <td>
                                <input type="hidden" name="name" value="opportunityCost">
                                <input placeholder='百分比' type='text' class='x-percent' name='ratio'>
                            </td>
                            <td>
                                <input placeholder='备注' type='text' name='remark'>
                            </td>
                        </tr>
                        <tr class="treegrid-2">
                            <td>风险补偿率</td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-2-1 treegrid-parent-2">
                            <td>投资风险补偿</td>
                            <td>
                                <input type="hidden" name="name" value="riskCompensation">
                                <input placeholder='百分比' type='text' class='x-percent' name='ratio'>
                            </td>
                            <td>
                                <input placeholder='备注' type='text' name='remark'>
                            </td>
                        </tr>
                        <tr class="treegrid-2-2 treegrid-parent-2">
                            <td>管理负担补偿</td>
                            <td>
                                <input type="hidden" name="name" value="manageCompensation">
                                <input placeholder='百分比' type='text' class='x-percent' name='ratio'>
                            </td>
                            <td>
                                <input placeholder='备注' type='text' name='remark'>
                            </td>
                        </tr>
                        <tr class="treegrid-2-3 treegrid-parent-2">
                            <td>缺乏流动性补偿</td>
                            <td>
                                <input type="hidden" name="name" value="liquidCompensation">
                                <input placeholder='百分比' type='text' class='x-percent' name='ratio'>
                            </td>
                            <td>
                                <input placeholder='备注' type='text' name='remark'>
                            </td>
                        </tr>
                        <tr class="treegrid-2-4 treegrid-parent-2">
                            <td>投资带来的优惠</td>
                            <td data-type="minus"></td>
                        </tr>
                        <tr class="treegrid-2-4-1 treegrid-parent-2-4">
                            <td>易与获得融资的好处</td>
                            <td>
                                <input type="hidden" name="name" value="financingAdvantage">
                                <input placeholder='百分比' type='text' data-type="minus" class='x-percent' name='ratio'>
                            </td>
                            <td>
                                <input placeholder='备注' type='text' name='remark'>
                            </td>
                        </tr>
                        <tr class="treegrid-2-4-2 treegrid-parent-2-4">
                            <td>所得税抵扣的好处</td>
                            <td>
                                <input type="hidden" name="name" value="taxDeductionAdvantage">
                                <input placeholder='百分比' type='text'  class='x-percent' name='ratio'>
                            </td>
                            <td>
                                <input placeholder='备注' type='text' name='remark'>
                            </td>
                        </tr>
                        <tr class="treegrid-3">
                            <td>合计</td>
                            <td>
                                <label name="resultValue"></label>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="rewardRateFunc.success(rewardRateFunc.rewardRateId);">
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
            rewardRateFunc.rewardRateRecursion($(this).closest('tr'));
        })
    })
    var rewardRateFunc = {};
    rewardRateFunc.onSuccess = undefined;
    rewardRateFunc.rewardRateId = undefined;//数据id

    //测算
    rewardRateFunc.calculation = function (rewardRateId, callback) {
        rewardRateFunc.rewardRateId = rewardRateId;
        rewardRateFunc.onSuccess = callback;
        rewardRateFunc.initForm(rewardRateId);
        $("#modal_reward_rate").modal();
    }

    //测算结果
    rewardRateFunc.success = function (rewardRateId) {
        var data = {};
        data.id = rewardRateId;
        data.parameterValue = [];
        data.resultValue = $("#frm_reward_rate").find('[name=resultValue]').text();
        $("#frm_reward_rate").find('tr').each(function () {
            var item = {};
            item.name = $(this).find('[name=name]').val();
            item.ratio = $(this).find('[name=ratio]').attr("data-value");
            item.remark = $(this).find('[name=remark]').val();
            data.parameterValue.push(item);
        })
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/toolRewardRate/saveToolRewardRate",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(data)
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    $("#modal_reward_rate").modal('hide');
                    if (rewardRateFunc.onSuccess) {
                        rewardRateFunc.onSuccess(result.data);
                    }
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

    }

    rewardRateFunc.getValue = function (node) {
        var val;
        if ($(node).treegrid('isLeaf')) {
            val = $(node).find(':text').attr('data-value');
        } else {
            val = AssessCommon.percentToPoint($(node).find('td:eq(1)').text());
        }
        return val;
    }

    //数据回显
    rewardRateFunc.initForm = function (rewardRateId) {
        if (rewardRateId && AssessCommon.isNumber(rewardRateId)) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/toolRewardRate/getToolRewardRateById",
                type: "post",
                dataType: "json",
                data: {
                    rewardRateId: rewardRateId
                },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        var data = JSON.parse(result.data.parameterValue);
                        $.each(data, function (i, item) {
                            var element = $("#frm_reward_rate").find('[name=name][value=' + item.name + ']');
                            var tr = element.closest('tr');
                            tr.find('[name=ratio]').attr('data-value', item.ratio).val(AssessCommon.pointToPercent(item.ratio)).trigger('blur');
                            tr.find('[name=remark]').val(item.remark);
                        });
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //递归从下到上依次计算
    rewardRateFunc.rewardRateRecursion = function (node) {
        if (node == undefined) return;
        var parentNode = $(node).treegrid('getParentNode');
        if (parentNode) {
            var childNodes = $(parentNode).treegrid('getChildNodes');
            var result = 0;
            childNodes.each(function () {
                var val = 0;
                var dataType = null;
                if ($(this).treegrid('isLeaf')) {
                    val = $(this).find(':text').attr('data-value');
                } else {
                    dataType = $(this).find('td:eq(1)').attr('data-type');
                    val = AssessCommon.percentToPoint($(this).find('td:eq(1)').text());
                }
                if (dataType == "minus") {
                    result += parseFloat(-val);
                } else {
                    result += parseFloat(val);
                }
            })
            $(parentNode).find('td:eq(1)').text(AssessCommon.pointToPercent(result));
            rewardRateFunc.rewardRateRecursion(parentNode);
        }

        //计算合计值
        var rootNodes = $('#frm_reward_rate').find('.tree').treegrid('getRootNodes');
        var result = 0;
        rootNodes.each(function () {
            var val = rewardRateFunc.getValue(this);
            if (val) {
                result += parseFloat(val);
            }
        })
        $('#frm_reward_rate').find('[name=resultValue]').text(AssessCommon.pointToPercent(result));
    }

</script>




