<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/9/20
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<small>
    <div class="x_content">
        <div class="btn-group">
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/projectCenter/projectNew"
               target="_self">
                新建项目
            </a>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary">项目列表</button>
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="${pageContext.request.contextPath}/projectCenter/projectList?menuid=${menuid}">项目列表</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/projectCenter/projectCsrList?menuid=${menuid}">债权项目列表</a>
                </li>
            </ul>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/projectCenter/projectCalendar?menuid=${menuid}"
               target="_self">
                项目日历
            </a>
        </div>
        <div class="btn-group">
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/projectCenter/index?menuid=${menuid}"
               target="_self">
                项目中心
            </a>
        </div>
    </div>
</small>
