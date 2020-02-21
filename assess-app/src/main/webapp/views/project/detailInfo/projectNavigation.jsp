<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-header">


    <!-- Navbar Header -->
    <nav class="navbar navbar-header navbar-expand-lg" data-background-color="blue2">

        <div class="container-fluid">
            <div class="collapse" id="search-nav">
                <h3 style="color: #ffffff">
                    <a class="tooltips" data-placement="bottom" data-original-title="返回系统首页"
                       href="${pageContext.request.contextPath}/home/main"><i class="fas fa-home"></i></a>
                    <span class="label label-warning">${projectInfo.projectStatus}</span>${projectInfo.projectName}</h3>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->
</div>
<div class="sidebar sidebar-style-2">
    <div class="scroll-wrapper sidebar-wrapper scrollbar scrollbar-inner" style="position: relative;">
        <div class="sidebar-wrapper scrollbar scrollbar-inner scroll-content"
             style="height: 2813px; margin-bottom: 0px; margin-right: 0px; max-height: none;">
            <div class="sidebar-content">
                <ul class="nav nav-primary">
                    <c:forEach items="${projectPlanList}" var="projectPlan">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/projectCenter/projectStageInfo/${projectInfo.id}/${projectPlan.workStageId}">
                                <c:choose>
                                    <c:when test="${projectPlan.projectStatus eq 'finish'}">
                                        <i class="fa fa-check-square" aria-hidden="true"></i>
                                    </c:when>
                                    <c:when test="${projectPlan.projectStatus eq 'planExecute' or projectPlan.projectStatus eq 'task'}">
                                        <i class="fa fa-check-square-o" aria-hidden="true"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-square-o" aria-hidden="true"></i>
                                    </c:otherwise>
                                </c:choose>
                                    ${projectPlan.planName}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="scroll-element scroll-x">
            <div class="scroll-element_outer">
                <div class="scroll-element_size"></div>
                <div class="scroll-element_track"></div>
                <div class="scroll-bar ui-draggable ui-draggable-handle" style="width: 100px;"></div>
            </div>
        </div>
        <div class="scroll-element scroll-y">
            <div class="scroll-element_outer">
                <div class="scroll-element_size"></div>
                <div class="scroll-element_track"></div>
                <div class="scroll-bar ui-draggable ui-draggable-handle" style="height: 100px;"></div>
            </div>
        </div>
    </div>
</div>

