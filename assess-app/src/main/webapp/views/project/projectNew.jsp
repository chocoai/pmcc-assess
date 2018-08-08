<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="col-xs-12">
                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>
                            新建项目
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <%@include file="/views/project/projectCenterHeader.jsp" %>
                        <c:forEach var="classItem" items="${keyValueDtoList}">
                            <div class="x_panel">
                                <div class="x_title collapse-link">
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                    </ul>
                                    <h3>${classItem.value}</h3>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="window.open('${pageContext.request.contextPath}/${typeItem.explain}?projectClassId=${classItem.key}&projectTypeId=${typeItem.key}')">
                                                    ${typeItem.value}</button>
                                            <c:if test="${not empty typeItem.keyValueDtos}">
                                                <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown"
                                                        aria-expanded="false">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <c:forEach var="categoryItem" items="${typeItem.keyValueDtos}">
                                                        <li>
                                                            <a target="_blank"
                                                               href="${pageContext.request.contextPath}/${categoryItem.explain}?projectClassId=${classItem.key}&projectTypeId=${typeItem.key}&projectCategoryId=${categoryItem.key}">${categoryItem.value}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
