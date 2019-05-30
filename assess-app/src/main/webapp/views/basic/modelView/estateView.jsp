<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province" id="province"
                            class="form-control search-select select2">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city" id="city" class="form-control search-select select2">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district" id="district" class="form-control search-select select2">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required="required"
                               name="name" class="form-control" value="${basicEstate.name}">
                        <span class="input-group-btn">
                            <div onclick="estateCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘方位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="position" class="form-control search-select position select2">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="blockId" value="${basicEstate.blockId}">
                        <input type="text"
                               placeholder="基础版块" class="form-control" name="blockName"
                               value="${basicEstate.blockName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="basicCommon.blockSelect(this)">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="街道名称" required
                           name="street" class="form-control" value="${basicEstate.street}">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="街道号" name="streetNumber" class="form-control"
                           value="${basicEstate.streetNumber}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="附号(请输入数字)"
                           name="attachNumber" class="form-control" value="${basicEstate.attachNumber}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占地面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="占地面积(请输入数字)" name="coverAnArea" class="form-control"
                           value="${basicEstate.coverAnArea}">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="容积率" name="volumetricRate" data-rule-number="true" class="form-control"
                           value="${basicEstate.volumetricRate}">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿化率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="绿化率" name="greeningRate" class="form-control x-percent"
                           value="${basicEstate.greeningRate}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="总楼栋数(请输入数字)" name="buildingNumber" required
                           class="form-control" value="${basicEstate.buildingNumber}">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开发商</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="开发商" class="form-control" name="developerName"
                           value="${basicEstate.developerName}">
                    <input type="hidden" placeholder="开发商" class="form-control" name="developer"
                           value="${basicEstate.developer}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="建筑面积(请输入数字)" name="floorArea" class="form-control"
                           value="${basicEstate.floorArea}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">均价</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="均价(请输入数字)" name="averagePrice" class="form-control"
                           value="${basicEstate.averagePrice}">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格区间</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="价格区间"
                           name="priceRange" class="form-control" value="${basicEstate.priceRange}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘概况</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="description"
                                  placeholder="楼盘概况">${basicEstate.description}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_floor_total_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
        <div id="industry_water_supply_plan">
            <div class="form-group">
                <div class="x-valid" id="">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水平面图</label>
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <input id="estate_water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_estate_water_supply_plan"></div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电平面图</label>
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <input id="estate_power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_estate_power_supply_plan"></div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供气平面图</label>
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <input id="estate_air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_estate_air_supply_plan"></div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">采暖平面图</label>
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <input id="estate_heating_plan" placeholder="上传附件" class="form-control" type="file">
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
                        <select class="form-control search-select select2 supplyGas" name="supplyGas">
                        </select>
                    </div>
                </div>
                <div class="x-valid supplyPower">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电信息</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select class="form-control search-select select2 supplyPower" name="supplyPower">
                        </select>
                    </div>
                </div>

                <div class="x-valid supplyWater">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水情况</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select class="form-control search-select select2 supplyWater" name="supplyWater">
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid drainWater">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">排水情况</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select class="form-control search-select select2 drainWater" name="drainWater">
                        </select>
                    </div>
                </div>
                <div class="x-valid supplyHeating">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供热信息</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select class="form-control search-select select2 supplyHeating" name="supplyHeating">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="x_content">
    <div class="x_title">
        <h3>
            土地基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicLandState">
        <input type="hidden" name="id" value="${basicEstateLandState.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" name="name"
                           placeholder="名称" value="${basicEstateLandState.name}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control landUseType" name="landUseType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类别</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control landUseCategory"
                            name="landUseCategory">
                        <option>请先选择土地用途类型</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地级别</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="landLevel" value="${basicEstateLandState.landLevel}">
                        <input type="text" readonly="readonly" onclick="basicCommon.landLevelSelect(this);"
                               placeholder="土地级别" class="form-control" name="landLevelName"
                               value="${basicEstateLandState.landLevelName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="basicCommon.landLevelSelect(this);">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">东至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="东至"
                           name="eastTo" value="${basicEstateLandState.eastTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">南至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="南至"
                           name="southTo" value="${basicEstateLandState.southTo}">
                </div>
            </div>
        </div>

        <div class="form-group">

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">西至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="西至"
                           name="westTo" value="${basicEstateLandState.westTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">北至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="北至"
                           name="northTo" value="${basicEstateLandState.northTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control shapeState" name="shapeState">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状备注</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control" name="shapeStateRemark"></textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" data-rule-number='true'
                           placeholder="土地面积(请输入数字)" name="landArea" value="${basicEstateLandState.landArea}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地形</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 planeness" name="planeness">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地势</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 topographicTerrain" name="topographicTerrain">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础设施完备度<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 infrastructureCompleteness" name="infrastructureCompleteness" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control developmentDegree" name="developmentDegree">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid" style="display: none">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度备注</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="土地开发程度备注"
                           name="developmentDegreeRemark" value="${basicEstateLandState.developmentDegreeRemark}">
                </div>
            </div>
            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1" id="developmentDegreeContentContainer">
            </div>
        </div>
        <div class="form-group">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地实体结论</label>
            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="conclusion"
                                  placeholder="土地实体结论">${basicEstateLandState.conclusion}</textarea>
            </div>
        </div>
        <div class="x_title">开发限制条件</div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" data-rule-number="true" placeholder="容积率"
                           name="plotRatio" value="${basicEstateLandState.plotRatio}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑密度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="建筑密度"
                           name="buildingDensity" value="${basicEstateLandState.buildingDensity}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿地率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control x-percent" placeholder="绿地率"
                           name="greenSpaceRate" value="${basicEstateLandState.greenSpaceRate}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">兼容比例</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="兼容比例"
                           name="compatibleRatio" value="${basicEstateLandState.compatibleRatio}">
                </div>
            </div>
        </div>
        <div class="x_title">土壤</div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">污染</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2" name="contaminated">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">酸碱度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2" name="ph">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">肥力</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2" name="fertility">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">稳定性</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2" name="holdOn">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">承载力</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2" name="bearingCapacity">
                    </select>
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


    </form>
</div>

<%@include file="/views/basic/modelView/estate/sonEstateView.jsp" %>

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
            <select class="form-control" name="landLevelGrade" onchange="estateCommon.landLevelHandle(this);">
                {landLevelGradeHTML}
            </select>
        </td>
        <td>
            <label name="reamark" class="form-control">{reamark}</label>
        </td>
        <td>
            <input type="hidden" class="form-control" name="dataLandLevelAchievement" value="{dataLandLevelAchievement}">
            <input type="text" class="form-control" name="landFactorTotalScore" value="{landFactorTotalScore}">
            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="estateCommon.landLevelEmpty(this)">
        </td>
    </tr>
</script>
