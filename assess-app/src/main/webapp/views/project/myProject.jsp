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
                            <h2>
                                我的立项
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form id="frmQuery" class="form-horizontal">
                                <div class="form-group ">
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            项目名称
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="项目名称" id="queryName" name="queryName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            状态
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select id="status" class="form-control">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${statusEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <button type="button" class="btn btn-primary"
                                                onclick="loadProjectList()">
                                            查询
                                        </button>
                                    </div>
                                </div>

                            </form>
                            <table id="tb_myProject" class="table table-bordered">
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
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称',width:'30%'});
        cols.push({field: 'useUnitName', title: '使用报告单位',width:'10%'});
        cols.push({field: 'departmentName', title: '评估部门',width:'5%'});
        cols.push({
            field: 'serviceEnd', title: '项目成员', width: '10%', formatter: function (value, row, index) {
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
            field: 'projectClassName', title: '项目类型',width:'5%', formatter: function (value, row, index) {
                return row.projectCategoryName;
            }
        });
        cols.push({field: 'projectStatus', title: '项目状态',width:'5%'});
        cols.push({field: 'entrustPurposeName', title: '委托目的',width:'5%'});
        cols.push({field: 'loanTypeName', title: '贷款类型',width:'5%'});
        cols.push({
            field: 'finishPre', title: '项目进度',width:'10%', formatter: function (value, row, index) {
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
            field: 'gmtCreated', title: '立项时间',width:'10%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = "";
                if (row.projectStatus) {
                    if (row.projectStatus == '草稿') {
                        str = "<a target='_blank' href='${pageContext.request.contextPath}/projectInfo/projectInfoEdit?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='重新申请' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-pencil-square-o'></i></a>";
                    } else {
                        str += "<a target='_blank' href='${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                    }
                }
                return str;
            }
        });
        $("#tb_myProject").bootstrapTable('destroy');
        TableInit("tb_myProject", "${pageContext.request.contextPath}/projectCenter/getMyProjectList", cols, {
            queryName: $("#queryName").val(),
            projectStatus:$("#status").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

</script>
</body>
</html>
