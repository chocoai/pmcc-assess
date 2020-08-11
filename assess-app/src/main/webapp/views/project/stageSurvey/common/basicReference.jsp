<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-8-11
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="div_basic_data_handle_referenceBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼盘数据修改内容</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    具体修改内容
                                </label>
                                <div class="col-sm-11">
                                    <div style="width:99%;height:200px;" id="basic_data_handle_referenceId"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="validBasicDataHandleReference()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script>
    var basic_data_handle_reference_callback = undefined;
    var basic_data_handle_reference = undefined;
    var basic_data_handle_content = undefined;

    $(document).ready(function () {
        if ($("#basic_data_handle_referenceId").size() != 0) {
            basic_data_handle_reference = UE.getEditor('basic_data_handle_referenceId', {
                toolbars: [
                    ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
                ],
                zIndex: 11009,
                initialFrameHeight: 120,
                elementPathEnabled: false,//是否启用元素路径，默认是true显示
                wordCount: false, //是否开启字数统计
                autoHeightEnabled: false,
                autoFloatEnabled: true
            });
        }
    }) ;

    //打开模态框
    function openBasicDataHandleReference(callback) {
        if ('${BASIC_DATA_HANDLE_REFERENCE}') {//只有当引用状态 存在才保存  BASIC_DATA_HANDLE_REFERENCE如果不为null那么表示引用状态
            if ('${BASIC_DATA_HANDLE_REFERENCE.modifyContent}') {
                basic_data_handle_reference.setContent('${BASIC_DATA_HANDLE_REFERENCE.modifyContent}', false);
            }
            var box = $("#div_basic_data_handle_referenceBox");
            box.modal("show");
            basic_data_handle_reference_callback = callback;
        } else {
            callback();
        }
    }

    //校验必须填写 修改内容
    function validBasicDataHandleReference() {
        var box = $("#div_basic_data_handle_referenceBox");
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var content = basic_data_handle_reference.getContent();
        if (!content) {
            notifyInfo('提示', '具体修改内容必须填写');
            return false;
        }
        basic_data_handle_content = content;
        basic_data_handle_reference_callback();
        box.modal("hide");
    }

    function saveBasicDataHandleReferenceData(callback) {
        var data = {id: '${BASIC_DATA_HANDLE_REFERENCE.id}', modifyContent: basic_data_handle_content};
        console.log(data) ;
        if ('${BASIC_DATA_HANDLE_REFERENCE}') {//只有当引用状态 存在才保存  BASIC_DATA_HANDLE_REFERENCE如果不为null那么表示引用状态
            $.ajax({
                url: "${pageContext.request.contextPath}/basicApplyBatchDetail/saveAndUpdateBasicApplyBatchDetail",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        basic_data_handle_content = null;
                        basic_data_handle_reference_callback = null;
                        if (callback) {
                            callback();
                        }
                    } else {
                        AlertError("保存失败,失败原因:" + result.errmsg);
                    }
                }
            });
        } else {//非  引用状态
            if (callback) {
                callback();
            }
        }
    }

</script>