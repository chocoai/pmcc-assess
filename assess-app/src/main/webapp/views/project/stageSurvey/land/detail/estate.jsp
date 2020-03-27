<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal" id="frm_estate">
    <input type="hidden" name="id" value="${basicEstate.id}">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">省
                </label>
                <div class="col-sm-3">
                    <label class="form-control input-full" name="provinceName">${basicEstate.provinceName}</label>
                </div>
                <label class="col-sm-1 control-label">市</label>
                <div class="col-sm-3">
                    <label class="form-control input-full" name="cityName">${basicEstate.cityName}</label>
                </div>
                <label class="col-sm-1 control-label">县</label>
                <div class="col-sm-3">
                    <label class="form-control input-full" name="districtName">${basicEstate.districtName}</label>
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
                        <label class="form-control" name="name">${basicEstate.name}</label>

                        <span class="input-group-btn">
                        <div onclick="estateCommon.mapMarker();" class="btn btn-info"><i
                                class="fa fa-map-marker"></i> 标注</div>

                        </span>
                    </div>
                </div>
                <label class="col-sm-1 control-label">地块方位</label>
                <div class="col-sm-3">
                    <label class="form-control input-full" name="positionName">${basicEstate.positionName}</label>
                </div>
                <label class="col-sm-1 control-label">基础版块<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control input-full" name="blockName">${basicEstate.blockName}</label>
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
                    <label class="form-control input-full">${basicEstate.blockDescription}</label>
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
                    <label class="form-control input-full">${basicEstate.street}</label>
                </div>
                <label class="col-sm-1 control-label">街道(村)号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.streetNumber}</label>
                </div>
                <label class="col-sm-1 control-label">附(组)号</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.attachNumber}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.coverAnArea}</label>
                </div>
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.volumetricRate}</label>
                </div>
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.greeningRate}</label>
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
                    <label class="form-control input-full">${basicEstate.buildingNumber}</label>
                </div>
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.developerName}</label>
                </div>
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.floorArea}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.averagePrice}</label>
                </div>
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstate.priceRange}</label>
                </div>
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    取得时间
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <label class="form-control input-full dbdate" name="openTime">
                        <fmt:formatDate value='${basicEstate.openTime}' pattern='yyyy-MM-dd'/>
                    </label>
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
                    <label class="form-control input-full">${basicEstate.locationDescribe}</label>

                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">总平面图</label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <div id="_estate_floor_total_plan"></div>
                </div>
                <label class="col-sm-1 control-label">外观图<span
                        class="symbol required"></span></label>
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
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
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地用途类型</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.landUseType}</label>
                </div>
                <label class="col-sm-1 control-label">土地用途类别</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.landUseCategory}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">东至</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.eastTo}</label>
                </div>
                <label class="col-sm-1 control-label">南至</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.southTo}</label>
                </div>
                <label class="col-sm-1 control-label">西至</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.westTo}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地级别</label>
                <div class="col-sm-3">
                    <c:if test="${empty basicEstateLandState.landLevelContent && !empty basicEstateLandState.landLevelName}">
                        <label class="form-control input-full"
                               name="landLevelName">${basicEstateLandState.landLevelName}</label>
                    </c:if>
                    <c:if test="${!empty basicEstateLandState.landLevelContent}">
                        <div class="input-group">
                            <input type="text" readonly="readonly" class="form-control input-full"
                                   name="landLevelName"
                                   value="${basicEstateLandState.landLevelName}">
                            <span class="input-group-btn">
                                              <button type="button" class="btn btn-default docs-tooltip"
                                                      onclick="estateCommon.landLevelLoadHtmlApproval();"
                                                      data-toggle="tooltip" data-original-title="土地因素">
                        <i class="fa fa-magic"></i>
                        </button>
                </span>
                        </div>
                    </c:if>
                </div>
                <label class="col-sm-1 control-label">土地形状</label>
                <div class="col-sm-3">

                    <label class="form-control input-full">${basicEstateLandState.shapeStateName}</label>
                </div>
                <label class="col-sm-1 control-label">北至</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.northTo}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地面积</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.landArea}</label>
                </div>
                <label class="col-sm-1 control-label">地形</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.planenessName}</label>
                </div>
                <label class="col-sm-1 control-label">地势</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.topographicTerrainName}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">土地开发程度</label>
                <div class="col-sm-3">

                    <label class="form-control input-full">${basicEstateLandState.developmentDegreeName}</label>
                </div>
                <c:if test="${basicEstateLandState.developmentDegreeName == '熟地'}">
                    <div class="col-sm-4 control-label">
                        <label class="form-control input-full input-full"
                               name="developmentDegreeContentName">${basicEstateLandState.developmentDegreeContentName}</label>
                    </div>
                </c:if>
                <c:if test="${basicEstateLandState.developmentDegreeName != '熟地'}">
                    <label class="col-sm-1 control-label">土地开发程度备注</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full input-full"
                               name="developmentDegreeRemark">${basicEstateLandState.developmentDegreeRemark}</label>
                    </div>
                </c:if>
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开发时间
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">

                    <label class="form-control input-full dbdate" name="openTime">
                        <fmt:formatDate value='${basicEstateLandState.developmentTime}' pattern='yyyy-MM-dd'/>
                    </label>
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
                    <label class="form-control input-full">${basicEstateLandState.plotRatio}</label>
                </div>
                <label class="col-sm-1 control-label">建筑密度</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.buildingDensity}</label>
                </div>
                <label class="col-sm-1 control-label">绿地率</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.greenSpaceRate}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">兼容比例</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.compatibleRatio}</label>
                </div>
                <label class="col-sm-1 control-label">建筑限高</label>
                <div class="col-sm-3">
                    <label class="form-control input-full">${basicEstateLandState.buildingHeightLimit}</label>
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
        <%--<div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">财产范围<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <label class="form-control input-full">${basicHouseTrading.scopePropertyName}</label>
                    </div>
                    <label class="col-sm-1 control-label">范围包括</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full">${basicHouseTrading.scopeInclude}</label>
                    </div>
                    <label class="col-sm-1 control-label">范围不包括</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full">${basicHouseTrading.scopeNotInclude}</label>
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

                        <label class="form-control input-full">${basicHouseTrading.taxBurden}</label>
                    </div>
                    <label class="col-sm-1 control-label">交易情况<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">

                        <label class="form-control input-full">${basicHouseTrading.transactionSituation}</label>
                    </div>
                    <label class="col-sm-1 control-label">价格类型<span
                            class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <label class="form-control input-full">${basicHouseTrading.priceTypeName}</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group" id="abnormalTransaction" style="display: none;">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">说明事项类型</label>
                    <div class="col-sm-3">

                        <label class="form-control input-full">${basicHouseTrading.descriptionTypeName}</label>
                    </div>
                    <label class="col-sm-1 control-label">说明事项内容</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full">${basicHouseTrading.descriptionContent}</label>
                    </div>
                </div>
            </div>
        </div>
           --%>

        <div class="x_title">
            <div class="clearfix"></div>
        </div>
        <%@include file="/views/project/stageSurvey/commonDetail/houseTradingBody.jsp" %>
    </form>
</div>
<c:if test="${empty isHistory}">
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
            <%@include file="/views/project/stageSurvey/examine/industry/detail/matchingMaterial.jsp" %>
        </c:if>
        <%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>
    </div>
</c:if>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        houseCommon.initDetailById('${basicHouse.id}', '', false);
    })
    $(function () {
        estateCommon.initDetailById('${basicEstate.id}', '', false);
    })
</script>