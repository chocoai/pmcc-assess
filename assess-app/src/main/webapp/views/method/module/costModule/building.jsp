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
                           name="constructionInstallationEngineeringFee"
                           onclick="build.constructionInstallationEngineeringFee.event();">
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

    <div class="constructionInstallationEngineeringFeeB" style="display: none;">
        <jsp:include page="constructionInstallationEngineeringFeeB.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="build.constructionInstallationEngineeringFee.close();">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="build.constructionInstallationEngineeringFee.getDataAndWriteHtml();">
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
                    <select name="infrastructureCostSelect2" required="required"
                            class="form-control search-select select2 infrastructureCostSelect2">
                    </select>
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
                    <select name="infrastructureMatchingCostSelect2" required="required"
                            class="form-control search-select select2 infrastructureMatchingCostSelect2">
                    </select>
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
                    <select name="addedValueAdditionalTaxRate" required="required"
                            class="form-control search-select select2 addedValueAdditionalTaxRateSelect2">
                    </select>
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
                       placeholder="成新率" class="form-control" name="newRate"
                       onclick="build.newRateModel.show();">
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

    /**--------------------------------基础方法------------------**/
    /**
     * @author:  zch
     * 描述: 成本法中所有的计算都会归纳到这进行运算算法 (弃用)
     * @date:2018-08-10
     **/
    build.inputAlgorithm = function (dataName, dataValue) {
        $(function () {
            build.inputEvent();
        });
    }

    /**
     * @author:  zch
     * 描述:input数据输入
     * @date:
     **/
    build.inputFun = {
        //建筑安装工程费
        constructionInstallationEngineeringFeeInput: function (data) {
            build.inputAlgorithmObject.reconnaissanceDesignFun(data);
            build.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignRoteInput: function (data) {
            build.inputAlgorithmObject.reconnaissanceDesignFun(data);
        },
        //基础设施建设费 单价选择
        infrastructureCostSelect2Input: function (data) {
            build.inputAlgorithmObject.infrastructureCostFun(data);
        },
        //公共配套设施建设费 单价选择
        infrastructureMatchingCostSelect2Input: function (data) {
            build.inputAlgorithmObject.infrastructureMatchingCostFun(data);
        },
        //开发期间单价
        devDuringPriceInput: function (data) {
            build.inputAlgorithmObject.devDuringPriceTaxFun(data);
        },
        //其它工程费单价
        otherEngineeringCostPriceInput: function (data) {
            build.inputAlgorithmObject.otherEngineeringCostFun(data);
        },
        //管理费率
        managementExpenseRoteInput: function (data) {
            build.inputAlgorithmObject.managementExpenseFun(data);
        },
        //不可预见费率
        unforeseenExpensesRoteInput: function (data) {
            build.inputAlgorithmObject.unforeseenExpensesFun();
        },
        //重置价格
        replacementValueInput: function (data) {
            build.inputAlgorithmObject.salesFeeFun();//销售费
            build.inputAlgorithmObject.valueAddedAdditionalTaxesFun();//增值及附加税金
            build.inputAlgorithmObject.assessPriceFun();//评估单价
        },
        //销售费率
        salesFeeRoteInput: function (data) {
            build.inputAlgorithmObject.salesFeeFun();//销售费
        },
        //增值及附加税率
        addedValueAdditionalTaxRateInput: function (data) {
            build.inputAlgorithmObject.valueAddedAdditionalTaxesFun();//增值及附加税金
        },
        //成新率
        newRateInput: function (data) {
            build.inputAlgorithmObject.assessPriceFun();//评估单价
        }
    }
    /**
     * @author:  zch
     * 描述:升级算法 方法
     * @date:
     **/
    build.inputAlgorithmObject = {
        //评估单价 = 重置价格*成新率
        assessPriceFun: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().replacementValue.key, null);//重置价格
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().newRate.key, null);//成新率
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().assessPrice.key, c);//评估单价
        },
        //增值及附加税金 = 重置价格*增值及附加税率
        valueAddedAdditionalTaxesFun: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            var key = build.config().inputConfig().addedValueAdditionalTaxRate.select;
            var value = $("." + build.config().inputConfig().addedValueAdditionalTaxRate.select).eq(1).val();
            AssessCommon.getDataDicInfo(value, function (data) {
                if (build.isNotNull(data)) {
                    if (build.isNotNull(data.name)) {
                        var percent = build.toPoint(data.name);
                        percent = build.inputAlgorithmObject.specialTreatment(percent); //增值及附加税率
                        b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().replacementValue.key, null);//重置价格
                        c = build.mul(percent, b);
                        build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().valueAddedAdditionalTaxes.key, c);//增值及附加税金
                        //
                    } else {
                        build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().valueAddedAdditionalTaxes.key, 0);//增值及附加税金
                    }
                } else {
                    build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().valueAddedAdditionalTaxes.key, 0);//增值及附加税金
                }
            });
        },
        //销售费 = 重置价格*销售费率
        salesFeeFun: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().salesFeeRote.key, null);//销售费率
            b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().replacementValue.key, null);//重置价格
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().salesFee.key, c);//销售费
        },
        //不可预见费 =（建设成本+管理费金额）*不可预见费率
        unforeseenExpensesFun: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().constructionCost.key, null);//建设成本
            b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().managementExpense.key, null);//管理费
            c = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().unforeseenExpensesRote.key, null);//不可预见费率
            f = build.add(a, b);
            f = build.mul(f, c);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().unforeseenExpenses.key, f);//不可预见费
        },
        //管理费 = 建设成本*管理费率
        managementExpenseFun: function (obj) {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().constructionCost.key, null);//建设成本
            b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().managementExpenseRote.key, null);//管理费率
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().managementExpense.key, c);//管理费
            build.inputAlgorithmObject.unforeseenExpensesFun();//不可预见费
            //建设成本会自动更新管理费,因此不可预见费不必更新建设费,只需要更新管理费
        },
        //建设成本 = 前期工程费+安装工程费+基础设施费+公共设施费+开发期间税费+其它工程费
        constructionCostFun: function () {
            var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().reconnaissanceDesign.key, null);//勘察设计和前期工程费
            b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().constructionInstallationEngineeringFee.key, null);//安装工程费
            c = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().infrastructureCost.key, null);//基础设施建设费
            d = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().infrastructureMatchingCost.key, null);//公共配套设施费用
            e = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().devDuringPriceTax.key, null);//开发期间税费
            f = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().otherEngineeringCost.key, null);//其它工程费
            var temp = null;
            temp = build.add(build.add(a, b), build.add(c, d));
            temp = build.add(build.add(e, f), temp);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().constructionCost.key, temp);//建设成本
            build.inputAlgorithmObject.managementExpenseFun();//管理费
        },
        //其它工程费 = 单价 *估价对象面积
        otherEngineeringCostFun: function () {
            var a = 0, b = 0, c = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().otherEngineeringCostPrice.key, null);//其它工程费单价
            b = build.config().hiddenData().area //估价对象面积
            b = build.inputAlgorithmObject.specialTreatment(b);
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().otherEngineeringCost.key, c);//其它工程费
            build.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //开发期间单价税费 = 开发期间单价*估价对象面积
        devDuringPriceTaxFun: function () {
            var a = 0, b = 0, c = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().devDuringPrice.key, null);//开发期间单价
            b = build.config().hiddenData().area //估价对象面积
            b = build.inputAlgorithmObject.specialTreatment(b);
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().devDuringPriceTax.key, c);//开发期间税费
            build.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //公共配套设施费用 = 公共配套设施费用单价*估价对象面积
        infrastructureMatchingCostFun: function () {
            var a = 0, b = 0, c = 0;
            var key = build.config().inputConfig().infrastructureMatchingCost.select;
            b = build.config().hiddenData().area //估价对象面积
            a = $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureMatchingCost.select).eq(1).val();
            a = build.inputAlgorithmObject.specialTreatment(a);
            b = build.inputAlgorithmObject.specialTreatment(b);
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().infrastructureMatchingCost.key, c);//公共配套设施费用
            build.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //基础设施建设费 = 基础设施建设费单价*估价对象面积
        infrastructureCostFun: function (obj) {
            var a = 0, b = 0, c = 0;
            var key = build.config().inputConfig().infrastructureCost.select;
            b = build.config().hiddenData().area //估价对象面积
            a = $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureCost.select).eq(1).val();
            a = build.inputAlgorithmObject.specialTreatment(a);
            b = build.inputAlgorithmObject.specialTreatment(b);
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().infrastructureCost.key, c);//基础设施建设费
            build.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        //勘察设计和前期工程费 = 建筑安装工程费*费率
        reconnaissanceDesignFun: function (obj) {
            var a = 0, b = 0, c = 0;
            a = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            b = build.inputAlgorithmObject.jqueryInputGetAndSet("get", build.config().inputConfig().reconnaissanceDesignRote.key, null);//勘察设计和前期工程费率
            c = build.mul(a, b);
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().reconnaissanceDesign.key, c);//勘察设计和前期工程费
            build.inputAlgorithmObject.constructionCostFun();//建设成本
        },
        jqueryInputGetAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("." + build.config().frm + " " + "input[name='" + name + "']").val();
                text = build.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + build.config().frm + " " + "input[name='" + name + "']").val(data);
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
            if (build.inputAlgorithmObject.isNotNull(obj)) {
                return obj;
            }
            return 0;
        }
    }
    /**
     * @author:  zch
     * 描述:input框事件
     * @date:2018-08-10
     **/
    build.inputEvent = function () {
        var arr = build.config().inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + build.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                try {
                    var funName = "build.inputFun." + key + "Input(" + input.val() + ")";
                    eval(funName);
                } catch (e) {
                    console.log("函数不存在!");
                }
            });
        });
        build.select2Event.init();
    };
    /**
     * @author:  zch
     * 描述:input select2 事件
     * @date:2018-08-13
     **/
    build.select2Event = {
        infrastructureCost: function () {//基础设施建设费 单价选择
            var key = build.config().inputConfig().infrastructureCost.select;
            $("." + build.config().frm + " ." + key).change(function () {
                var value = $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureCost.select).eq(1).val();
                if (build.isNumber(value)) {
                    try {
                        var funName = "build.inputFun." + build.config().inputConfig().infrastructureCost.select + "Input(" + value + ")";
                        eval(funName);
                    } catch (e) {
                        console.log("函数不存在!");
                    }
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        infrastructureMatchingCost: function () {//公共配套设施建设费 单价选择
            var key = build.config().inputConfig().infrastructureMatchingCost.select;
            $("." + build.config().frm + " ." + key).change(function () {
                var value = $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureMatchingCost.select).eq(1).val();
                if (build.isNumber(value)) {
                    try {
                        var funName = "build.inputFun." + build.config().inputConfig().infrastructureMatchingCost.select + "Input(" + value + ")";
                        eval(funName);
                    } catch (e) {
                        console.log("函数不存在!");
                    }
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        addedValueAdditionalTaxRate: function () {
            var key = build.config().inputConfig().addedValueAdditionalTaxRate.select;
            $("." + key).change(function () {
                var value = $("." + build.config().inputConfig().addedValueAdditionalTaxRate.select).eq(1).val();
                AssessCommon.getDataDicInfo(value, function (data) {
                    var percent = build.toPoint(data.name);
                    if (build.isNumber(percent)) {
                        try {
                            var funName = "build.inputFun." + build.config().inputConfig().addedValueAdditionalTaxRate.key + "Input(" + percent + ")";
                            eval(funName);
                        } catch (e) {
                            console.log("函数不存在!");
                        }
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            });
        },
        init: function () {
            build.select2Event.infrastructureCost();
            build.select2Event.infrastructureMatchingCost();
            build.select2Event.addedValueAdditionalTaxRate();
        }
    }
    /**
     * @author:  zch
     * 描述:定义input框初始化
     * @date:2018-08-10
     **/
    build.inputInit = function () {
        build.inputEvent();
        build.select2LoadData.init();
        //使数据校验生效
        $("#" + build.config().frm).validate();
    }

    /**
     * @author:  zch
     * 描述://加载所有select2数据
     * @date:
     **/
    build.select2LoadData = {
        /**
         * @author:  zch
         * 描述:增值及附加税率
         * @date:
         **/
        loadAddedValueAdditionalTaxRate: function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.build_addedvalueadditionaltaxrate, "", function (html, data) {
                $("." + build.config().frm + " ." + build.config().inputConfig().addedValueAdditionalTaxRate.select).html(html);
                $("." + build.config().frm + " ." + build.config().inputConfig().addedValueAdditionalTaxRate.select).select2();//加载样式
            })
        },
        /**
         * @author:  zch
         * 描述:获取基础设施费用列表和公共配套设施费用
         * @date:2018-08-10
         **/
        loadCostAndMatchingCost: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/marketCost/listCostAndMatchingCost",
                type: "get",
                data: {projectId:"${projectInfo.id}"},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var cost = result.data.DataInfrastructureCost;
                        var matchingCost = result.data.InfrastructureMatchingCost;
                        var option = "<option value=''>请选择</option>";
                        if (cost.length > 0) {
                            for (var i = 0; i < cost.length; i++) {
                                option += "<option value='" + cost[i].number + "'>" + cost[i].name + "</option>";
                            }
                            $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureCost.select).html(option);
                            $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureCost.select).select2();
                        }
                        if (matchingCost.length > 0) {
                            for (var i = 0; i < matchingCost.length; i++) {
                                option += "<option value='" + matchingCost[i].number + "'>" + matchingCost[i].name + "</option>";
                            }
                            $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureMatchingCost.select).html(option);
                            $("." + build.config().frm + " ." + build.config().inputConfig().infrastructureMatchingCost.select).select2();
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        init: function () {
            build.select2LoadData.loadCostAndMatchingCost();
            build.select2LoadData.loadAddedValueAdditionalTaxRate();
        }
    }

    /**
     * @author:  zch
     * 描述:建筑安装工程费
     * @date:2018-08-09
     **/
    build.constructionInstallationEngineeringFee = {
        event: function () {
            $("." + build.config().engineeringFee).show();
            $(function () {
                constructEngineeringObject.viewInit();
            });
        },
        getDataAndWriteHtml: function () {
            var data = constructEngineeringObject.getCalculatedResults();
            build.inputAlgorithmObject.jqueryInputGetAndSet("set", build.config().inputConfig().constructionInstallationEngineeringFee.key, data);
            build.inputFun.constructionInstallationEngineeringFeeInput(data);
            $("." + build.config().engineeringFee).hide();
        },
        close: function () {
            $("." + build.config().engineeringFee).hide();
        }
    }

    //用做模态框初始化数据的标识符
    var newRateModelFlag = true;

    /**
     * @author:  zch
     * 描述:成新率 (包括初始化,事件,算法,加载等)
     * @date:
     **/
    build.newRateModel = {
        setNewRateModelFlag: function (flag) {
            newRateModelFlag = flag;
        },
        getNewRateModelFlag: function () {
            return newRateModelFlag;
        },
        //获取最后计算的结果
        getResult: function () {
            var rr = $("." + build.config().newRate + " .integratednewRate").html();
            return rr;
        },
        //算法
        algorithm: {
            //综合成新率
            integratednewRate: function (obj) {
                var a = $("." + build.config().newRate + " " + "input[name='" + 'newRateA' + "']").val();//年限法 成新率
                var b = $("." + build.config().newRate + " " + "input[name='" + 'weightYear' + "']").val(); //年限法 权重
                var c = $("." + build.config().newRate + " " + "input[name='" + 'newRateG' + "']").val();//观察法 成新率
                var d = $("." + build.config().newRate + " " + "input[name='" + 'weightG' + "']").val();//观察法 权重
                console.log("test a:" + a + " ;b:" + b + " ;c:" + c + " ;d:" + d);
                var k = 0;
                //综合成新率 ==> (成新率*权重+残值率*权重)/2
                var k1 = build.mul(build.newRateModel.algorithm.specialTreatment(a), build.newRateModel.algorithm.specialTreatment(b));
                var k2 = build.mul(build.newRateModel.algorithm.specialTreatment(c), build.newRateModel.algorithm.specialTreatment(d));
                k = build.add(k1, k2);
                $("." + build.config().newRate + " .integratednewRate").html(k);
                build.newRateModel.select2Event.eventInit();
            },
            //年限法 成新率
            newRateA: function (obj) {
                var id = $("." + build.config().newRate + " .durableLife").eq(1).val();
                var a = 0;//经济耐用年限
                var b = 0;//残值率
                var c = $("." + build.config().newRate + " " + "input[name='" + 'useYear' + "']").val();//已经使用年限
                $.ajax({
                    url: "${pageContext.request.contextPath}/architecture/getByDataBuildingNewRateId",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            var data = null;
                            try {
                                data = result.data;
                            } catch (e) {
                            }
                            if (build.isNotNull(data)) {
                                a = data.durableLife;
                                b = data.residualValue;
                                b = build.toPoint(b);
                            }
                            c = build.newRateModel.algorithm.specialTreatment(c);
                            console.log("test a:" + a + " ;b:" + b + " ;c:" + c);
                            //成新率 = 1- (（1-残值率）*已使用年限/经济耐用年限 )
                            var d = 0;
                            d = 1 - (1 - b) * (c / a);
                            $("." + build.config().newRate + " " + "input[name='" + 'newRateA' + "']").val(d);
                            build.newRateModel.algorithm.integratednewRate(d);
                            build.newRateModel.select2Event.eventInit();
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
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
                if (build.isNotNull(obj)) {
                    return obj;
                }
                return 0;
            }
        },
        //经济耐用年限 select2事件
        select2Event: {
            //年限法 经济耐用年限 事件
            durableLife: function () {
                var key = build.config().newRate + " .durableLife";
                $("." + key).change(function () {
                    var id = $("." + key).eq(1).val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/architecture/getByDataBuildingNewRateId",
                        type: "get",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                var data = null;
                                try {
                                    data = result.data;
                                } catch (e) {
                                    console.log("说明没有数据!");
                                }
                                if (build.isNotNull(data)) {
                                    build.newRateModel.select2Event.residualValue(data.residualValue);
                                    build.newRateModel.select2Event.buildingStructure(data.buildingStructure);
                                    build.newRateModel.algorithm.newRateA(data);
                                } else {
                                    build.newRateModel.select2Event.residualValue(0);
                                    build.newRateModel.select2Event.buildingStructure(0);
                                    build.newRateModel.algorithm.newRateA(data);
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                });
            },
            //年限法 残值率
            residualValue: function (obj) {
                $("." + build.config().newRate + " .residualValue").val(obj);
            },
            //年限法 建筑结构
            buildingStructure: function (obj) {
                $("." + build.config().newRate + " .buildingStructure").val(obj);
            },
            //年限法 已使用年限 事件
            inputUseYear: function () {
                var key = "useYear";
                var input = $("." + build.config().newRate + " " + "input[name='" + key + "']");
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    if (build.isNumber(value)) {
                        build.newRateModel.algorithm.newRateA(value);
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            },
            //年限法 权重 事件
            inputWeightYear: function () {
                var key = "weightYear";
                var input = $("." + build.config().newRate + " " + "input[name='" + key + "']");
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    if (build.isNumber(value)) {
                        build.newRateModel.algorithm.integratednewRate(value);
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            }, // 观察法 成新率 事件
            inputNewRateG: function () {
                var key = "newRateG";
                var input = $("." + build.config().newRate + " " + "input[name='" + key + "']");
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    if (build.isNumber(value)) {
                        build.newRateModel.algorithm.integratednewRate(value);
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            },// 观察法 权重 事件
            inputWeightG: function () {
                var key = "weightG";
                var input = $("." + build.config().newRate + " " + "input[name='" + key + "']");
                input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                    var value = input.val();
                    if (build.isNumber(value)) {
                        build.newRateModel.algorithm.integratednewRate(value);
                    } else {
                        Alert("请输入合法数字!")
                    }
                });
            },
            //事件初始化
            eventInit: function () {
                build.newRateModel.select2Event.durableLife();
                build.newRateModel.select2Event.inputUseYear();
                build.newRateModel.select2Event.inputWeightYear();
                build.newRateModel.select2Event.inputNewRateG();
                build.newRateModel.select2Event.inputWeightG();
            }
        },
        show: function () {
            if (build.newRateModel.getNewRateModelFlag()) {
                build.newRateModel.selectInit();
                build.newRateModel.setNewRateModelFlag(false);
            }
            $("." + build.config().newRate).modal("show");
        },
        save: function () {
            if (!$("#" + build.config().newRate).valid()) {
                return false;
            }
            var kk = build.newRateModel.getResult();
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().newRate.key + "']").val(kk);
            build.inputFun.newRateInput(kk);
            $("." + build.config().newRate).modal("hide");
        },
        selectInit: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/marketCost/dataBuildingNewRateList",
                type: "get",
                data: {},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var optionA = "<option value=''>请选择</option>";
                        $.each(result.data, function (i, n) {
                            if (build.isNotNull(n.durableLife)) {
                                optionA += "<option value='" + n.id + "'>" + n.durableLife + "</option>";
                            }
                        })
                        $("." + build.config().newRate + " .durableLife").html(optionA);
                        $("." + build.config().newRate + " .durableLife").select2();
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }


    /**--------------------------------初始化------------------**/
    $(function () {
        build.inputInit();
        //模态框事件
        build.newRateModel.select2Event.eventInit();
    })

</script>

<!-- 通用面板模板 弃用 -->
<script type="text/html" id="generalPanelTemplate">
    <div class="panel panel-warning" style="width:300px;">
        <div class="panel-heading">
            <div>{title}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label" onclick="build.inputCancel(this);">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;X
                </label>
            </div>
        </div>
        <div class="panel-body btn-toolbar" role="toolbar">
            <div class="btn-group">
                <input class="form-control" name="{name}">
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default" data-name="{dataName}" onclick="build.inputSubmit(this)">
                    <i class="glyphicon glyphicon-ok"></i></button>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</script>


<div class="modal fade bs-example-modal-lg newRateModelA" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">成新率</h3>
            </div>
            <form class="form-horizontal" id="newRateModelA">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        年限法
                                    </label>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            经济耐用年限
                                        </label>
                                        <div class="col-sm-5">
                                            <select name="durableLife" required="required"
                                                    class="form-control search-select select2 durableLife">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            已使用年限
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="useYear"
                                                   data-rule-number='true' required="required"
                                                   placeholder="已使用年限">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            残值率
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control residualValue"
                                                   name="residualValue"
                                                   readonly="readonly"
                                                   placeholder="残值率">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            成新率
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="newRateA" readonly="readonly"
                                                   placeholder="成新率">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            建筑结构
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control buildingStructure" name="buildingStructure" readonly="readonly"
                                                   placeholder="建筑结构" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            权重
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="weightYear"
                                                   data-rule-number='true' required="required"
                                                   placeholder="权重">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        观察法
                                    </label>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            成新率
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="newRateG"
                                                   data-rule-number='true' required="required"
                                                   placeholder="成新率">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            权重
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="weightG"
                                                   data-rule-number='true' required="required"
                                                   placeholder="权重">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        <font color="red">综合成新率</font>
                                    </label>
                                    <div class="x-valid">
                                        <div class="col-sm-5">
                                            <label class="control-label integratednewRate">
                                                0
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="build.newRateModel.save();">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>