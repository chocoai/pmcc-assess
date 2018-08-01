<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <!-- 包含此文件时需要删除掉css -->
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="x_content">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-default"
                            data-toggle="modal" href="#divBox" onclick="examineBuilding_.prototype.firstData(this);"> 第一栋
                    </button>
                    <button type="button" class="btn btn-primary"
                            data-toggle="modal" href="#divBox" onclick="examineBuilding_.prototype.saveData()"> 保存
                    </button>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-default"
                            data-toggle="modal" href="#divBox" onclick="examineBuilding_.prototype.twoData(this);"> 第二栋
                    </button>
                    <button type="button" class="btn btn-primary"
                            data-toggle="modal" href="#divBox" onclick="examineBuilding_.prototype.saveData()"> 保存
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
                    <input id="building_floor_plan" name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外装图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_figure_outside" name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_floor_Appearance_figure" name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
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
            <button type="button" class="btn btn-success" data-toggle="modal" onclick="examineBuilding_.prototype.subShowModelData()"> 新增
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

</body>

<script>
    $(function () {
        //两个方法 都可以假如选项卡载入时 初始化
    });
</script>

<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var examineBuilding_ ;
    (function () {
        var flag = true;
        var sonFlag = true;
        examineBuilding_ = function () {

        };
        examineBuilding_.prototype = {
            select2IsNotNull:function (data) {
                if (data == null){
                    return false;
                }
                if (data == ''){
                    return false;
                }
                if (data == ""){
                    return false;
                }
                if (data == 0){
                    return false;
                }
                return true;
            },
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setSonFlag: function (sonFlag_) {
                sonFlag = sonFlag_;
            },
            getSonFlag: function () {
                return sonFlag;
            },
            getBuildId:function () {
                var data =  formParams(examineBuilding_.prototype.config().frm);
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
            },
            twoData:function (target) {
                var data = {};
                if ($("#declareId").size() > 0){
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0){
                    data.examineType = $("#examineType").val();
                }
                $("#" + examineBuilding_.prototype.config().frm).clearAll();
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getTwoData",
                    type: "get",
                    data:data,
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            if (result.data != null){
                                //改变按钮颜色
                                $(target).removeClass();
                                $(target).addClass("btn btn-primary");
                                $("#" + examineBuilding_.prototype.config().frm).initForm(result.data);
                                $("#" + examineBuilding_.prototype.config().frm+" .openTime").val(formatDate(result.data.openTime));
                                $("#" + examineBuilding_.prototype.config().frm+" .roomTime").val(formatDate(result.data.roomTime));
                                if (result.data.buildingCategory == null || result.data.buildingCategory == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(result.data.buildingCategory).trigger("change");
                                }
                                if (result.data.buildingStructure == null || result.data.buildingStructure == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(result.data.buildingStructure).trigger("change");
                                }
                                if (result.data.buildingstructurepid == null || result.data.buildingstructurepid == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingstructurepid").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingstructurepid").val(result.data.buildingstructurepid).trigger("change");
                                }
                                if (result.data.propertyType == null || result.data.propertyType == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(result.data.propertyType).trigger("change");
                                }
                                if (result.data.builderId == null || result.data.builderId == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(result.data.builderId).trigger("change");
                                }
                                if (result.data.propertyId == null || result.data.propertyId == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(result.data.propertyId).trigger("change");
                                }
                            }
                            examineBuilding_.prototype.showFiles();
                            examineBuilding_.prototype.subLoadDataList();
                        };
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            firstData:function (target) {
                var data = {};
                if ($("#declareId").size() > 0){
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0){
                    data.examineType = $("#examineType").val();
                }
                $("#" + examineBuilding_.prototype.config().frm).clearAll();
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getFirstData",
                    type: "get",
                    data:data,
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            if (result.data != null){
                                //改变按钮颜色
                                $(target).removeClass();
                                $(target).addClass("btn btn-primary");
                                $("#" + examineBuilding_.prototype.config().frm).initForm(result.data);
                                $("#" + examineBuilding_.prototype.config().frm+" .openTime").val(formatDate(result.data.openTime));
                                $("#" + examineBuilding_.prototype.config().frm+" .roomTime").val(formatDate(result.data.roomTime));
                                if (result.data.buildingCategory == null || result.data.buildingCategory == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(result.data.buildingCategory).trigger("change");
                                }
                                if (result.data.buildingStructure == null || result.data.buildingStructure == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(result.data.buildingStructure).trigger("change");
                                }
                                if (result.data.buildingstructurepid == null || result.data.buildingstructurepid == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingstructurepid").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .buildingstructurepid").val(result.data.buildingstructurepid).trigger("change");
                                }
                                if (result.data.propertyType == null || result.data.propertyType == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(result.data.propertyType).trigger("change");
                                }
                                if (result.data.builderId == null || result.data.builderId == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(result.data.builderId).trigger("change");
                                }
                                if (result.data.propertyId == null || result.data.propertyId == '') {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(null).trigger("change");
                                } else {
                                    $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(result.data.propertyId).trigger("change");
                                }
                            }
                            examineBuilding_.prototype.showFiles();
                            examineBuilding_.prototype.subLoadDataList();
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            viewInit: function () {
                // examineBuilding_.prototype.loadDataDicList();
                if (examineBuilding_.prototype.getFlag()){
                    examineBuilding_.prototype.init();
                    examineBuilding_.prototype.initRemoveExamineBuildingOutfit();
                    examineBuilding_.prototype.setFlag(false);
                }
                examineBuilding_.prototype.subLoadDataList();
            },
            config: function () {
                var data = {};
                data.table = "ExamineBuilding_List";
                data.box = "divBoxExamineBuilding_";
                data.frm = "frmExamineBuilding_";
                data.sonBox = "divBoxExamineBuildingOutfit";
                data.sonTable = "ExamineBuildingOutfitList";
                data.sonFrm = "frmExamineBuildingOutfit";
                data.type = "null";//
                data.database_Table = AssessDBKey.ExamineBuilding;//
                data.building_floor_plan = "building_floor_plan";//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
                data.building_figure_outside = "building_figure_outside";//外装图id和字段
                data.building_floor_Appearance_figure = "building_floor_Appearance_figure";//外观图id和字段
                return data;
            },
            subDataSave:function () {
                if (!$("#"+examineBuilding_.prototype.config().sonFrm).valid()){
                    return false;
                }
                var data = formParams(examineBuilding_.prototype.config().sonFrm);
                data.buildingId = examineBuilding_.prototype.getBuildId();
                if ($("#declareId").size() > 0){
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0){
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/saveAndUpdateExamineBuildingOutfit",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#'+examineBuilding_.prototype.config().sonBox).modal('hide');
                            examineBuilding_.prototype.subLoadDataList();
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
            subDataRemove:function (id) {
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/deleteExamineBuildingOutfitById",
                    type: "post",
                    dataType: "json",
                    data: {id:id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            examineBuilding_.prototype.subLoadDataList();
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
            subGetAndInit:function (id) {
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitById",
                    type: "get",
                    dataType: "json",
                    data: {id:id},
                    success: function (result) {
                        if (result.ret) {
                            $("#"+examineBuilding_.prototype.config().sonFrm).clearAll();
                            $("#" + examineBuilding_.prototype.config().sonFrm).initForm(result.data);
                            if (result.data.decoratingMaterial == null || result.data.decoratingMaterial == ''){
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decoratingMaterial").val(null).trigger("change");
                            }else {
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decoratingMaterial").val(result.data.decoratingMaterial).trigger("change");
                            }
                            if (result.data.materialPrice == null || result.data.materialPrice == ''){
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .materialPrice").val(null).trigger("change");
                            }else {
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .materialPrice").val(result.data.materialPrice).trigger("change");
                            }
                            if (result.data.constructionTechnology == null || result.data.constructionTechnology == ''){
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .constructionTechnology").val(null).trigger("change");
                            }else {
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .constructionTechnology").val(result.data.constructionTechnology).trigger("change");
                            }
                            if (result.data.decorationPart == null || result.data.decorationPart == ''){
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decorationPart").val(null).trigger("change");
                            }else {
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decorationPart").val(result.data.decorationPart).trigger("change");
                            }
                            $('#'+examineBuilding_.prototype.config().sonBox).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            subShowModelData:function () {
                $("#"+examineBuilding_.prototype.config().sonFrm).clearAll();
                if (examineBuilding_.prototype.getSonFlag()){
                    examineBuilding_.prototype.subDataInit();
                    examineBuilding_.prototype.setSonFlag(false);
                }
                $('#'+examineBuilding_.prototype.config().sonBox).modal("show");
            },
            subDataInit:function () {
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decorating_material",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if(gradeNum > 0){
                                for(var i = 0;i< gradeNum;i++){
                                    option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                                }
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decoratingMaterial").html(option);
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decoratingMaterial").select2({ minimumResultsForSearch: -1 });//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_material_price",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if(gradeNum > 0){
                                for(var i = 0;i< gradeNum;i++){
                                    option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                                }
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .materialPrice").html(option);
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .materialPrice").select2({ minimumResultsForSearch: -1 });//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_construction_technology",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if(gradeNum > 0){
                                for(var i = 0;i< gradeNum;i++){
                                    option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                                }
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .constructionTechnology").html(option);
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .constructionTechnology").select2({ minimumResultsForSearch: -1 });//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decoration_part",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if(gradeNum > 0){
                                for(var i = 0;i< gradeNum;i++){
                                    option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                                }
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decorationPart").html(option);
                                $("#"+examineBuilding_.prototype.config().sonFrm+" .decorationPart").select2({ minimumResultsForSearch: -1 });//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            subLoadDataList:function () {
                var cols = [];
                cols.push({field: 'decorationPartName', title: '装修部位'});
                cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                cols.push({field: 'materialPriceName', title: '材料价格区间'});
                cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.subGetAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.subDataRemove(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#"+examineBuilding_.prototype.config().sonTable).bootstrapTable('destroy');
                TableInit(examineBuilding_.prototype.config().sonTable, "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitList", cols, {
                    declareId : $("#declareId").val(),
                    examineType : $("#examineType").val(),
                    buildingId:examineBuilding_.prototype.getBuildId()
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'builderName', title: '建造商'});
                cols.push({field: 'propertyName', title: '物业公司'});
                cols.push({field: 'buildingCategoryName', title: '建筑类别'});
                cols.push({field: 'buildingStructureName', title: '建筑结构'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + examineBuilding_.prototype.config().table).bootstrapTable('destroy');
                TableInit(examineBuilding_.prototype.config().table, "${pageContext.request.contextPath}/examineBuilding/getExamineBuildingList", cols, {
                    type: examineBuilding_.prototype.config().type
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
                    url: "${pageContext.request.contextPath}/examineBuilding/deleteExamineBuildingById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            examineBuilding_.prototype.loadDataDicList();
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
                $("#" + examineBuilding_.prototype.config().frm).clearAll();
                $("#" + examineBuilding_.prototype.config().frm + " .type").val(examineBuilding_.prototype.config().type);
                $('#' + examineBuilding_.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + examineBuilding_.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(examineBuilding_.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/saveAndUpdateExamineBuilding",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            // $('#' + examineBuilding_.prototype.config().box).modal('hide');
                            // examineBuilding_.prototype.loadDataDicList();
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
            getAndInit: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getExamineBuildingById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + examineBuilding_.prototype.config().frm).clearAll();
                            $("#" + examineBuilding_.prototype.config().frm).initForm(result.data);
                            if (result.data.buildingCategory == null || result.data.buildingCategory == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(result.data.buildingCategory).trigger("change");
                            }
                            if (result.data.buildingStructurePid == null || result.data.buildingStructurePid == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(result.data.buildingStructurePid).trigger("change");
                            }
                            if (result.data.buildingStructure == null || result.data.buildingStructure == '') {
                                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").val(null).trigger("change");
                            } else {
                                console.log(result.data.buildingStructure);
                                console.log(result.data);
                                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").val(result.data.buildingStructure).trigger("change");
                                // $("#"+examineBuilding_.prototype.config().frm+" .buildingStructureV").val(result.data.buildingStructure).trigger("change");
                                // $("#frmExamineBuilding_buildingStructure").val(result.data.buildingStructure).trigger("change");
                            }
                            if (result.data.propertyType == null || result.data.propertyType == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(result.data.propertyType).trigger("change");
                            }
                            if (result.data.builderId == null || result.data.builderId == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(result.data.builderId).trigger("change");
                            }
                            if (result.data.propertyId == null || result.data.propertyId == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(result.data.propertyId).trigger("change");
                            }
                            $('#' + examineBuilding_.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            initRemoveExamineBuildingOutfit:function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/initRemoveExamineBuildingOutfit",
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('楼栋外装初始化成功');
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            init: function () {
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
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").html(option);
                                // $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").select2({minimumResultsForSearch: -1});//加载样式
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").select2();//加载样式
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
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").html(option);
                                // $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").eq(1).val();
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
                                        // $("#"+examineBuilding_.prototype.config().frm+"buildingStructure").empty();
                                        $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").html(option);
                                        // $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                                        $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2();//加载样式
                                    }

                                }
                            },
                            error: function (e) {
                                Alert("调用服务端方法失败，失败原因:" + e);
                            }
                        });
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
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").html(option);
                                // $("#" + examineBuilding_.prototype.config().frm + " .propertyType").select2({minimumResultsForSearch: -1});//加载样式
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
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
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").html(option);
                                // $("#" + examineBuilding_.prototype.config().frm + " .builderId").select2({minimumResultsForSearch: -1});//加载样式
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").select2();//加载样式
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
                                            $("#" + examineBuilding_.prototype.config().frm + " .propertyId").html(option);
                                            $("#" + examineBuilding_.prototype.config().frm + " .propertyId").select2({minimumResultsForSearch: -1});//加载样式
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
                })

            },
            uploadFiles:function () {
                FileUtils.uploadFiles({
                    target: examineBuilding_.prototype.config().building_floor_plan,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName:examineBuilding_.prototype.config().building_floor_plan,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });

                //
                FileUtils.uploadFiles({
                    target: examineBuilding_.prototype.config().building_figure_outside,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName:examineBuilding_.prototype.config().building_figure_outside,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });

                //
                FileUtils.uploadFiles({
                    target: examineBuilding_.prototype.config().building_floor_Appearance_figure,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName:examineBuilding_.prototype.config().building_floor_Appearance_figure,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                examineBuilding_.prototype.showFiles();
            },
            showFiles:function () {
                FileUtils.getFileShows({
                    target: examineBuilding_.prototype.config().building_floor_plan,
                    formData: {
                        fieldsName:examineBuilding_.prototype.config().building_floor_plan,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: examineBuilding_.prototype.config().building_figure_outside,
                    formData: {
                        fieldsName:examineBuilding_.prototype.config().building_figure_outside,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: examineBuilding_.prototype.config().building_floor_Appearance_figure,
                    formData: {
                        fieldsName:examineBuilding_.prototype.config().building_floor_Appearance_figure,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
            }
        }
    })();

</script>

<div id="divBoxExamineBuildingOutfit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋外装情况</h3>
            </div>
            <form id="frmExamineBuildingOutfit" class="form-horizontal">
                <input type="hidden"  name="id">
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
                                            <select required="required" name="decorationPart" class="form-control search-select select2 decorationPart">
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
                                            <select required="required" name="constructionTechnology" class="form-control search-select select2 constructionTechnology">
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
                                            <select required="required" name="materialPrice" class="form-control search-select select2 materialPrice">
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
                                            <select required="required" name="decoratingMaterial" class="form-control search-select select2 decoratingMaterial">
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
                    <button type="button" class="btn btn-primary" onclick="examineBuilding_.prototype.subDataSave();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

