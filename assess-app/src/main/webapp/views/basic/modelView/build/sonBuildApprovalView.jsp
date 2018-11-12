<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label>
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
            getBuildingId: function () {
                var data = formParams(objectData.config.basicBuilding.frm);
                var buildingId = data.id;
                try {
                    if (buildingModel.prototype.isEmpty(buildingId)) {
                        return buildingId;
                    }
                } catch (e) {
                    return "0";
                }
                return "0";
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
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'decorationPartName', title: '装修部位'});
                        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                        cols.push({field: 'materialPriceName', title: '材料价格区间'});
                        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                        $("#" + buildingModel.prototype.config().sonTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().sonTable, "${pageContext.request.contextPath}/basicBuildingOutfit/getBootstrapTableVo", cols, {
                            buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber) ? navButtonBuild.switchNumber : '0',
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval:true
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
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'structureName', title: '层面结构'});
                        cols.push({field: 'description', title: '描述'});
                        $("#" + buildingModel.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/basicBuildingSurface/getBootstrapTableVo", cols, {
                            buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber) ? navButtonBuild.switchNumber : '0',
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval:true
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
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'categoryName', title: '类别'});
                        cols.push({field: 'materialQualityName', title: '材质'});
                        cols.push({field: 'name', title: '名称'});
                        $("#" + buildingModel.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/basicBuildingMaintenance/getBootstrapTableVo", cols, {
                            buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber) ? navButtonBuild.switchNumber : '0',
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval:true
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
                    loadDataDicList: function () {
                        var cols = [];
                        // cols.push({field: 'waterProof', title: '防水'});
                        // cols.push({field: 'heatPreservation', title: '保温'});
                        // cols.push({field: 'heatInsulation', title: '隔热'});
                        cols.push({field: 'type', title: '类型'});
                        cols.push({field: 'decorationPartName', title: '装修部位'});
                        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                        cols.push({field: 'materialPriceName', title: '材料价格区间'});
                        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().examineBuildingFunctionTable, "${pageContext.request.contextPath}/basicBuildingFunction/getBootstrapTableVo", cols, {
                            buildingNumber: buildingModel.prototype.isEmpty(navButtonBuild.switchNumber) ? navButtonBuild.switchNumber : '0',
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval:true
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
</html>

