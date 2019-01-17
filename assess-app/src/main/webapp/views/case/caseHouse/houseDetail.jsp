<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>房屋基本信息</h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal" id="caseHouseFrm">
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
                <div class="x-valid">
                    <label class="col-sm-1 control-label">户型图朝向</label>
                    <div class="btn btn-success" onclick="objectData.orientationFun(true)">户型图朝向
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">朝向</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.orientationName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">面积</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouse.area}</label>
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
                    <label class="col-sm-1 control-label">房屋出租占用情况描述</label>
                    <div class="col-sm-11">
                        <label class="form-control">${caseHouse.description}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">房屋平面图</label>
                    <div class="col-sm-3">
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
                    <label class="col-sm-1 control-label">交易情况</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.transactionSituationName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">财产范围</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.scopePropertyName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">税费负担</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.taxBurdenName}</label>
                    </div>
                </div>
            </div>
            <c:if test="${isHouseAbnormal eq true}">
                <div class="form-group">
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
            </c:if>
            <div class="x_title">融资条件</div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">首付款比例</label>
                    <div class="col-sm-3">
                        <label class="form-control" name="downPaymentRatio">${caseHouseTrading.downPaymentRatio}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">贷款利率</label>
                    <div class="col-sm-3">
                        <label class="form-control" name="lendingRate">${caseHouseTrading.lendingRate}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">贷款期限</label>
                    <div class="col-sm-3">
                        <label class="form-control" name="loanPeriod">${caseHouseTrading.loanPeriod}</label>
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
                        <label class="form-control">${caseHouseTrading.tradingTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">付款方式</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseHouseTrading.paymentMethodName}</label>
                    </div>
                </div>
                <c:if test="${isHouseInstallment eq true}">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">分期支付利率</label>
                        <div class="col-sm-3">
                            <label class="form-control"
                                   name="installmentInterestRate">${caseHouseTrading.installmentInterestRate}</label>
                        </div>
                    </div>
                </c:if>
            </div>
            <c:if test="${isHouseSell eq true}">
                <div class="form-group ExamineHouseTradingSell">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">买方额外支付的税</label>
                        <div class="col-sm-3">
                            <label class="form-control" name="buyerExtraTax">${caseHouseTrading.buyerExtraTax}</label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">买方额外支付的费</label>
                        <div class="col-sm-3">
                            <label class="form-control" name="buyerExtraFee">${caseHouseTrading.buyerExtraFee}</label>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${isHouseLease eq true}">
                <div class="form-group ExamineHouseTradingLease">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">承租方额外支付的税</label>
                        <div class="col-sm-3">
                            <label class="form-control"
                                   name="rentingExtraTax">${caseHouseTrading.rentingExtraTax}</label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">承租方额外支付的费</label>
                        <div class="col-sm-3">
                            <label class="form-control"
                                   name="rentingExtraFee">${caseHouseTrading.rentingExtraFee}</label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">押金（元）</label>
                        <div class="col-sm-3">
                            <label class="form-control" name="deposit">${caseHouseTrading.deposit}</label>
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
                                value='${caseHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/></label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">交易总价（元）</label>
                    <div class="col-sm-3">
                        <label class="form-control"
                               name="tradingTotalPrice">${caseHouseTrading.tradingTotalPrice}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">交易单价（元）</label>
                    <div class="col-sm-3">
                        <label class="form-control" name="tradingUnitPrice">${caseHouseTrading.tradingUnitPrice}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">信息来源类型</label>
                    <div class="col-sm-3">
                        <label class="form-control"
                               name="informationTypeName">${caseHouseTrading.informationTypeName}</label>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${isHouseInfomationOpen eq true}">
                        <div class="x-valid infomationTypeOpen">
                            <label class="col-sm-1 control-label">信息来源类别</label>
                            <div class="col-sm-3">
                                <label class="form-control"
                                       name="informationCategoryName">${caseHouseTrading.informationCategoryName}</label>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="x-valid infomationTypeOther">
                            <label class="col-sm-1 control-label">姓名</label>
                            <div class="col-sm-3">
                                <label class="form-control" name="name">${caseHouseTrading.name}</label>
                            </div>
                        </div>
                        <div class="x-valid infomationTypeOther">
                            <label class="col-sm-1 control-label">电话</label>
                            <div class="col-sm-3">
                                <label class="form-control" name="phone">${caseHouseTrading.phone}</label>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>

    <div class="x_content">
        <!-- 房间 -->
        <c:if test="${hasHouseRoomData}">
            <%@include file="/views/case/caseHouse/caseHouseRoom.jsp" %>
        </c:if>

        <!-- 临街 -->
        <c:if test="${hasHouseFaceStreetData}">
            <%@include file="/views/case/caseHouse/caseHouseFaceStreet.jsp" %>
        </c:if>

        <!-- 房屋配套设备设施 -->
        <c:if test="${hasHouseCorollaryEquipmentData}">
            <%@include file="/views/case/caseHouse/caseHouseCorollaryEquipment.jsp" %>
        </c:if>

        <!-- 电力通讯网络 -->
        <c:if test="${hasHouseIntelligentData}">
            <%@include file="/views/case/caseHouse/caseHouseIntelligent.jsp" %>
        </c:if>

        <!-- 供水 -->
        <c:if test="${hasHouseWaterData}">
            <%@include file="/views/case/caseHouse/caseHouseWater.jsp" %>
        </c:if>

        <!-- 排水 -->
        <c:if test="${hasHouseWaterDrainData}">
            <%@include file="/views/case/caseHouse/caseHouseWaterDrain.jsp" %>
        </c:if>

        <!-- 新风情况 -->
        <c:if test="${hasHouseEquipmentNewWind}">
            <%@include file="/views/case/caseHouse/caseHouseNewWind.jsp" %>
        </c:if>

        <!-- 空调情况 -->
        <c:if test="${hasHouseEquipmentAirConditioner}">
            <%@include file="/views/case/caseHouse/caseHouseAirConditioner.jsp" %>
        </c:if>

        <!-- 房屋供暖 -->
        <c:if test="${hasHouseEquipmentHeating}">
            <%@include file="/views/case/caseHouse/caseHouseHeating.jsp" %>
        </c:if>

        <!-- 房屋完损度 -->
        <c:if test="${hasHouseDamagedDegreeData}">
            <%@include file="/views/case/caseHouse/caseDamagedDegree.jsp" %>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/case/case.common.js"></script>
<script type="text/javascript">
    var objectData = new Object();

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
        subLoadList: function (type_) {
            var cols = [];
            var tableListId = "";
            if (type_ == AssessDicKey.examineHouseTransactionTypeLease) {
                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
                tableListId = "tableTradingLease";
            }
            if (type_ == AssessDicKey.examineHouseTransactionTypeSell) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
                tableListId = "tableTradingSell";
            }
            $("#" + tableListId).bootstrapTable('destroy');
            TableInit(tableListId, "${pageContext.request.contextPath}/caseHouse/getCaseHouseTradingLeaseAndSellDtoVos", cols, {
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

    objectData.orientationFun = function () {
        var contentUrl = '${pageContext.request.contextPath}/map/houseTagging?';
        contentUrl += 'attachmentId=${caseEstateTagging.attachmentId}';
        contentUrl += '&lng=${caseEstateTagging.lng}';
        contentUrl += '&lat=${caseEstateTagging.lat}';
        contentUrl += '&deg=${caseEstateTagging.deg}';
        contentUrl += '&readonly=true';
        layer.open({
            type: 2,
            title: '房屋标注',
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: contentUrl
        });
    };

    $(function () {
        objectData.showFile(AssessUploadKey.HOUSE_HUXING_PLAN);
        objectData.showFile(AssessUploadKey.HOUSE_NEW_HUXING_PLAN);
        objectData.showFile(AssessUploadKey.HOUSE_IMG_PLAN);
        objectData.house.subLoadList(AssessDicKey.examineHouseTransactionTypeSell);
        objectData.house.subLoadList(AssessDicKey.examineHouseTransactionTypeLease);
    });

</script>