<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
  在建工程
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            开发土地面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发土地面积" class="form-control">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发建筑面积
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发建筑面积" class="form-control">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            开发期
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="开发期" class="form-control">
            </div>
        </div>
    </div>
    <div>
        <jsp:include page="constructionInstallationEngineeringFeeA.jsp"></jsp:include>
    </div>
</form>
<script>
    var construction = new Object();
    //是否为null
    construction.isNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    //是否是数字
    construction.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    //是否是正数
    construction.isPlus = function (obj) {
        return AlgorithmsPrototype.prototype.isPlus(obj);
    }
    //加法
    construction.add = function (a,b) {
        var result = AlgorithmsPrototype.prototype.add(a,b);
        if (construction.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }
    //减法
    construction.sub = function (a,b) {
        var result = AlgorithmsPrototype.prototype.sub(a,b);
        if (construction.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }
    //乘法
    construction.mul = function (a,b) {
        var result = AlgorithmsPrototype.prototype.sub(a,b);
        if (construction.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }
    //除法
    construction.div = function (a,b) {
        var result = AlgorithmsPrototype.prototype.div(a,b);
        if (construction.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }

    /**--------------------------------初始化------------------**/
    $(function () {
        // constructEngineeringObjectA.viewInit();
    });
</script>