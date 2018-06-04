<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>本金利息情况</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_interestAssist" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    分析基准日<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="分析基准日" name="analysisDatumDate" class="form-control dbdate" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    本金<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="本金" data-rule-number='true' name="principal" class="form-control ">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    利息<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="利息" data-rule-number='true' name="interest" class="form-control">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    本息和<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="本息和" data-rule-number='true' name="principalInterestTotal" class="form-control">
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
                <a href="javascript:;" class="btn btn-warning" onclick="interestAssistSubmit();">
                    保存
                </a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_interestAssist").validate();
    });
    function interestAssistSubmit() {
        if (!$("#frm_interestAssist").valid()) {
            return false;
        }

    }
</script>