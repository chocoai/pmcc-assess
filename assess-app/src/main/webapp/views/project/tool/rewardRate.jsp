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
                            <td><input type="hidden" name="name" value="opportunityCost"></td>
                        </tr>
                        <tr class="treegrid-2">
                            <td>风险补偿率</td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-2-1 treegrid-parent-2">
                            <td>投资风险补偿</td>
                            <td><input type="hidden" name="name" value="riskCompensation"></td>
                        </tr>
                        <tr class="treegrid-2-2 treegrid-parent-2">
                            <td>管理负担补偿</td>
                            <td><input type="hidden" name="name" value="manageCompensation"></td>
                        </tr>
                        <tr class="treegrid-2-3 treegrid-parent-2">
                            <td>缺乏流动性补偿</td>
                            <td><input type="hidden" name="name" value="liquidCompensation"></td>
                        </tr>
                        <tr class="treegrid-2-4 treegrid-parent-2">
                            <td>投资带来的优惠</td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-2-4-1 treegrid-parent-2-4">
                            <td>易与获得融资的好处</td>
                            <td><input type="hidden" name="name" value="financingAdvantage"></td>
                        </tr>
                        <tr class="treegrid-2-4-2 treegrid-parent-2-4">
                            <td>所得税抵扣的好处</td>
                            <td><input type="hidden" name="name" value="taxDeductionAdvantage"></td>
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
        rewardRateFunc.getHtml();
        $('#frm_reward_rate').find('.tree').treegrid();
        $('#frm_reward_rate').find(':text').bind('blur', function () {
            rewardRateRecursion($(this).closest('tr'));
        })
    })
    var rewardRateFunc = {};
    rewardRateFunc.onSuccess = undefined;
    rewardRateFunc.rewardRateId = 2;//数据id

    //测算
    rewardRateFunc.calculation = function (callback) {
        if (callback) rewardRateFunc.onSuccess = callback;

        if (rewardRateFunc.rewardRateId && rewardRateFunc.rewardRateId != 0) {
            rewardRateFunc.initForm(rewardRateFunc.rewardRateId,);
        }
        $("#modal_reward_rate").modal();
    }

    //测算结果
    rewardRateFunc.success = function (rewardRateId) {
        var data = {};
        data.id = rewardRateId;
        data.parameterValue = [];
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
                    var rootNodes = $('#frm_reward_rate').find('.tree').treegrid('getRootNodes');
                    var result = 0;
                    rootNodes.each(function () {
                        var val = rewardRateFunc.getValue(this);
                        if (val) {
                            result += parseFloat(val.replace(/%$/, ''));
                        }
                    })
                    $("#modal_reward_rate").modal('hide');
                    if (rewardRateFunc.onSuccess) {
                        rewardRateFunc.onSuccess(result + '%');
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
            val = $(node).find(':text').val();
        } else {
            val = $(node).find('td:eq(1)').text();
        }
        return val;
    }

    //数据回显
    rewardRateFunc.initForm = function (rewardRateId) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/toolRewardRate/initForm",
            type: "post",
            dataType: "json",
            data: {
                rewardRateId: rewardRateId
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_reward_rate").find('tr').each(function () {
                        var ratio;
                        var ratioPercent;
                        var remark;
                        var name = $(this).find('[name=name]').val();
                        if (name) {
                            $.each(JSON.parse(result.data.parameterValue), function (i, item) {
                                if (item.name == name) {
                                    remark = item.remark;
                                    ratio = item.ratio;
                                    ratioPercent = (parseFloat(item.ratio) * 100).toFixed(2) + "%";
                                    return false;
                                }
                            });
                            $(this).find('[name=ratio]').attr("data-value", ratio);
                            $(this).find('[name=ratio]').val(ratioPercent);
                            $(this).find('[name=remark]').val(remark);
                            rewardRateRecursion($(this).closest('tr'));
                        }
                    })
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


    rewardRateFunc.getHtml = function () {
        $("#frm_reward_rate").find('tr').each(function () {
            if ($(this).find('input').length > 0) {
                var html = "";
                html += "<td>";
                html += "<input placeholder='百分比' type='text' class='x-percent' name='ratio'>";
                html += '</td>';
                html += "<td>";
                html += "<input placeholder='备注' type='text' name='remark'>";
                html += '</td>';
                $(this).append(html);
            }
        })
    };

    //递归从下到上依次计算
    function rewardRateRecursion(node) {
        if (node == undefined) return;
        var parentNode = $(node).treegrid('getParentNode');
        if (parentNode) {
            var childNodes = $(parentNode).treegrid('getChildNodes');
            var result = 0;
            childNodes.each(function () {
                var val = rewardRateFunc.getValue(this);
                if (val) {
                    result += parseFloat(val);
                }
            })
            $(parentNode).find('td:eq(1)').text(result.toFixed(2) + "%")
            rewardRateRecursion(parentNode);
        }
    }
</script>




