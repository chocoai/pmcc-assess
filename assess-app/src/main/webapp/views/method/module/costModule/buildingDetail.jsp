<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:03
  To change this template use File | Settings | File Templates.
  建筑物
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal frmBuild" id="frmBuild">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            委估对象面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" placeholder="委估对象面积"
                       class="form-control mdCost area" name="area" readonly="readonly" value="${mdCost.area}">
                <!-- 委估对象面积 -->
            </div>
        </div>

        <label class="col-sm-1 control-label">
            委估对象价格
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" placeholder="委估对象价格"
                       class="form-control mdCost price" name="price" readonly="readonly" value="${mdCost.area}">
                <!-- 委估对象价格 -->
            </div>
        </div>

    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="勘察设计和前期工程费率" data-toggle="popover reconnaissanceDesignRote" class="form-control"
                           name="reconnaissanceDesignRote">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                建筑安装工程费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑安装工程费" readonly="readonly" class="form-control"
                           name="constructionInstallationEngineeringFee">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="勘察设计和前期工程费" data-toggle="popover reconnaissanceDesign"
                           class="form-control" name="reconnaissanceDesign" readonly="readonly">
                </div>
            </div>
        </div>
    </div>




    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                基础设施建设费 单价选择
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="公共配套设施建设费 单价选择" class="form-control" name="infrastructureCostSelect2">

                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                基础设施建设费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="基础设施建设费" class="form-control" name="infrastructureCost">
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                公共配套设施建设费 单价选择
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="公共配套设施建设费 单价选择" class="form-control" name="infrastructureMatchingCostSelect2">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                公共配套设施建设费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="公共配套设施建设费" class="form-control" name="infrastructureMatchingCost">
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                开发期间单价
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="开发期间单价" class="form-control" name="devDuringPrice">
                </div>
            </div>
        </div>
        <div class="x-valid">
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
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                其它工程费单价
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="其它工程费单价" class="form-control" name="otherEngineeringCostPrice">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                其它工程费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="其它工程费" class="form-control" name="otherEngineeringCost">
                </div>
            </div>
        </div>
        <div class="x-valid">
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
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                管理费率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="管理费率" class="form-control" name="managementExpenseRote">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                管理费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="管理费" class="form-control" name="managementExpense">
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
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
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                不可预见费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="不可预见费" class="form-control" name="unforeseenExpenses">
                </div>
            </div>
        </div>
        <div class="x-valid">
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
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                销售费率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" data-rule-number='true' required="required"
                           placeholder="销售费率" class="form-control" name="salesFeeRote">
                </div>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                销售费
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="销售费" class="form-control" name="salesFee">
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">
                增值及附加税率
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="销售费" class="form-control" name="addedValueAdditionalTaxRateSelect2">
                </div>
            </div>
        </div>
        <div class="x-valid">
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
    </div>

    <div class="form-group">
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
            投资计息利率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="投资计息利率" class="form-control" name="interestRateOnInvestment">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            投资利息
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利息" class="form-control" name="interestInInvestment">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            投资利润率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="投资利润率" class="form-control" name="investmentProfitRate">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            投资利润
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="投资利润" class="form-control" name="investmentProfit">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            成新率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="成新率" class="form-control" name="newRate">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            评估单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="评估单价" class="form-control" name="assessPrice">
            </div>
        </div>

    </div>


</form>


<script type="text/javascript">
    /**
     * @author:  zch
     * 描述: 定义一个对象 (页面上不能与此对象的名称相同)
     * @date:2018-08-08
     **/
    var build = new Object();
    /**--------------------------------基础算法------------------**/
    /**
     * @author:  zch
     * 描述:是否为null
     * @date:2018-08-08
     **/
    build.isNotNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    /**
     * @author:  zch
     * 描述:是否是数字
     * @date:2018-08-08
     **/
    build.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    /**
     * @author:  zch
     * 描述:是否是正数
     * @date:2018-08-08
     **/
    build.isPlus = function (obj) {
        return AlgorithmsPrototype.prototype.isPlus(obj);
    }
    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-08
     **/
    build.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:2018-08-08
     **/
    build.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:2018-08-08
     **/
    build.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:除法
     * @date:2018-08-08
     **/
    build.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:百分数转小数
     * @date:
     **/
    build.toPoint = function (percent) {
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
    build.toPercent = function (point) {
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
    build.config = function () {
        //延迟显示和隐藏弹出框的毫秒数
        var config = {};
        config.show = 500;
        config.hide = 1000;
        config.frm = "frmBuild";//表单id
        config.engineeringFee = "constructionInstallationEngineeringFeeB"; //子表单id
        config.newRate = "newRateModelA"; //成新率 子表单

        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        var inputNameConfig = {
            reconnaissanceDesign: {key: "reconnaissanceDesign", describe: "勘察设计和前期工程费"},
            reconnaissanceDesignRote: {key: "reconnaissanceDesignRote", describe: "勘察设计和前期工程费率"},
            constructionInstallationEngineeringFee: {
                key: "constructionInstallationEngineeringFee",
                describe: "建筑安装工程费"
            },
            infrastructureCost: {
                key: "infrastructureCost",//infrastructureCost 算是一个input
                describe: "基础设施费用",
                select: "infrastructureCostSelect2"//infrastructureCostSelect2 算是一个select (input)
            },
            infrastructureMatchingCost: {
                key: "infrastructureMatchingCost",//infrastructureMatchingCost 算是一个input
                describe: "公共配套设施费用",
                select: "infrastructureMatchingCostSelect2"//注意:infrastructureMatchingCostSelect2算是一个select (input)
            },
            devDuringPrice: {
                key: "devDuringPrice",
                describe: "开发期间单价"
            },
            devDuringPriceTax: {
                key: "devDuringPriceTax",
                describe: "开发期间单价税收"
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
                select: "addedValueAdditionalTaxRateSelect2"
            },
            valueAddedAdditionalTaxes: {key: "valueAddedAdditionalTaxes", describe: "增值及附加税金"},
            realEstateDevelopmentCycle: {key: "realEstateDevelopmentCycle", describe: "类式房产开发周期"},
            interestPeriod: {key: "interestPeriod", describe: "计息周期"},
            interestRateOnInvestment: {key: "interestRateOnInvestment", describe: "投资计息利率"},
            interestInInvestment: {key: "interestInInvestment", describe: "投资利息"},
            investmentProfitRate: {key: "investmentProfitRate", describe: "投资利润率"},
            investmentProfit: {key: "investmentProfit", describe: "投资利润"},
            newRate: {key: "newRate", describe: "成新率"},
            assessPrice: {key: "assessPrice", describe: "评估单价"},
        };
        config.inputConfig = function () {
            return inputNameConfig;
        };
        /**
         * @author:  zch
         * 描述:用做给页面上的所有input框添加事件
         * @date:2018-08-10
         **/
        config.inputName = function () {
            var arr = new Array();
            $.each(build.config().inputConfig(), function (i, n) {
                arr.push(n);
            });
            return arr;
        };
        config.hiddenData = function () {
            var area = $(".mdCost .area").val();
            var price = $(".mdCost .price").val();
            if (!build.isNotNull(area)) {
                area = Math.round(Math.random() * 100);
            }
            if (!build.isNotNull(price)) {
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


</script>




