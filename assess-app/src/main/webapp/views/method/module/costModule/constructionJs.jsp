
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var construction = {};

    construction.target = $("#constructionFrm");
    construction.fixed = 2; //小数点保留2位
    construction.fixedMax = 4; //小数点保留4位
    construction.fixedMin = 0; //小数点保留0位

    construction.callCompareMethod = function (this_) {

    };

    construction.calculationE6 = function () {
        //=D3*D6/10000
        var d3 = construction.target.find("input[name='developLandAreaTax']").val() ;
        var d6 = construction.target.find("input[name='landPurchasePrice']").val() ;
        if (!AssessCommon.isNumber(d3)) {
            return false;
        }
        if (!AssessCommon.isNumber(d6)) {
            return false;
        }
        var c = math.chain(d3).multiply(d6).divide(10000) ;
        construction.target.find("input[name='e6']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE7 = function () {
        //=ROUND(E6*D7,2)
        var d7 = construction.target.find("input[name='landGetRelevant']").attr("data-value") ;
        var e6 = construction.target.find("input[name='e6']").val() ;
        if (!AssessCommon.isNumber(e6)) {
            return false;
        }
        if (!AssessCommon.isNumber(d7)) {
            return false;
        }
        var c = math.chain(e6).multiply(d7) ;
        construction.target.find("input[name='e7']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationD8 = function () {
        //=E8/D3*10000
        var d3 = construction.target.find("input[name='developLandAreaTax']").val() ;
        var e8 = construction.target.find("input[name='additionalCostLandAcquisition']").val() ;
        if (!AssessCommon.isNumber(d3)) {
            return false;
        }
        if (!AssessCommon.isNumber(e8)) {
            return false;
        }
        var c = math.chain(e8).divide(d3).multiply(10000) ;
        construction.target.find("input[name='d8']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationLandGetCostTotalE9 = function () {
        //=SUM(E6:F8)
        var e6 = construction.target.find("input[name='e6']").val() ;
        var e7 = construction.target.find("input[name='e7']").val() ;
        var e8 = construction.target.find("input[name='additionalCostLandAcquisition']").val() ;
        if (!AssessCommon.isNumber(e6)) {
            return false;
        }
        if (!AssessCommon.isNumber(e7)) {
            return false;
        }
        if (!AssessCommon.isNumber(e8)) {
            return false;
        }
        var c = math.chain(e6).add(e7).add(e8) ;
        c = c.value.toFixed(construction.fixed) ;
        construction.target.find("input[name='landGetCostTotal']").val(c).trigger('blur') ;
        construction.target.find(".landGetCostTotal").html(c) ;
    };

    construction.calculationE12 = function () {
        //=F3*D12/10000
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        var d12 = construction.target.find("input[name='constructionInstallationEngineeringFee']").val();
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        if (!AssessCommon.isNumber(d12)) {
            return false;
        }
        var c = math.chain(f3).multiply(d12).divide(10000) ;
        construction.target.find("input[name='e12']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE11 = function () {
        //=IF(E12=" "," ",ROUND(E12*D11,2))
        var e12 = construction.target.find("input[name='e12']").val();
        var d11 = construction.target.find("input[name='reconnaissanceDesign']").attr("data-value");
        if (!AssessCommon.isNumber(e12)) {
            return false;
        }
        if (!AssessCommon.isNumber(d11)) {
            return false;
        }
        var c = math.chain(e12).multiply(d11) ;
        construction.target.find("input[name='e11']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE13 = function () {
        //=$F$3*D13/10000
        var d13 = construction.target.find("select[name='infrastructureCost']").val() ;
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d13)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d13).multiply(f3).divide(10000) ;
        construction.target.find("input[name='e13']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE14 = function () {
        //=$F$3*D14/10000
        var d14 = construction.target.find("select[name='infrastructureMatchingCost']").val() ;
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d14)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d14).multiply(f3).divide(10000) ;
        construction.target.find("input[name='e14']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE15 = function () {
        //=$F$3*D15/10000
        var d15 = construction.target.find("select[name='devDuring']").val() ;
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d15)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d15).multiply(f3).divide(10000) ;
        construction.target.find("input[name='e15']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE16 = function () {
        //=$F$3*D16/10000
        var d16 = construction.target.find("input[name='otherEngineeringCost']").val() ;
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d16)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d16).multiply(f3).divide(10000) ;
        construction.target.find("input[name='e16']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE17 = function () {
        //=SUM(E11:F16)
        var e11 = construction.target.find("input[name='e11']").val();
        var e12 = construction.target.find("input[name='e12']").val();
        var e13 = construction.target.find("input[name='e13']").val();
        var e14 = construction.target.find("input[name='e14']").val();
        var e15 = construction.target.find("input[name='e15']").val();
        var e16 = construction.target.find("input[name='e16']").val();
        if (!AssessCommon.isNumber(e11)) {
            return false;
        }
        if (!AssessCommon.isNumber(e12)) {
            return false;
        }
        if (!AssessCommon.isNumber(e13)) {
            return false;
        }
        if (!AssessCommon.isNumber(e14)) {
            return false;
        }
        if (!AssessCommon.isNumber(e15)) {
            return false;
        }
        if (!AssessCommon.isNumber(e16)) {
            return false;
        }
        var c = math.chain(e11).add(e12).add(e13).add(e14).add(e15).add(e16) ;
        c = c.value.toFixed(construction.fixed) ;
        construction.target.find("input[name='constructionSubtotal']").val(c).trigger('blur') ;
        construction.target.find(".constructionSubtotal").html(c) ;
    };

    construction.calculationE18 = function () {
        //=IF(E17=" "," ",ROUND(E17*D18,2))
        var e17 = construction.target.find("input[name='constructionSubtotal']").val() ;
        var d18 = construction.target.find("input[name='unforeseenExpenses']").attr("data-value");
        var c = math.chain(e17).multiply(d18);
        construction.target.find("input[name='e18']").val(c.value.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE19 = function () {
        //=IF(E17=" "," ",(E9+E17+E18)*D19)
        var e9 = construction.target.find("input[name='landGetCostTotal']").val() ;
        var e17 = construction.target.find("input[name='constructionSubtotal']").val() ;
        var e18 = construction.target.find("input[name='e18']").val();
        var d19 = construction.target.find("input[name='managementExpense']").attr("data-value");
        if (!AssessCommon.isNumber(e9)) {
            return false;
        }
        if (!AssessCommon.isNumber(e17)) {
            return false;
        }
        if (!AssessCommon.isNumber(e18)) {
            return false;
        }
        if (!AssessCommon.isNumber(d19)) {
            return false;
        }
        var c = math.multiply( math.chain(e9).add(e17).add(e18).done(),  d19) ;
        construction.target.find("input[name='e19']").val(c.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.calculationE21 = function () {
        //=IF(F3=" "," ",(E9+E11+E13)*((1+D21)^H3-1)+(E12+E14+E15+E16+E18+E19)*((1+D21)^(H3/2)-1))
        var e9 = construction.target.find("input[name='landGetCostTotal']").val() ;
        var e11 = construction.target.find("input[name='e11']").val();
        var e12 = construction.target.find("input[name='e12']").val();
        var e13 = construction.target.find("input[name='e13']").val();
        var e14 = construction.target.find("input[name='e14']").val();
        var e15 = construction.target.find("input[name='e15']").val();
        var e16 = construction.target.find("input[name='e16']").val();
        var e18 = construction.target.find("input[name='e18']").val();
        var e19 = construction.target.find("input[name='e19']").val() ;
        var d21 = construction.target.find("input[name='interestInvestmentTax']").attr("data-value");
        var h3 = construction.target.find("input[name='developYearNumberTax']").val();

        try {
            var a =  Math.pow(1+math.number(d21),math.number(h3)) ;
            var c1 = math.multiply(  math.chain(e9).add(e11).add(e13).done() ,math.chain( a  ).subtract(1).done() )  ;
            var b = Math.pow(1+math.number(d21),math.number(h3) / 2) ;
            var c2 = math.multiply(  math.chain(e12).add(e14).add(e15).add(e16).add(e18).add(e19).done() ,math.chain(  b ).subtract(1).done() )  ;
            var c = math.add(c1,c2) ;
            c = c.toFixed(construction.fixed) ;
            construction.target.find("input[name='interestInvestment']").val(c).trigger('blur') ;
            construction.target.find(".interestInvestment").html(c) ;
        } catch (e) {

        }
    };

    construction.calculationG21 = function () {
        //=ROUND(D20*((1+D21)^(H3/2)-1),5)
        var d20 = construction.target.find("input[name='salesFee']").attr("data-value");
        var d21 = construction.target.find("input[name='interestInvestmentTax']").attr("data-value");
        var h3 = construction.target.find("input[name='developYearNumberTax']").val();
        if (!AssessCommon.isNumber(d20)) {
            return false;
        }
        if (!AssessCommon.isNumber(d21)) {
            return false;
        }
        if (!AssessCommon.isNumber(h3)) {
            return false;
        }
        var a = Math.pow(1+math.number(d21),math.number(h3) / 2) ;
        var c = math.multiply(d20, math.subtract(a,1)) ;
        construction.target.find("input[name='g21']").val(c.toFixed(5)).trigger('blur') ;
    };

    construction.calculationG23 = function () {
        //=ROUND(D23*D20,5)
        var d23 = construction.target.find("input[name='investmentProfitTax']").attr("data-value");
        var d20 = construction.target.find("input[name='salesFee']").attr("data-value");
        if (!AssessCommon.isNumber(d20)) {
            return false;
        }
        if (!AssessCommon.isNumber(d23)) {
            return false;
        }
        var c = math.chain(d23).multiply(d20).done() ;
        construction.target.find("input[name='g23']").val(c.toFixed(5)).trigger('blur') ;
    };

    construction.calculationG24 = function () {
        //=ROUND(D20+G21+D22+G23,4)
        var d20 = construction.target.find("input[name='salesFee']").attr("data-value");
        var d22 = construction.target.find("input[name='salesTaxAndAdditional']").attr("data-value");
        var g21 = construction.target.find("input[name='g21']").val() ;
        var g23 = construction.target.find("input[name='g23']").val() ;
        if (!AssessCommon.isNumber(d20)) {
            return false;
        }
        if (!AssessCommon.isNumber(d22)) {
            return false;
        }
        if (!AssessCommon.isNumber(g21)) {
            return false;
        }
        if (!AssessCommon.isNumber(g23)) {
            return false;
        }
        var c = math.chain(d20).add(g21).add(d22).add(g23).done() ;
        construction.target.find("input[name='g24']").val(c.toFixed(4)).trigger('blur') ;
    };

    construction.calculationE23 = function () {
        //=IF(D9=" "," ",(E9+E17+E18+E19)*D23)
        var d23 = construction.target.find("input[name='investmentProfitTax']").attr("data-value");
        var e9 = construction.target.find("input[name='landGetCostTotal']").val() ;
        var e17 = construction.target.find("input[name='constructionSubtotal']").val() ;
        var e18 = construction.target.find("input[name='e18']").val();
        var e19 = construction.target.find("input[name='e19']").val() ;
        if (!AssessCommon.isNumber(e9)) {
            return false;
        }
        if (!AssessCommon.isNumber(e17)) {
            return false;
        }
        if (!AssessCommon.isNumber(e18)) {
            return false;
        }
        if (!AssessCommon.isNumber(e19)) {
            return false;
        }
        if (!AssessCommon.isNumber(d23)) {
            return false;
        }
        var c = math.multiply(d23 , math.chain(e9).add(e17).add(e18).add(e19).done()) ;
        c = c.toFixed(construction.fixed) ;
        construction.target.find("input[name='investmentProfit']").val(c).trigger('blur') ;
        construction.target.find(".investmentProfit").html(c) ;
    };

    construction.constructionAssessmentValue2calculationE24 = function () {
        //=IF(D9=" "," ",E9+E17+E18+E19+E21+E23)
        var e23 = construction.target.find("input[name='investmentProfit']").val() ;
        var e21 = construction.target.find("input[name='interestInvestment']").val() ;
        var e9 = construction.target.find("input[name='landGetCostTotal']").val() ;
        var e17 = construction.target.find("input[name='constructionSubtotal']").val() ;
        var e18 = construction.target.find("input[name='e18']").val();
        var e19 = construction.target.find("input[name='e19']").val() ;
        if (!AssessCommon.isNumber(e21)) {
            return false;
        }
        if (!AssessCommon.isNumber(e23)) {
            return false;
        }
        var c = math.chain(e9).add(e17).add(e18).add(e19).add(e21).add(e23).done() ;
        construction.target.find("input[name='constructionAssessmentValue2']").val(c.toFixed(construction.fixed)).trigger('blur') ;
    };

    construction.constructionAssessmentValueCalculationE25 = function () {
        //=IF(E24=" "," ",E24/(1-G24))
        var e24 = construction.target.find("input[name='constructionAssessmentValue2']").val() ;
        var g24 = construction.target.find("input[name='g24']").val() ;
        if (!AssessCommon.isNumber(e24)) {
            return false;
        }
        if (!AssessCommon.isNumber(g24)) {
            return false;
        }
        var c = math.chain(e24).divide(1-math.number(g24)).done() ;
        c = c.toFixed(construction.fixed) ;
        construction.target.find("input[name='constructionAssessmentValue']").val(c).trigger('blur') ;
        construction.target.find(".constructionAssessmentValue").html(c) ;
    };

    construction.constructionAssessmentPriceCorrectingCalculationE26 = function () {
        //=IF(E25=" "," ",E25*10000/F3)
        var e25 = construction.target.find("input[name='constructionAssessmentValue']").val() ;
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(e25)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(e25).multiply(10000).divide(f3).done() ;
        c = c.toFixed(construction.fixed) ;
        construction.target.find("input[name='constructionAssessmentPriceCorrecting']").val(c).trigger('blur') ;
        construction.target.find(".constructionAssessmentPriceCorrecting").html(c) ;
    };

    construction.constructionInstallationEngineeringFeeEvent = {
        show:function () {
            var target = $("#boxMdCostConstruction");
            if (target.find(".panel-body").find("table").size() == 0) {
                target.find(".panel-body").append(developmentCommon.architecturalB.getHtml());
                developmentCommon.architecturalB.treeGirdParse(target);
            }
            var pid = 0;
            if (developmentCommon.isNotBlank('${mdCostConstruction}')){
                if (developmentCommon.isNotBlank('${mdCostConstruction.id}')){
                    pid = '${mdCostConstruction.id}' ;
                }
            }
            developmentCommon.architecturalB.getData("mdCostConstruction",AssessDBKey.MdCost,pid,'${projectPlanDetails.id}',function (data) {
                var item = undefined ;
                if (data.length >= 1){
                    var n = data[0] ;
                    if (n.jsonContent){
                        try {
                            item = JSON.parse(n.jsonContent) ;
                        } catch (e) {
                            console.log("解析异常!") ;
                        }
                    }
                }
                if (item){
                    developmentCommon.architecturalB.initData(target.find("table"),item) ;
                }
            }) ;
            target.modal("show");
        },
        save:function () {
            var target = $("#boxMdCostConstruction");
            var table = target.find("table");
            var value = table.find("tfoot").find("input[name='totalPrice']").first().val();
            if (!AssessCommon.isNumber(value)) {
                toastr.success('请重新填写!');
                return false;
            }
            value = Number(value);
            construction.target.find("input[name='constructionInstallationEngineeringFee']").val(value.toFixed(construction.fixed)).trigger('blur');

            var data = developmentCommon.architecturalB.getFomData(table);
            var pid = 0;
            if (developmentCommon.isNotBlank('${mdCostConstruction}')){
                if (developmentCommon.isNotBlank('${mdCostConstruction.id}')){
                    pid = '${mdCostConstruction.id}' ;
                }
            }
            developmentCommon.saveMdArchitecturalObj(data , "mdCostConstruction" ,AssessDBKey.MdCost,pid , function () {

            }) ;
            target.modal("hide");
        }
    } ;



</script>