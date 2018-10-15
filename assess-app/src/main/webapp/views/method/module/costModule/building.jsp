<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="buildModel">

    <jsp:include page="../costModule/build/cost.jsp"></jsp:include>

    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        勘察设计和前期工程费 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"
                               name="reconnaissanceDesign">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        建筑安装工程费 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="建筑安装工程费" class="form-control" readonly="readonly"
                               name="constructionInstallationEngineeringFee" value="1000">
                    </div>
                </div>
            </div>


        </form>
    </div>

    <jsp:include page="../costModule/build/totalTaxRate.jsp"></jsp:include>

    <jsp:include page="../costModule/build/designParameters.jsp"></jsp:include>
    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        管理费
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="管理费" class="form-control" readonly="readonly"
                               name="managementExpense">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        不可预见费
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="不可预见费" class="form-control" readonly="readonly"
                               name="unforeseenExpenses">
                    </div>
                </div>
            </div>
        </form>
    </div>

    <jsp:include page="../costModule/build/resultView.jsp"></jsp:include>

</div>

<script>
    var build = new Object();

    build.config = {
        id: "buildModel",
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        totalTaxRate: {
            key: "totalTaxRate",
            name: "合计税率",
            business: "businessTax",//营业税
            urbanMaintenance: "urbanMaintenanceTax",//城建税
            education: "educationTax",//教育费附加
            localEducation: "localEducationTax",//地方教育费附加
            stampDuty: "stampDuty"//印花税
        },
        inputConfig: {
            reconnaissanceDesign: {
                key: "reconnaissanceDesign",
                tax: "reconnaissanceDesignTax",
                correcting: "",
                name: "勘察设计和前期工程费"
            },
            constructionInstallationEngineeringFee: {
                key: "constructionInstallationEngineeringFee",
                tax: "constructionInstallationEngineeringFeeTax",
                correcting: "",
                name: "建筑安装工程费",
                class: "constructionInstallationEngineeringFeeClass"
            },
            infrastructureCost: {
                key: "infrastructureCost",
                tax: "infrastructureCostTax",
                correcting: "",
                name: "基础设施费用"
            },
            infrastructureMatchingCost: {
                key: "infrastructureMatchingCost",
                tax: "infrastructureMatchingCostTax",
                correcting: "",
                name: "公共配套设施费用"
            },
            devDuring: {
                key: "devDuring",
                tax: "devDuringTax",
                correcting: "",
                name: "开发期间"
            },
            otherEngineeringCost: {
                key: "otherEngineeringCost",
                tax: "otherEngineeringCostTax",
                correcting: "",
                name: "其它工程费"
            },
            unforeseenExpenses: {
                key: "unforeseenExpenses",
                tax: "unforeseenExpensesTax",
                correcting: "",
                name: "不可预见费"
            },
            managementExpense: {
                key: "managementExpense",
                tax: "managementExpenseTax",
                correcting: "managementExpenseCorrecting",
                name: "管理费"
            },
            salesFee: {
                key: "salesFee",
                tax: "salesFeeTax",
                correcting: "salesFeeCorrecting",
                name: "销售费用"
            },
            interestInvestment: {
                key: "interestInvestment",
                tax: "interestInvestmentTax",//利率
                correcting: "interestInvestmentCorrecting",//计息周期
                name: "投资利息"
            },
            investmentProfit: {
                key: "investmentProfit",
                tax: "investmentProfitTax",
                correcting: "investmentProfitCorrecting",
                name: "投资利润"
            },
            constructionCost: {
                key: "constructionCost",
                tax: "",
                name: "建设成本"
            },
            replacementValue: {key: "replacementValue", name: "重置价格", tax: ""},
            newRate: {
                key: "newRate",
                name: "成新率",
                tax: "",
                div: "newRateModelDiv",
                frm: "newRateModelFrm",
                correcting: "newRateCorrecting"
            },
            assessPrice: {key: "assessPrice", name: "评估单价", tax: ""}
        }
    };

    build.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    build.loadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/marketCost/listCostAndMatchingCost",
            type: "get",
            data: {projectId: "${projectInfo.id}"},
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var infrastructureVo = result.data.InfrastructureVo;
                    var optionA = "<option>请选择</option>";
                    var optionB = "<option>请选择</option>";
                    if (infrastructureVo.length > 0) {
                        var temp = null;
                        for (var i = 0; i < infrastructureVo.length; i++) {
                            temp = infrastructureVo[i].temp + " (" + infrastructureVo[i].priceCost + ")";
                            optionA += "<option value='" + infrastructureVo[i].priceCost + "'>" + temp + "</option>";
                            temp = infrastructureVo[i].temp + " (" + infrastructureVo[i].priceMarch + ")";
                            optionB += "<option value='" + infrastructureVo[i].priceMarch + "'>" + temp + "</option>";
                        }
                        $("#" + build.config.id).find("select." + build.config.inputConfig.infrastructureCost.tax).html(optionA);
                        $("#" + build.config.id).find("select." + build.config.inputConfig.infrastructureMatchingCost.tax).html(optionB);
                    }

                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    build.algorithm = {
        businessTaxFun: function () {
            //合计税率
            build.algsObj.totalTaxRate();
        },
        urbanMaintenanceTaxFun: function () {
            //合计税率
            build.algsObj.totalTaxRate();
        },
        educationTaxFun: function () {
            //合计税率
            build.algsObj.totalTaxRate();
        },
        localEducationTaxFun: function () {
            //合计税率
            build.algsObj.totalTaxRate();
        },
        stampDutyFun: function () {
            //合计税率
            build.algsObj.totalTaxRate();
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignTaxFun: function () {
            build.algsObj.reconnaissanceDesign();
        },
        //基础设施建设费
        infrastructureCostTaxFun: function () {
            build.algsObj.constructionCost();//建设成本
        },
        //公共配套设施建设费
        infrastructureMatchingCostTaxFun: function () {
            build.algsObj.constructionCost();//建设成本
        },
        //开发期间税费
        devDuringTaxFun: function () {
            build.algsObj.constructionCost();//建设成本
        },
        //其它工程费
        otherEngineeringCostTaxFun: function () {
            build.algsObj.constructionCost();//建设成本
        },
        //管理费率
        managementExpenseTaxFun: function () {
            var b = build.algsObj.getAndSet("get", build.config.inputConfig.managementExpense.tax, null);
            if (b >= 0.03 && b <= 0.07) {
                build.algsObj.managementExpense();//管理费
            } else {
                toastr.success('管理费参考值3%-7%');
            }
        },
        //不可预见费率
        unforeseenExpensesTaxFun: function () {
            var c = build.algsObj.getAndSet("get", build.config.inputConfig.unforeseenExpenses.tax, null);
            if (c >= 0.03 && c <= 0.08) {
                build.algsObj.unforeseenExpenses();//不可预见费
            } else {
                toastr.success('不可预见费参考值3%-7%');
            }
        },
        //销售费用率
        salesFeeTaxFun: function () {
            build.algsObj.replacementValue();//重置价格
        },
        //计息周期
        interestInvestmentCorrectingFun: function () {
            build.algsObj.interestInvestment();//投资利息
        },
        //投资利息率
        interestInvestmentTaxFun: function () {
            build.algsObj.interestInvestment();//投资利息
        },
        //投资利润率
        investmentProfitTaxFun: function () {
            build.algsObj.investmentProfit();//投资利润
        }
    };

    build.algsObj = {
        //评估单价
        assessPrice: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + build.config.id).find("." + build.config.inputConfig.replacementValue.key).html();//重置价格
            a = Number(a);
            b = build.algsObj.getAndSet("get", build.config.inputConfig.newRate.key, null);//成新率
            c = a * b;
            c = c.toFixed(4);
            $("#" + build.config.id).find("." + build.config.inputConfig.assessPrice.key).html(c);//评估单价
        },
        //成新率
        newRate: function () {
            build.algsObj.assessPrice();//评估单价
        },
        //重置价格
        replacementValue: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, temp = 0;
            var p = 0, o = 0, s = 0;
            a = build.algsObj.getAndSet("get", build.config.inputConfig.unforeseenExpenses.key, null);//不可预见费
            b = build.algsObj.getAndSet("get", build.config.inputConfig.managementExpense.key, null);//管理费
            c = $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html();//建设成本
            c = Number(c);
            d = $("#" + build.config.id).find("." + build.config.inputConfig.investmentProfit.key).html();//投资利息
            d = Number(d);
            e = $("#" + build.config.id).find("." + build.config.inputConfig.interestInvestment.key).html();//投资利息
            e = Number(e);
            o = build.algsObj.getAndSet("get", build.config.inputConfig.salesFee.tax, null);//销售费用率
            p = build.algsObj.getAndSet("get", build.config.totalTaxRate.key, null);//合计税率
            s = build.algsObj.getAndSet("get", build.config.inputConfig.investmentProfit.tax, null);//
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c) || !AssessCommon.isNumber(d) || !AssessCommon.isNumber(e)) {
                return false;
            }
            if (!AssessCommon.isNumber(p) || !AssessCommon.isNumber(o)) {
                return false;
            }
            temp = a + b + c + d + e;
            temp = temp / (1 - p - o - (o * s));
            temp = temp.toFixed(4);
            $("#" + build.config.id).find("." + build.config.inputConfig.replacementValue.key).html(temp);//重置价格
            build.algsObj.assessPrice();//评估单价
        },
        //投资利润
        investmentProfit: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, temp = 0;
            a = build.algsObj.getAndSet("get", build.config.inputConfig.unforeseenExpenses.key, null);//不可预见费
            b = build.algsObj.getAndSet("get", build.config.inputConfig.managementExpense.key, null);//管理费
            c = $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html();//建设成本
            c = Number(c);
            d = build.algsObj.getAndSet("get", build.config.inputConfig.investmentProfit.tax, null);
            temp = a + b + c;
            temp = temp * d;
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c) || !AssessCommon.isNumber(d)) {
                return false;
            }
            temp = temp.toFixed(4);
            $("#" + build.config.id).find("." + build.config.inputConfig.investmentProfit.key).html(temp);//投资利润率
            build.algsObj.replacementValue();//重置价格
        },
        //投资利息
        interestInvestment: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, temp = 0;
            a = build.algsObj.getAndSet("get", build.config.inputConfig.unforeseenExpenses.key, null);//不可预见费
            b = build.algsObj.getAndSet("get", build.config.inputConfig.managementExpense.key, null);//管理费
            c = $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html();//建设成本
            c = Number(c);
            d = build.algsObj.getAndSet("get", build.config.inputConfig.interestInvestment.tax, null);
            e = build.algsObj.getAndSet("get", build.config.inputConfig.interestInvestment.correcting, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c) || !AssessCommon.isNumber(d) || !AssessCommon.isNumber(e)) {
                return false;
            }
            temp = a + b + c;
            temp = temp * (Math.pow(1 + d, e / 2) - 1);
            temp = temp.toFixed(4);
            $("#" + build.config.id).find("." + build.config.inputConfig.interestInvestment.key).html(temp);//投资利息
            build.algsObj.replacementValue();//重置价格
        },
        //不可预见费
        unforeseenExpenses: function () {
            var a = 0, b = 0, c = 0, d = 0;
            a = $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html();
            a = Number(a);
            b = build.algsObj.getAndSet("get", build.config.inputConfig.managementExpense.key, null);
            c = build.algsObj.getAndSet("get", build.config.inputConfig.unforeseenExpenses.tax, null);
            d = (a + b) * c;
            d = d.toFixed(4);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (c >= 0.03 && c <= 0.08) {
                build.algsObj.getAndSet("set", build.config.inputConfig.unforeseenExpenses.key, d);
            }
            build.algsObj.interestInvestment();//投资利息
            build.algsObj.investmentProfit();//投资利润
        },
        //管理费
        managementExpense: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html();
            a = Number(a);
            b = build.algsObj.getAndSet("get", build.config.inputConfig.managementExpense.tax, null);
            c = a * b;
            c = c.toFixed(4);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (b >= 0.03 && b <= 0.07) {
                build.algsObj.getAndSet("set", build.config.inputConfig.managementExpense.key, c);
            }
            build.algsObj.unforeseenExpenses();//不可预见费
        },
        //建设成本
        constructionCost: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, temp = 0;
            a = $("#" + build.config.id + " ." + build.config.inputConfig.infrastructureCost.tax).eq(1).val();//基础设施建设费
            a = Number(a);
            b = $("#" + build.config.id + " ." + build.config.inputConfig.infrastructureMatchingCost.tax).eq(1).val();//公共配套设施建设费
            b = Number(b);
            c = build.algsObj.getAndSet("get", build.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            d = build.algsObj.getAndSet("get", build.config.inputConfig.devDuring.tax, null);
            e = build.algsObj.getAndSet("get", build.config.inputConfig.otherEngineeringCost.tax, null);
            f = build.algsObj.getAndSet("get", build.config.inputConfig.reconnaissanceDesign.key, null);
            temp = a + b + c + d + e + f;
            temp = temp.toFixed(4);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c) || !AssessCommon.isNumber(d)) {
                return false;
            }
            if (!AssessCommon.isNumber(e) || !AssessCommon.isNumber(f)) {
                return false;
            }
            $("#" + build.config.id).find("." + build.config.inputConfig.constructionCost.key).html(temp);
            build.algsObj.managementExpense();//管理费
        },
        //建筑安装工程费
        constructionInstallationEngineeringFee: function () {
            build.algsObj.reconnaissanceDesign();
        },
        //勘察设计和前期工程费
        reconnaissanceDesign: function () {
            var a = 0, b = 0, c = 0;
            a = build.algsObj.getAndSet("get", build.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            b = build.algsObj.getAndSet("get", build.config.inputConfig.reconnaissanceDesign.tax, null);
            c = a * b;
            build.algsObj.getAndSet("set", build.config.inputConfig.reconnaissanceDesign.key, c);
            build.algsObj.constructionCost();//建设成本
        },
        //合计税率
        totalTaxRate: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, temp = 0;
            b = build.algsObj.getAndSet("get", build.config.totalTaxRate.urbanMaintenance, null);//城建税
            a = build.algsObj.getAndSet("get", build.config.totalTaxRate.business, null);//营业税
            c = build.algsObj.getAndSet("get", build.config.totalTaxRate.education, null);//教育费附加
            d = build.algsObj.getAndSet("get", build.config.totalTaxRate.localEducation, null);//地方教育费附加
            e = build.algsObj.getAndSet("get", build.config.totalTaxRate.stampDuty, null);//印花税
            temp = 1 + b + c + d;
            temp = a * temp;
            temp = e + temp;
            temp = AssessCommon.pointToPercent(temp);
            build.algsObj.getAndSet("set", build.config.totalTaxRate.key, temp);
            build.algsObj.replacementValue();//重置价格
        },
        getAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("#" + build.config.id).find("input[name='" + name + "']").val();
                text = build.algsObj.specialTreatment(text);
                text = Number(text);
                return text;
            }
            if (flag == 'set') {
                $("#" + build.config.id).find("input[name='" + name + "']").val(data);
            }
        },
        isNotNull: function (obj) {
            if (obj == 0) {
                return true;
            }
            if (obj) {
                return true;
            }
            return false;
        },
        specialTreatment: function (obj) {
            if (build.algsObj.isNotNull(obj)) {
                var nnn = "" + obj + "";
                var str = nnn.substring(nnn.length - 1, nnn.length);
                if (str == '%') {//检测是否为百分比
                    str = AssessCommon.percentToPoint(nnn);
                    return str;
                }
                return obj;
            }
            return 0;
        }
    };

    build.inputEvent = function () {
        $.each(build.config.inputConfig, function (i, n) {
            var tax = n.tax;
            var input = $("#" + build.config.id).find("input[name='" + tax + "']");
            if (build.isEmpty(tax)) {
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    try {
                        if (build.isEmpty(value)) {
                            var funName = "build.algorithm." + tax + "Fun(" + ")";
                            console.log(funName);
                            eval(funName);
                        }
                    } catch (e) {
                        console.log("没有相关定义的函数或者是属于子表单");
                        console.log(e);
                    }
                });
            }
        });
    };

    build.otherEventBlur = function () {
        var arr = new Array();
        arr.push(build.config.inputConfig.interestInvestment.correcting);
        arr.push(build.config.totalTaxRate.business);
        arr.push(build.config.totalTaxRate.education);
        arr.push(build.config.totalTaxRate.localEducation);
        arr.push(build.config.totalTaxRate.stampDuty);
        arr.push(build.config.totalTaxRate.urbanMaintenance);
        $.each(arr, function (i, n) {
            var input = $("#" + build.config.id).find("input[name='" + n + "']");
            if (build.isEmpty(n)) {
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    try {
                        if (build.isEmpty(value)) {
                            var funName = "build.algorithm." + n + "Fun(" + ")";
                            console.log(funName);
                            eval(funName);
                        }
                    } catch (e) {
                        console.log("没有相关定义的函数或者是属于子表单");
                        console.log(e);
                    }
                });
            }
        });
    };

    /**
     * @author:  zch
     * 描述:建安工程费用测算
     * @date:2018-10-11
     **/
    build.constructionInstallationEngineeringFeeEvent = {
        show: function () {
            layer.open({
                type: 1,
                area: '1000px;',
                offset: 't',
                content: $("#" + build.config.id).find("." + build.config.inputConfig.constructionInstallationEngineeringFee.class)
            });
            $(function () {

            });
        },
        eventSave: function () {

        },
        close: function () {
            layer.close(layer.index); //它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
        },
        serverSave: function (data) {
            var url = "${pageContext.request.contextPath}/marketCost/saveAndUpdateMdCostAndDevelopmentOther";
            $.ajax({
                url: url,
                type: "post",
                data: {
                    jsonContent: JSON.stringify(data),
                    type: "MdCostBuilding",
                    id: "${mdCostAndDevelopmentOtherBuilding.id}"
                },
                dataType: "json",
                success: function (result) {
                    toastr.success('成功');
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    };

    //监听change 事件
    build.monitor = {
        // 基础设施建设费
        infrastructureCost: function () {
            var tax = build.config.inputConfig.infrastructureCost.tax;
            $("#" + build.config.id + " ." + tax).change(function () {
                var funName = "build.algorithm." + tax + "Fun(" + ")";
                console.log(funName);
                try {
                    eval(funName);
                } catch (e) {
                    console.log("没有这个函数");
                    console.log(e);
                }
            });
        },
        //公共配套设施建设费
        infrastructureMatchingCost: function () {
            var tax = build.config.inputConfig.infrastructureMatchingCost.tax;
            $("#" + build.config.id + " ." + tax).change(function () {
                var funName = "build.algorithm." + tax + "Fun(" + ")";
                console.log(funName);
                try {
                    eval(funName);
                } catch (e) {
                    console.log("没有这个函数");
                    console.log(e);
                }
            });
        },
        init: function () {
            build.monitor.infrastructureCost();
            build.monitor.infrastructureMatchingCost();
        }
    };


    /**
     * @author:  zch
     * 描述:收集数据
     * @date:2018-10-12
     **/
    build.formParams = function () {
        var item = {};
        var forms = $("#" + build.config.id).find("form");
        $.each(forms, function (i, n) {
            // if (!$(n).valid()) {
            //     return false;
            // }
        });
        $.each(forms, function (i, n) {
            try {
                /*Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。 ECMAScript6 (可能不兼容) */
                item = Object.assign(item, formSerializeArray($(n)));
            } catch (e) {
                item = $.extend(item, formSerializeArray($(n)));
            }
        });
        return item;
    };


    /**
     * @author:  zch
     * 描述:赋值
     * @date:2018-10-12
     **/
    build.initForm = function (item) {
        var forms = $("#" + build.config.id).find("form");
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(item);
        });
    };

    build.newRateModel = {
        show: function () {
            $("#" + build.config.inputConfig.newRate.div).modal("show");
            $("#" + build.config.inputConfig.newRate.frm).validate();
            $(function () {
                build.newRateModel.event.init();
            });
        },
        close: function () {
            var v = $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.correcting).html();
            build.algsObj.getAndSet("set", build.config.inputConfig.newRate.key, Number(v));//成新率
            $("#" + build.config.inputConfig.newRate.div).modal("hide");
            build.algsObj.newRate();//成新率
        },
        event: {
            radioChange: function () {
                $("#" + build.config.inputConfig.newRate.frm).find("input[type='radio'][name='method']").change(function () {
                    var v = $("#" + build.config.inputConfig.newRate.frm).find(":radio:checked").val();
                    v = Number(v);
                    if (v == 1) {
                        $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm + "A").show();
                        $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm + "B").hide();
                    }
                    if (v == 2) {
                        $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm + "A").hide();
                        $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm + "B").show();
                    }
                    if (v == 12) {
                        $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm + "A").show();
                        $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm + "B").show();
                    }
                    $("#" + build.config.inputConfig.newRate.frm).find("." + build.config.inputConfig.newRate.frm).show();
                });
            },
            init: function () {
                build.newRateModel.event.radioChange();
                build.newRateModel.event.selectInit();
            },
            selectInit: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/architecture/dataBuildingNewRateList",
                    type: "get",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var optionA = "<option value=''>请选择</option>";
                            $.each(result.data, function (i, n) {
                                console.log(n);
                                if (build.isEmpty(n.durableLife)) {
                                    optionA += "<option value='" + n.id + "'>" + n.buildingStructure + "-" + n.buildingUseName + "</option>";
                                }
                            });
                            $("#" + build.config.inputConfig.newRate.frm).find("select.durableLife").empty();
                            $("#" + build.config.inputConfig.newRate.frm).find("select.durableLife").html(optionA);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            }
        }
    };

    $(function () {
        build.loadData();
        build.inputEvent();
        build.otherEventBlur();
        build.monitor.init();
    });
</script>
<div id="newRateModelDiv" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">成新率</h3>
            </div>
            <form class="form-horizontal" id="newRateModelFrm">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-header">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <span class="col-sm-2">
                                        </span>
                                        <span class="col-sm-2">
                                            <input type="radio" name="method" value="1">
                                            <label>年限法</label>
                                        </span>
                                        <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                            <input type="radio" name="method" value="2">
                                            <label>观察法</label>
                                        </span>
                                        <span class="col-sm-2  checkbox-inline">
                                            <input type="radio" name="method" value="12">
                                            <label>年限/观察法</label>
                                        </span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-md-12 newRateModelFrmA" style="display: none">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        年限法
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经济耐用年限
                                    </label>
                                    <div class="col-sm-5">
                                        <select name="durableLife" required="required"
                                                class="form-control search-select select2 durableLife">
                                        </select>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        已使用年限
                                    </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="useYear"
                                               data-rule-number='true' required="required"
                                               placeholder="已使用年限">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        残值率
                                    </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control residualValue"
                                               name="residualValue"
                                               readonly="readonly"
                                               placeholder="残值率">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        成新率
                                    </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="newRateA" readonly="readonly"
                                               placeholder="成新率">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        建筑结构
                                    </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control buildingStructure"
                                               name="buildingStructure" readonly="readonly"
                                               placeholder="建筑结构" required="required">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        权重
                                    </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="weightYear"
                                               data-rule-number='true' required="required"
                                               placeholder="权重">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 newRateModelFrmB" style="display:none;">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        观察法
                                    </label>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            成新率
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="newRateG"
                                                   data-rule-number='true' required="required"
                                                   placeholder="成新率">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            权重
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="weightG"
                                                   data-rule-number='true' required="required"
                                                   placeholder="权重">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-md-12 newRateModelFrm" style="display: none">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        <font color="red">计算所得成新率</font>
                                    </label>
                                    <div class="x-valid">
                                        <div class="col-sm-5">
                                            <label class="control-label newRateCorrecting">
                                                0
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="build.newRateModel.close();">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>