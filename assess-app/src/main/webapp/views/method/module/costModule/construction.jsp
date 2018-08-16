<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
  在建工程
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal frmConstruction">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            开发土地面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发土地面积" class="form-control" name="developmentLandArea">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发建筑面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发建筑面积" class="form-control" name="developmentBuildArea">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发期" class="form-control" name="developmentDate">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            土地购买单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="土地购买单价" class="form-control" name="unitPriceLandPurchase">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地购买合价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地购买合价" class="form-control" name="landPurchasePrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地取得税率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="landAcquisitionTaxRate"
                        class="landAcquisitionTaxRateClass form-control search-select select2"></select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            土地取得税费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地取得税费" class="form-control" name="landAcquisitionTax">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地取得单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地取得单价" class="form-control" name="landAcquisitionPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            土地取得小计
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="土地取得小计" class="form-control" name="landAcquisitionTotal">
            </div>
        </div>
    </div>

    <div class="form-group">

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费"
                       class="form-control" name="reconnaissanceDesign" readonly="readonly">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费率" class="form-control"
                       name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       onclick="construction.constructionInstallationEngineeringFee.event();"
                       placeholder="建筑安装工程费" class="form-control" name="constructionInstallationEngineeringFee">
            </div>
        </div>
    </div>

    <div class="constructionInstallationEngineeringFeeA" style="display: none;">
        <jsp:include page="constructionInstallationEngineeringFeeA.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="construction.constructionInstallationEngineeringFee.close();">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="construction.constructionInstallationEngineeringFee.getDataAndWriteHtml();">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureCostSelect"
                        class="form-control search-select select2 infrastructureCostSelect">
                </select>
            </div>
        </div>

        <label class="col-sm-1 control-label">
            基础设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="基础设施建设费" class="form-control" name="infrastructureCost">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            公共配套设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureMatchingCostSelect"
                        class="form-control search-select select2 infrastructureMatchingCostSelect">
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            公共配套设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="公共配套设施建设费" class="form-control" name="infrastructureMatchingCost">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期间单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发期间单价" class="form-control" name="devDuringPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期间税费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="开发期间税费" class="form-control" name="devDuringPriceTax">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            其它工程费单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="其它工程费单价" class="form-control" name="otherEngineeringCostPrice">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            其它工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="其它工程费" class="form-control" name="otherEngineeringCost">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建设成本
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="建设成本" class="form-control" name="constructionCost">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            管理费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="管理费" class="form-control" name="managementExpense">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            管理费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="管理费率" class="form-control" name="managementExpenseRote">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            不可预见费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="不可预见费率" class="form-control" name="unforeseenExpensesRote">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            不可预见费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="不可预见费" class="form-control" name="unforeseenExpenses">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            销售费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="销售费率" class="form-control" name="salesFeeRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            重置价格
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="重置价格" class="form-control" name="replacementValue">
            </div>
        </div>

    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            销售费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="销售费" class="form-control" name="salesFee">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            增值及附加税率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="addedValueAdditionalTaxRate"
                        class="form-control search-select select2 addedValueAdditionalTaxRateSelect">
                </select>
            </div>
        </div>

        <label class="col-sm-1 control-label">
            增值及附加税金
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="增值及附加税金" class="form-control" name="valueAddedAdditionalTaxes">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资计息利率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="投资计息利率" class="form-control" name="interestRateOnInvestment">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            计息周期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="计息周期" class="form-control" name="interestPeriod">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            投资计息税率修正
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资计息税率修正" class="form-control" name="interestRateOnInvestmentCorrect">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资利息
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利息" class="form-control" name="interestInInvestment">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发利润率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发利润率" class="form-control" name="developmentProfitMarginRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发利润
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="开发利润" class="form-control" name="developmentProfitMargin">
            </div>
        </div>
    </div>

    <div class="form-group">

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                开发利润修正
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="开发利润修正" class="form-control" name="developmentProfitMarginRoteCorrect">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                在建工程评估价值
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="在建工程评估价值" class="form-control" name="evaluationValueConstructionProject">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                在建工程评估价值修正
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="在建工程评估价值修正" class="form-control" name="evaluationValueConstructionProjectCorrect">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                在建工程评估值
            </label>
            <div class="col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="在建工程评估值" class="form-control" name="constructionProcesAssessValue">
            </div>
        </div>
    </div>
</form>
<script>
    /**
     * @author:  zch
     * 描述: 定义一个对象 (页面上不能与此对象的名称相同)
     * @date:2018-08-08
     **/
    var construction = new Object();
    /**--------------------------------基础算法------------------**/
    /**
     * @author:  zch
     * 描述:是否为null
     * @date:2018-08-08
     **/
    construction.isNotNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    /**
     * @author:  zch
     * 描述:是否是数字
     * @date:2018-08-08
     **/
    construction.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    /**
     * @author:  zch
     * 描述:是否是正数
     * @date:2018-08-08
     **/
    construction.isPlus = function (obj) {
        return AlgorithmsPrototype.prototype.isPlus(obj);
    }
    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-08
     **/
    construction.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:2018-08-08
     **/
    construction.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:2018-08-08
     **/
    construction.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:除法
     * @date:2018-08-08
     **/
    construction.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:百分数转小数
     * @date:
     **/
    construction.toPoint = function (percent) {
        var str = percent.replace("%", "");
        str = str / 100;
        return str;
    }
    /**
     * @author:  zch
     * 描述:小数转百分数
     * *这里需要先用Number进行数据类型转换，然后去指定截取转换后的小数点后几位(按照四舍五入)，这里是截取一位，0.1266转换后会变成12.7%
     * @date:
     **/
    construction.toPercent = function (point) {
        var str = Number(point * 100).toFixed(1);
        str += "%";
        return str;
    }
    /**--------------------------------配置------------------**/
    /**
     * @author:  zch
     * 描述:参数配置
     * @date:2018-08-10
     **/
    construction.config = function () {
        var config = {};
        config.frm = "frmConstruction";//表单id
        config.engineeringFee = "constructionInstallationEngineeringFeeA"; //子表单id
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        var inputNameConfig = {
            constructionInstallationEngineeringFee: {
                key: "constructionInstallationEngineeringFee",
                describe: "建筑安装工程费", value: "", select: ""
            },
            developmentLandArea: {key: "developmentLandArea", describe: "开发土地面积", value: "", select: ""},
            developmentBuildArea: {key: "developmentBuildArea", describe: "开发建筑面积", value: "", select: ""},
            developmentDate: {key: "developmentDate", describe: "开发日期", value: "", select: ""},
            unitPriceLandPurchase: {key: "unitPriceLandPurchase", describe: "土地购买单价", value: "", select: ""},
            landPurchasePrice: {key: "landPurchasePrice", describe: "土地购买合价", value: "", select: ""},
            landAcquisitionTaxRate: {
                key: "landAcquisitionTaxRate",
                describe: "土地取得税率",
                value: "",
                select: "landAcquisitionTaxRateClass"
            },
            landAcquisitionTax: {key: "landAcquisitionTax", describe: "土地取得税费", value: "", select: ""},
            landAcquisitionPrice: {key: "landAcquisitionPrice", describe: "土地取得单价", value: "", select: ""},
            landAcquisitionTotal: {key: "landAcquisitionTotal", describe: "土地取得小计", value: "", select: ""},
            reconnaissanceDesign: {key: "reconnaissanceDesign", describe: "勘察设计和前期工程费"},
            reconnaissanceDesignRote: {key: "reconnaissanceDesignRote", describe: "勘察设计和前期工程费率"},
            infrastructureCost: {
                key: "infrastructureCost",//infrastructureCost 算是一个input
                describe: "基础设施费用",
                select: "infrastructureCostSelect"//infrastructureCostSelect 算是一个select (input)
            },
            infrastructureMatchingCost: {
                key: "infrastructureMatchingCost",//infrastructureMatchingCost 算是一个input
                describe: "公共配套设施费用",
                select: "infrastructureMatchingCostSelect"//注意:infrastructureMatchingCostSelect算是一个select (input)
            },
            devDuringPrice: {
                key: "devDuringPrice",
                describe: "开发期间单价", value: "", select: ""
            },
            devDuringPriceTax: {
                key: "devDuringPriceTax",
                describe: "开发期间单价税收", value: "", select: ""
            },
            otherEngineeringCost: {key: "otherEngineeringCost", describe: "其它工程费"},
            otherEngineeringCostPrice: {key: "otherEngineeringCostPrice", describe: "其它工程费单价"},
            constructionCost: {key: "constructionCost", describe: "建设成本"},
            managementExpense: {key: "managementExpense", describe: "管理费"},
            managementExpenseRote: {key: "managementExpenseRote", describe: "管理费率"},
            unforeseenExpenses: {key: "unforeseenExpenses", describe: "不可预见费"},
            unforeseenExpensesRote: {key: "unforeseenExpensesRote", describe: "不可预见费率"},
            salesFeeRote: {key: "salesFeeRote", describe: "销售费率"},
            salesFee: {key: "salesFee", describe: "销售费"},
            replacementValue: {key: "replacementValue", describe: "重置价格"},
            addedValueAdditionalTaxRate: {
                key: "addedValueAdditionalTaxRate",
                describe: "增值及附加税率",
                select: "addedValueAdditionalTaxRateSelect"
            },
            valueAddedAdditionalTaxes: {key: "valueAddedAdditionalTaxes", describe: "增值及附加税金"},
            interestPeriod: {key: "interestPeriod", describe: "计息周期"},
            interestRateOnInvestment: {key: "interestRateOnInvestment", describe: "投资计息利率"},
            interestRateOnInvestmentCorrect: {key: "interestRateOnInvestmentCorrect", describe: "投资计息税率修正"},
            interestInInvestment: {key: "interestInInvestment", describe: "投资利息"},//
            developmentProfitMarginRote: {key: "developmentProfitMarginRote", describe: "开发利润率", value: "", select: ""},
            developmentProfitMargin: {key: "developmentProfitMargin", describe: "开发利润", value: "", select: ""},
            developmentProfitMarginRoteCorrect: {key: "developmentProfitMarginRoteCorrect", describe: "开发利润率修正", value: "", select: ""},
            evaluationValueConstructionProject: {key: "evaluationValueConstructionProject", describe: "在建工程评估价值", value: "", select: ""},
            evaluationValueConstructionProjectCorrect: {key: "evaluationValueConstructionProjectCorrect", describe: "在建工程评估价值修正", value: "", select: ""},
            constructionProcesAssessValue: {key: "constructionProcesAssessValue", describe: "在建工程评估值", value: "", select: ""}
        };
        config.inputConfig = function () {
            return inputNameConfig;
        };
        config.inputName = function () {
            var arr = new Array();
            //遍历对象
            $.each(construction.config().inputConfig(), function (i, n) {
                arr.push(n);
            })
            return arr;
        }
        config.hiddenData = function () {
            var area = $(".mdCost .area").val();
            var price = $(".mdCost .price").val();
            if (!construction.isNotNull(area)) {
                area = Math.round(Math.random() * 100);
            }
            if (!construction.isNotNull(price)) {
                price = Math.round(Math.random() * 100) + Math.random();
            }
            var data = {
                area: area,
                price: price
            };
            return data;
        };
        return config;
    }

    /**--------------------------------基础方法------------------**/

    /**
     * @author:  zch
     * 描述: 成本法中所有的计算都会归纳到这进行运算算法
     * @date:2018-08-15
     **/
    construction.inputAlgorithm = function (dataName, dataValue) {
        var b = dataValue; //局部变量===============================================>
        b = construction.inputAlgorithmObject.specialTreatment(b);
        console.log("test:" + dataName + " " + dataValue);
        //土地开发面积
        if (dataName == construction.config().inputConfig().developmentLandArea.key) {
            construction.inputAlgorithmObject.landPurchasePriceFun(dataValue);
        }
        //土地购买单价
        if (dataName == construction.config().inputConfig().unitPriceLandPurchase.key) {
            construction.inputAlgorithmObject.landPurchasePriceFun(dataValue);
            construction.inputAlgorithmObject.landAcquisitionPriceFun(dataValue);
        }
        //土地取得税费
        if (dataName == construction.config().inputConfig().landAcquisitionTaxRate.key) {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().landPurchasePrice.key, null);//土地购买合价
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().landAcquisitionTax.key, c); //土地取得税费
            construction.inputAlgorithmObject.landAcquisitionPriceFun(dataValue);
        }
        //土地取得小计
        if (dataName == construction.config().inputConfig().landAcquisitionTotal.key) {
            construction.inputAlgorithmObject.landAcquisitionTotalFun(dataValue);
        }
        //勘察设计和前期工程费 = 建筑安装工程费*勘察设计和前期工程费率
        if (dataName == construction.config().inputConfig().reconnaissanceDesignRote.key) {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(b, a);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().reconnaissanceDesign.key, c); //勘察设计和前期工程费
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //公共配套设施建设费 = 公共配套设施建设费单价*开发建筑面积
        if (dataName == construction.config().inputConfig().infrastructureMatchingCost.select) {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(b, a);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureMatchingCost.key, c); //公共配套设施建设费
            construction.inputAlgorithmObject.constructionCostFun();
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //基础设施建设费 = 基础设施建设费单价*开发建筑面积
        if (dataName == construction.config().inputConfig().infrastructureCost.select) {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(b, a);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureCost.key, c); //基础设施建设费
            construction.inputAlgorithmObject.constructionCostFun();
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //更新 基础设施建设费和公共配套设施建设费 开发期间税费
        if (dataName == construction.config().inputConfig().developmentBuildArea.key) { // 开发建筑面积
            var a, c, d, e;
            a = $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureCost.select).eq(1).val();//基础设施建设费单价
            c = $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureMatchingCost.select).eq(1).val();//公共配套设施建设费单价
            e = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().devDuringPrice.key, null);//开发期间单价
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.inputAlgorithmObject.specialTreatment(c);
            e = construction.inputAlgorithmObject.specialTreatment(e);

            d = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureCost.key, d); //基础设施建设费
            d = construction.mul(c, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureMatchingCost.key, d); //公共配套设施建设费
            d = construction.mul(e, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().devDuringPriceTax.key, d); //开发期间税费
            construction.inputAlgorithmObject.otherEngineeringCostFun(b);
        }
        //开发期间税费 = 开发建筑面积 * 开发期间单价
        if (dataName == construction.config().inputConfig().devDuringPrice.key) {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().devDuringPriceTax.key, c); //开发期间税费
            construction.inputAlgorithmObject.constructionCostFun();
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //其它工程费 = 其它工程费单价 * 开发建筑面积
        if (dataName == construction.config().inputConfig().otherEngineeringCostPrice.key) {
            construction.inputAlgorithmObject.otherEngineeringCostFun(b);
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //建设成本
        if (dataName == construction.config().inputConfig().constructionCost.key) {
            construction.inputAlgorithmObject.constructionCostFun();
        }
        //管理费 = 建设成本*管理费率
        if (dataName == construction.config().inputConfig().managementExpenseRote.key) {
            construction.inputAlgorithmObject.managementExpenseRoteFun();
        }
        //不可预见费
        if (dataName == construction.config().inputConfig().unforeseenExpensesRote.key) {
            construction.inputAlgorithmObject.unforeseenExpensesFun();
        }
        //销售费
        if (dataName == construction.config().inputConfig().salesFeeRote.key) {//销售费率
            construction.inputAlgorithmObject.salesFeeFun();
            construction.inputAlgorithmObject.developmentProfitMarginRoteCorrectFun();
            construction.inputAlgorithmObject.evaluationValueConstructionProjectCorrectFun();
        }
        //增值及附加税金
        if (dataName == construction.config().inputConfig().addedValueAdditionalTaxRate.key) {//增值及附加税率
            construction.inputAlgorithmObject.addedValueAdditionalTaxRateFun();
            construction.inputAlgorithmObject.evaluationValueConstructionProjectCorrectFun();
        }
        //重置价格
        if (dataName == construction.config().inputConfig().replacementValue.key) {
            construction.inputAlgorithmObject.addedValueAdditionalTaxRateFun();
            construction.inputAlgorithmObject.salesFeeFun();
        }
        //计息周期
        if (dataName == construction.config().inputConfig().interestPeriod.key) {
            construction.inputAlgorithmObject.interestRateOnInvestmentCorrectFun();
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //投资计息利率
        if (dataName == construction.config().inputConfig().interestRateOnInvestment.key) {
            construction.inputAlgorithmObject.interestRateOnInvestmentCorrectFun();
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //投资利息
        if (dataName == construction.config().inputConfig().interestInInvestment.key) {
            construction.inputAlgorithmObject.interestInInvestmentFun();
        }
        //开发利润率
        if (dataName == construction.config().inputConfig().developmentProfitMarginRote.key) {
            construction.inputAlgorithmObject.developmentProfitMarginRoteFun();
        }
        $(function () {
            construction.inputEvent();
        });
    }
    construction.inputAlgorithmObject = {
        //在建工程评估值 = 在建工程评估价值/(1-在建工程评估价值修正)
        constructionProcesAssessValueFun:function () {
            var a, c, b;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().evaluationValueConstructionProject.key,null); //在建工程评估价值
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().evaluationValueConstructionProjectCorrect.key,null); //在建工程评估价值修正
            c = construction.sub(1,a);
            c = construction.div(b,c);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().constructionProcesAssessValue.key,c); //在建工程评估值
        },
        //在建工程评估价值修正 = 销售费率+投资计息税率修正+增值及附加税率+开发利润率修正
        evaluationValueConstructionProjectCorrectFun:function () {
            var a, c, b, d, e,f,m;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().salesFeeRote.key, null);//销售费率
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().interestRateOnInvestmentCorrect.key,null); //投资计息税率修正
            c = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentProfitMarginRoteCorrect.key,null);//开发利润率修正
            var value = $("." + construction.config().inputConfig().addedValueAdditionalTaxRate.select).eq(1).val();
            AssessCommon.getDataDicInfo(value, function (data) {
                var percent = construction.toPoint(data.name);
                if (construction.isNumber(percent)) {
                    //percent 增值及附加税率
                    percent = construction.inputAlgorithmObject.specialTreatment(percent);
                    m = construction.add(  construction.add(a,b),construction.add(c,percent)  );
                    construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().evaluationValueConstructionProjectCorrect.key,m); //在建工程评估价值修正
                    construction.inputAlgorithmObject.constructionProcesAssessValueFun();
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        //在建工程评估价值 = 土地取得小计+建设成本+不可预见费+管理费+投资利息+开发利润
        evaluationValueConstructionProjectFun:function () {
            var a, c, b, d, e,f,m;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().landAcquisitionTotal.key, null); //土地取得小计
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionCost.key, null);//建设成本
            c = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().unforeseenExpenses.key,null); //不可预见费
            d = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().managementExpense.key, null); //管理费
            e = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().interestInInvestment.key, null); //投资利息
            f = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentProfitMargin.key,null); //开发利润
            m = construction.add(   construction.add(a,b) , construction.add(c,d)      );
            m = construction.add(   construction.add(e,f) , m      );
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().evaluationValueConstructionProject.key,m); //在建工程评估价值
            construction.inputAlgorithmObject.constructionProcesAssessValueFun();
        },
        //开发利润率修正
        developmentProfitMarginRoteCorrectFun:function () {
            var a, c, b ;
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentProfitMarginRote.key, null); //开发利润率
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().salesFeeRote.key, null);//销售费率
            c = construction.mul(a,b);
           construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().developmentProfitMarginRoteCorrect.key, c);//开发利润率修正
            construction.inputAlgorithmObject.evaluationValueConstructionProjectCorrectFun();
        },
        //开发利润 = (土地取得小计+建设成本+不可预见费+管理费)* 开发利润率
        developmentProfitMarginFun:function () {
            var a, c, b, d, e,f;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().landAcquisitionTotal.key, null); //土地取得小计
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionCost.key, null);//建设成本
            c = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().unforeseenExpenses.key,null); //不可预见费
            d = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().managementExpense.key, null); //管理费
            e = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentProfitMarginRote.key, null); //开发利润率
            f = construction.add(  construction.add(a,b), construction.add(c,d ) );
            f = construction.mul(f,e);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().developmentProfitMargin.key, f); //开发利润
            construction.inputAlgorithmObject.evaluationValueConstructionProjectFun();
        },
        //开发利润率被输入 需要调用的方法
        developmentProfitMarginRoteFun:function () {
            construction.inputAlgorithmObject.developmentProfitMarginFun();
            construction.inputAlgorithmObject.developmentProfitMarginRoteCorrectFun();
        },
        /**
         * 投资利息 =
         * (土地取得小计+勘察设计和前期工程费+基础设施建设费)*((1+投资计息利率)^ 计息周期-1)+( 建筑安装工程费+公共配套设施建设费+开发期间税费+其它工程费+不可预见费+管理费)*((1+投资计息利率)^( 计息周期/2)-1)
         * -------------------------------A-----------------------------------------|| -------------------------------------------------B---------------------------------------------------
         * **
         */
        interestInInvestmentFun: function () {
            var a, c, b, d, e;
            var f, g, h, j, k, l;
            var m , n ,temp;//运算变量
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().landAcquisitionTotal.key, null); //土地取得小计
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().reconnaissanceDesign.key,null); //勘察设计和前期工程费
            c = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().infrastructureCost.key, null); //基础设施建设费
            d = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().interestRateOnInvestment.key, null); //投资计息利率
            e = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().interestPeriod.key, null); //计息周期

            f = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionInstallationEngineeringFee.key, null); //建筑安装工程费
            g = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().infrastructureMatchingCost.key,null); //公共配套设施建设费
            h = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().devDuringPriceTax.key, null); //开发期间税费
            j = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().otherEngineeringCost.key, null); //其它工程费
            k = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().unforeseenExpenses.key,null); //不可预见费
            l = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().managementExpense.key, null); //管理费
            //数据校验
            var flag = construction.isNumber(a) && construction.isNumber(b) && construction.isNumber(c) && construction.isNumber(d) && construction.isNumber(e) ;
            flag = construction.isNumber(f) && construction.isNumber(g) && construction.isNumber(h) && construction.isNumber(j) && construction.isNumber(k) ;
            flag = flag && construction.isNumber(l);
            if (!flag){
                return false;
            }
            //首先计算A段
            m = construction.add(a,b) ;
            m = construction.add(m,c);
            n = construction.add(1,d);
            temp = construction.sub(e,1);
            n = Math.pow(n,temp);
            m = construction.mul(m,n);//A段结果
            //接着计算B段结果
            n = construction.add(   construction.add(f,g) , construction.add(h,j)      );
            n = construction.add(   construction.add(k,l) , n);
            temp = Math.pow(construction.add(1,d),construction.sub(construction.div(e,2),1));
            n = construction.mul(n,temp); //B段结果
             // 最终结果
            m = construction.add(m,n);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().interestInInvestment.key, m); //投资利息
            construction.inputAlgorithmObject.evaluationValueConstructionProjectFun();
        },
        //投资计息税率修正 = (1+投资利息利率)^(计息周期/2)-1
        interestRateOnInvestmentCorrectFun: function () {
            var a, c, b;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().interestPeriod.key, null); //计息周期
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().interestRateOnInvestment.key, null); //投资计息利率
            if (construction.isNotNull(a)) {
                if (construction.isNotNull(b)) {
                    a = construction.inputAlgorithmObject.specialTreatment(a);
                    b = construction.inputAlgorithmObject.specialTreatment(b);
                    a = construction.add(a, 1);
                    b = construction.div(construction.mul(b, 2), 1);
                    c = Math.pow(a, b);
                    construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().interestRateOnInvestmentCorrect.key, c); //投资计息税率修正
                    construction.inputAlgorithmObject.evaluationValueConstructionProjectCorrectFun();
                }
            }
        },
        //增值及附加税金 = 重置价格*增值及附加税率
        addedValueAdditionalTaxRateFun: function () {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().replacementValue.key, null); //重置价格
            a = construction.inputAlgorithmObject.specialTreatment(a);
            var value = $("." + construction.config().inputConfig().addedValueAdditionalTaxRate.select).eq(1).val();
            AssessCommon.getDataDicInfo(value, function (data) {
                var percent = construction.toPoint(data.name);
                if (construction.isNumber(percent)) {
                    c = construction.mul(a, percent);
                    construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().valueAddedAdditionalTaxes.key, c); //增值及附加税金
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        //销售费 = 重置价格*销售费率
        salesFeeFun: function () {
            var a, c, b, d, e;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().replacementValue.key, null); //重置价格
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().salesFeeRote.key, null);//销售费率
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().salesFee.key, c); //销售费
        },
        //不可预见费
        unforeseenExpensesFun: function () {
            var a, c, b, d, e;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().managementExpense.key, null); //管理费
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionCost.key, null);//建设成本
            c = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().unforeseenExpensesRote.key, null);//不可预见费率
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.inputAlgorithmObject.specialTreatment(c);
            d = construction.add(a, b);
            d = construction.mul(d, c);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().unforeseenExpenses.key, d); //不可预见费
            construction.inputAlgorithmObject.interestInInvestmentFun();
            construction.inputAlgorithmObject.developmentProfitMarginFun();
            construction.inputAlgorithmObject.evaluationValueConstructionProjectFun();
        },
        //管理费
        managementExpenseRoteFun: function () {
            var a, c, b;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().managementExpenseRote.key, null);//管理费率
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionCost.key, null);//建设成本
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().managementExpense.key, c); //管理费
            construction.inputAlgorithmObject.interestInInvestmentFun();
            construction.inputAlgorithmObject.developmentProfitMarginFun();
            construction.inputAlgorithmObject.evaluationValueConstructionProjectFun();
        },
        //建设成本 = 前期工程费+安装工程费+基础设施费+公共设施费+开发期间税费+其它工程费
        constructionCostFun: function () {
            var a, c, b, d, e, f, g;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().reconnaissanceDesign.key, null); //勘察设计和前期工程费
            c = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().infrastructureCost.key, null); //基础设施建设费
            d = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().infrastructureMatchingCost.key, null); //公共配套设施建设费
            e = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().otherEngineeringCost.key, null); //其它工程费
            f = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().devDuringPriceTax.key, null); //开发期间税费
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.inputAlgorithmObject.specialTreatment(c);
            d = construction.inputAlgorithmObject.specialTreatment(d);
            e = construction.inputAlgorithmObject.specialTreatment(e);
            f = construction.inputAlgorithmObject.specialTreatment(f);
            g = construction.add(construction.add(a, b), construction.add(c, d));
            g = construction.add(construction.add(e, f), g);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().constructionCost.key, g); //建设成本
            construction.inputAlgorithmObject.managementExpenseRoteFun();
            construction.inputAlgorithmObject.unforeseenExpensesFun();
            construction.inputAlgorithmObject.developmentProfitMarginFun();
            construction.inputAlgorithmObject.evaluationValueConstructionProjectFun();
        },
        //其它工程费
        otherEngineeringCostFun: function (obj) {
            var a, c, b;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().otherEngineeringCostPrice.key, null);//其它工程费单价
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().otherEngineeringCost.key, c); //其它工程费
            construction.inputAlgorithmObject.constructionCostFun();
        },
        //勘察设计和前期工程费 = 建筑安装工程费*勘察设计和前期工程费率
        reconnaissanceDesignFun: function (obj) {
            var a, c, b;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().reconnaissanceDesignRote.key, null);//勘察设计和前期工程费率
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.mul(b, a);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().reconnaissanceDesign.key, c); //勘察设计和前期工程费
        },
        //土地取得小计 = 土地取得单价*开发土地面积
        landAcquisitionTotalFun: function (obj) {
            var a, b, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().landAcquisitionPrice.key, null); //土地取得单价
            b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentLandArea.key, null); //开发土地面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            b = construction.inputAlgorithmObject.specialTreatment(b);
            c = construction.mul(a, b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().landAcquisitionTotal.key, c); //土地取得小计
            construction.inputAlgorithmObject.interestInInvestmentFun();
            construction.inputAlgorithmObject.developmentProfitMarginFun();
            construction.inputAlgorithmObject.evaluationValueConstructionProjectFun();
        },
        //土地取得单价 = 土地购买单价+土地购买单价*土地取得税率
        landAcquisitionPriceFun: function (obj) {
            var a, b, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().unitPriceLandPurchase.key, null);//土地购买单价
            b = $("." + construction.config().frm + " ." + construction.config().inputConfig().landAcquisitionTaxRate.select).eq(1).val();//土地取得税率
            AssessCommon.getDataDicInfo(b, function (data) {
                try {
                    //可能收集到的是null值()
                    b = construction.toPoint(data.name);
                } catch (e) {
                    b = 0;
                }
                a = construction.inputAlgorithmObject.specialTreatment(a);
                c = construction.mul(a, b);
                c = construction.add(c, a);
                construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().landAcquisitionPrice.key, c); //土地取得单价
                construction.inputAlgorithmObject.landAcquisitionTotalFun(c);
            });
        },
        //土地取得税费
        landAcquisitionTaxFun: function (obj) {
            var a, b, c;
            b = $("." + construction.config().frm + " ." + construction.config().inputConfig().landAcquisitionTaxRate.select).eq(1).val();
            AssessCommon.getDataDicInfo(b, function (data) {
                try {
                    //可能收集到的是null值()
                    b = construction.toPoint(data.name);
                } catch (e) {
                    b = 0;
                }
                b = construction.inputAlgorithmObject.specialTreatment(b);
                a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().landPurchasePrice.key, null);//土地购买合价
                c = construction.mul(a, b);
                construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().landAcquisitionTax.key, c); //土地取得税费
            });
        },
        //土地购买合价 = 土地购买单价*开发土地面积
        landPurchasePriceFun: function (obj) {
            if (construction.isNotNull(obj)) {
                var c = null;
                var b = null;
                var a = null;
                b = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentLandArea.key, null);//土地开发面积
                a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().unitPriceLandPurchase.key, null);//土地购买单价
                b = construction.inputAlgorithmObject.specialTreatment(b);
                a = construction.inputAlgorithmObject.specialTreatment(a);
                //计算
                c = construction.mul(a, b);
                // console.log("test " + "a:" + a + "; b:" + b + "; c" + c);
                //设置算出的结果值到input中
                construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().landPurchasePrice.key, c); //土地购买合价
                construction.inputAlgorithmObject.landAcquisitionTaxFun(c);
            }
        },
        jqueryInputGetAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("." + construction.config().frm + " " + "input[name='" + name + "']").val() ;
                text = construction.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + construction.config().frm + " " + "input[name='" + name + "']").val(data);
            }
        },
        isNotNull: function (obj) {
            if (obj == 0) {
                return true;
            }
            // if (obj == '') {
            //     return true;
            // }
            if (obj) {
                return true;
            }
            return false;
        },
        specialTreatment: function (obj) {
            if (construction.inputAlgorithmObject.isNotNull(obj)) {
                return obj;
            }
            return 0;
        }
    }

    /**
     * @author:  zch
     * 描述:input框事件 以及select change事件
     * @date:2018-08-15
     **/
    construction.inputEvent = function () {
        var arr = construction.config().inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + construction.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                if (construction.isNumber(value)) {
                    construction.inputAlgorithm(key, input.val());
                } else {
                    Alert("请输入合法数字!")
                }
            });
        });
        construction.selectEvent.init();
    }
    construction.selectEvent = {
        //基础设施建设费 单价选择
        infrastructureCost: function () {
            var key = construction.config().inputConfig().infrastructureCost;
            $("." + construction.config().frm + " ." + key.select).change(function () {
                var value = $("." + construction.config().frm + " ." + key.select).eq(1).val();
                if (construction.isNumber(value)) {
                    construction.inputAlgorithm(key.select, value);
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        //公共配套设施建设费 单价选择
        infrastructureMatchingCost: function () {
            var key = construction.config().inputConfig().infrastructureMatchingCost;
            $("." + construction.config().frm + " ." + key.select).change(function () {
                var value = $("." + construction.config().frm + " ." + key.select).eq(1).val();
                if (construction.isNumber(value)) {
                    construction.inputAlgorithm(key.select, value);
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        //土地取得税率 change 事件
        landAcquisitionTaxRate: function () {
            var data = construction.config().inputConfig().landAcquisitionTaxRate;
            var key = construction.config().frm + " ." + data.select;
            $("." + key).change(function () {
                var value = $("." + key).eq(1).val();
                AssessCommon.getDataDicInfo(value, function (data) {
                    var percent = construction.toPoint(data.name);
                    if (construction.isNumber(percent)) {
                        construction.inputAlgorithm(construction.config().inputConfig().landAcquisitionTaxRate.key, percent);
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            });
        },
        //增值及附加税率  change 事件
        addedValueAdditionalTaxRate: function () {
            var data = construction.config().inputConfig().addedValueAdditionalTaxRate;
            var key = construction.config().frm + " ." + data.select;
            $("." + key).change(function () {
                var value = $("." + key).eq(1).val();
                AssessCommon.getDataDicInfo(value, function (data) {
                    var percent = construction.toPoint(data.name);
                    if (construction.isNumber(percent)) {
                        construction.inputAlgorithm(construction.config().inputConfig().addedValueAdditionalTaxRate.key, percent);
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            });
        },
        init: function () {
            construction.selectEvent.landAcquisitionTaxRate();
            construction.selectEvent.infrastructureCost();
            construction.selectEvent.infrastructureMatchingCost();
            construction.selectEvent.addedValueAdditionalTaxRate();
        }
    }

    construction.eventInit = function () {
        construction.inputEvent();
        construction.select2LoadData.init();
    }

    //加载所有select2数据
    construction.select2LoadData = {
        //获取基础设施费用列表和公共配套设施费用
        loadCostAndMatchingCost: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/marketCost/listCostAndMatchingCost",
                type: "get",
                data: {},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var cost = result.data.InfrastructureCost;
                        var matchingCost = result.data.InfrastructureMatchingCost;
                        var option = "<option value=''>请选择</option>";
                        if (cost.length > 0) {
                            for (var i = 0; i < cost.length; i++) {
                                option += "<option value='" + cost[i].number + "'>" + cost[i].name + "</option>";
                            }
                            $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureCost.select).html(option);
                            $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureCost.select).select2();
                        }
                        if (matchingCost.length > 0) {
                            for (var i = 0; i < matchingCost.length; i++) {
                                option += "<option value='" + matchingCost[i].number + "'>" + matchingCost[i].name + "</option>";
                            }
                            $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureMatchingCost.select).html(option);
                            $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureMatchingCost.select).select2();
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        //土地取得税率 加载数据
        loadSelect2DataLandAcquisitionTaxRate: function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.build_landAcquisitionTaxRate, "", function (html, data) {
                $("." + construction.config().frm + " ." + construction.config().inputConfig().landAcquisitionTaxRate.select).html(html);
                $("." + construction.config().frm + " ." + construction.config().inputConfig().landAcquisitionTaxRate.select).select2();//加载样式
            })
        },
        //增值及附加税率
        loadSelect2DataAddedValueAdditionalTaxRate: function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.build_addedvalueadditionaltaxrate, "", function (html, data) {
                $("." + construction.config().frm + " ." + construction.config().inputConfig().addedValueAdditionalTaxRate.select).html(html);
                $("." + construction.config().frm + " ." + construction.config().inputConfig().addedValueAdditionalTaxRate.select).select2();//加载样式
            })
        },
        init: function () {
            construction.select2LoadData.loadSelect2DataLandAcquisitionTaxRate();
            construction.select2LoadData.loadCostAndMatchingCost();
            construction.select2LoadData.loadSelect2DataAddedValueAdditionalTaxRate();
        }
    }


    /**
     * @author:  zch
     * 描述:建筑安装工程费
     * @date:2018-08-15
     **/
    construction.constructionInstallationEngineeringFee = {
        event: function () {
            $("." + construction.config().frm + " ." + construction.config().engineeringFee).show();
            $(function () {
                constructEngineeringObjectA.viewInit();
            });
        },
        getDataAndWriteHtml: function () {
            var data = constructEngineeringObjectA.getCalculatedResults();
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().constructionInstallationEngineeringFee.key, data); //建筑安装工程费
            $("." + construction.config().frm + " ." + construction.config().engineeringFee).hide();
            construction.inputAlgorithmObject.reconnaissanceDesignFun(data);
            construction.inputAlgorithmObject.constructionCostFun();
        },
        close: function () {
            $("." + construction.config().frm + " ." + construction.config().engineeringFee).hide();
        }
    }

    /**--------------------------------初始化------------------**/
    $(function () {
        construction.eventInit();
    });
</script>