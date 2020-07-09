/**
 * Created by kings on 2018-11-9.
 */
var buildingModelView;
(function () {
    //配置的局部变量用做 对象属性 (初始化标识符)
    buildingModelView = function () {
    };
    buildingModelView.prototype = {
        isEmpty: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        /**
         * @author:  zch
         * 描述:
         * @date: 页面 初始化
         **/
        viewInit: function () {
            buildingModelView.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
            buildingModelView.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
            buildingModelView.prototype.sonModelMethod.buildingSurface.loadDataDicList();
            buildingModelView.prototype.sonModelMethod.buildingFunction.loadDataDicList();
        },
        /**
         * @author:  zch
         * 描述:配置文件
         * @date:
         **/
        config: function () {
            var data = {};
            data.sonBox = "divBoxExamineBuildingOutfit";
            data.sonTable = "ExamineBuildingOutfitList";
            data.sonFrm = "frmExamineBuildingOutfit";

            data.examineBuildingSurfaceBox = "divBoxExamineBuildingSurface";
            data.examineBuildingSurfaceTable = "ExamineBuildingSurfaceList";
            data.examineBuildingSurfaceFrm = "ExamineBuildingSurfaceFrm";

            data.examineBuildingMaintenanceBox = "divBoxExamineBuildingMaintenance";
            data.examineBuildingMaintenanceTable = "ExamineBuildingMaintenanceList";
            data.examineBuildingMaintenanceFrm = "ExamineBuildingMaintenanceFrm";

            data.examineBuildingFunctionBox = "examineBuildingFunction";
            data.examineBuildingFunctionTable = "examineBuildingFunctionList";
            data.examineBuildingFunctionFrm = "examineBuildingFunctionFrm";
            return data;
        },
        sonModelMethod: {
            /**楼栋外装情况**/
            buildingOutfit: {
                getAndInit: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingOutfit/getBasicBuildingOutfitById",
                        type: "get",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                $("#" + buildingModelView.prototype.config().sonFrm).clearAll();
                                var data = result.data;
                                if (buildingModelView.prototype.isEmpty(data)) {
                                    $("#" + buildingModelView.prototype.config().sonFrm).initForm(data);
                                    buildingModelView.prototype.sonModelMethod.buildingOutfit.init(data);
                                }
                                $('#' + buildingModelView.prototype.config().sonBox).modal("show");
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().sonFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().sonFrm,true);
                    data.buildingId = buildingCommon.getBuildingId();
                    $.ajax({
                        url: getContextPath() + "/basicBuildingOutfit/saveAndUpdateBasicBuildingOutfit",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "保存成功");
                                $('#' + buildingModelView.prototype.config().sonBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                showModel: function () {
                    $("#" + buildingModelView.prototype.config().sonFrm).clearAll();
                    buildingModelView.prototype.sonModelMethod.buildingOutfit.init({});
                    $('#' + buildingModelView.prototype.config().sonBox).modal("show");
                },
                removeData: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingOutfit/deleteBasicBuildingOutfit",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "删除成功");
                                buildingModelView.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                init: function (item) {
                    $("#" + buildingModelView.prototype.config().sonFrm).find('select.decoratingMaterial').off('change').on('change',function () {
                        AssessCommon.loadDataDicByPid($(this).val(), item.constructionTechnology, function (html, data) {
                            $("#" + buildingModelView.prototype.config().sonFrm).find('select.constructionTechnology').empty().html(html).trigger('change');
                        });
                        item.constructionTechnology = null;
                    });
                    $("#" + buildingModelView.prototype.config().sonFrm).find('select.constructionTechnology').off('change').on('change',function () {
                        AssessCommon.loadDataDicByPid($(this).val(), item.materialPrice, function (html, data) {
                            $("#" + buildingModelView.prototype.config().sonFrm).find('select.materialPrice').empty().html(html).trigger('change');
                        });
                        item.materialPrice = null;
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.decoratingMaterial, function (html, data) {
                        $("#" + buildingModelView.prototype.config().sonFrm).find('select.decoratingMaterial').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonMaterialGrade, item.materialGrade, function (html, data) {
                        $("#" + buildingModelView.prototype.config().sonFrm).find('select.materialGrade').empty().html(html).trigger('change');
                    });

                    AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_building_decoration_part, null, function (html, data) {
                        $("#build_decorationPart_datalist").empty().html(html).trigger('change');
                    }, true);
                },
                loadDataDicList: function () {
                    var cols = commonColumn.buildingOutfitColumn();
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingOutfit.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingOutfit.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().sonTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().sonTable, getContextPath() + "/basicBuildingOutfit/getBootstrapTableVo", cols, {
                        buildingId: buildingCommon.getBuildingId()
                    }, {
                        showColumns: false,
                        showRefresh: false,
                        search: false,
                        onLoadSuccess: function () {
                            $('.tooltips').tooltip();
                        }
                    });
                }

            },
            /**屋面结构**/
            buildingSurface: {
                getAndInit: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingSurface/getBasicBuildingSurfaceById",
                        type: "get",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                $("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).clearAll();
                                var data = result.data;
                                if (buildingModelView.prototype.isEmpty(data)) {
                                    $("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).initForm(data);
                                    buildingModelView.prototype.sonModelMethod.buildingSurface.init(data);
                                }
                                $('#' + buildingModelView.prototype.config().examineBuildingSurfaceBox).modal("show");
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().examineBuildingSurfaceFrm,true);
                    data.buildingId = buildingCommon.getBuildingId();
                    $.ajax({
                        url: getContextPath() + "/basicBuildingSurface/saveAndUpdateBasicBuildingSurface",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "保存成功");
                                $('#' + buildingModelView.prototype.config().examineBuildingSurfaceBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                showModel: function () {
                    $("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).clearAll();
                    buildingModelView.prototype.sonModelMethod.buildingSurface.init({});
                    $('#' + buildingModelView.prototype.config().examineBuildingSurfaceBox).modal("show");
                },
                removeData: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingSurface/deleteBasicBuildingSurface",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "删除成功");
                                buildingModelView.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                init: function (item) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_structure, item.structure, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).find('select.structure').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = commonColumn.buildingSurfaceColumn();
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingSurface.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingSurface.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().examineBuildingSurfaceTable, getContextPath() + "/basicBuildingSurface/getBootstrapTableVo", cols, {
                        buildingId: buildingCommon.getBuildingId()
                    }, {
                        showColumns: false,
                        showRefresh: false,
                        search: false,
                        onLoadSuccess: function () {
                            $('.tooltips').tooltip();
                        }
                    });
                }
            },
            /**围护结构**/
            buildingMaintenance: {
                getAndInit: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingMaintenance/getBasicBuildingMaintenanceById",
                        type: "get",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                                var data = result.data;
                                if (buildingModelView.prototype.isEmpty(data)) {
                                    $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).initForm(data);
                                    buildingModelView.prototype.sonModelMethod.buildingMaintenance.init(data);
                                }
                                $('#' + buildingModelView.prototype.config().examineBuildingMaintenanceBox).modal("show");
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().examineBuildingMaintenanceFrm,true);
                    data.buildingId = buildingCommon.getBuildingId();
                    $.ajax({
                        url: getContextPath() + "/basicBuildingMaintenance/saveAndUpdateBasicBuildingMaintenance",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "保存成功");
                                $('#' + buildingModelView.prototype.config().examineBuildingMaintenanceBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                showModel: function () {
                    $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                    buildingModelView.prototype.sonModelMethod.buildingMaintenance.init({});
                    $('#' + buildingModelView.prototype.config().examineBuildingMaintenanceBox).modal("show");
                },
                removeData: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingMaintenance/deleteBasicBuildingMaintenance",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "删除成功");
                                buildingModelView.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                init: function (item) {
                    $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).find('select.type').off('change').on('change',function () {
                        AssessCommon.loadDataDicByPid($(this).val(), item.materialQuality, function (html, data) {
                            $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).find('select.materialQuality').empty().html(html).trigger('change');
                        });
                        item.materialQuality = null;
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_maintenance_type, item.type, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).find('select.type').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_maintenance_category, item.category, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).find('select.category').empty().html(html).trigger('change');
                    });

                },
                loadDataDicList: function () {
                    var cols =commonColumn.buildingMaintenanceColumn();
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingMaintenance.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingMaintenance.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().examineBuildingMaintenanceTable, getContextPath() + "/basicBuildingMaintenance/getBootstrapTableVo", cols, {
                        buildingId: buildingCommon.getBuildingId()
                    }, {
                        showColumns: false,
                        showRefresh: false,
                        search: false,
                        onLoadSuccess: function () {
                            $('.tooltips').tooltip();
                        }
                    });
                }
            },
            /**楼栋建筑功能**/
            buildingFunction: {
                getAndInit: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingFunction/getBasicBuildingFunctionById",
                        type: "get",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).clearAll();
                                var data = result.data;
                                if (buildingModelView.prototype.isEmpty(data)) {
                                    $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).initForm(data);
                                    buildingModelView.prototype.sonModelMethod.buildingFunction.init(data);
                                }
                                $('#' + buildingModelView.prototype.config().examineBuildingFunctionBox).modal("show");
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().examineBuildingFunctionFrm,true);
                    data.buildingId = buildingCommon.getBuildingId();
                    $.ajax({
                        url: getContextPath() + "/basicBuildingFunction/saveAndUpdateBasicBuildingFunction",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "保存成功");
                                $('#' + buildingModelView.prototype.config().examineBuildingFunctionBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                showModel: function () {
                    $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).clearAll();
                    buildingModelView.prototype.sonModelMethod.buildingFunction.init({});
                    $('#' + buildingModelView.prototype.config().examineBuildingFunctionBox).modal("show");
                },
                removeData: function (id) {
                    $.ajax({
                        url: getContextPath() + "/basicBuildingFunction/deleteBasicBuildingFunction",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "删除成功");
                                buildingModelView.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                            }
                            else {
                                AlertError("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                init: function (item) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decoration_part, item.decorationPart, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.decorationPart').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_function_type, item.type, function (html, optionArray) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.type').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = commonColumn.buildingFunctionColumn();
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingFunction.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                            str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingFunction.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().examineBuildingFunctionTable, getContextPath() + "/basicBuildingFunction/getBootstrapTableVo", cols, {
                        buildingId: buildingCommon.getBuildingId()
                    }, {
                        showColumns: false,
                        showRefresh: false,
                        search: false,
                        onLoadSuccess: function () {
                            $('.tooltips').tooltip();
                        }
                    });
                }
            }
        }
    };

    //绑定事件
    $("#" + buildingModelView.prototype.config().sonTable).closest('.full-height').find('.card-header').bind('click', function () {
        buildingModelView.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
    });
    //绑定事件
    $("#" + buildingModelView.prototype.config().examineBuildingSurfaceTable).closest('.full-height').find('.card-header').bind('click', function () {
        buildingModelView.prototype.sonModelMethod.buildingSurface.loadDataDicList();
    });
    //绑定事件
    $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceTable).closest('.full-height').find('.card-header').bind('click', function () {
        buildingModelView.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
    });
    //绑定事件
    $("#" + buildingModelView.prototype.config().examineBuildingFunctionTable).closest('.full-height').find('.card-header').bind('click', function () {
        buildingModelView.prototype.sonModelMethod.buildingFunction.loadDataDicList();
    });

})();
