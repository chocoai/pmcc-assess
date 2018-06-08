<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>


            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>

                </div>
            </div>


            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}工作成果</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control">${projectPlanDetails.actualHours}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${projectPlanDetails.taskRemarks}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <div id="_file_upload_task"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadDataDicList();

        GetFileShows("file_upload_task",
            {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}",
                processInsId: "${processInsId}"
            }, "0");
    })
    function saveform() {
        saveApprovalform("");
    }

    function loadDataDicList() {
        var cols = [];
//        cols.push({field: 'surveySheetNumber', title: '查勘单编号'});
//        cols.push({field: 'surveyPeople', title: '查勘人'});
//        cols.push({field: 'surveyTime', title: '查勘时间'});
//        cols.push({field: 'belongWarrant', title: '所属权证'});
//        cols.push({field: 'ledLuminousPeople', title: '领勘人'});
//        cols.push({field: 'surveyPicture', title: '查勘图片'});
//        cols.push({field: 'surveyImage', title: '查勘图像'});
//        cols.push({field: 'locationPicture', title: '位置图片'});
//        cols.push({field: 'surveyLocaltion', title: '查勘位置'});

        cols.push({field: 'surveyPeople', title: '查勘人'});
        cols.push({
            field: 'surveyTime', title: '查勘时间', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({field: 'ledLuminousPeople', title: '领勘人'});
        cols.push({field: 'houseName', title: '楼盘名称'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:detailsData(' + row.id + ');" >查看详情</i></a>';
                str += '</div>';
                return str;
            }
        });

        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyLocale/list", cols, {
            planDetailsId: ${projectPlanDetails.id}
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    function detailsData(id) {
        window.open("${pageContext.request.contextPath}/surveyLocale/detailsIndex?projectId=${projectId}&id=" + id);
    }

</script>
</body>
</html>

