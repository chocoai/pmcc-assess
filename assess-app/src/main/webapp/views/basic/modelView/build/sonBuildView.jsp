<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/18
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.showModel();"> 新增
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
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.showModel()"> 新增
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
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.showModel();"> 新增
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
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.showModel();"> 新增
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
    $(function () {
        examineBuilding_.prototype.viewInit();
    });
</script>
<script type="application/javascript">

    var examineBuilding_;
    (function () {
        //配置的局部变量用做 对象属性 (初始化标识符)
        var flag = true;
        var sonFlag = true; //子类标识符
        var examineBuildingMaintenanceFlag = true;//子类 标识符
        var examineBuildingSurfaceFlag = true;//子类 标识符
        var examineBuildingFunctionFlag = true;//子类 标识符
        var navButtonBuildFlag = false;//触发校验机制
        var attachmentId = null;//临时存储的附件id
        var objArray = new Array();
        examineBuilding_ = function () {

        };
        examineBuilding_.prototype = {
            //获取临时存储的附件id
            getAttachmentId: function () {
                return attachmentId;
            },
            //设置临时存储的附件id
            setAttachmentId: function (target) {
                attachmentId = target;
            },
            /**
             * @author:  zch
             * 描述:根据索引获取数据
             * @date:2018-09-03
             **/
            getObjArray: function (index) {
                var data = null;
                if (examineBuilding_.prototype.isEmpty(index)) {
                    data = objArray[index];
                    return data;
                }
            },
            /**
             * @author:  zch
             * 描述:设置数据
             * @date:2018-09-03
             **/
            setObjArrayElement: function (index, data) {
                objArray[index] = data;
            },
            setNavButtonBuildFlag: function (flag_) {
                navButtonBuildFlag = flag_;
            },
            getNavButtonBuildFlag: function () {
                return navButtonBuildFlag;
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
            getExamineBuildingFunctionFlag: function () {
                return examineBuildingFunctionFlag;
            },
            setExamineBuildingFunctionFlag: function (target) {
                examineBuildingFunctionFlag = target;
            },
            getIdentifier: function () {
                var data = formParams(examineBuilding_.prototype.config().frm);
                var identifier = data.identifier;
                if (examineBuilding_.prototype.isEmpty(identifier)) {
                    return identifier;
                }
                return "0";
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
                if (item) {
                    return true;
                }
                return false;
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
             * 描述:
             * @date: 页面 初始化
             **/
            viewInit: function () {
                examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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

                data.examineBuildingSurfaceBox = "divBoxExamineBuildingSurface";
                data.examineBuildingSurfaceTable = "ExamineBuildingSurfaceList";
                data.examineBuildingSurfaceFrm = "ExamineBuildingSurfaceFrm";

                data.examineBuildingMaintenanceBox = "divBoxExamineBuildingMaintenance";
                data.examineBuildingMaintenanceTable = "ExamineBuildingMaintenanceList";
                data.examineBuildingMaintenanceFrm = "ExamineBuildingMaintenanceFrm";

                data.examineBuildingFunctionBox = "examineBuildingFunction";
                data.examineBuildingFunctionTable = "examineBuildingFunctionList";
                data.examineBuildingFunctionFrm = "examineBuildingFunctionFrm";

                data.type = "null";//
                data.database_Table = AssessDBKey.ExamineBuilding;//
                data.building_floor_plan = "building_floor_plan";//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
                data.building_figure_outside = "building_figure_outside";//外装图id和字段
                data.building_floor_Appearance_figure = "building_floor_Appearance_figure";//外观图id和字段
                return data;
            },
            sonModelMethod: {
                /**楼栋外装情况**/
                buildingOutfit: {
                    getAndInit: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/getCaseBuildingOutfitById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getSonFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingOutfit.init();
                                        examineBuilding_.prototype.setSonFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().sonFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().sonFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.decoratingMaterial, "decoratingMaterial");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.materialPrice, "materialPrice");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.constructionTechnology, "constructionTechnology");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.decorationPart, "decorationPart");
                                    }
                                    $('#' + examineBuilding_.prototype.config().sonBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().sonFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().sonFrm);
                        data.buildingId = '${empty caseBuilding.id?0:caseBuilding.id}' ;
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/saveAndUpdateCaseBuildingOutfit",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().sonBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
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
                        $("#" + examineBuilding_.prototype.config().sonFrm).clearAll();
                        if (examineBuilding_.prototype.getSonFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingOutfit.init();
                            examineBuilding_.prototype.setSonFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().sonBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/deleteCaseBuildingOutfitById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
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
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_decorating_material",
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
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_material_price",
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
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_construction_technology",
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
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_decoration_part",
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
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'decorationPartName', title: '装修部位'});
                        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                        cols.push({field: 'materialPriceName', title: '材料价格区间'});
                        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().sonTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().sonTable, "${pageContext.request.contextPath}/caseBuildingOutfit/getCaseBuildingOutfitList", cols, {
                            buildingId: '${empty caseBuilding.id?0:caseBuilding.id}'
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
                            url: "${pageContext.request.contextPath}/caseBuildingSurface/getCaseBuildingSurfaceById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getExamineBuildingSurfaceFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingSurface.init();
                                        examineBuilding_.prototype.setExamineBuildingSurfaceFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingSurfaceFrm,
                                            data.structure, "structure");
                                    }
                                    $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().examineBuildingSurfaceFrm);
                        data.buildingId = '${empty caseBuilding.id?0:caseBuilding.id}' ;
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingSurface/saveAndUpdateCaseBuildingSurface",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
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
                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).clearAll();
                        if (examineBuilding_.prototype.getExamineBuildingSurfaceFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingSurface.init();
                            examineBuilding_.prototype.setExamineBuildingSurfaceFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingSurface/deleteCaseBuildingSurfaceById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
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
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingSurface/examine_building_structure",
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
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'structureName', title: '层面结构'});
                        cols.push({field: 'description', title: '描述'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/caseBuildingSurface/getCaseBuildingSurfaceList", cols, {
                            buildingId: '${empty caseBuilding.id?0:caseBuilding.id}'
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
                            url: "${pageContext.request.contextPath}/caseBuildingMaintenance/getCaseBuildingMaintenanceById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getExamineBuildingMaintenanceFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingMaintenance.init();
                                        examineBuilding_.prototype.setExamineBuildingMaintenanceFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(
                                            examineBuilding_.prototype.config().examineBuildingMaintenanceFrm, data.category, "category");
                                        examineBuilding_.prototype.objectWriteSelectData(
                                            examineBuilding_.prototype.config().examineBuildingMaintenanceFrm, data.materialQuality, "materialQuality");
                                    }
                                    $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().examineBuildingMaintenanceFrm);
                        data.buildingId = '${empty caseBuilding.id?0:caseBuilding.id}' ;
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingMaintenance/saveAndUpdateCaseBuildingMaintenance",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
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
                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                        if (examineBuilding_.prototype.getExamineBuildingMaintenanceFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingMaintenance.init();
                            examineBuilding_.prototype.setExamineBuildingMaintenanceFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingMaintenance/deleteCaseBuildingMaintenanceById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
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
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingMaintenance/examine_building_maintenance_category",
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
                            url: "${pageContext.request.contextPath}/caseBuildingMaintenance/examine_building_materialquality",
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
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'categoryName', title: '类别'});
                        cols.push({field: 'materialQualityName', title: '材质'});
                        cols.push({field: 'name', title: '名称'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/caseBuildingMaintenance/getCaseBuildingMaintenanceList", cols, {
                            buildingId: '${empty caseBuilding.id?0:caseBuilding.id}'
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
                            url: "${pageContext.request.contextPath}/caseBuildingFunction/getCaseBuildingFunctionById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getExamineBuildingFunctionFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingFunction.init();
                                        examineBuilding_.prototype.setExamineBuildingFunctionFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.decoratingMaterial, "decoratingMaterial");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.materialPrice, "materialPrice");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.constructionTechnology, "constructionTechnology");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.decorationPart, "decorationPart");
                                    }
                                    $('#' + examineBuilding_.prototype.config().examineBuildingFunctionBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().examineBuildingFunctionFrm);
                        data.buildingId = '${empty caseBuilding.id?0:caseBuilding.id}' ;
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingFunction/saveAndUpdateCaseBuildingFunction",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().examineBuildingFunctionBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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
                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).clearAll();
                        if (examineBuilding_.prototype.getExamineBuildingFunctionFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingFunction.init();
                            examineBuilding_.prototype.setExamineBuildingFunctionFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().examineBuildingFunctionBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingFunction/deleteCaseBuildingFunctionById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
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
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_decorating_material",
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
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .decoratingMaterial").html(option);
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .decoratingMaterial").select2({minimumResultsForSearch: -1});//加载样式
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_material_price",
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
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .materialPrice").html(option);
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .materialPrice").select2({minimumResultsForSearch: -1});//加载样式
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_construction_technology",
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
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .constructionTechnology").html(option);
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .constructionTechnology").select2({minimumResultsForSearch: -1});//加载样式
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseBuildingOutfit/examine_building_decoration_part",
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
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .decorationPart").html(option);
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm + " .decorationPart").select2({minimumResultsForSearch: -1});//加载样式
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
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
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().examineBuildingFunctionTable, "${pageContext.request.contextPath}/caseBuildingFunction/getCaseBuildingFunctionList", cols, {
                            buildingId: '${empty caseBuilding.id?0:caseBuilding.id}'
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
            },
            file: {

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
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.saveData();">
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
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.saveData();">
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
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.saveData();">
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
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

