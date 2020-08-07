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
    <title>复核与指导工时考核</title>
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
                                        复核与指导工时考核
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmRevieScoreItem" class="form-horizontal">
                                    <c:forEach items="${projectReviewScoreItems}" var="item" varStatus="status">
                                        <div class="card full-height">
                                            <div class="card-header collapse-link">
                                                <div class="card-head-row">
                                                    <div class="card-title">
                                                        <h4>${item.planName}</h4>
                                                    </div>
                                                    <div class="card-tools">
                                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                                class="fa fa-angle-down"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1">
                                                                标准
                                                            </label>
                                                            <div class="col-md-11">
                                                                <input type="hidden" name="planId" value="${item.planId}">
                                                                <input type="hidden" name="planName" value="${item.planName}">
                                                                <input type="hidden" name="standard" value="${item.standard}">
                                                                <input type="hidden" name="standardScore" value="${item.standardScore}">
                                                                <span style="color: red;"> ${item.standard}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1">
                                                                任务
                                                            </label>
                                                            <div class="col-md-11">
                                                                <table class="table table-bordered"
                                                                       id="tbPlanDetailsList${item.planId}"></table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1">
                                                                得分<span class="symbol required"></span>
                                                            </label>
                                                            <div class="col-md-3">
                                                                <input type="text" data-rule-number="true" required
                                                                       class="form-control input-full"
                                                                       name="score" value="${item.score}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1">
                                                                说明
                                                            </label>
                                                            <div class="col-md-11">
                                                                    <textarea id="remark${item.planId}"
                                                                              name="remark">${item.remark}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <script type="text/javascript">
                                            $(function () {
                                                reviewScore.loadPlanDetailsList($('#tbPlanDetailsList${item.planId}'), '${item.planId}');

                                                var ueItem = UE.getEditor('remark${item.planId}', {
                                                    toolbars: [
                                                        ['fullscreen', 'source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
                                                    ],
                                                    initialFrameWidth: '99%',
                                                    initialFrameHeight: 120,
                                                    elementPathEnabled: false,//是否启用元素路径，默认是true显示
                                                    wordCount: false, //是否开启字数统计
                                                    autoHeightEnabled: false,
                                                    autoFloatEnabled: true
                                                });
                                                reviewScore.ueContainer.push(ueItem);
                                            });
                                        </script>
                                    </c:forEach>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        历史记录
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-up"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body" style="display: none;">
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
    reviewScore.ueContainer = [];

    reviewScore.loadHistoryList = function () {
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

    //加载任务
    reviewScore.loadPlanDetailsList = function ($list, planId) {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({
            field: 'projectPhaseName', title: '名称', width: '35%', formatter: function (value, row, index) {
                var str = row.projectPhaseName;
                if (row.planRemarks) {
                    str += "<span style='font-size: 10px;'>(" + row.planRemarks + ")</span>";
                }
                return str;
            }
        });
        cols.push({
            field: 'executeUserName', title: '责任人/审批人', width: '15%', formatter: function (value, row, index) {
                var s = value;
                if (row.approverUserName) {
                    s += '/' + row.approverUserName;
                }
                return s;
            }
        });
        cols.push({
            field: 'status', title: '状态', formatter: function (value, row, index) {
                var str = "";
                switch (value) {
                    case "runing": {
                        str = "<span class='label label-info'>" + "进行中" + "</span>";
                        break;
                    }
                    case "finish": {
                        str = "<span class='label label-success'>" + "已完成" + "</span>";
                        break;
                    }
                    case "close": {
                        str = "<span class='label label-warning'>" + "关闭" + "</span>";
                        break;
                    }
                    case "none": {
                        str = "<span class='label label-default'>" + row.projectPhaseName + "</span>";
                        break;
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'planStartDate', title: '开始日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'planEndDate', title: '结束日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '7%', formatter: function (value, row, index) {
                var str = "";
                if (row.displayUrl) {
                    str += "<button type='button' onclick='projectStagePlan.taskOpenWin(\"" + row.displayUrl + "\")' href='javascript://' style='margin-left: 5px;' title='查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                }
                return str;
            }
        });
        var select = {
            projectId: "${projectInfo.id}",
            planId: planId
        };
        $list.bootstrapTable('destroy');
        TableInit($list, "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId", cols, {
            formData: JSON.stringify(select)
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
        if (!$('#frmRevieScoreItem').valid()) {
            return false;
        }
        var trs = $('#frmRevieScoreItem').find('.card-body');
        var data = {};
        var reviewScoreItemList = [];
        var totalScore = null;
        trs.each(function (i, item) {
            var reviewScoreItem = {};
            reviewScoreItem.planId = $(item).find('[name=planId]').val();
            reviewScoreItem.planName = $(item).find('[name=planName]').val();
            reviewScoreItem.standard = $(item).find('[name=standard]').val();
            reviewScoreItem.score = $(item).find('[name=score]').val();
            reviewScoreItem.standardScore = $(item).find('[name=standardScore]').val();
            reviewScoreItem.remark = reviewScore.ueContainer[i].getContent();
            if (reviewScoreItem.score) {
                totalScore += parseFloat(reviewScoreItem.score);
            }
            reviewScoreItemList.push(reviewScoreItem);
        })
        data.reviewScoreItemList = reviewScoreItemList;
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
            var jsonData = formSerializeArray($("#process_variable_form"));
            jsonData.formData = JSON.stringify(data);
            $.post('${pageContext.request.contextPath}/projectReviewScore/editCommit', jsonData, function (result) {
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
