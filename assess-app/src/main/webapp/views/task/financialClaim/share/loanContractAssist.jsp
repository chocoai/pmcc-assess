<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_loanContractAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            借款合同号<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="借款合同号" name="contractNumber" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            借款发放时间<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="借款发放时间" data-date-format='yyyy-mm-dd' name="loanTime" class="form-control dbdate">
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
                       placeholder="期限" name="term" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            担保方式<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="担保方式" name="guaranteeMethod" class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <input id="loanContractAssistUpload" name="loanContractAssistUpload" type="file" multiple="false">
            <div id="_loanContractAssistUpload">
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

<script type="text/javascript">
    $(function () {
        loadLoanContractAssist();
        FileUtils.uploadFiles({
            target: "loanContractAssistUpload",
            showFileList: false
        }, {
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_csr_contract",
                    creater: "${currUserAccount}",
                    tableId: $("#frm_loanContractAssist [name$='id']").val()
                };
                $("#loanContractAssistUpload").data('uploadifive').settings.formData = formData;   //动态更改formData的值
            },
            onUploadComplete: function () {
                loadloanContractAssistFiles($("#frm_loanContractAssist [name$='id']").val());
            }
        });
    });
    function loadloanContractAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "loanContractAssistUpload",
            formData: {
                tableName: "tb_csr_contract",
                tableId: tableId
            },
            deleteFlag: true
        });
    }
    function loadLoanContractAssist() {
        $.ajax({
            url: "${pageContext.request.contextPath}/csrContract/loadLoanContractAssist",
            data: {
                borrowerId: "${planDetailsParent.projectPhaseId}",//该项业务特殊，存储的内容为客户编号
                detailsId:$("#loanContractAssist_details_id").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_loanContractAssist").clearAll();
                    $("#frm_loanContractAssist").validate();
                    $("#frm_loanContractAssist").initForm(result.data);
                    loadloanContractAssistFiles(result.data.id);
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
    function loanContractAssistSubmit() {
        if (!$("#frm_loanContractAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_loanContractAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"]=$("#loanContractAssist_details_id").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrContract/saveLoanContract",
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