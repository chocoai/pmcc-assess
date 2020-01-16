<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link href="/pmcc-assess/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <jsp:include page="./module/marketCompareIndex.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<input type="hidden" id="marketCompareJSON" value='${marketCompareJSON}'>
<input type="hidden" id="fieldsJSON" value='${fieldsJSON}'>
<input type="hidden" id="evaluationJSON" value='${evaluationJSON}'>
<input type="hidden" id="casesJSON" value='${casesJSON}'>
<input type="hidden" id="standardJudgesJSON" value='${standardJudgesJSON}'>
<script type="text/javascript">
    $(function () {
        //市场比较法信息初始化
        marketCompare.init({
            marketCompare: JSON.parse($("#marketCompareJSON").val()),
            fields: JSON.parse($("#fieldsJSON").val()),
            evaluation: JSON.parse($("#evaluationJSON").val()),
            standardJudges: JSON.parse($("#standardJudgesJSON").val()),
            projectId: ${judgeObject.projectId},
            mcId: '${mcId}',
            isLand: '${isLand}',
            judgeObjectId: '${judgeObject.id}',
            readonly: getQueryString("readonly"),
            cases: JSON.parse($("#casesJSON").val())
        });
    })

    //保存结果
    function saveResult(callback) {
        if (!marketCompare.valid()) {
            return false;
        }
        var data = marketCompare.getData();
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCompare/saveResult',
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    if (callback) {
                        callback(result.data.id, result.data.price)
                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }
</script>
</html>

