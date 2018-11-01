<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="buildingModel.prototype.sonModelMethod.buildingOutfit.showModel();"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingOutfitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            层面结构 <label class="control-label ExamineBuildingSurfaceList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="buildingModel.prototype.sonModelMethod.buildingSurface.showModel()"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingSurfaceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            维护结构 <label class="control-label ExamineBuildingMaintenanceList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="buildingModel.prototype.sonModelMethod.buildingMaintenance.showModel();"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingMaintenanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            建筑功能 <label class="control-label examineBuildingFunctionList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="buildingModel.prototype.sonModelMethod.buildingFunction.showModel();"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="examineBuildingFunctionList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="application/javascript">
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
                            url: "${pageContext.request.contextPath}/basicBuildingOutfit/getBasicBuildingOutfitById",
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
                            url: "${pageContext.request.contextPath}/basicBuildingOutfit/saveAndUpdateBasicBuildingOutfit",
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
                            url: "${pageContext.request.contextPath}/basicBuildingOutfit/deleteBasicBuildingOutfit",
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
                        TableInit(buildingModel.prototype.config().sonTable, "${pageContext.request.contextPath}/basicBuildingOutfit/getBootstrapTableVo", cols, {
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
                            url: "${pageContext.request.contextPath}/basicBuildingSurface/getBasicBuildingSurfaceById",
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
                            url: "${pageContext.request.contextPath}/basicBuildingSurface/saveAndUpdateBasicBuildingSurface",
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
                            url: "${pageContext.request.contextPath}/basicBuildingSurface/deleteBasicBuildingSurface",
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
                        TableInit(buildingModel.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/basicBuildingSurface/getBootstrapTableVo", cols, {
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
                            url: "${pageContext.request.contextPath}/basicBuildingMaintenance/getBasicBuildingMaintenanceById",
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
                            url: "${pageContext.request.contextPath}/basicBuildingMaintenance/saveAndUpdateBasicBuildingMaintenance",
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
                            url: "${pageContext.request.contextPath}/basicBuildingMaintenance/deleteBasicBuildingMaintenance",
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
                        TableInit(buildingModel.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/basicBuildingMaintenance/getBootstrapTableVo", cols, {
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
                            url: "${pageContext.request.contextPath}/basicBuildingFunction/getBasicBuildingFunctionById",
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
                            url: "${pageContext.request.contextPath}/basicBuildingFunction/saveAndUpdateBasicBuildingFunction",
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
                            url: "${pageContext.request.contextPath}/basicBuildingFunction/deleteBasicBuildingFunction",
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
                    },
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'waterProof', title: '防水'});
                        cols.push({field: 'heatPreservation', title: '保温'});
                        cols.push({field: 'heatInsulation', title: '隔热'});
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
                        TableInit(buildingModel.prototype.config().examineBuildingFunctionTable, "${pageContext.request.contextPath}/basicBuildingFunction/getBootstrapTableVo", cols, {
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

</script>

<div id="divBoxExamineBuildingOutfit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋外装情况</h3>
            </div>
            <form id="frmExamineBuildingOutfit" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="buildingModel.prototype.sonModelMethod.buildingOutfit.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineBuildingMaintenance" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">维护结构</h3>
            </div>
            <form id="ExamineBuildingMaintenanceFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="名称"
                                                   name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分类
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材质
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialQuality"
                                                    class="form-control search-select select2 materialQuality">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="buildingModel.prototype.sonModelMethod.buildingMaintenance.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="divBoxExamineBuildingSurface" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">层面结构</h3>
            </div>
            <form id="ExamineBuildingSurfaceFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            层面结构
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="structure"
                                                    class="form-control search-select select2 structure">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            描述
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="描述"
                                                   name="description" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="buildingModel.prototype.sonModelMethod.buildingSurface.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="examineBuildingFunction" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑功能</h3>
            </div>
            <form id="examineBuildingFunctionFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            保温
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="保温"
                                                   name="heatPreservation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            防水
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="防水"
                                                   name="waterProof" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔热
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="隔热"
                                                   name="heatInsulation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="buildingModel.prototype.sonModelMethod.buildingFunction.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

