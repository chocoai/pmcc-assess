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
    architecturalB: {id: "architecturalB"}
};

developmentCommon.architecturalB = {
    getData: function (type, databaseName, pid, planDetailsId, callback) {
        developmentCommon.getMdArchitecturalObjList(type, databaseName, pid, planDetailsId, callback);
    },
    reckon: undefined,
    //树中的运算
    totalResult: function (that, reckon) {
        var tr = $(that).closest("tr");
        var value = $(that).val();
        if (!developmentCommon.isNotBlank(value)) {
            return "";
        }
        var resultValue = "";
        if (!$.isNumeric(value)) {
            value = $(that).attr('data-value');
            if (!$.isNumeric(value)) {
                $(that).val('');
                alert("不符合，必须是数字!");
                return false;
            }
        }
        switch (reckon) {
            case 'a':
                var a3 = tr.find("input[name='price']").val();
                var area0 = tr.find("input[name='area']").val();
                if ($.isNumeric(a3) && $.isNumeric(area0)) {
                    a3 = Number(a3) * Number(area0);
                    resultValue = a3.toFixed(2);
                }
                break;
            case 'b': {
                var a = tr.find("input[name='price']").val();
                var b = tr.find("input[name='valuationDateDegreeCompletion']").attr('data-value');
                var area1 = tr.find("input[name='area']").val();
                if ($.isNumeric(a) && $.isNumeric(b)) {
                    var c = Number(a) * (1 - Number(b)) * Number(area1);
                    resultValue = c.toFixed(2);
                }
            }
                break;
            case 'c': {
                var a2 = tr.find("input[name='price']").val();
                var b2 = tr.find("input[name='valuationDateDegreeCompletion']").attr('data-value');
                var area2 = tr.find("input[name='area']").val();
                if ($.isNumeric(a2) && $.isNumeric(b2)) {
                    var c2 = Number(a2) * Number(b2) * Number(area2);
                    resultValue = c2.toFixed(2);
                }
            }
                break;
            default:
                break;
        }
        tr.find("td[name='result']").text(resultValue);
        var table = $(that).closest("table");
        var result = 0;
        table.find("tbody").find("tr").each(function (i, item) {
            var v = $(item).find("td[name='result']").html();
            if ($.isNumeric(v)) {
                result += Number(v);
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
    appendHtml: function (target, obj, attribute, price, callback) {
        developmentCommon.architecturalB.init({
            target: target,
            obj: obj,
            attribute: attribute,
            price: price,
            callback: callback
        });
    },
    init: function (options) {
        var defaultObj = {
            reckon: 'b'
        };
        defaultObj = $.extend({}, defaultObj, options);
        developmentCommon.architecturalB.reckon = defaultObj.reckon;
        var target = defaultObj.target;
        target.append($("#" + developmentCommon.config.architecturalB.id).html());
        developmentCommon.architecturalB.treeGirdParse(target);
        var table = target.find("table");
        var tbody = table.find("tbody");
        var caption = table.find("caption");
        caption.find("[data-view-name=" + defaultObj.reckon + "]").show();
        //第一级
        AssessCommon.loadDataDicByKey(AssessDicKey.build_security_engineering_project_market_cost, '', function (html, dataTree) {
            $.each(dataTree, function (index, data) {
                var key = data.fieldName.split(".").join("_");
                var parentHtml = "";
                switch (defaultObj.reckon) {
                    case 'a':
                        target.find("table").find("thead").find("tr").first().find("th[name='valuationDateDegreeCompletion']").remove();
                        parentHtml = $("#architecturalAModelParent").html();
                        break;
                    case 'b':
                        parentHtml = $("#architecturalBModelParent").html();
                        target.find("table").find("thead").find("th[name='result']").text('续建工程费计算值');
                        break;
                    case 'c':
                        parentHtml = $("#architecturalBModelParent").html();
                        break;
                    default:
                        break;
                }
                parentHtml = parentHtml.replace(/{index}/g, index);
                parentHtml = parentHtml.replace(/{key}/g, key);
                parentHtml = parentHtml.replace(/{name}/g, data.name);
                tbody.append(parentHtml);
                //第二级
                AssessCommon.loadDataDicByPid(data.id, '', function (html, item) {
                    var tr = tbody.find("[data-key='" + key + "']");
                    $.each(item, function (childIndex, n) {
                        var childHtml = "";
                        switch (defaultObj.reckon) {
                            case 'a':
                                childHtml = $("#architecturalAModelChildren").html();
                                break;
                            case 'b':
                                childHtml = $("#architecturalBModelChildren").html();
                                break;
                            case 'c':
                                childHtml = $("#architecturalBModelChildren").html();
                                break;
                            default:
                                break;
                        }
                        childHtml = childHtml.replace(/{index}/g, index);
                        childHtml = childHtml.replace(/{key}/g, key);
                        childHtml = childHtml.replace(/{reckon}/g, defaultObj.reckon);//方法计算模型
                        childHtml = childHtml.replace(/{name}/g, n.name);
                        childHtml = childHtml.replace(/{childIndex}/g, (childIndex + 1));
                        developmentCommon.architecturalB.addChild(tr, childHtml);
                        var childTr = tbody.find("[data-key='" + key + "'][data-role='child']");
                        //赋值
                        developmentCommon.architecturalB.initTrData(childTr[childIndex], defaultObj.obj, defaultObj.attribute, defaultObj.callback, defaultObj.reckon);
                    });
                });
            });
            developmentCommon.architecturalB.treeGirdParse(target);
        });
        table.find("tfoot").find("tr").find("input[name='totalPrice']").val(defaultObj.price);
    },
    //根据表格行来赋值
    initTrData: function (tr, data, attribute, callback, reckon) {
        if (callback) {
            callback(tr);
        }
        if (!$.isArray(data)) {
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
            var that = undefined;
            if (item.remark) {
                that = $(tr).find("input[name='remark']").first();
                that.val(item.remark);
            }
            if (item.price) {
                that = $(tr).find("input[name='price']").first();
                that.val(item.price);
            }
            if (item.area) {
                that = $(tr).find("input[name='area']").first();
                that.val(item.area);
            }
            if (item.valuationDateDegreeCompletion) {
                var valuationDateDegreeCompletion = parseFloat(item.valuationDateDegreeCompletion);
                valuationDateDegreeCompletion /= 100;
                that = $(tr).find("input[name='valuationDateDegreeCompletion']").first();
                that.val(item.valuationDateDegreeCompletion);
                that.attr('data-value', valuationDateDegreeCompletion);
            }
            if (that) {
                if (that.size() != 0) {
                    developmentCommon.architecturalB.totalResult(that[0], reckon);
                }
            }
            if (attribute) {
                $(tr).find("input").attr(attribute);
            }
        }
        if (callback) {
            callback(tr);
        }
    },
    //收集数据值
    getFomData: function (table) {
        var tbody = table.find('tbody');
        var data = [];
        tbody.find("tr").each(function (i, tr) {
            var dataKey = $(tr).attr('data-key');
            var role = $(tr).attr('data-role');
            var name = '';
            var price = '';
            var area = '';
            var remark = '';
            var valuationDateDegreeCompletion = '';
            name = $(tr).find("td").first().text();
            if ($(tr).find("input[name='price']").size() != 0) {
                price = $(tr).find("input[name='price']").first().val();
            }
            if ($(tr).find("input[name='area']").size() != 0) {
                area = $(tr).find("input[name='area']").first().val();
            }
            if ($(tr).find("input[name='remark']").size() != 0) {
                remark = $(tr).find("input[name='remark']").first().val();
            }
            if ($(tr).find("input[name='valuationDateDegreeCompletion']").size() != 0) {
                valuationDateDegreeCompletion = $(tr).find("input[name='valuationDateDegreeCompletion']").first().val();
            }
            data.push({
                dataKey: dataKey,
                role: role,
                name: name,
                price: price,
                area: area,
                remark: remark,
                reckon: developmentCommon.architecturalB.reckon,
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
        url: getContextPath() + "/mdArchitecturalObj/saveMdArchitecturalObj",
        data: {jsonString: JSON.stringify(data), forData: JSON.stringify(obj)},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" +e);
        }
    });
};

developmentCommon.getMdArchitecturalObjById = function (id, callback) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/mdArchitecturalObj/getMdArchitecturalObjById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.deleteMdArchitecturalObjById = function (id, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() + "/mdArchitecturalObj/deleteMdArchitecturalObjById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.removeMdArchitecturalObj = function (type, databaseName, pid, planDetailsId, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() + "/mdArchitecturalObj/removeMdArchitecturalObj",
        data: {type: type, databaseName: databaseName, pid: pid, planDetailsId: planDetailsId},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.getMdArchitecturalObjList = function (type, databaseName, pid, planDetailsId, callback) {
    developmentCommon.getMdArchitecturalObjList2({
        type: type,
        databaseName: databaseName,
        pid: pid,
        planDetailsId: planDetailsId
    }, callback);
};

developmentCommon.getMdArchitecturalObjList2 = function (data, callback) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/mdArchitecturalObj/getMdArchitecturalObjList",
        data: data,
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.saveMdCalculatingMethodEngineeringCost = function (data, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() + "/mdCalculatingMethodEngineeringCost/saveMdCalculatingMethodEngineeringCost",
        data: {formData: JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.deleteMdCalculatingMethodEngineeringCostHandle = function (rows, callback) {
    var idArray = [];
    var arBranch = [];
    $.each(rows, function (i, item) {
        idArray.push(item.id);
        if (item.architecturalObjId) {
            arBranch.push(item.architecturalObjId);
        }
    });
    this.deleteMdCalculatingMethodEngineeringCostById(idArray.join(","), function () {
        developmentCommon.deleteMdArchitecturalObjById(arBranch.join(","), null);
        if (callback) {
            callback();
        }
    });
};

developmentCommon.deleteMdCalculatingMethodEngineeringCostById = function (id, callback) {
    $.ajax({
        type: "post",
        url: getContextPath() + "/mdCalculatingMethodEngineeringCost/deleteMdCalculatingMethodEngineeringCostById",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.getMdCalculatingMethodEngineeringCostList = function (data, callback) {
    $.ajax({
        type: "get",
        url: getContextPath() + "/mdCalculatingMethodEngineeringCost/getMdCalculatingMethodEngineeringCostList",
        data: data,
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (e) {
            AlertError("错误", "调用服务端方法失败，失败原因:" + e);
        }
    });
};

developmentCommon.loadMdCalculatingMethodEngineeringCostTable = function (table, quarm, toolbar, callback, array) {
    var cols = [];
    cols.push({checkbox: true, width: "5%"});
    cols.push({
        field: 'name', title: '名称', width: "20%"
    });
    cols.push({
        field: 'area', title: '建筑面积', width: "10%"
    });
    cols.push({
        field: 'price', title: '建安单价(元/㎡)', width: "36%", class: 'editable'
    });
    if (array) {
        $.each(array, function (i, item) {
            cols.push(item);
        });
    }
    var method = {
        onEditableSave: function (field, row, oldValue, $el) {
            try {
                if (field == 'area') {
                    if ($.isNumeric(row.area) && $.isNumeric(row.price) && $.isNumeric(oldValue)) {
                        var total = Number(oldValue) * Number(row.price);
                        var price = total / Number(row.area);
                        price = price.toFixed(2);
                        row.price = price;
                    }
                }
            } catch (e) {
            }
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
    TableInit(table.attr("id"), getContextPath() + "/mdCalculatingMethodEngineeringCost/getBootstrapTableVo", cols, quarm, method);
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

developmentCommon.loadSchemeInfoTableList = function (quarm, callbackName) {
    var table = $("#boxSchemeInfoList");
    var cols = [];
    cols.push({
        field: 'id', title: '测算方法', width: "20%", formatter: function (value, row, index) {
            var str = '';
            str += row.judgeObjectName + "-";
            str += row.methodName;
            return str;
        }
    });
    cols.push({
        field: 'gmtModified', title: '最后修改日期',width: "20%", formatter: function (value, row, index) {
            return formatDate(value);
        }
    });

    cols.push({
        field: 'gmtCreated', title: '测算方法创建日期',width: "20%", formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    cols.push({
        field: 'id', title: '引用', width: "20%", formatter: function (value, row, index) {
            var str = '';
            str += '<button type="button" class="btn btn-xs btn-success" onclick="{method}(' + row.methodDataId +","+"'boxSchemeInfoModel'"+  ');" >引用 <i class="fa fa-check-circle"></i></button>';
            str += '';
            str = str.replace(/{method}/g, callbackName);
            return str;
        }
    });
    var method = {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {//加载成功时执行
            // table.bootstrapTable('filterBy', {
            //     methodDataId: [itemId]
            // })
        },
        onLoadError: function () {

        }
    };
    table.bootstrapTable('destroy');
    TableInit(table, getContextPath() + "/schemeInfo/getBootstrapTableVo", cols, quarm, method);
};

developmentCommon.infrastructureChildren = {
    getDataList: function (data, callback) {
        $.ajax({
            type: "get",
            url: getContextPath() + "/mdDevelopmentInfrastructureChildren/getMdDevelopmentInfrastructureChildrenList",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (e) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + e);
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
            url: getContextPath() + "/mdDevelopmentInfrastructureChildren/save",
            data: {forData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (e) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + e);
            }
        });
    },
    delete: function (data, callback) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdDevelopmentInfrastructureChildren/delete",
            data: {id: data.join(",")},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (e) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + e);
            }
        });
    },
    loadTable: function (pid, planDetailsId, type, selectId, toolbar) {
        var data = {pid: pid, planDetailsId: planDetailsId, type: type};
        developmentCommon.infrastructureChildren.loadTable2(data, selectId, toolbar);
    },
    loadTable2: function (data, selectId, toolbar) {
        var cols = [];
        cols.push({checkbox: true});
        cols.push({field: 'name', title: '设施名称'});
        cols.push({field: 'number', title: '价钱(元/㎡)'});
        selectId.bootstrapTable('destroy');
        TableInit(selectId.attr("id"), getContextPath() + "/mdDevelopmentInfrastructureChildren/getBootstrapTableVo", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
        if (toolbar) {
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

