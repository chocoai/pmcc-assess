<%--
 房屋交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>房屋交易信息 </h3>
        <div class="clearfix"></div>
    </div>
    <form id="frm_houseTrading" class="form-horizontal">
        <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
        <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineHouseTradingVo.id}">

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="财产范围"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.scopeProperty}"
                           name="scopeProperty" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">融资条件<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="融资条件"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.financingConditions}"
                           name="financingConditions" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">税费负担<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="税费负担"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.taxBurdenName}"
                           name="taxBurdenName" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">正常交易<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="正常交易"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.normalTransactionName}"
                           name="normalTransactionName" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项类型<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明事项类型"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionType}"
                           name="descriptionType" class="form-control" readonly="readonly">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项内容<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明事项内容" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionContent}"
                           name="descriptionContent" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易时间<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input readonly="readonly" placeholder="交易时间"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control  tradingTime">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易类型<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易类型" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingTypeName}"
                           name="tradingPrice"
                           class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易价格<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易价格(请输入数字)" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingPrice}" data-rule-number='true'
                           name="tradingPrice"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方支付的额外税费" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.buyerExtraTaxFee}"
                           name="buyerExtraTaxFee"
                           class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">付款方式<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="出售总额" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.paymentMethodName}"
                           name="paymentMethodName" class="form-control">
                </div>
            </div>

            <div class="x-valid totalSale" style="display: none;">
                <label class="col-sm-1 control-label">出售总额</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="出售总额" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.totalSale}"
                           name="totalSale" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="承租方支付的额外税费" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.rentingExtraTaxFee}"
                           name="rentingExtraTaxFee"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金</label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" placeholder="押金" class="form-control" name="deposit"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.deposit}">
                </div>
            </div>

        </div>

        <div class="form-group" style="display: none">
            <div class="x-valid">
                <div class="col-sm-11">
                    <table class="table table-bordered" id="ExamineHouseTradingLeaseAndSellTableSon">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项类型<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" placeholder="说明事项类型" class="form-control"
                           name="descriptionType"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionTypeName}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项内容<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明事项内容" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionContent}"
                           name="descriptionContent"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源分类<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="信息来源分类" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.informationTypeName}"
                           name="informationTypeName" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="信息来源" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.information}"
                           name="information" class="form-control">
                </div>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        examineHouseTrading.prototype.init();
    });
</script>

<script type="text/javascript">
    var examineHouseTrading = function () {

    };
    examineHouseTrading.prototype = {
        init: function () {
            $("#" + examineHouseTrading.prototype.config().frm + " .tradingTime").val(formatDate("${surveyExamineDataInfoVo.examineHouseTradingVo.tradingTime}"));
            var tradingTypeID = "${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}";
            if (examineHouseTrading.prototype.isEmpty(tradingTypeID)) {
                AssessCommon.getDataDicInfo(tradingTypeID, function (data) {
                    var tradingType = data.fieldName;
                    $("#" + examineHouseTrading.prototype.config().tableSon).parent().parent().parent().show();
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {
                        $("#" + examineHouseTrading.prototype.config().frm + " ." + examineHouseTrading.prototype.config().examineHouseTradingSellID).hide();
                        $("#" + examineHouseTrading.prototype.config().frm + " ." + examineHouseTrading.prototype.config().examineHouseTradingLeaseID).show();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingLeaseID);
                    }
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                        var id = "${surveyExamineDataInfoVo.examineHouseTradingVo.paymentMethod}" ;
                        AssessCommon.getDataDicInfo(id,function (item) {
                            $("#" + examineHouseTrading.prototype.config().frm + " ." + examineHouseTrading.prototype.config().examineHouseTradingLeaseID).hide();
                            $("#" + examineHouseTrading.prototype.config().frm + " ." + examineHouseTrading.prototype.config().examineHouseTradingSellID).show();
                            if (item.name == "一次性"){
                                $("."+examineHouseTrading.prototype.config().totalSale).show();
                            }
                            if (item.name == "分期付款"){
                                examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingSellID);
                            }
                        });
                    }
                })
            }
            examineHouseTrading.prototype.select2InitMethodWrite(tradingTypeID, "tradingType");
        },
        select2InitMethodWrite: function (data, name) {
            if (examineHouseTrading.prototype.isEmpty(data)) {
                if (examineHouseTrading.prototype.isEmpty(name)) {
                    $("#" + examineHouseTrading.prototype.config().frm + " ." + name).val(data).trigger("change");
                }
            } else {
                if (examineHouseTrading.prototype.isEmpty(name)) {
                    $("#" + examineHouseTrading.prototype.config().frm + " ." + name).val(null).trigger("change");
                }
            }
        },
        isEmpty: function (data) {
            if (data) {
                return true;
            }
            return false;
        },
        config: function () {
            return {
                frm: "frm_houseTrading",
                frmSon: "frm_ExamineHouseTradingLeaseAndSell",
                totalSale:"totalSale",
                divBoxSon: "divBoxExamineHouseTradingLeaseAndSell",
                tableSon: "ExamineHouseTradingLeaseAndSellTableSon",
                examineHouseTradingSellID: "ExamineHouseTradingSell",//根据 ExamineHouseTradingSellAndLeaseDtoTypeEnum配置(key需要与数据字典配置一致)
                examineHouseTradingLeaseID: "ExamineHouseTradingLease"
            };
        },
        //子类列表加载
        subLoadList: function (type_) {
            var cols = [];
            if (type_ == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});

            }
            if (type_ == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {

                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
            }
            var data = {};
            if ($("#declareId").size() > 0) {
                data.declareId = $("#declareId").val();
            }
            if ($("#planDetailsId").size() > 0) {
                data.planDetailsId = $("#planDetailsId").val();
            }
            if ($("#examineType").size() > 0) {
                data.examineType = $("#examineType").val();
            }
            $("#" + examineHouseTrading.prototype.config().tableSon).bootstrapTable('destroy');
            TableInit(examineHouseTrading.prototype.config().tableSon, "${pageContext.request.contextPath}/examineHouse/getExamineHouseTradingSellAndLeaseDtoList", cols, {
                type: type_, examineType: data.examineType, declareId: data.declareId,planDetailsId:data.planDetailsId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };

</script>
