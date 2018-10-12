<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="constructionModel">


    <jsp:include page="../costModule/construction/develop.jsp"></jsp:include>

    <jsp:include page="../costModule/construction/cost.jsp"></jsp:include>

    <jsp:include page="../costModule/construction/designParameters.jsp"></jsp:include>

    <jsp:include page="../costModule/construction/resultView.jsp"></jsp:include>

</div>

<script>

    var construction = new Object();

    construction.config = {
        id: "constructionModel",
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        inputConfig: {
            developLandArea:{
                key:"developLandArea",
                tax: "developLandAreaTax",
                name:"开发土地面积"
            },
            developBuildArea:{
                key:"developBuildArea",
                tax: "developBuildAreaTax",
                name:"开发建筑面积"
            },
            developYearNumber:{
                key:"developYearNumber",
                tax: "developYearNumberTax",
                name:"开发年"
            },
            landPurchasePrice:{
                key:"landPurchasePrice",
                name:"土地购买价格",
                tax: "landPurchasePriceTax"
            },
            landGetRelevant:{
                key:"landGetRelevant",
                tax:"landGetRelevantTax",
                name:"土地取得相关税费"
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
            constructionAssessmentValue:{
                key:"constructionAssessmentValue",
                tax:"",
                name:"在建工程评估价值"
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

    };

    construction.algsObj = {
        //建筑安装工程费
        constructionInstallationEngineeringFee: function () {

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
                var funName = "construction.algorithm." + tax + "Fun(" + ")";
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
            var tax = construction.config.inputConfig.infrastructureMatchingCost.tax;
            $("#" + construction.config.id + " ." + tax).change(function () {
                var funName = "construction.algorithm." + tax + "Fun(" + ")";
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
        return item;
    };


    /**
     * @author:  zch
     * 描述:赋值
     * @date:2018-10-12
     **/
    construction.initForm = function (item) {
        var forms = $("#" + construction.config.id).find("form");
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(item);
        });
    };

    $(function () {
        construction.loadData();
        construction.inputEvent();
        construction.monitor.init();
    });
    
</script>