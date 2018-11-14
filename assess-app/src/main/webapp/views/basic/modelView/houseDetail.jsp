<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                房屋基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicHouseFrm">
        <input type="hidden" name="id" value="${basicHouse.id}">

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.houseNumber}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在楼层<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.floor}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">使用环境<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.useEnvironmentName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型选择<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control">${basicHouse.huxingName}</label>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <div class="col-sm-3 house_latest_family_plan">

                </div>
            </div>


            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.orientation}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载用途<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.certUseName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">实际用途<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.practicalUseName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">权益限制<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.rightInterestsRestriction}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋出租占用情况途描述</label>
                <div class="col-sm-11">
                    <label class="form-control">${basicHouse.description}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图<span
                        class="symbol required"></span></label>
                <div class="col-sm-5">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                房屋交易信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicTradingFrm">
        <input type="hidden" name="id" value="${basicHouseTrading.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">财产范围<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.scopeProperty}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">融资条件<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.financingConditions}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">税费负担<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.taxBurdenName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">正常交易<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.normalTransactionName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.descriptionTypeName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项内容<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.descriptionContent}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易时间<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.tradingTimeName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易类型</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.tradingTypeName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易价格<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.tradingPrice}</label>
                </div>
            </div>
        </div>

        <div class="form-group BasicHouseTradingSell" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.buyerExtraTaxFee}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">付款方式<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.paymentMethodName}</label>
                </div>
            </div>

            <div class="x-valid" style="display: none;">
                <label class="col-sm-1 control-label">出售总额</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.totalSale}</label>
                </div>
            </div>
        </div>

        <div class="form-group BasicHouseTradingLease" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.rentingExtraTaxFee}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.deposit}</label>
                </div>
            </div>
        </div>

        <div class="form-group" id="tableTradingLeaseAndSellDiv" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1" style="text-align: right;">
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
                    <label class="form-control">${basicHouseTrading.informationTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.information}</label>
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/house/sonHouseApprovalView.jsp" %>