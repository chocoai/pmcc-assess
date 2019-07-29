<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 非工业交通仓储 -->
<c:if test="${basicApply.type == 0}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/building.jsp" %>
</c:if>

<!-- 构筑物 -->
<c:if test="${basicApply.type == 2}">
    <%@include file="/views/project/stageSurvey/examine/residence/apply/structuresProspect.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/examine/industry/apply/building.jsp" %>
</c:if>


<script src="${pageContext.request.contextPath}/js/basic/building/sonBuildView.js"></script>

