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
                                    <form class="form-horizontal" id="basicUnitFrm">
                                        <input type="hidden" name="id" value="${basicUnit.id}">
                                        <input type="hidden" name="quoteId" value="${basicUnit.quoteId}">
                                        <input type="hidden" name="estateId" value="${basicUnit.estateId}">
                                        <input type="hidden" name="buildingId" value="${basicUnit.buildingId}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">

                                                    <div id="basicUnit"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
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
</html>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');

    })
</script>