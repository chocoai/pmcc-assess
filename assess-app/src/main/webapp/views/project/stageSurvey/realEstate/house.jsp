<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息填写</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        信息填写
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showHistoryModal();">历史记录
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showCaseQuoteModal();">引用案例
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showProjectQuoteModal();">引用备选案例
                                        </button>
                                    </div>
                                </div>
                            </div>
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
                                        <input type="hidden" name="quoteId" value="${basicHouse.quoteId}">
                                        <input type="hidden" name="unitId" value="${basicHouse.unitId}">
                                        <input type="hidden" name="estateId" value="${basicHouse.estateId}">
                                        <input type="hidden" name="buildingId" value="${basicHouse.buildingId}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">房号<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" required placeholder="房号"
                                                                           name="houseNumber"
                                                                           class="form-control input-full"
                                                                           value="${basicHouse.houseNumber}"
                                                                           id="txt_House_search">
                                                                </div>
                                                                <label class="col-sm-1">所在楼层<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="所在楼层" name="floor"
                                                                           required
                                                                           class="form-control input-full"
                                                                           value="${basicHouse.floor}">
                                                                </div>
                                                                <label class="col-sm-1">楼层描述</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" class="form-control input-full"
                                                                           name="floorDesc"
                                                                           value="${basicHouse.floorDesc}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">户型(开发商)</label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="hidden" name="huxingId"
                                                                               value="${basicHouse.huxingId}">
                                                                        <input type="text" readonly="readonly"
                                                                               onclick="houseCommon.selectHuxing(this);"
                                                                               placeholder="户型" class="form-control"
                                                                               name="huxingName"
                                                                               value="${basicHouse.huxingName}">

                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-warning btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                                清空
                                                                            </button>
                                                                        </div>
                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-primary btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="houseCommon.selectHuxing(this);">
                                                                                选择
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1">户型图(开发商)</label>
                                                                <div class="col-sm-3">
                                                                    <div id="_house_huxing_plan"></div>
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">户型(装修)</label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="hidden" name="huxingData"
                                                                               value="${basicHouse.huxingData}">
                                                                        <input type="text" placeholder="户型(装修)"
                                                                               name="newHuxingName"
                                                                               class="form-control"
                                                                               value="${basicHouse.newHuxingName}">
                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-primary btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="houseCommon.openHuxingModal();">
                                                                                编辑
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1">户型图(装修)</label>
                                                                <div class="col-sm-3">
                                                                    <input id="house_new_huxing_plan" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_house_new_huxing_plan"></div>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <input type="hidden" name="mapId"
                                                                           value="${basicHouse.mapId}">
                                                                    <div class="btn btn-success btn-sm"
                                                                         onclick="houseCommon.orientationFun(false)">
                                                                        户型图朝向
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">面积</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="面积" name="area"
                                                                           data-rule-number="true"
                                                                           class="form-control input-full"
                                                                           value="${basicHouse.area}">
                                                                </div>
                                                                <label class="col-sm-1">面积描述</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" data-rule-maxlength="255"
                                                                           placeholder="面积描述"
                                                                           name="areaDesc"
                                                                           class="form-control input-full"
                                                                           value="${basicHouse.areaDesc}">
                                                                </div>
                                                                <label class="col-sm-1">调查方式<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full search-select select2 researchType"
                                                                            name="researchType"
                                                                            required>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">
                                                                    证载用途<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                                    <div class="input-group">
                                                                        <input type="text" name="certUse"
                                                                               list="certUseList"
                                                                               value="${basicHouse.certUse}"
                                                                               class="form-control">
                                                                        <datalist id="certUseList">

                                                                        </datalist>

                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-warning btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                                清空
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    实际用途<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                                    <div class="input-group">
                                                                        <input type="text" required name="practicalUse"
                                                                               list="practicalUseList"
                                                                               value="${basicHouse.practicalUse}"
                                                                               class="form-control">
                                                                        <datalist id="practicalUseList">

                                                                        </datalist>
                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-warning btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                                清空
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1">装修情况<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full 	decorateSituation"
                                                                            name="decorateSituation" required>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">装修情况描述<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" data-rule-maxlength="255"
                                                                           placeholder="装修情况描述" required
                                                                           name="decorateSituationDescription"
                                                                           class="form-control input-full"
                                                                           value="${basicHouse.decorateSituationDescription}">
                                                                </div>
                                                                <label class="col-sm-1">使用情况<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full useCondition"
                                                                            name="useCondition" required>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid" style="display: none;">
                                                                <label class="col-sm-1">使用情况描述<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-11">
                    <textarea class="form-control input-full" required
                              name="useConditionDescription"
                              id="useConditionDescription">${basicHouse.useConditionDescription}</textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">房屋平面图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="house_img_plan" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_house_img_plan"></div>
                                                                </div>
                                                                <label class="col-sm-1">房屋装饰图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="house_decorate" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_house_decorate"></div>
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    附件
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input id="house_file" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_house_file"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
                                                    <label class="col-sm-1">物业类型<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <div class="input-group">
                                                            <input type="text" required name="tenementType"
                                                                   value="${basicHouseHuxing.tenementType}"
                                                                   class="form-control" list="tenementTypeList">
                                                            <datalist id="tenementTypeList">

                                                            </datalist>
                                                            <div class="input-group-prepend">
                                                                <button class="btn btn-warning btn-sm "
                                                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                        type="button"
                                                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                                                    清空
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-1">户型名称<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <div class="input-group">
                                                            <input type="hidden" name="huxingData"
                                                                   value="${basicHouseHuxing.huxingData}">
                                                            <input type="text" placeholder="户型(装修)"
                                                                   name="name"
                                                                   class="form-control"
                                                                   value="${basicHouseHuxing.name}">
                                                            <div class="input-group-prepend">
                                                                <button class="btn btn-primary btn-sm "
                                                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                        type="button"
                                                                        onclick="houseCommon.openHouseHuxingModal();">
                                                                    编辑
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-1">面积<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="面积" name="area"
                                                               class="form-control input-full"
                                                               required
                                                               value="${basicHouseHuxing.area}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">朝向<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 orientation"
                                                                name="orientation"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">空间布局<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 spatialDistribution"
                                                                name="spatialDistribution"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">水电费标准<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 utilitiesMeasure"
                                                                name="utilitiesMeasure"
                                                                required>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">所在楼层<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="所在楼层" name="currentFloor"
                                                               class="form-control input-full" id="currentFloor"
                                                               required value="${basicHouseHuxing.currentFloor}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">备注<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-11">
                                                        <textarea class="form-control input-full" required
                                                                  name="description"
                                                                  id="description">${basicHouseHuxing.description}</textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">
                                                        附件
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input id="house_huxing" type="file" multiple="false">
                                                        <div id="_house_huxing"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
                                                    <label class="col-sm-1">财产范围<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 scopeProperty"
                                                                name="scopeProperty"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">范围包括<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="范围包含" name="scopeInclude"
                                                               class="form-control input-full"
                                                               required
                                                               value="${basicHouseTrading.scopeInclude}">
                                                    </div>
                                                    <label class="col-sm-1">范围不包括<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="范围不包含" name="scopeNotInclude"
                                                               class="form-control input-full"
                                                               required
                                                               value="${basicHouseTrading.scopeNotInclude}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">税费负担<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 taxBurden"
                                                                name="taxBurden"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">交易情况<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full transactionSituation"
                                                                name="transactionSituation"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">价格类型<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full " name="priceType"
                                                                required>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid" id="abnormalTransaction"
                                                     style="display: none;">
                                                    <label class="col-sm-1">说明事项类型</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full descriptionType"
                                                                name="descriptionType">
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">说明事项内容</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="说明事项内容"
                                                               name="descriptionContent"
                                                               class="form-control input-full"
                                                               value="${basicHouseTrading.descriptionContent}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">交易类型<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full tradingType"
                                                                name="tradingType"
                                                                required="required">
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">付款方式<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full paymentMethod"
                                                                name="paymentMethod" required>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="" style="display: none;">
                                                    <label class="col-sm-1">分期支付利率</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="分期支付利率"
                                                               name="installmentInterestRate"
                                                               class="form-control input-full"
                                                               value="${basicHouseTrading.installmentInterestRate}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="x_title tradingCondition">融资条件</div>
                                        <div class="row form-group tradingCondition">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">首付款比例<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               name="downPaymentRatio" placeholder="首付款比例"
                                                               required>
                                                    </div>
                                                    <label class="col-sm-1">贷款利率<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               name="lendingRate" placeholder="贷款利率"
                                                               required>
                                                    </div>
                                                    <label class="col-sm-1">贷款期限<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               data-rule-digits="true" name="loanPeriod"
                                                               placeholder="贷款期限"
                                                               required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x_title">
                                            <div class="clearfix"></div>
                                        </div>


                                        <div class="row form-group ExamineHouseTradingSell" style="display: none">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">买方额外支付的税</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="买方额外支付的税"
                                                               name="buyerExtraTax" class="form-control input-full"
                                                               value="${basicHouseTrading.buyerExtraTax}">
                                                    </div>
                                                    <label class="col-sm-1">买方额外支付的费</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="买方额外支付的费"
                                                               name="buyerExtraFee" class="form-control input-full"
                                                               value="${basicHouseTrading.buyerExtraFee}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group ExamineHouseTradingLease" style="display: none">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">承租方额外支付的税</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="承租方额外支付的税"
                                                               name="rentingExtraTax"
                                                               value="${basicHouseTrading.rentingExtraTax}"
                                                               class="form-control input-full">
                                                    </div>
                                                    <label class="col-sm-1">承租方额外支付的费</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="承租方额外支付的费"
                                                               name="rentingExtraFee"
                                                               value="${basicHouseTrading.rentingExtraFee}"
                                                               class="form-control input-full">
                                                    </div>
                                                    <label class="col-sm-1">押金（元）</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="押金（元）"
                                                               class="form-control input-full" name="deposit"
                                                               value="${basicHouseTrading.deposit}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group ExamineHouseTradingSell" style="display: none">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <div class="col-sm-1" style="text-align: right;">
                                                        <button type="button" class="btn btn-success btn-sm"
                                                                data-toggle="modal"
                                                                onclick="houseCommon.addTradingSellAndLease()"> <span
                                                                class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                                                        </button>
                                                    </div>
                                                    <div class="col-sm-11">
                                                        <table class="table table-bordered"
                                                               id="tableTradingSell"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group ExamineHouseTradingLease" style="display: none">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <div class="col-sm-1" style="text-align: right;">
                                                        <button type="button" class="btn btn-success btn-sm"
                                                                data-toggle="modal"
                                                                onclick="houseCommon.addTradingSellAndLease()"><span
                                                                class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                                                        </button>
                                                    </div>
                                                    <div class="col-sm-11">
                                                        <table class="table table-bordered"
                                                               id="tableTradingLease"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">交易时间<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input placeholder="交易时间" required="required"
                                                               name="tradingTime" data-date-format="yyyy-mm-dd"
                                                               class="form-control input-full date-picker dbdate tradingTime"
                                                               value="<fmt:formatDate value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1">交易总价（元）<span
                                                            class="symbol required"></span> </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="交易总价（元）"
                                                               class="form-control input-full"
                                                               name="tradingTotalPrice" required
                                                               onblur="houseCommon.computeUnitPrice();"
                                                               value="${basicHouseTrading.tradingTotalPrice}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">交易单价（元）<span
                                                            class="symbol required"></span> </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="交易单价（元）"
                                                               class="form-control input-full" name="tradingUnitPrice"
                                                               required
                                                               value="${basicHouseTrading.tradingUnitPrice}">
                                                    </div>
                                                    <label class="col-sm-1">单价内涵<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full priceConnotation"
                                                                name="priceConnotation"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1 priceConnotationUnitContent">单价单位<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3 priceConnotationUnitContent">
                                                        <input type="text" placeholder="单价单位(元/㎡|元/个)"
                                                               class="form-control input-full"
                                                               name="priceConnotationUnit"
                                                               id="priceConnotationUnit" required
                                                               value="${basicHouseTrading.priceConnotationUnit}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">信息来源<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 informationType"
                                                                name="informationType">
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1 infomationTypeOpen" style="display: none;">信息来源类别</label>
                                                    <div class="col-sm-3 infomationTypeOpen" style="display: none;">
                                                        <select class="form-control input-full search-select select2 informationCategory"
                                                                name="informationCategory">
                                                        </select>
                                                    </div>


                                                    <label class="col-sm-1 infomationTypeOther" style="display: none;">姓名<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3 infomationTypeOther" style="display: none;">
                                                        <input type="text" placeholder="姓名"
                                                               class="form-control input-full" name="name" required
                                                               value="${basicHouseTrading.name}">
                                                    </div>


                                                    <label class="col-sm-1 infomationTypeOther" style="display: none;">电话<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3 infomationTypeOther" style="display: none;">
                                                        <input type="text" placeholder="电话"
                                                               class="form-control input-full" name="phone" required
                                                               value="${basicHouseTrading.phone}">
                                                    </div>
                                                </div>


                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">
                                                        交易附件
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input id="house_trading_file_f" type="file" multiple="false">
                                                        <div id="_house_trading_file_f"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/houseRoom.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/houseRoomDecorate.jsp" %>
                                    <c:if test="${formType eq 'residence'}">
                                        <%@include file="/views/project/stageSurvey/common/houseWater.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseWaterDrain.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseNewWind.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseAirConditioner.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseHeating.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseIntelligent.jsp" %>
                                    </c:if>
                                    <c:if test="${formType eq 'industry'}">
                                        <%@include
                                                file="/views/project/stageSurvey/common/houseCorollaryEquipment.jsp" %>
                                    </c:if>
                                    <%@include file="/views/project/stageSurvey/common/houseDamagedDegree.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/houseHuxingPrice.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
                            </button>
                            <button type="button" class="btn btn-warning" onclick="saveDataInfo();">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<div id="divBoxTradingLeaseAndSell" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">&times;</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTradingLeaseAndSell" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type" class="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <!--lease -->
                                <div class="lease">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    租金支付时间起<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="租金支付时间起"
                                                           name="rentPaymentTimeStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-2">
                                                    租金支付时间止<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="租金支付时间起"
                                                           name="rentPaymentTimeEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    租金增长比率<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="text" data-rule-number='true'
                                                           class="form-control input-full"
                                                           name="rentGrowthRate"
                                                           placeholder="租金增长比率(请输入数字)" required="required">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--sell -->
                                <div class="sell">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    分期支付时间起<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="分期支付时间起"
                                                           name="instalmentPeriodStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-2">
                                                    分期支付时间起止<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="分期支付时间起止"
                                                           name="instalmentPeriodEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    分期支付利息<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="text" data-rule-number='true'
                                                           class="form-control input-full"
                                                           name="instalmentInterest"
                                                           placeholder="分期支付利息(请输入数字)" required="required">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="houseCommon.saveTradingSellAndLease()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxHuxing" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">户型</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHuxing" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                卧室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="house" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                客厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="saloon"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                厨房
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="kitchen"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                卫生间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="toilet"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                花园
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="garden"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                阳台
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="balcony"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="houseCommon.spliceHuxing()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxHouseHuxing" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">户型</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseHuxing" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <%--住宅 residence--%>
                                <div style="display: none;" class="row form-group residence">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                卧室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="bedroom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                客厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="drawing"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                中餐厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="restaurant"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group residence">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                西餐厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="westernRestaurant"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                茶室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="tearoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                影视室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="television"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--商铺/商场 store--%>
                                <div style="display: none;" class="row form-group store">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                商间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="storeNum"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                商区
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="businessZone"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--餐饮酒店 hotel--%>
                                <div style="display: none;" class="x_title hotel">住宿</div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                普通标间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="commonStandardRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                商务标间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="businessStandardRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                高级标间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="expertStandardRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                普通单间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="commonSingleRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                商务单间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="businessSingleRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                高级单间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="expertSingleRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                普通套房
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="commonSuite"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                商务套房
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="businessSuite"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                高级套房
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="expertSuite"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="x_title hotel">商业</div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                会议室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="meetingRoom1"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                会议厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="meetingHall"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                商务厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="businessHall"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    <div style="display: none;" class="row form-group hotel">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">
                                                    影视厅
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="个数" name="televisionHall"
                                                           data-rule-number='true'
                                                           class="form-control input-full">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <div style="display: none;" class="x_title hotel">餐饮</div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                普通包间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="commonPrivateRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                标准包间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="standardPrivateRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                豪华包间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="luxuryPrivateRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                餐饮大厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="cateringHall"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                共用餐区
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="shareRepast"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--办公 work--%>
                                <div style="display: none;" class="row form-group work">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                会议室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="meetingRoom2"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                会客室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="receptionArea"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                休息室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="lounge"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group work">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                办公室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="workRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                办公区
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="workArea"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                档案室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="archivesRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group work">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                影视室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="televisionRoom"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--生产 production--%>
                                <div style="display: none;" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                生产车间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="productionShop"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                维修车间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="maintainShop"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                成品车间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="finishedShop"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                热力车间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="heatingShop"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                中转车间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="transferShop"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--仓储 storage--%>
                                <div style="display: none;" class="row form-group storage">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                储库
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="storageCavern"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                储仓
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="warehouse"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="x_title common">通用部分</div>
                                <div class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                卫生间
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="toilet"
                                                           class="form-control">
                                                    <input type="hidden" name="toiletContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1">
                                                洗浴间
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="bathroom"
                                                           class="form-control">
                                                    <input type="hidden" name="bathroomContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1">
                                                厨房
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="kitchen"
                                                           class="form-control">
                                                    <input type="hidden" name="kitchenContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                阳台
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="balcony"
                                                           class="form-control">
                                                    <input type="hidden" name="balconyContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1">
                                                车库
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="carport"
                                                           class="form-control">
                                                    <input type="hidden" name="carportContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1">
                                                楼梯间
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="staircase"
                                                           class="form-control">
                                                    <input type="hidden" name="staircaseContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                电梯间
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="elevator"
                                                           class="form-control">
                                                    <input type="hidden" name="elevatorContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1">
                                                过道
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" placeholder="个数"
                                                           name="passage"
                                                           class="form-control">
                                                    <input type="hidden" name="passageContent">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseCommon.openCommonItemModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="houseCommon.spliceHouseHuxing()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxHuxingCommonDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHuxingCommonDetail" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1">
                                                公共
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="common" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                独立
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="independent"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1">
                                                公用
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="public"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">

            </div>

        </div>
    </div>
</div>


<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/select/huxing.select.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/autocomplete/roomType.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        houseCommon.initById('${basicHouse.id}');
        $("#txt_House_search").apHouse({
            caseUnitId: function () {
                return '${quoteId}';
            },
            onSelect: function (id, name) {
                caseFun.caseHouse.showModel('${quoteId}', name);
            }
        });
    })

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
        var item = {};
        item.basicHouse = formSerializeArray(houseCommon.houseForm);
        item.basicTrading = formSerializeArray(houseCommon.houseTradingForm);
        item.basicDamagedDegree = damagedDegree.getFormData();
        var formData = JSON.stringify(examineCommon.getFormData());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                formData: formData,
                formClassify: '${tbType}',
                planDetailsId: '${planDetailsId}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "保存数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败,失败原因:" + result.errmsg);
                }
            }
        });
    }

    function showHistoryModal() {
        historyInfo.caseHouse.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        caseFun.caseHouse.showModel(${quoteId});
    }

    function showProjectQuoteModal() {
        projectHouse.prototype.showModel();
    }
</script>