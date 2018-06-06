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
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered"></table>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

</html>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/share/model_employee.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>

<script type="text/javascript">
    var treeGridJson = {};
    $(function () {
        GetPlanItemList();
    });


    function GetPlanItemList() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/getProjectPlan",
            data: {
                planId: ${projectPlan.id}
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                result.rows = sortObjectArray(result.rows, ["_parentId", "sorting"]);
                treeGridJson = result;
                treeGridload();

            },
            error: function (result) {
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
                rownumbers: true,
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
                    {field: "planRemarks", title: "说明", width: "25%", align: "left"}
                ]]
            }
        )
        ;
    }

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        Loading.progressShow();
        var opinions = loadOpation();
        $("#opinions").val(opinions);
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/submitPlanApproval",
            type: "post",
            dataType: "json",
            data: formParams("frm_approval"),
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }

</script>