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
        commonParameter: {id: "commonDevelopmentParameterBase", handle: "commonDevelopmentParameterHandle"},
        architecturalA: {id: "architecturalA", handle: "architecturalAHandle"}
    };

    developmentCommon.architecturalA = {
        totalResult:function (that) {
            var value = $(that).val() ;
            if (!AssessCommon.isNumber(value)){
                alert("必须是数字!") ;
                return false;
            }
            var table = $(that).closest("table") ;
            var result = 0;
            table.find("tbody").find("tr").find("input[name='price']").each(function () {
                if (developmentCommon.isNotBlank($(this).val())){
                    result += Number($(this).val()) ;
                }
            });
            table.find("tfoot").find("tr").find("input[name='totalPrice']").val(result) ;
        } ,
        treeGirdParse:function (target) {
            target.find("table").treegrid();
        },
        getHtml: function () {
            return $("#" + developmentCommon.config.architecturalA.id).html();
        }
    } ;

    //收入类(参数)
    developmentCommon.parameter = {
        getDeclareEconomicIndicatorsContentList: function (callback) {
            $.ajax({
                url: getContextPath() + "/declareEconomicIndicatorsContent/getDeclareEconomicIndicatorsContentList",
                type: "post",
                dataType: "json",
                data: {indicatorsHeadId: '${declareBuildEngineeringAndEquipmentCenter.indicatorId}'},
                success: function (result) {
                    if (result.ret) {
                        callback(result.data);
                    } else {
                        callback([]);
                    }
                },
                error: function (result) {
                    callback([]);
                }
            });
        },
        editableInit: function () {
            developmentCommon.parameter.getDeclareEconomicIndicatorsContentList(function (data) {
                $("." + developmentCommon.config.commonParameter.handle).find("table").each(function () {
                    var table = $(this);
                    table.find("tbody").find("tr").each(function () {
                        var tr = $(this);
                        tr.find("a").each(function (i, item) {
                            var target = $(item);
                            var fun = target.attr("onclick");
                            target.editable({
                                type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                                disabled: false,             //是否禁用编辑 ,默认 false
                                emptytext: "空文本",          //空值的默认文本
                                mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                                validate: function (value) { //字段验证
                                    if (AssessCommon.isNumber(value)) {
                                        if (developmentCommon.isNotBlank(fun)) {
                                            fun += "(" + value + ")";
                                            eval(fun);
                                        }
                                    } else {
                                        return '必须是数字';
                                    }
                                }
                            });
                        });
                    });
                });
                $("." + developmentCommon.config.commonParameter.handle).find("table").each(function () {
                    var table = $(this);
                    table.find("tbody").find("tr").each(function () {
                        var tr = $(this);
                        //赋值
                        var name = tr.find("td").first().text();
                        if (data.length > 0) {
                            for (var j = 0; j < data.length; j++) {
                                var item = data[j];
                                if (item.name == name) {
                                    if (developmentCommon.isNotBlank(item.planIndex)) {
                                        tr.find("td").eq(1).find("a").text(item.planIndex);
                                    }
                                    if (developmentCommon.isNotBlank(item.salabilityNumber)) {
                                        tr.find("td").eq(2).find("a").text(item.salabilityNumber);
                                    }
                                    if (developmentCommon.isNotBlank(item.assessSalabilityNumber)) {
                                        tr.find("td").eq(3).find("a").text(item.assessSalabilityNumber);
                                    }
                                    if (developmentCommon.isNotBlank(item.remark)) {
                                        tr.find("td").eq(5).find("a").text(item.remark);
                                    }
                                    break;
                                }
                            }
                        }
                    });
                    //填充值以后触发
                    developmentCommon.parameter.handleCalculationWrite(table.find("tfoot").find("input[name='buildArea']"), 0, developmentCommon.parameter.handleCalculationA.name);
                    developmentCommon.parameter.handleCalculationWrite(table.find("tfoot").find("input[name='maySaleArea']"), 0, developmentCommon.parameter.handleCalculationB.name);
                    developmentCommon.parameter.handleCalculationWrite(table.find("tfoot").find("input[name='maySaleAreaNext']"), 0, developmentCommon.parameter.handleCalculationC.name);
                });
            });
        },
        handleCalculationA: function (value) {
            var that = this;
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='buildArea']"), value, that.handleCalculationA.name);
            });
        },
        handleCalculationB: function (value) {
            var that = this;
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='maySaleArea']"), value, that.handleCalculationB.name);
            });
        },
        handleCalculationC: function (value) {
            var that = this;
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='maySaleAreaNext']"), value, that.handleCalculationC.name);
            });
        },
        handleCalculationD: function (value) {
            var that = this;
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='unitPrice']"), value, that.handleCalculationD.name);
            });
        },
        handleCalculationWrite: function (input, value, fun) {
            var table = input.closest('table');
            var result = 0;
            table.find("tbody").find("a").each(function (i, item) {
                var target = $(item);
                var funName = target.attr("onclick");
                if (developmentCommon.isNotBlank(funName)) {
                    if (funName.indexOf(fun) != -1) {
                        var className = target.attr("class");
                        if (className.indexOf("editable-open") != -1) {
                            if (developmentCommon.isNotBlank(value)) {
                                result += value;
                            }
                        } else {
                            result += Number(target.html());
                        }
                    }
                }
            });
            //触发事件
            input.val(result).trigger('onblur');
        },
        handleCalculation: function (value) {
            if (developmentCommon.isNotBlank(value)) {
                try {
                    eval(value);
                } catch (e) {
                    console.log("没有相关定义的函数或者是属于子表单");
                }
            }
        },
        getHtml: function () {
            return $("#" + developmentCommon.config.commonParameter.id).html();
        }
    };

</script>

<script type="text/html" id="commonDevelopmentParameterBase" data-title="收入类(参数)">
    <div class="commonDevelopmentParameterHandle">
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="strategyBusiness">
                        <td>商业</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="word">
                        <td>办公</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="hotel">
                        <td>宾馆</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="fitnessActivities">
                        <td>健身活动用房</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="estateManagement">
                        <td>物管用房</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="ownerActivity">
                        <td>业主活动用房</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="undergroundBusinessShop">
                        <td>地下商业建筑</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="motorVehicleParkingNumber">
                        <td>机动车停车位个数(非面积不属于统计范围)</td>
                        <td><a data-key="buildArea">0.00</a>
                        </td>
                        <td><a data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    <tr data-key="motorVehicleParking">
                        <td>机动车停车位</td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationA" data-key="buildArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationB" data-key="maySaleArea">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationC"
                               data-key="maySaleAreaNext">0.00</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    </tbody>

                    <tfoot>
                    <tr data-key="estimateSaleTotal">
                        <td>预期销售合计</td>
                        <td><input type="text" class="form-control" name="buildArea"
                                   readonly="readonly" value="0.00"
                                   onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                        <td><input type="text" class="form-control" name="maySaleArea"
                                   readonly="readonly" value="0.00"
                                   onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                        <td><input type="text" class="form-control" name="maySaleAreaNext"
                                   readonly="readonly" value="0.00"
                                   onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                        <td><input type="text" class="form-control" name="unitPrice"
                                   readonly="readonly" value="0.00"
                                   onblur="developmentCommon.parameter.handleCalculation('{fun}')"></td>
                        <td><input type="text" placeholder="不可售建筑面积" class="form-control"></td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="architecturalA" data-title="简单树">
    <table class="table tree" id="architecturalAHandle">
        <thead>
            <tr>
                <th>工程名称</th>
                <th>单方造价(元/㎡)</th>
            </tr>
        </thead>

        <tbody>

            <tr class="treegrid-1" data-key="architecturalEngineering"  data-role="parent">
                <td>建筑工程 </td>
                <td>  </td>
            </tr>
            <tr class="treegrid-1-1 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
                <td> 地下基础 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-1-2 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
                <td> 地下室 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-1-3 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
                <td> 地上主体 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>

            <tr class="treegrid-2" data-key="installationWorks"  data-role="parent">
                <td>安装工程</td>
                <td>  </td>
            </tr>
            <tr class="treegrid-2-1 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 电气工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-2 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 给排水工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-3 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 燃气工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-4 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 消防工程含消防报警 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-5 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 通风空调工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-6 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 智能化系统工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-7 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 电梯工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>
            <tr class="treegrid-2-8 treegrid-parent-2" data-key="installationWorks" data-role="child">
                <td> 其它工程 </td>
                <td> <input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" data-rule-number="true" style="width: 100px;"> </td>
            </tr>

        </tbody>

        <tfoot>
            <tr class="treegrid-99" data-key="architecturalEngineering"  data-role="parent">
                <td>合计 </td>
                <td> <input type="text" readonly="readonly" name="totalPrice"> </td>
            </tr>
        </tfoot>
    </table>
</script>

