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
                       placeholder="勘察设计和前期工程费"  data-toggle="popover reconnaissanceDesign"
                       class="form-control" name="reconnaissanceDesign">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            勘察设计和前期工程费率
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="勘察设计和前期工程费率" data-toggle="popover reconnaissanceDesignRote" class="form-control"
                       name="reconnaissanceDesignRote">
            </div>
        </div>

        <label class="col-sm-1 control-label">
            建筑安装工程费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="建筑安装工程费" value="2" readonly="readonly" class="form-control"
                       name="constructionInstallationEngineeringFee"
                       onclick="build.constructionInstallationEngineeringFee()">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            基础设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureCost"
                        class="form-control search-select select2 infrastructureCost">
                </select>
            </div>
        </div>

        <label class="col-sm-1 control-label">
            公共配套设施建设费
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <select name="infrastructureMatchingCost"
                        class="form-control search-select select2 infrastructureMatchingCost">
                </select>
            </div>
        </div>
    </div>


</form>


<script type="text/javascript">
    /**
    * @author:  zch
    * 描述: 定义一个对象 (页面上不能与此对象的名称相同)
    * @date:2018-08-08
    **/
    var build = new Object();
    /**--------------------------------基础算法------------------**/
    /**
     * @author:  zch
     * 描述:是否为null
     * @date:2018-08-08
     **/
    build.isNull = function (obj) {
        return AlgorithmsPrototype.prototype.isNotNull(obj);
    }
    /**
     * @author:  zch
     * 描述:是否是数字
     * @date:2018-08-08
     **/
    build.isNumber = function (obj) {
        return AlgorithmsPrototype.prototype.isNumber(obj);
    }
    /**
     * @author:  zch
     * 描述:是否是正数
     * @date:2018-08-08
     **/
    build.isPlus = function (obj) {
        return AlgorithmsPrototype.prototype.isPlus(obj);
    }
    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-08
     **/
    build.add = function (a, b) {
        var result = AlgorithmsPrototype.prototype.add(a, b);
        if (build.isNull(result)) {
            return result;
        } else {
            Alert("输入非法请重新输入");
        }
    }
    /**
     * @author:  zch
     * 描述:减法
     * @date:2018-08-08
     **/
    build.sub = function (a, b) {
        var result = AlgorithmsPrototype.prototype.sub(a, b);
        if (build.isNull(result)) {
            return result;
        } else {
            Alert("输入非法请重新输入");
        }
    }
    /**
     * @author:  zch
     * 描述:乘法
     * @date:2018-08-08
     **/
    build.mul = function (a, b) {
        var result = AlgorithmsPrototype.prototype.mul(a, b);
        if (build.isNull(result)) {
            return result;
        } else {
            Alert("输入非法请重新输入");
        }
    }
    /**
    * @author:  zch
    * 描述:除法
    * @date:2018-08-08
    **/
    build.div = function (a, b) {
        var result = AlgorithmsPrototype.prototype.div(a, b);
        if (build.isNull(result)) {
            return result;
        } else {
            Alert("输入非法请重新输入");
        }
    }

    /**--------------------------------配置------------------**/
    /**
     * @author:  zch
     * 描述:参数配置
     * @date:2018-08-10
     **/
    build.config = function () {
        //延迟显示和隐藏弹出框的毫秒数
        var config = {};
        config.show = 500;
        config.hide = 1000;
        config.frm = "frmBuild";//表单id
        config.engineeringFee = "constructionInstallationEngineeringFeeA"; //子表单id
        /*此处的配置name必须与页面上的一致*/
        var inputNameConfig = {
            reconnaissanceDesign: {key:"reconnaissanceDesign",value:"勘察设计和前期工程费"},
            reconnaissanceDesignRote: {key:"reconnaissanceDesignRote",value:"勘察设计和前期工程费率"},
            constructionInstallationEngineeringFee: {key:"constructionInstallationEngineeringFee",value:"建筑安装工程费"},
            infrastructureCost: {key:"infrastructureCost",value:"基础设施费用"},
            infrastructureMatchingCost: {key:"infrastructureMatchingCost",value:"公共配套设施费用"}
        };
        config.inputConfig = function () {
            return inputNameConfig;
        }
        /**
        * @author:  zch
        * 描述:用做给页面上的所有input框添加事件
        * @date:2018-08-10
        **/
        config.inputName = function () {
            var arr = new Array();
            arr.push([{key: inputNameConfig.reconnaissanceDesign.key, value: inputNameConfig.reconnaissanceDesign.value}]);
            arr.push([{key: inputNameConfig.reconnaissanceDesignRote.key, value: inputNameConfig.reconnaissanceDesignRote.value}]);
            arr.push([{key: inputNameConfig.constructionInstallationEngineeringFee.key, value: inputNameConfig.constructionInstallationEngineeringFee.value}]);
            arr.push([{key: inputNameConfig.infrastructureCost.key, value: inputNameConfig.infrastructureCost.value}]);
            arr.push([{key: inputNameConfig.infrastructureMatchingCost.key, value: inputNameConfig.infrastructureMatchingCost.value}]);
            return arr;
        }
        return config;
    }

    /**--------------------------------基础方法------------------**/
    /**
     * @author:  zch
     * 描述: 成本法中所有的计算都会归纳到这进行运算算法
     * @date:2018-08-10
     **/
    build.inputAlgorithm = function (dataName, dataValue) {
        if (dataName == build.config().inputConfig().reconnaissanceDesignRote.key) {//输入的是勘察设计和前期工程费率
            var a = $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().constructionInstallationEngineeringFee.key + "']").val();//建筑安装工程费
            if (!build.isNumber(a)) {
                Alert("数据不合法!");
                return false;
            }
            var b = dataValue;
            var c = build.mul(a, b);//勘察设计和前期工程费 = 建筑安装工程费*费率
            $("." + build.config().frm + " " + "input[name='" + build.config().inputConfig().reconnaissanceDesign.key + "']").val(c);
        }
        $(function () {
            build.inputEvent();
        });
    }
    /**
     * @author:  zch
     * 描述:input框事件
     * @date:2018-08-10
     **/
    build.inputEvent = function () {
        var arr = build.config().inputName();
        $.each(arr, function (i, n) {
            var key = n[0].key;
            var input = $("." + build.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                if (build.isNumber(value)){
                    build.inputAlgorithm(key, input.val());
                }else {
                    Alert("请输入合法数字!")
                }
            });
        })
    };
    /**
     * @author:  zch
     * 描述:定义input框初始化
     * @date:2018-08-10
     **/
    build.inputInit = function () {
        build.inputEvent();
        build.loadCostAndMatchingCost();
    }
    /**
    * @author:  zch
    * 描述:获取基础设施费用列表和公共配套设施费用
    * @date:2018-08-10
    **/
    build.loadCostAndMatchingCost = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/marketCost/listCostAndMatchingCost",
            type: "get",
            data:{},
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                   var cost = result.data.InfrastructureCost;
                   var matchingCost = result.data.InfrastructureMatchingCost;
                    var option = "<option value=''>请选择</option>";
                    if (cost.length > 0) {
                        for (var i = 0; i < cost.length; i++) {
                            option += "<option value='" + cost[i].name + "'>" + cost[i].number + "</option>";
                        }
                        $("."+build.config().frm+" ."+build.config().inputConfig().infrastructureCost.key).html(option);
                        $("."+build.config().frm+" ."+build.config().inputConfig().infrastructureCost.key).select2();
                    }
                    // if (matchingCost.length > 0) {
                    //     for (var i = 0; i < cost.length; i++) {
                    //         option += "<option value='" + matchingCost[i].number + "'>" + matchingCost[i].number + "</option>";
                    //     }
                    //     $("."+build.config().frm+" ."+build.config().inputConfig().infrastructureMatchingCost.key).html(option);
                    //     $("."+build.config().frm+" ."+build.config().inputConfig().infrastructureMatchingCost.key).select2();
                    // }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }


    // /**
    //  * @author:  zch
    //  * 描述:勘察设计和前期工程费率
    //  * @date:2018-08-08
    //  **/
    // build.reconnaissanceDesignRote = function () {
    //     $("[data-toggle='popover reconnaissanceDesignRote']").popover({
    //         html : true,
    //         delay:{show:build.config().show, hide:build.config().hide},
    //         content: function() {
    //             var html = $("#generalPanelTemplate").html();
    //             html = html.replace(/{name}/g,"reconnaissanceDesignRote").replace(/{dataName}/g,"reconnaissanceDesignRote").replace(/{title}/g,"勘察设计和前期工程费率") ;
    //             return html;
    //         },
    //         placement:"top",
    //         trigger:"click"
    //     });
    // }

    /**
     * @author:  zch
     * 描述:建筑安装工程费
     * @date:2018-08-09
     **/
    build.constructionInstallationEngineeringFee = function () {
        $("." + build.config().engineeringFee).modal("show");
        $(function () {
            constructionInstallationEngineeringFeeA.treegrid();
        });
    }
    /**--------------------------------初始化------------------**/
    $(function () {
        build.inputInit();
    })

</script>

<!-- 通用面板模板 弃用 -->
<script type="text/html" id="generalPanelTemplate">
    <div class="panel panel-warning" style="width:300px;">
        <div class="panel-heading">
            <div>{title}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label" onclick="build.inputCancel(this);">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;X
                </label>
            </div>
        </div>
        <div class="panel-body btn-toolbar" role="toolbar">
            <div class="btn-group">
                <input class="form-control" name="{name}">
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default" data-name="{dataName}" onclick="build.inputSubmit(this)">
                    <i class="glyphicon glyphicon-ok"></i></button>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</script>

<div class="modal fade bs-example-modal-lg constructionInstallationEngineeringFeeA" data-backdrop="static" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <jsp:include page="constructionInstallationEngineeringFeeA.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>
