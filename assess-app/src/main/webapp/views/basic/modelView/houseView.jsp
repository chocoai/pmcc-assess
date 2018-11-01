<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3><small>
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
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="所在楼层(请输入数字)" name="floor"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">使用环境<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 useEnvironment"
                            name="useEnvironment" required="required">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型选择<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <select class="form-control huxingId" name="huxingId">
                        </select>
                        <label class="input-group-addon btn">刷新户型<i
                                class="fa fa-refresh"></i></label>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">户型图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="house_latest_family_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="朝向" name="orientation"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 certUse" name="certUse"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 practicalUse" name="practicalUse"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">权益限制<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                           name="rightInterestsRestriction"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋出租占用情况途描述<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                                    <textarea class="form-control" name="description" required="required">

                                    </textarea>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
                <div class="col-sm-5">
                    <input id="house_house_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_house_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="x_content">
    <div class="x_title">
        <h3> <small>
            房屋交易信息
        </small></h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicTradingFrm">
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

        <div class="form-group CaseHouseTradingSell" style="display: none">
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

            <div class="x-valid totalSale" style="display: none;">
                <label class="col-sm-1 control-label">出售总额</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="出售总额"
                           name="totalSale" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group CaseHouseTradingLease" style="display: none">
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

        <div class="form-group" id="CaseHouseTradingLeaseAndSellDtoTableSonDiv" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1" style="text-align: right;">
                    <button type="button" class="btn btn-success" data-toggle="modal" href="#divBox"
                            onclick=""> 新增
                    </button>
                </div>
                <div class="col-sm-11">
                    <table class="table table-bordered" id="CaseHouseTradingLeaseAndSellDtoTableSon">
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
    </form>
</div>

<script type="text/javascript">

    var HouseModelFun = new Object();

    HouseModelFun.config = {
        houseFrm:"basicHouseFrm",
        tradingFrm:"basicTradingFrm"
    };

    /**
     * 判断字符串以及null等
     */
    HouseModelFun.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * 判断对象 属性
     */
    HouseModelFun.isNotBlankObjectProperty = function (obj) {
        for (var key in obj) {
            if (this.isNotBlank(obj[key])) {
                return true;
            }
        }
        return false
    };

    /**
     * 判断对象
     */
    HouseModelFun.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    HouseModelFun.houseInit = function (item) {
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.certUse, function (html, data) {
            $("#"+HouseModelFun.config.houseFrm).find("select.certUse").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, item.practicalUse, function (html, data) {
            $("#"+HouseModelFun.config.houseFrm).find("select.practicalUse").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseEnvironmentUse, item.useEnvironment, function (html, data) {
            $("#"+HouseModelFun.config.houseFrm).find("select.useEnvironment").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, item.newsHuxing, function (html, data) {
            $("#"+HouseModelFun.config.houseFrm).find("select.useEnvironment").empty().html(html).trigger('change');
        });
    };

    HouseModelFun.tradingInit = function (item) {
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, item.taxBurden, function (html, data) {
            $("#"+HouseModelFun.config.tradingFrm).find("select.taxBurden").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.casesHouseTransactionType, item.tradingType, function (html, data) {
            $("#"+HouseModelFun.config.tradingFrm).find("select.tradingType").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, item.descriptionType, function (html, data) {
            $("#"+HouseModelFun.config.tradingFrm).find("select.descriptionType").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, item.normalTransaction, function (html, data) {
            $("#"+HouseModelFun.config.tradingFrm).find("select.normalTransaction").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, item.paymentMethod, function (html, data) {
            $("#"+HouseModelFun.config.tradingFrm).find("select.paymentMethod").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseClassificationInformationSources, item.informationType, function (html, data) {
            $("#"+HouseModelFun.config.tradingFrm).find("select.informationType").empty().html(html).trigger('change');
        });
    };


</script>