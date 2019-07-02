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
                            <a class="btn btn-danger" href="javascript://"
                               onclick="projectDetails.finishProject()"><i class="fa fa-check">&nbsp;</i>完成</a>
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
                                <button type="button" class="btn btn-primary">
                                    项目变更
                                </button>
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
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
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary">
                                    项目发文
                                </button>
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <c:forEach var="item" items="${documentTemplateList}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/documentSend/applyIndex/${item.id}&${projectInfo.id}"
                                               target="_blank">${item.templateName}</a>
                                        </li>
                                    </c:forEach>
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
                    <h3>
                        工作成果
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="btn-group">
                        <a target="_blank" class="btn btn-primary"
                           href="${pageContext.request.contextPath}/projectReportFile/index?projectId=${projectInfo.id}">估价委托书及相关证明</a>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());"
                                class="btn btn-primary">
                            <i class='fa fa-refresh fa-white'></i> 刷新
                        </button>
                    </div>

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
                                                <div class="btn btn-sm btn-dark">计划编制</div>
                                                <div class="btn btn-sm btn-default" data-placement="top"
                                                     data-toggle="tooltip"
                                                     data-original-title="责任人">${plan.planExecutor}
                                                </div>
                                                <c:if test="${empty plan.planExecutUrl}">
                                                    <div class="btn btn-sm btn-warning" data-placement="top"
                                                         data-toggle="tooltip" data-original-title="重启"
                                                         onclick=""><i class="fa fa-reply"></i></div>
                                                    <div class="btn btn-sm btn-warning" data-placement="top"
                                                         data-toggle="tooltip" data-original-title="查看"
                                                         onclick="window.open('${plan.planDisplayUrl}')"><i
                                                            class="fa fa-search"></i></div>
                                                </c:if>
                                                <c:if test="${not empty plan.planExecutUrl and plan.planCanExecut eq true}">
                                                    <div class="btn btn-sm btn-success" data-placement="top"
                                                         data-toggle="tooltip"
                                                         onclick="projectDetails.taskOpenWin('${plan.planExecutUrl}')"
                                                         data-original-title="处理"><i class="fa fa-arrow-right"></i>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </c:if>
                                    </div>
                                    <p>
                                        <input type="hidden" name="copyPlanDetailsId">
                                    <table id="plan_task_list${plan.id}" class="table table-bordered"></table>
                                    </p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        项目发文
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_documentSendList">
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        项目日志
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_projectLogList">
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        外勤信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_projectLegWorkList">
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        开票信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_projectInvoiceList">
                    </table>
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

        projectDetails.getRuningTab().tab('show');
        projectDetails.loadDocumentSend();
        projectDetails.loadProjectLog();
        setInterval(function () {
            projectDetails.loadPlanItem(projectDetails.getActiveTab().closest('li').attr('plan-id'));
        }, 30 * 1000)
    })

    function writeToErpProject() {
        $.ajax({
            url: '${pageContext.request.contextPath}/home/writeToErpProject',
            data: {projectId: '${projectInfo.id}'},
            type: 'post',
            success: function (result) {
                if (result.ret) {
                    Alert("写入成功");
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }
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
                            var html = '<div class="btn-group">';
                            html += '<div class="btn btn-sm btn-dark">' + result.data.planName + '</div>';
                            if (result.data.planExecutor) {
                                html += '<div class="btn btn-sm btn-default" data-placement="top" data-toggle="tooltip" data-original-title="责任人">' + result.data.planExecutor + '</div>';
                            }
                            if (result.data.projectStatus == 'finish' && '${projectInfo.projectStatus}' != 'finish') {
                                html += '<div class="btn btn-sm btn-primary" data-placement="top" data-toggle="tooltip" data-original-title="重启" onclick="projectDetails.replyPlan(' + planId + ');"><i class="fa fa-reply"></i></div>';
                            }
                            if (result.data.planExecutUrl) {
                                var btnClass = result.data.processInsId == "-1" ? "success" : "primary";
                                html += '<div class="btn btn-sm btn-' + btnClass + '" data-placement="top" data-toggle="tooltip" onclick="projectDetails.taskOpenWin(\'' + result.data.planExecutUrl + '\')" data-original-title="处理"><i class="fa fa-arrow-right"></i> </div>';
                            }
                            html += '<div class="btn btn-sm btn-warning" data-placement="top" data-toggle="tooltip" data-original-title="查看" onclick="window.open(\'' + result.data.planDisplayUrl + '\')"><i class="fa fa-search"></i></div>';
                            html += '</div>';
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
                lines: false,
                autoRowHeight: false,
                fitColumns: false,
                checkOnSelect: false,
                singleSelect: false,
                width: 'auto',
                method: "get",
                rownumbers: true,
                toolbar: "#plan_task_list_toolbar",
                columns: [[
                    {
                        field: 'projectPhaseName',
                        align: 'left',
                        title: '工作内容',
                        width: '30%',
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
                                s += " <a onclick='projectDetails.updateExecuteUser(\"" + row.id + "\")' href='javascript://' title='调整责任人' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-user fa-white'></i></a>";
                            }
                            if (row.excuteUrl) {
                                var btnClass = 'btn-success';
                                if (/processInsId/.test(row.excuteUrl)) {
                                    btnClass = 'btn-primary';
                                }
                                s += " <a onclick='projectDetails.taskOpenWin(\"" + row.excuteUrl + "\")' href='javascript://' title='处理' class='btn btn-xs " + btnClass + " tooltips' ><i class='fa fa-arrow-right fa-white'></i></a>";
                            } else if (row.displayUrl) {
                                if (row.canReplay) {
                                    s += " <a href='javascript://' onclick='projectDetails.replyTask(" + row.id + ")' title='重启' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-reply fa-white'></i></a>";
                                }
                                s += " <a target='_blank' href='" + row.displayUrl + "' title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                            }
                            if (row.canCopy) {
                                s += " <a href='javascript://' onclick='projectDetails.taskCopy(this," + row.id + ");' data-planDetailsId='" + row.id + "' title='复制' class='btn btn-xs btn-warning btn-copy' ><i class='fa fa-copy fa-white'></i> <span>复制</span></a>";
                            }
                            if (row.canPaste) {
                                s += " <a href='javascript://' onclick='projectDetails.taskPaste(this," + row.id + ");' data-planDetailsId='" + row.id + "' title='粘贴' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-paste fa-white'></i> <span>粘贴</span></a>";
                            }
                            return s;
                        }
                    }
                ]],
                onLoadSuccess: function () {
                    var copyPlanDetailsId = $('#tab_plan_' + defaults.planId).find('[name=copyPlanDetailsId]').val();
                    if (copyPlanDetailsId) {
                        $('#tab_plan_' + defaults.planId).find('.btn-copy[data-planDetailsId=' + copyPlanDetailsId + ']').find('span').text('已被复制');
                    }
                }
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

        //完成
        finishProject: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/finishProject",
                data: {
                    projectId: "${projectInfo.id}",
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        Alert("操作成功，项目正常完成");
                    }
                    else {
                        Alert(result.errmsg);
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

        //重启计划
        replyPlan: function (planId) {
            layer.prompt({title: '输入重启计划的原因', formType: 2}, function (val, index) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/ProjectPlan/replyProjectPlan',
                    data: {
                        planId: planId,
                        reason: val
                    },
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('计划重启成功');
                            projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                            layer.close(index);
                        } else {
                            toastr.info(result.errmsg);
                        }
                    }
                })
            });
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
                            toastr.info(result.errmsg);
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
        taskCopy: function (_this, id) {
            $(_this).closest('.tab-pane').find('.btn-copy').each(function () {
                $(this).find('span').text('复制');
            });
            $(_this).find('span').text('已被复制');
            $(_this).closest('.tab-pane').find('[name=copyPlanDetailsId]').val(id);
        },

        //工作事项粘贴
        taskPaste: function (_this, id) {
            var copyPlanDetailsId = $(_this).closest('.tab-pane').find('[name=copyPlanDetailsId]').val();
            if (!copyPlanDetailsId) {
                Alert('请选择复制对象');
                return false;
            }
            if (id == copyPlanDetailsId) {
                Alert('无法粘贴自己');
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/projectPlanDetails/taskPaste',
                data: {
                    copyPlanDetailsId: copyPlanDetailsId,
                    pastePlanDetailsId: id
                },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                        toastr.success("粘贴完成");
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        },

        //项目发文
        loadDocumentSend: function () {
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({field: 'userName', title: '创建人'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            $("#tb_documentSendList").bootstrapTable('destroy');
            TableInit("tb_documentSendList", "${pageContext.request.contextPath}/documentSend/getDocumentSendVoList", cols, {
                projectId: ${projectInfo.id}
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

        //项目日志
        loadProjectLog: function () {
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'userName', title: '创建人'});
            $("#tb_projectLogList").bootstrapTable('destroy');
            TableInit("tb_projectLogList", "${pageContext.request.contextPath}/home/getWorkLogByProjectId", cols, {
                publicProjectId: ${projectInfo.publicProjectId}
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }

        //项目外勤

        //项目开票
    };

</script>

</html>
