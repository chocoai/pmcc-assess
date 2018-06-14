<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="frm_interestAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            分析基准日<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="analysisDatumDate"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            本金<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="principal"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            利息<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="interest"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            本息和<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="principalInterestTotal"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
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
                <label class="form-control" name="actualHours"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            成果描述
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                <label class="form-control" name="taskRemarks"></label>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        interestAssist();
        
    });
    function loadinterestAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "interestAssistUpload",
            formData: {
                tableName: "tb_csr_principal_interest",
                tableId: tableId
            },
            deleteFlag: false
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
                    loadinterestAssistFiles(data.id);
                    $("#frm_interestAssist").initForm(result.data);
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