<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  楼盘基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_estate" class="form-horizontal">
    <input type="hidden" name="id" value="${basicEstate.id}">

    <div class="x_content">
        <div class="x_title">
            <h3>
                楼盘基本信息
            </h3>
            <div class="clearfix"></div>
        </div>

        <%@include file="/views/project/stageSurvey/examine/residence/detail/estateHead.jsp" %>
        <%@include file="./estateBody.jsp" %>
    </div>

</form>
