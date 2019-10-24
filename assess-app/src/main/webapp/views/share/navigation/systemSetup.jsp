<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-12  col-sm-12  col-md-2  col-lg-2 ">
    <div class="list-group table-list-nav">
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/baseDataDic/index');" class="list-group-item">数据字典<i class="fa fa-bookmark"></i></a>
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/ProjectPhase/view');" class="list-group-item">阶段事项<i class="fa fa-cloud-download"></i></a>
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/baseProjectClassify/index');" class="list-group-item">项目类型<i class="fa fa-spinner"></i></a>
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/baseFileTemplate/index');" class="list-group-item">文件模板<i class="fa fa-magic"></i></a>
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/dataExamineTask/index');" class="list-group-item">查勘内容<i class="fa fa-flag-checkered"></i></a>
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/setUseField/index');" class="list-group-item">设定用途<i class="fa fa-coffee"></i></a>
        <a onclick="systemSetUp.openUrl('${pageContext.request.contextPath}/assessReport/index');" class="list-group-item">报表管理<i class="fa fa-barcode"></i></a>
    </div>
</div>
<script type="text/javascript">
    var systemSetUp = {};

    systemSetUp.openUrl = function (url) {
        window.location.href = url + location.search;
    }
</script>