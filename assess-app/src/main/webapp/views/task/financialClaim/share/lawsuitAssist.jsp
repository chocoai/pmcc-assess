<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_lawsuitAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
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

<script type="text/javascript">
    $(function () {
        lawsuitAssist();
    });
    function lawsuitAssist() {
        $.ajax({
            url: "${pageContext.request.contextPath}/csrLitigation/loadLoanLitigation",
            data: {
                borrowerId: "${planDetailsParent.projectPhaseId}",//该项业务特殊，存储的内容为客户编号
                detailsId:$("#lawsuitAssist_details_id").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_lawsuitAssist").clearAll();
                    $("#frm_lawsuitAssist").validate();
                    $("#frm_lawsuitAssist").initForm(result.data);
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
    function lawsuitAssistSubmit() {
        if (!$("#frm_lawsuitAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_lawsuitAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"]=$("#lawsuitAssist_details_id").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrLitigation/saveLoanLitigation",
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