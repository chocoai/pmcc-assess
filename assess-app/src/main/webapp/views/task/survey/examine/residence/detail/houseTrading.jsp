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
                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input required="required" placeholder="交易时间" readonly="readonly"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate tradingTime" value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingTime}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易类型<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交易类型" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}"
                           name="tradingType" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">交易价格<span class="symbol required"></span></label>
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
                <label class="col-sm-1 control-label">买方支付的额外税费<span class="symbol required"></span></label>
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
                <label class="col-sm-1 control-label">承租方支付的额外税费<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="承租方支付的额外税费" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.rentingExtraTaxFee}"
                           name="rentingExtraTaxFee"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="押金" readonly="readonly" class="form-control" name="deposit" value="${surveyExamineDataInfoVo.examineHouseTradingVo.deposit}">
                </div>
            </div>

        </div>

        <div class="form-group" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1">
                </div>
                <div class="col-sm-11">
                    <table class="table table-bordered" id="ExamineHouseTradingLeaseAndSellTableSon">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项类型<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明事项类型" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionType}"
                           name="descriptionType" class="form-control">
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
    </form>
</div>

<script>
    var houseTrading_config ;
    (function () {
        var frm = "frm_houseTrading" ;
        var sellID = "ExamineHouseTradingSell" ;
        var leaseID = "ExamineHouseTradingLease" ;//根据 ExamineHouseTradingSellAndLeaseDtoTypeEnum配置(key需要与数据字典配置一致)
        var tableSon = "ExamineHouseTradingLeaseAndSellTableSon" ;
        var dataBaseName = AssessDBKey.ExamineHouse;
        houseTrading_config = {
            getFrm:function () {
                return frm;
            },
            getSellId:function () {
                return sellID;
            },
            getLeaseID:function () {
                return leaseID;
            },
            getSonTableID:function () {
                return tableSon;
            }
        }
    })();
    var houseTrading = Object.create(houseTrading_config);
    houseTrading.init = function () {
        houseTrading.select2LoadData();
        houseTrading.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}","tradingType");
        houseTrading.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionType}","descriptionType");
        var tradingTypeID = "${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}" ;
        if (houseTrading.select2IsNotNull(tradingTypeID)){
            $.ajax({
                url: "${pageContext.request.contextPath}/examineHouse/getBaseDataDicById",
                type: "get",
                data:{id:tradingTypeID},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var  tradingType = result.data.fieldName;
                        $("#"+houseTrading.getSonTableID()).parent().parent().parent().show();
                        if (tradingType == houseTrading.getSellId()) {
                            $("#"+houseTrading.getFrm() +" ."+houseTrading.getSellId()).hide();
                            $("#"+houseTrading.getFrm() +" ."+houseTrading.getLeaseID()).show();
                            houseTrading.subLoadList(houseTrading.getLeaseID());
                        }
                        if (tradingType == houseTrading.getSellId()) {
                            $("#"+houseTrading.getFrm() +" ."+houseTrading.getSellId()).show();
                            $("#"+houseTrading.getFrm() +" ."+houseTrading.getLeaseID()).hide();
                            houseTrading.subLoadList(houseTrading.getSellId());
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        $("#" + houseTrading.getFrm() + " :input").attr("readonly","readonly");
    };
    houseTrading.select2InitMethodWrite = function (data, name) {
        if (houseTrading.select2IsNotNull(data)) {
            if (houseTrading.select2IsNotNull(name)) {
                $("#" + houseTrading.getFrm() + " ." + name).val(data).trigger("change");
            }
        } else {
            $("#" + houseTrading.getFrm() + " ." + name).val(null).trigger("change");
        }
    };
    houseTrading.select2IsNotNull = function (data) {
        if (data == null) {
            return false;
        }
        if (data == '') {
            return false;
        }
        if (data == "") {
            return false;
        }
        if (data == 0) {
            return false;
        }
        return true;
    };
    houseTrading.select2LoadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/examineHouse/examine_house_transaction_type",
            type: "get",
            async:false,
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + houseTrading.getFrm() + " .tradingType").html(option);
                        $("#" + houseTrading.getFrm() + " .tradingType").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineHouse/examine_house_description_type",
            type: "get",
            async:false,
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + houseTrading.getFrm() + " .descriptionType").html(option);
                        $("#" + houseTrading.getFrm() + " .descriptionType").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    houseTrading.subLoadList = function (type_) {
        var cols = [];
        if (type_ == houseTrading.getSellId()) {
            cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
            cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
            cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});

        }
        if (type_ == houseTrading.getLeaseID()) {

            cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
            cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
            cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
        }
        $("#" + houseTrading.getSonTableID()).bootstrapTable('destroy');
        TableInit(houseTrading.getSonTableID(), "${pageContext.request.contextPath}/examineHouse/getExamineHouseTradingSellAndLeaseDtoList", cols, {
            type: type_
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    $(function () {
        // houseTrading.init();
    });
</script>
