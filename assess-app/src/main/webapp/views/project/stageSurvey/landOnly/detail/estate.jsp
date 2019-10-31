<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal" id="frm_estate">
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
            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                取得时间
            </label>
            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                <label class="form-control dbdate" name="openTime">
                    <fmt:formatDate value='${basicEstate.openTime}' pattern='yyyy-MM-dd'/>
                </label>
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
            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                开发时间
            </label>
            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">

                <label class="form-control dbdate" name="openTime">
                    <fmt:formatDate value='${basicEstateLandState.developmentTime}' pattern='yyyy-MM-dd'/>
                </label>
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
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑限高 m²</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control">${basicEstateLandState.buildingHeightLimit}</label>
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
                    <label class="form-control">${basicHouseTrading.scopePropertyName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">范围包括</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouseTrading.scopeInclude}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">范围不包括</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouseTrading.scopeNotInclude}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">税费负担<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">

                    <label class="form-control">${basicHouseTrading.taxBurden}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">交易情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">

                    <label class="form-control">${basicHouseTrading.transactionSituation}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouseTrading.priceTypeName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div id="abnormalTransaction" style="display: none;">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">说明事项类型</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">

                        <label class="form-control">${basicHouseTrading.descriptionTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">说明事项内容</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${basicHouseTrading.descriptionContent}</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="x_title">
            <div class="clearfix"></div>
        </div>
        <%@include file="/views/project/stageSurvey/commonDetail/houseTradingBody.jsp" %>
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
    <%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js"></script>
<script type="text/javascript">
    $(function () {
        houseCommon.initById('${basicHouse.id}');
    })
    $(function () {
        estateCommon.initById('${basicEstate.id}');
    })
</script>