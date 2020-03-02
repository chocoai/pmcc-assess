<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@include file="projectNavigation.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="page-inner">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        项目管理
                                        <small>
                                            <div class="btn-group">
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm" type="button"
                                                            onclick="projectDetails.finishProject()"><span
                                                            class="btn-label"><i class="fa fa-check"></i></span>完成
                                                    </button>
                                                </div>
                                                <c:if test="${projectStatusEnum ne 'close' and projectStatusEnum ne 'finish'}">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-info btn-sm" type="button"
                                                                onclick="projectDetails.stopProject()"><span
                                                                class="btn-label"><i class="fa fa-stop"></i></span>终止
                                                        </button>
                                                    </div>
                                                </c:if>
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm dropdown-toggle" type="button"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">
                                                        项目变更
                                                    </button>
                                                    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(410px, 43px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/member.change/applyView?projectId=${projectInfo.id}"
                                                           target="_blank">成员变更</a>
                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/project.information.change/applyView?projectId=${projectInfo.id}"
                                                           target="_blank">信息变更</a>
                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/project.scheme.change/applyView?projectId=${projectInfo.id}"
                                                           target="_blank">方案变更</a>
                                                    </div>
                                                </div>
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm dropdown-toggle" type="button"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">
                                                    <span class="btn-label">
												        <i class="fa fa-file-invoice"></i>
											        </span>
                                                        公司盖章发文
                                                    </button>
                                                    <div class="dropdown-menu" x-placement="bottom-start"
                                                         style="position: absolute; transform: translate3d(410px, 43px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                        <c:forEach var="item" items="${documentTemplateList}">
                                                            <a class="dropdown-item"
                                                               href="${pageContext.request.contextPath}/documentSend/applyIndex/${item.id}&${projectInfo.id}"
                                                               target="_blank">${item.templateName}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm dropdown-toggle" type="button"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">
                                                    <span class="btn-label">
												        <i class="fa fa-file-invoice"></i>
											        </span>
                                                        委托方盖章模板
                                                    </button>
                                                    <div class="dropdown-menu" x-placement="bottom-start"
                                                         style="position: absolute; transform: translate3d(410px, 43px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                        <c:forEach var="item" items="${documentClientTemplateList}">
                                                            <a class="dropdown-item"
                                                               href="${pageContext.request.contextPath}/documentSend/applyClientIndex/${item.id}&${projectInfo.id}"
                                                               target="_blank">${item.templateName}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm dropdown-toggle" type="button"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">
                                                    <span class="btn-label">
												        <i class="fa fa-file-invoice"></i>
											        </span>
                                                        报告签收单
                                                    </button>
                                                    <div class="dropdown-menu" x-placement="bottom-start"
                                                         style="position: absolute; transform: translate3d(410px, 43px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                        <c:forEach var="item" items="${signBill}">
                                                            <a class="dropdown-item"
                                                               href="${pageContext.request.contextPath}/documentSend/applySignBillIndex/${item.id}&${projectInfo.id}"
                                                               target="_blank">${item.templateName}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm" type="button"
                                                            onclick="projectDetails.projectSubsequent()">
                                                        后续事项
                                                    </button>
                                                </div>
                                                <div class="input-group-append">
                                                    <button class="btn btn-info btn-sm" type="button"
                                                            onclick="projectDetails.projectTakeNumber()">
                                                        项目拿号
                                                    </button>
                                                </div>
                                            </div>
                                        </small>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="nav flex-column nav-pills nav-secondary" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                                            <a class="nav-link active" onclick="projectDetails.loadDocumentSend(this);" data-table-id="tb_documentSendList" data-toggle="pill" href="#div_documentSendList" role="tab" aria-controls="v-pills-home" aria-selected="true">发文信息</a>
                                            <a class="nav-link" onclick="projectDetails.loadDocumentOpinion(this)" data-table-id="tb_documentOpinionList" data-toggle="pill" href="#div_documentOpinionList" role="tab" aria-controls="v-pills-profile" aria-selected="false">意见稿信息</a>
                                            <a class="nav-link" onclick="projectDetails.loadSubsequent(this)" data-table-id="tb_subsequentList" data-toggle="pill" href="#div_subsequentList" role="tab" aria-controls="v-pills-messages" aria-selected="false">后续事项信息</a>
                                            <a class="nav-link" onclick="projectDetails.loadTakeNumber(this)" data-table-id="tb_takeNumber" data-toggle="pill" href="#div_takeNumber" role="tab" aria-controls="v-pills-home" aria-selected="true">拿号信息</a>
                                            <a class="nav-link" onclick="projectDetails.loadProjectLog(this)" data-table-id="tb_projectLogList" data-toggle="pill" href="#div_projectLogList" role="tab" aria-controls="v-pills-profile" aria-selected="false">日志信息</a>
                                            <a class="nav-link" onclick="projectDetails.loadProjectLegwork(this)" data-table-id="tb_projectLegWorkList" data-toggle="pill" href="#div_projectLegWorkList" role="tab" aria-controls="v-pills-messages" aria-selected="false">外勤信息</a>
                                            <a class="nav-link" onclick="projectDetails.loadProjectBill(this)" data-table-id="tb_projectBillList" data-toggle="pill" href="#div_projectBillList" role="tab" aria-controls="v-pills-messages" aria-selected="false">开票信息</a>
                                        </div>
                                    </div>
                                    <div class="col-md-10">
                                        <div class="bootstrap-table-list" id="div_documentSendList">
                                            <table title="发文信息" class="table table-bordered" id="tb_documentSendList">
                                            </table>
                                        </div>
                                        <div class="bootstrap-table-list" id="div_documentOpinionList">
                                            <table title="意见稿信息" class="table table-bordered"
                                                   id="tb_documentOpinionList">
                                            </table>
                                        </div>
                                        <div class="bootstrap-table-list" id="div_subsequentList">
                                            <table title="后续事项信息" class="table table-bordered" id="tb_subsequentList">
                                            </table>
                                        </div>
                                        <div class="bootstrap-table-list" id="div_takeNumber">
                                            <table title="拿号信息" class="table table-bordered" id="tb_takeNumber">
                                            </table>
                                        </div>
                                        <div class="bootstrap-table-list" id="div_projectLogList">
                                            <table title="日志信息" class="table table-bordered" id="tb_projectLogList">
                                            </table>
                                        </div>
                                        <div class="bootstrap-table-list" id="div_projectLegWorkList">
                                            <table title="外勤信息" class="table table-bordered" id="tb_projectLegWorkList">
                                            </table>
                                        </div>
                                        <div class="bootstrap-table-list" id="div_projectBillList">
                                            <table title="开票信息" class="table table-bordered" id="tb_projectBillList">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_details.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
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
        $("#v-pills-tab a:first").trigger('click');
    })
    var projectDetails = {
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
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                        AlertSuccess("操作成功", "项目正常完成");
                    }
                    else {
                        AlertError("操作失败,失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        },
        //加载列表前执行的方法
        preLoadListFn: function (_this) {
            //$(_this).css('background-color', '#6861ce').closest('div').find('a').not($(_this)).removeClass('background-color');
            var target = $("#" + $(_this).attr('data-table-id'));
            var targetDiv = target.closest('.bootstrap-table-list');
            targetDiv.show().siblings().hide();
            return target;
        },
        //发文信息
        loadDocumentSend: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
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

            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/documentSend/getDocumentSendVoList", cols, {
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

        //意见稿信息
        loadDocumentOpinion: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
            var cols = [];
            cols.push({field: 'areaGroupName', title: '区域名称'});
            cols.push({field: 'reportTypeName', title: '报告类型'});
            cols.push({field: 'fileViewName', title: '意见稿'});
            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/documentOpinion/getDocumentOpinionVoList", cols, {
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
        loadSubsequent: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'suggestion', title: '处理意见'});
            cols.push({field: 'fileViewName', title: '附件'});
            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/projectSubsequent/getSubsequentList", cols, {
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
        loadTakeNumber: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
            var cols = [];
            cols.push({field: 'reportTypeName', title: '报告类型'});
            cols.push({field: 'numberValue', title: '文号'});
            cols.push({field: 'remark', title: '说明'});
            cols.push({field: 'creatorName', title: '拿号人'});
            cols.push({
                field: 'gmtCreated', title: '拿号时间', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/projectTakeNumber/getTakeNumberList", cols, {
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
        loadProjectLog: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'creator', title: '创建人'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/home/getWorkLogByProjectId", cols, {
                publicProjectId: '${projectInfo.publicProjectId}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function (key) {
                    $('.tooltips').tooltip();
                }
            });
        },

        //项目外勤
        loadProjectLegwork: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
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
            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/rpcHrService/getHrLegworkList", cols, {
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


        //项目开票
        loadProjectBill: function (_this) {
            var target = projectDetails.preLoadListFn(_this);
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
                field: 'thisBalance', title: '收款金额', formatter: function (value, row, index) {
                    if (value) {
                        return (Number(value) / 100).toFixed(2);
                    }
                }
            });
            cols.push({field: 'company', title: '公司'});
            cols.push({field: 'billExplain', title: '说明'});
            cols.push({field: 'applyUserName', title: '申请人'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    return "<a target='_blank' href='/pmcc-finance/FinancialBase/DetailsIndex?processInsId=" + row.processInsId + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                }
            });
            target.bootstrapTable('destroy');
            TableInit(target, "${pageContext.request.contextPath}/rpcFinanceService/getFinancialBillMakeOutList", cols, {
                publicProjectId: '${projectInfo.publicProjectId}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };
</script>