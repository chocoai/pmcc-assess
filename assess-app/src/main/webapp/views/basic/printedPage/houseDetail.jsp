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

            <div class="x_panel">
                <div class="x_content">
                    <div class="x_title">
                        <h3>房屋基本信息</h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="caseHouseFrm">
                        <input type="hidden" value="${caseHouse.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.houseNumber}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">所在楼层</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.floor}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.huxingName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">新户型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.newHuxingName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">朝向</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.orientationName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">面积</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.area}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证载用途</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.certUseName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">实际用途</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.practicalUseName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">权益限制</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseHouse.rightInterestsRestriction}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房屋出租占用情况描述</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${caseHouse.description}</label>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="x_content">
                    <!-- 房间 -->
                    <c:if test="${hasHouseRoomData}">
                        <%@include file="/views/basic/printedPage/caseHouseRoom.jsp" %>
                    </c:if>

                    <!-- 临街 -->
                    <c:if test="${hasHouseFaceStreetData}">
                        <%@include file="/views/basic/printedPage/caseHouseFaceStreet.jsp" %>
                    </c:if>

                    <!-- 电力通讯网络 -->
                    <c:if test="${hasHouseIntelligentData}">
                        <%@include file="/views/basic/printedPage/caseHouseIntelligent.jsp" %>
                    </c:if>

                    <!-- 供水 -->
                    <c:if test="${hasHouseWaterData}">
                        <%@include file="/views/basic/printedPage/caseHouseWater.jsp" %>
                    </c:if>

                    <!-- 排水 -->
                    <c:if test="${hasHouseWaterDrainData}">
                        <%@include file="/views/basic/printedPage/caseHouseWaterDrain.jsp" %>
                    </c:if>

                    <!-- 新风情况 -->
                    <c:if test="${hasHouseEquipmentNewWind}">
                        <%@include file="/views/basic/printedPage/caseHouseNewWind.jsp" %>
                    </c:if>

                    <!-- 空调情况 -->
                    <c:if test="${hasHouseEquipmentAirConditioner}">
                        <%@include file="/views/basic/printedPage/caseHouseAirConditioner.jsp" %>
                    </c:if>

                    <!-- 房屋供暖 -->
                    <c:if test="${hasHouseEquipmentHeating}">
                        <%@include file="/views/basic/printedPage/caseHouseHeating.jsp" %>
                    </c:if>

                    <!-- 房屋完损度 -->
                    <%--  <c:if test="${hasHouseDamagedDegreeData}">
                          <%@include file="/views/basic/printedPage/caseDamagedDegree.jsp" %>
                      </c:if>--%>
                    <!-- 结构完损度 -->
                      <c:if test="${hasStructuralPortion}">
                          <%@include file="/views/basic/printedPage/structuralPortion.jsp" %>
                      </c:if>
                    <!-- 装修完损度 -->
                      <c:if test="${hasFitmentPortion}">
                          <%@include file="/views/basic/printedPage/fitmentPortion.jsp" %>
                      </c:if>
                    <!-- 设备完损度 -->
                      <c:if test="${hasEquipmentPortion}">
                          <%@include file="/views/basic/printedPage/equipmentPortion.jsp" %>
                      </c:if>
                    <!-- 其他完损度 -->
                      <c:if test="${hasOtherPortion}">
                          <%@include file="/views/basic/printedPage/otherPortion.jsp" %>
                      </c:if>
                    <!-- 承重构件明细 -->
                      <c:if test="${hasStructuralElementDetail}">
                          <%@include file="/views/basic/printedPage/structuralElementDetail.jsp" %>
                      </c:if>
                    <!-- 非承重墙明细 -->
                      <c:if test="${hasNonbearingWallDetail}">
                          <%@include file="/views/basic/printedPage/nonbearingWallDetail.jsp" %>
                      </c:if>
                    <!-- 屋面明细 -->
                      <c:if test="${hasRoofDetail}">
                          <%@include file="/views/basic/printedPage/roofDetail.jsp" %>
                      </c:if>
                    <!-- 楼地面明细 -->
                      <c:if test="${hasFlooringDetail}">
                          <%@include file="/views/basic/printedPage/flooringDetail.jsp" %>
                      </c:if>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/case/case.common.js"></script>
<script type="text/javascript">
    var objectData = new Object();


</script>
