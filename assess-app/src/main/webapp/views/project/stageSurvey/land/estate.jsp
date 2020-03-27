<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal" id="frm_estate">
    <input type="hidden" name="id" value="${basicEstate.id}">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control input-full search-select select2 province">
                    </select>
                </div>
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="city" class="form-control input-full search-select select2 city">
                    </select>
                </div>
                <label class="col-sm-1 control-label">县</label>
                <div class="col-sm-3">
                    <select name="district" class="form-control input-full search-select select2 district">
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">地块名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
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
                <label class="col-sm-1 control-label">地块方位</label>
                <div class="col-sm-3">
                    <select name="position" class="form-control input-full search-select position select2">
                    </select>
                </div>
                <label class="col-sm-1 control-label">基础版块<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="blockId" value="${basicEstate.blockId}">
                        <input type="text" onchange="$(this).closest('.input-group').find('[name=blockId]').val('0');"
                               placeholder="基础版块" class="form-control" name="blockName"
                               value="${basicEstate.blockName}">
                        <div class="input-group-prepend">
                            <button class="btn btn-warning btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                清空
                            </button>
                        </div>
                        <div class="input-group-prepend">
                            <button class="btn btn-primary btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="examineCommon.blockSelect(this);">选择
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">基础版块描述<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control input-full" name="blockDescription" id="blockDescription" required
                                  placeholder="基础版块描述">${basicEstate.blockDescription}</textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">街道(乡)名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="街道(乡)名称" required
                           name="street" class="form-control input-full" value="${basicEstate.street}">
                </div>
                <label class="col-sm-1 control-label">街道(村)号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="街道(村)号" name="streetNumber" class="form-control input-full"
                           value="${basicEstate.streetNumber}">
                </div>
                <label class="col-sm-1 control-label">附(组)号</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="附(组)号"
                           name="attachNumber" class="form-control input-full" value="${basicEstate.attachNumber}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="地块面积(请输入数字)" name="coverAnArea" class="form-control input-full"
                           value="${basicEstate.coverAnArea}">
                </div>
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="容积率" name="volumetricRate" class="form-control input-full"
                           value="${basicEstate.volumetricRate}">
                </div>
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="绿化率" name="greeningRate" class="form-control input-full"
                           value="${basicEstate.greeningRate}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">总楼栋数<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="总楼栋数(请输入数字)" name="buildingNumber" required
                           class="form-control input-full" value="${basicEstate.buildingNumber}">
                </div>
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="开发商" class="form-control input-full" name="developerName"
                           value="${basicEstate.developerName}">
                    <input type="hidden" placeholder="开发商" class="form-control input-full" name="developer"
                           value="${basicEstate.developer}">
                </div>
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="建筑面积(请输入数字)" name="floorArea" class="form-control input-full"
                           value="${basicEstate.floorArea}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="均价(请输入数字)" name="averagePrice" class="form-control input-full"
                           value="${basicEstate.averagePrice}">
                </div>
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="价格区间"
                           name="priceRange" class="form-control input-full" value="${basicEstate.priceRange}">
                </div>
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    取得时间
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <input type="text" placeholder="取得时间" data-date-format='yyyy-mm-dd'
                           name="openTime" class="form-control input-full dbdate">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">地块区位分析<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control input-full" name="locationDescribe" required
                                  placeholder="地块区位分析">${basicEstate.locationDescribe}</textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">总平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control input-full"
                           type="file">
                    <div id="_estate_floor_total_plan"></div>
                </div>
                <label class="col-sm-1 control-label">外观图<span
                        class="symbol required"></span></label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control input-full"
                           type="file">
                    <div id="_estate_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="x_title">
    <div class="clearfix"></div>
</div>
<form class="form-horizontal" id="frm_estateLandState">
    <input type="hidden" name="id" value="${basicEstateLandState.id}">
    <input type="hidden" name="landFactorTotalScoreResult">
    <input type="hidden" name="landLevelContentResult">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="landUseType" class="form-control" list="landUseTypeList"
                               value="${basicEstateLandState.landUseType}">
                        <datalist id="landUseTypeList">

                        </datalist>
                        <div class="input-group-prepend">
                            <button class="btn btn-warning btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                清空
                            </button>
                        </div>
                    </div>
                </div>
                <label class="col-sm-1 control-label">
                    土地用途类别<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="landUseCategory" list="landUseCategoryList"
                               value="${basicEstateLandState.landUseCategory}"
                               class="form-control">
                        <datalist id="landUseCategoryList">

                        </datalist>
                        <div class="input-group-prepend">
                            <button class="btn btn-warning btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                清空
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">东至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="东至"
                           name="eastTo" value="${basicEstateLandState.eastTo}">
                </div>
                <label class="col-sm-1 control-label">南至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="南至"
                           name="southTo" value="${basicEstateLandState.southTo}">
                </div>
                <label class="col-sm-1 control-label">西至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="西至"
                           name="westTo" value="${basicEstateLandState.westTo}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地级别<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="landLevel" value='${basicEstateLandState.landLevel}'>
                        <input type="text" readonly="readonly" onclick="examineCommon.landLevelSelect(this);"
                               placeholder="土地级别" class="form-control" name="landLevelName" required
                               value="${basicEstateLandState.landLevelName}">
                        <div class="input-group-prepend">
                            <button class="btn btn-warning btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                清空
                            </button>
                        </div>
                        <div class="input-group-prepend">
                            <button class="btn btn-primary btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="examineCommon.landLevelSelect(this);">选择
                            </button>
                        </div>
                        <div class="input-group-prepend">
                            <button class="btn btn-info btn-sm "
                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                    type="button" onclick="estateCommon.openLevelDetailModal(this);">因素
                            </button>
                        </div>
                    </div>
                </div>
                <label class="col-sm-1 control-label">土地形状</label>
                <div class="col-sm-3">
                    <select class="form-control input-full shapeState" name="shapeState">
                    </select>
                </div>
                <label class="col-sm-1 control-label">北至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="北至"
                           name="northTo" value="${basicEstateLandState.northTo}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地面积</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" data-rule-number='true'
                           placeholder="土地面积(请输入数字)" name="landArea" value="${basicEstateLandState.landArea}">
                </div>
                <label class="col-sm-1 control-label">地形</label>
                <div class="col-sm-3">
                    <select class="form-control input-full search-select select2 planeness" name="planeness">
                    </select>
                </div>
                <label class="col-sm-1 control-label">地势</label>
                <div class="col-sm-3">
                    <select class="form-control input-full search-select select2 topographicTerrain"
                            name="topographicTerrain">
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地开发程度</label>
                <div class="col-sm-3">
                    <select class="form-control input-full developmentDegree" name="developmentDegree">
                    </select>
                </div>

                <label class="col-sm-1 developmentDegreeContent">土地开发程度备注</label>
                <div class="col-sm-3 developmentDegreeContent">
                    <input type="text" class="form-control input-full" placeholder="土地开发程度备注"
                           name="developmentDegreeRemark" value="${basicEstateLandState.developmentDegreeRemark}">
                </div>


                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开发时间
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <input type="text" placeholder="开发时间" data-date-format='yyyy-mm-dd'
                           name="developmentTime" class="form-control input-full dbdate">
                </div>

                <div class="col-sm-4">
                    <div id="developmentDegreeContentContainer"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="x_title"></div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full " placeholder="容积率"
                           name="plotRatio" value="${basicEstateLandState.plotRatio}">
                </div>
                <label class="col-sm-1 control-label">建筑密度</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="建筑密度"
                           name="buildingDensity" value="${basicEstateLandState.buildingDensity}">
                </div>
                <label class="col-sm-1 control-label">绿地率</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="绿地率"
                           name="greenSpaceRate" value="${basicEstateLandState.greenSpaceRate}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">兼容比例</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" placeholder="兼容比例"
                           name="compatibleRatio" value="${basicEstateLandState.compatibleRatio}">
                </div>
                <label class="col-sm-1 control-label">建筑限高</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control input-full" data-rule-number='true'
                           placeholder="建筑限高(数字)" name="buildingHeightLimit"
                           value="${basicEstateLandState.buildingHeightLimit}">
                </div>
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
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">财产范围<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <select class="form-control input-full search-select select2 scopeProperty" name="scopeProperty"
                                required>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label">范围包括</label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="范围包含" name="scopeInclude" class="form-control input-full"
                               value="${basicHouseTrading.scopeInclude}">
                    </div>
                    <label class="col-sm-1 control-label">范围不包括</label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="范围不包含" name="scopeNotInclude" class="form-control input-full"
                               value="${basicHouseTrading.scopeNotInclude}">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">税费负担<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <select class="form-control input-full search-select select2 taxBurden" name="taxBurden"
                                required>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label">交易情况<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <select class="form-control input-full transactionSituation" name="transactionSituation"
                                required>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label">价格类型<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <select class="form-control input-full " name="priceType" required>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group" id="abnormalTransaction" style="display: none;">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">说明事项类型</label>
                    <div class="col-sm-3">
                        <select class="form-control input-full descriptionType" name="descriptionType">
                        </select>
                    </div>
                    <label class="col-sm-1 control-label">说明事项内容</label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="说明事项内容" name="descriptionContent"
                               class="form-control input-full"
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
<%@include file="/views/project/tool/landLevelModalView.jsp" %>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js?v=${assessVersion}"></script>
<div id="detailAchievementModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="landLevelContentFrm">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="10%">土地级别类别</th>
                                        <th width="10%">土地级别类型</th>
                                        <th width="10%">土地级别等级</th>
                                        <th width="30%">说明</th>
                                        <th width="10%">分值</th>
                                        <th width="5%"></th>
                                    </tr>
                                    </thead>
                                    <tbody id="landLevelTabContent">

                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="estateCommon.saveLandLevelTabContent()">
                    保存
                </button>
            </div>

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
            <select class="form-control input-full" name="landLevelGrade"
                    onchange="estateCommon.landLevelHandle(this);">
                {landLevelGradeHTML}
            </select>
        </td>
        <td>
            {reamark}
        </td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">
            <input type="text" class="form-control input-full x-percent" name="landFactorTotalScore"
                   value="{landFactorTotalScore}">
            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="estateCommon.landLevelEmpty(this)">
        </td>
    </tr>
</script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}');
    })
</script>