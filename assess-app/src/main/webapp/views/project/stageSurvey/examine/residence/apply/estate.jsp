<%--
  楼盘基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_estate" class="form-horizontal">
    <input type="hidden" name="id" value="${basicEstate.id}">

    <div class="x_content">
        <div class="x_title">
            <h3>
                楼盘基本信息
            </h3>
            <div class="clearfix"></div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2 province">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="city" class="form-control search-select select2 city">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县</label>
                <div class="col-sm-3">
                    <select name="district" class="form-control search-select select2 district">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" id="txt_estate_search" data-rule-maxlength="100" placeholder="楼盘名称" required="required"
                               name="name" class="form-control" value="${basicEstate.name}">
                        <span class="input-group-btn">
                            <div onclick="estateCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘方位</label>
                <div class="col-sm-3">
                    <select name="position" class="form-control search-select position select2">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">基础版块<span class="symbol required"></span></label>
                <div class="col-sm-3">
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
                <label class="col-sm-1 control-label">街道名称<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="街道名称" required
                           name="street" class="form-control" value="${basicEstate.street}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="街道号" name="streetNumber" class="form-control"
                           value="${basicEstate.streetNumber}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="附号(请输入数字)"
                           name="attachNumber" class="form-control" value="${basicEstate.attachNumber}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="占地面积(请输入数字)" name="coverAnArea" class="form-control"
                           value="${basicEstate.coverAnArea}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100"
                           placeholder="容积率" name="volumetricRate" class="form-control"
                           value="${basicEstate.volumetricRate}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100"
                           placeholder="绿化率" name="greeningRate" class="form-control"
                           value="${basicEstate.greeningRate}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总楼栋数<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="总楼栋数(请输入数字)" name="buildingNumber" required
                           class="form-control" value="${basicEstate.buildingNumber}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="开发商" class="form-control" name="developerName"
                           value="${basicEstate.developerName}">
                    <input type="hidden" placeholder="开发商" class="form-control" name="developer"
                           value="${basicEstate.developer}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="建筑面积(请输入数字)" name="floorArea" class="form-control"
                           value="${basicEstate.floorArea}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="均价(请输入数字)" name="averagePrice" class="form-control"
                           value="${basicEstate.averagePrice}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="价格区间"
                           name="priceRange" class="form-control" value="${basicEstate.priceRange}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">楼盘概况</label>
            <div class="col-sm-11">
                        <textarea class="form-control" name="description"
                                  placeholder="楼盘概况">${basicEstate.description}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总平面图</label>
                <div class="col-sm-5">
                    <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_floor_total_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图</label>
                <div class="col-sm-5">
                    <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>

       <%-- <div id="industry_water_supply_plan">
            <div class="form-group">
                <div class="x-valid" id="">
                    <label class="col-sm-1 control-label">供水平面图</label>
                    <div class="col-sm-5">
                        <input id="water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_water_supply_plan"></div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">供电平面图</label>
                    <div class="col-sm-5">
                        <input id="power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_power_supply_plan"></div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">供气平面图</label>
                    <div class="col-sm-5">
                        <input id="air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_air_supply_plan"></div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">采暖平面图</label>
                    <div class="col-sm-5">
                        <input id="heating_plan" placeholder="上传附件" class="form-control" type="file">
                        <div id="_heating_plan"></div>
                    </div>
                </div>
            </div>
        </div>--%>
        <div class="x_content" id="industrySupplyInfo">
        <div class="x_title">楼盘供应信息</div>
        <div class="form-horizontal">
            <div class="form-group">
                <div class="x-valid supplyGas">
                    <label class="col-sm-1 control-label">供气信息</label>
                    <div class="col-sm-3">
                        <select class="form-control search-select select2 supplyGas" name="supplyGas">
                        </select>
                    </div>
                </div>
                <div class="x-valid supplyPower">
                    <label class="col-sm-1 control-label">供电信息</label>
                    <div class="col-sm-3">
                        <select class="form-control search-select select2 supplyPower" name="supplyPower">
                        </select>
                    </div>
                </div>

                <div class="x-valid supplyWater">
                    <label class="col-sm-1 control-label">供水情况</label>
                    <div class="col-sm-3">
                        <select class="form-control search-select select2 supplyWater" name="supplyWater">
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid drainWater">
                    <label class="col-sm-1 control-label">排水情况</label>
                    <div class="col-sm-3">
                        <select class="form-control search-select select2 drainWater" name="drainWater">
                        </select>
                    </div>
                </div>
                <div class="x-valid supplyHeating">
                    <label class="col-sm-1 control-label">供热信息</label>
                    <div class="col-sm-3">
                        <select class="form-control search-select select2 supplyHeating" name="supplyHeating">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>


    </div>
</form>


