<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list-group table-list-nav">
    <a onclick="systemSetUp.openUrl(this,'${pageContext.request.contextPath}/baseDataDic/index');" class="list-group-item">数据字典</a>
    <a onclick="systemSetUp.openUrl(this,'${pageContext.request.contextPath}/ProjectPhase/view');" class="list-group-item">阶段事项</a>
    <a onclick="systemSetUp.openUrl(this,'${pageContext.request.contextPath}/baseProjectClassify/index');" class="list-group-item">项目类型</a>
    <a onclick="systemSetUp.openUrl(this,'${pageContext.request.contextPath}/baseFileTemplate/index');" class="list-group-item">文件模板</a>
    <a onclick="systemSetUp.openUrl(this,'${pageContext.request.contextPath}/setUseField/index');" class="list-group-item">设定用途</a>
    <a onclick="systemSetUp.openUrl(this,'${pageContext.request.contextPath}/assessReport/index');" class="list-group-item">报表管理</a>
</div>
<script type="text/javascript">
    var systemSetUp = {};

    systemSetUp.openUrl = function (url) {
        window.location.href = url + location.search;
    }
</script>