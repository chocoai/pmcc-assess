<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 假设开发法 -->
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>
            ${judgeObject.name}
            <small>(${judgeObject.evaluationArea}㎡)</small>
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <%@include file="/views/method/module/developmentModule/landEngineeringDetail.jsp" %>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRateDetail.jsp" %>

<%@include file="/views/method/module/economicIndicators.jsp" %>

