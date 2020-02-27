<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="subNavMenu" class="list-group table-list-nav">
    <a data-item="templateSet" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/templateSet/templateSetIndex');" class="list-group-item">基础报告模板</a>
    <a data-item="baseReportField" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/baseReportField/index');" class="list-group-item">其他报告模板</a>
    <a data-item="numberRule" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/numberRule/index');" class="list-group-item">文号规则</a>
    <a data-item="evaluationHypothesis" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/evaluationHypothesis/view');" class="list-group-item">评估假设</a>
    <a data-item="evaluationPrinciple" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/evaluationPrinciple/view');" class="list-group-item">评估原则</a>
    <a data-item="evaluationBasis" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/evaluationBasis/view');" class="list-group-item">评估依据</a>
    <a data-item="bestUse" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/bestUse/index');" class="list-group-item">最佳利用方式</a>
    <a data-item="reportAnalysisView" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/reportAnalysis/view');" class="list-group-item">变现能力分析</a>
    <a data-item="reportAnalysisRisk" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/reportAnalysisRisk/view');" class="list-group-item">风险分析</a>
    <a data-item="reportAnalysisBackground" onclick="reportSetUp.openUrl('${pageContext.request.contextPath}/reportAnalysisBackground/view');" class="list-group-item">市场背景描述与分析</a>
</div>
<script type="text/javascript">
    $(function () {
        $('#subNavMenu a').each(function () {
            if(location.href.indexOf($(this).attr("data-item"))>0){
                $(this).css("background-color",'#b6d3b6');
            }
        })
    })

    var reportSetUp = {};

    reportSetUp.openUrl = function (url) {
        window.location.href = url + location.search;
    }
</script>