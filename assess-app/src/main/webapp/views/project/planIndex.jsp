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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${panelTitle}阶段工作计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_plan" class="form-horizontal">
                        <input type="hidden" id="bisChildren" value="${bisChildren}">
                        <input type="hidden" id="planId" name="id" value="${projectPlan.id}">
                        <input type="hidden" id="workStageId" value="${projectPlan.workStageId}">
                        <input type="hidden" id="planDetailsIds" value="${planDetailsIds}">
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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${panelTitle}阶段工作计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered" style="max-height: auto;"></table>
                </div>
            </div>
            <div class="x_panel">
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
                                            所属工作内容
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="projectPhaseId" id="projectPhaseId" class="form-control search-select select2">
                                                <option value="">-选择-</option>
                                                <c:forEach var="item" items="${projectPhases}">
                                                    <option value="${item.id}">${item.projectPhaseName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            工作内容
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="hidden" id="planDetailsId" name="id"/>
                                            <input type="hidden" id="pid" name="pid"/>
                                            <input type="hidden" id="firstPid" name="firstPid"/>
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
                                    <label class="col-sm-2 control-label">
                                        责任部门
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="hidden" placeholder="责任部门" maxlength="50" id="executeDepartmentId" name="executeDepartmentId" class="form-control">
                                        <input type="text" placeholder="责任部门" maxlength="50" id="executeDepartmentName" name="executeDepartmentName" class="form-control" onclick="selDept()"
                                               readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            计划工时
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="计划工时" data-rule-number='true' maxlength="5" id="planHours" name="planHours"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            权重占比
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="权重占比" data-rule-number='true' maxlength="5" id="proportion" name="proportion"
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

<%@include file="/views/share/project/projectPlanSet.jsp" %>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/project-plan.js"></script>