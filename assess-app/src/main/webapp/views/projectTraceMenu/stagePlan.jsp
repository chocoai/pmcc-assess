<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2019-8-12
  Time: 14:47
  To change this template use File | Settings | File Templates.
  公共的项目计划配置
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <p id="projectStageToolbar">
                    <a class="btn btn-info" onclick="projectStagePlan.createTask()">任务分派</a>
                    <a class="btn btn-info" onclick="">自动安排</a>
                    <a class="btn btn-danger" onclick="">发起任务</a>
                    <label class="label label-warning">先分派任务，再发起任务！发起任务后才能进行操作</label>
                </p>
                <table id="tb_project_stage" class="table table-bordered">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">计划编辑</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            工作内容
                                        </label>
                                        <div class="col-sm-10">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            任务细名
                                        </label>
                                        <div class="col-sm-10">

                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开始时间
                                        </label>
                                        <div class="col-sm-4">
                                            <input required type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd' name="planStartDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            结束时间
                                        </label>
                                        <div class="col-sm-4">
                                            <input required type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd' name="planEndDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        责任人
                                    </label>
                                    <div class="col-sm-4">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        说明
                                    </label>
                                    <div class="col-sm-10">
                                        <textarea class="form-control" name="planRemarks"
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
                <button type="button" class="btn btn-primary" onclick="">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var projectStagePlan = {
        stageTable: $("#tb_project_stage"),
        initStageTable: function () {
            var cols = [];
            cols.push({field: 'id', title: 'id', visible: false});
            cols.push({checkbox: true});
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
                        case "none": {
                            str = "<label class='label label-default'>" + "无" + "</label>";
                            break;
                        }
                    }
                    return str;
                }
            });

            cols.push({
                field: 'excuteUrl', title: '待执行任务', formatter: function (value, row, index) {
                    var html = "";
                    if (value){
                        html += "<button class=\"btn btn-default\" onclick=\"window.open('{server}','_blank')\">"+row.projectPhaseName;
                        html = html.replace(/{server}/g, value);
                        html += "<i class='fa fa-arrow-right fa-white'></i>" ;
                        html += "</button>" ;
                    }
                    return html;
                }
            });

            cols.push({
                field: 'displayUrl', title: '任务查看', formatter: function (value, row, index) {
                    var html = "";
                    if (value){
                        html += "<button class=\"btn btn-success\" onclick=\"window.open('{server}','_blank')\">"+row.projectPhaseName;
                        html = html.replace(/{server}/g, value);
                        html += "<i class='fa fa-check-circle'></i>" ;
                        html += "</button>" ;
                    }
                    return html;
                }
            });
            cols.push({
                field: 'planStartDate', title: '开始日期', formatter: function (value, row, index) {
                    return formatDate(value, false)
                }
            });
            cols.push({
                field: 'planEndDate', title: '结束日期', formatter: function (value, row, index) {
                    return formatDate(value, false)
                }
            });
            cols.push({field: 'executeUserName', title: '执行人'});
            cols.push({field: 'planRemarks', title: '说明'});
            cols.push({
                    title: '操作',
                    formatter: function (value, row, index) {
                        var str = "";

                        return str;
                    }

                }
            );
            TableInit(projectStagePlan.stageTable, "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId", cols, {
                projectId: "${projectInfo.id}",
                planId: "${projectPlan.id}"
            }, {
                toolbar: '#projectStageToolbar',
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };

    $(function () {
        projectStagePlan.initStageTable();
    });

    // 选择责任人
    function selectExecuteUserAccount() {
        erpEmployee.select({
            multi: false,
            currOrgId: 1,
            userName: $("#executeUserName").val(),
            userAccount: $("#executeUserAccount").val(),
            onSelected: function (data) {
                if (data.account) {
                    $("#executeUserAccount").val(data.account);
                    $("#executeUserName").val(data.name);
                } else {
                    $("#executeUserAccount").val("");
                    $("#executeUserName").val("");
                }
            }
        });
    };
</script>