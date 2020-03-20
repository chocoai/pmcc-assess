<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>

<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <jsp:include page="/views/method/module/marketCompareIndex.jsp"></jsp:include>
                    <div class="col-md-12">
                        <div class="x_panel card">
                            <div class="x_content card-body">
                                <div style="text-align: center;">
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
                    </div>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
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
            projectId: ${projectPlanDetails.projectId},
            mcId: ${mcId},
            isLand: '${isLand}',
            judgeObjectId: '${judgeObject.id}',
            cases: JSON.parse($("#casesJSON").val())
        });
    })
</script>
<script type="application/javascript">
    //提交
    function submit() {
        if (!marketCompare.valid()) {
            return false;
        }
        var data = {};
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
            notifySuccess("成功",'保存成功');
        });
    }

</script>

</html>

