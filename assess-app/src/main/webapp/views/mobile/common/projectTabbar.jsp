<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item mui-active" href="${pageContext.request.contextPath}/mobileProjectInfo/projectInfo">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">信息</span>
    </a>
    <a class="mui-tab-item" onclick="location.href='${pageContext.request.contextPath}/mobileProjectInfo/projectSurvey';" href="javascript://">
        <span class="mui-icon mui-icon-email"><span class="mui-badge">9</span></span>
        <span class="mui-tab-label">查勘</span>
    </a>
    <a class="mui-tab-item" href="${pageContext.request.contextPath}/mobileProjectInfo/projectCase">
        <span class="mui-icon mui-icon-contact"></span>
        <span class="mui-tab-label">案例</span>
    </a>
    <a class="mui-tab-item" href="${pageContext.request.contextPath}/mobileProjectInfo/projectInventory">
        <span class="mui-icon mui-icon-gear"></span>
        <span class="mui-tab-label">清查</span>
    </a>
</nav>