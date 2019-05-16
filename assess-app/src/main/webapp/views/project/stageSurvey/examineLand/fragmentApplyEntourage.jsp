<%--
 从表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <!--  非工业仓储 -->
    <c:if test="${basicApply.type==0}">
        <%@include file="/views/project/stageSurvey/examine/residence/apply/estateParking.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingEducation.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMarket.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMedical.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingRecreation.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingRestaurant.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMetro.jsp" %>

        <%@include file="/views/project/stageSurvey/examine/residence/apply/houseFaceStreet.jsp" %>
    </c:if>

    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingEnvironment.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingFinance.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingTransit.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMainRoad.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingMainConversion.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/apply/matchingTrafficHub.jsp" %>

    <!--  工业仓储 -->
    <c:if test="${basicApply.type == 1}">
        <%@include file="/views/project/stageSurvey/examine/industry/apply/matchingMaterial.jsp" %>
    </c:if>
</div>
