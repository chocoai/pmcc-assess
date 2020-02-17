<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
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
                                                                <%--<h3>${typeItem.value}</h3>--%>
                                                                <%--<p>${classItem.value}</p>--%>
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
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

<!-- end: MAIN CONTAINER -->
</div>
</body>

</html>
