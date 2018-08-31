<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <form class="form-horizontal">
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
                <div class="col-sm-3">
                    <label class="btn btn-success" onclick="examineBuilding_.prototype.getByNumberData(1,this)">楼栋基础(第一部分)</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-4">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary">
                            复制上部分
                        </label>
                        <label class="btn btn-primary" onclick="examineBuilding_.prototype.nav.prev(this)">
                            上一部分
                        </label>
                        <label class="btn btn-primary" onclick="examineBuilding_.prototype.nav.next(this)">
                            下一部分<span class="badge navTotal">总:0</span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    当前第<span class="badge navPageNum">1</span>部分
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
                            name="buildingStructurePid"
                            class="form-control search-select select2 buildingstructurePid">
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
        </div>

        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-5">
                </div>
                <div class="col-sm-2">
                    <label class="btn btn-success" onclick="examineBuilding_.prototype.saveData();">保存或者更新 <i
                            class="fa fa-user"></i></label>
                </div>
                <div class="col-sm-5">
                </div>
            </div>
        </div>
    </form>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.subShowModelData()"> 新增
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
            层面结构
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.examineBuildingSurfaceShowModelData()"> 新增
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
            维护结构
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.examineBuildingMaintenanceShowModelData()"> 新增
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

<script type="application/javascript">
    $(function () {
        //两个方法 都可以假如选项卡载入时 初始化
        ContainerFunForInit.building.push(examineBuilding_.prototype.viewInit);//初始化方法写入容器
        ContainerFunForInit.building.push(examineBuilding_.prototype.uploadFiles);//初始化方法写入容器
    });
</script>
<script type="application/javascript">

    var examineBuilding_;
    (function () {
        //配置的局部变量用做 对象属性 (初始化标识符)
        var flag = true;
        var sonFlag = true;
        var examineBuildingMaintenanceFlag = true;
        var examineBuildingSurfaceFlag = true;
        examineBuilding_ = function () {

        };
        examineBuilding_.prototype = {
            select2IsNotNull: function (data) {
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
            },
            setExamineBuildingSurfaceFlag: function (flag_) {
                examineBuildingSurfaceFlag = flag_;
            },
            getExamineBuildingSurfaceFlag: function () {
                return examineBuildingSurfaceFlag;
            },
            setExamineBuildingMaintenanceFlag: function (flag_) {
                examineBuildingMaintenanceFlag = flag_;
            },
            getExamineBuildingMaintenanceFlag: function () {
                return examineBuildingMaintenanceFlag;
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
            getBuildId: function () {
                var data = formParams(examineBuilding_.prototype.config().frm);
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
            },
            isEmpty: function (item) {
                if (item == null) {
                    return false;
                }
                if (item == "") {
                    return false;
                }
                if (item == '') {
                    return false;
                }
                return true;
            },
            objectWriteSelectData: function (frm, data, name) {
                if (examineBuilding_.prototype.isEmpty(data)) {
                    $("#" + frm + " ." + name).val(data).trigger("change");
                } else {
                    $("#" + frm + " ." + name).val(null).trigger("change");
                }
            },
            /**
             * @author:  zch
             * 描述:第一栋或者其它栋写数据
             * @date:
             **/
            dataNumberWrite: function (result) {
                $("#" + examineBuilding_.prototype.config().frm).initForm(result.data);
                $("#" + examineBuilding_.prototype.config().frm + " .openTime").val(formatDate(result.data.openTime));
                $("#" + examineBuilding_.prototype.config().frm + " .roomTime").val(formatDate(result.data.roomTime));
                var frm = examineBuilding_.prototype.config().frm;
                examineBuilding_.prototype.objectWriteSelectData(frm, result.data.buildingCategory, "buildingCategory");
                examineBuilding_.prototype.objectWriteSelectData(frm, result.data.buildingStructure, "buildingStructure");
                examineBuilding_.prototype.objectWriteSelectData(frm, result.data.buildingstructurePid, "buildingstructurePid");
                examineBuilding_.prototype.objectWriteSelectData(frm, result.data.propertyType, "propertyType");
                examineBuilding_.prototype.objectWriteSelectData(frm, result.data.builderId, "builderId");
                examineBuilding_.prototype.objectWriteSelectData(frm, result.data.propertyId, "propertyId");
            },
            /**
             * @author:  zch
             * 描述:
             * @date: 页面 初始化
             **/
            viewInit: function () {
                if (examineBuilding_.prototype.getFlag()) {
                    examineBuilding_.prototype.init();
                    examineBuilding_.prototype.initRemoveExamineBuildingOutfit();
                    examineBuilding_.prototype.nav.startWrite();
                    examineBuilding_.prototype.setFlag(false);
                }
                examineBuilding_.prototype.subLoadDataList();
                examineBuilding_.prototype.examineBuildingMaintenanceLoadList();
                examineBuilding_.prototype.examineBuildingSurfaceLoadList();
            },
            /**
             * @author:  zch
             * 描述:配置文件
             * @date:
             **/
            config: function () {
                var data = {};
                data.table = "ExamineBuilding_List";
                data.box = "divBoxExamineBuilding_";
                data.frm = "frmExamineBuilding_";
                data.sonBox = "divBoxExamineBuildingOutfit";
                data.sonTable = "ExamineBuildingOutfitList";
                data.sonFrm = "frmExamineBuildingOutfit";

                data.examineBuildingSurfaceTable = "ExamineBuildingSurfaceList";
                data.examineBuildingSurfaceFrm = "ExamineBuildingSurfaceFrm";
                data.examineBuildingSurfaceBox = "divBoxExamineBuildingSurface";

                data.examineBuildingMaintenanceTable = "ExamineBuildingMaintenanceList";
                data.examineBuildingMaintenanceFrm = "ExamineBuildingMaintenanceFrm";
                data.examineBuildingMaintenanceBox = "divBoxExamineBuildingMaintenance";

                data.type = "null";//
                data.database_Table = AssessDBKey.ExamineBuilding;//
                data.building_floor_plan = "building_floor_plan";//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
                data.building_figure_outside = "building_figure_outside";//外装图id和字段
                data.building_floor_Appearance_figure = "building_floor_Appearance_figure";//外观图id和字段
                return data;
            },
            /**
             * @author:  zch
             * 描述:更新导航栏数据信息
             * @date:
             **/
            nav: {
                startWrite: function () {
                    var data = {};
                    if ($("#declareId").size() > 0) {
                        data.declareId = $("#declareId").val();
                    }
                    if ($("#examineType").size() > 0) {
                        data.examineType = $("#examineType").val();
                    }
                    $.ajax({
                        url: "${pageContext.request.contextPath}/examineBuilding/getExamineBuildingList",
                        type: "get",
                        dataType: "json",
                        data: data,
                        success: function (result) {
                            $(".navTotal").html("总:" + result.total);
                            examineBuilding_.prototype.nav.writePageNum("0");
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                writePageNum: function (num) {
                    $(".navPageNum").html(num);
                },
                //下一部分
                next: function (item) {
                    var number = parseInt($(".navPageNum").html()) + 1;
                    examineBuilding_.prototype.getByNumberData(number,item);
                },
                //上一部分
                prev: function (item) {
                    var number = parseInt($(".navPageNum").html()) - 1;
                    examineBuilding_.prototype.getByNumberData(number,item);
                }
            },
            getByNumberData: function (number, item) {
                var data = {};
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                data.number = number;
                console.log(data);
                $("#" + examineBuilding_.prototype.config().frm).clearAll();
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getByNumberData",
                    type: "get",
                    data: data,
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            if (result.data != null) {
                                console.log(result);
                                //改变按钮颜色
                                $(item).removeClass();
                                $(item).addClass("btn btn-primary");
                                examineBuilding_.prototype.dataNumberWrite(result);
                            }else {
                                toastr.success('请重新选择!');
                            }
                            examineBuilding_.prototype.showFiles();
                            examineBuilding_.prototype.subLoadDataList();
                            examineBuilding_.prototype.examineBuildingMaintenanceLoadList();
                            examineBuilding_.prototype.examineBuildingSurfaceLoadList();
                            examineBuilding_.prototype.nav.writePageNum(data.number);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            /**
             * @author:  zch
             * 描述:楼栋外装 保存
             * @date:
             **/
            subDataSave: function () {
                if (!$("#" + examineBuilding_.prototype.config().sonFrm).valid()) {
                    return false;
                }
                var data = formParams(examineBuilding_.prototype.config().sonFrm);
                data.buildingId = examineBuilding_.prototype.getBuildId();
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/saveAndUpdateExamineBuildingOutfit",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + examineBuilding_.prototype.config().sonBox).modal('hide');
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
            /**
             * @author:  zch
             * 描述: 楼栋外装 删除
             * @date:
             **/
            subDataRemove: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/deleteExamineBuildingOutfitById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
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
            /**
             * @author:  zch
             * 描述: 楼栋外装 获取 并且在页面上赋值
             * @date:
             **/
            subGetAndInit: function (id) {
                var objectWrite = new Object();
                objectWrite.write = function (item, name) {
                    if (examineBuilding_.prototype.isEmpty(item)) {
                        $("#" + examineBuilding_.prototype.config().sonFrm + " ." + name).val(item).trigger("change");
                    } else {
                        $("#" + examineBuilding_.prototype.config().sonFrm + " ." + name).val(null).trigger("change");
                    }
                };
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + examineBuilding_.prototype.config().sonFrm).clearAll();
                            $("#" + examineBuilding_.prototype.config().sonFrm).initForm(result.data);
                            objectWrite.write(result.data.decoratingMaterial, "decoratingMaterial");
                            objectWrite.write(result.data.materialPrice, "materialPrice");
                            objectWrite.write(result.data.constructionTechnology, "constructionTechnology");
                            objectWrite.write(result.data.decorationPart, "decorationPart");
                            $('#' + examineBuilding_.prototype.config().sonBox).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            /**
             * @author:  zch
             * 描述:楼栋外装 显示添加框
             * @date:
             **/
            subShowModelData: function () {
                $("#" + examineBuilding_.prototype.config().sonFrm).clearAll();
                if (examineBuilding_.prototype.getSonFlag()) {
                    examineBuilding_.prototype.subDataInit();
                    examineBuilding_.prototype.setSonFlag(false);
                }
                $('#' + examineBuilding_.prototype.config().sonBox).modal("show");
            },
            examineBuildingMaintenanceShowModelData: function () {
                $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                if (examineBuilding_.prototype.getExamineBuildingMaintenanceFlag()) {
                    examineBuilding_.prototype.examineBuildingMaintenanceInit();
                    examineBuilding_.prototype.setExamineBuildingMaintenanceFlag(false);
                }
                $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal("show");
            },
            examineBuildingSurfaceShowModelData: function () {
                $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).clearAll();
                if (examineBuilding_.prototype.getExamineBuildingSurfaceFlag()) {
                    examineBuilding_.prototype.examineBuildingSurfaceInit();
                    examineBuilding_.prototype.setExamineBuildingSurfaceFlag(false);
                }
                $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal("show");
            },
            examineBuildingMaintenanceGetAndInit: function (id) {
                var objectWrite = new Object();
                objectWrite.write = function (data, name) {
                    if (examineBuilding_.prototype.isEmpty(data)) {
                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm + " ." + name).val(data).trigger("change");
                    } else {
                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm + " ." + name).val(null).trigger("change");
                    }
                };
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingMaintenance/getExamineBuildingMaintenanceById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (examineBuilding_.prototype.getExamineBuildingMaintenanceFlag()) {
                                examineBuilding_.prototype.examineBuildingMaintenanceInit();
                                examineBuilding_.prototype.setExamineBuildingMaintenanceFlag(false);
                            }
                            $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                            $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).initForm(result.data);
                            objectWrite.write(result.data.category, 'category');
                            objectWrite.write(result.data.materialQuality, 'materialQuality');
                            $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            examineBuildingSurfaceGetAndInit: function (id) {
                var objectWrite = new Object();
                objectWrite.write = function (data, name) {
                    if (examineBuilding_.prototype.isEmpty(data)) {
                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm + " ." + name).val(data).trigger("change");
                    } else {
                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm + " ." + name).val(null).trigger("change");
                    }
                };
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingSurface/getExamineBuildingSurfaceById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (examineBuilding_.prototype.getExamineBuildingSurfaceFlag()) {
                                examineBuilding_.prototype.examineBuildingSurfaceInit();
                                examineBuilding_.prototype.setExamineBuildingSurfaceFlag(false);
                            }
                            $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).clearAll();
                            $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).initForm(result.data);
                            objectWrite.write(result.data.structure, 'structure');
                            $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            examineBuildingMaintenanceRemove: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingMaintenance/deleteExamineBuildingMaintenanceById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            examineBuilding_.prototype.examineBuildingMaintenanceLoadList();
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
            examineBuildingSurfaceRemove: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingSurface/deleteExamineBuildingSurfaceById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            examineBuilding_.prototype.examineBuildingSurfaceLoadList();
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
            examineBuildingMaintenanceSave: function () {
                if (!$("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).valid()) {
                    return false;
                }
                var data = formParams(examineBuilding_.prototype.config().examineBuildingMaintenanceFrm);
                data.buildingId = examineBuilding_.prototype.getBuildId();
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingMaintenance/saveAndUpdateExamineBuildingMaintenance",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal('hide');
                            examineBuilding_.prototype.examineBuildingMaintenanceLoadList();
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
            examineBuildingSurfaceSave: function () {
                if (!$("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).valid()) {
                    return false;
                }
                var data = formParams(examineBuilding_.prototype.config().examineBuildingSurfaceFrm);
                data.buildingId = examineBuilding_.prototype.getBuildId();
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingSurface/saveAndUpdateExamineBuildingSurface",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal('hide');
                            examineBuilding_.prototype.examineBuildingSurfaceLoadList();
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
            examineBuildingMaintenanceInit: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingMaintenance/examine_building_maintenance_category",
                    type: "get",
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm + " .category").html(option);
                                $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm + " .category").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingMaintenance/examine_building_materialquality",
                    type: "get",
                    async: false,
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
                                $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm + " .materialQuality").html(option);
                                $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm + " .materialQuality").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            examineBuildingSurfaceInit: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingSurface/examine_building_structure",
                    type: "get",
                    async: false,
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
                                $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm + " .structure").html(option);
                                $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm + " .structure").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            examineBuildingSurfaceLoadList: function () {
                var cols = [];
                cols.push({field: 'structureName', title: '层面结构'});
                cols.push({field: 'description', title: '描述'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.examineBuildingSurfaceGetAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.examineBuildingSurfaceRemove(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                TableInit(examineBuilding_.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/examineBuildingSurface/getExamineBuildingSurfaceList", cols, {
                    declareId: $("#declareId").val(),
                    examineType: $("#examineType").val(),
                    buildingId: examineBuilding_.prototype.getBuildId()
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            examineBuildingMaintenanceLoadList: function () {
                var cols = [];
                cols.push({field: 'categoryName', title: '类别'});
                cols.push({field: 'materialQualityName', title: '材质'});
                cols.push({field: 'name', title: '名称'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.examineBuildingMaintenanceGetAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.examineBuildingMaintenanceRemove(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                TableInit(examineBuilding_.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/examineBuildingMaintenance/getExamineBuildingMaintenanceList", cols, {
                    declareId: $("#declareId").val(),
                    examineType: $("#examineType").val(),
                    buildingId: examineBuilding_.prototype.getBuildId()
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            subDataInit: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decorating_material",
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
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .decoratingMaterial").html(option);
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .decoratingMaterial").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_material_price",
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
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .materialPrice").html(option);
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .materialPrice").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_construction_technology",
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
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .constructionTechnology").html(option);
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .constructionTechnology").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decoration_part",
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
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .decorationPart").html(option);
                                $("#" + examineBuilding_.prototype.config().sonFrm + " .decorationPart").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            subLoadDataList: function () {
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
                $("#" + examineBuilding_.prototype.config().sonTable).bootstrapTable('destroy');
                TableInit(examineBuilding_.prototype.config().sonTable, "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitList", cols, {
                    declareId: $("#declareId").val(),
                    examineType: $("#examineType").val(),
                    buildingId: examineBuilding_.prototype.getBuildId()
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
                            examineBuilding_.prototype.firstAndTwoWrite(result);
                            $('#' + examineBuilding_.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            initRemoveExamineBuildingOutfit: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuildingOutfit/initRemoveExamineBuildingOutfit",
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            init: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_category, null, function (html, data) {
                    $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").html(html);
                    $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").select2();//加载样式
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, null, function (html, data) {
                    $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").html(html);
                    $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").select2();//加载样式
                });
                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").eq(1).val();
                    if (id != null && id != '' && id != 0) {
                        AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                            $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").html(html);
                            $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2();//加载样式
                        });
                    }
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, null, function (html, data) {
                    $("#" + examineBuilding_.prototype.config().frm + " .propertyType").html(html);
                    $("#" + examineBuilding_.prototype.config().frm + " .propertyType").select2();//加载样式
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                    type: "get",
                    dataType: "json",
                    data: {type: "type"},
                    success: function (result) {
                        if (result.ret) {
                            var item = result.data;
                            var option = "";
                            if (item.DataBuilder.length > 0) {
                                option = "<option value=''>请选择</option>";
                                var data = item.DataBuilder;
                                for (var i = 0; i < item.DataBuilder.length; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").html(option);
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").select2();//加载样式
                                option = "";
                            }
                            if (item.DataProperty.length > 0) {
                                option = "<option value=''>请选择</option>";
                                var data = item.DataProperty;
                                for (var i = 0; i < item.DataProperty.length; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyId").html(option);
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyId").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            uploadFiles: function () {
                FileUtils.uploadFiles({
                    target: examineBuilding_.prototype.config().building_floor_plan,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName: examineBuilding_.prototype.config().building_floor_plan,
                        tableName: examineBuilding_.prototype.config().database_Table,
                        tableId: examineBuilding_.prototype.getBuildId(),
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                examineBuilding_.prototype.showFiles();
            },
            showFiles: function () {
                FileUtils.getFileShows({
                    target: examineBuilding_.prototype.config().building_floor_plan,
                    formData: {
                        fieldsName: examineBuilding_.prototype.config().building_floor_plan,
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
                    <button type="button" class="btn btn-primary" onclick="examineBuilding_.prototype.subDataSave();">
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
                                            <input type="text"
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
                            onclick="examineBuilding_.prototype.examineBuildingMaintenanceSave()">
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
                                            <input type="text"
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
                            onclick="examineBuilding_.prototype.examineBuildingSurfaceSave()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

