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
                    <h2>${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <select id="evaluator" name="evaluator" class="form-control" required="required">
                                        <option selected="selected"
                                                value="${thisUserInfo.id}">${thisUserInfo.userName}</option>
                                    </select>
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
                    <table class="table" id="tb_List">
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
                        <c:choose>
                            <c:when test="${surveyAssetTemplates == null}">
                                <c:forEach items="${checkContentList}" var="items" varStatus="s">
                                    <tr>
                                        <td>${s.index + 1}</td>
                                        <td name="inventoryContent">${items.name}</td>
                                        <td>
                                            <input id="areConsistent${items.id}" name="areConsistent" type="checkbox"
                                                   value="一致" style="vertical-align:middle;" onclick="showHiddenCheck(this,${items.id})"/>
                                            <span style="vertical-align:middle;">一致</span>
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="登记面积"
                                                   id="registrationAddress${items.id}" name="registrationAddress"
                                                   class="form-control showHidden"></td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="实际面积"
                                                   id="actualAddress${items.id}" name="actualAddress" class="form-control showHidden">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="差异原因"
                                                   id="differenceReason${items.id}" name="differenceReason"
                                                   class="form-control showHidden">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="证明文件"
                                                   id="credential${items.id}" name="credential" class="form-control showHidden">
                                        </td>
                                        <td>
                                            <input id="credentialAccessory${items.id}" name="credentialAccessory"
                                                   type="file" multiple="false" class="showHidden">
                                            <div id="_credentialAccessory${items.id}" class="showHidden"></div>
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="证明人"
                                                   id="voucher${items.id}" name="voucher" class="form-control showHidden">
                                        </td>
                                        <td>
                                            <input placeholder="调查时间" id="surveyTime${items.id}" name="surveyTime"
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate showHidden" readonly="readonly">
                                        </td>
                                        <td>
                                            <a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空重填</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${surveyAssetTemplates}" var="survey" varStatus="s">
                                    <input type="hidden" name="id" value="${survey.id}">
                                    <tr>
                                        <td>${s.index + 1}</td>
                                        <td name="inventoryContent">${survey.inventoryContent}</td>
                                        <td>
                                            <input id="areConsistent${survey.id}" name="areConsistent" type="checkbox"
                                                   value="" style="vertical-align:middle;" onclick="showHiddenCheck(this,${items.id})"/>
                                            <span style="vertical-align:middle;">一致</span>
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="登记面积"
                                                   id="registrationAddress${survey.id}" name="registrationAddress"
                                                   class="form-control showHidden" value="${survey.registrationAddress}"></td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="实际面积"
                                                   id="actualAddress${survey.id}" name="actualAddress" class="form-control showHidden"
                                                   value="${survey.actualAddress}">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="差异原因"
                                                   id="differenceReason${survey.id}" name="differenceReason"
                                                   class="form-control showHidden" value="${survey.differenceReason}">
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="证明文件"
                                                   id="credential${survey.id}" name="credential" class="form-control showHidden"
                                                   value="${survey.credential}">
                                        </td>
                                        <td>
                                            <input id="credentialAccessory${survey.id}" name="credentialAccessory"
                                                   type="file" multiple="false" class="showHidden">
                                            <div id="_credentialAccessory${survey.id}" class="showHidden"></div>
                                        </td>
                                        <td>
                                            <input type="text" data-rule-maxlength="50" placeholder="证明人"
                                                   id="voucher${survey.id}" name="voucher" class="form-control showHidden"
                                                   value="${survey.voucher}">
                                        </td>
                                        <td>
                                            <input placeholder="调查时间" id="surveyTime${survey.id}" name="surveyTime"
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate showHidden" readonly="readonly"
                                                   value='<fmt:formatDate value="${surveyAssetOtherTemplate.exerciseDate}" pattern="yyyy-MM-dd"/>'>
                                        </td>
                                        <td>
                                            <a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空重填</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>

                    <form id="frm_checkbox" class="form-horizontal">
                        <div class="form-group">

                            <label class="col-sm-2 control-label" data-label="&lt;label&gt;">
                                他项权利与实际情况是否一致
                            </label>

                            <label class="col-sm-0.5 control-label">
                                <input id="agreementBox" type="checkbox" value="checkbox"
                                       style="vertical-align:middle;"/>
                                <span style="vertical-align:middle;">一致</span>
                            </label>
                        </div>

                    </form>

                    <form id="frm_survey" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                他权登记人
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="他权登记人"
                                           id="otherRightsRegistrar" name="otherRightsRegistrar" class="form-control"
                                           value="${surveyAssetOtherTemplate.otherRightsRegistrar}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                实际行权人
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="实际行权人"
                                           id="rightHander" name="rightHander" class="form-control"
                                           value="${surveyAssetOtherTemplate.rightHander}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                登记面积
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="登记面积"
                                           id="registerArea" name="registerArea" class="form-control"
                                           value="${surveyAssetOtherTemplate.registerArea}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <label class="col-sm-1 control-label">
                                实际面积
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="实际面积"
                                           id="actualArea" name="actualArea" class="form-control"
                                           value="${surveyAssetOtherTemplate.actualArea}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                登记用途
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="登记用途"
                                           id="registerPurpose" name="registerPurpose" class="form-control"
                                           value="${surveyAssetOtherTemplate.registerPurpose}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                实际用途
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="实际用途"
                                           id="actualPurpose" name="actualPurpose" class="form-control"
                                           value="${surveyAssetOtherTemplate.actualPurpose}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">登记日期</label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="登记日期" id="registerDate" name="registerDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.registerDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">到期日</label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="到期日" id="dueDate" name="dueDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.dueDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">实际行权人行权日期</label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="实际行权人行权日期" id="exerciseDate"
                                           name="exerciseDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.exerciseDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">预计到期日</label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="预计到期日" id="predictDueDate"
                                           name="predictDueDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.predictDueDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}成果提交</h2>
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

<%@include file="/views/share/main_footer.jsp" %>
<%--<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>--%>
<script type="application/javascript">

    $(function () {
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
            target: "credentialAccessory${checkContentList.get(0).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: 0,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${checkContentList.get(0).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${empty surveyAssetTemplateVo?0:surveyAssetTemplateVo.credentialAccessory},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "credentialAccessory${checkContentList.get(1).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: 0,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${checkContentList.get(1).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${empty surveyAssetTemplateVo?0:surveyAssetTemplateVo.credentialAccessory},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "credentialAccessory${checkContentList.get(2).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: 0,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${checkContentList.get(2).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${empty surveyAssetTemplateVo?0:surveyAssetTemplateVo.credentialAccessory},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "credentialAccessory${checkContentList.get(3).id}",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: 0,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory${checkContentList.get(3).id}",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${empty surveyAssetTemplateVo?0:surveyAssetTemplateVo.credentialAccessory},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    });

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
    var items ="";
    function getRowsData() {
        var trs = $("#tb_List").find('tbody tr');
        var data=[];
        $.each(trs, function (i, tr) {
            var item = {};

            item.id = $(tr).find('[name="id"]').val();    //id
            item.inventoryContent = $(tr).find('[name="inventoryContent"]').val();    //清查内容
            item.areConsistent = $(tr).find('[name="areConsistent"]').val();    //是否一致
            item.registrationAddress = $(tr).find('[name="registrationAddress"]').val();    //登记面积
            item.actualAddress = $(tr).find('[name="actualAddress"]').val();                //实际面积
            item.differenceReason = $(tr).find('[name="differenceReason"]').val();          //差异原因
            item.credential = $(tr).find('[name="credential"]').val();                      //证明文件
            item.voucher = $(tr).find('[name="voucher"]').val();                            //证明人
            item.surveyTime = $(tr).find('[name="surveyTime"]').val();                      //查勘时间
            data.push(item);
        });
        items=data;
        console.log(JSON.stringify(data));
        return data;
    }

    var json = "";
    function params() {
        getRowsData();
        var data = {};
        var surveyAssetInventoryDto = formParams("frm_assess");//评估人员 核对时间
        var surveyAssetOtherTemplateDto = formParams("frm_survey"); //他项权利是否一致
        data.surveyAssetInventoryDto = surveyAssetInventoryDto;
        data.surveyAssetOtherTemplateDto = surveyAssetOtherTemplateDto;
        data.surveyAssetTemplateDtos = items;

        //合并json
        json = JSON.stringify(data);
        console.info(data)
    }

    function submit() {
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
    function showHiddenCheck(_this,id) {
        if ($('#areConsistent'+id).prop("checked")) {
            $(_this).closest("tr").find(".showHidden,div").css('display','none');
            $(_this).closest("tr").find("input").val("");
        }else{
            $(_this).closest("tr").find(".showHidden,div").css('display','block');
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
    var evaluator = document.getElementById("evaluator");
    evaluator.onclick = function () {
        erpEmployee.select({
            onSelected: function (data) {
                evaluator.removeChild(evaluator.firstChild);
                var fieldElment = document.createElement("option");
                fieldElment.setAttribute("value", data.account);
                fieldElment.setAttribute("selected", "selected");
                fieldElment.appendChild(document.createTextNode(data.name));
                evaluator.appendChild(fieldElment);
            }
        });
    }


</script>

</html>

