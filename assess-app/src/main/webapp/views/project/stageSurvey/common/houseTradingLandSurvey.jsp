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

                <label class="col-sm-1">说明事项类型</label>
                <div class="col-sm-3">
                    <select class="form-control input-full descriptionType"
                            name="descriptionType">
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">说明事项内容</label>
                <div class="col-sm-11">
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
                <label class="col-sm-1">市场级别</label>
                <div class="col-sm-3">
                    <select class="form-control input-full "
                            name="transactionLevel" >
                    </select>
                </div>
                <label class="col-sm-1">交易类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control input-full tradingType"
                            name="tradingType"
                            required="required">
                    </select>
                </div>
                <label class="col-sm-1">税费负担<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control input-full search-select select2 taxBurden"
                            name="taxBurden"
                            required>
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
                <label class="col-sm-1">付款方式<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control input-full paymentMethod"
                            name="paymentMethod" required>
                    </select>
                </div>
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
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">

                <label class="col-sm-1 ">限制事项</label>
                <div class="col-sm-3 ">
                    <input type="text" placeholder="限制事项"
                           class="form-control input-full"
                           name="restrictions" >
                </div>

                <label class="col-sm-1">限制说明</label>
                <div class="col-sm-3"><input type="text" placeholder="限制说明" name="restrictionsRemark" value="" class="form-control input-full"></div>

                <div class="col-sm-3 ">
                    <button class="btn btn-sm btn-success" type="button" onclick="houseCommon.appendRestrictionsHTML(this );"><i class="fa fa-plus"></i></button>
                </div>
            </div>
        </div>
    </div>


    <div id="houseTradingFilePart"></div>
</form>
</div>
<div id="divBoxTradingLeaseAndSell" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">&times;</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTradingLeaseAndSell" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="tradingId">
                    <input type="hidden" name="type" class="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <!--lease -->
                                <div class="lease">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    租金支付时间起<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="租金支付时间起"
                                                           name="rentPaymentTimeStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-2">
                                                    租金支付时间止<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="租金支付时间起"
                                                           name="rentPaymentTimeEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    租金增长比率<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="number" data-rule-number='true'
                                                           class="form-control input-full"
                                                           name="rentGrowthRate"
                                                           placeholder="租金增长比率(请输入数字)" required="required">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--sell -->
                                <div class="sell">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    分期支付时间起<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="分期支付时间起"
                                                           name="instalmentPeriodStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-2">
                                                    分期支付时间起止<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="分期支付时间起止"
                                                           name="instalmentPeriodEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    分期支付利息<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="number" data-rule-number='true'
                                                           class="form-control input-full"
                                                           name="instalmentInterest"
                                                           placeholder="分期支付利息(请输入数字)" required="required">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="houseLandTrading .saveTradingSellAndLease(this)">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<script type="application/javascript">
    var houseLandTrading = {
        basicHouseTradingFrm: "basicHouseTradingFrm",
        basicHouseLandTradingAppend: "basicHouseLandTradingAppend",
        basicHouseTradingDiv: "basicHouseTradingDiv",
        houseTradingFilePart: "houseTradingFilePart",

    };


    /**
     * 添加html,并且替换
     * @returns {*|jQuery}
     */
    houseLandTrading.appendHtml = function (flag) {
        if (flag) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouseTrading/basicHouseTradingList",
                type: "get",
                dataType: "json",
                data: {
                    houseId: '${basicHouse.id}',
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                var html = $("#" + houseLandTrading.basicHouseTradingDiv).html();
                                var number = item.id;
                                html = html.replace(/_number/g, number).replace(/{index}/g, i + 1);
                                $("#" + houseLandTrading.basicHouseLandTradingAppend).append(html);
                                houseLandTrading.initBasicHouseTradingForm(item, number);
                                DatepickerUtils.parse();
                            });
                        } else {
                            flag = false;
                        }
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        if (!flag) {
            var data = {};
            data.houseId = '${basicHouse.id}'
            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouseTrading/saveAndUpdateBasicHouseTrading",
                type: "post",
                dataType: "json",
                data: {
                    formData: JSON.stringify(data),
                },
                success: function (result) {
                    if (result.ret) {
                        var html = $("#" + houseLandTrading.basicHouseTradingDiv).html();
                        var index = $("#" + houseLandTrading.basicHouseLandTradingAppend).find('.form-horizontal').length;
                        var number = result.data.id;
                        html = html.replace(/_number/g, number).replace(/{index}/g, index + 1);
                        $("#" + houseLandTrading.basicHouseLandTradingAppend).append(html);
                        houseLandTrading.initBasicHouseTradingForm(result.data, number);
                        DatepickerUtils.parse();
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        }

    }

    //交易情况
    houseLandTrading.initBasicHouseTradingForm = function (data, number) {
        console.log("test2") ;
        var houseTradingFormId = houseLandTrading.basicHouseTradingFrm + number;
        var target = $("#" + houseTradingFormId) ;
        houseLandTrading.changeEvent(data, number);
        if (!data ) {
            return false ;
        }
        target.initForm(data);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionSituation, data.transactionSituation, function (html, data) {
            target.find("select.transactionSituation").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, data.scopeProperty, function (html, data) {
            target.find("select.scopeProperty").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, data.taxBurden, function (html, data) {
            target.find("select.taxBurden").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, data.descriptionType, function (html, data) {
            target.find("select.descriptionType").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.tradingType, function (html, data) {
            target.find("select.tradingType").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionTransactionLevel, data.transactionLevel, function (html, data) {
            target.find("select[name=transactionLevel]").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseFinancingConditions, data.financingConditions, function (html, data) {
            target.find("select.financingConditions").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceType, data.informationType, function (html, data) {
            target.find("select.informationType").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceCategory, data.informationCategory, function (html, data) {
            target.find("select.informationCategory").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePriceConnotation, data.priceConnotation, function (html, data) {
            target.find("select.priceConnotation").empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouse_transaction_price_type, data.priceType, function (html, data) {
            target.find("select.priceType").empty().html(html).trigger('change');
        }, false);
        houseLandTrading.showPriceConnotationUnit(data, number);
        var fileId = "houseTradingFilePart" + number;
        houseLandTrading.getFilePartHtml(fileId, number);
        //初始化上传控件
        $.each(houseLandTrading.houseFileControlIdArray, function (i, item) {
            houseLandTrading.fileUpload(item, number);
            houseLandTrading.fileShow(item, number, true);
        });
        target.closest('.col-md-12').find("input[name='bisMark']").prop("checked", data.bisMark);
        houseCommon.loadRestrictionsHTML(data,target) ;
    };


    //下拉框change事件
    houseLandTrading.changeEvent = function (basicHouseTrading, number) {
        var houseTradingFormId = houseLandTrading.basicHouseTradingFrm + number;
        $("#" + houseTradingFormId).find("select.transactionSituation").off('change').on('change', function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHouseTransactionAbnormal) {
                $("#" + houseTradingFormId).find(".abnormalTransaction").show();
            } else {
                $("#" + houseTradingFormId).find(".abnormalTransaction").hide();
            }
        });

        $("#" + houseTradingFormId).find('[name=tradingType]').off('change').on('change', function () {
            var key = $(this).find("option:selected").attr('key');
            var paymentMethod = basicHouseTrading == null ? null : basicHouseTrading.paymentMethod;
            if (key == AssessDicKey.examineHouseTransactionTypeSell) {
                $("#" + houseTradingFormId).find('.ExamineHouseTradingSell').show();
                $("#" + houseTradingFormId).find('.ExamineHouseTradingLease').hide();
                houseLandTrading.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeSell, false, number);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, basicHouseTrading.paymentMethod, function (html, data) {
                    $("#" + houseTradingFormId).find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else if (key == AssessDicKey.examineHouseTransactionTypeLease) {
                $("#" + houseTradingFormId).find('.ExamineHouseTradingSell').hide();
                $("#" + houseTradingFormId).find('.ExamineHouseTradingLease').show();
                houseLandTrading.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeLease, false, number);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethodLease, basicHouseTrading.paymentMethod, function (html, data) {
                    $("#" + houseTradingFormId).find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else {
                $("#" + houseTradingFormId).find('.ExamineHouseTradingSell').hide();
                $("#" + houseTradingFormId).find('.ExamineHouseTradingLease').hide();
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, basicHouseTrading.paymentMethod, function (html, data) {
                    $("#" + houseTradingFormId).find("select.paymentMethod").empty().html(html).trigger('change');
                });
            }
        });

        $("#" + houseTradingFormId).find('[name=paymentMethod]').change(function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHousePaymentMethodInstallment) {
                $("#" + houseTradingFormId).find('.installmentInterestRate').show();
            } else {

                $("#" + houseTradingFormId).find('.installmentInterestRate').hide();
            }
            if (key == AssessDicKey.examineHousePaymentMethodDisposable || key == AssessDicKey.examineHousePaymentMethodLeaseDisposable) {
                $("#" + houseTradingFormId).find('.tradingCondition').hide();
            } else {
                $("#" + houseTradingFormId).find('.tradingCondition').show();
            }
        });

        //信息来源类型
        $("#" + houseTradingFormId).find("[name=informationType]").change(function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHouseInformationSourceTypeOpen) {
                $("#" + houseTradingFormId).find('.infomationTypeOpen').show();
                $("#" + houseTradingFormId).find('.infomationTypeOther').hide();
            } else {
                $("#" + houseTradingFormId).find('.infomationTypeOpen').hide();
                $("#" + houseTradingFormId).find('.infomationTypeOther').show();
            }
        })
    };


    houseLandTrading.showPriceConnotationUnit = function (basicHouseTrading, number) {
        var houseTradingFormId = houseLandTrading.basicHouseTradingFrm + number;
        if (houseCommon.isNotBlank(basicHouseTrading.priceConnotation)) {
            var strArr = ["建筑面积单价", "套内面积单价"];//来自于实体描述1(1).docx中的规则
            var priceConnotationId = basicHouseTrading.priceConnotation;
            if (priceConnotationId) {
                AssessCommon.getDataDicInfo(priceConnotationId, function (priceConnotationData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(priceConnotationData.name) != -1) {
                        $("#" + houseTradingFormId).find(".priceConnotationUnitContent").hide();
                    } else {
                        $("#" + houseTradingFormId).find(".priceConnotationUnitContent").show();
                    }
                });
            }
        }
        //绑定变更事件
        $("#" + houseTradingFormId).find("select.priceConnotation").off('change').on('change', function () {
            var strArr = ["建筑面积单价", "套内面积单价"];//来自于实体描述1(1).docx中的规则
            var priceConnotationId = $("#" + houseTradingFormId).find("select.priceConnotation").val();
            if (priceConnotationId) {
                AssessCommon.getDataDicInfo(priceConnotationId, function (priceConnotationData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(priceConnotationData.name) != -1) {
                        $("#" + houseTradingFormId).find(".priceConnotationUnitContent").hide();
                    } else {
                        $("#" + houseTradingFormId).find(".priceConnotationUnitContent").show();
                    }
                });
            }
        });

    }


    houseLandTrading.loadTradingSellAndLeaseList = function (tradingType, readonly, number) {
        var cols = [];
        var tbListId = '';
        if (tradingType == AssessDicKey.examineHouseTransactionTypeSell) {
            tbListId = 'tableTradingSell' + number;
            cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
            cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
            cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
        }
        if (tradingType == AssessDicKey.examineHouseTransactionTypeLease) {
            tbListId = 'tableTradingLease' + number;
            cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
            cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
            cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
        }
        if (!readonly) {
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' " + "onclick=houseLandTrading.deleteTradingSellAndLease(" + row.id + ",'" + tradingType + "','" + number + "'" + ")" + ">";
                    str += "<i class='fa fa-minus fa-white'>" + "</i>";
                    str += "</a>";
                    str += '</div>';
                    return str;
                }
            });
        }
        $("#" + tbListId).bootstrapTable('destroy');
        TableInit(tbListId, getContextPath() + "/basicHouseTradingLeaseAndSell/getLeaseAndSellVos", cols, {
            type: tradingType,
            tradingId: number,
            houseId: houseCommon.getHouseId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    //保存出售或出租
    houseLandTrading.saveTradingSellAndLease = function (_this) {
        var number = $(_this).closest(".modal-content").find("input[name='tradingId']").val();
        var frmSon = 'frmTradingLeaseAndSell';
        var divBoxSon = 'divBoxTradingLeaseAndSell';
        if (!$("#" + frmSon).valid()) {
            return false;
        }
        var data = formParams(frmSon);
        data.tradingType = data.type;
        data.houseId = houseCommon.getHouseId();
        var url = getContextPath() + "/basicHouseTradingLeaseAndSell/saveAndUpdateHouseTradingLeaseAndSell";
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    $("#" + divBoxSon).modal("hide");
                    houseLandTrading.loadTradingSellAndLeaseList(data.type, false, number);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    //删除出售或出租
    houseLandTrading.deleteTradingSellAndLease = function (id, type, number) {
        $.ajax({
            url: getContextPath() + "/basicHouseTradingLeaseAndSell/removeLeaseAndSell",
            type: "post",
            dataType: "json",
            data: {
                id: id,
                type: type
            },
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '删除成功');
                    houseLandTrading.loadTradingSellAndLeaseList(type, false, number);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    /**
     * 清除html
     * @param _this
     */
    houseLandTrading.cleanHTMLData = function (_this) {
        var x_panel = $(_this).closest(".col-md-12");
        var form = x_panel.find("form").eq(0);
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouseTrading/deleteBasicHouseTrading",
                type: "post",
                dataType: "json",
                data: {id: form.find("input[name='id']").val()},
                success: function (result) {
                    if (result.ret) {
                        x_panel.remove();
                        notifySuccess("成功", "删除数据成功");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        });
    }


    /**
     * 单个保存
     * @param _this
     * @returns {boolean}
     */
    houseLandTrading.saveHouseTrading = function (flag, _this) {
        if (flag) {
            //单个保存
            var data = formParams(_this);
            data.bisMark = $("#" + _this).closest('.col-md-12').find("input[name='bisMark']").prop("checked");

            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouseTrading/saveAndUpdateBasicHouseTrading",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                    } else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {

        }
    }

    //新增出售或出租
    houseLandTrading.addTradingSellAndLease = function (number) {
        var houseTradingFormId = houseLandTrading.basicHouseTradingFrm + number;
        var key = $("#" + houseTradingFormId).find('[name=tradingType]').find("option:selected").attr('key');
        var frmSon = 'frmTradingLeaseAndSell';
        var divBoxSon = 'divBoxTradingLeaseAndSell';
        $("#" + frmSon).clearAll().find(".type").val(key);
        $("#" + frmSon).find('input[name="tradingId"]').val(number);
        if (key == AssessDicKey.examineHouseTransactionTypeSell) {
            $("#" + divBoxSon).find(".lease").hide();
            $("#" + divBoxSon).find(".sell").show();
            $("#" + divBoxSon).find(".modal-title").html("出售信息");
        }
        if (key == AssessDicKey.examineHouseTransactionTypeLease) {
            $("#" + divBoxSon).find(".lease").show();
            $("#" + divBoxSon).find(".sell").hide();
            $("#" + divBoxSon).find(".modal-title").html("出租信息");
        }
        $("#" + divBoxSon).modal("show");
    };

    //附件上传控件id数组
    houseLandTrading.houseFileControlIdArray = [];
    //附件
    houseLandTrading.getFilePartHtml = function (fieldName, number) {
        var fileDiv = $('#' + fieldName);
        fileDiv.empty();
        var houseFileHtml = '';
        AssessCommon.loadAsyncDataDicByKey(houseLandTrading.houseTradingFilePart, '', function (html, resultData) {

            var groupIndex = 2;//分成2个一组
            var result = [];
            for (var k = 0, len = resultData.length; k < len; k += groupIndex) {
                result.push(resultData.slice(k, k + groupIndex));
            }
            $.each(result, function (i, data) {
                houseFileHtml += '<div class="row form-group">';
                houseFileHtml += '<div class="col-md-12">';
                houseFileHtml += '<div class="form-inline x-valid">';
                $.each(data, function (j, item) {
                    houseFileHtml += '<label class="col-sm-1 col-md-2">' + item.name + '</label>';
                    houseFileHtml += '<div class="col-sm-5 col-md-4">';
                    houseFileHtml += '<input id="' + item.fieldName + number + '" placeholder="上传附件" class="form-control input-full" type="file">';
                    houseFileHtml += '<div id="_' + item.fieldName + number + '"></div>';
                    houseFileHtml += '</div>';
                    houseLandTrading.houseFileControlIdArray.push(item.fieldName);
                });
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
            });

        }, false);
        fileDiv.append(houseFileHtml);
    };

    //附件上传
    houseLandTrading.fileUpload = function (fieldsName, number) {
        var itemId = fieldsName + number;
        FileUtils.uploadFiles({
            target: itemId,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouseTrading,
                tableId: number
            },
            deleteFlag: true,
            onUploadComplete: function () {
                houseLandTrading.fileShow(fieldsName, number);

            }
        });
    };

    houseLandTrading.fileShow = function (fieldsName, number, deleteFlag) {
        var itemId = fieldsName + number;
        FileUtils.getFileShows({
            target: itemId,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouseTrading,
                tableId: number
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag,

        })
    };

    houseLandTrading.init = function () {
        $("#" + houseLandTrading.basicHouseLandTradingAppend).empty();
        houseLandTrading.appendHtml(true);
    }

    houseLandTrading.updateBisMark = function (_form, _this) {
        var data = formParams(_form);
        data.bisMark = $(_this).prop("checked");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicHouseTrading/updateBisMark",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    var forms = $("#basicHouseLandTradingAppend").find("form");
                    $.each(forms, function (i, n) {
                        var text = $(n).attr("id");
                        if (text.indexOf(houseLandTrading.basicHouseTradingFrm) != -1 && text != _form) {
                            $(this).closest('.col-md-12').find("input[name='bisMark']").prop("checked", false);
                        }
                    });
                    notifySuccess("成功", "设置成功");
                } else {
                    AlertError("失败", "设置失败，失败原因:" + result.errmsg);
                    $(_this).prop("checked", true);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        });

    }
</script>