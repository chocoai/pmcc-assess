<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 假设开发法 -->
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>假设开发法</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal" id="developmentFrm">
            <input type="hidden" name="id" value="${mdDevelopment.id}">
            <div class="col-sm-12 form-group">
                <span class="col-sm-1">
                    <label>经营方式</label><span class="symbol required"></span>
                </span>
                <c:if test="${mdDevelopment.type == 1}">
                    <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                         <input type="radio" id="developmentLand" name="development" value="1" checked="checked">
                        <label for="developmentLand">土地</label>
                    </span>
                </c:if>
                <c:if test="${mdDevelopment.type == 2}">
                   <span class="col-sm-2  checkbox-inline">
                    <input type="radio" id="developmentEngineering" name="development" value="2" checked="checked">
                    <label for="developmentEngineering">在建工程</label>
                    </span>
                </c:if>
            </div>
        </form>
    </div>

    <div class="x_content">
        <c:if test="${mdDevelopment.type == 1}">
            <form class="form-horizontal" id="mdDevelopmentLandFrm">
                <%@include file="/views/method/module/developmentModule/landEngineeringDetail.jsp" %>
            </form>
        </c:if>
    </div>

    <div class="x_content">
        <c:if test="${mdDevelopment.type == 2}">
            <form class="form-horizontal" id="mdDevelopmentEngineeringFrm">
                <%@include file="/views/method/module/developmentModule/underConstructionDetail.jsp" %>
            </form>
        </c:if>
    </div>

</div>

<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRateDetail.jsp" %>

<script>
    var development = {};

    development.config = {
        frm: "#developmentFrm",
        land: {
            frm: "#mdDevelopmentLandFrm"
        },
        engineering: {
            frm: "#mdDevelopmentEngineeringFrm"
        }
    };
    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    development.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    development.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };





</script>

