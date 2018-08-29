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
                <label class="col-sm-1 control-label">交易时间<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input readonly="readonly" placeholder="交易时间"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate tradingTime">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易类型<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易类型" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}" data-rule-number='true'
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
                    <input type="text" readonly="readonly" placeholder="押金"  class="form-control" name="deposit" value="${surveyExamineDataInfoVo.examineHouseTradingVo.deposit}">
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
                    <input type="text" readonly="readonly" placeholder="说明事项类型"  class="form-control" name="descriptionType" value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionType}">
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
    </form>
</div>

<script type="text/javascript">
    $(function () {
        examineHouseTrading.prototype.select2Init();
        examineHouseTrading.prototype.init();
    });
</script>

<script type="text/javascript">
    var examineHouseTrading = function () {

    };
    examineHouseTrading.prototype = {
        select2Init:function () {
            //页面保存数据后 展示数据
            $("#"+examineHouseTrading.prototype.config().frm+" .tradingTime").val(formatDate("${surveyExamineDataInfoVo.examineHouseTradingVo.tradingTime}"));
            examineHouseTrading.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionType}","descriptionType");

            var tradingTypeID = "${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}" ;
            if (examineHouseTrading.prototype.select2IsNotNull(tradingTypeID)){
                AssessCommon.getDataDicInfo(tradingTypeID,function (data) {
                    var  tradingType = data.fieldName;
                    $("#"+examineHouseTrading.prototype.config().tableSon).parent().parent().parent().show();
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingSellID).hide();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingLeaseID).show();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingLeaseID);
                    }
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingSellID).show();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingLeaseID).hide();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingSellID);
                    }
                })
            }
            examineHouseTrading.prototype.select2InitMethodWrite(tradingTypeID,"tradingType");
        },
        select2InitMethodWrite:function (data,name) {
            if (examineHouseTrading.prototype.select2IsNotNull(data)){
                if (examineHouseTrading.prototype.select2IsNotNull(name)){
                    $("#"+examineHouseTrading.prototype.config().frm+" ."+name).val(data).trigger("change");
                }
            }else {
                if (examineHouseTrading.prototype.select2IsNotNull(name)){
                    $("#"+examineHouseTrading.prototype.config().frm+" ."+name).val(null).trigger("change");
                }
            }
        },
        select2IsNotNull:function (data) {
            if (data == null){
                return false;
            }
            if (data == ''){
                return false;
            }
            if (data == ""){
                return false;
            }
            if (data == 0){
                return false;
            }
            return true;
        },
        config: function () {
            return {
                frm: "frm_houseTrading",
                frmSon: "frm_ExamineHouseTradingLeaseAndSell",
                divBoxSon: "divBoxExamineHouseTradingLeaseAndSell",
                tableSon: "ExamineHouseTradingLeaseAndSellTableSon",
                examineHouseTradingSellID: "ExamineHouseTradingSell",//根据 ExamineHouseTradingSellAndLeaseDtoTypeEnum配置(key需要与数据字典配置一致)
                examineHouseTradingLeaseID: "ExamineHouseTradingLease"
            };
        },
        //子类列表加载
        subLoadList:function (type_) {
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
            var data = {} ;
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#planDetailsId").size() > 0){
                data.planDetailsId = $("#planDetailsId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $("#" + examineHouseTrading.prototype.config().tableSon).bootstrapTable('destroy');
            TableInit(examineHouseTrading.prototype.config().tableSon, "${pageContext.request.contextPath}/examineHouse/getExamineHouseTradingSellAndLeaseDtoList", cols, {
                type: type_,examineType:data.examineType,declareId:data.declareId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        //子类 删除
        subRemove:function (id,type) {

        },
        subShowModel:function () {

        },
        //子类添加
        subSave: function () {

        },

        init: function () {
            //第一次加载
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").html(html);
                $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").select2();//加载样式
            })
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm + " .descriptionType").html(html);
                $("#" + examineHouseTrading.prototype.config().frm + " .descriptionType").select2();//加载样式
            })

            $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").change(function () {
                var tradingID = $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").eq(1).val();
                var tradingType = null;
                AssessCommon.getDataDicInfo(tradingID,function (data) {
                    tradingType = data.fieldName;
                    $("#"+examineHouseTrading.prototype.config().tableSon).parent().parent().parent().show();
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingSellID).hide();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingLeaseID).show();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingLeaseID);
                    }
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingSellID).show();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingLeaseID).hide();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingSellID);
                    }
                })
            });
        }
    };

</script>
