<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>


            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <div class="input-group">
                                        <input type="hidden" id="evaluatorID">
                                        <input type="text" class="form-control" readonly="readonly"
                                               value="${thisUserInfo.userName}" required="required"
                                               id="evaluator" name="evaluator" maxlength="200">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectEvaluator()">
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

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input required="required" placeholder="核对日期" id="checkDate" name="checkDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetInventory.checkDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                        </div>

                    </form>

                    <div class="x_title">
                        <h2>
                            <small><i class="fa fa-bars"></i>清查内容</small>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table" id="tb_surveyList">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>清查内容</th>
                            <th>是否一致</th>
                            <th>登记面积</th>
                            <th>实际面积</th>
                            <th>差异原因</th>
                            <th>证明文件</th>
                            <th>证明文件附件</th>
                            <th>证明人</th>
                            <th>调查时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                                <c:forEach items="${surveyAssetTemplateVos}" var="items" varStatus="s">
                                    <tr>
                                        <input type="hidden" id="id" name="id" value="${items.id}">
                                        <td>${s.index + 1}</td>
                                        <td name="inventoryContent">${items.inventoryContentName}</td>
                                        <td>
                                            <input id="areConsistent${items.id}" name="areConsistent" type="checkbox"
                                                   value="一致" style="vertical-align:middle;"
                                                   onclick="showHiddenCheck(this,${items.id})"/>
                                            <span style="vertical-align:middle;">一致</span>
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="登记面积"
                                                   id="registrationAddress${items.id}" name="registrationAddress"
                                                   class="form-control showHidden" value="${items.registrationAddress}">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="实际面积"
                                                   id="actualAddress${items.id}" name="actualAddress"
                                                   class="form-control showHidden" value="${items.actualAddress}">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="差异原因"
                                                   id="differenceReason${items.id}" name="differenceReason"
                                                   class="form-control showHidden" value="${items.differenceReason}">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="证明文件"
                                                   id="credential${items.id}" name="credential"
                                                   class="form-control showHidden" value="${items.credential}">
                                        </td>
                                        <td>
                                            <input id="credentialAccessory${items.id}" name="credentialAccessory"
                                                   type="file" multiple="false" class="showHidden">
                                            <div id="_credentialAccessory${items.id}" class="showHidden"></div>
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="证明人"
                                                   id="voucher${items.id}" name="voucher"
                                                   class="form-control showHidden" value="${items.voucher}">
                                        </td>
                                        <td>
                                            <input placeholder="调查时间" id="surveyTime${items.id}" name="surveyTime"
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate showHidden"
                                                   readonly="readonly" value='<fmt:formatDate value="${items.surveyTime}" pattern="yyyy-MM-dd"/>'>
                                        </td>
                                        <td>
                                            <a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空重填</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </tbody>
                    </table>

                    <div class="x_title">
                        <h2>
                            <small class="col-sm-1"><i class="fa fa-bars"></i>他项权利</small>
                        </h2>
                        <button type="button" class="btn btn-success" onclick="addData()"
                                data-toggle="modal" href="#divBox"> 新增
                        </button>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>

                    <%--<form id="frm_checkbox" class="form-horizontal">--%>
                    <%--<div class="form-group">--%>

                    <%--<label class="col-sm-2 control-label" data-label="&lt;label&gt;">--%>
                    <%--他项权利与实际情况是否一致--%>
                    <%--</label>--%>

                    <%--<label class="col-sm-0.5 control-label">--%>
                    <%--<input id="agreementBox" type="checkbox" value="checkbox"--%>
                    <%--style="vertical-align:middle;"/>--%>
                    <%--<span style="vertical-align:middle;">一致</span>--%>
                    <%--</label>--%>
                    <%--</div>--%>

                    <%--</form>--%>

                    <%--<form id="frm_survey" class="form-horizontal">--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-1 control-label">--%>
                    <%--他权登记人--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input type="text" placeholder="他权登记人"--%>
                    <%--id="otherRightsRegistrar" name="otherRightsRegistrar" class="form-control"--%>
                    <%--value="${surveyAssetOtherTemplate.otherRightsRegistrar}">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--实际行权人--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input type="text" placeholder="实际行权人"--%>
                    <%--id="rightHander" name="rightHander" class="form-control"--%>
                    <%--value="${surveyAssetOtherTemplate.rightHander}">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--登记面积--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input type="text" placeholder="登记面积"--%>
                    <%--id="registerArea" name="registerArea" class="form-control"--%>
                    <%--value="${surveyAssetOtherTemplate.registerArea}">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--实际面积--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input type="text" placeholder="实际面积"--%>
                    <%--id="actualArea" name="actualArea" class="form-control"--%>
                    <%--value="${surveyAssetOtherTemplate.actualArea}">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--登记用途--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input type="text" placeholder="登记用途"--%>
                    <%--id="registerPurpose" name="registerPurpose" class="form-control"--%>
                    <%--value="${surveyAssetOtherTemplate.registerPurpose}">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--实际用途--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input type="text" placeholder="实际用途"--%>
                    <%--id="actualPurpose" name="actualPurpose" class="form-control"--%>
                    <%--value="${surveyAssetOtherTemplate.actualPurpose}">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">登记日期</label>--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input required="required" placeholder="登记日期" id="registerDate" name="registerDate"--%>
                    <%--data-date-format="yyyy-mm-dd"--%>
                    <%--class="form-control date-picker dbdate" readonly="readonly"--%>
                    <%--value="<fmt:formatDate value='${surveyAssetOtherTemplate.registerDate}' pattern='yyyy-MM-dd'/>">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">到期日</label>--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input required="required" placeholder="到期日" id="dueDate" name="dueDate"--%>
                    <%--data-date-format="yyyy-mm-dd"--%>
                    <%--class="form-control date-picker dbdate" readonly="readonly"--%>
                    <%--value="<fmt:formatDate value='${surveyAssetOtherTemplate.dueDate}' pattern='yyyy-MM-dd'/>">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">实际行权人行权日期</label>--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input required="required" placeholder="实际行权人行权日期" id="exerciseDate"--%>
                    <%--name="exerciseDate" data-date-format="yyyy-mm-dd"--%>
                    <%--class="form-control date-picker dbdate" readonly="readonly"--%>
                    <%--value="<fmt:formatDate value='${surveyAssetOtherTemplate.exerciseDate}' pattern='yyyy-MM-dd'/>">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">预计到期日</label>--%>
                    <%--<div class="col-sm-3">--%>
                    <%--<input required="required" placeholder="预计到期日" id="predictDueDate"--%>
                    <%--name="predictDueDate" data-date-format="yyyy-mm-dd"--%>
                    <%--class="form-control date-picker dbdate" readonly="readonly"--%>
                    <%--value="<fmt:formatDate value='${surveyAssetOtherTemplate.predictDueDate}' pattern='yyyy-MM-dd'/>">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</form>--%>

                </div>
            </div>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}成果提交</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_task" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="实际工时" data-rule-number='true'
                                           id="actualHours" name="actualHours" class="form-control" maxlength="3"
                                           value="${projectPlanDetails.actualHours}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" id="taskRemarks" name="taskRemarks"
                                                  class="form-control">${projectPlanDetails.taskRemarks}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <input id="apply_file" name="apply_file" type="file" multiple="false">
                                <div id="_apply_file">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
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

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <%--<input type="hidden" id="id" name="id" value="0">--%>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="类型"
                                                   id="type" name="type"
                                                   class="form-control"
                                                   value="${surveyAssetOtherTemplate.type}">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            他权登记人
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="他权登记人"
                                                   id="otherRightsRegistrar" name="otherRightsRegistrar"
                                                   class="form-control"
                                                   value="${surveyAssetOtherTemplate.otherRightsRegistrar}">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            实际行权人
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="实际行权人"
                                                   id="rightHander" name="rightHander" class="form-control"
                                                   value="${surveyAssetOtherTemplate.rightHander}">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            登记面积
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="登记面积"
                                                   id="registerArea" name="registerArea" class="form-control"
                                                   value="${surveyAssetOtherTemplate.registerArea}">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            实际面积
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="实际面积"
                                                   id="actualArea" name="actualArea" class="form-control"
                                                   value="${surveyAssetOtherTemplate.actualArea}">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            登记用途
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="登记用途"
                                                   id="registerPurpose" name="registerPurpose" class="form-control"
                                                   value="${surveyAssetOtherTemplate.registerPurpose}">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            实际用途
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="实际用途"
                                                   id="actualPurpose" name="actualPurpose" class="form-control"
                                                   value="${surveyAssetOtherTemplate.actualPurpose}">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">登记日期</label>
                                        <div class="col-sm-9">
                                            <input placeholder="登记日期" id="registerDate" name="registerDate"
                                                   data-date-format="yyyy-mm-dd" required
                                                   value="<fmt:formatDate value="${surveyAssetOtherTemplate.registerDate}"   pattern="yyyy-MM-dd"/>"
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">到期日</label>
                                        <div class="col-sm-9">
                                            <input required="required" placeholder="到期日" id="dueDate" name="dueDate"
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly"
                                                   value="<fmt:formatDate value='${surveyAssetOtherTemplate.dueDate}' pattern='yyyy-MM-dd'/>">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">实际行权人行权日期</label>
                                        <div class="col-sm-9">
                                            <input required="required" placeholder="实际行权人行权日期" id="exerciseDate"
                                                   name="exerciseDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly"
                                                   value="<fmt:formatDate value='${surveyAssetOtherTemplate.exerciseDate}' pattern='yyyy-MM-dd'/>">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">预计到期日</label>
                                        <div class="col-sm-9">
                                            <input required="required" placeholder="预计到期日" id="predictDueDate"
                                                   name="predictDueDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly"
                                                   value="<fmt:formatDate value='${surveyAssetOtherTemplate.predictDueDate}' pattern='yyyy-MM-dd'/>">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="排序" required data-rule-digits="true"
                                                   id="sorting" name="sorting" class="form-control"
                                                   value="${surveyAssetOtherTemplate.sorting}">
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
                        <button type="button" class="btn btn-primary" onclick="saveData()">
                            保存
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
<script type="application/javascript">

    $(function () {
        loadDataDicList();

        $("#btn_select_customer").click(function () {
            crmCustomer.select({
                multi: false,//是否允许多选
                onSelected: function (nodes) {
                    console.log(nodes);
                }
            });
        })

        $("#frm_task").validate();

        FileUtils.uploadFiles({
            target: "apply_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })


        FileUtils.uploadFiles({
            target: "credentialAccessory${surveyAssetTemplateVos.get(0).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(0).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${surveyAssetTemplateVos.get(0).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(0).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "credentialAccessory${surveyAssetTemplateVos.get(1).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(1).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${surveyAssetTemplateVos.get(1).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(1).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "credentialAccessory${surveyAssetTemplateVos.get(2).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(2).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${surveyAssetTemplateVos.get(2).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(2).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "credentialAccessory${surveyAssetTemplateVos.get(3).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(3).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${surveyAssetTemplateVos.get(3).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${surveyAssetTemplateVos.get(3).id},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    });

    //加载 他项权利列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'type', title: '类型'});
        cols.push({field: 'otherRightsRegistrar', title: '他权登记人'});
        cols.push({field: 'rightHander', title: '实际行权人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'actualArea', title: '实际面积'});
        cols.push({field: 'registerPurpose', title: '登记用途'});
        cols.push({field: 'actualPurpose', title: '实际用途'});

        cols.push({
            field: 'registerDate', title: '登记日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'dueDate', title: '到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'exerciseDate', title: '实际行权人行权日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'predictDueDate', title: '预计到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({field: 'sorting', title: '排序'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editHrProfessional(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="delData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyAssetOtherTemplate/list", cols, {
            pid: ${empty surveyAssetInventory?0:surveyAssetInventory.id}
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //    function getRowsData() {
    //        var trs = $("#tb_List").find('tbody tr');
    //        var data=[];
    //        $.each(trs, function (i, tr) {
    //            var item = {};
    //            item.registrationAddress = $(tr).find('[name="registrationAddress"]').val();    //登记面积
    //            item.actualAddress = $(tr).find('[name="actualAddress"]').val();                //实际面积
    //            item.differenceReason = $(tr).find('[name="differenceReason"]').val();          //差异原因
    //            item.credential = $(tr).find('[name="credential"]').val();                      //证明文件
    //            item.voucher = $(tr).find('[name="voucher"]').val();                            //证明人
    //            item.surveyTime = $(tr).find('[name="surveyTime"]').val();                      //查勘时间
    //            data.push(item);
    //        });
    //        console.log(JSON.stringify(data));
    //        return data;
    //    }
    var items = "";
    function getRowsData() {
        var trs = $("#tb_surveyList").find('tbody tr');
        console.log(trs);
        var data = [];
        $.each(trs, function (i, tr) {
            var item = {};

            var temp = $(tr).find('[name="areConsistent"]:checked').prop("checked");    //是否一致
            if(temp){
                item.areConsistent = "一致";    //是否一致
            }else{
                item.areConsistent = "不一致";
            }
            item.inventoryContent = $(tr).find('[name="inventoryContent"]').text();    //清查内容
            item.registrationAddress = $(tr).find('[name="registrationAddress"]').val();    //登记面积
            item.actualAddress = $(tr).find('[name="actualAddress"]').val();                //实际面积
            item.differenceReason = $(tr).find('[name="differenceReason"]').val();          //差异原因
            item.credential = $(tr).find('[name="credential"]').val();                      //证明文件
            item.voucher = $(tr).find('[name="voucher"]').val();                            //证明人
            item.surveyTime = $(tr).find('[name="surveyTime"]').val();                      //查勘时间
            item.projectId = ${projectPlanDetails.projectId};
            item.planDetailId = ${projectPlanDetails.planId};
            item.id = $(tr).find('[name="id"]').val();    //id
            data.push(item);
        });
        items = data;
        console.log(JSON.stringify(data));
        return data;
    }

    var json = "";
    function params() {
        getRowsData();
        var data = {};
        var surveyAssetInventoryDto = formParams("frm_assess");//评估人员 核对时间
//        var surveyAssetOtherTemplateDto = formParams("frm_survey"); //他项权利是否一致
        data.surveyAssetInventoryDto = surveyAssetInventoryDto;
//        data.surveyAssetOtherTemplateDto = surveyAssetOtherTemplateDto;
        data.surveyAssetTemplateDtos = items;

        //合并json
        json = JSON.stringify(data);
        console.info(data)
    }

    function submit() {
//        params();
//        return false;
        if (!$("#frm_task").valid()) {
            return false;
        }

        if ("${processInsId}" != "0") {
            params();
            submitEditToServer(json, $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            params();
            submitToServer(json, $("#taskRemarks").val(), $("#actualHours").val());
        }
    }

    //他权一致显示隐藏切换
    $('#agreementBox').click(function () {
        if ($('#agreementBox').prop("checked")) {
            $('#frm_survey').css('display', 'none');
            $('#frm_survey').clearAll();
        } else {
            $('#frm_survey').css('display', 'block');
        }
    });


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
        getRowsData();
        $(_this).closest("tr").find("input").val("");
    }

    //选择人员
    /*erpDepartment.select({
     onSelected:function (nodes) {
     console.log(nodes);
     }
     });

     erpEmployee.select({
     onSelected:function (data) {
     console.log(data);
     }
     });*/

    // 评估人员
    //    var evaluator = document.getElementById("evaluator");
    //    evaluator.onclick = function () {
    //        erpEmployee.select({
    //            onSelected: function (data) {
    //                evaluator.removeChild(evaluator.firstChild);
    //                var fieldElment = document.createElement("option");
    //                fieldElment.setAttribute("value", data.account);
    //                fieldElment.setAttribute("selected", "selected");
    //                fieldElment.appendChild(document.createTextNode(data.name));
    //                evaluator.appendChild(fieldElment);
    //            }
    //        });
    //    }
    //评估人员
    // 项目经理
    function selectEvaluator() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#evaluator").val(data.name);
                $("#evaluatorID").val(data.account);
            }
        });
    }

    function addData() {
        $("#frm").clearAll();
    }

    function saveData() {
        var flag = false;
        var data = formParams("frm");
        data.projectId = ${projectPlanDetails.projectId};
        data.planDetailId = ${projectPlanDetails.planId};
        data.pid = ${empty surveyAssetInventory?0:surveyAssetInventory.id};
        console.log(data);
//        var data = $("#frm").serialize();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetOtherTemplate/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadDataDicList();
                        $('#divBox').modal('hide');
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

    function editHrProfessional(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $('#divBox').modal();
    }

    function delData(id, tbId) {
        console.log(id);
        console.log(tbId);
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetOtherTemplate/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

</script>

</html>

