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
            <div class="x_panel">
                <div class="x_title">
                    <h2>查勘信息详情</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_survey" class="form-horizontal">
                        <input type="hidden" name="planDetailsId" value="${planDetailsId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">查勘人<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyLocaleExploreDetail.surveyPeople}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">查勘时间<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <label class="form-control"><fmt:formatDate value="${surveyLocaleExploreDetail.surveyTime}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">领勘人<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyLocaleExploreDetail.ledLuminousPeople}</label>
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
                                    <label class="form-control">${surveyLocaleExploreDetail.houseName}</label>
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
                                <%--<input id="surveyImage" name="surveyImage" type="file" multiple="false">--%>
                                <div id="_surveyImage">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                查勘视频上传
                            </label>
                            <div class="col-sm-11">
                                <%--<input id="surveyVideo" name="surveyVideo" type="file" multiple="false">--%>
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
<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">
    //保存数据
    function saveData() {
        var data = formParams("frm_survey");
        data.surveyLocaltion = document.getElementById("surveyLocaltion").innerHTML;
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyLocale/save",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {
                        window.close();
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
</script>


<script type="text/javascript">

    //附件加载
    $(function () {

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
            target: "surveyImage",
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
