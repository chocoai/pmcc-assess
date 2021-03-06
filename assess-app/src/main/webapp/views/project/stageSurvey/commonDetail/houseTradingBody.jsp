<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                <label class="form-control input-full" name="scopeInclude">${basicHouseTrading.scopeInclude}</label>
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
                <label class="form-control input-full" name="taxBurdenName">${basicHouseTrading.taxBurdenName}</label>
            </div>
            <label class="col-sm-1 control-label">交易情况</label>
            <div class="col-sm-3">
                <label class="form-control input-full"
                       name="transactionSituationName">${basicHouseTrading.transactionSituationName}</label>
            </div>
            <label class="col-sm-1 control-label">价格类型</label>
            <div class="col-sm-3">
                <label class="form-control input-full" name="priceTypeName">${basicHouseTrading.priceTypeName}</label>
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
<div class="x_title">融资条件</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">首付款比例</label>
            <div class="col-sm-3">
                <label class="form-control input-full"
                       name="downPaymentRatio">${basicHouseTrading.downPaymentRatio}</label>
            </div>
            <label class="col-sm-1 control-label">贷款利率</label>
            <div class="col-sm-3">
                <label class="form-control input-full" name="lendingRate">${basicHouseTrading.lendingRate}</label>
            </div>
            <label class="col-sm-1 control-label">贷款期限</label>
            <div class="col-sm-3">
                <label class="form-control input-full" name="loanPeriod">${basicHouseTrading.loanPeriod}</label>
            </div>
        </div>
    </div>
</div>
<div class="x_title">
    <div class="clearfix"></div>
</div>
<c:if test="${basicHouseTrading.tradingTypeName=='出售'}">
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
            <table class="table table-bordered" id="tableTradingSell"></table>

        </div>
    </div>
</c:if>


<c:if test="${basicHouseTrading.tradingTypeName != '出售'}">
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
            </div>
        </div>
    </div>
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
                    <label class="form-control input-full" name="deposit">${basicHouseTrading.deposit}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <table class="table table-bordered" id="tableTradingLease"></table>
        </div>
    </div>
</c:if>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">交易时间</label>
            <div class="col-sm-3">
                <label class="form-control input-full dbdate" name="tradingTime"><fmt:formatDate
                        value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/></label>
            </div>
            <label class="col-sm-1 control-label">单价内涵</label>
            <div class="col-sm-3">
                <label class="form-control input-full"
                       name="priceConnotationName">${basicHouseTrading.priceConnotationName}</label>
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
            <label class="col-sm-1 control-label">土地买售人</label>
            <div class="col-sm-3">
                <label class="form-control input-full"
                       name="landBuyerSeller">${basicHouseTrading.landBuyerSeller}</label>
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
                    <label class="form-control input-full" name="name">${basicHouseTrading.name}</label>
                </div>

                <label class="col-sm-1 control-label">电话</label>
                <div class="col-sm-3">
                    <label class="form-control input-full" name="phone">${basicHouseTrading.phone}</label>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">
                交易附件
            </label>
            <div class="col-sm-3">
                <div id="_house_trading_file_f"></div>
            </div>
        </div>
    </div>
</div>
