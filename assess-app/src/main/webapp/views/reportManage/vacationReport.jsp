<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed"><%--<%@include file="share/main_head.jsp" %>--%><!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12 ">
                    <!-- start: DEFAULT TREE PANEL -->
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>${baseViewDto.currentMenu.name}</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="x_content">
                                <iframe id="report_iframe" width="100%" height="100%"
                                        src="${pageContext.request.contextPath}/ureport/preview?_u=erp:${reportProviderFile.reportName}"
                                        frameborder="0" scrolling="auto"></iframe>
                            </div>
                        </div>
                    </div>
                    <!-- end: DEFAULT TREE PANEL -->
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script>
    $(function () {
        changeFrameHeight();
    });

    function changeFrameHeight() {
        var ifm = document.getElementById("report_iframe");
        ifm.height = document.documentElement.clientHeight - 56;
    }

    window.onresize = function () {
        changeFrameHeight();
    };
</script>
