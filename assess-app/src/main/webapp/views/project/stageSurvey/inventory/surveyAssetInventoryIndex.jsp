<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row mt--2">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${masterName} 清查业务
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
                                    <input type="hidden" name="influenceFactorRemarkText"
                                           value="${surveyAssetInventory.influenceFactorRemarkText}">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">查看原件<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                     <span class="radio-inline">
                                                     <div class="input-group">
                                                         <span class="input-group-addon">
                                                          <input type="radio" name="bisCheckOriginal" value="1">是
                                                             <input type="radio" checked="checked"
                                                                    name="bisCheckOriginal"
                                                                    value="0">否
                                                              </span>
                                                          </div>
                                                      </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group bisCheckOriginalZero">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">


                                                <label class="col-sm-1 col-form-label">
                                                    查看方法
                                                </label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full" name="findMethod">
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 control-label">
                                                    查看结果附件
                                                </label>
                                                <div class="col-sm-3">
                                                    <input id="networkFindFile" type="file" multiple="false">
                                                    <div id="_networkFindFile"></div>
                                                </div>

                                                <label class="col-sm-1 control-label">
                                                    查看说明
                                                </label>
                                                <div class="col-sm-3">
                                           <textarea placeholder="查询说明" class="form-control input-full"
                                                     name="networkRemark">${surveyAssetInventory.networkRemark}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group bisCheckOriginalOne" style="display: none;">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    核对日期<span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" required placeholder="核对日期"
                                                           name="checkDate"
                                                           data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate"
                                                           readonly="readonly"
                                                           value="<fmt:formatDate value='${surveyAssetInventory.checkDate}' pattern='yyyy-MM-dd'/>">

                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    证明文件
                                                </label>
                                                <div class="col-sm-3">
                                                    <input id="checkOriginalFile" type="file" multiple="false">
                                                    <div id="_checkOriginalFile"></div>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                           <textarea placeholder="说明" class="form-control input-full"
                                                     name="remark">${surveyAssetInventory.remark}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group" data-name="findMethod" style="display: none;">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    查询的地址
                                                </label>
                                                <div class="col-sm-3">
                                                    <input class="form-control input-full" placeholder="查询的地址"
                                                           value="${surveyAssetInventory.networkAddress}"
                                                           name="networkAddress">
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                        color="#6f5499" size="10"/>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    分割限制<span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full"
                                                            name="segmentationLimit"
                                                            required>
                                                        <option value="可分">可分</option>
                                                        <option value="不可分" selected>不可分</option>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    影响对象
                                                </label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full search-select select2 "
                                                            name="affected" multiple="multiple">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    影响要素
                                                </label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full search-select select2"
                                                            name="influenceFactor" multiple="multiple">
                                                    </select>
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
                                    <table id="tb_surveyList">
                                        <thead>
                                        <tr>
                                            <th style="width: 6%">是否一致</th>
                                            <th style="width: 10%">一致性内容</th>
                                            <th style="width: 10%">登记<span
                                                    style="color:red;font-size:10px;">(没有登记信息则填无)</span></th>
                                            <th style="width: 10%">实际</th>
                                            <th style="width: 10%">差异原因</th>
                                            <th style="width: 10%">证明文件</th>
                                            <th style="width: 7%">证明文件附件</th>
                                            <th style="width: 7%">证明人</th>
                                            <th style="width: 8%">调查时间</th>
                                            <th style="width: 8%">确认一致</th>
                                            <th style="width: 6%">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                                            <tr>
                                                <td>
                                                    <div class="x-valid ">

                                                        <div class="form-check" style="justify-content:left">
                                                            <label class="form-check-label">
                                                                <input class="form-check-input" type="checkbox"
                                                                       id="areConsistent${item.id}"
                                                                       onclick="survey.areConsistentEvent(this) ;"
                                                                       name="areConsistent${item.id}"
                                                                       value="一致" ${item.areConsistent eq '一致'?'checked':''} ${empty item.areConsistent ?'checked':''}>
                                                                <span class="form-check-sign">一致</span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </td>

                                                <input type="hidden" name="id" value="${item.id}">

                                                <td>${item.inventoryContentName}</td>

                                                <td>
                                                    <div class="x-valid show-hide">
                                                        <input type="text" data-rule-maxlength="50" placeholder="登记"
                                                               required
                                                               id="registration${item.id}"
                                                               onblur="survey.isAgreement(this);"
                                                               name="registration${item.id}" data-name="registration"
                                                               class="form-control input-full "
                                                               value="${item.registration}">
                                                    </div>
                                                </td>

                                                <td>
                                                    <div class="x-valid show-hide">
                                                        <input type="text" data-rule-maxlength="50" placeholder="实际"
                                                               required
                                                               id="actual${item.id}"
                                                               onblur="survey.isAgreement(this);"
                                                               name="actual${item.id}" data-name="actual"
                                                               class="form-control input-full "
                                                               value="${item.actual}">
                                                    </div>
                                                </td>

                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="50" placeholder="差异原因"
                                                               required
                                                               id="differenceReason${item.id}"
                                                               name="differenceReason${item.id}"
                                                               class="form-control input-full"
                                                               value="${item.differenceReason}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="50" placeholder="证明文件"
                                                               required
                                                               id="credential${item.id}" name="credential"
                                                               class="form-control input-full"
                                                               value="${item.credential}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input id="credentialAccessory${item.id}"
                                                               name="credentialAccessory${item.id}" type="file"
                                                               multiple="false">
                                                        <div id="_credentialAccessory${item.id}"></div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="50" placeholder="证明人"
                                                               required
                                                               id="voucher${item.id}" name="voucher${item.id}"
                                                               class="form-control input-full" value="${item.voucher}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input placeholder="调查时间" id="surveyTime${item.id}"
                                                               name="surveyTime${item.id}" required
                                                               data-date-format="yyyy-mm-dd"
                                                               class="form-control input-full date-picker dbdate"
                                                               readonly="readonly"
                                                               value='<fmt:formatDate value="${item.surveyTime}" pattern="yyyy-MM-dd"/>'>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <select class="form-control input-full"
                                                                id="sureConsistent${item.id}"
                                                                name="sureConsistent${item.id}" required>
                                                            <option value=""></option>
                                                            <option value="一致" ${item.sureConsistent eq '一致'?'selected':''}>
                                                                一致
                                                            </option>
                                                            <option value="不一致" ${item.sureConsistent eq '不一致'?'selected':''}>
                                                                不一致
                                                            </option>
                                                        </select>
                                                    </div>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-xs btn-danger"
                                                            onclick="$(this).closest('tr').find('input').val('') ;">清空
                                                    </button>
                                                </td>
                                            </tr>
                                            <script type="text/javascript">
                                                $(function () {
                                                    //清查内容附件上传和加载
                                                    survey.uploadFileCommon("${item.id}");
                                                    survey.showFileCommon("${item.id}");
                                                    survey.initAgreement('#areConsistent${item.id}');
                                                })
                                            </script>
                                        </c:forEach>

                                        </tbody>
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
                                                    <select class="form-control input-full" id="paymentStatus"
                                                            name="paymentStatus" required
                                                            onchange="showButton()">
                                                        <option value="">请选择</option>
                                                        <option value="正常" selected="selected">正常</option>
                                                        <option value="不正常">不正常</option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 col-form-label showPaymentAdd"
                                                       style="display: none">
                                                    明细新增
                                                </label>
                                                <div class="col-sm-3 showPaymentAdd" style="display: none">
                                                    <div class="btn btn-xs btn-success"
                                                         onclick="appendPaymentHTML(this)"><i
                                                            class="fa fa-plus"></i></div>
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
                                                    <input id="paymentStatusFile" name="house_estateParking"
                                                           placeholder="上传附件" class="form-control input-full"
                                                           type="file">
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
                                                    <select class="form-control input-full" id="rimIsNormal"
                                                            name="rimIsNormal" required
                                                            onchange="showButton()">
                                                        <option value="">请选择</option>
                                                        <option value="正常" selected="selected">正常</option>
                                                        <option value="不正常">不正常</option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 col-form-label showZoneAdd" style="display:none">
                                                    区位损坏新增
                                                </label>
                                                <div class="col-sm-3 showZoneAdd" style="display:none">
                                                    <div class="btn btn-xs btn-success"
                                                         onclick="appendHTML('zoneProjectName','zoneProjectItem','zoneBit',this)">
                                                        <i class="fa fa-plus"></i></div>
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
                                                    <select class="form-control input-full" id="entityIsDamage"
                                                            name="entityIsDamage" required
                                                            onchange="showButton()">
                                                        <option value="">请选择</option>
                                                        <option value="损坏">损坏</option>
                                                        <option value="未损坏" selected="selected">未损坏</option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 col-form-label showEntityAdd"
                                                       style="display:none">
                                                    实物损坏新增
                                                </label>
                                                <div class="col-sm-3 showEntityAdd" style="display:none">
                                                    <div class="btn btn-xs btn-success"
                                                         onclick="appendHTML('entityProjectName','entityProjectItem','entity',this)">
                                                        <i class="fa fa-plus"></i></div>
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
                                                        是否有转让限制
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <select id="bisLimit"
                                                                class='form-control input-full'
                                                                onchange="showLimit()">
                                                            <option value="请选择">-请选择-</option>
                                                            <option value="是">是</option>
                                                            <option value="否">否</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <button type="button" class="btn btn-sm btn-success"
                                                                onclick="survey.findDeclareRecord() ;">
                                                            权证查看
                                                        </button>
                                                        <button type="button" class="btn btn-sm btn-success"
                                                                onclick="survey.findRightDetail();">
                                                            他项权力查看
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group showLimit">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1  col-form-label">
                                                        转让限制
                                                    </label>
                                                    <div class="col-sm-11">
                                    <textarea placeholder="转让限制" name="transferLimit" id="transferLimit"
                                              class="form-control input-full"
                                              value="${surveyAssetInventory.transferLimit}"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>


                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script type="text/html" id="influenceFactorRemarkTextHtml">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 col-form-label">
                    {name}说明
                </label>
                <div class="col-sm-10">
                    <textarea data-name="influenceFactorRemarkText" name="influenceFactorRemarkText{id}"
                              class="form-control input-full" placeholder="{name}说明"></textarea>
                </div>
                <div class="col-sm-1"><input class="btn btn-warning" type="button" value="X"
                                             onclick="survey.influenceFactorEvent(this,'{id}') ;"></div>
            </div>
        </div>
    </div>
</script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="application/javascript">

    var survey = {};

    survey.frm = $("#frm_asset");

    survey.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    survey.fileUpload = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: deleteFlag
        });
    };

    survey.showFile = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: deleteFlag
        })
    };

    survey.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    //公共  赋值 方法
    survey.initFormData = function (form, item, fileArr, bisDetail, tableName, inputArr) {
        var frm = $(form.selector);
        frm.clearAll(['bisCheckOriginal']);
        frm.initForm(item);
        frm.validate();
        if (fileArr) {
            $.each(fileArr, function (i, n) {
                if (bisDetail == false) {
                    survey.showFile(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', false);
                    survey.fileUpload(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', false);
                } else {
                    survey.showFile(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', true);
                    survey.fileUpload(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', true);
                }
            });
        }
        if (inputArr) {
            $.each(inputArr, function (i, n) {
                frm.find("input[name='" + n + "']").val(formatDate(item[n]));
                frm.find("label[name='" + n + "']").html(formatDate(item[n]));
            });
        }
    };

    survey.initSurveyAssetInventoryForm = function (data) {
        var frm = survey.handleJquery(survey.frm);
        //附件
        var arr = ["checkOriginalFile", "paymentStatusFile", "networkFindFile", AssessUploadKey.INVENTORY_PAYMENT_STATUS, AssessUploadKey.INVENTORY_CHECK_ORIGINAL];
        //日期
        var inputArr = ["checkDate"];
        survey.initFormData(frm, data, arr, false, AssessDBKey.SurveyAssetInventory, inputArr);
        if (data.transferLimit) {
            $("#bisLimit").val("是");
        } else {
            $("#bisLimit").val("否");
        }
        AssessCommon.loadDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultCheckOriginal, data.findOriginal, function (html, item) {
            frm.find("select[name='findOriginal']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, data.application, function (html, item) {
            frm.find("select[name='application']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.CERTIFICATE_HANDLING_TYPE, data.certificate, function (html, item) {
            frm.find("select[name='certificate']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultFindMethod, data.findMethod, function (html, item) {
            frm.find("select[name='findMethod']").empty().html(html).trigger('change');
        });
        frm.find("select[name='findMethod']").change(function () {
            var id = $(this).val();
            AssessCommon.getDataDicInfo(id, function (item) {
                if (item.fieldName == AssessDicKey.projectSurveyInventoryContentDefaultFindMethodNetwork) {
                    frm.find("div[data-name=findMethod]").show();
                } else {
                    frm.find("div[data-name=findMethod]").hide();
                }
            });

        });
        AssessCommon.loadNewAsyncDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultAffected, data.affected, function (html, item) {
            frm.find("select[name='affected']").empty().html(html).trigger('change');
            if (data.affected) {
                frm.find("select[name='affected']").val(data.affected.split(",")).trigger('change');
            }
        }, true, false);
        AssessCommon.loadNewAsyncDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultInfluenceFactor, data.influenceFactor, function (html, item) {
            frm.find("select[name='influenceFactor']").empty().html(html);
            if (data.influenceFactor) {
                frm.find("select[name='influenceFactor']").val(data.influenceFactor.split(",")).trigger('change');
            }
        }, true, false);
        frm.find("select[name='influenceFactor']").change(function () {
            var ids = $(this).val();
            if (!ids) {
                return false;
            }
            var target = frm.find("select[name='influenceFactor']").closest(".form-group");
            var arr = $.grep(ids, function (n, i) {
                if (n) {
                    return true;
                } else {
                    return false;
                }
            });
            $.each(arr, function (i, id) {
                var name = "influenceFactorRemarkText" + id;
                var size = frm.find("[name=" + name + "]").size();
                var html = $("#influenceFactorRemarkTextHtml").html();
                if (size == 0) {
                    AssessCommon.getDataDicInfo(id, function (item) {
                        html = html.replace(/{id}/g, id);
                        html = html.replace(/{name}/g, item.name);
                        target.after(html);
                        var influenceFactorRemarkText = frm.find("input[name=influenceFactorRemarkText]").val();
                        //进行动态赋值对象
                        var obj = {};
                        if (influenceFactorRemarkText) {
                            var testData = influenceFactorRemarkText.split(",");
                            $.each(testData, function (i, str) {
                                var rD = str.split(":");
                                obj[rD[0]] = rD[1];
                            });
                        }
                        if (obj) {
                            var value = obj[name];
                            if (value) {
                                frm.find("textarea[name=" + name + "]").val(value);
                            }
                        }
                    });
                }
            })
        });

        if (data.id) {
            if (data.transferLimit) {
                $("#transferLimit").val(data.transferLimit);
            }
            if (data.entityIsDamage) {
                $("#entityIsDamage").val(data.entityIsDamage);
            }
            if (data.rimIsNormal) {
                $("#rimIsNormal").val(data.rimIsNormal);
            }
            if (data.paymentStatus) {
                $("#paymentStatus").val(data.paymentStatus);
            }
            writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', data.zoneDamage);
            writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', data.entityDamage);
            writeHTMLData('otherProjectName', 'otherProjectItem', 'otherProject', data.otherProject);
            writePaymentHTMLData(${surveyAssetInventory.paymentContent});
        }
        try {
            if (data.bisCheckOriginal != null && data.bisCheckOriginal != undefined) {
                if (data.bisCheckOriginal) {
                    frm.find("[name=bisCheckOriginal][value=0]").attr("checked", false);
                    frm.find("[name=bisCheckOriginal][value=1]").attr("checked", true);
                } else {
                    frm.find("[name=bisCheckOriginal][value=0]").attr("checked", true);
                    frm.find("[name=bisCheckOriginal][value=1]").attr("checked", false);
                }
            }
        } catch (e) {
        }
        frm.find("[name=bisCheckOriginal]").change(function () {
            var value = $(this).val();
            if (value == '1') {
                frm.find(".bisCheckOriginalOne").show();
                frm.find(".bisCheckOriginalZero").hide();
            } else {
                frm.find(".bisCheckOriginalOne").hide();
                frm.find(".bisCheckOriginalZero").show();
            }
        });
    };


    survey.getSurveyAssetInventoryById = function (id, callback) {
        Loading.progressShow();
        $.ajax({
            type: "get",
            url: '${pageContext.request.contextPath}' + "/surveyAssetInventory/getSurveyAssetInventoryById",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };

    survey.areConsistentEvent = function (_this) {
        var value = $(_this).prop("checked");
        var tr = $(_this).closest('tr');
        if (value) {
            tr.find('.show-hide').hide();
        } else {
            tr.find('.show-hide').show();
        }
    };

    /**
     * 初始化
     */
    survey.initAgreement = function (selector) {
        var tr = $(selector).closest('tr');
        var registration = $.trim(tr.find('[name^=registration]').val());//登记
        var actual = $.trim(tr.find('[name^=actual]').val());//实际
        var num = 1;
        if (actual && registration) {
            if (actual != registration) {
                num++;
            }
        }
        if (num == 1) {
            tr.find('.show-hide').hide();
            tr.find("input[type=checkbox]").prop('checked', true);
        } else {
            tr.find('.show-hide').show();
            tr.find("input[type=checkbox]").prop('checked', false);
        }
    };

    /**
     * 验证登记与实际是否一致，如果不一致需填写相关内容
     * @param _this
     */
    survey.isAgreement = function (_this) {
        var value = $(_this).val();
        if (!value) {
            return false;
        }
        var tr = $(_this).closest('tr');
        var registration = $.trim(tr.find('[name^=registration]').val());//登记
        var actual = $.trim(tr.find('[name^=actual]').val());//实际
        if (actual && registration) {
            if (actual == registration) {
                tr.find('.show-hide').hide();
                tr.find("input[type=checkbox]").prop('checked', true);
            }
        }
    };

    //select2 change事件的处理
    survey.influenceFactorEvent = function (_this, id) {
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        $(_this).closest(".form-group").remove();
        if (data.influenceFactor) {
            var arr = data.influenceFactor.split(",");
            arr = $.grep(arr, function (n, i) {
                return n != id;
            });
            frm.find("select[name='influenceFactor']").val(arr).trigger('change');
        }
    };


    //上传附件通用
    survey.uploadFileCommon = function (tableId) {
        survey.fileUpload("credentialAccessory" + tableId, AssessDBKey.SurveyAssetInventoryContent, tableId, true);
    };

    //显示附件
    survey.showFileCommon = function (tableId) {
        survey.showFile("credentialAccessory" + tableId, AssessDBKey.SurveyAssetInventoryContent, tableId, true);
    };


    //权证查看
    survey.findDeclareRecord = function () {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '500px'],
            content: '${pageContext.request.contextPath}/declareRecord/view/${declareRecord.id}',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                //放弃按钮 不需要做处理
            },
            btnAlign: 'c',
//            btn: ['关闭'],
            yes: function (index, layero) {
                layer.closeAll('iframe');
            },
            btn2: function (index, layero) {
                layer.closeAll('iframe');
            }
        });
//        layer.full(frame);
    };

    survey.findRightDetail = function () {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '500px'],
            content: '${pageContext.request.contextPath}/surveyAssetRightDeclare/taskRightDetail/${declareRecord.projectId}',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                //放弃按钮 不需要做处理
            },
            btnAlign: 'c',
//            btn: ['关闭'],
            yes: function (index, layero) {
                layer.closeAll('iframe');
            },
            btn2: function (index, layero) {
                layer.closeAll('iframe');
            }
        });
//        layer.full(frame);
    };


    //获取需要保存的数据
    function getFormData() {
        var trs = $("#tb_surveyList").find('tbody tr');
        var dataItem = [];
        $.each(trs, function (i, tr) {
            var item = {};
            item.registration = $(tr).find('[name^="registration"]').val();    //登记面积
            item.actual = $(tr).find('[name^="actual"]').val();//实际面积
            var areConsistentEle = $(tr).find('[name^="areConsistent"]'); //是否一致
            if (areConsistentEle.prop("checked")) {
                item.areConsistent = areConsistentEle.val();
            } else {
                item.areConsistent = '不一致';
            }
            item.differenceReason = $(tr).find('[name^="differenceReason"]').val(); //差异原因
            item.credential = $(tr).find('[name^="credential"]').val(); //证明文件
            item.voucher = $(tr).find('[name^="voucher"]').val(); //证明人
            item.surveyTime = $(tr).find('[name^="surveyTime"]').val(); //查勘时间
            item.sureConsistent = $(tr).find('[name^="sureConsistent"]').val();   //确认一致
            item.projectId = ${projectPlanDetails.projectId};
            item.planDetailId = ${projectPlanDetails.id};
            item.id = $(tr).find('[name="id"]').val();    //id
            dataItem.push(item);
        });
        var data = {};
        var surveyAssetInventory = formParams("frm_asset");//评估人员 核对时间
        var resultData = [];
        $.each(Object.keys(surveyAssetInventory), function (i, name) {
            if (name.indexOf('influenceFactorRemarkText') != -1) {
                if (name != 'influenceFactorRemarkText') {
                    if (surveyAssetInventory[name]) {
                        resultData.push(name + ":" + surveyAssetInventory[name]);
                    }
                }
            }
        });
        surveyAssetInventory.influenceFactorRemarkText = resultData.join(",");
        data.surveyAssetInventory = surveyAssetInventory;
        data.assetInventoryContentList = dataItem;
        data.surveyAssetInventory.specialCase = $("#specialCase").val();
        data.surveyAssetInventory.rimIsNormal = $("#rimIsNormal").val();
        data.surveyAssetInventory.entityIsDamage = $("#entityIsDamage").val();
        data.surveyAssetInventory.transferLimit = $("#transferLimit").val();
        data.surveyAssetInventory.paymentStatus = $("#paymentStatus").val();

        data.surveyAssetInventory.zoneDamage = [];
        data.surveyAssetInventory.entityDamage = [];
        data.surveyAssetInventory.otherProject = [];
        $("#damageSurvey").find('.form-group').each(function () {
            var zoneBit = {};
            var zoneProjectName = $(this).find('[name^=zoneProjectName]').val();
            var zoneProjectItem = $(this).find('[name^=zoneProjectItem]').val();
            if (zoneProjectName && zoneProjectItem) {
                zoneBit.zoneProjectName = zoneProjectName;
                zoneBit.zoneProjectItem = zoneProjectItem;
                data.surveyAssetInventory.zoneDamage.push(zoneBit);
            }

            var entity = {};
            var entityProjectName = $(this).find('[name^=entityProjectName]').val();
            var entityProjectItem = $(this).find('[name^=entityProjectItem]').val();
            if (entityProjectName && entityProjectItem) {
                entity.entityProjectName = entityProjectName;
                entity.entityProjectItem = entityProjectItem;
                data.surveyAssetInventory.entityDamage.push(entity);
            }

            var otherProject = {};
            var otherProjectName = $(this).find('[name^=otherProjectName]').val();
            var otherProjectItem = $(this).find('[name^=otherProjectItem]').val();
            if (otherProjectName && otherProjectItem) {
                otherProject.otherProjectName = otherProjectName;
                otherProject.otherProjectItem = otherProjectItem;
                data.surveyAssetInventory.otherProject.push(otherProject);
            }
        });

        data.surveyAssetInventory.paymentContent = [];
        $("#taxesPaymentSurvey").find('.form-group').each(function () {
            var paymentItem = {};
            var projectName = $(this).find('[name^=projectName]').val();
            var remark = $(this).find('[name^=remark]').val();
            var money = $(this).find('[name^=money]').val();
            if (projectName && money) {
                paymentItem.projectName = projectName;
                paymentItem.remark = remark;
                paymentItem.money = money;
                data.surveyAssetInventory.paymentContent.push(paymentItem);
            }

        });
        return data;
    }

    function newGetFormData(callback) {
        getFormData();
        if (!$("#frm_asset").valid()) {
            return false;
        }
        if (!$("#frm_asset_inventory_content").valid()) {
            return false;
        }
        if (!$("#damageSurvey").valid()) {
            return false;
        }
        if (!$("#taxesPaymentSurvey").valid()) {
            return false;
        }
        if (callback) {
            callback(getFormData());
        }
    }


    function showLimit() {
        if ($("#bisLimit").val() == "是") {
            $(".showLimit").show();
        } else {
            $(".showLimit").hide();
            $("#transferLimit").val("");
        }
    }


    var num = 0;

    function appendHTML(projectName, projectItem, item, this_) {
        var html = "<div class='row form-group' >";
        html += " <div class='col-md-12'>";
        html += "<div class='form-inline x-valid'>";

        html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' required class='form-control input-full' name='" + projectName + num + "'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "明细" + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' required class='form-control input-full' name='" + projectItem + num + "'>";
        html += "</div>";


        html += " <div class='col-sm-1'>";
        html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";

        num++;
        $("." + item).append(html);
    }

    function cleanHTMLData(_this) {
        $(_this).closest(".form-group").remove();
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
            html += "<input type='text' required class='form-control input-full' name='" + projectName + i + "' value='" + n[projectName] + "'>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "明细" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<input type='text' required class='form-control input-full' name='" + projectItem + i + "' value='" + n[projectItem] + "'>";
            html += "</div>";


            html += "<div class='col-sm-1'>";
            html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("." + item).append(html);
        })
    }

    function appendPaymentHTML(this_) {
        var html = "<div class='row form-group' >";
        html += " <div class='col-md-12'>";
        html += "<div class='form-inline x-valid'>";

        html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' required class='form-control input-full' name='projectName" + num + "'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "类型" + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<select required class='form-control input-full' name='remark " + num + "'>";
        html += "<option value='应缴' selected>" + "应缴" + "</option>";
        html += "<option value='未缴'>" + "未缴" + "</option>";
        html += "<option value='欠缴'>" + "欠缴" + "</option>";
        html += "</select>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "金额" + "</label>";
        html += "<div class='col-sm-2'>";
        html += "<input type='text' required class='form-control input-full' name='money " + num + "' data-rule-number='true'>";
        html += "</div>";


        html += " <div class='col-sm-1'>";
        html += "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";

        num++;
        $(".paymentItem").append(html);
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
            html += "<input type='text' required class='form-control input-full' name='projectName " + i + "' value='" + n["projectName"] + "'>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "类型" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<select id='select" + i + "' required class='form-control input-full' name='remark" + i + "' >";
            html += "<option value='应缴'>" + "应缴" + "</option>";
            html += "<option value='未缴'>" + "未缴" + "</option>";
            html += "<option value='欠缴'>" + "欠缴" + "</option>";
            html += "</select>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "金额" + "</label>";
            html += "<div class='col-sm-2'>";
            html += "<input type='text' required class='form-control input-full' name='money" + i + "' value='" + n["money"] + "'>";
            html += "</div>";

            html += " <div class='col-sm-1'>";
            html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $(".paymentItem").append(html);
            var selectId = "select" + i;
            $("#" + selectId).val(n["remark"]);
        })
    }

    function showButton() {
        if ($("#rimIsNormal").val() == "不正常") {
            $(".showZoneAdd").show();
        } else {
            $(".zoneBit").empty();
            $(".showZoneAdd").hide();
        }
        if ($("#entityIsDamage").val() == "损坏") {
            $(".showEntityAdd").show();
        } else {
            $(".entity").empty();
            $(".showEntityAdd").hide();
        }
        if ($("#paymentStatus").val() == "不正常") {
            $(".showPaymentAdd").show();
            $("#showUploadFile").show();
        } else {
            $(".paymentItem").empty();
            $(".showPaymentAdd").hide();
            $("#showUploadFile").hide();
        }
    }


    $(function () {
        if ('${surveyAssetInventory}') {
            if ('${surveyAssetInventory.id}') {
                survey.getSurveyAssetInventoryById('${surveyAssetInventory.id}', function (data) {
                    survey.initSurveyAssetInventoryForm(data);
                });
            } else {
                survey.initSurveyAssetInventoryForm({checkDate: new Date()});
            }
        }
        showLimit();
        showButton();
    });


</script>

</html>

