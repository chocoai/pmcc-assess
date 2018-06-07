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
                <div class="x_content">
                    <form id="frm_plan" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    计划名称
                                </label>
                                <div class="col-sm-3">
                                    <input type="hidden" name="id" value="${projectPlan.id}">
                                    <label class="form-control">${projectPlan.planName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开始日期<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="开始日期"
                                           value="<fmt:formatDate value="${projectPlan.projectPlanStart}" pattern="yyyy-MM-dd"/>"
                                           id="projectPlanStart" name="projectPlanStart"
                                           data-date-format='yyyy-mm-dd'
                                           class="form-control dbdate">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    结束日期<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="结束日期"
                                           value="<fmt:formatDate value="${projectPlan.projectPlanEnd}" pattern="yyyy-MM-dd"/>"
                                           id="projectPlanEnd" name="projectPlanEnd"
                                           data-date-format='yyyy-mm-dd'
                                           class="form-control dbdate">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    说明
                                </label>
                                <div class="col-sm-11">
                                        <textarea placeholder="说明" name="planRemarks"
                                                  class="form-control">${projectPlan.planRemarks}</textarea>
                                </div>
                            </div>
                        </div>
                        <c:if test="${detailsPlan==1}"> <%--是否允许下级修改计划--%>
                            <input type="hidden" id="detailsPlan" name="detailsPlan" value="${detailsPlan}">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        细分计划
                                    </label>
                                    <div class="col-sm-5">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" id="chk_isNext" name="chk_isNext">
                                            细分计划
                                        </label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        责任人
                                    </label>
                                    <div class="col-sm-5">
                                        <input type="hidden" id="nextApproval" name="nextApproval">
                                        <input type="text" required id="nextApprovalName" name="nextApprovalName"
                                               onclick="nextEmployee()"
                                               class="form-control" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${processInsId!=0}">
                            <input type="hidden" id="opinions" name="opinions" value="0">
                            <input type="hidden" id="bisNext" name="bisNext" value="0">

                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </c:if>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2>${panelTitle}阶段工作计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="panel-body x_content">

                    <span id="toolbarSub">
            <button type="button" class="btn btn-success" onclick="keySet()"
                    data-toggle="modal" href="#divSubDataDicManage"> 快速设置
            </button>
        </span>
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
                                    <label><input type="checkbox" onchange="checkPhase(this)">全部</label>
                                    <c:forEach var="item" items="${projectPhases}">
                                        <label class="chk_phase"><input type="checkbox" class="chk_phase" value="${item.id}">${item.projectPhaseName}</label>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td class="hidden-xs">开始时间</td>
                                <td class="hidden-xs">
                                    <input type="text" required name="planStartDate" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>
                                <td class="hidden-xs">结束时间</td>
                                <td class="hidden-xs">
                                    <input type="text" required name="planEndDate" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>

                            </tr>
                            <tr>
                                <td class="hidden-xs">责任人</td>
                                <td class="hidden-xs">
                                    <input type="hidden" id="fast_executeUserAccount" name="executeUserAccount" class="fast_value">
                                    <input type="text" required id="fast_executeUserName" name="executeUserName" class="form-control" readonly="readonly"
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
    $(function () {
        getPlanItemList();
        loadProjectPlanDetails();
        $("#frm_fastset").validate();
    });
    function ReloadLoadProjectPlanDetails() {
        TableReload("PlanItemListed");
    }
    function loadProjectPlanDetails() {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'projectPhaseName', title: '客户名称'});
        <c:forEach var="item" items="${projectPhases}">
        cols.push({
            field: '${item.phaseForm}', title: '${item.projectPhaseName}', formatter: function (value, row, index) {
                var s = row["${item.phaseForm}executeUserName"];

                return s;
            }
        });
        </c:forEach>


        TableInit("PlanItemListed", "${pageContext.request.contextPath}/planFinancialClaim/getProjectPlanDetailsByPlanApply", cols,
            {
                planId: ${planId},
                projectId: ${projectId}
            }, {
                toolbar: "#toolbarSub",
                onClickRow: function (row, value, field) {
                    $("#frm_planDetails").clearAll();
                    $("#frm_planDetails").validate();
                    $("#planDetailsId").val(row[field + "id"]);
                    $("#projectPhaseName").val(row[field + "projectPhaseName"]);
                    $("#planStartDate").val(formatDate(row[field + "planStartDate"]));
                    $("#planEndDate").val(formatDate(row[field + "planEndDate"]));
                    $("#executeUserAccount").val(row[field + "executeUserAccount"]);
                    $("#executeUserName").val(row[field + "executeUserName"]);
                    $("#planHours").val(row[field + "planHours"]);
                    $("#planRemarks").val(row[field + "planRemarks"]);
                    $('#div_plan').modal({backdrop: 'static', keyboard: false});
                }
            });
    }

    function checkPhase(obj) {
        var isChecked = $(obj).prop("checked");
        if (isChecked) {
            $(".chk_phase").prop("checked", true);
        }
        else {
            $(".chk_phase").prop("checked", false);
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
                formData: JSON.stringify(data)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    //保存完后其他动作
                    toastr.success("保存成功");
                    ReloadLoadProjectPlanDetails();
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


    function getPlanItemList() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/planFinancialClaim/getProjectPlanCustomer",
            data: {
                projectId: ${projectId}
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    var html = "<label><input type='checkbox'  onchange='selectCheckBox(this)' value='-1'>全部</label>";
                    $.each(result.data, function (i, j) {
                        var index = parseInt((i / 10) + 1);
                        if (i % 10 == 0) {
                            html += "<br/><label><input type='checkbox' class='check_group' onchange='selectCheckBox(this)' value='" + index + "'>" + index + "组</label>";
                        }

                        html += "<label class='chk_customer'><input type='checkbox' class='chk_customer group_" + index + "' value='" + j.id + "'>" + j.name + "</label>";
                    });
                    $("#td_customers").html(html);
                } else {
                    Alert("读取客户信息数据失败:" + result.errmsg);
                }


            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function selectCheckBox(obj) {
        var objVal = $(obj).val();
        if ($(obj).prop("checked")) {
            if (objVal == -1) {
                $(".chk_customer").prop("checked", true);
                $(".check_group").prop("checked", true);
            }
            else {
                $(".group_" + objVal).prop("checked", true);
            }
        }
        else {
            if (objVal == -1) {
                $(".chk_customer").prop("checked", false);
                $(".check_group").prop("checked", false);
            }
            else {
                $(".group_" + objVal).prop("checked", false);
            }
        }

    }

    function keySet() {
        $("#frm_fastset").clearAll();
        $('#div_fastSet').modal({backdrop: 'static', keyboard: false});
    }

    function saveFastset() {
        if (!$("#frm_fastset").valid()) {
            return false;
        }
        var data = formParams("frm_fastset");
        //取客户
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

        //取任务
        data["planId"] = "${planId}";
        data["projectId"] = 1;
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/planFinancialClaim/fastSetPlan",
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    //保存完后其他动作
                    ReloadLoadProjectPlanDetails();
                    toastr.success("保存成功");
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


    function commitApply() {
        if (!$("#frm_plan").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_plan");
        data["bisChildren"] = "${bisChildren}";
        data["projectId"] =${projectPlan.projectId};
        var url = "${pageContext.request.contextPath}/planFinancialClaim/saveProjectPlan";
        if ("${processInsId}" != "0") {
            url = "${pageContext.request.contextPath}/planFinancialClaim/submitPlanEdit";
        }
        $.ajax({
            url: url,
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
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