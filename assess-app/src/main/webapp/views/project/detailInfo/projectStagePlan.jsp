<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${projectPlan.projectStatus ne 'wait'}">
    <style>
        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            word-break: break-all;
        }

        .table > thead > tr > th:nth-child(4) {
            width: 200px;
        }
    </style>
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header collapse-link">
                <div class="card-head-row">
                    <div class="card-title">
                            ${projectPlan.planName}
                        <small>
                            <a target="_blank" class="btn btn-xs btn-primary"
                               href="${pageContext.request.contextPath}/projectReportFile/index?projectId=${projectInfo.id}">估价委托书及相关证明</a>
                            <input type="button" class="btn btn-xs btn-primary"
                                   onclick="projectDetailsEnterNextStage();"
                                   value="进入下阶段">
                            <span id="workStageCustomBtn">
                        </span>
                        </small>
                    </div>
                    <div class="card-tools">
                        <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" id="project_stage_query">
                    <div class="row form-group">
                        <div class="col-md-3">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">
                                任务状态
                            </label>
                            <div class="col-sm-10">
                                <select class="form-control input-full" name="status">
                                    <option value="">-请选择-</option>
                                    <option value="wait">等待发起</option>
                                    <option value="runing">进行中</option>
                                    <option value="finish">完成</option>
                                </select>
                            </div>
                        </div>
                        </div>
                        <div class="col-md-3">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">
                                责任人
                            </label>
                            <div class="col-sm-10">
                                <input type="hidden" name="executeUserAccount">
                                <input type="text" readonly="readonly"
                                       onclick="projectStagePlan.selectProjectPhaseExecuteUserAccount(this);"
                                       placeholder="责任人" name="executeUserAccountName" class="form-control input-full">
                            </div>
                        </div>
                        </div>
                        <div class="col-md-3">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">
                                开始时间
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="开始时间" data-date-format="yyyy-mm-dd"
                                       name="planStartDate"
                                       class="form-control input-full dbdate" readonly="readonly">
                            </div>
                        </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-inline x-valid">
                                <label class=" col-sm-2 col-form-label">
                                    结束时间
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" placeholder="结束时间" data-date-format="yyyy-mm-dd"
                                           name="planEndDate"
                                           class="form-control input-full dbdate" readonly="readonly">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">

                        <div class="col-md-3">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">
                                名称
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="名称" name="projectPhaseName" class="form-control input-full">
                            </div>
                        </div>
                        </div>

                        <bu class="btn btn-primary btn-sm" onclick="projectStagePlan.loadProjectTaskList();">查询</bu>
                        <a class="btn btn-info btn-sm" onclick="projectStagePlan.createTask()">添加任务</a>
                        <a class="btn btn-danger btn-sm" onclick="projectStagePlan.deletePlanDetailsByIds()">删除任务</a>
                        <a class="btn btn-primary btn-sm"
                                   onclick="projectStagePlan.setExecuteUserAccount();">设置责任人</a>

                    </div>
                </form>
                <table id="tb_project_stage" class="table table-bordered">
                </table>
            </div>
        </div>
    </div>
</c:if>
<%--    <div class="row">
        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <div class="x_panel">
                <div class="x_title">
                    <h3>
                            ${projectPlan.planName}
                        <small>
                            <a target="_blank" class="btn btn-xs btn-primary"
                               href="${pageContext.request.contextPath}/projectReportFile/index?projectId=${projectInfo.id}">估价委托书及相关证明</a>
                            <input type="button" class="btn btn-xs btn-primary"
                                   onclick="projectDetailsEnterNextStage();"
                                   value="进入下阶段">
                            <span id="workStageCustomBtn">
                        </span>
                        </small>
                    </h3>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="project_stage_query">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    任务状态
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select class="form-control input-full" name="status">
                                        <option value="">-请选择-</option>
                                        <option value="wait">等待发起</option>
                                        <option value="runing">进行中</option>
                                        <option value="finish">完成</option>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    责任人
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="hidden" name="executeUserAccount">
                                    <input type="text" readonly="readonly"
                                           onclick="projectStagePlan.selectProjectPhaseExecuteUserAccount(this);"
                                           placeholder="责任人" name="executeUserAccountName" class="form-control input-full">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    开始时间
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" placeholder="开始时间" data-date-format="yyyy-mm-dd"
                                           name="planStartDate"
                                           class="form-control input-full dbdate" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    结束时间
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" placeholder="结束时间" data-date-format="yyyy-mm-dd"
                                           name="planEndDate"
                                           class="form-control input-full dbdate" readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    名称
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" placeholder="名称" name="projectPhaseName" class="form-control input-full">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                                    <a class="btn btn-primary" onclick="projectStagePlan.loadProjectTaskList();">查询</a>
                                    <a class="btn btn-info" onclick="projectStagePlan.createTask()">添加任务</a>
                                    <a class="btn btn-danger" onclick="projectStagePlan.deletePlanDetailsByIds()">删除任务</a>
                                    <a class="btn btn-primary"
                                       onclick="projectStagePlan.setExecuteUserAccount();">设置责任人</a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <table id="tb_project_stage" class="table table-bordered">
                    </table>
                </div>
            </div>
        </div>
    </div>--%>

<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">计划编辑</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                工作事项<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="projectPhaseId"
                                                        onchange="projectStagePlan.setPhaseNameDefault(this)"
                                                        class="form-control input-full search-select select2">
                                                    <option value="">-选择-</option>
                                                    <c:forEach var="item" items="${projectPhaseVoList}">
                                                        <option value="${item.id}">${item.projectPhaseName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                任务名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="hidden" name="id"/>
                                                <input type="text" placeholder="任务名称" required="required" maxlength="100"
                                                       name="projectPhaseName"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                开始时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input required type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd'
                                                       name="planStartDate"
                                                       class="form-control input-full dbdate">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                结束时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input required type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd'
                                                       name="planEndDate"
                                                       class="form-control input-full dbdate">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                责任人 <span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="hidden" name="executeUserAccount">
                                                <input readonly="readonly"
                                                       onclick="projectStagePlan.selectProjectPhaseExecuteUserAccount(this)"
                                                       class="form-control input-full" placeholder="点击选择责任人" type="text"
                                                       required="required" name="executeUserName"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-11">
                                            <textarea class="form-control input-full" name="planRemarks"
                                                      placeholder="说明"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="projectStagePlan.saveStagePlan(this);">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<%--
<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">计划编辑</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            工作事项 <span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select required="required" name="projectPhaseId"
                                                    onchange="projectStagePlan.setPhaseNameDefault(this)"
                                                    class="form-control input-full search-select select2">
                                                <option value="">-选择-</option>
                                                <c:forEach var="item" items="${projectPhaseVoList}">
                                                    <option value="${item.id}">${item.projectPhaseName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            任务名称 <span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-8  col-sm-8  col-md-8  col-lg-8 ">
                                            <input type="hidden" name="id"/>
                                            <input type="text" placeholder="任务名称" required="required" maxlength="100"
                                                   name="projectPhaseName"
                                                   class="form-control input-full">
                                        </div>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="button" class="btn btn-xs btn-success" value="＋"
                                                   onclick="projectStagePlan.addPhaseName(this);">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            开始时间 <span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input required type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd'
                                                   name="planStartDate"
                                                   class="form-control input-full dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            结束时间 <span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input required type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd'
                                                   name="planEndDate"
                                                   class="form-control input-full dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        责任人 <span class="symbol required"></span>
                                    </label>
                                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                        <input type="hidden" name="executeUserAccount">
                                        <input readonly="readonly"
                                               onclick="projectStagePlan.selectProjectPhaseExecuteUserAccount(this)"
                                               class="form-control input-full" placeholder="点击选择责任人" type="text"
                                               required="required" name="executeUserName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        说明
                                    </label>
                                    <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                        <textarea class="form-control input-full" name="planRemarks"
                                                  placeholder="说明"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="projectStagePlan.saveStagePlan(this);">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
--%>

<div id="replyTaskBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">重启任务</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="planDetailsId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                重启原因<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-11">
                                             <textarea placeholder="重启原因" name="reason"
                                                       class="form-control input-full" required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <input id="returnUploadFile" type="file" multiple="false">
                                                <div id="_returnUploadFile"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="projectStagePlan.replyTaskBtn();">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<%--<div id="replyTaskBox" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">重启任务</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="planDetailsId">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            重启原因<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <textarea placeholder="重启原因" name="reason"
                                                      class="form-control input-full" required></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            附件
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input id="returnUploadFile" type="file" multiple="false">
                                            <div id="_returnUploadFile"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="projectStagePlan.replyTaskBtn()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>--%>
<script type="text/html" id="phaseNameHtml">
    <div class="form-group append-phase-name">
        <div class="x-valid">
            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                任务名称 <span class="symbol required"></span>
            </label>
            <div class=" col-xs-8  col-sm-8  col-md-8  col-lg-8 ">
                <input type="hidden" name="id"/>
                <input type="text" placeholder="任务名称" required="required" maxlength="100"
                       name="projectPhaseName"
                       class="form-control input-full">
            </div>
            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                <input type="button" class="btn btn-xs btn-warning" value="－"
                       onclick="$(this).closest('.form-group').remove();">
            </div>
        </div>
    </div>
</script>
<script type="text/javascript">
    //进入下阶段
    function projectDetailsEnterNextStage() {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectInfo/enterNextStage',
            data: {
                projectId: '${projectInfo.id}'
            },
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("操作成功", "提交数据成功")
                    try {
                        projectStagePlan.stageTable.bootstrapTable('refresh');
                    } catch (e) {
                        console.log(e);
                    }
                } else {
                    AlertError(result.errmsg);
                }
            }
        });
    }
</script>
<script type="text/javascript">

    var projectStagePlan = {
        stageTable: $("#tb_project_stage"),
        loadProjectTaskList: function () {
            var cols = [];
            cols.push({field: 'id', title: 'id', visible: false});
            cols.push({checkbox: true});
            cols.push({
                field: 'projectPhaseName', title: '名称', width: '45%', formatter: function (value, row, index) {
                    var str = row.projectPhaseName;
                    if (row.planRemarks) {
                        str += "<span style='font-size: 10px;'>(" + row.planRemarks + ")</span>";
                    }
                    return str;
                }
            });
            cols.push({
                field: 'executeUserName', title: '责任人/审批人', formatter: function (value, row, index) {
                    var s = value;
                    if (row.approverUserName) {
                        s += '/' + row.approverUserName;
                    }
                    return s;
                }
            });
            cols.push({
                field: 'status', title: '状态', formatter: function (value, row, index) {
                    var str = "";
                    switch (value) {
                        case "runing": {
                            str = "<label class='label label-info'>" + "进行中" + "</label>";
                            break;
                        }
                        case "finish": {
                            str = "<label class='label label-success'>" + "已完成" + "</label>";
                            break;
                        }
                        case "wait": {
                            str = "<label class='label label-warning'>" + "等待发起" + "</label>";
                            break;
                        }
                        case "none": {
                            str = "<label class='label label-default'>" + row.projectPhaseName + "</label>";
                            break;
                        }
                    }
                    return str;
                }
            });
            cols.push({
                field: 'planStartDate', title: '开始日期', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'planEndDate', title: '结束日期', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    var str = "";
                    if (row.status == 'wait') {
                        str += "<a onclick='projectStagePlan.editStagePlan(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-edit fa-white'></i></a>";
                        str += "<a onclick='projectStagePlan.deleteStagePlan(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips' ><i class='fa fa-minus fa-white'></i></a>";
                    }
                    if (row.canReplay) {
                        str += "<a onclick='projectStagePlan.replyTask(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='重启' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-reply fa-white'></i></a>";
                    }
                    if (row.excuteUrl) {
                        var btnClass = 'btn-success';
                        if (/processInsId/.test(row.excuteUrl)) {
                            btnClass = 'btn-primary';
                        }
                        str += "<a onclick='projectStagePlan.taskOpenWin(\"" + row.excuteUrl + "\")' href='javascript://' style='margin-left: 5px;' data-placement='top' data-original-title='提交' class='btn btn-xs " + btnClass + " tooltips'  ><i class='fa fa-arrow-right fa-white'></i></a>";
                    }
                    if (row.displayUrl) {
                        str += "<a onclick='projectStagePlan.taskOpenWin(\"" + row.displayUrl + "\")' href='javascript://' style='margin-left: 5px;' data-placement='top' data-original-title='查看' class='btn btn-xs btn-warning tooltips'  ><i class='fa fa-search fa-white'></i></a>";
                    }
                    if (row.canCopy) {
                        str += " <a href='javascript://' onclick='projectStagePlan.taskCopy(this," + row.id + ");' data-planDetailsId='" + row.id + "' title='复制' class='btn btn-xs btn-warning btn-copy' ><i class='fa fa-copy fa-white'></i> <span>复制</span></a>";
                    }
                    if (row.canPaste) {
                        str += " <a href='javascript://' onclick='projectStagePlan.taskPaste(this," + row.id + ");' data-planDetailsId='" + row.id + "' title='粘贴' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-paste fa-white'></i> <span>粘贴</span></a>";
                    }
                    return str;
                }
            });
            var select = {
                projectId: "${projectInfo.id}",
                planId: "${projectPlan.id}"
            };
            var data = formSerializeArray($("#project_stage_query"));
            jQuery.extend(select, data);
            projectStagePlan.stageTable.bootstrapTable('destroy');
            TableInit(projectStagePlan.stageTable, "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId", cols, {formData: JSON.stringify(select)}, {
                toolbar: '#projectStageToolbar',
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        replyTask: function (planDetailsId) {
            var box = $("#replyTaskBox");
            var form = box.find("form");
            form.clearAll();
            $("#_returnUploadFile").empty();
            form.initForm({planDetailsId: planDetailsId});
            //重启附件
            projectStagePlan.loadTemplateAttachment(planDetailsId);
            FileUtils.uploadFiles({
                target: "returnUploadFile",
                showFileList: false,
                onUpload: function (file) {//上传之前触发
                    var formData = {
                        tableName: AssessDBKey.ProjectTaskReturnRecord,
                        tableId: 0,
                        fieldsName: planDetailsId
                    };
                    return formData;
                },
                onUploadComplete: function () {
                    projectStagePlan.loadTemplateAttachment(planDetailsId);
                }
            });
            box.modal("show");
        },
        replyTaskBtn: function () {
            var box = $("#replyTaskBox");
            var form = box.find("form");
            if (!form.valid()) {
                return false;
            }
            var data = formSerializeArray(form);
            $.ajax({
                url: '${pageContext.request.contextPath}/projectPlanDetails/replyProjectPlanDetails',
                data: {
                    planDetailsId: data.planDetailsId,
                    formData: JSON.stringify(data)
                },
                success: function (result) {
                    if (result.ret) {
                        box.modal("hide");
                        notifySuccess("成功", "任务重启成功");
                        projectStagePlan.stageTable.bootstrapTable('refresh');

                    } else {
                        notifyWarning("失败","失败原因:"+result.errmsg);
                    }
                }
            })
        },
        //打开任务页面的回调
        taskOpenWin: function (url) {
            openWin(url, function () {
                projectStagePlan.stageTable.bootstrapTable('refresh');
            })
        },

        loadTemplateAttachment: function (planDetailsId) {
            //加载附件
            FileUtils.getFileShows({
                target: "returnUploadFile",
                formData: {
                    tableName: AssessDBKey.ProjectTaskReturnRecord,
                    tableId: 0,
                    fieldsName: planDetailsId
                },
                editFlag: true,
                deleteFlag: true
            });
        }
    };

    /**
     * erp 人员选择
     */
    projectStagePlan.selectExecuteUserAccount = function (multi, callback) {
        erpEmployee.select({
            multi: multi,
            currOrgId: '${companyId}',
            onSelected: function (data) {
                if (callback) {
                    callback(data);
                }
            }
        });
    };

    /**
     * 任务分派
     */
    projectStagePlan.createTask = function () {
        var box = $("#div_plan");
        var form = box.find("form");
        form.clearAll().find('.append-phase-name').remove();
        box.modal("show");
    };

    /*执行人员安排*/
    projectStagePlan.setExecuteUserAccount = function () {
        if ('${projectInfo.projectMemberVo.userAccountManager}' != '${sysUserDto.userAccount}') {
            notifyInfo("只有项目经理才能安排任务的执行人员");
            return false;
        }
        var rows = projectStagePlan.stageTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo("还未选择相关任务");
        }
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });
        projectStagePlan.selectExecuteUserAccount(false, function (data) {
            if (data && data.account) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/projectPlanDetails/batchUpdateExecuteUser',
                    data: {
                        planDetailsIds: idArray.join(","),
                        newExecuteUser: data.account
                    },
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "责任人调整成功");
                            projectStagePlan.stageTable.bootstrapTable('refresh');
                        } else {
                            AlertError(result.errmsg);
                        }
                    }
                });
            } else {
                AlertError("还未选择任何人员");
            }
        });

    };

    /**
     * 任务编辑
     */
    projectStagePlan.editStagePlan = function (id) {
        var box = $("#div_plan");
        var form = box.find("form");
        var item = projectStagePlan.stageTable.bootstrapTable('getRowByUniqueId', id);
        form.clearAll();
        form.initForm(item).find('.append-phase-name').remove();
        box.modal("show");
    };

    /**
     * 任务分派选择人员
     * @param _this
     */
    projectStagePlan.selectProjectPhaseExecuteUserAccount = function (_this) {
        projectStagePlan.selectExecuteUserAccount(false, function (data) {
            if (data && data.account) {
                $(_this).val(data.name);
                $(_this).closest("form").find("[name='executeUserAccount']").val(data.account);
            } else {
                notifyWarning("还未选择任何人员");
            }
        });
    };

    projectStagePlan.setPhaseNameDefault = function (_this) {
        var form = $(_this).closest("form");
        var val = $(_this).find("option:selected").val();
        if (!form.find("[name='projectPhaseName']").val() && val) {
            var name = $(_this).find("option:selected").text();
            form.find("[name='projectPhaseName']").val(name);
        }
    };

    /**
     * 任务分派 添加任务
     */
    projectStagePlan.saveStagePlan = function () {
        var box = $("#div_plan");
        var form = box.find("form");
        if (!form.valid()) {
            return false;
        }
        var data = formSerializeArray(form);
        data.planId = '${projectPlan.id}';
        data.projectId = '${projectInfo.id}';
        data.projectWorkStageId = '${projectWorkStage.id}';
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectPlanDetails/saveProjectStagePlan",
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    box.modal("hide");
                    projectStagePlan.stageTable.bootstrapTable('refresh');
                } else {
                    AlertError("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    /**
     * 删除任务
     * @param id
     */
    projectStagePlan.deletePlanDetailsByIds = function () {
        if ('${projectInfo.projectMemberVo.userAccountManager}' != '${sysUserDto.userAccount}') {
            notifyInfo("只有项目经理才能安排任务的执行人员");
            return false;
        }
        var rows = projectStagePlan.stageTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo("还未选择相关任务");
            return false;
        }
        var idArray = [];
        $.each(rows, function (i, item) {
            idArray.push(item.id);
        });

        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectPlanDetails/deletePlanDetailsByIds",
                data: {
                    planDetailsIds: idArray.join()
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "任务已经删除");
                        projectStagePlan.loadProjectTaskList();
                    } else {
                        AlertError(result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });

        })
    };
    var copyPlanDetailsTempId = undefined;
    //工作事项复制
    projectStagePlan.taskCopy = function (_this, id) {
        $(_this).closest('.tab-pane').find('.btn-copy').each(function () {
            $(this).find('span').text('复制');
        });
        $(_this).find('span').text('已被复制');
        copyPlanDetailsTempId = id;
    }

    //工作事项粘贴
    projectStagePlan.taskPaste = function (_this, id) {
        if (!copyPlanDetailsTempId) {
            notifyInfo('请选择复制对象');
            return false;
        }
        if (id == copyPlanDetailsTempId) {
            notifyInfo('无法粘贴自己');
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/projectPlanDetails/taskPaste',
            data: {
                copyPlanDetailsId: copyPlanDetailsTempId,
                pastePlanDetailsId: id
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "粘贴完成");
                } else {
                    AlertError("复制失败，失败原因:" + result);
                }
            }
        })
    }

    //添加事项名称
    projectStagePlan.addPhaseName = function (_this) {
        var html = $('#phaseNameHtml').html();
        var phaseNameEles = $(_this).closest('form').find('.append-phase-name');
        if (phaseNameEles.length > 0) {
            phaseNameEles.last().after(html);
        } else {
            $(_this).closest('.form-group').after(html);
        }
    }

    $(function () {
        projectStagePlan.loadProjectTaskList();
    });

</script>
