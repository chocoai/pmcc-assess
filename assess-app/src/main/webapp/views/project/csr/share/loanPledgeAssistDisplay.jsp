<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="tb_loanPledgeAssist" class="table table-bordered">
</table>
<div id="frm_loanPledgeAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            合同签订日期<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="signData"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            合同号<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="contractNumber"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            抵押人<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="mortgagor"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            抵押行<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="mortgageCompany"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            贷款金额<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="loanAmount"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            抵押物地址<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="mortgageAddress"></label>
            </div>
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-1 control-label">
            土地性质<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="landNature"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            土地面积<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="landArea"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            房产性质<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="houseNature"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            面积 <span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="area"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            外评价值<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="evaluationValue"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            现本金余额<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="principalBalance"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            抵押物现状 <span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="mortgageStatus"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            是否起诉查封<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="bisSealUp"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <div id="_PledgeAssistUpload">
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
        loanPledgeAssist();
    });
    function loadPledgeAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "PledgeAssistUpload",
            formData: {
                tableName: "tb_csr_borrower_mortgage",
                tableId: tableId
            },
            deleteFlag: false
        });
    }
    function loanPledgeAssist() {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'signData', title: '合同签订日期'});
        cols.push({field: 'contractNumber', title: '合同号'});
        cols.push({field: 'mortgagor', title: '抵押人'});
        cols.push({field: 'mortgageCompany', title: '抵押行'});
        cols.push({field: 'loanAmount', title: '贷款金额'});
        TableInit("tb_loanPledgeAssist", "${pageContext.request.contextPath}/csrBorrowerMortgage/getCsrBorrowerMortgage", cols,
            {
                borrowerId: "${planDetailsParent.projectPhaseId}",
                detailsId: $("#loanPledgeAssist_details_id").val()
            }, {
                search: false,
                showRefresh: false,
                onClickRow: function (row) {
                    $("#frm_loanPledgeAssist").initForm(row);
                    loadPledgeAssistFiles(row.id)
                }
            });
    }
</script>