<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>借款合同基本情况</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_loanContractAssist" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    借款合同号<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="借款合同号" name="contractNumber" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    借款发放时间<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="借款发放时间" name="loanTime" class="form-control dbdate">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    金额<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="金额" data-rule-number='true' name="amount" class="form-control">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    品种<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="品种" name="variety" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    期限<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="期限" name="term" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    担保方式<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="担保方式" data-rule-number='true' name="guaranteeMethod" class="form-control" >
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
                <a href="javascript:;" class="btn btn-warning" onclick="loanContractAssistSubmit();">
                    保存
                </a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_loanContractAssist").validate();
    });
    function loanContractAssistSubmit() {
        if (!$("#frm_loanContractAssist").valid()) {
            return false;
        }

    }
</script>