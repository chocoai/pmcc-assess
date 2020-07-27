<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>复核工时</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        复核工时
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmRevieScore" class="form-horizontal">
                                    <input type="hidden" name="id" value="${projectReviewScore.id}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col" width="20%">阶段</th>
                                            <th scope="col" width="20%">得分</th>
                                            <th scope="col" width="60%">说明</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${keyValueDtos}" var="item">
                                            <tr>
                                                <td scope="col">
                                                        ${item.key}<input type="hidden" name="key" value="${item.key}">
                                                </td>
                                                <td scope="col">
                                                    <input type="text" data-rule-number="true" required
                                                           class="form-control input-full"
                                                           name="value" value="${item.value}">
                                                </td>
                                                <td scope="col">
                                                    <input type="text" class="form-control input-full"
                                                           name="explain" value="${item.explain}">
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
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="commit_btn" style="margin-left: 10px;" class="btn btn-primary"
                                    onclick="applySumit();">
                                提交
                            </button>
                        </div>
                    </div>
                    <c:if test="${not empty processInsId and processInsId != 0}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="process_variable_form">
                            <%@include file="/views/share/form_edit.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        reviewScore.loadHistoryList();
    })

    var reviewScore = {};

    reviewScore.loadHistoryList = function () {
        var cols = [];
        cols.push({field: 'creatorName', title: '填写人', width: '10%'});
        cols.push({
            field: 'gmtCreated', title: '填写时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'content', title: '内容', width: '70%', formatter: function (value, row, index) {
                var str = '';
                if (value) {
                    var json = JSON.parse(value);
                    $.each(json, function (i, item) {
                        str += item.key + "【" + item.value + "】" + item.explain + '<br/>';
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

    function applySumit() {
        if (!$('#frmRevieScore').valid()) {
            return false;
        }
        var trs = $('#frmRevieScore').find('tbody tr');
        var data = {};
        var contentArray = [];
        var totalScore = null;
        trs.each(function (i, item) {
            var keyValue = {};
            keyValue.key = $(item).find('[name=key]').val();
            keyValue.value = $(item).find('[name=value]').val();
            keyValue.explain = $(item).find('[name=explain]').val();
            if (keyValue.value) {
                totalScore += parseFloat(keyValue.value);
            }
            contentArray.push(keyValue);
        })
        data.content = contentArray;
        data.totalScore = totalScore;
        if ('${processInsId}' == '' || '${processInsId}' == '0') {
            $.post('${pageContext.request.contextPath}/projectReviewScore/applyCommit', {
                formData: JSON.stringify(data),
                projectId: '${projectInfo.id}'
            }, function (result) {
                if (result.ret) {
                    AlertSuccess('成功', '提交成功', function () {
                        window.close();
                    })
                } else {
                    AlertError('失败', result.errmsg);
                }
            }, 'json');
        } else {
            data.masterId = '${projectReviewScore.id}';
            var jsonData = formApproval.getFormData();
            jsonData.formData = JSON.stringify(data);
            $.post('${pageContext.request.contextPath}/projectReviewScore/applyCommit', jsonData, function (result) {
                if (result.ret) {
                    AlertSuccess('成功', '提交成功', function () {
                        window.close();
                    })
                } else {
                    AlertError('失败', result.errmsg);
                }
            }, 'json');
        }
    }
</script>
