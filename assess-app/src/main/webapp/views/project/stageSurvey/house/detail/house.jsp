<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>房屋</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        详情
                    </h2>
                </div>
            </div>
            <div class="x_panel">

                <c:if test="${basicApplyBatch.type == 0 }">
                    <%@include file="/views/project/stageSurvey/commonDetail/house.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/houseTrading.jsp" %>
                </c:if>


                <!-- 工业交通仓储 -->
                <c:if test="${basicApplyBatch.type == 1}">
                    <%@include file="/views/project/stageSurvey/commonDetail/industry/house.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/houseTrading.jsp" %>
                </c:if>






            </div>
            <div class="x_panel">
                <div class="x_content">
                    <%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>

                    <c:if test="${basicApplyBatch.type == 0 }">
                        <%@include file="/views/project/stageSurvey/commonDetail/houseWater.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseWaterDrain.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseNewWind.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseAirConditioner.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseHeating.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseIntelligent.jsp" %>
                    </c:if>

                    <%@include file="/views/project/stageSurvey/commonDetail/houseRoom.jsp" %>

                    <c:if test="${basicApplyBatch.type == 1}">
                        <%@include file="/views/project/stageSurvey/commonDetail/industry/houseCorollaryEquipment.jsp" %>
                    </c:if>

                    <%@include file="/views/project/stageSurvey/commonDetail/houseDamagedDegree.jsp" %>

                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/project/tool/toolMapHandleView.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js"></script>

<script>
    $(function () {
        houseCommon.initById('${basicHouse.id}');
    })
</script>
</html>
