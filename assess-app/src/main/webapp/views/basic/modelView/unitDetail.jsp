<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 非工业交通仓储 -->
<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/detail/unit.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/detail/unit.jsp" %>
</c:if>



<%@include file="/views/project/stageSurvey/examine/residence/detail/unitDecorate.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/unitHuxing.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/detail/unitElevator.jsp" %>
