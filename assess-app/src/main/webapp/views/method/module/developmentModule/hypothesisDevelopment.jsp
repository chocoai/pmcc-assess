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
        build: {
            cycle: "cycleBuild",
            build: "build",
            describe: "建设周期"
        },
        business: {
            operate: "operateBusiness",
            strategy: "strategyBusiness",
            business: "business",
            describe: "商业"
        },
        residence: {
            residence: "residence",
            ordinary: "ordinaryResidence",
            apartment: "apartmentResidence",
            villa: "villaResidence",
            describe: "住宅"
        },
        undergroundBusiness: {
            shop: "undergroundBusinessShop",
            undergroundBusiness:"undergroundBusiness",
            describe: "地下商业"
        },
        garage: {
            big: "bigGarage",
            small: "smallGarage",
            garage: "garage",
            describe: "车库类型"
        },
        work: {
            close: "closeWork",
            open: "openWork",
            scenery: "sceneryWork",
            unit: "unitWork",
            work: "work",
            describe: "办公建筑类型"
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

                strategyBusinessUnitPrice: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.unitPrice},
                strategyBusinessTotalPrice: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.totalPrice},
                strategyBusinessMaySaleArea: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.maySaleArea},
                strategyBusinessBuildArea: {key: hypothesisDevelopment.formType.business.strategy + hypothesisDevelopment.formType.base.buildArea},
                operateBusinessUnitPrice: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.unitPrice},
                operateBusinessTotalPrice: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.totalPrice},
                operateBusinessMaySaleArea: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.maySaleArea},
                operateBusinessBuildArea: {key: hypothesisDevelopment.formType.business.operate + hypothesisDevelopment.formType.base.buildArea},

                undergroundBusinessShopUnitPrice: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.unitPrice},
                undergroundBusinessShopTotalPrice: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.totalPrice},
                undergroundBusinessShopMaySaleArea: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.maySaleArea},
                undergroundBusinessShopBuildArea: {key: hypothesisDevelopment.formType.undergroundBusiness.shop + hypothesisDevelopment.formType.base.buildArea},


                villaResidenceUnitPrice: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.unitPrice},
                villaResidenceTotalPrice: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.totalPrice},
                villaResidenceMaySaleArea: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.maySaleArea},
                villaResidenceBuildArea: {key: hypothesisDevelopment.formType.residence.villa + hypothesisDevelopment.formType.base.buildArea},
                apartmentResidenceUnitPrice: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.unitPrice},
                apartmentResidenceTotalPrice: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.totalPrice},
                apartmentResidenceMaySaleArea: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.maySaleArea},
                apartmentResidenceBuildArea: {key: hypothesisDevelopment.formType.residence.apartment + hypothesisDevelopment.formType.base.buildArea},
                ordinaryResidenceUnitPrice: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.unitPrice},
                ordinaryResidenceTotalPrice: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.totalPrice},
                ordinaryResidenceMaySaleArea: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.maySaleArea},
                ordinaryResidenceBuildArea: {key: hypothesisDevelopment.formType.residence.ordinary + hypothesisDevelopment.formType.base.buildArea},

                closeWorkUnitPrice: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.unitPrice},
                closeWorkTotalPrice: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.totalPrice},
                closeWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.maySaleArea},
                closeWorkBuildArea: {key: hypothesisDevelopment.formType.work.close + hypothesisDevelopment.formType.base.buildArea},
                openWorkUnitPrice: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.unitPrice},
                openWorkTotalPrice: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.totalPrice},
                openWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.maySaleArea},
                openWorkBuildArea: {key: hypothesisDevelopment.formType.work.open + hypothesisDevelopment.formType.base.buildArea},
                unitWorkUnitPrice: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.unitPrice},
                unitWorkTotalPrice: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.totalPrice},
                unitWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.maySaleArea},
                unitWorkBuildArea: {key: hypothesisDevelopment.formType.work.unit + hypothesisDevelopment.formType.base.buildArea},
                sceneryWorkUnitPrice: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.unitPrice},
                sceneryWorkTotalPrice: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.totalPrice},
                sceneryWorkMaySaleArea: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.maySaleArea},
                sceneryWorkBuildArea: {key: hypothesisDevelopment.formType.work.scenery + hypothesisDevelopment.formType.base.buildArea},

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
    hypothesisDevelopment.inputFun = {}
    hypothesisDevelopment.inputAlgorithmObject = {
        //地下商业
        undergroundBusinessShopFun: function () {
            var a, b, c;
            console.log("undergroundBusinessShopFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //闭式员工办公 算法
        closeWorkFun: function () {
            var a, b, c;
            console.log("closeWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        unitWorkFun:function () {
            var a, b, c;
            console.log("unitWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        openWorkFun:function () {
            var a, b, c;
            console.log("openWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        sceneryWorkFun:function () {
            var a, b, c;
            console.log("sceneryWorkFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        strategyBusinessFun:function () {
            var a, b, c;
            console.log("strategyBusinessFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        operateBusinessFun:function () {
            var a, b, c;
            console.log("operateBusinessFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        ordinaryResidenceFun:function () {
            var a, b, c;
            console.log("ordinaryResidenceFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        apartmentResidenceFun:function () {
            var a, b, c;
            console.log("apartmentResidenceFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        villaResidenceFun:function () {
            var a, b, c;
            console.log("villaResidenceFun()");
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //大车库 算法
        bigGarageFun: function () {
            var a, b, c, d;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageBuildArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageMaySaleArea.key, null);
            c = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().bigGarageUnitPrice.key, null);
            d = hypothesisDevelopment.mul(b, c);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().bigGarageTotalPrice.key, d);
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        //小车库 算法
        smallGarageFun: function () {
            var a, b, c;
            a = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().smallGarageMaySaleArea.key, null);
            b = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get", hypothesisDevelopment.config().inputConfig().smallGarageUnitPrice.key, null);
            c = hypothesisDevelopment.mul(b, a);
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().smallGarageTotalPrice.key, c);
            hypothesisDevelopment.inputAlgorithmObject.baseFormFun();
        },
        baseFormFun: function () {
            var key = null, data = null;
            var estimateBuildSaleArea = 0, estimateMaySaleArea = 0, estimateSaleTotal = 0, mayNotSaleArea = 0,
                totalBuildArea = 0;
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
            totalBuildArea = hypothesisDevelopment.add(mayNotSaleArea, estimateBuildSaleArea);
            /*总建筑面积*/
            hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("set", hypothesisDevelopment.config().inputConfig().totalBuildArea.key, totalBuildArea);
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
        console.log("formType 进入@" + key);
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.garage)) {//属于车库
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.big)) {//大车库
                hypothesisDevelopment.inputAlgorithmObject.bigGarageFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.garage.small)) {//小车库
                hypothesisDevelopment.inputAlgorithmObject.smallGarageFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.work)) {//属于办公
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.close)) {//闭式办公
                hypothesisDevelopment.inputAlgorithmObject.closeWorkFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.scenery)) {//
                hypothesisDevelopment.inputAlgorithmObject.sceneryWorkFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.unit)) {//
                hypothesisDevelopment.inputAlgorithmObject.unitWorkFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.work.open)) {//
                hypothesisDevelopment.inputAlgorithmObject.openWorkFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.business.business)) {//属于商业
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.business.strategy)) {//
                hypothesisDevelopment.inputAlgorithmObject.strategyBusinessFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.business.operate)) {//
                hypothesisDevelopment.inputAlgorithmObject.operateBusinessFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.undergroundBusiness.undergroundBusiness)) {//属于地下商业
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.undergroundBusiness.shop)) {//地下商业购物广场
                hypothesisDevelopment.inputAlgorithmObject.undergroundBusinessShopFun();
            }
        }
        if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.residence)) {//属于住宅
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.ordinary)) {//
                hypothesisDevelopment.inputAlgorithmObject.ordinaryResidenceFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.apartment)) {//
                hypothesisDevelopment.inputAlgorithmObject.apartmentResidenceFun();
            }
            if (hypothesisDevelopment.indexOfUtils(key, hypothesisDevelopment.formType.residence.villa)) {//
                hypothesisDevelopment.inputAlgorithmObject.villaResidenceFun();
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
                    var pid = $("." + key).eq(1).val();
                    AssessCommon.loadDataDicByPid(pid, "", function (html, data) {
                        $("." + key + "Type").prev().remove();
                        $("." + key + "Type").empty();
                        if (hypothesisDevelopment.isNotNull(data)) {
                            $("." + key + "Type").html(html);
                            $("." + key + "Type").select2();
                        } else {
                            AssessCommon.getDataDicInfo(pid, function (data) {
                                if (hypothesisDevelopment.isNotNull(data)) {
                                    if (data.fieldName == 'build') {
                                        $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.formType.build.cycle).toggle();
                                    }
                                }
                            });
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
                            $("." + hypothesisDevelopment.config().frm + " ." + data.fieldName).toggle();
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
