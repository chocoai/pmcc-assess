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
                    <h3>${panelTitle}阶段工作计划</h3>
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
                        <c:if test="${processInsId!=0}">
                            <input type="hidden" id="opinions" name="opinions" value="0">
                            <input type="hidden" id="bisNext" name="bisNext" value="0">

                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </c:if>
                    </form>
                    <div class="treeGrid panel-body x_content" style="padding: 0px;">
                        <table id="PlanItemListed" class="table table-bordered" style="max-height: auto;"></table>
                    </div>
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


<div id="tb" style="padding:5px;height:auto;display: none;">
    <div style=" margin-bottom:5px">
        <button type="button" onclick="move('up')" class="btn btn-primary btn-xs">
            <i class='fa fa-arrow-up fa-white'></i> 上移
        </button>
        <button type="button" onclick="move('down')" class="btn btn-primary btn-xs">
            <i class='fa fa-arrow-down fa-white'></i> 下移
        </button>
        <button type="button" onclick="keySet()" class="btn btn-warning btn-xs">
            <i class='fa fa-fire fa-white'></i> 快速设置
        </button>
    </div>
</div>

<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="h4_modeTitle">计划编辑</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <form id="frm_planDetails" class="form-horizontal">
                                <input type="hidden" name="id">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所属工作内容
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="projectPhaseId" id="projectPhaseId" placeholder="所属工作内容" class="form-control search-select select2">
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
                                            <input type="hidden" id="pid" name="pid"/>
                                            <input type="hidden" id="firstPid" name="firstPid"/>
                                            <input type="text" placeholder="工作内容" required maxlength="50" id="projectPhaseName" name="projectPhaseName"
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

<div id="div_fastSet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">快速设置</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <form id="frm_fastset" class="form-horizontal">
                                <table class="table table-striped table-bordered table-hover table-bordered" id="sample-table-2">
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
                                        <td class="hidden-xs">开始时间</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" title="开始时间" value="planStartDate">
                                            <input type="text" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">结束时间</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="planEndDate">
                                            <input type="text" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">责任人</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="executeUserAccount">
                                            <input type="hidden" id="fast_executeUserAccount" class="fast_value">
                                            <input type="text" id="fast_executeUserName" class="form-control" readonly="readonly"
                                                   onclick="selFastEmployee()">
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" id="btn_user" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">责任部门</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="executeDepartmentId">
                                            <input type="hidden" id="fast_executeDepartmentId" class="fast_value">
                                            <input type="text" id="fast_executeDepartmentName" class="form-control" onclick="selFastDept()"
                                                   readonly="readonly">
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" id="btn_dept" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">计划工时</td>
                                        <td class="hidden-xs">
                                            <div class="x-valid">
                                                <input type="hidden" class="fast_fileds" value="planHours">
                                                <input type="text" data-rule-number='true' maxlength="5" class="form-control fast_value">
                                            </div>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">

                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">

                                        <td class="hidden-xs">权重占比</td>
                                        <td class="hidden-xs">
                                            <div class="x-valid">
                                                <input type="hidden" class="fast_fileds" value="proportion">
                                                <input type="text" data-rule-number='true' maxlength="5" class="form-control fast_value">
                                            </div>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
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

</html>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/project-plan.js?v=${assessVersion}"></script>
