<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 房屋交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房屋交易信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicTradingFrm">
        <input type="hidden" name="id" value="${basicHouseTrading.id}">
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
            <c:if test="${basicHouseTrading.transactionSituationName ne '正常'}">
                <div>
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">说明事项类型</label>
                        <div class="col-sm-3">
                            <label class="form-control">${basicHouseTrading.descriptionTypeName}</label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">说明事项内容</label>
                        <div class="col-sm-3">
                            <label class="form-control">${basicHouseTrading.descriptionContent}</label>
                        </div>
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
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易类型<span class="symbol required"></span></label>
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
            <div class="x-valid" style="display: none;">
                <label class="col-sm-1 control-label">分期支付利率</label>
                <div class="col-sm-3">
                    <label class="form-control"
                           name="installmentInterestRate">${basicHouseTrading.installmentInterestRate}</label>
                </div>
            </div>
        </div>
        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方额外支付的税</label>
                <div class="col-sm-3">
                    <label class="form-control" name="buyerExtraTax">${basicHouseTrading.buyerExtraTax}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方额外支付的费</label>
                <div class="col-sm-3">
                    <label class="form-control" name="buyerExtraFee">${basicHouseTrading.buyerExtraFee}</label>
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方额外支付的税</label>
                <div class="col-sm-3">
                    <label class="form-control" name="rentingExtraTax">${basicHouseTrading.rentingExtraTax}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方额外支付的费</label>
                <div class="col-sm-3">
                    <label class="form-control" name="rentingExtraFee">${basicHouseTrading.rentingExtraFee}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金（元）</label>
                <div class="col-sm-3">
                    <label class="form-control" name="deposit">${basicHouseTrading.deposit}</label>
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <div class="col-sm-11">
                    <table class="table table-bordered" id="tableTradingSell"></table>
                </div>
            </div>
        </div>
        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <div class="col-sm-11">
                    <table class="table table-bordered" id="tableTradingLease"></table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control dbdate" name="tradingTime"><fmt:formatDate
                            value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易总价（元）<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="tradingTotalPrice">${basicHouseTrading.tradingTotalPrice}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易单价（元）<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="tradingUnitPrice">${basicHouseTrading.tradingUnitPrice}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源类型</label>
                <div class="col-sm-3">
                    <label class="form-control"
                           name="informationTypeName">${basicHouseTrading.informationTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">姓名</label>
                <div class="col-sm-3">
                    <label class="form-control" name="name">${basicHouseTrading.name}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">电话</label>
                <div class="col-sm-3">
                    <label class="form-control" name="phone">${basicHouseTrading.phone}</label>
                </div>
            </div>
        </div>
    </form>
</div>


