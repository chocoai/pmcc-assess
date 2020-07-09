<%--
 查勘交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
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
                    <input type="text" placeholder="范围包含"
                           name="scopeInclude"
                           class="form-control input-full"
                           required
                           value="${basicHouseTrading.scopeInclude}">
                </div>
                <label class="col-sm-1">范围不包括<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="范围不包含"
                           name="scopeNotInclude"
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
                    <select class="form-control input-full "
                            name="priceType"
                            required>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid"
                 id="abnormalTransaction"
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
                    <input type="text"
                           class="form-control input-full"
                           name="downPaymentRatio"
                           placeholder="首付款比例"
                           required>
                </div>
                <label class="col-sm-1">贷款利率<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           class="form-control input-full"
                           name="lendingRate" placeholder="贷款利率"
                           required>
                </div>
                <label class="col-sm-1">贷款期限<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           class="form-control input-full"
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


    <div class="row form-group ExamineHouseTradingSell"
         style="display: none">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">买方额外支付的税</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方额外支付的税"
                           name="buyerExtraTax"
                           class="form-control input-full"
                           value="${basicHouseTrading.buyerExtraTax}">
                </div>
                <label class="col-sm-1">买方额外支付的费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方额外支付的费"
                           name="buyerExtraFee"
                           class="form-control input-full"
                           value="${basicHouseTrading.buyerExtraFee}">
                </div>
            </div>
        </div>
    </div>

    <div class="row form-group ExamineHouseTradingLease"
         style="display: none">
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
                           class="form-control input-full"
                           name="deposit"
                           value="${basicHouseTrading.deposit}">
                </div>
            </div>
        </div>
    </div>

    <div class="row form-group ExamineHouseTradingSell"
         style="display: none">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <div class="col-sm-1" style="text-align: right;">
                    <button type="button"
                            class="btn btn-success btn-sm"
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
    <div class="row form-group ExamineHouseTradingLease"
         style="display: none">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <div class="col-sm-1" style="text-align: right;">
                    <button type="button"
                            class="btn btn-success btn-sm"
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
                <label class="col-sm-1">面积<span
                        class="symbol required"></span> </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="面积" data-rule-number="true"
                           class="form-control input-full"
                           id="tempLandArea" required
                           onblur="houseCommon.computeUnitPrice(this);"
                           value="${basicHouse.area}">
                </div>
                <label class="col-sm-1">交易时间<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="交易时间" required="required"
                           name="tradingTime"
                           data-date-format="yyyy-mm-dd"
                           class="form-control input-full date-picker dbdate tradingTime"
                           value="<fmt:formatDate value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/>">
                </div>
                <label class="col-sm-1">交易总价（元）<span
                        class="symbol required"></span> </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易总价（元）" data-rule-number="true"
                           class="form-control input-full"
                           name="tradingTotalPrice" required
                           onblur="houseCommon.computeUnitPrice(this);"
                           value="${basicHouseTrading.tradingTotalPrice}">
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">土地附加成本(万元)</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地附加成本"
                           class="form-control input-full" data-rule-number="true"
                           name="cost"
                           value="${basicHouseTrading.cost}">
                </div>
                <label class="col-sm-1">交易单价（元）<span
                        class="symbol required"></span> </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易单价（元）"
                           class="form-control input-full" data-rule-number="true"
                           name="tradingUnitPrice" onblur="houseCommon.computePerMuPrice();"
                           required
                           value="${basicHouseTrading.tradingUnitPrice}">
                </div>
                <label class="col-sm-1">每亩单价（元）<span
                        class="symbol required"></span> </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="每亩单价（元）"
                           class="form-control input-full"
                           name="perMuPrice"
                           required data-rule-number="true"
                           value="${basicHouseTrading.perMuPrice}">
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
                <label class="col-sm-1 infomationTypeOpen"
                       style="display: none;">信息来源类别</label>
                <div class="col-sm-3 infomationTypeOpen"
                     style="display: none;">
                    <select class="form-control input-full search-select select2 informationCategory"
                            name="informationCategory">
                    </select>
                </div>


                <label class="col-sm-1 infomationTypeOther"
                       style="display: none;">姓名<span
                        class="symbol required"></span></label>
                <div class="col-sm-3 infomationTypeOther"
                     style="display: none;">
                    <input type="text" placeholder="姓名"
                           class="form-control input-full"
                           name="name" required
                           value="${basicHouseTrading.name}">
                </div>


                <label class="col-sm-1 infomationTypeOther"
                       style="display: none;">电话<span
                        class="symbol required"></span></label>
                <div class="col-sm-3 infomationTypeOther"
                     style="display: none;">
                    <input type="text" placeholder="电话"
                           class="form-control input-full"
                           name="phone" required
                           value="${basicHouseTrading.phone}">
                </div>
            </div>
        </div>
    </div>
    <div id="houseTradingFilePart"></div>
</form>
</div>