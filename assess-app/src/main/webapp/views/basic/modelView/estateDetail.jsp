<%@ page contentType="text/html;charset=UTF-8" language="java" %>





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


<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js"></script>