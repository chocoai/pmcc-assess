<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="landEngineeringModel">
    <jsp:include page="../developmentModule/commonParameter.jsp"></jsp:include>
    <jsp:include page="../developmentModule/land/cost.jsp"></jsp:include>
    <div>
        <form class="form-horizontal">
            <input type="hidden"
                   placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"
                   name="reconnaissanceDesign">
            <input type="hidden"
                   placeholder="建筑安装工程费" class="form-control" readonly="readonly"
                   name="constructionInstallationEngineeringFee" value="0">
            <input type="hidden"
                   placeholder="基础设施费用" class="form-control" readonly="readonly"
                   name="infrastructureCost">
            <input type="hidden"
                   placeholder="公共配套设施建设费" class="form-control" readonly="readonly"
                   name="infrastructureMatchingCost">
            <input type="hidden"
                   placeholder="开发期间税费" class="form-control" readonly="readonly"
                   name="devDuring">
            <input type="hidden"
                   placeholder="其它工程费" class="form-control" readonly="readonly"
                   name="otherEngineeringCost">
            <input type="hidden"
                   placeholder="不可预见费" class="form-control" readonly="readonly"
                   name="unforeseenExpenses">
        </form>
    </div>
    <jsp:include page="../developmentModule/land/designParameters.jsp"></jsp:include>
    <div>
        <form class="form-horizontal">
            <input type="hidden"
                   placeholder="设计费参数比率" class="form-control" readonly="readonly"
                   name="designFeeParameterRatio">
            <input type="hidden"
                   placeholder="管理费" class="form-control" readonly="readonly"
                   name="managementExpenseCorrecting">
            <input type="hidden"
                   placeholder="管理费(金额)" class="form-control" readonly="readonly"
                   name="managementExpense">
            <input type="hidden"
                   placeholder="销售费用(金额)" class="form-control" readonly="readonly"
                   name="salesFee">
            <input type="hidden"
                   placeholder="投资利息(金额)" class="form-control" readonly="readonly"
                   name="interestInvestment">
            <input type="hidden"
                   placeholder="投资利润(计算率)" class="form-control" readonly="readonly"
                   name="investmentProfitCorrecting">
            <input type="hidden"
                   placeholder="销售税金及附加(计算率)" class="form-control" readonly="readonly"
                   name="salesTaxAndAdditionalCorrecting">
            <input type="hidden"
                   placeholder="销售税金及附加(金额)" class="form-control" readonly="readonly"
                   name="salesTaxAndAdditional">
            <input type="hidden"
                   placeholder="地价(金额)" class="form-control" readonly="readonly"
                   name="landPrice">
            <input type="hidden"
                   placeholder="单元格 E33" class="form-control" readonly="readonly"
                   name="estimateUnitPriceLandE33">
            <input type="hidden"
                   placeholder="单元格 F33" class="form-control" readonly="readonly"
                   name="estimateUnitPriceLandF33">
        </form>
    </div>

    <jsp:include page="../developmentModule/land/resultView.jsp"></jsp:include>
    <div class="constructionInstallationEngineeringFeeClass" style="display: none;">
        <jsp:include page="/views/method/module/architecturalEngineering/landDevelopment.jsp"></jsp:include>

    </div>


</div>
<script>
    var landEngineering = new Object();

    landEngineering.config = {
        id: "landEngineeringModel",
        projectConstructionPeriod: "projectConstructionPeriod",//项目建设期(年)
        developedTime: "developedTime",//已开发时间(年)
        estimateUnitPriceLand: {
            name: "委估土地单价",
            C33: "estimateUnitPriceLandC33",
            E33: "estimateUnitPriceLandE33",
            F33: "estimateUnitPriceLandF33"
        },
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        inputConfig: {
            estimateSaleTotal: {
                key: "estimateSaleTotal",
                name: "预期销售合计",
                tax: ""
            },
            smallGarageTotalPrice: {
                key: "smallGarageTotalPrice",
                tax: "",
                name: "销售合价"
            },
            totalGrossFloorArea: {
                key: "totalGrossFloorArea",
                tax: "",
                correcting: "",
                name: "总建筑面积小计"
            },
            nonSaleFloorArea: {
                key: "nonSaleFloorArea",
                tax: "nonSaleFloorAreaTax",
                correcting: "",
                name: "不可售建筑面积"
            },
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
            constructionCostSubtotal: {
                key: "constructionCostSubtotal",
                tax: "",
                name: "工程建设成本小计"
            },
            unforeseenExpenses: {
                key: "unforeseenExpenses",
                tax: "unforeseenExpensesTax",
                correcting: "",
                name: "不可预见费"
            },
            //设计费参数比率
            designFeeParameterRatio: {
                key: "designFeeParameterRatio",
                tax: "",
                name: "设计费参数比率"
            },
            deed: {
                key: "deed",
                tax: "deedTax",
                correcting: "deedCorrecting",
                name: "契税"
            },
            transactionCost: {
                key: "transactionCost",
                tax: "transactionCostTax",
                correcting: "transactionCostCorrecting",
                name: "交易费"
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
                tax: "interestInvestmentTax",
                correcting: "interestInvestmentCorrecting",
                name: "投资利息"
            },
            investmentProfit: {
                key: "investmentProfit",
                tax: "investmentProfitTax",
                correcting: "investmentProfitCorrecting",
                name: "投资利润"
            },
            salesTaxAndAdditional: {
                key: "salesTaxAndAdditional",
                tax: "",
                correcting: "salesTaxAndAdditionalCorrecting",
                name: "销售税金及附加"
            },
            businessAdditional: {
                key: "businessAdditional",
                tax: "businessAdditionalTax",
                name: "增值税金及附加"
            },
            landIncrement: {
                key: "landIncrement",
                tax: "landIncrementTax",
                name: "土地增值"
            },
            landPrice: {
                key: "landPrice",
                tax: "",
                correcting: "landPriceCorrecting",
                name: "地价"
            }
        }
    };

    landEngineering.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    landEngineering.dataObject = null;

    landEngineering.select2InitMethodWrite = function (data, name) {
        if (landEngineering.isEmpty(data)) {
            $("#" + landEngineering.config.id).find("select." + name).val(data).trigger("change");
        } else {
            $("#" + landEngineering.config.id).find("select." + name).val(null).trigger("change");
        }
    };

    landEngineering.loadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructure/listInfrastructure",
            type: "get",
            data: {
                province: "${schemeAreaGroup.province}",
                city: "${schemeAreaGroup.city}",
                district: "${schemeAreaGroup.district}"
            },
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var dataA = result.data;
                    var optionA = "<option>请选择</option>";
                    var optionB = "<option>请选择</option>";
                    var optionC = "<option>请选择</option>";
                    if (dataA.length > 0) {
                        var tempA, tempB, tempC;
                        for (var i = 0; i < dataA.length; i++) {
                            tempA = dataA[i].temp + " (" + dataA[i].costTotal + ")";
                            tempB = dataA[i].temp + " (" + dataA[i].matchingCostTotal + ")";
                            tempC = dataA[i].temp + " (" + dataA[i].devTaxTotal + ")";
                            if (i == 0) {
                                optionA += "<option selected='selected' value='" + dataA[i].costTotal + "'>" + tempA + "</option>";
                                optionB += "<option selected='selected' value='" + dataA[i].matchingCostTotal + "'>" + tempB + "</option>";
                                optionC += "<option selected='selected' value='" + dataA[i].devTaxTotal + "'>" + tempC + "</option>";
                            } else {
                                optionA += "<option value='" + dataA[i].costTotal + "'>" + tempA + "</option>";
                                optionB += "<option value='" + dataA[i].matchingCostTotal + "'>" + tempB + "</option>";
                                optionC += "<option value='" + dataA[i].devTaxTotal + "'>" + tempC + "</option>";
                            }
                        }
                        $("#" + landEngineering.config.id).find("select." + landEngineering.config.inputConfig.infrastructureCost.tax).html(optionA).trigger('change');
                        $("#" + landEngineering.config.id).find("select." + landEngineering.config.inputConfig.infrastructureMatchingCost.tax).html(optionB).trigger('change');
                        $("#" + landEngineering.config.id).find("select." + landEngineering.config.inputConfig.devDuring.tax).html(optionC).trigger('change');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
            type: "get",
            data: {
                province: "${schemeAreaGroup.province}",
                city: "${schemeAreaGroup.city}", bisNationalUnity: "true"
            },//传入全国性质,和具体区域
            dataType: "json",
            success: function (result) {
                var a = 0, b = 0, c = 0, d = 0, e = 0, g = 0, h = 0, k = 0, v = 0;
                $.each(result.data, function (i, n) {
                    if (n.typeName == "土地增值税") {
                        v = Number(n.taxRate);
                        landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.landIncrement.tax, AssessCommon.pointToPercent(v));//土地增值率
                        landEngineering.algsObj.salesTaxAndAdditionalCorrecting();//销售税金及附加 计算率
                    }
                });
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    landEngineering.algorithm = {
        //不可售建筑面积
        nonSaleFloorAreaTaxFun: function () {
            console.log("test");
            landEngineering.algsObj.totalGrossFloorArea();
        },
        //勘察设计和前期工程费
        reconnaissanceDesignTaxFun: function () {
            landEngineering.algsObj.reconnaissanceDesign();
        },
        //基础设施建设费
        infrastructureCostTaxFun: function () {
            landEngineering.algsObj.infrastructureCost();
        },
        //公共配套设施建设费
        infrastructureMatchingCostTaxFun: function () {
            landEngineering.algsObj.infrastructureMatchingCost();
        },
        //开发期间税费
        devDuringTaxFun: function () {
            landEngineering.algsObj.devDuring();
        },
        //其它工程费率
        otherEngineeringCostTaxFun: function () {
            landEngineering.algsObj.otherEngineeringCost();
        },
        //不可预见费
        unforeseenExpensesTaxFun: function () {
            landEngineering.algsObj.unforeseenExpenses();
        },
        //契税率
        deedTaxFun: function () {
            landEngineering.algsObj.designFeeParameterRatio();
        },
        //交易费率
        transactionCostTaxFun: function () {
            landEngineering.algsObj.designFeeParameterRatio();
        },
        //管理费率
        managementExpenseTaxFun: function () {
            landEngineering.algsObj.managementExpenseCorrecting();//管理费
            landEngineering.algsObj.managementExpense();//管理费 金额
        },
        //销售费用率
        salesFeeTaxFun: function () {
            landEngineering.algsObj.salesFee();//销售费用 金额
        },
        //投资利息率
        interestInvestmentTaxFun: function () {
            var projectConstructionPeriod = $("#" + landEngineering.config.projectConstructionPeriod).val();
            if (!landEngineering.isEmpty(projectConstructionPeriod)) {//项目建设期不能为null,至于其它在调用此方法时就会校验
                toastr.success('项目建设期不能为null');
                return false;
            }
            landEngineering.algsObj.interestInvestment();//投资利息 金额
            landEngineering.algsObj.estimateUnitPriceLandE33();//E33
        },
        //投资利润
        investmentProfitTaxFun: function () {
            landEngineering.algsObj.investmentProfit();//投资利润 金额
            landEngineering.algsObj.investmentProfitCorrecting();//投资利润 计算率
        },
        //增值税金及附加
        businessAdditionalTaxFun: function () {
            landEngineering.algsObj.salesTaxAndAdditionalCorrecting();//销售税金及附加 计算率
        },
        //土地增值
        landIncrementTaxFun: function () {
            landEngineering.algsObj.salesTaxAndAdditionalCorrecting();//销售税金及附加 计算率
        }
    };

    landEngineering.algsObj = {
        //单元格 计算 excel 单元格 C33 公式=D32/C13*10000 (此方法未测试)
        estimateUnitPriceLandC33: function () {
            var a = 0, b = 0, c = 0, d = 0, temp = 0;
            a = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.landPrice.correcting).html();//D32
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            a = Number(a);
            b = Number(b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            temp = a / b * 10000;
            temp = temp.toFixed(4);
            if (!AssessCommon.isNumber(temp)) {
                return false;
            }
            $("#" + landEngineering.config.id).find("." + landEngineering.config.estimateUnitPriceLand.C33).html(temp);
        },
        //委估土地单价 计算 excel 单元格 F33 公式=D24+D25+D26+D29+E33
        estimateUnitPriceLandF33: function () {
            var a = 0, b = 0, c = 0, d = 0, temp = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.estimateUnitPriceLand.E33, null);//E33
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.correcting, null);//管理费计算率 D26
            c = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.investmentProfit.correcting, null);//投资利润 计算率 D29
            d = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率-1 = D24+D25
            temp = a + b + c + d - 1;
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(c)) {
                return false;
            }
            temp = temp.toFixed(4);
            if (!AssessCommon.isNumber(temp)) {
                return false;
            }
            landEngineering.algsObj.getAndSet("set", landEngineering.config.estimateUnitPriceLand.F33, temp);//F33
            landEngineering.algsObj.landPriceCorrecting();
        },
        //委估土地单价 计算 excel 单元格 E33 公式=ROUND(((1+C28)^D28-1)*E23+((1+C28)^(D28/2)-1)*D26,4)
        estimateUnitPriceLandE33: function () {
            var a = 0, b = 0, temp = 0;
            var interestInvestment = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.interestInvestment.tax, null);//投资利息率 C28
            var projectConstructionPeriod = $("#" + landEngineering.config.projectConstructionPeriod).val();//D28
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率 E23
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.correcting, null);//管理费计算率 D26
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(interestInvestment)) {
                return false;
            }
            temp = (Math.pow(1 + interestInvestment, projectConstructionPeriod) - 1) * a;
            temp += (Math.pow(1 + interestInvestment, projectConstructionPeriod / 2) - 1) * b;
            temp = temp.toFixed(4);
            if (!AssessCommon.isNumber(temp)) {
                return false;
            }
            landEngineering.algsObj.getAndSet("set", landEngineering.config.estimateUnitPriceLand.E33, temp);//E33
            landEngineering.algsObj.estimateUnitPriceLandF33();//计算单元格F33
        },
        //地价  == >=E32/(1+F33)
        landPriceCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.estimateUnitPriceLand.F33, null);//F33
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.landPrice.key, null);//地价金额 E32
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            c = b / (1 + a);
            c = c.toFixed(4);
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.landPrice.correcting).html(c);
            landEngineering.algsObj.estimateUnitPriceLandC33();
        },
        // 地价金额  ==> F11-E21-E22-SUM(E26:E29)-E30)
        landPrice: function () {
            var a = 0, b = 0, c = 0, d = 0, f = 0, g = 0, h = 0, k = 0, temp = 0;
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.smallGarageTotalPrice.key, null);//销售合价
            c = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            c = Number(c);
            a = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html();//不可预见费 (金额)
            a = Number(a);
            f = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.key, null);//管理费 金额
            d = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.salesFee.key, null);//销售费用 金额
            h = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.investmentProfit.key).html()//投资利润 金额
            h = Number(h);
            g = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.interestInvestment.key, null);//投资利息 金额
            k = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.salesTaxAndAdditional.key, null);//销售税金及附加 金额
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(h)) {
                return false;
            }
            if (!AssessCommon.isNumber(g)) {
                return false;
            }
            temp = b - c - a - (f + d + h + g) - k;
            temp = temp.toFixed(4);
            if (!AssessCommon.isNumber(temp)) {
                return false;
            }
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.landPrice.key, temp);//地价金额
            landEngineering.algsObj.landPriceCorrecting();
        },
        //销售税金及附加 计算率 = C31+E31 ==> 增值税金及附加率+土地增值率
        salesTaxAndAdditionalCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.businessAdditional.tax, null);//增值税金及附加率
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.landIncrement.tax, null);//土地增值率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = a + b;
            c = c.toFixed(4);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.salesTaxAndAdditional.correcting, c);//销售税金及附加 计算率
            landEngineering.algsObj.salesTaxAndAdditional();//销售税金及附加 金额
        },
        //销售税金及附加 金额 ==> =IF(F11=" "," ",F11*C30)
        salesTaxAndAdditional: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.salesTaxAndAdditional.correcting, null);//销售税金及附加 计算率
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.smallGarageTotalPrice.key, null);//销售合价
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b;
            c = c.toFixed(4);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.salesTaxAndAdditional.key, c);//销售税金及附加 金额
            landEngineering.algsObj.landPrice();//地价 金额
        },
        //投资利润 计算率 =  (设计费参数比率+管理费计算率)*投资利润
        investmentProfitCorrecting: function () {
            var a = 0, b = 0, c = 0, d = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.correcting, null);//管理费计算率
            c = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.investmentProfit.tax, null);//投资利润率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(c)) {
                return false;
            }
            d = (a + b) * c;
            d = d.toFixed(4);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.investmentProfit.correcting, d);//投资利润 计算率
            landEngineering.algsObj.estimateUnitPriceLandF33();//计算单元格F33
        },
        //投资利润 金额 =(E21+E22+SUM(E26:E27))*C29
        investmentProfit: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html();//不可预见费金额
            b = Number(b);
            a = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            a = Number(a);
            c = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.key, null);//管理费 金额
            d = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.salesFee.key, null);//销售费用 金额
            e = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.investmentProfit.tax, null);//投资利润率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            f = (a + b + c + d) * e;
            f = f.toFixed(4);
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.investmentProfit.key).html(f)//投资利润 金额
            landEngineering.algsObj.landPrice();//地价 金额
        },
        // excel 公式: (E16+E18+E19+E20+E22+SUM(E26:E27))*((1+C28)^(D28/2)-1)+(SUM(E15+E17))*((1+C28)^(D28)-1))
        /*
         投资利息 金额 = (建筑安装工程费金额+公共配套设施建设费金额+开发期间税费金额 +其它工程费金额+不可预见费金额(管理费金额+销售费用金额)) * ( (1+投资利息率)^(项目建设期/2) - 1)  ) +
         (勘察设计和前期工程费金额 +基础设施建设费金额)*( (1+投资利息率)^ (项目建设期) -1 )
         */
        interestInvestment: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, k = 0;
            var temp = 0;
            var interestInvestment = 0;
            interestInvestment = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.interestInvestment.tax, null);//投资利息率
            var projectConstructionPeriod = $("#" + landEngineering.config.projectConstructionPeriod).val();
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.key, null);//建筑安装工程费 (金额)
            c = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.infrastructureMatchingCost.key, null);//公共配套设施建设费 (金额)
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.devDuring.key, null);//开发期间税费 (金额)
            e = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.otherEngineeringCost.key, null);//其它工程费 (金额)
            f = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.key, null);//管理费 金额
            g = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.salesFee.key, null);//销售费用 金额
            k = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html();//不可预见费金额
            k = Number(k);

            h = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.reconnaissanceDesign.key, null);//勘察设计和前期工程费 (金额)
            i = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.infrastructureCost.key, null);//基础设施建设费 (金额)
            if (!landEngineering.isEmpty(projectConstructionPeriod)) {//项目建设期不能为null,至于其它在调用此方法时就会校验
                return false;
            }
            temp = (a + c + b + e + f + g + k) * (Math.pow(1 + interestInvestment, projectConstructionPeriod / 2) - 1);
            temp += (h + i) * (Math.pow(1 + interestInvestment, projectConstructionPeriod) - 1);
            temp = temp.toFixed(3);
            if (!AssessCommon.isNumber(temp)) {
                return false;
            }
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.interestInvestment.key, temp);//投资利息 金额
            landEngineering.algsObj.landPrice();//地价 金额
        },
        //销售费用 金额 = 销售费用率 * 销售合价
        salesFee: function () {
            var a = 0, b = 0, c = 0;
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.smallGarageTotalPrice.key, null);//销售合价
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.salesFee.tax, null);//销售费用率
            c = (a * b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.salesFee.key, c);//销售费用 金额
            landEngineering.algsObj.interestInvestment();//投资利息 金额
            landEngineering.algsObj.investmentProfit();//投资利润 金额
        },
        //管理费 金额 = (不可预见费 (金额) + 工程建设成本小计)*管理费率
        managementExpense: function () {
            var a = 0, b = 0, c = 0, d = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.tax, null);//管理费率
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html();//不可预见费 (金额)
            b = Number(b);
            c = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            c = Number(c);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(c)) {
                return false;
            }
            d = (c + b) * a;
            d = d.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.managementExpense.key, d);//管理费 金额
            landEngineering.algsObj.interestInvestment();//投资利息 金额
            landEngineering.algsObj.investmentProfit();//投资利润 金额
        },
        //管理费计算率 = 管理费率 * 设计费参数比率
        managementExpenseCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.managementExpense.tax, null);//管理费率
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率
            c = (a * b);
            c = c.toFixed(4);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.managementExpense.correcting, c);//管理费计算率
            landEngineering.algsObj.investmentProfitCorrecting();//投资利润 计算率
            landEngineering.algsObj.estimateUnitPriceLandE33();//E33
        },
        //设计费参数比率
        designFeeParameterRatio: function () {
            var a = 0, b = 0, c = 0;
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.deed.tax, null);//契税率
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.transactionCost.tax, null);//交易费率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = 1 + a + b;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.designFeeParameterRatio.key, c);//设计费参数比率
            landEngineering.algsObj.managementExpenseCorrecting();//管理费 计算率
        },
        //不可预见费 (金额) = 不可预见费 * 工程建设成本小计
        unforeseenExpenses: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            a = Number(a);
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.unforeseenExpenses.tax, null);//不可预见费
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = (a * b);
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.unforeseenExpenses.key, c);//不可预见费 (金额)
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html(c);
            landEngineering.algsObj.managementExpense();//管理费 金额
        },
        //工程建设成本小计
        constructionCostSubtotal: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.otherEngineeringCost.key, null);//其它工程费 (金额)
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.devDuring.key, null);//开发期间税费 (金额)
            c = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.infrastructureMatchingCost.key, null);//公共配套设施建设费 (金额)
            d = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.infrastructureCost.key, null);//基础设施建设费 (金额)
            e = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.reconnaissanceDesign.key, null);//勘察设计和前期工程费 (金额)
            f = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.key, null);//建筑安装工程费 (金额)
            g = a + b + c + d + e + f;
            g = g.toFixed(3);
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html(g);
            landEngineering.algsObj.unforeseenExpenses();//不可预见费 (金额)
        },
        //其它工程费 (金额) = 其它工程费*总建筑面积小计 / 1000
        otherEngineeringCost: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.otherEngineeringCost.tax, null);
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            c = (a * b) / 10000;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.otherEngineeringCost.key, c);
            landEngineering.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //开发期间税费 (金额) = 开发期间税费*总建筑面积小计 /1000
        devDuring: function () {
            var a = 0, b = 0, c = 0;
            // a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.devDuring.tax, null);
            a = $("#" + landEngineering.config.id).find("select." + landEngineering.config.inputConfig.devDuring.tax).val();
            a = Number(a);
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            c = (a * b) / 10000;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.devDuring.key, c);
            landEngineering.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //公共配套设施建设费 (金额) = 公共配套设施建设费*总建筑面积小计 /1000
        infrastructureMatchingCost: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + landEngineering.config.id + " ." + landEngineering.config.inputConfig.infrastructureMatchingCost.tax).eq(1).val();//公共配套设施建设费
            a = Number(a);
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = (a * b) / 10000;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.infrastructureMatchingCost.key, c);
            landEngineering.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //基础设施建设费 (金额) = 基础设施建设费*总建筑面积小计 /1000
        infrastructureCost: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + landEngineering.config.id + " ." + landEngineering.config.inputConfig.infrastructureCost.tax).eq(1).val();//基础设施建设费
            a = Number(a);
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.infrastructureCost.key, c);
            landEngineering.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //勘察设计和前期工程费 (金额) = 勘察设计和前期工程费 * 建筑安装工程费 (计算金额)
        reconnaissanceDesign: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.reconnaissanceDesign.tax, null);
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            c = a * b;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.reconnaissanceDesign.key, c);
            landEngineering.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //建筑安装工程费 (计算金额) = 建筑安装工程费计算金额(控件数据)  / 10000
        constructionInstallationEngineeringFee: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.tax, null);
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            c = (a * b) / 10000;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.key, c);
            landEngineering.algsObj.reconnaissanceDesign();
            landEngineering.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //总建筑面积小计 = 预期销售合计 + 不可售建筑面积
        totalGrossFloorArea: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.nonSaleFloorArea.tax, null);
            b = Number($("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.estimateSaleTotal.key).html());
            c = a + b;
            c = c.toFixed(4);
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html(c);
            try {
                landEngineering.algsObj.constructionInstallationEngineeringFee();
                landEngineering.algsObj.infrastructureCost();
                landEngineering.algsObj.infrastructureMatchingCost();
                landEngineering.algsObj.devDuring();
                landEngineering.algsObj.otherEngineeringCost();
            } catch (e) {
            }
        },
        getAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("#" + landEngineering.config.id).find("input[name='" + name + "']").val();
                text = landEngineering.algsObj.specialTreatment(text);
                text = Number(text);
                return text;
            }
            if (flag == 'set') {
                $("#" + landEngineering.config.id).find("input[name='" + name + "']").val(data);
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
            if (landEngineering.algsObj.isNotNull(obj)) {
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

    landEngineering.inputEvent = function () {
        $.each(landEngineering.config.inputConfig, function (i, n) {
            var tax = n.tax;
            var input = $("#" + landEngineering.config.id).find("input[name='" + tax + "']");
            if (landEngineering.isEmpty(tax)) {
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    try {
                        if (landEngineering.isEmpty(value)) {
                            var funName = "landEngineering.algorithm." + tax + "Fun(" + ")";
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
    landEngineering.constructionInstallationEngineeringFeeEvent = {
        show: function (input) {
            layer.open({
                type: 1,
                title: '建筑安装工程费',
                area: ['920px', '740px'],
                offset: 't',
                btn: ['保存'],
                yes: function () {
                    $(input).val(landEngineeringDevelopment.getTotal);
                    layer.close(layer.index);
                    landEngineering.constructionInstallationEngineeringFeeEvent.eventSave(landEngineeringDevelopment.getJsonValue());
                },
                content: $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionInstallationEngineeringFee.class),
                success:function () {
                    landEngineeringDevelopment.viewInit();
                }
            });
        },
        eventSave: function (data) {
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.tax, landEngineeringDevelopment.getTotal());
            landEngineering.algsObj.constructionInstallationEngineeringFee();
            landEngineering.constructionInstallationEngineeringFeeEvent.serverSave(data);
        },
        serverSave: function (data) {
            var url = "${pageContext.request.contextPath}/marketCost/saveAndUpdateMdCostAndDevelopmentOther";
            $.ajax({
                url: url,
                type: "post",
                data: {
                    jsonContent: JSON.stringify(data),
                    type: "MdDevelopmentArchitectural",
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
    landEngineering.monitor = {
        // 基础设施建设费
        infrastructureCost: function () {
            var tax = landEngineering.config.inputConfig.infrastructureCost.tax;
            $("#" + landEngineering.config.id + " ." + tax).change(function () {
                var funName = "landEngineering.algorithm." + tax + "Fun(" + ")";
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
            var tax = landEngineering.config.inputConfig.infrastructureMatchingCost.tax;
            $("#" + landEngineering.config.id + " ." + tax).change(function () {
                var funName = "landEngineering.algorithm." + tax + "Fun(" + ")";
                console.log(funName);
                try {
                    eval(funName);
                } catch (e) {
                    console.log("没有这个函数");
                    console.log(e);
                }
            });
        },
        //开发期间税费
        devDuring: function () {
            var tax = landEngineering.config.inputConfig.devDuring.tax;
            $("#" + landEngineering.config.id + " ." + tax).change(function () {
                var funName = "landEngineering.algorithm." + tax + "Fun(" + ")";
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
            landEngineering.monitor.infrastructureCost();
            landEngineering.monitor.infrastructureMatchingCost();
            landEngineering.monitor.devDuring();
        }
    };


    /**
     * @author:  zch
     * 描述:收集数据
     * @date:2018-10-12
     **/
    landEngineering.formParams = function () {
        var item = {};
        var table = $("#" + landEngineering.config.id).find("#" + parameter.config.frm + " table").eq(0).html();
        var forms = $("#" + landEngineering.config.id).find("form");
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
        item.projectConstructionPeriod = $("#" + landEngineering.config.projectConstructionPeriod).val();
        item.developedTime = $("#" + landEngineering.config.developedTime).val();

        item.estimateSaleTotal = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.estimateSaleTotal.key).html();
        item.totalGrossFloorArea = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
        item.constructionCostSubtotal = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html();
        item.unforeseenExpenses = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html();
        item.investmentProfit = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.investmentProfit.key).html();
        item.landPriceCorrecting = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.landPrice.correcting).html();
        item.estimateUnitPriceLandC33 = $("#" + landEngineering.config.id).find("." + landEngineering.config.estimateUnitPriceLand.C33).html();
        item.table = table;
        return item;
    };

    landEngineering.specialTreatment2 = function (obj) {
        if (landEngineering.isEmpty(obj)) {
            var nnn = "" + obj + "";
            var str = nnn.substring(nnn.length - 1, nnn.length);
            if (str == '%') {//检测是否为百分比
                return nnn;
            } else {
                str = AssessCommon.pointToPercent(Number(nnn));
                return str;
            }
            return obj;
        }
        return 0;
    };

    /**
     * @author:  zch
     * 描述:赋值
     * @date:2018-10-12
     **/
    landEngineering.initForm = function (item) {
        var forms = $("#" + landEngineering.config.id).find("form");
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        if (landEngineering.isEmpty(item.projectConstructionPeriod)) {
            $("#" + landEngineering.config.projectConstructionPeriod).val(null);
        }
        if (landEngineering.isEmpty(item.developedTime)) {
            $("#" + landEngineering.config.developedTime).val(null);
        }

        $.each(forms, function (i, n) {
            $(n).initForm(item);
        });
        if (landEngineering.isEmpty(item.projectConstructionPeriod)) {
            $("#" + landEngineering.config.projectConstructionPeriod).val(item.projectConstructionPeriod);
        }
        if (landEngineering.isEmpty(item.developedTime)) {
            $("#" + landEngineering.config.developedTime).val(item.developedTime);
        }
        if (landEngineering.isEmpty(item.estimateSaleTotal)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.estimateSaleTotal.key).html(item.estimateSaleTotal);
        }
        if (landEngineering.isEmpty(item.totalGrossFloorArea)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html(item.totalGrossFloorArea);
        }
        if (landEngineering.isEmpty(item.constructionCostSubtotal)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.constructionCostSubtotal.key).html(item.constructionCostSubtotal);
        }
        if (landEngineering.isEmpty(item.unforeseenExpenses)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.unforeseenExpenses.key).html(item.unforeseenExpenses);
        }
        if (landEngineering.isEmpty(item.investmentProfit)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.investmentProfit.key).html(item.investmentProfit);
        }
        if (landEngineering.isEmpty(item.landPriceCorrecting)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.landPrice.correcting).html(item.landPriceCorrecting);
        }
        if (landEngineering.isEmpty(item.estimateUnitPriceLandC33)) {
            $("#" + landEngineering.config.id).find("." + landEngineering.config.estimateUnitPriceLand.C33).html(item.estimateUnitPriceLandC33);
        }
        if (landEngineering.isEmpty(item.table)) {
            $("#" + landEngineering.config.id).find("#" + parameter.config.frm + " table").eq(0).html(item.table);
        }
        $.each(forms, function (i, n) {
            var inputs = $(n).find(":input");
            $.each(inputs, function (i, k) {
                var kk = $(k);
                var className = kk.attr("class");
                var str = null;
                var name = kk.attr("name");
                if (className.indexOf("x-percent") != -1) {
                    try {
                        str = eval("item." + name);
                        if (landEngineering.isEmpty(str)) {
                            str = landEngineering.specialTreatment2(str);
                            landEngineering.algsObj.getAndSet("set", name, str);
                        }
                    } catch (e) {
                    }
                }
            });
        });
        $(function () {
            parameter.init();
        });
    };

    $(function () {
        landEngineering.loadData();
        landEngineering.inputEvent();
        landEngineering.monitor.init();
    });

</script>

