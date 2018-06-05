<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="tb_loadassessAssist" class="table table-bordered">
</table>
<div id="frm_assessAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            评估方法<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="appraisalMethod"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            评估数量<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="pawnArea"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            市场单价<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="pawnArea"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            市场价值判断
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="pawnAmount"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            变现系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="pawnRealizationCoefficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            变现价值
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="pawnRealization"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            拍卖费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeAuctionCoefficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            拍卖费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeAuction"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            诉讼费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeLawsuitCoefficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            诉讼费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeLawsuit"></label>
            </div>
        </div>

    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            执行费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeExecuteCoefficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            执行费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeExecute"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            司法鉴定费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeAuthenticateCofficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            司法鉴定费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="disposeAuthenticate"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            其它费用系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="dispoetOtherCofficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            其它费用
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="dispoetOther"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            契税及印花税系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="taxStampCofficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            契税及印花税
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="taxStamp"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            增值税及附加税系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="taxAddedvalueCofficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            增值税及附加税
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="taxAddedvalue"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            土地增值税系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="taxLandCofficient"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            土地增值税
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="taxLand"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            抵押物变现净收入额<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="pawnRealizationValueAmount"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计追偿借款人一般净收入<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="recoveryIncomeOrdinary"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计追偿全部保证人净收入<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="recoveryIncomeAll"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计可追偿第三方净收入<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="recoveryIncomeThirdParty"></label>
            </div>
        </div>


    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            预计可追偿净收入合计<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="recoveryIncomAmount"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            不良贷款预计受偿收加金额合计<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="recoveryBadLoans"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计收回时间<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <label class="form-control" name="recoveryLimit"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            实际工时<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="actualHours"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            成果描述
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <label class="form-control" name="taskRemarks"></label>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        loadassessAssist();
    });
    function loadassessAssist() {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'appraisalMethod', title: '评估方法'});
        cols.push({field: 'pawnArea', title: '评估数量'});
        cols.push({field: 'pawnPrice', title: '市场单价'});
        cols.push({field: 'pawnAmount', title: '市场价值判断'});
        cols.push({field: 'pawnRealization', title: '变现价值'});
        cols.push({field: 'dispoetAmount', title: '处理相关费用小计'});
        cols.push({field: 'taxAmount', title: '税金小计'});
        cols.push({field: 'costAmount', title: '费用合计（处置费用+税金）'});
        cols.push({field: 'pawnRealizationValueAmount', title: '抵押物变现净收入额'});
        cols.push({field: 'recoveryIncomAmount', title: '预计可追偿净收入合计'});
        cols.push({field: 'recoveryBadLoans', title: '不良贷款预计受偿收回金额合计'});
        cols.push({field: 'recoveryLimit', title: '预计收回时间'});

        TableInit("tb_loadassessAssist", "${pageContext.request.contextPath}/csrCalculation/getCsrCalculation", cols,
            {
                borrowerId: "${planDetailsParent.projectPhaseId}",
                detailsId: $("#assessAssist_details_id").val()
            }, {
                search: false,
                showRefresh: false,
                onClickRow: function (row) {
                    $("#frm_assessAssist").initForm(row);
                }
            });
    }

</script>