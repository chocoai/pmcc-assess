<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/project/stageInit/other/otherProjectIndexJs.jsp" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h2>
            项目信息
            <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
        </h2>
        <input type="hidden" value="${projectInfo.id}">
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <!-- 项目基本信息 start -->
        <%@include file="/views/project/stageInit/other/projectInfo.jsp" %>
        <!-- 项目基本信息 end -->
    </div>
</div>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h2> 委托人</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <!-- 委托人 start -->
    <%@include file="/views/project/stageInit/other/projectConsignor.jsp" %>
    <!-- 委托人 end -->
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <h2> 占有人</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <!-- 占有人 start -->
    <%@include file="/views/project/stageInit/other/projectPossessor.jsp" %>
    <!-- 占有人 end -->
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h2> 报告使用单位</h2>
        <div class="clearfix"></div>
    </div>
    <!-- 报告使用单位 start -->
    <%@include file="/views/project/stageInit/other/projectUnit_information.jsp" %>
    <!-- 报告使用单位 end -->
</div>

