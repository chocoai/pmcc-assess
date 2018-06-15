<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="frm_lawsuitAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            诉讼保全<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="litigationPreservation"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            诉讼保全情况<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="litigationPreservationInfo"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <div id="_lawsuitAssistUpload">
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
        lawsuitAssist();
    });
    function loadlawsuitAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "lawsuitAssistUpload",
            formData: {
                tableName: "tb_csr_litigation",
                tableId: tableId
            },
            deleteFlag: false
        });
    }
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
                    loadlawsuitAssistFiles(result.data.id);
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
</script>