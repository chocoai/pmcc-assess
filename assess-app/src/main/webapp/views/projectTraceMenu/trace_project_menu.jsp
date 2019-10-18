<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <span class="site_title" onclick="openProjectInfoUrl();" style="cursor: pointer">
                项目详情
            </span>
        </div>
        <div class="clearfix"></div>
        <br>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <c:forEach items="${workStageList}" var="workStage">
                        <li>
                            <a href="${pageContext.request.contextPath}/projectPlanDetails/openProjectMenuLink/${projectInfo.id}/${workStage.id}">
                                <i class="fa fa-info-circle" aria-hidden="true"></i>
                                    ${workStage.workStageName}
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
        </nav>
    </div>
</div>
<script type="text/javascript">
    function openProjectInfoUrl() {
        window.location.href = '${pageContext.request.contextPath}/projectPlanDetails/projectTraceProjectInfo?projectId=${projectInfo.id}';
    }
</script>

