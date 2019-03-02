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
                        <div class="btn-group">
                            <a id="btn_followProject" class="btn btn-warning"
                               style="display: ${projectFollowFlog ne '0'?'none':''}"
                               href="javascript://" onclick="projectDetails.followProject()"><i
                                    class="fa fa-eye">&nbsp;</i>关注</a>
                            <a id="btn_cancelFollowProject" class="btn btn-warning"
                               style="display: ${projectFollowFlog ne '1'?'none':''}"
                               href="javascript://" onclick="projectDetails.cancelFollowProject()"><i
                                    class="fa fa-eye-slash">&nbsp;</i>取消关注</a>

                            <c:if test="${projectStatusEnum ne 'pause' and projectStatusEnum ne 'close' and projectStatusEnum ne 'finish'}">
                                <a class="btn btn-primary" href="javascript://" onclick="projectDetails.pauseProject()"><i
                                        class="fa fa-pause">&nbsp;</i>暂停</a>
                            </c:if>

                            <c:if test="${projectStatusEnum=='pause'}">
                                <a class="btn btn-success" href="javascript://"
                                   onclick="projectDetails.restartProject()"><i class="fa fa-reply">&nbsp;</i>重启</a>
                            </c:if>
                            <c:if test="${projectStatusEnum ne 'close' and projectStatusEnum ne 'finish'}">
                                <a class="btn btn-primary" href="javascript://"
                                   onclick="projectDetails.stopProject()"><i class="fa fa-stop">&nbsp;</i>终止</a>
                            </c:if>
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger">
                                    项目变更
                                </button>
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/member.change/applyView?projectId=${projectInfo.id}"
                                           target="_blank">成员变更</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/project.information.change/applyView?projectId=${projectInfo.id}"
                                           target="_blank">信息变更</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
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
                    <h3>工作成果</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs plan_tab">
                            <c:forEach items="${projectPlanList}" var="plan">
                                <li class="plan_tab_li" plan-id="${plan.id}">
                                    <a href="#tab_plan_${plan.id}" data-toggle="tab"
                                       aria-expanded="true">${plan.planName}
                                        <c:if test="${plan.projectStatus eq 'planExecute' or plan.projectStatus eq 'task'}">
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
                                    <div id="plan_item_${plan.id}" class="col-md-3 col-sm-3 col-xs-3 col-sm-offset-1">
                                        <c:if test="${not empty plan.planDisplayUrl and plan.bisAutoComplete eq false}">
                                            <div class="btn-group">
                                                <c:if test="${empty plan.planExecutUrl}">
                                                    <div class="btn btn-sm btn-primary">计划编制</div>
                                                    <div class="btn btn-sm btn-warning" data-placement="top"
                                                         data-toggle="tooltip" data-original-title="查看"
                                                         onclick="window.open('${plan.planDisplayUrl}')"><i
                                                            class="fa fa-search"></i></div>
                                                </c:if>
                                                <c:if test="${not empty plan.planExecutUrl}">
                                                    <div class="btn btn-sm btn-primary">计划编制</div>
                                                    <div class="btn btn-sm btn-default" data-placement="top"
                                                         data-toggle="tooltip"
                                                         data-original-title="责任人">${plan.planExecutor}
                                                    </div>
                                                    <c:if test="${plan.planCanExecut eq true}">
                                                        <div class="btn btn-sm btn-success" data-placement="top"
                                                             data-toggle="tooltip"
                                                             onclick="projectDetails.taskOpenWin('${plan.planExecutUrl}')"
                                                             data-original-title="处理"><i class="fa fa-arrow-right"></i>
                                                        </div>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </c:if>
                                    </div>
                                    <a href="javascript://" class="btn btn-xs btn-warning"
                                       onclick="projectDetails.taskCopy(${plan.id});"><i
                                            class="fa fa-copy"></i> 复制</a><label id="lbl_copy_${plan.id}"></label>
                                    <a href="javascript://" class="btn btn-xs btn-warning"
                                       onclick="projectDetails.taskPaste(${plan.id});"><i
                                            class="fa fa-paste"></i> 粘贴</a>
                                    <p>
                                    <table id="plan_task_list${plan.id}" class="table table-bordered"></table>
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
<script type="text/html" id="planItemHtml">
    <div class="btn-group">
        <div class="btn btn-sm btn-primary">计划编制</div>
        <div class="btn btn-sm btn-default" data-placement="top" data-toggle="tooltip"
             data-original-title="责任人">{planExecutor}
        </div>
        <div class="btn btn-sm btn-{btnClass}" data-placement="top" data-toggle="tooltip"
             onclick="projectDetails.taskOpenWin('{planExecutUrl}')"
             data-original-title="处理"><i class="fa fa-arrow-right"></i>
        </div>
    </div>
</script>
<script type="text/html" id="planItemViewHtml">
    <div class="btn-group">
        <div class="btn btn-sm btn-primary">计划编制</div>
        <div class="btn btn-sm btn-warning" data-placement="top"
             data-toggle="tooltip" data-original-title="查看"
             onclick="window.open('{planDisplayUrl}')"><i class="fa fa-search"></i></div>
    </div>
</script>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        //注册事件
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            projectDetails.loadPlanTabInfo($(this));
        });

        projectDetails.getRuningTab().tab('show');

        setInterval(function () {
            projectDetails.loadPlanItem(projectDetails.getActiveTab().closest('li').attr('plan-id'));
        }, 30 * 1000)
    })
</script>
<script type="application/javascript">
    var projectDetails = {
        loadPlanTabInfo: function (tab) {
            var that = $(tab).closest('li');
            projectDetails.loadTaskList({
                target: $('#plan_task_list' + that.attr('plan-id')),
                projectId: '${projectInfo.id}',
                planId: that.attr('plan-id')
            });
        },

        loadPlanItem: function (planId) {
            $.ajax({
                url: '${pageContext.request.contextPath}/projectInfo/getProjectPlanItem',
                data: {planId: planId},
                success: function (result) {
                    $('#plan_item_' + planId).empty();
                    if (result.ret) {
                        if (result.data.planDisplayUrl && result.data.bisAutoComplete == false) {
                            var html = '';
                            if (result.data.planExecutUrl) {
                                html = $('#planItemHtml').html().replace(/{planExecutor}/g, result.data.planExecutor)
                                    .replace(/{planExecutUrl}/g, result.data.planExecutUrl)
                                    .replace(/{btnClass}/g, result.data.processInsId == "-1" ? "success" : "primary");
                            } else {
                                html = $('#planItemViewHtml').html().replace(/{planDisplayUrl}/g, result.data.planDisplayUrl);
                            }
                            $('#plan_item_' + planId).append(html);
                        }
                    }
                },
                global: false,
                error: function () {
                }
            })
        },

        loadTaskList: function (options) {
            var defaults = {
                target: undefined,
                projectId: undefined,
                planId: undefined
            }
            var defaults = $.extend({}, defaults, options);
            projectDetails.loadPlanItem(defaults.planId);
            $('#plan_task_list' + defaults.planId).treegrid({
                url: "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId",
                queryParams: {
                    projectId: defaults.projectId,
                    planId: defaults.planId
                },
                idField: 'id',
                treeField: 'projectPhaseName',
                datatype: 'json',
                lines: true,
                autoRowHeight: false,
                fitColumns: false,
                checkOnSelect: false,
                singleSelect: false,
                width: 'auto',
                method: "get",
                rownumbers: true,
                columns: [[
                    {field: 'cbx', title: 'id', checkbox: true},
                    {
                        field: 'projectPhaseName',
                        align: 'left',
                        title: '工作内容',
                        width: '20%',
                        formatter: function (value, row) {
                            var s = value;
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
                    {field: 'id', title: 'PlanItemId', align: 'center', hidden: true},
                    {
                        field: 'processInsId',
                        align: 'left',
                        title: '操作',
                        width: '20%',
                        formatter: function (value, row) {
                            var s = "";
                            if (!row.bisStart && row.bisLastLayer && '${isPM}' == 'true') {
                                s += " <a onclick='projectDetails.updateExecuteUser(\"" + row.id + "\")' href='javascript://' data-placement='top' data-original-title='调整责任人' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-user fa-white'></i></a>";
                            }
                            if (row.excuteUrl) {
                                var btnClass = 'btn-success';
                                if (/processInsId/.test(row.excuteUrl)) {
                                    btnClass = 'btn-primary';
                                }
                                s += " <a onclick='projectDetails.taskOpenWin(\"" + row.excuteUrl + "\")' href='javascript://' data-placement='top' data-original-title='处理' class='btn btn-xs " + btnClass + " tooltips' ><i class='fa fa-arrow-right fa-white'></i></a>";
                            } else if (row.displayUrl) {
                                if (row.canReplay) {
                                    s += " <a href='javascript://' onclick='projectDetails.replyTask(" + row.id + ")' data-placement='top' data-original-title='任务打回' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-reply fa-white'></i></a>";
                                }
                                s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                            }
                            //如果项目结束并且任务为现场查勘或案例调查，可申请将数据写入到案例库中

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

        //暂停
        pauseProject: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectPause/isChanging",
                data: {
                    projectId: "${projectInfo.id}",
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var url = "${pageContext.request.contextPath}/projectPause/apply?projectId=" + ${projectInfo.id};
                        window.open(url, '_blank');
                    }
                    else {
                        Alert("变更失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //重启
        restartProject: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectRestart/isChanging",
                data: {
                    projectId: "${projectInfo.id}",
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var url = "${pageContext.request.contextPath}/projectRestart/apply?projectId=" + ${projectInfo.id};
                        window.open(url, '_blank');
                    }
                    else {
                        Alert("变更失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //终止
        stopProject: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectStop/isChanging",
                data: {
                    projectId: "${projectInfo.id}",
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var url = "${pageContext.request.contextPath}/projectStop/apply?projectId=" + ${projectInfo.id};
                        window.open(url, '_blank');
                    }
                    else {
                        Alert("变更失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //选择进行中的阶段tab
        getRuningTab: function () {
            return $('.plan_tab').find('i:first').closest('li').find('a');
        },

        //选择激活的tab
        getActiveTab: function () {
            return $('.plan_tab').find('li.active').find('a');
        },

        //打开任务页面的回调
        taskOpenWin: function (url) {
            openWin(url, function () {
                projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
            })
        },

        //重启任务
        replyTask: function (planDetailsId) {
            layer.prompt({title: '输入重启任务的原因', formType: 2}, function (val, index) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/projectPlanDetails/replyProjectPlanDetails',
                    data: {
                        planDetailsId: planDetailsId,
                        reason: val
                    },
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('任务重启成功');
                            projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                            layer.close(index);
                        } else {
                            Alert(result.errmsg);
                        }
                    }
                })
            });
        },

        //调整责任人
        updateExecuteUser: function (planDetailsId) {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data && data.account) {
                        $.ajax({
                            url: '${pageContext.request.contextPath}/projectPlanDetails/updateExecuteUser',
                            data: {
                                planDetailsId: planDetailsId,
                                newExecuteUser: data.account
                            },
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('责任人调整成功');
                                    projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                                } else {
                                    Alert(result.errmsg);
                                }
                            }
                        });
                    } else {
                        Alert("还未选择任何人员");
                    }
                }
            });
        },

        //工作事项复制
        taskCopy: function (planId) {
            var rows = $('#plan_task_list' + planId).treegrid("getChecked");
            if (rows && rows.length > 0) {
                if (rows.length == 1) {
                    $('#lbl_copy_' + planId).text(rows[0].name).attr('data-planDetailsId', rows[0].id);
                } else {
                    Alert('只能选择一行数据作为复制数据');
                }
            } else {
                Alert('请选择需要复制的行数据');
            }
        },

        //工作事项粘贴
        taskPaste: function (planId) {
            var copyPlanDetailsId = $('#lbl_copy_' + planId).attr('data-planDetailsId');
            var rows = $('#plan_task_list' + planId).treegrid("getChecked");
            if (rows && rows.length > 0) {
                var pastePlanDetailsIdArray = [];
                $.each(rows, function (i, item) {
                    pastePlanDetailsIdArray.push(item.id);
                })
                $.ajax({
                    url: '${pageContext.request.contextPath}/projectPlanDetails/taskPaste',
                    data: {
                        copyPlanDetailsId: copyPlanDetailsId,
                        pastePlanDetailsIds: pastePlanDetailsIdArray.join()
                    },
                    success: function (result) {
                        if(result.ret){
                            Alert("粘贴完成");
                        }else{
                            Alert(result.errmsg);
                        }
                    }
                })
            } else {
                Alert('请选择需要粘贴的行数据');
            }
        }
    };

</script>

</html>
