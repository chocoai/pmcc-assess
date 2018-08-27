<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2> 日志记录
            <c:if test="${not empty projectId}">
                <small class="radio-inline">
                    <input type="radio" name="formLogType" id="formLogProcess" onclick="formLog.loadLogList(false);">
                    <label for="formLogProcess">流程</label>
                </small>
                <small class="radio-inline">
                    <input type="radio" name="formLogType" id="formLogProject" onclick="formLog.loadLogList(true);">
                    <label for="formLogProject">项目</label>
                </small>
            </c:if>
        </h2>
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
            projectId: "${empty projectId?0:projectId}",
            processInsId: "${empty processInsId?0:processInsId}",
            processLogUrl: "${pageContext.request.contextPath}/public/getApprovalLog",
            projectLogUrl: "${pageContext.request.contextPath}/public/getApprovalLogByProject"

        },

        //初始化加载数据
        initLoadData: function () {
            //1.如果项目id为空 则直接加载流程日志
            //2.如果项目id不为空 流程id为空 则直接加载项目日志
            //3.项目id与流程id都不为空 则优先加载流程日志
            if(formLog.config.projectId&&!formLog.config.processInsId){
                $("#formLogProject").trigger('click');
            }else{
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

</script>