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
            <input type="hidden" value='${mdDevelopmentJson}'>
            <div class="col-sm-12 form-group">
                <span class="col-sm-1">
                    <label>经营方式</label><span class="symbol required"></span>
                </span>
                <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                     <input type="radio" id="developmentLand" name="development" value="1" checked="checked">
                    <label for="developmentLand">土地</label>
                </span>
                <span class="col-sm-2  checkbox-inline">
                    <input type="radio" id="developmentEngineering" name="development" value="2">
                    <label for="developmentEngineering">在建工程</label>
                </span>
            </div>

            <div id="developmentHeadId" class="form-group">
            </div>
        </form>
    </div>

    <div class="x_content">
        <form class="form-horizontal">

        </form>
    </div>

</div>

<jsp:include page="/views/method/module/developmentModule/developmentCommon.jsp"></jsp:include>

<div class="developmentLand">

</div>

<div class="developmentEngineering" style="display: none;">

</div>

<script>
    var development = {} ;

    development.config = {
        headId:"#developmentHeadId",
        frm:"#developmentFrm"
    } ;
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
        var mdDevelopmentJson = $("#mdDevelopmentJson").val() ;
        var data = undefined ;
        if (development.isNotBlank(mdDevelopmentJson)){
            data = JSON.parse(mdDevelopmentJson) ;
        }
        if (development.isNotBlankObject(data)){

        }
        $(development.config.headId).empty().append(developmentCommon.head.getHtml()) ;
    };

    $(document).ready(function () {


        development.initData() ;


    }) ;

</script>

