
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x-valid">
    <label class="col-sm-1 control-label">
        <a class="btn-app btn">${reportType.name}<i class="fa fa-file-word-o"></i></a>
    </label>
    <div class="col-sm-3">
        <div id="_${reportType.fieldName}${generationVo.areaGroupId}"></div>
    </div>
</div>
