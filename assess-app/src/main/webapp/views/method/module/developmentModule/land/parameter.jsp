<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/10/8
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>收入类(参数)</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal" id="parameterFrm">
            <div class="form-group">
                <div class="col-md-12 col-sm-12">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <td>项目</td>
                            <td>建筑面积（㎡）</td>
                            <td>可售面积（㎡）</td>
                            <td>单位售价（元/㎡）</td>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>住宅建筑</td>
                            <td><a href="#" class="villaResidenceBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                            </td>
                            <td><a href="#" class="villaResidenceMaySaleArea" data-type="text" data-title="可售面积">0.00</a>
                            </td>
                            <td><a href="#" class="villaResidenceUnitPrice" data-type="text" data-title="单位售价">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>商业建筑</td>
                            <td><a href="#" class="strategyBusinessBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                            </td>
                            <td><a href="#" class="strategyBusinessMaySaleArea" data-type="text" data-title="可售面积">0.00</a>
                            </td>
                            <td><a href="#" class="strategyBusinessUnitPrice" data-type="text"
                                   data-title="单位售价">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>办公建筑</td>
                            <td><a href="#" class="workBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                            </td>
                            <td><a href="#" class="workBuildMaySaleArea" data-type="text" data-title="可售面积">0.00</a>
                            </td>
                            <td><a href="#" class="workBuildUnitPrice" data-type="text" data-title="单位售价">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>地下机动车停车库建筑</td>
                            <td><a href="#" class="undergroundGarageBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                            </td>
                            <td><a href="#" class="undergroundGarageMaySaleArea" data-type="text"
                                   data-title="可售面积">0.00</a>
                            </td>
                            <td><a href="#" class="undergroundGarageUnitPrice" data-type="text"
                                   data-title="单位售价">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>地下商业建筑</td>
                            <td><a href="#" class="undergroundBusinessShopBuildArea" data-type="text" data-title="建筑面积">0.00</a>
                            </td>
                            <td><a href="#" class="undergroundBusinessShopMaySaleArea" data-type="text"
                                   data-title="可售面积">0.00</a>
                            </td>
                            <td><a href="#" class="undergroundBusinessShopUnitPrice" data-type="text" data-title="单位售价">0.00</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-md-1 col-sm-1 control-label">不可售建筑面积(㎡)</label>
                        <div class="col-md-2 col-sm-2">
                            <input type="text"
                                   placeholder="不可售建筑面积(㎡)" class="form-control" name="nonSaleFloorAreaTax">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-md-1 col-sm-1 control-label">预期销售合计</label>
                        <div class="col-md-2 col-sm-2">
                            <input type="text"
                                   placeholder="预期销售合计" class="form-control" name="estimateSaleTotal"
                                   readonly="readonly" value="0.00">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
                        <div class="col-md-2 col-sm-2">
                            <input type="text" readonly="readonly"
                                   placeholder="可售面积" class="form-control" name="smallGarageMaySaleArea" value="0.00">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
                        <div class="col-md-2 col-sm-2">
                            <input type="text" readonly="readonly"
                                   placeholder="销售合价" class="form-control" name="smallGarageTotalPrice" value="0.00">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    var parameter = new Object();

    parameter.config = {
        frm: "parameterFrm",
        names: function () {
            var arr = new Array();
            arr.push({key: "villaResidenceBuildArea", name: "建筑面积"}, {
                key: "villaResidenceMaySaleArea",
                name: "可售面积"
            }, {key: "villaResidenceUnitPrice", name: "单位售价"});
            arr.push({key: "strategyBusinessBuildArea", name: "建筑面积"}, {
                key: "strategyBusinessMaySaleArea",
                name: "可售面积"
            }, {key: "strategyBusinessUnitPrice", name: "单位售价"});
            arr.push({key: "workBuildArea", name: "建筑面积"}, {
                key: "workBuildMaySaleArea",
                name: "可售面积"
            }, {key: "workBuildUnitPrice", name: "单位售价"});
            arr.push({key: "undergroundGarageBuildArea", name: "建筑面积"}, {
                key: "undergroundGarageMaySaleArea",
                name: "可售面积"
            }, {key: "undergroundGarageUnitPrice", name: "单位售价"});
            arr.push({
                key: "undergroundBusinessShopBuildArea",
                name: "建筑面积"
            }, {key: "undergroundBusinessShopMaySaleArea", name: "可售面积"}, {
                key: "undergroundBusinessShopUnitPrice",
                name: "单位售价"
            });
            return arr;
        }
    };

    parameter.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * @author:  zch
     * 描述:开启行编辑
     * @date:2018-10-08
     **/
    parameter.init = function () {
        $.each(parameter.config.names(), function (i, n) {
            $("#" + parameter.config.frm).find("." + n.key).editable(
                {
                    type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                    disabled: false,             //是否禁用编辑
                    emptytext: "空文本",          //空值的默认文本
                    mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                    validate: function (value) { //字段验证
                        if (parameter.isEmpty(value)) {
                            if (isNaN(value)) {
                                return '必须是数字';
                            } else {
                                //更新各种合计
                                parameter.write(value, n.name,n.key);
                            }
                        } else {
                            return '不能为空';
                        }
                    }
                }
            );
        });
    };

    parameter.write = function (item, name,key) {
        if (name == '建筑面积') {
            var str = 0;
            if (key != "villaResidenceBuildArea"){
                str += Number($("#" + parameter.config.frm).find(".villaResidenceBuildArea").html());
            }
            if (key != "strategyBusinessBuildArea"){
                str += Number($("#" + parameter.config.frm).find(".strategyBusinessBuildArea").html());
            }
            if (key != "workBuildArea"){
                str += Number($("#" + parameter.config.frm).find(".workBuildArea").html());
            }
            if (key != "undergroundGarageBuildArea"){
                str += Number($("#" + parameter.config.frm).find(".undergroundGarageBuildArea").html());
            }
            if (key != "undergroundBusinessShopBuildArea"){
                str += Number($("#" + parameter.config.frm).find(".undergroundBusinessShopBuildArea").html());
            }
            str += Number(item);
            $("#" + parameter.config.frm).find("input[name='estimateSaleTotal']").val(str);
            $("#"+landEngineering.config.id).find("."+landEngineering.config.inputConfig.estimateSaleTotal.key).html(str);
            try {
                landEngineering.algsObj.totalGrossFloorArea();
            } catch (e) {
                console.log("函数名称被更改!");
                console.log(e);
            }
        }
        if (name == '可售面积') {
            var str = 0;
            str += Number($("#" + parameter.config.frm).find(".villaResidenceMaySaleArea").html());
            str += Number($("#" + parameter.config.frm).find(".strategyBusinessMaySaleArea").html());
            str += Number($("#" + parameter.config.frm).find(".workBuildMaySaleArea").html());
            str += Number($("#" + parameter.config.frm).find(".undergroundGarageMaySaleArea").html());
            str += Number($("#" + parameter.config.frm).find(".undergroundBusinessShopMaySaleArea").html());
            str += Number(item);
            $("#" + parameter.config.frm).find("input[name='smallGarageMaySaleArea']").val(str);
        }
        if (name == '单位售价') {
            var str = 0;
            str += Number($("#" + parameter.config.frm).find(".villaResidenceUnitPrice").html());
            str += Number($("#" + parameter.config.frm).find(".strategyBusinessUnitPrice").html());
            str += Number($("#" + parameter.config.frm).find(".workBuildUnitPrice").html());
            str += Number($("#" + parameter.config.frm).find(".undergroundGarageUnitPrice").html());
            str += Number($("#" + parameter.config.frm).find(".undergroundBusinessShopUnitPrice").html());
            str += Number(item);
            $("#" + parameter.config.frm).find("input[name='smallGarageTotalPrice']").val(str);
        }
    };

    $(function () {
        parameter.init();
    });
</script>