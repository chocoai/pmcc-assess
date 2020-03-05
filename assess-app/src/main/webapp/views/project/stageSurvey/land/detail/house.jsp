<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>房屋</title>
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
                                        房屋基本信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="basicHouseFrm">
                                    <input type="hidden" name="id" value="${basicHouse.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">房号</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="houseNumber">${basicHouse.houseNumber}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">所在楼层</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="floor">${basicHouse.floor}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">楼层描述</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="floorDesc">${basicHouse.floorDesc}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">户型</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="huxingName">${basicHouse.huxingName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">户型图</label>
                                                <div class="col-sm-3">
                                                    <div id="_house_huxing_plan"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">空间布局</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="spatialDistributionName">${basicHouse.spatialDistributionName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">空间布局描述</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="spatialDistributionDesc">${basicHouse.spatialDistributionDesc}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">朝向</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="orientationName">${basicHouse.orientationName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">面积</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="area">${basicHouse.area}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">面积描述</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="areaDesc">${basicHouse.areaDesc}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">调查方式</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="researchTypeName">${basicHouse.researchTypeName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">实际用途</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="practicalUse">${basicHouse.practicalUse}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">使用情况</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="useConditionName">${basicHouse.useConditionName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">使用情况描述</label>
                                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                                    <label class="form-control input-full" id="useConditionDescription"
                                                           name="useConditionDescription">${basicHouse.useConditionDescription}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">房屋平面图</label>
                                                <div class="col-sm-3">
                                                    <div id="_house_img_plan"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${empty isHistory}">
                        <%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseRoom.jsp" %>
                        <c:if test="${formType eq 'residence'}">
                            <%@include file="/views/project/stageSurvey/commonDetail/houseWater.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/houseWaterDrain.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/houseNewWind.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/houseAirConditioner.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/houseHeating.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/houseIntelligent.jsp" %>
                        </c:if>
                        <c:if test="${formType eq 'industry'}">
                            <%@include file="/views/project/stageSurvey/commonDetail/houseCorollaryEquipment.jsp" %>
                        </c:if>
                        <%@include file="/views/project/stageSurvey/commonDetail/houseDamagedDegree.jsp" %>
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

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js?v=${assessVersion}"></script>

<script type="text/javascript">
    $(function () {
        houseCommon.initDetailById('${basicHouse.id}', '', false);
    })
</script>
</html>


