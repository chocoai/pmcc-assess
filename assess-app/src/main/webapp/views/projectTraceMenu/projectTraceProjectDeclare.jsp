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

            <%@include file="./stagePlan.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div id="projectDeclareToolbar" style="display: none">
                        <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" onclick="">创建组</button>
                                    <button type="button" class="btn btn-primary" onclick="">删除组</button>
                                </span>
                            <label class="label label-warning">先创建组，然后再给组分配申报数据</label>
                            <span class="input-group-btn"></span>
                        </div>
                    </div>
                    <table id="tb_project_declare_table" class="table table-bordered">
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

    var declareRecordGroup = {};

    declareRecordGroup.groupTarget = $("#tb_project_declare_table");

    declareRecordGroup.loadTable = function () {
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({
            field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
            }, width: "15%"
        });
        cols.push({field: 'name', title: '名称', width: "6%"});
        cols.push({field: 'remarks', title: '备注', width: "15%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行

            },
            onLoadError: function () {

            }
        };
        var quarm = {
            projectId: '${projectInfo.id}',
            planId: '${projectPlan.id}'
        };
        declareRecordGroup.groupTarget.bootstrapTable('destroy');
        TableInit(declareRecordGroup.groupTarget.attr("id"), "${pageContext.request.contextPath}/declareRecordGroup/getBootstrapTableVo", cols, quarm, method);
        var bootstrapTable = declareRecordGroup.groupTarget.closest(".bootstrap-table");
        if (bootstrapTable.size() != 0) {
            var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
            if (fixedTableToolbar.size() != 0) {
                fixedTableToolbar.append($("#projectDeclareToolbar").html());
            }
        }
    };

    $(document).ready(function () {
        declareRecordGroup.loadTable();
    });


</script>