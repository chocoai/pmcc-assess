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
                <label class="col-sm-1 control-label">房号</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.houseNumber}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在楼层</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.floor}</label>
                </div>
            </div>
            <div class="x-valid" id="industryUseEnvironment">
                <label class="col-sm-1 control-label">使用环境</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.useEnvironmentName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.huxingName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型图</label>
                <div class="col-sm-3" id="_house_huxing_plan">

                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.orientation}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">新户型</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.newHuxingName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">新户型图</label>
                <div class="col-sm-3" id="_house_new_huxing_plan">

                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载用途</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.certUseName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">实际用途</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.practicalUseName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">权益限制</label>
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
                <label class="col-sm-1 control-label">房屋平面图</label>
                <div class="col-sm-3">
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
                <label class="col-sm-1 control-label">财产范围</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.scopePropertyName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">融资条件</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.financingConditionsName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">税费负担</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.taxBurdenName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易情况</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.normalTransactionName}</label>
                </div>
            </div>
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

        <div class="form-group">

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易类型</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.tradingTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源分类</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.informationTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.informationName}</label>
                </div>
            </div>

        </div>

        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.buyerExtraTaxFee}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">付款方式</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.paymentMethodName}</label>
                </div>
            </div>

            <div class="x-valid" style="display: none;">
                <label class="col-sm-1 control-label">出售总额</label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" class="form-control" name="totalSale" value="${basicHouseTrading.totalSale}">
                </div>
            </div>
            <div class="x-valid" style="display: none;">
                <label class="col-sm-1 control-label">分期支付利率</label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" class="form-control" name="installmentInterestRate" value="${basicHouseTrading.installmentInterestRate}">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingLease" style="display: none">
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
                <label class="col-sm-1 control-label">交易时间</label>
                <div class="col-sm-3">
                    <label class="form-control"><fmt:formatDate value="${basicHouseTrading.tradingTime}" pattern="yyyy-MM-dd"></fmt:formatDate> </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易总价</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.tradingTotalPrice}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易单价</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.tradingUnitPrice}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">电话</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.phone}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">姓名</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouseTrading.name}</label>
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/house/sonHouseDetail.jsp" %>
<script src="${pageContext.request.contextPath}/js/basic/house/house.common.js"></script>