<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="query_form" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <input id="queryProjectName" name="queryProjectName" class="form-control"  placeholder="项目名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <input id="queryTitle" name="queryTitle" class="form-control"  placeholder="标题"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <button type="button" class="btn btn-success" onclick="statisticsByCondition()">
                                        查询
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <iframe id="report_iframe" width="100%" height="100%"
                            src="${pageContext.request.contextPath}/ureport/preview?_u=erp:workLog.ureport.xml&_i=1&_r=1"
                            frameborder="0" scrolling="auto"></iframe>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">
    $(function () {
        changeFrameHeight();
    });


    function statisticsByCondition(){
        var data = formParams("query_form");
        document.getElementById('report_iframe').src="${pageContext.request.contextPath}/ureport/preview?_u=erp:workLog.ureport.xml&_i=1&_r=1&queryProjectName="+data.queryProjectName+"&queryTitle="+data.queryTitle;
    }


    function changeFrameHeight() {
        var ifm = document.getElementById("report_iframe");
        ifm.height = document.documentElement.clientHeight - 56;
    }

    window.onresize = function () {
        changeFrameHeight();
    };
</script>
</html>
