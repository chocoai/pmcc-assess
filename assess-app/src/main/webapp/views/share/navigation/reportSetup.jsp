<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-12  col-sm-12  col-md-2  col-lg-2 ">
    <div class="list-group table-list-nav">
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/templateSet/templateSetIndex');" class="list-group-item">基础报告模板<i class="fa fa-bookmark"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/baseReportField/index');" class="list-group-item">其他报告模板<i class="fa fa-cloud-download"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/numberRule/index');" class="list-group-item">文号规则<i class="fa fa-spinner"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/evaluationHypothesis/view');" class="list-group-item">评估假设<i class="fa fa-magic"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/evaluationPrinciple/view');" class="list-group-item">评估原则<i class="fa fa-flag-checkered"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/evaluationBasis/view');" class="list-group-item">评估依据<i class="fa fa-coffee"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/bestUse/index');" class="list-group-item">最佳利用方式<i class="fa fa-barcode"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/reportAnalysis/view');" class="list-group-item">变现能力分析<i class="fa fa-barcode"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/reportAnalysisRisk/view');" class="list-group-item">风险分析<i class="fa fa-barcode"></i></a>
        <a onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/reportAnalysisBackground/view');" class="list-group-item">市场背景描述与分析<i class="fa fa-barcode"></i></a>
    </div>
</div>
<script type="text/javascript">
    var reportSetUp = {};

    reportSetUp.openUrl = function (url) {
        window.location.href = url + location.search;
    }
</script>