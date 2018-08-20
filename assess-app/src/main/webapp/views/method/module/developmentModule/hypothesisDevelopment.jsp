<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="frmDevelopment form-horizontal" id="frmDevelopment">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建筑类别</label>
            <div class="col-md-2 col-sm-2">
                <select name="hypothesisDevelopmentSelect2"
                        class="form-control search-select select2 hypothesisDevelopmentSelect2">
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建筑类型</label>
            <div class="col-md-2 col-sm-2">
                <select name="hypothesisDevelopmentSelect2Type"
                        class="form-control search-select select2 hypothesisDevelopmentSelect2Type">
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预计销售建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预计销售建筑面积" class="form-control" name="estimateBuildSaleArea" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预计销售合计</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预计销售合计" class="form-control" name="estimateSaleTotal" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预计销售可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预计销售可售面积" class="form-control" name="estimateMaySaleArea" readonly="readonly">
            </div>
        </div>
        <input type="hidden"
               placeholder="不可销售面积" name="mayNotSaleArea" class="mayNotSaleArea" value="22.1">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">总建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="总建筑面积" class="form-control" name="totalBuildArea" readonly="readonly">
            </div>
        </div>
    </div>
    <!-- 引入类型form表单 -->
    <div class="baseFrmDevelopment">
        <jsp:include page="fromDevelopment.jsp"></jsp:include>
    </div>

</form>

<script>
    var hypothesisDevelopment = new Object();
    hypothesisDevelopment.setFlagK = function (obj) {
        this.flagK = obj;
    }
    hypothesisDevelopment.getFlagK = function () {
        this.flagK = true;
        return this.flagK;
    }
    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:除法
     * @date:2018-08-08
     **/
    hypothesisDevelopment.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        return result;
    }
    //判断是否为数字
    hypothesisDevelopment.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    //百分数转小数
    hypothesisDevelopment.toPoint = function (obj) {
        return AssessCommon.toPoint(obj);
    }
    //小数转百分数
    hypothesisDevelopment.toPercent = function (obj) {
        return AssessCommon.toPercent(obj);
    }
    hypothesisDevelopment.isNotNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    hypothesisDevelopment.formType = {
        garage: {
            big: "bigGarage",
            small: "smallGarage",
            garage: "garage",
            describe: "车库类型"
        },
        base: {
            unitPrice: "UnitPrice", /* 单位售价 */
            totalPrice: "TotalPrice", /* 合价 */
            maySaleArea: "MaySaleArea", /* 可售面积 */
            buildArea: "BuildArea", /* 建筑面积 */
            describe: "类型基础配置"
        }
    }
    hypothesisDevelopment.config = function () {
        var config = {};
        config.frm = "frmDevelopment";//表单id
        config.baseFrm = "baseFrmDevelopment";
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        config.inputConfig = function () {
            return {
                constructionInstallationEngineeringFee: {
                    key: "constructionInstallationEngineeringFee",
                    describe: "建筑安装工程费",
                    value: "",
                    select: ""
                },
                hypothesisDevelopmentSelect2: {
                    key: "hypothesisDevelopmentSelect2",
                    describe: "建筑类别",
                    value: "",
                    select: "hypothesisDevelopmentSelect2"
                },
                hypothesisDevelopmentSelect2Type: {
                    key: "hypothesisDevelopmentSelect2Type",
                    describe: "建筑类型",
                    value: "",
                    select: "hypothesisDevelopmentSelect2Type"
                },
                estimateSaleTotal: {key: "estimateSaleTotal", describe: "预计销售合计"},
                estimateMaySaleArea: {key: "estimateMaySaleArea", describe: "预计销售可售面积"},
                estimateBuildSaleArea: {key: "estimateBuildSaleArea", describe: "预计销售建筑面积"},
                totalBuildArea: {key: "totalBuildArea", describe: "总建筑面积"},
                mayNotSaleArea: {key: "mayNotSaleArea", describe: "不可销售面积"},
                constructionCycle: {key: "constructionCycle", describe: "建设周期", value: "", select: ""},
                developedTime: {key: "developedTime", describe: "开发周期", value: "", select: ""},

                smallGarageUnitPrice: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.unitPrice},
                smallGarageTotalPrice: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.totalPrice},
                smallGarageMaySaleArea: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.maySaleArea},
                smallGarageBuildArea: {key: hypothesisDevelopment.formType.garage.small + hypothesisDevelopment.formType.base.buildArea},
                bigGarageUnitPrice: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.unitPrice},
                bigGarageTotalPrice: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.totalPrice},
                bigGarageMaySaleArea: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.maySaleArea},
                bigGarageBuildArea: {key: hypothesisDevelopment.formType.garage.big + hypothesisDevelopment.formType.base.buildArea},


            };
        };
        config.inputName = function () {
            var arr = new Array();
            //遍历对象
            $.each(hypothesisDevelopment.config().inputConfig(), function (i, n) {
                arr.push(n);
            })
            return arr;
        };
        return config;
    };
    hypothesisDevelopment.inputFun = {
        workInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.workFun();
        }
    }
    hypothesisDevelopment.inputAlgorithmObject = {
        //大车库 算法
        bigGarageFun: function () {
            var a, b, c, d;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageBuildArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageMaySaleArea.key, null);
            c = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageUnitPrice.key, null);
            d = hypothesisDevelopment.mul(b,c);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().bigGarageTotalPrice.key, d);
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //小车库 算法
        smallGarageFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().smallGarageMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().smallGarageUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b,a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().smallGarageTotalPrice.key,c);
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        baseFormFun: function () {
            var key = null, data = null;
            var estimateBuildSaleArea = 0, estimateMaySaleArea = 0, estimateSaleTotal = 0, mayNotSaleArea = 0,totalBuildArea = 0;
            key = "." + hypothesisDevelopment.config().frm;
            key += " ." + hypothesisDevelopment.config().baseFrm;
            data = $(key + " :input");
            $.each(data, function (i, n) {
                var name = $(n).attr("name");
                var temp = 0;
                if (hypothesisDevelopment.indexOfUtils(name, hypothesisDevelopment.formType.base.maySaleArea)) {//可售面积
                    temp = $(n).val();
                    temp = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(temp);
                    estimateMaySaleArea = hypothesisDevelopment.add(estimateMaySaleArea, temp);
                }
                if (hypothesisDevelopment.indexOfUtils(name, hypothesisDevelopment.formType.base.buildArea)) {//建筑面积
                    temp = $(n).val();
                    temp = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(temp);
                    estimateBuildSaleArea = hypothesisDevelopment.add(estimateBuildSaleArea, temp);
                }
                if (hypothesisDevelopment.indexOfUtils(name, hypothesisDevelopment.formType.base.totalPrice)) {//合价
                    temp = $(n).val();
                    temp = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(temp);
                    estimateSaleTotal = hypothesisDevelopment.add(estimateSaleTotal, temp);
                }
            });
            /*预计销售建筑面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().estimateBuildSaleArea.key, estimateBuildSaleArea);
            /*预计销售可售面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().estimateMaySaleArea.key, estimateMaySaleArea);
            /*预计销售合计*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().estimateSaleTotal.key, estimateSaleTotal);
            //总建筑面积 = 预计销售建筑面积+不可销售面积
            mayNotSaleArea = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().mayNotSaleArea.key, null);
            totalBuildArea = hypothesisDevelopment.add(mayNotSaleArea,estimateBuildSaleArea);
            /*总建筑面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().totalBuildArea.key, totalBuildArea);
        },
        //子表单 办公建筑 算法
        workFun: function () {
            var workBuildArea = null;
            workBuildArea = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().workBuildArea, null);
        },
        jqueryInputGetAndSet: function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + name + "']").val();
                text = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + name + "']").val(data);
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
            if (hypothesisDevelopment.inputAlgorithmObject.isNotNull(obj)) {
                return obj;
            }
            return 0;
        }
    };
    /**
     * @author:  zch
     * 描述:判断某个字符串中是否包含某个字符串 (workdddg,WORK) return true
     * @date:
     **/
    hypothesisDevelopment.indexOfUtils = function (s, str) {
        if (s.indexOf(str) >= 0) {
            return true;
        }
        s = s.toUpperCase();
        str = str.toUpperCase();
        if (s.indexOf(str) >= 0) {
            return true;
        }
        return false;
    };
    hypothesisDevelopment.inputForm = function (key, value) {
        console.log("formType 进入@");
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.garage)) {//属于车库
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.big)) {//大车库
                hypothesisDevelopment.inputAlgorithmObject.bigGarageFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.small)) {//小车库
                hypothesisDevelopment.inputAlgorithmObject.smallGarageFun();
            }
        }
    };
    hypothesisDevelopment.inputEvent = function () {
        var arr = hypothesisDevelopment.config().inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                if (hypothesisDevelopment.isNumber(value)) {
                    try {
                        var funName = "hypothesisDevelopment.inputFun." + key + "Input(" + input.val() + ")";
                        eval(funName);
                    } catch (e) {
                        console.log("没有相关定义的函数或者是属于子表单");
                        hypothesisDevelopment.inputForm(key, value);
                    }
                } else {
                    Alert("请输入合法数字!")
                }
            });
        })
        hypothesisDevelopment.selectEvent.monitor.monitor();
    }

    hypothesisDevelopment.selectEvent = {
        load: {
            hypothesisDevelopmentSelect2: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.mdHypothesisDevelopment, "", function (html, data) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(data, function (i, item) {
                        retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                    });
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select).html(retHtml);
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select).select2();
                });
            },
            load: function () {
                hypothesisDevelopment.selectEvent.load.hypothesisDevelopmentSelect2();
            }
        },
        //监听 change 事件
        monitor: {
            hypothesisDevelopmentSelect2: function () {
                var key = hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select;
                $("." + key).change(function () {
                    var value = $("." + key).eq(1).val();
                    AssessCommon.loadDataDicByPid(value, "", function (html, data) {
                        $("." + key + "Type").prev().remove();
                        $("." + key + "Type").empty();
                        if (hypothesisDevelopment.isNotNull(data)) {
                            $("." + key + "Type").html(html);
                            $("." + key + "Type").select2();
                        }
                    });
                });
            },
            hypothesisDevelopmentSelect2Type: function () {
                var key = hypothesisDevelopment.config().frm + " .";
                key += hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select;
                key += "Type";
                $("." + key).change(function () {
                    var value = $("." + key).eq(1).val();
                    AssessCommon.getDataDicInfo(value, function (data) {
                        if (hypothesisDevelopment.isNotNull(data)) {
                            $("." + hypothesisDevelopment.config().frm + " ." + data.fieldName).show();
                        }
                    });
                });

            },
            monitor: function () {
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2();
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2Type();
            }
        }
    };
    hypothesisDevelopment.eventInit = function () {
        hypothesisDevelopment.selectEvent.load.load();
        hypothesisDevelopment.inputEvent();
    };

    $(function () {
        hypothesisDevelopment.eventInit();
    });
</script>
