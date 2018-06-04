<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>测算</h2>        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_assessAssist" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    评估方法<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="评估方法" name="contractNumber" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    评估数量<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="评估数量" name="loanTime" class="form-control dbdate">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    市场单价<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="市场单价" data-rule-number='true' name="amount" class="form-control">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    市场价值判断<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="市场价值判断" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    变现系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="变现系数" name="term" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    变现价值<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="变现价值" data-rule-number='true' name="guaranteeMethod" class="form-control" >
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    拍卖费系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="拍卖费系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    拍卖费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="拍卖费" name="term" class="form-control" >
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    诉讼费系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="诉讼费系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    诉讼费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="诉讼费" name="term" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    执行费系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="执行费系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    执行费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="执行费" name="term" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    司法鉴定费系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="司法鉴定费系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    司法鉴定费<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="司法鉴定费" name="term" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    其它费用系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="其它费用系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    其它费用<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="其它费用" name="term" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    契税及印花税系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="契税及印花税系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    契税及印花税<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="契税及印花税" name="term" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    增值税及附加税系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="增值税及附加税系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    增值税及附加税<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="增值税及附加税" name="term" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    土地增值税系数<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="土地增值税系数" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    土地增值税<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="土地增值税" name="term" class="form-control" >
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    预计追偿借款人一般净收入<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="预计追偿借款人一般净收入" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    预计追偿全部保证人净收入<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="预计追偿全部保证人净收入" name="term" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    预计可追偿第三方净收入<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="预计可追偿第三方净收入" name="term" class="form-control" >
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    抵押物变现净收入额<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="抵押物变现净收入额" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    预计可追偿净收入合计<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="预计可追偿净收入合计" name="term" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    不良贷款预计受偿收加金额合计<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="不良贷款预计受偿收加金额合计" name="term" class="form-control" >
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    预计收回时间<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="预计收回时间" name="variety" class="form-control">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    实际工时<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="实际工时" data-rule-number='true' name="actualHours" class="form-control" maxlength="3">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    成果描述
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" name="taskRemarks"
                                                  class="form-control"></textarea>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-sm-offset-5">
                <a href="javascript:;" class="btn btn-warning" onclick="assessAssistSubmit();">
                    保存
                </a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_assessAssist").validate();
    });
    function assessAssistSubmit() {
        if (!$("#frm_assessAssist").valid()) {
            return false;
        }

    }
</script>