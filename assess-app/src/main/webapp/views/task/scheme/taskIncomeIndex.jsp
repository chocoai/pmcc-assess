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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <jsp:include page="/views/task/scheme/module/supportInfoModule.jsp"></jsp:include>
            <jsp:include page="/views/method/module/incomeIndex.jsp"></jsp:include>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
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
<input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
<input type="hidden" id="mdIncomeJSON" value='${mdIncomeJSON}'>
<input type="hidden" id="incomeSelfSupportJSON" value='${incomeSelfSupportJSON}'>

<script type="text/javascript">
    $(function () {
        //支撑信息初始化
        supportInfoModule.init({
            supportInfo: JSON.parse($("#supportInfosJSON").val())
        });

        //收益法数据初始化
        income.init({
            incomeInfo: JSON.parse($("#mdIncomeJSON").val()),
            incomeSelfSupport: JSON.parse($("#incomeSelfSupportJSON").val())
        })
    })
</script>
<script type="application/javascript">
    function submit() {
        if (!supportInfoModule.valid()) {
            return false;
        }
        if (!income.valid()) {
            return false;
        }
        var data = {};
        data.supportInfoList = supportInfoModule.getData();
        data.incomeInfo = income.getData();
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

</script>

</html>

