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
        <%@include file="/views/method/module/developmentModule/landEngineering.jsp" %>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js"></script>
<%@include file="/views/method/module/developmentModule/landEngineeringJs.jsp" %>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRate.jsp" %>
<%@include file="/views/method/module/economicIndicators.jsp" %>

<script>




    var development = {};

    development.config = {
        frm: "#mdDevelopmentLandFrm"
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
        var target = $(development.config.frm);
        calculationNumeric(formSerializeArray(target),function (data) {
            target.find("td[name='price']").html(data.price);
            target.find("input[name='price']").val(data.price);
            target.find("td[name='constructionCostSubtotal']").html(data.constructionCostSubtotal);
            target.find("input[name='constructionCostSubtotal']").val(data.constructionCostSubtotal);
            target.find("td[name='interestInvestment']").html(data.interestInvestment);
            target.find("input[name='interestInvestment']").val(data.interestInvestment);
            target.find("td[name='investmentProfit']").html(data.investmentProfit);
            target.find("input[name='investmentProfit']").val(data.investmentProfit);
            target.find("td[name='assessPrice']").html(data.assessPrice);
            target.find("input[name='assessPrice']").val(data.assessPrice);
            target.find("input[name='projectConstructionPeriod']").val(data.projectConstructionPeriod);
            if ($("#md_development_form").size() != 0){
                $("#md_development_form") .find("input[name='price']").val(data.price) ;
            }
        });
    }

    function calculationNumeric(data,callback) {
        $.ajax({
            type: "post",
            url: getContextPath() +"/mdDevelopment/calculationNumeric",
            data: {fomData:JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback){
                        callback(result.data) ;
                    }
                }else {
//                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


    development.valid = function (callback) {
        var frm = $(development.config.frm);
        if (!frm.valid()) {
            return false;
        }
        if (callback){
            callback() ;
        }
    };

    development.getFomData = function () {
        var frm = $(development.config.frm);
        var data = formSerializeArray(frm) ;
        data.planDetailsId = '${projectPlanDetails.id}' ;
        return data ;
    };

    development.initData = function () {
        var frm = $(development.config.frm);
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
                                        frm.find("input[name='landValueAddedTax']").val(taxRate) ;
                                        frm.find("input[name='landValueAddedTax']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.typeName == '契税'){
                                    if (item.taxRate){
                                        frm.find("input[name='deedTaxRate']").val(taxRate) ;
                                        frm.find("input[name='deedTaxRate']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.typeName == '所得税'){
                                    if (item.taxRate){
                                        frm.find("input[name='projectDevelopmentIncomeTax']").val(taxRate) ;
                                        frm.find("input[name='projectDevelopmentIncomeTax']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.typeName == '其它税费'){
                                    if (item.taxRate){
                                        frm.find("input[name='otherEngineeringCost']").val(taxRate) ;
                                        frm.find("input[name='otherEngineeringCost']").attr("data-value",item.taxRate) ;
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
        landEngineering.unsaleableBuildingAreaFunHandle() ;
        landEngineering.loadMdCalculatingMethodEngineeringCostTable('${!empty mdDevelopment.type}'?'${mdDevelopment.type}':undefined) ;
    };

    development.writeValueEvent = function (value) {
        var frm = $(development.config.frm);
        if (value == 1){
            var managementExpense = frm.find("input[placeholder='续建管理费率']") ;
            managementExpense.attr({placeholder:"管理费率"}) ;
            managementExpense.closest(".form-group").find("label").first().text("管理费率") ;

            var landGetRelevant = frm.find("input[placeholder='在建工程修复费用']") ;
            landGetRelevant.attr({placeholder:"土地取得附加成本"}) ;
            landGetRelevant.closest(".form-group").find("label").first().text("土地取得附加成本") ;

            var interestInvestmentTax = frm.find("input[placeholder='续建投资利息率']") ;
            interestInvestmentTax.attr({placeholder:"投资利息率"}) ;
            interestInvestmentTax.closest(".form-group").find("label").first().text("投资利息率") ;

            var investmentProfitTax = frm.find("input[placeholder='续建投资利润率']") ;
            investmentProfitTax.attr({placeholder:"投资利润率"}) ;
            investmentProfitTax.closest(".form-group").find("label").first().text("投资利润率") ;
        }
        if (value == 2){
            var managementExpense = frm.find("input[placeholder='管理费率']") ;
            managementExpense.attr({placeholder:"续建管理费率"}) ;
            managementExpense.closest(".form-group").find("label").first().text("续建管理费率") ;

            var landGetRelevant = frm.find("input[placeholder='土地取得附加成本']") ;
            landGetRelevant.attr({placeholder:"在建工程修复费用"}) ;
            landGetRelevant.closest(".form-group").find("label").first().text("在建工程修复费用") ;

            var interestInvestmentTax = frm.find("input[placeholder='投资利息率']") ;
            interestInvestmentTax.attr({placeholder:"续建投资利息率"}) ;
            interestInvestmentTax.closest(".form-group").find("label").first().text("续建投资利息率") ;

            var investmentProfitTax = frm.find("input[placeholder='投资利润率']") ;
            investmentProfitTax.attr({placeholder:"续建投资利润率"}) ;
            investmentProfitTax.closest(".form-group").find("label").first().text("续建投资利润率") ;
        }
    };

    $(document).ready(function () {

        development.initData();

        var frm = $(development.config.frm);
        var type = '${mdDevelopment.type}' ;
        if (development.isNotBlank(type)){
            development.writeValueEvent(type) ;
            if (type == '1') {
                $("#developmentLand").attr('checked','true').trigger('change');
                $("#developmentEngineering").attr('checked','false').trigger('change');
            }
            if (type == '2') {
                $("#developmentEngineering").attr('checked','true').trigger('change');
                $("#developmentLand").attr('checked','false').trigger('change');
            }
            landEngineering.loadMdCalculatingMethodEngineeringCostTable(type) ;
            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
        }
        frm.find("input[type='radio'][name='type']").change(function () {
            var value = $(this).val();
            landEngineering.loadMdCalculatingMethodEngineeringCostTable() ;
            development.writeValueEvent(value) ;
        });
    });
</script>