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
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="房号" name="houseNumber"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在楼层</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="所在楼层(请输入数字)" name="floor"
                           class="form-control">
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

            <div class="x-valid" id="industryNewsHuxing">
                <label class="col-sm-1 control-label">最新户型</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 newsHuxing"
                            name="newsHuxing">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型选择</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <select class="form-control huxingId" name="huxingId">
                        </select>
                        <label class="input-group-addon btn" onclick="houseModelFun.unitHuxingSelectLoad(this)">刷新户型<i
                                class="fa fa-refresh"></i></label>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">户型图</label>
                <div class="col-sm-3">
                    <div class="house_latest_family_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="朝向" name="orientation" readonly="readonly"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载用途</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 certUse" name="certUse">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">实际用途</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 practicalUse" name="practicalUse">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">权益限制</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                           name="rightInterestsRestriction"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋出租占用情况途描述</label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="description"></textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
                <div class="col-sm-5">
                    <input id="house_img_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
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
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 scopeProperty" name="scopeProperty"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">融资条件<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 financingConditions" name="financingConditions"
                            required="required">
                    </select>
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
                <label class="col-sm-1 control-label">交易类型<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 tradingType" name="tradingType"
                            required="required">
                    </select>
                </div>
            </div>
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
                    <select class="form-control search-select select2 information"
                            name="information" required="required">
                    </select>
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

            <div class="x-valid" style="display: none;">
                <label class="col-sm-1 control-label">分期支付利率</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="分期支付利率"
                           name="installmentInterestRate" class="form-control">
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
                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input required="required" placeholder="交易时间"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate tradingTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易总价</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易总价" class="form-control" name="tradingTotalPrice">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易单价</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易单价" class="form-control" name="tradingUnitPrice">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">电话</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="电话" class="form-control" name="phone">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">姓名</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="姓名" class="form-control" name="name">
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/views/basic/modelView/house/sonHouseView.jsp" %>
<script src="${pageContext.request.contextPath}/js/basic/house/houseView.js"></script>

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
                    <button type="button" class="btn btn-primary" onclick="houseModelFun.tradingSellAndLease.save()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>