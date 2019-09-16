/**
 * Created by zch on 2019-8-29.
 */


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
    getData: function (type, databaseName, pid, planDetailsId, callback) {
        developmentCommon.getMdArchitecturalObjList(type, databaseName, pid, planDetailsId, callback);
    },//树中的运算
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
    addChild: function (tr, html) {
        var childs = tr.treegrid('getChildNodes');
        if (childs.length <= 0) {
            tr.after(html);
        } else {
            //如果最后一个子项下还有子项则在子项的子项后添加元素
            var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
            if (subChilds.length <= 0) {
                $(childs.get(childs.length - 1)).after(html);
            } else {
                $(subChilds.get(subChilds.length - 1)).after(html);
            }
        }
        tr.closest('table').treegrid();
    },//动态添加树
    appendHtml: function (target, obj, attribute, price ,callback) {
        target.append($("#" + developmentCommon.config.architecturalB.id).html());
        developmentCommon.architecturalB.treeGirdParse(target);
        var table = target.find("table");
        var tbody = table.find("tbody");
        //第一级
        AssessCommon.loadDataDicByKey(AssessDicKey.build_security_engineering_project_market_cost, '', function (html, dataTree) {
            $.each(dataTree, function (index, data) {
                var key = data.fieldName.split(".").join("_");
                var parentHtml = $("#architecturalBModelParent").html();
                parentHtml = parentHtml.replace(/{index}/g, index);
                parentHtml = parentHtml.replace(/{key}/g, key);
                parentHtml = parentHtml.replace(/{name}/g, data.name);
                tbody.append(parentHtml);
                //第二级
                AssessCommon.loadDataDicByPid(data.id, '', function (html, item) {
                    var tr = tbody.find("[data-key='" + key + "']");
                    $.each(item, function (childIndex, n) {
                        var childHtml = $("#architecturalBModelChildren").html();
                        childHtml = childHtml.replace(/{index}/g, index);
                        childHtml = childHtml.replace(/{key}/g, key);
                        childHtml = childHtml.replace(/{name}/g, n.name);
                        childHtml = childHtml.replace(/{childIndex}/g, (childIndex + 1));
                        developmentCommon.architecturalB.addChild(tr, childHtml);
                        var childTr = tbody.find("[data-key='" + key + "'][data-role='child']");
                        //赋值
                        developmentCommon.architecturalB.initTrData(childTr[childIndex], obj, attribute,callback);
                    });
                });
            });
            developmentCommon.architecturalB.treeGirdParse(target);
        });
        table.find("tfoot").find("tr").find("input[name='totalPrice']").val(price);
    },
    //根据表格行来赋值
    initTrData: function (tr, data, attribute,callback) {
        if (callback){
            callback(tr) ;
        }
        if (!$.isArray(data)){
            return false;
        }
        var dataKey = $(tr).attr('data-key');
        var name = $(tr).find("td").first().text();
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            if (dataKey != item.dataKey) {
                continue;
            }
            if (name != item.name) {
                continue;
            }
            var a = undefined;
            var b = undefined;
            if (item.remark) {
                $(tr).find("input[name='remark']").first().val(item.remark);
            }
            if (item.price) {
                $(tr).find("input[name='price']").first().val(item.price);
                a = item.price;
            }
            if (item.valuationDateDegreeCompletion) {
                var valuationDateDegreeCompletion = parseFloat(item.valuationDateDegreeCompletion);
                valuationDateDegreeCompletion /= 100;
                $(tr).find("input[name='valuationDateDegreeCompletion']").first().val(item.valuationDateDegreeCompletion);
                $(tr).find("input[name='valuationDateDegreeCompletion']").first().attr('data-value', valuationDateDegreeCompletion);
                b = valuationDateDegreeCompletion;
            }
            if ($.isNumeric(a) && $.isNumeric(b)) {
                var c = Number(a) * Number(b);
                $(tr).find("td").last().text(c.toFixed(2));
            }
            if (attribute){
                $(tr).find("input").attr(attribute);
            }
        }
    },//收集数据值
    getFomData: function (table) {
        var tbody = table.find('tbody');
        var data = [];
        tbody.find("tr").each(function (i, tr) {
            var dataKey = $(tr).attr('data-key');
            var role = $(tr).attr('data-role');
            var name = $(tr).find("td").first().text();
            var price = $(tr).find("input[name='price']").first().val();
            var remark = $(tr).find("input[name='remark']").first().val();
            var valuationDateDegreeCompletion = $(tr).find("input[name='valuationDateDegreeCompletion']").first().val();
            data.push({
                dataKey: dataKey,
                role: role,
                name: name,
                price: price,
                remark: remark,
                valuationDateDegreeCompletion: valuationDateDegreeCompletion
            });
        });
        return data;
    }
};

developmentCommon.saveMdArchitecturalObj = function (data, type, databaseName, pid, planDetailsId, callback) {
    var item = {
        type: type,
        planDetailsId: planDetailsId,
        pid: pid,
        databaseName: databaseName
    };
    developmentCommon.removeMdArchitecturalObj(item.type, item.databaseName, item.pid, item.planDetailsId, function () {
        developmentCommon.saveMdArchitecturalObj2(data, item, callback);
    });
};

developmentCommon.saveMdArchitecturalObj2 = function (data, obj, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() +"/mdArchitecturalObj/saveMdArchitecturalObj",
        data: {jsonString: JSON.stringify(data), forData: JSON.stringify(obj)},
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
};

developmentCommon.getMdArchitecturalObjById = function (id, callback) {
    $.ajax({
        type: "get",
        url: getContextPath() +"/mdArchitecturalObj/getMdArchitecturalObjById",
        data: {id: id},
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

developmentCommon.deleteMdArchitecturalObjById = function (id, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() +"/mdArchitecturalObj/deleteMdArchitecturalObjById",
        data: {id: id},
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

developmentCommon.removeMdArchitecturalObj = function (type, databaseName, pid, planDetailsId, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() +"/mdArchitecturalObj/removeMdArchitecturalObj",
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
    developmentCommon.getMdArchitecturalObjList2({type: type, databaseName: databaseName, pid: pid, planDetailsId: planDetailsId} ,callback) ;
};

developmentCommon.getMdArchitecturalObjList2 = function (data, callback) {
    $.ajax({
        type: "get",
        url:getContextPath() + "/mdArchitecturalObj/getMdArchitecturalObjList",
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
};

developmentCommon.saveMdCalculatingMethodEngineeringCost = function (data, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() +"/mdCalculatingMethodEngineeringCost/saveMdCalculatingMethodEngineeringCost",
        data: {formData: JSON.stringify(data)},
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
};

developmentCommon.deleteMdCalculatingMethodEngineeringCostById = function (id, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() +"/mdCalculatingMethodEngineeringCost/deleteMdCalculatingMethodEngineeringCostById",
        data: {id: id},
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

developmentCommon.getMdCalculatingMethodEngineeringCostList = function (data, callback) {
    $.ajax({
        type: "get",
        url: getContextPath() +"/mdCalculatingMethodEngineeringCost/getMdCalculatingMethodEngineeringCostList",
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
};

developmentCommon.loadMdCalculatingMethodEngineeringCostTable = function (table, quarm, toolbar, callback, array) {
    var cols = [];
    cols.push({checkbox: true,});
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'area', title: '建筑面积'});
    cols.push({field: 'price', title: '单价 (元/㎡)'});
    if (array) {
        $.each(array, function (i, item) {
            cols.push(item);
        });
    }
    var method = {
        onEditableSave: function (field, row, oldValue, $el) {
            table.bootstrapTable('updateByUniqueId', {id: row.id, row: row});
            developmentCommon.saveMdCalculatingMethodEngineeringCost(row, function () {
                if (callback) {
                    callback();
                }
                toastr.success('编辑成功!');
            }, function () {
                toastr.success('编辑失败!');
            });
        },
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {//加载成功时执行
            if (callback) {
                callback();
            }
        },
        onLoadError: function () {

        }
    };
    table.bootstrapTable('destroy');
    TableInit(table.attr("id"), getContextPath() +"/mdCalculatingMethodEngineeringCost/getBootstrapTableVo", cols, quarm, method);
    if (toolbar) {
        if (toolbar.size() != 0) {
            var bootstrapTable = table.closest(".bootstrap-table");
            if (bootstrapTable.size() != 0) {
                var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
                if (fixedTableToolbar.size() != 0) {
                    fixedTableToolbar.append(toolbar.html());
                }
            }
        }
        // toolbar.empty();
    }
};

developmentCommon.architecturalA = {
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
            var price = $(tr).find("input[name='price']").first().val();
            data.push({dataKey: dataKey, role: role, name: name, price: price});
        });
        return data;
    },
    addChild: function (tr, html) {
        var childs = tr.treegrid('getChildNodes');
        if (childs.length <= 0) {
            tr.after(html);
        } else {
            //如果最后一个子项下还有子项则在子项的子项后添加元素
            var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
            if (subChilds.length <= 0) {
                $(childs.get(childs.length - 1)).after(html);
            } else {
                $(subChilds.get(subChilds.length - 1)).after(html);
            }
        }
        tr.closest('table').treegrid();
    },//动态添加树
    appendHtml:function (target, obj, attribute, price ,callback) {
        target.append($("#" + developmentCommon.config.architecturalA.id).html());
        developmentCommon.architecturalA.treeGirdParse(target);
        var table = target.find("table");
        var tbody = table.find("tbody");
        //第一级
        AssessCommon.loadDataDicByKey(AssessDicKey.build_security_engineering_project_market_cost, '', function (html, dataTree) {
            $.each(dataTree, function (index, data) {
                var key = data.fieldName.split(".").join("_");
                var parentHtml = $("#architecturalAModelParent").html();
                parentHtml = parentHtml.replace(/{index}/g, index);
                parentHtml = parentHtml.replace(/{key}/g, key);
                parentHtml = parentHtml.replace(/{name}/g, data.name);
                tbody.append(parentHtml);
                //第二级
                AssessCommon.loadDataDicByPid(data.id, '', function (html, item) {
                    var tr = tbody.find("[data-key='" + key + "']");
                    $.each(item, function (childIndex, n) {
                        var childHtml = $("#architecturalAModelChildren").html();
                        childHtml = childHtml.replace(/{index}/g, index);
                        childHtml = childHtml.replace(/{key}/g, key);
                        childHtml = childHtml.replace(/{name}/g, n.name);
                        childHtml = childHtml.replace(/{childIndex}/g, (childIndex + 1));
                        developmentCommon.architecturalA.addChild(tr, childHtml);
                        var childTr = tbody.find("[data-key='" + key + "'][data-role='child']");
                        //赋值
                        developmentCommon.architecturalA.initTrData(childTr[childIndex], obj, attribute,callback);
                    });
                });
            });
            developmentCommon.architecturalA.treeGirdParse(target);
        });
        table.find("tfoot").find("tr").find("input[name='totalPrice']").val(price);
    },
    initTrData:function (tr, data, attribute,callback) {
        if (!$.isArray(data)){
            return false;
        }
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            if ($(tr).attr('data-key') != item.dataKey) {
                continue;
            }
            if ($(tr).find("td").first().text() != item.name) {
                continue;
            }
            if (item.price) {
                $(tr).find("input[name='price']").first().val(item.price);
            }
            if (attribute){
                $(tr).find("input").attr(attribute);
            }
            if (callback){
                callback(tr) ;
            }
        }
    }
};

//假设开发法 收入类保存
developmentCommon.loadIncomeCategorySave = function (data, callback, errorCallback) {
    $.ajax({
        type: "post",
        url: getContextPath() +"/mdDevelopmentIncomeCategory/saveMdDevelopmentIncomeCategory",
        data: {fomData: JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
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
developmentCommon.deleteIncomeCategory = function (data, callback, errorCallback) {
    $.ajax({
        type: "post",
        url:getContextPath() + "/mdDevelopmentIncomeCategory/deleteMdDevelopmentIncomeCategory",
        data: {ids: data.join(",")},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
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
developmentCommon.calculationIncomeCategory = function (field, data) {
    var multiply = null;
    var arr = ['saleableArea', 'number', 'unitPrice'];
    if (jQuery.inArray(field, arr) != -1) {
        if (AssessCommon.isNumber(data.saleableArea)) {
            multiply = math.add(math.bignumber(0), math.bignumber(data.saleableArea));
        }
        if (AssessCommon.isNumber(data.number)) {
            multiply = math.add(multiply, math.bignumber(data.number));
        }
        if (AssessCommon.isNumber(data.unitPrice)) {
            if (multiply != null) {
                var c = math.chain(math.bignumber(data.unitPrice)).multiply(multiply).divide(10000).done();
                data.totalSaleableAreaPrice = c.toString();
            }
        }
    }
    return data;
};

//假设开发法 收入类table
developmentCommon.loadIncomeCategoryTable = function (table, quarm, toolbar, callback) {
    var cols = [];
    cols.push({checkbox: true, width: "5%"});
    cols.push({field: 'name', title: '名称', width: "10%"});
    cols.push({
        field: 'plannedBuildingArea', title: '规划建筑面积', width: "10%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)) {
                    return '必须是数字!';
                }
            }
        }
    });
    cols.push({
        field: 'totalSaleableAreaPrice', title: '总可售面积售价', width: "10%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)) {
                    return '必须是数字!';
                }
            }
        }
    });
    cols.push({
        field: 'saleableArea', title: '可售面积', width: "10%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)) {
                    return '必须是数字!';
                }
            }
        }
    });
    cols.push({
        field: 'number', title: '个数', width: "10%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)) {
                    return '必须是数字!';
                }
            }
        }
    });
    cols.push({
        field: 'unitPrice', title: '单位售价', width: "10%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)) {
                    return '必须是数字!';
                }
            }
        }
    });
    cols.push({
        field: 'assessArea', title: '评估面积', width: "15%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if (!$.isNumeric(value)) {
                    return '必须是数字!';
                }
            }
        }
    });
    cols.push({
        field: 'remark', title: '说明', width: "20%", class: 'editable', editable: {
            type: 'text',
            validate: function (value) {
                if ($.trim(value) == '') {
                    return '说明不能为空!';
                }
            }
        }
    });
    var method = {
        onEditableSave: function (field, row, oldValue, $el) {
            row = developmentCommon.calculationIncomeCategory(field, row);
            table.bootstrapTable('updateByUniqueId', {id: row.id, row: row});
            developmentCommon.loadIncomeCategorySave(row, function () {
                toastr.success('编辑成功!');
                if (callback) {
                    callback();
                }
            }, function () {
                toastr.success('编辑失败!');
            });
        },
        showColumns: true,
        showRefresh: true,
        search: false,
        onLoadSuccess: function () {//加载成功时执行
            if (callback) {
                callback();
            }
        },
        onLoadError: function () {

        }
    };
    table.bootstrapTable('destroy');
    TableInit(table.attr("id"), getContextPath() +"/mdDevelopmentIncomeCategory/getBootstrapTableVo", cols, quarm, method);
    if (toolbar) {
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
    if (!$(_this).val()) {
        return false;
    }
    if (!$.isNumeric($(_this).val())) {
        $(_this).val('');
        alert("请输入数字");
        return false;
    }
    var form = $(_this).closest("form");
    var data = formSerializeArray($(form));
    data = developmentCommon.calculationIncomeCategory($(_this).attr("name"), data);
    $(form).initForm(data);
};

developmentCommon.infrastructureChildren = {
    getDataList: function (data, callback) {
        $.ajax({
            type: "get",
            url: getContextPath() +"/mdDevelopmentInfrastructureChildren/getMdDevelopmentInfrastructureChildrenList",
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
        var item = [];
        item.push(data);
        developmentCommon.infrastructureChildren.saveArray(item, callback);
    },
    saveArray: function (data, callback) {
        $.ajax({
            type: "post",
            url: getContextPath() +"/mdDevelopmentInfrastructureChildren/save",
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
            url: getContextPath() +"/mdDevelopmentInfrastructureChildren/delete",
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
        var data = {pid:pid,planDetailsId:planDetailsId,type:type} ;
        developmentCommon.infrastructureChildren.loadTable2(data,selectId,toolbar) ;
    },
    loadTable2: function (data, selectId, toolbar) {
        var cols = [];
        cols.push({checkbox: true});
        cols.push({field: 'name', title: '设施名称'});
        cols.push({field: 'number', title: '单价(元/㎡)'});
        selectId.bootstrapTable('destroy');
        TableInit(selectId.attr("id"), getContextPath() +"/mdDevelopmentInfrastructureChildren/getBootstrapTableVo", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
        if (toolbar){
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
    }
};
