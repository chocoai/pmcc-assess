
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">交易类型<span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select class="form-control tradingType" name="tradingType"
                    required="required">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">付款方式<span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select class="form-control paymentMethod" name="paymentMethod" required>
            </select>
        </div>
    </div>
    <div class="x-valid" style="display: none;">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分期支付利率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="分期支付利率"
                   name="installmentInterestRate" class="form-control"
                   value="${basicHouseTrading.installmentInterestRate}">
        </div>
    </div>
</div>
<div class="form-group ExamineHouseTradingSell" style="display: none">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">买方额外支付的税</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="买方额外支付的税"
                   name="buyerExtraTax" class="form-control" value="${basicHouseTrading.buyerExtraTax}">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">买方额外支付的费</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="买方额外支付的费"
                   name="buyerExtraFee" class="form-control" value="${basicHouseTrading.buyerExtraFee}">
        </div>
    </div>
</div>

<div class="form-group ExamineHouseTradingLease" style="display: none">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">承租方额外支付的税</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="承租方额外支付的税" name="rentingExtraTax"
                   value="${basicHouseTrading.rentingExtraTax}"
                   class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">承租方额外支付的费</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="承租方额外支付的费" name="rentingExtraFee"
                   value="${basicHouseTrading.rentingExtraFee}"
                   class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">押金（元）</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="押金（元）" class="form-control" name="deposit"
                   value="${basicHouseTrading.deposit}">
        </div>
    </div>
</div>

<div class="form-group ExamineHouseTradingSell" style="display: none">
    <div class="x-valid">
        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 " style="text-align: right;">
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="houseCommon.addTradingSellAndLease()"> 新增
            </button>
        </div>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <table class="table table-bordered" id="tableTradingSell"></table>
        </div>
    </div>
</div>
<div class="form-group ExamineHouseTradingLease" style="display: none">
    <div class="x-valid">
        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 " style="text-align: right;">
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="houseCommon.addTradingSellAndLease()"> 新增
            </button>
        </div>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <table class="table table-bordered" id="tableTradingLease"></table>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">交易时间<span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input placeholder="交易时间" required="required"
                   name="tradingTime" data-date-format="yyyy-mm-dd"
                   class="form-control date-picker dbdate tradingTime"
                   value="<fmt:formatDate value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/>">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">交易总价（元）${empty surveyCaseStudy?"":'<span class="symbol required"></span>'} </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="交易总价（元）" class="form-control" name="tradingTotalPrice"
            ${empty surveyCaseStudy?"":'required'} value="${basicHouseTrading.tradingTotalPrice}">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单价内涵<span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select class="form-control search-select select2 priceConnotation" name="priceConnotation" required>
            </select>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">交易单价（元）${empty surveyCaseStudy?"":'<span class="symbol required"></span>'} </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="交易单价（元）" class="form-control" name="tradingUnitPrice" required
            ${empty surveyCaseStudy?"":'required'} value="${basicHouseTrading.tradingUnitPrice}">
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地买售人</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="土地买售人" class="form-control" name="landBuyerSeller" value="${basicHouseTrading.landBuyerSeller}">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">信息来源类型</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select class="form-control search-select select2 informationType" name="informationType">
            </select>
        </div>
    </div>
    <div class="x-valid infomationTypeOpen" style="display: none;">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">信息来源类别</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select class="form-control search-select select2 informationCategory" name="informationCategory">
            </select>
        </div>
    </div>
    <div class="x-valid infomationTypeOther" style="display: none;">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">姓名</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="姓名" class="form-control" name="name"
                   value="${basicHouseTrading.name}">
        </div>
    </div>
    <div class="x-valid infomationTypeOther" style="display: none;">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">电话</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="电话" class="form-control" name="phone"
                   value="${basicHouseTrading.phone}">
        </div>
    </div>
</div>

<div id="divBoxTradingLeaseAndSell" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"></h3>
            </div>
            <form id="frmTradingLeaseAndSell" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <!--lease -->
                            <div class="panel-body lease">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            租金支付时间起<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input required="required" placeholder="租金支付时间起"
                                                   name="rentPaymentTimeStart" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            租金支付时间止<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input required="required" placeholder="租金支付时间起"
                                                   name="rentPaymentTimeEnd" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            租金增长比率<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="rentGrowthRate"
                                                   placeholder="租金增长比率(请输入数字)" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--sell -->
                            <div class="panel-body sell">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            分期支付时间起<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input required="required" placeholder="分期支付时间起"
                                                   name="instalmentPeriodStart" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            分期支付时间起止<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input required="required" placeholder="分期支付时间起止"
                                                   name="instalmentPeriodEnd" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            分期支付利息<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="instalmentInterest"
                                                   placeholder="分期支付利息(请输入数字)" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseCommon.saveTradingSellAndLease()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
