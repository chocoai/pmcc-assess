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
        architecturalA: {id: "architecturalA", handle: "architecturalAHandle", detail: 'architecturalADetail'},
        architecturalB: {id: "architecturalB", handle: "architecturalBHandle", detail: 'architecturalBDetail'}
    };

    developmentCommon.architecturalB = {
        get: function (type, callback) {
            try {
                var pid = 0;
                if (developmentCommon.isNotBlank('${mdDevelopment}')) {
                    if (developmentCommon.isNotBlank('${mdDevelopment.id}')) {
                        pid = '${mdDevelopment.id}';
                    }
                }
                developmentCommon.getMdArchitecturalObjList(type, AssessDBKey.MdDevelopment, pid, '${projectPlanDetails.id}', callback);
            } catch (e) {
            }
        },
        getData: function (type, databaseName, pid, planDetailsId, callback) {
            developmentCommon.getMdArchitecturalObjList(type, databaseName, pid, planDetailsId, callback);
        },
        initData: function (table, data) {
            var tbody = table.find('tbody');
            var result = 0;
            tbody.find("tr").each(function (i, tr) {
                var dataKey = $(tr).attr('data-key');
                var role = $(tr).attr('data-role');
                var name = $(tr).find("td").first().text();
                var value = $(tr).find("input[name='price']").first().val();
                $.each(data, function (i, item) {
                    if (item.role == role) {
                        if (dataKey == item.dataKey) {
                            if (name == item.name) {
                                var a = undefined;
                                var b = undefined;
                                if ($(tr).find("input[name='price']").first().size() != 0) {
                                    if (item.value) {
                                        $(tr).find("input[name='price']").first().val(item.value);
                                        a = item.value;
                                    }
                                }
                                if ($(tr).find("input[name='valuationDateDegreeCompletion']").first().size() != 0) {
                                    if (item.valuationDateDegreeCompletion) {
                                        var valuationDateDegreeCompletion = parseFloat(item.valuationDateDegreeCompletion);
                                        valuationDateDegreeCompletion /= 100;
                                        $(tr).find("input[name='valuationDateDegreeCompletion']").first().val(item.valuationDateDegreeCompletion);
                                        $(tr).find("input[name='valuationDateDegreeCompletion']").first().attr('data-value', valuationDateDegreeCompletion);
                                        b = valuationDateDegreeCompletion;
                                    }
                                }
                                if (developmentCommon.isNotBlank(a) && developmentCommon.isNotBlank(b)) {
                                    var c = Number(a) * Number(b);
                                    $(tr).find("td").last().text(c.toFixed(2));
                                    result += c;
                                }
                            }
                        }
                    }
                });
            });
            table.find("tfoot").find("tr").find("input[name='totalPrice']").val(result.toFixed(2));
        },
        totalResult: function (that) {
            var tr = $(that).closest("tr");
            var value = $(that).val();
            var a = tr.find("input[name='price']").val();
            var b = tr.find("input[name='valuationDateDegreeCompletion']").attr('data-value');
            if (developmentCommon.isNotBlank(value)) {
                if (!AssessCommon.isNumber(value)) {
                    var reg = new RegExp(/^[0-9]+%$/);
                    if (!reg.test(value)) {
                        $(that).val('');
                        alert("不符合，必须是数字!");
                        return false;
                    }
                }
                if (!AssessCommon.isNumber(a)) {
                    return false;
                }
                if (!AssessCommon.isNumber(b)) {
                    return false;
                }
                var c = Number(a) * Number(b);
                tr.find("td").last().text(c.toFixed(2));
            } else {
                tr.find("td").last().text('');
            }
            var table = $(that).closest("table");
            var result = 0;
            table.find("tbody").find("tr").each(function (i, item) {
                var v = $(item).children("td").last().html();
                if (developmentCommon.isNotBlank(v)) {
                    if (AssessCommon.isNumber(v)) {
                        result += Number(v);
                    }
                }
            });
            table.find("tfoot").find("tr").find("input[name='totalPrice']").val(result.toFixed(2));
        },
        treeGirdParse: function (target) {
            target.find("table").treegrid();
        },
        getHtml: function () {
            return $("#" + developmentCommon.config.architecturalB.id).html();
        },
        getHtmlDetail: function () {
            return $("#" + developmentCommon.config.architecturalB.detail).html();
        },
        getFomData: function (table) {
            var tbody = table.find('tbody');
            var data = [];
            tbody.find("tr").each(function (i, tr) {
                var dataKey = $(tr).attr('data-key');
                var role = $(tr).attr('data-role');
                var name = $(tr).find("td").first().text();
                var value = $(tr).find("input[name='price']").first().val();
                var valuationDateDegreeCompletion = $(tr).find("input[name='valuationDateDegreeCompletion']").first().val();
                data.push({
                    dataKey: dataKey,
                    role: role,
                    name: name,
                    value: value,
                    valuationDateDegreeCompletion: valuationDateDegreeCompletion
                });
            });
            return data;
        }
    };

    developmentCommon.saveMdArchitecturalObj = function (data, type, databaseName, pid, planDetailsId, callback) {
        var item = {
            forData: JSON.stringify(data),
            type: type,
            planDetailsId: planDetailsId,
            pid: pid,
            databaseName: databaseName
        };
        developmentCommon.removeMdArchitecturalObj(item.type, item.databaseName, item.pid, item.planDetailsId, function () {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/mdArchitecturalObj/saveMdArchitecturalObj",
                data: item,
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        });
    };

    developmentCommon.removeMdArchitecturalObj = function (type, databaseName, pid, planDetailsId, callback) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/mdArchitecturalObj/removeMdArchitecturalObj",
            data: {type: type, databaseName: databaseName, pid: pid, planDetailsId: planDetailsId},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    developmentCommon.getMdArchitecturalObjList = function (type, databaseName, pid, planDetailsId, callback) {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/mdArchitecturalObj/getMdArchitecturalObjList",
            data: {type: type, databaseName: databaseName, pid: pid, planDetailsId: planDetailsId},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    developmentCommon.architecturalA = {
        get: function (type, callback) {
            var pid = 0;
            if (developmentCommon.isNotBlank('${mdDevelopment}')) {
                if (developmentCommon.isNotBlank('${mdDevelopment.id}')) {
                    pid = '${mdDevelopment.id}';
                }
            }
            developmentCommon.getMdArchitecturalObjList(type, AssessDBKey.MdDevelopment, pid, '${projectPlanDetails.id}', callback);
        },
        totalResult: function (that) {
            var value = $(that).val();
            if (developmentCommon.isNotBlank(value)) {
                if (!AssessCommon.isNumber(value)) {
                    $(that).val('');
                    alert("必须是数字!");
                    return false;
                }
            }
            var table = $(that).closest("table");
            var result = math.bignumber(0);
            table.find("tbody").find("tr").find("input[name='price']").each(function () {
                if (developmentCommon.isNotBlank($(this).val())) {
                    result = math.add(result, math.bignumber($(this).val()))
                }
            });
            table.find("tfoot").find("tr").find("input[name='totalPrice']").val(result.toString());
        },
        treeGirdParse: function (target) {
            target.find("table").treegrid();
        },
        getFomData: function (table) {
            var tbody = table.find('tbody');
            var data = [];
            tbody.find("tr").each(function (i, tr) {
                var dataKey = $(tr).attr('data-key');
                var role = $(tr).attr('data-role');
                var name = $(tr).find("td").first().text();
                var value = $(tr).find("input[name='price']").first().val();
                data.push({dataKey: dataKey, role: role, name: name, value: value});
            });
            return data;
        },
        initData: function (table, data) {
            var tbody = table.find('tbody');
            var result = 0;
            tbody.find("tr").each(function (i, tr) {
                var dataKey = $(tr).attr('data-key');
                var role = $(tr).attr('data-role');
                var name = $(tr).find("td").first().text();
                var value = $(tr).find("input[name='price']").first().val();
                $.each(data, function (i, item) {
                    if (item.role == role) {
                        if (dataKey == item.dataKey) {
                            if (name == item.name) {
                                if ($(tr).find("input[name='price']").first().size() != 0) {
                                    $(tr).find("input[name='price']").first().val(item.value);
                                    if (AssessCommon.isNumber(item.value)) {
                                        result += Number(item.value);
                                    }
                                }
                            }
                        }
                    }
                });
            });
            table.find("tfoot").find("tr").find("input[name='totalPrice']").val(result);
        },
        getHtml: function () {
            return $("#" + developmentCommon.config.architecturalA.id).html();
        },
        getHtmlDetail: function () {
            return $("#" + developmentCommon.config.architecturalA.detail).html();
        }
    };

    //收入类(参数)
    developmentCommon.parameter = {
        getDeclareEconomicIndicatorsContentList: function (callback) {
            $.ajax({
                url: getContextPath() + "/declareEconomicIndicatorsContent/getEntityListByPid",
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
        initData: function (table, data, equals) {
            if (table.size() == 0) {
                return false;
            }
            var buildArea = 0;
            var maySaleArea = 0;
            var maySaleAreaNext = 0;
            table.find("tbody").find("tr").each(function (i, tr) {
                var dataKey = $(tr).attr('data-key');
                var name = $(tr).find("td").first().text();
                $.each(data, function (j, item) {
                    if (item.name == name) {
                        if (equals) {
                        } else {
                            equals = item.dataKey == dataKey;
                        }
                        if (equals) {
                            if (AssessCommon.isNumber(item.buildArea)) {
                                buildArea += Number(item.buildArea);
                            }
                            if (AssessCommon.isNumber(item.maySaleAreaNext)) {
                                maySaleAreaNext += Number(item.maySaleAreaNext);
                            }
                            if (AssessCommon.isNumber(item.unitPrice)) {
                                var multiply = math.bignumber(0);
                                if (AssessCommon.isNumber(item.number)) {
                                    multiply = math.add(multiply, math.bignumber(item.number));
                                }
                                if (AssessCommon.isNumber(item.maySaleAreaNext)) {
                                    multiply = math.add(multiply, math.bignumber(item.maySaleAreaNext));
                                }
                                if (!AssessCommon.isNumber(item.maySaleArea)) {

                                }
                                var c = math.chain(math.bignumber(item.unitPrice)).multiply(multiply).done();
                                item.maySaleArea = c.toString() ;
                            }
                            if (AssessCommon.isNumber(item.maySaleArea)) {
                                maySaleArea += Number(item.maySaleArea);
                            }
                            $(tr).find("td").eq(1).find("a").text(item.buildArea);
                            $(tr).find("td").eq(2).find("a").text(item.maySaleArea);
                            $(tr).find("td").eq(3).find("a").text(item.maySaleAreaNext);
                            $(tr).find("td").eq(4).find("a").text(item.number);
                            $(tr).find("td").eq(5).find("a").text(item.unitPrice);
                            $(tr).find("td").eq(6).find("a").text(item.assessArea);
                            $(tr).find("td").eq(7).find("a").text(item.remark);
                        }
                    }
                });
            });
            table.find("tfoot").find("input[name='buildArea']").val(buildArea);
            table.find("tfoot").find("input[name='maySaleArea']").val(maySaleArea);
            table.find("tfoot").find("input[name='maySaleAreaNext']").val(maySaleAreaNext);
        },
        getFomData: function (table) {
            var data = [];
            table.find("tbody").find("tr").each(function (i, tr) {
                var item = {};
                var dataKey = $(tr).attr('data-key');
                item.name = $(tr).find("td").first().text();
                item.buildArea = $(tr).find("td").eq(1).find("a").text();
                item.maySaleArea = $(tr).find("td").eq(2).find("a").text();
                item.maySaleAreaNext = $(tr).find("td").eq(3).find("a").text();
                item.number = $(tr).find("td").eq(4).find("a").text();
                item.unitPrice = $(tr).find("td").eq(5).find("a").text();
                item.assessArea = $(tr).find("td").eq(6).find("a").text();
                item.remark = $(tr).find("td").eq(7).find("a").text();
                item.dataKey = dataKey;
                data.push(item);
            });
            console.log(data) ;
            return data;
        },
        editableInit: function (callback) {
            var handle = $("." + developmentCommon.config.commonParameter.handle);
            developmentCommon.parameter.getDeclareEconomicIndicatorsContentList(function (data) {
                handle.find("table").each(function () {
                    var table = $(this);
                    table.find("tbody").find("tr").each(function () {
                        var tr = $(this);
                        tr.find("a").each(function (i, item) {
                            var target = $(item);
                            var fun = target.attr("onclick");
                            var dataKey = target.attr("data-key");
                            target.editable({
                                type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                                disabled: false,             //是否禁用编辑 ,默认 false
                                emptytext: "数值说明",          //空值的默认文本
                                mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                                validate: function (value) { //字段验证
                                    if (AssessCommon.isNumber(value)) {
                                        if (developmentCommon.isNotBlank(fun)) {
                                            if (fun.indexOf('handleCalculationA') != -1) {
                                                developmentCommon.parameter.handleCalculationA(value);
                                            }
                                            if (fun.indexOf('handleCalculationB') != -1) {
                                                developmentCommon.parameter.handleCalculationB(tr, value);
                                            }
                                            if (fun.indexOf('handleCalculationC') != -1) {
                                                developmentCommon.parameter.handleCalculationC(value, target);
                                            }
                                            if (fun.indexOf('handleCalculationD') != -1) {
                                                developmentCommon.parameter.handleCalculationD(value, target);
                                            }
                                            if (fun.indexOf('handleCalculationE') != -1) {
                                                developmentCommon.parameter.handleCalculationE(value, target);
                                            }
                                        }
                                    } else {
                                        if (dataKey != 'remark') {
                                            return '必须是数字';
                                        }
                                    }
                                },
                                display: function (value) {
                                    $(this).text(value);
                                }
                            });
                        });
                    });
                    var item = [];
                    var dataA = [];
                    if (data.length >= 1) {
                        dataA = data.concat([]);
                    }
                    if (dataA.length >= 1) {
                        $.each(dataA, function (i, obj) {
                            var vo = {};
                            if (obj.childData) {
                                var childData = JSON.parse(obj.childData);
                                dataA = dataA.concat(childData);
                            }
                        });
                    }
                    if (dataA.length >= 1) {
                        $.each(dataA, function (i, obj) {
                            var vo = {};
                            if (obj.planIndex) {
                                vo.buildArea = obj.planIndex;
                            }
                            if (obj.salabilityNumber) {
                                vo.maySaleAreaNext = obj.salabilityNumber;
                            }
                            if (obj.assessSalabilityNumber) {
                                vo.number = obj.assessSalabilityNumber;
                            }
                            if (obj.remark) {
                                vo.remark = obj.remark;
                            }
                            if (obj.name) {
                                vo.name = obj.name;
                            }
                            item.push(vo);
                        });
                    }
                    developmentCommon.parameter.initData(table, item, true);
                });
                if (callback) {
                    callback();
                }
            });
        },
        handleCalculationA: function (value) {
            var that = this;
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='buildArea']"), value, that.handleCalculationA.name);
            });
        },
        handleCalculationB: function (tr, data) {
            var that = this;
            if (tr.size() == 0) {
                return false;
            }
            var unitPrice = tr.find("a[data-key='unitPrice']").text();
            var maySaleAreaNext = tr.find("a[data-key='maySaleAreaNext']").text();
            var number = tr.find("a[data-key='number']").text();
            var multiply = null;
            if (data.unitPrice) {
                unitPrice = data.unitPrice;
                multiply = math.bignumber(0);
                if (AssessCommon.isNumber(number)) {
                    multiply = math.add(multiply, math.bignumber(number));
                }
                if (AssessCommon.isNumber(maySaleAreaNext)) {
                    multiply = math.add(multiply, math.bignumber(maySaleAreaNext));
                }
            }
            if (data.maySaleAreaNext) {
                multiply = math.bignumber(data.maySaleAreaNext);
                if (AssessCommon.isNumber(number)) {
                    multiply = math.add(multiply, math.bignumber(number));
                }
            }
            if (data.number) {
                multiply = math.bignumber(data.number);
                if (AssessCommon.isNumber(maySaleAreaNext)) {
                    multiply = math.add(multiply, math.bignumber(maySaleAreaNext));
                }
            }
            if (multiply != null) {
                if (AssessCommon.isNumber(unitPrice)) {
                    var c = math.chain(math.bignumber(unitPrice)).multiply(multiply).done();
                    data.maySaleArea = c.toString();
                }
            }
            if (data.maySaleArea) {
                tr.find("a[data-key='maySaleArea']").text(data.maySaleArea);
            }
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='maySaleArea']"), data.maySaleArea, that.handleCalculationB.name);
            });
        },
        handleCalculationC: function (value, target) {
            var that = this;
            this.handleCalculationB(target.closest("tr"), {maySaleAreaNext: value});
            $("." + developmentCommon.config.commonParameter.handle).each(function () {
                that.handleCalculationWrite($(this).find("table").find("tfoot").find("input[name='maySaleAreaNext']"), value, that.handleCalculationC.name);
            });
        },
        handleCalculationD: function (value, target) {
            var that = this;
            this.handleCalculationB(target.closest("tr"), {unitPrice: value});
        },
        handleCalculationE: function (value, target) {
            this.handleCalculationB(target.closest("tr"), {number: value});
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
                        if (className.indexOf("open") != -1) {
                            if (developmentCommon.isNotBlank(value)) {
                                result += Number(value);
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

    developmentCommon.infrastructureChildren = {
        getDataList: function (data, callback) {
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/getMdDevelopmentInfrastructureChildrenList",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    } else {
                        Alert("失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        },
        save: function (data, callback) {
           var item = [] ;
           item.push(data) ;
            developmentCommon.infrastructureChildren.saveArray(item,callback) ;
        },
        saveArray: function (data, callback) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/save",
                data: {forData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        },
        delete: function (data, callback) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/delete",
                data: {id: data.join(",")},
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(result.data);
                        }
                    } else {
                        Alert("失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        },
        loadTable: function (pid, planDetailsId, type, selectId, toolbar) {
            var cols = [];
            cols.push({checkbox: true});
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'number', title: '金额'});
            cols.push({field: 'tax', title: '税费'});
            selectId.bootstrapTable('destroy');
            TableInit(selectId.attr("id"), "${pageContext.request.contextPath}/mdDevelopmentInfrastructureChildren/getBootstrapTableVo?pid=" + pid + "&planDetailsId=" + planDetailsId + "&type=" + type, cols, {}, {
                showColumns: true,
                showRefresh: true,
                search: false
            });
            if (toolbar.size() != 0) {
                var bootstrapTable = selectId.closest(".bootstrap-table");
                if (bootstrapTable.size() != 0) {
                    var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
                    if (fixedTableToolbar.size() != 0) {
                        fixedTableToolbar.append(toolbar.html());
                    }
                }
            }
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
                        <td>总可售面积售价(万元)</td>
                        <td>可售面积</td>
                        <td>个数</td>
                        <td>单位售价（元/㎡）</td>
                        <td>评估面积</td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
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
                        <td><a onclick="developmentCommon.parameter.handleCalculationE" data-key="number">0</a>
                        </td>
                        <td><a onclick="developmentCommon.parameter.handleCalculationD" data-key="unitPrice">0.00</a>
                        </td>
                        <td><a data-key="assessArea">0.00</a></td>
                        <td><a data-key="remark"></a>
                        </td>
                    </tr>
                    </tbody>

                    <tfoot>
                    <tr data-key="estimateSaleTotal">
                        <td>预期销售合计</td>

                        <td><input type="text" class="form-control" name="buildArea"
                                   readonly="readonly" value="0.00"
                                   onblur="developmentCommon.parameter.handleCalculation('{funA1}')"></td>

                        <td><input type="text" class="form-control" name="maySaleArea"
                                   readonly="readonly" value="0.00"
                                   onblur="developmentCommon.parameter.handleCalculation('{funA2}')"></td>

                        <td colspan="2"><input type="text" class="form-control" name="maySaleAreaNext"
                                               readonly="readonly" value="0.00"
                                               onblur="developmentCommon.parameter.handleCalculation('{funA3}')"></td>
                        <td colspan="3"><input type="text" name="unsaleableBuildingArea"
                                               onblur="developmentCommon.parameter.handleCalculation('{funA5}')"
                                               placeholder="不可售建筑面积" class="form-control"
                                               value="${mdDevelopment.unsaleableBuildingArea}"></td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="architecturalB" data-title="复杂树">
    <table class="table tree" id="architecturalBHandle">
        <thead>
        <tr>
            <th>工程名称</th>
            <th>单方造价(元/㎡)</th>
            <th>估价时点完工程度</th>
            <th>估价时点单价(元/㎡)</th>
        </tr>
        </thead>

        <tbody>

        <tr class="treegrid-1" data-key="architecturalEngineering" data-role="parent">
            <td>建筑工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-1 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下基础</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-2 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下室</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-3 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地上主体</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-2" data-key="installationWorks" data-role="parent">
            <td>安装工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-1 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电气工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-2 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 给排水工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-3 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 燃气工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-4 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 消防工程含消防报警</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-5 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 通风空调工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-6 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 智能化系统工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-7 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电梯工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-8 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-3" data-key="decorationEngineering" data-role="parent">
            <td>装饰工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-1 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 楼地面工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-2 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙墙柱面工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-3 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙墙柱面工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-4 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 天棚工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-5 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 门窗工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-6 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-7 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-8 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-4" data-key="ancillaryWorks" data-role="parent">
            <td>附属工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-1 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 道路</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-2 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 围墙</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-3 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 大门</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-4 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 绿化</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-5 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 园林</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-6 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 景观</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-5" data-key="secondInstallationProject" data-role="parent">
            <td>二装工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-5-1 treegrid-parent-5" data-key="secondInstallationProject" data-role="child">
            <td> 二装工程 子项</td>
            <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                       placeholder="(数字)" name="valuationDateDegreeCompletion" data-rule-number="true"
                       style="width: 100px;"></td>
            <td></td>
        </tr>
        </tbody>

        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" class="form-control" readonly="readonly" name="totalPrice"></td>
        </tr>
        </tfoot>
    </table>
</script>

<script type="text/html" id="architecturalBDetail" data-title="复杂树 详情(审批用)">
    <table class="table tree">
        <thead>
        <tr>
            <th>工程名称</th>
            <th>单方造价(元/㎡)</th>
            <th>估价时点完工程度</th>
            <th>估价时点单价(元/㎡)</th>
        </tr>
        </thead>

        <tbody>

        <tr class="treegrid-1" data-key="architecturalEngineering" data-role="parent">
            <td>建筑工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-1 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下基础</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-2 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下室</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-3 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地上主体</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-2" data-key="installationWorks" data-role="parent">
            <td>安装工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-1 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电气工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-2 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 给排水工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-3 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 燃气工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-4 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 消防工程含消防报警</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-5 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 通风空调工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-6 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 智能化系统工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-7 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电梯工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-8 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-3" data-key="decorationEngineering" data-role="parent">
            <td>装饰工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-1 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 楼地面工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-2 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙墙柱面工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-3 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙墙柱面工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-4 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 天棚工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-5 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 门窗工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-6 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-7 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-8 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-4" data-key="ancillaryWorks" data-role="parent">
            <td>附属工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-1 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 道路</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-2 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 围墙</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-3 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 大门</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-4 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 绿化</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-5 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 园林</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-6 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 景观</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>

        <tr class="treegrid-5" data-key="secondInstallationProject" data-role="parent">
            <td>二装工程</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-5-1 treegrid-parent-5" data-key="secondInstallationProject" data-role="child">
            <td> 二装工程 子项</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="(数字)"
                       name="valuationDateDegreeCompletion" data-rule-number="true" style="width: 100px;"></td>
            <td></td>
        </tr>

        </tbody>

        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" class="form-control" readonly="readonly" name="totalPrice"></td>
        </tr>
        </tfoot>
    </table>
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

        <tr class="treegrid-1" data-key="architecturalEngineering" data-role="parent">
            <td>建筑工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-1-1 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下基础</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-1-2 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下室</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-1-3 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地上主体</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-2" data-key="installationWorks" data-role="parent">
            <td>安装工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-2-1 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电气工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-2 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 给排水工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-3 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 燃气工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-4 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 消防工程含消防报警</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-5 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 通风空调工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-6 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 智能化系统工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-7 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电梯工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-8 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-3" data-key="decorationEngineering" data-role="parent">
            <td>装饰工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-3-1 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 楼地面工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-2 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙墙柱面工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-3 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙墙柱面工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-4 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 天棚工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-5 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 门窗工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-6 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-7 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-8 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-4" data-key="ancillaryWorks" data-role="parent">
            <td>附属工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-4-1 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 道路</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-2 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 围墙</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-3 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 大门</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-4 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 绿化</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-5 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 园林</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-6 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 景观</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-5" data-key="secondInstallationProject" data-role="parent">
            <td>二装工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-5-1 treegrid-parent-5" data-key="secondInstallationProject" data-role="child">
            <td> 二装工程 子项</td>
            <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)"
                       name="price" data-rule-number="true" style="width: 100px;"></td>
        </tr>
        </tbody>

        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" class="form-control" readonly="readonly" name="totalPrice"></td>
        </tr>
        </tfoot>
    </table>
</script>

<script type="text/html" id="architecturalADetail" data-title="简单树 详情(审批用)">
    <table class="table tree">
        <thead>
        <tr>
            <th>工程名称</th>
            <th>单方造价(元/㎡)</th>
        </tr>
        </thead>

        <tbody>

        <tr class="treegrid-1" data-key="architecturalEngineering" data-role="parent">
            <td>建筑工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-1-1 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下基础</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-1-2 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下室</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-1-3 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地上主体</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-2" data-key="installationWorks" data-role="parent">
            <td>安装工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-2-1 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电气工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-2 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 给排水工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-3 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 燃气工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-4 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 消防工程含消防报警</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-5 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 通风空调工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-6 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 智能化系统工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-7 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电梯工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-8 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-3" data-key="decorationEngineering" data-role="parent">
            <td>装饰工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-3-1 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 楼地面工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-2 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙墙柱面工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-3 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙墙柱面工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-4 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 天棚工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-5 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 门窗工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-6 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-7 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-8 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 其它工程</td>
            <td><input type="text" class="form-control" readonly="readonly" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-4" data-key="ancillaryWorks" data-role="parent">
            <td>附属工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-4-1 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 道路</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-2 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 围墙</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-3 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 大门</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-4 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 绿化</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-5 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 园林</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-6 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 景观</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-5" data-key="secondInstallationProject" data-role="parent">
            <td>二装工程</td>
            <td></td>
        </tr>
        <tr class="treegrid-5-1 treegrid-parent-5" data-key="secondInstallationProject" data-role="child">
            <td> 二装工程 子项</td>
            <td><input type="text" class="form-control" readonly="readonly" onblur="" placeholder="单价(数字)" name="price"
                       data-rule-number="true" style="width: 100px;"></td>
        </tr>

        </tbody>

        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" class="form-control" readonly="readonly" name="totalPrice"></td>
        </tr>
        </tfoot>
    </table>
</script>

<div id="basicMdDevelopmentInfrastructureChildrenModalTool" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">子类税费</h3>
            </div>
            <div class="panel-body">
                <form id="basicMdDevelopmentInfrastructureChildrenModalFrm" class="form-horizontal">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="name" class="form-control" required="required"
                                       placeholder="名称">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="number" placeholder="金额" class="form-control"
                                       data-rule-number='true'
                                       required="required">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                税费
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="tax" data-rule-number='true' class="form-control"
                                       placeholder="(数字)">
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

<script type="text/html" id="landEngineeringMdDevelopmentInfrastructureFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="landEngineering.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>


<script type="text/html" id="underConstructionMdDevelopmentInfrastructureFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="underConstruction.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>


<script type="text/html" id="underConstructionMdDevelopmentInfrastructureFooterX">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="construction.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>



