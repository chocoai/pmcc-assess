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
            <form id="basicApplyFrm" class="form-horizontal">
                <input type="hidden" name="id" value="${basicApply.id}">
                <input type="hidden" name="caseUnitId" value="${basicApply.caseUnitId}">
                <input type="hidden" name="estatePartInMode" value="${basicApply.estatePartInMode}">
                <input type="hidden" name="buildingPartInMode" value="${basicApply.buildingPartInMode}">
                <input type="hidden" name="unitPartInMode" value="${basicApply.unitPartInMode}">
                <input type="hidden" name="housePartInMode" value="${basicApply.housePartInMode}">
            </form>
            <%@include file="/views/basic/basicDetailCommon.jsp" %>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">

</script>
<script type="application/javascript">

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }

        var data = formApproval.getFormData();
        data.blockName = estateCommon.estateForm.find('[name=blockName]').val();
        data.writeBackBlockFlag = estateCommon.estateForm.find('[name=writeBackBlockFlag]').prop('checked');
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/basicApprovalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert(result.errmsg);
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
