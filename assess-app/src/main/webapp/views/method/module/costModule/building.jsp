<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:03
  To change this template use File | Settings | File Templates.
  建筑物
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal frmBuild">
    <div class="form-group">
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

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费率" data-toggle="popover reconnaissanceDesignRote" class="form-control"
                       name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="建筑安装工程费" value="0" readonly="readonly" class="form-control"
                       name="constructionInstallationEngineeringFee"
                       onclick="build.constructionInstallationEngineeringFee.event();">
            </div>
        </div>
    </div>

    <div class="constructionInstallationEngineeringFeeB" style="display: none;">
        <jsp:include page="constructionInstallationEngineeringFeeB.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭" onclick="build.constructionInstallationEngineeringFee.close();">
                <input class="btn btn-success" value="确认" type="button" onclick="build.constructionInstallationEngineeringFee.getDataAndWriteHtml();">
            </div>
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureCostSelect2"
                        class="form-control search-select select2 infrastructureCostSelect2">
                </select>
            </div>
        </div>

        <label class="col-sm-1 control-label">
            基础设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="基础设施建设费" class="form-control" name="infrastructureCost" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            公共配套设施建设费 单价选择
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureMatchingCostSelect2"
                        class="form-control search-select select2 infrastructureMatchingCostSelect2">
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
                       placeholder="公共配套设施建设费" class="form-control" name="infrastructureMatchingCost" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期间单价
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发期间单价" class="form-control" name="devDuringPrice" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期间税费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="开发期间税费" class="form-control" name="devDuringPriceTax" value="0">
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
                       placeholder="其它工程费单价" class="form-control" name="otherEngineeringCostPrice" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            其它工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="其它工程费" class="form-control" name="otherEngineeringCost" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建设成本
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       placeholder="建设成本" class="form-control" name="constructionCost" value="0">
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
                       placeholder="管理费" class="form-control" name="managementExpense" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            管理费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="管理费率" class="form-control" name="managementExpenseRote" value="0">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            不可预见费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="不可预见费率" class="form-control" name="unforeseenExpensesRote" value="0">
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
                       placeholder="不可预见费" class="form-control" name="unforeseenExpenses" value="0">
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
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        var inputNameConfig = {
            reconnaissanceDesign: {key: "reconnaissanceDesign", describe: "勘察设计和前期工程费"},
            reconnaissanceDesignRote: {key: "reconnaissanceDesignRote", describe: "勘察设计和前期工程费率"},
            constructionInstallationEngineeringFee: {
                key: "constructionInstallationEngineeringFee",
                describe: "建筑安装工程费"
            },
            infrastructureCost: {
                key: "infrastructureCost",
                describe: "基础设施费用",
                select: "infrastructureCostSelect2"
            },
            infrastructureMatchingCost: {
                key: "infrastructureMatchingCost",
                describe: "公共配套设施费用",
                select: "infrastructureMatchingCostSelect2"
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
            arr.push([{
                key: inputNameConfig.reconnaissanceDesign.key,
                value: inputNameConfig.reconnaissanceDesign.value
            }]);
            arr.push([{
                key: inputNameConfig.reconnaissanceDesignRote.key,
                value: inputNameConfig.reconnaissanceDesignRote.value
            }]);
            arr.push([{
                key: inputNameConfig.constructionInstallationEngineeringFee.key,
                value: inputNameConfig.constructionInstallationEngineeringFee.value
            }]);
            arr.push([{key: inputNameConfig.infrastructureCost.key, value: inputNameConfig.infrastructureCost.value}]);
            arr.push([{
                key: inputNameConfig.infrastructureMatchingCost.key,
                value: inputNameConfig.infrastructureMatchingCost.value
            }]);
            arr.push([{key: inputNameConfig.devDuringPrice.key, value: inputNameConfig.devDuringPrice.value}]);
            arr.push([{key: inputNameConfig.devDuringPriceTax.key, value: inputNameConfig.devDuringPriceTax.value}]);
            arr.push([{
                key: inputNameConfig.otherEngineeringCost.key,
                value: inputNameConfig.otherEngineeringCost.value
            }]);
            arr.push([{
                key: inputNameConfig.otherEngineeringCostPrice.key,
                value: inputNameConfig.otherEngineeringCostPrice.value
            }]);
            arr.push([{key: inputNameConfig.constructionCost.key, value: inputNameConfig.constructionCost.value}]);
            arr.push([{key: inputNameConfig.managementExpense.key, value: inputNameConfig.managementExpense.value}]);
            arr.push([{
                key: inputNameConfig.managementExpenseRote.key,
                value: inputNameConfig.managementExpenseRote.value
            }]);
            arr.push([{key: inputNameConfig.unforeseenExpenses.key, value: inputNameConfig.unforeseenExpenses.value}]);
            arr.push([{
                key: inputNameConfig.unforeseenExpensesRote.key,
                value: inputNameConfig.unforeseenExpensesRote.value
            }]);
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
     * 描述: 成本法中所有的计算都会归纳到这进行运算算法
     * @date:2018-08-10
     **/
    build.inputAlgorithm = function (dataName, dataValue) {
        var object = new Object();
        object.specialTreatment = function (obj) {
            if (object.isNotNull(obj)) {
                return obj;
            }
            return 0;
        }
        object.isNotNull = function (obj) {
            if (obj == 0) {
                return true;
            }
            if (obj == '') {
                return true;
            }
            if (obj) {
                return true;
            }
            return false;
        }
        //建设成本
        object.constructionCostFun = function (obj) {
            /*建设成本 = 前期工程费+安装工程费+基础设施费+公共设施费+开发期间税费+其它工程费*/
            var c = 0;
            if (build.isNotNull(obj)){
                c = build.add(c,object.specialTreatment($("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().reconnaissanceDesign.key + "']").val()));//前期工程费
                c = build.add(c,object.specialTreatment($("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionInstallationEngineeringFee.key + "']").val()));//安装工程费
                c = build.add(c,object.specialTreatment($("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().infrastructureCost.key + "']").val()));//基础设施建设费
                c = build.add(c,object.specialTreatment($("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().infrastructureMatchingCost.key + "']").val()));//公共配套设施建设费
                c = build.add(c,object.specialTreatment($("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().devDuringPriceTax.key + "']").val()));//开发期间税费
                c = build.add(c,object.specialTreatment($("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().otherEngineeringCost.key + "']").val()));//其它工程费
            }
            //赋值
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionCost.key + "']").val(c);
        };
        var b = dataValue;
        //勘察设计和前期工程费率
        if (dataName == build.config().inputConfig().reconnaissanceDesignRote.key) {
            var a = $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionInstallationEngineeringFee.key + "']").val();//建筑安装工程费
            var c = build.mul(object.specialTreatment(a),object.specialTreatment(b));//勘察设计和前期工程费 = 建筑安装工程费*费率
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().reconnaissanceDesign.key + "']").val(c);
            object.constructionCostFun(c);
        }
        //基础设施建设费
        if (dataName == build.config().inputConfig().infrastructureCost.select) {
            var a = build.config().hiddenData().area //估价对象面积
            var c = build.mul(object.specialTreatment(a),object.specialTreatment(b));//基础设施建设费 = 基础设施建设费单价*估价对象面积
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().infrastructureCost.key + "']").val(c);
            object.constructionCostFun(c);
        }
        //公共配套设施建设费
        if (dataName == build.config().inputConfig().infrastructureMatchingCost.select) {
            var a = build.config().hiddenData().area //估价对象面积
            var c = build.mul(object.specialTreatment(a),object.specialTreatment(b));//公共配套设施建设费 = 公共配套设施建设费单价*估价对象面积
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().infrastructureMatchingCost.key + "']").val(c);
            object.constructionCostFun(c);
        }
        //开发期间税费
        if (dataName == build.config().inputConfig().devDuringPrice.key) {
            var a = build.config().hiddenData().area //估价对象面积
            var c = build.mul(object.specialTreatment(a),object.specialTreatment(b));//开发期间税费 = 估价对象面积*开发期间单价
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().devDuringPriceTax.key + "']").val(c);
            object.constructionCostFun(c);
        }
        //其它工程费
        if (dataName == build.config().inputConfig().otherEngineeringCostPrice.key) {
            var a = build.config().hiddenData().area //估价对象面积
            var c = build.mul(object.specialTreatment(a),object.specialTreatment(b));//其它工程费 = 估价对象面积*其它工程费单价
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().otherEngineeringCost.key + "']").val(c);
            object.constructionCostFun(c);
        }
        //管理费
        if (dataName == build.config().inputConfig().managementExpenseRote.key) {
            var a = $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionCost.key + "']").val();//建设成本
            var c = build.mul(object.specialTreatment(a),object.specialTreatment(b));//管理费 = 管理费率*建设成本
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().managementExpense.key + "']").val(c);
        }
        //不可预见费
        if (dataName == build.config().inputConfig().unforeseenExpensesRote.key) {
            var a = $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionCost.key + "']").val();//建设成本
            var d = $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().managementExpense.key + "']").val();//管理费金额
            //不可预见费 = （建设成本+管理费金额）*不可预见费率
            var c = build.add(object.specialTreatment(a),object.specialTreatment(d));
            c = build.mul(object.specialTreatment(c),object.specialTreatment(b));
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().unforeseenExpenses.key + "']").val(c);
        }
        $(function () {
            build.inputEvent();
        });
    }
    /**
     * @author:  zch
     * 描述:input框事件
     * @date:2018-08-10
     **/
    build.inputEvent = function () {
        var arr = build.config().inputName();
        $.each(arr, function (i, n) {
            var key = n[0].key;
            var input = $("." + build.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                if (build.isNumber(value)) {
                    build.inputAlgorithm(key, input.val());
                } else {
                    Alert("请输入合法数字!")
                }
            });
        })
    };
    /**
     * @author:  zch
     * 描述:input select2 事件
     * @date:2018-08-13
     **/
    build.select2Event = {
        infrastructureCost: function () {//基础设施建设费 单价选择
            var key = build.config().inputConfig().infrastructureCost.select;
            $("." + key).change(function () {
                var value = $("." + build.config().inputConfig().infrastructureCost.select).eq(1).val();
                if (build.isNumber(value)) {
                    build.inputAlgorithm(key, value);
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        infrastructureMatchingCost: function () {//公共配套设施建设费 单价选择
            var key = build.config().inputConfig().infrastructureMatchingCost.select;
            $("." + key).change(function () {
                var value = $("." + build.config().inputConfig().infrastructureMatchingCost.select).eq(1).val();
                if (build.isNumber(value)) {
                    build.inputAlgorithm(key, value);
                } else {
                    Alert("请输入合法数字!")
                }
            });
        },
        init: function () {
            build.select2Event.infrastructureCost();
            build.select2Event.infrastructureMatchingCost();
        }
    }
    /**
     * @author:  zch
     * 描述:定义input框初始化
     * @date:2018-08-10
     **/
    build.inputInit = function () {
        build.inputEvent();
        build.loadCostAndMatchingCost();
    }
    /**
     * @author:  zch
     * 描述:获取基础设施费用列表和公共配套设施费用
     * @date:2018-08-10
     **/
    build.loadCostAndMatchingCost = function () {
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
    }



    /**
     * @author:  zch
     * 描述:建筑安装工程费
     * @date:2018-08-09
     **/
    build.constructionInstallationEngineeringFee = {
        event:function () {
            $("." + build.config().engineeringFee).show();
            $(function () {
                constructEngineeringObject.viewInit();
            });
        },
        getDataAndWriteHtml:function () {
            var data = constructEngineeringObject.getCalculatedResults();
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionInstallationEngineeringFee.key + "']").val(data);
            $("." + build.config().engineeringFee).hide();
        },
        close:function () {
            $("." + build.config().engineeringFee).hide();
        }
    }
    /**--------------------------------初始化------------------**/
    $(function () {
        build.inputInit();
        build.select2Event.init();
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


<%--<div class="modal fade bs-example-modal-lg constructionInstallationEngineeringFeeB" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">--%>
<%--<div class="modal-dialog modal-lg">--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span--%>
<%--aria-hidden="true">&times;</span></button>--%>
<%--<h3 class="modal-title">建安工程费</h3>--%>
<%--</div>--%>
<%--<jsp:include page="constructionInstallationEngineeringFeeB.jsp"></jsp:include>--%>
<%--<form class="form-horizontal">--%>

<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="easyui-window constructionInstallationEngineeringFeeB"  data-options="iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">--%>
<%--<jsp:include page="constructionInstallationEngineeringFeeB.jsp"></jsp:include>--%>
<%--</div>--%>

