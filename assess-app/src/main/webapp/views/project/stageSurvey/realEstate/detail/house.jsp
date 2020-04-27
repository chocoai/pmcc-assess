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
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
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
                                                               name="certUseName">${basicHouse.certUseName}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">实际用途</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="practicalUseName">${basicHouse.practicalUseName}</label>
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
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            户型
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseHuxing">
                                        <input type="hidden" name="id" value="${basicHouseHuxing.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">物业类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="tenementType">${basicHouseHuxing.tenementType}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">户型名称</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="newHuxingName">${basicHouseHuxing.name}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">面积(m²)</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="area">${basicHouseHuxing.area}</label>
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
                                                               name="orientationName">${basicHouseHuxing.orientationName}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">空间布局</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="spatialDistributionName">${basicHouseHuxing.spatialDistributionName}</label>
                                                    </div>
                                                    <label class="col-sm-1">户数</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full" id="quantity"
                                                               name="quantity">${basicHouseHuxing.quantity}</label>
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
                                                               name="utilitiesMeasureName">${basicHouseHuxing.utilitiesMeasureName}</label>
                                                    </div>
                                                    <label class="col-sm-1 utilitiesTypeContent">水电费类型<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3 utilitiesTypeContent">
                                                        <label class="form-control input-full" id="utilitiesType"
                                                               name="utilitiesTypeName">${basicHouseHuxing.utilitiesTypeName}</label>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div id="houseHuxingFilePart"></div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋交易信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicTradingFrm">
                                        <input type="hidden" name="id" value="${basicHouseTrading.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">财产范围</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="scopePropertyName">${basicHouseTrading.scopePropertyName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">范围包括</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="scopeInclude">${basicHouseTrading.scopeInclude}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">范围不包括</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="scopeNotInclude">${basicHouseTrading.scopeNotInclude}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">税费负担</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="taxBurdenName">${basicHouseTrading.taxBurdenName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">交易情况</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="transactionSituationName">${basicHouseTrading.transactionSituationName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">价格类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="priceTypeName">${basicHouseTrading.priceTypeName}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <c:if test="${!empty basicHouseTrading.descriptionTypeName}">
                                                        <label class="col-sm-1 control-label">说明事项类型</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="descriptionTypeName">${basicHouseTrading.descriptionTypeName}</label>
                                                        </div>

                                                    </c:if>
                                                    <c:if test="${!empty basicHouseTrading.descriptionContent}">

                                                        <label class="col-sm-1 control-label">说明事项内容</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="descriptionContent">${basicHouseTrading.descriptionContent}</label>
                                                        </div>
                                                    </c:if>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">交易类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="tradingTypeName">${basicHouseTrading.tradingTypeName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">付款方式</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="paymentMethodName">${basicHouseTrading.paymentMethodName}</label>
                                                    </div>

                                                    <c:if test="${!empty basicHouseTrading.installmentInterestRate}">

                                                        <label class="col-sm-1 control-label">分期支付利率</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="installmentInterestRate">${basicHouseTrading.installmentInterestRate}</label>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x_title tradingCondition">融资条件</div>
                                        <div class="row form-group tradingCondition">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">首付款比例</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="downPaymentRatio">${basicHouseTrading.downPaymentRatio}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">贷款利率</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="lendingRate">${basicHouseTrading.lendingRate}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">贷款期限</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="loanPeriod">${basicHouseTrading.loanPeriod}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${basicHouseTrading.tradingTypeName=='出售'}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">买方额外支付的税</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="buyerExtraTax">${basicHouseTrading.buyerExtraTax}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">买方额外支付的费</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="buyerExtraFee">${basicHouseTrading.buyerExtraFee}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                            <table class="table table-bordered"
                                                                   id="tableTradingSell"></table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${basicHouseTrading.tradingTypeName != '出售'}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">承租方额外支付的税</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="rentingExtraTax">${basicHouseTrading.rentingExtraTax}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">承租方额外支付的费</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="rentingExtraFee">${basicHouseTrading.rentingExtraFee}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">押金（元）</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="deposit">${basicHouseTrading.deposit}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                            <table class="table table-bordered"
                                                                   id="tableTradingLease"></table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">交易时间</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full dbdate"
                                                               name="tradingTime"><fmt:formatDate
                                                                value='${basicHouseTrading.tradingTime}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">交易总价（元）</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="tradingTotalPrice">${basicHouseTrading.tradingTotalPrice}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">交易单价（元）</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="tradingUnitPrice">${basicHouseTrading.tradingUnitPrice}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">单价内涵</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="priceConnotationName">${basicHouseTrading.priceConnotationName}</label>
                                                    </div>

                                                    <c:if test="${not empty basicHouseTrading.priceConnotationUnit}">

                                                        <label class="col-sm-1 control-label">单价单位</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="priceConnotationUnit"
                                                                   id="priceConnotationUnit">${basicHouseTrading.priceConnotationUnit}</label>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">信息来源类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="informationTypeName">${basicHouseTrading.informationTypeName}</label>
                                                    </div>

                                                    <c:if test="${basicHouseTrading.informationTypeName == '公开信息'}">

                                                        <label class="col-sm-1 control-label">信息来源类别</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="informationCategoryName">${basicHouseTrading.informationCategoryName}</label>
                                                        </div>

                                                    </c:if>
                                                    <c:if test="${basicHouseTrading.informationTypeName != '公开信息'}">

                                                        <label class="col-sm-1 control-label">姓名</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="name">${basicHouseTrading.name}</label>
                                                        </div>

                                                        <label class="col-sm-1 control-label">电话</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="phone">${basicHouseTrading.phone}</label>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="houseTradingFilePart"></div>
                                    </form>
                                </div>
                                <div class="x_content">
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
                                </div>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/project/chksCustomize/chksSurvey.jsp" %>

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
<%@include file="/views/share/chksCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js?v=${assessVersion}"></script>

<script>
    $(function () {
        houseCommon.initDetailById('${basicHouse.id}', '', false);
    })
</script>
</html>
