
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">财产范围</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.scopePropertyName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">范围包括</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.scopeInclude}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">范围不包括</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.scopeNotInclude}</label>
        </div>
    </div>
</div>
<div class="form-group" >
    <div class="x-valid">
        <label class="col-sm-1 control-label">税费负担</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.taxBurdenName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">交易情况</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.transactionSituationName}</label>
        </div>
    </div>
</div>
<div class="form-group" >
    <c:if test="${!empty basicHouseTrading.descriptionTypeName}">
        <div class="x-valid">
            <label class="col-sm-1 control-label">说明事项类型</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.descriptionTypeName}</label>
            </div>
        </div>
    </c:if>
    <c:if test="${!empty basicHouseTrading.descriptionContent}">
        <div class="x-valid">
            <label class="col-sm-1 control-label">说明事项内容</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.descriptionContent}</label>
            </div>
        </div>
    </c:if>
</div>
<div class="x_title">融资条件</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">首付款比例</label>
        <div class="col-sm-3">
            <label class="form-control" name="downPaymentRatio">${basicHouseTrading.downPaymentRatio}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">贷款利率</label>
        <div class="col-sm-3">
            <label class="form-control" name="lendingRate">${basicHouseTrading.lendingRate}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">贷款期限</label>
        <div class="col-sm-3">
            <label class="form-control" name="loanPeriod">${basicHouseTrading.loanPeriod}</label>
        </div>
    </div>
</div>
<div class="x_title">
    <div class="clearfix"></div>
</div>
<c:if test="${basicHouseTrading.tradingTypeName=='出售'}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">交易类型</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.tradingTypeName}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">付款方式</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.paymentMethodName}</label>
            </div>
        </div>
        <c:if test="${!empty basicHouseTrading.installmentInterestRate}">
            <div class="x-valid">
                <label class="col-sm-1 control-label">分期支付利率</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.installmentInterestRate}</label>
                </div>
            </div>
        </c:if>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">买方额外支付的税</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.buyerExtraTax}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">买方额外支付的费</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.buyerExtraFee}</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <div class="col-sm-12">
                <table class="table table-bordered" id="tableTradingSell"></table>
            </div>
        </div>
    </div>
</c:if>


<c:if test="${basicHouseTrading.tradingTypeName != '出售'}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">交易类型</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.tradingTypeName}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">付款方式</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.paymentMethodName}</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">承租方额外支付的税</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.rentingExtraTax}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">承租方额外支付的费</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.rentingExtraFee}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">押金（元）</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.deposit}</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <div class="col-sm-12">
                <table class="table table-bordered" id="tableTradingLease"></table>
            </div>
        </div>
    </div>
</c:if>



<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">交易时间</label>
        <div class="col-sm-3">
            <label class="form-control dbdate" name="tradingTime"><fmt:formatDate
                    value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/></label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">交易总价（元）</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.tradingTotalPrice}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">单价内涵</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.priceConnotationName}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">交易单价（元）</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.tradingUnitPrice}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">土地买售人</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.landBuyerSeller}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">信息来源类型</label>
        <div class="col-sm-3">
            <label class="form-control">${basicHouseTrading.informationTypeName}</label>
        </div>
    </div>
    <c:if test="${basicHouseTrading.informationTypeName == '公开信息'}">
        <div class="x-valid">
            <label class="col-sm-1 control-label">信息来源类别</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.informationCategoryName}</label>
            </div>
        </div>
    </c:if>
    <c:if test="${basicHouseTrading.informationTypeName != '公开信息'}">
        <div class="x-valid">
            <label class="col-sm-1 control-label">姓名</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.name}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">电话</label>
            <div class="col-sm-3">
                <label class="form-control">${basicHouseTrading.phone}</label>
            </div>
        </div>
    </c:if>
</div>