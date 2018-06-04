<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/9/20
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<small>

    <a class="btn btn-danger" href="${pageContext.request.contextPath}/projectInfo/projectIndex" target="_blank">
        新建项目
    </a>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/csrProjectInfo/projectIndex" target="_blank">
        新建债权项目
    </a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/projectCenter/projectCsrList?menuid=${menuid}" target="_self">
        债权项目列表
    </a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/projectCenter/projectList?menuid=${menuid}" target="_self">
        项目列表
    </a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/projectCenter/projectCalendar?menuid=${menuid}" target="_self">
        项目日历
    </a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/projectCenter/projectProgress?menuid=${menuid}" target="_self">
        项目进度
    </a>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/projectCenter/index?menuid=${menuid}" target="_self">
        项目中心
    </a>
</small>
