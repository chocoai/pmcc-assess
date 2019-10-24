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
                        土地带房产
                    </h2>
                </div>
            </div>
            <div class="x_panel">

                <form id="frm_estate" class="form-horizontal">
                    <input type="hidden" name="id" value="${basicEstate.id}">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="provinceName">${basicEstate.provinceName}</label>
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="cityName">${basicEstate.cityName}</label>
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="districtName">${basicEstate.districtName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块名称<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <div class="input-group">
                                    <label class="form-control" name="name">${basicEstate.name}</label>
                                    <span class="input-group-btn">
                                    <div onclick="estateCommon.mapMarker();" class="btn btn-info"><i
                                            class="fa fa-map-marker"></i> 标注</div>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块方位</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="positionName">${basicEstate.positionName}</label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="blockName">${basicEstate.blockName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块描述<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                <label class="form-control">${basicEstate.blockDescription}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道(乡)名称<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.street}</label>
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道(村)号<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.streetNumber}</label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附(组)号</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.attachNumber}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块面积</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.coverAnArea}</label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.volumetricRate}</label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿化率</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.greeningRate}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.buildingNumber}</label>
                            </div>
                        </div>

                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开发商</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${basicEstate.developerName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块区位分析<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                <label class="form-control">${basicEstate.locationDescribe}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总平面图</label>
                            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                <div id="_estate_floor_total_plan"></div>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                <div id="_estate_floor_Appearance_figure"></div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${basicApplyBatch.type == 1}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水平面图</label>
                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">

                                    <div id="_estate_water_supply_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电平面图</label>
                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">

                                    <div id="_estate_power_supply_plan"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供气平面图</label>
                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">

                                    <div id="_estate_air_supply_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">采暖平面图</label>
                                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">

                                    <div id="_estate_heating_plan"></div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="x_content">
                        <div class="x_title">基础设施情况</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础设施完备度<span
                                        class="symbol required"></span></label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">

                                    <label class="form-control">${basicEstate.infrastructureCompleteness}</label>
                                </div>
                            </div>
                            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  col-sm-offset-1"
                                 id="industrySupplyInfoContainer">

                            </div>
                        </div>
                    </div>
                </form>


                <div class="x_content">
                    <div class="x_title">
                        <h3>土地实体情况 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form id="frm_estateLandState" class="form-horizontal">
                        <input type="hidden" name="id" value="${basicEstateLandState.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类型</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.landUseTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类别</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.landUseCategoryName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">东至</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.eastTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">南至</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.southTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">西至</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.westTo}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地级别</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.landLevelName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.shapeStateName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">北至</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.northTo}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地面积</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.landArea}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地形</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.planenessName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地势</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.topographicTerrainName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.developmentDegreeName}</label>
                                </div>
                            </div>
                            <div class="x-valid" style="display: none">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度备注</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.developmentDegreeRemark}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地利用现状</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.presentSituationLandUse}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  col-sm-offset-1"
                                 id="developmentDegreeContentContainer">
                            </div>
                        </div>
                        <div class="x_title">开发限制条件</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.plotRatio}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑密度</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.buildingDensity}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿地率</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.greenSpaceRate}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">兼容比例</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.compatibleRatio}</label>
                                </div>
                            </div>
                            <div class="x-valid" style="display: none">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">投资强度（万元/亩）</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${basicEstateLandState.investmentIntensity}</label>
                                </div>
                            </div>
                            <div class="x-valid" style="display: none">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑限高 m²</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">

                                    <label class="form-control">${basicEstateLandState.buildingHeightLimit}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                <table class="table table-striped table-bordered" style="display: none">
                                    <thead>
                                    <tr>
                                        <th width="10%">土地级别类别</th>
                                        <th width="10%">土地级别类型</th>
                                        <th width="10%">土地级别等级</th>
                                        <th width="20%">说明</th>
                                        <th width="10%">分值</th>
                                        <th width="5%"></th>
                                    </tr>
                                    </thead>
                                    <tbody id="landLevelTabContent">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <script type="text/html" id="landLevelTabContentBody">
                            <tr class="group">
                                <td class="table-cell">
                                    {landLevelTypeName}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td>
                                    {landLevelCategoryName}
                                </td>
                                <td>
                                    <select class="form-control" name="landLevelGrade"
                                            onchange="estateCommon.landLevelHandle(this);">
                                        {landLevelGradeHTML}
                                    </select>
                                </td>
                                <td>
                                    <label name="reamark" class="form-control">{reamark}</label>
                                </td>
                                <td>
                                    <input type="hidden" class="form-control" name="dataLandLevelAchievement"
                                           value="{dataLandLevelAchievement}">
                                    <input type="text" class="form-control" name="landFactorTotalScore"
                                           value="{landFactorTotalScore}">
                                    <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
                                </td>
                                <td>
                                    <input class="btn btn-warning" type="button" value="X"
                                           onclick="estateCommon.landLevelEmpty(this)">
                                </td>
                            </tr>
                        </script>
                    </form>
                </div>

                <div class="x_content">
                    <%@include file="/views/project/stageSurvey/commonDetail/estateNetwork.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/estateParking.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingEnvironment.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/matchingFinance.jsp" %>
                    <c:if test="${basicApplyBatch.type == 0}">
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

                    <c:if test="${basicApplyBatch.type == 1}">
                        <%@include file="/views/project/stageSurvey/commonDetail/industry/matchingMaterial.jsp" %>
                    </c:if>
                </div>


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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js"></script>
</html>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}');
    })
</script>

