<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h4>房屋基本信息 </h4>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal" id="basicHouseFrm">
            <input type="hidden" value="${caseHouse.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">房号</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.houseNumber}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">所在楼层</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.floor}</label>
                    </div>
                </div>
                <c:if test="${caseHouse.type eq 1}">
                    <div class="x-valid" id="industryUseEnvironment">
                        <label class="col-sm-1 control-label">使用环境</label>
                        <div class="col-sm-3">
                            <label class="form-control">${caseHouse.useEnvironmentName}</label>
                        </div>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">户型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.huxingName}</label>
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
                        <label class="form-control">${caseHouse.orientation}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">新户型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.newHuxingName}</label>
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
                        <label class="form-control">${caseHouse.certUseName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">实际用途</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.practicalUseName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">权益限制</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.rightInterestsRestriction}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">房屋出租占用情况途描述</label>
                    <div class="col-sm-11">
                        <label class="form-control">${caseHouse.description}</label>
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
            </div>
        </form>
    </div>

    <div class="x_content">
        <div class="x_title">
            <h4>
                房屋交易信息
            </h4>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal" id="basicTradingFrm">
            <input type="hidden" name="id" value="${caseHouseTrading.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">财产范围</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.scopePropertyName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">融资条件</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.financingConditionsName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">税费负担</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.taxBurdenName}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">交易情况</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.normalTransactionName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">说明事项类型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.descriptionTypeName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">说明事项内容</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.descriptionContent}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">

                <div class="x-valid">
                    <label class="col-sm-1 control-label">交易类型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.tradingTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">信息来源分类</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.informationTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">信息来源</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.informationName}</label>
                    </div>
                </div>

            </div>

            <div class="form-group ExamineHouseTradingSell" style="display: none">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">买方支付的额外税费</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.buyerExtraTaxFee}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">付款方式</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.paymentMethodName}</label>
                    </div>
                </div>

                <div class="x-valid" style="display: none;">
                    <label class="col-sm-1 control-label">出售总额</label>
                    <div class="col-sm-3">
                        <input type="text" readonly="readonly" class="form-control" name="totalSale" value="${caseHouseTrading.totalSale}">
                    </div>
                </div>
                <div class="x-valid" style="display: none;">
                    <label class="col-sm-1 control-label">分期支付利率</label>
                    <div class="col-sm-3">
                        <input type="text" readonly="readonly" class="form-control" name="installmentInterestRate" value="${caseHouseTrading.installmentInterestRate}">
                    </div>
                </div>
            </div>

            <div class="form-group ExamineHouseTradingLease" style="display: none">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.rentingExtraTaxFee}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">押金</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.deposit}</label>
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
                        <label class="form-control"><fmt:formatDate value="${caseHouseTrading.tradingTime}" pattern="yyyy-MM-dd"></fmt:formatDate> </label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">交易总价</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.tradingTotalPrice}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">交易单价</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.tradingUnitPrice}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">电话</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.phone}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">姓名</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.name}</label>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 房间 -->
<div class="room" style="display: ${hasHouseRoomData?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseRoom.jsp" %>
</div>

<!-- 临街 -->
<div class="street" style="display: ${hasHouseFaceStreetData?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseFaceStreet.jsp" %>
</div>

<!-- 房屋配套设备设施 -->
<div class="corollary_equipment" style="display: ${hasHouseCorollaryEquipmentData?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseCorollaryEquipment.jsp" %>
</div>

<!-- 电力通讯网络 -->
<div class="intelligent" style="display: ${hasHouseIntelligentData?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseIntelligent.jsp" %>
</div>

<!-- 供排水	 -->
<div class="water" style="display: ${hasHouseWaterData?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseWater.jsp" %>
</div>

<!-- 新风情况	 -->
<div class="houseNewWind" style="display: ${hasHouseEquipmentNewWind?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseNewWind.jsp" %>
</div>

<!-- 空调情况	 -->
<div class="airConditioner" style="display: ${hasHouseEquipmentAirConditioner?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseAirConditioner.jsp" %>
</div>

<!-- 房间供暖	 -->
<div class="houseHeating" style="display: ${hasHouseEquipmentHeating?'block':'none'};">
    <%@include file="/views/case/caseHouse/caseHouseHeating.jsp" %>
</div>

<script type="text/javascript">
    var objectData = new Object();

    objectData.config = {
        basicHouse: {
            key: "basicHouse",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm",
            leaseID: "ExamineHouseTradingLease",//房屋出租
            sellID: "ExamineHouseTradingSell",//房屋出售
            totalSale: "totalSale",//出售总额
            divBoxSon: "divBoxTradingLeaseAndSell",
            tableSon: "tableTradingLeaseAndSell",
            frmSon: "frmTradingLeaseAndSell",
            houseFileId: "house_img_plan"
        }
    };

    /**
     * 判断字符串以及null等
     */
    objectData.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    objectData.showFile = function (fieldsName) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.CaseHouse,
                tableId: "${caseHouse.id}"
            },
            deleteFlag: false
        })
    };

    objectData.house = {
        init: function () {
            var tradingID = "${caseHouseTrading.tradingType}";
            var tradingType = null;
            AssessCommon.getDataDicInfo(tradingID, function (data) {
                tradingType = data.fieldName;
                if (tradingType == objectData.config.basicHouse.leaseID) {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.sellID).hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.leaseID).show();
                    $("#" + objectData.config.basicHouse.tableSon + "Div").show();
                    objectData.house.subLoadList(objectData.config.basicHouse.leaseID);
                }
                if (tradingType == objectData.config.basicHouse.sellID) {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.sellID).show();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.leaseID).hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("#" + objectData.config.basicHouse.tableSon + "Div").hide();
                }
            });
            AssessCommon.getDataDicInfo("${caseHouseTrading.paymentMethod}", function (data) {
                if (data.name == '一次性') {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='totalSale']").parent().parent().show();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='installmentInterestRate']").parent().parent().hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("#" + objectData.config.basicHouse.tableSon + "Div").hide();
                }
                if (data.name == '分期付款') {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='totalSale']").parent().parent().hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='installmentInterestRate']").parent().parent().show();
                    $("#" + objectData.config.basicHouse.tableSon + "Div").show();
                    objectData.house.subLoadList(objectData.config.basicHouse.sellID);
                }
            });

            objectData.showFile('house_huxing_plan');
            objectData.showFile('house_img_plan');
        },
        subLoadList: function (type_) {
            var cols = [];
            if (type_ == objectData.config.basicHouse.leaseID) {
                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
            }
            if (type_ == objectData.config.basicHouse.sellID) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
            }
            $("#" + objectData.config.basicHouse.tableSon).bootstrapTable('destroy');
            TableInit(objectData.config.basicHouse.tableSon, "${pageContext.request.contextPath}/caseHouse/getCaseHouseTradingLeaseAndSellDtoVos", cols, {
                type: type_, houseId: '${empty caseHouse.id?0:caseHouse.id}', approval: true
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

    $(function () {
        objectData.house.init();
    });

</script>