<%@ page contentType="text/html;charset=UTF-8" language="java" %>






<!-- 非工业交通仓储 或 在建工程-->
<c:if test="${basicApply.type == 0 || basicApply.type == 3}">
    <%@include file="/views/project/stageSurvey/commonDetail/building.jsp" %>
</c:if>

<!-- 构筑物 -->
<c:if test="${basicApply.type == 2}">
    <%@include file="/views/project/stageSurvey/commonDetail/structuresProspect.jsp" %>
</c:if>


<!-- 工业交通仓储 -->
<c:if test="${basicApply.type == 1}">
    <%@include file="/views/project/stageSurvey/commonDetail/industry/building.jsp" %>
</c:if>







