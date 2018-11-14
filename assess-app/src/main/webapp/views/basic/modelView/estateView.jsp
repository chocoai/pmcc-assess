<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                楼盘基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicEstateFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2 province">
                        <option value="" name="province">-请选择-</option>
                        <c:forEach items="${ProvinceList}" var="item">
                            <c:choose>
                                <c:when test="${item.areaId == projectInfo.province}">
                                    <option value="${item.areaId}"
                                            selected="selected">${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.areaId}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
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
                <label class="col-sm-1 control-label">街道</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="街道"
                           name="street" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">基础版块<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="blockId" class="form-control search-select select2 blockId"
                            required="required">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required="required"
                           name="name" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘方位</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼盘方位"
                           name="position" class="form-control">
                </div>
            </div>

            <div class="x-valid supplyGas">
                <label class="col-sm-1 control-label">供气信息</label>
                <div class="col-sm-3">
                    <select name="supplyGas" class="form-control search-select select2"
                            required="required">
                        <option value="true">有</option>
                        <option value="false">无</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid supplyPower">
                <label class="col-sm-1 control-label">供电信息</label>
                <div class="col-sm-3">
                    <select name="supplyPower" class="form-control search-select select2"
                            required="required">
                        <option value="true">有</option>
                        <option value="false">无</option>
                    </select>
                </div>
            </div>

            <div class="x-valid supplyWater">
                <label class="col-sm-1 control-label">供排水情况</label>
                <div class="col-sm-3">
                    <select name="supplyWater" class="form-control search-select select2"
                            required="required">
                        <option value="true">有</option>
                        <option value="false">无</option>
                    </select>
                </div>
            </div>

            <div class="x-valid supplyHeating">
                <label class="col-sm-1 control-label">供热信息</label>
                <div class="col-sm-3">
                    <select name="supplyHeating" class="form-control search-select select2"
                            required="required">
                        <option value="true">有</option>
                        <option value="false">无</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="占地面积(请输入数字)" name="coverAnArea" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="容积率(请输入数字)" name="volumetricRate" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="绿化率(请输入数字)" name="greeningRate" class="form-control">
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘概况</label>
                <div class="col-sm-11">
                        <textarea class="form-control" name="description"
                                  placeholder="楼盘概况"></textarea>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼栋数</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="楼栋数(请输入数字)" name="buildingNumber"
                           class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="developerId">
                        <input type="text" readonly="readonly"
                               placeholder="开发商" class="form-control">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="objectData.estate.developerSelect(this)">
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
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="建筑面积(请输入数字)" name="floorArea" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="均价(请输入数字)" name="averagePrice" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="价格区间"
                           name="priceRange" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="附号(请输入数字)"
                           name="attachNumber" class="form-control">
                </div>
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

        <div class="form-group">
            <div class="x-valid" id="industry_water_supply_plan">
                <label class="col-sm-1 control-label">供水平面图</label>
                <div class="col-sm-5">
                    <input id="water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid" id="industry_power_supply_plan">
                <label class="col-sm-1 control-label">供电平面图</label>
                <div class="col-sm-5">
                    <input id="power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_power_supply_plan"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid" id="industry_air_supply_plan">
                <label class="col-sm-1 control-label">供气平面图</label>
                <div class="col-sm-5">
                    <input id="air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid" id="industry_heating_plan">
                <label class="col-sm-1 control-label">采暖平面图</label>
                <div class="col-sm-5">
                    <input id="heating_plan" placeholder="上传附件" class="form-control" type="file">
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
                <label class="col-sm-1 control-label">土地名称</label>
                <div class="col-sm-11">
                    <input type="text" class="form-control" name="name"
                           placeholder="名称">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUseType" name="landUseType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUseCategory"
                            name="landUseCategory">
                        <option>请先选择土地用途类型</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="landLevel">
                        <input type="text" readonly="readonly"
                               placeholder="土地级别" class="form-control">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="objectData.estate.landLevelSelect(this)">
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
                <label class="col-sm-1 control-label">东至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至"
                           name="eastTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至"
                           name="southTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至"
                           name="westTo">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至"
                           name="northTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状状况</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地形状状况"
                           name="shapeState">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地平整度</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地平整度"
                           name="planeness">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地开发程度"
                           name="developmentDegree">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发限制条件</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="开发限制条件"
                           name="restrictiveCondition">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土壤</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土壤"
                           name="soil">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形地势</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="地形地势" name="topographicTerrain">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" data-rule-number='true'
                           placeholder="土地面积(请输入数字)" name="landArea">
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/estate/sonEstateView.jsp" %>