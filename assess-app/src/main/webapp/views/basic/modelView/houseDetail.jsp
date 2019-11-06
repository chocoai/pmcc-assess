<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${basicApply.type == 0 || basicApply.type == 3}">
    <%@include file="/views/project/stageSurvey/commonDetail/house.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseTrading.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/commonDetail/industry/house.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseTrading.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>


<c:if test="${basicApply.type == 0 || basicApply.type == 3}">
    <%@include file="/views/project/stageSurvey/commonDetail/houseWater.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseWaterDrain.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseNewWind.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseAirConditioner.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseHeating.jsp" %>
    <%@include file="/views/project/stageSurvey/commonDetail/houseIntelligent.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/commonDetail/houseRoom.jsp" %>

<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/commonDetail/industry/houseCorollaryEquipment.jsp" %>
</c:if>

<%@include file="/views/project/stageSurvey/commonDetail/houseDamagedDegree.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js?v=${assessVersion}"></script>