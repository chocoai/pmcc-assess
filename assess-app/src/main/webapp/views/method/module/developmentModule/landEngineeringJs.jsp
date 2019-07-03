<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var landEngineering = {};
    landEngineering.target = $("#mdDevelopmentLandFrm");
    landEngineering.fixed = 2; //小数点保留2位


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
        var c = Number(f18) * Number(f21) * Number(f20) / 1000;
        landEngineering.target.find("input[name='d20']").val(c.toFixed(landEngineering.fixed));
        this.target.find(".d26").trigger('blur');
    };

    //单元格d21
    landEngineering.calculationD21 = function () {
        console.log("calculationD21");
        var f21 = landEngineering.target.find("input[name='f21']").val();
        var f18 = this.target.find("input[name='f18']").val();
        var c = Number(f21) * Number(f18) / 1000;
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
        var c = Number(f22) * Number(f18) / 1000;
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
        var c = Number(f23) * Number(f18) / 1000;
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
        var c = Number(f24) * Number(f18) / 1000;
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
        var c = Number(f25) * Number(f18) / 1000;
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
        var c = Number(d26) * Number(f27) / 1000;
        landEngineering.target.find("input[name='d27']").val(c.toFixed(landEngineering.fixed));
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
        landEngineering.target.find("input[name='d32']").val(c.toFixed(landEngineering.fixed));
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