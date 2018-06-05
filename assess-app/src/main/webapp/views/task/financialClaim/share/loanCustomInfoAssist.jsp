<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_loanCustomInfoAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
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

<script type="text/javascript">
    $(function () {
        loanCustomInfoAssist();
    });
    function loanCustomInfoAssist() {
        $.ajax({
            url: "${pageContext.request.contextPath}/csrBorrower/loadLoanBorrower",
            data: {
                borrowerId: "${planDetailsParent.projectPhaseId}",//该项业务特殊，存储的内容为客户编号
                detailsId:$("#loanCustomInfoAssist_details_id").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_loanCustomInfoAssist").clearAll();
                    $("#frm_loanCustomInfoAssist").validate();
                    $("#frm_loanCustomInfoAssist").initForm(result.data);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
    function loanCustomInfoAssistSubmit() {
        if (!$("#frm_loanCustomInfoAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_loanCustomInfoAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"]=$("#loanCustomInfoAssist_details_id").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrBorrower/saveLoanBorrower",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
</script>