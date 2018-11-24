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
        getBuildingId:function () {
            return $('#'+objectData.config.basicBuilding.frm).find('[name=id]').val();
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
                        url: getContextPath()+"/basicBuildingOutfit/getBasicBuildingOutfitById",
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
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().sonFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().sonFrm);
                    data.buildingNumber = buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModelView.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingOutfit/saveAndUpdateBasicBuildingOutfit",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModelView.prototype.config().sonBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
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
                        url: getContextPath()+"/basicBuildingOutfit/deleteBasicBuildingOutfit",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('删除成功');
                                buildingModelView.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                init: function (item) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decoration_part, item.decorationPart, function (html, data) {
                        $("#" + buildingModelView.prototype.config().sonFrm).find('select.decorationPart').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.decoratingMaterial, function (html, data) {
                        $("#" + buildingModelView.prototype.config().sonFrm).find('select.decoratingMaterial').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPrice, function (html, data) {
                        $("#" + buildingModelView.prototype.config().sonFrm).find('select.materialPrice').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                        $("#" + buildingModelView.prototype.config().sonFrm).find('select.constructionTechnology').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = [];
                    cols.push({field: 'decorationPartName', title: '装修部位'});
                    cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                    cols.push({field: 'materialPriceName', title: '材料价格区间'});
                    cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingOutfit.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingOutfit.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().sonTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().sonTable, getContextPath()+"/basicBuildingOutfit/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0',
                        buildingId:buildingModelView.prototype.getBuildingId()
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
            /**层面结构**/
            buildingSurface: {
                getAndInit: function (id) {
                    $.ajax({
                        url: getContextPath()+"/basicBuildingSurface/getBasicBuildingSurfaceById",
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
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().examineBuildingSurfaceFrm);
                    data.buildingNumber = buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModelView.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingSurface/saveAndUpdateBasicBuildingSurface",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModelView.prototype.config().examineBuildingSurfaceBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
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
                        url: getContextPath()+"/basicBuildingSurface/deleteBasicBuildingSurface",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('删除成功');
                                buildingModelView.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                init: function (item) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_structure, item.structure, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingSurfaceFrm).find('select.structure').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = [];
                    cols.push({field: 'structureName', title: '层面结构'});
                    cols.push({field: 'description', title: '描述'});
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingSurface.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingSurface.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().examineBuildingSurfaceTable, getContextPath()+"/basicBuildingSurface/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0',
                        buildingId:buildingModelView.prototype.getBuildingId()
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
            /**维护结构**/
            buildingMaintenance: {
                getAndInit: function (id) {
                    $.ajax({
                        url: getContextPath()+"/basicBuildingMaintenance/getBasicBuildingMaintenanceById",
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
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().examineBuildingMaintenanceFrm);
                    data.buildingNumber = buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModelView.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingMaintenance/saveAndUpdateBasicBuildingMaintenance",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModelView.prototype.config().examineBuildingMaintenanceBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
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
                        url: getContextPath()+"/basicBuildingMaintenance/deleteBasicBuildingMaintenance",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('删除成功');
                                buildingModelView.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                init: function (item) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_maintenance_category, item.category, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).find('select.category').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_materialquality, item.materialQuality, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceFrm).find('select.materialQuality').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = [];
                    cols.push({field: 'categoryName', title: '类别'});
                    cols.push({field: 'materialQualityName', title: '材质'});
                    cols.push({field: 'name', title: '名称'});
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingMaintenance.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingMaintenance.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().examineBuildingMaintenanceTable, getContextPath()+"/basicBuildingMaintenance/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ,
                        buildingId:buildingModelView.prototype.getBuildingId()
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
                        url: getContextPath()+"/basicBuildingFunction/getBasicBuildingFunctionById",
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
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModelView.prototype.config().examineBuildingFunctionFrm);
                    data.buildingNumber = buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModelView.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingFunction/saveAndUpdateBasicBuildingFunction",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModelView.prototype.config().examineBuildingFunctionBox).modal('hide');
                                buildingModelView.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
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
                        url: getContextPath()+"/basicBuildingFunction/deleteBasicBuildingFunction",
                        type: "post",
                        dataType: "json",
                        data: {id: id},
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('删除成功');
                                buildingModelView.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                init: function (item) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decoration_part, item.decorationPart, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.decorationPart').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.decoratingMaterial, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.decoratingMaterial').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPrice, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.materialPrice').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.constructionTechnology').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_function_type, item.type, function (html, optionArray) {
                        $("#" + buildingModelView.prototype.config().examineBuildingFunctionFrm).find('select.type').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = [];
                    cols.push({field: 'typeName', title: '类型'});
                    cols.push({field: 'decorationPartName', title: '装修部位'});
                    cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                    cols.push({field: 'materialPriceName', title: '材料价格区间'});
                    cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModelView.prototype.sonModelMethod.buildingFunction.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModelView.prototype.sonModelMethod.buildingFunction.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModelView.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                    TableInit(buildingModelView.prototype.config().examineBuildingFunctionTable, getContextPath()+"/basicBuildingFunction/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModelView.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ,
                        buildingId:buildingModelView.prototype.getBuildingId()
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
    }
})();
