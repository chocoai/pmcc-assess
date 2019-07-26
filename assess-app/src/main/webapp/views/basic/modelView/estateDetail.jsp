<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal" id="basicEstateFrm">
    <div class="x_content">
        <div class="x_title">
            <h3>
                楼盘基本信息
            </h3>
            <div class="clearfix"></div>
        </div>

        <input type="hidden" name="id" value="${basicEstate.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.provinceName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.cityName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.districtName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <label class="form-control">${basicEstate.name}</label>
                        <span class="input-group-btn">
                             <c:choose>
                                 <c:when test="${isApplyBatch eq 'show'}">
                                           <div onclick="estateCommon.mapMarker2(true,${tableId});" class="btn btn-info"><i
                                                   class="fa fa-map-marker"></i> 标注</div>
                                 </c:when>
                                 <c:otherwise>
                                            <div onclick="estateCommon.mapMarker(true);" class="btn btn-info"><i
                                                    class="fa fa-map-marker"></i> 标注</div>
                                 </c:otherwise>
                             </c:choose>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘方位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.positionName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块</label>
                <c:choose>
                    <c:when test="${not empty processInsId and empty basicEstate.blockId}">
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <div class="input-group mar-btm">
                                <input type="text" name="blockName" class="form-control" value="${basicEstate.blockName}">
                                <div class="input-group-addon">
                                    <input id="checkbox-addons" name="writeBackBlockFlag" class="magic-checkbox" type="checkbox">
                                    <label for="checkbox-addons">回写</label>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${basicEstate.blockName}</label>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.street}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.streetNumber}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.attachNumber}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占地面积</label>
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
                    <label class="form-control" id="greeningRate_d">${basicEstate.greeningRate}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数</label>
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
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.floorArea}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">均价</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.averagePrice}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格区间</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstate.priceRange}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开盘时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control dbdate">
                        <fmt:formatDate
                                value='${basicEstate.openTime}' pattern='yyyy-MM-dd'/>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘概况</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control">${basicEstate.description}</label>
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
        <div id="industry_water_supply_plan">
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
        </div>
    </div>

    <div class="x_content" id="industrySupplyInfo" style="display: none;">
        <div class="x_title">楼盘供应信息</div>
        <div class="form-horizontal">
            <div class="form-group">
                <div class="x-valid supplyGas">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供气信息</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${basicEstate.supplyGasName}</label>
                    </div>
                </div>
                <div class="x-valid supplyPower">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电信息</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${basicEstate.supplyPowerName}</label>
                    </div>
                </div>

                <div class="x-valid supplyWater">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水情况</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${basicEstate.supplyWaterName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid drainWater">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">排水情况</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${basicEstate.drainWaterName}</label>
                    </div>
                </div>
                <div class="x-valid supplyHeating">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供热信息</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${basicEstate.supplyHeatingName}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<div class="x_content">
    <div class="x_title">土地基本信息</div>
    <form class="form-horizontal" id="basicLandState">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.name}</label>
                </div>
            </div>
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地级别</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.landLevelName}</label>
                </div>
            </div>
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
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">西至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.westTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">北至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.northTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.shapeStateName}</label>
                </div>
            </div>
        </div>
    <%--    <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状备注</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control">${basicEstateLandState.shapeStateRemark}</label>
                </div>
            </div>
        </div>--%>
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
            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1" id="developmentDegreeContentContainer"></div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地实体结论</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control">${basicEstateLandState.conclusion}</label>
                </div>
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
                    <label class="form-control" id="greenSpaceRate_d">${basicEstateLandState.greenSpaceRate}</label>
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
        </div>
        <div class="x_title">土壤</div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">污染</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.contaminatedName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">酸碱度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicEstateLandState.phName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">肥力</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" id="fertility_d">${basicEstateLandState.fertilityName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">稳定性</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" id="holdOn_d">${basicEstateLandState.holdOnName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">承载力</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" id="bearingCapacity_d">${basicEstateLandState.bearingCapacityName}</label>
                </div>
            </div>
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
</div>


<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td  class="table-cell">
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
<%@include file="/views/basic/modelView/estate/sonEstateDetail.jsp" %>


