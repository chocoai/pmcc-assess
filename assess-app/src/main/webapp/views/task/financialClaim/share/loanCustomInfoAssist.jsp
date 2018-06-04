<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>借款人主体资格</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_loanCustomInfoAssist" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    借款人<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="借款人" name="name" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    身份证号<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="身份证号" name="idNumber" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    婚否<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="婚否" name="maritalStatus" class="form-control">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    学历<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="学历" name="education" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    工作单位<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="工作单位" name="workUnit" class="form-control" >
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    职务<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="职务" name="post" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    户籍所在地<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="户籍所在地" name="domicilePlace" class="form-control">
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    现居住地址<span class="symbol required"></span>
                </label>
                <div class="x-valid">
                    <div class="col-sm-5">
                        <input type="text" required
                               placeholder="现居住地址" name="presentAddress" class="form-control" >
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
                <a href="javascript:;" class="btn btn-warning" onclick="loanCustomInfoAssistSubmit();">
                    保存
                </a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#frm_loanCustomInfoAssist").validate();
    });
    function loanCustomInfoAssistSubmit() {
        if (!$("#frm_loanCustomInfoAssist").valid()) {
            return false;
        }

    }
</script>