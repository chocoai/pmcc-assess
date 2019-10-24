<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>纯土地</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        详情
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <%@include file="/views/project/stageSurvey/landOnly/detail/estate.jsp" %>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>

</html>
<script type="text/javascript">
    $(function () {
        <%--estateCommon.initById('${basicEstate.id}');--%>
        <%--houseCommon.initById('${basicHouse.id}');--%>
    });
</script>
