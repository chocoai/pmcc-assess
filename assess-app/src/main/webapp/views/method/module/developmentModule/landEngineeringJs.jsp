<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var landEngineering = {};
    landEngineering.target = $("#mdDevelopmentLandFrm");
    landEngineering.infrastructureChildrenTable = $("#landMdDevelopmentInfrastructureChildrenTable");
    landEngineering.infrastructureFooterHtml = "#landEngineeringMdDevelopmentInfrastructureFooter";
    landEngineering.incomeCategoryTable = $("#landIncomeCategoryTableId");
    landEngineering.incomeCategoryFooterHtml = "#landMdDevelopmentIncomeCategoryFooter";
    landEngineering.fixed = 2; //小数点保留2位
    landEngineering.fixedMax = 4; //小数点保留4位
    landEngineering.fixedMin = 0; //小数点保留0位
    landEngineering.type = 'land' ;


    //单元格f18
    landEngineering.calculationF18 = function () {
        var a = this.target.find("input[name='unsaleableBuildingArea']").val();
        var b = this.target.find("input[name='saleableArea']").val();
        var c = 0;
        if (development.isNotBlank(a)) {
            c += Number(a);
        }
        if (development.isNotBlank(b)) {
            c += Number(b);
        }
        this.target.find("input[name='f18']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='reconnaissanceDesign']").trigger('blur');
        this.target.find("input[name='infrastructureCost']").trigger('blur');
        this.target.find("input[name='infrastructureMatchingCost']").trigger('blur');
        this.target.find("input[name='devDuring']").trigger('blur');
        this.target.find("input[name='otherEngineeringCost']").trigger('blur');
    };

    //单元格D20
    landEngineering.calculationD20 = function () {
        console.log("calculationD20");
        var f18 = this.target.find("input[name='f18']").val();
        var f20 = this.target.find("input[name='reconnaissanceDesign']").attr("data-value");
        var f21 = this.target.find("input[name='constructionInstallationEngineeringFee']").val();
        if (!AssessCommon.isNumber(f20)) {
            return false;
        }
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f21)) {
            return false;
        }
        var c = Number(f18) * Number(f21) * Number(f20) / 10000;
        landEngineering.target.find("input[name='d20']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='constructionCostSubtotal']").trigger('blur');
    };

    //单元格d21
    landEngineering.calculationD21 = function () {
        console.log("calculationD21");
        var f18 = this.target.find("input[name='f18']").val();
        var f21 = landEngineering.target.find("input[name='constructionInstallationEngineeringFee']").val();
        var c = Number(f21) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d21']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='constructionCostSubtotal']").trigger('blur');
    };

    //单元格d22
    landEngineering.calculationD22 = function () {
        console.log("calculationD22");
        var f18 = this.target.find("input[name='f18']").val();
        var f22 = this.target.find("input[name='infrastructureCost']").val();
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f22)) {
            return false;
        }
        var c = Number(f22) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d22']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='constructionCostSubtotal']").trigger('blur');
    };

    landEngineering.calculationF22 = function (_this) {
        var item = $(_this).find('option:selected') ;
        var pid = item.attr('data-key') ;
        var type = item.attr('data-type') ;
        if (pid){
            $.ajax({
                url: "${pageContext.request.contextPath}/dataInfrastructureChildren/getDataList",
                type: "get",
                dataType: "json",
                data: {pid:pid,type:type},
                success: function (result) {
                    if (result.ret) {
                        var arr = [] ;
                        var data = result.data ;
                        if (data.length == 0){
                            return false ;
                        }
                        $.each(data,function (i,n) {
                            var obj = {name:n.name,number:n.number,tax:n.tax} ;
                            obj.planDetailsId = '${projectPlanDetails.id}' ;
                            obj.type = landEngineering.type ;
                            obj.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
                            arr.push(obj) ;
                        });
                        developmentCommon.infrastructureChildren.saveArray(arr,function () {
                            toastr.success('添加成功!');
                            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                            landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
                        });
                    }
                    else {
                        Alert("失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }else {
            toastr.success('无子项!');
        }
    };

    //单元格d23
    landEngineering.calculationD23 = function () {
        console.log("calculationD23");
        var f18 = this.target.find("input[name='f18']").val();
        var f23 = this.target.find("input[name='infrastructureMatchingCost']").val();
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f23)) {
            return false;
        }
        var c = Number(f23) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d23']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='constructionCostSubtotal']").trigger('blur');
    };

    //单元格d24
    landEngineering.calculationD24 = function () {
        console.log("calculationD24");
        var f18 = this.target.find("input[name='f18']").val();
        var f24 = this.target.find("input[name='devDuring']").val();
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f24)) {
            return false;
        }
        var c = Number(f24) * Number(f18) / 10000;
        this.target.find("input[name='d24']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='constructionCostSubtotal']").trigger('blur');
    };



    //单元格Dd25
    landEngineering.calculationD25 = function () {
        console.log("calculationD25");
        var f18 = this.target.find("input[name='f18']").val();
        var f25 = this.target.find("input[name='otherEngineeringCost']").attr("data-value");
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f25)) {
            return false;
        }
        var c = Number(f25) * Number(f18) / 10000;
        this.target.find("input[name='d25']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='constructionCostSubtotal']").trigger('blur');
    };

    //单元格D26
    landEngineering.calculationD26 = function () {
        console.log("calculationD26");
        var d20 = this.target.find("input[name='d20']").val();
        var d21 = this.target.find("input[name='d21']").val();
        var d22 = this.target.find("input[name='d22']").val();
        var d23 = this.target.find("input[name='d23']").val();
        var d24 = this.target.find("input[name='d24']").val();
        var d25 = this.target.find("input[name='d25']").val();
        var arr = [];
        if (development.isNotBlank(d20)) {
            arr.push(d20);
        }
        if (development.isNotBlank(d21)) {
            arr.push(d21);
        }
        if (development.isNotBlank(d22)) {
            arr.push(d22);
        }
        if (development.isNotBlank(d23)) {
            arr.push(d23);
        }
        if (development.isNotBlank(d24)) {
            arr.push(d24);
        }
        if (development.isNotBlank(d25)) {
            arr.push(d25);
        }
        if (arr.length == 0) {
            return false;
        }
        var c = 0;
        $.each(arr, function (i, item) {
            c += Number(item);
        });
        c = c.toFixed(landEngineering.fixed) ;
        this.target.find("input[name='constructionCostSubtotal']").val(c);
        this.target.find(".constructionCostSubtotal").html(c);
        this.target.find("input[name='unforeseenExpenses']").trigger('blur');
        this.target.find("input[name='f40']").trigger('blur');
    };

    //单元格D27
    landEngineering.calculationD27 = function () {
        var d26 = this.target.find("input[name='constructionCostSubtotal']").val() ;
        var f27 = this.target.find("input[name='unforeseenExpenses']").attr("data-value");
        if (!AssessCommon.isNumber(d26)) {
            return false;
        }
        if (!AssessCommon.isNumber(f27)) {
            return false;
        }
        var c = Number(d26) * Number(f27) ;
        this.target.find("input[name='d27']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='interestInvestmentTax']").trigger('blur');
        this.target.find("input[name='investmentProfitTax']").trigger('blur');
        this.target.find("input[name='f40']").trigger('blur');
    };

    //单元格D28
    landEngineering.calculationD28 = function () {
        var f29 = this.target.find("input[name='deedTaxRate']").attr("data-value");
        var f30 = this.target.find("input[name='transactionTaxRate']").attr("data-value");
        if (!AssessCommon.isNumber(f29)) {
            return false;
        }
        if (!AssessCommon.isNumber(f30)) {
            return false;
        }
        var c = Number(f29) + Number(f30) +1;
        this.target.find("input[name='d28']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='d32']").trigger('blur');
        this.target.find("input[name='d34']").trigger('blur');
    } ;

    //单元格D32
    landEngineering.calculationD32 = function () {
        var g32 = this.target.find("input[name='managementExpense']").attr("data-value");
        var d28 = this.target.find("input[name='d28']").val();
        if (!AssessCommon.isNumber(d28)) {
            return false;
        }
        if (!AssessCommon.isNumber(g32)) {
            return false;
        }
        var c = Number(d28) * Number(g32) ;
        this.target.find("input[name='d32']").val(c.toFixed(landEngineering.fixedMax));
        this.target.find("input[name='d34']").trigger('blur');
        this.target.find("input[name='d35']").trigger('blur');
        this.target.find("input[name='h40']").trigger('blur');
    };

    //单元格F32
    landEngineering.calculationF32 = function () {
        //=(SUM(D26+D27)+F31)*G32
        var f31 = this.target.find("input[name='landGetRelevant']").val();
        var g32 = this.target.find("input[name='managementExpense']").attr("data-value");
        var d26 = this.target.find("input[name='constructionCostSubtotal']").val() ;
        var d27 = this.target.find("input[name='d27']").val();
        if (!AssessCommon.isNumber(f31)) {
            return false;
        }
        if (!AssessCommon.isNumber(g32)) {
            return false;
        }
        if (!AssessCommon.isNumber(d26)) {
            return false;
        }
        if (!AssessCommon.isNumber(d27)) {
            return false;
        }
        var c = Number(d26) + Number(d27) + Number(f31);
        c *= Number(g32) ;
        this.target.find("input[name='f32']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='interestInvestment']").trigger('blur');//f34
        this.target.find("input[name='investmentProfit']").trigger('blur');//f35
        this.target.find("input[name='f40']").trigger('blur');
    };

    //单元格F33
    landEngineering.calculationF33 = function () {
        console.log('landEngineering.calculationF33') ;
        var g33 = this.target.find("input[name='salesFee']").attr("data-value");
        var e16 = this.target.find("input[name='totalSaleableAreaPrice']").val();
        if (!AssessCommon.isNumber(g33)) {
            return false;
        }
        if (!AssessCommon.isNumber(e16)) {
            return false;
        }
        var c = ( Number(e16) * Number(g33) ) ;
        c = Number(c) ;
        if (AssessCommon.isNumber(c)) {
            this.target.find("input[name='f33']").val(c.toFixed(landEngineering.fixed));
            this.target.find("input[name='interestInvestment']").trigger('blur');//f34
            this.target.find("input[name='investmentProfit']").trigger('blur');//f35
            this.target.find("input[name='f40']").trigger('blur');
        }
    };

    //单元格F34
    landEngineering.calculationF34 = function () {
        console.log("landEngineering.calculationF34") ;
        //,(D21+D23+D24+D25+D27+F32+F33)  *  ( (1+G34)^(D3/2)-1)   +(SUM(D20+D22)+E31) * (  (1+G34)^(D3)-1 )    )
        var g34 = this.target.find("input[name='interestInvestmentTax']").attr("data-value");
        var d20 = this.target.find("input[name='d20']").val();
        var d21 = this.target.find("input[name='d21']").val();
        var d22 = this.target.find("input[name='d22']").val();
        var d23 = this.target.find("input[name='d23']").val();
        var d24 = this.target.find("input[name='d24']").val();
        var d25 = this.target.find("input[name='d25']").val();
        var d27 = this.target.find("input[name='d27']").val();
        var f33 = this.target.find("input[name='f33']").val();
        var f32 = this.target.find("input[name='f32']").val();
        var d3 = this.target.find("input[name='projectConstructionPeriod']").val() ;
        var f31 = this.target.find("input[name='landGetRelevant']").val();

        if (!AssessCommon.isNumber(d3)) {
            return false
        }
        if (!AssessCommon.isNumber(g34)) {
            return false
        }
        var a = Number(d21) + Number(d23) + Number(d24) + Number(d25) + Number(d27)  + Number(f32)  + Number(f33);
        var b = Math.pow(1 + Number(g34), Number(d3) / 2) - 1 ;
        var c1 = a *b ;
        var c2 = (Number(d20) + Number(d22) + Number(f31))   *   ( Math.pow(1 + Number(g34), Number(d3))  - 1)   ;
        var c = c1 + c2;
        if (AssessCommon.isNumber(c)) {
            c = c.toFixed(landEngineering.fixed) ;
            this.target.find(".interestInvestment").html(c);
            this.target.find("input[name='interestInvestment']").val(c);
            this.target.find("input[name='f40']").trigger('blur');
        }
    };

    //单元格D34
    landEngineering.calculationD34 = function () {
        console.log("landEngineering.calculationD34") ;
        //(1+G34)^D3-1)*D28+((1+G34)^(D3/2)-1)*D32
        var g34 = this.target.find("input[name='interestInvestmentTax']").attr("data-value");
        var d3 = this.target.find("input[name='projectConstructionPeriod']").val() ;
        var d28 = this.target.find("input[name='d28']").val() ;
        var d32 = this.target.find("input[name='d32']").val() ;
        if (!AssessCommon.isNumber(g34)) {
            return false;
        }
        if (!AssessCommon.isNumber(d3)) {
            return false
        }
        var c1 = ( Math.pow(1 + Number(g34),Number(d3)) - 1)  * Number(d28) ;
        var c2 = (Math.pow(1 + Number(g34),Number(d3)/2) - 1) * Number(d32) ;
        var c = c1 + c2;
        if (AssessCommon.isNumber(c)) {
            this.target.find("input[name='d34']").val(c.toFixed(landEngineering.fixedMax));
            this.target.find("input[name='h40']").trigger('blur');
        }
    };

    //单元格D35
    landEngineering.calculationD35 = function () {
        //=(D28+D32)*G35
        var g35 = this.target.find("input[name='investmentProfitTax']").attr("data-value");
        var d28 = this.target.find("input[name='d28']").val();
        var d32 = this.target.find("input[name='d32']").val() ;
        if (!AssessCommon.isNumber(g35)) {
            return false;
        }
        if (!AssessCommon.isNumber(d28)) {
            return false;
        }
        if (!AssessCommon.isNumber(d32)) {
            return false;
        }
        var c  = (Number(d28) + Number(d32)) * Number(g35) ;
        this.target.find("input[name='d35']").val(c.toFixed(landEngineering.fixedMax));
        this.target.find("input[name='h40']").trigger('blur');
    } ;

    //单元格F35
    landEngineering.calculationF35 = function () {
        //=(D26+D27+F32+F33+E31)*G35
        var g35 = this.target.find("input[name='investmentProfitTax']").attr("data-value");
        var d26 = this.target.find("input[name='constructionCostSubtotal']").val() ;
        var d27 = this.target.find("input[name='d27']").val();
        var f32 = this.target.find("input[name='f32']").val();
        var f33 = this.target.find("input[name='f33']").val();
        var f31 = this.target.find("input[name='landGetRelevant']").val();
        if (!AssessCommon.isNumber(g35)) {
            return false;
        }
        if (!AssessCommon.isNumber(d26)) {
            return false;
        }
        if (!AssessCommon.isNumber(d27)) {
            return false;
        }
        if (!AssessCommon.isNumber(f32)) {
            return false;
        }
        if (!AssessCommon.isNumber(f33)) {
            return false;
        }
        if (!AssessCommon.isNumber(f31)) {
            return false;
        }
        if (!AssessCommon.isNumber(f32)) {
            return false;
        }
        var c = ( Number(d26) + Number(d27)+ Number(f32)+ Number(f33)+ Number(f31) ) * Number(g35) ;
        c = c.toFixed(landEngineering.fixed) ;
        this.target.find(".investmentProfit").html(c);
        this.target.find("input[name='investmentProfit']").val(c);
        this.target.find("input[name='f40']").trigger('blur');
    } ;

    //单元格D36
    landEngineering.calculationD36 = function () {
        var f37 = this.target.find("input[name='salesTaxAndAdditional']").attr("data-value");
        var f38 = this.target.find("input[name='landValueAddedTax']").attr("data-value");
        var f39 = this.target.find("input[name='projectDevelopmentIncomeTax']").attr("data-value");
        var arr = [] ;
        if ($.isNumeric(f37)){
            arr.push(f37) ;
        }
        if ($.isNumeric(f38)){
            arr.push(f38) ;
        }
        if ($.isNumeric(f39)){
            arr.push(f39) ;
        }
        if (arr.length < 1){
            return false ;
        }
        var c = math.bignumber(0) ;
        $.each(arr,function (i,item) {
            c = math.add(c, math.bignumber(item)) ;
        });
        //将对象转换为普通数字
        c = math.number(c.toString()) ;
        c = c.toFixed(landEngineering.fixedMax) ;
        this.target.find("input[name='d36']").val(c);
        this.target.find("input[name='f36']").trigger('blur');
    } ;

    //单元格F36 =E16*D36
    landEngineering.calculationF36 = function () {
        var d36 = this.target.find("input[name='d36']").val() ;
        var e16 = this.target.find("input[name='totalSaleableAreaPrice']").val();
        if (!AssessCommon.isNumber(d36)) {
            return false;
        }
        if (!AssessCommon.isNumber(e16)) {
            return false;
        }
        var c = Number(d36) * Number(e16) ;
        this.target.find("input[name='f36']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='f40']").trigger('blur');
    };

    //单元格H40
    landEngineering.calculationH40 = function () {
        //=D29+D30+D32+D35+D34
        var f29 = this.target.find("input[name='deedTaxRate']").attr("data-value");
        var f30 = this.target.find("input[name='transactionTaxRate']").attr("data-value");
        var d32 = this.target.find("input[name='d32']").val();
        var d34 = this.target.find("input[name='d34']").val();
        var d35 = this.target.find("input[name='d35']").val();
        var c = undefined ;
        try {
            c = Number(f29) + Number(f30)+ Number(d32)+ Number(d34)+ Number(d35)  ;
        } catch (e) {
        }
        if (AssessCommon.isNumber(c)) {
            this.target.find("input[name='h40']").val(c.toFixed(landEngineering.fixedMax));
            this.target.find("input[name='e40']").trigger('blur');
        }
    };

    //单元格F40
    landEngineering.calculationF40 = function () {
        console.log('landEngineering.calculationF40') ;
        //E16-D26-D27-E31-SUM(F32:F35)-F36)
        var e16 = this.target.find("input[name='totalSaleableAreaPrice']").val();
        var d26 = this.target.find("input[name='constructionCostSubtotal']").val() ;
        var d27 = this.target.find("input[name='d27']").val();
        var f31 = this.target.find("input[name='landGetRelevant']").val();
        var f32 = this.target.find("input[name='f32']").val();
        var f33 = this.target.find("input[name='f33']").val();
        var f36 = this.target.find("input[name='f36']").val();
        var f34 = this.target.find("input[name='interestInvestment']").val();
        var f35 = this.target.find("input[name='investmentProfit']").val();
        try {
            var a = math.add(  math.bignumber(d26) , math.bignumber(d27)  , math.bignumber(f31) , math.bignumber(f32), math.bignumber(f33), math.bignumber(f34) ,math.bignumber(f35) ,math.bignumber(f36)) ;
            var c = math.number(math.subtract(math.bignumber(e16) , a).toString()) ;
            this.target.find("input[name='f40']").val(c.toFixed(landEngineering.fixedMax));
            this.target.find("input[name='e40']").trigger('blur');
        } catch (e) {
        }
    } ;

    //单元格E40 =F40/(1+H40)
    landEngineering.calculationE40 = function () {
        console.log('landEngineering.calculationE40') ;
        var f40 = this.target.find("input[name='f40']").val();
        var h40 = this.target.find("input[name='h40']").val();
        if (!AssessCommon.isNumber(f40)) {
            return false;
        }
        if (!AssessCommon.isNumber(h40)) {
            return false;
        }
        var c = Number(f40) / (1 + Number(h40)) ;
        this.target.find("input[name='e40']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='assessPrice']").trigger('blur');
    } ;

    //单元格D41 =E40/F18*10000
    landEngineering.calculationD41 = function () {
        console.log('landEngineering.calculationD41') ;
        var f18 = this.target.find("input[name='f18']").val();
        var e40 = this.target.find("input[name='e40']").val();
        if (!AssessCommon.isNumber(e40)) {
            return false;
        }
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        var c = Number(e40) / Number(f18)  * 10000;
        c = c.toFixed(landEngineering.fixedMin) ;
        this.target.find("input[name='assessPrice']").val(c);
        this.target.find(".assessPrice").html(c);
        this.target.find("input[name='price']").trigger('blur');
    } ;

    //单元格D43
    landEngineering.calculationD43 = function () {
        console.log('landEngineering.calculationD43') ;
        //ROUND((1-1/(1+E43)^G43)/(1-1/(1+E43)^F43),4))
        var e43 = this.target.find("input[name='remunerationRate']").attr("data-value");
        var g43 = landEngineering.target.find("input[name='remainingYears']").val();
        var f43 = landEngineering.target.find("input[name='statutoryLife']").val();
        if (!AssessCommon.isNumber(e43)) {
            return false;
        }
        if (!AssessCommon.isNumber(g43)) {
            return false;
        }
        if (!AssessCommon.isNumber(f43)) {
            return false;
        }
        e43 = Number(e43) ;
        g43 = Number(g43) ;
        f43 = Number(f43) ;
        var a = 1 - 1 / Math.pow(1+e43,g43) ;
        var b = 1 - 1 / Math.pow(1+e43,f43) ;
        var c = a/ b;
        this.target.find("input[name='d43']").val(c.toFixed(landEngineering.fixedMax));
        this.target.find("input[name='price']").trigger('blur');
    };

    //单元格D47 =D41*D43*D44*D45+D46
    landEngineering.calculationD47 = function () {
        console.log('landEngineering.calculationD47') ;
        var d41 = landEngineering.target.find("input[name='assessPrice']").val();
        var d44 = landEngineering.target.find("input[name='amendmentStatusRights']").val();
        var d46 = landEngineering.target.find("input[name='developmentDegreeRevision']").val();
        var d45 = landEngineering.target.find("input[name='otherAmendments']").val();
        var d43 = this.target.find("input[name='d43']").val();
        try {
            var c = Number(d41) * Number(d43)* Number(d44)* Number(d45) + Number(d46) ;
            c = c.toFixed(landEngineering.fixedMin) ;
            this.target.find("input[name='price']").val(c);
            this.target.find(".price").html(c);
        } catch (e) {
        }
    } ;

    //单元格f21
    landEngineering.constructionInstallationEngineeringFeeEvent = {
        show: function () {
            var target = $("#boxLandEngineering");
            if (target.find(".panel-body").find("table").size() == 0) {
                target.find(".panel-body").append(developmentCommon.architecturalA.getHtml());
                developmentCommon.architecturalA.treeGirdParse(target);
            }
            developmentCommon.architecturalA.get(landEngineering.type , function (data) {
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
                    developmentCommon.architecturalA.initData(target.find("table"),item) ;
                }
            }) ;
            target.modal("show");
        },
        save: function () {
            landEngineering.constructionInstallationEngineeringFeeEvent.event(function (data) {
                var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
                developmentCommon.saveMdArchitecturalObj(data , landEngineering.type , AssessDBKey.MdDevelopment,pid,'${projectPlanDetails.id}', function () {
                    toastr.success('保存成功!');
                }) ;
            }) ;
        },
        select:function () {
            landEngineering.constructionInstallationEngineeringFeeEvent.event() ;
        },
        event:function (callback) {
            var target = $("#boxLandEngineering");
            var table = target.find("table");
            var value = table.find("tfoot").find("input[name='totalPrice']").first().val();
            if (!AssessCommon.isNumber(value)) {
                toastr.success('请重新填写!');
                return false;
            }
            value = Number(value);
            landEngineering.target.find("input[name='constructionInstallationEngineeringFee']").val(value.toFixed(landEngineering.fixed)).trigger('blur');
            landEngineering.target.find("input[name='reconnaissanceDesign']").trigger('blur');
            if (callback){
                callback(developmentCommon.architecturalA.getFomData(table)) ;
            }
            target.modal("hide");
        }
    };

    landEngineering.getRewardRate = function (_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                AssessCommon.elementParsePoint(group.find('[name=remunerationRate]').val(data.resultValue));
                group.find('[name=rewardRateId]').val(data.id);
                group.find('[name=remunerationRate]').val(data.resultValue).trigger('blur');
            }
        })
    } ;

    landEngineering.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.infrastructureChildren.loadTable(pid,'${projectPlanDetails.id}',landEngineering.type,landEngineering.infrastructureChildrenTable,$("#toolbarMdDevelopmentInfrastructureChildrenTable")) ;
        landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
    };

    landEngineering.deleteMdDevelopmentInfrastructureChildrenTable = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            developmentCommon.infrastructureChildren.delete(data , function () {
                toastr.success('删除成功!');
                landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
            });
        } else {
            toastr.success('至少勾选一个!');
        }
    };

    landEngineering.editMdDevelopmentInfrastructureChildrenTable = function (table,box ,flag) {
        var target = $(box) ;
        var frm = target.find("form") ;
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        if (flag){
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                frm.initForm(data);
                target.find(".modal-footer").empty().append($(landEngineering.infrastructureFooterHtml).html()) ;
                target.modal('show');
            } else {
                toastr.success('勾选一个!');
            }
        }else {
            frm.clearAll();
            frm.initForm({pid:pid});
            target.find(".modal-footer").empty().append($(landEngineering.infrastructureFooterHtml).html()) ;
            target.modal('show');
        }
    };
    landEngineering.saveMdDevelopmentInfrastructureChildrenTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent() ;
        var frm = target.find("form") ;
        if (!frm.valid()) {
            return false ;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}' ;
        data.type = landEngineering.type ;
        data.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.infrastructureChildren.save(data , function () {
            toastr.success('添加成功!');
            target.modal('hide');
            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
            landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
        });
    } ;

    landEngineering.writeMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.infrastructureChildren.getDataList({planDetailsId:'${projectPlanDetails.id}',pid:pid,type:landEngineering.type} ,function (item) {
            var result = 0;
            if (item.length >= 1){
                $.each(item,function (i,n) {
                    result += Number(n.number) ;
                });
            }
            landEngineering.target.find("input[name='infrastructureCost']").val(result).trigger('blur');
        }) ;
    };

    landEngineering.saveMdDevelopmentIncomeCategoryTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent() ;
        var frm = target.find("form") ;
        if (!frm.valid()) {
            return false ;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}' ;
        data.type = landEngineering.type ;
        data.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.loadIncomeCategorySave(data,function (item) {
            toastr.success('添加成功!');
            target.modal('hide');
            landEngineering.incomeCategoryTable.bootstrapTable('refresh');
            landEngineering.writeMdDevelopmentIncomeCategoryTable(landEngineering.incomeCategoryTable,item) ;
        },function () {

        }) ;
    };

    landEngineering.deleteMdDevelopmentIncomeCategoryTable = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            var ids = $.map($(table).bootstrapTable('getSelections'), function (row) {
                return row.id
            });
            developmentCommon.deleteIncomeCategory(data,function () {
                $(table).bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                $(table).bootstrapTable('refresh');
                toastr.success('删除成功!');
                landEngineering.writeMdDevelopmentIncomeCategoryTable($(table)) ;
            },function () {
                toastr.success('删除失败!');
            }) ;
        } else {
            toastr.success('至少勾选一个!');
        }
    };

    landEngineering.loadIncomeCategoryTable = function () {
        var obj = {type:landEngineering.type,planDetailsId:'${projectPlanDetails.id}'} ;
        developmentCommon.loadIncomeCategoryTable(landEngineering.incomeCategoryTable,obj,$("#toolbarLandIncomeCategoryTableId"),function () {
            landEngineering.writeMdDevelopmentIncomeCategoryTable(landEngineering.incomeCategoryTable,null) ;
        }) ;
    } ;

    landEngineering.editMdDevelopmentIncomeCategoryTable = function (table,box ,flag) {
        var target = $(box) ;
        var frm = target.find("form") ;
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        if (flag){
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                frm.initForm(data);
                target.find(".modal-footer").empty().append($(landEngineering.incomeCategoryFooterHtml).html()) ;
                target.modal('show');
            } else {
                toastr.success('勾选一个!');
            }
        }else {
            frm.clearAll();
            frm.initForm({pid:pid});
            target.find(".modal-footer").empty().append($(landEngineering.incomeCategoryFooterHtml).html()) ;
            target.modal('show');
        }
    };

    landEngineering.writeMdDevelopmentIncomeCategoryTable = function (table,obj) {
        var data = table.bootstrapTable('getData') ;
        if (obj){
            data.push(obj) ;
        }
        var plannedBuildingArea = math.bignumber(0);
        var totalSaleableAreaPrice = math.bignumber(0);
        var saleableArea = math.bignumber(0);
        $.each(data,function (i,n) {
            if ($.isNumeric(n.plannedBuildingArea)){
                plannedBuildingArea = math.add(plannedBuildingArea, math.bignumber(n.plannedBuildingArea)) ;
            }
            if ($.isNumeric(n.totalSaleableAreaPrice)){
                totalSaleableAreaPrice = math.add(totalSaleableAreaPrice, math.bignumber(n.totalSaleableAreaPrice)) ;
            }
            if ($.isNumeric(n.saleableArea)){
                saleableArea = math.add(saleableArea, math.bignumber(n.saleableArea)) ;
            }
        });
        plannedBuildingArea = plannedBuildingArea.toString() ;
        totalSaleableAreaPrice = totalSaleableAreaPrice.toString() ;
        saleableArea = saleableArea.toString() ;
        this.target.find("input[name='plannedBuildingArea']").val(plannedBuildingArea).trigger('blur');
        this.target.find("label[name='plannedBuildingArea']").html(plannedBuildingArea);
        this.target.find("input[name='totalSaleableAreaPrice']").val(totalSaleableAreaPrice).trigger('blur');
        this.target.find("label[name='totalSaleableAreaPrice']").html(totalSaleableAreaPrice);
        this.target.find("input[name='saleableArea']").val(saleableArea).trigger('blur');
        this.target.find("label[name='saleableArea']").html(saleableArea);
    };

    landEngineering.unsaleableBuildingAreaFunHandle = function () {
        this.target.find("a").each(function (i,item) {
            var target = $(item);
            var dataKey = target.attr("data-key");
            if (dataKey == 'unsaleableBuildingArea'){
                target.editable({
                    type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                    disabled: false,             //是否禁用编辑 ,默认 false
                    emptytext: "不可售建筑面积(请输入数字最多保留两位小数)",          //空值的默认文本
                    mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                    validate: function (value) { //字段验证
                        if ($.isNumeric(value)){
                            landEngineering.target.find("input[name='unsaleableBuildingArea']").val(value).trigger('blur');
                        }else {
                            return '必须是数字';
                        }
                    },
                    display: function (value) {
                        $(this).text(value);
                    }
                });
            }
        });
    };

    /**
     * 触发事件
     */
    landEngineering.inputBlurEvent = function () {
        if (development.isNotBlank('${mdDevelopment.id}')){
            if ('${mdDevelopment.type == 1}'){
                console.log('inputBlurEvent') ;
                landEngineering.target.find("input").trigger('blur');
            }
        }
    };



    /**
     math.sqrt(4) 开方
     math.add( ) 加
     math.subtract( )减
     math.divide( ) 除
     math.multiply( )乘
     */
</script>