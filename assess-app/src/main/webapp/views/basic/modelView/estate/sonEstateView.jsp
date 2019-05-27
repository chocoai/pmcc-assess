<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="/views/project/stageSurvey/examine/residence/apply/estateNetwork.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/estateParking.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingEnvironment.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingFinance.jsp" %>

<div id="industryMatchingInfo" style="display: none;">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingEducation.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingRecreation.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingRestaurant.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMarket.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMedical.jsp" %>
</div>

<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingTransit.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingTrafficHub.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMetro.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMainRoad.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMainConversion.jsp" %>



<div id="industrySupplyWater">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyWater.jsp" %>
</div>

<div  id="industryDrainWater">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyDrainWater.jsp" %>
</div>

<div  id="industrySupplyPower">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyPower.jsp" %>
</div>

<div  id="industrySupplyHeating">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyHeating.jsp" %>
</div>

<div  id="industrySupplyGas">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/estateSupplyGas.jsp" %>
</div>

<div  id="industryMaterial">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/matchingMaterial.jsp" %>
</div>

<script src="${pageContext.request.contextPath}/js/basic/estate/sonEstateView.js"></script>


