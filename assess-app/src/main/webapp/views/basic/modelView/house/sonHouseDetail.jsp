<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="/views/project/stageSurvey/examine/residence/detail/houseRoom.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/houseFaceStreet.jsp" %>

<div id="industryCorollaryEquipment">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/houseCorollaryEquipment.jsp" %>
</div>
<div id="industryIntelligent">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseIntelligent.jsp" %>
</div>
<div id="industryWater">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseWater.jsp" %>
</div>
<div id="industryHouseWaterDrain">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseWaterDrain.jsp" %>
</div>
<div id="industryNewWind">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseNewWind.jsp" %>
</div>
<div id="industryAirConditioner">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseAirConditioner.jsp" %>
</div>
<div id="industryHeating">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseHeating.jsp" %>
</div>

<%@include file="/views/project/stageSurvey/examine/residence/detail/houseDamagedDegree.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js"></script>