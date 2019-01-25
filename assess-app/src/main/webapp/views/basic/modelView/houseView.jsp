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
                <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" required placeholder="房号" name="houseNumber"
                           class="form-control" value="${basicHouse.houseNumber}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在楼层" name="floor" required
                           class="form-control" value="${basicHouse.floor}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层描述</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="floorDesc" value="${basicHouse.floorDesc}"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" readonly="readonly" onclick="houseCommon.selectHuxing(this);"
                               placeholder="户型" class="form-control" name="huxingName"
                               value="${basicHouse.huxingName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="houseCommon.selectHuxing(this);">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型图</label>
                <div class="col-sm-3">
                    <div id="_house_huxing_plan"></div>
                </div>
            </div>
            <div class="x-valid" id="industryUseEnvironment">
                <label class="col-sm-1 control-label">使用环境</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 useEnvironment"
                            name="useEnvironment">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">新户型</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="新户型" name="newHuxingName"
                           class="form-control" value="${basicHouse.newHuxingName}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">新户型图</label>
                <div class="col-sm-3">
                    <input id="house_new_huxing_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_new_huxing_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <div class="col-sm-3 col-sm-offset-1">
                    <div id="container"></div>
                    <div class="btn btn-success" onclick="houseCommon.orientationFun(false,0)">户型图朝向</div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 orientation" name="orientation" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="面积" name="area" data-rule-number="true" required
                           class="form-control" value="${basicHouse.area}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 certUse" name="certUse" required>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 practicalUse" name="practicalUse" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">权益限制</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                           name="rightInterestsRestriction"
                           class="form-control" value="${basicHouse.rightInterestsRestriction}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋出租占用情况描述</label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="description">${basicHouse.description}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图</label>
                <div class="col-sm-3">
                    <input id="house_img_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <input id="house_file" type="file" multiple="false">
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
                <label class="col-sm-1 control-label">交易情况</label>
                <div class="col-sm-3">
                    <select class="form-control transactionSituation" name="transactionSituation">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">财产范围</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 scopeProperty" name="scopeProperty">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">税费负担</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 taxBurden" name="taxBurden">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group" id="abnormalTransaction" style="display: none;">
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项类型</label>
                <div class="col-sm-3">
                    <select class="form-control descriptionType" name="descriptionType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项内容</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明事项内容" name="descriptionContent" class="form-control"
                           value="${basicHouseTrading.descriptionContent}">
                </div>
            </div>
        </div>
        <div class="x_title">融资条件</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">首付款比例</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="downPaymentRatio" placeholder="首付款比例">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">贷款利率</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="lendingRate" placeholder="贷款利率">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">贷款期限</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="loanPeriod" placeholder="贷款期限">
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
                    <select class="form-control tradingType" name="tradingType"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">付款方式</label>
                <div class="col-sm-3">
                    <select class="form-control paymentMethod" name="paymentMethod">
                    </select>
                </div>
            </div>
            <div class="x-valid" style="display: none;">
                <label class="col-sm-1 control-label">分期支付利率</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="分期支付利率"
                           name="installmentInterestRate" class="form-control"
                           value="${basicHouseTrading.installmentInterestRate}">
                </div>
            </div>
        </div>
        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方额外支付的税</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方额外支付的税"
                           name="buyerExtraTax" class="form-control" value="${basicHouseTrading.buyerExtraTax}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方额外支付的费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方额外支付的费"
                           name="buyerExtraFee" class="form-control" value="${basicHouseTrading.buyerExtraFee}">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方额外支付的税</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="承租方额外支付的税" name="rentingExtraTax"
                           value="${basicHouseTrading.rentingExtraTax}"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方额外支付的费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="承租方额外支付的费" name="rentingExtraFee"
                           value="${basicHouseTrading.rentingExtraFee}"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金（元）</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="押金（元）" class="form-control" name="deposit"
                           value="${basicHouseTrading.deposit}">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1" style="text-align: right;">
                    <button type="button" class="btn btn-success" data-toggle="modal"
                            onclick="houseCommon.addTradingSellAndLease()"> 新增
                    </button>
                </div>
                <div class="col-sm-11">
                    <table class="table table-bordered" id="tableTradingSell"></table>
                </div>
            </div>
        </div>
        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1" style="text-align: right;">
                    <button type="button" class="btn btn-success" data-toggle="modal"
                            onclick="houseCommon.addTradingSellAndLease()"> 新增
                    </button>
                </div>
                <div class="col-sm-11">
                    <table class="table table-bordered" id="tableTradingLease"></table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input required="required" placeholder="交易时间"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate tradingTime"
                           value="<fmt:formatDate value='${basicHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/>">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易总价（元）<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易总价（元）" class="form-control" name="tradingTotalPrice" required
                           value="${basicHouseTrading.tradingTotalPrice}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易单价（元）<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易单价（元）" class="form-control" name="tradingUnitPrice" required
                           value="${basicHouseTrading.tradingUnitPrice}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源类型</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 informationType" name="informationType">
                    </select>
                </div>
            </div>
            <div class="x-valid infomationTypeOpen" style="display: none;">
                <label class="col-sm-1 control-label">信息来源类别</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 informationCategory" name="informationCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid infomationTypeOther" style="display: none;">
                <label class="col-sm-1 control-label">姓名</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="姓名" class="form-control" name="name"
                           value="${basicHouseTrading.name}">
                </div>
            </div>
            <div class="x-valid infomationTypeOther" style="display: none;">
                <label class="col-sm-1 control-label">电话</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="电话" class="form-control" name="phone"
                           value="${basicHouseTrading.phone}">
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/views/basic/modelView/house/sonHouseView.jsp" %>
<script src="${pageContext.request.contextPath}/js/basic/huxing.select.js"></script>

<script>
</script>


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
                        <div class="col-md-12">
                            <!--lease -->
                            <div class="panel-body lease">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            租金支付时间起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="租金支付时间起"
                                                   name="rentPaymentTimeStart" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            租金支付时间止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="租金支付时间起"
                                                   name="rentPaymentTimeEnd" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            租金增长比率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
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
                                        <label class="col-sm-2 control-label">
                                            分期支付时间起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="分期支付时间起"
                                                   name="instalmentPeriodStart" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分期支付时间起止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="分期支付时间起止"
                                                   name="instalmentPeriodEnd" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分期支付利息<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
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