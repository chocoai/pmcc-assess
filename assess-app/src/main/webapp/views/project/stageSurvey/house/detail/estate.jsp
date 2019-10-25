<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>楼盘</title>
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
                <!-- 非工业交通仓储 或 在建工程-->
                <c:if test="${basicApplyBatch.type == 0 || basicApplyBatch.type == 3}">
                    <%@include file="/views/project/stageSurvey/commonDetail/estate.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/estateLandState.jsp" %>
                </c:if>

                <!-- 构筑物 -->
                <c:if test="${basicApplyBatch.type == 2}">
                    <%@include file="/views/project/stageSurvey/commonDetail/estate.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/estateLandState.jsp" %>
                </c:if>


                <!-- 工业交通仓储 -->
                <c:if test="${basicApplyBatch.type == 1}">
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/estate.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/estateLandState.jsp" %>
                </c:if>

                <!-- 从表 -->
                <%@include file="/views/project/stageSurvey/commonDetail/estateNetwork.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/estateParking.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/matchingEnvironment.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/matchingFinance.jsp" %>

                <!-- 非工业交通仓储 或 在建工程-->
                <c:if test="${basicApplyBatch.type == 0 || basicApplyBatch.type == 3}">
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingEducation.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingRecreation.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingRestaurant.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingMarket.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingMedical.jsp" %>
                </c:if>


                <%@include file="/views/project/stageSurvey/commonDetail/matchingTransit.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/matchingTrafficHub.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/matchingMetro.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/matchingMainRoad.jsp" %>
                <%@include file="/views/project/stageSurvey/commonDetail/matchingMainConversion.jsp" %>


                <!-- 非工业交通仓储 或 在建工程-->
                <c:if test="${basicApplyBatch.type == 0 || basicApplyBatch.type == 3}">
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/estateSupplyWater.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/estateSupplyDrainWater.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/estateSupplyPower.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/estateSupplyHeating.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/estateSupplyGas.jsp" %>
                </c:if>

                <!-- 工业交通仓储 -->
                <c:if test="${basicApplyBatch.type == 1}">
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/matchingMaterial.jsp" %>
                </c:if>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js"></script>
</html>
