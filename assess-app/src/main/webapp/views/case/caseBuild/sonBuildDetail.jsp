<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>
            楼栋外装
        </h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <div>
            <table class="table table-bordered" id="ExamineBuildingOutfitList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>
            建筑功能
        </h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <div>
            <table class="table table-bordered" id="examineBuildingFunctionList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<c:if test="${caseBuilding.type eq 1}">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>
                屋面结构
            </h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <div>
                <table class="table table-bordered" id="ExamineBuildingSurfaceList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>
                围护结构
            </h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <div>
                <table class="table table-bordered" id="ExamineBuildingMaintenanceList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</c:if>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/case/case.common.js"></script>
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
                return $('#basicBuildingFrm').find('[name=id]').val();
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
                        var cols = commonColumn.buildingOutfitColumn();
                        $("#" + buildingModel.prototype.config().sonTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().sonTable, "${pageContext.request.contextPath}/caseBuildingOutfit/getCaseBuildingOutfitList", cols, {
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval: true
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
                    loadDataDicList: function () {
                        var cols = commonColumn.buildingSurfaceColumn();
                        $("#" + buildingModel.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/caseBuildingSurface/getCaseBuildingSurfaceList", cols, {
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval: true
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
                    loadDataDicList: function () {
                        var cols = commonColumn.buildingMaintenanceColumn();
                        $("#" + buildingModel.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/caseBuildingMaintenance/getCaseBuildingMaintenanceList", cols, {
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval: true
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
                        var cols = commonColumn.buildingFunctionColumn();
                        $("#" + buildingModel.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                        TableInit(buildingModel.prototype.config().examineBuildingFunctionTable, "${pageContext.request.contextPath}/caseBuildingFunction/getCaseBuildingFunctionList", cols, {
                            buildingId: buildingModel.prototype.getBuildingId(),
                            approval: true
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

