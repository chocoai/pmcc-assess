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

            <form id="basicApplyFrm" class="form-horizontal">
                <input type="hidden" name="id" value="${basicApply.id}">
                <input type="hidden" name="caseUnitId" value="${basicApply.caseUnitId}">
                <input type="hidden" name="estatePartInMode" value="${basicApply.estatePartInMode}">
                <input type="hidden" name="buildingPartInMode" value="${basicApply.buildingPartInMode}">
                <input type="hidden" name="unitPartInMode" value="${basicApply.unitPartInMode}">
                <input type="hidden" name="housePartInMode" value="${basicApply.housePartInMode}">
            </form>
            <%@include file="/views/basic/basicDetailCommon.jsp" %>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
