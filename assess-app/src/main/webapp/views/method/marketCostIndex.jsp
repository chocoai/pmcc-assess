<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>成本法</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content optionsBuildBox">
        <div>
            <!-- 隐藏框数据 -->
            <input type="hidden" id="mdCostAndDevelopmentOtherJSON" name="mdCostAndDevelopmentOtherJSON"
                   value='${mdCostAndDevelopmentOtherJSON}'>
            <input type="hidden" id="mdCostBuildingJSON" name="mdCostBuildingJSON" value='${mdCostBuildingJSON}'>
            <input type="hidden" id="mdCostConstructionJSON" name="mdCostConstructionJSON"
                   value='${mdCostConstructionJSON}'>
        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>建筑形态</label>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" name="building" value="1" checked="checked">
                <label>建筑物</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" name="building" value="2">
                <label>在建工程</label>
            </span>
        </div>
        <div class="building">
            <!-- 主题内容 -->
            <jsp:include page="module/costModule/building.jsp"></jsp:include>
        </div>
        <div class="construction" style="display: none;">
            <!-- 主题内容 -->
            <jsp:include page="module/costModule/construction.jsp"></jsp:include>
        </div>
    </div>
</div>

<script type="text/javascript">
    var AlgorithmsPrototype = function () {

    };

    /**
     * @author:  zch
     * 描述:判断是否为数字
     * @date:
     **/
    AlgorithmsPrototype.prototype.isNumber = function (obj) {
        if (obj == 0) {
            return true;
        }
        if (obj == '0') {
            return true;
        }
        if (AlgorithmsPrototype.prototype.isNotNull(obj)) {
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if (regPos.test(obj) || regNeg.test(obj)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    };

    /**
     * @author:  zch
     * 描述:判断是否为null
     * @date:
     **/
    AlgorithmsPrototype.prototype.isNotNull = function (obj) {
        if (obj == null) {
            return false;
        }
        if (obj == '') {
            return false;
        }
        if (obj == "") {
            return false;
        }
        if (obj == 'undefined') {
            return false;
        }
        return true;
    };

    /**
     * @author:  zch
     * 描述:判断是否为正数
     * @date:2018-08-07
     **/
    AlgorithmsPrototype.prototype.isPlus = function (argA) {
        if (AlgorithmsPrototype.prototype.isNotNull(argA)) {//非空校验
            if (AlgorithmsPrototype.prototype.isNumber(argA)) {//数字校验
                var reg = /^\d+(?=\.{0,1}\d+$|$)/;
                if (reg.test(argA)) {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    };


    /**
     * @author:  zch
     * 描述:函数，加法函数，用来得到精确的加法结果 如:3,2 = 5
     * 参数：arg1：第一个加数；arg2第二个加数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数）
     * @date:2018-08-07
     * @return 两数相加的结果
     **/
    AlgorithmsPrototype.prototype.add = function (arg1, arg2) {
        arg1 = arg1.toString(), arg2 = arg2.toString();
        var arg1Arr = arg1.split("."), arg2Arr = arg2.split("."), d1 = arg1Arr.length == 2 ? arg1Arr[1] : "",
            d2 = arg2Arr.length == 2 ? arg2Arr[1] : "";
        var maxLen = Math.max(d1.length, d2.length);
        var m = Math.pow(10, maxLen);
        var result = Number(((arg1 * m + arg2 * m) / m).toFixed(maxLen));
        var d = arguments[2];
        return typeof d === "number" ? Number((result).toFixed(d)) : result;
    };

    /**
     * @author:  zch
     * 描述:减法函数，用来得到精确的减法结果 如:3,2 = 1
     *参数：arg1：第一个加数；arg2第二个加数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数
     * @date:2018-08-07
     **/
    AlgorithmsPrototype.prototype.sub = function (arg1, arg2) {
        if (AlgorithmsPrototype.prototype.isNumber(arg1)) {
            if (AlgorithmsPrototype.prototype.isNumber(arg2)) {
                return AlgorithmsPrototype.prototype.add(arg1, -Number(arg2), arguments[2]);
            } else {
                return "";
            }
        } else {
            return "";
        }
    };

    /**
     * @author:  zch
     * 描述:乘法函数，用来得到精确的乘法结果 如:3,2 = 6
     * 参数：arg1：第一个乘数；arg2第二个乘数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数)
     * @date:2018-08-07
     **/
    AlgorithmsPrototype.prototype.mul = function (arg1, arg2) {
        if (AlgorithmsPrototype.prototype.isNumber(arg1)) {
            if (AlgorithmsPrototype.prototype.isNumber(arg2)) {
                var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
                m = (r1.split(".")[1] ? r1.split(".")[1].length : 0) + (r2.split(".")[1] ? r2.split(".")[1].length : 0);
                resultVal = Number(r1.replace(".", "")) * Number(r2.replace(".", "")) / Math.pow(10, m);
                return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * @author:  zch
     * 参数：arg1：除数；arg2被除数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数)
     * 描述:除法函数，用来得到精确的除法结果 如:3,2 = 1.5
     * @date:2018-08-07
     **/
    AlgorithmsPrototype.prototype.div = function (arg1, arg2) {
        if (AlgorithmsPrototype.prototype.isNumber(arg1)) {
            if (AlgorithmsPrototype.prototype.isNumber(arg2)) {
                if (arg1 != 0) {//除数不能为0
                    var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
                    m = (r2.split(".")[1] ? r2.split(".")[1].length : 0) - (r1.split(".")[1] ? r1.split(".")[1].length : 0);
                    resultVal = Number(r1.replace(".", "")) / Number(r2.replace(".", "")) * Math.pow(10, m);
                    return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }


    var optionsBuildBox = new Object();
    optionsBuildBox.showBuilding = function () {
        $(".optionsBuildBox .building").show();
        $(".optionsBuildBox .construction").hide();
    };
    optionsBuildBox.showConstruction = function () {
        $(".optionsBuildBox .construction").show();
        $(".optionsBuildBox .building").hide();
    };
    /**
     * @author:  zch
     * 描述:选项卡初始化载入
     * @date:
     **/
    optionsBuildBox.tabControl = function () {
        $(".optionsBuildBox input[type='radio'][name='building']").change(function () {
            var val = $(".optionsBuildBox :radio:checked").val();
            if (val == 1) {
                optionsBuildBox.showBuilding();
            }
            if (val == 2) {
                optionsBuildBox.showConstruction();
            }
        });
    };

    optionsBuildBox.mdCostBuildingInit = function (data) {
        $("#frmBuild").initForm(data);
    };
    optionsBuildBox.mdCostConstructionInit = function (data) {
        $("#frmConstruction").initForm(data);
    };
    /**
     * @author:  zch
     * 描述:处理页面更新时
     * @date:
     **/
    optionsBuildBox.updateInit = function () {
        var item = [{
            "id": 654,
            "pid": 653,
            "name": "土建工程",
            "key": null,
            "_parentId": null,
            "number": "1",
            "parent": true,
            "area": 0,
            "totalCost": 0,
            "currency": 0,
            "valuationDateDegreeCompletion": null,
            "valuationDateTotal": 0,
            "valuationDateCurrency": 0,
            "continuedConstructionInvestmentTotal": 0,
            "continuedConstructionInvestmentCurrency": 0,
            "children": [{
                "id": 660,
                "pid": 654,
                "name": "地下土建工程",
                "key": null,
                "_parentId": 654,
                "number": "1-1",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 659,
                "pid": 654,
                "name": "地上主体土建工程",
                "key": null,
                "_parentId": 654,
                "number": "1-2",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }],
            "state": "open"
        }, {
            "id": 655,
            "pid": 653,
            "name": "安装工程",
            "key": null,
            "_parentId": null,
            "number": "2",
            "parent": true,
            "area": 22.4,
            "totalCost": 996.8,
            "currency": 44.5,
            "valuationDateDegreeCompletion": null,
            "valuationDateTotal": 0,
            "valuationDateCurrency": 0,
            "continuedConstructionInvestmentTotal": 0,
            "continuedConstructionInvestmentCurrency": 0,
            "children": [{
                "id": 666,
                "pid": 655,
                "name": "天然气",
                "key": null,
                "_parentId": 655,
                "number": "2-1",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 661,
                "pid": 655,
                "name": "给排水",
                "key": null,
                "_parentId": 655,
                "number": "2-2",
                "parent": false,
                "area": "22.4000000",
                "totalCost": 996.8,
                "currency": "44.5000000",
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 664,
                "pid": 655,
                "name": "消防及安防",
                "key": null,
                "_parentId": 655,
                "number": "2-3",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 662,
                "pid": 655,
                "name": "强电",
                "key": null,
                "_parentId": 655,
                "number": "2-4",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 663,
                "pid": 655,
                "name": "弱电（含通讯）",
                "key": null,
                "_parentId": 655,
                "number": "2-5",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 665,
                "pid": 655,
                "name": "电梯",
                "key": null,
                "_parentId": 655,
                "number": "2-6",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }, {
                "id": 667,
                "pid": 655,
                "name": "制冷与取暖工程",
                "key": null,
                "_parentId": 655,
                "number": "2-7",
                "parent": false,
                "area": 0,
                "totalCost": 0,
                "currency": 0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0,
                "valuationDateCurrency": 0,
                "continuedConstructionInvestmentTotal": 0,
                "continuedConstructionInvestmentCurrency": 0,
                "state": "open"
            }],
            "state": "open"
        }, {
            "id": 656,
            "pid": 653,
            "name": "装饰工程",
            "key": null,
            "_parentId": null,
            "number": "3",
            "parent": true,
            "area": 0,
            "totalCost": 0,
            "currency": 0,
            "valuationDateDegreeCompletion": null,
            "valuationDateTotal": 0,
            "valuationDateCurrency": 0,
            "continuedConstructionInvestmentTotal": 0,
            "continuedConstructionInvestmentCurrency": 0,
            "state": "open"
        }, {
            "id": 657,
            "pid": 653,
            "name": "附属工程",
            "key": null,
            "_parentId": null,
            "number": "4",
            "parent": true,
            "area": 0,
            "totalCost": 0,
            "currency": 0,
            "valuationDateDegreeCompletion": null,
            "valuationDateTotal": 0,
            "valuationDateCurrency": 0,
            "continuedConstructionInvestmentTotal": 0,
            "continuedConstructionInvestmentCurrency": 0,
            "state": "open"
        }, {
            "id": 658,
            "pid": 653,
            "name": "二装工程",
            "key": null,
            "_parentId": null,
            "number": "5",
            "parent": true,
            "area": 0,
            "totalCost": 0,
            "currency": 0,
            "valuationDateDegreeCompletion": null,
            "valuationDateTotal": 0,
            "valuationDateCurrency": 0,
            "continuedConstructionInvestmentTotal": 0,
            "continuedConstructionInvestmentCurrency": 0,
            "state": "open"
        }];
        var data = {
            "total": 14,
            "rows": [{
                "id": 654,
                "pid": 653,
                "name": "土建工程",
                "key": null,
                "_parentId": null,
                "number": "1",
                "parent": true,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 655,
                "pid": 653,
                "name": "安装工程",
                "key": null,
                "_parentId": null,
                "number": "2",
                "parent": true,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 656,
                "pid": 653,
                "name": "装饰工程",
                "key": null,
                "_parentId": null,
                "number": "3",
                "parent": true,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 657,
                "pid": 653,
                "name": "附属工程",
                "key": null,
                "_parentId": null,
                "number": "4",
                "parent": true,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 658,
                "pid": 653,
                "name": "二装工程",
                "key": null,
                "_parentId": null,
                "number": "5",
                "parent": true,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 660,
                "pid": 654,
                "name": "地下土建工程",
                "key": null,
                "_parentId": 654,
                "number": "1-1",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 659,
                "pid": 654,
                "name": "地上主体土建工程",
                "key": null,
                "_parentId": 654,
                "number": "1-2",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 666,
                "pid": 655,
                "name": "天然气",
                "key": null,
                "_parentId": 655,
                "number": "2-1",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 661,
                "pid": 655,
                "name": "给排水",
                "key": null,
                "_parentId": 655,
                "number": "2-2",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 664,
                "pid": 655,
                "name": "消防及安防",
                "key": null,
                "_parentId": 655,
                "number": "2-3",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 662,
                "pid": 655,
                "name": "强电",
                "key": null,
                "_parentId": 655,
                "number": "2-4",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 663,
                "pid": 655,
                "name": "弱电（含通讯）",
                "key": null,
                "_parentId": 655,
                "number": "2-5",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 665,
                "pid": 655,
                "name": "电梯",
                "key": null,
                "_parentId": 655,
                "number": "2-6",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }, {
                "id": 667,
                "pid": 655,
                "name": "制冷与取暖工程",
                "key": null,
                "_parentId": 655,
                "number": "2-7",
                "parent": false,
                "area": 0.0,
                "totalCost": 0.0,
                "currency": 0.0,
                "valuationDateDegreeCompletion": null,
                "valuationDateTotal": 0.0,
                "valuationDateCurrency": 0.0,
                "continuedConstructionInvestmentTotal": 0.0,
                "continuedConstructionInvestmentCurrency": 0.0
            }]
        };
        var mdCostAndDevelopmentOther = "${mdCostAndDevelopmentOther}" ;
        var mdCostBuilding = "${mdCostBuilding}";
        var mdCostConstruction = "${mdCostConstruction}";
        if (AlgorithmsPrototype.prototype.isNotNull(mdCostBuilding)) {
            optionsBuildBox.showBuilding();
            try {
                mdCostBuilding = $("#mdCostBuildingJSON").val();
                mdCostBuilding = JSON.parse(mdCostBuilding);
                //初始化数据
                optionsBuildBox.mdCostBuildingInit(mdCostBuilding);
                if (AlgorithmsPrototype.prototype.isNotNull(mdCostAndDevelopmentOther)){
                    mdCostAndDevelopmentOther = $("#mdCostAndDevelopmentOtherJSON").val();
                    mdCostAndDevelopmentOther = JSON.parse(mdCostAndDevelopmentOther);
                    constructEngineeringObject.setServerData(mdCostAndDevelopmentOther);
                }
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
        if (AlgorithmsPrototype.prototype.isNotNull(mdCostConstruction)) {
            optionsBuildBox.showConstruction();
            try {
                mdCostConstruction = $("#mdCostConstructionJSON").val();
                mdCostConstruction = JSON.parse(mdCostConstruction);
                //初始化数据
                optionsBuildBox.mdCostConstructionInit(mdCostConstruction);
                if (AlgorithmsPrototype.prototype.isNotNull(mdCostAndDevelopmentOther)){
                    mdCostAndDevelopmentOther = $("#mdCostAndDevelopmentOtherJSON").val();
                    mdCostAndDevelopmentOther = JSON.parse(mdCostAndDevelopmentOther);
                    constructEngineeringObjectA.setServerData(mdCostAndDevelopmentOther);
                }
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    }

    optionsBuildBox.getMdCostBuilding = function () {
        var jsonParams = formParams("frmBuild");
        jsonParams.id = "${mdCostBuilding.id}";//确保修改能成功
        return jsonParams;
    };
    optionsBuildBox.getMdCostConstruction = function () {
        var jsonParams = formParams("frmConstruction");
        jsonParams.id = "${mdCostConstruction.id}";//确保修改能成功
        return jsonParams;
    };

    $(function () {
        optionsBuildBox.tabControl();
        optionsBuildBox.updateInit();
    });
</script>

