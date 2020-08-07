<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>复核与指导工时考核</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        复核与指导工时考核
                                        <button type="button" class="btn btn-md btn-primary"
                                                onclick="saveReviewScoreItem()">保存
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmRevieScoreItem" class="form-horizontal">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col" width="20%">阶段</th>
                                            <th scope="col" width="20%">得分</th>
                                            <th scope="col" width="60%">说明</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${projectReviewScoreItems}" var="item" varStatus="status">
                                            <tr class="reviewRow">
                                                <td scope="col">${item.planName}
                                                    <input type="hidden" name="planId" value="${item.planId}">
                                                    <input type="hidden" name="planName" value="${item.planName}">
                                                </td>
                                                <td scope="col">
                                                    <input type="text" data-rule-number="true" required
                                                           class="form-control input-full"
                                                           name="score" value="${item.score}">
                                                </td>
                                                <td scope="col">
                                                    <textarea id="remark${status.index}"
                                                              name="remark">${item.remark}</textarea>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        历史数据
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered" id="tbHistoryList"></table>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    var ueContainer = [];
    $(function () {
        loadHistoryList();
        $('#frmRevieScoreItem').find('[name=remark]').each(function (i, item) {
            var id = $(this).attr('id');
            var ueItem = UE.getEditor(id, {
                toolbars: [
                    ['fullscreen', 'source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
                ],
                initialFrameWidth: 700,
                initialFrameHeight: 120,
                elementPathEnabled: false,//是否启用元素路径，默认是true显示
                wordCount: false, //是否开启字数统计
                autoHeightEnabled: false,
                autoFloatEnabled: true
            });
            ueContainer[i] = ueItem;
        })
    })

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectReviewScore/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function loadHistoryList() {
        var cols = [];
        cols.push({field: 'creatorName', title: '填写人', width: '10%'});
        cols.push({
            field: 'gmtCreated', title: '填写时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'reviewScoreItemList', title: '内容', width: '50%', formatter: function (value, row, index) {
                var str = '';
                if (value) {
                    $.each(value, function (i, item) {
                        str += item.planName + "【" + item.score + "】";
                        str += item.remark + '<br/>';
                    })
                }
                return str;
            }
        });
        $("#tbHistoryList").bootstrapTable('destroy');
        TableInit("tbHistoryList", "${pageContext.request.contextPath}/projectReviewScore/getHistroyList", cols, {
            reviewId: '${projectReviewScore.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    function saveReviewScoreItem() {
        if (!$('#frmRevieScoreItem').valid()) {
            return false;
        }
        var trs = $('#frmRevieScoreItem').find('.reviewRow');
        var data = {};
        var reviewScoreItemList = [];
        var totalScore = null;
        trs.each(function (i, item) {
            var reviewScoreItem = {};
            reviewScoreItem.planId = $(item).find('[name=planId]').val();
            reviewScoreItem.planName = $(item).find('[name=planName]').val();
            reviewScoreItem.score = $(item).find('[name=score]').val();
            reviewScoreItem.remark = ueContainer[i].getContent();
            if (reviewScoreItem.score) {
                totalScore += parseFloat(reviewScoreItem.score);
            }
            reviewScoreItemList.push(reviewScoreItem);
        })
        data.reviewId = '${projectReviewScore.id}';
        data.reviewScoreItemList = reviewScoreItemList;
        data.totalScore = totalScore;
        $.post('${pageContext.request.contextPath}/projectReviewScore/saveReviewScoreGroup', {
            formData: JSON.stringify(data)
        }, function (result) {
            if (result.ret) {
                notifySuccess('提示', '保存成功');
                loadHistoryList();
            } else {
                AlertError('失败', result.errmsg);
            }
        }, 'json')
    }
</script>