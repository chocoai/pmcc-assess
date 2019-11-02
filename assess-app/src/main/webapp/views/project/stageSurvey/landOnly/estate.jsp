<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal" id="frm_estate">
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
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块名称<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <div class="input-group">
                    <input type="text" id="txt_estate_search" data-rule-maxlength="100" placeholder="地块名称"
                           required="required"
                           name="name" class="form-control" value="${basicEstate.name}">
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
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道(乡)名称<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" placeholder="街道(乡)名称" required
                       name="street" class="form-control" value="${basicEstate.street}">
            </div>
        </div>

        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道(村)号<span
                    class="symbol required"></span></label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" required
                       placeholder="街道(村)号" name="streetNumber" class="form-control"
                       value="${basicEstate.streetNumber}">
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附(组)号</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" data-rule-maxlength="100" data-rule-number='true'
                       placeholder="附(组)号"
                       name="attachNumber" class="form-control" value="${basicEstate.attachNumber}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占地面积</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" data-rule-maxlength="100" data-rule-number='true'
                       placeholder="地块面积(请输入数字)" name="coverAnArea" class="form-control"
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
                取得时间
            </label>
            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                <input type="text" placeholder="取得时间" data-date-format='yyyy-mm-dd'
                       name="openTime" class="form-control dbdate">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块区位分析<span
                    class="symbol required"></span></label>
            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="locationDescribe" required
                                  placeholder="地块区位分析">${basicEstate.locationDescribe}</textarea>
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
</form>
<div class="x_title">
    <div class="clearfix"></div>
</div>
<form class="form-horizontal" id="frm_estateLandState">
    <input type="hidden" name="id" value="${basicEstateLandState.id}">
    <div class="form-group">
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
            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                开发时间
            </label>
            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                <input type="text" placeholder="开发时间" data-date-format='yyyy-mm-dd'
                       name="developmentTime" class="form-control dbdate">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  col-sm-offset-1" id="developmentDegreeContentContainer">
        </div>
    </div>
    <div class="x_title"></div>
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
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑限高 m²</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" class="form-control" data-rule-number='true'
                       placeholder="建筑限高 m² (数字)" name="buildingHeightLimit"
                       value="${basicEstateLandState.buildingHeightLimit}">
            </div>
        </div>
    </div>
</form>
<form id="basicHouseFrm" class="form-horizontal">
    <input type="hidden" name="id" value="${basicHouse.id}">
    <input type="hidden" name="houseNumber" value="${basicHouse.houseNumber}">
</form>
<div class="x_content">
    <div class="x_title">
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicTradingFrm">
        <input type="hidden" name="id" value="${basicHouseTrading.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">财产范围<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 scopeProperty" name="scopeProperty" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">范围包括</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="范围包含" name="scopeInclude" class="form-control"
                           value="${basicHouseTrading.scopeInclude}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">范围不包括</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="范围不包含" name="scopeNotInclude" class="form-control"
                           value="${basicHouseTrading.scopeNotInclude}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">税费负担<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 taxBurden" name="taxBurden" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">交易情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control transactionSituation" name="transactionSituation" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control " name="priceType" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div id="abnormalTransaction" style="display: none;">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">说明事项类型</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select class="form-control descriptionType" name="descriptionType">
                        </select>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">说明事项内容</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" placeholder="说明事项内容" name="descriptionContent" class="form-control"
                               value="${basicHouseTrading.descriptionContent}">
                    </div>
                </div>
            </div>
        </div>
        <div class="x_title">
            <div class="clearfix"></div>
        </div>
        <%@include file="/views/project/stageSurvey/common/houseTradingBody.jsp" %>
    </form>
</div>
<%@include file="/views/project/stageSurvey/common/houseTradingBodyBox.jsp" %>
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
    <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js?v=${assessVersion}"></script>
