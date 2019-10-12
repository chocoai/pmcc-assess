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
            <div class="x_panel">
                <div class="x_content">

                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <div class="row tile_count">
                            <span class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                                <span class="count_top"><i class="fa fa-bell"></i> 报名日期</span>
                                <div class="count"></div>
                                <span class="count_bottom">


                                </span>
                            </span>
                            <span class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                                <span class="count_top"><i class="fa fa-bullseye"></i> 保证金缴纳日期</span>
                                <div class="count"></div>
                                <span class="count_bottom">


                                </span>

                            </span>
                            <span class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                                <span class="count_top"><i class="fa fa-leaf"></i> 投标日期</span>
                                <div class="count"></div>
                                <span class="count_bottom">


                                </span>
                            </span>
                            <span class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                                <span class="count_top"><i class="fa fa-ticket"></i> 中标通知书</span>
                                <div class="count"></div>
                                <span class="count_bottom">

                                </span>
                            </span>
                            <span class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                                <span class="count_top"><i class="fa fa-umbrella"></i> 保证金退还</span>
                                <div class="count"></div>
                                <span class="count_bottom">


                                </span>
                            </span>
                            <span class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                                <span class="count_top"><i class="fa fa-paper-plane"></i> 履约保证金</span>
                                <div class="count"></div>
                                <span class="count_bottom">

                                </span>
                            </span>
                        </div>
                        <table id="tb_project_task_list" class="table table-bordered">
                        </table>
                    </div>

                    <!-- start project-detail sidebar -->
                    <div class="col-md-3 col-sm-3 col-xs-12">
                        <section class="panel">
                            <div class="panel-body">
                                <h3 class="green">${projectInfo.projectTypeName}[${projectInfo.projectCategoryName}]</h3>
                                <p>${projectInfo.projectSynopsis}</p>
                                <div class="project_detail">
                                    <p class="title">项目经理</p>
                                    <p>${projectInfo.projectManageName}</p>

                                </div>

                                <br>
                                <ul class="list-unstyled project_files">
                                    <li>
                                        <c:if test="${projectInfoStep.obtain}">
                                            <i style="color: green" class="fa fa-check-square"></i>
                                        </c:if>
                                        标书取得
                                    </li>

                                    <li><c:if test="${projectInfoStep.analysis}">
                                        <i style="color: green" class="fa fa-check-square"></i>
                                    </c:if>
                                        投标分析
                                    </li>
                                    <li><c:if test="${projectInfoStep.make}">
                                        <i style="color: green" class="fa fa-check-square"></i>
                                    </c:if>
                                        标书制作
                                    </li>
                                    <li><c:if test="${projectInfoStep.bid}">
                                        <i style="color: green" class="fa fa-check-square"></i>
                                    </c:if>
                                        投标
                                    </li>
                                    <li><c:if test="${projectInfoStep.result}">
                                        <i style="color: green" class="fa fa-check-square"></i>
                                    </c:if>
                                        结束跟踪
                                    </li>
                                </ul>
                                <h3 class="green">项目日志</h3>
                                <ul class="to_do">
                                    <c:forEach items="${projectLogRecord}" var="item">
                                        <li>
                                            <label class="label label-warning"><fmt:formatDate value="${item.created}" pattern="yyyy-MM-dd"/></label>
                                            <p>[<strong>${item.creator}</strong>]${item.changeContent} </p>
                                        </li>
                                    </c:forEach>
                                </ul>

                                <br>
                            </div>

                        </section>

                    </div>
                    <!-- end project-detail sidebar -->

                </div>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>

</html>
<script type="text/javascript">
    var projectDetailsObj = {
        initStageTable: function () {
            var cols = [];
            cols.push({field: 'id', title: 'id', visible: false});
            cols.push({
                field: 'taskStatus', title: '状态', formatter: function (value, row, index) {
                    var str = "";
                    switch (row.taskStatus) {
                        case "未开始": {
                            str = "<label class='label label-danger'>" + value + "</label>";
                            break;
                        }
                        case "待提交":
                        case "审批中": {
                            str = "<label class='label label-info'>" + value + "</label>";
                            break;
                        }

                        case "已完成": {
                            str = "<label class='label label-success'>" + value + "</label>";
                            break;
                        }
                    }
                    return str;
                }
            });
            cols.push({field: 'stageName', title: '阶段'});
            cols.push({
                field: 'projectPhaseName', title: '工作任务', formatter: function (value, row, index) {
                    var str = "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?projectDetailsId=" + row.id + "'  >" + value + "</a>";
                    return str;
                }
            });
            cols.push({
                field: 'planStartDate', title: '开始日期', formatter: function (value, row, index) {
                    return formatDate(value, false)
                }
            });
            cols.push({
                field: 'planEndDate', title: '结束日期', formatter: function (value, row, index) {
                    return formatDate(value, false)
                }
            });

            cols.push({field: 'executeUserName', title: '执行人'});
            cols.push({field: 'planRemarks', title: '说明'});
            cols.push({
                    title: '操作',
                    formatter: function (value, row, index) {
                        var str = "";
                        str += "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?projectDetailsId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看' class='btn btn-xs btn-info tooltips'  ><i class='fa fa-search fa-white'></i></a>";
                        if ("${projectInfo.projectStatus}" == "进行中" && row.taskStatus == "待提交" && row.executeUserAccount == "${currUserAccount}") {
                            str += "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskIndex?responsibilityId=" + row.returnDetailsId + "' style='margin-left: 5px;' data-placement='top' data-original-title='提交' class='btn btn-xs btn-success tooltips'  ><i class='fa fa-external-link fa-white'></i></a>";
                        }
                        return str;
                    }

                }
            );
            TableInit($("#tb_project_task_list"), "${pageContext.request.contextPath}/projectStagePlan/getProjectPlan", cols, {
                projectId: "${projectInfo.id}"
            }, {
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };
    $(function () {
        projectDetailsObj.initStageTable();
    })
</script>