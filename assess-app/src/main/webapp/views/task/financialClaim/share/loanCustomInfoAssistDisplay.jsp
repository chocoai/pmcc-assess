<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_loanCustomInfoAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            借款人<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="name"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            身份证号<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="idNumber"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            婚否<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="maritalStatus"></label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            学历<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="education"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            工作单位<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="workUnit"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            职务<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <label class="form-control" name="post"></label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            户籍所在地<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-5">
                <label class="form-control" name="domicilePlace"></label>
            </div>
        </div>
        <label class="col-sm-1 control-label">
            现居住地址<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-5">
                <label class="form-control" name="presentAddress"></label>
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
                detailsId: $("#loanCustomInfoAssist_details_id").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
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
</script>