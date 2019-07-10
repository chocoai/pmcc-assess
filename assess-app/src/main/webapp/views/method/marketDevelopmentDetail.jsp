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
            <input type="hidden" value='${mdDevelopmentJson}' id="mdDevelopmentJson">
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

            <div class="form-group">
                <!-- append html -->
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        项目建设期(年)
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.projectConstructionPeriod}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        已开发时间(年)
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.developedYear}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        剩余开发时间(年)
                    </label>
                    <div class="col-sm-3">
                        <label class="form-control">${mdDevelopment.remainingDevelopmentYear}</label>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="x_content">
        <c:if test="${mdDevelopment.type == 1}">
            <form class="form-horizontal" id="mdDevelopmentLandFrm">
                <div id="landParameter" class="x_panel">
                    <!-- append html -->
                </div>
                <jsp:include page="/views/method/module/developmentModule/landEngineeringDetail.jsp"></jsp:include>
            </form>
        </c:if>
    </div>

    <div class="x_content">
        <c:if test="${mdDevelopment.type == 2}">
            <form class="form-horizontal" id="mdDevelopmentEngineeringFrm">
                <div id="engineeringParameter" class="x_panel">
                    <!-- append html -->
                </div>
                <jsp:include page="/views/method/module/developmentModule/underConstructionDetail.jsp"></jsp:include>
            </form>
        </c:if>
    </div>

</div>

<jsp:include page="/views/method/module/developmentModule/developmentCommon.jsp"></jsp:include>

<jsp:include page="/views/project/tool/rewardRateDetail.jsp"></jsp:include>

<script>
    var development = {};

    development.config = {
        frm: "#developmentFrm",
        land: {
            parameter: "#landParameter",
            frm: "#mdDevelopmentLandFrm"
        },
        engineering: {
            parameter: "#engineeringParameter",
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


    development.initData = function () {
        var landFrm = $("#mdDevelopmentLandFrm");
        var engineeringFrm = $("#mdDevelopmentEngineeringFrm");
        var head = undefined ;
        var data = undefined ;
        try {
            var mdDevelopmentJson = $("#mdDevelopmentJson").val() ;
            if (development.isNotBlank(mdDevelopmentJson)){
                data = JSON.parse(mdDevelopmentJson) ;
                if (data.headContent){
                    try {
                        head = JSON.parse(data.headContent) ;
                    } catch (e) {
                        console.log("解析错误!") ;
                    }
                }
            }
        } catch (e) {
            console.log("解析错误!") ;
        }
        //土地
        if (landFrm.find(development.config.land.parameter).find("." + developmentCommon.config.commonParameter.handle).size() == 0) {
            var html = developmentCommon.parameter.getHtml();
            landFrm.find(development.config.land.parameter).empty().append(html);
            landFrm.find("input[name='unsaleableBuildingArea']").attr('readonly', 'readonly');
            if (development.isNotBlankObject(head)){
                developmentCommon.parameter.initData(landFrm.find("table").first(),head) ;
            }
        }


        //在建工程
        if (engineeringFrm.find(development.config.engineering.parameter).find("." + developmentCommon.config.commonParameter.handle).size() == 0) {
            var html = developmentCommon.parameter.getHtml();
            engineeringFrm.find(development.config.engineering.parameter).empty().append(html);
            engineeringFrm.find("input[name='unsaleableBuildingArea']").attr('readonly', 'readonly');
            if (development.isNotBlankObject(head)){
                developmentCommon.parameter.initData(engineeringFrm.find("table").first(),head) ;
            }
        }
    };

    development.initData();


</script>

