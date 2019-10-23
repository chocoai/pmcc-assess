<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

        <%@include file="/views/project/stageSurvey/examine/residence/detail/houseTradingBody.jsp" %>
    </form>
</div>


