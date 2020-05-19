<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>附件上传页面</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        附件上传页面
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>单元基本信息</h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicUnitFrm">
                                        <input type="hidden" name="id" value="${basicUnit.id}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <%--<%@include file="/views/project/stageSurvey/common/unitHuxing.jsp" %>--%>
                                </div>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonUnitView.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
                                <script type="text/javascript">
                                    $(function () {
                                        unitCommon.initById('${basicUnit.id}');
                                    })
                                </script>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/project/stageSurvey/common/canvasQRcodePagination.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>

<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">

</script>

