<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- 非工业交通仓储 或 在建工程-->
<c:if test="${basicApplyBatch.type == 0 || basicApplyBatch.type == 3}">
    <%@include file="/views/project/stageSurvey/commonDetail/unit.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApplyBatch.type == 1}">
    <%@include file="/views/project/stageSurvey/commonDetail/industry/unit.jsp" %>
</c:if>



<%@include file="/views/project/stageSurvey/commonDetail/unitDecorate.jsp" %>
<%@include file="/views/project/stageSurvey/commonDetail/unitHuxing.jsp" %>
<%@include file="/views/project/stageSurvey/commonDetail/unitElevator.jsp" %>

<script src="${pageContext.request.contextPath}/js/basic/unit/sonUnitView.js?v=${assessVersion}"></script>