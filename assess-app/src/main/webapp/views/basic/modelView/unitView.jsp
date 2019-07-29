<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 非工业交通仓储 -->
<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/unit.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/unit.jsp" %>
</c:if>


<%@include file="/views/project/stageSurvey/examine/residence/apply/unitDecorate.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/unitHuxing.jsp" %>
<%@include file="/views/project/stageSurvey/examine/residence/apply/unitElevator.jsp" %>

<script src="${pageContext.request.contextPath}/js/basic/unit/sonUnitView.js"></script>

