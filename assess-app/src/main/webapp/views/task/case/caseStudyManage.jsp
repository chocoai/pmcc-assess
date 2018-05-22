<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
</head>

<body>

<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <input type="hidden" id="JsonValue" value='${JsonValue}'>
            <div class="x_panel" style="display: none;">
                <div class="x_title">
                    <h2>案例调查明细</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_dynamic_content" class="form-horizontal">

                    </form>
                </div>
            </div>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>案例信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <button type="button" class="btn btn-primary" onclick="selectDynamicForm()">
                        选择表单模板
                    </button>
                    <form id="frm_survey" class="form-horizontal">
                        <input type="hidden" name="id" value="${surveyCaseStudyDetail.id}">
                        <input type="hidden" name="planDetailsId" value="${planDetailsId}">
                        <input type="hidden" name="dynamicFormId" value="${surveyCaseStudyDetail.dynamicFormId}">
                        <input type="hidden" name="dynamicTableId" value="${surveyCaseStudyDetail.dynamicTableId}">
                        <input type="hidden" name="dynamicTableName" value="${surveyCaseStudyDetail.dynamicTableName}">
                        <input type="hidden" id="caseLocaltion" name="caseLocaltion"
                               value="${surveyCaseStudyDetail.caseLocaltion}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    相关权证
                                </label>
                                <div class="col-sm-11">
                                    <c:forEach items="${surveyCorrelationCardVos}" var="item">
                                        <span class="checkbox-inline">
                                            <input type="checkbox" ${item.bisChecked?"checked=\"checked\"":""}
                                                   id="correlationCard_${item.id}"
                                                   name="correlationCard" value="${item.id}">
                                        <label for="correlationCard_${item.id}">${item.name}</label></span>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    案例类型
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="caseType" name="caseType">
                                        <option value="">-请选择-</option>
                                        <c:forEach var="item" items="${caseType}">
                                            <option ${item.id eq surveyCaseStudyDetail.caseType?"selected=\"selected\"":""}  value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    信息来源
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="informationSource" name="informationSource">
                                        <option value="">-请选择-</option>
                                        <c:forEach var="item" items="${caseInfoSource}">
                                            <option ${item.id eq surveyCaseStudyDetail.informationSource?"selected=\"selected\"":""} value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼盘名称
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50" placeholder="楼盘名称"
                                           id="houseName" name="houseName"
                                           value="${surveyCaseStudyDetail.houseName}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50" placeholder="单价"
                                           id="price" name="price"
                                           value="${surveyCaseStudyDetail.price}"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易情况
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50" placeholder="交易情况"
                                           id="dealCaondition" name="dealCaondition"
                                           value="${surveyCaseStudyDetail.dealCaondition}"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易时间
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="交易时间" id="dealTime" name="dealTime"
                                           data-date-format="yyyy-mm-dd"
                                           value="<fmt:formatDate value="${surveyCaseStudyDetail.dealTime}"   pattern="yyyy-MM-dd"/>"
                                           class="form-control date-picker dbdate" readonly="readonly">
                                </div>
                            </div>

                        </div>


                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    付款方式
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50" placeholder="付款方式"
                                           id="paymentMethod" name="paymentMethod"
                                           value="${surveyCaseStudyDetail.paymentMethod}"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    联系人
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50" placeholder="联系人"
                                           id="linkman" name="linkman"
                                           value="${surveyCaseStudyDetail.linkman}"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    联系方式
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50" placeholder="联系方式"
                                           id="contactWay" name="contactWay"
                                           value="${surveyCaseStudyDetail.contactWay}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                案例定位
                            </label>
                            <div class="col-sm-11">
                                <c:if test="${surveyCaseStudyDetail.id eq 0}">
                                    <iframe src="${pageContext.request.contextPath}/map/positionPicker?position=${surveyCaseStudyDetail.caseLocaltion}"
                                            width="900" height="600" frameborder=”no” border=”0″ marginwidth=”0″
                                            marginheight=”0″ scrolling=”no” allowtransparency=”yes”></iframe>
                                </c:if>
                                <c:if test="${surveyCaseStudyDetail.id ne 0}">
                                    <div id="_caseLocaltion">
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            //显示定位图片
                                            loadCaseLocaltionFiles();
                                        })
                                        function loadCaseLocaltionFiles() {
                                            FileUtils.getFileShows({
                                                target: "caseLocaltion",
                                                formData: {
                                                    tableName: "tb_survey_case_study_detail",
                                                    tableId: ${surveyCaseStudyDetail.id},
                                                    fieldsName: "case_localtion"
                                                },
                                                deleteFlag: false
                                            })
                                        }
                                    </script>
                                </c:if>
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

                        <button id="saveFrm" type="button" class="btn btn-primary" onclick="saveData()">
                            保存
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datadic-select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form-configure-utils.js"></script>
<script type="text/javascript">
    $(function () {
        if ("${surveyCaseStudyDetail.dynamicFormId}" > "0") {
            showDynamicForm();
        }
    })

    //显示动态表单数据
    function showDynamicForm() {
        FormConfigureUtils.getDynamicFormHtml({
            formModuleId: $("#frm_survey").find('[name=dynamicFormId]').val(),
            readOnly: false,
            jsonValue: $("#JsonValue").val(),
            success: function (html) {
                $("#frm_dynamic_content").append(html).closest('.x_panel').show();
            }
        });
    }

    //保存数据
    function saveData() {
        if (!$("#frm_dynamic_content").valid()) {
            return false;
        }
        if (!$("#frm_survey").valid()) {
            return false;
        }

        var dynamicFormData = formParams("frm_dynamic_content");
        var data = formParams("frm_survey");
        data.dynamicFormData = dynamicFormData;
        console.info(data);
        $.ajax({
            url: "${pageContext.request.contextPath}/caseStudy/save",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {
                        window.close();
                        window.opener.location.reload();    //刷新父页面
                    });
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

    //选择动态表单模板
    function selectDynamicForm() {
        assessDataDic.select({
            key: "assess.class",
            onSelected: function (nodes) {
                if (nodes) {
                    var node = nodes[0];
                    var form = $("#frm_survey");
                    $.ajax({
                        url: "${pageContext.request.contextPath}/formConfigure/getDataDicFormInfo",
                        type: "post",
                        dataType: "json",
                        data: {baseDataDicId: node.id},
                        success: function (result) {
                            if (result.ret) {
                                form.find('[name=dynamicFormId]').val(result.data.id);
                                form.find('[name=dynamicTableId]').val(result.data.tableId);
                                form.find('[name=dynamicTableName]').val(result.data.tableName);
                                FormConfigureUtils.getDynamicFormHtml({
                                    formModuleId: result.data.id,
                                    readOnly: false,
                                    jsonValue: result.data.fieldVos,
                                    success: function (html) {
                                        $("#frm_dynamic_content").append(html).closest('.x_panel').show();
                                    }
                                });
                            }
                        }
                    })
                }
            }
        })
    }

    //map选址成功回调
    function positionPickerSuccess(positionResult) {
        $("#caseLocaltion").val(positionResult.position);
    }
    //map选址失败回调
    function positionPickerFail(positionResult) {
        //暂不处理
    }
</script>

</html>
