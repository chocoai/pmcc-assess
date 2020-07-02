<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>楼盘</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        楼盘基本信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_estate" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicEstate.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">省
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="provinceName">${basicEstate.provinceName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">市</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="cityName">${basicEstate.cityName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">县</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="districtName">${basicEstate.districtName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">楼盘名称</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <label class="form-control"
                                                               name="name">${basicEstate.name}</label>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-info btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="estateCommon.mapMarker(true);">
                                                                标注
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">楼盘方位</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="positionName">${basicEstate.positionName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">基础版块<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="blockName">${basicEstate.blockName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">基础版块描述</label>
                                                <div class="col-sm-11" name="locationDescribe">
                                                    <label class="form-control input-full">${basicEstate.blockDescription}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-sm-12">
                                                    <table class="table estateStreetInfos">
                                                        <thead>
                                                        <tr>
                                                            <th style="width: 10%">街道号</th>
                                                            <th style="width: 10%">大门名称</th>
                                                            <th style="width: 10%">附号</th>
                                                            <th style="width: 7%">大门图</th>
                                                            <th style="width: 7%"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">占地面积</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">
                                                        ${basicEstate.coverAnArea}${basicEstate.coverAnAreaUnit}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">容积率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="volumetricRate">${basicEstate.volumetricRate}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">绿化率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">
                                                        ${basicEstate.greeningRate}
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">总楼栋数</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="buildingNumber">${basicEstate.buildingNumber}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">开发商</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="developerName">${basicEstate.developerName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    开盘时间
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full dbdate" name="openTime">
                                                        <fmt:formatDate value='${basicEstate.openTime}'
                                                                        pattern='yyyy-MM-dd'/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">楼盘区位分析</label>
                                                <div class="col-sm-11" name="locationDescribe">
                                                    <label class="form-control input-full">${basicEstate.locationDescribe}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="nonIndustrialFile"></div>
                                    <c:if test="${formType eq 'industry'}">
                                        <div id="industrialFile"></div>
                                    </c:if>
                                    <script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
                                    <%@include file="/views/method/module/developmentCommon.jsp" %>


                                    <div class="card-header">
                                        <div class="card-category">基础设施情况</div>
                                    </div>
                                    <div class="x_content">
                                        <div class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">基础设施完备度</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${basicEstate.infrastructureCompletenessName}</label>
                                                        </div>
                                                        <label class="col-sm-1">
                                                            楼盘外设定
                                                        </label>
                                                        <div class="col-sm-7">
                                                            <label class="form-control input-full">${basicEstate.infrastructureName}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        土地实体情况
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_estateLandState" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicEstateLandState.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">土地名称</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="name">${basicEstateLandState.name}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-sm-12">
                                                    <table class="table basicEstateLandCategoryInfo">
                                                        <thead>
                                                        <tr>
                                                            <th style="width: 15%">土地用途类型</th>
                                                            <th style="width: 15%">土地用途类别</th>
                                                            <th style="width: 10%">土地取得时间</th>
                                                            <th style="width: 10%">土地使用年限</th>
                                                            <th style="width: 30%">土地级别</th>
                                                            <th style="width: 10%"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                        color="#6f5499" size="10">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">${basicEstateLandState.eastToName}</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="eastTo">${basicEstateLandState.eastTo}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">${basicEstateLandState.southToName}</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="southTo">${basicEstateLandState.southTo}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">${basicEstateLandState.westToName}</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="westTo">${basicEstateLandState.westTo}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">

                                                <label class="col-sm-1 control-label">${basicEstateLandState.northToName}</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="northTo">${basicEstateLandState.northTo}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">土地形状</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="shapeStateName">${basicEstateLandState.shapeStateName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">土地面积</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${basicEstateLandState.landArea}${basicEstateLandState.landAreaUnit}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">

                                                <label class="col-sm-1 control-label">地形</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="planenessName">${basicEstateLandState.planenessName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">地势</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="topographicTerrainName">${basicEstateLandState.topographicTerrainName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">土地开发程度</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="developmentDegreeName">${basicEstateLandState.developmentDegreeName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">土地利用现状</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="presentSituationLandUse">${basicEstateLandState.presentSituationLandUse}</label>
                                                </div>
                                                <c:if test="${basicEstateLandState.developmentDegreeName == '熟地'}">
                                                    <label class="col-sm-1">
                                                        楼盘内设定
                                                    </label>
                                                    <div class="col-sm-7">
                                                        <label class="form-control input-full"
                                                               name="developmentDegreeContentName">${basicEstateLandState.developmentDegreeContentName}</label>
                                                    </div>
                                                </c:if>
                                                <c:if test="${basicEstateLandState.developmentDegreeName != '熟地'}">
                                                    <label class="col-sm-1 control-label">土地开发程度备注</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="developmentDegreeRemark">${basicEstateLandState.developmentDegreeRemark}</label>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">宗地内现状</label>
                                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                                    <label class="form-control input-full">${basicEstateLandState.currentSituation}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-header">
                                        <div class="card-category">开发限制条件</div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">容积率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="plotRatio">${basicEstateLandState.plotRatio}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">建筑密度</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="buildingDensity">
                                                        ${basicEstateLandState.buildingDensity}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">绿地率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="greenSpaceRate">
                                                        ${basicEstateLandState.greenSpaceRate}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">兼容比例</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="compatibleRatio">${basicEstateLandState.compatibleRatio}</label>
                                                </div>

                                                <c:if test="${!empty basicEstateLandState.investmentIntensity}">

                                                    <label class="col-sm-1 control-label">投资强度（万元/亩）</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="investmentIntensity">${basicEstateLandState.investmentIntensity}</label>
                                                    </div>

                                                </c:if>
                                                <c:if test="${!empty basicEstateLandState.buildingHeightLimit}">

                                                    <label class="col-sm-1 control-label">建筑限高</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="buildingHeightLimit">${basicEstateLandState.buildingHeightLimit}</label>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-header">
                                        <div class="card-category">土壤</div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">污染</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="contaminatedName">${basicEstateLandState.contaminatedName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">酸碱度</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="phName">${basicEstateLandState.phName}</label>
                                                </div>
                                                <c:if test="${not empty basicEstateLandState.holdOnName}">

                                                    <label class="col-sm-1 control-label">稳定性</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="holdOnName">${basicEstateLandState.holdOnName}</label>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <c:if test="${not empty basicEstateLandState.bearingCapacityName}">
                                                    <label class="col-sm-1 control-label">承载力</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="bearingCapacityName">${basicEstateLandState.bearingCapacityName}</label>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <c:if test="${not empty basicEstateLandState.specialRestrictions}">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">投资强度（万元/亩）</label>
                                                    <div class="col-sm-11">
                                                        <label class="form-control input-full"
                                                               name="specialRestrictions">${basicEstateLandState.specialRestrictions}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>


                    <c:if test="${empty isHistory}">
                        <!-- 从表 -->
                        <%@include file="/views/project/stageSurvey/commonDetail/estateInvestigation.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/estateNetwork.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/estateParking.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingEnvironment.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingFinance.jsp" %>
                        <!-- 非工业交通仓储 -->
                        <c:if test="${formType eq 'residence'}">
                            <%@include file="/views/project/stageSurvey/commonDetail/matchingEducation.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/matchingRecreation.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/matchingRestaurant.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/matchingMarket.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/matchingMedical.jsp" %>
                        </c:if>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingTransit.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingTrafficHub.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingMetro.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingMainRoad.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/matchingMainConversion.jsp" %>
                        <!-- 工业交通仓储 -->
                        <c:if test="${formType eq 'industry'}">
                            <%@include file="/views/project/stageSurvey/commonDetail/estateSupplyWater.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/estateSupplyDrainWater.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/estateSupplyPower.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/estateSupplyHeating.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/estateSupplyGas.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/matchingMaterial.jsp" %>
                        </c:if>

                    </c:if>

                    <%@include file="/views/chks/assessmentSurvey.jsp" %>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


</body>

<%@include file="/views/project/stageSurvey/commonDetail/estateStreetInfo.jsp" %>
<%@include file="/views/project/stageSurvey/commonDetail/estateLandCategoryInfo.jsp" %>

<%@include file="/views/chks/assessmentCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js?v=${assessVersion}"></script>


<script type="text/javascript">
    $(function () {
        estateCommon.initDetailById('${basicEstate.id}', '', false);
    })
</script>

</html>
