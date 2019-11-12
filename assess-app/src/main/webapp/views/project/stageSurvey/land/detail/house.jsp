<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        房屋
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="basicHouseFrm">
                        <input type="hidden" name="id" value="${basicHouse.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="houseNumber">${basicHouse.houseNumber}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在楼层</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="floor">${basicHouse.floor}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层描述</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="floorDesc">${basicHouse.floorDesc}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="huxingName">${basicHouse.huxingName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型图</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div id="_house_huxing_plan"></div>
                                </div>
                            </div>


                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">空间布局</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="spatialDistributionName">${basicHouse.spatialDistributionName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">空间布局描述</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="spatialDistributionDesc">${basicHouse.spatialDistributionDesc}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">朝向</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="orientationName">${basicHouse.orientationName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">面积</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="area">${basicHouse.area}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">面积描述</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="areaDesc">${basicHouse.areaDesc}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">调查方式</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="researchTypeName">${basicHouse.researchTypeName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">实际用途</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="practicalUseName">${basicHouse.practicalUseName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用情况</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="useConditionName">${basicHouse.useConditionName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用情况描述</label>
                                <div class=" col-xs-7  col-sm-7  col-md-7  col-lg-7 ">
                                    <label class="form-control" id="useConditionDescription"
                                           name="useConditionDescription">${basicHouse.useConditionDescription}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋平面图</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div id="_house_img_plan"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${empty isHistory}">
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
                            <%@include
                                    file="/views/project/stageSurvey/commonDetail/industry/houseCorollaryEquipment.jsp" %>
                        </c:if>

                        <%@include file="/views/project/stageSurvey/commonDetail/houseDamagedDegree.jsp" %>

                    </div>
                </c:if>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        houseCommon.initById('${basicHouse.id}');
    })
</script>
</html>


