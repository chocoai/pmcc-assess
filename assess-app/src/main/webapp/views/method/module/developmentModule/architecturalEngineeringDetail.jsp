<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="frmArchitecturalEngineering form-horizontal" id="frmArchitecturalEngineering">
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
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建设周期</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" required="required"
                       placeholder="建设周期" class="form-control" name="">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">已开发时间</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" required="required"
                       placeholder="已开发时间" class="form-control" name="">
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
    <div class="baseFrmArchitecturalEngineering">
        <jsp:include page="fromDevelopmentDetail.jsp"></jsp:include>
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
                       placeholder="勘察设计和前期工程费率" class="form-control" data-rule-number='true' required="required"
                       name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly"
                       onclick="architecturalObj.constructionInstallationEngineeringFee.event();"
                       placeholder="建筑安装工程费" class="form-control" name="constructionInstallationEngineeringFee">
            </div>
        </div>
    </div>

    <div class="architecturalEngineeringModel" style="display: none;">
        <jsp:include page="../architecturalEngineering/architecturalEngineeringM.jsp"></jsp:include>
        <div class="form-group">
            <div class="col-sm-6">
            </div>
            <div class="col-sm-6">
                <input class="btn btn btn-primary" type="button" value="关闭"
                       onclick="architecturalObj.constructionInstallationEngineeringFee.close();">
                <input class="btn btn-success" value="确认" type="button"
                       onclick="architecturalObj.constructionInstallationEngineeringFee.getDataAndWriteHtml();">
            </div>
        </div>
    </div>

</form>

<script>
    var ArchitecturalEngineeringObj = function () {

    };
    /**
     * @author:  zch
     * 描述:加法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:除法
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        return result;
    }
    /**
     * @author:  zch
     * 描述:判断是否是数字
     * @date:
     **/
    ArchitecturalEngineeringObj.prototype.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    //百分数转小数
    ArchitecturalEngineeringObj.prototype.toPoint = function (obj) {
        var str = obj.replace("%", "");
        str = str / 100;
        return str;
    }
    //小数转百分数
    ArchitecturalEngineeringObj.prototype.toPercent = function (point) {
        var str = Number(point * 100).toFixed(1);
        str += "%";
        return str;
    }
    ArchitecturalEngineeringObj.prototype.isNotNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }

    ArchitecturalEngineeringObj.prototype.formType = {
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
            undergroundBusiness: "undergroundBusiness",
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
    };
    ArchitecturalEngineeringObj.prototype.config = {
        frm: function () {
            return "frmArchitecturalEngineering";//表单id
        },
        baseFrm: function () {
            return "baseFrmArchitecturalEngineering";
        },
        architecturalEngineeringModel: function () {
            return "architecturalEngineeringModel";
        },
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        inputConfig: function () {
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

                smallGarageUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                smallGarageTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                smallGarageMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                smallGarageBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.small + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                bigGarageUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                bigGarageTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                bigGarageMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                bigGarageBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.garage.big + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                strategyBusinessUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                strategyBusinessTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                strategyBusinessMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                strategyBusinessBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.strategy + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                operateBusinessUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                operateBusinessTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                operateBusinessMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                operateBusinessBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.business.operate + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                undergroundBusinessShopUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                undergroundBusinessShopTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                undergroundBusinessShopMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                undergroundBusinessShopBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.undergroundBusiness.shop + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},


                villaResidenceUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                villaResidenceTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                villaResidenceMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                villaResidenceBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.villa + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                apartmentResidenceUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                apartmentResidenceTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                apartmentResidenceMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                apartmentResidenceBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.apartment + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                ordinaryResidenceUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                ordinaryResidenceTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                ordinaryResidenceMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                ordinaryResidenceBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.residence.ordinary + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                closeWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                closeWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                closeWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                closeWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.close + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                openWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                openWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                openWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                openWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.open + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                unitWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                unitWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                unitWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                unitWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.unit + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},
                sceneryWorkUnitPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.unitPrice},
                sceneryWorkTotalPrice: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.totalPrice},
                sceneryWorkMaySaleArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.maySaleArea},
                sceneryWorkBuildArea: {key: ArchitecturalEngineeringObj.prototype.formType.work.scenery + ArchitecturalEngineeringObj.prototype.formType.base.buildArea},

                reconnaissanceDesignRote: {key: "reconnaissanceDesignRote", describe: "勘察设计和前期工程费率"},
                reconnaissanceDesign: {key: "reconnaissanceDesign", describe: "勘察设计和前期工程费"},
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
                developmentBuildArea: {key: "developmentBuildArea", describe: "开发建筑面积", value: "", select: ""},
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
                addedValueAdditionalTaxRate: {
                    key: "addedValueAdditionalTaxRate",
                    describe: "增值及附加税率",
                    select: "addedValueAdditionalTaxRateSelect"
                },
                valueAddedAdditionalTaxes: {key: "valueAddedAdditionalTaxes", describe: "增值及附加税金"},
                interestPeriod: {key: "interestPeriod", describe: "计息周期"},
                interestRateOnInvestment: {key: "interestRateOnInvestment", describe: "投资计息利率"},
                interestRateOnInvestmentCorrect: {key: "interestRateOnInvestmentCorrect", describe: "投资计息税率修正"},
                interestInInvestment: {key: "interestInInvestment", describe: "投资利息"},
                investmentProfit: {key: "investmentProfit", describe: "投资利润"},
            };
        },
        inputName: function () {
            var arr = new Array();
            //遍历对象
            $.each(ArchitecturalEngineeringObj.prototype.config.inputConfig(), function (i, n) {
                arr.push(n);
            })
            return arr;
        }
    };


    var architecturalObj = new ArchitecturalEngineeringObj();

    architecturalObj.inputFun = {
        //建筑安装工程费
        constructionInstallationEngineeringFeeInput:function () {
            architecturalObj.inputAlgorithmObject.reconnaissanceDesignFun();
        },
        //勘察设计和前期工程费率
        reconnaissanceDesignRoteInput:function () {
            architecturalObj.inputAlgorithmObject.reconnaissanceDesignFun();
        }
    };

    architecturalObj.inputAlgorithmObject = {
        //勘察设计和前期工程费
        reconnaissanceDesignFun:function () {
            var a = 0, b = 0, c = 0;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().reconnaissanceDesignRote.key, null);//勘察设计和前期工程费率
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().constructionInstallationEngineeringFee.key, null);//建筑安装工程费
            c = architecturalObj.mul(a,b);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().reconnaissanceDesign.key,c);//勘察设计和前期工程费
        },
        //大车库 算法
        bigGarageFun: function () {
            var a, b, c, d;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().bigGarageBuildArea.key, null);
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().bigGarageMaySaleArea.key, null);
            c = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().bigGarageUnitPrice.key, null);
            d = architecturalObj.mul(b, c);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().bigGarageTotalPrice.key, d);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        //小车库 算法
        smallGarageFun: function () {
            var a, b, c;
            a = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().smallGarageMaySaleArea.key, null);
            b = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().smallGarageUnitPrice.key, null);
            c = architecturalObj.mul(b, a);
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().smallGarageTotalPrice.key, c);
            architecturalObj.inputAlgorithmObject.baseFormFun();
        },
        baseFormFun: function () {
            var key = null, data = null;
            var estimateBuildSaleArea = 0, estimateMaySaleArea = 0, estimateSaleTotal = 0, mayNotSaleArea = 0,
                totalBuildArea = 0;
            key = "." + architecturalObj.config.frm();
            key += " ." + architecturalObj.config.baseFrm();
            data = $(key + " :input");
            $.each(data, function (i, n) {
                var name = $(n).attr("name");
                var temp = 0;
                if (architecturalObj.indexOfUtils(name, architecturalObj.formType.base.maySaleArea)) {//可售面积
                    temp = $(n).val();
                    temp = architecturalObj.inputAlgorithmObject.specialTreatment(temp);
                    estimateMaySaleArea = architecturalObj.add(estimateMaySaleArea, temp);
                }
                if (architecturalObj.indexOfUtils(name, architecturalObj.formType.base.buildArea)) {//建筑面积
                    temp = $(n).val();
                    temp = architecturalObj.inputAlgorithmObject.specialTreatment(temp);
                    estimateBuildSaleArea = architecturalObj.add(estimateBuildSaleArea, temp);
                }
                if (architecturalObj.indexOfUtils(name, architecturalObj.formType.base.totalPrice)) {//合价
                    temp = $(n).val();
                    temp = architecturalObj.inputAlgorithmObject.specialTreatment(temp);
                    estimateSaleTotal = architecturalObj.add(estimateSaleTotal, temp);
                }
            });
            /*预计销售建筑面积*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().estimateBuildSaleArea.key, estimateBuildSaleArea);
            /*预计销售可售面积*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().estimateMaySaleArea.key, estimateMaySaleArea);
            /*预计销售合计*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().estimateSaleTotal.key, estimateSaleTotal);
            //总建筑面积 = 预计销售建筑面积+不可销售面积
            mayNotSaleArea = architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("get", architecturalObj.config.inputConfig().mayNotSaleArea.key, null);
            totalBuildArea = architecturalObj.add(mayNotSaleArea, estimateBuildSaleArea);
            /*总建筑面积*/
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().totalBuildArea.key, totalBuildArea);
        },
        jqueryInputGetAndSet: function (flag, name, data) {
            console.log("jqueryInputGetAndSet");
            if (flag == 'get') {
                var text = null;
                text = $("." + architecturalObj.config.frm() + " " + "input[name='" + name + "']").val();
                text = architecturalObj.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + architecturalObj.config.frm() + " " + "input[name='" + name + "']").val(data);
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
            if (architecturalObj.inputAlgorithmObject.isNotNull(obj)) {
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
    architecturalObj.indexOfUtils = function (s, str) {
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

    architecturalObj.inputForm = function (key, value) {
        console.log("formType 进入@" + key);
        if (architecturalObj.indexOfUtils(key, architecturalObj.formType.garage.garage)) {//属于车库
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.garage.big)) {//大车库
                architecturalObj.inputAlgorithmObject.bigGarageFun();
            }
            if (architecturalObj.indexOfUtils(key, architecturalObj.formType.garage.small)) {//小车库
                architecturalObj.inputAlgorithmObject.smallGarageFun();
            }
        }
    }

    architecturalObj.inputEvent = function () {
        var arr = architecturalObj.config.inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + architecturalObj.config.frm() + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                try {
                    console.log("key:" + key);
                    var funName = "architecturalObj.inputFun." + key + "Input(" + input.val() + ")";
                    console.log(funName);
                    eval(funName);
                } catch (e) {
                    console.log("没有相关定义的函数或者是属于子表单");
                    architecturalObj.inputForm(key, value);
                }
            });
        })
    }

    architecturalObj.selectEvent = {
        load: {
            /**
             * @author:  zch
             * 描述:动态类型
             * @date:
             **/
            hypothesisDevelopmentSelect2: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.mdHypothesisDevelopment, "", function (html, data) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(data, function (i, item) {
                        retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                    });
                    $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select).html(retHtml);
                    $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select).select2();
                });
            },
            loadAddedValueAdditionalTaxRate: function () {

            },
            load: function () {
                architecturalObj.selectEvent.load.hypothesisDevelopmentSelect2();
                architecturalObj.selectEvent.load.loadAddedValueAdditionalTaxRate();
            }
        },
        //监听change 事件
        monitor: {
            hypothesisDevelopmentSelect2: function () {
                var key = architecturalObj.config.frm() + " ." + architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select;
                $("." + key).change(function () {
                    var pid = $("." + key).eq(1).val();
                    AssessCommon.loadDataDicByPid(pid, "", function (html, data) {
                        $("." + key + "Type").prev().remove();
                        $("." + key + "Type").empty();
                        if (architecturalObj.isNotNull(data)) {
                            $("." + key + "Type").html(html);
                            $("." + key + "Type").select2();
                        }
                    });
                });
            },
            //监听之后 处理显示问题
            hypothesisDevelopmentSelect2Type: function () {
                var key = architecturalObj.config.frm() + " .";
                key += architecturalObj.config.inputConfig().hypothesisDevelopmentSelect2.select;
                key += "Type";
                $("." + key).change(function () {
                    var value = $("." + key).eq(1).val();
                    AssessCommon.getDataDicInfo(value, function (data) {
                        if (architecturalObj.isNotNull(data)) {
                            $("." + architecturalObj.config.frm() + " ." + data.fieldName).toggle();
                        }
                    });
                });
            },
            monitor: function () {
                architecturalObj.selectEvent.monitor.hypothesisDevelopmentSelect2();
                architecturalObj.selectEvent.monitor.hypothesisDevelopmentSelect2Type();
            }
        }
    }

    architecturalObj.eventInit = function () {
        $("#" + ArchitecturalEngineeringObj.prototype.config.frm()).validate();
        architecturalObj.selectEvent.load.load();
        architecturalObj.inputEvent();
        architecturalObj.selectEvent.monitor.monitor();
    };

    architecturalObj.constructionInstallationEngineeringFee = {
        event: function () {
            $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.architecturalEngineeringModel()).show();
            $(function () {
                architecturalEngineeringObj.viewInit();
            });
        },
        close: function () {
            $("." + architecturalObj.config.frm() + " ." + architecturalObj.config.architecturalEngineeringModel()).hide();
        },
        getDataAndWriteHtml: function () {
            var data = architecturalEngineeringObj.getCalculatedResults();
            architecturalObj.inputAlgorithmObject.jqueryInputGetAndSet("set", architecturalObj.config.inputConfig().constructionInstallationEngineeringFee.key,data);//建筑安装工程费
            architecturalObj.constructionInstallationEngineeringFee.close();
            architecturalObj.inputFun.constructionInstallationEngineeringFeeInput();
            architecturalObj.constructionInstallationEngineeringFee.saveAndUpdate();
        },
        saveAndUpdate:function () {

        }
    }

    //初始化
    $(function () {
        architecturalObj.eventInit();
    });
</script>

