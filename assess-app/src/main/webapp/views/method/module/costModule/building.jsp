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
                       placeholder="勘察设计和前期工程费" data-toggle="popover reconnaissanceDesign" class="form-control" name="reconnaissanceDesign">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费率" data-toggle="popover reconnaissanceDesignRote" class="form-control" name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="建筑安装工程费" class="form-control">
            </div>
        </div>
    </div>



</form>


<script type="text/javascript">
    var build = new Object();
    //是否为null
    build.isNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    //是否是数字
    build.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    //是否是正数
    build.isPlus = function (obj) {
        return AlgorithmsPrototype.prototype.isPlus(obj);
    }
    //加法
    build.add = function (a,b) {
        var result = AlgorithmsPrototype.prototype.add(a,b);
        if (build.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }
    //减法
    build.sub = function (a,b) {
        var result = AlgorithmsPrototype.prototype.sub(a,b);
        if (build.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }
    //乘法
    build.mul = function (a,b) {
        var result = AlgorithmsPrototype.prototype.sub(a,b);
        if (build.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }
    //除法
    build.div = function (a,b) {
        var result = AlgorithmsPrototype.prototype.div(a,b);
        if (build.isNull(result)){
            return result;
        }else {
            Alert("输入非法请重新输入");
        }
    }

    build.popoverConfig = function () {
        //延迟显示和隐藏弹出框的毫秒数
        var config = {};
        config.show = 500;
        config.hide = 1000;
        config.frm = "frmBuild" ;
        return config;
    }

    /**
    * @author:  zch
    * 描述:收集弹出框的数据并且隐藏弹出框
    * @date:
    **/
    build.inputSubmit = function (item) {
        var dataName = $(item).attr("data-name");
        var html = "input[name='" +dataName +"']" ;
        var dataValue = $(item).parent().parent().find(html).val();
        $("."+build.popoverConfig().frm+" "+html).val(dataValue);
        build.inputCancel(item);

    }

    /**
    * @author:  zch
    * 描述:隐藏弹出框
    * @date:
    **/
    build.inputCancel = function (item) {
        $(item).parent().parent().parent().parent().parent().hide();
    }

    /**
    * @author:  zch
    * 描述:勘察设计和前期工程费
    * @date:2018-08-08
    **/
    build.reconnaissanceDesign = function () {
        $("[data-toggle='popover reconnaissanceDesign']").popover({
            html : true,
            // title: "勘察设计和前期工程费",
            delay:{show:build.popoverConfig().show, hide:build.popoverConfig().hide},
            content: function() {
                var html = $("#generalPanelTemplate").html();
                html = html.replace(/{name}/g,"reconnaissanceDesign").replace(/{dataName}/g,"reconnaissanceDesign").replace(/{title}/g,"勘察设计和前期工程费") ;
                return html;
            },
            // placement:"auto",
            trigger:"click"
        });
    }

    build.reconnaissanceDesignRote = function () {
        $("[data-toggle='popover reconnaissanceDesignRote']").popover({
            html : true,
            // title: "勘察设计和前期工程费率",
            delay:{show:build.popoverConfig().show, hide:build.popoverConfig().hide},
            content: function() {
                var html = $("#generalPanelTemplate").html();
                html = html.replace(/{name}/g,"reconnaissanceDesignRote").replace(/{dataName}/g,"reconnaissanceDesignRote").replace(/{title}/g,"勘察设计和前期工程费率") ;
                return html;
            },
            // placement:"auto",
            trigger:"click"
        });
    }

    $(function () {
        build.reconnaissanceDesign();
        build.reconnaissanceDesignRote();
    })

</script>

<!-- 通用面板模板 -->
<script type="text/html" id="generalPanelTemplate">
    <div class="panel panel-warning" style="width:300px;">
        <div class="panel-heading">
            <h6>{title}</h6>
            <%--<label>x</label><br>--%>
        </div>

        <%--<div class="panel-body form-inline editableform">--%>
            <%--<div class="control-group form-group">--%>
                <%--<div class="editable-input">--%>
                    <%--<input class="form-control input-sm" type="text" name="{name}">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="editable-buttons">--%>
                <%--<button class="btn btn-primary btn-sm" onclick="build.inputSubmit(this)"><i class="glyphicon glyphicon-ok"></i></button>--%>
                <%--<button class="btn btn-default btn-sm" onclick="build.inputCancel(this)"><i class="glyphicon glyphicon-remove"></i></button>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="panel-body btn-toolbar" role="toolbar">
            <div class="btn-group">
                <input class="form-control" name="{name}">
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default" data-name="{dataName}" onclick="build.inputSubmit(this)"><i class="glyphicon glyphicon-ok"></i></button>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</script>