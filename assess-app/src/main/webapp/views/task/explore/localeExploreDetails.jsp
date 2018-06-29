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
            <!--填写表单-->
            <input type="hidden" id="JsonValue" value='${JsonValue}'>
            <div class="x_panel" style="display: none;">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>查勘明细</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_dynamic_content" class="form-horizontal">

                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>查勘信息详情</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_survey" class="form-horizontal">
                        <input type="hidden" name="dynamicFormId" value="${surveyLocaleExploreDetail.dynamicFormId}">
                        <input type="hidden" name="dynamicTableId" value="${surveyLocaleExploreDetail.dynamicTableId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">查勘人<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyLocaleExploreDetail.surveyPeople}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">查勘时间<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate value="${surveyLocaleExploreDetail.surveyTime}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">领勘人<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyLocaleExploreDetail.ledLuminousPeople}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyLocaleExploreDetail.houseName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘定位
                            </label>
                            <div class="col-sm-11">
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
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘图像
                            </label>
                            <div class="col-sm-11">
                                <div id="_surveyImage">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘视频
                            </label>
                            <div class="col-sm-11">
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src='/assets/js/comm/pmcc.js'></script>
<script src='/assets/js/comm/erp-footer.js'></script>
<script src='${pageContext.request.contextPath}/js/common.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form-configure-utils.js"></script>
<script type="text/javascript">

    //附件加载
    $(function () {
        if ("${surveyLocaleExploreDetail.dynamicFormId}" != "0") {
            showDynamicForm();
        }

        loadSurveyImageFiles();
        loadSurveyVideoFiles();
    })

    //显示动态表单数据
    function showDynamicForm() {
        FormConfigureUtils.getDynamicFormHtml({
            formModuleId: $("#frm_survey").find('[name=dynamicFormId]').val(),
            readOnly: true,
            jsonValue: $("#JsonValue").val(),
            success: function (html) {
                $("#frm_dynamic_content").append(html).closest('.x_panel').show();
            }
        });
    }

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

    //显示视频附件
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
