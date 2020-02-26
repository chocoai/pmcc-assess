<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="subNavMenu" class="list-group table-list-nav">
    <a data-item="baseDataDic" onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/baseDataDic/index');" class="list-group-item">数据字典</a>
    <a data-item="ProjectPhase" onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/ProjectPhase/view');" class="list-group-item">阶段事项</a>
    <a data-item="baseProjectClassify" onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/baseProjectClassify/index');" class="list-group-item">项目类型</a>
    <a data-item="baseFileTemplate" onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/baseFileTemplate/index');" class="list-group-item">文件模板</a>
    <a data-item="setUseField" onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/setUseField/index');" class="list-group-item">设定用途</a>
    <a data-item="assessReport" onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/assessReport/index');" class="list-group-item">报表管理</a>
</div>
<script type="text/javascript">
    $(function () {
        $('#subNavMenu a').each(function () {
            if(location.href.indexOf($(this).attr("data-item"))>0){
                $(this).css("background-color",'#b6d3b6');
            }
        })
    })
    var systemSetUp = {};

    systemSetUp.openUrl = function (url) {
        window.location.href = url + location.search;
    }
</script>