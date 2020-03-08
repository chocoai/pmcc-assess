<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-header">


    <!-- Navbar Header -->
    <nav class="navbar navbar-header navbar-expand-lg" data-background-color="blue2">

        <div class="container-fluid">
            <div class="nav-toggle">
                <button class="btn btn-toggle toggle-sidebar"><i class="icon-menu"></i></button>
            </div>
            <div class="collapse" id="search-nav"  style="max-width: 100%">
                <h3 style="color: #ffffff">
                    <span class="label label-warning">${projectInfo.projectStatus}</span> <span style="cursor: pointer;" onclick="window.location.href='${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=${projectInfo.id}'">${projectInfo.projectName}</span>
                </h3>
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
                                        <i class="far fa-calendar-check" aria-hidden="true"></i>
                                    </c:when>
                                    <c:when test="${projectPlan.projectStatus eq 'planExecute' or projectPlan.projectStatus eq 'task'}">
                                        <i class="far fa-calendar-alt" aria-hidden="true"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="far fa-calendar" aria-hidden="true"></i>
                                    </c:otherwise>
                                </c:choose>
                                <p>
                                        ${projectPlan.planName}
                                </p>

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

