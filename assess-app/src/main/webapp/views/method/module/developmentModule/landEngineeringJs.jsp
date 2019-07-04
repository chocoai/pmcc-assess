<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var landEngineering = {};
    landEngineering.target = $("#mdDevelopmentLandFrm");
    landEngineering.fixed = 2; //小数点保留2位
    landEngineering.fixedMax = 4; //小数点保留2位


    //单元格f18
    landEngineering.calculationF18 = function () {
        var tfoot = this.target.find("table").find("tfoot");
        var a = tfoot.find("tr").first().find("input[name='unsaleableBuildingArea']").val();
        var b = tfoot.find("tr").first().find("input[name='maySaleAreaNext']").val();
        var c = 0;
        if (development.isNotBlank(a)) {
            c += Number(a);
        }
        if (development.isNotBlank(b)) {
            c += Number(b);
        }
        this.target.find("input[name='f18']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='f20']").trigger('blur');
        this.target.find("select[name='f22']").trigger('change');
        this.target.find("select[name='f23']").trigger('change');
        this.target.find("select[name='f24']").trigger('change');
        this.target.find("input[name='f25']").trigger('blur');
    };

    //单元格D20
    landEngineering.calculationD20 = function () {
        console.log("calculationD20");
        var f20 = this.target.find("input[name='f20']").attr("data-value");
        var f18 = this.target.find("input[name='f18']").val();
        var f21 = this.target.find("input[name='f21']").val();
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
        this.target.find(".d26").trigger('blur');
    };

    //单元格d21
    landEngineering.calculationD21 = function () {
        console.log("calculationD21");
        var f21 = landEngineering.target.find("input[name='f21']").val();
        var f18 = this.target.find("input[name='f18']").val();
        var c = Number(f21) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d21']").val(c.toFixed(landEngineering.fixed));
        this.target.find(".d26").trigger('blur');
    };

    //单元格d22
    landEngineering.calculationD22 = function () {
        console.log("calculationD22");
        var f18 = this.target.find("input[name='f18']").val();
        var f22 = this.target.find("select[name='f22']").val();
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f22)) {
            return false;
        }
        var c = Number(f22) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d22']").val(c.toFixed(landEngineering.fixed));
        this.target.find(".d26").trigger('blur');
    };

    //单元格d23
    landEngineering.calculationD23 = function () {
        console.log("calculationD23");
        var f18 = this.target.find("input[name='f18']").val();
        var f23 = this.target.find("select[name='f23']").val();
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f23)) {
            return false;
        }
        var c = Number(f23) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d23']").val(c.toFixed(landEngineering.fixed));
        this.target.find(".d26").trigger('blur');
    };

    //单元格d24
    landEngineering.calculationD24 = function () {
        console.log("calculationD24");
        var f18 = this.target.find("input[name='f18']").val();
        var f24 = this.target.find("select[name='f24']").val();
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f24)) {
            return false;
        }
        var c = Number(f24) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d24']").val(c.toFixed(landEngineering.fixed));
        this.target.find(".d26").trigger('blur');
    };

    //单元格Dd25
    landEngineering.calculationD25 = function () {
        console.log("calculationD25");
        var f18 = this.target.find("input[name='f18']").val();
        var f25 = this.target.find("input[name='f25']").attr("data-value");
        if (!AssessCommon.isNumber(f18)) {
            return false;
        }
        if (!AssessCommon.isNumber(f25)) {
            return false;
        }
        var c = Number(f25) * Number(f18) / 10000;
        landEngineering.target.find("input[name='d25']").val(c.toFixed(landEngineering.fixed));
        this.target.find(".d26").trigger('blur');
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
        this.target.find(".d26").html(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='f27']").trigger('blur');
        this.target.find("input[name='f31']").trigger('blur');

    };

    //单元格D27
    landEngineering.calculationD27 = function () {
        var d26 = this.target.find(".d26").html();
        var f27 = this.target.find("input[name='f27']").attr("data-value");
        if (!AssessCommon.isNumber(d26)) {
            return false;
        }
        if (!AssessCommon.isNumber(f27)) {
            return false;
        }
        var c = Number(d26) * Number(f27) ;
        landEngineering.target.find("input[name='d27']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='g34']").trigger('blur');
        this.target.find("input[name='g35']").trigger('blur');
    };

    //单元格D28
    landEngineering.calculationD28 = function () {
        var f29 = this.target.find("input[name='f29']").attr("data-value");
        var f30 = this.target.find("input[name='f30']").attr("data-value");
        if (!AssessCommon.isNumber(f29)) {
            return false;
        }
        if (!AssessCommon.isNumber(f30)) {
            return false;
        }
        var c = Number(f29) + Number(f30) +1;
        landEngineering.target.find("input[name='d28']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='g32']").trigger('blur');
        this.target.find("input[name='d34']").trigger('blur');
    } ;

    //单元格D32
    landEngineering.calculationD32 = function () {
        var g32 = this.target.find("input[name='g32']").attr("data-value");
        var d28 = this.target.find("input[name='d28']").val();
        if (!AssessCommon.isNumber(d28)) {
            return false;
        }
        if (!AssessCommon.isNumber(g32)) {
            return false;
        }
        var c = Number(d28) * Number(g32) ;
        landEngineering.target.find("input[name='d32']").val(c.toFixed(landEngineering.fixedMax));
        this.target.find("input[name='g34']").trigger('blur');
        this.target.find("input[name='d34']").trigger('blur');
        this.target.find("input[name='g35']").trigger('blur');
        this.target.find("input[name='h40']").trigger('blur');
    };

    //单元格F32
    landEngineering.calculationF32 = function () {
        //=(SUM(D26+D27)+F31)*G32
        var f31 = this.target.find("input[name='f31']").val();
        var g32 = this.target.find("input[name='g32']").attr("data-value");
        var d26 = this.target.find(".d26").html();
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
        landEngineering.target.find("input[name='f32']").val(c.toFixed(landEngineering.fixed));
        this.target.find("input[name='g34']").trigger('blur');
        this.target.find("input[name='g35']").trigger('blur');
    };

    //单元格F33
    landEngineering.calculationF33 = function () {
        var g33 = this.target.find("input[name='g33']").attr("data-value");
        var tfoot = this.target.find("table").find("tfoot");
        var a = tfoot.find("tr").first().find("input[name='maySaleArea']").val();
        if (!AssessCommon.isNumber(g33)) {
            return false;
        }
        if (!AssessCommon.isNumber(a)) {
            return false;
        }
        var c = ( Number(a) * Number(g33) ) ;
        c = Number(c) ;
        if (AssessCommon.isNumber(c)) {
            console.log("f33:"+c) ;
            landEngineering.target.find("input[name='f33']").val(c.toFixed(landEngineering.fixed));
            this.target.find("input[name='g34']").trigger('blur');
            this.target.find("input[name='g35']").trigger('blur');
        }
    };

    //单元格F34
    landEngineering.calculationF34 = function () {
        //,(D21+D23+D24+D25+D27+F32+F33)  *  ( (1+G34)^(D3/2)-1)   +(SUM(D20+D22)+E31) * (  (1+G34)^(D3)-1 )    )
        var g34 = this.target.find("input[name='g34']").attr("data-value");
        var d20 = this.target.find("input[name='d20']").val();
        var d21 = this.target.find("input[name='d21']").val();
        var d22 = this.target.find("input[name='d22']").val();
        var d23 = this.target.find("input[name='d23']").val();
        var d24 = this.target.find("input[name='d24']").val();
        var d25 = this.target.find("input[name='d25']").val();
        var d27 = this.target.find("input[name='d27']").val();
        var f33 = this.target.find("input[name='f33']").val();
        var f32 = this.target.find("input[name='f32']").val();
        var d3 = $(development.config.frm).find("input[name='projectConstructionPeriod']").val() ;
        var f31 = this.target.find("input[name='f31']").val();

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
            this.target.find(".f34").html(c.toFixed(landEngineering.fixed));
        }
    };

    //单元格D34
    landEngineering.calculationD34 = function () {
        //(1+G34)^D3-1)*D28+((1+G34)^(D3/2)-1)*D32
        var g34 = this.target.find("input[name='g34']").attr("data-value");
        var d3 = $(development.config.frm).find("input[name='projectConstructionPeriod']").val() ;
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
            landEngineering.target.find("input[name='d34']").val(c.toFixed(landEngineering.fixedMax));
            this.target.find("input[name='h40']").trigger('blur');
        }
    };

    //单元格D35
    landEngineering.calculationD35 = function () {
        //=(D28+D32)*G35
        var g35 = this.target.find("input[name='g35']").attr("data-value");
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
        landEngineering.target.find("input[name='d35']").val(c.toFixed(landEngineering.fixedMax));
        this.target.find("input[name='h40']").trigger('blur');
    } ;

    //单元格F35
    landEngineering.calculationF35 = function () {
        //=(D26+D27+F32+F33+E31)*G35
        var g35 = this.target.find("input[name='g35']").attr("data-value");
        var d26 = this.target.find(".d26").html();
        var d27 = this.target.find("input[name='d27']").val();
        var f32 = this.target.find("input[name='f32']").val();
        var f33 = this.target.find("input[name='f33']").val();
        var f31 = this.target.find("input[name='f31']").val();
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
        this.target.find(".f35").html(c.toFixed(landEngineering.fixed));
    } ;

    //单元格H40
    landEngineering.calculationH40 = function () {
        //=D29+D30+D32+D35+D34
        var f29 = this.target.find("input[name='f29']").attr("data-value");
        var f30 = this.target.find("input[name='f30']").attr("data-value");
        var d32 = this.target.find("input[name='d32']").val();
        var d34 = this.target.find("input[name='d34']").val();
        var d35 = this.target.find("input[name='d35']").val();
        var c = undefined ;
        try {
            c = Number(f29) + Number(f30)+ Number(d32)+ Number(d34)+ Number(d35)  ;
        } catch (e) {
        }
        if (AssessCommon.isNumber(c)) {
            landEngineering.target.find("input[name='h40']").val(c.toFixed(landEngineering.fixedMax));
        }
    };

    //单元格f21
    landEngineering.constructionInstallationEngineeringFeeEvent = {
        show: function () {
            var target = $("#boxLandEngineering");
            if (target.find(".panel-body").find("table").size() == 0) {
                target.find(".panel-body").append(developmentCommon.architecturalA.getHtml());
                developmentCommon.architecturalA.treeGirdParse(target);
            }
            target.modal("show");
        },
        save: function () {
            var target = $("#boxLandEngineering");
            var table = target.find("table");
            var value = table.find("tfoot").find("input[name='totalPrice']").first().val();
            if (!AssessCommon.isNumber(value)) {
                toastr.success('请重新填写!');
                return false;
            }
            value = Number(value);
            landEngineering.target.find("input[name='f21']").val(value.toFixed(landEngineering.fixed));
            landEngineering.target.find("input[name='d21']").trigger('blur');
            landEngineering.target.find("input[name='d20']").trigger('blur');
            target.modal("hide");
        }
    };
</script>