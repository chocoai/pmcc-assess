<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="constructionModel">


    <jsp:include page="../costModule/construction/develop.jsp"></jsp:include>
    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--土地购买价格 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="土地购买价格" class="form-control" readonly="readonly"--%>
                <%--name="landPurchasePrice">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--土地取得相关税费 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="土地取得相关税费" class="form-control" readonly="readonly"--%>
                <%--name="landGetRelevant">--%>
                <%--</div>--%>
                <%--</div>--%>
                <input type="hidden"
                       placeholder="土地取得相关税费" class="form-control" readonly="readonly"
                       name="landGetRelevant">
                <input type="hidden"
                       placeholder="土地购买价格" class="form-control" readonly="readonly"
                       name="landPurchasePrice">
            </div>
        </form>
    </div>

    <jsp:include page="../costModule/construction/totalTaxRate.jsp"></jsp:include>

    <jsp:include page="../costModule/construction/cost.jsp"></jsp:include>
    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--建筑安装工程费 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="建筑安装工程费" class="form-control" readonly="readonly"--%>
                <%--name="constructionInstallationEngineeringFee">--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--勘察设计和前期工程费 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"--%>
                <%--name="reconnaissanceDesign">--%>
                <%--</div>--%>
                <%--</div>--%>
                <input type="hidden"
                       placeholder="建筑安装工程费" class="form-control" readonly="readonly"
                       name="constructionInstallationEngineeringFee">
                <input type="hidden"
                       placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"
                       name="reconnaissanceDesign">
            </div>

            <div class="form-group">
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--基础设施费用 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="基础设施费用" class="form-control" readonly="readonly"--%>
                <%--name="infrastructureCost">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--公共配套设施费用 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="公共配套设施费用" class="form-control" readonly="readonly"--%>
                <%--name="infrastructureMatchingCost">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--开发期间税费 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="开发期间税费" class="form-control" readonly="readonly"--%>
                <%--name="devDuring">--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>

            <div class="form-group">
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--其它工程费 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="其它工程费" class="form-control" readonly="readonly"--%>
                <%--name="otherEngineeringCost">--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>
            <input type="hidden"
                   placeholder="其它工程费" class="form-control" readonly="readonly"
                   name="otherEngineeringCost">
            <input type="hidden"
                   placeholder="开发期间税费" class="form-control" readonly="readonly"
                   name="devDuring">
            <input type="hidden"
                   placeholder="公共配套设施费用" class="form-control" readonly="readonly"
                   name="infrastructureMatchingCost">
            <input type="hidden"
                   placeholder="基础设施费用" class="form-control" readonly="readonly"
                   name="infrastructureCost">
        </form>
    </div>

    <jsp:include page="../costModule/construction/designParameters.jsp"></jsp:include>
    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--不可预见费率 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="不可预见费率" class="form-control" readonly="readonly"--%>
                <%--name="unforeseenExpenses">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--管理费率 (计算金额)--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="管理费率" class="form-control" readonly="readonly"--%>
                <%--name="managementExpense">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--营业税金及附加率--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="营业税金及附加率" class="form-control" readonly="readonly"--%>
                <%--name="businessAdditional">--%>
                <%--</div>--%>
                <%--</div>--%>
                <input type="hidden"
                       placeholder="不可预见费率" class="form-control" readonly="readonly"
                       name="unforeseenExpenses">
                <input type="hidden"
                       placeholder="管理费率" class="form-control" readonly="readonly"
                       name="managementExpense">
                <input type="hidden"
                       placeholder="营业税金及附加率" class="form-control" readonly="readonly"
                       name="businessAdditional">
            </div>

            <div class="form-group">
                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--G20--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="G20" class="form-control" readonly="readonly"--%>
                <%--name="G20">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--G22--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="G22" class="form-control" readonly="readonly"--%>
                <%--name="G22">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="x-valid">--%>
                <%--<label class="col-sm-1 control-label">--%>
                <%--G23--%>
                <%--</label>--%>
                <%--<div class="col-sm-3">--%>
                <%--<input type="text"--%>
                <%--placeholder="G23" class="form-control" readonly="readonly"--%>
                <%--name="G23">--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>
            <input type="hidden"
                   placeholder="G20" class="form-control" readonly="readonly"
                   name="G20">
            <input type="hidden"
                   placeholder="G22" class="form-control" readonly="readonly"
                   name="G22">
            <input type="hidden"
                   placeholder="G23" class="form-control" readonly="readonly"
                   name="G23">
            <input type="hidden"
                   placeholder="在建工程单价" class="form-control" readonly="readonly"
                   name="constructionAssessmentPrice">
        </form>
    </div>

    <jsp:include page="../costModule/construction/resultView.jsp"></jsp:include>
    <div class="constructionInstallationEngineeringFeeClass" style="display: none;">
        <jsp:include page="/views/method/module/architecturalEngineering/constructionEngineering.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="construction.constructionInstallationEngineeringFeeEvent.close()">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="construction.constructionInstallationEngineeringFeeEvent.eventSave()">
            </div>
        </div>
    </div>
</div>

<script>

    var construction = new Object();

    construction.config = {
        id: "constructionModel",
        totalTaxRate: {
            key: "totalTaxRate",
            name: "合计税率",
            business: "businessTax",//营业税
            urbanMaintenance: "urbanMaintenanceTax",//城建税
            education: "educationTax",//教育费附加
            localEducation: "localEducationTax",//地方教育费附加
            stampDuty: "stampDuty"//印花税
        },
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        inputConfig: {
            developLandArea: {
                key: "developLandArea",
                tax: "developLandAreaTax",
                name: "开发土地面积"
            },
            developBuildArea: {
                key: "developBuildArea",
                tax: "developBuildAreaTax",
                name: "开发建筑面积"
            },
            developYearNumber: {
                key: "developYearNumber",
                tax: "developYearNumberTax",
                name: "开发年"
            },
            landPurchasePrice: {
                key: "landPurchasePrice",
                name: "土地购买价格",
                tax: "landPurchasePriceTax"
            },
            landGetRelevant: {
                key: "landGetRelevant",
                tax: "landGetRelevantTax",
                name: "土地取得相关税费"
            },
            landGetCostTotal: {
                key: "landGetCostTotal",
                name: "土地取得成本合计",
                tax: ""
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
            constructionSubtotal: {
                key: "constructionSubtotal",
                tax: "",
                name: "建设成本小计"
            },
            unforeseenExpenses: {
                key: "unforeseenExpenses",
                tax: "unforeseenExpensesTax",
                correcting: "",
                name: "不可预见费"
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
            constructionAssessmentValue: {
                key: "constructionAssessmentValue",
                tax: "",
                name: "在建工程评估价值"
            },
            constructionAssessmentPrice: {
                key: "constructionAssessmentPrice",
                tax: "",
                correcting: "constructionAssessmentPriceCorrecting",
                name: "在建工程单位价"
            }
        }
    };

    construction.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    construction.loadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/marketCost/listCostAndMatchingCost",
            type: "get",
            data: {province: "${schemeAreaGroup.province}",city: "${schemeAreaGroup.city}",district: "${schemeAreaGroup.district}"},
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
                        $("#" + construction.config.id).find("select." + construction.config.inputConfig.infrastructureCost.tax).html(optionA);
                        $("#" + construction.config.id).find("select." + construction.config.inputConfig.infrastructureMatchingCost.tax).html(optionB);
                    }

                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    construction.algorithm = {
        //开发建筑面积
        developBuildAreaTaxFun: function () {
            construction.algsObj.constructionInstallationEngineeringFee();
            construction.algsObj.infrastructureCost();
            construction.algsObj.infrastructureMatchingCost();
            construction.algsObj.devDuring();
            construction.algsObj.otherEngineeringCost();
            construction.algsObj.constructionAssessmentPriceCorrecting();//在建工程单位价
        },
        //开发土地面积
        developLandAreaTaxFun: function () {
            construction.algsObj.landPurchasePrice();
        },
        //土地购买价格
        landPurchasePriceTaxFun: function () {
            construction.algsObj.landPurchasePrice();
        },
        //契税率
        deedTaxFun: function () {
            construction.algsObj.landGetRelevant();
        },
        //交易费率
        transactionCostTaxFun: function () {
            construction.algsObj.landGetRelevant();
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignTaxFun: function () {
            construction.algsObj.reconnaissanceDesign();
        },
        //开发期间税费
        devDuringTaxFun: function () {
            construction.algsObj.devDuring();
        },
        //其它工程费率
        otherEngineeringCostTaxFun: function () {
            construction.algsObj.otherEngineeringCost();
        },
        //不可预见费率
        unforeseenExpensesTaxFun: function () {
            construction.algsObj.unforeseenExpenses();
        },
        //管理费率
        managementExpenseTaxFun: function () {
            var a = construction.algsObj.getAndSet("get", construction.config.inputConfig.managementExpense.tax, null);
            if (a >= 0.03 && a <= 0.08) {
                construction.algsObj.managementExpense();
            }else {
                toastr.success('管理费参考值3%-8%');
            }
        },
        businessTaxFun: function () {
            construction.algsObj.businessAdditional();//营业税金及附加
        },
        urbanMaintenanceTaxFun: function () {
            construction.algsObj.businessAdditional();//营业税金及附加
        },
        educationTaxFun: function () {
            construction.algsObj.businessAdditional();//营业税金及附加
        },
        localEducationTaxFun: function () {
            construction.algsObj.businessAdditional();//营业税金及附加
        },
        stampDutyFun: function () {
            construction.algsObj.businessAdditional();//营业税金及附加
        },
        //投资利息率
        interestInvestmentTaxFun: function () {
            construction.algsObj.interestInvestment();//投资利息
            construction.algsObj.G20();
        },
        //开发年数
        developYearNumberTaxFun: function () {
            construction.algsObj.interestInvestment();//投资利息
            construction.algsObj.G20();
        },
        //投资利润率
        investmentProfitTaxFun: function () {
            construction.algsObj.investmentProfit();//投资利润
            construction.algsObj.G22();
        },
        //销售费用率
        salesFeeTaxFun: function () {
            construction.algsObj.G20();
            construction.algsObj.G22();
            construction.algsObj.G23();
        }
    };

    construction.algsObj = {
        //在建工程单位价
        constructionAssessmentPriceCorrecting: function () {
            var a = 0, b = 0, temp = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.constructionAssessmentPrice.key, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developBuildArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            temp = (a * 10000) / b;
            $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentPrice.correcting).html(temp.toFixed(4));
        },
        //评估单价=IF(E23=" "," ",E23/(1-G23))
        constructionAssessmentPrice: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, temp = 0;
            a = $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentValue.key).html();
            b = construction.algsObj.getAndSet("get", "G23", null);
            a = Number(a);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            temp = a / (1 - b);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.constructionAssessmentPrice.key, temp.toFixed(4));
            construction.algsObj.constructionAssessmentPriceCorrecting();//在建工程单位价
        },
        //在建工程评估价值  = (E8+E16+E17+E18+E20+E22)
        constructionAssessmentValue: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, temp = 0;
            a = $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html();//E8 土地取得成本合计
            b = $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html();//E16
            c = construction.algsObj.getAndSet("get", construction.config.inputConfig.unforeseenExpenses.key, null);//E17 不可预见费
            d = construction.algsObj.getAndSet("get", construction.config.inputConfig.managementExpense.key, null);//E18 管理费
            e = $("#" + construction.config.id).find("." + construction.config.inputConfig.interestInvestment.key).html();//E20 投资利息
            f = $("#" + construction.config.id).find("." + construction.config.inputConfig.investmentProfit.key).html();//E22 投资利润
            a = Number(a);
            b = Number(b);
            e = Number(e);
            f = Number(f);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(d) || !AssessCommon.isNumber(e) || !AssessCommon.isNumber(f)) {
                return false;
            }
            temp = a + b + c + d + e + f;
            $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentValue.key).html(temp.toFixed(4));
            construction.algsObj.constructionAssessmentPrice();//评估单价
        },
        //=ROUND(D19+G20+D21+G22,4)
        G23: function () {
            var d19 = construction.algsObj.getAndSet("get", construction.config.inputConfig.salesFee.tax, null);//D19
            var g20 = construction.algsObj.getAndSet("get", "G20", null);
            var d21 = construction.algsObj.getAndSet("get", construction.config.inputConfig.businessAdditional.key, null);
            var g22 = construction.algsObj.getAndSet("get", "G22", null);
            if (!AssessCommon.isNumber(d19) || !AssessCommon.isNumber(g20) || !AssessCommon.isNumber(d21) || !AssessCommon.isNumber(g22)) {
                return false;
            }
            var temp = d19 + g20 + d21 + g22;
            construction.algsObj.getAndSet("set", "G23", temp.toFixed(4));
            construction.algsObj.constructionAssessmentPrice();//评估单价
        },
        //=ROUND(D22*D19,5)
        G22: function () {
            var d19 = construction.algsObj.getAndSet("get", construction.config.inputConfig.salesFee.tax, null);//D19
            var d22 = construction.algsObj.getAndSet("get", construction.config.inputConfig.investmentProfit.tax, null);//D22
            if (!AssessCommon.isNumber(d19) || !AssessCommon.isNumber(d22)) {
                return false;
            }
            var temp = d19 * d22;
            construction.algsObj.getAndSet("set", "G22", temp.toFixed(4));
            construction.algsObj.G23();
        },
        //=ROUND(D19*((1+D20)^(H3/2)-1),5)
        G20: function () {
            var d19 = construction.algsObj.getAndSet("get", construction.config.inputConfig.salesFee.tax, null);
            var interestInvestment = construction.algsObj.getAndSet("get", construction.config.inputConfig.interestInvestment.tax, null);//D20 投资利息率
            var developYearNumber = construction.algsObj.getAndSet("get", construction.config.inputConfig.developYearNumber.tax, null);//H3 开发年数
            var temp = 0;
            temp = Math.pow(1 + interestInvestment, developYearNumber / 2) - 1;
            temp = temp * d19;
            if (!AssessCommon.isNumber(d19) || !AssessCommon.isNumber(interestInvestment) || !AssessCommon.isNumber(developYearNumber)) {
                return false;
            }
            construction.algsObj.getAndSet("set", "G20", temp.toFixed(4));
            construction.algsObj.G23();
        },
        //投资利润 (E8+E16+E17+E18)*D22)
        investmentProfit: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, temp = 0;
            a = $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html();//E8 土地取得成本合计
            b = $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html();//E16
            c = construction.algsObj.getAndSet("get", construction.config.inputConfig.unforeseenExpenses.key, null);//E17 不可预见费
            d = construction.algsObj.getAndSet("get", construction.config.inputConfig.managementExpense.key, null);//E18 管理费
            e = construction.algsObj.getAndSet("get", construction.config.inputConfig.investmentProfit.tax, null);//D22
            a = Number(a);
            b = Number(b);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(d) || !AssessCommon.isNumber(e)) {
                return false;
            }
            temp = (a + b + c + d) * e;
            $("#" + construction.config.id).find("." + construction.config.inputConfig.investmentProfit.key).html(temp.toFixed(4));
            construction.algsObj.constructionAssessmentValue();//在建工程评估价值
        },
        //投资利息(E8+E10+E12)*((1+D20)^H3-1)+(E11+E13+E14+E15+E17+E18)*((1+D20)^(H3/2)-1))
        interestInvestment: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, temp = 0;
            a = $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html();//E8 土地取得成本合计
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.reconnaissanceDesign.key, null);//E10 勘察设计和前期工程费
            c = construction.algsObj.getAndSet("get", construction.config.inputConfig.infrastructureCost.key, null);//E12 基础设施建设费
            d = construction.algsObj.getAndSet("get", construction.config.inputConfig.constructionInstallationEngineeringFee.key, null);//E11 建筑安装工程费
            e = construction.algsObj.getAndSet("get", construction.config.inputConfig.infrastructureMatchingCost.key, null);//E13 公共配套设施建设费
            f = construction.algsObj.getAndSet("get", construction.config.inputConfig.devDuring.key, null);//E14 开发期间税费 计算金额
            g = construction.algsObj.getAndSet("get", construction.config.inputConfig.otherEngineeringCost.key, null);//E15 其它工程费率 计算金额
            h = construction.algsObj.getAndSet("get", construction.config.inputConfig.unforeseenExpenses.key, null);//E17 不可预见费
            i = construction.algsObj.getAndSet("get", construction.config.inputConfig.managementExpense.key, null);//E18 管理费
            var developYearNumber = construction.algsObj.getAndSet("get", construction.config.inputConfig.developYearNumber.tax, null);//H3 开发年数
            var interestInvestment = construction.algsObj.getAndSet("get", construction.config.inputConfig.interestInvestment.tax, null);//D20 投资利息率
            a = Number(a);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(d) || !AssessCommon.isNumber(e) || !AssessCommon.isNumber(f)) {
                return false;
            }
            if (!AssessCommon.isNumber(g) || !AssessCommon.isNumber(h) || !AssessCommon.isNumber(i)) {
                return false;
            }
            if (!AssessCommon.isNumber(developYearNumber) || !AssessCommon.isNumber(interestInvestment)) {
                return false;
            }
            temp = (a + b + c) * (Math.pow(1 + interestInvestment, developYearNumber) - 1);
            temp += (d + e + f + g + h + i) * (Math.pow(1 + interestInvestment, developYearNumber / 2) - 1);
            $("#" + construction.config.id).find("." + construction.config.inputConfig.interestInvestment.key).html(temp.toFixed(4));
            construction.algsObj.constructionAssessmentValue();//在建工程评估价值
        },
        //营业税金及附加 = H30+D30+D30*(E30+F30+G30)
        businessAdditional: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, temp = 0;
            a = construction.algsObj.getAndSet("get", construction.config.totalTaxRate.business, null);//营业税 D30
            b = construction.algsObj.getAndSet("get", construction.config.totalTaxRate.education, null);//教育费附加 F30
            c = construction.algsObj.getAndSet("get", construction.config.totalTaxRate.localEducation, null);//地方教育费附加 G30
            d = construction.algsObj.getAndSet("get", construction.config.totalTaxRate.stampDuty, null);//印花税 H30
            e = construction.algsObj.getAndSet("get", construction.config.totalTaxRate.urbanMaintenance, null);//城建税 E30
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(d) || !AssessCommon.isNumber(e)) {
                return false;
            }
            temp = d + a + a * (e + b + c);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.businessAdditional.key, temp.toFixed(4));
            construction.algsObj.G23();
        },
        //管理费
        managementExpense: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, temp = 0;
            a = $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html();
            a = Number(a);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.unforeseenExpenses.key, null);
            c = $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html();
            c = Number(c);
            d = construction.algsObj.getAndSet("get", construction.config.inputConfig.managementExpense.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(d)) {
                return false;
            }
            temp = (a + b + c) * d;
            construction.algsObj.getAndSet("set", construction.config.inputConfig.managementExpense.key, temp.toFixed(4));
            construction.algsObj.interestInvestment();//投资利息
            construction.algsObj.investmentProfit();//投资利润
        },
        //不可预见费
        unforeseenExpenses: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html();
            a = Number(a);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.unforeseenExpenses.tax, null);
            c = a * b;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.unforeseenExpenses.key, c);
            construction.algsObj.managementExpense();
        },
        //建设成本小计
        constructionSubtotal: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, temp = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.reconnaissanceDesign.key, null); //勘察设计和前期工程费
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            c = construction.algsObj.getAndSet("get", construction.config.inputConfig.infrastructureCost.key, null);//基础设施建设费
            d = construction.algsObj.getAndSet("get", construction.config.inputConfig.infrastructureMatchingCost.key, null);//公共配套设施建设费
            e = construction.algsObj.getAndSet("get", construction.config.inputConfig.devDuring.key, null);//开发期间税费 计算金额
            f = construction.algsObj.getAndSet("get", construction.config.inputConfig.otherEngineeringCost.key, null);//其它工程费率 计算金额
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            if (!AssessCommon.isNumber(d) || !AssessCommon.isNumber(e) || !AssessCommon.isNumber(f)) {
                return false;
            }
            temp = a + b + c + d + e + f;
            temp = temp.toFixed(4);
            $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html(temp);
            construction.algsObj.unforeseenExpenses();//不可预见费
        },
        //其它工程费率 计算金额
        otherEngineeringCost: function () {
            var a = 0, b = 0, c = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.otherEngineeringCost.tax, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developBuildArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.otherEngineeringCost.key, c);
            construction.algsObj.constructionSubtotal();//建设成本小计
        },
        //开发期间税费 计算金额
        devDuring: function () {
            var a = 0, b = 0, c = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.devDuring.tax, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developBuildArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.devDuring.key, c);
            construction.algsObj.constructionSubtotal();//建设成本小计
        },
        //基础设施建设费
        infrastructureCost: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + construction.config.id).find("select." + construction.config.inputConfig.infrastructureCost.tax).val();//基础设施建设费
            a = Number(a);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developBuildArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.infrastructureCost.key, c);
            construction.algsObj.constructionSubtotal();//建设成本小计
        },
        //公共配套设施建设费
        infrastructureMatchingCost: function () {
            var a = 0, b = 0, c = 0;
            a = $("#" + construction.config.id).find("select." + construction.config.inputConfig.infrastructureMatchingCost.tax).val();//公共配套设施建设费
            a = Number(a);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developBuildArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.infrastructureMatchingCost.key, c);
            construction.algsObj.constructionSubtotal();//建设成本小计
        },
        //勘察设计和前期工程费
        reconnaissanceDesign: function () {
            var a = 0, b = 0, c = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.reconnaissanceDesign.tax, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            c = a * b;
            c = c.toFixed(4);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            construction.algsObj.getAndSet("set", construction.config.inputConfig.reconnaissanceDesign.key, c);
            construction.algsObj.constructionSubtotal();//建设成本小计
        },
        //土地取得成本合计
        landGetCostTotal: function () {
            var a = 0, b = 0, c = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.landGetRelevant.key, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.landPurchasePrice.key, null);
            c = a + b;
            c = c.toFixed(4);
            $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html(c);
            construction.algsObj.managementExpense();
        },
        //土地取得相关税费 (计算金额)
        landGetRelevant: function () {
            var a = 0, b = 0, c = 0, d = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.deed.tax, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.transactionCost.tax, null);
            c = construction.algsObj.getAndSet("get", construction.config.inputConfig.landPurchasePrice.key, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b) || !AssessCommon.isNumber(c)) {
                return false;
            }
            d = (a + b) * c;
            d = d.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.landGetRelevant.key, d);
            construction.algsObj.landGetCostTotal();
        },
        //土地购买价格 计算值
        landPurchasePrice: function () {
            var a = 0, b = 0, c = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.landPurchasePrice.tax, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developLandArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.landPurchasePrice.key, c);
            construction.algsObj.landGetRelevant();
        },
        //建筑安装工程费
        constructionInstallationEngineeringFee: function () {
            var a = 0, b = 0, c = 0;
            a = construction.algsObj.getAndSet("get", construction.config.inputConfig.constructionInstallationEngineeringFee.tax, null);
            b = construction.algsObj.getAndSet("get", construction.config.inputConfig.developBuildArea.tax, null);
            if (!AssessCommon.isNumber(a) || !AssessCommon.isNumber(b)) {
                return false;
            }
            c = a * b / 10000;
            c = c.toFixed(4);
            construction.algsObj.getAndSet("set", construction.config.inputConfig.constructionInstallationEngineeringFee.key, c);
            construction.algsObj.reconnaissanceDesign();
        },
        getAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("#" + construction.config.id).find("input[name='" + name + "']").val();
                text = construction.algsObj.specialTreatment(text);
                text = Number(text);
                return text;
            }
            if (flag == 'set') {
                $("#" + construction.config.id).find("input[name='" + name + "']").val(data);
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
            if (construction.algsObj.isNotNull(obj)) {
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

    construction.inputEvent = function () {
        $.each(construction.config.inputConfig, function (i, n) {
            var tax = n.tax;
            var input = $("#" + construction.config.id).find("input[name='" + tax + "']");
            if (construction.isEmpty(tax)) {
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    try {
                        if (construction.isEmpty(value)) {
                            var funName = "construction.algorithm." + tax + "Fun(" + ")";
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

    construction.otherEventBlur = function () {
        var arr = new Array();
        arr.push(construction.config.totalTaxRate.business);
        arr.push(construction.config.totalTaxRate.education);
        arr.push(construction.config.totalTaxRate.localEducation);
        arr.push(construction.config.totalTaxRate.stampDuty);
        arr.push(construction.config.totalTaxRate.urbanMaintenance);
        $.each(arr, function (i, n) {
            var input = $("#" + construction.config.id).find("input[name='" + n + "']");
            if (construction.isEmpty(n)) {
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    try {
                        if (construction.isEmpty(value)) {
                            var funName = "construction.algorithm." + n + "Fun(" + ")";
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
    construction.constructionInstallationEngineeringFeeEvent = {
        show: function () {
            layer.open({
                type: 1,
                area: '1000px;',
                offset: 't',
                content: $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionInstallationEngineeringFee.class)
            });
            $(function () {
                constructEngineeringObjectA.viewInit();
            });
        },
        eventSave: function () {
            construction.algsObj.getAndSet("set", construction.config.inputConfig.constructionInstallationEngineeringFee.tax, constructEngineeringObjectA.getCalculatedResults());
            construction.constructionInstallationEngineeringFeeEvent.serverSave(constructEngineeringObjectA.loadData());
            construction.algsObj.constructionInstallationEngineeringFee();
            construction.constructionInstallationEngineeringFeeEvent.close();
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
                    type: "MdCostConstruction",
                    id: "${mdCostAndDevelopmentOtherConstruction.id}"
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
    construction.monitor = {
        // 基础设施建设费
        infrastructureCost: function () {
            var tax = construction.config.inputConfig.infrastructureCost.tax;
            $("#" + construction.config.id + " ." + tax).change(function () {
                construction.algsObj.infrastructureCost();
            });
        },
        //公共配套设施建设费
        infrastructureMatchingCost: function () {
            var tax = construction.config.inputConfig.infrastructureMatchingCost.tax;
            $("#" + construction.config.id + " ." + tax).change(function () {
                construction.algsObj.infrastructureMatchingCost();
            });
        },
        init: function () {
            construction.monitor.infrastructureCost();
            construction.monitor.infrastructureMatchingCost();
        }
    };


    /**
     * @author:  zch
     * 描述:收集数据
     * @date:2018-10-12
     **/
    construction.formParams = function () {
        var item = {};
        var forms = $("#" + construction.config.id).find("form");
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
        item.landGetCostTotal = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html());
        item.constructionSubtotal = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html());
        item.interestInvestment = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.interestInvestment.key).html());
        item.investmentProfit = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.investmentProfit.key).html());
        item.constructionAssessmentValue = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentValue.key).html());
        item.constructionAssessmentPriceCorrecting = Number($("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentPrice.correcting).html());
        return item;
    };


    /**
     * @author:  zch
     * 描述:赋值
     * @date:2018-10-12
     **/
    construction.initForm = function (item) {
        var forms = $("#" + construction.config.id).find("form");
        item = JSON.parse(item);
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(item);
        });
        $("#" + construction.config.id).find("." + construction.config.inputConfig.landGetCostTotal.key).html(item.landGetCostTotal);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionSubtotal.key).html(item.constructionSubtotal);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.interestInvestment.key).html(item.interestInvestment);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.investmentProfit.key).html(item.investmentProfit);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentValue.key).html(item.constructionAssessmentValue);
        $("#" + construction.config.id).find("." + construction.config.inputConfig.constructionAssessmentPrice.correcting).html(item.constructionAssessmentPriceCorrecting);
    };

    $(function () {
        construction.loadData();
        construction.inputEvent();
        construction.otherEventBlur();
        construction.monitor.init();
    });

</script>