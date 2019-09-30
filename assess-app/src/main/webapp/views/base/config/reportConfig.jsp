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
    <title>报告配置</title>
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
                            <h2>报告配置</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/templateSet/templateSetIndex" ><span class="value "><i class="fa fa-reorder"></i> 基础报告模板</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/baseReportField/index" ><span class="value "><i class="fa fa-reorder"></i> 其他报告模板</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/numberRule/index" ><span class="value "><i class="fa fa-reorder"></i> 文号规则</span></a>
                                    </li>
                                </ul>
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/evaluationHypothesis/view" ><span class="value "><i class="fa fa-reorder"></i> 评估假设</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/evaluationPrinciple/view" ><span class="value "><i class="fa fa-reorder"></i> 评估原则</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/evaluationBasis/view" ><span class="value "><i class="fa fa-reorder"></i> 评估依据</span></a>
                                    </li>
                                </ul>
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/bestUse/Index" ><span class="value "><i class="fa fa-reorder"></i> 最佳利用方式</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/reportAnalysis/view" ><span class="value "><i class="fa fa-reorder"></i> 变现能力分析</span></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/reportAnalysisRisk/view" ><span class="value "><i class="fa fa-reorder"></i> 风险分析</span></a>
                                    </li>
                                </ul>
                                <ul class="stats-overview">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/reportAnalysisBackground/view" ><span class="value "><i class="fa fa-reorder"></i> 市场背景描述与分析</span></a>
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
