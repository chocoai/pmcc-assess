<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">审批日志</div>
            </div>
        </div>
        <div class="card-body">
            <ul class="nav nav-pills nav-primary" id="pills-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab"
                       aria-controls="pills-home" aria-selected="true">流程日志</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab"
                       aria-controls="pills-profile" aria-selected="false">项目日志</a>
                </li>
            </ul>
            <div class="tab-content mt-2 mb-3" id="pills-tabContent">
                <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                     aria-labelledby="pills-home-tab">
                    <table id="tb_log" class="table table-striped jambo_table bulk_action table-bordered"></table>
                </div>
                <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                    <table id="tb_log_project"
                           class="table table-striped jambo_table bulk_action table-bordered"></table>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="application/javascript">
    var processLogsObj = {
        projectId: "${projectId}",
        processInsId: "${processInsId}"
    };

    $(function () {

        //数据检查
        if (!processLogsObj.projectId) {
            processLogsObj.projectId = "${projectInfo.id}";
        }
        if (!processLogsObj.processInsId) {
            processLogsObj.processInsId = "${projectPlanDetails.processInsId}";
        }
        //---end

        processLogsObj.loadLogProjectTable($("#tb_log_project"),processLogsObj.projectId);
        processLogsObj.loadLogProcessTable($("#tb_log"),processLogsObj.processInsId);
    });

    //加载项目日志
    processLogsObj.loadLogProjectTable=function ($list,projectId) {
        var cols = [];
        cols.push({field: 'workStage', title: '项目阶段', width: '15%'});
        cols.push({field: 'workPhase', title: '工作事项'});
        cols.push({field: 'activityName', title: '审批节点'});
        cols.push({field: 'createrName', title: '审批人'});
        cols.push({
            field: 'created', title: '审批时间', formatter: function (value, row, index) {
                return formatDate(value, true);
            }
        });
        cols.push({field: 'conclusion', title: '结论'});
        cols.push({field: 'opinions', title: '审批意见'});
        cols.push({
            field: 'attachmentVos', title: '审批附件', formatter: function (value, row, index) {
                return "<div id='_uploadFile_project_log_list" + row.id + "'></div>";
            }
        });
        var paramData = {
            onLoadSuccess: function (data) {
                $(".tooltips").tooltip();
                if (data["rows"]) {
                    $.each(data["rows"], function (i, j) {
                        FileUtils.showFileList({
                            target: "uploadFile_project_log_list" + j.id,
                            editFlag: false,
                            deleteFlag: false,
                            showMode: "table",
                            data: j.attachmentVos
                        });
                    })
                }
                var demoWidth = $(window).width();
                if (demoWidth <= 720) {
                    $list.bootstrapTable('hideColumn', 'Number');
                    $list.bootstrapTable('hideColumn', 'workStage');
                    $list.bootstrapTable('hideColumn', 'workPhase');
                    $list.bootstrapTable('hideColumn', 'activityName');
                    $list.bootstrapTable('hideColumn', 'conclusion');
                    $list.bootstrapTable('hideColumn', 'attachmentVos');
                }
            }
        };
        TableInit($list, "${pageContext.request.contextPath}/public/getApprovalLogByProject", cols, {projectId: projectId}, paramData);
    }

    //加载流程日志
    processLogsObj.loadLogProcessTable=function ($list,processInsId) {
        var cols = [];
        cols.push({field: 'activityName', title: '审批节点'});
        cols.push({field: 'createrName', title: '审批人'});
        cols.push({
            field: 'created', title: '审批时间', formatter: function (value, row, index) {
                return formatDate(value, true);
            }
        });
        cols.push({field: 'conclusion', title: '结论'});
        cols.push({field: 'opinions', title: '审批意见'});
        cols.push({
            field: 'attachmentVos', title: '审批附件', formatter: function (value, row, index) {
                return "<div id='_uploadFile_log_list" + row.id + "'></div>";
            }
        });
        var paramData = {
            showRefresh: false,                  //是否显示刷新按钮
            search: false,
            onLoadSuccess: function (data) {
                $(".tooltips").tooltip();
                if (data["rows"]) {
                    $.each(data["rows"], function (i, j) {
                        FileUtils.showFileList({
                            target: "uploadFile_log_list" + j.id,
                            editFlag: false,
                            deleteFlag: false,
                            showMode: "table",
                            data: j.attachmentVos
                        });
                    })
                }
                var demoWidth = $(window).width();
                if (demoWidth <= 720) {
                    $list.bootstrapTable('hideColumn', 'Number');
                    $list.bootstrapTable('hideColumn', 'activityName');
                    $list.bootstrapTable('hideColumn', 'conclusion');
                    $list.bootstrapTable('hideColumn', 'attachmentVos');
                }
            }
        };
        TableInit($list, "${pageContext.request.contextPath}/public/getApprovalLog", cols, {processInsId: processInsId}, paramData);
    }

</script>