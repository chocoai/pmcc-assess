<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>单元</title>
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
                <!-- 非工业交通仓储 -->
                <c:if test="${basicApplyBatch.type == 0 }">
                    <%@include file="/views/project/stageSurvey/commonDetail/unit.jsp" %>
                </c:if>


                <!-- 工业交通仓储 -->
                <c:if test="${basicApplyBatch.type == 1}">
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/unit.jsp" %>
                </c:if>



            </div>
            <div class="x_panel">
                <div class="x_content">
                    <%@include file="/views/project/stageSurvey/commonDetail/unitDecorate.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/unitHuxing.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/unitElevator.jsp" %>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button class="btn btn-default" onclick="window.close()">
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
<%@include file="/views/project/tool/toolMapHandleView.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>


<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
    })
</script>
</html>
