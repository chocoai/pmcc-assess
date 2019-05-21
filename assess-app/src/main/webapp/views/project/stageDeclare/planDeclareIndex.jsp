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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>工作计划说明</h3>
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
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    说明
                                </label>
                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
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
                </div>
                <div class="x_content">
                    <div class="treeGrid panel-body x_content" style="padding: 0px;">
                        <table id="PlanItemListed" class="table table-bordered" style="max-height: auto;"></table>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${panelTitle}阶段工作计划</h3>
                    <div class="clearfix"></div>
                </div>

            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
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

<div id="div_plan" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="h4_modeTitle">计划编辑</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">

                            <form id="frm_planDetails" class="form-horizontal">
                                <input type="hidden" id="planDetailsId" name="id"/>
                                <input type="hidden" id="pid" name="pid"/>
                                <input type="hidden" id="firstPid" name="firstPid"/>
                                <input type="hidden" id="projectPhaseId" name="projectPhaseId"
                                       value="${projectPhases.get(0).id}"/>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            工作内容<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" placeholder="工作内容" required maxlength="50"
                                                   name="projectPhaseName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            申报表
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <div class="input-group">
                                                <input type="hidden" id="declareFormId" name="declareFormId"/>
                                                <input type="text" id="declareFormName" name="declareFormName"
                                                       readonly="readonly"
                                                       placeholder="申报表" class="form-control"
                                                       onclick="selectDeclareForm(this);">
                                                <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="selectDeclareForm(this);">
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
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            开始时间
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd'
                                                   id="planStartDate" name="planStartDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            结束时间
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd'
                                                   id="planEndDate" name="planEndDate"
                                                   class="form-control dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        责任人
                                    </label>
                                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                        <input type="hidden" placeholder="责任人" maxlength="50"
                                               id="executeUserAccount"
                                               name="executeUserAccount" class="form-control">
                                        <input type="text" placeholder="责任人" maxlength="50" id="executeUserName"
                                               name="executeUserName" class="form-control" readonly="readonly"
                                               onclick="selEmployee()">
                                    </div>
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        责任部门
                                    </label>
                                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                        <input type="hidden" placeholder="责任部门" maxlength="50"
                                               id="executeDepartmentId"
                                               name="executeDepartmentId" class="form-control">
                                        <input type="text" placeholder="责任部门" maxlength="50"
                                               id="executeDepartmentName"
                                               name="executeDepartmentName" class="form-control" onclick="selDept()"
                                               readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            计划工时
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" placeholder="计划工时" data-rule-number='true'
                                                   maxlength="5"
                                                   id="planHours" name="planHours"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            权重占比
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" placeholder="权重占比" data-rule-number='true'
                                                   maxlength="5"
                                                   id="proportion" name="proportion"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        说明
                                    </label>
                                    <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                        <input type="text" placeholder="说明" maxlength="50" id="planRemarks"
                                               name="planRemarks"
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

<div id="tb" style="padding:5px;height:auto;display: none;">
    <div style=" margin-bottom:5px">
        <c:choose>
            <c:when test="${bisComprehensiveAssets}">
                <button type="button" onclick="addCompany()" class="btn btn-success btn-xs">
                    <i class='fa fa-plus fa-white'></i> 新增公司
                </button>
            </c:when>
            <c:otherwise>
                <button type="button" onclick="addfirst()" class="btn btn-success btn-xs">
                    <i class='fa fa-plus fa-white'></i> 新增第一级
                </button>
            </c:otherwise>
        </c:choose>
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

<div id="div_fastSet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">快速设置</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
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
                                        <td class="hidden-xs">开始时间</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" title="开始时间" value="planStartDate">
                                            <input type="text" data-date-format='yyyy-mm-dd'
                                                   class="fast_value form-control dbdate"></td>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning"
                                                    onclick="clearFastValue(this)">清除
                                            </button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">结束时间</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="planEndDate">
                                            <input type="text" data-date-format='yyyy-mm-dd'
                                                   class="fast_value form-control dbdate"></td>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning"
                                                    onclick="clearFastValue(this)">清除
                                            </button>
                                        </td>
                                    </tr>
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
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">责任部门</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="executeDepartmentId">
                                            <input type="hidden" id="fast_executeDepartmentId" class="fast_value">
                                            <input type="text" id="fast_executeDepartmentName" class="form-control"
                                                   onclick="selFastDept()"
                                                   readonly="readonly">
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" id="btn_dept"
                                                    onclick="clearFastValue(this)">清除
                                            </button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">计划工时</td>
                                        <td class="hidden-xs">
                                            <div class="x-valid">
                                                <input type="hidden" class="fast_fileds" value="planHours">
                                                <input type="text" data-rule-number='true' maxlength="5"
                                                       class="form-control fast_value">
                                            </div>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">

                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning"
                                                    onclick="clearFastValue(this)">清除
                                            </button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">

                                        <td class="hidden-xs">权重占比</td>
                                        <td class="hidden-xs">
                                            <div class="x-valid">
                                                <input type="hidden" class="fast_fileds" value="proportion">
                                                <input type="text" data-rule-number='true' maxlength="5"
                                                       class="form-control fast_value">
                                            </div>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning"
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
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select/project.classify.select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/project-plan.js"></script>
<script type="text/javascript">
    //申报表选择
    function selectDeclareForm(_this) {
        var param = {
            modalName: "申报表选择",
            pid: "${empty projectInfo.projectCategoryId?projectInfo.projectTypeId:projectInfo.projectCategoryId}",
            filterKey: [AssessProjectClassifyKey.explore, AssessProjectClassifyKey.case],
            onSelected: function (nodes) {
                $("#declareFormId").val(nodes[0].id);
                $("#declareFormName").val(nodes[0].name);
            }
        };
        assessProjectClassify.select(param);
    }
</script>
