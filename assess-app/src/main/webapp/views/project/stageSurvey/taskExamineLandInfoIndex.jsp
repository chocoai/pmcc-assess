<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.css">
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
                    <%@include file="/views/project/stageSurvey/examineLand/fragmentApplyEstate.jsp" %>

                    <%@include file="/views/project/stageSurvey/examineLand/fragmentApplyEstateLandState.jsp" %>

                    <%@include file="/views/project/stageSurvey/examineLand/fragmentApplyHouseTrading.jsp" %>

                    <%@include file="/views/project/stageSurvey/examineLand/fragmentApplyEntourage.jsp" %>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消<i style="margin-left: 10px" class="fa fa-close"></i>
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success" onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary" onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js"></script>
<!-- 表单js -->
<script src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>

<!-- 控件js -->
<script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/developer.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/builder.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/property.js"></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js'></script>


<!-- 高德抓取周边数据 -->
<script src="${pageContext.request.contextPath}/js/select/selectMap/transit.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/metro.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/finance.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/education.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/recreation.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/restaurant.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/market.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/medical.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/trafficHub.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/distance.get.fun.js"></script>
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js"></script>


<!-- 从表 -->
<script src='${pageContext.request.contextPath}/js/common.column.js'></script>
<script src="${pageContext.request.contextPath}/js/basic/estate/sonEstateView.js"></script>
<script src="${pageContext.request.contextPath}/js/basic/house/sonHouseView.js"></script>


<script type="text/javascript">
    $(document).ready(function () {

        //初始化方法和值
        estateCommon.detail(basicCommon.getApplyId(), function (data) {
            estateCommon.initForm({estate: data.basicEstate, land: data.basicEstateLandState});
        });

        houseCommon.detail(basicCommon.getApplyId(), function (data) {
            houseCommon.initForm(data);
        });
        //启动自动填充控件

        //楼盘自动填充插件
        estateCommon.autocompleteStart();


        //房屋自动填充插件
        houseCommon.autocompleteStart();

        //必要的准备
        estateCommon.estateForm.find("select[name='supplyCommunication']").parent().parent().show();
        estateCommon.estateForm.find("select[name='supplyRoad']").parent().parent().show();
        estateCommon.estateForm.find("div[onclick]").each(function (i,n) {
            var fName = $(n).attr("onclick");
            if (fName == 'estateCommon.mapMarker()'){
                $(n).hide();
            }
            if (fName == 'estateCommon.mapLandMarker(false)'){
                $(n).show();
            }
        });
    });
</script>
<script type="text/javascript">

    //任务提交
    function submit(mustUseBox) {
        var formData = basicCommon.getFormData();
        console.log(formData);

        //数据校验
        if (!basicCommon.valid()) {
            return false;
        }


        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        } else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }

</script>
</html>

