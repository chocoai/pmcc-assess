<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 楼栋基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_content">
    <form class="form-horizontal" id="navButtonBuild">
        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="building.getNumberData(this,1)">
                            楼栋基础
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="building.getNumberData(this,2)">
                            第二部分
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="building.getNumberData(this,3)">
                            第三部分
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="building.getNumberData(this,4)">
                            第四部分
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </form>

    <form class="form-horizontal" id="frmExamineBuilding_">
        <input type="hidden" name="id">
        <input type="hidden" name="identifier">
        <input type="hidden" id="oneExamineBuilding"
               value='${surveyExamineDataInfoVo.examineBuildingVoMap['oneExamineBuilding'].jsonContent}'>
        <input type="hidden" id="twoExamineBuilding"
               value='${surveyExamineDataInfoVo.examineBuildingVoMap['twoExamineBuilding'].jsonContent}'>
        <input type="hidden" id="threeExamineBuilding"
               value='${surveyExamineDataInfoVo.examineBuildingVoMap['threeExamineBuilding'].jsonContent}'>
        <input type="hidden" id="fourExamineBuilding"
               value='${surveyExamineDataInfoVo.examineBuildingVoMap['fourExamineBuilding'].jsonContent}'>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval" readonly="readonly"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                           class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    公共设施使用费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="公共设施使用费(数字)" name="facilitiesUseFee"
                           class="form-control" readonly="readonly">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层起
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层起(数字)" name="floorStart" readonly="readonly"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层止(数字)" name="floorEnd" readonly="readonly"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)"
                           name="floorCount" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)"
                           name="buildingHeight" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)"
                           name="buildingArea" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)"
                           name="coverAnArea" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)"
                           name="floorHeight" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    进深
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="进深(数字)" readonly="readonly"
                           name="diameterDepth" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)"
                           name="landUseYear" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    净高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="净高(数字)"
                           name="netHeight" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在位置" name="location" class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control openTime" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="交房时间"
                           name="roomTime" readonly="readonly" data-date-format="yyyy-mm-dd"
                           class="form-control roomTime">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <input type="text" data-title="propertyType" value="${examineBuildingVo.propertyTypeName}"
                           readonly="readonly" name="propertyTypeName" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构上级
                </label>
                <div class="col-sm-3">
                    <input type="text" data-title="buildingStructure" value="${examineBuildingVo.buildingStructureName}"
                           readonly="readonly" name="buildingStructureName" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构(下级)
                </label>
                <div class="col-sm-3">
                    <input type="text" data-title="buildingstructurePid"
                           value="${examineBuildingVo.buildingstructurePid}" readonly="readonly"
                           name="buildingstructurePid" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类别
                </label>
                <div class="col-sm-3">
                    <input type="text" data-title="buildingCategory" value="${examineBuildingVo.buildingCategoryName}"
                           readonly="readonly" name="buildingCategoryName" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司
                </label>
                <div class="col-sm-3">
                    <input type="text" data-title="builderId" value="${examineBuildingVo.builderName}"
                           readonly="readonly" name="builderName" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司
                </label>
                <div class="col-sm-3">
                    <input type="text" data-title="propertyId" value="${examineBuildingVo.propertyName}"
                           readonly="readonly" name="propertyName" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">平面图<span class="symbol readonly"></span></label>
                <div class="col-sm-5">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>
        </div>


    </form>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装
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
            屋面结构
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
            围护结构
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
            建筑功能
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="examineBuildingFunctionList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var building_config;
    (function () {
        var frm = "frmExamineBuilding_";
        var sonTableID = "ExamineBuildingOutfitList";
        var examineBuildingSurfaceTable = "ExamineBuildingSurfaceList";
        var examineBuildingMaintenanceTable = "ExamineBuildingMaintenanceList";
        var examineBuildingFunctionTable = "examineBuildingFunctionList";
        var building_floor_plan = "building_floor_plan";//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
        var building_figure_outside = "building_figure_outside";//外装图id和字段
        var building_floor_Appearance_figure = "building_floor_Appearance_figure"; //外观图id和字段
        building_config = new Object();
        building_config.getExamineBuildingSurfaceTable = function () {
            return examineBuildingSurfaceTable;
        };
        building_config.getExamineBuildingMaintenanceTable = function () {
            return examineBuildingMaintenanceTable;
        };
        building_config.getExamineBuildingFunctionTable = function () {
            return examineBuildingFunctionTable;
        };
        building_config.getFrm = function () {
            return frm;
        };
        building_config.getSonTableID = function () {
            return sonTableID;
        };
        building_config.getFloorPlan = function () {
            return building_floor_plan;
        };
        building_config.getFigureOutside = function () {
            return building_figure_outside;
        };
        building_config.getAppearanceFigure = function () {
            return building_floor_Appearance_figure;
        };
        building_config.getBuildID = function () {
            var data = formParams(frm);
            var id = data.id;
            if (id == 0) {
                return 0;
            }
            if (id == '') {
                return 0;
            }
            if (id == null) {
                return 0;
            }
            return id;
        };
    })();

    var building = Object.create(building_config);

    building.init = function () {
        building.showFiles();
    };
    building.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    building.getIdentifier = function () {
        var data = formParams(building.getFrm());
        var identifier = data.identifier;
        if (building.isEmpty(identifier)) {
            return identifier;
        }
        return "0";
    },
        building.getNumberData = function (target, number) {
            var temp = "${surveyExamineDataInfoVo.examineBuildingVoMap}".split(",");
            if (number > temp.length) {
                toastr.success('数据不存在!');
                return false;
            }
            var data = "";
            if (number == 1) {
                data = $("#oneExamineBuilding").val();
            }
            if (number == 2) {
                data = $("#twoExamineBuilding").val();
            }
            if (number == 3) {
                data = $("#threeExamineBuilding").val();
            }
            if (number == 3) {
                data = $("#fourExamineBuilding").val();
            }
            if (building.isEmpty(data)) {
                data = JSON.parse(data);
                building.writeData(data);
            }
            if ($("#navButtonBuild button").size() > 0) {
                $.each($("#navButtonBuild button"), function (i, n) {
                    $(n).removeClass();
                    $(n).addClass("btn btn-default");
                });
            }
            if ($(target).size() > 0) {
                $(target).removeClass();
                $(target).addClass("btn btn-primary");
            }
            if (building.isEmpty(data)) {
                building.newFileShows(building.getFloorPlan(), building.getFloorPlan() + data.identifier);
            }
            building.subLoadDataList();
            building.examineBuildingMaintenanceLoadList();
            building.examineBuildingSurfaceLoadList();
            building.examineBuildingFunctionList();
        };


    building.writeData = function (item) {
        if (building.isEmpty(item)) {
            $("#" + building.getFrm()).initForm(item);
            $("#" + building.getFrm() + " .openTime").val(formatDate(item.openTime));
            $("#" + building.getFrm() + " .roomTime").val(formatDate(item.roomTime));
        }
    };

    building.newFileShows = function (target, fieldsName) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.ExamineBuilding,
                tableId: building.getBuildID(),
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
    };
    building.subLoadDataList = function () {
        var cols = [];
        cols.push({field: 'decorationPartName', title: '装修部位'});
        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
        cols.push({field: 'materialPriceName', title: '材料价格区间'});
        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
        $("#" + building.getSonTableID()).bootstrapTable('destroy');
        TableInit(building.getSonTableID(), "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitList", cols, {
            declareId: $("#declareId").val(),
            examineType: $("#examineType").val(),
            planDetailsId: $("#planDetailsId").val(),
            buildNumber: building.getIdentifier()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
    building.examineBuildingSurfaceLoadList = function () {
        var cols = [];
        cols.push({field: 'structureName', title: '屋面结构'});
        cols.push({field: 'description', title: '描述'});
        $("#" + building.getExamineBuildingSurfaceTable()).bootstrapTable('destroy');
        TableInit(building.getExamineBuildingSurfaceTable(), "${pageContext.request.contextPath}/examineBuildingSurface/getExamineBuildingSurfaceList", cols, {
            declareId: $("#declareId").val(),
            examineType: $("#examineType").val(),
            planDetailsId: $("#planDetailsId").val(),
            buildNumber: building.getIdentifier()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
    building.examineBuildingMaintenanceLoadList = function () {
        var cols = [];
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'materialQualityName', title: '材质'});
        cols.push({field: 'name', title: '名称'});
        $("#" + building.getExamineBuildingMaintenanceTable()).bootstrapTable('destroy');
        TableInit(building.getExamineBuildingMaintenanceTable(), "${pageContext.request.contextPath}/examineBuildingMaintenance/getExamineBuildingMaintenanceList", cols, {
            declareId: $("#declareId").val(),
            examineType: $("#examineType").val(),
            planDetailsId: $("#planDetailsId").val(),
            buildNumber: building.getIdentifier()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
    building.examineBuildingFunctionList = function () {
        var cols = [];
        cols.push({field: 'waterProof', title: '防水'});
        cols.push({field: 'heatPreservation', title: '保温'});
        cols.push({field: 'heatInsulation', title: '隔热'});
        cols.push({field: 'decorationPartName', title: '装修部位'});
        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
        cols.push({field: 'materialPriceName', title: '材料价格区间'});
        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
        $("#" + building.getExamineBuildingFunctionTable()).bootstrapTable('destroy');
        TableInit(building.getExamineBuildingFunctionTable(), "${pageContext.request.contextPath}/examineBuildingFunction/getExamineBuildingFunctionList", cols, {
            declareId: $("#declareId").val(),
            examineType: $("#examineType").val(),
            planDetailsId: $("#planDetailsId").val(),
            buildNumber: building.getIdentifier()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

</script>