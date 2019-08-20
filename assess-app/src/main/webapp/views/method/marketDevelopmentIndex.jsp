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
                <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                     <input type="radio" id="developmentLand" name="development" value="1" checked="checked">
                    <label for="developmentLand">土地</label>
                </span>
                <span class="col-sm-2  checkbox-inline">
                    <input type="radio" id="developmentEngineering" name="development" value="2">
                    <label for="developmentEngineering">在建工程</label>
                </span>
            </div>
        </form>
    </div>

    <div class="x_content">
        <form class="form-horizontal" id="mdDevelopmentLandFrm">
            <%@include file="/views/method/module/developmentModule/landEngineering.jsp" %>
            <%@include file="/views/method/module/developmentModule/landEngineeringJs.jsp" %>
        </form>
    </div>

    <div class="x_content">
        <form class="form-horizontal" id="mdDevelopmentEngineeringFrm" style="display: none">
            <%@include file="/views/method/module/developmentModule/underConstruction.jsp" %>
            <%@include file="/views/method/module/developmentModule/underConstructionJs.jsp" %>
        </form>
    </div>

</div>

<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRate.jsp" %>

<script>

    function checkParams(that) {
        if (!development.isNotBlank(that)) {
            return false;
        }
        var value = $(that).val();
        var i = 0;
        if (!development.isNotBlank(value)) {
            return false;
        }
        if ($.isNumeric(value)) {
            i++;
        }
        var reg = new RegExp(/^[0-9]+\.?[0-9]*%$/);
        if (reg.test(value)) {
            i++;
        }
        if (i == 0) {
            alert("不符合，必须是数字!");
            $(that).attr("data-value", '');
            $(that).val('');
            return false;
        }
    }

    function projectConstructionPeriodFun(_this) {
        var target = $(_this).closest("form") ;
        var developedYear = target.find("input[name='developedYear']").val() ;
        var remainingDevelopmentYear = target.find("input[name='remainingDevelopmentYear']").val() ;
        if (!AssessCommon.isNumber(developedYear)) {
            return false;
        }
        if (!AssessCommon.isNumber(remainingDevelopmentYear)) {
            return false;
        }
        var c = 0;
        if (development.isNotBlank(developedYear)) {
            c += Number(developedYear);
        }
        if (development.isNotBlank(remainingDevelopmentYear)) {
            c += Number(remainingDevelopmentYear);
        }
        target.find("input[name='projectConstructionPeriod']").val(c) ;
        target.find("input[name='projectConstructionPeriod']").trigger('blur');
    }

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

    //参数校验
    development.checkParams = function (that) {
        if (!development.isNotBlank(that)) {
            return false;
        }
        var value = $(that).val();
        var i = 0;
        if (!development.isNotBlank(value)) {
            return false;
        }
        var reg = new RegExp(/^[0-9]+\.?[0-9]*%$/);
        if (reg.test(value)) {
            i++;
        }
        if ($.isNumeric(value)) {
            i++;
        }
        if (i == 0) {
            alert("不符合，必须是数字!");
            $(that).attr("data-value", '');
            $(that).val('');
            return false;
        }
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
        var data = formSerializeArray(frm) ;
        if (!$(development.config.frm).valid()) {
            return false;
        }
        if (!frm.valid()) {
            return false;
        }
        if (callback){
            callback() ;
        }
    };

    development.getFomData = function () {
        var head = formSerializeArray($(development.config.frm)) ;
        var frm = undefined;
        if (head.development == '1') {
            frm = landEngineering.target ;
        }
        if (head.development == '2') {
            frm = underConstruction.target ;
        }
        var data = formSerializeArray(frm) ;
        data.id = development.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        data.type = head.development ;
        data.price = frm.find("input[name='price']").val() ;
        data.planDetailsId = '${projectPlanDetails.id}' ;
        return data ;
    };

    development.initData = function () {
        var landFrm = $(development.config.land.frm) ;
        var engineeringFrm = $(development.config.engineering.frm) ;
        if (!development.isNotBlank('${mdDevelopment.id}')){
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: {province:'${schemeAreaGroup.province}',city:'${schemeAreaGroup.city}',district:'${schemeAreaGroup.district}',bisNationalUnity:true},
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
        landEngineering.loadMdDevelopmentInfrastructureChildrenTable() ;
        underConstruction.loadMdDevelopmentInfrastructureChildrenTable() ;
        landEngineering.loadIncomeCategoryTable();
        underConstruction.loadIncomeCategoryTable();
        landEngineering.unsaleableBuildingAreaFunHandle() ;
        underConstruction.unsaleableBuildingAreaFunHandle() ;
        landEngineering.inputBlurEvent() ;
        underConstruction.inputBlurEvent() ;
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
            }
            if (type == '2') {
                $(development.config.land.frm).hide();
                $(development.config.engineering.frm).show();
                $("#developmentEngineering").attr('checked','true') ;
            }
        }
    });
</script>