<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 房屋交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房屋交易信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicTradingFrm">
        <input type="hidden" name="id" value="${basicHouseTrading.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 scopeProperty" name="scopeProperty" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">范围包括</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="范围包含" name="scopeInclude" class="form-control"
                           value="${basicHouseTrading.scopeInclude}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">范围不包括</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="范围不包含" name="scopeNotInclude" class="form-control"
                           value="${basicHouseTrading.scopeNotInclude}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">税费负担<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 taxBurden" name="taxBurden" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易情况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control transactionSituation" name="transactionSituation" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div id="abnormalTransaction" style="display: none;">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">说明事项类型</label>
                    <div class="col-sm-3">
                        <select class="form-control descriptionType" name="descriptionType">
                        </select>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">说明事项内容</label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="说明事项内容" name="descriptionContent" class="form-control"
                               value="${basicHouseTrading.descriptionContent}">
                    </div>
                </div>
            </div>
        </div>
        <div class="x_title">融资条件</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">首付款比例<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="downPaymentRatio" placeholder="首付款比例" required>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">贷款利率<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="lendingRate" placeholder="贷款利率" required>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">贷款期限<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="loanPeriod" placeholder="贷款期限" required>
                </div>
            </div>
        </div>
        <div class="x_title">
            <div class="clearfix"></div>
        </div>
        <%@include file="./houseTradingBody.jsp" %>
    </form>
</div>



