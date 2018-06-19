<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>


            <c:if test="${projectPlanDetailsVo.declareFormName=='loanCustomInfoAssist'}">
                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>${projectPlanDetailsVo.projectPhaseName}</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" id="${projectPlanDetailsVo.declareFormName}_details_id" value="${projectPlanDetailsVo.id}">
                        <jsp:include page="/views/task/financialClaim/share/${projectPlanDetailsVo.declareFormName}Display.jsp"></jsp:include>
                    </div>
                </div>
            </c:if>
            <c:if test="${projectPlanDetailsVo.declareFormName!='loanCustomInfoAssist'}">
                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h2>客户信息</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content collapse">
                        <jsp:include page="/views/task/financialClaim/share/loanCustomInfoAssistDisplay.jsp"></jsp:include>
                    </div>
                </div>
                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>${projectPlanDetailsVo.projectPhaseName}</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" id="${projectPlanDetailsVo.declareFormName}_details_id" value="${projectPlanDetailsVo.id}">
                        <jsp:include page="/views/task/financialClaim/share/${projectPlanDetailsVo.declareFormName}Display.jsp"></jsp:include>
                    </div>
                </div>
            </c:if>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var opinions = loadOpation();
        $("#opinions").val(opinions);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTask/submitTaskApproval",
            type: "post",
            dataType: "json",
            data: formParams("frm_approval"),
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }

</script>

</html>

