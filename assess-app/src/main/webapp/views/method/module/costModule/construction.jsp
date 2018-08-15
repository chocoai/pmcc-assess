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
            土地取得税
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="landAcquisitionTaxRate" class="landAcquisitionTaxRateClass form-control"></select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" readonly="readonly" onclick="construction.constructionInstallationEngineeringFee.event();"
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
                describe: "土地取得税",
                value: "",
                select: "landAcquisitionTaxRateClass"
            },
            //landAcquisitionTaxRate
        };
        config.inputConfig = function () {
            return inputNameConfig;
        };
        config.inputName = function () {
            var arr = new Array();
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

        //土地开发面积
        if (dataName == construction.config().inputConfig().developmentLandArea.key) {
            construction.inputAlgorithmObject.landPurchasePriceFun(dataValue);
        }
        //土地购买单价
        if (dataName == construction.config().inputConfig().unitPriceLandPurchase.key) {
            construction.inputAlgorithmObject.landPurchasePriceFun(dataValue);
        }
        $(function () {
            construction.inputEvent();
        });
    }
    construction.inputAlgorithmObject = {

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
     * 描述:input框事件
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
        })
    }
    construction.eventInit = function () {
        construction.inputEvent();
    }

    /**
     * @author:  zch
     * 描述:建筑安装工程费
     * @date:2018-08-15
     **/
    construction.constructionInstallationEngineeringFee = {
        event:function () {
            $("." + construction.config().frm + " ."+construction.config().engineeringFee).show();
            $(function () {
                constructEngineeringObjectA.viewInit();
            });
        },
        getDataAndWriteHtml:function () {
            var data = constructEngineeringObjectA.getCalculatedResults();
            construction.inputAlgorithmObject.jqueryInputGetAndSet("set", construction.config().inputConfig().constructionInstallationEngineeringFee.key, data); //土地购买合价
            $("." + construction.config().frm + " ."+construction.config().engineeringFee).hide();
        },
        close:function () {
            $("." + construction.config().frm + " ."+construction.config().engineeringFee).hide();
        }
    }

    /**--------------------------------初始化------------------**/
    $(function () {
        construction.eventInit();
    });
</script>