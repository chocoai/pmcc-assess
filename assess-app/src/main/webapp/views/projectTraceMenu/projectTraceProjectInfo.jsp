<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="./trace_project_menu.jsp" %>
        <div class="right_col" role="main">


            <%@include file="/views/share/project/projectInfoSimple.jsp" %>


            <div class="x_panel">
                <div class="x_content">
                    <div class="page-title" style="margin: 0px">
                        <div class="title_right">
                            <div class="col-md-12 col-sm-12 col-xs-12 pull-right" style="margin: 0px">
                                <div class="btn-group">
                                    <a class="btn btn-danger" href="javascript://"
                                       onclick="projectDetails.finishProject()"><i class="fa fa-check">&nbsp;</i>完成</a>
                                    <c:if test="${projectStatusEnum ne 'close' and projectStatusEnum ne 'finish'}">
                                        <a class="btn btn-primary" href="javascript://"
                                           onclick="projectDetails.stopProject()"><i class="fa fa-stop">&nbsp;</i>终止</a>
                                    </c:if>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary">
                                            项目变更
                                        </button>
                                        <button type="button" class="btn btn-primary dropdown-toggle"
                                                data-toggle="dropdown">
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
                                        <button type="button" class="btn btn-primary dropdown-toggle"
                                                data-toggle="dropdown">
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
                                       onclick="projectDetails.projectSubsequent()">后续事项</a>
                                    <a class="btn btn-primary" href="javascript://"
                                       onclick="projectDetails.projectTakeNumber()">项目拿号</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="list-group">
                            <a onclick="projectDetails.loadDocumentSend('tb_documentSendList')" class="list-group-item">项目发文
                                <i class="fa fa-bookmark"></i>
                            </a>
                            <a onclick="projectDetails.loadDocumentOpinion('tb_documentOpinionList')"
                               class="list-group-item">项目意见稿
                                <i class="fa fa-cloud-download"></i>
                            </a>
                            <a onclick="projectDetails.loadSubsequent('tb_subsequentList')" class="list-group-item">后续事项
                                <i class="fa fa-spinner"></i>
                            </a>
                            <a onclick="projectDetails.loadTakeNumber('tb_takeNumber')" class="list-group-item">项目拿号
                                <i class="fa fa-magic"></i>
                            </a>
                            <a onclick="projectDetails.loadProjectLog('tb_projectLogList')" class="list-group-item">项目日志
                                <i class="fa fa-flag-checkered"></i>
                            </a>
                            <a onclick="projectDetails.loadProjectLegwork('tb_projectLegWorkList')" class="list-group-item">外勤信息
                                <i class="fa fa-coffee"></i>
                            </a>
                            <a onclick="projectDetails.loadProjectBill('tb_projectBillList')" class="list-group-item">开票信息
                                <i class="fa fa-barcode"></i>
                            </a>
                        </div>

                    </div>
                </div>
            </div>

            <div class="x_panel" style="display: none;">
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

            <div class="x_panel" style="display: none;">
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

            <div class="x_panel" style="display: none;">
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

            <div class="x_panel" style="display: none;">
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

            <div class="x_panel" style="display: none;">
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

            <div class="x_panel" style="display: none;">
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

            <div class="x_panel" style="display: none;">
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
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">
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

        //项目发文
        loadDocumentSend: function (key) {
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
            if (key != 'tb_documentSendList') {
                return false;
            }
            $("#" + key).parent().parent().show();
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
        loadDocumentOpinion: function (key) {
            var cols = [];
            cols.push({field: 'areaGroupName', title: '区域名称'});
            cols.push({field: 'reportTypeName', title: '报告类型'});
            cols.push({field: 'fileViewName', title: '意见稿'});
            if (key != 'tb_documentOpinionList') {
                return false;
            }
            $("#" + key).parent().parent().show();
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
        loadSubsequent: function (key) {
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'suggestion', title: '处理意见'});
            cols.push({field: 'fileViewName', title: '附件'});
            if (key != 'tb_subsequentList') {
                return false;
            }
            $("#" + key).parent().parent().show();
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
        loadTakeNumber: function (key) {
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
            if (key != 'tb_takeNumber') {
                return false;
            }
            $("#" + key).parent().parent().show();
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
        loadProjectLog: function (key) {
            var cols = [];
            cols.push({field: 'title', title: '标题'});
            cols.push({field: 'content', title: '内容'});
            cols.push({field: 'creator', title: '创建人'});
            cols.push({
                field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            if (key != 'tb_projectLogList') {
                return false;
            }
            $("#" + key).parent().parent().show();
            $("#tb_projectLogList").bootstrapTable('destroy');
            TableInit("tb_projectLogList", "${pageContext.request.contextPath}/home/getWorkLogByProjectId", cols, {
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
        loadProjectLegwork: function (key) {
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
            if (key != 'tb_projectLegWorkList') {
                return false;
            }
            $("#" + key).parent().parent().show();
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


        //项目开票
        loadProjectBill: function (key) {
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
            if (key != 'tb_projectBillList') {
                return false;
            }
            $("#" + key).parent().parent().show();
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
        }
    };


</script>