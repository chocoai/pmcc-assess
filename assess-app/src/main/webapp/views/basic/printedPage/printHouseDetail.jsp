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

            <div class="x_panel" style="border: 0px;">
                <div class="x_content">
                    <div class="col-sm-12" style="text-align: center;"><h2>${fullName}</h2></div>
                  <%--  <form class="form-horizontal" id="basicHouseFrm">
                        <input type="hidden" value="${basicHouse.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.houseNumber}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">所在楼层</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.floor}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.huxingName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">新户型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.newHuxingName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">朝向</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.orientationName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">面积</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.areaarea}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证载用途</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.certUseName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">实际用途</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.practicalUseName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">权益限制</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${basicHouse.rightInterestsRestriction}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房屋出租占用情况描述</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${basicHouse.description}</label>
                                </div>
                            </div>
                        </div>

                    </form>--%>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th colspan="10" style="font-size: 14px;">房屋基本信息</th>
                        </tr>
                        <tr>
                            <td width="10%">所在楼层</td>
                            <td width="10%">户型</td>
                            <td width="10%">新户型</td>
                            <td width="10%">朝向</td>
                            <td width="10%">面积</td>
                            <td width="10%">证载用途</td>
                            <td width="10%">实际用途</td>
                            <td width="10%">权益限制</td>

                        </tr>
                        </thead>
                        <tr>
                            <td width="10%">${basicHouse.floor}</td>
                            <td width="10%">${basicHouse.huxingName}</td>
                            <td width="10%">${basicHouse.newHuxingName}</td>
                            <td width="10%">${basicHouse.orientationName}</td>
                            <td width="10%">${basicHouse.area}</td>
                            <td width="10%">${basicHouse.certUseName}</td>
                            <td width="10%">${basicHouse.practicalUseName}</td>
                            <td width="10%">${basicHouse.rightInterestsRestriction}</td>
                        </tr>
                        <tr>
                            <td width="10%">房屋出租占用情况描述:</td>
                            <td colspan="7">${basicHouse.description}</td>
                        </tr>
                        <tr>

                        </tr>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="x_content">
                    <!-- 房间 -->
                    <c:if test="${hasHouseRoomData}">
                        <%@include file="/views/basic/printedPage/printHouseRoom.jsp" %>
                    </c:if>

                    <!-- 临街 -->
                    <c:if test="${hasHouseFaceStreetData}">
                        <%@include file="/views/basic/printedPage/printHouseFaceStreet.jsp" %>
                    </c:if>

                    <!-- 电力通讯网络 -->
                    <c:if test="${hasHouseIntelligentData}">
                        <%@include file="/views/basic/printedPage/printHouseIntelligent.jsp" %>
                    </c:if>

                    <!-- 供水 -->
                    <c:if test="${hasHouseWaterData}">
                        <%@include file="/views/basic/printedPage/printHouseWater.jsp" %>
                    </c:if>

                    <!-- 排水 -->
                    <c:if test="${hasHouseWaterDrainData}">
                        <%@include file="/views/basic/printedPage/printHouseWaterDrain.jsp" %>
                    </c:if>

                    <!-- 新风情况 -->
                    <c:if test="${hasHouseEquipmentNewWind}">
                        <%@include file="/views/basic/printedPage/printHouseNewWind.jsp" %>
                    </c:if>

                    <!-- 空调情况 -->
                    <c:if test="${hasHouseEquipmentAirConditioner}">
                        <%@include file="/views/basic/printedPage/printHouseAirConditioner.jsp" %>
                    </c:if>
                    <!-- 房屋供暖 -->
                    <c:if test="${hasHouseEquipmentHeating}">
                        <%@include file="/views/basic/printedPage/printHouseHeating.jsp" %>
                    </c:if>
                    <!-- 房屋完损度 -->
                    <%@include file="/views/basic/printedPage/printDamagedDegree.jsp" %>
                    <%--<!-- 结构完损度 -->--%>
                    <%--<c:if test="${hasStructuralPortion}">--%>
                    <%--<%@include file="/views/basic/printedPage/printPortionStructural.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 装修完损度 -->--%>
                    <%--<c:if test="${hasFitmentPortion}">--%>
                    <%--<%@include file="/views/basic/printedPage/printPortionFitment.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 设备完损度 -->--%>
                    <%--<c:if test="${hasEquipmentPortion}">--%>
                    <%--<%@include file="/views/basic/printedPage/printPortionEquipment.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 其他完损度 -->--%>
                    <%--<c:if test="${hasOtherPortion}">--%>
                    <%--<%@include file="/views/basic/printedPage/printPortionOther.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 承重构件明细 -->--%>
                    <%--<c:if test="${hasStructuralElementDetail}">--%>
                    <%--<%@include file="/views/basic/printedPage/z-structuralElementDetail.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 非承重墙明细 -->--%>
                    <%--<c:if test="${hasNonbearingWallDetail}">--%>
                    <%--<%@include file="/views/basic/printedPage/z-nonbearingWallDetail.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 屋面明细 -->--%>
                    <%--<c:if test="${hasRoofDetail}">--%>
                    <%--<%@include file="/views/basic/printedPage/z-roofDetail.jsp" %>--%>
                    <%--</c:if>--%>
                    <%--<!-- 楼地面明细 -->--%>
                    <%--<c:if test="${hasFlooringDetail}">--%>
                    <%--<%@include file="/views/basic/printedPage/z-flooringDetail.jsp" %>--%>
                    <%--</c:if>--%>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/case/case.common.js?v=${assessVersion}"></script>
<script type="text/javascript">
    var objectData = new Object();


</script>
