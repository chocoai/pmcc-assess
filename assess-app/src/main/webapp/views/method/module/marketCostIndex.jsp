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
            <input type="hidden" name="area" class="mdCost area">
            <input type="hidden" name="price" class="mdCost price">
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
            <jsp:include page="./costModule/building.jsp"></jsp:include>
        </div>
        <div class="construction" style="display: none;">
            <!-- 主题内容 -->
            <jsp:include page="./costModule/construction.jsp"></jsp:include>
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
        if (AlgorithmsPrototype.prototype.isNumber(arg1)) {
            if (AlgorithmsPrototype.prototype.isNumber(arg2)) {
                arg1 = arg1.toString(), arg2 = arg2.toString();
                var arg1Arr = arg1.split("."), arg2Arr = arg2.split("."), d1 = arg1Arr.length == 2 ? arg1Arr[1] : "",
                    d2 = arg2Arr.length == 2 ? arg2Arr[1] : "";
                var maxLen = Math.max(d1.length, d2.length);
                var m = Math.pow(10, maxLen);
                var result = Number(((arg1 * m + arg2 * m) / m).toFixed(maxLen));
                var d = arguments[2];
                return typeof d === "number" ? Number((result).toFixed(d)) : result;
            } else {
                return '';
            }
        } else {
            return "";
        }
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
    /**
     * @author:  zch
     * 描述:测试
     * @date:  2018-08-07
     **/
    AlgorithmsPrototype.prototype.dataTest = function () {
        console.log("加法测试(正常参数):" + AlgorithmsPrototype.prototype.add(0.2, 0.1));
        console.log("加法测试(异常参数 null , 0.1):" + AlgorithmsPrototype.prototype.add(null, 0.1));
        console.log("加法测试(异常参数 null , null):" + AlgorithmsPrototype.prototype.add(null, null));
        console.log("加法测试(异常参数 '' , null):" + AlgorithmsPrototype.prototype.add('', null));
        console.log("加法测试(异常参数 '' , ''):" + AlgorithmsPrototype.prototype.add('', ''));

        console.log("减法测试(正常参数):" + AlgorithmsPrototype.prototype.sub(3, 2));
        console.log("减法测试(异常参数 null , 0.1):" + AlgorithmsPrototype.prototype.sub(null, 2));
        console.log("减法测试(异常参数 null , null):" + AlgorithmsPrototype.prototype.sub(null, null));
        console.log("减法测试(异常参数 '' , null):" + AlgorithmsPrototype.prototype.sub('', null));
        console.log("减法测试(异常参数 '' , ''):" + AlgorithmsPrototype.prototype.sub('', ''));

        console.log("乘法测试(正常参数):" + AlgorithmsPrototype.prototype.mul(3, 2));
        console.log("乘法测试(异常参数 null , 0.1):" + AlgorithmsPrototype.prototype.mul(null, 2));
        console.log("乘法测试(异常参数 null , null):" + AlgorithmsPrototype.prototype.mul(null, null));
        console.log("乘法测试(异常参数 '' , null):" + AlgorithmsPrototype.prototype.mul('', null));
        console.log("乘法测试(异常参数 '' , ''):" + AlgorithmsPrototype.prototype.mul('', ''));

        console.log("除法测试(正常参数):" + AlgorithmsPrototype.prototype.div(3, 2));
        console.log("除法测试(异常参数 null , 0):" + AlgorithmsPrototype.prototype.div(null, 0));
        console.log("除法测试(异常参数 0 , 0):" + AlgorithmsPrototype.prototype.div(0, 0));
        console.log("除法测试(异常参数 null , null):" + AlgorithmsPrototype.prototype.div(null, null));
        console.log("除法测试(异常参数 '' , null):" + AlgorithmsPrototype.prototype.div('', null));
        console.log("除法测试(异常参数 '' , ''):" + AlgorithmsPrototype.prototype.div('', ''));
    };

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

    $(function () {
        optionsBuildBox.tabControl();
        // AlgorithmsPrototype.prototype.dataTest();
    });
</script>

