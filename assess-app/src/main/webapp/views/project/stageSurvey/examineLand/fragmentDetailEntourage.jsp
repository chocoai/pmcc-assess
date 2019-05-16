<%--
 从表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <!--  非工业仓储 -->
    <c:if test="${basicApply.type==0}">
        <%@include file="/views/project/stageSurvey/examine/residence/detail/estateParking.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingEducation.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMarket.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMedical.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingRecreation.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingRestaurant.jsp" %>
        <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMetro.jsp" %>

        <%@include file="/views/project/stageSurvey/examine/residence/detail/houseFaceStreet.jsp" %>
    </c:if>

    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingEnvironment.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingFinance.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingTransit.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMainRoad.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingMainConversion.jsp" %>
    <%@include file="/views/project/stageSurvey/examine/residence/detail/matchingTrafficHub.jsp" %>

    <!--  工业仓储 -->
    <c:if test="${basicApply.type == 1}">
        <%@include file="/views/project/stageSurvey/examine/industry/detail/matchingMaterial.jsp" %>
    </c:if>
</div>
