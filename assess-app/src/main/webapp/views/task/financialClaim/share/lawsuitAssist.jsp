<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>诉讼保全情况</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_lawsuitAssist" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    诉讼保全<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="诉讼保全" name="litigationPreservation" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    诉讼保全情况<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="诉讼保全情况" name="litigationPreservationInfo" class="form-control">
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
                <a href="javascript:;" class="btn btn-warning" onclick="lawsuitAssistSubmit();">
                    保存
                </a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_lawsuitAssist").validate();
    });
    function lawsuitAssistSubmit() {
        if (!$("#frm_lawsuitAssist").valid()) {
            return false;
        }

    }
</script>