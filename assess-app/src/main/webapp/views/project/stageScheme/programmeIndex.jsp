<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <h1>
                        ${projectInfo.projectName}-方案编制
                    </h1>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>

            <c:forEach items="${areaGroups}" var="item">
                <div class="x_panel area_panel">
                    <input type="hidden" name="areaGroupId" value="${item.id}">
                    <div class="x_title collapse-link" onclick="programme.loadJudgeObjectList(this);">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h3>
                            <label>${item.areaName}</label>
                            <small>
                                <c:if test="${item.bisMerge eq true}">
                                    <button class="btn btn-xs btn-warning btn-area-merge-cancel">
                                        取消合并
                                    </button>
                                </c:if>
                                <c:if test="${item.bisMerge ne true}">
                                    <button class="btn btn-xs btn-warning btn-area-merge">
                                        合并
                                    </button>
                                </c:if>
                            </small>
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content collapse">
                        <form id="frmJudgeObject${item.id}" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        评估基准日<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" name="valueTimePoint" required="required" placeholder="评估基准日"
                                               data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                               readonly="readonly" pattern='yyyy-MM-dd'
                                               value="<fmt:formatDate value="${empty item.valueTimePoint?projectInfo.valuationDate:item.valueTimePoint}"
                                   pattern="yyyy-MM-dd"/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        基准日说明<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" name="timePointExplain" required="required"
                                               placeholder="基准日说明" class="form-control"
                                               value="${item.timePointExplain}">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select name="entrustmentPurpose" class="form-control" required>
                                            <option value="">-请选择-</option>
                                            <c:forEach items="${entrustmentPurposes}" var="entrustmentPurpose">
                                                <c:choose>
                                                    <c:when test="${entrustmentPurpose.id eq item.entrustPurpose}">
                                                        <option value="${entrustmentPurpose.id}"
                                                                selected="selected">${entrustmentPurpose.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${entrustmentPurpose.id eq projectInfo.entrustPurpose}">
                                                            <option value="${entrustmentPurpose.id}"
                                                                    selected="selected">${entrustmentPurpose.name}</option>
                                                        </c:if>
                                                        <c:if test="${entrustmentPurpose.id ne projectInfo.entrustPurpose}">
                                                            <option value="${entrustmentPurpose.id}">${entrustmentPurpose.name}</option>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的描述<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" name="remarkEntrustPurpose" required="required"
                                               placeholder="委托目的描述" class="form-control"
                                               value="${empty item.remarkEntrustPurpose?projectInfo.remarkEntrustPurpose:item.remarkEntrustPurpose}">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">

                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select name="valueDefinition" class="form-control" required>
                                            <option value="">-请选择-</option>
                                            <c:forEach items="${valueTypes}" var="valueDefinition">
                                                <c:choose>
                                                    <c:when test="${valueDefinition.id eq item.valueDefinition}">
                                                        <option value="${valueDefinition.id}"
                                                                selected="selected">${valueDefinition.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${valueDefinition.id eq projectInfo.valueType}">
                                                            <option value="${valueDefinition.id}"
                                                                    selected="selected">${valueDefinition.name}</option>
                                                        </c:if>
                                                        <c:if test="${valueDefinition.id ne projectInfo.valueType}">
                                                            <option value="${valueDefinition.id}">${valueDefinition.name}</option>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值类型描述
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="valueDefinitionDesc" placeholder="价值类型描述" value="${item.valueConnotationDesc}">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值内涵<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select name="valueConnotation" class="form-control search-select select2"
                                                multiple="multiple" required>
                                            <option value="">-请选择-</option>
                                            <c:forEach items="${valueConnotations}" var="valueConnotation">
                                                <option value="${valueConnotation.id}">${valueConnotation.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值内涵描述
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="valueConnotationDesc" placeholder="价值内涵描述" value="${item.valueConnotationDesc}">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">

                            </div>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th style="width: 5%">编号</th>
                                        <%--<th style="width: 10%">权证号</th>--%>
                                    <th style="width: 5%">所有权人</th>
                                    <th style="width: 15%">坐落</th>
                                    <th style="width: 10%">证载用途</th>
                                    <th style="width: 10%">实际用途</th>
                                    <th style="width: 10%">设定用途</th>
                                    <th style="width: 10%">最佳利用描述</th>
                                    <th style="width: 5%">证载面积</th>
                                    <th style="width: 5%">评估面积</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </form>
                        <script type="text/javascript">
                            $(function () {
                                programme.setValueConnotation(${item.id}, '${item.valueConnotation}');
                            })
                        </script>
                    </div>
                </div>
            </c:forEach>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>他项权利</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_inventory_right_list">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button class="btn btn-warning" onclick="programme.saveProgrammeAll();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="commit_btn" class="btn btn-success" onclick="programme.submitProgramme();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="divBoxMethodExtend" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">评估方法</h3>
            </div>
            <div class="modal-body">
                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                        <c:forEach items="${dataDicMethodList}" var="item" varStatus="status">
                            <li role="presentation" ${status.index==0?'class="active"':''} >
                                <a href="#tab_content${item.id}" id="tab${item.id}" role="tab"
                                   data-toggle="tab" aria-expanded="true">${item.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <c:forEach items="${dataDicMethodList}" var="method" varStatus="status">
                            <div role="tabpanel" class="tab-pane fade ${status.index==0?'active in':''} "
                                 id="tab_content${method.id}"
                                 aria-labelledby="home-tab">
                                <form id="frm_method_${method.id}" class="form-horizontal" data-name="${method.name}">
                                    <input type="hidden" name="id" value="0">
                                    <input type="hidden" name="judgeObjectId">
                                    <input type="hidden" name="name" value="${method.name}">
                                    <input type="hidden" name="methodType" value="${method.id}">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <div class="col-sm-10 col-sm-offset-2">
                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,true)"
                                                       name="bisApplicable" id="rdoApplicable${method.id}" value="true">
                                                <label for="rdoApplicable${method.id}">适用</label>
                                                </span>

                                                <span class="radio-inline">
                                                <input type="radio" onclick="applicableChange(this,false)"
                                                       name="bisApplicable" id="rdoNotApplicable${method.id}"
                                                       value="false">
                                                <label for="rdoNotApplicable${method.id}">不适用</label>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="applicable" style="display: none;">
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    方法模板
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-4">
                                                        <select class="form-control" name="methodTemplate"
                                                                onchange="evaluationMethodChange(this);">
                                                            <option value="">-请选择-</option>
                                                            <c:forEach items="${evaluationMethodMap.get(method.id)}"
                                                                       var="evaluationMethod">
                                                                <option value="${evaluationMethod.id}"
                                                                        data-applicable="${evaluationMethod.applicableReason}"
                                                                        data-not-applicable="${evaluationMethod.notApplicableReason}">
                                                                        ${evaluationMethod.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label class="col-sm-2 control-label">
                                                    方法适用原因<span class="symbol required"></span>
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                        <textarea required placeholder="方法适用原因" name="applicableReason"
                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="applicableReason-field">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="not-applicable" style="display: none;">
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    方法不适用原因<span class="symbol required"></span>
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                        <textarea required placeholder="方法不适用原因" name="notApplicableReason"
                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="notApplicableReason-field"></div>
                                        </div>

                                    </div>
                                    <div class="well thinkingWell" style="display: none;">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">
                                                思路模板
                                            </label>
                                            <div class="x-valid">
                                                <div class="col-sm-4">
                                                    <select class="form-control" name="thinkingTemplate"
                                                            onchange="evaluationThinkingChange(this);">
                                                        <option value="">-请选择-</option>
                                                        <c:forEach items="${evaluationThinkingMap.get(method.id)}"
                                                                   var="evaluationThinking">
                                                            <option value="${evaluationThinking.id}"
                                                                    data-templateContent="${evaluationThinking.templateContent}">${evaluationThinking.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">
                                                评估思路<span class="symbol required"></span>
                                            </label>
                                            <div class="x-valid">
                                                <div class="col-sm-10">
                                                        <textarea required placeholder="评估思路"
                                                                  name="thinking"
                                                                  class="form-control"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="thinking-field"></div>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="savesEvaluationMethod()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<!--查看他项权利信息-->
<div id="viewInventoryRightModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利信息</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">类型</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="typeName"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">类别</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="categoryName"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">他权证编号</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="number"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">登记日期</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="registerDate"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        义务人
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="obligor"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        权利人
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="obligee"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        登记金额
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="registerAmount"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        行权金额
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="actualAmount"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        登记面积
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="registerArea"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        他权级次
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="rightRank"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">开始日期</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="beginDate"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">结束日期</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="endDate"></label>
                                    </div>
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
            </div>
        </div>
    </div>
</div>
<!--查看调查信息-->
<div id="viewExamineInfoModal" class="modal fade bs-example-modal-xs" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-xs">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"></h3>
            </div>
            <div class="modal-body">
                <div class="x_content">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<!--查看合并的委估对象明细-->
<div id="viewMergeJudgeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"></h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tb_judge_detail_list">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</div>
<!--动态字段-->
<script type="text/html" id="dynamicFieldHtml">
    <label class="col-sm-2 control-label">
        {name}
    </label>
    <div class="x-valid">
        <div class="col-sm-4">
            <input type="text" class="form-control" data-name="{name}" onkeyup="{functionName}(this);">
        </div>
    </div>
</script>
<!--评估对象-->
<script type="text/html" id="judgeObjectHtml">
    <tr>
        <td>
            <input type="hidden" data-name="id" value="{id}">
            <input type="hidden" data-name="bisSplit" value="{bisSplit}">
            <input type="hidden" data-name="bisMerge" value="{bisMerge}">
            <input type="hidden" data-name="number" value="{number}">
            <input type="hidden" data-name="splitNumber" value="{splitNumber}">
            <input type="hidden" data-name="declareId" value="{declareId}">
            <input type="hidden" data-name="rentalPossessionDesc" value="{rentalPossessionDesc}">
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <%--<td>--%>
        <%--<label class="form-control" data-name="name">--%>
        <%--<span>{name}</span>--%>
        <%--<a href="javascript://" onclick="programme.viewJudgeInfo(this);"--%>
        <%--class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>--%>
        <%--</label>--%>
        <%--</td>--%>
        <td>
            <label class="form-control" data-name="ownership">{ownership}
                <a href="javascript://" onclick="programme.viewJudgeInfo(this);"
                   class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>
            </label></td>
        <td><label class="form-control" data-name="seat"><span>{seat}</span></label></td>
        <td><label class="form-control" data-name="certUse">{certUse}</label></td>
        <td><label class="form-control" data-name="practicalUse">{practicalUse}</label></td>
        <td>
            <div class="x-valid">
                <select class="form-control" required data-name="setUse" name="setUse{id}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${setUseList}" var="setUse">
                        <option value="${setUse.id}">${setUse.name}</option>
                    </c:forEach>
                </select>
            </div>
        </td>
        <td>
            <div class="x-valid">
                <select class="form-control" required data-name="bestUse" name="bestUse{id}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${bestUseList}" var="bestUse">
                        <option value="${bestUse.id}">${bestUse.name}</option>
                    </c:forEach>
                </select>
            </div>
        </td>
        <td><label class="form-control">{floorArea}</label></td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-number="true"
                       data-rule-range="[0,{floorArea}]"
                       name="evaluationArea{id}" data-name="evaluationArea"
                       placeholder="评估面积" value="{evaluationArea}">
            </div>
        </td>
        <td>
            <a href="javascript://" onclick="programme.splitJudge(this);"
               class="btn btn-xs btn-success judge-split tooltips">拆分</a>
            <a href="javascript://" onclick="programme.delSplitJudge(this);"
               class="btn btn-xs btn-warning judge-remove tooltips">移除</a>
            <a href="javascript://" onclick="programme.mergeJudge(this);"
               class="btn btn-xs btn-warning judge-merge tooltips">合并</a>
            <a href="javascript://" onclick="programme.mergeJudgeCancel(this);"
               class="btn btn-xs btn-warning judge-merge-cancel tooltips">取消合并</a>
            <a href="javascript://" title="评估方法" onclick="setEvaluationMethod(this);"
               class="btn btn-xs btn-success judge-method tooltips">方法</a>
            <a href="javascript://" title="出租或占用情况描述" onclick="programme.updateRentalPossessionDesc(this);"
               class="btn btn-xs btn-success judge-description tooltips">描述</a>
        </td>
    </tr>
</script>
</body>
</html>
<div id="modal_inventory_right" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利</h3>
            </div>
            <div class="modal-body">
                <form id="frm_inventory_right" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <div class="form-group border-bottom-line">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                附件
                            </label>
                            <div class="col-sm-10">
                                <input id="inventoryRightFile" type="file" multiple="false">
                                <div id="_inventoryRightFile"></div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                类型<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" required id="type" name="type"
                                        onchange="typeChange(this);">
                                    <option value="">-请选择-</option>
                                    <c:forEach var="items" items="${inventoryRightTypeList}">
                                        <option value="${items.id}">${items.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                类别<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" required id="category" name="category">

                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                他权证编号<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="他权证编号" required
                                       id="number" name="number"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">登记日期<span class="symbol required"></span></label>
                            <div class="col-sm-4">
                                <input placeholder="登记日期" id="registerDate" name="registerDate"
                                       data-date-format="yyyy-mm-dd" required
                                       class="form-control date-picker dbdate" readonly="readonly">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                义务人<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="义务人" required
                                       id="obligor" name="obligor"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                权利人<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="权利人" required
                                       id="obligee" name="obligee" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                登记金额
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="登记金额"
                                       id="registerAmount" name="registerAmount" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                行权金额
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="行权金额"
                                       id="actualAmount" name="actualAmount" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                登记面积
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="登记面积"
                                       id="registerArea" name="registerArea" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                他权级次
                            </label>
                            <div class="col-sm-4">
                                <input type="text" placeholder="他权级次"
                                       id="rightRank" name="rightRank" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">开始日期</label>
                            <div class="col-sm-4">
                                <input placeholder="开始日期" id="beginDate"
                                       name="beginDate" data-date-format="yyyy-mm-dd"
                                       class="form-control date-picker dbdate" readonly="readonly">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">结束日期</label>
                            <div class="col-sm-4">
                                <input placeholder="结束日期" id="endDate"
                                       name="endDate" data-date-format="yyyy-mm-dd"
                                       class="form-control date-picker dbdate" readonly="readonly">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="programme.saveInventoryRight()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".area_panel .x_title").each(function () {
            $(this).trigger('click');
        })
        programme.loadInventoryRightList();

        //阻止合并按钮的冒泡
        $(".btn-area-merge").click(function (e) {
            programme.areaMerge(this);
            e.stopPropagation();
        })
        //阻止合并按钮的冒泡
        $(".btn-area-merge-cancel").click(function (e) {
            programme.areaMergeCancel($(this).closest('.area_panel').find('[name=areaGroupId]').val());
            e.stopPropagation();
        })

        FileUtils.uploadFiles({
            target: "inventoryRightFile",
            showFileList: false,
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: AssessDBKey.SurveyAssetInventoryRight,
                    tableId: $("#frm_inventory_right").find('[name=id]').val()
                };
                return formData;
            },
            onUploadComplete: function () {
                programme.loadInventoryRightFile($("#frm_inventory_right").find('[name=id]').val());
            }
        });
    })

    //方案
    var programme = {};
    programme.config = {
        areaPopIndex: 0,//区域弹框index
        judgePopIndex: 0,//委估对象弹框index
        //区域合并项html
        areaItemHtml: '<li data-areaGroupId="{areaGroupId}"> <p> <label>{areaName}</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>',
        //委估对象合并项html
        judgeItemHtml: '<li data-judgeId="{judgeId}"> <p> <label>{name}</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>',
        currJudgeMethodButton: undefined //当前评估方法button
    };

    //设置价值内涵的值
    programme.setValueConnotation = function (id, valueConnotation) {
        if (valueConnotation) {
            $("#frmJudgeObject" + id).find('[name=valueConnotation]').select2('val', JSON.parse(valueConnotation));
        }
    }

    //加载区域下的委估对象列表
    programme.loadJudgeObjectList = function (_this) {
        var tbody = $(_this).closest(".area_panel").find(".table").find("tbody");
        tbody.empty();
        var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeProgramme/getSchemeJudgeObjectList",
            data: {
                areaGroupId: areaGroupId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    $.each(result.data, function (i, item) {
                        var html = $("#judgeObjectHtml").html();
                        html = html.replace(/{id}/g, item.id == undefined ? "" : item.id);
                        html = html.replace(/{bisSplit}/g, item.bisSplit == undefined ? false : item.bisSplit);
                        html = html.replace(/{bisMerge}/g, item.bisMerge == undefined ? false : item.bisMerge);
                        html = html.replace(/{number}/g, item.number == undefined ? "" : item.number);
                        html = html.replace(/{splitNumber}/g, item.splitNumber == undefined ? "" : item.splitNumber);
                        html = html.replace(/{rentalPossessionDesc}/g, item.rentalPossessionDesc == undefined ? "" : item.rentalPossessionDesc);
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace(/{name}/g, item.name == undefined ? "" : item.name);
                        html = html.replace(/{declareId}/g, item.declareRecordId == undefined ? "" : item.declareRecordId);
                        html = html.replace(/{ownership}/g, item.ownership == undefined ? "" : AssessCommon.substring(item.ownership, 10));
                        html = html.replace(/{seat}/g, item.seat == undefined ? "" : AssessCommon.substring(item.seat, 20));
                        html = html.replace(/{certUse}/g, item.certUse == undefined ? "" : item.certUse);
                        html = html.replace(/{practicalUse}/g, item.practicalUse == undefined ? "" : item.practicalUse);
                        html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);
                        tbody.append(html);
                        //设值
                        var lastTr = tbody.find("tr:last");
                        lastTr.find('[data-name="setUse"]').val(item.setUse);
                        lastTr.find('[data-name="bestUse"]').val(item.bestUse);
                        lastTr.find('td:last').find(item.bisSplit ? '.judge-split' : '.judge-remove').remove();
                        lastTr.find('td:last').find(item.bisMerge ? '.judge-merge' : '.judge-merge-cancel').remove();
                        if (item.bisSetFunction) {
                            lastTr.find('td:last').find('.judge-method').removeClass('btn-success').addClass('btn-primary');
                        }
                    })
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //合并项移除
    programme.mergeItemRemove = function (_this) {
        $(_this).closest('li').remove();
    };

    //区域合并
    programme.areaMerge = function (_this) {
        var areaName = $(_this).closest('h3').find('label').text();
        var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
        var html = programme.config.areaItemHtml;
        if (programme.config.areaPopIndex <= 0) {
            layer.closeAll();
            programme.config.areaPopIndex = layer.open({
                title: "区域合并",
                offset: 't',
                shade: false,
                zIndex: 998,
                area: ['420px', '300px'], //宽高
                content: '<ul id="area-merge-ul" class="to_do"></ul>',
                yes: function (index, layero) {
                    programme.areaMergeSubmit();
                },
                end: function () {
                    programme.config.areaPopIndex = 0;
                },
                success: function () {
                    $("#area-merge-ul").prepend(html.replace(/{areaName}/g, areaName).replace(/{areaGroupId}/g, areaGroupId));
                }
            });
        } else {
            //该区域已添加则直接返回
            var isExist = false;
            $("#area-merge-ul").find('li').each(function () {
                if ($(this).attr('data-areaGroupId') == areaGroupId) {
                    isExist = true;
                    return;
                }
            })
            if (!isExist) {
                $("#area-merge-ul").prepend(html.replace(/{areaName}/g, areaName).replace(/{areaGroupId}/g, areaGroupId));
            }
        }
    };

    //区域合并提交
    programme.areaMergeSubmit = function () {
        var areaGroupIdArray = [];
        $("#area-merge-ul").find('li').each(function () {
            areaGroupIdArray.push($(this).attr('data-areaGroupId'));
        })
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/areaGroupMerge',
            data: {
                projectId: '${projectInfo.id}',
                areaGroupIds: areaGroupIdArray.join()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("区域合并成功", 1, null, function () {
                        window.location.href = window.location.href;
                    })
                } else {
                    Alert("区域合并失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //区域合并取消
    programme.areaMergeCancel = function (id) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/areaGroupMergeCancel',
            data: {
                id: id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("区域合并取消成功", 1, null, function () {
                        window.location.href = window.location.href;
                    })
                } else {
                    Alert("区域合并取消失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //委估对象拆分
    programme.splitJudge = function (_this) {
        programme.saveProgrammeArea($(_this).closest('.area_panel'));
        //后台添加数据
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/splitJudge',
            data: {
                projectId: '${projectInfo.id}',
                areaGroupId: $(_this).closest('.area_panel').find('[name=areaGroupId]').val(),
                id: $(_this).closest('tr').find('[data-name="id"]').val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    programme.loadJudgeObjectList($(_this).closest('.area_panel'));
                } else {
                    Alert("委估对象拆分失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //删除拆分出来的委估对象
    programme.delSplitJudge = function (_this) {
        programme.saveProgrammeArea($(_this).closest('.area_panel'));
        //后台添加数据
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/delSplitJudge',
            data: {
                projectId: '${projectInfo.id}',
                areaGroupId: $(_this).closest('.area_panel').find('[name=areaGroupId]').val(),
                id: $(_this).closest('tr').find('[data-name="id"]').val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    programme.loadJudgeObjectList($(_this).closest('.area_panel'));
                } else {
                    Alert("委估对象删除失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //委估对象合并
    programme.mergeJudge = function (_this) {
        programme.saveProgrammeArea($(_this).closest('.area_panel'));
        var name = $(_this).closest('tr').find('[data-name="seat"]').find('span').text();
        var judgeId = $(_this).closest('tr').find('[data-name="id"]').val();
        var html = programme.config.judgeItemHtml;
        if (programme.config.judgePopIndex <= 0) {
            programme.config.judgePopIndex = layer.open({
                title: "委估对象合并",
                offset: 't',
                shade: false,
                zIndex: 999,
                area: ['420px', '300px'], //宽高
                content: '<ul id="judge-merge-ul" class="to_do"></ul>',
                yes: function (index, layero) {
                    programme.mergeJudgeSubmit(_this, $(_this).closest('.area_panel'));
                },
                end: function () {
                    programme.config.judgePopIndex = 0;
                },
                success: function () {
                    $("#judge-merge-ul").prepend(html.replace(/{name}/g, name).replace(/{judgeId}/g, judgeId));
                }
            });
        } else {
            //该区域已添加则直接返回
            var isExist = false;
            $("#judge-merge-ul").find('li').each(function () {
                if ($(this).attr('data-judgeId') == judgeId) {
                    isExist = true;
                    return;
                }
            })
            if (!isExist) {
                $("#judge-merge-ul").prepend(html.replace(/{name}/g, name).replace(/{judgeId}/g, judgeId));
            }
        }
    };

    //委估对象合并提交
    programme.mergeJudgeSubmit = function (_this, panel) {
        var judgeIdArray = [];
        $("#judge-merge-ul").find('li').each(function () {
            judgeIdArray.push($(this).attr('data-judgeId'));
        })
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/mergeJudge',
            data: {
                ids: judgeIdArray.join()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('委估对象合并成功');
                    layer.close(programme.config.judgePopIndex);
                    programme.loadJudgeObjectList(panel);
                } else {
                    Alert("委估对象合并失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //取消委估对象合并
    programme.mergeJudgeCancel = function (_this) {
        programme.saveProgrammeArea($(_this).closest('.area_panel'));
        //后台添加数据
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/mergeJudgeCancel',
            data: {
                id: $(_this).closest('tr').find('[data-name="id"]').val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('委估对象取消合并成功');
                    programme.loadJudgeObjectList($(_this).closest('.area_panel'));
                } else {
                    Alert("权证拆分失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //获取区域下的方案数据
    programme.getProgrammeAreaData = function (areaPanel) {
        var data = {}; //找出需要保存的数据
        data.areaGroupId = $(areaPanel).find('[name="areaGroupId"]').val();
        data.valueTimePoint = $(areaPanel).find('[name="valueTimePoint"]').val();
        data.timePointExplain = $(areaPanel).find('[name="timePointExplain"]').val();

        data.entrustmentPurpose = $(areaPanel).find('[name="entrustmentPurpose"]').val();
        data.remarkEntrustPurpose = $(areaPanel).find('[name="remarkEntrustPurpose"]').val();

        data.valueDefinition = $(areaPanel).find('[name="valueDefinition"]').val();
        data.valueConnotation = $(areaPanel).find('[name="valueConnotation"]').val();
        if (!data.valueConnotation) {
            data.valueConnotation = [];
        }
        data.valueDefinitionDesc = $(areaPanel).find('[name="valueDefinitionDesc"]').val();
        data.valueConnotationDesc = $(areaPanel).find('[name="valueConnotationDesc"]').val();
        data.schemeJudgeObjects = [];

        var trs = $(areaPanel).find(".table").find("tbody").find('tr');
        if (trs && trs.length > 0) {
            $.each(trs, function (i, tr) {
                var schemeJudgeObject = {};
                schemeJudgeObject.id = $(tr).find('[data-name="id"]').val();
                schemeJudgeObject.setUse = $(tr).find('[data-name="setUse"]').val();
                schemeJudgeObject.bestUse = $(tr).find('[data-name="bestUse"]').val();
                schemeJudgeObject.evaluationArea = $(tr).find('[data-name="evaluationArea"]').val();
                data.schemeJudgeObjects.push(schemeJudgeObject);
            })
        }
        return data;
    };

    //保存区域下方案
    programme.saveProgrammeArea = function (areaPanel) {
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/saveProgrammeArea',
            data: {
                formData: JSON.stringify(programme.getProgrammeAreaData(areaPanel))
            },
            async: false,
            type: "post",
            dataType: "json",
            success: function (result) {
                //不做任何信息提示
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //保存区域下方案
    programme.saveProgrammeAll = function (_this) {
        var data = [];
        $(".area_panel").each(function () {
            data.push(programme.getProgrammeAreaData($(this)));
        })
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/saveProgrammeAll',
            data: {
                formData: JSON.stringify(data)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                } else {
                    Alert("保存成功失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //提交方案
    programme.submitProgramme = function () {
        //前端验证
        var isPass = true;
        $("form[id^=frmJudgeObject]").each(function () {
            var that = $(this);
            var options = {
                msg: "请检查【" + that.closest('.area_panel').find('h2').find('label').text() + "】填写的信息",
                hiddenValid: true
            };
            if (!$(this).valid(options)) {
                isPass = false;
                return false;
            }
        })
        if (!isPass) return false;
        var data = [];
        $(".area_panel").each(function () {
            data.push(programme.getProgrammeAreaData($(this)));
        })
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/submitProgramme',
            data: {
                projectId: '${projectInfo.id}',
                planId: '${planId}',
                formData: JSON.stringify(data)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交方案成功", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //加载合并对象的明细
    programme.loadJudgeDetailList = function (pid) {
        var cols = [];
        cols.push({field: 'number', title: '编号'});
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'ownership', title: '所有权人'});
        cols.push({field: 'seat', title: '坐落'});
        cols.push({field: 'certUse', title: '证载用途'});
        cols.push({field: 'practicalUse', title: '实际用途'});
        cols.push({field: 'floorArea', title: '证载面积'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="programme.viewJudgeDetailExamineInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_judge_detail_list").bootstrapTable('destroy');
        TableInit("tb_judge_detail_list", "${pageContext.request.contextPath}/schemeProgramme/getJudgeObjectListByPid", cols, {
            pid: pid
        }, {
            showColumns: false,
            showRefresh: true,
            search: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //查看他项信息
    programme.viewInventoryRightInfo = function (index) {
        var row = $("#tb_inventory_right_list").bootstrapTable('getData')[index];
        $("#viewInventoryRightModal").find('[data-name]').each(function () {
            $(this).text('').text(row[$(this).attr('data-name')]);
        })
        $("#viewInventoryRightModal").find('[data-name=registerDate]').text(formatDate(row.registerDate, false));
        $("#viewInventoryRightModal").find('[data-name=beginDate]').text(formatDate(row.beginDate, false));
        $("#viewInventoryRightModal").find('[data-name=endDate]').text(formatDate(row.endDate, false));
        $("#viewInventoryRightModal").modal();
    };

    //查看委估对象明细
    programme.viewJudgeDetailExamineInfo = function (index) {
        var row = $("#tb_judge_detail_list").bootstrapTable('getData')[index];
        programme.viewExamineInfo(row.declareRecordId);
    };

    //查看委估对象相关信息
    programme.viewJudgeInfo = function (_this) {
        var tr = $(_this).closest('tr');
        var bisMerge = tr.find('[data-name=bisMerge]').val();
        if (bisMerge == 'true') {
            var pid = tr.find('[data-name=id]').val();
            programme.loadJudgeDetailList(pid);
            $("#viewMergeJudgeModal").modal();
        } else {
            programme.viewExamineInfo(tr.find('[data-name=declareId]').val());
        }
    };

    //查看委估对象调查信息
    programme.viewExamineInfo = function (declareId) {
        layer.open({
            type: 1,
            title: "调查信息",
            offset: 't',
            shade: false,
            area: ['720px', '450px'], //宽高
            content: '<table id="examine_list" class="table table-bordered" style="max-height: auto;"></table>',
            success: function () {
                $("#examine_list").treegrid({
                        url: '${pageContext.request.contextPath}/schemeProgramme/getPlanDetailsByDeclareId?declareId=' + declareId,
                        method: 'get',
                        idField: 'id',
                        treeField: 'projectPhaseName',
                        datatype: 'json',
                        lines: true,
                        width: 'auto',
                        rownumbers: true,
                        onLoadSuccess: function () {
                            $(".tooltips").tooltip();
                        },
                        columns: [[
                            {
                                field: "projectPhaseName",
                                title: "工作内容",
                                width: "70%",
                                align: "left",
                                formatter: function (value, row) {
                                    return value
                                }
                            },
                            {
                                field: 'workStages', title: '操作', width: '30%', formatter: function (value, row) {
                                if (row.bisEnable) {
                                    var s = "";
                                    if (row.displayUrl) {
                                        s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                                    }
                                    return s;
                                }
                            }
                            }
                        ]]
                    }
                );
            }
        });
    };

    //加载他项权利
    programme.loadInventoryRightList = function () {
        var cols = [];
        cols.push({field: 'certName', title: '权证号'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top"  onclick="programme.editInventoryRight(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="programme.viewInventoryRightInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_inventory_right_list").bootstrapTable('destroy');
        TableInit("tb_inventory_right_list", "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByProjectId", cols, {
            projectId: '${projectInfo.id}'
        }, {
            showColumns: false,
            showRefresh: true,
            search: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //保存他权
    programme.saveInventoryRight = function () {
        var data = formParams("frm_inventory_right");
        if ($("#frm_inventory_right").valid()) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/save",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        programme.loadInventoryRightList();
                        $('#modal_inventory_right').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //编辑他权信息
    programme.editInventoryRight = function (index) {
        var row = $("#tb_inventory_right_list").bootstrapTable('getData')[index];
        $("#frm_inventory_right").clearAll().initForm(row);
        $("#registerDate").val(formatDate(row.registerDate, false));
        $("#beginDate").val(formatDate(row.beginDate, false));
        $("#endDate").val(formatDate(row.endDate, false));
        AssessCommon.loadDataDicByPid(row.type, row.category, function (html) {
            $("#category").html(html);
        })

        programme.loadInventoryRightFile(row.id);
        $('#modal_inventory_right').modal();
    }

    //加载他权附件
    programme.loadInventoryRightFile = function (tableId) {
        FileUtils.getFileShows({
            target: "inventoryRightFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryRight,
                tableId: tableId
            },
            deleteFlag: true
        });
    }

    //调整描述内容
    programme.updateRentalPossessionDesc = function (_this) {
        var tr = $(_this).closest('tr');
        layer.prompt({
            title: '出租占用情况描述',
            formType: 2,
            zIndex: 999, //重点1
            value: tr.find('[data-name=rentalPossessionDesc]').val(),
            area: ['800px', '350px']
        }, function (val, index) {
            $.ajax({
                url: '${pageContext.request.contextPath}/schemeProgramme/updateRentalPossessionDesc',
                data: {
                    id: tr.find('[data-name="id"]').val(),
                    rentalPossessionDesc: val
                },
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        tr.find('[data-name=rentalPossessionDesc]').val(val);
                        layer.close(index);
                    } else {
                        toastr.error(result.errmsg);
                    }
                }
            })
        });
    }

</script>
<script type="text/javascript">
    /*
     *------------------------------------------------------------------------------------------------------
     *评估方法设置相关
     *------------------------------------------------------------------------------------------------------
     */

    //方法适用原因字段替换
    function methodApplicableFieldReplace(_this) {
        //1.先找到模板 2.再依次找到字段填写的信息
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="methodTemplate"]').find('option:selected').attr("data-applicable");
        tabPane.find('.applicableReason-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="applicableReason"]').val(template);
    }

    //方法不适用原因字段替换
    function methodNotApplicableFieldReplace(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="methodTemplate"]').find('option:selected').attr("data-not-applicable");
        tabPane.find('.notApplicableReason-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="notApplicableReason"]').val(template);
    }

    //思路字段替换
    function thinkingFieldReplace(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="thinkingTemplate"]').find('option:selected').attr("data-templateContent");
        tabPane.find('.thinking-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="thinking"]').val(template);
    }

    //创建动态字段html
    function createDynaicFieldHtml(fieldArray, functionName) {
        if (fieldArray) {
            var resultHtml = '<div class="form-group">';
            $.each(fieldArray, function (i, item) {
                if (i > 0 && i % 2 == 0) {
                    resultHtml += '</div><div class="form-group">';
                }
                var templateHtml = $("#dynamicFieldHtml").html();
                templateHtml = templateHtml.replace(/{name}/g, item).replace(/{functionName}/, functionName);
                resultHtml += templateHtml;
            })
            resultHtml += '</div>';
            return resultHtml;
        } else {
            return '';
        }
    }

    //保存评估方法
    function savesEvaluationMethod() {
        //验证数据是否填写完整
        var isPass = true;
        $("#myTabContent").find('.tab-pane').find('form').each(function () {
            if (!$(this).valid("请检查【" + $(this).attr('data-name') + "】是否填写完整！")) {
                isPass = false;
                return false;
            }
        });
        if (!isPass) return false;
        var judgeFunctionList = [];
        $("#myTabContent").find('.tab-pane').each(function () {
            var judgeFunction = {};
            judgeFunction.id = $(this).find('[name="id"]').val();
            judgeFunction.judgeObjectId = $(this).find('[name="judgeObjectId"]').val();
            judgeFunction.name = $(this).find('[name="name"]').val();
            judgeFunction.methodType = $(this).find('[name="methodType"]').val();
            judgeFunction.bisApplicable = $(this).find('[name="bisApplicable"]:checked').val();
            judgeFunction.applicableReason = $(this).find('[name="applicableReason"]').val();
            judgeFunction.notApplicableReason = $(this).find('[name="notApplicableReason"]').val();
            judgeFunction.thinking = $(this).find('[name="thinking"]').val();
            judgeFunctionList.push(judgeFunction);
        })
        //检查各个方法数据是否填写完整
        if (judgeFunctionList.length > 0) {
            for (var i = 0; i < judgeFunctionList.length; i++) {
                if (!methodHasWriteFull(judgeFunctionList[i])) {
                    toastr.info("请检查【" + judgeFunctionList[i].name + "】是否填写完整！");
                    return false;
                }
            }
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/saveJudgeFunction',
            data: {
                formData: JSON.stringify(judgeFunctionList)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    toastr.success('评估方法保存成功');
                    programme.config.currJudgeMethodButton.removeClass('btn-success').addClass('btn-primary');
                    $('#divBoxMethodExtend').modal('hide');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //方法信息是否填写完整
    function methodHasWriteFull(judgeFunction) {
        if (judgeFunction) {
            if (judgeFunction.bisApplicable == undefined) return false;
            if (judgeFunction.bisApplicable == 'true') {
                if (!judgeFunction.applicableReason) return false;
            } else {
                if (!judgeFunction.notApplicableReason) return false;
            }
            if (!judgeFunction.thinking) {
                return false;
            }
        }
        return true;
    }

    //设置评估方法
    function setEvaluationMethod(_this) {
        var judgeObjectId = $(_this).closest("tr").find('[data-name="id"]').val();
        $("#myTabContent").find('[name="judgeObjectId"]').val(judgeObjectId);
        programme.config.currJudgeMethodButton = $(_this);
        //还原数据状态
        cleanEvaluationMethod();
        //如果该估计对象已经设置过评估方法，则将数据填充回去
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getSchemeJudgeFunctions',
            data: {
                judgeObjectId: judgeObjectId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    $.each(result.data, function (i, item) {
                        var methodTypeEle = $("#myTabContent").find('.tab-pane').find('[name="methodType"][value="' + item.methodType + '"]')
                        var tabPane = $(methodTypeEle).closest(".tab-pane");
                        tabPane.find('[name="id"]').val(item.id);
                        if (item.bisApplicable) {
                            tabPane.find('[name="bisApplicable"][value="true"]').prop('checked', true);
                            tabPane.find('.applicable').show();
                        } else {
                            tabPane.find('[name="bisApplicable"][value="false"]').prop('checked', true);
                            tabPane.find('.not-applicable').show();
                        }
                        tabPane.find('.thinkingWell').show();
                        tabPane.find('[name="applicableReason"]').val(item.applicableReason);
                        tabPane.find('[name="notApplicableReason"]').val(item.notApplicableReason);
                        tabPane.find('[name="thinking"]').val(item.thinking);
                    })
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
        $("#divBoxMethodExtend").modal();
    }

    //清空
    function cleanEvaluationMethod() {
        $("#myTab").find('a:first').tab('show');
        $("#myTabContent").find('form').each(function () {
            $(this).clearValid();
        })
        $("#myTabContent").find('[name="id"]').val('0');
        $("#myTabContent").find('[name="bisApplicable"]').attr("checked", false);
        $("#myTabContent").find('[name="applicableReason"]').val('');
        $("#myTabContent").find('[name="notApplicableReason"]').val('');
        $("#myTabContent").find('[name="thinking"]').val('');

        $("#myTabContent").find('[name="methodTemplate"]').val('');
        $("#myTabContent").find('[name="thinkingTemplate"]').val('');

        $("#myTabContent").find('.applicable').hide();
        $("#myTabContent").find('.not-applicable').hide();
        $("#myTabContent").find('.thinkingWell').hide();
        $("#myTabContent").find('.applicableReason-field').empty();
        $("#myTabContent").find('.notApplicableReason-field').empty();
        $("#myTabContent").find('.thinking-field').empty();

    }

    //评估方法模板选项change
    function evaluationMethodChange(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var bisApplicable = tabPane.find('[name=bisApplicable]:checked').val();
        var option = $(_this).find('option:selected');
        tabPane.find('.applicableReason-field').empty();
        tabPane.find('.notApplicableReason-field').empty();
        if (bisApplicable == "true") {
            tabPane.find('[name="applicableReason"]').val(option.attr("data-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'methodApplicableFieldReplace');
                tabPane.find('.applicableReason-field').append(html);
            }
        } else if (bisApplicable == "false") {
            tabPane.find('[name="notApplicableReason"]').val(option.attr("data-not-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-not-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'methodNotApplicableFieldReplace');
                tabPane.find('.notApplicableReason-field').append(html);
            }
        }
    }

    //评估思路模板选项change
    function evaluationThinkingChange(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var option = $(_this).find('option:selected');
        tabPane.find('.thinking-field').empty();
        tabPane.find('[name="thinking"]').val(option.attr("data-templateContent"));
        var fieldArray = AssessCommon.extractField(option.attr("data-templateContent"));
        if (fieldArray && fieldArray.length > 0) {
            var html = createDynaicFieldHtml(fieldArray, 'thinkingFieldReplace');
            tabPane.find('.thinking-field').append(html);
        }
    }

    //适用切换
    function applicableChange(_this, isApplicable) {
        var tabPane = $(_this).closest(".tab-pane");
        var thisRadio = $(_this).find('input:radio');
        thisRadio.attr('checked', true);
        $(_this).closest('.btn-group').find('input:radio').not(thisRadio).attr('checked', false);
        if (isApplicable) {
            tabPane.find('.applicable').show();
            tabPane.find('.not-applicable').hide();
        } else {
            tabPane.find('.applicable').hide();
            tabPane.find('.not-applicable').show();
        }
        tabPane.find('.thinkingWell').show();
    }

</script>


