<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="keyValueBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">key-value管理</h4>
            </div>
            <form id="keyValueFrm" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            key<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" required data-rule-maxlength="50" placeholder="key值"
                                                   id="key" name="key" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            value<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" required data-rule-maxlength="50" placeholder="value值"
                                                   id="value" name="value" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-9">
                                    <textarea placeholder="备注" id="explain" name="explain"
                                              class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="keyValueManage.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">
    var keyValueManage ={};
    //保存数据
    keyValueManage.saveData = function(id) {
        if ($("#keyValueFrm").valid()) {
            var data = {};
            data.id=$("#id").val();
            data.keyValue={};
            data.keyValue.key = $("#key").val();
            data.keyValue.value = $("#value").val();
            data.keyValue.explain = $("#explain").val();
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/baseDataDic/saveKeyValue",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#keyValueBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    };

    //编辑字典数据
    keyValueManage.editDataDic = function(id) {
        $("#keyValueFrm").clearAll();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/baseDataDic/getKeyValue",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#id").val(id);
                    if(result.data) {
                        $("#key").val(result.data.key);
                        $("#value").val(result.data.value);
                        $("#explain").val(result.data.explain);
                    }
                    $('#keyValueBox').modal();
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

</script>


</html>
