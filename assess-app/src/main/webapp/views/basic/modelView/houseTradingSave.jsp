
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="id">
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" placeholder="财产范围" required="required"
                   name="scopeProperty" class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">融资条件<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" placeholder="融资条件" required="required"
                   name="financingConditions" class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">税费负担<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 taxBurden" name="taxBurden"
                    required="required">
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">正常交易<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 normalTransaction"
                    name="normalTransaction"
                    required="required">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">说明事项类型<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 descriptionType"
                    name="descriptionType"
                    required="required">
            </select>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">说明事项内容<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" placeholder="说明事项内容" required="required"
                   name="descriptionContent"
                   class="form-control">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input required="required" placeholder="交易时间"
                   name="tradingTime" data-date-format="yyyy-mm-dd"
                   class="form-control date-picker dbdate tradingTime">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">交易类型<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 tradingType" name="tradingType"
                    required="required">
            </select>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">交易价格<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" placeholder="交易价格(请输入数字)" required="required" data-rule-number='true'
                   name="tradingPrice" class="form-control">
        </div>
    </div>
</div>

<div class="form-group BasicHouseTradingSell" style="display: none">
    <div class="x-valid">
        <label class="col-sm-1 control-label">买方支付的额外税费</label>
        <div class="col-sm-3">
            <input type="text" placeholder="买方支付的额外税费"
                   name="buyerExtraTaxFee" class="form-control">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">付款方式<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 paymentMethod"
                    name="paymentMethod"
                    required="required">
            </select>
        </div>
    </div>

    <div class="x-valid" style="display: none;">
        <label class="col-sm-1 control-label">出售总额</label>
        <div class="col-sm-3">
            <input type="text" placeholder="出售总额"
                   name="totalSale" class="form-control">
        </div>
    </div>
</div>

<div class="form-group BasicHouseTradingLease" style="display: none">
    <div class="x-valid">
        <label class="col-sm-1 control-label">承租方支付的额外税费</label>
        <div class="col-sm-3">
            <input type="text" placeholder="承租方支付的额外税费" name="rentingExtraTaxFee"
                   class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">押金</label>
        <div class="col-sm-3">
            <input type="text" placeholder="押金" class="form-control" name="deposit">
        </div>
    </div>
</div>

<div class="form-group" id="tableTradingLeaseAndSellDiv" style="display: none">
    <div class="x-valid">
        <div class="col-sm-1" style="text-align: right;">
            <button type="button" class="btn btn-success" data-toggle="modal" href="#divBox"
                    onclick="houseModelFun.tradingSellAndLease.show()"> 新增
            </button>
        </div>
        <div class="col-sm-11">
            <table class="table table-bordered" id="tableTradingLeaseAndSell">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">信息来源分类<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 informationType"
                    name="informationType"
                    required="required">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">信息来源<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" placeholder="信息来源" required="required" name="information"
                   class="form-control">
        </div>
    </div>
</div>