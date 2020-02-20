<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="./projectNavigation.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="./projectStagePlan.jsp" %>
                    <%@include file="/views/share/form_details.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

<%--<div class="container body">
    <div class="main_container">
        <%@include file="./projectNavigation.jsp" %>
        <div class="right_col" role="main">
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="./projectStagePlan.jsp" %>
            <%@include file="/views/share/form_details.jsp" %>
        </div>
    </div>
</div>--%>
</body>

</html>
