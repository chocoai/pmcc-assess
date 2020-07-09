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
                            <%--<c:if test="${projectStatusEnum ne 'pause' and projectStatusEnum ne 'close' and projectStatusEnum ne 'finish'}">--%>
                            <%--<a class="btn btn-primary" href="javascript://" onclick="projectDetails.pauseProject()"><i--%>
                            <%--class="fa fa-pause">&nbsp;</i>暂停</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${projectStatusEnum=='pause'}">--%>
                            <%--<a class="btn btn-success" href="javascript://"--%>
                            <%--onclick="projectDetails.restartProject()"><i class="fa fa-reply">&nbsp;</i>重启</a>--%>
                            <%--</c:if>--%>
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
                                    <li>
                                        <a href="${pageContext.request.contextPath}/project.scheme.change/applyView?projectId=${projectInfo.id}"
                                           target="_blank">方案变更</a>
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
                            <a class="btn btn-primary" href="javascript://"
                               onclick="projectDetails.projectSubsequent()"></i>后续事项</a>
                            <a class="btn btn-primary" href="javascript://"
                               onclick="projectDetails.projectTakeNumber()"></i>项目拿号</a>
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
                    <%--<div class="btn-group">--%>
                        <%--<button type="button" onclick="projectDetails.enterNextStage();"--%>
                                <%--class="btn btn-primary">--%>
                            <%--进入下阶段--%>
                        <%--</button>--%>
                    <%--</div>--%>
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
                                    <div class="col-md-5" id="showZtree${plan.id}" style="display: none;">
                                        <p>
                                            <small>
                                                <a href="javascript://;" class="btn btn-xs btn-success"
                                                   onclick="batchUpdateExecuteUser()">批量设置责任人
                                                </a>
                                            </small>
                                            <span class="radio-inline">
                                            <input type="radio" required name="checkedType"
                                                   onclick="checkAllOrNo(true)"><label>全选</label>
                                            </span>
                                            <span class="radio-inline">
                                            <input type="radio" name="checkedType" onclick="checkAllOrNo(false)"><label>全不选</label>
                                            </span>
                                            <span class="radio-inline">
                                            <input type="radio" name="checkedType"
                                                   onclick="checkReverse()"><label>反选</label>
                                            </span>
                                            <span class="radio-inline">
                                            <input type="radio" name="expandType"
                                                   onclick="expandAll(true)"><label>展开</label>
                                            </span>
                                            <span class="radio-inline">
                                            <input type="radio" name="expandType"
                                                   onclick="expandAll(false)"><label>收缩</label>
                                            </span>
                                        </p>
                                        <ul id="ztree${plan.id}" class="ztree"></ul>
                                    </div>
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
                        项目意见稿
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_documentOpinionList">
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        后续事项
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_subsequentList">
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        项目拿号
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_takeNumber">
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
                    <table class="table table-bordered" id="tb_projectBillList">
                    </table>
                </div>
            </div>
            <%@include file="/views/share/form_details.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<div id="ztreePlanTaskInfo" style="display: none;">
    <div class="col-md-12 form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">开始时间</label>
                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                    <label class="form-control dbdate" name="planStartDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">结束时间</label>
                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                    <label class="form-control dbdate" name="planEndDate"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">计划工时</label>
                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                    <label class="form-control" name="planHours"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">提交时间</label>
                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                    <label class="form-control dbdate" name="taskSubmitTime"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">责任人</label>
                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                    <label class="form-control" name="executeUserName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div style="text-align: center;" id="operateToolbar">
            </div>
        </div>
    </div>
</div>
<div id="replyTaskBox" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">重启任务</h3>
            </div>
            <div class="modal-body">
                <form id="replyTaskForm" class="form-horizontal">
                    <input type="hidden" id="planDetailsId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            重启原因<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="重启原因" name="reason"
                                                      class="form-control" required></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            附件
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="returnUploadFile" type="file" multiple="false">
                                            <div id="_returnUploadFile"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="projectDetails.replyTaskBtn()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js?v=${assessVersion}"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        //注册事件
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            projectDetails.loadPlanTabInfo($(this));
        });

        projectDetails.getRuningTab().tab('show');
        projectDetails.loadDocumentSend();
        projectDetails.loadDocumentOpinion();
        projectDetails.loadSubsequent();
        projectDetails.loadTakeNumber();
        projectDetails.loadProjectLog();
        projectDetails.loadProjectLegwork();
        projectDetails.loadProjectBill();
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
    var copyPlanDetailsTempId = undefined;
    var projectDetails = {
        loadPlanTabInfo: function (tab) {
            var that = $(tab).closest('li');
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/getTotalPlanDetails",
                data: {
                    planId: that.attr('plan-id')
                },
                type: 'post',
                success: function (result) {
                    if (result) {
                        if (result.data <= 3000) {
                            $("#showZtree" + that.attr('plan-id')).hide();
                            projectDetails.loadTaskList({
                                target: $('#plan_task_list' + that.attr('plan-id')),
                                projectId: '${projectInfo.id}',
                                planId: that.attr('plan-id')
                            });
                        } else {
                            $("#showZtree" + that.attr('plan-id')).show();
                            ztreeInit({
                                target: $('#ztree' + that.attr('plan-id')),
                                projectId: '${projectInfo.id}',
                                planId: that.attr('plan-id')
                            });
                        }
                    }
                }
            })

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
                            if (result.data.planExecutUrl) {
                                var btnClass = result.data.processInsId == "-1" ? "success" : "primary";
                                html += '<div class="btn btn-sm btn-' + btnClass + '" data-placement="top" data-toggle="tooltip" onclick="projectDetails.taskOpenWin(\'' + result.data.planExecutUrl + '\')" data-original-title="处理"><i class="fa fa-arrow-right"></i> </div>';
                            } else if ('${projectInfo.projectStatus}' != 'finish' && '${isPM}' == 'true' && result.data.status != 'runing') {
                                html += '<div class="btn btn-sm btn-primary" data-placement="top" data-toggle="tooltip" data-original-title="重启" onclick="projectDetails.replyPlan(' + planId + ');"><i class="fa fa-reply"></i></div>';
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
                    formData: JSON.stringify({
                        projectId: defaults.projectId,
                        planId: defaults.planId
                    })
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
                            console.log(row);
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
                        notifySuccess('成功',"关注成功");
                        $("#btn_followProject").hide();
                        $("#btn_cancelFollowProject").show();
                    }
                    else {
                        Alert("关注失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                        notifySuccess('成功',"取消成功");
                        $("#btn_followProject").show();
                        $("#btn_cancelFollowProject").hide();
                    }
                    else {
                        Alert("取消失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        },

        //后续事项
        projectSubsequent: function () {
            var url = "${pageContext.request.contextPath}/projectSubsequent/apply?projectId=" + ${projectInfo.id};
            window.open(url, '_blank');
        },
        //项目拿号
        projectTakeNumber: function () {
            var url = "${pageContext.request.contextPath}/projectTakeNumber/apply?projectId=" + ${projectInfo.id};
            window.open(url, '_blank');
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
            layer.closeAll();
            openWin(url, function () {
                projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
            })
        },

        //打开任务页面的回调
        ztreeTaskOpenWin: function (id, url) {
            layer.closeAll();
            openWin(url, function () {
                //projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                $.ajax({
                    url: "${pageContext.request.contextPath}/projectPlanDetails/getProjectPlanDetailsById",
                    data: {
                        id: id
                    },
                    type: 'post',
                    success: function (result) {
                        if (result) {
                            refreshNode(result.data);
                        }
                    }
                })
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
                            notifySuccess('成功','计划重启成功');
                            projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                            layer.closeAll();
                        } else {
                            notifyInfo('提示',result.errmsg);
                        }
                    }
                })
            });
        },

        //重启任务modal
        replyTask: function (planDetailsId) {
            $("#replyTaskForm").clearAll();
            $("#_returnUploadFile").empty();
            $("#replyTaskForm").find("#planDetailsId").val(planDetailsId);
            //重启附件
            projectDetails.loadTemplateAttachment(planDetailsId);
            FileUtils.uploadFiles({
                target: "returnUploadFile",
                showFileList: false,
                onUpload: function (file) {//上传之前触发
                    var formData = {
                        tableName: AssessDBKey.ProjectTaskReturnRecord,
                        tableId: 0,
                        fieldsName: planDetailsId
                    };
                    return formData;
                },
                onUploadComplete: function () {
                    projectDetails.loadTemplateAttachment(planDetailsId);
                }
            });
            $("#replyTaskBox").modal("show");
        },

        //加载附件
        loadTemplateAttachment: function (planDetailsId) {
            FileUtils.getFileShows({
                target: "returnUploadFile",
                formData: {
                    tableName: AssessDBKey.ProjectTaskReturnRecord,
                    tableId: 0,
                    fieldsName: planDetailsId
                },
                editFlag: true,
                deleteFlag: true
            });
        },

        //重启任务
        replyTaskBtn: function () {
            var planDetailsId = $("#replyTaskForm").find("#planDetailsId").val();
            if (!$("#replyTaskForm").valid()) {
                return false;
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/projectPlanDetails/replyProjectPlanDetails',
                data: {
                    planDetailsId: planDetailsId,
                    formData: JSON.stringify(formParams("replyTaskForm"))
                },
                success: function (result) {
                    if (result.ret) {
                        $("#replyTaskBox").modal("hide");
                        notifySuccess('成功','任务重启成功');
                        //projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                        refreshNode(result.data);
                    } else {
                        notifyInfo('提示',result.errmsg);
                    }
                }
            })

        },

        //调整责任人
        updateExecuteUser: function (planDetailsId) {
            layer.close(layer.index);
            erpEmployee.select({
                currOrgId: '${companyId}',
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
                                    notifySuccess('成功','责任人调整成功');
                                    //projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                                    refreshNode(result.data);
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
            //$(_this).closest('.tab-pane').find('[name=copyPlanDetailsId]').val(id);
            copyPlanDetailsTempId = id;

        },

        //工作事项粘贴
        taskPaste: function (_this, id) {
            var copyPlanDetailsId = copyPlanDetailsTempId;
            if (!copyPlanDetailsId) {
                Alert('请选择复制对象');
                return false;
            }
            if (id == copyPlanDetailsId) {
                Alert('无法粘贴自己');
                return false;
            }
            layer.closeAll();
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
                        notifySuccess('成功',"粘贴完成");
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
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    return "<a target='_blank' href='${pageContext.request.contextPath}/documentSend/detailsIndex?processInsId=" + row.processInsId + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
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

        //项目意见稿
        loadDocumentOpinion: function () {
            var cols = [];
            cols.push({field: 'areaGroupName', title: '区域名称'});
            cols.push({field: 'reportTypeName', title: '报告类型'});
            cols.push({field: 'fileViewName', title: '意见稿'});
            $("#tb_documentOpinionList").bootstrapTable('destroy');
            TableInit("tb_documentOpinionList", "${pageContext.request.contextPath}/documentOpinion/getDocumentOpinionVoList", cols, {
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

        //后续事项
        loadSubsequent: function () {
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'suggestion', title: '处理意见'});
            cols.push({field: 'fileViewName', title: '附件'});
            $("#tb_subsequentList").bootstrapTable('destroy');
            TableInit("tb_subsequentList", "${pageContext.request.contextPath}/projectSubsequent/getSubsequentList", cols, {
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

        //项目拿号类型、文号、说明、拿号人、拿号时间
        loadTakeNumber: function () {
            var cols = [];
            cols.push({field: 'reportTypeName', title: '报告类型'});
            cols.push({field: 'numberValue', title: '文号'});
            cols.push({field: 'remark', title: '说明'});
            cols.push({field: 'creatorName', title: '拿号人'});
            cols.push({
                field: 'takeTime', title: '拿号时间', formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            $("#tb_takeNumber").bootstrapTable('destroy');
            TableInit("tb_takeNumber", "${pageContext.request.contextPath}/projectTakeNumber/getTakeNumberList", cols, {
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
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'creator', title: '创建人'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            $("#tb_projectLogList").bootstrapTable('destroy');
            TableInit("tb_projectLogList", "${pageContext.request.contextPath}/home/getWorkLogByProjectId", cols, {
                publicProjectId: '${projectInfo.publicProjectId}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

        //项目外勤
        loadProjectLegwork: function () {
            var cols = [];
            cols.push({field: 'legworkContent', title: '内容'});
            cols.push({field: 'creator', title: '创建人'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    return "<a target='_blank' href='/pmcc-hr/hrBase/detailsIndex?processInsId=" + row.processInsId + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                }
            });
            $("#tb_projectLegWorkList").bootstrapTable('destroy');
            TableInit("tb_projectLegWorkList", "${pageContext.request.contextPath}/rpcHrService/getHrLegworkList", cols, {
                publicProjectId: '${projectInfo.publicProjectId}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

        //选择人员
        personSelect: function () {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data.account) {
                        $("#returnPersonName").val(data.name);
                        $("#returnPerson").val(data.account);
                    }
                    else {
                        $("#returnPerson").val('');
                        $("#returnPerson").val('');
                    }
                }
            });
        },

        //项目开票
        loadProjectBill: function () {
            var cols = [];
            cols.push({field: 'billNumber', title: '票号'});
            cols.push({
                field: 'amount', title: '开票金额', formatter: function (value, row, index) {
                    if (value) {
                        return (Number(value) / 100).toFixed(2);
                    }
                }
            });
            cols.push({
                field: 'payAmount', title: '到账金额', formatter: function (value, row, index) {
                    if (value) {
                        return (Number(value) / 100).toFixed(2);
                    }
                }
            });
            cols.push({field: 'applyUserName', title: '申请人'});
            cols.push({
                field: 'created', title: '申请日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    return "<a target='_blank' href='/pmcc-finance/FinancialBase/DetailsIndex?processInsId=" + row.processInsId + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                }
            });
            $("#tb_projectBillList").bootstrapTable('destroy');
            TableInit("tb_projectBillList", "${pageContext.request.contextPath}/rpcFinanceService/getFinancialBillMakeOutList", cols, {
                publicProjectId: '${projectInfo.publicProjectId}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

        //进入项目下个阶段
        enterNextStage: function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/projectInfo/enterNextStage',
                data: {
                    projectId: '${projectInfo.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功','操作成功');
                    } else {
                        Alert(result.errmsg);
                    }
                }
            });
        }
    };


</script>
<script type="application/javascript">
    var zTreeObj;

    function ztreeInit(options) {
        var defaults = {
            target: undefined,
            projectId: undefined,
            planId: undefined
        }
        var defaults = $.extend({}, defaults, options);
        var setting = {
            check: {
                enable: true
            },
            view: {
                selectedMulti: false, //允许同时选中多个节点。
                showIcon: true
            },
            data: {
                key: {
                    name: "nodeName"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid"
                }
            },
            // 回调函数
            callback: {
                onClick: function (event, treeId, treeNode, clickFlag) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/projectInfo/getProjectPlanDetail",
                        data: {
                            id: treeNode.id
                        },
                        type: 'post',
                        success: function (result) {
                            if (result) {
                                var s = "";
                                if (!result.data.bisStart && result.data.bisLastLayer && '${isPM}' == 'true') {
                                    s += " <a onclick='projectDetails.updateExecuteUser(\"" + result.data.id + "\")' href='javascript://' title='调整责任人' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-user fa-white'></i></a>";
                                }
                                if (result.data.excuteUrl) {
                                    var btnClass = 'btn-success';
                                    if (/processInsId/.test(result.data.excuteUrl)) {
                                        btnClass = 'btn-primary';
                                    }
                                    s += " <a onclick='projectDetails.ztreeTaskOpenWin(" + result.data.id + ",\"" + result.data.excuteUrl + "\")' href='javascript://' title='处理' class='btn btn-xs " + btnClass + " tooltips' ><i class='fa fa-arrow-right fa-white'></i></a>";
                                } else if (result.data.displayUrl) {
                                    if (result.data.canReplay) {
                                        s += " <a href='javascript://' onclick='projectDetails.replyTask(" + result.data.id + ")' title='重启' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-reply fa-white'></i></a>";
                                    }
                                    s += " <a target='_blank' href='" + result.data.displayUrl + "' title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                                }
                                if (result.data.canCopy) {
                                    s += " <a href='javascript://' onclick='projectDetails.taskCopy(this," + result.data.id + ");' data-planDetailsId='" + result.data.id + "' title='复制' class='btn btn-xs btn-warning btn-copy' ><i class='fa fa-copy fa-white'></i> <span>复制</span></a>";
                                }
                                if (result.data.canPaste) {
                                    s += " <a href='javascript://' onclick='projectDetails.taskPaste(this," + result.data.id + ");' data-planDetailsId='" + result.data.id + "' title='粘贴' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-paste fa-white'></i> <span>粘贴</span></a>";
                                }

                                var taskInfo = $("#ztreePlanTaskInfo");
                                taskInfo.initForm(treeNode);
                                $("#operateToolbar").empty().append(s);
                                //显示对应数据 显示可操作按钮
                                //页面层
                                layer.closeAll('page');
                                layer.open({
                                    type: 1,
                                    shade: 0,
                                    area: ['50%', '300px'], //宽高
                                    content: $("#ztreePlanTaskInfo").html(),
                                    btnAlign: 'c',
                                });
                            }
                        }
                    })

                }
            }
        };
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId",
            data: {
                projectId: defaults.projectId,
                planId: defaults.planId
            },
            type: 'get',
            success: function (result) {
                if (result) {
                    $.each(result.rows, function (i, item) {
                        setNodeIcon(item);
                    })
                    zTreeObj = $.fn.zTree.init(defaults.target, setting, result.rows);
                    zTreeObj.expandAll(true);
                }
            }
        })
    }


    //调整责任人
    function batchUpdateExecuteUser() {
        var nodes = zTreeObj.getCheckedNodes(true);
        var length = 0;
        for (var p in nodes) {
            length++;
        }
        if (length == 0) {
            alert("请勾选节点");
            return false;
        }
        var planDetailsIds = [];
        for (var i = 0; i < length; i++) {
//            if (nodes[i].bisRestart == false && nodes[i].bisStart == false) {
//                continue;
//            }
            if (nodes[i].bisLastLayer == true) {
                planDetailsIds.push(nodes[i].id);
            }
//            if (nodes[i].bisLastLayer == false && nodes[i].pid != null && nodes[i].pid != 0) {
//                planDetailsIds.push(nodes[i].id);
//            }

        }
        console.log(planDetailsIds);
        layer.close(layer.index);
        erpEmployee.select({
            currOrgId: '${companyId}',
            onSelected: function (data) {
                if (data && data.account) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/projectPlanDetails/batchUpdateExecuteUser',
                        data: {
                            planDetailsIds: planDetailsIds.join(),
                            newExecuteUser: data.account
                        },
                        success: function (result) {
                            if (result.ret) {
                                //projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
                                var that = $(projectDetails.getActiveTab()).closest('li');
                                ztreeInit({
                                    target: $('#ztree' + that.attr('plan-id')),
                                    projectId: '${projectInfo.id}',
                                    planId: that.attr('plan-id')
                                });
                                notifySuccess('成功','责任人调整成功');
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
    }

    /**
     * 刷新当前节点
     *
     */
    function refreshNode(data) {
        if (zTreeObj) {
            var node = zTreeObj.getSelectedNodes()[0];
            node.nodeName = data.nodeName;
            node.status = data.status;
            node.returnDetailsReason = data.returnDetailsReason;
            node.taskSubmitTime = data.taskSubmitTime;
            node.bisStart = data.bisStart;
            node.processInsId = data.processInsId;
            node.actualHours = data.actualHours;
            node.bisRestart = data.bisRestart;
            node.displayUrl = data.displayUrl;
            node.canReplay = data.canReplay;
            node.excuteUrl = data.excuteUrl;
            setNodeIcon(node);
            zTreeObj.updateNode(node, false);
        } else {
            projectDetails.loadPlanTabInfo(projectDetails.getActiveTab());
        }
    }

    function setNodeIcon(item) {
        //已完成
        if (item.displayUrl && item.canReplay == true && !item.excuteUrl) {
            item.icon = "${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/icons/search.png";
        }
        //待处理
        else if (item.bisStart == false && item.excuteUrl) {
            item.icon = "${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/icons/pencil.png";
        }
        //待审批
        else if (item.excuteUrl && item.bisStart == true) {
            item.icon = "${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/icons/tip.png";
        }
    }

    //全选或全不选
    function checkAllOrNo(flag) {
        zTreeObj.checkAllNodes(flag);
    }
    //反选
    function checkReverse() {
        var checkedTrueNodes = zTreeObj.getCheckedNodes(true);//选中的节点
        var allNodes = zTreeObj.getNodes();//获取所有节点
        var nodes = zTreeObj.transformToArray(allNodes); //转变为数组
        zTreeObj.checkAllNodes(true);//全选
        $.each(nodes, function (index, node) {
            $.each(checkedTrueNodes, function (i, checkedTrue) {
                if (node == checkedTrue) {
                    node.checked = false;  //设为不选中
                    zTreeObj.updateNode(node);//更新状态
                }
            });

        });

    }
    //展开或收缩
    function expandAll(flag) {
        zTreeObj.expandAll(flag);
    }


</script>
</html>
