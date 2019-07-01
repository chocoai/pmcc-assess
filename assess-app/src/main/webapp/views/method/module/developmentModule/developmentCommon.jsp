<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">

    var developmentCommon = {};

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    developmentCommon.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    developmentCommon.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    developmentCommon.config = {
        commonParameter: {id: "commonDevelopmentParameterBase",handle:"commonDevelopmentParameterHandle"},
        head :{id: "commonDevelopmentHeadBase",handle:"commonDevelopmentHeadHandle"}
    };

    //头部参数
    developmentCommon.head = {
        getHtml:function () {
            return $("#"+developmentCommon.config.head.id).html() ;
        }
    } ;

    //收入类(参数)
    developmentCommon.parameter = {
        editableInit:function () {
            var frm = $("#"+developmentCommon.config.commonParameter.handle) ;
            var arr = [] ;
            frm.find("a").each(function (i,item) {
                var target = $(item) ;
                target.editable({
                    type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                    disabled: false,             //是否禁用编辑 ,默认 false
                    emptytext: "空文本",          //空值的默认文本
                    mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                    validate: function (value) { //字段验证
                        console.log(value) ;
                    }
                }) ;
            }) ;
        },
        handleCalculationA:function (that) {

        },
        handleCalculationB:function (that) {

        },
        handleCalculationC:function (that) {

        },
        handleCalculationD:function (that) {

        },
        handleCalculation:function (value) {
            if (developmentCommon.isNotBlank(value)){
                try {
                    console.log(value);
                    eval(value);
                } catch (e) {
                    console.log("没有相关定义的函数或者是属于子表单");
                    console.log(e);
                }
            }
        },
        getHtml:function () {
            return $("#"+developmentCommon.config.commonParameter.id).html() ;
        }
    } ;

</script>

<script type="text/html" id="commonDevelopmentParameterBase" data-title="收入类(参数)">
    <div id="commonDevelopmentParameterHandle">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h3>收入类(参数)</h3>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="col-md-12 col-sm-12 form-group">
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
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="strategyBusiness">
                            <td>商业</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="word">
                            <td>办公</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="hotel">
                            <td>宾馆</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="fitnessActivities">
                            <td>健身活动用房</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="estateManagement">
                            <td>物管用房</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="ownerActivity">
                            <td>业主活动用房</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="undergroundBusinessShop">
                            <td>地下商业建筑</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="motorVehicleParkingNumber">
                            <td>机动车停车位个数</td>
                            <td><a data-key="buildArea">0.00</a>
                            </td>
                            <td><a data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                        <tr data-key="motorVehicleParking">
                            <td>机动车停车位</td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationA(this)" data-key="buildArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationB(this)" data-key="maySaleArea">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationC(this)" data-key="maySaleAreaNext">0.00</a>
                            </td>
                            <td><a  onclick="developmentCommon.parameter.handleCalculationD(this)" data-key="unitPrice">0.00</a>
                            </td>
                            <td><a  data-key="remark"></a>
                            </td>
                        </tr>
                    </tbody>

                    <tfoot>
                        <tr data-key="estimateSaleTotal">
                            <td>预期销售合计</td>
                            <td><input type="text" class="form-control" name="buildArea"
                                       readonly="readonly" value="0.00" onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                            <td><input type="text" class="form-control" name="maySaleArea"
                                       readonly="readonly" value="0.00" onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                            <td><input type="text" class="form-control" name="maySaleAreaNext"
                                       readonly="readonly" value="0.00" onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="col-md-12 col-sm-12">
                <div class="row">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 control-label">预期销售合计</label>
                            <div class="col-md-2 col-sm-2">
                                <input type="text"
                                       placeholder="预期销售合计" class="form-control" name="estimateSaleTotal"
                                       readonly="readonly" value="0.00">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="commonDevelopmentHeadBase">
    <div id="commonDevelopmentHeadHandle">
        <div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    项目建设期(年)
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目建设期(年)"
                           class="form-control" name="projectConstructionPeriod">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    已开发时间(年)
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="已开发时间(年)"
                           class="form-control" name="developedTime" >
                </div>
            </div>
        </div>
    </div>
</script>