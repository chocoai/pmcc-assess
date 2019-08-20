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

    //假设开发法 收入类保存
    developmentCommon.loadIncomeCategorySave = function (data,callback,errorCallback) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/mdDevelopmentIncomeCategory/saveMdDevelopmentIncomeCategory",
            data: {fomData:JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }else {
                    if (errorCallback) {
                        errorCallback(result.errmsg);
                    }
                }
            },
            error: function (e) {
                if (errorCallback) {
                    errorCallback(e);
                }
            }
        });
    };

    //假设开发法 收入类删除
    developmentCommon.deleteIncomeCategory = function (data,callback,errorCallback) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/mdDevelopmentIncomeCategory/deleteMdDevelopmentIncomeCategory",
            data: {ids:data.join(",")},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }else {
                    if (errorCallback) {
                        errorCallback(result.errmsg);
                    }
                }
            },
            error: function (e) {
                if (errorCallback) {
                    errorCallback(e);
                }
            }
        });
    };

    //假设开发法 收入类计算
    developmentCommon.calculationIncomeCategory = function (field,data) {
        var multiply = null;
        var arr = ['saleableArea','number' ,'unitPrice'] ;
        if (jQuery.inArray(field,arr) != -1){
            if (AssessCommon.isNumber(data.saleableArea)) {
                multiply = math.add(math.bignumber(0), math.bignumber(data.saleableArea));
            }
            if (AssessCommon.isNumber(data.number)) {
                multiply = math.add(multiply, math.bignumber(data.number));
            }
            if (AssessCommon.isNumber(data.unitPrice)) {
                if (multiply != null){
                    var c = math.chain(math.bignumber(data.unitPrice)).multiply(multiply).divide(10000).done();
                    data.totalSaleableAreaPrice = c.toString();
                }
            }
        }
        return data ;
    };

    //假设开发法 收入类table
    developmentCommon.loadIncomeCategoryTable = function (table,quarm,toolbar,callback) {
        var cols = [];
        cols.push({checkbox: true,width:"5%"});
        cols.push({field: 'name', title: '名称',width:"10%"});
        cols.push({field: 'plannedBuildingArea', title: '规划建筑面积',width:"10%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)){
                    return '必须是数字!';
                }
            }
        }});
        cols.push({field: 'totalSaleableAreaPrice', title: '总可售面积售价',width:"10%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)){
                    return '必须是数字!';
                }
            }
        }});
        cols.push({field: 'saleableArea', title: '可售面积',width:"10%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)){
                    return '必须是数字!';
                }
            }
        }});
        cols.push({field: 'number', title: '个数',width:"10%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)){
                    return '必须是数字!';
                }
            }
        }});
        cols.push({field: 'unitPrice', title: '单位售价',width:"10%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)){
                    return '必须是数字!';
                }
            }
        }});
        cols.push({field: 'assessArea', title: '评估面积',width:"15%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)){
                    return '必须是数字!';
                }
            }
        }});
        cols.push({field: 'remark', title: '说明',width:"20%", class: 'editable',editable: {
            type: 'text',
            validate: function (value) {
                if ($.trim(value) == '') {
                    return '说明不能为空!';
                }
            }
        }});
        var method = {
            onEditableSave:function (field, row, oldValue, $el) {
                row = developmentCommon.calculationIncomeCategory(field,row) ;
                table.bootstrapTable('updateByUniqueId', {id: row.id, row: row});
                developmentCommon.loadIncomeCategorySave(row,function () {
                    toastr.success('编辑成功!');
                    if (callback){
                        callback() ;
                    }
                },function () {
                    toastr.success('编辑失败!');
                });
            },
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess:function () {//加载成功时执行
                if (callback){
                    callback() ;
                }
            },
            onLoadError:function () {

            }
        } ;
        table.bootstrapTable('destroy');
        TableInit(table.attr("id"), "${pageContext.request.contextPath}/mdDevelopmentIncomeCategory/getBootstrapTableVo",cols,quarm, method);
        if (toolbar){
            if (toolbar.size() != 0) {
                var bootstrapTable = table.closest(".bootstrap-table");
                if (bootstrapTable.size() != 0) {
                    var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
                    if (fixedTableToolbar.size() != 0) {
                        fixedTableToolbar.append(toolbar.html());
                    }
                }
            }
        }
    };

    //假设开发法收入类模型添加的简单计算
    developmentCommon.handleIncomeCategory = function (_this) {
        if (!$(_this).val()){
            return false;
        }
        if (!$.isNumeric($(_this).val())){
            $(_this).val('');
            alert("请输入数字");
            return false;
        }
        var form = $(_this).closest("form") ;
        var data = formSerializeArray($(form)) ;
        data = developmentCommon.calculationIncomeCategory($(_this).attr("name"),data) ;
        $(form).initForm(data);
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
//            cols.push({field: 'tax', title: '税费'});
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
            <td> 二装工程</td>
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
            <td> 二装工程</td>
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
            <td> 二装工程</td>
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
            <td> 二装工程</td>
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

<div id="basicMdDevelopmentIncomeCategoryModalTool" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">收入类</h3>
            </div>
            <div class="panel-body">
                <form id="basicMdDevelopmentIncomeCategoryModalFrm" class="form-horizontal">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="id">
                    <input type="hidden" name="planDetailsId">
                    <input type="hidden" name="type">
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
                                规划建筑面积
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="plannedBuildingArea" placeholder="规划建筑面积" class="form-control"
                                       data-rule-number='true'>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                总可售面积售价(自动计算)
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" readonly="readonly" name="totalSaleableAreaPrice" placeholder="总可售面积售价(可售面积*单位售价/10000)" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                可售面积<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="saleableArea" placeholder="可售面积" class="form-control"
                                       required="required" onblur="developmentCommon.handleIncomeCategory(this)">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                个数
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="number" placeholder="个数" class="form-control"
                                       data-rule-number='true'>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                单位售价<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="unitPrice" placeholder="单位售价" class="form-control"
                                       required="required" onblur="developmentCommon.handleIncomeCategory(this)">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                评估面积
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="assessArea" placeholder="评估面积" class="form-control"
                                       data-rule-number='true'>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                说明
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <textarea name="remark" placeholder="说明" class="form-control"></textarea>
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

<script type="text/html" id="landMdDevelopmentIncomeCategoryFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="landEngineering.saveMdDevelopmentIncomeCategoryTable(this)">
        保存
    </button>
</script>

<script type="text/html" id="engineeringMdDevelopmentIncomeCategoryFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="underConstruction.saveMdDevelopmentIncomeCategoryTable(this)">
        保存
    </button>
</script>



