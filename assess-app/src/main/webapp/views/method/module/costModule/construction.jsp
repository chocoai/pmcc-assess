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
            //landAcquisitionPrice
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
        }
        //公共配套设施建设费 = 公共配套设施建设费单价*开发建筑面积
        if (dataName == construction.config().inputConfig().infrastructureMatchingCost.select) {
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(b, a);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureMatchingCost.key, c); //公共配套设施建设费
        }
        //基础设施建设费 = 基础设施建设费单价*开发建筑面积
        if (dataName == construction.config().inputConfig().infrastructureCost.select) {
            var a, c ;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(b, a);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureCost.key, c); //基础设施建设费
        }
        //更新 基础设施建设费和公共配套设施建设费 开发期间税费
        if (dataName == construction.config().inputConfig().developmentBuildArea.key) {
            var a, c, d,e;
            a = $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureCost.select).eq(1).val();//基础设施建设费单价
            c = $("." + construction.config().frm + " ." + construction.config().inputConfig().infrastructureMatchingCost.select).eq(1).val();//公共配套设施建设费单价
            e = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().devDuringPrice.key, null);//开发期间单价
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.inputAlgorithmObject.specialTreatment(c);
            e = construction.inputAlgorithmObject.specialTreatment(e);

            d  = construction.mul(a,b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureCost.key, d); //基础设施建设费
            d  = construction.mul(c,b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().infrastructureMatchingCost.key, d); //公共配套设施建设费
            d  = construction.mul(e,b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().devDuringPriceTax.key, d); //开发期间税费
        }
        //开发期间税费 = 开发建筑面积 * 开发期间单价
        if (dataName == construction.config().inputConfig().devDuringPrice.key){
            var a, c;
            a = construction.inputAlgorithmObject.jqueryInputGetAndSet("get", construction.config().inputConfig().developmentBuildArea.key, null);//开发建筑面积
            a = construction.inputAlgorithmObject.specialTreatment(a);
            c = construction.mul(a,b);
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().devDuringPriceTax.key, c); //开发期间税费
        }
        $(function () {
            construction.inputEvent();
        });
    }
    construction.inputAlgorithmObject = {
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
                return $("." + construction.config().frm + " " + "input[name='" + name + "']").val();
            }
            if (flag == 'set') {
                $("." + construction.config().frm + " " + "input[name='" + name + "']").val(data);
            }
        },
        isNotNull: function (obj) {
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
        init: function () {
            construction.selectEvent.landAcquisitionTaxRate();
            construction.selectEvent.infrastructureCost();
            construction.selectEvent.infrastructureMatchingCost();
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
        init: function () {
            construction.select2LoadData.loadSelect2DataLandAcquisitionTaxRate();
            construction.select2LoadData.loadCostAndMatchingCost();
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