<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h3>
                         ${csrProjectInfo.name}
                        <small><label style="padding: 5px;" class="label label-danger">
                            ${csrProjectInfo.projectStatus}
                        </label></small>
                    </h3>

                </div>
                <div class="title_right">
                    <div class="col-md-12 col-sm-12 col-xs-12 pull-right" style="margin: 0px">
                        <a id="btn_followProject" style="margin-left: 20px;display: none" class="btn btn-warning"
                           onclick="followProject()">关注</a>
                        <a id="btn_cancelFollowProject" style="margin-left: 20px;display: none" class="btn btn-warning"
                           onclick="cancelFollowProject()">取消关注</a>
                        <c:if test="${projectStatusEnum=='normal'}">
                            <a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/projectClose/closeIndex?projectId=${csrProjectInfo.id}"
                               target="_blank">终止</a>
                            <a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/ProjectSuspend/suspendIndex?projectId=${csrProjectInfo.id}"
                               target="_blank">暂停</a>
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger">
                                    项目变更
                                </button>
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a onclick="changeMember()" target="_blank">成员变更</a>
                                        <a href="${pageContext.request.contextPath}/ProjectWorkStageRestart/restartApply?projectId=${csrProjectInfo.id}"
                                           target="_blank">阶段重启</a>
                                        <a href="${pageContext.request.contextPath}/projectPlanHistory/projectPlanHistoryIndex?projectId=${csrProjectInfo.id}"
                                           target="_blank">总体时间</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-success">
                                    人力资源相关
                                </button>
                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="/pmcc-hr/HrBase/ApplyIndex/${legwork}/${csrProjectInfo.publicProjectId}" target="_blank">外勤</a>
                                        <a href="/pmcc-hr/HrBase/ApplyIndex/${overtime}/${csrProjectInfo.publicProjectId}" target="_blank">加班</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-success">
                                    费用相关
                                </button>
                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="/pmcc-finance/FinancialBase/ApplyIndex/${expense}/${csrProjectInfo.publicProjectId}" target="_blank">费用报销</a>
                                        <a href="/pmcc-finance/FinancialBase/ApplyIndex/${pay}/${csrProjectInfo.publicProjectId}" target="_blank">项目走款</a>
                                        <a href="/pmcc-finance/FinancialBase/ApplyIndex/${pettyCash}/${csrProjectInfo.publicProjectId}" target="_blank">项目借款</a>
                                        <a href="/pmcc-finance/FinancialBase/ApplyIndex/${bill}/${csrProjectInfo.publicProjectId}" target="_blank">开发票</a>
                                        <a href="/pmcc-finance/FinancialBase/ApplyIndex/${receivables}/${csrProjectInfo.publicProjectId}" target="_blank">到账确认</a>
                                        <a href="/pmcc-finance/FinancialBase/ApplyIndex/${strikeBalance}/${csrProjectInfo.publicProjectId}" target="_blank">冲账</a>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${projectStatusEnum=='pause'}">
                            <a class="btn btn-success"
                               onclick="restartProject()"
                               target="_blank">重启</a>
                        </c:if>

                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <c:if test="${not empty keyValueDtos}">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>项目任务</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <c:forEach var="item" items="${keyValueDtos}">
                                    <label class="label  ${item.explain}">${item.key}：${item.value}</label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="panel panel-default" id="panel_project_suspend" style="display: none;">
                <div class="panel-heading">
                    <i class="fa fa-external-link-square"></i>项目暂停记录
                </div>
                <div class="panel-body">
                    <table id="tb_projectSuspend" class="table table-bordered">
                    </table>
                </div>
            </div>
            <!--招标信息-->
            <%@include file="/views/share/csr/projectInfoCsr.jsp" %>
            <div class="x_panel">
                <c:forEach items="${csrProjectInfo.csrProjectInfoGroupVos}" var="data">
                <div class="x_title">
                    <h2>${data.projectName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="form-horizontal">
                    <div class="x_content">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                项目经理
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control" id="lab_userAccountManagerName">${data.projectManagerName}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                项目成员
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${data.projectMemberName}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                文号
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${data.number}</label>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2>工作成果</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered"></table>
                </div>
            </div>
            <%@include file="/views/share/form_details.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>


<div id="projectMember" class="modal fade" data-backdrop="static" tabindex="-1" data-width="760"
     style="display: none;">
    <div class="modal-header">
        <h4 class="modal-title">项目组员</h4>
    </div>
    <div class="modal-body">
        <form id="frm_projectMemeber" class="form-horizontal">
            <%@include file="/views/project/member/memeberModel.jsp" %>
        </form>
    </div>
    <div class="modal-footer">
        <button type='button' onclick="saveProjectMember()" class='btn btn-success save-event'>
            <i class='fa fa-check'></i> 确认
        </button>
        <button type="button" data-dismiss="modal" class="btn btn-light-grey">
            关闭
        </button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        if ("${projectFollowFlog}" == "0") {
            $("#btn_followProject").show();
            $("#btn_cancelFollowProject").hide();
        }
        else {
            $("#btn_followProject").hide();
            $("#btn_cancelFollowProject").show();
        }

        if ("${canRestart}" == "1") {
            loadProjectSuspend();
        }

        treeGridload();
    });


    function loadProjectSuspend() {
        var cols = [];
        cols.push({field: 'supendDate', title: '暂停时间'});
        cols.push({field: 'suspendUserName', title: '申请人'});
        cols.push({field: 'suspendReason', title: '暂停原因'});
        cols.push({field: 'status', title: '处理结论'});
        cols.push({
            field: 'processInsId', title: '查看审批', formatter: function (value, row, index) {
                var str = "";
                str += "<a target='_blank' href='/ProjectSuspend/suspendDetails?processInsId=" + value + "'>查看</a>";
                return str;
            }
        });
        cols.push({
            field: 'files', title: '附件', formatter: function (value, row, index) {
                var str = "";
                $.each(value, function (i, j) {
                    if (j.key != null) {
                        str += "<a onclick='showAttachment(" + j.key + ",\"" + j.explain + "\")' class='fileupload-preview'>" + j.value + "</a>";
                        str += "<a><i class='fa fa-download' onclick='downAttachments(" + j.key + ")' style='margin-left: 15px;font-size: 15px;'></i></a>";
                        str += "<br/>";
                    }
                })
                return str;
            }
        });
        TableInit("tb_projectSuspend", "${pageContext.request.contextPath}/ProjectSuspend/getProjectSuspendHistory", cols, {
            projectId: "${csrProjectInfo.id}"
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            onLoadSuccess: function (data) {
                console.log(data);
                if (data) {
                    $("#panel_project_suspend").show();
                }
            }
        });
    }

    function changeMember() {
        $('#projectMember').modal({
            backdrop: 'static'
        });
    }

    function saveProjectMember() {
        $("#frm_projectMemeber").validate();
        if (!$("#frm_projectMemeber").valid()) {
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/projectMember/saveChangeProjectMemeber",
            type: "post",
            dataType: "Json",
            data: {
                userAccountManager: $("#userAccountManager").val(),
                userAccountMember: $("#userAccountMember").val(),
                userAccountQuality: $("#userAccountQuality").val(),
                projectId: "${csrProjectInfo.id}"
            },
            success: function (result) {
                if (result.ret) {
                    $('#projectMember').modal("hide");
                    $("#lab_userAccountManagerName").html($("#userAccountManagerName").val());
                    $("#lab_userAccountMemberName").html($("#userAccountMemberName").val());
                    $("#lab_userAccountQualityName").html($("#userAccountQualityName").val());

                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }

            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });


    }

    function addWorkLog() {
        $("#id").val(0);
        $("#title").val("");
        $("#content").val("");
        $("#projectIds").val("${csrProjectInfo.id}");
        $("#projectNames").val("${csrProjectInfo.projectName}");
        $("#_file_upload").html("");
        $('#workLog').modal({
            backdrop: 'static'
        });
    }

    function treeGridload() {
        $("#PlanItemListed").treegrid({
            url: "${pageContext.request.contextPath}/csrProjectInfo/getProjectTaskByProjectId",
            queryParams: {projecId: ${csrProjectInfo.id}},
            idField: 'id',
            treeField: 'projectPhaseName',
            datatype: 'json',
            lines: true,
            width: 'auto',
            method: "get",
            rownumbers: true,
            columns: [[
                {
                    field: 'projectPhaseName', align: 'left', title: '工作内容', width: '20%', formatter: function (value, row) {
                    var s = value;
                    if (row.bisNew) {
                        s += "<i class='clip-new' style='font-size: 15px;color: red'></i>";
                    }
                    return s;
                }
                },
                {
                    field: 'planStartDate', align: 'center', title: '开始时间', width: '10%', formatter: function (value, row) {
                    return formatDate(value, false);
                }
                },
                {
                    field: 'planEndDate', align: 'center', title: '结束时间', width: '10%', formatter: function (value, row) {
                    return formatDate(value, false);
                }
                },
                {field: 'planHours', align: 'center', title: '计划工时(h)', width: '5%'},
                {field: 'actualHours', align: 'center', title: '实际工时(h)', width: '5%'},
                {
                    field: 'taskSubmitTime', align: 'center', title: '提交时间', width: '10%', formatter: function (value, row) {
                    return formatDate(value, false);
                }
                },
                {field: 'executeUserName', align: 'center', title: '责任人', width: '10%'},
                {field: 'id', title: 'PlanItemId', align: 'center', hidden: true},
                {
                    field: 'tasks', align: 'center', title: '工作成果', width: '15%', formatter: function (value, row) {
                    var s = "";
                    if (value) {
                        $.each(value, function (i, j) {
                            s += "<a onclick='showAttachment(" + j.key + ",\"" + j.explain + "\")'  class='fileupload-preview'>" + j.value + "</a>";
                            s += "<a>";
                            s += "<i class='fa fa-download' onclick='downAttachments(" + j.key + ")'style='margin-left: 15px;font-size: 15px;'></i>";
                            s += "<br/>";
                            s += "</a>";
                        })
                    }
                    return s;
                }
                },
                {
                    field: 'processInsId', align: 'center', title: '操作', width: '10%', formatter: function (value, row) {

                    var s = "";
                    if (row.bisLastLayer) {
                        if (row.url) {
                            s += "<a target='_blank' href='" + row.url + "'>提交</a> "
                        } else {
                             s = "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?projectDetailsId=" + row.id + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' ><i class='fa fa-search fa-white'></i></a>";

                        }
                    }
                    return s;
                }
                }
            ]]
        });
    }

    function followProject() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectFollow/followProject",
            data: {
                projectId: "${csrProjectInfo.id}",
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
    }

    function cancelFollowProject() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectFollow/cancelFollowProject",
            data: {
                projectId: "${csrProjectInfo.id}",
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
    }

    function restartProject() {
        Alert("确认重启吗？重启后项目将可以进行上传上应的工作成果!", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/ProjectSuspend/startProject",
                data: {
                    projectId: "${csrProjectInfo.id}",
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
    }
</script>

</html>
