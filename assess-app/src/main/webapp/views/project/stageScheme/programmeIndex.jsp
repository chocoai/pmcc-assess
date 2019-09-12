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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>权证信息</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <form id="declare_record_form" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                权证号
                            </label>
                            <div class="col-sm-2">
                                <input type="text" data-rule-maxlength="50" placeholder="权证号" name="name"
                                       class="form-control">
                            </div>
                            <label class="col-sm-1 control-label">
                                坐落
                            </label>
                            <div class="col-sm-2">
                                <input type="text" data-rule-maxlength="50" placeholder="坐落" name="seat"
                                       class="form-control">
                            </div>
                            <label class="col-sm-1 control-label">
                                是否上报告
                            </label>
                            <div class="col-sm-2">
                                <select class="form-control" name="bisPartIn">
                                    <option value="">-请选择-</option>
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="programme.loadDeclareRecordList();">
                                    查询
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="programme.generatorAreaGroup();">
                                    生成方案数据
                                </button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered" id="tb_declare_record_list"></table>
                </div>
            </div>

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
                                               value="${empty item.timePointExplain?valueDateExplain:item.timePointExplain}">
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
                                        财产范围<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select class="form-control" name="propertyScope" required></select>
                                            <%--<input type="text" class="form-control" name="propertyScope"--%>
                                            <%--placeholder="财产范围" value="${item.propertyScope}" required>--%>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产包括<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="scopeInclude"
                                               placeholder="财产包括" value="${item.scopeInclude}" required>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产不包括
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="scopeNotInclude"
                                               placeholder="财产不包括" value="${item.scopeNotInclude}" required>
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
                                    <th style="width: 6%">终止日期</th>
                                    <th style="width: 6%">剩余年限</th>
                                    <th style="width: 6%">证载用途</th>
                                    <th style="width: 6%">实际用途</th>
                                    <c:if test="${projectInfo.projectCategoryName eq '房产'}">
                                        <th style="width: 6%">设定用途</th>
                                        <th style="width: 10%">最佳利用方式</th>
                                    </c:if>
                                    <c:if test="${projectInfo.projectCategoryName eq '土地'}">
                                        <th style="width: 6%">设定容积率</th>
                                        <th style="width: 6%">规划容积率</th>
                                        <th style="width: 6%">实际容积率</th>
                                    </c:if>
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
                                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, '${item.propertyScope}', function (html, data) {
                                    $("#frmJudgeObject${item.id}").find("[name=propertyScope]").empty().html(html);
                                });
                            })
                        </script>
                    </div>
                </div>
            </c:forEach>
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
<div id="modal_method_info" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">方法设置</h3>
            </div>
            <div class="modal-body">
                <form id="frm_method_info" class="form-horizontal">
                    <input type="hidden" name="judgeObjectId">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th width="35%">基本方法</th>
                                    <%--<th>其它方法</th>--%>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td id="baseMethodTd">
                                        <c:forEach var="item" items="${baseMethodList}">
                                            <div class="btn-group" style="margin: 10px;">
                                                <button class="btn btn-sm btn-dark"
                                                        type="button">${item.name}</button>
                                                <button onclick="programmeMethod.selectUseBase(this);"
                                                        data-use-flag="false" data-method-type="${item.id}"
                                                        data-method-name="${item.name}"
                                                        class="btn btn-sm btn-default btn-select-use"
                                                        type="button" title="适用"><i
                                                        class="fa fa-check"></i></button>
                                            </div>
                                        </c:forEach>
                                    </td>
                                    <%--<td id="otherMethodTd">--%>
                                        <%--<c:forEach var="item" items="${otherMethodList}">--%>
                                            <%--<div class="btn-group" style="margin: 10px;">--%>
                                                <%--<button class="btn btn-sm btn-dark"--%>
                                                        <%--type="button">${item.name}</button>--%>
                                                <%--<button onclick="programmeMethod.selectUseOther(this);"--%>
                                                        <%--data-use-flag="false" data-method-type="${item.id}"--%>
                                                        <%--data-method-name="${item.name}"--%>
                                                        <%--class="btn btn-sm btn-default btn-select-use"--%>
                                                        <%--type="button" title="选用"><i--%>
                                                        <%--class="fa fa-check"></i>--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                        <%--</c:forEach>--%>
                                    <%--</td>--%>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th width="20%">适用方法</th>
                                    <th width="20%">模板</th>
                                    <th width="60%">适用原因</th>
                                </tr>
                                </thead>
                                <tbody id="applicableTbody">
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th width="20%">不适用方法</th>
                                    <th width="20%">模板</th>
                                    <th width="60%">不适用原因</th>
                                </tr>
                                </thead>
                                <tbody id="notApplicableTbody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick=" programmeMethod.saveJudgeFunction();">
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
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        备注
                                    </label>
                                    <div class="col-sm-10">
                                        <label class="form-control" data-name="remark"></label>
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
        <td>
            <label class="form-control" data-name="ownership">{ownership}
                <a href="javascript://" onclick="programme.viewJudgeInfo(this);"
                   class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>
            </label></td>
        <td><label class="form-control" data-name="seat"><span>{seat}</span></label></td>
        <td><label class="form-control" data-name="landUseEndDate"><span>{landUseEndDate}</span></label></td>
        <td><label class="form-control" data-name="landRemainingYear"><span>{landRemainingYear}</span></label></td>
        <td><label class="form-control" data-name="certUse">{certUse}</label></td>
        <td><label class="form-control" data-name="practicalUse">{practicalUse}</label></td>
        <c:if test="${projectInfo.projectCategoryName eq '房产'}">
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
                        <c:forEach items="${bestUseList}" var="bestUse">
                            <option value="${bestUse.id}">${bestUse.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </td>
        </c:if>
        <c:if test="${projectInfo.projectCategoryName eq '土地'}">
            <td>
                <div class="x-valid">
                    <input class="form-control" type="text" required data-rule-number="true"
                           name="setPlotRatio{id}" data-name="setPlotRatio"
                           placeholder="设定容积率" value="{setPlotRatio}">
                </div>
            </td>
            <td>
                <div class="x-valid">
                    <input class="form-control" type="text" required data-rule-number="true"
                           name="planPlotRatio{id}" data-name="planPlotRatio"
                           placeholder="规划容积率" value="{planPlotRatio}">
                </div>
            </td>
            <td>
                <div class="x-valid">
                    <input class="form-control" type="text" required data-rule-number="true"
                           name="actualPlotRatio{id}" data-name="actualPlotRatio"
                           placeholder="实际容积率" value="{actualPlotRatio}">
                </div>
            </td>
        </c:if>
        <td><label class="form-control">{floorArea}</label></td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-number="true"
                       data-rule-range="[1,{floorArea}]"
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
            <a href="javascript://" title="评估方法" onclick="programmeMethod.setMethod(this);"
               class="btn btn-xs btn-success judge-method tooltips">方法</a>
        </td>
    </tr>
</script>
</body>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".area_panel .x_title").each(function () {
            $(this).trigger('click');
        })
        programme.loadDeclareRecordList();
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
        judgeItemHtml: '<li data-judgeId="{judgeId}"> <p> <label onclick="programme.setStandardJudge(this);">{mergeNumber}号估价对象</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>',
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
                        html = html.replace(/{ownership}/g, item.ownership == undefined ? "" : AssessCommon.substring(item.ownership, 100));
                        html = html.replace(/{seat}/g, item.seat == undefined ? "" : AssessCommon.substring(item.seat, 100));
                        html = html.replace(/{landUseEndDate}/g, item.landUseEndDate == undefined ? "" : formatDate(item.landUseEndDate));
                        html = html.replace(/{landRemainingYear}/g, item.landRemainingYear == undefined ? "" : item.landRemainingYear);
                        html = html.replace(/{certUse}/g, item.certUse == undefined ? "" : item.certUse);
                        html = html.replace(/{practicalUse}/g, item.practicalUse == undefined ? "" : item.practicalUse);
                        html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);
                        html = html.replace(/{setPlotRatio}/g, item.setPlotRatio == undefined ? "" : item.setPlotRatio);
                        html = html.replace(/{planPlotRatio}/g, item.planPlotRatio == undefined ? "" : item.planPlotRatio);
                        html = html.replace(/{actualPlotRatio}/g, item.actualPlotRatio == undefined ? "" : item.actualPlotRatio);
                        tbody.append(html);
                        //设值
                        var lastTr = tbody.find("tr:last");
                        lastTr.find('[data-name="setUse"]').val(item.setUse);
                        if (item.bestUse) {
                            lastTr.find('[data-name="bestUse"]').val(item.bestUse);
                        }
                        lastTr.find('td:last').find(item.bisSplit ? '.judge-split' : '.judge-remove').remove();
                        lastTr.find('td:last').find(item.bisMerge ? '.judge-merge' : '.judge-merge-cancel').remove();
                        if (item.splitNumber) {
                            //lastTr.find('td:last').find('.judge-merge').remove();
                        }
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
        //先验证该行数据是否填写正确及完整
        var $form = $(_this).closest('form');
        var $tr = $(_this).closest('tr');
        var passFlag = $form.validate().element($tr.find('[data-name=setUse]'));
        if (!passFlag) return false;
        passFlag = $form.validate().element($tr.find('[data-name=evaluationArea]'));
        if (!passFlag) return false;

        programme.saveProgrammeArea($(_this).closest('.area_panel'));
        var mergeNumber = $(_this).closest('tr').find('[data-name="mergeNumber"]').text();
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
                    programme.saveProgrammeAll(function () {
                        programme.mergeJudgeSubmit(_this, $(_this).closest('.area_panel'));
                    });
                },
                end: function () {
                    programme.config.judgePopIndex = 0;
                },
                success: function () {
                    $("#judge-merge-ul").prepend(html.replace(/{mergeNumber}/g, mergeNumber).replace(/{judgeId}/g, judgeId));
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
                $("#judge-merge-ul").prepend(html.replace(/{mergeNumber}/g, mergeNumber).replace(/{judgeId}/g, judgeId));
            }
        }
    };

    //委估对象合并提交
    programme.mergeJudgeSubmit = function (_this, panel) {
        var judgeIdArray = [];
        var standardJudgeId = null;
        $("#judge-merge-ul").find('li').each(function () {
            judgeIdArray.push($(this).attr('data-judgeId'));
            if ($(this).attr('data-standard-flag') == 'true') {
                standardJudgeId = $(this).attr('data-judgeId');
            }
        })
        if (!standardJudgeId) {
            Alert('参与合并的估价对象中未设置标准房地产');
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/mergeJudge',
            data: {
                ids: judgeIdArray.join(),
                standardJudgeId: standardJudgeId
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
        data.propertyScope = $(areaPanel).find('[name="propertyScope"]').val();
        data.scopeInclude = $(areaPanel).find('[name="scopeInclude"]').val();
        data.scopeNotInclude = $(areaPanel).find('[name="scopeNotInclude"]').val();
        data.schemeJudgeObjects = [];

        var trs = $(areaPanel).find(".table").find("tbody").find('tr');
        if (trs && trs.length > 0) {
            $.each(trs, function (i, tr) {
                var schemeJudgeObject = {};
                schemeJudgeObject.id = $(tr).find('[data-name="id"]').val();
                schemeJudgeObject.setUse = $(tr).find('[data-name="setUse"]').val();
                schemeJudgeObject.bestUse = $(tr).find('[data-name="bestUse"]').val();
                schemeJudgeObject.setPlotRatio = $(tr).find('[data-name="setPlotRatio"]').val();
                schemeJudgeObject.planPlotRatio = $(tr).find('[data-name="planPlotRatio"]').val();
                schemeJudgeObject.actualPlotRatio = $(tr).find('[data-name="actualPlotRatio"]').val();
                schemeJudgeObject.evaluationArea = $(tr).find('[data-name="evaluationArea"]').val();
                data.schemeJudgeObjects.push(schemeJudgeObject);
            })
        }
        return data;
    };

    //设置合并的估价对象
    programme.setStandardJudge = function (_this) {
        $(_this).closest('ul').find('li').removeAttr('data-standard-flag').removeAttr('data-standard-explain');
        $(_this).closest('ul').find('label').css('color', '');
        $(_this).closest('li').attr('data-standard-flag', 'true');
        $(_this).css('color', 'red');
    }

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
    programme.saveProgrammeAll = function (callback) {
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
                    if (callback) {
                        callback();
                    } else {
                        toastr.success('保存成功');
                    }
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
        FileUtils.getFileShows({
            target: "inventoryRightFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryRight,
                tableId: row.id
            },
            deleteFlag: false
        });
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

    //加载权证信息
    programme.loadDeclareRecordList = function () {
        var cols = [];
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'seat', title: '坐落'});
        cols.push({field: 'certUse', title: '证载用途'});
        cols.push({field: 'practicalUse', title: '实际用途'});
        cols.push({field: 'floorArea', title: '面积'});
        cols.push({
            field: 'bisPartIn', title: '是否上报告', formatter: function (value, row, index) {
                if (value == true) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top"  onclick="programme.addOrRemoveDeclareRecord(' + row.id + ',true);" ><i class="fa fa-add fa-white"></i>上报告</a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="programme.addOrRemoveDeclareRecord(' + row.id + ',false);" ><i class="fa fa-remove fa-white"></i>移除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_declare_record_list").bootstrapTable('destroy');
        TableInit("tb_declare_record_list", "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, {
            projectId: '${projectInfo.id}',
            name: $("#declare_record_form").find('[name=name]').val(),
            seat: $("#declare_record_form").find('[name=seat]').val(),
            bisPartIn: $("#declare_record_form").find('[name=bisPartIn]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //添加或移除申报记录数据
    programme.addOrRemoveDeclareRecord = function (id, bisPartIn) {
        var idArray = [];
        if (id) {
            idArray.push(id);
        } else {

        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRecord/addOrRemoveDeclareRecord",
            type: "post",
            dataType: "json",
            data: {
                ids: idArray.join(),
                bisPartIn: bisPartIn
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('操作成功');
                    programme.loadDeclareRecordList();
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

    //生成方案数据
    programme.generatorAreaGroup = function () {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeProgramme/generatorAreaGroup",
            type: "post",
            dataType: "json",
            data: {
                projectId: '${projectInfo.id}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("数据生成成功", 2, null, function () {
                        window.location.href = window.location.href;
                    })
                }
                else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
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

    //编辑其它信息
    programme.editOtherInfo = function (_this) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getJugdeObjectById',
            type: 'get',
            data: {
                judgeObjectId: $(_this).closest('tr').find("[data-name=id]").val()
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_other_info").clearAll().initForm(result.data);
                    $("#modal_other_info").modal();
                } else {
                    toastr.error(result.errmsg);
                }
            }
        })
    }

    //保存其它信息
    programme.saveOtherInfo = function () {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/updateSchemeJudgeObject',
            data: formParams("frm_other_info"),
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    $("#modal_other_info").modal('hide');
                } else {
                    toastr.error(result.errmsg);
                }
            }
        })
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
    var programmeMethod = {};

    programmeMethod.setMethod = function (_this) {
        programmeMethod.clean();
        var judgeObjectId = $(_this).closest("tr").find('[data-name="id"]').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getJudgeFunction',
            data: {
                judgeObjectId: judgeObjectId
            },
            success: function (result) {
                if (result.ret && result.data) {
                    $.each(result.data.judgeFunctions, function (i, item) {
                        if (item.bisApplicable == true) {
                            $("#frm_method_info").find('.btn-select-use[data-method-type=' + item.methodType + ']').trigger('click');
                            $("#applicableTbody tr[data-method-type=" + item.methodType + "]").find('textarea').val(item.applicableReason);
                        } else {
                            $("#notApplicableTbody tr[data-method-type=" + item.methodType + "]").find('textarea').val(item.notApplicableReason);
                        }
                    })
                }
            }
        })
        $("#frm_method_info").find('[name=judgeObjectId]').val(judgeObjectId);
        $("#modal_method_info").modal();
    }

    //基本方法选用
    programmeMethod.selectUseBase = function (_this) {
        //1.如果适用，添加适用tr，如果不适用移除适用tr
        //2.自动找出不适用的方法
        var useFlag = $(_this).attr('data-use-flag');
        var methodType = $(_this).attr('data-method-type');
        var methodName = $(_this).attr('data-method-name');
        var htmlTemplate = '<tr data-method-type="{methodType}">' +
            '<td>{methodName}</td><td> <select class="form-control" onchange="programmeMethod.methodTempChange(this);"></select></td>' +
            '<td><div class="x-valid"> <textarea class="form-control" name="{reasonName}{methodType}" required></textarea></div></td></tr>';

        if (useFlag == 'false') {
            var trHtml = new String(htmlTemplate);
            trHtml = trHtml.replace(/{methodType}/g, methodType).replace(/{methodName}/g, methodName).replace(/{reasonName}/g, "applicableReason");
            $("#applicableTbody").append(trHtml);
            programmeMethod.getMethodTemplate($("#applicableTbody").find('tr:last').find('select'), methodType);
            //不适用方法中移除该项
            $("#notApplicableTbody tr").each(function () {
                if ($(this).attr('data-method-type') == methodType) {
                    $(this).remove();
                }
            })
        } else {
            $("#applicableTbody tr").each(function () {
                if ($(this).attr('data-method-type') == methodType) {
                    $(this).remove();
                }
            })
        }
        $(_this).attr('data-use-flag', useFlag == 'false' ? true : false).toggleClass('btn-default').toggleClass('btn-success');
        //自动获取不适用方法
        $('#notApplicableTbody').empty();
        $("#baseMethodTd").find('.btn-select-use').each(function () {
            if ($(this).attr('data-use-flag') == 'false') {
                var trHtml = new String(htmlTemplate);
                trHtml = trHtml.replace(/{methodType}/g, $(this).attr('data-method-type')).replace(/{methodName}/g, $(this).attr('data-method-name')).replace(/{reasonName}/g, "notApplicableReason");
                $("#notApplicableTbody").append(trHtml);
                programmeMethod.getMethodTemplate($("#notApplicableTbody").find('tr:last').find('select'), $(this).attr('data-method-type'));
            }
        })
    }

    //其它方法选用
    programmeMethod.selectUseOther = function (_this) {
        var useFlag = $(_this).attr('data-use-flag');
        $(_this).attr('data-use-flag', useFlag == 'false' ? true : false).toggleClass('btn-default').toggleClass('btn-success');
    }

    //获取方法模板数据
    programmeMethod.getMethodTemplate = function ($element, methodType) {
        $.ajax({
            url: '${pageContext.request.contextPath}/evaluationMethod/getMethodList',
            type: 'get',
            data: {
                method: methodType
            },
            success: function (result) {
                if (result.ret) {
                    var html = '<option>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        html += ' <option value="' + item.id + '" data-applicableReason="' + item.applicableReason + '" data-notApplicableReason="' + item.notApplicableReason + '">' + item.name + '</option>';
                    })
                    $element.append(html);
                }
            }
        })
    }

    //方法模板change
    programmeMethod.methodTempChange = function (_this) {
        $(_this).closest('tr').find('textarea[name^=applicableReason]').val($(_this).find('option:selected').attr('data-applicableReason'));
        $(_this).closest('tr').find('textarea[name^=notApplicableReason]').val($(_this).find('option:selected').attr('data-notApplicableReason'));
    }

    //获取保存的数据
    programmeMethod.getMethodData = function () {
        var data = {};
        var judgeObjectId = $("#frm_method_info").find('[name=judgeObjectId]').val();
        data.judgeObjectId = judgeObjectId;
        data.notApplicableReason = $('#notApplicableReason').val();
        data.judgeFunctions = [];
        $("#applicableTbody tr").each(function () {
            var judgeFunction = {};
            judgeFunction.judgeObjectId = judgeObjectId;
            judgeFunction.name = $(this).find('td:eq(0)').text();
            judgeFunction.methodType = $(this).attr('data-method-type');
            judgeFunction.bisApplicable = true;
            judgeFunction.applicableReason = $(this).find('textarea').val();
            data.judgeFunctions.push(judgeFunction);
        })
        $("#otherMethodTd").find('[data-use-flag=true]').each(function () {
            var judgeFunction = {};
            judgeFunction.judgeObjectId = judgeObjectId;
            judgeFunction.name = $(this).attr('data-method-name');
            judgeFunction.methodType = $(this).attr('data-method-type');
            judgeFunction.bisApplicable = true;
            data.judgeFunctions.push(judgeFunction);
        })

        $("#notApplicableTbody tr").each(function () {
            var judgeFunction = {};
            judgeFunction.judgeObjectId = judgeObjectId;
            judgeFunction.name = $(this).find('td:eq(0)').text();
            judgeFunction.methodType = $(this).attr('data-method-type');
            judgeFunction.bisApplicable = false;
            judgeFunction.notApplicableReason = $(this).find('textarea').val();
            data.judgeFunctions.push(judgeFunction);
        })
        return data;
    }

    //保存的数据
    programmeMethod.saveJudgeFunction = function () {
        if ($("#applicableTbody tr").length <= 0) {
            Alert('还未选择适用的方法');
            return false;
        }
        if (!$("#frm_method_info").valid()) {
            return false;
        }
        var data = programmeMethod.getMethodData();
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/saveJudgeFunction',
            data: {
                formData: JSON.stringify(data)
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    Loading.progressHide();
                    $("#modal_method_info").modal('hide');
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //清空数据
    programmeMethod.clean = function () {
        $("#frm_method_info").find('[name=judgeObjectId]').val('');
        $("#frm_method_info").find('.btn-select-use').attr('data-use-flag', false).addClass('btn-default').removeClass('btn-success');
        $('#applicableTbody,#notApplicableTd').empty();
        $('#notApplicableReason').val('');
    }
</script>



