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
        </div>
    </div>
</form>
</div>