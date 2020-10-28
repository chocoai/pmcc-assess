<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 房屋交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card-header">
    <div class="card-title">交易信息</div>
</div>
<div class="x_content">
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
                    <label class="col-sm-1 control-label">说明事项类型</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="descriptionTypeName">${basicHouseTrading.descriptionTypeName}</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">说明事项内容</label>
                    <div class="col-sm-11">
                        <label class="form-control input-full"
                               name="descriptionContent">${basicHouseTrading.descriptionContent}</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1">市场级别</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="transactionLevelName">${basicHouseTrading.transactionLevelName}</label>
                    </div>
                    <label class="col-sm-1 control-label">交易类型</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="tradingTypeName">${basicHouseTrading.tradingTypeName}</label>
                    </div>
                    <label class="col-sm-1 control-label">税费负担</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="taxBurdenName">${basicHouseTrading.taxBurdenName}</label>
                    </div>
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
                        <label class="col-sm-1 control-label">付款方式</label>
                        <div class="col-sm-3">
                            <label class="form-control input-full"
                                   name="paymentMethodName">${basicHouseTrading.paymentMethodName}</label>
                        </div>
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
                    <label class="col-sm-1 control-label">面积</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full">${basicHouse.area}</label>
                    </div>
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
                    <label class="col-sm-1 control-label">土地附加成本(万元)</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="cost">${basicHouseTrading.cost}</label>
                    </div>
                    <label class="col-sm-1 control-label">交易单价（元）</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="tradingUnitPrice">${basicHouseTrading.tradingUnitPrice}</label>
                    </div>
                    <label class="col-sm-1 control-label">每亩单价（元）</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="perMuPrice">${basicHouseTrading.perMuPrice}</label>
                    </div>
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
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">限制事项</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="restrictions">${basicHouseTrading.restrictions}</label>
                    </div>

                    <label class="col-sm-1 control-label">限制事项</label>
                    <div class="col-sm-3">
                        <label class="form-control input-full"
                               name="restrictionsRemark">${basicHouseTrading.restrictionsRemark}</label>
                    </div>
                </div>
            </div>
        </div>
        <div id="houseTradingFilePart"></div>
    </form>
</div>