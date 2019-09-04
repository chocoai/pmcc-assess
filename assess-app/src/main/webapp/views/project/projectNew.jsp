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
            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>
                            新建项目
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <c:forEach var="classItem" items="${keyValueDtoList}">
                            <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                <c:if test="${not empty typeItem.keyValueDtos}">
                                    <c:forEach var="categoryItem" items="${typeItem.keyValueDtos}">
                                        <a target="_blank"
                                           href="${pageContext.request.contextPath}/${categoryItem.explain}?projectClassId=${classItem.key}&projectTypeId=${typeItem.key}&projectCategoryId=${categoryItem.key}">
                                            <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                                <div class="tile-stats">
                                                    <div class="icon"><i class="fa fa-bank"></i>
                                                    </div>
                                                    <div class="count">${categoryItem.value}</div>
                                                    <h3>${typeItem.value}</h3>
                                                    <p>${classItem.value}</p>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </div>
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
