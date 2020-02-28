<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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

                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${declareRecord.name}</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_asset" class="form-horizontal">
                                    <input type="hidden" name="id" value="${surveyAssetInventory.id}">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    核对日期<span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"><fmt:formatDate
                                                            value="${surveyAssetInventory.checkDate}" pattern="yyyy-MM-dd"/></label>

                                                </div>
                                                <label class="col-sm-1 col-form-label">是否查看原件<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"> ${surveyAssetInventory.bisCheckOriginal eq true? '是':'否'}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    分割限制<span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.segmentationLimit}</label>
                                                </div>
                                                <div class="showUse"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group showCertificate">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    能否使用
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.canUse}</label>
                                                </div>

                                                <label class="col-sm-1 col-form-label">
                                                    证载用途
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.applicationName}</label>

                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    是否办证</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.certificateName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    证明文件
                                                </label>
                                                <div class="col-sm-3">
                                                    <div id="_checkOriginalFile"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-11">
                                                    <label class="form-control input-full">${surveyAssetInventory.remark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- 清查内容 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        清查内容
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_asset_inventory_content" class="form-horizontal">
                                    <table class="table" id="tb_List">
                                        <thead>
                                        <tr>
                                            <th style="width: 10%">一致性内容</th>
                                            <th style="width: 10%">登记</th>
                                            <th style="width: 10%">实际</th>
                                            <th style="width: 6%">是否一致</th>
                                            <th style="width: 10%">差异原因</th>
                                            <th style="width: 10%">证明文件</th>
                                            <th style="width: 10%">证明文件附件</th>
                                            <th style="width: 5%">证明人</th>
                                            <th style="width: 8%">调查时间</th>
                                            <th style="width: 6%">确认一致</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                                        <tr>
                                            <td>
                                                <label class="form-control input-full">${item.inventoryContentName}</label>
                                            </td>
                                            <td>
                                                <label class="form-control input-full">${item.registration}</label>
                                            </td>
                                            <td>
                                                <label class="form-control input-full">${item.actual}</label>
                                            </td>
                                            <td>
                                                <label class="form-control input-full">${item.areConsistent}</label>
                                            </td>
                                            <c:if test="${item.areConsistent eq '不一致'}">
                                                <td>
                                                    <label class="form-control input-full">${item.differenceReason}</label>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">${item.credential}</label>
                                                </td>
                                                <td>
                                                    <div id="_credentialAccessory${item.id}"></div>
                                                    <script type="text/javascript">
                                                        $(function () {
                                                            //清查内容附件加载
                                                            showFileCommon("${item.id}");
                                                        })
                                                    </script>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">${item.voucher}</label>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">
                                                        <fmt:formatDate value="${item.surveyTime}" pattern="yyyy-MM-dd"/>
                                                    </label>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">${item.sureConsistent}</label>
                                                </td>
                                            </c:if>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>


                    <!-- 税费、工程、物管欠款调查 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        税费、工程、物管欠款调查
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form id="taxesPaymentSurvey" class="form-horizontal">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    缴纳情况
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.paymentStatus}
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="paymentItem">
                                    </div>
                                    <div class="row form-group" id="showUploadFile" style="display: none">
                                        <div class="col-md-12">
                                            <div class="form-inline  x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    附件
                                                </label>
                                                <div class="col-sm-11">
                                                    <div id="_paymentStatusFile"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- 损坏调查表 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        损坏调查表
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form id="damageSurvey" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    区位是否损坏
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.rimIsNormal}
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="zoneBit">

                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    实物状况是否损坏
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.entityIsDamage}
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="entity">

                                    </div>
                                    <c:if test="${projectInfo.projectCategoryId == houseLand}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        影响评估的其他事项
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <div class="btn btn-xs btn-success"
                                                             onclick="appendHTML('otherProjectName','otherProjectItem','otherProject',this)">
                                                            <i class="fa fa-plus"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="otherProject">

                                        </div>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- 转让限制 start -->
                    <c:if test="${projectInfo.projectCategoryId != houseLand}">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                            转让限制
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-body">
                                    <form class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1  col-form-label">
                                                        转让限制
                                                    </label>
                                                    <div class="col-sm-11">
                                                        <label class="form-control input-full">${surveyAssetInventory.transferLimit}
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <%@include file="/views/share/form_approval.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

<%--<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>
                        ${projectPlanDetails.projectPhaseName}
                        <small>${declareRecord.name}</small>
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">核对日期</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full"><fmt:formatDate
                                            value="${surveyAssetInventory.checkDate}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">是否查看原件</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full"> ${surveyAssetInventory.bisCheckOriginal eq true? '是':'否'}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <button type="button" class="btn btn-xs btn-warning" onclick="checkRealEstate()"
                                            data-toggle="modal"> 查看权证信息
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分割限制</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full">${surveyAssetInventory.segmentationLimit}</label>
                                </div>
                            </div>
                            <div id="showUse" style="display: none">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">能否使用</label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <label class="form-control input-full">${surveyAssetInventory.canUse}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载用途</label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <label class="form-control input-full">${surveyAssetInventory.applicationName}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="showCertificate" style="display: none;">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">是否办证</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full">${surveyAssetInventory.certificateName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证明文件</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div id="_checkOriginalFile"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">说明</label>
                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                    <label class="form-control input-full">${surveyAssetInventory.remark}</label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>清查内容</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table class="table" id="tb_List">
                        <thead>
                        <tr>
                            <th style="width: 10%">一致性内容</th>
                            <th style="width: 10%">登记</th>
                            <th style="width: 10%">实际</th>
                            <th style="width: 6%">是否一致</th>
                            <th style="width: 10%">差异原因</th>
                            <th style="width: 10%">证明文件</th>
                            <th style="width: 10%">证明文件附件</th>
                            <th style="width: 5%">证明人</th>
                            <th style="width: 8%">调查时间</th>
                            <th style="width: 6%">确认一致</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                        <tr>
                            <td>
                                <label class="form-control input-full">${item.inventoryContentName}</label>
                            </td>
                            <td>
                                <label class="form-control input-full">${item.registration}</label>
                            </td>
                            <td>
                                <label class="form-control input-full">${item.actual}</label>
                            </td>
                            <td>
                                <label class="form-control input-full">${item.areConsistent}</label>
                            </td>
                            <c:if test="${item.areConsistent eq '不一致'}">
                                <td>
                                    <label class="form-control input-full">${item.differenceReason}</label>
                                </td>
                                <td>
                                    <label class="form-control input-full">${item.credential}</label>
                                </td>
                                <td>
                                    <div id="_credentialAccessory${item.id}"></div>
                                    <script type="text/javascript">
                                        $(function () {
                                            //清查内容附件加载
                                            showFileCommon("${item.id}");
                                        })
                                    </script>
                                </td>
                                <td>
                                    <label class="form-control input-full">${item.voucher}</label>
                                </td>
                                <td>
                                    <label class="form-control input-full">
                                        <fmt:formatDate value="${item.surveyTime}" pattern="yyyy-MM-dd"/>
                                    </label>
                                </td>
                                <td>
                                    <label class="form-control input-full">${item.sureConsistent}</label>
                                </td>
                            </c:if>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>税费、工程、物管欠款调查</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="taxesPaymentSurvey" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    缴纳情况
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full">${surveyAssetInventory.paymentStatus}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="paymentItem">

                        </div>
                        <div class="form-group" id="showUploadFile" style="display: none">
                            <div class="x-valid">
                                <label class=" ol-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    附件
                                </label>
                                <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                    <div id="_paymentStatusFile"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>损坏调查表</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="damageSurvey" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    区位是否损坏
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full">${surveyAssetInventory.rimIsNormal}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="zoneBit">

                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    实物状况是否损坏
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control input-full">${surveyAssetInventory.entityIsDamage}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="entity">

                        </div>
                        <c:if test="${projectInfo.projectCategoryId == houseLand}">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        影响评估的其他事项
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    </div>
                                </div>
                            </div>
                            <div class="otherProject">

                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <c:if test="${projectInfo.projectCategoryId != houseLand}">
                <div class="x_panel">
                    <div class="x_title">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>转让限制</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        转让限制
                                    </label>
                                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                        <label class="form-control input-full">${surveyAssetInventory.transferLimit}
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>--%>
</body>


<%@include file="/views/project/stageSurvey/certificate.jsp" %>
<script type="application/javascript">
    $(function () {
        loadAssetOtherRightList();

        FileUtils.getFileShows({
            target: "checkOriginalFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_CHECK_ORIGINAL,
                tableId: '${surveyAssetInventory.id}'
            },
            deleteFlag: false
        })

        FileUtils.getFileShows({
            target: "paymentStatusFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_PAYMENT_STATUS,
                tableId: '${surveyAssetInventory.id}'
            },
            deleteFlag: false
        })

        if ("${surveyAssetInventory}") {
            showOther();

            writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', ${surveyAssetInventory.zoneDamage});
            writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', ${surveyAssetInventory.entityDamage});
            writeHTMLData('otherProjectName', 'otherProjectItem', 'otherProject', ${surveyAssetInventory.otherProject});
            writePaymentHTMLData(${surveyAssetInventory.paymentContent});
        }
    })

    //获取对应房产证信息
    function checkRealEstate() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRecord/getCertificateId",
            type: "get",
            dataType: "json",
            data: {declareRecordId: "${projectPlanDetails.declareRecordId}"},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    certificate.prototype.getAndInit(result.data.dataTableName, result.data.dataTableId);
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //显示附件通用
    function showFileCommon(tableId) {
        FileUtils.getFileShows({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryContent,
                tableId: tableId
            },
            deleteFlag: false
        })
    }

    function saveform() {
        saveApprovalform("");
    }

    //加载他项权利附件
    function loadInventoryRightFile(tableId) {
        FileUtils.getFileShows({
            target: "inventoryRightFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryRight,
                tableId: tableId
            },
            deleteFlag: true
        });
    }

    function loadAssetOtherRightList() {
        var cols = [];
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
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="viewInventoryRightInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_inventory_right_list").bootstrapTable('destroy');
        TableInit("tb_inventory_right_list", "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //查看他项信息
    function viewInventoryRightInfo(index) {
        var row = $("#tb_inventory_right_list").bootstrapTable('getData')[index];
        $("#viewInventoryRightModal").find('[data-name]').each(function () {
            $(this).text('').text(row[$(this).attr('data-name')]);
        })
        loadInventoryRightFile(row.id);
        $("#viewInventoryRightModal").find('[data-name=registerDate]').text(formatDate(row.registerDate, false));
        $("#viewInventoryRightModal").find('[data-name=beginDate]').text(formatDate(row.beginDate, false));
        $("#viewInventoryRightModal").find('[data-name=endDate]').text(formatDate(row.endDate, false));
        $("#viewInventoryRightModal").modal();

    };

    function showOther() {
        if ("${surveyAssetInventory.segmentationLimit}" == "可分") {
            $(".showCertificate").show();
            $("#showUse").show();
        } else {
            $(".showCertificate").hide();
            $("#showUse").hide();

        }
        if ("${surveyAssetInventory.paymentStatus}" == "不正常") {
            $("#showUploadFile").show();
        } else {
            $("#showUploadFile").hide();
        }
    }

    function writeHTMLData(projectName, projectItem, item, json) {
        $("." + item).empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='row form-group' >";
            html += " <div class='col-md-12'>";
            html += "<div class='form-inline x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n[projectName];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "明细" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n[projectItem];
            html += "</label>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("." + item).append(html);
        })
    }

    function writePaymentHTMLData(json) {
        $(".paymentItem").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='row form-group' >";
            html += " <div class='col-md-12'>";
            html += "<div class='form-inline x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n["projectName"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "类型" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n["remark"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "金额" + "</label>";
            html += "<div class='col-sm-2'>";
            html += "<label class='form-control input-full'>" + n["money"];
            html += "</div>";


            html += "</div>";
            html += "</div>";
            html += "</div>";
            $(".paymentItem").append(html);
        })
    }

</script>
</body>
</html>

