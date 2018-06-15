<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_loanPledgeAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            合同签订日期<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="合同签订日期" data-date-format='yyyy-mm-dd' name="signData" class="form-control dbdate">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            合同号<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="合同号" name="contractNumber" class="form-control ">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            抵押人<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="抵押人" name="mortgagor" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            抵押行<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="抵押行" name="mortgageCompany" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            贷款金额<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="贷款金额" name="loanAmount" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            抵押物地址<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="抵押物地址" name="mortgageAddress" class="form-control">
            </div>
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-1 control-label">
            土地性质<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="土地性质" name="landNature" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            土地面积<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="土地面积" name="landArea" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            房产性质<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="房产性质" name="houseNature" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            面积 <span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="面积" name="area" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            外评价值<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="外评价值" name="evaluationValue" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            现本金余额<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="现本金余额" name="principalBalance" class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            抵押物现状 <span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="抵押物现状" name="mortgageStatus" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            是否起诉查封<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="是否起诉查封" name="bisSealUp" class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <input id="loanPledgeAssistUpload" name="loanPledgeAssistUpload" type="file" multiple="false">
            <div id="_loanPledgeAssistUpload">
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
        <a href="javascript:;" class="btn btn-warning" onclick="loanPledgeAssistSubmit();">
            保存
        </a>
    </div>
</form>
<table id="tb_loanPledgeAssist" class="table table-bordered">
</table>
<script type="text/javascript">
    $(function () {
        loanPledgeAssist();
        FileUtils.uploadFiles({
            target: "loanPledgeAssistUpload",
            showFileList: false
        }, {
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_csr_borrower_mortgage",
                    creater: "${currUserAccount}",
                    tableId: $("#frm_loanPledgeAssist [name$='id']").val()
                };
                $("#loanPledgeAssistUpload").data('uploadifive').settings.formData = formData;   //动态更改formData的值
            },
            onUploadComplete: function () {
                loadloanPledgeAssistFiles($("#frm_loanPledgeAssist [name$='id']").val());
            }
        });
    });
    function loadloanPledgeAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "loanPledgeAssistUpload",
            formData: {
                tableName: "tb_csr_borrower_mortgage",
                tableId: tableId
            },
            deleteFlag: true
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
        cols.push({
            field: 'opation', title: '操作',
            formatter: function (value, row, index) {

                var str = "<a onclick='loanPledgeAssistDeleteItems(" + row.id + ")' style='margin-left: 5px;' data-toggle='tooltip' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips' ><i class='fa fa-minus fa-white'></i></a>";
                return str;
            }
        });
        TableInit("tb_loanPledgeAssist", "${pageContext.request.contextPath}/csrBorrowerMortgage/getCsrBorrowerMortgage", cols,
            {
                borrowerId: "${planDetailsParent.projectPhaseId}",
                detailsId: $("#loanPledgeAssist_details_id").val()
            }, {
                search: false,
                showRefresh: false,
                onClickRow: function (row) {
                    $("#frm_loanPledgeAssist").initForm(row);
                    loadloanPledgeAssistFiles(row.id);
                },
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
    function loanPledgeAssistDeleteItems(id) {
        Alert("确认要删除么,删除后数据将不可恢复？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/csrBorrowerMortgage/deleteCsrBorrowerMortgage",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loanPledgeAssistReload();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }
    function loanPledgeAssistReload() {
        TableReload("tb_loanPledgeAssist");
    }
    function loanPledgeAssistSubmit() {
        if (!$("#frm_loanPledgeAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_loanPledgeAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"] = $("#loanPledgeAssist_details_id").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrBorrowerMortgage/saveLoanBorrowerMortgage",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    loanPledgeAssistReload();
                    var actualHours = $("#frm_loanPledgeAssist [name$='actualHours']").val();
                    var taskRemarks = $("#frm_loanPledgeAssist [name$='taskRemarks']").val();
                    $("#frm_loanPledgeAssist").clearAll();
                    $("#frm_loanPledgeAssist").validate();
                    $("#frm_loanPledgeAssist [name$='actualHours']").val(actualHours);
                    $("#frm_loanPledgeAssist [name$='taskRemarks']").val(taskRemarks);
                    $("#_loanPledgeAssistUpload").html("");
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