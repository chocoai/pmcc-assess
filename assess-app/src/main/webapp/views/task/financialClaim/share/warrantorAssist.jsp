<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_warrantorAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            保证人姓名<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="保证人姓名" name="name" class="form-control">
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
        <a href="javascript:;" class="btn btn-warning" onclick="warrantorAssistSubmit();">
            保存
        </a>
    </div>
</form>
<table id="tb_warrantorAssist" class="table table-bordered">
</table>


<script type="text/javascript">
    $(function () {
        warrantorAssist();
    });
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
                }
            });
    }
    function warrantorAssistReload() {
        TableReload("tb_warrantorAssist");
    }
    function warrantorAssistSubmit() {
        if (!$("#frm_warrantorAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_warrantorAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"] = $("#warrantorAssist_details_id").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrGuarantor/saveLoanGuarantor",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    warrantorAssistReload();
                    var actualHours=$("#frm_warrantorAssist [name$='actualHours']").val();
                    var taskRemarks=$("#frm_warrantorAssist [name$='taskRemarks']").val();
                    $("#frm_warrantorAssist").clearAll();
                    $("#frm_warrantorAssist").validate();
                    $("#frm_warrantorAssist [name$='actualHours']").val(actualHours);
                    $("#frm_warrantorAssist [name$='taskRemarks']").val(taskRemarks);
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