<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/30
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--考核项目的模态窗口-->
<div id="config_items_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">
                    <li id="modal_title_" class="fa fa-cogs">考核项配置</li>
                </h4>
            </div>
            <form id="config_form" class="form-horizontal">
                <input type="hidden" id="assessment_id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="panel-body">

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label" for="assessmentDes">
                                        考核标准<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-10">
                                    <textarea class="form-control" id="assessmentDes" name="assessmentDes" required
                                              data-rule-maxlength="255" rows="6" placeholder="填写考核项目的说明文字"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label" for="minScore">
                                        下限分数<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input id="minScore" class="form-control" name="minScore" placeholder="可以为负数" required
                                               data-rule-number="true">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label" for="maxScore">
                                        上限分数<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input id="maxScore" class="form-control" name="maxScore" placeholder="最高分值" required
                                               data-rule-digits="true">
                                    </div>
                                </div>
                                <div>
                                    <label class="col-sm-2 control-label" for="itemValid">
                                        是否启用<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="hidden" value="true" name="itemValid" id="itemValid">
                                        <div id="item_valid_" class="checkbox" >
                                            <input type="checkbox" class="flat" checked="checked"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </form>


            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button onclick="saveAssessmentItem()" type="button" class="btn btn-success">
                    添加
                </button>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        $("#config_form").validate();
    })
    function addAssessmentItem() {
        $("#config_form").clearAll();
        $("#assessment_id").val("0");
        $('#config_items_modal').modal({backdrop: 'static', keyboard: false});
    }

    function saveAssessmentItem() {
        if ($("#config_form").valid()) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/AssessmentItem/saveAssessmentItem",
                type: "post",
                dataType: "json",
                data: formParams("config_form"),
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#tb_boxReList').bootstrapTable("refresh");
                        $('#config_items_modal').modal('hide');
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
    }

</script>
