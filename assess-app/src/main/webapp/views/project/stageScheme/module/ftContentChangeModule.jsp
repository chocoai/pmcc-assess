<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--方法适用原因或不适用原因，及评估思路内容调整--%>
<div class="x_panel">
    <div class="x_title">
        <h3>
            评估方法原因及评估思路调整
            <small>
                <div class="btn btn-primary btn-xs" onclick="ftContentChange.edit(${judgeObject.id});">调整</div>
            </small>
        </h3>
    </div>
</div>
<div id="ft_content_change_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">调整</h3>
            </div>
            <div class="modal-body">
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="ft_content_change_tab" class="nav nav-tabs bar_tabs" role="tablist">

                        </ul>
                        <div id="ft_content_change_tab_content" class="tab-content form-horizontal">

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="ftContentChange.save()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {

    })

    var ftContentChange = {};

    //编辑
    ftContentChange.edit = function (judgeObjectId) {
        $("#ft_content_change_tab,#ft_content_change_tab_content").empty();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getSchemeJudgeFunctions',
            data: {
                judgeObjectId: judgeObjectId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    console.log(result.data);
                    $.each(result.data, function (i, item) {
                        var active = '';
                        if (i == 0) {
                            active = 'active';
                        }
                        var html = '<li role="presentation" class="' + active + '"><a href="#tab_content' + item.id + '" role="tab" data-toggle="tab" aria-expanded="true">' + item.name + '</a></li>';
                        $("#ft_content_change_tab").append(html);

                        var htmlContent = '<div role="tabpanel" class="tab-pane fade ' + active + ' in" id="tab_content' + item.id + '" aria-labelledby="home-tab">';
                        htmlContent += '<form class="form-horizontal"><input type="hidden" name="id" value="' + item.id + '">';
                        if (item.bisApplicable) {
                            htmlContent += '<div class="form-group">'
                                + '<label class="col-sm-2 control-label">'
                                + '方法适用原因<span class="symbol required"></span>'
                                + '</label>'
                                + '<div class="x-valid">'
                                + '<div class="col-sm-10">'
                                + '<textarea required placeholder="方法适用原因" name="applicableReason"'
                                + 'class="form-control">' + item.applicableReason + '</textarea>'
                                + '</div></div></div>';
                        } else {
                            htmlContent += '<div class="form-group">'
                                + '<label class="col-sm-2 control-label">'
                                + '方法不适用原因<span class="symbol required"></span>'
                                + '</label>'
                                + '<div class="x-valid">'
                                + '<div class="col-sm-10">'
                                + '<textarea required placeholder="方法不适用原因" name="notApplicableReason"'
                                + 'class="form-control">' + item.notApplicableReason + '</textarea>'
                                + '</div></div></div>';
                        }
                        htmlContent += '<div class="form-group">'
                            + '<label class="col-sm-2 control-label">'
                            + '评估思路<span class="symbol required"></span>'
                            + '</label>'
                            + '<div class="x-valid">'
                            + '<div class="col-sm-10">'
                            + '<textarea required placeholder="评估思路" name="thinking"'
                            + 'class="form-control">' + item.thinking + '</textarea>'
                            + '</div></div></div>';
                        htmlContent += '</form></div>';
                        $("#ft_content_change_tab_content").append(htmlContent);
                    })
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
        $("#ft_content_change_modal").modal();
    }

    //保存
    ftContentChange.save = function () {
        var dataArray = [];
        $("#ft_content_change_tab_content").find('form').each(function () {
            var data = formSerializeArray($(this));
            dataArray.push(data);
        })
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/changeFunctionContent',
            data: {formData: JSON.stringify(dataArray)},
            type: 'post',
            dataType: 'json',
            success: function (result) {
                Loading.progressHide();
                toastr.success('保存成功');
                $("#ft_content_change_modal").modal('hide');
            }
        })
    }
</script>
