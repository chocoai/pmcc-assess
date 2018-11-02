<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/27
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                在建工程（设备安装） &nbsp;&nbsp;&nbsp;&nbsp;
                <small>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success" onclick="equipmentInstallation.showAddModel();"
                                data-toggle="modal"> 新增
                        </button>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入设备安装
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn" onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftDeclareBuildEquipmentInstall)">下载模板</a></li>
                            <li><a class="btn btn-default" onclick="$('#equipmentInstallationUpload').val('').trigger('click')">导入</a></li>
                        </ul>
                    </div>
                </small>
            </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="equipmentInstallationTableList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="equipmentInstallationUpload" name="file" style="display: none;" onchange="equipmentInstallation.inputFile();">

<script>

    var equipmentInstallationConfig = {
        name: "设备安装",
        frm: "equipmentInstallationFrm",
        table: "equipmentInstallationTableList",
        box: "equipmentInstallationBox",
        fileId:"equipmentInstallationFileId",
        excelUpload:"equipmentInstallationUpload",
        declareBuildingConstructionPermit: {
            box: "declareBuildingConstructionPermitBoxE",
            frm: "declareBuildingConstructionPermitFrmE",
            fileId: "declareBuildingConstructionPermitFileIdE",
            name: "建筑工程施工许可证"
        },
        declarePreSalePermit:{
            box:"declarePreSalePermitBoxE",
            frm:"declarePreSalePermitFrmE",
            fileId:"declarePreSalePermitFileIdE",
            name: "商品房预售许可证"
        },
        declareLandUsePermit:{
            box:"declareLandUsePermitBoxE",
            frm:"declareLandUsePermitFrmE",
            fileId:"declareLandUsePermitFileIdE",
            name:"建设用地规划许可证"
        },
        declareBuildingPermit:{
            box:"declareBuildingPermitBoxE",
            frm:"declareBuildingPermitFrmE",
            fileId:"declareBuildingPermitFileIdE",
            name:"建设工程规划许可证"
        },
        declareRealtyLandCert:{
            box:"declareRealtyLandCertBoxE",
            frm:"declareRealtyLandCertFrmE",
            fileId:"declareRealtyLandCertFileIdE",
            name:"土地证"
        },
        declareRealtyRealEstateCert:{
            box:"declareRealtyRealEstateCertBoxE",
            frm:"declareRealtyRealEstateCertFrmE",
            fileId:"declareRealtyRealEstateCertFileIdE",
            name:"不动产"
        },
        declareEconomicIndicators: {
            box: "declareEconomicIndicatorsBox",
            frm: "declareEconomicIndicatorsFrm",
            fileId: "declareEconomicIndicatorsFileId",
            name: "经济指标"
        }
    };

    var equipmentInstallation = new Object();
    //处理标识符的地方-------start
    equipmentInstallation.equipmentInstallationFlag = true;
    equipmentInstallation.declareRealtyLandCertFlag = true;
    equipmentInstallation.declareRealtyRealEstateCertFlag = true;
    //----------------------end

    equipmentInstallation.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    equipmentInstallation.objectWriteSelectData = function (frm, data, name) {
        if (equipmentInstallation.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    equipmentInstallation.showFile = function (target,tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id,
                projectId: 0
            },
            deleteFlag: true
        });
    };

    equipmentInstallation.fileUpload = function (target,tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            onUpload: function (file) {
                var formData = {
                    fieldsName: target,
                    tableName: tableName,
                    tableId: id
                };
                return formData;
            }, onUploadComplete: function (result,file) {
                equipmentInstallation.showFile(target,tableName,id);
            },
            deleteFlag: true
        });
    };

    equipmentInstallation.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）显示
     * @date:2018-09-27
     **/
    equipmentInstallation.showAddModel = function () {
        $("#" + equipmentInstallationConfig.frm).clearAll();
        if (equipmentInstallation.equipmentInstallationFlag){
            equipmentInstallation.init();
            equipmentInstallation.equipmentInstallationFlag = false;
        }
        equipmentInstallation.showFile(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,0);
        equipmentInstallation.fileUpload(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,0);
        //使校验生效
        $("#" + equipmentInstallationConfig.frm).validate();
        $('#' + equipmentInstallationConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）更新
     * @date:2018-09-27
     **/
    equipmentInstallation.saveAndUpdateData = function () {
        if (!$("#" + equipmentInstallationConfig.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.frm);
        data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
        data.declareType = declareFunObj.getDeclareType("设备安装");
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/saveAndUpdateDeclareBuildEquipmentInstall",
            data: data,
            success: function (result) {
                if (result.ret) {
                    equipmentInstallation.loadList();
                    toastr.success('成功!');
                    $('#' + equipmentInstallationConfig.box).modal("hide");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）编辑
     * @date:2018-09-27
     **/
    equipmentInstallation.editData = function (id) {
        $("#" + equipmentInstallationConfig.frm).clearAll();
        if (equipmentInstallation.equipmentInstallationFlag){
            equipmentInstallation.init();
            equipmentInstallation.equipmentInstallationFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data ;
                    if (equipmentInstallation.isEmpty(data)){
                        $("#" + equipmentInstallationConfig.frm).initForm(result.data);
                        equipmentInstallation.showFile(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,data.id);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,data.id);
                        $("#" + equipmentInstallationConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + equipmentInstallationConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + equipmentInstallationConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + equipmentInstallationConfig.frm + "province"),
                            cityTarget: $("#" + equipmentInstallationConfig.frm + "city"),
                            districtTarget: $("#" + equipmentInstallationConfig.frm + "district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        });
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        //使校验生效
        $("#" + equipmentInstallationConfig.frm).validate();
        $('#' + equipmentInstallationConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）删除
     * @date:2018-09-27
     **/
    equipmentInstallation.deleteData = function (id) {
        Alert("是否删除",2,null,function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/deleteDeclareBuildEquipmentInstallById",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        equipmentInstallation.loadList();
                        toastr.success('成功!');
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        });
    };

    equipmentInstallation.handleFather = function (item) {
        //处理日期转换问题
        if (civilEngineering.isEmpty(item.declarationDate)) {
            item.declarationDate = formatDate(item.declarationDate);
        }
        if (civilEngineering.isEmpty(item.startDate)) {
            item.startDate = formatDate(item.startDate);
        }
        if (civilEngineering.isEmpty(item.expectedCompletionDate)) {
            item.expectedCompletionDate = formatDate(item.expectedCompletionDate);
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/saveAndUpdateDeclareBuildEquipmentInstall",
            data: item,
            success: function (result) {
                if (result.ret) {
                    equipmentInstallation.loadList();
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:建设工程规划许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingPermitView = function (id) {
        var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).clearAll();
        var buildingPermitId = item.buildingPermitId ;
        if (equipmentInstallation.isEmpty(buildingPermitId)){
            $.ajax({
                url: "${pageContext.request.contextPath}/declareBuildingPermit/getDeclareBuildingPermitById",
                type: "get",
                dataType: "json",
                data: {id: buildingPermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (equipmentInstallation.isEmpty(data)) {
                            data.pid = id;
                            $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).initForm(data);
                            $("#" + equipmentInstallationConfig.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                            equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }else {
            $("#" + equipmentInstallationConfig.declareBuildingPermit.frm).initForm({pid: id});
            equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
        }
        $('#' + equipmentInstallationConfig.declareBuildingPermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:建设工程规划许可证 更新
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingPermitSaveAndUpdate = function () {
        if (!$("#" + equipmentInstallationConfig.declareBuildingPermit.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.declareBuildingPermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildingPermit/saveAndUpdateDeclareBuildingPermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + equipmentInstallationConfig.declareBuildingPermit.box).modal("hide");
                    var item = result.data;
                    if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.buildingPermitId = item;
                        equipmentInstallation.handleFather(fData);
                        toastr.success('成功!');
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:建设用地规划许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareLandUsePermitView = function (id) {
        var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).clearAll();
        var landUsePermitId = item.landUsePermitId ;
        if (equipmentInstallation.isEmpty(landUsePermitId)){
            $.ajax({
                url: "${pageContext.request.contextPath}/declareLandUsePermit/getDeclareLandUsePermitById",
                type: "get",
                dataType: "json",
                data: {id: landUsePermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (equipmentInstallation.isEmpty(data)) {
                            data.pid = id;
                            $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).initForm(data);
                            $("#" + equipmentInstallationConfig.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                            equipmentInstallation.showFile(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }else {
            $("#" + equipmentInstallationConfig.declareLandUsePermit.frm).initForm({pid: id});
            equipmentInstallation.showFile(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
        }
        $('#' + equipmentInstallationConfig.declareLandUsePermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:建设用地规划许可证 更新
     * @date:2018-09-28
     **/
    equipmentInstallation.declareLandUsePermitSaveAndUpdate = function () {
        if (!$("#" + equipmentInstallationConfig.declareLandUsePermit.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.declareLandUsePermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareLandUsePermit/saveAndUpdateDeclareLandUsePermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + equipmentInstallationConfig.declareLandUsePermit.box).modal("hide");
                    var item = result.data;
                    if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.landUsePermitId = item;
                        equipmentInstallation.handleFather(fData);
                        toastr.success('成功!');
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:商品房预售许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declarePreSalePermitView = function (id) {
        var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).clearAll();
        var preSalePermitId = item.preSalePermitId;
        if (equipmentInstallation.isEmpty(preSalePermitId)){
            $.ajax({
                url: "${pageContext.request.contextPath}/declarePreSalePermit/getDeclarePreSalePermitById",
                type: "get",
                dataType: "json",
                data: {id: preSalePermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (equipmentInstallation.isEmpty(data)) {
                            data.pid = id;
                            $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).initForm(data);
                            $("#" + equipmentInstallationConfig.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                            equipmentInstallation.showFile(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                            equipmentInstallation.fileUpload(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }else {
            $("#" + equipmentInstallationConfig.declarePreSalePermit.frm).initForm({pid: id});
            equipmentInstallation.showFile(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
            equipmentInstallation.fileUpload(equipmentInstallationConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
        }
        $('#' + equipmentInstallationConfig.declarePreSalePermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:商品房预售许可证 更新
     * @date:2018-09-28
     **/
    equipmentInstallation.declarePreSalePermitSaveAndUpdate = function () {
        if (!$("#" + equipmentInstallationConfig.declarePreSalePermit.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.declarePreSalePermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declarePreSalePermit/saveAndUpdateDeclarePreSalePermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + equipmentInstallationConfig.declarePreSalePermit.box).modal("hide");
                    var item = result.data;
                    if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.preSalePermitId = item;
                        equipmentInstallation.handleFather(fData);
                        toastr.success('成功!');
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:建筑工程施工许可证 view
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingConstructionPermitView = function (id) {
        var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).clearAll();
        var buildingConstructionPermitId = item.buildingConstructionPermitId;
        if (equipmentInstallation.isEmpty(buildingConstructionPermitId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                type: "get",
                dataType: "json",
                data: {id: buildingConstructionPermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (equipmentInstallation.isEmpty(data)) {
                            data.pid = id;
                            $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).initForm(data);
                            $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                            $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                            equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            $("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).initForm({pid: id});
            equipmentInstallation.showFile(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
        }
        $('#' + equipmentInstallationConfig.declareBuildingConstructionPermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:建筑工程施工许可证 更新
     * @date:2018-09-28
     **/
    equipmentInstallation.declareBuildingConstructionPermitSaveAndUpdate = function () {
        if (!$("#" + equipmentInstallationConfig.declareBuildingConstructionPermit.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.declareBuildingConstructionPermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildingConstructionPermit/saveAndUpdateDeclareBuildingConstructionPermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + equipmentInstallationConfig.declareBuildingConstructionPermit.box).modal("hide");
                    var item = result.data;
                    if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.buildingConstructionPermitId = item;
                        equipmentInstallation.handleFather(fData);
                        toastr.success('成功!');
                    }
                } else {
                    console.log(result.errmsg);
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:土地证
     * @date:2018-09-28
     **/
    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated = {
        init:function () {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='unit']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='unit']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='floor']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='floor']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .district").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
                if (equipmentInstallation.isEmpty(id)) {
                    equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
        },
        write:function () {
            var temp = null;
            var district = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
            var unit = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='unit']").val();
            var floor = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='floor']").val();
            var roomNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val();
            var streetNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val();
            var attachedNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val();
            var buildingNumber = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val();
            if (!equipmentInstallation.isEmpty(unit)) {
                unit = "";
            } else {
                unit = unit + "单元";
            }
            if (!equipmentInstallation.isEmpty(floor)) {
                floor = "";
            } else {
                floor = floor + "楼";
            }
            if (!equipmentInstallation.isEmpty(roomNumber)) {
                roomNumber = "";
            } else {
                roomNumber = roomNumber + "号";
            }
            if (!equipmentInstallation.isEmpty(streetNumber)) {
                streetNumber = "";
            }
            if (!equipmentInstallation.isEmpty(attachedNumber)) {
                attachedNumber = "";
            } else {
                attachedNumber =   "附" + attachedNumber;
            }
            if (!equipmentInstallation.isEmpty(buildingNumber)) {
                buildingNumber = "";
            } else {
                buildingNumber = buildingNumber + "栋";
            }
            if (equipmentInstallation.isEmpty(district)) {
                AssessCommon.getAreaById(district, function (data) {
                    if (!equipmentInstallation.isEmpty(data)) {
                        district = "";
                    } else {
                        district = data.name;
                    }
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
                });
            } else {
                district = "";
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
            }
        }
    };
    equipmentInstallation.declareRealtyLandCertCertRoleCertName = {
        init:function () {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='location']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='location']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='year']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='year']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='number']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='number']").val())){
                    equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .type").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
                if (equipmentInstallation.isEmpty(id)) {
                    equipmentInstallation.declareRealtyLandCertCertRoleCertName.write();
                }
            });
        },
        write:function () {
            var temp = "" ;
            var id = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
            var location = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='location']").val();
            var year = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='year']").val();
            var number = $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='number']").val();
            if (equipmentInstallation.isEmpty(id)) {
                AssessCommon.getDataDicInfo(id, function (data) {
                    if (equipmentInstallation.isEmpty(data)) {
                        var temp = location +  data.name +year+ "第" + number + "号";
                        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                    }
                });
            } else {
                var temp = location +  year+ "第" + number + "号";
                $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
            }
        }
    };
    equipmentInstallation.declareRealtyLandCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType,'', function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).find('select.useRightType').empty().html(html).trigger('change');
        });
        equipmentInstallation.declareRealtyLandCertCertRoleCertName.init();
        equipmentInstallation.declareRealtyLandCertCertRoleBeLocated.init();
    };
    equipmentInstallation.declareRealtyLandCertView = function (id) {
        if (equipmentInstallation.declareRealtyLandCertFlag){
            equipmentInstallation.declareRealtyLandCertInit();
            equipmentInstallation.declareRealtyLandCertFlag = false;
        }
        var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', id);
        var landId = item.landId ;
        $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).clearAll();
        if (equipmentInstallation.isEmpty(landId)){
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                type: "get",
                dataType: "json",
                data: {id: landId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (equipmentInstallation.isEmpty(data)) {
                            data.pidC = id;
                            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).initForm(data);
                            equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm,data.purpose,"purpose");
                            equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm,data.type,"type");
                            equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyLandCert.frm,data.useRightType,"useRightType");
                            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                            equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "province"),
                                cityTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "city"),
                                districtTarget: $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm + "district"),
                                provinceValue: data.province,
                                cityValue: data.city,
                                districtValue: data.district
                            });
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }else {
            $("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).initForm({pidC: id});
            equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
        }
        $('#' + equipmentInstallationConfig.declareRealtyLandCert.box).modal("show");
    };
    equipmentInstallation.declareRealtyLandCertSaveAndUpdate = function () {
        if (!$("#" + equipmentInstallationConfig.declareRealtyLandCert.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.declareRealtyLandCert.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + equipmentInstallationConfig.declareRealtyLandCert.box).modal("hide");
                    var item = result.data;
                    if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pidC = data.pidC;
                        var fData = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', pidC);
                        fData.landId = item;
                        equipmentInstallation.handleFather(fData);
                        toastr.success('成功!');
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    /**
     * @author:  zch
     * 描述:不动产
     * @date:2018-09-28
     **/
    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated = {
        init:function () {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .district").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
                if (equipmentInstallation.isEmpty(id)) {
                    equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
        },
        write:function () {
            var temp = "";
            var district = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
            var unit = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val();
            var floor = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val();
            var roomNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val();
            var streetNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val();
            var attachedNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val();
            var buildingNumber = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val();
            if (!equipmentInstallation.isEmpty(unit)) {
                unit = "";
            } else {
                unit = unit + "单元";
            }
            if (!equipmentInstallation.isEmpty(floor)) {
                floor = "";
            } else {
                floor = floor + "楼";
            }
            if (!equipmentInstallation.isEmpty(roomNumber)) {
                roomNumber = "";
            } else {
                roomNumber = roomNumber + "号";
            }
            if (!equipmentInstallation.isEmpty(streetNumber)) {
                streetNumber = "";
            }
            if (!equipmentInstallation.isEmpty(attachedNumber)) {
                attachedNumber = "";
            } else {
                attachedNumber = "附" + attachedNumber;
            }
            if (!equipmentInstallation.isEmpty(buildingNumber)) {
                buildingNumber = "";
            } else {
                buildingNumber = buildingNumber + "栋";
            }
            if (equipmentInstallation.isEmpty(district)) {
                AssessCommon.getAreaById(district, function (data) {
                    if (!equipmentInstallation.isEmpty(data)) {
                        district = "";
                    } else {
                        district = data.name;
                    }
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
                });
            } else {
                district = "";
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
            }
        }
    };
    equipmentInstallation.declareRealtyRealEstateCertRoleCertName = {
        init:function () {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='location']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleCertName.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='number']").blur(function () {
                if (equipmentInstallation.isEmpty($("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val())){
                    equipmentInstallation.declareRealtyRealEstateCertRoleCertName.write();
                }
            });
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .type").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
                if (equipmentInstallation.isEmpty(id)) {
                    equipmentInstallation.declareRealtyRealEstateCertRoleCertName.write();
                }
            });
        },
        write:function () {
            var location = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val();
            var number = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val();
            var id = $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
            if (!equipmentInstallation.isEmpty(location)) {
                location = "";
            }
            if (!equipmentInstallation.isEmpty(number)) {
                number = "";
            }
            if (!equipmentInstallation.isEmpty(id)) {
                id = "";
            }
            if (equipmentInstallation.isEmpty(id)) {
                AssessCommon.getDataDicInfo(id, function (data) {
                    if (equipmentInstallation.isEmpty(data)) {
                        var temp = location + "房权证" + data.name + "字地" + number + "号";
                        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
                    }
                });
            } else {
                var temp = location + "房权证" + id + "字地" + number + "号";
                $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
            }
        }
    };
    equipmentInstallation.declareRealtyRealEstateCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType,'', function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareUseRightType, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.useRightType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareCommonSituation, "",function (html, data) {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).find('select.publicSituation').empty().html(html).trigger('change');
        });
        equipmentInstallation.declareRealtyRealEstateCertRoleCertName.init();
        equipmentInstallation.declareRealtyRealEstateCertRoleBeLocated.init();
    };
    equipmentInstallation.declareRealtyRealEstateCertView = function (id) {
        if (equipmentInstallation.declareRealtyRealEstateCertFlag){
            equipmentInstallation.declareRealtyRealEstateCertInit();
            equipmentInstallation.declareRealtyRealEstateCertFlag = false;
        }
        var item = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', id);
        var realEstateId = item.realEstateId ;
        $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).clearAll();
        if (equipmentInstallation.isEmpty(realEstateId)){
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                type: "get",
                dataType: "json",
                data: {id: realEstateId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (equipmentInstallation.isEmpty(data)) {
                            data.pidC = id;
                            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).initForm(data);
                            equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,data.purpose,"purpose");
                            equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,data.type,"type");
                            equipmentInstallation.objectWriteSelectData(equipmentInstallationConfig.declareRealtyRealEstateCert.frm,data.useRightType,"useRightType");
                            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                            equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "province"),
                                cityTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "city"),
                                districtTarget: $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm + "district"),
                                provinceValue: data.province,
                                cityValue: data.city,
                                districtValue: data.district
                            });
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }else {
            $("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).initForm({pidC: id});
            equipmentInstallation.showFile(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
            equipmentInstallation.fileUpload(equipmentInstallationConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
        }
        $('#' + equipmentInstallationConfig.declareRealtyRealEstateCert.box).modal("show");
    };
    equipmentInstallation.declareRealtyRealEstateCertSaveAndUpdate = function () {
        if (!$("#" + equipmentInstallationConfig.declareRealtyRealEstateCert.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.declareRealtyRealEstateCert.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + equipmentInstallationConfig.declareRealtyRealEstateCert.box).modal("hide");
                    var item = result.data;
                    if (equipmentInstallation.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pidC = data.pidC;
                        var fData = $("#" + equipmentInstallationConfig.table).bootstrapTable('getRowByUniqueId', pidC);
                        fData.realEstateId = item;
                        equipmentInstallation.handleFather(fData);
                        toastr.success('成功!');
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     * 经济指标
     */
    equipmentInstallation.declareEconomicIndicatorsView = function (id) {
        $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).clearAll();
        $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('[name=pid]').val(id);
        $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('[name=planDetailsId]').val('${projectPlanDetails.id}');
        $("#" + equipmentInstallationConfig.declareEconomicIndicators.frm).find('.dynamic').remove();
        economicIndicators.initForm(id);
        $('#' + equipmentInstallationConfig.declareEconomicIndicators.box).modal("show");
    };


    equipmentInstallation.loadList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'bookEquipmentFee', title: '账面设备费'});
        cols.push({field: 'bookCapitalCost', title: '账面资金成本'});
        cols.push({field: 'bookInstallationFee', title: '账面安装费'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown' id='dropdownMenu2'>"+"操作" +"<span class='caret'>"+"</span>"+"</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu2'>" ;
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.deleteData(" +row.id+")'"+">"+"删除"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.editData(" +row.id+")'"+">"+"编辑"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyLandCertView(" +row.id+")'"+">"+"土地证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareRealtyRealEstateCertView(" +row.id+")'"+">"+"不动产"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingPermitView(" +row.id+")'"+">"+"建设工程规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareLandUsePermitView(" +row.id+")'"+">"+"建设用地规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareBuildingConstructionPermitView(" +row.id+")'"+">"+"建筑工程施工许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declarePreSalePermitView(" +row.id+")'"+">"+"商品房预售许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='equipmentInstallation.declareEconomicIndicatorsView(" + row.id + ")'" + ">" + "经济规划指标" + "</a>" + "</li>";
                str += "</ul>" ;
                str += "</div>";
                return str;
            }
        });
        $("#" + equipmentInstallationConfig.table).bootstrapTable('destroy');
        TableInit(equipmentInstallationConfig.table, "${pageContext.request.contextPath}/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    /**
     * @author:  zch
     * 描述:导入
     * @date:2018-09-28
     **/
    equipmentInstallation.inputFile = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/importData",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId:equipmentInstallationConfig.excelUpload,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret){
                    equipmentInstallation.loadList();
                    Alert(result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    $(function () {
        equipmentInstallation.loadList();
    });
</script>

<!-- 建设工程规划许可证 -->
<div id="declareBuildingPermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设工程规划许可证</h3>
            </div>
            <form id="declareBuildingPermitFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设单位（个人）<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设单位（个人）" name="unit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设位置<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设位置"  name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareBuildingPermitFileIdE"
                                                   name="declareBuildingPermitFileIdE"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingPermitFileIdE"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="equipmentInstallation.declareBuildingPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建设用地规划许可证 -->
<div id="declareLandUsePermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设用地规划许可证</h3>
            </div>
            <form id="declareLandUsePermitFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地单位" name="unit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地位置<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地位置" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地性质<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地性质" name="nature" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">用地面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true' name="area" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareLandUsePermitFileIdE"
                                                   name="declareLandUsePermitFileIdE"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareLandUsePermitFileIdE"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="equipmentInstallation.declareLandUsePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 商品房预售许可证 -->
<div id="declarePreSalePermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">商品房预售许可证</h3>
            </div>
            <form id="declarePreSalePermitFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">售房单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="售房单位" name="salesUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">法定代表人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="法定代表人" name="legalRepresentative" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售范围<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售范围" name="preSaleScope" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售面积(数字)" data-rule-maxlength="100" data-rule-number='true' name="preSaleArea" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋用途<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋用途" name="housingUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑结构<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑结构" name="buildingStructure" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预售款监管信息<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="预售款监管信息" name="preSaleSupervisionInformation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">在建工程抵押情况<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declarePreSalePermitFileIdE"
                                                   name="declareBuildingConstructionPermitFileIdE"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declarePreSalePermitFileIdE"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="equipmentInstallation.declarePreSalePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑工程施工许可证</h3>
            </div>
            <form id="declareBuildingConstructionPermitFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证书编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证书编号" name="certificateNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">发证机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="发证机关" name="issuingOrgan" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="日期"
                                                   name="date" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设单位（个人）<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设单位（个人）" name="buildUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设地址<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设地址" name="buildAddress" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建设规模<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建设规模" name="scaleConstruction" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">勘察单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="勘察单位" name="reconnaissanceUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">设计单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="设计单位" name="designUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">施工单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="施工单位" name="constructionUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">监理单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="监理单位" name="constructionControlUnit"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">勘察单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">设计单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">施工单位项目负责人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="施工单位项目负责人" name="constructionUnitPerson"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">总监理工程师<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                                                   class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">合同工期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="合同工期"
                                                   name="contractPeriod" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareBuildingConstructionPermitFileIdE"
                                                   name="declareBuildingConstructionPermitFileIdE"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingConstructionPermitFileIdE"></div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="equipmentInstallation.declareBuildingConstructionPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 在建工程（设备安装） -->
<div id="equipmentInstallationBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">设备安装</h3>
            </div>
            <form id="equipmentInstallationFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province" id="equipmentInstallationFrmprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="equipmentInstallationFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="equipmentInstallationFrmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">占有单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="占有单位" name="occupancyUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">规格型号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="规格型号" name="specificationModel" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">生产厂家<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="生产厂家" name="manufacturer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">计量单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="计量单位" name="measurementUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">数量<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                   placeholder="数量(数字)" name="number" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">开工日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="开工日期"
                                                   name="startDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预期完成日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="预期完成日期"
                                                   name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面设备费<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面设备费" name="bookEquipmentFee" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面资金成本<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面资金成本" name="bookCapitalCost" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面安装费<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面安装费" name="bookInstallationFee" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="申报人" name="declarer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="申报日期"
                                                   name="declarationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="remark" required="required"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="equipmentInstallationFileId" name="equipmentInstallationFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_equipmentInstallationFileId"></div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="equipmentInstallation.saveAndUpdateData();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 土地证 -->
<div id="declareRealtyLandCertBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="declareRealtyLandCertFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pidC">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyLandCertFrmEprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCertFrmEcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            县(区)
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCertFrmEdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            所在地
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="location" required="required" class="form-control" placeholder="所在地">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用权人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="ownership" required="required"  class="form-control" placeholder="土地使用权人">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地权证号
                                        </label>
                                        <div class="col-sm-11">
                                            <input type="text" name="landCertName" readonly="readonly" class="form-control" placeholder="土地权证号">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            年份
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" data-rule-maxlength="100" data-rule-number='true' name="year" class="form-control" placeholder="年份(数字如:2018)">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            编号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" name="number" class="form-control" placeholder="编号">
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房屋坐落" name="beLocated" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">街道号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="街道号" name="streetNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附号</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附号(数字)" name="attachedNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">栋号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">地号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="地号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">图号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="图号" name="graphNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="purpose"
                                                    class="form-control search-select select2 purpose">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">取得价格<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="取得价格" name="acquisitionPrice" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            使用权类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="useRightType" class="form-control search-select select2 useRightType">
                                                <option value="请选择">请选择</option>
                                                <option value="划拨" name="useRightType">划拨</option>
                                                <option value="出证" name="useRightType">出证</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            终止日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="终止日期"
                                                   name="terminationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">使用权面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">独用面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">登记机关<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="登记机关" name="registrationAuthority" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记日期"
                                                   name="registrationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">记事<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyLandCertFileIdE" name="declareRealtyLandCertFileIdE"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyLandCertFileIdE"></div>
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
                    <button type="button" class="btn btn-primary" onclick="equipmentInstallation.declareRealtyLandCertSaveAndUpdate();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 不动产 -->
<div id="declareRealtyRealEstateCertBoxE" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产</h3>
            </div>
            <form id="declareRealtyRealEstateCertFrmE" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pidC">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyRealEstateCertFrmEprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyRealEstateCertFrmEcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            县(区)
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyRealEstateCertFrmEdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房产权证号<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房产权证号" name="certName" class="form-control">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">所在地<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="所在地" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">编号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                   placeholder="编号(数字)" name="number" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房产证类型<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control search-select select2 type"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋所有权人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋所有权人" name="ownership" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="publicSituation" class="form-control search-select select2 publicSituation"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="floorArea" class="form-control" data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <input type="text" readonly="readonly"
                                                   placeholder="房屋坐落" name="beLocated" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">街道号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="街道号" name="streetNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附号(数字)" name="attachedNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">栋号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记时间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记时间"
                                                   name="registrationTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋性质<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋性质" name="nature" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">规划用途<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="规划用途" name="planningUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">总层数<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="总层数(数字)" name="floorCount" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证载面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">套内面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="套内面积(数字)" name="innerArea" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地证号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">土地取得方式<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地取得方式" name="landAcquisition" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用年限起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="土地使用年限起"
                                                   name="useStartDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用年限止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="土地使用年限止"
                                                   name="useEndDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">公摊面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="公摊面积(数字)" name="publicArea" class="form-control"
                                                   required="required" data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附记其它<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附记其它" name="otherNote" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="登记机关" name="registrationAuthority" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            登记日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="登记日期"
                                                   name="registrationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">地号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="地号" name="landNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">图号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="图号" name="graphNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="purpose"
                                                    class="form-control search-select select2 purpose">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">取得价格<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="取得价格" name="acquisitionPrice" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            使用权类型
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="useRightType" class="form-control search-select select2 useRightType">
                                                <option value="请选择">请选择</option>
                                                <option value="划拨" name="useRightType">划拨</option>
                                                <option value="出证" name="useRightType">出证</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            终止日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input placeholder="终止日期"
                                                   name="terminationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">使用权面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">独用面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">记事<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyRealEstateCertFileIdE" name="declareRealtyRealEstateCertFileIdE"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyRealEstateCertFileIdE"></div>
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
                    <label class="btn btn-primary" onclick="equipmentInstallation.declareRealtyRealEstateCertSaveAndUpdate()">
                        保存
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>

