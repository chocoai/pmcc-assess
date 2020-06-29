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
                                                    <input type="text" required placeholder="核对日期" id="checkDate"
                                                           name="checkDate"
                                                           data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate"
                                                           readonly="readonly"
                                                           value="<fmt:formatDate value='${surveyAssetInventory.checkDate}' pattern='yyyy-MM-dd'/>">

                                                </div>
                                                <label class="col-sm-1 col-form-label">是否查看原件<span
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    分割限制<span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full" id="segmentationLimit"
                                                            name="segmentationLimit"
                                                            required onchange="showOther()">
                                                        <option value="可分">可分</option>
                                                        <option value="不可分" selected>不可分</option>
                                                    </select>
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
                                                    <select class="form-control input-full" id="canUse" name="canUse">
                                                        <option value="" selected>请选择</option>
                                                        <option value="正常使用">正常使用</option>
                                                        <option value="不能正常使用">不能正常使用</option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 col-form-label">
                                                    证载用途
                                                </label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full" id="application"
                                                            name="application">
                                                        <option value="">-请选择-</option>
                                                        <c:forEach var="items" items="${types}">
                                                            <option value="${items.id}">${items.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    是否办证</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full" id="certificate"
                                                            name="certificate">
                                                        <option value="">-请选择-</option>
                                                        <c:forEach var="items" items="${certificateTypes}">
                                                            <option value="${items.id}">${items.name}</option>
                                                        </c:forEach>
                                                    </select>
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
                                                    <input id="checkOriginalFile" type="file" multiple="false">
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
                                           <textarea placeholder="说明" class="form-control input-full"
                                                     name="remark">${surveyAssetInventory.remark}</textarea>
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
                                            <th style="width: 10%">一致性内容</th>
                                            <th style="width: 10%">登记<span
                                                    style="color:red;font-size:10px;">(没有登记信息则填无)</span></th>
                                            <th style="width: 10%">实际</th>
                                            <th style="width: 6%">是否一致</th>
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
                                                <input type="hidden" id="id" name="id" value="${item.id}">
                                                <td>${item.inventoryContentName}</td>
                                                <td>
                                                    <div class="x-valid">
                                                        <input type="text" data-rule-maxlength="100" placeholder="登记"
                                                               required
                                                               id="registration${item.id}" onchange="isAgreement(this);"
                                                               name="registration${item.id}"
                                                               class="form-control input-full "
                                                               value="${item.registration}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid">
                                                        <input type="text" data-rule-maxlength="100" placeholder="实际"
                                                               required
                                                               id="actual${item.id}" onchange="isAgreement(this);"
                                                               name="actual${item.id}"
                                                               class="form-control input-full " value="${item.actual}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <label data-name="areConsistent">${item.areConsistent}</label>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="200" placeholder="差异原因"
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
                                                        <input type="text" data-rule-maxlength="100" placeholder="证明文件"
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
                                                        <input type="text" data-rule-maxlength="100" placeholder="证明人"
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
                                                            <option value="一致">一致</option>
                                                            <option value="不一致">不一致</option>
                                                        </select>
                                                    </div>
                                                </td>
                                                <script type="text/javascript">
                                                    $(function () {
                                                        getConsistentVal("sureConsistent${item.id}", "${item.sureConsistent}");
                                                    })
                                                </script>
                                                <td>
                                                    <button type="button" class="btn btn-xs btn-danger"
                                                            onclick="emptyRefill(this)">清空
                                                    </button>
                                                </td>
                                            </tr>
                                            <script type="text/javascript">
                                                $(function () {
                                                    //清查内容附件上传和加载
                                                    uploadFileCommon("${item.id}");
                                                    showFileCommon("${item.id}");
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

                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<%@include file="/views/project/stageSurvey/certificate.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="application/javascript">

    $(function () {
        loadAssetRightList();
        if("${surveyAssetInventory.transferLimit}"){
            $("#bisLimit").val("是");
        }else{
            $("#bisLimit").val("否");
        }
        showLimit();
        FileUtils.uploadFiles({
            target: "checkOriginalFile",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_CHECK_ORIGINAL,
                tableId: '${empty surveyAssetInventory?0:surveyAssetInventory.id}'
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "checkOriginalFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_CHECK_ORIGINAL,
                tableId: '${empty surveyAssetInventory?0:surveyAssetInventory.id}'
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "paymentStatusFile",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_PAYMENT_STATUS,
                tableId: '${empty surveyAssetInventory?0:surveyAssetInventory.id}'
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "paymentStatusFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_PAYMENT_STATUS,
                tableId: '${empty surveyAssetInventory?0:surveyAssetInventory.id}'
            },
            deleteFlag: true
        })


        if ("${surveyAssetInventory}".length > 0) {
            if ("${surveyAssetInventory.segmentationLimit}".length > 0) {
                $("#segmentationLimit").val("${surveyAssetInventory.segmentationLimit}");
            }
            $("#canUse").val("${surveyAssetInventory.canUse}");
            $("#application").val("${surveyAssetInventory.application}");
            $("#certificate").val("${surveyAssetInventory.certificate}");
            $("#transferLimit").val("${surveyAssetInventory.transferLimit}");
            if ("${surveyAssetInventory.entityIsDamage}") {
                $("#entityIsDamage").val("${surveyAssetInventory.entityIsDamage}");
            }
            if ("${surveyAssetInventory.rimIsNormal}".length > 0) {
                $("#rimIsNormal").val("${surveyAssetInventory.rimIsNormal}");
            }
            writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', '${surveyAssetInventory.zoneDamage}');
            writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', '${surveyAssetInventory.entityDamage}');
            writeHTMLData('otherProjectName', 'otherProjectItem', 'otherProject', '${surveyAssetInventory.otherProject}');
            if ("${surveyAssetInventory.paymentStatus}") {
                $("#paymentStatus").val("${surveyAssetInventory.paymentStatus}");
            }
            writePaymentHTMLData(${surveyAssetInventory.paymentContent});
        }
        showOther();
        showButton();
    })
    ;

    //验证登记与实际是否一致，如果不一致需填写相关内容
    function isAgreement(_this) {
        var tr = $(_this).closest('tr');
        var registration = $.trim(tr.find('[name^=registration]').val());//登记
        var actual = $.trim(tr.find('[name^=actual]').val());//实际
        if (AssessCommon.isNumber(registration) && AssessCommon.isNumber(actual)) {
            registration = parseFloat(registration);
            actual = parseFloat(actual);
        }
        if (registration == actual) {
            tr.find('[data-name=areConsistent]').text('一致');
            tr.find('.show-hide').hide();
        } else {
            tr.find('[data-name=areConsistent]').text('不一致');
            tr.find('.show-hide').show();
        }
    }


    //上传附件通用
    function uploadFileCommon(tableId) {
        FileUtils.uploadFiles({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryContent,
                tableId: tableId
            },
            deleteFlag: true
        });
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
            deleteFlag: true
        })
    }

    //类型改变
    function typeChange(_this) {
        $("#category").empty();
        AssessCommon.loadDataDicByPid($(_this).val(), '', function (html) {
            $("#category").html(html);
        })
    }

    //加载 他项权利列表
    function loadAssetRightList() {
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
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editData(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="delData(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    }


    //获取需要保存的数据
    function getFormData() {
        var trs = $("#tb_surveyList").find('tbody tr');
        var dataItem = [];
        $.each(trs, function (i, tr) {
            var item = {};
            item.registration = $(tr).find('[name^="registration"]').val();    //登记面积
            item.actual = $(tr).find('[name^="actual"]').val();                //实际面积
            item.areConsistent = $(tr).find('[data-name="areConsistent"]').text();   //是否一致
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
        data.surveyAssetInventory = formParams("frm_asset");//评估人员 核对时间
        data.surveyAssetInventory.segmentationLimit = $("#segmentationLimit").val();
        data.surveyAssetInventory.canUse = $("#canUse").val();
        data.surveyAssetInventory.application = $("#application").val();
        data.surveyAssetInventory.certificate = $("#certificate").val();
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

    function submit(mustUseBox) {
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
        var formData = JSON.stringify(getFormData());


        if ("${processInsId}" != "0") {
            submitEditToServer(formData);
        }
        else {
            submitToServer(formData, mustUseBox);
        }
    }


    //表格一致显示隐藏切换
    function showHiddenCheck(_this, id) {
        if ($('#areConsistent' + id).prop("checked")) {
            $(_this).closest("tr").find(".showHidden,div").css('display', 'none');
            $(_this).closest("tr").find("input:text").val("");
        } else {
            $(_this).closest("tr").find(".showHidden,div").css('display', 'block');
        }
    }

    //清空重填
    function emptyRefill(_this) {
        $(_this).closest("tr").find("input").val("");
    }

    //评估人员
    function selectEvaluator() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#evaluator").val(data.name);
                $("#evaluatorID").val(data.account);
            }
        });
    }


    function showOther() {
        if ($("#segmentationLimit").val() == "可分") {
            $(".showCertificate").show();
            $(".showUse").show();
        } else {
            $(".showCertificate").hide();
            $(".showUse").hide();
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

    function cleanHTMLData(item) {
        var value = "";
        $(item).parent().parent().parent().parent().remove();
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

    function getConsistentVal(id, value) {
        $("#" + id).val(value);
    }
</script>

</html>

