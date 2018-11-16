/**
 * Created by kings on 2018-11-9.
 */
var buildingModel;
(function () {
    //配置的局部变量用做 对象属性 (初始化标识符)
    buildingModel = function () {
    };
    buildingModel.prototype = {
        isEmpty: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        getBuildingId:function () {
            var data = navButtonBuild.getObjArray(navButtonBuild.switchNumber);
            var buildingId = data.id;
            try {
                return basicIndexCommon.getBuildId();
                if (buildingModel.prototype.isEmpty(buildingId)){
                    // return buildingId;
                }
            } catch (e) {
                return "0" ;
            }
            return "0" ;
        },
        /**
         * @author:  zch
         * 描述:
         * @date: 页面 初始化
         **/
        viewInit: function () {
            buildingModel.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
            buildingModel.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
            buildingModel.prototype.sonModelMethod.buildingSurface.loadDataDicList();
            buildingModel.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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
                                $("#" + buildingModel.prototype.config().sonFrm).clearAll();
                                var data = result.data;
                                if (buildingModel.prototype.isEmpty(data)) {
                                    $("#" + buildingModel.prototype.config().sonFrm).initForm(data);
                                    buildingModel.prototype.sonModelMethod.buildingOutfit.init(data);
                                }
                                $('#' + buildingModel.prototype.config().sonBox).modal("show");
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModel.prototype.config().sonFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModel.prototype.config().sonFrm);
                    data.buildingNumber = buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModel.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingOutfit/saveAndUpdateBasicBuildingOutfit",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModel.prototype.config().sonBox).modal('hide');
                                buildingModel.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
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
                    $("#" + buildingModel.prototype.config().sonFrm).clearAll();
                    buildingModel.prototype.sonModelMethod.buildingOutfit.init({});
                    $('#' + buildingModel.prototype.config().sonBox).modal("show");
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
                                buildingModel.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
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
                        $("#" + buildingModel.prototype.config().sonFrm).find('select.decorationPart').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.decoratingMaterial, function (html, data) {
                        $("#" + buildingModel.prototype.config().sonFrm).find('select.decoratingMaterial').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPrice, function (html, data) {
                        $("#" + buildingModel.prototype.config().sonFrm).find('select.materialPrice').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                        $("#" + buildingModel.prototype.config().sonFrm).find('select.constructionTechnology').empty().html(html).trigger('change');
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
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModel.prototype.sonModelMethod.buildingOutfit.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModel.prototype.sonModelMethod.buildingOutfit.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModel.prototype.config().sonTable).bootstrapTable('destroy');
                    TableInit(buildingModel.prototype.config().sonTable, getContextPath()+"/basicBuildingOutfit/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0',
                        buildingId:buildingModel.prototype.getBuildingId()
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
                                $("#" + buildingModel.prototype.config().examineBuildingSurfaceFrm).clearAll();
                                var data = result.data;
                                if (buildingModel.prototype.isEmpty(data)) {
                                    $("#" + buildingModel.prototype.config().examineBuildingSurfaceFrm).initForm(data);
                                    buildingModel.prototype.sonModelMethod.buildingSurface.init(data);
                                }
                                $('#' + buildingModel.prototype.config().examineBuildingSurfaceBox).modal("show");
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModel.prototype.config().examineBuildingSurfaceFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModel.prototype.config().examineBuildingSurfaceFrm);
                    data.buildingNumber = buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModel.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingSurface/saveAndUpdateBasicBuildingSurface",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModel.prototype.config().examineBuildingSurfaceBox).modal('hide');
                                buildingModel.prototype.sonModelMethod.buildingSurface.loadDataDicList();
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
                    $("#" + buildingModel.prototype.config().examineBuildingSurfaceFrm).clearAll();
                    buildingModel.prototype.sonModelMethod.buildingSurface.init({});
                    $('#' + buildingModel.prototype.config().examineBuildingSurfaceBox).modal("show");
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
                                buildingModel.prototype.sonModelMethod.buildingSurface.loadDataDicList();
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
                        $("#" + buildingModel.prototype.config().examineBuildingSurfaceFrm).find('select.structure').empty().html(html).trigger('change');
                    });
                },
                loadDataDicList: function () {
                    var cols = [];
                    cols.push({field: 'structureName', title: '层面结构'});
                    cols.push({field: 'description', title: '描述'});
                    cols.push({
                        field: 'id', title: '操作', formatter: function (value, row, index) {
                            var str = '<div class="btn-margin">';
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModel.prototype.sonModelMethod.buildingSurface.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModel.prototype.sonModelMethod.buildingSurface.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModel.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                    TableInit(buildingModel.prototype.config().examineBuildingSurfaceTable, getContextPath()+"/basicBuildingSurface/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0',
                        buildingId:buildingModel.prototype.getBuildingId()
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
                                $("#" + buildingModel.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                                var data = result.data;
                                if (buildingModel.prototype.isEmpty(data)) {
                                    $("#" + buildingModel.prototype.config().examineBuildingMaintenanceFrm).initForm(data);
                                    buildingModel.prototype.sonModelMethod.buildingMaintenance.init(data);
                                }
                                $('#' + buildingModel.prototype.config().examineBuildingMaintenanceBox).modal("show");
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModel.prototype.config().examineBuildingMaintenanceFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModel.prototype.config().examineBuildingMaintenanceFrm);
                    data.buildingNumber = buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModel.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingMaintenance/saveAndUpdateBasicBuildingMaintenance",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModel.prototype.config().examineBuildingMaintenanceBox).modal('hide');
                                buildingModel.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
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
                    $("#" + buildingModel.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                    buildingModel.prototype.sonModelMethod.buildingMaintenance.init({});
                    $('#' + buildingModel.prototype.config().examineBuildingMaintenanceBox).modal("show");
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
                                buildingModel.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
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
                        $("#" + buildingModel.prototype.config().examineBuildingMaintenanceFrm).find('select.category').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_materialquality, item.materialQuality, function (html, data) {
                        $("#" + buildingModel.prototype.config().examineBuildingMaintenanceFrm).find('select.materialQuality').empty().html(html).trigger('change');
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
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModel.prototype.sonModelMethod.buildingMaintenance.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModel.prototype.sonModelMethod.buildingMaintenance.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModel.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                    TableInit(buildingModel.prototype.config().examineBuildingMaintenanceTable, getContextPath()+"/basicBuildingMaintenance/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ,
                        buildingId:buildingModel.prototype.getBuildingId()
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
                                $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).clearAll();
                                var data = result.data;
                                if (buildingModel.prototype.isEmpty(data)) {
                                    $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).initForm(data);
                                    buildingModel.prototype.sonModelMethod.buildingFunction.init(data);
                                }
                                $('#' + buildingModel.prototype.config().examineBuildingFunctionBox).modal("show");
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                saveData: function () {
                    if (!$("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).valid()) {
                        return false;
                    }
                    var data = formParams(buildingModel.prototype.config().examineBuildingFunctionFrm);
                    data.buildingNumber = buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ;
                    data.buildingId = buildingModel.prototype.getBuildingId();
                    $.ajax({
                        url: getContextPath()+"/basicBuildingFunction/saveAndUpdateBasicBuildingFunction",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('保存成功');
                                $('#' + buildingModel.prototype.config().examineBuildingFunctionBox).modal('hide');
                                buildingModel.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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
                    $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).clearAll();
                    buildingModel.prototype.sonModelMethod.buildingFunction.init({});
                    $('#' + buildingModel.prototype.config().examineBuildingFunctionBox).modal("show");
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
                                buildingModel.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).find('select.decorationPart').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.decoratingMaterial, function (html, data) {
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).find('select.decoratingMaterial').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPrice, function (html, data) {
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).find('select.materialPrice').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).find('select.constructionTechnology').empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_function_type, item.type, function (html, optionArray) {
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionFrm).find('select.type').empty().html(html).trigger('change');
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
                            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="buildingModel.prototype.sonModelMethod.buildingFunction.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="buildingModel.prototype.sonModelMethod.buildingFunction.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                            str += '</div>';
                            return str;
                        }
                    });
                    $("#" + buildingModel.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                    TableInit(buildingModel.prototype.config().examineBuildingFunctionTable, getContextPath()+"/basicBuildingFunction/getBootstrapTableVo", cols, {
                        buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber)?navButtonBuild.switchNumber:'0' ,
                        buildingId:buildingModel.prototype.getBuildingId()
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
