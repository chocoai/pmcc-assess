var unitDecorate;
(function () {
    unitDecorate = function () {

    };
    unitDecorate.prototype = {
        config: function () {
            var data = {};
            data.table = "ExamineUnitDecorateList";
            data.box = "divBoxExamineUnitDecorate";
            data.frm = "frmExamineUnitDecorate";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitDecorateColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="unitDecorate.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitDecorate.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitDecorate.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitDecorate.prototype.config().table, getContextPath() + "/basicUnitDecorate/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitDecorate/deleteBasicUnitDecorate",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        unitDecorate.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitDecorate.prototype.init({});
            $('#' + unitDecorate.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitDecorate.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(unitDecorate.prototype.config().frm, true);
            data.unitId = unitCommon.getUnitId();
            $.ajax({
                url: getContextPath() + "/basicUnitDecorate/saveAndUpdateBasicUnitDecorate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + unitDecorate.prototype.config().box).modal('hide');
                        unitDecorate.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitDecorate/getBasicUnitDecorateById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (unitDecorate.prototype.isNotBlank(result.data)) {
                            unitDecorate.prototype.init(result.data);
                        } else {
                            unitDecorate.prototype.init({});
                        }
                        $('#' + unitDecorate.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            var frm = $("#" + unitDecorate.prototype.config().frm);
            frm.clearAll().initForm(item, function () {
                frm.find('select.decoratingMaterial').off('change').on('change', function () {
                    AssessCommon.loadDataDicByPid($(this).val(), item.constructionTechnology, function (html, data) {
                        frm.find('select.constructionTechnology').empty().html(html).trigger('change');
                    });
                });
                frm.find('select.constructionTechnology').off('change').on('change', function () {
                    AssessCommon.loadDataDicByPid($(this).val(), item.materialPriceRange, function (html, data) {
                        frm.find('select.materialPriceRange').empty().html(html).trigger('change');
                    });
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitInteriorDecorationPart, item.decorationPart, function (html, data) {
                    frm.find('select.decorationPart').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitInteriorDecorationMaterial, item.decoratingMaterial, function (html, data) {
                    frm.find('select.decoratingMaterial').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonMaterialGrade, item.materialGrade, function (html, data) {
                    frm.find('select.materialGrade').empty().html(html).trigger('change');
                });

                UnitCommonPartFun.getBasicUnitCommonPartList({unitId: unitCommon.getUnitId()}, function (dataAll) {
                    var unitCommonParts = [];
                    $.each(dataAll, function (i, data) {
                        var resultData = null;
                        if (data.unitLocation) {
                            try {
                                resultData = JSON.parse(data.unitLocation);
                            } catch (e) {
                            }
                        }
                        if (resultData) {
                            $.each(resultData, function (j, item) {
                                var name = item.name + item.index;
                                unitCommonParts.push(name);
                            })
                        }
                    });
                    var html = '';
                    $.each(unitCommonParts, function (i, name) {
                        html += '<option value="" selected>-请选择-</option>';
                        html += "<option value='" + name + "'>" + name + "</option>";
                    });
                    frm.find('#unitDecorate_datalist').empty().html(html).trigger('change');
                });
            });
        },
        openPartItemModal: function () {
            UnitCommonPartFun.getBasicUnitCommonPartList({unitId: unitCommon.getUnitId()}, function (dataAll) {
                var unitCommonParts = [];
                $.each(dataAll, function (i, data) {
                    var resultData = null;
                    if (data.unitLocation) {
                        try {
                            resultData = JSON.parse(data.unitLocation);
                        } catch (e) {
                        }
                    }
                    if (resultData) {
                        $.each(resultData, function (j, item) {
                            var name = item.name;
                            unitCommonParts.push(name);
                        })
                    }
                });
                var target = $("#frmUnitDecoratePartItem").find(".card-body");
                target.empty();
                var resultHtml = '<div>';
                var divLength = Math.ceil(unitCommonParts.length / 6);
                for (var j = 0; j < divLength; j++) {
                    resultHtml += '<div class="row form-group">';
                    resultHtml += '<div class="col-md-12">';
                    resultHtml += "<div class='form-check' style='justify-content:left'>";
                    var length = (j + 1) * 6 > unitCommonParts.length ? unitCommonParts.length : (j + 1) * 6;
                    for (var i = j * 6; i < length; i++) {
                        resultHtml += "<label class='form-check-label'>";
                        resultHtml += "<input class='form-check-input' type='checkbox' name='partItemCheckBox' ";
                        resultHtml += 'value="' + unitCommonParts[i] + '">';
                        resultHtml += "<span class='form-check-sign'>" + unitCommonParts[i] + "</span></label>";
                    }
                    resultHtml += "</div>";
                    resultHtml += "</div>";
                    resultHtml += "</div>";
                }
                console.log(resultHtml);
                target.append(resultHtml);
            });
            // AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examineUnitCommonPart, '', function (html, resultData) {
            //     var target = $("#frmUnitDecoratePartItem").find(".card-body");
            //     target.empty();
            //     var resultHtml = '<div>';
            //     var divLength = Math.ceil(resultData.length / 6);
            //     for (var j = 0; j < divLength; j++) {
            //         resultHtml += '<div class="row form-group">';
            //         resultHtml += '<div class="col-md-12">';
            //         resultHtml += "<div class='form-check' style='justify-content:left'>";
            //         var length = (j + 1) * 6 > resultData.length ? resultData.length : (j + 1) * 6;
            //         for (var i = j * 6; i < length; i++) {
            //             resultHtml += "<label class='form-check-label'>";
            //             resultHtml += "<input class='form-check-input' type='checkbox' name='partItemCheckBox' ";
            //             resultHtml += 'value="' + resultData[i].name + '">';
            //             resultHtml += "<span class='form-check-sign'>" + resultData[i].name + "</span></label>";
            //         }
            //         resultHtml += "</div>";
            //         resultHtml += "</div>";
            //         resultHtml += "</div>";
            //     }
            //
            //     target.append(resultHtml);
            // }, false);

            var value = $('#' + unitDecorate.prototype.config().frm).find("input[name='unitCommonPart']").val();
            if (unitDecorate.prototype.isNotBlank(value)) {
                var valueArray = value.split(",");
                var checkboxs = $("#frmUnitDecoratePartItem").find("input[name='partItemCheckBox']");
                AssessCommon.checkboxToChecked(checkboxs, valueArray);
            }
            $("#divBoxUnitDecoratePartItem").modal("show");
        },
        splicePartItem: function () {
            var value = [];
            $("#frmUnitDecoratePartItem").find("input[name='partItemCheckBox']:checked").each(function (i) {
                value.push($(this).val());
            })
            $("#" + unitDecorate.prototype.config().frm).find("input[name='unitCommonPart']").val(value);
            $("#divBoxUnitDecoratePartItem").modal("hide");
        }
    }

    //绑定事件
    $('#' + unitDecorate.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitDecorate.prototype.loadDataDicList();
    })
})();

var unitCommonPart = function () {

};

unitCommonPart.prototype.config = function () {
    var data = {};
    data.table = "ExamineUnitCommonPartList";
    data.box = "divBoxExamineUnitCommonPart";
    data.frm = "frmExamineUnitCommonPart";
    return data;
};

unitCommonPart.prototype.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};
unitCommonPart.prototype.loadDataDicList = function () {
    var cols = commonColumn.unitCommonPartColumn();
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="unitCommonPart.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitCommonPart.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
            str += '</div>';
            return str;
        }
    });
    $("#" + unitCommonPart.prototype.config().table).bootstrapTable('destroy');
    TableInit(unitCommonPart.prototype.config().table, getContextPath() + "/basicUnitCommonPart/getBootstrapTableVo", cols, {
        unitId: unitCommon.getUnitId()
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    });
};
unitCommonPart.prototype.removeData = function (id) {
    $.ajax({
        url: getContextPath() + "/basicUnitCommonPart/deleteBasicUnitCommonPart",
        type: "post",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                notifySuccess("成功", "删除成功");
                unitCommonPart.prototype.loadDataDicList();
            }
            else {
                AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("失败", "调用服务端方法失败，失败原因:" + result);
        }
    })
};
unitCommonPart.prototype.showModel = function () {
    unitCommonPart.prototype.init({});
    $('#' + unitCommonPart.prototype.config().box).modal("show");
};
unitCommonPart.prototype.saveData = function () {
    var frm = $("#" + unitCommonPart.prototype.config().frm);
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.unitId = unitCommon.getUnitId();
    var resultData = [];
    frm.find(".unitLocationTextModel").find(".form-group").each(function () {
        var group = $(this);
        var index = group.find("[data-name=index]").val();
        var name = group.find("[data-name=name]").val();
        var unitLocation = group.find("[data-name=unitLocation]").val();
        var description = group.find("[data-name=description]").val();
        var obj = {index: index, name: name, unitLocation: unitLocation, description: description};
        resultData.push(obj);
    });
    data.unitLocation = JSON.stringify(resultData);
    $.ajax({
        url: getContextPath() + "/basicUnitCommonPart/saveAndUpdateBasicUnitCommonPart",
        type: "post",
        dataType: "json",
        data: data,
        success: function (result) {
            if (result.ret) {
                notifySuccess("成功", "保存成功");
                $('#' + unitCommonPart.prototype.config().box).modal('hide');
                unitCommonPart.prototype.loadDataDicList();
            }
            else {
                AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            console.log(result);
            AlertError("失败", "调用服务端方法失败，失败原因:" + result);
        }
    })
};
unitCommonPart.prototype.getAndInit = function (id) {
    $.ajax({
        url: getContextPath() + "/basicUnitCommonPart/getBasicUnitCommonPartById",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (unitCommonPart.prototype.isNotBlank(result.data)) {
                    unitCommonPart.prototype.init(result.data);
                } else {
                    unitCommonPart.prototype.init({});
                }
                $('#' + unitCommonPart.prototype.config().box).modal("show");
            }
        },
        error: function (result) {
            AlertError("失败", "调用服务端方法失败，失败原因:" + result);
        }
    })
};
unitCommonPart.prototype.getBasicUnitCommonPartList = function (query, callback) {
    $.ajax({
        url: getContextPath() + "/basicUnitCommonPart/basicUnitCommonPartList",
        type: "get",
        dataType: "json",
        data: query,
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            }
        },
        error: function (result) {
            AlertError("失败", "调用服务端方法失败，失败原因:" + result);
        }
    })
};
unitCommonPart.prototype.compare = function (obj1, obj2) {
    var val1 = obj1.index;
    var val2 = obj2.index;
    if (val1 < val2) {
        return -1;
    } else if (val1 > val2) {
        return 1;
    } else {
        return 0;
    }
};
unitCommonPart.prototype.init = function (item) {
    $("#" + unitCommonPart.prototype.config().frm).clearAll().initForm(item, function () {
        AssessCommon.loadDataListHtml(AssessDicKey.examineUnitLocation, item.unitLocation, function (html, data) {
            $("#" + unitCommonPart.prototype.config().frm).find("#unitLocationList").empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitCommonPart, null, function (html, data) {
            html = '';
            html += '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                html += "<option value='" + item.name + "'>" + item.name + "</option>";
            });
            $("#" + unitCommonPart.prototype.config().frm).find('#unitCommonPart_datalist').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitMonad, null, function (html, data) {
            html = '';
            html += '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                html += "<option value='" + item.name + "'>" + item.name + "</option>";
            });
            $("#" + unitCommonPart.prototype.config().frm).find('#unitMonad_datalist').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitQuantity, null, function (html, data) {
            html = '';
            html += '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                html += "<option value='" + item.name + "'>" + item.name + "</option>";
            });
            $("#" + unitCommonPart.prototype.config().frm).find('#unitQuantity_datalist').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.unit_commonPart_description, null, function (html, data) {
            html = '';
            html += '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                html += "<option value='" + item.name + "'>" + item.name + "</option>";
            });
            $("#" + unitCommonPart.prototype.config().frm).find('#unitDescriptionList').empty().html(html).trigger('change');
        });
        var target = $(".unitLocationTextModel");
        target.empty();
        if (item.unitLocation) {
            var resultData = JSON.parse(item.unitLocation);
            try {
                resultData.sort(unitCommonPart.prototype.compare);
            } catch (e) {
            }
            for (var i = 0; i < resultData.length; i++) {
                var obj = resultData[i];
                var html = unitCommonPart.prototype.replaceHtml(obj);
                target.append(html);
            }
        }
    });
};
unitCommonPart.prototype.openPartItemModal = function () {
    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examineUnitCommonPart, '', function (html, resultData) {
        var target = $("#frmExamineUnitPartItem").find(".card-body");
        target.empty();
        var resultHtml = '<div>';
        var divLength = Math.ceil(resultData.length / 6);
        for (var j = 0; j < divLength; j++) {
            resultHtml += '<div class="row form-group">';
            resultHtml += '<div class="col-md-12">';
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            var length = (j + 1) * 6 > resultData.length ? resultData.length : (j + 1) * 6;
            for (var i = j * 6; i < length; i++) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='partItemCheckBox' ";
                resultHtml += 'value="' + resultData[i].name + '">';
                resultHtml += "<span class='form-check-sign'>" + resultData[i].name + "</span></label>";
            }
            resultHtml += "</div>";
            resultHtml += "</div>";
            resultHtml += "</div>";
        }

        target.append(resultHtml);
    }, false);

    var value = $('#' + unitCommonPart.prototype.config().frm).find("input[name='unitCommonPart']").val();
    if (unitCommonPart.prototype.isNotBlank(value)) {
        var valueArray = value.split(",");
        var checkboxs = $("#frmExamineUnitPartItem").find("input[name='partItemCheckBox']");
        AssessCommon.checkboxToChecked(checkboxs, valueArray);
    }
    $("#divBoxExamineUnitPartItem").modal("show");
};
unitCommonPart.prototype.splicePartItem = function () {
    var value = [];
    $("#frmExamineUnitPartItem").find("input[name='partItemCheckBox']:checked").each(function (i) {
        value.push($(this).val());
    });
    $("#" + unitCommonPart.prototype.config().frm).find("input[name='unitCommonPart']").val(value);
    $("#divBoxExamineUnitPartItem").modal("hide");
};

unitCommonPart.prototype.appendRecording = function (_this) {
    var frm = $(_this).closest("form");
    var data = formSerializeArray(frm);
    if (!data.unitCommonPart) {
        notifyWarning("提示", "部位名称必须填写!");
        return false;
    }
    if (!data.unitQuantity) {
        notifyWarning("提示", "数量必须填写!");
        return false;
    }
    if (!data.unitMonad) {
        notifyWarning("提示", "单位必须填写!");
        return false;
    }
    var target = $(".unitLocationTextModel");
    target.empty();
    var start = target.find(".form-group").size();
    var len = Number(data.unitQuantity) + start;
    for (var i = 1 + start; i <= len; i++) {
        var html = unitCommonPart.prototype.replaceHtml({index: i, name: data.unitCommonPart + i});
        target.append(html);
    }

};

unitCommonPart.prototype.replaceHtml = function (data) {
    var html = $("#unitLocationTextModelHtml").html();
    html = html.replace(/{index}/g, data.index);
    if (data.unitLocation) {
        html = html.replace(/{unitLocation}/g, data.unitLocation);
    } else {
        html = html.replace(/{unitLocation}/g, '');
    }
    if (data.name) {
        html = html.replace(/{name}/g, data.name);
    } else {
        html = html.replace(/{name}/g, '');
    }
    if (data.description) {
        html = html.replace(/{description}/g, data.description);
    } else {
        html = html.replace(/{description}/g, '');
    }
    return html;
};

unitCommonPart.prototype.clearHtml = function (_this) {
    $(_this).closest(".form-group").remove();
};

(function () {
    //绑定事件
    $('#' + unitCommonPart.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitCommonPart.prototype.loadDataDicList();
    })

})();

var UnitCommonPartFun = new unitCommonPart();
window.UnitCommonPartFun = UnitCommonPartFun;


////----------------------------------
var unitHuxing;
(function () {
    unitHuxing = function () {
    };
    unitHuxing.prototype = {
        config: function () {
            var data = {};
            data.table = "UnitHuxingList";
            data.box = "divBoxUnitHuxing";
            data.frm = "frmUnitHuxing";
            data.unitHuxingFileIDFildName = "house_latest_family_planV";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitHuxingColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="unitHuxing.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitHuxing.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitHuxing.prototype.config().table, getContextPath() + "/basicUnitHuxing/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/deleteBasicUnitHuxing",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        unitHuxing.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitHuxing.prototype.init({});
            $('#' + unitHuxing.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitHuxing.prototype.config().frm).valid()) {
                return false;
            }
            if ($("#_" + unitHuxing.prototype.config().unitHuxingFileIDFildName).html().length <= 0) {
                notifyInfo('提示', "请上传户型图");
                return false;
            }

            var data = formParams(unitHuxing.prototype.config().frm, true);
            data.unitId = unitCommon.getUnitId();
            data.houseCategory = unitHuxing.prototype.rule("get", data);
            var key = $("#" + unitHuxing.prototype.config().frm).find('select.type').find('option:selected').attr('key');
            if (key == AssessDicKey.examineUnitHuxingTypeProduction) {
                data.name = "跨长" + data.spanLength + ",跨宽" + data.spanWidth + ",跨数" + data.spanNumber;
            } else if (key == AssessDicKey.examineUnitHuxingTypeOffice) {
                data.name = "开间" + data.bay + ",进深" + data.deep;
            } else {
                data.name = unitHuxing.prototype.rule("formatter", data);
            }
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/saveAndUpdateBasicUnitHuxing",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + unitHuxing.prototype.config().box).modal('hide');
                        unitHuxing.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        /**
         * @author:  zch
         * 描述:户型的类别填写方式【】室【】厅【】厨【】卫【】花园【】阳台
         * @date:
         **/
        rule: function (flag, item) {
            var text = "";
            //格式化
            if (flag == "formatter") {
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    text += item.house + "室";
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    text += item.saloon + "厅";
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    text += item.kitchen + "厨";
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    text += item.toilet + "卫";
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    text += item.garden + "花园";
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    text += item.balcony + "阳台";
                }
                return text;
            }
            //转为json存入数据库
            if (flag == "get") {
                var data = {};
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    data.house = item.house;
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    data.saloon = item.saloon;
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    data.kitchen = item.kitchen;
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    data.toilet = item.toilet;
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    data.garden = item.garden;
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    data.balcony = item.balcony;
                }
                return JSON.stringify(data);
            }
            //赋值
            if (flag == "set") {
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='house']").val(item.house);
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='saloon']").val(item.saloon);
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='kitchen']").val(item.kitchen);
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='toilet']").val(item.toilet);
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='garden']").val(item.garden);
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='balcony']").val(item.balcony);
                }
            }
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/getBasicUnitHuxingById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (unitHuxing.prototype.isNotNull(data)) {
                            unitHuxing.prototype.init(data);
                        } else {
                            unitHuxing.prototype.init({});
                        }
                        $('#' + unitHuxing.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + unitHuxing.prototype.config().frm).clearAll();
            $("#" + unitHuxing.prototype.config().frm).initForm(item, function () {
                $("#" + unitHuxing.prototype.config().frm).find('select.type').off('change').on('change', function () {
                    var key = $(this).find('option:selected').attr('key');
                    if (key == AssessDicKey.examineUnitHuxingTypeStay) {
                        $("#huxingTypeStay").show();
                        $("#huxingTypeProduction,#huxingTypeOffice").hide();
                    } else if (key == AssessDicKey.examineUnitHuxingTypeProduction) {
                        $("#huxingTypeProduction").show();
                        $("#huxingTypeStay,#huxingTypeOffice").hide();
                    } else if (key == AssessDicKey.examineUnitHuxingTypeOffice) {
                        $("#huxingTypeOffice").show();
                        $("#huxingTypeProduction,#huxingTypeStay").hide();
                    } else {
                        if (basicCommon.basicApplyForm.find('[name=type]:checked').val() == 1) {
                            $("#huxingTypeStay,#huxingTypeProduction,#huxingTypeOffice").hide();
                        }
                    }
                })
            });
            FileUtils.uploadFiles({
                target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: unitHuxing.prototype.isNotNull(item.id) ? item.id : 0
                },
                deleteFlag: true
            });

            FileUtils.getFileShows({
                target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                formData: {
                    fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: unitHuxing.prototype.isNotNull(item.id) ? item.id : 0
                },
                deleteFlag: true
            })

            if (unitHuxing.prototype.isNotNull(item.houseCategory)) {
                unitHuxing.prototype.rule("set", JSON.parse(item.houseCategory));
            }

            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitHuxingType, item.type, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.type').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonOrientation, item.orientation, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.orientation').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseSpatialDistribution, item.spatialDistribution, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.spatialDistribution').empty().html(html).trigger('change');
            });
        },
        //下载附件模板
        getAttachmentId: function (tableId) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/getAttachmentId",
                type: "get",
                data: {tableId: tableId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        window.open(FileUtils.config.downloadUrl + result.data);
                    }
                }
            })
        },
        importHouseBtn: function () {
            $('#ajaxFileUploadHouse').val('').trigger('click');
        },
        importHouse: function () {
            Loading.progressShow();
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/basicUnitHuxing/importHouse",
                data: {
                    unitId: unitCommon.getUnitId()
                },//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: 'ajaxFileUploadHouse',//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", result.data);
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    //绑定事件
    $('#' + unitHuxing.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitHuxing.prototype.loadDataDicList();
    })
})();


var unitElevator;
(function () {
    unitElevator = function () {

    };
    unitElevator.prototype = {
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "ExamineUnitElevatorList";
            data.box = "divBoxExamineUnitElevator";
            data.frm = "frmExamineUnitElevator";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitElevatorColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="unitElevator.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitElevator.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitElevator.prototype.config().table, getContextPath() + "/basicUnitElevator/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitElevator/deleteBasicUnitElevator",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        unitElevator.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitElevator.prototype.init({});
            $('#' + unitElevator.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitElevator.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(unitElevator.prototype.config().frm, true);
            data.unitId = unitCommon.getUnitId();
            $.ajax({
                url: getContextPath() + "/basicUnitElevator/saveAndUpdateBasicUnitElevator",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + unitElevator.prototype.config().box).modal('hide');
                        unitElevator.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitElevator/getBasicUnitElevatorById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        unitElevator.prototype.init(result.data);
                        $('#' + unitElevator.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + unitElevator.prototype.config().frm).clearAll().initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonMaintenanceSituation, item.maintenance, function (html, data) {
                $("#" + unitElevator.prototype.config().frm).find('select.maintenance').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitElevatorType, item.type, function (html, data) {
                $("#" + unitElevator.prototype.config().frm).find('select.type').empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + unitElevator.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitElevator.prototype.loadDataDicList();
    })
})();


var unitStairs;

(function () {
    unitStairs = function () {

    };
    unitStairs.prototype = {
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "BasicUnitStairsList";
            data.box = "divBoxBasicUnitStairs";
            data.boxItem = "divBoxBasicUnitStairsItem";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitStairsColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="unitStairs.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitStairs.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitStairs.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitStairs.prototype.config().table, getContextPath() + "/basicUnitStairs/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitStairs/deleteBasicUnitStairs",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        unitStairs.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitStairs.prototype.init({});
            $('#' + unitStairs.prototype.config().box).modal("show");
        },
        saveData: function () {
            var frm = $('#' + unitStairs.prototype.config().box).find("form");
            if (!frm.valid()) {
                return false;
            }
            var data = formSerializeArray(frm);
            data.unitId = unitCommon.getUnitId();
            $.ajax({
                url: getContextPath() + "/basicUnitStairs/saveAndUpdateBasicUnitStairs",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + unitStairs.prototype.config().box).modal('hide');
                        unitStairs.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            var data = $("#" + unitStairs.prototype.config().table).bootstrapTable('getRowByUniqueId', id);
            unitStairs.prototype.init(data);
            $('#' + unitStairs.prototype.config().box).modal("show");
        },
        init: function (item) {
            var frm = $('#' + unitStairs.prototype.config().box).find("form");
            frm.clearAll().initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitStairs_type, null, function (html, data) {
                html = '';
                html += '<option value="" selected>-请选择-</option>';
                $.each(data, function (i, item) {
                    html += "<option value='" + item.name + "'>" + item.name + "</option>";
                });
                frm.find('#UnitStairs_TYPE_List').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitStairs_use, null, function (html, data) {
                html = '';
                html += '<option value="" selected>-请选择-</option>';
                $.each(data, function (i, item) {
                    html += "<option value='" + item.name + "'>" + item.name + "</option>";
                });
                frm.find('#UnitStairs_purpose_List').empty().html(html).trigger('change');
            });
        },
        openPartItemModal:function () {
            var box = $("#" + unitStairs.prototype.config().boxItem) ;
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitCommonPart, null, function (html, data) {
                var unitCommonPart = "" ;
                $.each(data,function (i,item) {
                    if (item.fieldName == AssessDicKey.examineUnitCommonPart_stairs){
                        unitCommonPart = item.name ;
                    }
                }) ;
                var json = {unitId: unitCommon.getUnitId()};
                if (unitCommonPart){
                    json.unitCommonPart = unitCommonPart;
                }
                UnitCommonPartFun.getBasicUnitCommonPartList(json, function (dataAll) {
                    var unitCommonParts = [];
                    $.each(dataAll, function (i, data) {
                        var resultData = null;
                        if (data.unitLocation) {
                            try {
                                resultData = JSON.parse(data.unitLocation);
                            } catch (e) {
                            }
                        }
                        if (resultData) {
                            $.each(resultData, function (j, item) {
                                var name = item.name;
                                unitCommonParts.push(name);
                            })
                        }
                    });
                    if (unitCommonParts.length == 0){
                        notifyWarning("提示", "请在公共部分选择部位楼梯间添加部位记录!");
                        return false ;
                    }
                    var target = box.find(".card-body");
                    target.empty();
                    var resultHtml = '<div>';
                    var divLength = Math.ceil(unitCommonParts.length / 6);
                    for (var j = 0; j < divLength; j++) {
                        resultHtml += '<div class="row form-group">';
                        resultHtml += '<div class="col-md-12">';
                        resultHtml += "<div class='form-check' style='justify-content:left'>";
                        var length = (j + 1) * 6 > unitCommonParts.length ? unitCommonParts.length : (j + 1) * 6;
                        for (var i = j * 6; i < length; i++) {
                            resultHtml += "<label class='form-check-label'>";
                            resultHtml += "<input class='form-check-input' type='checkbox' name='partItemCheckBox' ";
                            resultHtml += 'value="' + unitCommonParts[i] + '">';
                            resultHtml += "<span class='form-check-sign'>" + unitCommonParts[i] + "</span></label>";
                        }
                        resultHtml += "</div>";
                        resultHtml += "</div>";
                        resultHtml += "</div>";
                    }
                    target.append(resultHtml);
                    box.modal("show");
                });
            });
        },
        splicePartItem:function () {
            var box = $("#" + unitStairs.prototype.config().boxItem) ;
            var frm = $('#' + unitStairs.prototype.config().box).find("form");
            var value = [];
            box.find("input[name='partItemCheckBox']:checked").each(function (i) {
                value.push($(this).val());
            }) ;
            frm.find("input[name='staircase']").val(value);
            box.modal("hide");
        }
    };

    //绑定事件
    $('#' + unitStairs.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitStairs.prototype.loadDataDicList();
    })
})();