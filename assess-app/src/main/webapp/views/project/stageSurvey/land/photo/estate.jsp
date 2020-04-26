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
                                <form class="form-horizontal" id="frm_estate">
                                    <input type="hidden" name="id" value="${basicEstate.id}">

                                    <div id="landEstateFile"></div>

                                </form>
                                <div class="x_title">
                                    <div class="clearfix"></div>
                                </div>
                                <form class="form-horizontal" id="frm_estateLandState">
                                    <input type="hidden" name="id" value="${basicEstateLandState.id}">
                                    <input type="hidden" name="landFactorTotalScoreResult">
                                    <input type="hidden" name="landLevelContentResult">

                                </form>


                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>

</html>
<script type="text/javascript">

</script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}','${tbType}');
    })
</script>

