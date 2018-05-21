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
                    <h2>案例调查详情</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_survey" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    案例类型
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetailName.caseTypeName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    信息来源
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetailName.informationSourceName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼盘名称
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetail.houseName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetail.price}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易情况
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetail.dealCaondition}</label>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易时间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate value="${surveyCaseStudyDetail.dealTime}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    付款方式
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetail.paymentMethod}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    联系人
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetail.linkman}</label>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    联系方式
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyCaseStudyDetail.contactWay}</label>
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
                            关闭
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



</script>

</html>
