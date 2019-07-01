
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>收入类(参数)</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal" id="parameterFrm">
            <div class="form-group">
                <div class="col-md-12 col-sm-12">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <td>项目名称</td>
                            <td>规划建筑面积（㎡）</td>
                            <td>可售面积或个数售价(万元)</td>
                            <td>可售面积或个数售价(万元)</td>
                            <td>单位售价（元/㎡）</td>
                            <td>说明</td>
                        </tr>
                        </thead>

                        <tbody>
                        <tr data-key="villaResidence">
                            <td>住宅</td>
                            <td><a  onclick="parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="unitPrice"></a>
                            </td>
                        </tr>
                        <tr>
                            <td>商业</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="strategyBusinessBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="strategyBusinessMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="strategyBusinessMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="strategyBusinessUnitPrice">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>办公</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="workBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="workMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="workMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="workUnitPrice">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>宾馆</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="hotelBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="hotelMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="hotelMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="hotelUnitPrice">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td> 健身活动用房</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="fitnessActivitiesBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="fitnessActivitiesMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="fitnessActivitiesMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="fitnessActivitiesUnitPrice">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>  物管用房</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="estateManagementBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="estateManagementMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="estateManagementMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="estateManagementUnitPrice">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>  业主活动用房</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="ownerActivityBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="ownerActivityMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="ownerActivityMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="ownerActivityUnitPrice">0.00</a>
                            </td>
                        </tr>
                        <tr>
                            <td>地下商业建筑</td>
                            <td><a  onclick="parameter.handleCalculationA(this,'{model}')" data-key="undergroundBusinessShopBuildArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationB(this,'{model}')" data-key="undergroundBusinessShopMaySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationC(this,'{model}')" data-key="undergroundBusinessShopMaySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="parameter.handleCalculationD(this,'{model}')" data-key="undergroundBusinessShopUnitPrice">0.00</a>
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
    var parameter = {};

    parameter.handleCalculationA = function (that,model) {
        console.log(model) ;
        console.log(that) ;
    };

    parameter.handleCalculationB = function (that,model) {
        console.log(model) ;
        console.log(that) ;
    };

    parameter.handleCalculationC = function (that,model) {
        console.log(model) ;
        console.log(that) ;
    };

    parameter.handleCalculationD = function (that,model) {
        console.log(model) ;
        console.log(that) ;
    };

    parameter.config = {
        frm: "#parameterFrm"
    };

    /**
     * @author:  zch
     * 描述:开启行编辑
     * @date:2018-10-08
     **/
    parameter.init = function () {
        var frm = $(parameter.config.frm) ;
        var arr = [] ;
        frm.find("a").each(function (i,item) {
            var target = $(item) ;
            target.editable({
                type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                disabled: false,             //是否禁用编辑
                emptytext: "空文本",          //空值的默认文本
                mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                validate: function (value) { //字段验证
                    console.log(value) ;
                }
            }) ;
        }) ;
        

    };

    $(function () {
        parameter.init();
    });
</script>