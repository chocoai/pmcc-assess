<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="tb_warrantorAssist" class="table table-bordered">
</table>
<div id="frm_warrantorAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            保证人姓名<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="name"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <div id="_warrantorAssistUpload">
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
        warrantorAssist();
    });
    function loadwarrantorAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "warrantorAssistUpload",
            formData: {
                tableName: "tb_csr_guarantor",
                tableId: tableId
            },
            deleteFlag: false
        });
    }
    function warrantorAssist() {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'name', title: '保证人姓名'});
        TableInit("tb_warrantorAssist", "${pageContext.request.contextPath}/csrGuarantor/getCsrGuarantor", cols,
            {
                borrowerId: "${planDetailsParent.projectPhaseId}",
                detailsId: $("#warrantorAssist_details_id").val()
            }, {
                search: false,
                showRefresh: false,
                onClickRow: function (row) {

                    $("#frm_warrantorAssist").initForm(row);
                    loadwarrantorAssistFiles(row.id)
                }
            });
    }
</script>