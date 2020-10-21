<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <c:forEach items="${areaGroups}" var="item">
                        <div class="col-md-12">
                            <div class="x_panel card area_panel">
                                <input type="hidden" name="areaGroupId" value="${item.id}">
                                <div class="x_title card-header collapse-link"
                                     onclick="programme.loadJudgeObjectList(this);">
                                    <div class="card-head-row">
                                        <div class="card-title">${item.areaName}</div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="x_content card-body collapse">
                                    <form id="frmJudgeObject${item.id}" class="form-horizontal">
                                        <div class="row form-group form-inline">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                委托目的
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.entrustPurposeName}</label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                委托目的类别
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.entrustAimTypeName}</label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                委托目的描述
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.remarkEntrustPurpose}</label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                委托目的限制
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.entrustPurposeLimit}</label>
                                            </div>
                                        </div>
                                        <div class="row form-group form-inline">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                评估基准日
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">
                                                    <fmt:formatDate value="${item.valueTimePoint}"
                                                                    pattern="yyyy-MM-dd"/>
                                                </label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                基准日说明
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.timePointExplain}</label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                价值类型
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.valueDefinitionName}</label>
                                            </div>
                                        </div>
                                        <div class="row form-group form-inline">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                价值类型描述
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <label class="form-control input-full">${item.valueDefinitionDesc}</label>
                                            </div>
                                        </div>
                                        <div class="row form-group form-inline">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                财产范围
                                            </label>
                                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                <label class="form-control input-full">${item.propertyScopeName}</label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                财产包括
                                            </label>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <label class="form-control input-full">${item.scopeInclude}</label>
                                            </div>
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                财产不包括
                                            </label>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <label class="form-control input-full">${item.scopeNotInclude}</label>
                                            </div>
                                        </div>
                                        <c:if test="${projectCategory eq 'land'}">
                                            <div class="row form-group form-inline">
                                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                    最佳利用方式
                                                </label>
                                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                    <label class="form-control input-full">${item.bestUse}</label>
                                                </div>
                                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                    最佳利用描述
                                                </label>
                                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                    <label class="form-control input-full">${item.bestUseDesc}</label>
                                                </div>
                                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                    宗地现状
                                                </label>
                                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                    <label class="form-control input-full">${item.currentSituation}</label>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="judge-object-content"></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<div id="divBoxMethodExtend" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><h4>评估方法</h4></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
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
                                                       readonly="readonly"
                                                       name="bisApplicable" id="rdoApplicable${method.id}" value="true">
                                                <label for="rdoApplicable${method.id}">适用</label>
                                                </span>

                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,false)"
                                                       readonly="readonly"
                                                       name="bisApplicable" id="rdoNotApplicable${method.id}"
                                                       value="false">
                                                <label for="rdoNotApplicable${method.id}">不适用</label>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="applicable" style="display: none;">
                                        <div class="well">
                                            <div class="form-group ">
                                                <label class="col-sm-2 control-label">
                                                    方法适用原因
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
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    思路适用原因
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                        <textarea required placeholder="思路适用原因" name="applicableThinking"
                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="applicableThinking-field"></div>
                                        </div>
                                    </div>
                                    <div class="not-applicable" style="display: none;">
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    方法不适用原因
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
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    思路不适用原因
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                                        <textarea required placeholder="思路不适用原因"
                                                                  name="notApplicableThinking"
                                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="notApplicableThinking-field"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
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
                <div class="x_content card-body">

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
<div id="modal_method_info" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><h4>评估方法</h4></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frm_method_info" class="form-horizontal">
                    <input type="hidden" name="judgeObjectId">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th width="30%">适用方法</th>
                                    <th width="70%">适用原因</th>
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
                                    <th width="30%">不适用方法</th>
                                    <th width="70%">不适用原因</th>
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
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<!--评估对象-->
<script type="text/html" id="judgeObjectHtml">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card x_panel">
                <div class="card-header">
                    <div class="card-head-row">
                        <div class="card-title">
                            <input type="checkbox">
                            <label style="word-break: break-all">{mergeNumber}</label>
                            <button type="button" href="javascript://" onclick="programmeMethod.setMethod(this);"
                                    class="btn btn-md btn-info judge-method tooltips">评估方法
                            </button>
                            <small></small>
                        </div>
                        <div class="card-tools">
                            <button type="button" class="btn  btn-link btn-primary btn-xs collapse-link"><span
                                    class="fa fa-angle-down"></span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card-body x_content">
                    <div class="form-horizontal">
                        <input type="hidden" data-name="id" value="{id}">
                        <input type="hidden" data-name="bisSplit" value="{bisSplit}">
                        <input type="hidden" data-name="bisMerge" value="{bisMerge}">
                        <input type="hidden" data-name="number" value="{number}">
                        <input type="hidden" data-name="splitNumber" value="{splitNumber}">
                        <input type="hidden" data-name="declareId" value="{declareId}">
                        <input type="hidden" data-name="rentalPossessionDesc" value="{rentalPossessionDesc}">
                        <input type="hidden" data-name="mergeNumber" value="{mergeNumber}">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <label class="col-sm-1 control-label">
                                        所有权人
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control input-full" data-name="ownership">{ownership}
                                            <button type="button" href="javascript://"
                                                    onclick="programme.viewJudgeInfo(this);"
                                                    class="btn btn-xs btn-info tooltips"><i
                                                    class="fa fa-white fa-search"></i></button>
                                        </label>
                                    </div>
                                    <label class="col-sm-1 control-label">
                                        坐落
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control input-full"
                                               data-name="seat"><span>{seat}</span></label>
                                    </div>
                                    <label class="col-sm-1 control-label">
                                        终止日期
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control input-full"
                                               data-name="landUseEndDate"><span>{landUseEndDate}</span></label>
                                    </div>
                                    <label class="col-sm-1 control-label">
                                        剩余年限
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control input-full"
                                               data-name="landRemainingYear"><span>{landRemainingYear}</span></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <label class="col-sm-1 control-label">
                                        证载用途
                                    </label>
                                    <div class="col-sm-2 x-valid">
                                        <label class="form-control input-full" data-name="certUse">{certUse}</label>
                                    </div>
                                    <label class="col-sm-1 control-label">
                                        实际用途
                                    </label>
                                    <div class="col-sm-2 x-valid">
                                        <label class="form-control input-full"
                                               data-name="practicalUse">{practicalUse}</label>
                                    </div>
                                    <c:choose>
                                        <c:when test="${projectCategory eq 'land'}">
                                            <label class="col-sm-1 control-label">
                                                设定用途类型
                                            </label>
                                            <div class="col-sm-2 x-valid">
                                                <label class="form-control input-full">{setUseClassifyName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                设定用途类别
                                            </label>
                                            <div class="col-sm-2 x-valid">
                                                <label class="form-control input-full">{setUseName}</label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="col-sm-1 control-label">
                                                设定用途
                                            </label>
                                            <div class="col-sm-2 x-valid">
                                                <label class="form-control input-full">{setUseName}</label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <c:if test="${projectCategory ne 'land'}">
                                        <label class="col-sm-1 control-label">
                                            最佳利用方式
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full">{bestUseName}</label>
                                        </div>
                                    </c:if>
                                    <label class="col-sm-1 control-label">
                                        证载面积
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control input-full">{floorArea}</label>
                                    </div>
                                    <label class="col-sm-1 control-label">
                                        评估面积
                                    </label>
                                    <div class="col-sm-2 x-valid">
                                        <label class="form-control input-full">{evaluationArea}</label>
                                    </div>
                                    <label class="col-sm-1 control-label" data-name="evaluationNumberContainer{id}"
                                           style="display: none;">
                                        评估数量({evaluationNumberUnit})
                                    </label>
                                    <div class="col-sm-2 x-valid" data-name="evaluationNumberContainer{id}"
                                         style="display: none;">
                                        <label class="form-control input-full">{evaluationNumber}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group" data-name="mergeExplainContainer{id}" style="display: none;">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <label class="col-sm-1 control-label">
                                        合并对象说明
                                    </label>
                                    <div class="col-sm-11 x-valid">
                                        <label class="form-control input-full">{mergeExplain}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group" data-name="splitExplainContainer{id}" style="display: none;">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <label class="col-sm-1 control-label">
                                        拆分对象说明
                                    </label>
                                    <div class="col-sm-11 x-valid">
                                        <label class="form-control input-full">{splitExplain}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${projectCategory eq 'land'}">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline">
                                        <label class="col-sm-1 control-label">
                                            规划容积率
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full" data-name="planPlotRatio">{planPlotRatio}</label>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            实际容积率
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full" data-name="actualPlotRatio">{actualPlotRatio}</label>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            设定容积率
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full" data-name="setPlotRatio">{setPlotRatio}</label>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            宗地外实际开发程度
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full" data-name="parcelOuterDevelopName">{parcelOuterDevelopName}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline">
                                        <label class="col-sm-1 control-label">
                                            宗地内实际开发程度
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full" data-name="parcelInnerDevelopName">{parcelInnerDevelopName}</label>
                                        </div>

                                        <label class="col-sm-1 control-label">
                                            宗地内设定开发程度
                                        </label>
                                        <div class="col-sm-2 x-valid">
                                            <label class="form-control input-full"
                                                   data-name="parcelSettingInnerDevelopName">{parcelSettingInnerDevelopName}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/assets/layer/layer.js?v=${assessVersion}"></script>

<script type="text/javascript">

    $(function () {
        $(".area_panel .card-header").each(function () {
            $(this).trigger('click');
        })
        programme.loadInventoryRightList();

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

    programme.loadJudgeObjectList = function (_this) {
        var tbody = $(_this).closest(".area_panel").find(".judge-object-content");
        tbody.empty();
        var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
        var data = {areaGroupId: areaGroupId};
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeProgramme/getSchemeJudgeObjectList",
            data: data,
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
                        html = html.replace(/{evaluationNumber}/g, item.evaluationNumber == undefined ? "" : item.evaluationNumber);
                        html = html.replace(/{evaluationNumberUnit}/g, item.evaluationNumberUnit == undefined ? "" : item.evaluationNumberUnit);
                        html = html.replace(/{setPlotRatio}/g, item.setPlotRatio == undefined ? "" : item.setPlotRatio);
                        html = html.replace(/{planPlotRatio}/g, item.planPlotRatio == undefined ? "" : item.planPlotRatio);
                        html = html.replace(/{actualPlotRatio}/g, item.actualPlotRatio == undefined ? "" : item.actualPlotRatio);
                        html = html.replace(/{setUseName}/g, item.setUseName == undefined ? "" : item.setUseName);
                        html = html.replace(/{setUseClassifyName}/g, item.setUseClassifyName == undefined ? "" : item.setUseClassifyName);
                        html = html.replace(/{bestUseName}/g, item.bestUseName == undefined ? "" : item.bestUseName);
                        html = html.replace(/{mergeExplain}/g, item.mergeExplain == undefined ? "" : item.mergeExplain);
                        html = html.replace(/{splitExplain}/g, item.splitExplain == undefined ? "" : item.splitExplain);
                        html = html.replace(/{currentSituation}/g, item.currentSituation == undefined ? "" : item.currentSituation);
                        html = html.replace(/{parcelOuterDevelopName}/g, item.parcelOuterDevelopName == undefined ? "" : item.parcelOuterDevelopName);
                        html = html.replace(/{parcelInnerDevelopName}/g, item.parcelInnerDevelopName == undefined ? "" : item.parcelInnerDevelopName);
                        html = html.replace(/{parcelSettingInnerDevelopName}/g, item.parcelSettingInnerDevelopName == undefined ? "" : item.parcelSettingInnerDevelopName);

                        tbody.append(html);
                        //设值
                        var lastTr = tbody.find(".x_panel:last");
                        if (item.evaluationNumber) {
                            lastTr.find('[data-name=evaluationNumberContainer' + item.id + ']').show();
                        }
                        if (item.bisMerge) {
                            lastTr.find('[data-name=mergeExplainContainer' + item.id + ']').show();
                        }
                        if (!item.bisSplit && item.splitNumber) {
                            lastTr.find('[data-name=splitExplainContainer' + item.id + ']').show();
                        }
                        if (item.bisSetFunction) {
                            lastTr.find('.x_title').find('.judge-method').removeClass('btn-success').addClass('btn-primary');
                        }
                        var desc = '';
                        if (item.standardNumber) {
                            desc += "【" + item.standardNumber + "号】";
                        }
                        if (item.surveyObjectName) {
                            desc += "【" + item.surveyObjectName + "】";
                        }
                        lastTr.find('.card-title').find('small').text(desc);
                    })
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
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
        cols.push({field: 'evaluationArea', title: '评估面积'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-info tooltips" data-placement="top"  onclick="programme.viewJudgeDetailExamineInfo(' + index + ')"><i class="fa fa-search fa-white"></i></button>';
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
        var tr = $(_this).closest('.x_panel');
        var bisMerge = tr.find('[data-name=bisMerge]').val();
        if (bisMerge == 'true') {
            var pid = tr.find('[data-name=id]').val();
            programme.loadJudgeDetailList(pid);
            $("#viewMergeJudgeModal").modal();
        } else {
            programme.viewExamineInfo(tr.find('[data-name=declareId]').val());
        }
    };

    //查看其它信息
    programme.viewOtherInfo = function (_this) {
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getJugdeObjectById',
            type: 'get',
            data: {
                judgeObjectId: $(_this).closest('tr').find("[data-name=id]").val()
            },
            success: function (result) {
                if (result.ret) {
                    $("#frm_other_info").clearAll().initForm(result.data);
                    $("#modal_other_info").modal();
                } else {
                    notifyInfo('错误', result.errmsg);
                }
            }
        })
    }

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
                                            s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' ><i class='fa fa-search fa-white'></i></a>";
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
        cols.push({field: 'categoryName', title: '类型'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
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

    //查看描述内容
    programme.viewRentalPossessionDesc = function (_this) {
        var rentalPossessionDesc = $(_this).closest('tr').find('[data-name=rentalPossessionDesc]').val();
        layer.open({
            title: '出租或占用情况描述'
            , content: rentalPossessionDesc
        });
    }


    var programmeMethod = {};

    programmeMethod.setMethod = function (_this) {
        $('#applicableTbody,#notApplicableTbody').empty();
        var judgeObjectId = $(_this).closest(".x_panel").find('[data-name="id"]').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getJudgeFunction',
            data: {
                judgeObjectId: judgeObjectId
            },
            success: function (result) {
                if (result.ret && result.data) {
                    var htmlTemplate = '<tr data-method-type="{methodType}"><td>{methodName}</td>' +
                        '<td><div class="x-valid"> <label class="form-control">{content}</label></div></td></tr>';
                    $.each(result.data.judgeFunctions, function (i, item) {
                        var trHtml = new String(htmlTemplate);
                        trHtml = trHtml.replace(/{methodType}/g, item.methodType).replace(/{methodName}/g, item.name);
                        if (item.bisApplicable == true) {
                            $("#applicableTbody").append(trHtml.replace(/{content}/g, AssessCommon.toString(item.applicableReason)));
                        } else {
                            $("#notApplicableTbody").append(trHtml.replace(/{content}/g, AssessCommon.toString(item.notApplicableReason)));
                        }
                    })
                }
            }
        })
        $("#modal_method_info").modal();
    }
</script>
<script type="application/javascript">


    function saveform() {
        saveApprovalform("");
    }
</script>
</html>

