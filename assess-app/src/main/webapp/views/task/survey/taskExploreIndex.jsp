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
                        <span class="col-sm-2"> <input type="radio" id="examineFormType_residence"
                                                       name="examineFormType"
                                                       value="residence"><label for="examineFormType_residence">&nbsp;住宅商业办公</label></span>
                        <span class="col-sm-2"> <input type="radio" id="examineFormType_industry" name="examineFormType"
                                                       value="industry"><label
                                for="examineFormType_industry">&nbsp;工业仓储</label></span>
                    </div>
                    <table id="survey_examine_task_list" class="easyui-datagrid" style="max-height: auto;"></table>
                    <div id="survey_examine_task_list_tb" style="padding:5px;height:auto;display: none;">
                        <div style=" margin-bottom:5px">
                            <button type="button" onclick="keySet()" class="btn btn-warning btn-xs">
                                <i class='fa fa-fire fa-white'></i> 快速设置
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
                <h4 class="modal-title">快速设置</h4>
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
                                        <th class="hidden-xs">范围</th>
                                        <th class="hidden-xs">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">责任人</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="executeUserAccount">
                                            <input type="hidden" id="fast_executeUserAccount" class="fast_value">
                                            <input type="text" id="fast_executeUserName" class="form-control"
                                                   readonly="readonly"
                                                   onclick="selFastEmployee()">
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" id="btn_user"
                                                    onclick="clearFastValue(this)">清除
                                            </button>
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
                <button type="button" class="btn btn-primary" onclick="saveFastset()">
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
        treeGridJson: {},

        //加载treegrid
        loadTreeGrid: function () {
            $("#survey_examine_task_list").treegrid({
                data: explore.treeGridJson,
                idField: 'id',
                treeField: 'name',
                datatype: 'json',
                lines: true,
                width: 'auto',
                toolbar: "#survey_examine_task_list_tb",
                rownumbers: true,
                onDblClickRow: function (row) {
                    editPlan(row.id);
                },
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                },

                columns: [[
                    {field: "projectPhaseName", title: "工作内容", width: "30%", align: "left"},
                    {
                        field: "executeUserName",
                        title: "责任人",
                        width: "20%",
                        align: "center"
                    },
                    {
                        field: 'workStages', title: '操作', width: '20%', formatter: function (value, row) {
                        if (row.bisEnable) {
                            var s = "";
                            if ($("#planDetailsIds").val()) {
                                //如果不为空则说明是子计划，如果为子计划，则只允许新增项或编辑当前项
                                var planDetailsId = $("#planDetailsIds").val();
                                var aPlanDetailsId = planDetailsId.split(",");
                                if ($.inArray(row.id + "", aPlanDetailsId) >= 0) {
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips' target='_blank' onclick='addPlan(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                                }
                                else {
                                    if ($.inArray(row.firstPid + "", aPlanDetailsId) >= 0) {
                                        s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips' target='_blank' onclick='addPlan(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                                        s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                                        if (row.bisLastLayer) {
                                            s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips' target='_blank' onclick='deletePlan(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                                        }

                                    }
                                }
                            }
                            else {
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips' target='_blank' onclick='addPlan(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                                if (row.bisLastLayer) {
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips' target='_blank'   onclick='deletePlan(" + row.id + ")'><i class='fa fa-minus fa-white'></i></a>";
                                }

                            }

                            return s;
                        }

                    }
                    }
                ]]
            });
        },

        //快速设置
        keySet: function () {
            //        $("#frm_fastset").clearAll();
            $("#frm_fastset").validate();
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

        //初始化该调查表下的所有任务
        initExamineTask: function (_this, examineType) {
            var examineFormType = $(_this).find("input:radio").val();
            Loading.progressShow();
            $.ajax({
                url: getContextPath() + "/surveyExamine/initExamineTask",
                data: {
                    planDetailsId: "${projectPlanDetails.id}",
                    examineFormType: examineFormType,
                    declareRecordId: "${parentPlan.declareRecordId}",
                    examineType: examineType
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        toastr.success("保存成功");

                        $('#div_plan').modal('hide');
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

