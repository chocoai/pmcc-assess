<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/house.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseTrading.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/house.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseTrading.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/examine/residence/detail/houseFaceStreet.jsp" %>


<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseWater.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseWaterDrain.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseNewWind.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseAirConditioner.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseHeating.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/houseIntelligent.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/examine/residence/detail/houseRoom.jsp" %>

<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/houseCorollaryEquipment.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/examine/residence/detail/houseDamagedDegree.jsp" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js"></script>
