<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/house.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseTrading.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/house.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseTrading.jsp" %>
</c:if>


<%@include file="/views/project/stageSurvey/examine/residence/apply/houseFaceStreet.jsp" %>

<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseWater.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseWaterDrain.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseNewWind.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseAirConditioner.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseHeating.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/houseIntelligent.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/examine/residence/apply/houseRoom.jsp" %>

<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/houseCorollaryEquipment.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/examine/residence/apply/houseDamagedDegree.jsp" %>

<script src="${pageContext.request.contextPath}/js/basic/house/sonHouseView.js"></script>