<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">交易类型<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control input-full tradingType" name="tradingType"
                        required="required">
                </select>
            </div>
            <label class="col-sm-1 control-label">付款方式<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control input-full paymentMethod" name="paymentMethod" required>
                </select>
            </div>
            <div style="display: none;">
                <label class="col-sm-1 control-label">分期支付利率</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="分期支付利率"
                           name="installmentInterestRate" class="form-control input-full"
                           value="${basicHouseTrading.installmentInterestRate}">
                </div>
            </div>
        </div>

    </div>
</div>
<div class="row form-group ExamineHouseTradingSell" style="display: none">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">买方额外支付的税</label>
            <div class="col-sm-3">
                <input type="text" placeholder="买方额外支付的税"
                       name="buyerExtraTax" class="form-control input-full" value="${basicHouseTrading.buyerExtraTax}">
            </div>
            <label class="col-sm-1 control-label">买方额外支付的费</label>
            <div class="col-sm-3">
                <input type="text" placeholder="买方额外支付的费"
                       name="buyerExtraFee" class="form-control input-full" value="${basicHouseTrading.buyerExtraFee}">
            </div>
        </div>
    </div>
</div>

<div class="row form-group ExamineHouseTradingLease" style="display: none">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">承租方额外支付的税</label>
            <div class="col-sm-3">
                <input type="text" placeholder="承租方额外支付的税" name="rentingExtraTax"
                       value="${basicHouseTrading.rentingExtraTax}"
                       class="form-control input-full">
            </div>
            <label class="col-sm-1 control-label">承租方额外支付的费</label>
            <div class="col-sm-3">
                <input type="text" placeholder="承租方额外支付的费" name="rentingExtraFee"
                       value="${basicHouseTrading.rentingExtraFee}"
                       class="form-control input-full">
            </div>
            <label class="col-sm-1 control-label">押金（元）</label>
            <div class="col-sm-3">
                <input type="text" placeholder="押金（元）" class="form-control input-full" name="deposit"
                       value="${basicHouseTrading.deposit}">
            </div>
        </div>
    </div>
</div>

<div class="row form-group ExamineHouseTradingSell" style="display: none">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <div class="col-sm-1" style="text-align: right;">
                <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                        onclick="houseCommon.addTradingSellAndLease()"> <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                </button>
            </div>
            <div class="col-sm-11">
                <table class="table table-bordered" id="tableTradingSell"></table>
            </div>
        </div>
    </div>
</div>
<div class="row form-group ExamineHouseTradingLease" style="display: none">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <div class="col-sm-1" style="text-align: right;">
                <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                        onclick="houseCommon.addTradingSellAndLease()"> <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                </button>
            </div>
            <div class="col-sm-11">
                <table class="table table-bordered" id="tableTradingLease"></table>
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input placeholder="交易时间" required="required"
                       name="tradingTime" data-date-format="yyyy-mm-dd"
                       class="form-control input-full date-picker dbdate tradingTime"
                       value="<fmt:formatDate value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/>">
            </div>
            <label class="col-sm-1 control-label">交易总价（元）${empty surveyCaseStudy?"":'<span class="symbol required"></span>'} </label>
            <div class="col-sm-3">
                <input type="text" placeholder="交易总价（元）" class="form-control input-full" name="tradingTotalPrice"
                       onblur="houseCommon.computeUnitPrice(this);"
                ${empty surveyCaseStudy?"":'required'} value="${basicHouseTrading.tradingTotalPrice}">
            </div>
            <label class="col-sm-1 control-label">单价内涵<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control input-full search-select select2 priceConnotation" name="priceConnotation"
                        required>
                </select>
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">交易单价（元）${empty surveyCaseStudy?"":'<span class="symbol required"></span>'} </label>
            <div class="col-sm-3">
                <input type="text" placeholder="交易单价（元）" class="form-control input-full" name="tradingUnitPrice"
                       required
                ${empty surveyCaseStudy?"":'required'} value="${basicHouseTrading.tradingUnitPrice}">
            </div>
            <label class="col-sm-1 control-label">土地买售人</label>
            <div class="col-sm-3">
                <input type="text" placeholder="土地买售人" class="form-control input-full" name="landBuyerSeller"
                       value="${basicHouseTrading.landBuyerSeller}">
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">信息来源类型</label>
            <div class="col-sm-3">
                <select class="form-control input-full search-select select2 informationType" name="informationType">
                </select>
            </div>
            <label class="col-sm-1 control-label infomationTypeOpen" style="display: none;">信息来源类别</label>
            <div class="col-sm-3 infomationTypeOpen" style="display: none;">
                <select class="form-control input-full search-select select2 informationCategory"
                        name="informationCategory">
                </select>
            </div>
            <label class="col-sm-1 control-label infomationTypeOther" style="display: none;">姓名</label>
            <div class="col-sm-3 infomationTypeOther" style="display: none;">
                <input type="text" placeholder="姓名" class="form-control input-full" name="name"
                       value="${basicHouseTrading.name}">
            </div>
            <label class="col-sm-1 control-label infomationTypeOther" style="display: none;">电话</label>
            <div class="col-sm-3 infomationTypeOther" style="display: none;">
                <input type="text" placeholder="电话" class="form-control input-full" name="phone"
                       value="${basicHouseTrading.phone}">
            </div>
        </div>
    </div>
</div>
<div id="houseTradingFilePart"></div>

