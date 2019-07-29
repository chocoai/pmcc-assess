<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!-- 非工业交通仓储 -->
<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/estate.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/estateLandState.jsp" %>
</c:if>

<!-- 构筑物 -->
<c:if test="${basicApply.type == 2}">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/estate.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/estateLandState.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/estate.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/estateLandState.jsp" %>
</c:if>

<!-- 从表 -->
<%@include file="/views/project/stageSurvey/examine/residence/detail/estateNetwork.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/estateParking.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingEnvironment.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingFinance.jsp" %>

<!-- 非工业交通仓储 -->
<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingEducation.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingRecreation.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingRestaurant.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMarket.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMedical.jsp" %>
</c:if>


<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingTransit.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingTrafficHub.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMetro.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMainRoad.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMainConversion.jsp" %>


<!-- 非工业交通仓储 -->
<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/estateSupplyWater.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/detail/estateSupplyDrainWater.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/detail/estateSupplyPower.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/detail/estateSupplyHeating.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/industry/detail/estateSupplyGas.jsp" %>
</c:if>

<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/matchingMaterial.jsp" %>
</c:if>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js"></script>
