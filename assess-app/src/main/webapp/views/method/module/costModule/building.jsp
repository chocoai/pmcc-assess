<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="buildModel">

    <jsp:include page="../costModule/build/cost.jsp"></jsp:include>

    <jsp:include page="../costModule/build/totalTaxRate.jsp"></jsp:include>

    <jsp:include page="../costModule/build/designParameters.jsp"></jsp:include>

    <jsp:include page="../costModule/build/resultView.jsp"></jsp:include>

</div>

<script>
    var build = new Object();

    build.config = {
        id: "buildModel",
        /*说明:key代表计算出的金额,correcting代表费率校正值,tax代表费率,name代表名称*/
        totalTaxRate:{
            key:"totalTaxRate",
            name:"合计税率",
            business:"businessTax",//营业税
            urbanMaintenance:"urbanMaintenanceTax",//城建税
            education:"educationTax",//教育费附加
            localEducation:"localEducationTax",//地方教育费附加
            stampDuty:"stampDuty"//印花税
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
            replacementValue: {key: "replacementValue", name: "重置价格",tax:""},
            newRate: {key: "newRate", name: "成新率",tax:""},
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

    build.algorithm = {};

    build.algsObj = {
        //建筑安装工程费
        constructionInstallationEngineeringFee: function () {

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
        $.each(arr,function (i,n) {
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

    $(function () {
        build.loadData();
        build.inputEvent();
        build.otherEventBlur();
        build.monitor.init();
    });
</script>