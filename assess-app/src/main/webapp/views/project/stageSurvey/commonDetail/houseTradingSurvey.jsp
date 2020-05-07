<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 房屋交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    交易信息
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
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

                            <label class="col-sm-1 control-label">单价内涵</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full"
                                       name="priceConnotationName">${basicHouseTrading.priceConnotationName}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>