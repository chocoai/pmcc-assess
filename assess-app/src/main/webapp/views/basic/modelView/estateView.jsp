<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 非工业交通仓储 或 在建工程-->
<c:if test="${basicApply.type == 0 || basicApply.type == 3}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/estate.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/estateLandState.jsp" %>
</c:if>

<!-- 构筑物 -->
<c:if test="${basicApply.type == 2}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/estate.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/estateLandState.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estate.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/estateLandState.jsp" %>
</c:if>

<!-- 从表 -->
<%@include file="/views/project/stageSurvey/examine/residence/apply/estateNetwork.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/estateParking.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingEnvironment.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingFinance.jsp" %>

<!-- 非工业交通仓储 或 在建工程-->
<c:if test="${basicApply.type == 0 || basicApply.type == 3}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingEducation.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingRecreation.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingRestaurant.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMarket.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMedical.jsp" %>
</c:if>


<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingTransit.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingTrafficHub.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMetro.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMainRoad.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMainConversion.jsp" %>


<!-- 非工业交通仓储 或 在建工程-->
<c:if test="${basicApply.type == 0 || basicApply.type == 3}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyWater.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyDrainWater.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyPower.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyHeating.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyGas.jsp" %>
</c:if>

<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/matchingMaterial.jsp" %>
</c:if>



<script src="${pageContext.request.contextPath}/js/basic/estate/sonEstateView.js?v=${assessVersion}"></script>