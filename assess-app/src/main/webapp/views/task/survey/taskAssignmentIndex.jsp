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
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h3>
                        ${declareRecord.name}
                    </h3>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!--填写表单-->
            <input type="hidden" id="declareId" name="declareId" value="${declareRecord.id}">
            <input type="hidden" id="examineType" name="examineType" value="${examineType}">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${projectPlanDetails.projectPhaseName}-任务分派</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-12">
                        <c:choose>
                            <c:when test="${empty surveyExamineInfo}">
                                <c:forEach var="item" items="${examineFormTypeList}">
                                    <span class="col-sm-2">
                                        <input type="radio" id="examineFormType_${item.key}"
                                               onclick="taskAssignment.initExamineTask(this);" name="examineFormType"
                                               value="${item.key}"><label
                                            for="examineFormType_${item.key}">&nbsp;${item.value}</label>
                                    </span>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="item" items="${examineFormTypeList}">
                                    <c:if test="${item.key eq surveyExamineInfo.examineFormType}">
                                        <span class="col-sm-2"><label>${item.value}</label> </span>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <table id="survey_examine_task_list" class="easyui-datagrid" style="max-height: auto;"></table>
                    <div id="survey_examine_task_list_tb" style="padding:5px;height:auto;display: none;">
                        <div style=" margin-bottom:5px">
                            <button type="button" onclick="taskAssignment.keySet()" class="btn btn-warning btn-xs">
                                <i class='fa fa-fire fa-white'></i> 批量设置
                            </button>
                        </div>
                    </div>
                    <div class="col-sm-12" style="text-align: center;">
                        <button class="btn btn-default" onclick="window.close();">
                            取 消
                        </button>
                        <button id="commit_btn" class="btn btn-success" onclick="taskAssignment.confirmAssignment();">
                            确认分派<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>

                    <div id="div_fastSet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1"
                         role="dialog"
                         aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title">批量设置</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-body">
                                                <form id="frm_fastset" class="form-horizontal">
                                                    <table class="table table-striped table-bordered table-hover table-bordered"
                                                           id="sample-table-2">
                                                        <thead>
                                                        <tr>
                                                            <th class="hidden-xs">设置内容</th>
                                                            <th class="hidden-xs">值</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr class="fast_tr">
                                                            <td class="hidden-xs">责任人</td>
                                                            <td class="hidden-xs">
                                                                <div class="x-valid">
                                                                    <div class="input-group">
                                                                        <input type="hidden"
                                                                               id="fast_executeUserAccount"
                                                                               class="fast_value">
                                                                        <input type="text" id="fast_executeUserName"
                                                                               readonly="readonly"
                                                                               placeholder="责任人" class="form-control"
                                                                               required
                                                                               onclick="taskAssignment.selFastEmployee();">
                                                                        <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择"
                                                            onclick="taskAssignment.selFastEmployee();">
                                                    <i class="fa fa-search"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            onclick="$(this).closest('.input-group').find('input').val('');"
                                                            data-toggle="tooltip" data-original-title="清除">
                                                    <i class="fa fa-trash-o"></i>
                                                    </button>
                                                </span>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" data-dismiss="modal" class="btn btn-default">
                                        取消
                                    </button>
                                    <button type="button" class="btn btn-primary"
                                            onclick="taskAssignment.saveFastset()">
                                        保存
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="add_examine_task_modal" class="modal fade bs-example-modal-lg" data-backdrop="static"
                         tabindex="1"
                         role="dialog"
                         aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title">添加调查任务</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-body">
                                                <form class="form-horizontal">
                                                    <input type="hidden" name="pid">
                                                    <table class="table table-striped table-bordered table-hover table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th class="hidden-xs">设置内容</th>
                                                            <th class="hidden-xs">值</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr class="fast_tr">
                                                            <td class="hidden-xs">任务信息</td>
                                                            <td class="hidden-xs">
                                                                <div class="content">

                                                                </div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" data-dismiss="modal" class="btn btn-default">
                                        取消
                                    </button>
                                    <button type="button" class="btn btn-primary"
                                            onclick="taskAssignment.saveSelectExamineTask()">
                                        保存
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="edit_examine_task_modal" class="modal fade bs-example-modal-lg" data-backdrop="static"
                         tabindex="1"
                         role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title">任务编辑</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-body">
                                                <form class="form-horizontal">
                                                    <input type="hidden" name="id">
                                                    <table class="table table-striped table-bordered table-hover table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th class="hidden-xs">设置内容</th>
                                                            <th class="hidden-xs">值</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr class="fast_tr">
                                                            <td class="hidden-xs">责任人</td>
                                                            <td class="hidden-xs">
                                                                <div class="x-valid">
                                                                    <div class="input-group">
                                                                        <input type="hidden" name="userAccount">
                                                                        <input type="text" readonly="readonly"
                                                                               name="userName"
                                                                               placeholder="责任人" class="form-control"
                                                                               required
                                                                               onclick="taskAssignment.editSelectEmployee();">
                                                                        <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择"
                                                            onclick="taskAssignment.editSelectEmployee();">
                                                    <i class="fa fa-search"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            onclick="$(this).closest('.input-group').find('input').val('');"
                                                            data-toggle="tooltip" data-original-title="清除">
                                                    <i class="fa fa-trash-o"></i>
                                                    </button>
                                                </span>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" data-dismiss="modal" class="btn btn-default">
                                        取消
                                    </button>
                                    <button type="button" class="btn btn-primary"
                                            onclick="taskAssignment.saveExamineTask()">
                                        保存
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function () {
        taskAssignment.loadTreeGrid();
    })

    var taskAssignment = {
        //加载treegrid
        loadTreeGrid: function () {
            $("#survey_examine_task_list").treegrid({
                url: getContextPath() + "/surveyExamine/getExamineTaskList?planDetailsId=${projectPlanDetails.id}",
                method: 'get',
                idField: 'id',
                checkbox: 'true',
                treeField: 'name',
                datatype: 'json',
                lines: true,
                width: 'auto',
                toolbar: "#survey_examine_task_list_tb",
                rownumbers: true,
                onDblClickRow: function (row) {
                    taskAssignment.editExamineTask(row.id);
                },
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                },

                columns: [[
                    {field: "name", title: "工作内容", width: "30%", align: "left"},
                    {
                        field: "userName",
                        title: "责任人",
                        width: "20%",
                        formatter: function (value, row) {
                            return row.pid == 0 ? "" : value;
                        }
                    },
                    {
                        field: "taskStatusName",
                        title: "状态",
                        width: "20%",
                        formatter: function (value, row) {
                            if (row.pid == 0) return "";
                            return value;
                        }
                    },
                    {
                        field: 'opt', title: '操作', width: '20%', formatter: function (value, row) {
                        //1.如果第一次层级只允许新增下级
                        //2.非第一层级 任务已结束不允许任何操作 任务可编辑与删除 必须填写任务不允许删除
                        var s = "";
                        if (row.pid == 0) {//第一层级
                            s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips'  onclick='taskAssignment.addExamineTask(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                        } else {
                            if (!row.bisFinish) {
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips'  onclick='taskAssignment.editExamineTask(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                                if (!row.bisMust) {
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips'  onclick='taskAssignment.delExamineTask(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                                }
                            }
                        }
                        return s;
                    }
                    }
                ]]
            });
        },

        //快速设置
        keySet: function () {
            var gridRows = $("#survey_examine_task_list").treegrid("getCheckedNodes");
            if (gridRows.length <= 0) {
                Alert('还未选择任何数据');
                return false;
            }
            $("#frm_fastset").clearAll();
            $('#div_fastSet').modal({backdrop: 'static', keyboard: false});
        },

        //快速设置选择人员
        selFastEmployee: function () {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data.account) {
                        $("#fast_executeUserAccount").val(data.account);
                        $("#fast_executeUserName").val(data.name);
                    }
                    else {
                        $("#fast_executeUserAccount").val("");
                        $("#fast_executeUserName").val("");
                    }
                }
            });
        },

        //保存批量设置
        saveFastset: function () {
            if (!$("#frm_fastset").valid()) {
                return false;
            }
            var gridRows = $("#survey_examine_task_list").treegrid("getCheckedNodes");
            var idArray = [];
            $.each(gridRows, function (i, item) {
                idArray.push(item.id);
            })
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/saveFastSet",
                data: {
                    ids: idArray.join(),
                    userAccount: $("#fast_executeUserAccount").val()
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("设置成功");
                        $('#div_fastSet').modal('hide');
                        taskAssignment.loadTreeGrid();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });

        },

        //新增调查任务
        addExamineTask: function (id) {
            var row = $("#survey_examine_task_list").treegrid("find", id);
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/getCanAppendTaskList",
                data: {
                    dataTaskId: row.dataTaskId,
                    pid: row.id,
                    planDetailsId: "${projectPlanDetails.id}"
                },
                type: "get",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        $('#add_examine_task_modal').find('.content').empty();
                        var html = '';
                        $.each(result.data, function (i, item) {
                            html += '<span class="checkbox-inline">' +
                                ' <input type="checkbox" id="add_cbx_task_' + item.id + '" name="dataExamineId"' +
                                ' value="' + item.id + '"><label for="add_cbx_task_' + item.id + '">' + item.name + '</label>' +
                                ' </span>';
                        })
                        $('#add_examine_task_modal').find('.content').html(html);
                        $('#add_examine_task_modal').find('[name="pid"]').val(row.id);
                        $('#add_examine_task_modal').modal();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //保存选择的调查任务
        saveSelectExamineTask: function () {
            var cbxs = $('#add_examine_task_modal').find('.content').find("[name='dataExamineId']:checked");
            if (cbxs.length <= 0) {
                Alert('还未选择任何任务数据');
                return false;
            }
            var idArray = [];
            cbxs.each(function () {
                idArray.push($(this).val());
            })
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/saveSelectExamineTask",
                data: {
                    planDetailsId: "${projectPlanDetails.id}",
                    userAccount: "${projectPlanDetails.executeUserAccount}",
                    declareRecordId: "${parentPlan.declareRecordId}",
                    examineType: "${examineType}",
                    pid: $('#add_examine_task_modal').find('[name="pid"]').val(),
                    dataTaskIds: idArray.join()
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        toastr.success("新增成功");
                        $('#add_examine_task_modal').modal('hide');
                        taskAssignment.loadTreeGrid();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //编辑调查任务
        editExamineTask: function (id) {
            var row = $("#survey_examine_task_list").treegrid("find", id);
            $("#edit_examine_task_modal").find("[name='id']").val(row.id);
            $("#edit_examine_task_modal").find("[name='userAccount']").val(row.userAccount);
            $("#edit_examine_task_modal").find("[name='userName']").val(row.userName);
            $('#edit_examine_task_modal').modal();
        },

        //编辑任务选择人员
        editSelectEmployee: function () {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data.account) {
                        $("#edit_examine_task_modal").find("[name='userAccount']").val(data.account);
                        $("#edit_examine_task_modal").find("[name='userName']").val(data.name);
                    }
                    else {
                        $("#edit_examine_task_modal").find("[name='userAccount']").val('');
                        $("#edit_examine_task_modal").find("[name='userName']").val('');
                    }
                }
            });
        },

        //保存调查任务
        saveExamineTask: function () {
            if (!$("#edit_examine_task_modal").find('form').valid()) {
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/saveSurveyExamineTask",
                data: {
                    id: $("#edit_examine_task_modal").find("[name='id']").val(),
                    userAccount: $("#edit_examine_task_modal").find("[name='userAccount']").val()
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        toastr.success("保存成功");
                        $('#edit_examine_task_modal').modal('hide');
                        taskAssignment.loadTreeGrid();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //初始化该调查表下的所有任务
        initExamineTask: function (_this) {
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/initExamineTask",
                data: {
                    projectId: "${projectPlanDetails.projectId}",
                    planDetailsId: "${projectPlanDetails.id}",
                    examineFormType: $("input:radio[name='examineFormType']:checked").val(),
                    userAccount: "${projectPlanDetails.executeUserAccount}",
                    declareRecordId: "${parentPlan.declareRecordId}",
                    examineType: "${examineType}"
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        toastr.success("初始化成功");
                        taskAssignment.loadTreeGrid();
                    } else {
                        Alert("初始化失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //删除调查任务
        delExamineTask: function (id) {
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/deleteSurveyExamineTask",
                data: {
                    id: id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("删除成功");
                        taskAssignment.loadTreeGrid();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //确认分派
        confirmAssignment: function () {
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/confirmAssignment",
                data: {
                    examineInfoId: "${surveyExamineInfo.id}",
                    projectId: "${projectPlanDetails.projectId}",
                    planDetailsId: "${projectPlanDetails.id}",
                    userAccount: "${projectPlanDetails.executeUserAccount}",
                    examineFormType: $("input:radio[name='examineFormType']:checked").val(),
                    declareRecordId: "${parentPlan.declareRecordId}",
                    examineType: "${examineType}"
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("分派成功", 1, null, function () {
                            window.close();
                        })
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        }
    }

</script>
</html>

