<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房屋基本信息
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
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层描述</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.floorDesc}</label>
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
            <div class="x-valid" id="industryUseEnvironment">
                <label class="col-sm-1 control-label">使用环境</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.useEnvironmentName}</label>
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
            <div class="x-valid">
                <div class="col-sm-3 col-sm-offset-1">
                    <div id="container"></div>
                    <div class="btn btn-success" onclick="houseCommon.orientationFun(true);">户型地图朝向</div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.orientationName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">面积</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicHouse.area}</label>
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
                <label class="col-sm-1 control-label">房屋出租占用情况描述</label>
                <div class="col-sm-11">
                    <label class="form-control">${basicHouse.description}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图</label>
                <div class="col-sm-5">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋装饰图</label>
                <div class="col-sm-5">
                    <div id="_house_decorate"></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <div id="_house_file"></div>
                </div>
            </div>
        </div>
    </form>
</div>

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

        <div class="form-group">
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
        <div class="form-group">
            <c:if test="${isHouseAbnormal eq true}">
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
            <c:if test="${isHouseInstallment eq true}">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">分期支付利率</label>
                    <div class="col-sm-3">
                        <label class="form-control"
                               name="installmentInterestRate">${basicHouseTrading.installmentInterestRate}</label>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${isHouseSell eq true}">
            <div class="form-group ExamineHouseTradingSell">
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
        </c:if>

        <c:if test="${isHouseLease eq true}">
            <div class="form-group ExamineHouseTradingLease">
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
        </c:if>

        <c:if test="${isHouseSell eq true}">
            <div class="form-group ExamineHouseTradingSell">
                <div class="x-valid">
                    <div class="col-sm-1" style="text-align: right;">
                    </div>
                    <div class="col-sm-11">
                        <table class="table table-bordered" id="tableTradingSell"></table>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${isHouseLease eq true}">
            <div class="form-group ExamineHouseTradingLease">
                <div class="x-valid">
                    <div class="col-sm-1" style="text-align: right;">
                    </div>
                    <div class="col-sm-11">
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
                    <label class="form-control" name="tradingTotalPrice">${basicHouseTrading.tradingTotalPrice}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">单价内涵</label>
                <div class="col-sm-3">
                    <label class="form-control"
                           name="priceConnotationName">${basicHouseTrading.priceConnotationName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易单价（元）</label>
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
            <c:choose>
                <c:when test="${isHouseInfomationOpen eq true}">
                    <div class="x-valid infomationTypeOpen">
                        <label class="col-sm-1 control-label">信息来源类别</label>
                        <div class="col-sm-3">
                            <label class="form-control"
                                   name="informationCategoryName">${basicHouseTrading.informationCategoryName}</label>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="x-valid infomationTypeOther">
                        <label class="col-sm-1 control-label">姓名</label>
                        <div class="col-sm-3">
                            <label class="form-control" name="name">${basicHouseTrading.name}</label>
                        </div>
                    </div>
                    <div class="x-valid infomationTypeOther">
                        <label class="col-sm-1 control-label">电话</label>
                        <div class="col-sm-3">
                            <label class="form-control" name="phone">${basicHouseTrading.phone}</label>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/house/sonHouseDetail.jsp" %>