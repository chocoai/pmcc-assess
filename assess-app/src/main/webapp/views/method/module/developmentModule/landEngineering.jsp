<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="landEngineeringModel">
    <jsp:include page="../developmentModule/land/parameter.jsp"></jsp:include>
    <jsp:include page="../developmentModule/land/cost.jsp"></jsp:include>

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
                               name="constructionInstallationEngineeringFee" value="7929.30">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        基础设施费用 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="基础设施费用" class="form-control" readonly="readonly"
                               name="infrastructureCost">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        公共配套设施建设费 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="公共配套设施建设费" class="form-control" readonly="readonly"
                               name="infrastructureMatchingCost">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        开发期间税费 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="开发期间税费" class="form-control" readonly="readonly"
                               name="devDuring">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        其它工程费 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="其它工程费" class="form-control" readonly="readonly"
                               name="otherEngineeringCost">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        不可预见费 (计算金额)
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

    <jsp:include page="../developmentModule/land/designParameters.jsp"></jsp:include>

    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        设计费参数比率 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="设计费参数比率" class="form-control" readonly="readonly"
                               name="designFeeParameterRatio">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        管理费 (计算率)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="管理费" class="form-control" readonly="readonly"
                               name="managementExpenseCorrecting">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        管理费 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="管理费(金额)" class="form-control" readonly="readonly"
                               name="managementExpense">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        销售费用 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="销售费用(金额)" class="form-control" readonly="readonly"
                               name="salesFee">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        投资利息 (计算金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="投资利息(金额)" class="form-control" readonly="readonly"
                               name="interestInvestment">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        投资利润 (计算率)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="投资利润(计算率)" class="form-control" readonly="readonly"
                               name="investmentProfitCorrecting">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        销售税金及附加 (计算率)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="销售税金及附加(计算率)" class="form-control" readonly="readonly"
                               name="salesTaxAndAdditionalCorrecting">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        销售税金及附加 (金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="销售税金及附加(金额)" class="form-control" readonly="readonly"
                               name="salesTaxAndAdditional">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        地价 (金额)
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="地价(金额)" class="form-control" readonly="readonly"
                               name="landPrice">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        单元格 E33
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="单元格 E33" class="form-control" readonly="readonly"
                               name="estimateUnitPriceLandE33">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        单元格 F33
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="单元格 F33" class="form-control" readonly="readonly"
                               name="estimateUnitPriceLandF33">
                    </div>
                </div>
            </div>

        </form>
    </div>

    <jsp:include page="../developmentModule/land/resultView.jsp"></jsp:include>
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
                name: "建筑安装工程费"
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
                name: "营业税金及附加"
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

    landEngineering.loadData = function () {
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
                        $("#" + landEngineering.config.id).find("select." + landEngineering.config.inputConfig.infrastructureCost.tax).html(optionA);
                        $("#" + landEngineering.config.id).find("select." + landEngineering.config.inputConfig.infrastructureMatchingCost.tax).html(optionB);
                    }

                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    landEngineering.algorithm = {
        //不可售建筑面积
        nonSaleFloorAreaTaxFun: function () {
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
        }

        //契税率
        ,
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
        //营业税金及附加
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
        estimateUnitPriceLandC33:function () {
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
            temp = a/b *10000;
            temp = temp.toFixed(4);
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
            c = b /(1+a);
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
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.landPrice.key, temp);//地价金额
            landEngineering.algsObj.landPriceCorrecting();
        },
        //销售税金及附加 计算率 = C31+E31 ==> 营业税金及附加率+土地增值率
        salesTaxAndAdditionalCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.businessAdditional.tax, null);//营业税金及附加率
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
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.devDuring.tax, null);
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
        //建筑安装工程费 (计算金额) = 建筑安装工程费 * 总建筑面积小计 / 10000
        constructionInstallationEngineeringFee: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.tax, null);
            b = $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            c = (a * b) / 10000;
            c = c.toFixed(3);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.key, c);
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
            landEngineering.algsObj.constructionInstallationEngineeringFee();
            landEngineering.algsObj.infrastructureCost();
            landEngineering.algsObj.infrastructureMatchingCost();
            landEngineering.algsObj.devDuring();
            landEngineering.algsObj.otherEngineeringCost();
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
        init: function () {
            landEngineering.monitor.infrastructureCost();
            landEngineering.monitor.infrastructureMatchingCost();
        }
    };
    $(function () {
        landEngineering.loadData();
        landEngineering.inputEvent();
        landEngineering.monitor.init();
    });

</script>

