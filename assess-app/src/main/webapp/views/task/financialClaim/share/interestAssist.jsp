<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_interestAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            分析基准日<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="分析基准日" data-date-format='yyyy-mm-dd'  name="analysisDatumDate" class="form-control dbdate" >
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
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <input id="interestAssistUpload" name="interestAssistUpload" type="file" multiple="false">
            <div id="_interestAssistUpload">
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

<script type="text/javascript">
    $(function () {
        interestAssist();
        FileUtils.uploadFiles({
            target: "interestAssistUpload",
            showFileList: false
        }, {
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_csr_principal_interest",
                    creater: "${currUserAccount}",
                    tableId: $("#frm_interestAssist [name$='id']").val()
                };
                $("#interestAssistUpload").data('uploadifive').settings.formData = formData;   //动态更改formData的值
            },
            onUploadComplete: function () {
                loadinterestAssistFiles($("#frm_interestAssist [name$='id']").val());
            }
        });
    });
    function loadinterestAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "interestAssistUpload",
            formData: {
                tableName: "tb_csr_principal_interest",
                tableId: tableId
            },
            deleteFlag: true
        });
    }
    function interestAssist() {
        $.ajax({
            url: "${pageContext.request.contextPath}/csrPrincipalInterest/loadLoanPrincipalInterest",
            data: {
                borrowerId: "${planDetailsParent.projectPhaseId}",//该项业务特殊，存储的内容为客户编号
                detailsId:$("#interestAssist_details_id").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_interestAssist").clearAll();
                    $("#frm_interestAssist").validate();
                    $("#frm_interestAssist").initForm(result.data);
                    loadinterestAssistFiles(result.data.id);
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
    function interestAssistSubmit() {
        if (!$("#frm_interestAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_interestAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"]=$("#interestAssist_details_id").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrPrincipalInterest/saveLoanPrincipalInterest",
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