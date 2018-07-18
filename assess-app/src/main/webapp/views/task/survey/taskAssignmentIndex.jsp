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
            <input type="hidden" id="declareId" name="declareId" value="${parentPlan.declareRecordId}">
            <input type="hidden" id="examineType" name="examineType" value="${examineType}">
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
                                   value="${item.key}"><label
                                     for="examineFormType_${item.key}">&nbsp;${item.value}</label>
                        </span>
                        </c:forEach>
                    </div>
                    <%@include file="/views/task/survey/common/examineTask.jsp" %>
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

