<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_estate" class="form-horizontal">
    <input type="hidden" name="id" value="${basicEstate.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                <span class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <select name="province"
                        class="form-control search-select select2 province">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <select name="city" class="form-control search-select select2 city">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <select name="district" class="form-control search-select select2 district">
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <div class="input-group">
                    <input type="text" id="txt_estate_search" data-rule-maxlength="100" placeholder="楼盘名称"
                           required="required"
                           name="name" class="form-control" value="${basicEstate.name}">
                    <span class="input-group-btn">
                             <div onclick="estateCommon.mapMarker(false);" class="btn btn-info">
                                 <i class="fa fa-map-marker"></i> 标注
                             </div>
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
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <div class="input-group">
                    <input type="hidden" name="blockId" value="${basicEstate.blockId}">
                    <input type="text" onchange="$(this).closest('.input-group').find('[name=blockId]').val('0');"
                           placeholder="基础版块" class="form-control" name="blockName"
                           value="${basicEstate.blockName}">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="examineCommon.blockSelect(this)">
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
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块描述<span
                    class="symbol required"></span></label>
            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="blockDescription" id="blockDescription" required
                                  placeholder="基础版块描述">${basicEstate.blockDescription}</textarea>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" placeholder="街道名称" required
                       name="street" class="form-control" value="${basicEstate.street}">
            </div>
        </div>

        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号<span
                    class="symbol required"></span></label>
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
                       placeholder="容积率" name="volumetricRate" class="form-control"
                       value="${basicEstate.volumetricRate}">
            </div>
        </div>

        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿化率</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text"
                       placeholder="绿化率" name="greeningRate" class="form-control"
                       value="${basicEstate.greeningRate}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数<span
                    class="symbol required"></span></label>
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
        <div class="x-valid">
            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                开盘时间
            </label>
            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                <input type="text" placeholder="开盘时间" data-date-format='yyyy-mm-dd'
                       name="openTime" class="form-control dbdate">
            </div>
        </div>

    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘区位分析<span
                    class="symbol required"></span></label>
            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="locationDescribe" required
                                  placeholder="楼盘区位分析">${basicEstate.locationDescribe}</textarea>
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
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图<span
                    class="symbol required"></span></label>
            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control"
                       type="file">
                <div id="_estate_floor_Appearance_figure"></div>
            </div>
        </div>
    </div>
    <c:if test="${formType eq 'industry'}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供水平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_water_supply_plan" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供电平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_power_supply_plan" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_power_supply_plan"></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">供气平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_air_supply_plan" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">采暖平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_heating_plan" placeholder="上传附件" class="form-control"
                           type="file">
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
                    <select class="form-control search-select select2 "
                            name="infrastructureCompleteness" required="required">
                    </select>
                </div>
            </div>
            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  col-sm-offset-1" id="industrySupplyInfoContainer">

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
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">西至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="西至"
                           name="westTo" value="${basicEstateLandState.westTo}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地级别</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="landLevel" value="${basicEstateLandState.landLevel}">
                        <input type="text" readonly="readonly" onclick="examineCommon.landLevelSelect(this);"
                               placeholder="土地级别" class="form-control" name="landLevelName"
                               value="${basicEstateLandState.landLevelName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="examineCommon.landLevelSelect(this);">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control shapeState" name="shapeState">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">北至</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="北至"
                           name="northTo" value="${basicEstateLandState.northTo}">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control developmentDegree" name="developmentDegree">
                    </select>
                </div>
            </div>
            <div class="x-valid" style="display: none">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度备注</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" placeholder="土地开发程度备注"
                           name="developmentDegreeRemark" value="${basicEstateLandState.developmentDegreeRemark}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地利用现状</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control"
                           placeholder="未开发，已开发完成投入使用，部分开发" name="presentSituationLandUse"
                           value="${basicEstateLandState.presentSituationLandUse}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  col-sm-offset-1"
                 id="developmentDegreeContentContainer">
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
                    <input type="text" class="form-control " placeholder="容积率"
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
                    <input type="text" class="form-control" placeholder="绿地率"
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
            <div class="x-valid" style="display: none">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">投资强度（万元/亩）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" data-rule-number='true'
                           placeholder="投资强度（万元/亩） 数字" name="investmentIntensity"
                           value="${basicEstateLandState.investmentIntensity}">
                </div>
            </div>
            <div class="x-valid" style="display: none">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑限高 m²</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" data-rule-number='true'
                           placeholder="建筑限高 m² (数字)" name="buildingHeightLimit"
                           value="${basicEstateLandState.buildingHeightLimit}">
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
            <div class="x-valid" style="display: none">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">投资强度（万元/亩）</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control"
                              name="specialRestrictions">${basicEstateLandState.specialRestrictions}</textarea>
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
                    <select class="form-control" name="landLevelGrade" onchange="estateCommon.landLevelHandle(this);">
                        {landLevelGradeHTML}
                    </select>
                </td>
                <td>
                    <label name="reamark" class="form-control">{reamark}</label>
                </td>
                <td>
                    <input type="hidden" class="form-control" name="dataLandLevelAchievement"
                           value="{dataLandLevelAchievement}">
                    <input type="text" class="form-control" name="landFactorTotalScore" value="{landFactorTotalScore}">
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
    <%@include file="/views/project/stageSurvey/common/estateNetwork.jsp" %>
    <%@include file="/views/project/stageSurvey/common/estateParking.jsp" %>
    <%@include file="/views/project/stageSurvey/common/matchingEnvironment.jsp" %>
    <%@include file="/views/project/stageSurvey/common/matchingFinance.jsp" %>
    <c:if test="${formType eq 'residence'}">
        <%@include file="/views/project/stageSurvey/common/matchingEducation.jsp" %>
        <%@include file="/views/project/stageSurvey/common/matchingRecreation.jsp" %>
        <%@include file="/views/project/stageSurvey/common/matchingRestaurant.jsp" %>
        <%@include file="/views/project/stageSurvey/common/matchingMarket.jsp" %>
        <%@include file="/views/project/stageSurvey/common/matchingMedical.jsp" %>
    </c:if>
    <%@include file="/views/project/stageSurvey/common/matchingTransit.jsp" %>
    <%@include file="/views/project/stageSurvey/common/matchingTrafficHub.jsp" %>
    <%@include file="/views/project/stageSurvey/common/matchingMetro.jsp" %>
    <%@include file="/views/project/stageSurvey/common/matchingMainRoad.jsp" %>
    <%@include file="/views/project/stageSurvey/common/matchingMainConversion.jsp" %>
    <c:if test="${formType eq 'industry'}">
        <%@include file="/views/project/stageSurvey/common/matchingMaterial.jsp" %>
    </c:if>
</div>
<%@include file="/views/project/tool/toolMapHandleView.jsp" %>

<script src='${pageContext.request.contextPath}/js/common.column.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonEstateView.js"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>


<!-- 高德抓取周边数据 -->
<script src="${pageContext.request.contextPath}/js/select/selectMap/transit.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/metro.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/finance.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/education.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/recreation.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/restaurant.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/market.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/medical.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/trafficHub.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/distance.get.fun.js"></script>
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js"></script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}');
    })
</script>