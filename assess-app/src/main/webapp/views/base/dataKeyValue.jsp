<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="keyValueBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">扩展属性</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="keyValueFrm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row form-group">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-success btn-md"
                                    onclick="keyValueManage.addExtendPropHtml();">添加扩展属性
                            </button>
                        </div>
                    </div>
                    <div class="keyValue"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="keyValueManage.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/html" id="dicExtendPropHtml">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">
                    键<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <textarea type="text" required class="form-control input-full" name="key" value="{key}">{key}</textarea>
                </div>
                <label class="col-sm-1 control-label">
                    值<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <textarea type="text" required class="form-control input-full" name="value" value="{value}">{value}</textarea>
                </div>
                <label class="col-sm-1 control-label">说明</label>
                <div class="col-sm-2">
                    <textarea type="text" class="form-control input-full" name="explain" value="{explain}">{explain}</textarea>
                </div>
                <div class="col-sm-1">
                    <input class='btn btn-warning btn-sm' type='button' value='X'
                           onclick='$(this).closest(".form-group").remove();'>
                </div>
            </div>
        </div>
    </div>
</script>
<script type="application/javascript">
    var keyValueManage = {};

    //新增扩展属性html
    keyValueManage.addExtendPropHtml = function (key, value, explain) {
        var keyValueEle = $("#keyValueFrm").find('.keyValue');
        var html = $('#dicExtendPropHtml').html();
        html = html.replace(/{key}/g, AssessCommon.toString(key));
        html = html.replace(/{value}/g, AssessCommon.toString(value));
        html = html.replace(/{explain}/g, AssessCommon.toString(explain));
        keyValueEle.append(html);
    }

    //编辑扩展属性html
    keyValueManage.editExtendPropHtml = function (id) {
        $("#keyValueFrm").find('.keyValue').empty();
        $.ajax({
            url: "${pageContext.request.contextPath}/baseDataDic/getDataDicInfo",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#keyValueFrm").find('[name=id]').val(id);
                    if (result.data && result.data.keyValue) {
                        var jsonarray = eval(result.data.keyValue);
                        $.each(jsonarray, function (i, item) {
                            keyValueManage.addExtendPropHtml(item.key,item.value,item.explain);
                        })
                    }
                    $('#keyValueBox').modal();
                } else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //保存数据
    keyValueManage.saveData = function (id) {
        if ($("#keyValueFrm").valid()) {
            var data = {};
            data.id = $("#keyValueFrm").find('[name=id]').val();
            data.keyValue = [];
            $("#keyValueFrm").find('.form-group').each(function () {
                var item = {};
                var key = $(this).find('[name^=key]').val();
                var value = $(this).find('[name^=value]').val();
                var explain = $(this).find('[name^=explain]').val();
                if (key && value) {
                    item.key = key;
                    item.value = value;
                    item.explain = explain;
                    data.keyValue.push(item);
                }
            });
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/baseDataDic/saveKeyValue",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#keyValueBox').modal('hide');
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    };
</script>


</html>
