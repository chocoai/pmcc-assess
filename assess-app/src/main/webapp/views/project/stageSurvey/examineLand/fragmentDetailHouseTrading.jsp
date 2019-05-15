<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">

    <div class="x_title">
        <h3>交易 </h3>
        <div class="clearfix"></div>
    </div>

    <form class="form-horizontal" id="basicHouseFrm">
        <!-- 不起作用只为了取得houseId -->
        <input type="hidden" name="id" value="${basicHouse.id}">
    </form>

    <form class="form-horizontal" id="basicTradingFrm">

        <input type="hidden" name="id" value="${basicHouseTrading.id}">

        <%@include file="/views/project/stageSurvey/examine/residence/detail/houseTradingBody.jsp" %>
    </form>




</div>