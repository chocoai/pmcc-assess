<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentPlan.projectPhaseName}-任务分派</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-12">
                        <c:forEach var="item" items="${examineFormTypeList}">
                             <span class="col-sm-2">
                            <input type="radio" id="examineFormType_${item.key}"
                                   onclick="examineTask.initExamineTask(this,'${surveyLocaleExplore.id}');" ${item.key eq surveyLocaleExplore.examineFormType?"checked=\"checked\"":""}
                                   name="examineFormType"
                                   value="${item.key}"><label for="examineFormType_${item.key}">&nbsp;${item.value}</label>
                        </span>
                        </c:forEach>
                    </div>
                    <%@include file="/views/task/survey/common/examineTask.jsp" %>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentPlan.projectPhaseName}-查勘信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <ul class="nav nav-tabs bar_tabs">
                        <li class="active">
                            <a href="#tab_content_block" data-toggle="tab">版块</a>
                        </li>
                        <li class="tab_estate">
                            <a href="#tab_content_estate" data-toggle="tab">楼盘</a>
                        </li>
                        <li class="tab_building">
                            <a href="#tab_content_building" data-toggle="tab">楼栋</a>
                        </li>
                        <li class="tab_unit">
                            <a href="#tab_content_unit" data-toggle="tab">单元</a>
                        </li>
                        <li class="tab_house">
                            <a href="#tab_content_house" data-toggle="tab">房屋</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_content_block">

                        </div>
                        <div class="tab-pane tab_estate" id="tab_content_estate" style="display: none">

                        </div>
                        <div class="tab-pane tab_building" id="tab_content_building" style="display: none">

                        </div>
                        <div class="tab-pane tab_unit" id="tab_content_unit" style="display: none">

                        </div>
                        <div class="tab-pane tab_house" id="tab_content_house" style="display: none">

                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="col-sm-4 col-sm-offset-5">
                    <button  class="btn btn-default" onclick="window.close();">
                        取 消
                    </button>
                    <button  class="btn btn-primary" onclick="">
                        保 存
                    </button>
                    <button  class="btn btn-success" onclick="">
                        提 交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                    </button>
                </div>
            </div>

            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</div>
</body>



<%@include file="/views/share/main_footer.jsp" %>


</html>

