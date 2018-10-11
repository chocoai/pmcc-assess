<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="underConstructionModel">
    <jsp:include page="../developmentModule/underConstruction/parameter.jsp"></jsp:include>
    <jsp:include page="../developmentModule/underConstruction/cost.jsp"></jsp:include>

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
                               name="constructionInstallationEngineeringFee" value="0">
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

    <jsp:include page="../developmentModule/underConstruction/designParameters.jsp"></jsp:include>

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

    <jsp:include page="../developmentModule/underConstruction/resultView.jsp"></jsp:include>
    <div class="constructionInstallationEngineeringFeeClass" style="display: none;">
        <jsp:include page="/views/method/module/architecturalEngineering/underDevelopment.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="underConstruction.constructionInstallationEngineeringFeeEvent.close()">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="underConstruction.constructionInstallationEngineeringFeeEvent.eventSave()">
            </div>
        </div>
    </div>
</div>

<script>
    var underConstruction = new Object();

    underConstruction.config = {
        id: "underConstructionModel",
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

    underConstruction.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    underConstruction.loadData = function () {
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
                        $("#" + underConstruction.config.id).find("select." + underConstruction.config.inputConfig.infrastructureCost.tax).html(optionA);
                        $("#" + underConstruction.config.id).find("select." + underConstruction.config.inputConfig.infrastructureMatchingCost.tax).html(optionB);
                    }

                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    underConstruction.algorithm = {
        //不可售建筑面积
        nonSaleFloorAreaTaxFun: function () {
            underConstruction.algsObj.totalGrossFloorArea();
        },
        //勘察设计和前期工程费
        reconnaissanceDesignTaxFun: function () {
            underConstruction.algsObj.reconnaissanceDesign();
        },
        //基础设施建设费
        infrastructureCostTaxFun: function () {
            underConstruction.algsObj.infrastructureCost();
        },
        //公共配套设施建设费
        infrastructureMatchingCostTaxFun: function () {
            underConstruction.algsObj.infrastructureMatchingCost();
        },
        //开发期间税费
        devDuringTaxFun: function () {
            underConstruction.algsObj.devDuring();
        },
        //其它工程费率
        otherEngineeringCostTaxFun: function () {
            underConstruction.algsObj.otherEngineeringCost();
        },
        //不可预见费
        unforeseenExpensesTaxFun: function () {
            underConstruction.algsObj.unforeseenExpenses();
        }

        //契税率
        ,
        deedTaxFun: function () {
            underConstruction.algsObj.designFeeParameterRatio();
        },
        //交易费率
        transactionCostTaxFun: function () {
            underConstruction.algsObj.designFeeParameterRatio();
        },
        //管理费率
        managementExpenseTaxFun: function () {
            underConstruction.algsObj.managementExpenseCorrecting();//管理费
            underConstruction.algsObj.managementExpense();//管理费 金额
        },
        //销售费用率
        salesFeeTaxFun: function () {
            underConstruction.algsObj.salesFee();//销售费用 金额
        },
        //投资利息率
        interestInvestmentTaxFun: function () {
            var projectConstructionPeriod = $("#" + underConstruction.config.projectConstructionPeriod).val();
            if (!underConstruction.isEmpty(projectConstructionPeriod)) {//项目建设期不能为null,至于其它在调用此方法时就会校验
                toastr.success('项目建设期不能为null');
                return false;
            }
            underConstruction.algsObj.interestInvestment();//投资利息 金额
            underConstruction.algsObj.estimateUnitPriceLandE33();//E33
        },
        //投资利润
        investmentProfitTaxFun: function () {
            underConstruction.algsObj.investmentProfit();//投资利润 金额
            underConstruction.algsObj.investmentProfitCorrecting();//投资利润 计算率
        },
        //营业税金及附加
        businessAdditionalTaxFun: function () {
            underConstruction.algsObj.salesTaxAndAdditionalCorrecting();//销售税金及附加 计算率
        },
        //土地增值
        landIncrementTaxFun: function () {
            underConstruction.algsObj.salesTaxAndAdditionalCorrecting();//销售税金及附加 计算率
        }
    };

    underConstruction.algsObj = {
        //单元格 计算 excel 单元格 C33 公式=D32/C13*10000 (此方法未测试)
        estimateUnitPriceLandC33: function () {
            var a = 0, b = 0, c = 0, d = 0, temp = 0;
            a = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.landPrice.correcting).html();//D32
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
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
            $("#" + underConstruction.config.id).find("." + underConstruction.config.estimateUnitPriceLand.C33).html(temp);
        },
        //委估土地单价 计算 excel 单元格 F33 公式=D24+D25+D26+D29+E33
        estimateUnitPriceLandF33: function () {
            var a = 0, b = 0, c = 0, d = 0, temp = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.estimateUnitPriceLand.E33, null);//E33
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.correcting, null);//管理费计算率 D26
            c = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.investmentProfit.correcting, null);//投资利润 计算率 D29
            d = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率-1 = D24+D25
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
            underConstruction.algsObj.getAndSet("set", underConstruction.config.estimateUnitPriceLand.F33, temp);//F33
            underConstruction.algsObj.landPriceCorrecting();
        },
        //委估土地单价 计算 excel 单元格 E33 公式=ROUND(((1+C28)^D28-1)*E23+((1+C28)^(D28/2)-1)*D26,4)
        estimateUnitPriceLandE33: function () {
            var a = 0, b = 0, temp = 0;
            var interestInvestment = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.interestInvestment.tax, null);//投资利息率 C28
            var projectConstructionPeriod = $("#" + underConstruction.config.projectConstructionPeriod).val();//D28
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率 E23
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.correcting, null);//管理费计算率 D26
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
            underConstruction.algsObj.getAndSet("set", underConstruction.config.estimateUnitPriceLand.E33, temp);//E33
            underConstruction.algsObj.estimateUnitPriceLandF33();//计算单元格F33
        },
        //地价  == >=E32/(1+F33)
        landPriceCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.estimateUnitPriceLand.F33, null);//F33
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.landPrice.key, null);//地价金额 E32
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            c = b / (1 + a);
            c = c.toFixed(4);
            $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.landPrice.correcting).html(c);
            underConstruction.algsObj.estimateUnitPriceLandC33();
        },
        // 地价金额  ==> F11-E21-E22-SUM(E26:E29)-E30)
        landPrice: function () {
            var a = 0, b = 0, c = 0, d = 0, f = 0, g = 0, h = 0, k = 0, temp = 0;
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.smallGarageTotalPrice.key, null);//销售合价
            c = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            c = Number(c);
            a = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.unforeseenExpenses.key).html();//不可预见费 (金额)
            a = Number(a);
            f = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.key, null);//管理费 金额
            d = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.salesFee.key, null);//销售费用 金额
            h = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.investmentProfit.key).html()//投资利润 金额
            h = Number(h);
            g = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.interestInvestment.key, null);//投资利息 金额
            k = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.salesTaxAndAdditional.key, null);//销售税金及附加 金额
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
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.landPrice.key, temp);//地价金额
            underConstruction.algsObj.landPriceCorrecting();
        },
        //销售税金及附加 计算率 = C31+E31 ==> 营业税金及附加率+土地增值率
        salesTaxAndAdditionalCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.businessAdditional.tax, null);//营业税金及附加率
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.landIncrement.tax, null);//土地增值率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = a + b;
            c = c.toFixed(4);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.salesTaxAndAdditional.correcting, c);//销售税金及附加 计算率
            underConstruction.algsObj.salesTaxAndAdditional();//销售税金及附加 金额
        },
        //销售税金及附加 金额 ==> =IF(F11=" "," ",F11*C30)
        salesTaxAndAdditional: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.salesTaxAndAdditional.correcting, null);//销售税金及附加 计算率
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.smallGarageTotalPrice.key, null);//销售合价
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b;
            c = c.toFixed(4);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.salesTaxAndAdditional.key, c);//销售税金及附加 金额
            underConstruction.algsObj.landPrice();//地价 金额
        },
        //投资利润 计算率 =  (设计费参数比率+管理费计算率)*投资利润
        investmentProfitCorrecting: function () {
            var a = 0, b = 0, c = 0, d = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.correcting, null);//管理费计算率
            c = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.investmentProfit.tax, null);//投资利润率
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
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.investmentProfit.correcting, d);//投资利润 计算率
            underConstruction.algsObj.estimateUnitPriceLandF33();//计算单元格F33
        },
        //投资利润 金额 =(E21+E22+SUM(E26:E27))*C29
        investmentProfit: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.unforeseenExpenses.key).html();//不可预见费金额
            b = Number(b);
            a = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            a = Number(a);
            c = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.key, null);//管理费 金额
            d = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.salesFee.key, null);//销售费用 金额
            e = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.investmentProfit.tax, null);//投资利润率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            f = (a + b + c + d) * e;
            f = f.toFixed(4);
            $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.investmentProfit.key).html(f)//投资利润 金额
            underConstruction.algsObj.landPrice();//地价 金额
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
            interestInvestment = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.interestInvestment.tax, null);//投资利息率
            var projectConstructionPeriod = $("#" + underConstruction.config.projectConstructionPeriod).val();
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.constructionInstallationEngineeringFee.key, null);//建筑安装工程费 (金额)
            c = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.infrastructureMatchingCost.key, null);//公共配套设施建设费 (金额)
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.devDuring.key, null);//开发期间税费 (金额)
            e = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.otherEngineeringCost.key, null);//其它工程费 (金额)
            f = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.key, null);//管理费 金额
            g = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.salesFee.key, null);//销售费用 金额
            k = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.unforeseenExpenses.key).html();//不可预见费金额
            k = Number(k);

            h = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.reconnaissanceDesign.key, null);//勘察设计和前期工程费 (金额)
            i = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.infrastructureCost.key, null);//基础设施建设费 (金额)
            if (!underConstruction.isEmpty(projectConstructionPeriod)) {//项目建设期不能为null,至于其它在调用此方法时就会校验
                return false;
            }
            temp = (a + c + b + e + f + g + k) * (Math.pow(1 + interestInvestment, projectConstructionPeriod / 2) - 1);
            temp += (h + i) * (Math.pow(1 + interestInvestment, projectConstructionPeriod) - 1);
            temp = temp.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.interestInvestment.key, temp);//投资利息 金额
            underConstruction.algsObj.landPrice();//地价 金额
        },
        //销售费用 金额 = 销售费用率 * 销售合价
        salesFee: function () {
            var a = 0, b = 0, c = 0;
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.smallGarageTotalPrice.key, null);//销售合价
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.salesFee.tax, null);//销售费用率
            c = (a * b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.salesFee.key, c);//销售费用 金额
            underConstruction.algsObj.interestInvestment();//投资利息 金额
            underConstruction.algsObj.investmentProfit();//投资利润 金额
        },
        //管理费 金额 = (不可预见费 (金额) + 工程建设成本小计)*管理费率
        managementExpense: function () {
            var a = 0, b = 0, c = 0, d = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.tax, null);//管理费率
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.unforeseenExpenses.key).html();//不可预见费 (金额)
            b = Number(b);
            c = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
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
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.managementExpense.key, d);//管理费 金额
            underConstruction.algsObj.interestInvestment();//投资利息 金额
            underConstruction.algsObj.investmentProfit();//投资利润 金额
        },
        //管理费计算率 = 管理费率 * 设计费参数比率
        managementExpenseCorrecting: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.managementExpense.tax, null);//管理费率
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.designFeeParameterRatio.key, null);//设计费参数比率
            c = (a * b);
            c = c.toFixed(4);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.managementExpense.correcting, c);//管理费计算率
            underConstruction.algsObj.investmentProfitCorrecting();//投资利润 计算率
            underConstruction.algsObj.estimateUnitPriceLandE33();//E33
        },
        //设计费参数比率
        designFeeParameterRatio: function () {
            var a = 0, b = 0, c = 0;
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.deed.tax, null);//契税率
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.transactionCost.tax, null);//交易费率
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = 1 + a + b;
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.designFeeParameterRatio.key, c);//设计费参数比率
            underConstruction.algsObj.managementExpenseCorrecting();//管理费 计算率
        },
        //不可预见费 (金额) = 不可预见费 * 工程建设成本小计
        unforeseenExpenses: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.constructionCostSubtotal.key).html();//工程建设成本小计
            a = Number(a);
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.unforeseenExpenses.tax, null);//不可预见费
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = (a * b);
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.unforeseenExpenses.key, c);//不可预见费 (金额)
            $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.unforeseenExpenses.key).html(c);
            underConstruction.algsObj.managementExpense();//管理费 金额
        },
        //工程建设成本小计
        constructionCostSubtotal: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.otherEngineeringCost.key, null);//其它工程费 (金额)
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.devDuring.key, null);//开发期间税费 (金额)
            c = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.infrastructureMatchingCost.key, null);//公共配套设施建设费 (金额)
            d = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.infrastructureCost.key, null);//基础设施建设费 (金额)
            e = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.reconnaissanceDesign.key, null);//勘察设计和前期工程费 (金额)
            f = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.constructionInstallationEngineeringFee.key, null);//建筑安装工程费 (金额)
            g = a + b + c + d + e + f;
            g = g.toFixed(3);
            $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.constructionCostSubtotal.key).html(g);
            underConstruction.algsObj.unforeseenExpenses();//不可预见费 (金额)
        },
        //其它工程费 (金额) = 其它工程费*总建筑面积小计 / 1000
        otherEngineeringCost: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.otherEngineeringCost.tax, null);
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            c = (a * b) / 10000;
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.otherEngineeringCost.key, c);
            underConstruction.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //开发期间税费 (金额) = 开发期间税费*总建筑面积小计 /1000
        devDuring: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.devDuring.tax, null);
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            c = (a * b) / 10000;
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.devDuring.key, c);
            underConstruction.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //公共配套设施建设费 (金额) = 公共配套设施建设费*总建筑面积小计 /1000
        infrastructureMatchingCost: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + underConstruction.config.id + " ." + underConstruction.config.inputConfig.infrastructureMatchingCost.tax).eq(1).val();//公共配套设施建设费
            a = Number(a);
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = (a * b) / 10000;
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.infrastructureMatchingCost.key, c);
            underConstruction.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //基础设施建设费 (金额) = 基础设施建设费*总建筑面积小计 /1000
        infrastructureCost: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + underConstruction.config.id + " ." + underConstruction.config.inputConfig.infrastructureCost.tax).eq(1).val();//基础设施建设费
            a = Number(a);
            b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
            b = Number(b);
            if (!AssessCommon.isNumber(a)) {
                return false;
            }
            if (!AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.infrastructureCost.key, c);
            underConstruction.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //勘察设计和前期工程费 (金额) = 勘察设计和前期工程费 * 建筑安装工程费 (计算金额)
        reconnaissanceDesign: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.reconnaissanceDesign.tax, null);
            b = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            c = a * b;
            c = c.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.reconnaissanceDesign.key, c);
            underConstruction.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //建筑安装工程费 (计算金额) = 建筑安装工程费计算金额(控件数据)  / 10000
        constructionInstallationEngineeringFee: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.constructionInstallationEngineeringFee.tax, null);
            // b = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
            // b = Number(b);
            // c = (a * b) / 10000;
            // c = c.toFixed(3);
            a = a / 10000;
            a = a.toFixed(3);
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.constructionInstallationEngineeringFee.key, a);
            underConstruction.algsObj.constructionCostSubtotal();//工程建设成本小计
        },
        //总建筑面积小计 = 预期销售合计 + 不可售建筑面积
        totalGrossFloorArea: function () {
            var a = 0, b = 0, c = 0;
            a = underConstruction.algsObj.getAndSet("get", underConstruction.config.inputConfig.nonSaleFloorArea.tax, null);
            b = Number($("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.estimateSaleTotal.key).html());
            c = a + b;
            c = c.toFixed(4);
            $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html(c);
            underConstruction.algsObj.constructionInstallationEngineeringFee();
            underConstruction.algsObj.infrastructureCost();
            underConstruction.algsObj.infrastructureMatchingCost();
            underConstruction.algsObj.devDuring();
            underConstruction.algsObj.otherEngineeringCost();
        },
        getAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("#" + underConstruction.config.id).find("input[name='" + name + "']").val();
                text = underConstruction.algsObj.specialTreatment(text);
                text = Number(text);
                return text;
            }
            if (flag == 'set') {
                $("#" + underConstruction.config.id).find("input[name='" + name + "']").val(data);
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
            if (underConstruction.algsObj.isNotNull(obj)) {
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

    underConstruction.inputEvent = function () {
        $.each(underConstruction.config.inputConfig, function (i, n) {
            var tax = n.tax;
            var input = $("#" + underConstruction.config.id).find("input[name='" + tax + "']");
            if (underConstruction.isEmpty(tax)) {
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    try {
                        if (underConstruction.isEmpty(value)) {
                            var funName = "underConstruction.algorithm." + tax + "Fun(" + ")";
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
    underConstruction.constructionInstallationEngineeringFeeEvent = {
        show: function () {
            layer.open({
                type: 1,
                area: '1000px;',
                offset: 't',
                content: $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.constructionInstallationEngineeringFee.class)
            });
            $(function () {
                underDevelopment.viewInit();
                var total = $("#" + underConstruction.config.id).find("." + underConstruction.config.inputConfig.totalGrossFloorArea.key).html();
                underDevelopment.setArea(total);
            });
        },
        eventSave: function () {
            var data = underDevelopment.getCalculatedResults();
            underConstruction.algsObj.getAndSet("set", underConstruction.config.inputConfig.constructionInstallationEngineeringFee.tax, data);
            underConstruction.algsObj.constructionInstallationEngineeringFee();
            // underConstruction.constructionInstallationEngineeringFeeEvent.serverSave(underDevelopment.loadData());
            underConstruction.constructionInstallationEngineeringFeeEvent.close();
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
    underConstruction.monitor = {
        // 基础设施建设费
        infrastructureCost: function () {
            var tax = underConstruction.config.inputConfig.infrastructureCost.tax;
            $("#" + underConstruction.config.id + " ." + tax).change(function () {
                var funName = "underConstruction.algorithm." + tax + "Fun(" + ")";
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
            var tax = underConstruction.config.inputConfig.infrastructureMatchingCost.tax;
            $("#" + underConstruction.config.id + " ." + tax).change(function () {
                var funName = "underConstruction.algorithm." + tax + "Fun(" + ")";
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
            underConstruction.monitor.infrastructureCost();
            underConstruction.monitor.infrastructureMatchingCost();
        }
    };
    $(function () {
        underConstruction.loadData();
        underConstruction.inputEvent();
        underConstruction.monitor.init();
    });
</script>