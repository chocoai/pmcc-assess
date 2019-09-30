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
                                <form id="query_form" class="form-horizontal">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                项目名称
                                            </label>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <input id="queryProjectName" name="queryProjectName" class="form-control"  placeholder="项目名称"/>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                标题
                                            </label>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <input id="queryTitle" name="queryTitle" class="form-control"  placeholder="标题"/>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                开始时间
                                            </label>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <input id="queryStartTime" name="queryStartTime" class="form-control date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                结束时间
                                            </label>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <input id="queryEndTime" name="queryEndTime" class="form-control date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"  placeholder="结束时间"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
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
                                        src="${pageContext.request.contextPath}/ureport/preview?_i=1&_r=1&_u=erp:${reportProviderFile.reportName}"
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

    function statisticsByCondition(){
        var data = formParams("query_form");
        document.getElementById('report_iframe').src="${pageContext.request.contextPath}/ureport/preview?_i=1&_r=1&queryProjectName="+ data.queryProjectName+"&queryTitle="+data.queryTitle+
            "&queryStartTime="+data.queryStartTime+"&queryEndTime="+data.queryEndTime+"&_u=erp:${reportProviderFile.reportName}";
    }
</script>
