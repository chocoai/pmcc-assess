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
            <!--填写表单-->
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
                                    结束日期1<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="结束日期1"
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
                    <c:forEach items="${dataList.items}" var="item">
                    <table class="table">
                        <tbody>
                        <h2>
                        ${item.provinceName}  ${item.cityName}
                            <c:if test="${!empty item.districtName}">
                                ${item.districtName}
                            </c:if>
                        </h2>
                                <tr>
                                    <th>估价对象编号</th>
                                    <th>权证号</th>
                                    <th>所有权人</th>
                                    <th>座落</th>
                                    <th>最佳利用设置</th>
                                    <th>合并测算序号</th>
                                    <th>证载面积</th>
                                    <th>评估面积</th>
                                    <th>评估方法</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${item.declareRecords}" var="data" varStatus="status">
                                        <tr>
                                            <td id="dataIndex${data.id}">${status.index + 1}</td>
                                            <td id="dataName${data.id}">${data.name}</td>
                                            <td id="dataCreator${data.id}">${data.creator}</td>
                                            <td id="dataSeat${data.id}">座落</td>
                                            <td id="dataBestUse${data.id}">
                                                <select class="form-control">
                                                    <c:forEach items="${bestusedescriptionList}" var="bestUse">
                                                        <option>${bestUse.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td id="dataMerge${data.id}">
                                                <input class="form-control" placeholder="合并测算序号">
                                            </td>
                                            <td id="dataFloorArea${data.id}"><label class="form-control">${data.floorArea}</label></td>
                                            <td id="dataAssessmentArea${data.id}">
                                                <input class="form-control" placeholder="评估面积">
                                            </td>
                                            <td>
                                                <button class="btn btn-success" id="methodID${data.id}" onclick="evaluationmethod(${data.id})">评估方法</button>
                                            </td>
                                            <td>
                                                <button class="btn btn-success" id="btnID${data.id}" onclick="splitEvaluate(${data.id})">拆分</button>
                                            </td>
                                        </tr>
                                </c:forEach>
                        </tbody>
                    </table>
                    </c:forEach>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title">
                    <h2>${panelTitle}阶段工作计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered" style="max-height: auto;"></table>
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

<!-- 评估方法 -->
<div id="divBoxMethod" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" data-height="260">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">评估方法</h4>
            </div>
            <form id="frmMethod" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <c:forEach items="${dataEvaluationMethod}" var="item">
                                    <div class="form-group panel">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label" >
                                                ${item.name}
                                                <input type="hidden" value="${item.id}"   class="form-control" >
                                            </label>

                                            <label class="col-sm-9 control-label" >
                                                适用<input type="radio" name="apply" onclick="applyMethodA('${item.id}','${item.name}')" >
                                                不适用<input type="radio" name="apply" onclick="applyMethodB('${item.id}','${item.name}')" >
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group" id="applyTemplateView${item.id}">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                适用原因模板
                                                <span class="input-group-btn">
                                            <button type="button" id="applyTemplate${item.id}" class="btn btn-primary">选择模板</button>
                                            </span>
                                            </label>
                                            <div class="col-sm-9">
                                            <textarea required="required" placeholder="请填写适用原因" class="form-control"  name="applicableReason">

                                            </textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group" id="applyNoTemplateView${item.id}">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                适用不原因模板
                                                <span class="input-group-btn">
                                            <button type="button" id="applyNoTemplate${item.id}" class="btn btn-primary">选择模板</button>
                                            </span>
                                            </label>
                                            <div class="col-sm-9">
                                            <textarea required="required" placeholder="请填写不适用原因" class="form-control" name="applicableReasonNo">

                                            </textarea>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            评估思路
                                        </label>
                                        <label class="col-sm-9 control-label">
                                            <input type="hidden" id="methodName">
                                            <input type="hidden" id="methodID">
                                            <label class="btn btn-success" onclick="evaluationthinking()">思路选择</label>
                                        </label>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 评估思路 -->
<div id="divBoxThink" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" data-height="190">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">评估思路</h4>
            </div>
            <form id="frmThink" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="modal-title" id="ThinkNameSHOW"></h3>
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default" onclick="evaluationthinkingClose()">
                        关闭
                    </button>
                    <button type="button" class="btn btn-primary" >
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="tb" style="padding:5px;height:auto;display: none;">
    <div style=" margin-bottom:5px">
        <button type="button" onclick="addfirst()" class="btn btn-success btn-xs">
            <i class='fa fa-plus fa-white'></i> 新增第一级
        </button>
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
                <h4 class="modal-title">快速设置</h4>
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
<%@include file="/views/share/model_employee.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function () {
        <c:forEach items="${dataEvaluationMethod}" var="item">
            $("#applyNoTemplateView"+${item.id}).hide();
            $("#applyTemplateView"+${item.id}).hide();
        </c:forEach>
    });
    function applyMethodA(id,name) {
        $("#methodID").val(id);
        $("#methodName").val(name);
        $("#applyNoTemplateView"+id).hide();
        $("#applyTemplateView"+id).show();
    }
    function applyMethodB(id,name) {
        $("#methodID").val(id);
        $("#methodName").val(name);
        $("#applyNoTemplateView"+id).show();
        $("#applyTemplateView"+id).hide();
    }
    //评估方法
    function evaluationmethod(id) {
        $("#frmMethod").clearAll();
        $("#divBoxMethod").modal();//显示
    }

    //评估思路
    function evaluationthinking() {
        $("#frmThink").clearAll();
        document.getElementById("ThinkNameSHOW").innerText = document.getElementById("methodName").value;
//        $("#ThinkNameSHOW").val($("#methodName").val());
        $("#divBoxThink").modal();//显示
    }

    //评估思路 关闭
    function evaluationthinkingClose() {
        $("#divBoxThink").hide();
    }
    
    function splitEvaluate(id) {
        var btnID = document.getElementById("btnID"+id);
        var parentElement = btnID.parentNode.parentNode;
        var trElement = document.createElement("tr");
        var len = 10;
        var dataIndex = document.getElementById("dataIndex"+id).innerText;
        var dataName = document.getElementById("dataName"+id).innerText;
        var dataCreator = document.getElementById("dataCreator"+id).innerText;
        var dataSeat = document.getElementById("dataSeat"+id).innerText;
        var dataFloorArea = document.getElementById("dataFloorArea"+id).innerText;
        var dataBestUse = document.getElementById("dataBestUse"+id).innerHTML;
        var dataMerge = document.getElementById("dataMerge"+id).innerHTML;
        for (var i = 0;i< len;i++){
            var tdElement = document.createElement("td");
            var j =0;
            if (i==j++){
                tdElement.innerHTML = dataIndex;
            }else if (i==j++){
                tdElement.innerHTML = dataName;
            }else if (i==j++){
                tdElement.innerHTML = dataCreator;
            }else if (i==j++){
                tdElement.innerHTML = dataSeat;
            }else if (i==j++){
                tdElement.innerHTML = dataBestUse;
            }else if (i==j++){
                tdElement.innerHTML = dataMerge;
            }else if (i==j++){
                var inputElement = document.createElement("input");
                inputElement.setAttribute("class","form-control");
                inputElement.setAttribute("placeholder","证载面积");
                tdElement.appendChild(inputElement);
            }else if (i==j++){
                var inputElement = document.createElement("input");
                inputElement.setAttribute("class","form-control");
                inputElement.setAttribute("placeholder","评估面积");
                tdElement.appendChild(inputElement);
            }else if (i==j++){

            }else if (i==j++){

            }else if (i==j++){

            }
            trElement.appendChild(tdElement);
        }
        parentElement.parentNode.insertBefore(trElement,parentElement);
    }
</script>
<script type="text/javascript">
    var treeGridJson = {};
    var treeGridJsonData = {};
    $(function () {
        getPlanItemList();
        $("#projectPhaseId").select2();

        DateUtils.dateSectionChoose($("#projectPlanStart"), $("#projectPlanEnd"));
        DateUtils.dateSectionChoose($("#planStartDate"), $("#planEndDate"));
    });

    function nextEmployee() {
        var thisUser = "";
        if ($("#nextApproval").val()) {
            var userName = $("#nextApprovalName").val();
            var userAccount = $("#nextApproval").val();
            thisUser = userName + "_" + userAccount;
        }
        loadSelectEmployee(1, thisUser, true, function (data) {
            if (data.account) {
                $("#nextApproval").val(data.account);
                $("#nextApprovalName").val(data.name);
            }
            else {
                $("#nextApproval").val("");
                $("#nextApprovalName").val("");
            }
        });
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

    //选部门控件
    function selDept() {
        loadSelectDept(1, $("#executeDepartmentId").val(), false, function (node) {
            $("#executeDepartmentId").val(node[0].id);
            $("#executeDepartmentName").val(node[0].text);
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

    //选部门控件
    function selFastDept() {
        loadSelectDept(1, $("#fast_executeDepartmentId").val(), false, function (node) {
            $("#fast_executeDepartmentId").val(node[0].id);
            $("#fast_executeDepartmentName").val(node[0].text);
        });
    }

    function clearFastValue(obj) {
        $(obj).closest("tr").find(".fast_value").val("");
        var objId = $(obj).attr("id");
        if (objId == "btn_user") {
            $("#fast_executeUserName").val("");
        }
        if (objId == "btn_dept") {
            $("#fast_executeDepartmentName").val("");
        }
    }

    function savePlanDtails() {
        if (!$("#frm_planDetails").valid()) {
            return false;
        }
        var data = formParams("frm_planDetails");
        data["planId"] =${projectPlan.id};
        data["workStageId"] =${projectPlan.workStageId};
        data["projectId"] =${projectPlan.projectId};
        data["projectWorkStageId"] =${projectPlan.workStageId};
        //将最新的列表顺序存入表中
        var detailsSoring = [];
        $.each(treeGridJsonData.rows, function (i, j) {
            detailsSoring.push({
                key: j.id,
                value: j.sorting,
                explain: j.pid
            });
        });
        data["detailsSoring"] = JSON.stringify(detailsSoring);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/saveProjectPlanDetails",
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
                    result = result.data;
                    result.rows = sortObjectArray(result.rows, ["sorting"]);
                    treeGridJson = result;
                    treeGridJsonData = $.extend(true, {}, result);
                    treeGridload();
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

    function addfirst() {
        $("#frm_planDetails").clearAll();
        $("#frm_planDetails").validate();
        $("#pid").val(0);
        $("#firstPid").val(0);
        $("#projectPhaseId").val(0);
        $("#planDetailsId").val(0);
        $("#projectPhaseId").select2();
        $('#div_plan').modal({backdrop: 'static', keyboard: false});
    }
    function addPlan(id) {

        $("#frm_planDetails").clearAll();
        $("#frm_planDetails").validate();
        var row = $('#PlanItemListed').treegrid('find', id);
        $("#pid").val(row["id"]);
        $("#firstPid").val(row["firstPid"]);
        $("#projectPhaseId").val(row["projectPhaseId"]);
        $("#planDetailsId").val(0);
        $("#projectPhaseId").select2();
        $('#div_plan').modal({backdrop: 'static', keyboard: false});

    }

    function editPlan(id) {
        var row = $('#PlanItemListed').treegrid('find', id);
        $("#frm_planDetails").clearAll();
        $("#frm_planDetails").validate();
        $("#frm_planDetails").initForm(row);
        $("#planDetailsId").val(row["id"]);
        $("#planStartDate").val(formatDate(row["planStartDate"]));
        $("#planEndDate").val(formatDate(row["planEndDate"]));
        $("#projectPhaseId").select2().val(row.projectPhaseId).trigger("change");
        $('#div_plan').modal({backdrop: 'static', keyboard: false});
    }
    function deletePlan(id) {
        Alert("删除后将不可恢复,确认删除？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/ProjectPlan/deletePlan",
                data: {
                    planDetailsId: id,
                    planId:${projectPlan.id}
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();

                    if (result.ret) {
                        result.data.rows = sortObjectArray(result.data.rows, ["sorting"]);
                        treeGridJson = result.data;
                        treeGridJsonData = $.extend(true, {}, result.data);
                        treeGridload();
                    } else {
                        Alert("删除数据失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }


    function getPlanItemList() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/getProjectPlanDetailsByPlanApply",
            data: {
                planId: ${planId}
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                result.rows = sortObjectArray(result.rows, ["sorting"]);
                treeGridJson = result;
                treeGridJsonData = $.extend(true, {}, result);
                treeGridload();

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
        if (!$("#frm_fastset").valid()) {
            return false;
        }

        //将最新的列表顺序存入表中
        var detailsSoring = [];
        $.each(treeGridJsonData.rows, function (i, j) {
            detailsSoring.push({
                key: j.id,
                value: j.sorting,
                explain: j.pid
            });
        });
        var detailsSoring = JSON.stringify(detailsSoring);
        var objArray = [];
        $.each($(".fast_tr"), function (i, j) {
            objArray.push({
                fastFileds: $(j).find(".fast_fileds").val(),
                fastValue: $(j).find(".fast_value").val(),
                fastRange: $(j).find(".fast_range").val()
            });
        });

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/fastSetPlan",
            data: {
                fields: JSON.stringify(objArray),
                planId:${planId},
                detailsSoring: detailsSoring
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    //保存完后其他动作
                    toastr.success("保存成功");
                    result = result.data;
                    result.rows = sortObjectArray(result.rows, ["sorting"]);
                    treeGridJson = result;
                    treeGridJsonData = $.extend(true, {}, result);
                    treeGridload();
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
                    editPlan(row.id);
                },
            onLoadSuccess: function () {
                    $(".tooltips").tooltip();
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
                    {
                        field: "executeDepartmentName",
                        title: "责任部门",
                        width: "10%",
                        align: "center"
                    },
                    {
                        field: "proportion",
                        title: "权重占比",
                        width: "5%",
                        align: "center"
                    },
                    {field: "planRemarks", title: "说明", width: "15%", align: "left"},
                    {field: "firstPid", title: "firstPid", align: "center", hidden: true},
                    {field: "sorting", title: "sorting", align: "center", hidden: true},
                    {field: "id", title: "PlanItemId", align: "center", hidden: true},
                    {field: "projectPhaseId", title: "projectPhaseId", align: "center", hidden: true},
                    {
                        field: 'workStages', title: '操作', width: '10%', formatter: function (value, row) {
                        if (row.bisEnable) {
                            var s = "";
                            if ("${planDetailsIds}") {
                                //如果不为空则说明是子计划，如果为子计划，则只允许新增项或编辑当前项
                                var planDetailsId = "${planDetailsIds}";
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
            }
        )
        ;
    }

    function move(o) {//将此方法加入上下移的按钮事件即可
        Loading.progressShow();
        var node = $("#PlanItemListed").treegrid("getSelected");
        $.each(treeGridJsonData.rows, function (i, j) {

            if (j.id == node.id) {
                if (o == "up") {
                    if (i - 1 >= 0) {
                        if (treeGridJsonData.rows[i - 1].pid == treeGridJsonData.rows[i].pid) {
                            treeGridJsonData.rows[i - 1].sorting = treeGridJsonData.rows[i - 1].sorting + 1;
                            treeGridJsonData.rows[i].sorting = treeGridJsonData.rows[i].sorting - 1;
                        }
                    }
                }
                else {
                    if (i + 1 < treeGridJsonData.rows.length) {
                        if (treeGridJsonData.rows[i + 1]._parentId == treeGridJsonData.rows[i]._parentId) {
                            treeGridJsonData.rows[i].sorting = treeGridJsonData.rows[i].sorting + 1;
                            treeGridJsonData.rows[i + 1].sorting = treeGridJsonData.rows[i + 1].sorting - 1;
                        }
                    }
                }

            }
        });

        treeGridJsonData.rows = sortObjectArray(treeGridJsonData.rows, ["sorting"]);
        treeGridJson = jQuery.extend(true, {}, treeGridJsonData);
        $('#PlanItemListed').treegrid('loadData', treeGridJson);
        Loading.progressHide();
    }

    function commitApply() {
        if ($("#chk_isNext").is(':checked')) {
            $("#nextApprovalName").attr("required", true);
            $("#detailsPlan").val(1);
        }
        else {
            $("#nextApprovalName").attr("required", false);
            $("#detailsPlan").val(0);
        }

        if (!$("#frm_plan").valid()) {
            return false;
        }
        //将最新的列表顺序存入表中
        var detailsSoring = [];
        $.each(treeGridJsonData.rows, function (i, j) {
            detailsSoring.push({
                key: j.id,
                value: j.sorting,
                explain: j.pid
            });
        });
        var data = formParams("frm_plan");
        data["detailsSoring"] = JSON.stringify(detailsSoring);
        data["bisChildren"] = "${bisChildren}";
        data["projectId"] =${projectPlan.projectId};
        Loading.progressShow();
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