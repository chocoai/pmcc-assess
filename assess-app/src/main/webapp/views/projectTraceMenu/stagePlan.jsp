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
    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
        <div class="x_panel">
            <div class="x_content">
                <p id="projectStageToolbar">
                    <a class="btn btn-info" onclick="projectStagePlan.createTask()">任务分派</a>
                    <a class="btn btn-info" onclick="">自动安排</a>
                    <a class="btn btn-danger" onclick="">发起任务</a>
                    <a class="btn btn-primary" onclick="projectStagePlan.setExecuteUserAccount();">任务执行人员安排</a>
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
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            工作内容
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            任务细名
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">

                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            开始时间
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input required type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd'
                                                   name="planStartDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            结束时间
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input required type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd'
                                                   name="planEndDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        责任人
                                    </label>
                                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        说明
                                    </label>
                                    <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
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

<div id="replyTaskBox" class="modal fade bs-example-modal-lg"
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
                                                      class="form-control" required></textarea>
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
                            str = "<label class='label label-default'>" + row.projectPhaseName + "</label>";
                            break;
                        }
                    }
                    return str;
                }
            });

            cols.push({
                field: 'excuteUrl', title: '待执行任务', formatter: function (value, row, index) {
                    var html = "";
                    if (value) {
                        html += "<button class=\"btn btn-default\" onclick=\"window.open('{server}','_blank')\">" + row.projectPhaseName;
                        html = html.replace(/{server}/g, value);
                        html += "<i class='fa fa-arrow-right fa-white'></i>";
                        html += "</button>";
                    }

                    return html;
                }
            });

            cols.push({
                field: 'displayUrl', title: '任务查看', formatter: function (value, row, index) {
                    var html = "";
                    if (value) {
                        html += "<button class=\"btn btn-success\" onclick=\"window.open('{server}','_blank')\">" + row.projectPhaseName;
                        html = html.replace(/{server}/g, value);
                        html += "<i class='fa fa-check-circle'></i>";
                        html += "</button>";

                        if (row.canReplay) {
                            html += " <button onclick='projectStagePlan.replyTask(" + row.id + ")' title='重启' class='btn btn-primary tooltips' ><i class='fa fa-reply fa-white'></i></button>";
                        }
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
//            cols.push({field: 'planRemarks', title: '说明'});
            cols.push({field: 'declareRecordName', title: '申报记录名称'});
            TableInit(projectStagePlan.stageTable, "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByPlanId", cols, {
                projectId: "${projectInfo.id}",
                planId: "${projectPlan.id}"
            }, {
                toolbar: '#projectStageToolbar',
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
                        toastr.success('任务重启成功');
                        projectStagePlan.stageTable.bootstrapTable('refresh');

                    } else {
                        toastr.info(result.errmsg);
                    }
                }
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
    projectStagePlan.selectExecuteUserAccount = function (multi,callback) {
        erpEmployee.select({
            multi: multi,
            currOrgId: '${companyId}',
            onSelected: function (data) {
                if (callback){
                    callback(data) ;
                }
            }
        });
    };

    /**
     * 任务分派
     */
    projectStagePlan.createTask = function () {
        var box = $("#div_plan");

        box.modal("show");
    };

    /*执行人员安排*/
    projectStagePlan.setExecuteUserAccount = function () {
        if ('${projectInfo.projectMemberVo.userAccountManager}' != '${sysUserDto.userAccount}'){
            toastr.info("只有项目经理才能安排任务的执行人员");
            return false ;
        }
        var rows = projectStagePlan.stageTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要安排人员的任务");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            projectStagePlan.selectExecuteUserAccount(false,function (data) {
                if (data && data.account) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/projectPlanDetails/batchUpdateExecuteUser',
                        data: {
                            planDetailsIds: idArray.join(","),
                            newExecuteUser: data.account
                        },
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('责任人调整成功');
                                projectStagePlan.stageTable.bootstrapTable('refresh');
                            } else {
                                Alert(result.errmsg);
                            }
                        }
                    });
                } else {
                    Alert("还未选择任何人员");
                }
            });
        }
    };

    $(function () {
        projectStagePlan.initStageTable();
    });

</script>
