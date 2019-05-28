<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<div class="x_panel">
    <form class="form-horizontal" id="frm_estate">
        <div class="x_content">
            <div class="x_title">
                <h3>楼盘基本信息</h3>
                <div class="clearfix"></div>
            </div>

            <input type="hidden" name="id" value="${caseEstate.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.provinceName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.cityName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.districtName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div class="input-group">
                            <label class="form-control">${caseEstate.name}</label>
                            <span class="input-group-btn">
                            <div onclick="caseCommon.viewMapMarker('${caseEstate.id}','estate','${caseEstate.name}');"
                                 class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘方位</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.positionName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.blockName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.street}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.streetNumber}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.attachNumber}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占地面积</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.coverAnArea}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <c:if test="${!empty caseEstate.volumetricRate}">
                            <c:choose>
                                <c:when test="${caseEstate.volumetricRate.matches('[0-9.]+')}">
                                    <label class="form-control">${caseEstate.volumetricRate*100}%</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="form-control">${caseEstate.volumetricRate}</label>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿化率</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <c:if test="${!empty caseEstate.greeningRate}">
                            <c:choose>
                                <c:when test="${caseEstate.greeningRate.matches('[0-9.]+')}">
                                    <label class="form-control">${caseEstate.greeningRate*100}%</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="form-control">${caseEstate.greeningRate}</label>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.buildingNumber}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开发商</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.developerName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.floorArea}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">均价</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.averagePrice}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格区间</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstate.priceRange}</label>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘概况</label>
                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <label class="form-control">${caseEstate.description}</label>
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
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <div id="_estate_floor_Appearance_figure"></div>
                    </div>
                </div>
            </div>
            <c:if test="${caseEstate.type eq 1}">
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水平面图</label>
                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                            <div id="_water_supply_plan"></div>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电平面图</label>
                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                            <div id="_power_supply_plan"></div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供气平面图</label>
                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                            <div id="_air_supply_plan"></div>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">采暖平面图</label>
                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                            <div id="_heating_plan"></div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${caseEstate.type eq 0}">
            <div class="x_content">
                <div class="x_title">
                    楼盘供应信息
                </div>
                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="x-valid supplyGas">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供气信息</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${caseEstate.supplyGasName}</label>
                            </div>
                        </div>
                        <div class="x-valid supplyPower">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电信息</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${caseEstate.supplyPowerName}</label>
                            </div>
                        </div>

                        <div class="x-valid supplyWater">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水情况</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${caseEstate.supplyWaterName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid drainWater">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">排水情况</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${caseEstate.drainWaterName}</label>
                            </div>
                        </div>
                        <div class="x-valid supplyHeating">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供热信息</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control">${caseEstate.supplyHeatingName}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </form>

    <div class="x_content">
        <div class="x_title">
            <h3>楼盘土地实体情况 </h3>
            <div class="clearfix"></div>
        </div>
        <form id="frm_estateLandState" class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地名称</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.name}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类型</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.landUseTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类别</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.landUseCategoryName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地级别</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.landLevelName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">东至</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.eastTo}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">南至</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.southTo}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">西至</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.westTo}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">北至</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.northTo}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.shapeStateName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状备注</label>
                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <label class="form-control">${caseEstateLandState.shapeStateRemark}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地面积</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.landArea}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地形</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.planenessName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地势</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.topographicTerrainName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础设施完备度</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.infrastructureCompletenessName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.developmentDegreeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <c:if test="${caseEstateLandState.developmentDegreeName == '熟地'}">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label"></label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${caseEstateLandState.developmentDegreeContentName}</label>
                        </div>
                    </c:if>
                    <c:if test="${caseEstateLandState.developmentDegreeName != '熟地'}">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度备注</label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${caseEstateLandState.developmentDegreeRemark}</label>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="x_title">开发限制条件</div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <c:if test="${!empty caseEstateLandState.plotRatio}">
                            <c:choose>
                                <c:when test="${caseEstateLandState.plotRatio.matches('[0-9.]+')}">
                                    <label class="form-control">${caseEstateLandState.plotRatio*100}%</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="form-control">${caseEstateLandState.plotRatio}</label>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑密度</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.buildingDensity}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿地率</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <c:if test="${!empty caseEstateLandState.greenSpaceRate}">
                            <c:choose>
                                <c:when test="${caseEstateLandState.greenSpaceRate.matches('[0-9.]+')}">
                                    <label class="form-control">${caseEstateLandState.greenSpaceRate*100}%</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="form-control">${caseEstateLandState.greenSpaceRate}</label>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">兼容比例</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.compatibleRatio}</label>
                    </div>
                </div>
            </div>
            <div class="x_title">土壤</div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">污染</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.contaminatedName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">酸碱度</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${caseEstateLandState.phName}</label>
                    </div>
                </div>
                <c:if test="${not empty caseEstateLandState.fertilityName}">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">肥力</label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${caseEstateLandState.fertilityName}</label>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <c:if test="${not empty caseEstateLandState.bearingCapacityName}">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">承载力</label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${caseEstateLandState.bearingCapacityName}</label>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty caseEstateLandState.holdOnName}">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">稳定性</label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${caseEstateLandState.holdOnName}</label>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <table class="table table-striped table-bordered" style="display: none">
                        <thead>
                        <tr>
                            <th width="20%">土地级别类别</th>
                            <th width="20%">土地级别类型</th>
                            <th width="15%">等级</th>
                            <th width="10%">分值</th>
                        </tr>
                        </thead>
                        <tbody id="landLevelTabContent">

                        </tbody>
                    </table>
                </div>
            </div>
        </form>

        <!-- 网络通信 -->
        <div class="network" style="display:${hasEstateNetworkData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseNetwork.jsp" %>
        </div>

        <!-- 车位信息 -->
        <div class="parking" style="display:${hasEstateParkingData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseParking.jsp" %>
        </div>

        <!-- 教育 -->
        <div class="education" style="display: ${hasMatchingEducationData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingEducation.jsp" %>
        </div>

        <!-- 环境 -->
        <div class="environment" style="display: ${hasMatchingEnvironmentData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingEnvironment.jsp" %>
        </div>

        <!-- 金融 -->
        <div class="finance" style="display: ${hasMatchingFinanceData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingFinance.jsp" %>
        </div>

        <!-- 购物商场 -->
        <div class="matchingMarket" style="display: ${hasMatchingLeisurePlaceMarket?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingMarket.jsp" %>
        </div>

        <!-- 医养条件 -->
        <div class="medical" style="display: ${hasMatchingMedicalData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingMedical.jsp" %>
        </div>

        <!-- 休闲娱乐 -->
        <div class="matchingRecreation" style="display: ${hasMatchingLeisurePlaceRecreation?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingRecreation.jsp" %>
        </div>

        <!-- 餐饮 -->
        <div class="matchingRestaurant" style="display: ${hasMatchingLeisurePlaceRestaurant?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingRestaurant.jsp" %>
        </div>

        <!-- 地铁 -->
        <div class="metro" style="display: ${hasMatchingTrafficMetro?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingMetro.jsp" %>
        </div>

        <!-- 公交 -->
        <div class="transit" style="display: ${hasMatchingTrafficTransit?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingTransit.jsp" %>
        </div>


        <!-- 主干道 -->
        <div class="mainroad" style="display: ${hasMatchingTrafficMainRoad?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingMainRoad.jsp" %>
        </div>

        <!-- 主要转换 -->
        <div class="mainconversion" style="display: ${hasMatchingTrafficMainConversion?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingMainConversion.jsp" %>
        </div>

        <!-- 交通枢纽 -->
        <div class="trafficHub" style="display: ${hasMatchingTrafficTrafficHub?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingTrafficHub.jsp" %>
        </div>

        <!-- 供水 -->
        <div class="supplywater" style="display: ${hasEstateSupplyWater?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseSupplyWater.jsp" %>
        </div>

        <!-- 排水 -->
        <div class="drainwater" style="display: ${hasEstateDrainWater?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseDrainWater.jsp" %>
        </div>

        <!-- 供电 -->
        <div class="supplypower" style="display: ${hasEstateSupplyPower?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseSupplyPower.jsp" %>
        </div>

        <!-- 供热 -->
        <div class="supplyheating" style="display: ${hasEstateSupplyHeating?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseSupplyHeating.jsp" %>
        </div>

        <!-- 供气 -->
        <div class="supplygas" style="display:${hasEstateSupplyGas?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseSupplyGas.jsp" %>
        </div>

        <!-- 原材料及销售条件 -->
        <div class="material" style="display: ${hasMatchingMaterialData?'block':'none'};">
            <%@include file="/views/case/caseEstate/caseMatchingMaterial.jsp" %>
        </div>
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
            <label name="gradeName" class="form-control">{gradeName}</label>
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control">{landFactorTotalScore}</label>
        </td>

    </tr>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/case/case.common.js"></script>
<script type="text/javascript">
    var CaseEstateFun = function () {

    };

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    CaseEstateFun.prototype.isNotBlank = function (item) {
        return caseCommon.isNotBlank(item);
    };

    CaseEstateFun.prototype.isNotBlankObject = function (obj) {
        return caseCommon.isNotBlankObject(obj);
    };

    CaseEstateFun.prototype.config = {
        estate: {
            frm: function () {
                return "frm_estate";//楼盘基本信息frm
            }
        },
        landState: {
            frm: function () {
                return "frm_estateLandState";//土地实体情况
            }
        },
        other: {
            box: function () {
                return "otherDivBox";//其它信息box
            },
            frm: function () {
                return "frmOther";//其它信息frm
            }
        }
    };

    CaseEstateFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    CaseEstateFun.prototype.writeSelectData = function (frm, data, name) {
        if (CaseEstateFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };
    CaseEstateFun.prototype.showFile = function (fieldsName, table) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: '${empty caseEstate.id?0:caseEstate.id}',
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    };

    var caseEstate = new CaseEstateFun();


    //模块 楼盘基本信息
    caseEstate.estateModel = {
        init: function () {
            var landLevelContent = undefined;

            try {
                landLevelContent = '${caseEstateLandState.landLevelContent}';
            } catch (e) {
            }
            if (caseCommon.isNotBlank(landLevelContent)) {
                var obj = {};
                try {
                    obj = JSON.parse(landLevelContent);
                } catch (e) {
                }
                if (caseCommon.isNotBlankObject(obj)) {
                    try {
                        var objData = caseCommon.landLevelFilter(obj);
                        caseCommon.landLevelLoadHtml(objData);
                    } catch (e) {
                    }
                }
            }
            //总平面图
            caseEstate.showFile(AssessUploadKey.ESTATE_FLOOR_TOTAL_PLAN, AssessDBKey.CaseEstate);
            //外观图
            caseEstate.showFile(AssessUploadKey.ESTATE_FLOOR_APPEARANCE_FIGURE, AssessDBKey.CaseEstate);
            //供水平面图
            caseEstate.showFile(AssessUploadKey.ESTATE_WATER_SUPPLY_PLAN, AssessDBKey.CaseEstate);
            //供电平面图
            caseEstate.showFile(AssessUploadKey.ESTATE_POWER_SUPPLY_PLAN, AssessDBKey.CaseEstate);
            //供气平面图
            caseEstate.showFile(AssessUploadKey.ESTATE_AIR_SUPPLY_PLAN, AssessDBKey.CaseEstate);
            //采暖平面图
            caseEstate.showFile(AssessUploadKey.ESTATE_HEATING_PLAN, AssessDBKey.CaseEstate);
        }
    };


    $(function () {
        caseEstate.estateModel.init();

        if ('${caseEstateLandState.developmentDegreeContent}') {
            var array = '${caseEstateLandState.developmentDegreeContent}'.split(',');
            AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
                if (resultData) {
                    var resultHtml = '';
                    $.each(resultData, function (i, item) {
                        resultHtml += '<span class="checkbox-inline"><input disabled="disabled" type="checkbox" ';
                        if ($.inArray(item.id.toString(), array) > -1) {
                            resultHtml += ' checked="checked" ';
                        }
                        resultHtml += ' id="developmentDegreeContent' + item.id + '" name="developmentDegreeContent" value="' + item.id + '">';
                        resultHtml += '<label for="developmentDegreeContent' + item.id + '">' + item.name + '</label></span>';
                    })
                    $("#developmentDegreeContentContainer").html(resultHtml);
                }
            })
        }
    });
</script>
