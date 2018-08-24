<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 假设开发法 -->
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>假设开发法</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content optionsBuildBox">
        <div>
            <!-- 隐藏框数据 -->
            <input type="hidden" id="mdDevelopmentHypothesisJSON" name="mdDevelopmentHypothesisJSON" value='${mdDevelopmentHypothesisJSON}'>
            <input type="hidden" id="mdDevelopmentArchitecturalJSON" name="mdDevelopmentArchitecturalJSON" value='${mdDevelopmentArchitecturalJSON}'>
        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>假设开发法:</label>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" name="development" value="1" checked="checked">
                <label>假设开发法</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" name="development" value="2">
                <label>在建工程建设开发法</label>
            </span>
        </div>
        <div class="hypothesisDevelopment">
            <jsp:include page="../method/module/developmentModule/hypothesisDevelopment.jsp"></jsp:include>
            <!-- 主题内容 -->
        </div>
        <div class="architecturalEngineering" style="display: none;">
            <jsp:include page="../method/module/developmentModule/architecturalEngineering.jsp"></jsp:include>
            <!-- 主题内容 -->
        </div>
    </div>
</div>
<script>

    var AlgorithmsPrototype = function () {

    };
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
    }


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
    optionsBuildBox.showHypothesisDevelopment = function () {
        $(".optionsBuildBox .hypothesisDevelopment").show();
        $(".optionsBuildBox .architecturalEngineering").hide();
    };
    optionsBuildBox.showArchitecturalEngineering = function () {
        $(".optionsBuildBox .architecturalEngineering").show();
        $(".optionsBuildBox .hypothesisDevelopment").hide();
    };

    /**
     * @author:  zch
     * 描述:选项卡初始化载入
     * @date:2018-08-17
     **/
    optionsBuildBox.tabControl = function () {
        $(".optionsBuildBox input[type='radio'][name='development']").change(function () {
            var val = $(".optionsBuildBox :radio:checked").val();
            if (val == 1) {
                optionsBuildBox.showHypothesisDevelopment();
            }
            if (val == 2) {
                optionsBuildBox.showArchitecturalEngineering();
            }
        });
    };

    //初始化假设开发法
    optionsBuildBox.hypothesisDevelopmentInit = function (data) {
        $("#frmDevelopment").initForm(data);
    };

    //在建工程建设开发法
    optionsBuildBox.architecturalEngineeringInit = function (data) {
        $("#frmArchitecturalEngineering").initForm(data);
    };
    /**
     * @author:  zch
     * 描述:获取假设开发法数据
     * @date:2018-08-17
     **/
    optionsBuildBox.getDevelopment = function () {
        var jsonParams = formParams("frmDevelopment");
        jsonParams.id = "${mdDevelopmentHypothesis.id}";//确保修改能成功
        return jsonParams;
    };

    /**
     * @author:  zch
     * 描述:在建工程建设开发法数据
     * @date:2018-08-17
     **/
    optionsBuildBox.getArchitecturalEngineering = function () {
        var jsonParams = formParams("frmArchitecturalEngineering");
        jsonParams.id = "${mdDevelopmentArchitectural.id}";//确保修改能成功
        return jsonParams;
    };

    optionsBuildBox.updateInit = function () {
        var mdDevelopmentHypothesis = "${mdDevelopmentHypothesis}" ;
        var mdDevelopmentArchitectural = "${mdDevelopmentArchitectural}" ;

        if (AlgorithmsPrototype.prototype.isNotNull(mdDevelopmentArchitectural)){
            optionsBuildBox.showArchitecturalEngineering();
            mdDevelopmentArchitectural = $("mdDevelopmentArchitecturalJSON").val();
            try {
                mdDevelopmentArchitectural = JSON.parse(mdDevelopmentArchitectural);
                optionsBuildBox.architecturalEngineeringInit(mdDevelopmentArchitectural);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }

        if (AlgorithmsPrototype.prototype.isNotNull(mdDevelopmentHypothesis)){
            optionsBuildBox.showHypothesisDevelopment();
            mdDevelopmentHypothesis = $("#mdDevelopmentHypothesisJSON").val();
            try {
                mdDevelopmentHypothesis = JSON.parse(mdDevelopmentHypothesis);
                optionsBuildBox.hypothesisDevelopmentInit(mdDevelopmentHypothesis);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    };

    $(function () {
        optionsBuildBox.tabControl();
        optionsBuildBox.updateInit();
    });
</script>

