<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>借款人资产抵押情况</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_loanPledgeAssist" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    合同签订日期<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="合同签订日期" name="signData" class="form-control dbdate" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    合同号<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="合同号" name="contractNumber" class="form-control ">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    抵押人<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="抵押人"  name="mortgagor" class="form-control">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    抵押行<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="抵押行" name="mortgageCompany" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    贷款金额<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="贷款金额" name="loanAmount" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    抵押物地址<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="抵押物地址" name="mortgageAddress" class="form-control" >
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-1 control-label">
                    土地性质<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="土地性质" name="landNature" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    土地面积<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="土地面积" name="landArea" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    房产性质<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="房产性质"  name="houseNature" class="form-control" >
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    面积 <span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="面积" name="area" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    外评价值<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="外评价值" name="evaluationValue" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    现本金余额<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="现本金余额"  name="principalBalance" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    抵押物现状 <span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="抵押物现状" name="mortgageStatus" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    是否起诉查封<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="是否起诉查封" name="bisSealUp" class="form-control" >
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
                <a href="javascript:;" class="btn btn-warning" onclick="loanPledgeAssistSubmit();">
                    保存
                </a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_loanPledgeAssist").validate();
    });
    function loanPledgeAssistSubmit() {
        if (!$("#frm_loanPledgeAssist").valid()) {
            return false;
        }

    }
</script>