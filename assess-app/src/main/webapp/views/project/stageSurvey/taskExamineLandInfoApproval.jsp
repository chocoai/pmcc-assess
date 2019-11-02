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

            <div class="x_panel">

                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${empty declareRecord?projectPlanDetails.projectPhaseName: declareRecord.name} 土地的查勘表</h3>
                    <div class="clearfix"></div>
                </div>

                <form id="basicApplyFrm">
                    <!--案例或者查勘 entity json-->
                    <input type="hidden" id="surveySceneExploreJson" value='${surveySceneExploreJson}'>
                    <input type="hidden" id="surveyCaseStudyJson" value='${surveyCaseStudyJson}'>

                    <input type="hidden" name="caseEstateId" value="${basicApply.caseEstateId}">
                    <input type="hidden" name="caseBuildingId" value="${basicApply.caseBuildingId}">
                    <input type="hidden" name="caseUnitId" value="${basicApply.caseUnitId}">
                    <input type="hidden" name="caseHouseId" value="${basicApply.caseHouseId}">
                    <input type="hidden" name="id" value="${basicApply.id}">
                </form>


                <div class="x_content">
                    <%@include file="/views/project/stageSurvey/examineLand/fragmentDateilEstate.jsp" %>
                    <%@include file="/views/project/stageSurvey/examineLand/fragmentDetailEstateLandState.jsp" %>
                    <%@include file="/views/project/stageSurvey/examineLand/fragmentDetailHouseTrading.jsp" %>
                    <%@include file="/views/project/stageSurvey/examineLand/fragmentDetailEntourage.jsp" %>
                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<!-- 从表 -->
<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<!-- 表单js -->
<script src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript">

    estateCommon.estateForm.find("div[onclick]").each(function (i,n) {
        var fName = $(n).attr("onclick");
        if (fName == 'estateCommon.mapMarker(true)'){
            $(n).parent().hide();
        }
        if (fName == 'estateCommon.mapLandMarker(true)'){
            $(n).parent().show();
        }
    });
    //审批提交
    function saveform() {
        saveApprovalform("");
    }
</script>
</body>
</html>

