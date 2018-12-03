<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineBuildingOutfitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industrySurface">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>层面结构 <label class="control-label ExamineBuildingSurfaceList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineBuildingSurfaceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryMaintenance">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>维护结构 <label class="control-label ExamineBuildingMaintenanceList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineBuildingMaintenanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>维护结构 <label class="control-label examineBuildingFunctionList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="examineBuildingFunctionList">
        </table>
    </div>
</div>

<script type="application/javascript">
    var buildingModelDetail;
    (function () {
        //配置的局部变量用做 对象属性 (初始化标识符)
        buildingModelDetail = function () {
        };
        buildingModelDetail.prototype = {
            isEmpty: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            getBuildingId: function () {
                return buildingCommon.buildingMainForm.find('[name=id]').val();
            },
            /**
             * @author:  zch
             * 描述:
             * @date: 页面 初始化
             **/
            viewInit: function () {
                buildingModelDetail.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                buildingModelDetail.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                buildingModelDetail.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                buildingModelDetail.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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
                        $("#" + buildingModelDetail.prototype.config().sonTable).bootstrapTable('destroy');
                        TableInit(buildingModelDetail.prototype.config().sonTable, "${pageContext.request.contextPath}/basicBuildingOutfit/getBootstrapTableVo", cols, {
                            buildingId: buildingCommon.getBuildingId(),
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
                        $("#" + buildingModelDetail.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                        TableInit(buildingModelDetail.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/basicBuildingSurface/getBootstrapTableVo", cols, {
                            buildingId: buildingCommon.getBuildingId(),
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
                        $("#" + buildingModelDetail.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                        TableInit(buildingModelDetail.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/basicBuildingMaintenance/getBootstrapTableVo", cols, {
                            buildingId: buildingCommon.getBuildingId(),
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
                        cols.push({field: 'typeName', title: '类型'});
                        cols.push({field: 'decorationPartName', title: '装修部位'});
                        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                        cols.push({field: 'materialPriceName', title: '材料价格区间'});
                        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                        $("#" + buildingModelDetail.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                        TableInit(buildingModelDetail.prototype.config().examineBuildingFunctionTable, "${pageContext.request.contextPath}/basicBuildingFunction/getBootstrapTableVo", cols, {
                            buildingId: buildingCommon.getBuildingId(),
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

