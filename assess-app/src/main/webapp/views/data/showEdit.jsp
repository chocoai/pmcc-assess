<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<form id="frm_Edit" class="form-horizontal">
    <div class="modal-body">
        <div class="row">
            <div class="col-md-12">
                <div class="panel-body">
                    <c:forEach items="${stageWeightProportions}" var="item">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-3 control-label">
                                    阶段<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-9">
                                    <label class="form-control">${item.stage}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div>
                                <label class="col-sm-3 control-label">
                                    占比<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-9">
                                    <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                           id="proportion" name="proportion" class="form-control"
                                           value="${item.proportion}">
                                </div>
                                <div class="col-sm-1">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">
                取消
            </button>
            <button type="button" class="btn btn-primary" onclick="saveData()">
                保存
            </button>
        </div>
    </div>
</form>
</body>
</html>
