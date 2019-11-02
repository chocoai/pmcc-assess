<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>${thisMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <%@include file="/views/project/projectCenterHeader.jsp" %>
                            <table id="tb_projectProgress" class="table table-bordered">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>


<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bid/projectProgress.js?v=${assessVersion}"></script>
<script type="application/javascript">
    $(function () {
        loadProjectProgress();
        InitSuspendList();

    })

    function loadProjectProgress() {
        var cols = [];
//        cols.push({field: 'projectId', title: '项目编号'});
        cols.push({
            field: 'projectName', width: '20%', title: '项目名称', formatter: function (value, row, index) {
                var retHtml = "";
                if (row.projectStatusFlog == 1) {
                    retHtml = "<label class='label label-success' onclick='showSuspendList(" + row.projectId + ")'>" + row.projectStatus + "</label>";
                }
                else {
                    retHtml = "<label class='label label-danger' onclick='showSuspendList(" + row.projectId + ")'>" + row.projectStatus + "</label>";
                }
                retHtml += "<a target='_blank' href='" + getContextPath() + "/projectInfo/projectDetails?projectId=" + row.projectId + "' title='查看详情'  >" + value + "</a>"
                return retHtml;
            }
        });
        cols.push({
            field: 'projectProgressWorkStageVos', width: '80%', title: '项目进度', formatter: function (value, row, index) {
                var retHtml = "<div class='form_wizard wizard_horizontal'>";
                retHtml += "<ul class='wizard_steps anchor'>";
                $.each(value, function (p, q) {
                    switch (q.workStageStatus) {
                        case 0: {
                            retHtml += "<li>";
                            retHtml += "<a class='disabled'>";
                            retHtml += "<div class='step_no'></div>";
                            retHtml += "<span class='step_descr'></span>";
                            retHtml += q.stagePlanEndDate + q.workStageName;
                            retHtml += "</a>";
                            retHtml += "</li>  ";
                            break;
                        }
                        case 1: {
                            retHtml += "<li>";
                            retHtml += "<a class='done tooltips' data-placement='top'";
                            retHtml += "data-original-title='" + q.stageUserName + " " + q.stagePlanEndDate + "'>";
                            retHtml += "<div class='step_no'></div>";
                            retHtml += "<span class='step_descr'></span>";
                            retHtml += q.stagePlanEndDate + q.workStageName;
                            retHtml += "</a>";
                            retHtml += "</li>";
                            break;
                        }
                        case 2: {
                            retHtml += "<li>";
                            retHtml += "<a class='selected tooltips' data-placement='top'";
                            retHtml += "data-original-title='" + q.stageUserName + " " + q.stagePlanEndDate + "'>";
                            retHtml += "<div class='step_no'></div>";
                            retHtml += "<span class='step_descr'></span>";
                            retHtml += q.stagePlanEndDate + q.workStageName;
                            retHtml += "</a>";
                            retHtml += "</li>";
                            break;
                        }
                    }
                })
                retHtml += "</ul>";
                retHtml += "</div>";
                return retHtml;
            }
        });
        TableInit("tb_projectProgress", "${pageContext.request.contextPath}/projectCenter/getProjectProgress", cols, {}, {
            pageSize: 5,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

</script>

</html>
