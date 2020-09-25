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
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                            ${projectPlan.planName}
                        <small>
                            <input type="button" class="btn btn-md btn-primary"
                                   onclick="window.open('${pageContext.request.contextPath}/projectReportFile/index?projectId=${projectInfo.id}');"
                                   value="估价委托书及相关证明">
                            <input type="button" class="btn btn-md btn-primary"
                                   onclick="projectDetailsEnterNextStage();" value="进入下阶段">
                            <input type="button" class="btn btn-md btn-primary"
                                   onclick="window.open('${pageContext.request.contextPath}/declareRecord/editDeclareRecordNumber/${projectInfo.id}');"
                                   value="权证编号变更">
                            <input type="button" class="btn btn-md btn-primary"
                                   onclick="window.open('${pageContext.request.contextPath}/generateReport/viewResultSheetReport/${projectInfo.id}');"
                                   value="结果表生成">
                        </small>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" id="project_stage_query">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    任务状态
                                </label>
                                <div class="col-sm-2">
                                    <select class="form-control input-full" name="status">
                                        <option value="">-请选择-</option>
                                        <option value="wait">等待发起</option>
                                        <option value="runing">进行中</option>
                                        <option value="finish">完成</option>
                                    </select>
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    责任人
                                </label>
                                <div class="col-sm-2">
                                    <input type="hidden" name="executeUserAccount">
                                    <input type="text" readonly="readonly"
                                           onclick="projectStagePlan.selectProjectPhaseExecuteUserAccount(this);"
                                           placeholder="责任人" name="executeUserAccountName"
                                           class="form-control input-full">
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    开始时间
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" placeholder="开始时间" data-date-format="yyyy-mm-dd"
                                           name="planStartDate"
                                           class="form-control input-full dbdate" readonly="readonly">
                                </div>
                                <label class=" col-sm-1 col-form-label">
                                    结束时间
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" placeholder="结束时间" data-date-format="yyyy-mm-dd"
                                           name="planEndDate"
                                           class="form-control input-full dbdate" readonly="readonly">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    名称
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="名称" name="projectPhaseName"
                                           class="form-control input-full">
                                </div>
                                <div class="col-sm-8">
                                    <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                            onclick="projectStagePlan.loadProjectTaskList();"><span class="btn-label"><i
                                            class="fa fa-search"></i></span>查询
                                    </button>
                                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                            data-toggle="modal" onclick="projectStagePlan.createTask()"><span
                                            class="btn-label"><i
                                            class="fa fa-plus"></i></span>添加任务
                                    </button>
                                    <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"
                                            data-toggle="modal"
                                            onclick="projectStagePlan.deletePlanDetailsByIds()"><span
                                            class="btn-label"><i class="fa fa-minus"></i></span>删除任务
                                    </button>
                                    <button style="margin-left: 5px" class="btn btn-info btn-sm" type="button"
                                            data-toggle="modal"
                                            onclick="projectStagePlan.setExecuteUserAccount();"><span
                                            class="btn-label"><i class="fa fa-people"></i></span>设置责任人
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <table id="tb_project_stage" class="table table-bordered">
                </table>
            </div>
        </div>
    </div>
</c:if>

<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加任务</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                工作事项<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-5">
                                                <select required="required" name="projectPhaseId"
                                                        onchange="projectStagePlan.setPhaseNameDefault(this)"
                                                        class="form-control input-full search-select select2">
                                                    <option value="">-选择-</option>
                                                    <c:forEach var="item" items="${projectPhaseVoList}">
                                                        <option value="${item.id}">${item.projectPhaseName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                责任人 <span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-5">
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
                                            <label class="col-sm-1 col-form-label">
                                                任务名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="hidden" name="id"/>
                                                <input type="text" placeholder="任务名称" required="required"
                                                       maxlength="100"
                                                       name="projectPhaseName"
                                                       class="form-control input-full">
                                            </div>
                                            <div class="col-sm-2">
                                                <button type="button" class="btn btn-success btn-sm"
                                                        onclick=" projectStagePlan.addPhaseName(this);">＋
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                开始时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-5">
                                                <input required type="text" placeholder="开始时间"
                                                       data-date-format='yyyy-mm-dd'
                                                       name="planStartDate"
                                                       class="form-control input-full dbdate">
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                结束时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-5">
                                                <input required type="text" placeholder="结束时间"
                                                       data-date-format='yyyy-mm-dd'
                                                       name="planEndDate"
                                                       class="form-control input-full dbdate">
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

<script type="text/html" id="phaseNameHtml">
    <div class="row form-group append-phase-name">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 col-form-label">
                    任务名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-9">
                    <input type="hidden" name="id"/>
                    <input type="text" placeholder="任务名称" required="required"
                           maxlength="100"
                           name="projectPhaseName"
                           class="form-control input-full">
                </div>
                <div class="col-sm-2">
                    <button type="button" class="btn btn-warning btn-sm"
                            onclick="$(this).closest('.form-group').remove();">－
                    </button>
                </div>
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
                field: 'projectPhaseName', title: '名称', width: '35%', formatter: function (value, row, index) {
                    var str = row.projectPhaseName;
                    if (row.planRemarks) {
                        str += "<span style='font-size: 10px;'>(" + row.planRemarks + ")</span>";
                    }
                    return str;
                }
            });
            cols.push({
                field: 'executeUserName', title: '责任人/审批人', width: '15%', formatter: function (value, row, index) {
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
                            str = "<span class='label label-info'>" + "进行中" + "</span>";
                            break;
                        }
                        case "finish": {
                            str = "<span class='label label-success'>" + "已完成" + "</span>";
                            break;
                        }
                        case "close": {
                            str = "<span class='label label-warning'>" + "关闭" + "</span>";
                            break;
                        }
                        case "none": {
                            str = "<span class='label label-default'>" + row.projectPhaseName + "</span>";
                            break;
                        }
                    }
                    return str;
                }
            });
            cols.push({
                field: 'planStartDate', title: '开始日期', width: '10%', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'planEndDate', title: '结束日期', width: '10%', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'opt', title: '操作', width: '7%', formatter: function (value, row, index) {
                    var str = "";
                    if (row.canReplay && !row.excuteUrl) {
                        str += "<button type='button' onclick='projectStagePlan.replyTask(" + row.id + ")' style='margin-left: 5px;' title='重启' class='btn btn-xs btn-primary'  ><i class='fa fa-reply fa-white'></i></button>";
                    }
                    if (row.excuteUrl) {
                        var btnClass = 'btn-success';
                        if (/processInsId/.test(row.excuteUrl)) {
                            btnClass = 'btn-primary';
                        }
                        str += "<button type='button' onclick='projectStagePlan.taskOpenWin(\"" + row.excuteUrl + "\")' href='javascript://' style='margin-left: 5px;' title='提交' class='btn btn-xs " + btnClass + "'  ><i class='fa fa-arrow-right fa-white'></i></button>";
                    }
                    if (row.displayUrl) {
                        str += "<button type='button' onclick='projectStagePlan.taskOpenWin(\"" + row.displayUrl + "\")' href='javascript://' style='margin-left: 5px;' title='查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
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
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
        <%--if ('${projectInfo.projectMemberVo.userAccountManager}' != '${sysUserDto.userAccount}') {--%>
        <%--    notifyInfo('提示',"只有项目经理才能安排任务的执行人员");--%>
        <%--    return false;--%>
        <%--}--%>
        var rows = projectStagePlan.stageTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "还未选择相关任务");
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
                notifyInfo("提示", "还未选择任何人员");
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
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    /**
     * 删除任务
     * @param id
     */
    projectStagePlan.deletePlanDetailsByIds = function () {
        <%--if ('${projectInfo.projectMemberVo.userAccountManager}' != '${sysUserDto.userAccount}') {--%>
        <%--    notifyInfo('提示',"只有项目经理才能刪除相关任务");--%>
        <%--    return false;--%>
        <%--}--%>
        var rows = projectStagePlan.stageTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "还未选择相关任务");
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
                        notifySuccess("成功", "任务删除成功");
                        projectStagePlan.loadProjectTaskList();
                    } else {
                        AlertError(result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });

        })
    };
    var copyPlanDetailsTempId = undefined;

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
