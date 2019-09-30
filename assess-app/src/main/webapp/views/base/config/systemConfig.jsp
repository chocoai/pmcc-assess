<%--
  Created by IntelliJ IDEA.
  User: wangpc
  Date: 2019-9-30
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统配置</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>系统配置</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/baseDataDic/index" ><span class="value "><i class="fa fa-reorder"></i> 数据字典</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ProjectPhase/view" ><span class="value "><i class="fa fa-reorder"></i> 阶段事项</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/baseProjectClassify/index" ><span class="value "><i class="fa fa-reorder"></i> 项目类型</span></a>
                                    </li>
                                </ul>
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/baseFileTemplate/index" ><span class="value "><i class="fa fa-reorder"></i> 文件模板</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/dataExamineTask/index" ><span class="value "><i class="fa fa-reorder"></i> 查勘内容</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/setUseField/index" ><span class="value "><i class="fa fa-reorder"></i> 设定用途</span></a>
                                    </li>
                                </ul>
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/assessReport/index" ><span class="value "><i class="fa fa-reorder"></i> 报表管理</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
