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
            <div class="x_panel" style="display: none;">
                <div class="x_title">
                    <h2>查勘明细</h2>
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
                    <h2>查勘信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <button type="button" class="btn btn-primary" onclick="selectDynamicForm()">
                        选择表单模板
                    </button>
                    <form id="frm_survey" class="form-horizontal">
                        <input type="hidden" name="id" value="${surveyLocaleExploreDetail.id}">
                        <input type="hidden" name="dynamicFormId" value="${surveyLocaleExploreDetail.dynamicFormId}">
                        <input type="hidden" name="dynamicTableId" value="${surveyLocaleExploreDetail.dynamicTableId}">
                        <input type="hidden" name="dynamicTableName" value="${surveyLocaleExploreDetail.dynamicTableName}">
                        <input type="hidden" name="planDetailsId" value="${planDetailsId}">
                        <input type="hidden" id="surveyLocaltion" name="surveyLocaltion"
                               value="${surveyLocaleExploreDetail.surveyLocaltion}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">查勘人<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50" placeholder="查勘人"
                                           id="surveyPeople" name="surveyPeople" required
                                           value="${surveyLocaleExploreDetail.surveyPeople}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">查勘时间<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input placeholder="查勘时间" id="surveyTime" name="surveyTime"
                                           data-date-format="yyyy-mm-dd" required
                                           value="<fmt:formatDate value="${surveyLocaleExploreDetail.surveyTime}"   pattern="yyyy-MM-dd"/>"
                                           class="form-control date-picker dbdate" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">领勘人<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50" placeholder="领勘人" required
                                           id="ledLuminousPeople" name="ledLuminousPeople"
                                           value="${surveyLocaleExploreDetail.ledLuminousPeople}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">相关权证<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <c:forEach items="${declareRecords}" var="item">
                                        <input type="checkbox" name="correlationCard" value="${item.id}">${item.name}
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50" placeholder="楼盘名称"
                                           id="houseName" name="houseName" required
                                           value="${surveyLocaleExploreDetail.houseName}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘定位
                            </label>
                            <div class="col-sm-11">
                                <c:if test="${surveyLocaleExploreDetail.id eq 0}">
                                    <iframe src="${pageContext.request.contextPath}/map/positionPicker?position=${surveyLocaleExploreDetail.surveyLocaltion}"
                                            width="900" height="600" frameborder=”no” border=”0″ marginwidth=”0″
                                            marginheight=”0″ scrolling=”no” allowtransparency=”yes”></iframe>
                                </c:if>
                                <c:if test="${surveyLocaleExploreDetail.id ne 0}">
                                    <div id="_surveyLocaltion">
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            //显示定位图片
                                            loadSurveyLocaltionFiles();
                                        })
                                        function loadSurveyLocaltionFiles() {
                                            FileUtils.getFileShows({
                                                target: "surveyLocaltion",
                                                formData: {
                                                    tableName: "tb_survey_locale_explore_detail",
                                                    tableId: ${surveyLocaleExploreDetail.id},
                                                    fieldsName: "survey_localtion"
                                                },
                                                deleteFlag: false
                                            })
                                        }
                                    </script>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘图像上传
                            </label>
                            <div class="col-sm-11">
                                <input id="surveyImage" name="surveyImage" type="file" multiple="false">
                                <div id="_surveyImage">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘视频上传
                            </label>
                            <div class="col-sm-11">
                                <input id="surveyVideo" name="surveyPicture" type="file" multiple="false">
                                <div id="_surveyVideo">
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
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyLocale/save",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {
                        window.close();
                        window.opener.location.reload();
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
                    var form=$("#frm_survey");
                    $.ajax({
                        url: "${pageContext.request.contextPath}/formConfigure/getDataDicFormInfo",
                        type: "post",
                        dataType: "json",
                        data: {baseDataDicId: node.id},
                        success: function (result) {
                            if (result.ret) {
                                form.find('[name=dynamicFormId]').val(node.id);
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
        $("#surveyLocaltion").val(positionResult.position);
    }
    //map选址失败回调
    function positionPickerFail(positionResult) {
        //暂不处理
    }
</script>


<script type="text/javascript">

    //附件上传和加载
    $(function () {
        FileUtils.uploadFiles({
            target: "surveyImage",
            showFileList: false,
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: ${surveyLocaleExploreDetail.id},
                fieldsName: "survey_image"
            },
            deleteFlag: true
        }, {
            onUploadComplete: function () {
                loadSurveyImageFiles();
            }
        });

        FileUtils.uploadFiles({
            target: "surveyVideo",
            showFileList: false,
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: ${surveyLocaleExploreDetail.id},
                fieldsName: "survey_video"
            },
            deleteFlag: true
        }, {
            onUploadComplete: function () {
                loadSurveyVideoFiles();
            }
        });

        loadSurveyImageFiles();
        loadSurveyVideoFiles();
    });

    //显示图片附件
    function loadSurveyImageFiles() {
        FileUtils.getFileShows({
            target: "surveyImage",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: ${surveyLocaleExploreDetail.id},
                fieldsName: "survey_image"
            },
            deleteFlag: true
        })
    }

    //显示图片附件
    function loadSurveyVideoFiles() {
        FileUtils.getFileShows({
            target: "surveyVideo",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: ${surveyLocaleExploreDetail.id},
                fieldsName: "survey_video"
            },
            deleteFlag: true
        })
    }

</script>
</html>
