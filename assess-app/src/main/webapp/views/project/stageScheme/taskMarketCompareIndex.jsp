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
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <jsp:include page="/views/project/stageScheme/module/ftContentChangeModule.jsp"></jsp:include>
            <jsp:include page="/views/project/stageScheme/module/supportInfoModule.jsp"></jsp:include>
            <jsp:include page="/views/method/module/marketCompareIndex.jsp"></jsp:include>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_save" class="btn btn-warning" onclick="save();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<input type="hidden" id="marketCompareJSON" value='${marketCompareJSON}'>
<input type="hidden" id="fieldsJSON" value='${fieldsJSON}'>
<input type="hidden" id="evaluationJSON" value='${evaluationJSON}'>
<input type="hidden" id="casesJSON" value='${casesJSON}'>
<input type="hidden" id="casesAllJSON" value='${casesAllJSON}'>

<input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
<script type="text/javascript">
    $(function () {
        //市场比较法信息初始化
        marketCompare.init({
            marketCompare: JSON.parse($("#marketCompareJSON").val()),
            fields: JSON.parse($("#fieldsJSON").val()),
            evaluation: JSON.parse($("#evaluationJSON").val()),
            casesAll: JSON.parse($("#casesAllJSON").val()),
            mcId: '${mcId}',
            setUse: '${setUse}',
            cases: JSON.parse($("#casesJSON").val())
        });

        //支撑信息初始化
        supportInfoModule.init({
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });
    })
</script>
<script type="application/javascript">
    //提交
    function submit() {
        if (!supportInfoModule.valid()) {
            return false;
        }
        if (!marketCompare.valid()) {
            return false;
        }
        var data = {};
        data.supportInfoList = supportInfoModule.getData();
        data.marketCompare = marketCompare.getData();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

    //保存
    function save() {
        marketCompare.save(function () {
            Alert('保存成功');
        });
    }

</script>

</html>

