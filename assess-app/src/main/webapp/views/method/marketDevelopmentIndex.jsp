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

            <div  class="form-group">
                <!-- append html -->
                <div>
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            项目建设期(年)
                        </label>
                        <div class="col-sm-3">
                            <input type="text" value="${mdDevelopment.projectConstructionPeriod}"
                                   placeholder="项目建设期(年)"
                                   class="form-control" name="projectConstructionPeriod" onblur="landEngineering.calculationF34(); landEngineering.calculationD34();underConstruction.calculationF34(); underConstruction.calculationD34();">
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </div>

    <div class="x_content">
        <form class="form-horizontal" id="mdDevelopmentLandFrm">
            <div id="landParameter" class="x_panel">
                <!-- append html -->
            </div>
            <jsp:include page="/views/method/module/developmentModule/landEngineering.jsp"></jsp:include>
            <jsp:include page="/views/method/module/developmentModule/landEngineeringJs.jsp"></jsp:include>
        </form>
    </div>

    <div class="x_content">
        <form class="form-horizontal" id="mdDevelopmentEngineeringFrm" style="display: none">
            <div id="engineeringParameter" class="x_panel">
                <!-- append html -->
            </div>
            <jsp:include page="/views/method/module/developmentModule/underConstruction.jsp"></jsp:include>
            <jsp:include page="/views/method/module/developmentModule/underConstructionJs.jsp"></jsp:include>
        </form>
    </div>

</div>

<jsp:include page="/views/method/module/developmentModule/developmentCommon.jsp"></jsp:include>

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

    development.valid = function (callback) {
        var head = formSerializeArray($(development.config.frm)) ;
        var frm = undefined;
        if (head.development == '1') {
            frm = $(development.config.land.frm) ;
        }
        if (head.development == '2') {
            frm = $(development.config.engineering.frm) ;
        }
        if (!frm.valid()) {
            return false;
        }
        callback() ;
    };

    development.getFomData = function () {
        var head = formSerializeArray($(development.config.frm)) ;
        var frm = undefined;
        var headTable = undefined;
        if (head.development == '1') {
            frm = $(development.config.land.frm) ;
            headTable = $(development.config.land.parameter).find("table").first();
        }
        if (head.development == '2') {
            frm = $(development.config.engineering.frm) ;
            headTable = $(development.config.engineering.parameter).find("table").first();
        }
        var headData = developmentCommon.parameter.getFomData(headTable) ;
        var data = formSerializeArray(frm) ;
        $.extend(data, head);
        data.headContent = JSON.stringify(headData) ;
        data.type = data.development ;
        data.planDetailsId = '${projectPlanDetails.id}' ;
        data.price = data.d47 ;
        data.assessPrice = data.d41 ;
        data.constructionCostSubtotal = frm.find(".d26").html() ;
        data.interestInvestment = frm.find(".f34").html() ;
        data.investmentProfit = frm.find(".f35").html() ;
        return data ;
    };

    development.initData = function () {
        var landFrm = $(development.config.land.frm) ;
        var engineeringFrm = $(development.config.engineering.frm) ;

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
            var html = developmentCommon.parameter.getHtml() ;
            html = html.replace(/{funA5}/g,"landEngineering.calculationF18()") ;
            html = html.replace(/{funA3}/g,"landEngineering.calculationF18()") ;
            html = html.replace(/{funA2}/g,"landEngineering.calculationF33();landEngineering.calculationF40();landEngineering.calculationF36()") ;
            landFrm.find(development.config.land.parameter).empty().append(html);
        }

//        landFrm.validate();
//        engineeringFrm.validate();

        //在建工程
        if (engineeringFrm.find(development.config.engineering.parameter).find("." + developmentCommon.config.commonParameter.handle).size() == 0) {
            var html = developmentCommon.parameter.getHtml() ;
            html = html.replace(/{funA5}/g,"underConstruction.calculationF18()") ;
            html = html.replace(/{funA3}/g,"underConstruction.calculationF18()") ;
            html = html.replace(/{funA2}/g,"underConstruction.calculationF33();underConstruction.calculationF40();underConstruction.calculationF36()") ;
            engineeringFrm.find(development.config.engineering.parameter).empty().append(html);
        }
        if (!development.isNotBlank(data)){
            var query = {province:'${schemeAreaGroup.province}',city:'${schemeAreaGroup.city}',district:'${schemeAreaGroup.district}',bisNationalUnity:true} ;
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: query,
                success: function (result) {
                    if (result.ret) {
                        if (result.data){
                            $.each(result.data,function (i,item) {
                                var taxRate = item.taxRate ;
                                if (item.taxRate){
                                    taxRate = Number(item.taxRate) * 100 ;
                                    taxRate = taxRate + "%" ;
                                }
                                if (item.typeName == '增值税'){
                                    if (item.taxRate){
                                        landFrm.find("input[name='f38']").val(taxRate) ;
                                        landFrm.find("input[name='f38']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.typeName == '契税'){
                                    if (item.taxRate){
                                        landFrm.find("input[name='f29']").val(taxRate) ;
                                        landFrm.find("input[name='f29']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.typeName == '所得税'){
                                    if (item.taxRate){
                                        landFrm.find("input[name='f39']").val(taxRate) ;
                                        landFrm.find("input[name='f39']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.typeName == '其它税费'){
                                    if (item.taxRate){
                                        landFrm.find("input[name='f25']").val(taxRate) ;
                                        landFrm.find("input[name='f25']").attr("data-value",item.taxRate) ;
                                    }
                                }
                            });
                        }
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
        if (development.isNotBlank(head)){
            developmentCommon.parameter.editableInit(function () {
                var type = '${mdDevelopment.type}' ;
                if (type == '1'){
                    developmentCommon.parameter.initData(landFrm.find("table").first(),head) ;
                }
                if (type == '2'){
                    developmentCommon.parameter.initData(engineeringFrm.find("table").first(),head) ;
                }
            });
        }else {
            //开启编辑
            developmentCommon.parameter.editableInit();
        }
    };

    $(document).ready(function () {

        development.initData();

        var frm = $(development.config.frm) ;
        frm.find("input[type='radio'][name='development']").change(function () {
            var data = formSerializeArray(frm);
            if (data.development == '1') {
                $(development.config.land.frm).show();
                $(development.config.engineering.frm).hide();
            }
            if (data.development == '2') {
                $(development.config.land.frm).hide();
                $(development.config.engineering.frm).show();
            }
        });
        var type = '${mdDevelopment.type}' ;
        if (development.isNotBlank(type)){
            if (type == '1') {
                $(development.config.land.frm).show();
                $(development.config.engineering.frm).hide();
                $("#developmentLand").attr('checked','true') ;
                $("#developmentEngineering").attr('checked','false') ;
            }
            if (type == '2') {

                $(development.config.land.frm).hide();
                $(development.config.engineering.frm).show();
                $("#developmentLand").attr('checked','false') ;
                $("#developmentEngineering").attr('checked','true') ;
            }
        }
    });

</script>

