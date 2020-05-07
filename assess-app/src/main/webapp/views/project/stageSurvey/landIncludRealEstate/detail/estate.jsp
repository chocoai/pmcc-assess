<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>土地带房产</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        土地带房产
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_estate" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicEstate.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">省
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="provinceName">${basicEstate.provinceName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">市</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="cityName">${basicEstate.cityName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">县</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="districtName">${basicEstate.districtName}</label>
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
                                                        <label class="form-control"
                                                               name="name">${basicEstate.name}</label>
                                                        <span class="input-group-btn">
                                    <div onclick="estateCommon.mapMarker(true);" class="btn btn-info"><i
                                            class="fa fa-map-marker"></i> 标注</div>
                                    </span>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">地块方位</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="positionName">${basicEstate.positionName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">基础版块<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="blockName">${basicEstate.blockName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">基础版块描述<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-11">
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
                                                <label class="col-sm-1 control-label">地块面积</label>
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
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">地块区位分析<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-11">
                                                    <label class="form-control input-full">${basicEstate.locationDescribe}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">总平面图</label>
                                                <div class="col-sm-5">
                                                    <div id="_estate_floor_total_plan"></div>
                                                </div>
                                                <label class="col-sm-1 control-label">外观图<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-5">
                                                    <div id="_estate_floor_Appearance_figure"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${basicApplyBatch.type == 1}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">供水平面图</label>
                                                    <div class="col-sm-5">

                                                        <div id="_estate_water_supply_plan"></div>
                                                    </div>
                                                    <label class="col-sm-1 control-label">供电平面图</label>
                                                    <div class="col-sm-5">

                                                        <div id="_estate_power_supply_plan"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">供气平面图</label>
                                                    <div class="col-sm-5">

                                                        <div id="_estate_air_supply_plan"></div>
                                                    </div>
                                                    <label class="col-sm-1 control-label">采暖平面图</label>
                                                    <div class="col-sm-5">

                                                        <div id="_estate_heating_plan"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="x_content">
                                        <div class="x_title">基础设施情况</div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">基础设施完备度<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">

                                                        <label class="form-control input-full">${basicEstate.infrastructureCompletenessName}</label>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <label class="form-control input-full">${basicEstate.infrastructureName}</label>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        土地实体情况
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_estateLandState" class="form-horizontal">
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
                                                <input type="hidden" name="landLevelContent"
                                                       value='${basicEstateLandState.landLevelContent}'>
                                                <label class="col-sm-1 control-label">土地级别</label>
                                                <div class="col-sm-3">
                                                    <c:if test="${empty basicEstateLandState.landLevelContent && !empty basicEstateLandState.landLevelName}">
                                                        <label class="form-control input-full"
                                                               name="landLevelName">${basicEstateLandState.landLevelName}</label>
                                                    </c:if>
                                                    <c:if test="${!empty basicEstateLandState.landLevelContent}">
                                                        <div class="input-group">
                                                            <input type="text" readonly="readonly"
                                                                   class="form-control input-full"
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
                                                <label class="col-sm-1 control-label">土地利用现状</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${basicEstateLandState.presentSituationLandUse}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="x_title">开发限制条件</div>
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
                                                <div style="display: none">
                                                    <label class="col-sm-1 control-label">投资强度（万元/亩）</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${basicEstateLandState.investmentIntensity}</label>
                                                    </div>
                                                </div>
                                                <div style="display: none">
                                                    <label class="col-sm-1 control-label">建筑限高</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${basicEstateLandState.buildingHeightLimit}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                    <c:if test="${empty isHistory}">
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
                            <%--<%@include file="/views/project/stageSurvey/commonDetail/industry/matchingMaterial.jsp" %>--%>
                        </c:if>
                    </c:if>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>
<div id="detailAchievementModal" class="modal fade bs-example-modal-lg"
     data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true" data-height="500">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地因素</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="landLevelContentFrm">
                    <div class="row form-group">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th width="10%">土地级别类别</th>
                                    <th width="10%">土地级别类型</th>
                                    <th width="10%">土地级别等级</th>
                                    <th width="20%">说明</th>
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
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
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
            {gradeName}
        </td>
        <td>
            <label name="reamark" class="form-control input-full">{reamark}</label>
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control input-full">{landFactorTotalScore}</label>
        </td>
    </tr>
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    $(function () {
        estateCommon.initDetailById('${basicEstate.id}', '', false);
    })
</script>

