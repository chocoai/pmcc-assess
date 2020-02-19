<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3> 日志记录

            <small class="radio-inline">
                <input type="radio" name="formLogType" id="formLogProcess" onclick="formLog.loadLogList(false);">
                <label for="formLogProcess">流程</label>
            </small>
            <c:if test="${not empty projectId}">
                <small class="radio-inline">
                    <input type="radio" name="formLogType" id="formLogProject" onclick="formLog.loadLogList(true);">
                    <label for="formLogProject">项目</label>
                </small>
            </c:if>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <table id="tb_log" class="table table-bordered"></table>
    </div>
</div>


<script type="application/javascript">
    $(function () {
        formLog.initLoadData();
    })

    var formLog = {
        //配置信息
        config: {
            projectId: "${empty projectId||projectId==0?-1:projectId}",
            processInsId: "${empty processInsId?-1:processInsId}",
            processLogUrl: "${pageContext.request.contextPath}/public/getApprovalLog",
            projectLogUrl: "${pageContext.request.contextPath}/public/getApprovalLogByProject"
        },

        //初始化加载数据
        initLoadData: function () {
            //1.如果项目id为空 则直接加载流程日志
            //2.如果项目id不为空 流程id为空 则直接加载项目日志
            //3.项目id与流程id都不为空 则优先加载流程日志
            if (formLog.config.projectId != "0" && (formLog.config.processInsId == "0" || formLog.config.processInsId == "-1")) {
                $("#formLogProject").trigger('click');
            } else {
                $("#formLogProcess").trigger('click');
            }
        },

        //加载日志
        loadLogList: function (isProject) {
            var cols = [];
            if (isProject) {
                cols.push({field: 'workStage', title: '项目阶段', width: '15%'});
            }
            cols.push({field: 'activityName', title: '审批节点', width: '10%'});
            cols.push({field: 'createrName', title: '审批人', width: '5%'});
            cols.push({
                field: 'created', title: '审批时间', width: '14%', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }
            });
            cols.push({field: 'conclusion', title: '结论', width: '5%'});
            cols.push({field: 'opinions', title: '审批意见', width: '35%'});
            cols.push({
                field: 'attachmentVos', title: '审批附件', width: '10%', formatter: function (value, row, index) {
                    if (value) {
                        var str = "";
                        $.each(value, function (i, j) {
                            str += "<a class='fileupload-preview'>" + j.fileName + "</a>";
                            str += "<a><i class='fa fa-download' onclick='FileUtils.downAttachments(" + j.id + ")' style='margin-left: 15px;font-size: 15px;'></i><br/></a>";
                        });
                        return str;
                    }
                }
            });
            var paramData = {};
            paramData["showColumns"] = false; //不显示选择显示列
            paramData["showRefresh"] = isProject;//不显示刷新按钮
            paramData["search"] = isProject;//不显示查询按钮
            $("#tb_log").bootstrapTable('destroy');
            TableInit("tb_log", isProject ? formLog.config.projectLogUrl : formLog.config.processLogUrl, cols,
                {
                    processInsId: formLog.config.processInsId,
                    projectId: formLog.config.projectId
                }, paramData);
        }
    };

</script>--%>

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
    var projectLogsObj = {
        projectId: "${projectId}",
        processInsId: "${processInsId}"
    };

    $(function () {

        //数据检查
        if (!projectLogsObj.projectId) {
            projectLogsObj.projectId = "${projectInfo.id}";
        }

        if (!projectLogsObj.processInsId) {
            projectLogsObj.processInsId = "${projectPlanDetails.processInsId}";
        }
        //---end

        loadLogProjectTable();
        loadLogTable();
    });


    function loadLogProjectTable() {

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
                    $("#tb_log_project").bootstrapTable('hideColumn', 'Number');
                    $("#tb_log_project").bootstrapTable('hideColumn', 'workStage');
                    $("#tb_log_project").bootstrapTable('hideColumn', 'workPhase');
                    $("#tb_log_project").bootstrapTable('hideColumn', 'activityName');
                    $("#tb_log_project").bootstrapTable('hideColumn', 'conclusion');
                    $("#tb_log_project").bootstrapTable('hideColumn', 'attachmentVos');
                }
            }
        };
        TableInit("tb_log_project", "${pageContext.request.contextPath}/public/getApprovalLogByProject", cols, {projectId: projectLogsObj.projectId}, paramData);

    }

    function loadLogTable() {
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
                    $("#tb_log").bootstrapTable('hideColumn', 'Number');
                    $("#tb_log").bootstrapTable('hideColumn', 'activityName');
                    $("#tb_log").bootstrapTable('hideColumn', 'conclusion');
                    $("#tb_log").bootstrapTable('hideColumn', 'attachmentVos');
                }
            }
        };
        TableInit("tb_log", "${pageContext.request.contextPath}/public/getApprovalLog", cols, {processInsId: projectLogsObj.processInsId}, paramData);

    }
</script>