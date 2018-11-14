<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>楼盘基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="frm_estate">
        <input type="hidden" name="id" value="${basicEstate.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.provinceName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.cityName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.districtName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.street}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.streetNumber}</label>
                </div>
            </div>


            <div class="x-valid">
                <label class="col-sm-1 control-label">基础版块</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.blockName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘名称</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.name}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘方位</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.position}</label>
                </div>
            </div>
            <div class="x-valid supplyGas">
                <label class="col-sm-1 control-label">供气信息</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.supplyGas?'有':'无'}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid supplyPower">
                <label class="col-sm-1 control-label">供电信息</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.supplyPower?'有':'无'}</label>
                </div>
            </div>

            <div class="x-valid supplyWater">
                <label class="col-sm-1 control-label">供排水情况</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.supplyWater?'有':'无'}</label>
                </div>
            </div>

            <div class="x-valid supplyHeating">
                <label class="col-sm-1 control-label">供热信息</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.supplyHeating?'有':'无'}</label>
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.coverAnArea}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.volumetricRate}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.greeningRate}</label>
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘概况</label>
                <div class="col-sm-11">
                    <label class="form-control">${basicEstate.description}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼栋数</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.buildingNumber}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.developerName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.floorArea}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.averagePrice}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.priceRange}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.attachNumber}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总平面图</label>
                <div class="col-sm-5">
                    <div id="_estate_floor_total_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图</label>
                <div class="col-sm-5">
                    <div id="_estate_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid" id="industry_water_supply_plan">
                <label class="col-sm-1 control-label">供水平面图</label>
                <div class="col-sm-5">
                    <div id="_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid" id="industry_power_supply_plan">
                <label class="col-sm-1 control-label">供电平面图</label>
                <div class="col-sm-5">
                    <div id="_power_supply_plan"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid" id="industry_air_supply_plan">
                <label class="col-sm-1 control-label">供气平面图</label>
                <div class="col-sm-5">
                    <div id="_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid" id="industry_heating_plan">
                <label class="col-sm-1 control-label">采暖平面图</label>
                <div class="col-sm-5">
                    <div id="_heating_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>


<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                土地交易基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicLandState">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                    <label class="form-control">${basicEstateLandState.name}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landUseTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landUseCategoryName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landLevelName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.eastTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.southTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.westTo}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.northTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状状况<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.shapeState}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地平整度<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.planeness}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.developmentDegree}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发限制条件<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.restrictiveCondition}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土壤<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.soil}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形地势<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.topographicTerrain}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landArea}</label>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/views/basic/modelView/estate/sonEstateDetail.jsp" %>

