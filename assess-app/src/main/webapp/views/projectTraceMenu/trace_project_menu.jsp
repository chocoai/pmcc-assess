<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">

            <span class="site_title">
                <i style="border: none" class="fa fa-trophy"></i> ${projectInfo.projectName}
                <small><span class="label label-info">${projectInfo.projectStatus}</span></small>
            </span>
            <div class="profile clearfix">

                <a onclick="" style="margin-left: 20px;" data-placement='top' data-original-title='关注'
                   class='btn btn-xs btn-info tooltips'>
                    <i class='fa fa-bell-o fa-white'></i></a>

                <a onclick="" style="margin-left: 20px;" data-placement='top' data-original-title='取消关注'
                   class='btn btn-xs btn-info tooltips'>
                    <i class='fa fa-bell-slash-o fa-white'></i></a>

                <a target="_blank" data-placement='top' data-original-title='终止'
                   class='btn btn-xs btn-danger tooltips'><i class='fa fa-stop fa-white'></i></a>

                <a onclick="" data-placement='top' data-original-title='拷贝项目' class='btn btn-xs btn-warning tooltips'><i
                        class='fa fa-copy fa-white'></i></a>

            </div>
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
            <div class="nav toggle"><a id="menu_toggle"><i class="fa fa-bars"></i></a></div>
            <div class=" nav toggle">
                <h3>
                    <a target="_blank" class="tooltips" href="${pageContext.request.contextPath}/projectPlanDetails/projectTraceProjectInfo?projectId=${projectInfo.id}" data-placement='bottom'
                    data-original-title='单击查看项目详情'>
                    项目详情
                    </a>
                </h3>
            </div>
        </nav>
    </div>
</div>

