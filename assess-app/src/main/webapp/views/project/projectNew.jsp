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
                                <div class="row">
                                    <c:forEach var="classItem" items="${keyValueDtoList}">
                                        <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                            <c:if test="${not empty typeItem.keyValueDtos}">
                                                <c:forEach var="categoryItem" items="${typeItem.keyValueDtos}">
                                                    <div class="col-sm-6 col-lg-3">
                                                        <div class="card p-3">
                                                            <div class="d-flex align-items-center">
									                            <span class="stamp stamp-md bg-success mr-3">
										                            <i class="fas fa-gem"></i>
                                                                </span>
                                                                <div>
                                                                    <h5 class="mb-1"><b><a target="_blank"
                                                                                           href="${pageContext.request.contextPath}/${categoryItem.explain}?projectClassId=${classItem.key}&projectTypeId=${typeItem.key}&projectCategoryId=${categoryItem.key}">${categoryItem.value}</a></b></h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
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
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

<!-- end: MAIN CONTAINER -->
</div>
</body>

</html>
