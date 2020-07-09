<%--
 案列交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="basicHouseTradingDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                        <div class="form-inline">
                        交易信息（{index}）
                        <div class="form-check" style="margin-left: 5px">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="bisMark"
                                       checked="checked" disabled="disabled">
                                <span class="form-check-sign">标准</span>
                            </label>
                        </div>
                        </div>
                    </div>
                    <div class="card-tools collapse-link">
                        <button class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body" style="display: none">
                <form class="form-horizontal" id="basicHouseTradingFrm_number">
                    <input type="hidden" name="id" value="_number">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">财产范围</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="scopePropertyName"></label>
                                </div>

                                <label class="col-sm-1 control-label">范围包括</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="scopeInclude"></label>
                                </div>

                                <label class="col-sm-1 control-label">范围不包括</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="scopeNotInclude"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">税费负担</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="taxBurdenName"></label>
                                </div>

                                <label class="col-sm-1 control-label">交易情况</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="transactionSituationName"></label>
                                </div>

                                <label class="col-sm-1 control-label">价格类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="priceTypeName"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group abnormalTransaction" style="display: none">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">说明事项类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="descriptionTypeName"></label>
                                </div>

                                <label class="col-sm-1 control-label">说明事项内容</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="descriptionContent"></label>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">交易类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="tradingTypeName"></label>
                                </div>
                                <label class="col-sm-1 control-label">付款方式</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="paymentMethodName"></label>
                                </div>
                                <label class="col-sm-1 control-label installmentInterestRate">分期支付利率</label>
                                <div class="col-sm-3 installmentInterestRate">
                                    <label class="form-control input-full"
                                           name="installmentInterestRate"></label>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="x_title tradingCondition">融资条件</div>
                    <div class="row form-group tradingCondition">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">首付款比例</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="downPaymentRatio"></label>
                                </div>

                                <label class="col-sm-1 control-label">贷款利率</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="lendingRate"></label>
                                </div>
                                <label class="col-sm-1 control-label">贷款期限</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="loanPeriod"></label>
                                </div>
                            </div>
                        </div>
                    </div>


                        <div class="row form-group ExamineHouseTradingSell">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-sm-1 control-label">买方额外支付的税</label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full"
                                               name="buyerExtraTax"></label>
                                    </div>
                                    <label class="col-sm-1 control-label">买方额外支付的费</label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full"
                                               name="buyerExtraFee"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group ExamineHouseTradingSell">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <table class="table table-bordered"
                                               id="tableTradingSell_number"></table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row form-group ExamineHouseTradingLease">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-sm-1 control-label">承租方额外支付的税</label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full"
                                               name="rentingExtraTax"></label>
                                    </div>
                                    <label class="col-sm-1 control-label">承租方额外支付的费</label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full"
                                               name="rentingExtraFee"></label>
                                    </div>
                                    <label class="col-sm-1 control-label">押金（元）</label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full"
                                               name="deposit"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group ExamineHouseTradingLease">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <table class="table table-bordered"
                                               id="tableTradingLease_number"></table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">交易时间</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full dbdate"
                                           name="tradingTime"></label>
                                </div>
                                <label class="col-sm-1 control-label">交易总价（元）</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="tradingTotalPrice"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">交易单价（元）</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="tradingUnitPrice"></label>
                                </div>

                                <label class="col-sm-1 control-label">单价内涵</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="priceConnotationName"></label>
                                </div>


                                    <label class="col-sm-1 control-label priceConnotationUnitContent">单价单位</label>
                                    <div class="col-sm-3 priceConnotationUnitContent">
                                        <label class="form-control input-full"
                                               name="priceConnotationUnit"></label>
                                    </div>

                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">信息来源类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full"
                                           name="informationTypeName"></label>
                                </div>



                                    <label class="col-sm-1 control-label infomationTypeOpen">信息来源类别</label>
                                    <div class="col-sm-3 infomationTypeOpen">
                                        <label class="form-control input-full "
                                               name="informationCategoryName"></label>
                                    </div>

                                    <label class="col-sm-1 control-label infomationTypeOther">姓名</label>
                                    <div class="col-sm-3 infomationTypeOther">
                                        <label class="form-control input-full"
                                               name="name"></label>
                                    </div>

                                    <label class="col-sm-1 control-label infomationTypeOther">电话</label>
                                    <div class="col-sm-3 infomationTypeOther">
                                        <label class="form-control input-full"
                                               name="phone"></label>
                                    </div>

                            </div>
                        </div>
                    </div>
                    <div id="houseTradingFilePart_number"></div>
                </form>
            </div>

        </div>
    </div>
</script>
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
                        onclick="houseTrading.saveTradingSellAndLease(this)">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script type="application/javascript">
    var houseTrading = {
        basicHouseTradingFrm: "basicHouseTradingFrm",
        basicHouseTradingAppend: "basicHouseTradingAppend",
        basicHouseTradingDiv: "basicHouseTradingDiv",
        houseTradingFilePart: "houseTradingFilePart",
    };


    /**
     * 添加html,并且替换
     * @returns {*|jQuery}
     */
    houseTrading.appendHtml = function (flag) {
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
                                var html = $("#" + houseTrading.basicHouseTradingDiv).html();
                                var number = item.id;
                                html = html.replace(/_number/g, number).replace(/{index}/g, i + 1);
                                $("#" + houseTrading.basicHouseTradingAppend).append(html);
                                houseTrading.initBasicHouseTradingForm(item, number)
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

    }

    //交易情况
    houseTrading.initBasicHouseTradingForm = function (data, number) {
        var houseTradingFormId = houseTrading.basicHouseTradingFrm + number;
        if (data != null) {
            $("#" + houseTradingFormId).initForm(data);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionSituation, data.transactionSituation, function (html, data) {
                $("#" + houseTradingFormId).find("select.transactionSituation").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, data.scopeProperty, function (html, data) {
                $("#" + houseTradingFormId).find("select.scopeProperty").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, data.taxBurden, function (html, data) {
                $("#" + houseTradingFormId).find("select.taxBurden").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, data.descriptionType, function (html, data) {
                $("#" + houseTradingFormId).find("select.descriptionType").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.tradingType, function (html, data) {
                $("#" + houseTradingFormId).find("select.tradingType").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseFinancingConditions, data.financingConditions, function (html, data) {
                $("#" + houseTradingFormId).find("select.financingConditions").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceType, data.informationType, function (html, data) {
                $("#" + houseTradingFormId).find("select.informationType").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceCategory, data.informationCategory, function (html, data) {
                $("#" + houseTradingFormId).find("select.informationCategory").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePriceConnotation, data.priceConnotation, function (html, data) {
                $("#" + houseTradingFormId).find("select.priceConnotation").empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouse_transaction_price_type, data.priceType, function (html, data) {
                $("#" + houseTradingFormId).find("select.priceType").empty().html(html).trigger('change');
            }, false);
            if (data.descriptionType) {
                $("#" + houseTradingFormId).find(".abnormalTransaction").show();
            } else {
                $("#" + houseTradingFormId).find(".abnormalTransaction").hide();
            }

            houseTrading.showPriceConnotationUnit(data, number);
            houseTrading.showTradingCondition(data, number);
            houseTrading.showInformationType(data, number);
            houseTrading.showTradingSellOrLeaese(data, number);
            houseTrading.showTransactionSituation(data, number);

            var fileId = "houseTradingFilePart"+number;
            houseTrading.getFilePartHtml(fileId,number);
            //初始化上传控件
            $.each(houseTrading.houseFileControlIdArray, function (i, item) {
                houseTrading.fileShow(item, number);
            });
            $("#" + houseTradingFormId).closest('.col-md-12').find("input[name='bisMark']").prop("checked", data.bisMark);

        }
    };

    houseTrading.showTransactionSituation=function(data,number) {
        var houseTradingFormId = houseTrading.basicHouseTradingFrm+number;
        var value = data.transactionSituation;
        AssessCommon.getDataDicInfo(value, function (bicData) {
            var key = bicData.fieldName;
            if (key == AssessDicKey.examineHouseTransactionAbnormal) {
                $("#"+houseTradingFormId).find(".abnormalTransaction").show();
            } else {
                $("#"+houseTradingFormId).find(".abnormalTransaction").hide();
            }
        });

    };

    houseTrading.showPriceConnotationUnit = function (basicHouseTrading, number) {
        var houseTradingFormId = houseTrading.basicHouseTradingFrm + number;
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

    houseTrading.showTradingCondition = function (data, number) {
        var houseTradingFormId = houseTrading.basicHouseTradingFrm + number;
        var value = data.paymentMethod;
        AssessCommon.getDataDicInfo(value, function (bicData) {
            var key = bicData.fieldName;
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

    };

    houseTrading.showInformationType = function (data, number) {
        var houseTradingFormId = houseTrading.basicHouseTradingFrm + number;
        var value = data.informationType;
        AssessCommon.getDataDicInfo(value, function (bicData) {
            var key = bicData.fieldName;
            if (key == AssessDicKey.examineHouseInformationSourceTypeOpen) {
                $("#" + houseTradingFormId).find('.infomationTypeOpen').show();
                $("#" + houseTradingFormId).find('.infomationTypeOther').hide();
            } else {
                $("#" + houseTradingFormId).find('.infomationTypeOpen').hide();
                $("#" + houseTradingFormId).find('.infomationTypeOther').show();
            }
        });

    };

    houseTrading.loadTradingSellAndLeaseList = function (tradingType, readonly, number) {
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

    houseTrading.showTradingSellOrLeaese=function(data,number) {
        var houseTradingFormId = houseTrading.basicHouseTradingFrm+number;
        var value = data.tradingType;
        AssessCommon.getDataDicInfo(value, function (bicData) {
            var key = bicData.fieldName;
            if (key == AssessDicKey.examineHouseTransactionTypeSell) {
                $("#"+houseTradingFormId).find('.ExamineHouseTradingSell').show();
                $("#"+houseTradingFormId).find('.ExamineHouseTradingLease').hide();
                houseTrading.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeSell,false,number);

            } else if (key == AssessDicKey.examineHouseTransactionTypeLease) {
                $("#"+houseTradingFormId).find('.ExamineHouseTradingSell').hide();
                $("#"+houseTradingFormId).find('.ExamineHouseTradingLease').show();
                houseTrading.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeLease,false,number);

            } else {
                $("#"+houseTradingFormId).find('.ExamineHouseTradingSell').hide();
                $("#"+houseTradingFormId).find('.ExamineHouseTradingLease').hide();

            }
        });

    };

    //附件上传控件id数组
    houseTrading.houseFileControlIdArray = [];
    //附件
    houseTrading.getFilePartHtml = function(fieldName,number){
        var fileDiv = $('#'+fieldName);
        fileDiv.empty();
        var houseFileHtml = '';
        AssessCommon.loadAsyncDataDicByKey(houseTrading.houseTradingFilePart, '', function (html, resultData) {

            var groupIndex= 2;//分成2个一组
            var result = [];
            for(var k=0,len=resultData.length;k<len;k+=groupIndex){
                result.push(resultData.slice(k,k+groupIndex));
            }
            $.each(result , function (i,data) {
                houseFileHtml += '<div class="row form-group">';
                houseFileHtml += '<div class="col-md-12">';
                houseFileHtml += '<div class="form-inline x-valid">';
                $.each(data,function (j,item) {
                    houseFileHtml += '<label class="col-sm-1 col-md-2">'+item.name+'</label>';
                    houseFileHtml += '<div class="col-sm-5 col-md-4">';
                    houseFileHtml += '<div id="_' + item.fieldName + number+'"></div>';
                    houseFileHtml += '</div>';
                    houseTrading.houseFileControlIdArray.push(item.fieldName);
                }) ;
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
            }) ;

        }, false);
        fileDiv.append(houseFileHtml);
    };

    houseTrading.fileShow = function (fieldsName,number) {
        var itemId = fieldsName+number;
        FileUtils.getFileShows({
            target: itemId,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouseTrading,
                tableId: number
            },
            deleteFlag: false,

        })
    };

    $(function () {
        houseTrading.appendHtml(true);
    });


</script>



