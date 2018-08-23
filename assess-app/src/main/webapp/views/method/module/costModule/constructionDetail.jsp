<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
  在建工程
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal frmConstruction" id="frmConstruction">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            开发土地面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
                       placeholder="开发土地面积" class="form-control" name="developmentLandArea">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发建筑面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" data-rule-number='true' required="required"
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
                       placeholder="建筑安装工程费" class="form-control" name="constructionInstallationEngineeringFee">
            </div>
        </div>
    </div>



    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="基础设施建设费 单价选择" class="form-control" name="infrastructureCostSelect">
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
                <input type="text" readonly="readonly"
                       placeholder="公共配套设施建设费 单价选择" class="form-control" name="infrastructureMatchingCostSelect">
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
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" data-rule-number='true' required="required"
                       placeholder="管理费率" class="form-control" name="managementExpenseRote">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            不可预见费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" data-rule-number='true' required="required"
                       placeholder="销售费率" class="form-control" name="salesFeeRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            重置价格
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
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
                <input type="text" readonly="readonly"
                       placeholder="增值及附加税率" class="form-control" name="addedValueAdditionalTaxRateSelect">
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
                <input type="text" data-rule-number='true' required="required"
                       placeholder="投资计息利率" class="form-control" name="interestRateOnInvestment">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            计息周期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" data-rule-number='true' required="required"
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
            developmentProfitMarginRoteCorrect: {
                key: "developmentProfitMarginRoteCorrect",
                describe: "开发利润率修正",
                value: "",
                select: ""
            },
            evaluationValueConstructionProject: {
                key: "evaluationValueConstructionProject",
                describe: "在建工程评估价值",
                value: "",
                select: ""
            },
            evaluationValueConstructionProjectCorrect: {
                key: "evaluationValueConstructionProjectCorrect",
                describe: "在建工程评估价值修正",
                value: "",
                select: ""
            },
            constructionProcesAssessValue: {
                key: "constructionProcesAssessValue",
                describe: "在建工程评估值",
                value: "",
                select: ""
            }
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
        return config;
    }


</script>