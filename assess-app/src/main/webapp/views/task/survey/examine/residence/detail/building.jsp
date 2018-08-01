<%--
 楼栋基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_content">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-default"
                            data-toggle="modal" href="#divBox" onclick="building.firstData(this)"> 第一栋
                    </button>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-default"
                            data-toggle="modal" href="#divBox" onclick="building.twoData(this)"> 第二栋
                    </button>
                </div>
            </div>
        </div>

    </form>

    <form class="form-horizontal" id="frmExamineBuilding_">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                           data-rule-number='true' class="form-control" required="required">
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
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层起
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层起(数字)" name="floorStart"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    径深
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="径深(数字)" data-rule-number='true'
                           name="diameterDepth" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    净高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="净高(数字)" data-rule-number='true'
                           name="netHeight" class="form-control">
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
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <select name="propertyType"
                            class="form-control search-select select2 propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构上级
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructure"
                            class="form-control search-select select2 buildingStructure">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构(下级)
                </label>
                <div class="col-sm-3">
                    <select id="frmExamineBuilding_buildingStructure"
                            name="buildingstructurepid"
                            class="form-control search-select select2 buildingstructurepid">
                        <option>请先选择建筑结构上级</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋基础 建筑类别
                </label>
                <div class="col-sm-3">
                    <select name="buildingCategory"
                            class="form-control search-select select2 buildingCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋基础 建筑公司
                </label>
                <div class="col-sm-3">
                    <select name="builderId"
                            class="form-control search-select select2 builderId">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋基础 物业公司
                </label>
                <div class="col-sm-3">
                    <select name="propertyId"
                            class="form-control search-select select2 propertyId">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">平面图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外装图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div id="_building_floor_Appearance_figure"></div>
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

<script>
    var building_config ;
    (function () {
        var frm = "frmExamineBuilding_" ;
        var sonTableID = "ExamineBuildingOutfitList" ;
        var building_floor_plan = "building_floor_plan" ;//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
        var building_figure_outside = "building_figure_outside" ;//外装图id和字段
        var building_floor_Appearance_figure = "building_floor_Appearance_figure" ; //外观图id和字段
        building_config = new Object();
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
            var data =  formParams(frm);
            var id = data.id;
            if (id == 0){
                return 0;
            }
            if (id == ''){
                return 0;
            }
            if (id == null){
                return 0;
            }
            return id;
        };
    })();
    var building =  Object.create(building_config);
    building.init = function () {
        building.select2LoadData();
        building.showFiles();
    };
    building.firstData = function (target) {
        var data = {};
        if ($("#declareId").size() > 0){
            data.declareId = $("#declareId").val();
        }
        if ($("#examineType").size() > 0){
            data.examineType = $("#examineType").val();
        }
        $("#" + building.getFrm()).clearAll();
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/getFirstData",
            type: "get",
            data:data,
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (building.isNotNull(result.data)){
                        if ($(target).size() > 0){
                            $(target).removeClass();
                            $(target).addClass("btn btn-primary");
                        }
                        building.writeData(result.data);
                    }
                    building.showFiles();
                    building.subLoadDataList();
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    building.twoData = function (target) {
        var data = {};
        if ($("#declareId").size() > 0){
            data.declareId = $("#declareId").val();
        }
        if ($("#examineType").size() > 0){
            data.examineType = $("#examineType").val();
        }
        $("#" + building.getFrm()).clearAll();
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/getTwoData",
            type: "get",
            data:data,
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (building.isNotNull(result.data)){
                        if ($(target).size() > 0){
                            $(target).removeClass();
                            $(target).addClass("btn btn-primary");
                        }
                        building.writeData(result.data);
                    }
                    building.showFiles();
                    building.subLoadDataList();
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    building.writeData = function (item) {
        if (building.isNotNull(item)){
            $("#" + building.getFrm()).initForm(item);
            $("#" + building.getFrm()+" .openTime").val(formatDate(item.openTime));
            $("#" + building.getFrm()+" .roomTime").val(formatDate(item.roomTime));
            if (item.buildingCategory == null || item.buildingCategory == '') {
                $("#" + building.getFrm() + " .buildingCategory").val(null).trigger("change");
            } else {
                $("#" + building.getFrm() + " .buildingCategory").val(item.buildingCategory).trigger("change");
            }
            if (item.buildingStructure == null || item.buildingStructure == '') {
                $("#" + building.getFrm() + " .buildingStructure").val(null).trigger("change");
            } else {
                $("#" + building.getFrm() + " .buildingStructure").val(item.buildingStructure).trigger("change");
            }
            if (item.buildingstructurepid == null || item.buildingstructurepid == '') {
                $("#" + building.getFrm() + " .buildingstructurepid").val(null).trigger("change");
            } else {
                $("#" + building.getFrm() + " .buildingstructurepid").val(item.buildingstructurepid).trigger("change");
            }
            if (item.propertyType == null || item.propertyType == '') {
                $("#" + building.getFrm() + " .propertyType").val(null).trigger("change");
            } else {
                $("#" + building.getFrm() + " .propertyType").val(item.propertyType).trigger("change");
            }
            if (item.builderId == null || item.builderId == '') {
                $("#" + building.getFrm() + " .builderId").val(null).trigger("change");
            } else {
                $("#" + building.getFrm() + " .builderId").val(item.builderId).trigger("change");
            }
            if (item.propertyId == null || item.propertyId == '') {
                $("#" + building.getFrm() + " .propertyId").val(null).trigger("change");
            } else {
                $("#" + building.getFrm() + " .propertyId").val(item.propertyId).trigger("change");
            }
            $("#" + building.getFrm()+" :input").attr("readonly","readonly");
        }
    };
    building.isNotNull = function (data) {
        if (data == null) {
            return false;
        }
        if (data == '') {
            return false;
        }
        if (data == "") {
            return false;
        }
        if (data == 0) {
            return false;
        }
        return true;
    };
    building.showFiles = function () {
        FileUtils.getFileShows({
            target: building.getFloorPlan(),
            formData: {
                fieldsName:building.getFloorPlan(),
                tableName: AssessDBKey.ExamineBuilding,
                tableId: building.getBuildID(),
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: building.getFigureOutside(),
            formData: {
                fieldsName:building.getFigureOutside(),
                tableName: AssessDBKey.ExamineBuilding,
                tableId: building.getBuildID(),
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: building.getAppearanceFigure(),
            formData: {
                fieldsName:building.getAppearanceFigure(),
                tableName: AssessDBKey.ExamineBuilding,
                tableId: building.getBuildID(),
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
    };
    building.select2ChangeEvent = function () {
        $("#" + building.getFrm() + " .buildingStructure").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + building.getFrm() + " .buildingStructure").eq(1).val();
            if (id != null && id != '' && id != 0) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getBasisList",
                    dataType: "JSON",
                    data: {'id': id},
                    async:false,
                    type: "GET",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + building.getFrm() + "buildingStructure").html(option);
                                $("#" + building.getFrm() + "buildingStructure").select2();//加载样式
                            }

                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
            }
        });
    };
    building.select2LoadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_examineBuilding_category",
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + building.getFrm() + " .buildingCategory").html(option);
                        $("#" + building.getFrm() + " .buildingCategory").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_building_structure",
            type: "get",
            dataType: "json",
            async:false,
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + building.getFrm() + " .buildingStructure").html(option);
                        $("#" + building.getFrm() + " .buildingStructure").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_building_type",
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + building.getFrm() + " .propertyType").html(option);
                        $("#" + building.getFrm() + " .propertyType").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
            type: "get",
            dataType: "json",
            data: {type: "DataBuilder"},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + building.getFrm() + " .builderId").html(option);
                        $("#" + building.getFrm() + " .builderId").select2();//加载样式
                    }
                    $.ajax({
                        url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                        type: "get",
                        dataType: "json",
                        data: {type: "DataProperty"},
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                var gradeNum = data.length;
                                var option = "<option value=''>请选择</option>";
                                if (gradeNum > 0) {
                                    for (var i = 0; i < gradeNum; i++) {
                                        option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                    }
                                    $("#" + building.getFrm() + " .propertyId").html(option);
                                    $("#" + building.getFrm() + " .propertyId").select2();//加载样式
                                }
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });

    };
    building.subLoadDataList = function () {
        var cols = [];
        cols.push({field: 'decorationPartName', title: '装修部位'});
        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
        cols.push({field: 'materialPriceName', title: '材料价格区间'});
        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
        $("#"+building.getSonTableID()).bootstrapTable('destroy');
        TableInit(building.getSonTableID(), "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitList", cols, {
            declareId : $("#declareId").val(),
            examineType : $("#examineType").val(),
            buildingId:building.getBuildID()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    $(function () {
        // building.init();
        //默认显示第一栋
        // building.firstData(null);
        building.select2ChangeEvent();
    });
</script>