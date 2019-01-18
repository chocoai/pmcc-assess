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
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${declareRecord.name}-${projectPlanDetails.projectPhaseName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table id="explore_list" class="table table-bordered" style="max-height: auto;"></table>
                    <form id="frm_explore" class="form-horizontal" style="display: none">
                        <fieldset>
                            <legend>同步数据到其它权证</legend>
                            <div class="form-group">
                                <div class="col-sm-10" id="declareCertContent">

                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <input type="hidden" id="jsonContentExplore" value='${surveySceneExplore.jsonContent}'>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        taskExploreIndex.getExploreTaskList();
        taskExploreIndex.loadDeclareCert();
    })
    function saveform() {
        saveApprovalform("");
    }


</script>
<script type="application/javascript">
    var taskExploreIndex = {};

    //加载现场查勘数据
    taskExploreIndex.getExploreTaskList = function () {
        $("#explore_list").treegrid({
                url: '${pageContext.request.contextPath}/surveyCaseStudy/getPlanTaskExamineList?planDetailsId=${projectPlanDetails.id}',
                method: 'get',
                idField: 'id',
                treeField: 'projectPhaseName',
                datatype: 'json',
                lines: true,
                width: 'auto',
                rownumbers: true,
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                },

                columns: [[
                    {field: "projectPhaseName", title: "工作内容", width: "20%", align: "left"},
                    {
                        field: "planStartDate",
                        title: "开始时间",
                        width: "10%",
                        align: "center",
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {
                        field: "planEndDate",
                        title: "结束时间",
                        width: "10%",
                        align: "center",
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {
                        field: "planHours",
                        title: "计划工时",
                        width: "5%",
                        align: "center"
                    },
                    {
                        field: "executeUserName",
                        title: "责任人",
                        width: "10%",
                        align: "center"
                    },
                    {
                        field: "executeDepartmentName",
                        title: "责任部门",
                        width: "10%",
                        align: "center"
                    },
                    {
                        field: "proportion",
                        title: "权重占比",
                        width: "5%",
                        align: "center"
                    },

                    {
                        field: 'workStages', title: '操作', width: '10%', formatter: function (value, row) {
                        if (row.bisEnable) {
                            var s = "";
                            if (row.pid == '${projectPlanDetails.id}'&&row.displayUrl) {
                                s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                            }
                            return s;
                        }
                    }
                    }
                ]]
            }
        );
    }

    //加载申报权证
    taskExploreIndex.loadDeclareCert = function () {
        var jsonContent = $("#jsonContentExplore").val();
        if (jsonContent) {
            jsonContent = JSON.parse(jsonContent);
            var html = '';
            $.each(jsonContent, function (i, item) {
                html += '<span class="checkbox-inline">';
                html += '<input type="checkbox" id="declareCert' + item.key + '" disabled="disabled"';
                if (item.isChecked) {
                    html += ' checked="checked" ';
                }
                html += 'name="declareCert" value="' + item.value + '" class="form-inline">';
                html += '<label for="declareCert' + item.key + '">' + item.value + '</label></span>';
            })
            $('#declareCertContent').append(html);
        }
    }
</script>
</body>
</html>
