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
            </div>
        </form>
    </div>
    <jsp:include page="../developmentModule/land/designParameters.jsp"></jsp:include>
    <jsp:include page="../developmentModule/land/resultView.jsp"></jsp:include>
</div>

<script>
    var landEngineering = new Object();

    landEngineering.config = {
        id: "landEngineeringModel",
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        inputConfig: {
            estimateSaleTotal: {
                key: "estimateSaleTotal",
                name: "预期销售合计",
                tax: ""
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
            },//设计费参数比率
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
                tax: "salesTaxAndAdditionalTax",
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
        infrastructureCostTaxFun:function () {

        }
    };

    landEngineering.algsObj = {
        //基础设施建设费
        infrastructureCost:function () {

        },
        //勘察设计和前期工程费 (金额) = 勘察设计和前期工程费 * 建筑安装工程费 (计算金额)
        reconnaissanceDesign: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.reconnaissanceDesign.tax, null);
            b = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.constructionInstallationEngineeringFee.key, null);
            c = a * b;
            console.log("a:"+a+" b:"+b+" c:"+c);
            landEngineering.algsObj.getAndSet("set", landEngineering.config.inputConfig.reconnaissanceDesign.key, c);
        },
        //总建筑面积小计 = 预期销售合计 + 不可售建筑面积
        totalGrossFloorArea: function () {
            var a = 0, b = 0, c = 0;
            a = landEngineering.algsObj.getAndSet("get", landEngineering.config.inputConfig.nonSaleFloorArea.tax, null);
            b = Number($("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.estimateSaleTotal.key).html());
            c = a + b;
            $("#" + landEngineering.config.id).find("." + landEngineering.config.inputConfig.totalGrossFloorArea.key).html(c);
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
                var str = nnn.substring(nnn.length-1,nnn.length);
                if (str == '%'){//检测是否为百分比
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
                        var funName = "landEngineering.algorithm." + tax + "Fun(" + ")";
                        console.log(funName);
                        eval(funName);
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
        infrastructureCost:function () {
            var tax = landEngineering.config.inputConfig.infrastructureCost.tax;
            $("#" + landEngineering.config.id + " ." + tax).change(function () {
                var funName = "landEngineering.algorithm." + tax + "Fun(" + ")";
                try {
                    eval(funName);
                } catch (e) {
                    console.log("没有这个函数");
                }
            });
        },
        //公共配套设施建设费
        infrastructureMatchingCost:function () {

        },
        init:function () {
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

