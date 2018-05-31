<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>

            <div class="x_panel">
                <div class="x_title">
                    <h2>${panelTitle}阶段工作计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered"></table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="commit_btn" class="btn btn-success" onclick="commitApply();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>


<div id="tb" style="padding:5px;height:auto;display: none;">
    <div style=" margin-bottom:5px">
        <button type="button" onclick="keySet()" class="btn btn-warning btn-xs">
            <i class='fa fa-fire fa-white'></i> 快速设置
        </button>
    </div>
</div>

<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="h4_modeTitle">计划编辑</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <form id="frm_planDetails" class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            工作内容
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="hidden" id="planDetailsId" name="id"/>
                                            <input type="text" placeholder="计划名称" required maxlength="50" id="projectPhaseName" name="projectPhaseName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开始时间
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd' id="planStartDate" name="planStartDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            结束时间
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd' id="planEndDate" name="planEndDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        责任人
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="hidden" placeholder="责任人" maxlength="50" id="executeUserAccount" name="executeUserAccount" class="form-control">
                                        <input type="text" placeholder="责任人" maxlength="50" id="executeUserName" name="executeUserName" class="form-control" readonly="readonly"
                                               onclick="selEmployee()">
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            计划工时
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="计划工时" data-rule-number='true' maxlength="5" id="planHours" name="planHours"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        说明
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="text" placeholder="说明" maxlength="50" id="planRemarks" name="planRemarks"
                                               class="form-control">
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
                <button type="button" class="btn btn-primary" onclick="savePlanDtails()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="div_fastSet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">快速设置</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="frm_fastset" class="form-horizontal">
                        <table class="table table-striped table-bordered table-hover table-bordered" id="sample-table-2">
                            <tbody>
                            <tr class="fast_tr">
                                <td class="hidden-xs">客户</td>
                                <td colspan="3" class="hidden-xs" id="td_customers">
                                </td>
                            </tr>
                            <tr class="fast_tr">
                                <td class="hidden-xs">工作事项</td>
                                <td colspan="3" class="hidden-xs">
                                    <label><input type="checkbox" id="chk_phase_all" value="0" onchange="checkPhase()">全部</label>
                                    <c:forEach var="item" items="${projectPhases}">
                                        <label class="chk_phase"><input type="checkbox" class="chk_phase" value="${item.id}">${item.projectPhaseName}</label>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td class="hidden-xs">开始时间</td>
                                <td class="hidden-xs">
                                    <input type="text" name="planStartDate" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>
                                <td class="hidden-xs">结束时间</td>
                                <td class="hidden-xs">
                                    <input type="text" name="planEndDate" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>

                            </tr>
                            <tr>
                                <td class="hidden-xs">责任人</td>
                                <td class="hidden-xs">
                                    <input type="hidden" id="fast_executeUserAccount" name="executeUserAccount" class="fast_value">
                                    <input type="text" id="fast_executeUserName" name="executeUserName" class="form-control" readonly="readonly"
                                           onclick="selFastEmployee()">
                                <td class="hidden-xs">计划工时</td>
                                <td class="hidden-xs">
                                    <div class="x-valid">
                                        <input type="text" data-rule-number='true' name="planHours" maxlength="5" class="form-control fast_value">
                                    </div>

                            </tr>
                            </tbody>
                        </table>
                    </form>
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

</html>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/share/model_employee.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>

<script type="text/javascript">
    var treeGridJsonData = {};
    $(function () {
        getPlanItemList();
    });
    function checkPhase() {
        var isChecked = $('#chk_phase_all').prop("checked");
        if (isChecked) {
            $(".chk_phase").hide();
        }
        else {
            $(".chk_phase").show();
        }
        isChecked = $('#chk_customer_all').prop("checked");
        if (isChecked) {
            $(".chk_customer").hide();
        }
        else {
            $(".chk_customer").show();
        }
    }
    function selEmployee() {
        var thisUser = "";
        if ($("#executeUserAccount").val()) {
            var userName = $("#executeUserName").val();
            var userAccount = $("#executeUserAccount").val();
            thisUser = userName + "_" + userAccount;
        }
        loadSelectEmployee(1, thisUser, true, function (data) {
            if (data.account) {
                $("#executeUserAccount").val(data.account);
                $("#executeUserName").val(data.name);

                //获取人员部门信息
                $.ajax({
                    url: "${pageContext.request.contextPath}/RpcErpService/getDepartmentByUserAccount",
                    type: "get",
                    data: {userAccount: data.account},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            $("#executeDepartmentId").val(result.data.id);
                            $("#executeDepartmentName").val(result.data.name);
                        }
                    }
                })
            }
            else {
                $("#executeUserAccount").val("");
                $("#executeUserName").val("");
            }
        });
    }


    function selFastEmployee() {
        loadSelectEmployee(1, "", true, function (data) {
            if (data.account) {
                $("#fast_executeUserAccount").val(data.account);
                $("#fast_executeUserName").val(data.name);

                //获取人员部门信息
                $.ajax({
                    url: "${pageContext.request.contextPath}/RpcErpService/getDepartmentByUserAccount",
                    type: "get",
                    data: {userAccount: data.account},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            $("#fast_executeDepartmentId").val(result.data.id);
                            $("#fast_executeDepartmentName").val(result.data.name);
                        }
                    }
                })
            }
            else {
                $("#fast_executeUserAccount").val("");
                $("#fast_executeUserName").val("");
            }
        });
    }


    function savePlanDtails() {
        if (!$("#frm_planDetails").valid()) {
            return false;
        }
        var data = formParams("frm_planDetails");
        $.ajax({
            url: "${pageContext.request.contextPath}/planFinancialClaim/updateProjectPlanDetails",
            data: {
                ds: JSON.stringify(data),
                planId:${projectPlan.id}
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    //保存完后其他动作
                    toastr.success("保存成功");
                    getPlanItemList();
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


    function editPlan(id) {
        var row = $('#PlanItemListed').treegrid('find', id);
        $("#frm_planDetails").clearAll();
        $("#frm_planDetails").validate();
        $("#frm_planDetails").initForm(row);
        $("#planDetailsId").val(row["id"]);
        $("#planStartDate").val(formatDate(row["planStartDate"]));
        $("#planEndDate").val(formatDate(row["planEndDate"]));
        $('#div_plan').modal({backdrop: 'static', keyboard: false});
    }


    function getPlanItemList() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/planFinancialClaim/getProjectPlanDetailsByPlanApply",
            data: {
                planId: ${planId},
                projectId: 1
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                // result.rows = sortObjectArray(result.rows, ["sorting"]);
                treeGridJson = result;
                treeGridJsonData = result;
                treeGridload();
                var html = "<label><input type='checkbox' id='chk_customer_all' value='0'>全部</label>";
                $.each(result.rows, function (i, j) {
                    if (j.pid == 0) {
                        if (j.executeUserName == "" || j.executeUserName == null) {
                            html += "<label class='chk_customer'><input type='checkbox' class='chk_customer' value='" + j.id + "'>" + j.projectPhaseName + "</label>";
                        }
                        else {
                            html += "<label class='chk_customer' style='color: red'><input type='checkbox' class='chk_customer' value='" + j.id + "'>" + j.projectPhaseName + "</label>";
                        }
                    }
                });
                $("#td_customers").html(html);

            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });


    }

    function keySet() {
//        $("#frm_fastset").clearAll();
        $("#frm_fastset").validate();
        $('#div_fastSet').modal({backdrop: 'static', keyboard: false});

    }

    function saveFastset() {
        var data = formParams("frm_fastset");
        //取客户
        var customerAll = $('#chk_customer_all').prop("checked");
        if (customerAll) {
            data["customerList"] = "";
        }
        else {
            var customerList = "";
            $.each($(".chk_customer"), function (p, q) {
                if ($(q).prop("checked")) {
                    customerList += $(q).val() + ",";
                }
            });
            if (customerList == "") {
                Alert("请至少选择一个客户");
                return false;
            }
            data["customerList"] = customerList;
        }

        var taskAll = $('#chk_phase_all').prop("checked");
        if (taskAll) {
            data["phaseList"] = "";
        }
        else {
            var phaseList = "";
            $.each($(".chk_phase"), function (p, q) {
                if ($(q).prop("checked")) {
                    phaseList += $(q).val() + ",";
                }
            });
            if (phaseList == "") {
                Alert("请至少选择一个任务");
                return false;
            }
            data["phaseList"] = phaseList;
        }

        //取任务
        data["planId"] = "${planId}";
        data["projectId"] = 1;
        // Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/planFinancialClaim/fastSetPlan",
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                // Loading.progressHide();

                if (result.ret) {
                    //保存完后其他动作
                    toastr.success("保存成功");
                    getPlanItemList();
                    $('#div_fastSet').modal('hide');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }

            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });


    }
    function treeGridload() {
        $("#PlanItemListed").treegrid({
            data: treeGridJson,
            idField: 'id',
            treeField: 'projectPhaseName',
            datatype: 'json',
            lines: true,
            width: 'auto',
            toolbar: "#tb",
            rownumbers: true,
            onDblClickRow: function (row) {
                if (row.pid > 0) {
                    editPlan(row.id);
                }
            },
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                $('#PlanItemListed').treegrid('collapseAll')
            },

            columns: [[
                {field: "projectPhaseName", title: "工作内容", width: "20%", align: "left"},
                {
                    field: "planStartDate",
                    title: "开始时间",
                    width: "10%",
                    align: "center",
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {
                    field: "planEndDate",
                    title: "结束时间",
                    width: "10%",
                    align: "center",
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {
                    field: "planHours",
                    title: "计划工时",
                    width: "5%",
                    align: "center"
                },
                {
                    field: "executeUserName",
                    title: "责任人",
                    width: "10%",
                    align: "center"
                },
                {field: "planRemarks", title: "说明", width: "25%", align: "left"},
                {field: "id", title: "PlanItemId", align: "center", hidden: true},
                {
                    field: 'workStages', title: '操作', width: '10%', formatter: function (value, row) {
                    if (row.bisEnable) {
                        var s = "";
                        if (row.pid > 0) {
                            s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";

                        }
                        return s;
                    }

                }
                }
            ]]
        });
    }


    function commitApply() {
        var url = "${pageContext.request.contextPath}/ProjectPlan/saveProjectPlan";
        if ("${processInsId}" != "0") {
            url = "${pageContext.request.contextPath}/ProjectPlan/submitPlanEdit";
        }
        $.ajax({
            url: url,
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }


</script>