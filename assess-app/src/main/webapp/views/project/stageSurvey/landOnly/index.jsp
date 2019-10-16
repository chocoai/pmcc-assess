<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>地块信息</title>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/basic.common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
    <script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
    <script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                       地块信息
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <%@include file="/views/project/stageSurvey/landOnly/estate.jsp" %>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                        <button class="btn btn-warning" onclick="saveDraft();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">
$(function () {
    estateCommon.initById('${basicEstate.id}')
})
</script>
