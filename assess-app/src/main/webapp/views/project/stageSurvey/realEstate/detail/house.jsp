<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>房屋</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/common_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <div class="col-md-12">
                                    <div class="card full-height">
                                        <div class="card-header collapse-link">
                                            <div class="card-head-row">
                                                <div class="card-title">
                                                    户型信息
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x_content">
                                            <form class="form-horizontal" id="basicHouseHuxing">
                                                <input type="hidden" name="id">
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">物业类型</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="tenementType"></label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">户型名称</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="name"></label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">面积(m²)</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="area"></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">朝向</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="orientationName"></label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">空间布局</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="spatialDistributionName"></label>
                                                            </div>
                                                            <label class="col-sm-1">户数</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full" id="quantity"
                                                                       name="quantity"></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">水电费标准</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="utilitiesMeasureName"></label>
                                                            </div>
                                                            <label class="col-sm-1 utilitiesTypeContent">水电费类型</label>
                                                            <div class="col-sm-3 utilitiesTypeContent">
                                                                <label class="form-control input-full" id="utilitiesType"
                                                                       name="utilitiesTypeName"></label>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="houseHuxingFilePart"></div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="card full-height">
                                        <div class="card-header collapse-link">
                                            <div class="card-head-row">
                                                <div class="card-title">
                                                    房屋信息
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x_content">
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
                                                            <label class="col-sm-1 control-label">面积(m²)</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="area">${basicHouse.area}</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">调查方式</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="researchTypeName">${basicHouse.researchTypeName}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">证载用途</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="certUse">${basicHouse.certUse}</label>
                                                            </div>
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
                                                            <label class="col-sm-1 control-label">装修情况</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="decorateSituationName">${basicHouse.decorateSituationName}</label>
                                                            </div>
                                                            <label class="col-sm-1 control-label">装修情况描述</label>
                                                            <div class="col-sm-3">
                                                                <label class="form-control input-full"
                                                                       name="decorateSituationDescription">${basicHouse.decorateSituationDescription}</label>
                                                            </div>

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
                                                <div id="houseFilePart"></div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${projectPhase eq 'caseStudyExtend'}">
                                    <div class="x_title">
                                        <h3>
                                            交易信息
                                            <small>
                                                <button class="btn btn-sm btn-success" style="margin-left: 5px"
                                                        type="button" onclick="houseTrading.appendHtml(false)">
                                                <span class="btn-label"><i
                                                        class="fa fa-plus"></i>
                                                </span>
                                                    添加分组
                                                </button>
                                            </small>
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div id="basicHouseTradingAppend"></div>
                                    <%@include file="/views/project/stageSurvey/commonDetail/houseTradingCase.jsp" %>
                                </c:if>
                                <div class="x_content">
                                    <c:if test="${projectPhase ne 'caseStudyExtend'}">
                                        <%@include file="/views/project/stageSurvey/commonDetail/houseTradingSurvey.jsp" %>
                                    </c:if>

                                    <c:if test="${empty isHistory}">
                                        <%@include file="/views/project/stageSurvey/commonDetail/houseRoom.jsp" %>
                                        <%@include file="/views/project/stageSurvey/commonDetail/houseRoomDecorate.jsp" %>
                                        <%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>
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
                                    <%@include file="/views/project/stageSurvey/commonDetail/houseHuxingPrice.jsp" %>
                                    <%@include file="/views/project/stageSurvey/commonDetail/houseSurveyRecord.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
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

<script>
    $(function () {
        houseCommon.initDetailById('${basicHouse.id}', '', false);
        if(${projectPhase eq 'caseStudyExtend'}){
            houseTrading.init();
        }
    })
</script>
</html>
