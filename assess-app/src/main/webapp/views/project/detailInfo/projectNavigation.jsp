<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <span class="site_title" onclick="openProjectInfoUrl();" style="cursor: pointer">
                项目阶段
            </span>
        </div>
        <div class="clearfix"></div>
        <br>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <c:forEach items="${projectPlanList}" var="projectPlan">
                        <li>
                            <a href="${pageContext.request.contextPath}/projectCenter/projectStageInfo/${projectInfo.id}/${projectPlan.workStageId}">
                                <c:choose>
                                    <c:when test="${projectPlan.projectStatus eq 'finish'}">
                                        <i class="fa fa-check" aria-hidden="true"></i>
                                    </c:when>
                                    <c:when test="${projectPlan.projectStatus eq 'planExecute' or projectPlan.projectStatus eq 'task'}">
                                        <i class="fa fa-ellipsis-h" aria-hidden="true"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-info" aria-hidden="true"></i>
                                    </c:otherwise>
                                </c:choose>
                                    ${projectPlan.planName}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="top_nav" id="pmcc_head">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <h3><a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    <strong onclick="openProjectInfoUrl();" style="cursor: pointer">${projectInfo.projectName}</strong>
                    <span class="label label-info">${projectInfo.projectStatus}</span>
                    <a target="_blank"
                       href="${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=${projectId}">老项目详情</a>
                </h3></div>
        </nav>
    </div>
</div>
<script type="text/javascript">
    function openProjectInfoUrl() {
        window.location.href = '${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=${projectInfo.id}';
    }
</script>

