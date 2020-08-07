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
                                                                得分
                                                            </label>
                                                            <div class="col-md-3">${item.score}</div>
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
                                                                    ${item.remark}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <script type="text/javascript">
                                            $(function () {
                                                loadPlanDetailsList($('#tbPlanDetailsList${item.planId}'), '${item.planId}');
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
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body" style="display: none;">
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
<script type="text/html"></script>
<script type="text/javascript">
    $(function () {
        loadHistoryList();
    })

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

    function loadPlanDetailsList($list, planId) {
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
</script>