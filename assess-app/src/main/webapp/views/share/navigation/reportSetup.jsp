<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list-group table-list-nav">
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/templateSet/templateSetIndex');" class="list-group-item">基础报告模板</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/baseReportField/index');" class="list-group-item">其他报告模板</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/numberRule/index');" class="list-group-item">文号规则</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/evaluationHypothesis/view');" class="list-group-item">评估假设</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/evaluationPrinciple/view');" class="list-group-item">评估原则</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/evaluationBasis/view');" class="list-group-item">评估依据</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/bestUse/index');" class="list-group-item">最佳利用方式</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/reportAnalysis/view');" class="list-group-item">变现能力分析</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/reportAnalysisRisk/view');" class="list-group-item">风险分析</a>
    <a onclick="reportSetUp.openUrl(this,'${pageContext.request.contextPath}/reportAnalysisBackground/view');" class="list-group-item">市场背景描述与分析</a>
</div>
<script type="text/javascript">

    var reportSetUp = {};

    reportSetUp.openUrl = function (_this,url) {
        window.location.href = url + location.search;
    }
</script>