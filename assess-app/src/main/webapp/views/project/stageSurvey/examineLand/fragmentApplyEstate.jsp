
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">

    <div class="x_title">
        <h3>区域 </h3>
        <div class="clearfix"></div>
    </div>

    <form id="frm_estate" class="form-horizontal">
        <input type="hidden" name="id" value="${basicEstate.id}">

        <%@include file="/views/project/stageSurvey/examine/residence/apply/estateHead.jsp" %>

        <%@include file="/views/project/stageSurvey/examine/residence/apply/estateFoot.jsp" %>

    </form>
</div>