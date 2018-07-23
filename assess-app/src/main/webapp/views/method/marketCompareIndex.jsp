<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link href="/pmcc-assess/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <jsp:include page="./module/marketCompareIndex.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script src="/pmcc-assess/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script type="application/javascript">


</script>
</html>

