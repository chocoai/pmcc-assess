<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="../trace_project_menu.jsp" %>
        <div class="right_col" role="main">

            <%@include file="/views/share/project/projectInfoSimple.jsp" %>

            <%@include file="./baseView.jsp" %>

            <%@include file="../stagePlan.jsp" %>


            <div class="x_panel">
                <div class="x_content">

                </div>
                <div class="x_content">

                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>

            <%@include file="/views/share/form_log.jsp" %>

            <%@include file="/views/share/form_details.jsp" %>

        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>


</html>

<script type="text/javascript">



    $(document).ready(function () {

    });


</script>