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
                                        <label class="col-sm-2 control-label">
                                            添加key-value
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="btn btn-xs btn-success"
                                                 onclick="appendHTML(this)">
                                                <i class="fa fa-plus"></i></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="keyValue">

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
            data.keyValue=[];
            $("#keyValueFrm").find('.form-group').each(function () {
                var item = {};
                var keyName = $(this).find('[name^=keyName]').val();
                var valueName = $(this).find('[name^=valueName]').val();
                var explain = $(this).find('[name^=explain]').val();
                if (keyName && valueName) {
                    item.keyName = keyName;
                    item.valueName = valueName;
                    item.explain = explain;
                    data.keyValue.push(item);
                }
            });
            console.log(data);
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
            url: "${pageContext.request.contextPath}/baseDataDic/getDataDicInfo",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#id").val(id);
                    if(result.data) {
                        writeHTMLData(result.data.keyValue );
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

    var num = 0;

    function appendHTML(this_) {
        var html = "<div class='form-group' >";
        html += "<div class='x-valid'>";

        html += "<label class='col-sm-1 control-label'>" + "key" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control' name='keyName'+ '" + num + "'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "value" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control' name='valueName'+ '" + num + "'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "说明" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control' name='explain'+ '" + num + "'>";
        html += "</div>";

        html += " <div class='col-sm-2'>";
        html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";

        num++;
        $(".keyValue").append(html);
    }

    function cleanHTMLData(item) {
        var value = "";
        $(item).parent().parent().parent().remove();
    }

    function writeHTMLData(json) {
        $(".keyValue").empty();
        if(json) {
            var jsonarray = eval(json);
            $.each(jsonarray, function (i, n) {
                var html = "<div class='form-group' >";
                html += "<div class='x-valid'>";

                html += "<label class='col-sm-1 control-label'>" + "key" + "</label>";
                html += "<div class='col-sm-2'>";
                html += "<input type='text' required class='form-control' name='keyName'+'" + i + "' value='" + n['keyName'] + "'>";
                html += "</div>";

                html += "<label class='col-sm-1 control-label'>" + "value" + "</label>";
                html += "<div class='col-sm-2'>";
                html += "<input type='text' required class='form-control' name='valueName'+'" + i + "' value='" + n['valueName'] + "'>";
                html += "</div>";

                html += "<label class='col-sm-1 control-label'>" + "说明" + "</label>";
                html += "<div class='col-sm-2'>";
                html += "<input type='text' required class='form-control' name='explain'+ '" + i + "' value='" + n['explain'] + "'>";
                html += "</div>";

                html += " <div class='col-sm-2'>";
                html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                $(".keyValue").append(html);
            })
        }
    }
</script>


</html>
