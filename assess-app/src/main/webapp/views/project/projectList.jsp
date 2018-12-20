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
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <h2>
                                项目列表
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <table id="tb_projectList" class="table table-bordered">
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
<script type="application/javascript">
    $(function () {
        loadProjectList();

    })
    function loadProjectList() {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称'});
        cols.push({
            field: 'serviceEnd', width: '20%', title: '项目成员', formatter: function (value, row, index) {
                var s = "";
                if (row.userAccountManagerName) {
                    s += "<label style='padding: 5px;' class='label label-info'>" + row.userAccountManagerName.split("_")[0] + "</label>"
                }
                if (row.userAccountMemberName) {
                    s += " " + row.userAccountMemberName.split("_")[0];
                }
                return s;
            }
        });
        cols.push({
            field: 'projectClassName', title: '项目类型', formatter: function (value, row, index) {
                var s = "";
                if (row.projectClassName) {
                    s += row.projectClassName;
                }
                if (row.projectClassName) {
                    s += "/" + row.projectTypeName;
                }
                if (row.projectClassName) {
                    s += "/" + row.projectCategoryName;
                }
                return s;
            }
        });
        cols.push({field: 'projectStatus', title: '项目状态'});
        cols.push({
            field: 'finishPre', width: '20%', title: '项目进度', formatter: function (value, row, index) {
                var s = "<div class='progress progress_sm' style='margin-bottom: 0px;'>";
                if (value == "100") {
                    s += "<div class='progress-bar progress-bar-success' role='progressbar'  style='width: " + value + "%;'></div>";
                }
                else {
                    s += "<div class='progress-bar progress-bar-warning' role='progressbar'  style='width: " + value + "%;'></div>";
                }
                s += "</div>";
                s += "<small>完成" + value + "%</small>";
                return s;
            }
        });
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var str = "<a target='_blank' href='${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                return str;
            }
        });
        $("#tb_projectList").bootstrapTable('destroy');
        TableInit("tb_projectList", "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, {}, {
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

</script>
</body>
</html>
