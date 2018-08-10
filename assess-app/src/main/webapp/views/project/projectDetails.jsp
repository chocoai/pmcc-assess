<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h3>
                        ${projectInfo.projectName}
                        <small><label style="padding: 5px;" class="label label-danger">
                            ${projectInfo.projectStatus}
                        </label></small>
                    </h3>

                </div>
                <div class="title_right">
                    <div class="col-md-12 col-sm-12 col-xs-12 pull-right" style="margin: 0px">
                        <c:choose>
                            <c:when test="${projectFollowFlog eq '0'}">
                                <div class="btn-group">
                                    <a id="btn_followProject" class="btn btn-warning"
                                       onclick="projectDetails.followProject()">关注</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="btn-group">
                                    <a id="btn_cancelFollowProject" class="btn btn-warning"
                                       onclick="projectDetails.cancelFollowProject()">取消关注</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${projectStatusEnum=='normal'}">
                            <div class="btn-group">
                                <a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/projectClose/closeIndex?projectId=${projectInfo.id}"
                                   target="_blank">终止</a>
                            </div>
                            <div class="btn-group">
                                <a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/ProjectSuspend/suspendIndex?projectId=${projectInfo.id}"
                                   target="_blank">暂停</a>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger">
                                    项目变更
                                </button>
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ProjectWorkStageRestart/restartApply?projectId=${projectInfo.id}"
                                           target="_blank">阶段重启</a>
                                        <a href="${pageContext.request.contextPath}/projectPlanHistory/projectPlanHistoryIndex?projectId=${projectInfo.id}"
                                           target="_blank">总体时间</a>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${projectStatusEnum=='pause'}">
                            <a class="btn btn-success"
                               onclick="projectDetails.restartProject()"
                               target="_blank">重启</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>工作成果</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs plan_tab">
                            <c:forEach items="${projectPlanList}" var="plan">
                                <li class="plan_tab_li" plan-id="${plan.id}">
                                    <a href="#tab_plan_${plan.id}" data-toggle="tab"
                                       aria-expanded="true">${plan.planName}
                                        <c:if test="${plan.projectStatus eq '安排计划' or plan.projectStatus eq '提交成果'}">
                                            <i class="fa fa-ellipsis-h"></i>
                                        </c:if>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <%--状态说明  fa-ellipsis-h 进行中  fa-power-off 结束 fa-external-link待提交 fa-edit待审批---%>
                        <div class="tab-content">
                            <c:forEach items="${projectPlanList}" var="plan">
                                <div class="tab-pane fade " id="tab_plan_${plan.id}">
                                        <%--判断逻辑 1.未到该阶段不做任何处理 2.该阶段已结束数据只做显示
                                        3.处理该阶段任务 计划任务结束 状态结束 只读
                                        计划任务为待提交状态 状态进行 可读 任务为当前则进入页面提交 不为当前人显示出责任人
                                        计划任务为待审批 状态进行 可读 任务为当前人则进入页面审批 不为当前人显示出责任人--%>
                                    <c:if test="${not empty plan.planDisplayUrl}">
                                        <div class="col-md-3 col-sm-3 col-xs-3 col-sm-offset-1">
                                            <div class="btn-group">
                                                <c:if test="${empty plan.planExecutUrl}">
                                                    <button class="btn btn-sm btn-primary" type="button">
                                                        计划编制
                                                    </button>
                                                    <button class="btn btn-sm btn-default" type="button"
                                                            data-placement="top"
                                                            data-toggle="tooltip" data-original-title="查看"
                                                            onclick="window.open('${plan.planDisplayUrl}')"><i
                                                            class="fa fa-search"></i></button>
                                                </c:if>
                                                <c:if test="${not empty plan.planExecutUrl}">
                                                    <button class="btn btn-sm btn-primary" type="button">
                                                        计划编制<i class="fa fa-ellipsis-h"></i>
                                                    </button>
                                                    <button class="btn btn-sm btn-default" type="button"
                                                            data-placement="top" data-toggle="tooltip"
                                                            onclick="window.open('${plan.planDisplayUrl}')"
                                                            data-original-title="查看"><i class="fa fa-search"></i>
                                                    </button>
                                                    <button class="btn btn-sm btn-default" type="button"
                                                            data-placement="top" data-toggle="tooltip"
                                                            data-original-title="责任人">${plan.planExecutor}
                                                    </button>
                                                    <c:if test="${plan.planCanExecut eq true}">
                                                        <button class="btn btn-sm btn-default" type="button"
                                                                data-placement="top" data-toggle="tooltip"
                                                                onclick="projectDetails.taskOpenWin('${plan.planExecutUrl}')"
                                                                data-original-title="处理"><i class="fa fa-edit"></i>
                                                        </button>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:if>


                                    <p>
                                    <table id="plan_item_list_${plan.id}" class="table table-bordered"></table>
                                    </p>

                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_details.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        //注册事件
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            projectDetails.loadPlanTabInfo($(this));
        });

        projectDetails.selectRuningTab();
    })
</script>
<script type="application/javascript">
    var projectDetails = {
        loadPlanTabInfo:function (tab) {
            var that = $(tab).closest('li');
            projectDetails.loadTaskList({
                target: $('#plan_item_list_' + that.attr('plan-id')),
                projectId: '${projectInfo.id}',
                planId: that.attr('plan-id')
            });
        },

        loadTaskList: function (options) {
            var defaults = {
                target: undefined,
                projectId: undefined,
                planId: undefined
            }
            var defaults = $.extend({}, defaults, options);
            $('#plan_item_list_' + defaults.planId).treegrid({
                url: "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId",
                queryParams: {
                    projectId: defaults.projectId,
                    planId: defaults.planId
                },
                idField: 'id',
                treeField: 'projectPhaseName',
                datatype: 'json',
                lines: true,
                width: 'auto',
                method: "get",
                rownumbers: true,
                columns: [[
                    {
                        field: 'projectPhaseName',
                        align: 'left',
                        title: '工作内容',
                        width: '20%',
                        formatter: function (value, row) {
                            var s = value;
                            if (row.status == 'runing') {
                                s += ' <i class="fa fa-ellipsis-h"></i>';
                            }
                            if (row.bisNew) {
                                s += " <i class='clip-new' style='font-size: 15px;color: red'></i>";
                            }
                            return s;
                        }
                    },
                    {
                        field: 'planStartDate',
                        align: 'center',
                        title: '开始时间',
                        width: '10%',
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {
                        field: 'planEndDate',
                        align: 'center',
                        title: '结束时间',
                        width: '10%',
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {field: 'planHours', align: 'center', title: '计划工时(h)', width: '5%'},
                    {field: 'actualHours', align: 'center', title: '实际工时(h)', width: '5%'},
                    {
                        field: 'taskSubmitTime',
                        align: 'center',
                        title: '提交时间',
                        width: '10%',
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {field: 'executeUserName', align: 'center', title: '责任人', width: '10%'},
                    {field: 'executor', align: 'center', title: '当前责任人', width: '10%'},
                    {field: 'id', title: 'PlanItemId', align: 'center', hidden: true},
                    {
                        field: 'processInsId',
                        align: 'left',
                        title: '操作',
                        width: '10%',
                        formatter: function (value, row) {
                            var s = "";
                            if (row.bisLastLayer) {
                                if (row.displayUrl) {
                                    s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' ><i class='fa fa-search fa-white'></i></a>";
                                }
                                if (row.canExecute) {
                                    s += " <a target='_blank' onclick='projectDetails.taskOpenWin(\"" + row.executeUrl + "\")' href='javascript://' data-placement='top' data-original-title='提交' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-edit fa-white'></i></a>";
                                }
                                if (row.canAssignment) {
                                    s += " <a target='_blank' href='${pageContext.request.contextPath}/surveyExamine/assignment?planDetailsId=" + row.id + "' data-placement='top' data-original-title='分派' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-random fa-white'></i></a>";
                                }
                            }
                            return s;

                        }
                    }
                ]]
            });
        },

        //关注项目
        followProject: function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/projectFollow/followProject",
                data: {
                    projectId: "${projectInfo.id}",
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("关注成功");
                        $("#btn_followProject").hide();
                        $("#btn_cancelFollowProject").show();
                    }
                    else {
                        Alert("关注失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //取消关注
        cancelFollowProject: function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/projectFollow/cancelFollowProject",
                data: {
                    projectId: "${projectInfo.id}",
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("取消成功");
                        $("#btn_followProject").show();
                        $("#btn_cancelFollowProject").hide();
                    }
                    else {
                        Alert("取消失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //阶段重启
        restartProject: function () {
            Alert("确认重启吗？重启后项目将可以进行上传上应的工作成果!", 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/ProjectSuspend/startProject",
                    data: {
                        projectId: "${projectInfo.id}",
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success("项目重启成功");
                            location.reload();
                        }
                        else {
                            Alert("项目重启失败，失败原因：" + result.errmsg, 1, null, null);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });
            });
        },

        //默认选择进行中的阶段tab
        selectRuningTab: function () {
            $('.plan_tab').find('i:first').closest('li').find('a').tab('show');
        },

        //打开任务页面的回调
        taskOpenWin: function (url) {
            openWin(url, function () {
                projectDetails.loadPlanTabInfo($('.plan_tab').find('i:first').closest('li').find('a'));
            })
        }
    };

</script>

</html>
