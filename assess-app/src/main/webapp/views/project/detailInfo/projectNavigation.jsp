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
    </div>
</div>
<div class="top_nav" id="pmcc_head">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <h3><a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    <strong onclick="openProjectInfoUrl();" style="cursor: pointer">${projectInfo.projectName}</strong>
                    <span class="label label-info">${projectInfo.projectStatus}</span>
                </h3></div>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="dropdown-toggle" href="${pageContext.request.contextPath}/home/main"><i class="fa fa-home tooltips " style="font-size: 26px" data-placement="bottom" data-original-title="首页"></i>${sysUserDto.userName}</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<script type="text/javascript">
    function openProjectInfoUrl() {
        window.location.href = '${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=${projectInfo.id}';
    }
</script>

