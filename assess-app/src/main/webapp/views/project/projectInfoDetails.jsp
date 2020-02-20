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
            <div class="page-inner mt--5">
                <div class="card-head-row">
                    <div class="card-title">
                        项目信息
                    </div>
                </div>
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfo.jsp" %>
                    <%@include file="/views/share/form_details.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


<%--<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h3>
                        ${projectInfo.projectName}
                    </h3>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/form_details.jsp" %>
        </div>
    </div>
</div>--%>
</body>

</html>
