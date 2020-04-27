<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息填写</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        房屋交易信息

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="basicHouseFrm" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicHouse.id}">
                                    <input type="hidden" name="houseNumber" value="${basicHouse.houseNumber}">
                                </form>
                                <div class="x_content">
                                    <form class="form-horizontal" id="basicTradingFrm">
                                        <input type="hidden" name="id" value="${basicHouseTrading.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">财产范围</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="scopePropertyName">${basicHouseTrading.scopePropertyName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">范围包括</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="scopeInclude">${basicHouseTrading.scopeInclude}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">范围不包括</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="scopeNotInclude">${basicHouseTrading.scopeNotInclude}</label>
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
                                                               name="taxBurdenName">${basicHouseTrading.taxBurdenName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">交易情况</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="transactionSituationName">${basicHouseTrading.transactionSituationName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">价格类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="priceTypeName">${basicHouseTrading.priceTypeName}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <c:if test="${!empty basicHouseTrading.descriptionTypeName}">
                                                        <label class="col-sm-1 control-label">说明事项类型</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="descriptionTypeName">${basicHouseTrading.descriptionTypeName}</label>
                                                        </div>

                                                    </c:if>
                                                    <c:if test="${!empty basicHouseTrading.descriptionContent}">

                                                        <label class="col-sm-1 control-label">说明事项内容</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="descriptionContent">${basicHouseTrading.descriptionContent}</label>
                                                        </div>
                                                    </c:if>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">交易类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="tradingTypeName">${basicHouseTrading.tradingTypeName}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">付款方式</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="paymentMethodName">${basicHouseTrading.paymentMethodName}</label>
                                                    </div>

                                                    <c:if test="${!empty basicHouseTrading.installmentInterestRate}">

                                                        <label class="col-sm-1 control-label">分期支付利率</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="installmentInterestRate">${basicHouseTrading.installmentInterestRate}</label>
                                                        </div>
                                                    </c:if>
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
                                                               name="downPaymentRatio">${basicHouseTrading.downPaymentRatio}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">贷款利率</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="lendingRate">${basicHouseTrading.lendingRate}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">贷款期限</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="loanPeriod">${basicHouseTrading.loanPeriod}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${basicHouseTrading.tradingTypeName=='出售'}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">买方额外支付的税</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="buyerExtraTax">${basicHouseTrading.buyerExtraTax}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">买方额外支付的费</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="buyerExtraFee">${basicHouseTrading.buyerExtraFee}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                            <table class="table table-bordered"
                                                                   id="tableTradingSell"></table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${basicHouseTrading.tradingTypeName != '出售'}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">承租方额外支付的税</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="rentingExtraTax">${basicHouseTrading.rentingExtraTax}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">承租方额外支付的费</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="rentingExtraFee">${basicHouseTrading.rentingExtraFee}</label>
                                                        </div>
                                                        <label class="col-sm-1 control-label">押金（元）</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="deposit">${basicHouseTrading.deposit}</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                            <table class="table table-bordered"
                                                                   id="tableTradingLease"></table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">交易时间</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full dbdate"
                                                               name="tradingTime"><fmt:formatDate
                                                                value='${basicHouseTrading.tradingTime}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">交易总价（元）</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="tradingTotalPrice">${basicHouseTrading.tradingTotalPrice}</label>
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
                                                               name="tradingUnitPrice">${basicHouseTrading.tradingUnitPrice}</label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">单价内涵</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="priceConnotationName">${basicHouseTrading.priceConnotationName}</label>
                                                    </div>

                                                    <c:if test="${not empty basicHouseTrading.priceConnotationUnit}">

                                                        <label class="col-sm-1 control-label">单价单位</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="priceConnotationUnit"
                                                                   id="priceConnotationUnit">${basicHouseTrading.priceConnotationUnit}</label>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">信息来源类型</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"
                                                               name="informationTypeName">${basicHouseTrading.informationTypeName}</label>
                                                    </div>

                                                    <c:if test="${basicHouseTrading.informationTypeName == '公开信息'}">

                                                        <label class="col-sm-1 control-label">信息来源类别</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="informationCategoryName">${basicHouseTrading.informationCategoryName}</label>
                                                        </div>

                                                    </c:if>
                                                    <c:if test="${basicHouseTrading.informationTypeName != '公开信息'}">

                                                        <label class="col-sm-1 control-label">姓名</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="name">${basicHouseTrading.name}</label>
                                                        </div>

                                                        <label class="col-sm-1 control-label">电话</label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"
                                                                   name="phone">${basicHouseTrading.phone}</label>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="houseTradingFilePart"></div>
                                    </form>
                                </div>

                                <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<%@include file="/views/share/chksCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js?v=${assessVersion}"></script>
<!-- 选项卡 内容之一 -->
<script type="text/html" id="damagedDegreeTabContentHtml">
    <div role="tabpanel" class="tab-pane fade" id="tab_content_{type}" aria-labelledby="profile-tab">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th width="10%">名称</th>
                <th width="10%">实例状况</th>
                <th width="60%">状况内容</th>
                <th width="10%">标准得分</th>
                <th width="10%">分数</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</script>
<!-- 结构部分下面4列 的 支撑html -->
<script type="text/html" id="damagedDegreeTabTrHtml">
    <tr class="group">
        <td>
            <input type="hidden" name="id" value="{id}">
            <input type="hidden" name="category" value="{category}">
            {categoryName}
            <div class="btn btn-xs btn-primary pull-right" {isShow}
                 onclick="damagedDegree.damagedDegreeDetailModalShow('{id}','{category}')">明细内容
            </div>
        </td>
        <td>
            <select class="form-control input-full" data-role="required" required="required" name="entityCondition" onchange="damagedDegree.entityConditionChange(this);"
                    data-intact="{intact}" data-basicallyIntact="{basicallyIntact}"
                    data-generalDamage="{generalDamage}" data-seriousDamage="{seriousDamage}">
                <option value="">-请选择-</option>
                <option value="intact">完好</option>
                <option value="basicallyIntact">基本完好</option>
                <option value="generalDamage">一般损坏</option>
                <option value="seriousDamage">严重损坏</option>
            </select>
        </td>
        <td>
            <span data-name="entityConditionContent" style="color: red;"></span>
            <textarea class="form-control input-full" name="entityConditionContent"></textarea>
        </td>
        <td>
            {standardScore}
        </td>
        <td>
            <input type="text"  class="form-control input-full" name="score" value="{score}">
        </td>
    </tr>
</script></html>
<script>
    $(function () {
        houseCommon.initDetailById('${basicHouse.id}', '', false);
    })
</script>


