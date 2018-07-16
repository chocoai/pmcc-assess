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
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentPlan.projectPhaseName}-任务分派</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-12">
                        <span class="col-sm-2">
                            <input type="radio" id="examineFormType_residence"
                                   onclick="explore.initExamineTask(this);"
                                   name="examineFormType" data-examineType="0"
                                   value="fc.residence"><label for="examineFormType_residence">&nbsp;住宅商业办公</label>
                        </span>
                        <span class="col-sm-2">
                            <input type="radio" id="examineFormType_industry" name="examineFormType" data-examineType="1"
                                   onclick="explore.initExamineTask(this)" value="fc.industry"><label
                                for="examineFormType_industry">&nbsp;工业仓储</label>
                        </span>
                    </div>
                    <table id="survey_examine_task_list" class="easyui-datagrid" style="max-height: auto;"></table>
                    <div id="survey_examine_task_list_tb" style="padding:5px;height:auto;display: none;">
                        <div style=" margin-bottom:5px">
                            <button type="button" onclick="explore.keySet()" class="btn btn-warning btn-xs">
                                <i class='fa fa-fire fa-white'></i> 批量设置
                            </button>
                        </div>
                    </div>
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="commit_btn" class="btn btn-primary" onclick="commitApply();">
                            确认分派<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>

    </div>
</div>
</div>
</body>

<div id="div_fastSet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
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
                                                    <input type="hidden" id="fast_executeUserAccount"
                                                           class="fast_value">
                                                    <input type="text" id="fast_executeUserName" readonly="readonly"
                                                           placeholder="责任人" class="form-control" required
                                                           onclick="explore.selFastEmployee();">
                                                    <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择"
                                                            onclick="explore.selFastEmployee();">
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
                <button type="button" class="btn btn-primary" onclick="explore.saveFastset()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="add_examine_task_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1"
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
                <button type="button" class="btn btn-primary" onclick="explore.saveSelectExamineTask()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="edit_examine_task_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1"
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
                                                    <input type="text" readonly="readonly" name="userName"
                                                           placeholder="责任人" class="form-control" required
                                                           onclick="explore.editSelectEmployee();">
                                                    <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择"
                                                            onclick="explore.editSelectEmployee();">
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
                <button type="button" class="btn btn-primary" onclick="explore.saveExamineTask()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">
    $(function () {
        explore.loadTreeGrid();
    })

    var explore = {
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
                    explore.editExamineTask(row);
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
                        field: "bisFinish",
                        title: "状态",
                        width: "20%",
                        formatter: function (value, row) {
                            if (row.pid == 0) return "";
                            return row.bisFinish ? "完成" : "进行中";
                        }
                    },
                    {
                        field: 'opt', title: '操作', width: '20%', formatter: function (value, row) {
                        //1.如果第一次层级只允许新增下级
                        //2.非第一层级 任务已结束不允许任何操作 任务可编辑与删除 必须填写任务不允许删除
                        var s = "";
                        if (row.pid == 0) {//第一层级
                            s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips'  onclick='explore.addExamineTask(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                        } else {
                            if (!row.bisFinish) {
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips'  onclick='explore.editExamineTask(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                                if (!row.bisMust) {
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips'  onclick='explore.delExamineTask(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
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
                        explore.loadTreeGrid();
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
            if(cbxs.length<=0){
                Alert('还未选择任何任务数据');
                return false;
            }
            var idArray=[];
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
                    examineType: $(":radio[name='examineFormType']:checked").attr('data-examineType'),
                    pid;
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
                        explore.loadTreeGrid();
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
                        explore.loadTreeGrid();
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
            var examineFormType = $(_this).val();
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/initExamineTask",
                data: {
                    planDetailsId: "${projectPlanDetails.id}",
                    userAccount: "${projectPlanDetails.executeUserAccount}",
                    examineFormType: examineFormType,
                    declareRecordId: "${parentPlan.declareRecordId}",
                    examineType: $(":radio[name='examineFormType']:checked").attr('data-examineType')
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        toastr.success("保存成功");
                        explore.loadTreeGrid();
                    } else {
                        Alert("保存失败:" + result.errmsg);
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
                        explore.loadTreeGrid();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        }
    }

</script>
</html>

